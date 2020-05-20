package iot.technology.mqtt;

import java.util.regex.Pattern;

/**
 * @Author: 穆书伟
 * @Date: 19-4-8 上午10:35
 * @Version 1.0
 */
public class MqttTopicMatcher {

    private final String topic;
    private final Pattern topicRegex;

    MqttTopicMatcher(String topic) {
        if (topic == null){
            throw new NullPointerException("topic");
        }
        this.topic = topic;
        this.topicRegex = Pattern.compile(topic.replace("+", "[^/]+").replace("#", ".+") + "$");
    }

    public String getTopic() {
        return topic;
    }

    public boolean matches(String topic){
        return this.topicRegex.matcher(topic).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MqttTopicMatcher that = (MqttTopicMatcher) o;

        return topic.equals(that.topic);
    }

    @Override
    public int hashCode() {
        return topic.hashCode();
    }
}
