package com.ningpai.common.util;

import com.ningpai.customer.bean.Customer;
import com.ningpai.des.DESCoder;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.util.MyLogger;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * 生成账户激活，重新设置密码的链接
 */
public class GenerateLinkUtils {

    // 字符串常量
    private static final String CHECK_CODE = "checkCode";
    // 特殊字符转换
    private static final String KEY = "5qian2mi0";

    private static final MyLogger LOGGER = new MyLogger(GenerateLinkUtils.class);

    private GenerateLinkUtils() {

    }


    /**
     * 生成账户激活链接
     */
    public static String generateActivateLink(HttpServletRequest request, CustomerAllInfo user) throws Exception {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        //初始化密钥
        String key = DESCoder.initKey();
        //生成密钥中的加号无法从前台传送到后台
        while (key.indexOf("+") >= 0) {
            key = DESCoder.initKey();
        }
        //加密Id
        byte[] inputData1 = (user.getCustomerId()).toString().getBytes();
        inputData1 = DESCoder.encrypt(inputData1, key);
        //加密邮箱
        byte[] inputData2 = user.getInfoEmail().getBytes();
        inputData2 = DESCoder.encrypt(inputData2, key);

        //        byte[] outputData = DESCoder.decrypt(inputData, key1);
        //        String outputStr = new String(outputData);
        url = url.substring(0, url.lastIndexOf("/")) + "/validbindemail.html?" + CHECK_CODE + "=" +
                generatePwdCheckcode(user) + "&d=" + DESCoder.encryptBASE64(inputData1) + "&mail=" + DESCoder
                .encryptBASE64(inputData2) + "&k=" + key;
        if (url.indexOf("+") > 0) {
            url = url.replaceAll("\\+", KEY);
        }
        return url;
    }

    /**
     * 忘记密码专用生成账户激活链接
     */
    public static String forgetgenerateActivateLink(HttpServletRequest request, CustomerAllInfo user) throws Exception {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        // 初始化密钥
        String key = DESCoder.initKey();
        // 生成密钥中的加号无法从前台传送到后台
        while (key.indexOf("+") >= 0) {
            key = DESCoder.initKey();
        }
        // 加密Id
        byte[] inputData1 = (user.getCustomerId()).toString().getBytes();
        inputData1 = DESCoder.encrypt(inputData1, key);
        // 加密邮箱
        byte[] inputData2 = user.getInfoEmail().getBytes();
        inputData2 = DESCoder.encrypt(inputData2, key);

        // byte[] outputData = DESCoder.decrypt(inputData, key1);
        // String outputStr = new String(outputData);
        url = url.substring(0, url.lastIndexOf("/")) + "/validate/newvalidbindemail.html?" + CHECK_CODE + "=" + generatePwdCheckcode(user) + "&d=" + DESCoder.encryptBASE64(inputData1)
                + "&mail=" + DESCoder.encryptBASE64(inputData2) + "&k=" + key;
        if (url.indexOf("+") > 0) {
            url = url.replaceAll("\\+", KEY);
        }
        //通过邮箱跳转过去
        request.getSession().setAttribute("user",user);
        request.getSession().setAttribute("uId",user.getCustomerId());
        return url;
    }

    /**
     * 生成找回密码链接
     */
    public static String generateFindPwdLink(HttpServletRequest request, Customer user) {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        return url.substring(0, url.lastIndexOf("/")) + "/validpwdemail.html?" + CHECK_CODE + "=" + generatePwdCheckcode(user) + "&d=" + user.getCustomerId();
    }

    /**
     * 生成找回密码链接
     */
    public static String generateCheckEmailLink(HttpServletRequest request, Customer user) throws Exception {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        // 初始化密钥
        String key = DESCoder.initKey();
        // 生成密钥中的加号无法从前台传送到后台
        while (key.indexOf("+") >= 0) {
            key = DESCoder.initKey();
        }
        // 加密Id
        byte[] inputData1 = (user.getCustomerId()).toString().getBytes();
        inputData1 = DESCoder.encrypt(inputData1, key);

        // 加密type
        byte[] inputData2 = (request.getSession().getAttribute("utype")).toString().getBytes();
        inputData2 = DESCoder.encrypt(inputData2, key);

        url = url.substring(0, url.lastIndexOf("/")) + "/valididemail.html?" + CHECK_CODE + "=" + generatePwdCheckcode(user) + "&type=" + DESCoder.encryptBASE64(inputData2)
                + "&d=" + DESCoder.encryptBASE64(inputData1) + "&k=" + key;
        // 判断是否存在+号
        if (url.indexOf("+") >= 0) {
            // 把+号转义
            url = url.replaceAll("\\+", KEY);
        }
        return url;
    }

    /**
     * 生成验证帐户的MD5校验码
     *
     * @param user
     *            要激活的帐户
     * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
     */
    public static String generateCheckcode(Customer user) {
        return md5(String.valueOf(new Date().getTime()));
    }

    /**
     * 生成验证帐户的MD5校验码
     *
     * @param user
     *            要激活的帐户
     * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
     */
    public static String generatePwdCheckcode(Customer user) {
        Date d = new Date();
        user.setPwdAeadTime(d);
        user.setPwdCaptcha(md5(String.valueOf(d.getTime())));
        return user.getPwdCaptcha();
    }

    /**
     * 验证校验码是否和注册时发送的验证码一致
     *
     * @param user
     *            要激活的帐户
     * @param request
     *            注册时发送的校验码
     * @return 如果一致返回1，否则返回0
     */
    public static int verifyCheckcode(Customer user, ServletRequest request) {
        String checkCode = request.getParameter(CHECK_CODE);
        // 若值相等则返回1
        if (generateCheckcode(user).equals(checkCode)) {

            return 1;
        }
        // 不相等则返回0
        return 0;
    }

    /**
     * md5 加密方式
     *
     * @param string
     *            需要加密的字符串
     * @return 加密后的字符串
     */
    public static String md5(String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("",e);
            return new String();
        }
    }

    /**
     * byte数组转换为hex字符串
     *
     * @param byteArray
     *            byte数组
     * @return hex字符串
     */
    private static String bytes2Hex(byte[] byteArray) {
        StringBuilder strBuf = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }

}
