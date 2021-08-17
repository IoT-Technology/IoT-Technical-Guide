package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Predicate;

/**
 * @author mushuwei
 */
public interface ActorSystem {

    ScheduledExecutorService getScheduler();

    void createDispatcher(String dispatcherId, ExecutorService executor);

    void destroyDispatcher(String dispatcherId);

    ActorRef getActor(ActorId actorId);

    ActorRef createRootActor(String dispatcherId, ActorCreator creator);

    ActorRef createChildActor(String dispatcherId, ActorCreator creator, ActorId actorId);

    void tell(ActorId target, ActorMsg actorMsg);

    void tellWithHighPriority(ActorId target, ActorMsg actorMsg);

    void stop(ActorRef actorRef);

    void stop(ActorId actorId);

    void stop();

    void broadcastToChildren(ActorId parent, ActorMsg msg);

    void broadcastToChildren(ActorId parent, Predicate<ActorId> childFilter, ActorMsg msg);

    List<ActorId> filterChildren(ActorId parent, Predicate<ActorId> childFilter);
}
