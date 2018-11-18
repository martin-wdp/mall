package com.ningpai.osgi.listener;

import com.ningpai.osgi.activator.Activator;
import com.ningpai.osgi.service.HttpSessionService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/**
 * 
 * <p>BundleSessionListener</p>
 * <p>Discription :Session监听，若bundle要将自己的session暴露给其他bundle使用，则要在web.xml中配置监听</p>
 * @author hehu
 * @date 2015年6月27日 上午3:09:25
 * @version 1.0
 */
public class BundleSessionListener implements HttpSessionListener {

    /**
     * session创建时，会自动将当前bundle的session暴露到共享区
     * @param httpSessionEvent session创建事件
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        //通过Activator得到session服务接口
        HttpSessionService httpSessionService = Activator.getHttpSessionService();
        //如果session服务接口不是空
        if(httpSessionService!=null) {
            //保存session
            Activator.getHttpSessionService().setSession(httpSessionEvent.getSession());
        }
    }
    /**
     * session销毁时，会自动将当前bundle的session从共享区删除
     * @param httpSessionEvent session销毁事件
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        //通过Activator得到session服务接口
        HttpSessionService httpSessionService = Activator.getHttpSessionService();
        //如果session服务接口不是空
        if(httpSessionService!=null) {
            //把session从系统中删除
            Activator.getHttpSessionService().removeSession(httpSessionEvent.getSession());
        }
    }

}
