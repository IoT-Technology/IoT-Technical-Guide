package iot.technology.actor;

/**
 * @author mushuwei
 */
public interface ActorRef {

    ActorId getActorId();

    void tell(ActorMsg actorMsg);

    void tellWithHighPriority(ActorMsg actorMsg);
}
