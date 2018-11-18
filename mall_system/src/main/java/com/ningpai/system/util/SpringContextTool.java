package com.ningpai.system.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring上下文工具类
 * 
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 13:17:21
 * @version V1.0
 */
public class SpringContextTool implements ApplicationContextAware {
    /** 内容 */
    private static ApplicationContext context;

    /**
     * acx
     * @param acx
     */
    public static void setContext(ApplicationContext acx) {
        SpringContextTool.context = acx;
    }

    /**
     * 静态getApplicationContext
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 通过配置的Bean名字获得Bean
     * 
     * @param beanName
     *            Bean名字
     * @return Bean
     */
    public static Object getBean(String beanName) {
        if (beanName == null || beanName.length() == 0) {
            return null;
        } else {
            return SpringContextTool.getApplicationContext().getBean(beanName);
        }
    }

    /**
     * applicationContext
     * 
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    public void setApplicationContext(ApplicationContext applicationContext) {

    }
}
