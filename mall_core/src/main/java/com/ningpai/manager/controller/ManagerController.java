package com.ningpai.manager.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.*;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logcore.util.OperaLogUtil;
import com.ningpai.manager.bean.Authority;
import com.ningpai.manager.bean.Manager;
import com.ningpai.manager.bean.ManagerAuthority;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.AuthorityMapper;
import com.ningpai.manager.service.IndexCountBeanService;
import com.ningpai.manager.service.ManagerServiceInterface;
import com.ningpai.manager.service.ManagerSmsService;
import com.ningpai.manager.service.MenuServiceInterface;

/**
 * 管理员控制器
 *
 * @author AthrunNatu
 * @version 0.0.1
 * @since 2013年12月16日下午3:06:24
 */
@Controller("managerController")
public class ManagerController {

    /**
     * 日志记录 *
     */
    private static final MyLogger LOGGER = new MyLogger(ManagerController.class);

    private static final String MANAGER = "manager";
    private static final String LOGINUSERID = "loginUserId";
    private static final String INITMANAGER_HTM = "initManager.htm";
    private static final String CHECKTYPE = "checkType";
    private static final String CHECKSUCCESS = "checkSuccess";

    private MenuServiceInterface menuServiceInterface;

    private ManagerServiceInterface managerService;

    private ManagerSmsService managerSmsService;

    private static final int HTHOUSAND = 100000;

    private static final String NAME = "name";

    @Resource(name = "IndexCountBeanService")
    private IndexCountBeanService indexCountBeanService;

    @Resource(name = "authorityMapper")
    private AuthorityMapper authorityMapper;

    /**
     * 管理员登陆
     *
     * @return 获取权限跳转地址
     */
    @RequestMapping("/iflogin")
    @ResponseBody
    public int ifLogin(HttpServletRequest request, String name, String password, String code, String patchaFlag) {
        if ("1".equals(patchaFlag) && null == code || !code.equals(request.getSession().getAttribute("PATCHCA"))) {
            // 验证码匹配
                return -1;
        }

        // 非空验证 用户名
        if (null != name) {
            LOGGER.info("管理员【" + name + "】登陆");
        }
        return managerService.ifManager(request, name, password);
    }

    /**
     * 跳转到下载谷歌提示页面
     *
     * @return toChrome_help
     */
    @RequestMapping("/tochromehelp")
    public ModelAndView toChromeHelp() {
        // 跳转提示
        return new ModelAndView("jsp/chrome_help");
    }

