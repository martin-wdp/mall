/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util.xml;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * XML工具类
 * */
public class XmlElementUtil {

    /**
     * 日志
     * */
    private static Logger LOGGER = Logger.getLogger(XmlElementUtil.class);

    /** 操作Element开始*/
    /**
     * 方法名称：getNodeList
     * <p>
     * 方法功能：获取父结点parent的所有子结点
     * <p>
     * 参数说明：@param parent 参数说明：@return
     * <p>
     * 返回：NodeList
     * <p>
     **/
    public NodeList getNodeList(Element parent) {
        return parent.getChildNodes();
    }

    /**
     * 方法名称：getElementsByName
     * <p>
     * 方法功能：在父结点中查询指定名称的结点集
     * <p>
     * 参数说明：@param parent 参数说明：@param name 参数说明：@return
     * <p>
     * 返回：Element[]
     * <p>
     **/
    public Element[] getElements(Element parent) {
        List<Node> resList = new ArrayList<>();
        NodeList nl = getNodeList(parent);
        for (int i = 0; i < nl.getLength(); i++) {
            Node nd = nl.item(i);
            if (nd.getNodeType() == 1) {
                resList.add(nd);
            }
        }
        Element[] res = new Element[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (Element) resList.get(i);
        }
        LOGGER.debug(parent.getNodeName() + "'s childrens num:" + res.length);
        return res;
    }

    /**
     * 方法名称：getElementsByName
     * <p>
     * 方法功能：在父结点中查询指定名称的结点集
     * <p>
     * 参数说明：@param parent 参数说明：@param name 参数说明：@return
     * <p>
     * 返回：Element[]
     * <p>
     **/
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Element[] getElementsByName(Element parent, String name) {
        List resList = new ArrayList();
        NodeList nl = getNodeList(parent);
        for (int i = 0; i < nl.getLength(); i++) {
            Node nd = nl.item(i);
            if (nd.getNodeName().equals(name)) {
                resList.add(nd);
            }
        }
        Element[] res = new Element[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = (Element) resList.get(i);
        }
        LOGGER.debug(parent.getNodeName() + "'s children of " + name + "'s num:" + res.length);
        return res;
    }

    /**
     * 方法名称：getElementName
     * <p>
     * 方法功能：获取指定Element的名称
     * <p>
     * 参数说明：@param element 参数说明：@return
     * <p>
     * 返回：String
     * <p>
     **/
    public String getElementName(Element element) {
        return element.getNodeName();
    }

    /**
     * 方法名称：getElementValue
     * <p>
     * 方法功能：获取指定Element的值
     * <p>
     * 参数说明：@param element 参数说明：@return
     * <p>
     * 返回：String
     * <p>
     **/
    public String getElementValue(Element element) {
        NodeList nl = element.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            // 是一个Text Node
            if (nl.item(i).getNodeType() == Node.TEXT_NODE) {
                LOGGER.debug(element.getNodeName() + " has a Text Node.");
                return element.getFirstChild().getNodeValue();
            }
        }
        LOGGER.error(element.getNodeName() + " hasn't a Text Node.");
        return null;
    }

    /**
     * 方法名称：getElementAttr
     * <p>
     * 方法功能：获取指定Element的属性attr的值
     * <p>
     * 参数说明：@param element 参数说明：@param attr 参数说明：@return
     * <p>
     * 返回：String
     * <p>
     **/
    public String getElementAttr(Element element, String attr) {
        return element.getAttribute(attr);
    }

    /**
     * 方法名称：setElementValue
     * <p>
     * 方法功能：设置指定Element的值
     * <p>
     * 参数说明：@param element 参数说明：@param val
     * <p>
     * 返回：void
     * <p>
     **/
    public void setElementValue(Element element, String val) {
        Node node = element.getOwnerDocument().createTextNode(val);
        NodeList nl = element.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node nd = nl.item(i);
            // 是一个Text Node
            if (nd.getNodeType() == Node.TEXT_NODE) {
                nd.setNodeValue(val);
                LOGGER.debug("modify " + element.getNodeName() + "'s node value succe.");
                return;
            }
        }
        LOGGER.debug("new " + element.getNodeName() + "'s node value succe.");
        element.appendChild(node);
    }

    /**
     * 方法名称：setElementAttr
     * <p>
     * 方法功能：设置结点Element的属性
     * <p>
     * 参数说明：@param element 参数说明：@param attr 参数说明：@param attrVal
     * <p>
     * 返回：void
     * <p>
     **/
    public void setElementAttr(Element element, String attr, String attrVal) {
        element.setAttribute(attr, attrVal);
    }

    /**
     * 方法名称：addElement
     * <p>
     * 方法功能：在parent下增加结点child
     * <p>
     * 参数说明：@param parent 参数说明：@param child
     * <p>
     * 返回：void
     * <p>
     **/
    public void addElement(Element parent, Element child) {
        parent.appendChild(child);
    }

    /**
     * 方法名称：addElement
     * <p>
     * 方法功能：在parent下增加字符串tagName生成的结点
     * <p>
     * 参数说明：@param parent 参数说明：@param tagName
     * <p>
     * 返回：void
     * <p>
     **/
    public void addElement(Element parent, String tagName) {
        Document doc = parent.getOwnerDocument();
        Element child = doc.createElement(tagName);
        parent.appendChild(child);
    }

    /**
     * 方法名称：addElement
     * <p>
     * 方法功能：在parent下增加tagName的Text结点，且值为text
     * <p>
     * 参数说明：@param parent 参数说明：@param tagName 参数说明：@param text
     * <p>
     * 返回：void
     * <p>
     **/
    public void addElement(Element parent, String tagName, String text) {
        Document doc = parent.getOwnerDocument();
        Element child = doc.createElement(tagName);
        setElementValue(child, text);
        parent.appendChild(child);
    }

    /**
     * 方法名称：removeElement
     * <p>
     * 方法功能：将父结点parent下的名称为tagName的结点移除
     * <p>
     * 参数说明：@param parent 参数说明：@param tagName
     * <p>
     * 返回：void
     * <p>
     **/
    public void removeElement(Element parent, String tagName) {
        LOGGER.debug("remove " + parent.getNodeName() + "'s children by tagName " + tagName + " begin...");
        NodeList nl = parent.getElementsByTagName(tagName);
        List<Node> ndList = new ArrayList<>();
        for (int i = 0; i < nl.getLength(); i++) {
            ndList.add(nl.item(i));
        }
        for (Node node : ndList) {
            parent.removeChild(node);
        }
        nl = parent.getElementsByTagName(tagName);
        LOGGER.debug("remove " + parent.getNodeName() + "'s children by tagName " + tagName + " end.");
    }
}
