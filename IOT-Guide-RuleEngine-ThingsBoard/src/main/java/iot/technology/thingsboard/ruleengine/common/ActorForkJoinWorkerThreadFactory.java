package iot.technology.thingsboard.ruleengine.common;

import lombok.NonNull;
import lombok.ToString;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author mushuwei
 */
@ToString
public class ActorForkJoinWorkerThreadFactory implements ForkJoinPool.ForkJoinWorkerThreadFactory {

    private final String namePrefix;
    private final AtomicLong threadNumber = new AtomicLong(1);

    public ActorForkJoinWorkerThreadFactory(@NonNull String namePrefix) {
        this.namePrefix = namePrefix;
    }

    @Override
    public ForkJoinWorkerThread newThread(ForkJoinPool pool) {
        ForkJoinWorkerThread thread = ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);
        thread.setName(namePrefix + "-" + thread.getPoolIndex() + "-" + threadNumber.getAndIncrement());
        return thread;
    }
}
