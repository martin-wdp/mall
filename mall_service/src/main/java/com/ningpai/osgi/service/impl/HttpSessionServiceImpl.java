package com.ningpai.osgi.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSession;

import com.ningpai.osgi.service.HttpSessionService;
/**
 * 
 * <p>HttpSessionServiceImpl</p>
 * <p>Discription :Session共享使用</p>
 * @author hehu
 * 2015年6月27日 上午3:21:29
 */
public class HttpSessionServiceImpl implements HttpSessionService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(HttpSessionServiceImpl.class);

    /** session共享使用 */
    private static Map<String,HttpSession> sessionMap = new HashMap<String,HttpSession>();

    /**
     * 根据sessionID获取session
     * @param sessionId session唯一标识
     * @return HttpSession
     */
    @Override
    public HttpSession getSession(String sessionId) {
        LOGGER.debug("getting session from share=======" + sessionId + "====" + sessionMap.get(sessionId));
        return sessionMap.get(sessionId);
    }

    /**
     * 缓存session
     * @param session 自己bundle的session
     */
    public void setSession(HttpSession session) {
        LOGGER.debug("setting session from share=======" + session.getId() + "====");
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    /**
     * 删除session
     * @param session 自己bundle的session
     */
    @Override
    public void removeSession(HttpSession session) {
        LOGGER.debug("remove session=============" + session.getId());
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    /**
     * 获取session缓存
     * @return session缓存
     */
    public static Map<String,HttpSession> getSessionMap() {
        return sessionMap;
    }

}
