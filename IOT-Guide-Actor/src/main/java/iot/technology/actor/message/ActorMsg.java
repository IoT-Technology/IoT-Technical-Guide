package iot.technology.actor.message;

import iot.technology.actor.core.ActorStopReason;

/**
 * @author mushuwei
 */
public interface ActorMsg {

    MsgType getMsgType();

    /**
     * Executed when the target actor is stopped or destroyed.
     * For Example, rule node failed to initialize or removed from rule chain
     * Implementation should cleanup the resources.
     *
     * @param reason
     */
    default void onActorStopped(ActorStopReason reason) {
    }
    
}
