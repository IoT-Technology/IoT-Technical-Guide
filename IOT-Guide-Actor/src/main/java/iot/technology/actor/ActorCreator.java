package iot.technology.actor;

/**
 * @author mushuwei
 */
public interface ActorCreator {

    ActorId createActorId();

    Actor createActor();
}
