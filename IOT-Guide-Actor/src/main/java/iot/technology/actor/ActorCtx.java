package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author mushuwei
 */
public interface ActorCtx extends ActorRef {

    ActorId getSelf();

    ActorRef getParentRef();

    void tell(ActorId target, ActorMsg msg);

    void stop(ActorId target);

    ActorRef getOrCreateChildActor(ActorId actorId, Supplier<String> dispatcher, Supplier<ActorCreator> creator);

    void broadcastToChildren(ActorMsg msg);

    void broadcastToChildren(ActorMsg msg, Predicate<ActorId> childFilter);

    List<ActorId> filterChildren(Predicate<ActorId> childFilter);
}
