package com.ningpai.site.order.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ningpai.util.MyLogger;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;



/**
 * Created by pl on 2015/11/13.
 * Desc:
 */
public class GetWxOrderno {
    public static DefaultHttpClient httpclient;

    /**
     * 单比订单或者多笔订单生成不同的收款单信息 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GetWxOrderno.class);

    static
    {
        httpclient = new DefaultHttpClient();
        httpclient = (DefaultHttpClient)HttpClientConnectionManager.getSSLInstance(httpclient);
    }


    /**
     *description:获取预支付id
     *@param url
     *@param xmlParam
     *@return
     * @author ex_yangxiaoyi
     * @see
     */
    public static String getPayNo(String url,String xmlParam){
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
        String prepay_id = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            LOGGER.error(jsonStr);
            if(jsonStr.indexOf("FAIL")!=-1){
                return prepay_id;
            }
            Map map = doXMLParse(jsonStr);
            prepay_id  = (String) map.get("prepay_id");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return prepay_id;
    }

    /**
     *description:获取扫码支付连接
     *@param url
     *@param xmlParam
     *@return
     * @author ex_yangxiaoyi
     * @see
     */
    public static String getCodeUrl(String url,String xmlParam){
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(ClientPNames.ALLOW_CIRCULAR_REDIRECTS, true);
        HttpPost httpost= HttpClientConnectionManager.getPostMethod(url);
        String code_url = "";
        try {
            httpost.setEntity(new StringEntity(xmlParam, "UTF-8"));
            HttpResponse response = httpclient.execute(httpost);
            String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
            LOGGER.error("微信返回值："+jsonStr);
            if(jsonStr.indexOf("FAIL")!=-1){
                return code_url;
            }
            Map map = doXMLParse(jsonStr);
            code_url  = (String) map.get("code_url");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            LOGGER.error("微信支付异常:"+e.getMessage());
        }
        return code_url;
    }


    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws Exception {
        if(null == strxml || "".equals(strxml)) {
            return null;
        }

        Map m = new HashMap();
        InputStream in = String2Inputstream(strxml);
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        Iterator it = list.iterator();
        while(it.hasNext()) {
            Element e = (Element) it.next();
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if(children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流
        in.close();

        return m;
    }
    /**
     * 获取子结点的xml
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuffer sb = new StringBuffer();
        if(!children.isEmpty()) {
            Iterator it = children.iterator();
            while(it.hasNext()) {
                Element e = (Element) it.next();
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<" + name + ">");
                if(!list.isEmpty()) {
                    sb.append(getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }
    public static InputStream String2Inputstream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }
}
