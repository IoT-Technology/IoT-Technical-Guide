package iot.technology.actor.core;

import iot.technology.actor.exception.ActorException;
import lombok.Getter;

/**
 * @author mushuwei
 */
public abstract class AbstractActor implements Actor {

    @Getter
    protected ActorCtx ctx;

    @Override
    public void init(ActorCtx ctx) throws ActorException {
        this.ctx = ctx;
    }

    public ActorRef getActorRef() {
        return ctx;
    }
}
