package iot.technology.actor.exception;

import iot.technology.actor.message.ActorId;
import lombok.Getter;

/**
 * @author mushuwei
 */
public class ActorNotRegisteredException extends RuntimeException {
    
    @Getter
    private ActorId target;

    public ActorNotRegisteredException(ActorId target, String message) {
        super(message);
        this.target = target;
    }
}
