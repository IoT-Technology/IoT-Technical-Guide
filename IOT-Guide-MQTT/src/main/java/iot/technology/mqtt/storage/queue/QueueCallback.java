package iot.technology.mqtt.storage.queue;

import iot.technology.mqtt.storage.msg.QueueMsgMetadata;

/**
 * @author james mu
 * @date 2020/8/31 12:07
 */
public interface QueueCallback {

    void onSuccess(QueueMsgMetadata metadata);

    void onFailure(Throwable t);
}