    /**
     * 跳转页面到首页
     *
     * @param name
     *            用户名
     * @param password
     * @return
     */
    @RequestMapping("/login11")
    public ModelAndView managerLogin(String name, String password) {
        // 跳转首页
        LOGGER.info("跳转到首页");
        return new ModelAndView();
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping("index")
    public ModelAndView loadIndex(HttpServletRequest request) {
        LOGGER.info("跳转到首页");
        ModelAndView mav = new ModelAndView("jsp/newindex");
        // 查询管理员信息
        Manager manager = managerService.queryManagerByName((String) request.getSession().getAttribute(NAME));
        mav.addObject(MANAGER, manager);
        // 查询菜单权限
        request.getSession().setAttribute("menus", managerService.queryMenuByMangerName(manager.getUsername()));
        request.getSession().setAttribute("menuId", 1);
        // 查询角色信息
        Authority at = authorityMapper.selectAuthByManagerId(manager.getId());
        return mav;
    }

    /**
     * 跳转首页
     *
     * @return
     */
    public ModelAndView loadIndex_back(HttpServletRequest request) {
        LOGGER.info("跳转到首页");
//        ModelAndView mav = new ModelAndView("jsp/index");
        ModelAndView mav = new ModelAndView("jsp/newindex");
        // 查询管理员信息
        Manager manager = managerService.queryManagerByName((String) request.getSession().getAttribute(NAME));
        mav.addObject(MANAGER, manager);
        // 查询菜单权限
        request.getSession().setAttribute("menus", managerService.queryMenuByMangerName(manager.getUsername()));
        request.getSession().setAttribute("menuId", 1);
        // 查询角色信息
        Authority at = authorityMapper.selectAuthByManagerId(manager.getId());
        // 查询首页统计
        mav.addObject("indexBean", indexCountBeanService.selectIndexCount());
        // 查询eChart数据
        Map<String, Object> eMap = indexCountBeanService.selectIndexEchartsData();

        request.getSession().setAttribute("designation", at.getDesignation());
        // 时间轴
        mav.addObject("data", JSONArray.fromObject(eMap.get("xtime")).toString());
        // 数据
        mav.addObject("series", JSONArray.fromObject(eMap.get("series")));

        mav.addObject("legend", JSONArray.fromObject(eMap.get("legend")).toString());

        return mav;
    }

    /**
     * 管理员登陆
     *
     * @return 加载权限菜单
     */
    @RequestMapping("/loadMenus")
    @ResponseBody
    public List<MenuVo> loadMenus(String name) {
        // 非空验证 用户名
        if (null != name) {
            LOGGER.info("管理员【" + name + "】登陆");
        }
        // 加载权限菜单
        return managerService.queryMenuByMangerName(name);
    }

    /**
     * 退出登录
     *
     * @return ModelAndView
     */
    @RequestMapping("/login")
    public ModelAndView logOut(HttpServletRequest request) {
        // 退出登陆
        request.getSession().removeAttribute(NAME);
        return new ModelAndView("jsp/begin");
    }

    /**
     * 加载管理员列表
     *
     * @return 管理员列表
     */
    @RequestMapping("/initManager")
    public ModelAndView initManager(PageBean pageBean, HttpServletRequest request, Manager manager) {
        // 获取管理员标识
        Integer flag = (Integer) request.getSession().getAttribute("managerFlag");
        Long createId = null;
        if (flag != null && flag != 1) {
            // 获取当前登录ID
            createId = (Long) request.getSession().getAttribute(LOGINUSERID);
        }
        pageBean.setUrl(INITMANAGER_HTM);
        // 查询管理员列表
        return new ModelAndView("jsp/core/manager/managerlist").addObject("pageBean", managerService.queryManagerList(manager, pageBean, createId));
    }

    /**
     * 查询所有的管理员信息 ,并以AJAX返回 FOR GOODS--WareHouse
     * <p/>
     * {@link com.ningpai.util.PageBean}
     *
     * @return
     */
    @RequestMapping("/queryAllManagerForWareHouse")
    @ResponseBody
    public List<Object> queryAllManagerForWareHouse(PageBean pb) {
        pb.setPageSize(HTHOUSAND);
        // 查询所有的管理员信息
        return this.managerService.queryManagerList(new Manager(), pb, null).getList();
    }

    /**
     * 跳转页面
     *
     * @return 设置中心
     */
    @RequestMapping("/initSetting")
    public ModelAndView initSetting() {
        return new ModelAndView("jsp/core/manager/settingcenter");
    }

    /**
     * 添加管理员
     *
     * @param manager
     *            管理员信息
     * @param authorityId
     *            权限编号
     * @return
     */
    @RequestMapping("/addManager")
    public ModelAndView addManager(HttpServletRequest request, Manager manager, String authorityId, HttpServletResponse response, MultipartHttpServletRequest multipartRequest) {
        // 非空验证 管理员名称
        if (null != manager.getUsername()) {
            LOGGER.info("新增名称为【" + manager.getUsername() + "】管理员");
        }
        // 设置管理员头像 setImg(manager, multipartRequest);
        Long createId = (Long) request.getSession().getAttribute(LOGINUSERID);
        manager.setCreateId(createId);
        // 插入数据库
        managerService.addManager(manager, authorityId);
        // 插入日志
        OperaLogUtil.addOperaLog(multipartRequest, (String) multipartRequest.getSession().getAttribute(NAME), "添加管理员", "添加管理员,管理员名:" + manager.getUsername());
        return new ModelAndView(new RedirectView(INITMANAGER_HTM));
    }

    /**
     * 设置头像
     *
     * @param manager
     * @param multipartRequest
     */
    public void setImg(Manager manager, MultipartHttpServletRequest multipartRequest) {
        // 判断图片
        if (multipartRequest.getFile(Constants.FILE) != null && !"".equals(multipartRequest.getFile(Constants.FILE).getOriginalFilename())) {
            // 使用上传后路径
            manager.setPhotoImg(UploadUtil.uploadFile(multipartRequest.getFile(Constants.FILE), multipartRequest).get("oldimg"));
        }
    }

    /**
     * 添加管理员(配置私有云演示账号)
     *
     * @param manager
     *            管理员信息
     * @param applyMobilephone
     *            管理员用户名
     * @return
     */
    @RequestMapping("/configManager")
    @ResponseBody
    public Map<String, Object> configManager(String applyMobilephone, Manager manager) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 非空验证 管理员名称
        if (null != manager.getUsername()) {
            LOGGER.info("新增名称为【" + manager.getUsername() + "】管理员");
        }
        // 配置演示账号（插入一条管理员账号）
        /* 设置用户名为手机号码 */
        manager.setUsername(applyMobilephone);
        /* 生成六位数字的随机密码 */
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        manager.setUserkey(((Integer) num).toString());
        // 设置创建人id
        manager.setCreateId(-1L);
        /* 设置authorityId */
        String authorityId = new String("1");
        managerService.addManager(manager, authorityId);
        // 获取刚插入的managerId，并查询出此条记录的详细信息
        Manager configManager = managerService.queryManagerById(manager.getId());
        map.put("configManager", configManager);
        map.put(MANAGER, manager);
        return map;
    }

