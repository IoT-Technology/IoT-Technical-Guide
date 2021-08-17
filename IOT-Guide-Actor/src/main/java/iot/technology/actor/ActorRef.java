package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;

/**
 * @author mushuwei
 */
public interface ActorRef {

    ActorId getActorId();

    void tell(ActorMsg actorMsg);

    void tellWithHighPriority(ActorMsg actorMsg);
}
