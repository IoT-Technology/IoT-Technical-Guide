package iot.technology.actor.core;

import iot.technology.actor.message.ActorId;
import iot.technology.actor.message.ActorMsg;

/**
 * @author mushuwei
 */
public interface ActorRef {

    ActorId getActorId();

    void tell(ActorMsg actorMsg);

    void tellWithHighPriority(ActorMsg actorMsg);
}