    /**
     * 查询管理员信息
     *
     * @param request
     *            管理员编号
     * @return
     */
    @RequestMapping("/queryManagerById")
    @ResponseBody
    public Manager queryManagerById(HttpServletRequest request, Long id) {
        if (id != null) {
            // id=(Long) request.getSession().getAttribute(LOGINUSERID);
            Manager manager = managerService.queryManagerById(id);
            manager.setUserkey(null);
            // 非空验证 管理员名称
            if (null != manager.getUsername()) {
                // 日志记录
                LOGGER.info("获取管理员【" + manager.getUsername() + "】的信息");
                return manager;
            }
        }
        return null;

    }

    /**
     * 根据条件查询管理员
     *
     * @param manager
     *            查询条件
     * @param pageBean
     *            查询结果
     * @param attr
     *            查询项
     * @return
     */
    @RequestMapping("/queryByManager")
    public ModelAndView queryByManager(Manager manager, PageBean pageBean, String[] attr) throws UnsupportedEncodingException {
        // 设置页面跳转路径
        pageBean.setUrl("queryByManager.htm");
        // 查询管理员
        return new ModelAndView("jsp/core/manager/managerlist", "pageBean", managerService.queryManagerList(manager, pageBean, null)).addObject(MANAGER, manager).addObject("attr",
                attr);
    }

