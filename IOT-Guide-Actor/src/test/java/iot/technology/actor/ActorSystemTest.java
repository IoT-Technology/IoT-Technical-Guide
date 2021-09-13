package iot.technology.actor;

import iot.technology.actor.message.IntActorMsg;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mushuwei
 */
@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class ActorSystemTest {

    public static final String ROOT_DISPATCHER = "root-dispatcher";
    private static final int _100K = 10;
    public static final int TIMEOUT_AWAIT_MAX_SEC = 100;

    private volatile ActorSystem actorSystem;
    private volatile ExecutorService submitPool;
    private int parallelism;

    @Before
    public void initActorSystem() {
        int cores = Runtime.getRuntime().availableProcessors();
        parallelism = Math.max(2, cores / 2);
        ActorSystemSettings settings = new ActorSystemSettings(5, parallelism, 42);
        actorSystem = new DefaultActorSystem(settings);
        submitPool = Executors.newFixedThreadPool(parallelism);
    }

    @After
    public void shutdownActorSystem() {
        actorSystem.stop();
        submitPool.shutdownNow();
    }

    @Test
    public void test1actorsAnd100KMessages() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        testActorsAndMessages(1, _100K, 1);
    }

    @Test
    public void testFailedInit() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        ActorTestCtx testCtx1 = getActorTestCtx(1);
        ActorTestCtx testCtx2 = getActorTestCtx(1);

        ActorRef actorId1 = actorSystem.createRootActor(ROOT_DISPATCHER,
                new FailedToInitActor.FailedToInitActorCreator(
                        new EntityActorId(UUID.randomUUID().toString()), testCtx1, 1, 3000));
        ActorRef actorId2 = actorSystem.createRootActor(ROOT_DISPATCHER,
                new FailedToInitActor.FailedToInitActorCreator(
                        new EntityActorId(UUID.randomUUID().toString()), testCtx2, 2, 1));

        actorId1.tell(new IntActorMsg(42));
        actorId2.tell(new IntActorMsg(42));

        Assert.assertFalse(testCtx1.getLatch().await(2, TimeUnit.SECONDS));
        Assert.assertFalse(testCtx1.getLatch().await(1, TimeUnit.SECONDS));
        Assert.assertFalse(testCtx1.getLatch().await(3, TimeUnit.SECONDS));
    }


    public void testActorsAndMessages(int actorsCount, int msgNumber, int times) throws InterruptedException {
        Random random = new Random();
        int[] randomIntegers = new int[msgNumber];
        long sumTmp = 0;
        for (int i = 0; i < msgNumber; i++) {
            int tmp = random.nextInt();
            randomIntegers[i] = tmp;
            sumTmp += tmp;
        }

        long expected = sumTmp;

        List<ActorTestCtx> testCtxes = new ArrayList<>();

        List<ActorRef> actorRefs = new ArrayList<>();
        for (int actorIdx = 0; actorIdx < actorsCount; actorIdx++) {
            ActorTestCtx testCtx = getActorTestCtx(msgNumber);
            actorRefs.add(actorSystem.createRootActor(ROOT_DISPATCHER,
                    new TestRootActor.TestRootActorCreator(new EntityActorId("actor"), testCtx)));
            testCtxes.add(testCtx);
        }

        for (int t = 0; t < times; t++) {
            long start = System.nanoTime();
            for (int i = 0; i < msgNumber; i++) {
                int tmp = randomIntegers[i];
                submitPool.execute(() -> actorRefs.forEach(actorId -> actorId.tell(new IntActorMsg(tmp))));
            }
            log.info("Submitted all messages");
            testCtxes.forEach(ctx -> {
                try {
                    boolean success = ctx.getLatch().await(1, TimeUnit.MINUTES);
                    if (!success) {
                        log.warn("Failed: {}, {}", ctx.getActual().get(), ctx.getInvocationCount().get());
                    }
                    Assert.assertTrue(success);
                    Assert.assertEquals(expected, ctx.getActual().get());
                    Assert.assertEquals(msgNumber, ctx.getInvocationCount().get());
                    ctx.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            long duration = System.nanoTime() - start;
            log.info("Time spend: {}ns ({} ms)", duration, TimeUnit.NANOSECONDS.toMillis(duration));
        }
    }

    private ActorTestCtx getActorTestCtx(int i) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        AtomicLong actual = new AtomicLong();
        AtomicInteger invocations = new AtomicInteger();
        return new ActorTestCtx(countDownLatch, invocations, i, actual);
    }
}
