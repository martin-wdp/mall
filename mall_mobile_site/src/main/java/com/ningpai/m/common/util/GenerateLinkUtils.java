package com.ningpai.m.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.customer.bean.Customer;
import com.ningpai.util.MyLogger;

/**
 * 生成账户激活，重新设置密码的链接
 */
public class GenerateLinkUtils {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(GenerateLinkUtils.class);

    private static final String CHECK_CODE = "checkCode";

    /**
     * 无参构造
     */
    private GenerateLinkUtils() {

    }

    /**
     * 生成账户激活链接
     */
    public static String generateActivateLink(HttpServletRequest request, Customer user) {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();

        return url.substring(0, url.lastIndexOf("/")) + "/validbindemail.html?" + CHECK_CODE + "=" + generateCheckcode(user) + "&d=" + user.getCustomerId();
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
    public static String generateCheckEmailLink(HttpServletRequest request, Customer user) {
        // 获得客户端发送请求的完整url
        String url = request.getRequestURL().toString();
        return url.substring(0, url.lastIndexOf("/")) + "/valididemail.html?" + CHECK_CODE + "=" + generatePwdCheckcode(user) + "&type="
                + request.getSession().getAttribute("utype") + "&d=" + user.getCustomerId();
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
     * MD5
     * 
     * @param string
     * @return
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
            return null;
        }
    }

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