    /**
     * 删除管理员
     * <p/>
     * 管理员编号
     *
     * @return
     */
    @RequestMapping("/deleteManager")
    public ModelAndView deleteManager(HttpServletRequest request, HttpServletResponse response, Long managerId) {

        PrintWriter pr = null;
        String[] customerIds = null;
        StringBuilder str = new StringBuilder("[");
        String empStr = "";
        try {
            pr = response.getWriter();
            if (managerId != null) {
                customerIds = new String[1];
                customerIds[0] = managerId + "";
                for (String id : customerIds) {
                    str.append(id + ",");
                }
                empStr = str.toString().substring(0, str.length() - 1) + "]";
            } else {
                customerIds = request.getParameterValues("parameterIds[]");
                for (String id : customerIds) {
                    str.append(id + ",");
                }
                empStr = str.toString().substring(0, str.length() - 1) + "]";
            }

            // 删除会员信息 输出信息 0 失败 1成功
            pr.print(managerService.deleteManager(customerIds));

        } catch (IOException e) {
            // 插入日志
            OperaLogUtil.addOperaException((String) request.getSession().getAttribute(NAME), e, request);
        } finally {
            // 插入日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(NAME), "删除管理员", "删除管理员,管理员编号:" + empStr);
            customerIds = null;
            str = null;
            pr = null;
            empStr = null;
        }

        return null;
    }

    /**
     * 删除管理员
     * <p/>
     * 管理员编号
     *
     * @return
     */
    @RequestMapping("/deleteallmanager")
    public ModelAndView deleteAllManager(HttpServletRequest request, HttpServletResponse response) {
        // 删除管理员
        managerService.deleteManager(request.getParameterValues("managerid"));
        return new ModelAndView(new RedirectView(INITMANAGER_HTM));
    }

