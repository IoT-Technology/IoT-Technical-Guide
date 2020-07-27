package iot.technology.custom.encryption;

import iot.technology.custom.encryption.impl.NotEncryption;

/**
 * @author james mu
 * @date 2020/7/27 14:07
 */
public interface Encryption {

    Encryption DEFAULT = new NotEncryption();

    /**
     * 加密算法
     *
     * @return
     */
    byte getEncryptionAlgorithm();

    /**
     * java 对象转换成二进制
     *
     * @param object
     * @return
     */
    byte[] encrypt(Object object);

    /**
     * 二进制转换成java 对象
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T decrypt(Class<T> clazz, byte[] bytes);
}
