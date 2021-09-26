package iot.technology.actor;

import iot.technology.actor.common.ActorSystemSettings;
import iot.technology.actor.core.ActorRef;
import iot.technology.actor.message.ActorId;
import iot.technology.actor.message.EntityActorId;
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
    private static final int _100K = 100 * 1000;
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
    public void test10actorsAnd100KMessages() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        testActorsAndMessages(10, _100K, 1);
    }

    @Test
    public void testNoMessagesAfterDestroy() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        ActorTestCtx testCtx1 = getActorTestCtx(1);
        ActorTestCtx testCtx2 = getActorTestCtx(1);

        ActorRef actorId1 = actorSystem.createRootActor(ROOT_DISPATCHER, new SlowInitActor.SlowInitActorCreator(
                new EntityActorId("test-entity-1"), testCtx1));
        ActorRef actorId2 = actorSystem.createRootActor(ROOT_DISPATCHER, new SlowInitActor.SlowInitActorCreator(
                new EntityActorId("test-entity-2"), testCtx2));

        actorId1.tell(new IntActorMsg(42));
        actorId2.tell(new IntActorMsg(42));
        actorSystem.stop(actorId1);

        Assert.assertTrue(testCtx2.getLatch().await(1, TimeUnit.SECONDS));
        Assert.assertFalse(testCtx1.getLatch().await(1, TimeUnit.SECONDS));
    }

    @Test
    public void testOneActorCreated() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        ActorTestCtx testCtx1 = getActorTestCtx(1);
        ActorTestCtx testCtx2 = getActorTestCtx(1);
        ActorId actorId = new EntityActorId("test-entity");
        final CountDownLatch initLatch = new CountDownLatch(1);
        final CountDownLatch actorsReadyLatch = new CountDownLatch(2);
        submitPool.submit(() -> {
            actorSystem.createRootActor(ROOT_DISPATCHER, new SlowCreateActor.SlowCreateActorCreator(actorId, testCtx1, initLatch));
            actorsReadyLatch.countDown();
        });
        submitPool.submit(() -> {
            actorSystem.createRootActor(ROOT_DISPATCHER, new SlowCreateActor.SlowCreateActorCreator(actorId, testCtx2, initLatch));
            actorsReadyLatch.countDown();
        });
        initLatch.countDown();
        Assert.assertTrue(actorsReadyLatch.await(TIMEOUT_AWAIT_MAX_SEC, TimeUnit.SECONDS));

        actorSystem.tell(actorId, new IntActorMsg(42));

        Assert.assertTrue(testCtx1.getLatch().await(TIMEOUT_AWAIT_MAX_SEC, TimeUnit.SECONDS));
        Assert.assertFalse(testCtx2.getLatch().await(1, TimeUnit.SECONDS));
    }

    @Test
    public void testFailedInit() throws InterruptedException {
        actorSystem.createDispatcher(ROOT_DISPATCHER, Executors.newWorkStealingPool(parallelism));
        ActorTestCtx testCtx1 = getActorTestCtx(1);
        ActorTestCtx testCtx2 = getActorTestCtx(1);

        ActorRef actorId1 = actorSystem.createRootActor(ROOT_DISPATCHER,
                new FailedToInitActor.FailedToInitActorCreator(
                        new EntityActorId("test-entity-1"), testCtx1, 1, 3000));
        ActorRef actorId2 = actorSystem.createRootActor(ROOT_DISPATCHER,
                new FailedToInitActor.FailedToInitActorCreator(
                        new EntityActorId("test-entity-2"), testCtx2, 2, 1));

        actorId1.tell(new IntActorMsg(42));
        actorId2.tell(new IntActorMsg(42));

        Assert.assertTrue(testCtx1.getLatch().await(2, TimeUnit.SECONDS));
        Assert.assertTrue(testCtx2.getLatch().await(1, TimeUnit.SECONDS));
        Assert.assertTrue(testCtx1.getLatch().await(3, TimeUnit.SECONDS));
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