    /**
     * 停用管理员(私有云停用账号)
     *
     * @param loginName
     *            管理员编号
     * @return
     */
    @RequestMapping("/delCloudManager")
    @ResponseBody
    public Map<String, Object> delCloudManager(String loginName) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询管理员
        Manager manager = managerService.queryDelManagerByName(loginName);
        if (manager != null) {
            String[] managerIds = new String[1];
            managerIds[0] = Long.toString(manager.getId());
            int line = managerService.deleteManager(managerIds);
            map.put("line", line);
        }
        return map;
    }

    /**
     * 启用管理员(私有云启用账号)
     *
     * @param loginName
     *            管理员编号
     * @return
     */
    @RequestMapping("/enableCloudManager")
    @ResponseBody
    public Map<String, Object> enableCloudManager(String loginName) {
        Map<String, Object> map = new HashMap<String, Object>();
        Manager manager = managerService.queryDelManagerByName(loginName);
        if (manager != null) {
            String[] managerIds = new String[1];
            managerIds[0] = Long.toString(manager.getId());
            int line = managerService.enabledManager(managerIds);
            // 查询更新后的manager
            Manager newManager = managerService.queryManagerById(manager.getId());
            map.put("createTime", newManager.getCreateTime());
            map.put("line", line);
        }
        return map;
    }

    /**
     * 修改管理员信息
     *
     * @param manager
     * @return
     */
    @RequestMapping("/updateManager")
    public ModelAndView updateManager(Manager manager, ManagerAuthority authority, MultipartHttpServletRequest multipartRequest) {
        // 非空验证 管理员名称
        if (null != manager.getUsername()) {
            // 日志记录
            LOGGER.info("修改管理员【" + manager.getUsername() + "】的信息");
        }
        managerService.updateManager(manager, authority);
        OperaLogUtil.addOperaLog(multipartRequest, (String) multipartRequest.getSession().getAttribute(NAME), "修改管理员", "修改管理员" + ",编号:" + manager.getId());
        return new ModelAndView(new RedirectView(INITMANAGER_HTM));
    }

    /**
     * 验证密码
     *
     * @param userKey
     * @return ModelAndView
     */
    @RequestMapping("/checkUserKey")
    public ModelAndView checkUserKey(HttpServletRequest request, HttpServletResponse response, String userKey) {
        // 非空验证 密码
        if (null != userKey) {
            // 日志记录
            LOGGER.info("验证密码【" + userKey + "】是否正确！");
        }
        try {
            // 输出检查结果
            response.getWriter().print(managerService.checkUserKey(request, userKey));
        } catch (IOException e) {
            OperaLogUtil.addOperaException((String) request.getSession().getAttribute(NAME), e, request);
        }

        return null;
    }

    /**
     * 验证密码
     *
     * @param userKey
     * @return
     */
    @RequestMapping("/checkUserKeyMain")
    public ModelAndView checkUserKeyMain(HttpServletRequest request, HttpServletResponse response, String userKey) {
        // 非空验证 密码
        if (null != userKey) {
            // 日志记录
            LOGGER.info("验证密码【" + userKey + "】是否正确！");
        }
        try {
            // 输出检查结果
            int f = managerService.checkUserKey(request, userKey);
            if (f == 1) {
                // 设置密码验证方式为 1 密码验证
                request.getSession().setAttribute(CHECKTYPE, "1");
                request.getSession().setAttribute(CHECKSUCCESS, "1");
            } else {
                request.getSession().setAttribute(CHECKTYPE, "");
                request.getSession().setAttribute(CHECKSUCCESS, "");
            }
            response.getWriter().print(f);
        } catch (IOException e) {
            OperaLogUtil.addOperaException((String) request.getSession().getAttribute(NAME), e, request);
        }

        return null;
    }

    /**
     * 修改密码
     *
     * @param request
     * @param response
     * @param userKey
     * @param newuserkey
     * @return
     */
    @RequestMapping("/modifiedUserKey")
    public ModelAndView modifiedUserKey(HttpServletRequest request, HttpServletResponse response, String userKey, String newuserkey) {
        // 非空验证 密码
        if (null != userKey) {
            // 日志记录
            LOGGER.info("修改密码，旧密码为【" + userKey + "】");
        }
        try {
            // 通过什么方式进行修改信息 checkType： 1.登录密码 2.手机验证码
            if (request.getSession().getAttribute(CHECKTYPE) != null && "1".equals(request.getSession().getAttribute(CHECKTYPE))) {
                // 检查登录密码是否正确,若正确才能对密码进行修改 checkSuccess：1.密码正确
                if (request.getSession().getAttribute(CHECKSUCCESS) != null && "1".equals(request.getSession().getAttribute(CHECKSUCCESS))) {
                    response.getWriter().print(managerService.modifiedUserKey(request, userKey, newuserkey));
                }
            } else if (request.getSession().getAttribute(CHECKTYPE) != null && "2".equals(request.getSession().getAttribute(CHECKTYPE))) {
                // 检查手机验证码是否正确,若正确才能对密码进行修改 checkSuccess：1.验证码正确
                if (request.getSession().getAttribute(CHECKSUCCESS) != null && "1".equals(request.getSession().getAttribute(CHECKSUCCESS))) {
                    response.getWriter().print(managerService.modifiedUserKey(request, userKey, newuserkey));
                }
            } else {
                response.getWriter().print(0);
            }

            request.getSession().setAttribute(CHECKTYPE, "");
            request.getSession().setAttribute(CHECKSUCCESS, "");
        } catch (IOException e) {
            OperaLogUtil.addOperaException((String) request.getSession().getAttribute(NAME), e, request);
        }

        return null;
    }

    /**
     * 修改管理员信息 -- 首页
     *
     * @param manager
     * @return
     */
    @RequestMapping("/modifymanager")
    public ModelAndView updateManager(Manager manager, HttpServletResponse response, MultipartHttpServletRequest multipartRequest) {
        // 非空验证 管理员名称
        if (null != manager.getUsername()) {
            // 日志记录
            LOGGER.info("修改管理员【" + manager.getUsername() + "】的信息");
        }
        // 通过什么方式进行修改信息 checkType： 1.登录密码 2.手机验证码
        if (multipartRequest.getSession().getAttribute(CHECKTYPE) != null && "1".equals(multipartRequest.getSession().getAttribute(CHECKTYPE)) && multipartRequest.getSession().getAttribute(CHECKSUCCESS) != null && "1".equals(multipartRequest.getSession().getAttribute(CHECKSUCCESS))) {
            // 检查登录密码是否正确,若正确才能对资料进行修改 checkSuccess：1.密码正确
                managerService.updateManagerOnly(manager);
                Manager m = managerService.queryManagerById(manager.getId());
                multipartRequest.getSession().setAttribute("photoImg", m.getPhotoImg());
        } else if (multipartRequest.getSession().getAttribute(CHECKTYPE) != null && "2".equals(multipartRequest.getSession().getAttribute(CHECKTYPE)) && multipartRequest.getSession().getAttribute(CHECKSUCCESS) != null && "1".equals(multipartRequest.getSession().getAttribute(CHECKSUCCESS))) {
            // 检查手机验证码是否正确,若正确才能对资料进行修改 checkSuccess：1.验证码正确
                managerService.updateManagerOnly(manager);
                Manager m = managerService.queryManagerById(manager.getId());
                multipartRequest.getSession().setAttribute("photoImg", m.getPhotoImg());
        }
        multipartRequest.getSession().setAttribute(CHECKTYPE, "");
        multipartRequest.getSession().setAttribute(CHECKSUCCESS, "");
        return new ModelAndView(new RedirectView("index.htm"));
    }

    /**
     * 发送验证码
     *
     * @param request
     *            手机号码
     * @return
     */
    @RequestMapping("sendcodecore")
    @ResponseBody
    public int sendMobileCode(HttpServletRequest request, String mobile) {
        // 非空验证 手机号码
        if (null != mobile) {
            // 日志记录
            LOGGER.info("发送验证码到号码为【" + mobile + "】的手机上");
        }
        return managerSmsService.sendPost(request, mobile);
    }

    /**
     * 检查手机验证码
     *
     * @return 0不同 1相同
     * @throws IOException
     */
    @RequestMapping("getcodecore")
    @ResponseBody
    public int getMCode(HttpServletRequest request, String code) throws IOException {
        // 非空验证 手机号码
        if (null != code) {
            // 日志记录
            LOGGER.info("检查手机验证码【" + code + "】是否正确");
        }
        int f = managerSmsService.getMCode(request, code);
        if (f == 1) {
            request.getSession().setAttribute(CHECKTYPE, "2");
            request.getSession().setAttribute(CHECKSUCCESS, "1");
        } else {
            request.getSession().setAttribute(CHECKTYPE, "");
            request.getSession().setAttribute(CHECKSUCCESS, "");
        }
        return f;
    }

    /**
     * 检查手机验证码
     *
     * @return 0不同 1相同
     * @throws IOException
     */
    @RequestMapping("getmobile")
    @ResponseBody
    public Manager getMobile(HttpServletRequest request) {
        return managerSmsService.queryManagerById((Long) request.getSession().getAttribute(LOGINUSERID));
    }

    /**
     * 验证管理员是否已经存在
     * <p/>
     * 管理员名
     *
     * @return 0 不存在 1存在
     */
    @RequestMapping("/checkmanagerexist")
    @ResponseBody
    public Long checkManagerExist(String usernamen) {
        // 非空验证 管理员名称
        if (null != usernamen) {
            // 日志记录
            LOGGER.info("验证管理员【" + usernamen + "】是否存在");
        }
        return managerService.checkManagerExist(usernamen);
    }

    public ManagerServiceInterface getManagerService() {
        return managerService;
    }

    @Resource(name = "managerService")
    public void setManagerService(ManagerServiceInterface managerService) {
        this.managerService = managerService;
    }

    public MenuServiceInterface getMenuServiceInterface() {
        return menuServiceInterface;
    }

    @Resource(name = "menuServiceInterface")
    public void setMenuServiceInterface(MenuServiceInterface menuServiceInterface) {
        this.menuServiceInterface = menuServiceInterface;
    }

    public ManagerSmsService getManagerSmsService() {
        return managerSmsService;
    }

    @Resource(name = "managerSmsService")
    public void setManagerSmsService(ManagerSmsService managerSmsService) {
        this.managerSmsService = managerSmsService;
    }

}
