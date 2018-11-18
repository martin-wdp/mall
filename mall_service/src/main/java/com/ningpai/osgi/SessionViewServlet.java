package com.ningpai.osgi;

import com.ningpai.osgi.service.impl.HttpSessionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

/**
 * 查看系统session
 * Created by NP-HEHU on 2015/8/22.
 * @date 2015-9-21 19:42:05
 * @version 1.0
 */
public class SessionViewServlet extends HttpServlet{
    /**
     * 查看系统session情况
     * @param req HttpServletRequest
     * @param resp HttpServletResponse
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //获取所有session
        Map<String,HttpSession> map = HttpSessionServiceImpl.getSessionMap();
        int count = 0;
        //遍历所有session
        for (Map.Entry<String, HttpSession> entry : map.entrySet()) {
            //打印sessionId-session
            out.append(entry.getKey() + "=======id-session======== " + entry.getValue()+"\r\t");
            HttpSession session = entry.getValue();
            //拿到session属性名
            Enumeration<String> bundleSessionAttributteNames = session.getAttributeNames();
            String bundleSessionAttributeName = null;
            //循环打印session中的key和value
            while(bundleSessionAttributteNames.hasMoreElements()) {
                //循环游标
                bundleSessionAttributeName = bundleSessionAttributteNames.nextElement();
                out.append(bundleSessionAttributeName + " =========k-v========== " + session.getAttribute(bundleSessionAttributeName));
            }
            count ++;
            //计算session个数
            out.append("======================="+count+" ======================== ");
        }
        out.flush();
    }
}
