package com.ningpai.util;

import org.apache.commons.httpclient.methods.PostMethod;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.NameValuePair;

/**
 * create wdp
 */
public class MSMSendUtil {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(MSMSendUtil.class);

    /**
     * zyer send
     * 
     * @return
     * @throws IOException
     */
    public static boolean sendMsm(String userId, String loginName, String password, String[] mobiles, String content, String expSmsId, String httpUrl) throws IOException {
       /* StringBuffer sub = new StringBuffer();
        BufferedReader br;
        URL url;
        HttpURLConnection con;
        String line;*/

        boolean result = false;
        HttpClient client = new HttpClient();
        PostMethod post = new PostMethod(httpUrl);
        post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

        String mobile = "";
        for (int j = 0; j < mobiles.length; j++) {
            mobile += mobiles[j];
            if (j < mobiles.length - 1) {
                mobile += ",";
            }
        }
        NameValuePair[] data = {
                new NameValuePair("account", loginName),
                new NameValuePair("pswd", password),
                new NameValuePair("mobile", mobile),
                new NameValuePair("msg", content),
                new NameValuePair("needstatus", "true"),
                new NameValuePair("product", ""),
                new NameValuePair("extno", "") };

        try {
            post.setRequestBody(data);
            client.executeMethod(post);


           String  strResult = new String(post.getResponseBodyAsString());
            String[] arrstr=strResult.split(",");
            if(arrstr.length>1)
            {
                String[] resultMsg = arrstr[1].split("\n");
                if( resultMsg[0].equals("0"))
                {
                    result = true;
                    LOGGER.info("短信提交成功，返回值：" + arrstr[2]);
                }
                else
                {
                    result = false;
                    LOGGER.info("短信提交失败，错误码：" + arrstr[0]);
                    //错误码 详见接口文档
                }
            }
            else
            {
                result = false;
                System.out.println("短信提交异常，返回值：" + result);
            }


        } catch (IOException e) {
            result = false;
            e.printStackTrace();
        }
        /*try {
           *//* String bBstring = "";
            String bQstring = "";
            if (expSmsId != null) {
                String[] baob = expSmsId.split("\\|");

                if (baob.length == 2) {
                    bBstring = baob[0];
                    bQstring = baob[1];
                }
            }

            // 设置发送内容的编码方式
            String sendContent = URLEncoder.encode((bBstring + content + bQstring).replaceAll("<br/>", " "), "UTF-8");// 发送内容
            String mobile = "";
            for (int j = 0; j < mobiles.length; j++) {
                mobile += mobiles[j];
                if (j < mobiles.length - 1) {
                    mobile += ",";
                }
            }

            url = new URL(httpUrl + "?action=send&userid=&account=" + loginName + "&pswd=" + password + "&mobile=" + mobile + "&msg=" + sendContent + "&needstatus=true");
            con = (HttpURLConnection) url.openConnection();

            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            // br=new BufferedReader(new InputStreamReader(url.openStream()));

            while ((line = br.readLine()) != null) {
                // 追加字符串获得XML形式的字符串
                sub.append(line + "");
                // System.out.println("提取数据 :  "+line);
            }
            br.close();*//*

        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("",e);
        }*/
        return result;
    }

    /**
     *  解析xml字符串
     * */
    public static boolean readStringXml(String xml) {
        Document doc;

        try {
            // 将字符转化为XML
            doc = DocumentHelper.parseText(xml);
            // 获取根节点
            Element rootElt = doc.getRootElement();

            // 拿到根节点的名称
            // System.out.println("根节点名称："+rootElt.getName());

            // 获取根节点下的子节点的值
            String returnstatus = rootElt.elementText("returnstatus").trim();
            String message = rootElt.elementText("message").trim();

            System.out.println("返回状态为：" + returnstatus);
            System.out.println("返回信息提示：" + message);
            if ("Success".equals(returnstatus)) {
                return true;
            } else {
                return false;
            }
        } catch (DocumentException e) {
            LOGGER.error("",e);
            return false;
        }

    }
}
