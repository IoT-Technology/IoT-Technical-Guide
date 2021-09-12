package iot.technology.actor;

import iot.technology.actor.exception.ActorNotRegisteredException;
import iot.technology.actor.message.ActorMsg;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author mushuwei
 */
@Slf4j
public class DefaultActorSystem implements ActorSystem {

    private final ConcurrentMap<String, Dispatcher> dispatchers = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, ActorMailbox> actors = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, ReentrantLock> actorCreationLocks = new ConcurrentHashMap<>();
    private final ConcurrentMap<ActorId, Set<ActorId>> parentChildMap = new ConcurrentHashMap<>();


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
        Dispatcher current = dispatchers.putIfAbsent(dispatcherId, new Dispatcher(dispatcherId, executor));
        if (current != null) {
            throw new RuntimeException("Dispatcher with id [" + dispatcherId + "] is not registered!");
        }

    }

    @Override
    public void destroyDispatcher(String dispatcherId) {
        Dispatcher dispatcher = dispatchers.remove(dispatcherId);
        if (dispatcher != null) {
            dispatcher.getExecutor().shutdownNow();
        } else {
            throw new RuntimeException("Dispatcher with id [" + dispatcherId + "] is not registered!");
        }
    }

    @Override
    public ActorRef getActor(ActorId actorId) {
        return actors.get(actorId);
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
        tell(target, actorMsg, false);
    }

    public void tell(ActorId target, ActorMsg actorMsg, boolean highPriotiry) {
        ActorMailbox mailbox = actors.get(target);
        if (mailbox == null) {
            throw new ActorNotRegisteredException(target, "Actor with id [" + target + "] is not registered!");
        }
        if (highPriotiry) {
            mailbox.tellWithHighPriority(actorMsg);
        } else {
            mailbox.tell(actorMsg);
        }
    }

    @Override
    public void tellWithHighPriority(ActorId target, ActorMsg actorMsg) {
        
    }

    @Override
    public void stop(ActorRef actorRef) {
        stop(actorRef.getActorId());
    }

    @Override
    public void stop(ActorId actorId) {
        Set<ActorId> children = parentChildMap.remove(actorId);
        if (children != null) {
            for (ActorId child : children) {
                stop(child);
            }
        }
        ActorMailbox mailbox = actors.remove(actorId);
        if (mailbox != null) {
            mailbox.destroy();
        }
    }

    @Override
    public void stop() {
        dispatchers.values().forEach(dispatcher -> {
            dispatcher.getExecutor().shutdown();
            try {
                dispatcher.getExecutor().awaitTermination(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                log.warn("[{}] Failed to stop dispatcher", dispatcher.getDispatcherId(), e);
            }
        });
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        actors.clear();
    }

    @Override
    public void broadcastToChildren(ActorId parent, ActorMsg msg) {
        broadcastToChildren(parent, id -> true, msg);
    }

    @Override
    public void broadcastToChildren(ActorId parent, Predicate<ActorId> childFilter, ActorMsg msg) {
        Set<ActorId> children = parentChildMap.get(parent);
        if (children != null) {
            children.stream().filter(childFilter).forEach(id -> tell(id, msg));
        }
    }

    @Override
    public List<ActorId> filterChildren(ActorId parent, Predicate<ActorId> childFilter) {
        Set<ActorId> children = parentChildMap.get(parent);
        if (children != null) {
            return children.stream().filter(childFilter).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }
}
