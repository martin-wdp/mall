package com.ningpai.osgi.service;

import javax.servlet.http.HttpSession;

/**
 * 
 * <p>HttpSessionService</p>
 * <p>Discription :通过该接口可使用共享区的session，也可往共享区的session中暴露自己的session</p>
 * @author hehu
 * 2015年6月27日 上午3:13:43
 */
public interface HttpSessionService {

    /**
     * 从共享区获取session
     * @param sessionId session唯一标识
     * @return 共享区session
     */
    HttpSession getSession(String sessionId);

    /**
     * 将自己bundle的session放入共享区
     * @param session 自己bundle的session
     */
    void setSession(HttpSession session);

    /**
     * 将bundle的session从共享区删除
     * @param session 自己bundle的session
     */
    void removeSession(HttpSession session);
}
