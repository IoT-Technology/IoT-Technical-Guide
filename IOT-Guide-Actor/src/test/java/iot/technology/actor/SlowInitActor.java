package iot.technology.actor;

import iot.technology.actor.core.Actor;
import iot.technology.actor.core.ActorCtx;
import iot.technology.actor.exception.ActorException;
import iot.technology.actor.message.ActorId;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mushuwei
 */
@Slf4j
public class SlowInitActor extends TestRootActor {

    public SlowInitActor(ActorId actorId, ActorTestCtx testCtx) {
        super(actorId, testCtx);
    }

    @Override
    public void init(ActorCtx ctx) throws ActorException {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.init(ctx);
    }

    public static class SlowInitActorCreator implements ActorCreator {

        private final ActorId actorId;
        private final ActorTestCtx testCtx;

        public SlowInitActorCreator(ActorId actorId, ActorTestCtx testCtx) {
            this.actorId = actorId;
            this.testCtx = testCtx;
        }

        @Override
        public ActorId createActorId() {
            return actorId;
        }

        @Override
        public Actor createActor() {
            return new SlowInitActor(actorId, testCtx);
        }
    }
}
