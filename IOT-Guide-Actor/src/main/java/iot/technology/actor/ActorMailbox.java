package iot.technology.actor;

import iot.technology.actor.message.ActorMsg;
import iot.technology.actor.message.ActorStopReason;
import iot.technology.actor.message.MsgType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
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

    private final ActorSystem system;
    private final ActorSystemSettings settings;
    private final ActorId selfId;
    private final Actor actor;
    private final Dispatcher dispatcher;
    private final ConcurrentLinkedQueue<ActorMsg> highPriorityMsgs = new ConcurrentLinkedQueue<>();
    private final ConcurrentLinkedQueue<ActorMsg> normalPriorityMsgs = new ConcurrentLinkedQueue<>();

    private final AtomicBoolean busy = new AtomicBoolean(FREE);
    private final AtomicBoolean ready = new AtomicBoolean(NOT_READY);
    private final AtomicBoolean destroyInProgress = new AtomicBoolean();
    private volatile ActorStopReason stopReason;

    public void initActor() {
        dispatcher.getExecutor().execute(() -> tryInit(1));
    }

    private void tryInit(int attempt) {
        try {
            log.debug("[{}] Trying to init actor, attempt: {}", selfId, attempt);
            if (!destroyInProgress.get()) {
                actor.init(this);
                if (!destroyInProgress.get()) {
                    ready.set(READY);
                    tryProcessQueue(false);
                }
            }
        } catch (Throwable t) {
            log.debug("[{}] Failed to init actor, attempt: {}", selfId, attempt, t);
            int attemptIdx = attempt + 1;
            InitFailureStrategy strategy = actor.onInitFailure(attempt, t);
            if (strategy.isStop() ||
                    (settings.getMaxActorInitAttempts() > 0 && attemptIdx > settings.getMaxActorInitAttempts())) {
                log.info("[{}] Failed to init actor, attempt {}, going to stop attempts.", selfId, attempt, t);
                stopReason = ActorStopReason.INIT_FAILED;
                destroy();
            } else if (strategy.getRetryDelay() > 0) {
                log.info("[{}] Failed to init actor, attempt {}, going to retry in attempts in {}ms", selfId, attempt,
                        strategy.getRetryDelay());
                log.debug("[{}] Error", selfId, t);
                system.getScheduler().schedule(() -> dispatcher.getExecutor().execute(() -> tryInit(attemptIdx)), strategy.getRetryDelay(),
                        TimeUnit.MICROSECONDS);
            } else {
                log.info("[{}] Failed to init actor, attempt {}, going to retry immediately", selfId, attempt);
                log.debug("[{}] Error", selfId, t);
                dispatcher.getExecutor().execute(() -> tryInit(attemptIdx));
            }
        }
    }

    public void destroy() {
        if (stopReason == null) {
            stopReason = ActorStopReason.STOPPED;
        }
        destroyInProgress.set(true);
        dispatcher.getExecutor().execute(() -> {
            try {
                ready.set(NOT_READY);
                actor.destroy();
                highPriorityMsgs.forEach(msg -> msg.onActorStopped(stopReason));
                normalPriorityMsgs.forEach(msg -> msg.onActorStopped(stopReason));
            } catch (Throwable t) {
                log.warn("[{}] Failed to destroy actor: {}", selfId, t);
            }
        });
    }

    private void enqueue(ActorMsg msg, boolean highPriority) {
        if (!destroyInProgress.get()) {
            if (highPriority) {
                highPriorityMsgs.add(msg);
            } else {
                normalPriorityMsgs.add(msg);
            }
            tryProcessQueue(true);
        } else {
            if (highPriority && msg.getMsgType().equals(MsgType.UPDATED_MSG)) {
                synchronized (this) {
                    if (stopReason == ActorStopReason.INIT_FAILED) {
                        destroyInProgress.set(false);
                        stopReason = null;
                        initActor();
                    } else {
                        msg.onActorStopped(stopReason);
                    }
                }
            } else {
                msg.onActorStopped(stopReason);
            }
        }
    }

    private void tryProcessQueue(boolean newMsg) {
        if (ready.get() == READY) {
            if (newMsg || !highPriorityMsgs.isEmpty() || !normalPriorityMsgs.isEmpty()) {
                if (busy.compareAndSet(FREE, BUSY)) {
                    dispatcher.getExecutor().execute(this::processMailbox);
                } else {
                    log.trace("[{}] MessageBox is busy, new msg: {}", selfId, newMsg);
                }
            } else {
                log.trace("[{}] MessageBox is empty, new msg: {}", selfId, newMsg);
            }
        } else {
            log.trace("[{}] MessageBox is not ready, new msg: {}", selfId, newMsg);
        }
    }

    private void processMailbox() {
        boolean noMoreElements = false;
        for (int i = 0; i < settings.getActorThroughput(); i++) {
            ActorMsg msg = highPriorityMsgs.poll();
            if (msg == null) {
                msg = normalPriorityMsgs.poll();
            }
            if (msg != null) {
                log.debug("[{}] Going to process message: {}", selfId, msg);
                actor.process(msg);
            } else {
                noMoreElements = true;
                break;
            }
        }
        if (noMoreElements) {
            busy.set(FREE);
            dispatcher.getExecutor().execute(() -> tryProcessQueue(false));
        } else {
            dispatcher.getExecutor().execute(this::processMailbox);
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
