package com.ningpai.osgi.listener;

import com.ningpai.osgi.activator.Activator;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * 模块间session共享监听
 * @Author hehu
 * 2015-07-09 13:30:28
 */
public class BundleSessionAttributeListener implements
        HttpSessionAttributeListener {

    /**
     * 添加attribute时，同时往共享区添加
     * @param se session添加属性事件
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        Activator.getHttpSessionService().setSession(se.getSession());
    }
    /**
     * 删除attribute时，同时从共享区删除
     * @param se session删除属性事件
     */
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        Activator.getHttpSessionService().setSession(se.getSession());
    }
    /**
     * 替换attribute时，同时往共享区替换
     * @param se session替换属性事件
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        Activator.getHttpSessionService().setSession(se.getSession());
    }

}
