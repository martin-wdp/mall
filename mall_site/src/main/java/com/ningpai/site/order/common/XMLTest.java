package com.ningpai.site.order.common;

import com.ningpai.site.order.util.KryptoUtil;

import java.io.File;

/**
 * Created by pl on 2015/10/31.
 * Desc:
 */
public class XMLTest {
    public static void main(String[] args) {
        //Generate the keys
        String keysDirPath = "E:\\资料整理\\xml数字签名\\xmldigitalsignature1\\keys";
        String xmlPath="E:\\资料整理\\xml数字签名\\xmldigitalsignature1\\xml";
        KryptoUtil util = new KryptoUtil();
        util.storeKeyPairs(keysDirPath);
        System.out.println("Private and Public Keys generated successfully ...");
        //Sign the XML Dcouments
        String xmlFilePath = xmlPath + File.separator + "employeesalary.xml";
        String signedXmlFilePath = xmlPath + File.separator + "digitallysignedEmpSal.xml";
        String privateKeyFilePath = keysDirPath + File.separator + "privatekey.key";
        String publicKeyFilePath = keysDirPath + File.separator + "publickey.key";
        XmlDigitalSignatureGenerator xmlSig = new XmlDigitalSignatureGenerator();
        xmlSig.generateXMLDigitalSignature(xmlFilePath, signedXmlFilePath, privateKeyFilePath, publicKeyFilePath);
        //Verify the signed XML Document
        try {
            boolean validFlag = XmlDigitalSignatureVerifier.
                    isXmlDigitalSignatureValid(signedXmlFilePath, publicKeyFilePath);
            System.out.println("Validity of XML Digital Signature : " + validFlag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
