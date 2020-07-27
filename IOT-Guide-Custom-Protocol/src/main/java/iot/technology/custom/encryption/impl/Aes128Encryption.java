package iot.technology.custom.encryption.impl;

import com.alibaba.fastjson.JSON;
import iot.technology.custom.encryption.Encryption;
import iot.technology.custom.encryption.EncryptionAlgorithm;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;

/**
 * @author james mu
 * @date 2020/7/27 14:13
 */
public class Aes128Encryption implements Encryption {

    private static final String KEY_ALGORITHM = "AES";

    public static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";

    @Override
    public byte getEncryptionAlgorithm() {
        return EncryptionAlgorithm.aes128;
    }

    @Override
    public byte[] encrypt(Object object) {
        byte[] plainByte = JSON.toJSONBytes(object);
        try {
            byte[] key = generateKey();
            AlgorithmParameters iv = generateIV();
            byte[] encryptedData = encrypt(plainByte, key, iv);
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return plainByte;
    }

    @Override
    public <T> T decrypt(Class<T> clazz, byte[] bytes) {
        try {
            byte[] key = generateKey();
            AlgorithmParameters iv = generateIV();
            byte[] decryptData = decrypt(bytes, key, iv);
            return JSON.parseObject(decryptData, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] generateKey() throws NoSuchAlgorithmException {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);
        SecretKey key = keyGenerator.generateKey();
        return key.getEncoded();
    }

    /**
     * 生成iv
     */

    public static AlgorithmParameters generateIV() throws Exception{
        /**
         * iv 为一个 16 字节的数组，这里采用和 iOS 端一样的构造方法，数据全为0
         */
        byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        AlgorithmParameters params = AlgorithmParameters.getInstance(KEY_ALGORITHM);
        params.init(new IvParameterSpec(iv));
        return params;
    }

    /**
     * 转化成JAVA的密钥格式
     */
    public static Key convertToKey(byte[] keyBytes) throws Exception{
        SecretKey secretKey = new SecretKeySpec(keyBytes,KEY_ALGORITHM);
        return secretKey;
    }

    /**
     *  加密
     */
    public static byte[] encrypt(byte[] data,byte[] keyBytes,AlgorithmParameters iv) throws Exception {
        //转化为密钥
        Key key = convertToKey(keyBytes);
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key,iv);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     */
    public static byte[] decrypt(byte[] encryptedData,byte[] keyBytes,AlgorithmParameters iv) throws Exception{
        Key key = convertToKey(keyBytes);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        //设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key,iv);
        return cipher.doFinal(encryptedData);
    }


}
