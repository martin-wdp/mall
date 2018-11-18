package com.ningpai.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.ningpai.osgi.service.HttpSessionService;
import com.ningpai.osgi.service.impl.HttpSessionServiceImpl;

/**
 * 
 * <p>ServiceActivator</p>
 * <p>Discription :bundle启动时，将服务暴露，提供服务</p>
 * @author hehu
 * 2015年6月27日 上午3:21:59
 */
public class ServiceActivator implements BundleActivator {
    /** HttpSessionService 服务注册 */
    private ServiceRegistration httpSessionServiceReg;

    /**
     * bundle启动时，将HttpSessionService暴露，提供服务
     * @param context bundle上下文
     */
    @Override
    public void start(BundleContext context) throws Exception {
        httpSessionServiceReg = context.registerService(HttpSessionService.class.getName(), new HttpSessionServiceImpl(), null);
    }

    /**
     * bundle停用时，将HttpSessionService停用，停止服务
     * @param context bundle上下文
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        httpSessionServiceReg.unregister();
    }

}
