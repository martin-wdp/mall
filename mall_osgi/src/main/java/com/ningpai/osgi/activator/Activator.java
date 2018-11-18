package com.ningpai.osgi.activator;

import com.ningpai.osgi.service.HttpSessionService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

/**
 * <p>Activator</p>
 * <p>Discription :bundle启动监听,在bundle启动时，会执行start方法，保留相关引用，以便bundle启动后使用</p>
 * @author hehu
 * 2015年6月27日 上午3:02:23
 */
public class Activator implements BundleActivator {
    /** bundle启动时，保留BundleContext引用 */
    private static BundleContext bundleContext;

    /** bundle启动时，保留ServiceReference引用 */
    private static ServiceReference httpSessionServiceRef;

    /** bundle启动时，保留HttpSessionService引用 */
    private static HttpSessionService httpSessionService;




    /**
     * bundle启动时被调用，保留相关引用
     * @param context bundle上下文
     */
    @Override
    public void start(BundleContext context) throws Exception {
        bundleContext = context;
        httpSessionServiceRef = context.getServiceReference(HttpSessionService.class.getName());
        if(httpSessionServiceRef!=null) {
            httpSessionService = (HttpSessionService) context.getService(httpSessionServiceRef);
        }

    }

    /**
     * bundle被停用时调用
     */
    @Override
    public void stop(BundleContext context) throws Exception {
        //停止应用时，目前暂不需要做任何事情
    }

    /**
     * 返回启动时保留的服务引用
     * @return ServiceReference 服务引用，
     * 可通过ServiceReference结合BundleContext查询服务
     */
    public static ServiceReference getHttpSessionServiceRef() {
        return httpSessionServiceRef;
    }

    /**
     * 返回启动时保留的bundleContext
     * @return BundleContext bundleContext，启动时初始化，
     * 可用来查询服务，注册服务等
     */
    public static BundleContext getBundleContext() {
        return bundleContext;
    }

    /**
     * 返回启动时保留的HttpSessionService，
     * 可通过HttpSessionService获取每个bundle暴漏的Session，
     * 也可通过HttpSessionService将自己的session暴漏出给其他bundle使用
     * @return HttpSessionService
     */
    public static HttpSessionService getHttpSessionService() {
        return httpSessionService;
    }

}
