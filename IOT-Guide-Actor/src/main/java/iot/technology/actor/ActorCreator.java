package iot.technology.actor;

import iot.technology.actor.core.Actor;
import iot.technology.actor.message.ActorId;

/**
 * @author mushuwei
 */
public interface ActorCreator {

    ActorId createActorId();

    Actor createActor();
}
