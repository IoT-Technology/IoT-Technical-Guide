package iot.technology.thingsboard.ruleengine.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

/**
 * @author mushuwei
 */
public class ActorExecutors {

    public static ExecutorService newWorkStealingPool(int parallelism, String namePrefix) {
        return new ForkJoinPool(parallelism,
                new ActorForkJoinWorkerThreadFactory(namePrefix),
                null, true);
    }

    private static ExecutorService newWorkStealingPool(int parallelism, Class clazz) {
        return newWorkStealingPool(parallelism, clazz.getSimpleName());

    }
}
