package iot.technology.actor.core;

import iot.technology.actor.exception.ActorException;
import iot.technology.actor.message.ActorMsg;

/**
 * @author mushuwei
 */
public interface Actor {

    boolean process(ActorMsg msg);

    ActorRef getActorRef();

    default void init(ActorCtx ctx) throws ActorException {
    }

    default void destroy() throws ActorException {
    }

    default InitFailureStrategy onInitFailure(int attempt, Throwable t) {
        return InitFailureStrategy.retryWithDelay(5000L * attempt);
    }

    default ProcessFailureStrategy onProcessFailure(Throwable t) {
        if (t instanceof Error) {
            return ProcessFailureStrategy.stop();
        } else {
            return ProcessFailureStrategy.resume();
        }
    }
}

