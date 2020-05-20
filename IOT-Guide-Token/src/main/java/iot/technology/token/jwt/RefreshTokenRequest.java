package iot.technology.token.jwt;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 上午11:08
 * @Version 1.0
 */
public class RefreshTokenRequest {

    private String refreshToken;

    @JsonCreator
    public RefreshTokenRequest(@JsonProperty("refreshToken") String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
