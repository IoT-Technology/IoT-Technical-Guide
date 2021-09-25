package iot.technology.actor;

import iot.technology.actor.core.Actor;
import iot.technology.actor.core.ActorCtx;
import iot.technology.actor.core.InitFailureStrategy;
import iot.technology.actor.exception.ActorException;
import iot.technology.actor.message.ActorId;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mushuwei
 */
@Slf4j
public class FailedToInitActor extends TestRootActor {

    int retryAttempts;
    int retryDelay;
    int attempts = 0;

    public FailedToInitActor(ActorId actorId, ActorTestCtx testCtx, int retryAttempts, int retryDelay) {
        super(actorId, testCtx);
        this.retryAttempts = retryAttempts;
        this.retryDelay = retryDelay;
    }

    @Override
    public void init(ActorCtx ctx) throws ActorException {
        if (attempts < retryAttempts) {
            attempts++;
            throw new ActorException("Test attempt", new RuntimeException());
        } else {
            super.init(ctx);
        }
    }

    @Override
    public InitFailureStrategy onInitFailure(int attempt, Throwable t) {
        return InitFailureStrategy.retryWithDelay(retryDelay);
    }

    public static class FailedToInitActorCreator implements ActorCreator {

        private final ActorId actorId;
        private final ActorTestCtx testCtx;
        private final int retryAttempts;
        private final int retryDelay;

        public FailedToInitActorCreator(ActorId actorId, ActorTestCtx testCtx, int retryAttempts, int retryDelay) {
            this.actorId = actorId;
            this.testCtx = testCtx;
            this.retryAttempts = retryAttempts;
            this.retryDelay = retryDelay;
        }

        @Override
        public ActorId createActorId() {
            return actorId;
        }

        @Override
        public Actor createActor() {
            return new FailedToInitActor(actorId, testCtx, retryAttempts, retryDelay);
        }
    }
}
