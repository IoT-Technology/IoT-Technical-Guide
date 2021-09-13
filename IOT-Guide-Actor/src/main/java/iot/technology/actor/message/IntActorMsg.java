package iot.technology.actor.message;

import lombok.Getter;

/**
 * @author mushuwei
 */
public class IntActorMsg implements ActorMsg {

    @Getter
    private final int value;

    public IntActorMsg(int value) {
        this.value = value;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.QUEUE_TO_RULE_ENGINE_MSG;
    }
}
