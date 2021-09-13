package iot.technology.actor;

import lombok.Getter;

/**
 * @author mushuwei
 */
public class EntityActorId implements ActorId {

    @Getter
    private final String entityId;

    public EntityActorId(String entityId) {
        this.entityId = entityId;
    }

}
