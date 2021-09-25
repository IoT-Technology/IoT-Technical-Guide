package iot.technology.actor.common;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copy of Executors.DefaultThreadFactory but with ability to set name of the pool
 *
 * @author mushuwei
 */
public class ActorThreadFactory implements ThreadFactory {
    public static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    public static ActorThreadFactory forName(String name) {
        return new ActorThreadFactory(name);
    }

    public ActorThreadFactory(String name) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        namePrefix = name + "-" + poolNumber.getAndIncrement() + "-thread-";
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }
}
