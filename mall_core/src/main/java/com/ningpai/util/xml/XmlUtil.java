/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util.xml;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Date;

/**
 * XML工具类
 * */
public class XmlUtil {

    /**
     * 日志
     * */
    private static Logger LOGGER = Logger.getLogger(XmlUtil.class);

    /**
     * 字符编码
     * */
    private static final String UTF8 = "UTF-8";

    /** xml文档对象 */
    private Document document;

    /**
     * 解析xml和xsl生成html
     * 
     * @param xmlFileName
     * @param xslFileName
     * @param htmlFileName
     */
    public void Transform(String xmlFileName, String xslFileName, String htmlFileName) {
        try {
            TransformerFactory tFac = TransformerFactory.newInstance();
            Source xslSource = new StreamSource(xslFileName);
            Transformer t = tFac.newTransformer(xslSource);
            File xmlFile = new File(xmlFileName);
            File htmlFile = new File(htmlFileName);
            Source source = new StreamSource(xmlFile);
            Result result = new StreamResult(htmlFile);
            LOGGER.debug(result.toString());
            t.transform(source, result);
        } catch (TransformerConfigurationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (TransformerException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 解析xml文件
     * 
     * @param fileName
     * @return
     */
    public Document parserXml(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            document = db.parse(fileName);
            LOGGER.debug("-----------------------------------" + "解析完毕" + "----------------------------------------");
            return document;

        } catch (FileNotFoundException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (ParserConfigurationException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (SAXException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        } catch (IOException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
        return document;
    }

    /**
     * 创建、修改xml文件
     * 
     * @param fileName
     */
    public void createXml(String fileName) {
        try {
            /** 将document中的内容写入文件中 */
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, UTF8);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(fileName));
            transformer.transform(source, result);
            LOGGER.debug("--------------------------------" + "更新 XML文件成功" + "-------------------------------------");
        } catch (final Exception exception) {
            LOGGER.error("更新" + fileName + "出错：", exception);
        }
    }

    /**
     * 创建、修改xml文件
     * 
     * @Title: createXml
     * @Description: 创建、修改xml文件
     * @param fileName
     * @param document
     */
    public synchronized void createXml(String fileName, Document document) {
        LOGGER.debug("============进入生成xml方法：" + new Date().toLocaleString() + "=================");
        try {
            // 判断文件是否存在，如存在就删掉它
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
                LOGGER.debug("==============删除xml文件==============");
            }
            /** 将document中的内容写入文件中 */
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, UTF8);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(fileName));
            transformer.transform(source, result);
            LOGGER.debug("--------------------------------" + "更新 XML文件成功" + "-------------------------------------");
        } catch (final Exception exception) {
            LOGGER.error("更新" + fileName + "出错：", exception);
        }
        LOGGER.debug("============退出生成xml方法：" + new Date().toLocaleString() + "=================");
    }

    /**
     * Document转xml字符串
     * 
     * @param document
     * @return
     */
    public String document2Str(Document document) {
        String xmlStr = null;
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t;
        try {
            t = tf.newTransformer();

            String encode = System.getProperty("file.encoding");
            LOGGER.error("编码格式：" + encode);
            t.setOutputProperty(OutputKeys.ENCODING, encode);
            t.setOutputProperty(OutputKeys.METHOD, "xml");
            //t.setOutputProperty("encoding", "gb2312");
            //t.setOutputProperty("encoding", UTF8);// 解决中文问题
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            t.transform(new DOMSource(document), new StreamResult(bos));
            xmlStr = bos.toString();
        } catch (TransformerConfigurationException e) {
            LOGGER.error("TransformerConfiguration错误", e);
        } catch (TransformerException e) {
            LOGGER.error("Transformer错误", e);
        }
        return xmlStr;
    }

    /**
     * xml字符串转Document
     * 
     * @param xmlStr
     * @return
     */
    public Document str2Document(String xmlStr) {
        Document doc = null;
        StringReader sr = new StringReader(xmlStr);
        InputSource is = new InputSource(sr);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            doc = builder.parse(is);
        } catch (ParserConfigurationException e) {
            LOGGER.error("ParserConfiguration错误", e);
        } catch (SAXException e) {
            LOGGER.error("SAX错误", e);
        } catch (IOException e) {
            LOGGER.error("IO错误", e);
        }
        return doc;
    }
}
