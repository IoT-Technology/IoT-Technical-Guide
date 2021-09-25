package iot.technology.actor;

import iot.technology.actor.core.AbstractActor;
import iot.technology.actor.core.Actor;
import iot.technology.actor.core.ActorCtx;
import iot.technology.actor.exception.ActorException;
import iot.technology.actor.message.ActorId;
import iot.technology.actor.message.ActorMsg;
import iot.technology.actor.message.IntActorMsg;
import lombok.Getter;

/**
 * @author mushuwei
 */
public class TestRootActor extends AbstractActor {

    @Getter
    private final ActorId actorId;
    @Getter
    private final ActorTestCtx testCtx;

    private boolean initialized;
    private long sum;
    private int count;

    public TestRootActor(ActorId actorId, ActorTestCtx testCtx) {
        this.actorId = actorId;
        this.testCtx = testCtx;
    }

    @Override
    public void init(ActorCtx ctx) throws ActorException {
        super.init(ctx);
        initialized = true;
    }

    @Override
    public boolean process(ActorMsg msg) {
        if (initialized) {
            int value = ((IntActorMsg) msg).getValue();
            sum += value;
            count += 1;
            if (count == testCtx.getExpectedInvocationCount()) {
                testCtx.getActual().set(sum);
                testCtx.getInvocationCount().addAndGet(count);
                sum = 0;
                count = 0;
                testCtx.getLatch().countDown();
            }
        }
        return true;
    }

    @Override
    public void destroy() throws ActorException {

    }

    public static class TestRootActorCreator implements ActorCreator {

        private final ActorId actorId;
        private final ActorTestCtx testCtx;

        public TestRootActorCreator(ActorId actorId, ActorTestCtx testCtx) {
            this.actorId = actorId;
            this.testCtx = testCtx;
        }

        @Override
        public ActorId createActorId() {
            return actorId;
        }

        @Override
        public Actor createActor() {
            return new TestRootActor(actorId, testCtx);
        }
    }
}
