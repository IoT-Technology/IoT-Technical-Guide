package iot.technology.custom.encryption.impl;

import com.alibaba.fastjson.JSON;
import iot.technology.custom.encryption.Encryption;
import iot.technology.custom.encryption.EncryptionAlgorithm;

/**
 * @author james mu
 * @date 2020/7/27 14:15
 */
public class NotEncryption implements Encryption {

    @Override
    public byte getEncryptionAlgorithm() {
        return EncryptionAlgorithm.not;
    }

    @Override
    public byte[] encrypt(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T decrypt(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
