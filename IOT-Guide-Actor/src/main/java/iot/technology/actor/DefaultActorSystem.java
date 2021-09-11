package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * @author mushuwei
 */
public class DefaultActorSystem implements ActorSystem {

    private final ConcurrentMap<String, Dispatcher> dispatchers = new ConcurrentHashMap<>();

    @Getter
    private final ActorSystemSettings settings;
    @Getter
    private final ScheduledExecutorService scheduler;

    public DefaultActorSystem(ActorSystemSettings settings) {
        this.settings = settings;
        this.scheduler =
                Executors.newScheduledThreadPool(settings.getSchedulerPoolSize(), ActorThreadFactory.forName("actor-system-scheduler"));
    }


    @Override
    public void createDispatcher(String dispatcherId, ExecutorService executor) {

    }

    @Override
    public void destroyDispatcher(String dispatcherId) {

    }

    @Override
    public ActorRef getActor(ActorId actorId) {
        return null;
    }

    @Override
    public ActorRef createRootActor(String dispatcherId, ActorCreator creator) {
        return null;
    }

    @Override
    public ActorRef createChildActor(String dispatcherId, ActorCreator creator, ActorId actorId) {
        return null;
    }

    @Override
    public void tell(ActorId target, ActorMsg actorMsg) {

    }

    @Override
    public void tellWithHighPriority(ActorId target, ActorMsg actorMsg) {

    }

    @Override
    public void stop(ActorRef actorRef) {

    }

    @Override
    public void stop(ActorId actorId) {

    }

    @Override
    public void stop() {

    }

    @Override
    public void broadcastToChildren(ActorId parent, ActorMsg msg) {

    }

    @Override
    public void broadcastToChildren(ActorId parent, Predicate<ActorId> childFilter, ActorMsg msg) {

    }

    @Override
    public List<ActorId> filterChildren(ActorId parent, Predicate<ActorId> childFilter) {
        return null;
    }
}
