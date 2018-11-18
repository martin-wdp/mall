package com.ningpai.login.qq;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.ningpai.util.MyLogger;
import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;

/**
 * Date: 12-12-4 Time: 上午10:28
 */
public class IndexServlet extends HttpServlet {

    private static final long serialVersionUID = 819802262562706148L;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(IndexServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException e) {
            LOGGER.error(""+e);
            LOGGER.error(e.toString());
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
