package com.ningpai.util;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

/**
 * Geetest（已弃用）
 *
 * @author Zheng
 * @time 2014年7月10日 下午3:29:09
 */
public class GeetestLib {

    /**
     * SDK版本编号
     */
    private static final int verCode = 8;

    /**
     * SDK版本名称
     */
    private static final String verName = "15.1.28.1";

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GeetestLib.class);

    private static final String baseUrl = "api.geetest.com";

    private static final String api_url = "http://" + baseUrl;

    private static final int defaultIsMobile = 0;

    /**
     * 公钥
     */
    private String captchaId = "";

    /**
     * 私钥
     */
    private String privateKey = "";
    /**
     * the challenge
     */
    private String challengeId = "";

    /**
     * set the own private pictures,default is ""
     */
    private String picId = "";

    /**
     * he captcha product type,default is 'embed'
     */
    private String productType = "embed";

    /**
     * when the productType is popup,it needs to set the submitbutton
     */
    private String submitBtnId = "submit-button";

    /**
     * 是否是移动端的
     */
    private int isMobile = defaultIsMobile;// 1--true,0-false

    /**
     * 一个无参构造函数
     */
    public GeetestLib() {

        Properties pop = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("com/ningpai/web/config/geetest.properties");

        try {
            pop.load(inputStream);
        } catch (IOException e) {
            LOGGER.info(e);
            inputStream = null;
            pop = null;
        }
        captchaId = pop.getProperty("CAPTCHAID");
        privateKey = pop.getProperty("PRIVATEKEY");

    }

    public GeetestLib(String privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * processing before the captcha display on the web front
     *
     * @return
     */
    public int preProcess() {

        if (registerChallenge() != 1) {
            return 0;
        }

        return 1;

    }

    /**
     * generate the dynamic front source
     *
     * product display mode :float,embed,popup
     * 
     * @return
     */
    public String getGtFrontSource() {

        String frontSource = String.format("<script type=\"text/javascript\" src=\"%s/get.php?" + "gt=%s&challenge=%s", this.api_url, this.captchaId, this.challengeId);

        if (this.productType.equals("popup")) {
            frontSource += String.format("&product=%s&popupbtnid=%s", this.productType, this.submitBtnId);
        } else {
            frontSource += String.format("&product=%s", this.productType);
        }

        frontSource += "\"></script>";

        return frontSource;
    }

    /**
     * 获取极验的服务器状态
     *
     * @author Zheng
     * @email dreamzsm@gmail.com
     * @time 2014年7月10日 下午7:12:38
     * @return
     */
    public int getGtServerStatus() {

        try {
            final String GET_URL = api_url + "/check_status.php";
            if (readContentFromGet(GET_URL).equals("ok")) {
                return 1;
            } else {
                System.out.println("gServer is Down");
                return 0;
            }
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * generate a random num
     *
     * @return
     */
    public int getRandomNum() {

        return (int) (Math.random() * 100);
    }

    /**
     * Register the challenge
     *
     * @return
     */
    public int registerChallenge() {
        try {
            String GET_URL = api_url + "/register.php?gt=" + this.captchaId;
            String result_str = readContentFromGet(GET_URL);
            if (32 == result_str.length()) {
                this.challengeId = result_str;
                return 1;
            } else {
                System.out.println("gServer register challenge failed");
                return 0;
            }
        } catch (Exception e) {
            gtlog("exception:register api:");
            LOGGER.info(e);
        }
        return 0;
    }

    /**
     * 读取服务器
     *
     * @author Zheng dreamzsm@gmail.com
     * @time 2014年7月10日 下午7:11:11
     * @param getURL
     * @return
     * @throws java.io.IOException
     */
    private String readContentFromGet(String getURL) throws IOException {

        URL getUrl = new URL(getURL);
        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

        connection.setConnectTimeout(1000);// 设置连接主机超时（单位：毫秒）
        connection.setReadTimeout(1000);// 设置从主机读取数据超时（单位：毫秒）

        // 建立与服务器的连接，并未发送数据

        connection.connect();
        // 发送数据到服务器并使用Reader读取返回的数据
        StringBuffer sBuffer = new StringBuffer();

        InputStream inStream = null;
        byte[] buf = new byte[1024];
        inStream = connection.getInputStream();
        for (int n; (n = inStream.read(buf)) != -1;) {
            sBuffer.append(new String(buf, 0, n, "UTF-8"));
        }
        inStream.close();
        connection.disconnect();// 断开连接

        return sBuffer.toString();
    }

    /**
     * 判断一个表单对象值是否为空
     *
     * @time 2014年7月10日 下午5:54:25
     * @param gtObj
     * @return
     */
    private boolean objIsEmpty(Object gtObj) {
        if (gtObj != null) {
            return false;
        }

        return true;
    }

    /**
     * 检查客户端的请求是否为空--三个只要有一个为空，则判断不合法
     *
     * @time 2014年7月10日 下午5:46:34
     * @param request
     * @return
     */
    public boolean resquestIsLegal(HttpServletRequest request) {

        if (objIsEmpty(request.getParameter("geetest_challenge"))) {
            return false;
        }

        if (objIsEmpty(request.getParameter("geetest_validate"))) {
            return false;
        }

        if (objIsEmpty(request.getParameter("geetest_seccode"))) {
            return false;
        }

        return true;
    }

    /**
     * 检验验证请求 传入的参数为request--vCode 8之后不再更新,不推荐使用
     *
     * @time 2014年7月10日 下午6:34:55
     * @param request
     * @return
     */
    public boolean validateRequest(HttpServletRequest request) {

        return this.validate(request.getParameter("geetest_challenge"), request.getParameter("geetest_validate"), request.getParameter("geetest_seccode"));
    }

    /**
     * 增强版的验证信息,提供了更多的验证返回结果信息，以让客户服务器端有不同的数据处理。
     *
     * @param request
     * @return
     */
    public String enhencedValidateRequest(HttpServletRequest request) {

        String challenge = request.getParameter("geetest_challenge");
        String validate = request.getParameter("geetest_validate");
        String seccode = request.getParameter("geetest_seccode");

        String host = baseUrl;
        String path = "/validate.php";
        int port = 80;
        String query = "seccode=" + seccode;
        String response = "";

        try {
            if (validate.length() <= 0) {
                return "fail";
            }

            if (!checkResultByPrivate(challenge, validate)) {
                return "fail";
            }

            response = postValidate(host, path, query, port);
            gtlog("response: " + response);
        } catch (Exception e) {
            LOGGER.info(e);
            response = "";
        }

        gtlog("md5: " + md5Encode(seccode));

        if (response.equals(md5Encode(seccode))) {
            return "success";
        } else {
            return response;
        }

    }

    /**
     * the old api use before version code 8(not include)
     *
     * @param challenge
     * @param validate
     * @param seccode
     * @return
     * @time 2014122_171529 by zheng
     */
    private boolean validate(String challenge, String validate, String seccode) {
        String host = baseUrl;
        String path = "/validate.php";
        int port = 80;
        if (validate.length() > 0 && checkResultByPrivate(challenge, validate)) {
            String query = "seccode=" + seccode;
            String response = "";
            try {
                response = postValidate(host, path, query, port);
                gtlog(response);
            } catch (Exception e) {
                LOGGER.info(e);
                gtlog(response);
            }

            gtlog("md5: " + md5Encode(seccode));

            if (response.equals(md5Encode(seccode))) {
                return true;
            }
        }
        return false;

    }

    /**
     * Print out log message Use to Debug
     *
     * @time 2014122_151829 by zheng
     *
     * @param message
     */
    public void gtlog(String message) {

    }

    private boolean checkResultByPrivate(String origin, String validate) {
        String encodeStr = md5Encode(privateKey + "geetest" + origin);
        return validate.equals(encodeStr);
    }

    private String postValidate(String host, String path, String data, int port) throws Exception {
        String response = "error";
        InetAddress addr = InetAddress.getByName(host);
        Socket socket = new Socket(addr, port);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF8"));
        wr.write("POST " + path + " HTTP/1.0\r\n");
        wr.write("Host: " + host + "\r\n");
        wr.write("Content-Type: application/x-www-form-urlencoded\r\n");
        wr.write("Content-Length: " + data.length() + "\r\n");
        wr.write("\r\n"); // 以空行作为分割
        // 发送数据
        wr.write(data);
        wr.flush();
        // 读取返回信息
        BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line;
        while ((line = rd.readLine()) != null) {
            System.out.println(line);
            response = line;
        }
        wr.close();
        rd.close();
        socket.close();
        return response;
    }

    /**
     * md5 加密
     *
     * @time 2014年7月10日 下午3:30:01
     * @param plainText
     * @return
     */
    public String md5Encode(String plainText) {
        String re_md5;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte[] b = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            LOGGER.info(e);
            re_md5 = "";
        }
        return re_md5;
    }

    public String getSubmitBtnId() {
        return submitBtnId;
    }

    public void setSubmitBtnId(String submitBtnId) {
        this.submitBtnId = submitBtnId;
    }

    public String getChallengeId() {
        return challengeId;
    }

    public void setChallengeId(String challengeId) {
        this.challengeId = challengeId;
    }

    /**
     * 获取版本编号
     *
     * @author Zheng
     * @email dreamzsm@gmail.com
     * @time 2014年7月11日 上午11:07:11
     * @return
     */
    public String getVersionInfo() {
        return verName;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(int isMobile) {
        this.isMobile = isMobile;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public int getVerCode() {
        return verCode;
    }

    public String getVerName() {
        return verName;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

}
