package com.qpmall.controller;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by pl on 2015/10/28.
 * Desc:
 */
public class VinWebService {
    /**
     * 测试函数
     * @param args
     */
    public static void main(String args[]) throws Exception{
        String result="";
        try {
            result = VinWebService.GetVinObj("http://58.221.57.73:8088/webService/YSBSService.asmx","LSVAH21ZX72332254","CJ6");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(result.contains("Model_ID")){
            Pattern p = Pattern.compile("<Model_ID>(.*?)</Model_ID>");
            Matcher m = p.matcher(result);
            ArrayList<String> strs = new ArrayList<String>();
            while (m.find()) {
                strs.add(m.group(1));
            }
            for (String s : strs){
                System.out.println(s);
            }
        }
    }

    public static String GetVinObj(String url,String vin,String key) throws Exception
    {
        //服务的地址
        URL wsUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) wsUrl.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
        OutputStream os = conn.getOutputStream();
        //请求体
        String soap = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
                "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <GetCXInfoByVIN xmlns=\"http://tempuri.org/\">\n" +
                "      <vin>"+vin+"</vin>\n" +
                //"      <bslx>"+key+"</bslx>\n" +
                "    </GetCXInfoByVIN>\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>";
        os.write(soap.getBytes());
        InputStream is = conn.getInputStream();
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(is);
        // 获取根元素
        Element root = document.getRootElement();
        String str="";
        // 迭代输出
        for (Iterator iter = root.elementIterator(); iter.hasNext();)
        {
            Element e = (Element) iter.next();
            System.out.println(e.getStringValue());
            str=e.getStringValue();
        }
        is.close();
        os.close();
        conn.disconnect();
        return str;
    }
}
