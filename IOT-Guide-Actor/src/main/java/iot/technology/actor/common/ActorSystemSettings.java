package iot.technology.actor.common;

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
