package iot.technology.actor;

import lombok.Data;

/**
 * @author mushuwei
 */
@Data
public class ActorSystemSettings {

    private final int actorThroughput;
    private final int schedulerPoolSize;
    private final int maxActorInitAttempts;

}
