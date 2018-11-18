package com.ningpai.site.order.common;

import com.ningpai.site.order.util.TenpayUtil;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Document;


/**
 * Created by pl on 2015/11/13.
 * Desc:
 */
public class WXPayHelp {
    /**
     * 元转换成分
     * @param amount
     * @return
     */
    public static String getMoney(String amount) {
        if(amount==null){
            return "";
        }
        // 金额转化为分为单位
        String currency =  amount.replaceAll("\\$|\\￥|\\,", "");  //处理包含, ￥ 或者$的金额
        int index = currency.indexOf(".");
        int length = currency.length();
        Long amLong = 0l;
        if(index == -1){
            amLong = Long.valueOf(currency+"00");
        }else if(length - index >= 3){
            amLong = Long.valueOf((currency.substring(0, index+3)).replace(".", ""));
        }else if(length - index == 2){
            amLong = Long.valueOf((currency.substring(0, index+2)).replace(".", "")+0);
        }else{
            amLong = Long.valueOf((currency.substring(0, index+1)).replace(".", "")+"00");
        }
        return amLong.toString();
    }

    /**
     * 获取随机字符串
     * @return
     */
    public static String getNonceStr() {
        // 随机数
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        return strTime + strRandom;
    }

    /**
     * description: 解析微信通知xml
     *
     * @param xml
     * @return
     * @author ex_yangxiaoyi
     * @see
     */
    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    public static Map parseXmlToList2(String xml) {
        Map retMap = new HashMap();
        try {
            StringReader read = new StringReader(xml);
            // 创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
            InputSource source = new InputSource(read);
            // 创建一个新的SAXBuilder
            SAXBuilder sb = new SAXBuilder();
            // 通过输入源构造一个Document
            Document doc = (Document) sb.build(source);
            Element root = doc.getRootElement();// 指向根节点
            List<Element> es = root.getChildren();
            if (es != null && es.size() != 0) {
                for (Element element : es) {
                    retMap.put(element.getName(), element.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retMap;
    }

}
