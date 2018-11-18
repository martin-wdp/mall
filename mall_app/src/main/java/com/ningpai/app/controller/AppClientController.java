package com.ningpai.app.controller;

import com.ningpai.app.bean.App;
import com.ningpai.app.bean.AppInstall;
import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.service.AppClientService;
import com.ningpai.app.service.AppInstallService;
import com.ningpai.app.service.AppMarketKeyService;
import com.ningpai.app.util.AppConstant;
import com.ningpai.app.util.HttpRequestUtil;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.service.ManagerServiceInterface;
import com.ningpai.osgi.activator.Activator;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.PageBean;
import com.ningpai.util.PropertieUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Properties;

/**
 * 属客户端
 * 客户端应用管理器
 * @author NP-Heh
 * 2015年6月30日 下午4:07:34
 */
@Controller
public class AppClientController {

    /** appClientService */
    @Resource(name="AppClientService")
    private AppClientService appClientService;

    /** 应用安装记录Service */
    @Resource(name="AppInstallService")
    private AppInstallService appInstallService;
    /**网站基本信息接口 */
    @Resource(name="basicSetService")
    private BasicSetService basicSetService;
    /**网站秘钥接口 */
    @Resource(name="AppMarketKeyService")
    private AppMarketKeyService appMarketKeyService;

