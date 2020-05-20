package iot.technology.token.model.token;

import java.io.Serializable;

/**
 * @Author: 穆书伟
 * @Date: 19-4-9 上午9:20
 * @Version 1.0
 */
public interface JwtToken extends Serializable {
    String getToken();
}
