package iot.technology.custom.util;


import io.netty.channel.Channel;
import iot.technology.custom.attribute.Attributes;
import iot.technology.custom.session.Session;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author james mu
 * @date 2020/8/10 15:11
 */
public class SessionUtil {

    private static final Map<String, Channel> clientIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        clientIdChannelMap.put(session.getClientId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            Session session = getSession(channel);
            clientIdChannelMap.remove(session.getClientId());
            channel.attr(Attributes.SESSION).set(null);
            System.out.println(session + " 退出登录!");
        }
    }

    public static boolean hasLogin(Channel channel) {
        return getSession(channel) != null;
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static Channel getChannel(String clientId) {
        return clientIdChannelMap.get(clientId);
    }
}