    /** 管理员接口 */
    @Resource(name = "managerService")
    private ManagerServiceInterface managerService;
    /**httpRequest服务接口 */
    @Resource(name = "HttpRequestService")
    private HttpRequestUtil httpRequestUtil;

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppClientController.class);


    /**
     * App市场页面，访问时，会首先判断网站基本信息是否有填写，没有回跳转空
     * 前端已有验证，缺少必填项时，会有具体提示
     * 网站基本信息填写完整后，会把网站的基本信息传送到管理后台，并返回一个秘钥
     * 保存返回的秘钥后，跳转到应用市场列表页面
     * @return 应用市场列表页面
     */
    @RequestMapping("app")
    public ModelAndView app() {
        //查询网站基本信息
        BasicSet basicSet = basicSetService.findBasicSet();
        //判断是否有基本信息，没有返回空
        if(basicSet==null) {
            return null;
        }
        //判断网站地址是否有填写，没有返回空
        if("".equals(basicSet.getBsetAddress())) {
            return null;
        }
        //判断网站负责人是否有填写，没有返回空
        if("".equals(basicSet.getBsetAdmin())) {
            return null;
        }
        //判断网站联系人电话是否有填写，没有返回空
        if("".equals(basicSet.getBsetPhone())) {
            return null;
        }
        //判断网站邮箱是否有填写，没有返回空
        if("".equals(basicSet.getBsetEmail())) {
            return null;
        }
        //查询本地存储的密钥
        AppMarketKey marketKey = appMarketKeyService.select(1L);
        //判断秘钥是否过期，或是否存在，
        if(marketKey==null || marketKey.getEndTime().getTime()<System.currentTimeMillis()) {
            //秘钥不存在或过期时，去服务器获取秘钥，并保存
            marketKey = appClientService.getAppMarketKeyFromServer(basicSet);
        }
        return new ModelAndView("jsp/app/app").addObject("marketKey",marketKey).addObject("basicSet",basicSet);
    }
    
    /**
     * 从服务器查询所有app
     * @param pb 分页参数
     * @param app 查询信息
     * @param response 
     * @return 应用列表的json字符串
     * @throws java.io.IOException
     */
    @RequestMapping("appclient")
    public String queryApps(PageBean pb,App app,HttpServletResponse response) throws IOException  {
        //设置返回值编码
        response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();

            Properties properties = PropertieUtil.readPropertiesFile(AppClientController.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/app.properties"));
            //App市场服务器地址，可以是域名
            String serverUrl = properties.getProperty("APP_SERVER");

            //查询本地存储的密钥状态
            int appMarketKeyState = queryAppMarketKeyState();
            if(appMarketKeyState<0) {
                out.append(appMarketKeyState+"");
            } else {
                //查询本地存储的密钥
                AppMarketKey marketKey = appMarketKeyService.select(1L);
                //从App市场服务器获取App列表
                String result = httpRequestUtil.sendGet(serverUrl+"applist.htm?pageNo="+pb.getPageNo()+"&appName="+URLEncoder.encode(app.getAppName(), "utf-8")+"&appMarketKey="+marketKey.getAppMarketKey());
                out.append(result);
            }
        } finally {
            if(out!=null) {
                out.flush();
                out.close();
            }
        }

        return null;
    }

    /**
     * 让每一台服务器都下载应用
     * @param id 应用id
     * @param request HttpServletRequest
     * @return 1表示正常发送请求，-2表示已经在安装
     */
    @RequestMapping("downloadapp")
    @ResponseBody
    public Integer downloadAppForAllServer(Long id,HttpServletRequest request) {
        // 安装记录，客户自己安装应用的记录
        AppInstall appInstall = appInstallService.selectByAppId(id);
        if(appInstall!=null && StringUtils.equals(appInstall.getInstallStatus(),"2")) {
            return -2;
        }
        //启动后台安装程序
        try {
            httpRequestUtil.invokeMethodOnAllServers("downloadbossapp","id="+id,"1",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR,e);
        }
        //启动商家后台安装程序
        try {
            httpRequestUtil.invokeMethodOnAllServers("downloadthirdapp","id="+id,"2",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //启动前台安装程序
        try {
            httpRequestUtil.invokeMethodOnAllServers("downloadsiteapp","id="+id,"3",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        return 1;
    }


    /**
     * 查询app详情
     * @param id 主键id
     * @return json app详情
     */
    @RequestMapping("queryAppDetail")
    @ResponseBody
    public Object appDetail(Long id) {
        return appClientService.queryAppDetailForShow(id);
    }

    /**
     * 让每一台服务器都启动应用
     * @param id 应用id
     * @param request HttpServletRequest
     */
    @RequestMapping("startapp")
    @ResponseBody
    public Integer startAppForAllServer(Long id,HttpServletRequest request) {
        //后台启动
        try {
            httpRequestUtil.invokeMethodOnAllServers("startbossapp","id="+id,"1",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR,e);
        }
        //商家启动
        try {
            httpRequestUtil.invokeMethodOnAllServers("startthirdapp","id="+id,"2",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //启动前台
        try {
            httpRequestUtil.invokeMethodOnAllServers("startsiteapp","id="+id,"3",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //将session中的菜单重新覆盖
        Manager manager =  managerService.queryManagerByName((String) request.getSession().getAttribute("name"));
        request.getSession().setAttribute(AppConstant.MENUS, managerService.queryMenuByMangerName(manager.getUsername()));
        return 1;
    }

    /**
     * 让每一台服务器都停止应用
     * @param id 应用id
     * @param request HttpServletRequest
     */
    @RequestMapping("stopapp")
    @ResponseBody
    public Integer stopAppForAllServer(Long id,HttpServletRequest request) {
        //停止后台
        try {
            httpRequestUtil.invokeMethodOnAllServers("stopbossapp","id="+id,"1",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR,e);
        }
        //停止商家
        try {
            httpRequestUtil.invokeMethodOnAllServers("stopthirdapp","id="+id,"2",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //停止前台应用
        try {
            httpRequestUtil.invokeMethodOnAllServers("stopsiteapp","id="+id,"3",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //将session中的菜单重新覆盖
        Manager manager =  managerService.queryManagerByName((String) request.getSession().getAttribute("name"));
        request.getSession().setAttribute(AppConstant.MENUS, managerService.queryMenuByMangerName(manager.getUsername()));
        return 1;
    }

    /**
     * 让每一台服务器都卸载应用
     * @param id 应用id
     * @param request HttpServletRequest
     */
    @RequestMapping("updateapp")
    @ResponseBody
    public Integer updateAppForAllServer(Long id,HttpServletRequest request) {
        try {
            httpRequestUtil.invokeMethodOnAllServers("updatebossapp","id="+id,"1",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        try {
            httpRequestUtil.invokeMethodOnAllServers("updatethirdapp","id="+id,"2",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        try {
            httpRequestUtil.invokeMethodOnAllServers("updatesiteapp","id="+id,"3",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        appInstallService.modifyAppUpdateFlag(id);
        //将session中的菜单重新覆盖
        Manager manager =  managerService.queryManagerByName((String) request.getSession().getAttribute("name"));
        request.getSession().setAttribute(AppConstant.MENUS, managerService.queryMenuByMangerName(manager.getUsername()));
        return 1;
    }

    /**
     * 让每一台服务器都卸载应用
     * @param id 应用id
     * @param request HttpServletRequest
     */
    @RequestMapping("uninstallapp")
    @ResponseBody
    public Integer uninstallAppForAllServer(Long id,HttpServletRequest request) {
        try {
            httpRequestUtil.invokeMethodOnAllServers("uninstallbossapp","id="+id,"1",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        try {
            httpRequestUtil.invokeMethodOnAllServers("uninstallthirdapp","id="+id,"2",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        try {
            httpRequestUtil.invokeMethodOnAllServers("uninstallsiteapp","id="+id,"3",request);
        } catch (IOException e) {
            LOGGER.error(AppConstant.ACCESS_ERROR, e);
        }
        //将session中的菜单重新覆盖
        Manager manager =  managerService.queryManagerByName((String) request.getSession().getAttribute("name"));
        request.getSession().setAttribute(AppConstant.MENUS, managerService.queryMenuByMangerName(manager.getUsername()));
        return 1;
    }
    /**
     * 查询客户端所有安装记录
     * @return json格式的App安装记录
     */
    @RequestMapping("queryAllInstallApps")
    @ResponseBody
    public List<AppInstall> queryAllInstallApps() {
        return appInstallService.queryAllInstallApps();
    }

    /**
     * 查询应用的开启状态
     * @param id appId
     * @return App的状态
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("queryappstate")
    @ResponseBody
    public int queryAppState(Long id) {
        try {
            //App安装记录
            AppInstall appInstall = appInstallService.selectByAppId(id);
            //获取bundle上下文
            BundleContext bundleContext = Activator.getBundleContext();
            //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而查询
            Bundle bundle =bundleContext==null?null:bundleContext.getBundle(appInstall.getBundleId());
            return bundle==null?Bundle.UNINSTALLED:bundle.getState();
        } catch (Exception e) {
            //只有在未安装状态下，才会抛异常
            LOGGER.error("应用未安装",e);
            return Bundle.UNINSTALLED;
        }
    }
    /**
     * 查询应用是否可以访问
     * @param id appId
     * @return App的状态
     * int UNINSTALLED = 1;
     * int INSTALLED = 2;
     * int RESOLVED = 4;
     * int STARTING = 8;
     * int STOPPING = 16;
     * int ACTIVE = 32;
     */
    @RequestMapping("isBundleAccessable")
    @ResponseBody
    public String isBundleAccessable(Long id,HttpServletRequest request) {
        try {

            //App安装记录
            AppInstall appInstall = appInstallService.selectByAppId(id);
            //获取bundle上下文
            BundleContext bundleContext = Activator.getBundleContext();
            //通过App安装记录，获取App在karaf中的bundleid，通过bundleId获取Bundle，进而查询
            Bundle bundle =bundleContext==null?null:bundleContext.getBundle(appInstall.getBundleId());
            if(bundle==null) {
                return "-2";
            }
            return httpRequestUtil.sendGet(request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/" + bundle.getSymbolicName() + "/appStartedNotice.htm");
        } catch (Exception e) {
            //只有在未安装状态下，才会抛异常
            LOGGER.error("应用未安装",e);
            return "-1";
        }
    }

    /**
     * 查询应用市场秘钥的状态
     * @return 秘钥状态：-1秘钥不存在，-2秘钥过期，1秘钥正常
     */
    @RequestMapping("queryAppMarketKeyState")
    @ResponseBody
    public int queryAppMarketKeyState() {
        //查询本地存储的密钥
        AppMarketKey marketKey = appMarketKeyService.select(1L);
        //判断秘钥是否存在，不存在，返回-1；
        if(marketKey==null) {
            return -1;
        }
        //判断秘钥是否过期,过期，返回-2；
        if(marketKey.getEndTime().getTime()<System.currentTimeMillis()) {
            return -2;
        }
        //秘钥正常，返回1；
        return 1;
    }
}
