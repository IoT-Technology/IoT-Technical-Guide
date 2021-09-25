package iot.technology.actor;


import iot.technology.actor.core.Actor;
import iot.technology.actor.message.ActorId;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author mushuwei
 */
@Slf4j
public class SlowCreateActor extends TestRootActor {

    public static final int TIMEOUT_AWAIT_MAX_MS = 5000;

    public SlowCreateActor(ActorId actorId, ActorTestCtx testCtx, CountDownLatch initLatch) {
        super(actorId, testCtx);
        try {
            initLatch.await(TIMEOUT_AWAIT_MAX_MS, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testCtx.getInvocationCount().incrementAndGet();
    }

    public static class SlowCreateActorCreator implements ActorCreator {

        private final ActorId actorId;
        private final ActorTestCtx testCtx;
        private final CountDownLatch initLatch;

        public SlowCreateActorCreator(ActorId actorId, ActorTestCtx testCtx, CountDownLatch initLatch) {
            this.actorId = actorId;
            this.testCtx = testCtx;
            this.initLatch = initLatch;
        }

        @Override
        public ActorId createActorId() {
            return actorId;
        }

        @Override
        public Actor createActor() {
            return new SlowCreateActor(actorId, testCtx, initLatch);
        }
    }

}
