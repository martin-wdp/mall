package com.ningpai.common.safe;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * CSRFTokenManager
 */
public class CSRFTokenManager {

    static final String CSRP_PARAM_NAME = "Token";
    public static final String CSRF_TOKEN_FOR_SESSION_ATTR_NAME = "token";

    /**
     * CSRFTokenManager
     */
    private CSRFTokenManager() {
    }

    /**
     * 获取token值
     *
     * @param session
     *            传递的session
     * @return 创建的session字符串
     */
    public static String getTokenForSession(HttpSession session) {
        String token = null;
        synchronized (session) {
            token = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
            if (null == token) {
                token = UUID.randomUUID().toString();
                session.setAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME, token);
            }
        }
        return token;
    }

    /**
     * 验证token值是否合法
     *
     * @param token
     *            待验证的token值
     * @return true:合法 false:不合法
     */
    public static boolean valiToken(String token, HttpSession session) {
        String seTok = (String) session.getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
        try {
            if (null != token) {
                // 如果相同返回true否则返回false
                return token.equals(seTok) ? true : false;
            }
            return false;
        } finally {
            seTok = null;
        }
    }

    /**
     * 从请求中取出token
     *
     * @return 取出的token字符串
     */
    public static String getTokenFromRequest(HttpServletRequest request) {
        return (String) request.getSession().getAttribute(CSRF_TOKEN_FOR_SESSION_ATTR_NAME);
    }

}
