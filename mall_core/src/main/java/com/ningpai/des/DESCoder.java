package com.ningpai.des;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES安全编码组件
 * Created by Mr.Song on 2015/5/25.
 *
 */
public abstract class DESCoder extends Coder {

    /**
     * ALGORITHM 算法
     */
    public static final String ALGORITHM = "DES";

    /**
     * 转换密钥<br>
     *
     * @param key 秘钥字节
     * @return 抓换后的秘钥
     * @throws Exception 转换过程红抛出的异常
     */
    private static Key toKey(byte[] key) throws Exception {
        DESKeySpec dks = new DESKeySpec(key);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        
        // 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
        // SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);
        return keyFactory.generateSecret(dks);
    }

    /**
     * 解密
     *
     * @param data 要解密的数据
     * @param key  揭秘的秘钥
     * @return 解密后的数据
     * @throws Exception 解密过程中出现的异常
     */
    public static byte[] decrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * 加密
     *
     * @param data 要加密的数据
     * @param key 加密时使用的秘钥
     * @return 加密过后的数据
     * @throws Exception 加密过程中抛出的异常
     */
    public static byte[] encrypt(byte[] data, String key) throws Exception {
        Key k = toKey(decryptBASE64(key));
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, k);

        return cipher.doFinal(data);
    }

    /**
     * 生成密钥
     *
     * @return 生成的秘钥
     * @throws Exception 生成秘钥时抛出异常
     */
    public static String initKey() throws Exception {
        return initKey(null);
    }

    /**
     * 生成密钥
     *
     * @param seed 要加密的数据
     * @return 生成的秘钥
     * @throws Exception 生成秘钥时抛出的异常
     */
    public static String initKey(String seed) throws Exception {
        SecureRandom secureRandom = null;

        if (seed != null) {
            secureRandom = new SecureRandom(decryptBASE64(seed));
        } else {
            secureRandom = new SecureRandom();
        }

        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM);
        kg.init(secureRandom);

        SecretKey secretKey = kg.generateKey();

        return encryptBASE64(secretKey.getEncoded());
    }
}
