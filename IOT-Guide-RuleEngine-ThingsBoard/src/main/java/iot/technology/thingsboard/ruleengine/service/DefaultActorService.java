package iot.technology.thingsboard.ruleengine.service;

import iot.technology.actor.common.ActorThreadFactory;
import iot.technology.thingsboard.ruleengine.common.ActorExecutors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author mushuwei
 */
@Service
@Slf4j
public class DefaultActorService implements ActorService {
    

    private ExecutorService initDispatcherExecutor(String dispatcherName, int poolSize) {
        if (poolSize == 0) {
            int cores = Runtime.getRuntime().availableProcessors();
            poolSize = Math.max(1, cores / 2);
        }
        if (poolSize == 1) {
            return Executors.newSingleThreadExecutor(ActorThreadFactory.forName(dispatcherName));
        } else {
            return ActorExecutors.newWorkStealingPool(poolSize, dispatcherName);
        }
    }
}
