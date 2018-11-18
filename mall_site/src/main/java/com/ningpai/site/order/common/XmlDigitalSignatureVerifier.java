package com.ningpai.site.order.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.PublicKey;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.ningpai.site.order.util.KryptoUtil;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This class is used to provide the functionality for the verification of a
 * digitally signed XML document.
 * @author <a href="mailto:debadatta.mishra@gmail.com">Debadatta Mishra</a>
 * @since 2013
 */
public class XmlDigitalSignatureVerifier {
    /**
     * Method used to get the XML document object by parsing xml file
     * @param xmlFilePath
     * @return
     */
    private static Document getXmlDocument(String xmlFilePath) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            doc = dbf.newDocumentBuilder().parse(new FileInputStream(xmlFilePath));
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    /**
     * Method used to verify the XML digital signature
     * @param signedXmlFilePath
     * @param pubicKeyFilePath
     * @return true or false
     * @throws Exception
     */
    public static boolean isXmlDigitalSignatureValid(String signedXmlFilePath,
                                                     String pubicKeyFilePath) throws Exception {
        boolean validFlag = false;
        Document doc = getXmlDocument(signedXmlFilePath);
        NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");
        if (nl.getLength() == 0) {
            throw new Exception("No XML Digital Signature Found, document is discarded");
        }
        PublicKey publicKey = new KryptoUtil().getStoredPublicKey(pubicKeyFilePath);
        DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));
        XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");
        XMLSignature signature = fac.unmarshalXMLSignature(valContext);
        validFlag = signature.validate(valContext);
        return validFlag;
    }
}
