package iot.technology.thingsboard.ruleengine.service;

import iot.technology.actor.core.AbstractActor;
import iot.technology.actor.core.InitFailureStrategy;
import iot.technology.actor.core.ProcessFailureStrategy;
import iot.technology.actor.exception.ActorException;
import iot.technology.actor.message.ActorMsg;
import lombok.extern.slf4j.Slf4j;

/**
 * @author mushuwei
 */
@Slf4j
public abstract class ContextAwareActor extends AbstractActor {

    public static final int ENTITY_PACK_LIMIT = 1024;

    protected final ActorSystemContext systemContext;

    public ContextAwareActor(ActorSystemContext systemContext) {
        super();
        this.systemContext = systemContext;
    }

    @Override
    public boolean process(ActorMsg msg) {
        return false;
    }

    @Override
    public void destroy() throws ActorException {
        super.destroy();
    }

    @Override
    public InitFailureStrategy onInitFailure(int attempt, Throwable t) {
        return super.onInitFailure(attempt, t);
    }

    @Override
    public ProcessFailureStrategy onProcessFailure(Throwable t) {
        return super.onProcessFailure(t);
    }
}
