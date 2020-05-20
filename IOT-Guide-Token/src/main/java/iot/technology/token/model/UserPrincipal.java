package iot.technology.token.model;

import java.io.Serializable;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午10:00
 * @Version 1.0
 */
public class UserPrincipal implements Serializable {

    private final Type type;
    private final String value;

    public UserPrincipal(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public enum Type {
        USER_NAME,
        PUBLIC_ID
    }
}
