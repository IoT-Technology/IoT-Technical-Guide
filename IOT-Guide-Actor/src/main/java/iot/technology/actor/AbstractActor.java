package iot.technology.actor;

/**
 * @author mushuwei
 */
public abstract class AbstractActor implements Actor {

    protected ActorCtx ctx;

    @Override
    public void init() throws ActorException {
        this.ctx = ctx;
    }

    public ActorRef getActorRef() {
        return ctx;
    }
}
