package iot.technology.token.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 穆书伟
 * @Date: 19-4-10 下午1:02
 * @Version 1.0
 */
public class PublicLoginRequest {

    private String publicId;

    @JsonCreator
    public PublicLoginRequest(@JsonProperty("publicId") String publicId) {
        this.publicId = publicId;
    }

    public String getPublicId() {
        return publicId;
    }
}
