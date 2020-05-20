package iot.technology.http.quota.inmemory;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author james mu
 * @date 2019/8/10 下午5:47
 */
@Slf4j
public abstract class IntervalRegistryCleaner {

    private final KeyBasedIntervalRegistry intervalRegistry;
    private final long cleanPeriodMs;
    private ScheduledExecutorService executor;

    public IntervalRegistryCleaner(KeyBasedIntervalRegistry intervalRegistry, long cleanPeriodMs) {
        this.intervalRegistry = intervalRegistry;
        this.cleanPeriodMs = cleanPeriodMs;
    }

    public void schedule() {
        if (executor != null){
            throw new IllegalStateException("Registry Cleaner already scheduled");
        }
        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::clean, cleanPeriodMs, cleanPeriodMs, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        if (executor != null) {
            executor.shutdown();
        }
    }

    public void clean() {
        try {
            intervalRegistry.clean();
        } catch (RuntimeException ex) {
            log.error("Could not clear Interval Registry", ex);
        }
    }
}
