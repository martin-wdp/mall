package com.ningpai.osgi.service;

import javax.servlet.http.HttpSession;

/**
 * 
 * <p>HttpSessionService</p>
 * <p>Discription :该接口用来提供共享Session服务，提供获取共享区的Session，将bundle的session放入共享区，以及从共享区删除bundle的session</p>
 * @author hehu
 * 2015年6月27日 上午3:17:42
 */
public interface HttpSessionService {

    /**
     * 从session共享区获取session
     * @param sessionId session唯一标识
     * @return 共享区的session
     */
    HttpSession getSession(String sessionId);

    /**
     * 将自己bundle的session放入共享区
     * @param session 自己bundle的session
     */
    void setSession(HttpSession session);

    /**
     * 将session从共享区删除
     * @param session 自己bundle的session
     */
    void removeSession(HttpSession session);
}
