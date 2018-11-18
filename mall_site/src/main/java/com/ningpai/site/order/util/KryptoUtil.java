package com.ningpai.site.order.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * This class is used as a cryptographic utility.
 *
 * @author <a href="mailto:debadatta.mishra@gmail.com">Debadatta Mishra</a>
 * @since 2013
 */
public class KryptoUtil {

    /**
     * Name of the algorithm
     */
    private static final String ALGORITHM = "RSA";

    /**
     * This method is used to generate key pair based upon the provided
     * algorithm
     *
     * @return KeyPair
     */
    private KeyPair generateKeyPairs() {
        KeyPair keyPair = null;
        KeyPairGenerator keyGen;
        try {
            keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(1024);
            keyPair = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }

    /**
     * Method used to store Private and Public Keys inside a directory
     *
     * @param dirPath to store the keys
     */
    public void storeKeyPairs(String dirPath) {
        KeyPair keyPair = generateKeyPairs();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        storeKeys(dirPath + File.separator + "publickey.key", publicKey);
        storeKeys(dirPath + File.separator + "privatekey.key", privateKey);
    }

    /**
     * Method used to store the key(Public/Private)
     *
     * @param filePath , name of the file
     * @param key
     */
    private void storeKeys(String filePath, Key key) {
        byte[] keyBytes = key.getEncoded();
        OutputStream outStream = null;
        try {
            outStream = new FileOutputStream(filePath);
            outStream.write(keyBytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) {
                try {
                    outStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Method used to retrieve the keys in the form byte array
     *
     * @param filePath of the key
     * @return byte array
     */
    private byte[] getKeyData(String filePath) {
        File file = new File(filePath);
        byte[] buffer = new byte[(int) file.length()];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            fis.read(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }

    /**
     * Method used to get the generated Private Key
     *
     * @param filePath of the PrivateKey file
     * @return PrivateKey
     */
    public PrivateKey getStoredPrivateKey(String filePath) {
        PrivateKey privateKey = null;
        byte[] keydata = getKeyData(filePath);
        PKCS8EncodedKeySpec encodedPrivateKey = new PKCS8EncodedKeySpec(keydata);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(encodedPrivateKey);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * Method used to get the generated Public Key
     *
     * @param filePath of the PublicKey file
     * @return PublicKey
     */
    public PublicKey getStoredPublicKey(String filePath) {
        PublicKey publicKey = null;
        byte[] keydata = getKeyData(filePath);
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        X509EncodedKeySpec encodedPublicKey = new X509EncodedKeySpec(keydata);
        try {
            publicKey = keyFactory.generatePublic(encodedPublicKey);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
}
