package iot.technology.custom.attribute;

import io.netty.util.AttributeKey;
import iot.technology.custom.session.Session;

/**
 * @author james mu
 * @date 2020/8/10 15:29
 */
public interface Attributes {
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
