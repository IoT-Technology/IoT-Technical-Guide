package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author mushuwei
 */
@Slf4j
@Data
public final class ActorMailbox implements ActorCtx {

    public static final boolean FREE = false;
    public static final boolean BUSY = true;

    public static final boolean NOT_READY = false;
    public static final boolean READY = true;

    private final ConcurrentLinkedQueue<ActorMsg> highPriorityMsgs = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<ActorMsg> normalPriorityMsgs = new ConcurrentLinkedQueue<>();
    private final AtomicBoolean busy = new AtomicBoolean(FREE);
    private final AtomicBoolean ready = new AtomicBoolean(NOT_READY);
    private final AtomicBoolean destroyInProgress = new AtomicBoolean();

    private void enqueue(ActorMsg msg, boolean highPriority) {
        if (!destroyInProgress.get()) {
            if (highPriority) {

            }
        }
    }

    @Override
    public ActorId getSelf() {
        return null;
    }

    @Override
    public ActorRef getParentRef() {
        return null;
    }

    @Override
    public void tell(ActorId target, ActorMsg msg) {

    }

    @Override
    public void stop(ActorId target) {

    }

    @Override
    public ActorRef getOrCreateChildActor(ActorId actorId, Supplier<String> dispatcher, Supplier<ActorCreator> creator) {
        return null;
    }

    @Override
    public void broadcastToChildren(ActorMsg msg) {

    }

    @Override
    public void broadcastToChildren(ActorMsg msg, Predicate<ActorId> childFilter) {

    }

    @Override
    public List<ActorId> filterChildren(Predicate<ActorId> childFilter) {
        return null;
    }

    @Override
    public ActorId getActorId() {
        return null;
    }

    @Override
    public void tell(ActorMsg actorMsg) {

    }

    @Override
    public void tellWithHighPriority(ActorMsg actorMsg) {

    }
}
