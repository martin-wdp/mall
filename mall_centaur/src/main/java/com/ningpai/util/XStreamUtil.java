package com.ningpai.util;

import com.ningpai.order.bean.TempOrder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 工具类
 * 
 * @author ggn
 *
 */
public class XStreamUtil {

    /**
     * 构造
     */
    private XStreamUtil() {
    }

    /**
     * Bean 转换XML
     * 
     * @param object
     * @return
     */
    public static String BeanToXml(TempOrder object) {
        XStream xStream = new XStream(new XppDriver(new XmlFriendlyNameCoder("_-", "_")));
        xStream.autodetectAnnotations(true);
        return xStream.toXML(object);
    }

}
