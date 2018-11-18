package com.ningpai.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.AuthCheckConf;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IAuthCheckConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 验证设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 09:58:51
 * @version V1.0
 */
@Controller("authCheckConfController")
public class AuthCheckConfController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            AuthCheckConfController.class);
    /** 验证设置 */
    private AuthCheckConf authCheckConf;
    /** 验证设置业务类 */
    private IAuthCheckConfBiz authCheckConfBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 验证设置列表初始化URL */
    private static final String INIT_AUTHCHECKCONF = "initAuthCheckConf.htm";
    /** 添加验证设置JSP页面 */
    private static final String ADD_AUTHCHECKCONF_JSP = "jsp/system/check/authcheckconf_add";
    /** 验证设置列表JSP页面 */
    private static final String AUTHCHECKCONF_LIST_JSP = "jsp/system/check/authcheckconf_list";
    /** 更新验证设置JSP页面 */
    private static final String UPDATE_AUTHCHECKCONF_JSP = "jsp/system/check/authcheckconf_update";
    /** 查询验证设置JSP页面 */
    private static final String READ_AUTHCHECKCONF_JSP = "jsp/system/check/authcheckconf_read";
    /** 删除状态常量 */
    private static final String DELETESTATUS = "deleteStatus";
    /** 消息常量 */
    private static final String MSG = "msg";
    /** 验证设置常量 */
    private static final String AUTHCHECKCONF = "authCheckConf";
    /** IDS常量 */
    private static final String IDS = "ids";
    /** 标题常量 */
    private static final String TITLE = "title";
    /** 设置code常量 */
    private static final String SETCODE = "setCode";

    /**
     * 初始化验证设置列表
     * 
     * @param pageBean
     *            分页对象
     * @param deleteStatus
     *            对象删除标识
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/initAuthCheckConf")
    public ModelAndView initAuthCheckConf(
            PageBean pageBean,
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            HttpServletRequest request) {
        // 设置url
        pageBean.setUrl(INIT_AUTHCHECKCONF);
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置试图名称
        mav.setViewName(AUTHCHECKCONF_LIST_JSP);
        // 创建map pa
        Map<String, Object> pa = new HashMap<String, Object>(1);
        // 将删除状态放入pa中
        pa.put(DELETESTATUS, deleteStatus);
        // 添加object
        mav.addObject("pageBean",
                authCheckConfBizImpl.getAuthCheckConfByField(pa, pageBean));
        mav.addObject(DELETESTATUS, deleteStatus);
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        // 获得删除对象的数量
        mav.addObject("deleteObjectSize", getDeleteObjectSize());
        return mav;
    }

    /**
     * 获得删除对象的数量
     * 
     * @return 删除对象的数量
     */
    private int getDeleteObjectSize() {
        // 创建map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        // 将删除状态放入map中
        pa.put(DELETESTATUS, 1);
        try {
            // 返回删除对象的数量
            return authCheckConfBizImpl.getAuthCheckConfByFieldTotal(pa);
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开验证设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddAuthCheckConfPage")
    public final ModelAndView openAddAuthCheckConfPage(
            final HttpServletRequest request) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 创建视图名称
        mav.setViewName(ADD_AUTHCHECKCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存验证设置
     * 
     * @param authCheckConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addAuthCheckConf")
    public ModelAndView addAuthCheckConf(AuthCheckConf authCheckConf,
            HttpServletRequest request) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        try {
            // 设置创建日期
            authCheckConf.setInsertDate(new Date());
            // 设置创建人
            authCheckConf.setInsertId(((Long) request.getSession()
                    .getAttribute("loginUserId")).intValue());
            // 设置删除状态
            authCheckConf.setDeleteStatus(0);
            // 添加验证设置
            boolean flag = authCheckConfBizImpl
                    .saveAuthCheckConf(authCheckConf);
            // 判断是否添加成功
            if (flag) {
                // 添加成功
                mav.addObject(MSG, "保存验证设置成功！");
            } else {
                // 添加失败
                mav.addObject(MSG, "保存验证设置失败！");
                // 返回添加页面
                return backAddPage(authCheckConf, mav);
            }
            // 设置视图
            mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("保存验证设置对象异常！", e);
            // 添加msg
            mav.addObject(MSG, "保存验证设置失败！");
            // 返回添加页面
            return backAddPage(authCheckConf, mav);
        }
        // 返回验证设置列表
        return mav;
    }

    /**
     * 返还到添加页面
     * 
     * @param currencyConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(AuthCheckConf authCheckConf,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        // 添加authCheckConf
        mav.addObject(AUTHCHECKCONF, authCheckConf);
        // 设置视图名称
        mav.setViewName(ADD_AUTHCHECKCONF_JSP);
        return mav;
    }

    /**
     * 打开验证设置修改页面
     * 
     * @param id
     *            验证设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateAuthCheckConfPage")
    public ModelAndView openUpdateAuthCheckConfPage(@RequestParam("id") int id) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改验证设置
            mav.addObject(AUTHCHECKCONF,
                    authCheckConfBizImpl.getAuthCheckConfById(id));
            // 设置视图名称
            mav.setViewName(UPDATE_AUTHCHECKCONF_JSP);
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("加载验证设置对象失败！", e);
            // 添加msg
            mav.addObject(MSG, "加载验证设置对象失败！");
            // 返回验证设置列表
            mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改验证设置
     */
    /**
     * 修改验证设置
     * 
     * @param authCheckConf
     *            验证设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateAuthCheckConf")
    public ModelAndView updateAuthCheckConf(AuthCheckConf authCheckConf,
            HttpServletRequest request) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        try {
            // 设置修改时间
            authCheckConf.setModifyDate(new Date());
            // 设置修改人
            authCheckConf.setModifyId(((Long) request.getSession()
                    .getAttribute("loginUserId")).intValue());
            // 修改验证设置
            if (authCheckConfBizImpl.updateAuthCheckConf(authCheckConf) >= 1) {
                // 修改成功
                mav.addObject(MSG, "修改验证设置成功！");
            } else {
                // 修改失败
                mav.addObject(MSG, "修改验证设置失败！");
                return backUpdatePage(authCheckConf, mav);
            }
            // 设置视图
            mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("修改验证设置对象异常！", e);
            // 添加msg
            mav.addObject(MSG, "修改验证设置失败！");
            // 返回修改页面
            return backUpdatePage(authCheckConf, mav);
        }
        // 返回验证设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param authCheckConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(AuthCheckConf authCheckConf,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        // 添加authCheckConf
        mav.addObject(AUTHCHECKCONF, authCheckConf);
        // 创建视图名称
        mav.setViewName(UPDATE_AUTHCHECKCONF_JSP);
        return mav;
    }

    /**
     * 查看验证设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            验证设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readAuthCheckConf")
    public ModelAndView readAuthCheckConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam("id") int id) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 添加删除状态
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询验证设置id是否为0
        if (id == 0) {
            // 添加msg
            mav.addObject(MSG, "验证设置对象id为空，无法执行查询！");
            // 设置视图
            mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
        } else {
            // 设置视图名称
            mav.setViewName(READ_AUTHCHECKCONF_JSP);
            // 查询验证设置
            mav.addObject(AUTHCHECKCONF,
                    authCheckConfBizImpl.getAuthCheckConfById(id));
        }
        return mav;
    }

    /**
     * 删除验证设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            验证设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteAuthCheckConf")
    public ModelAndView deleteAuthCheckConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = IDS) String ids) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置视图
        mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            // 添加msg
            mav.addObject(MSG, "对象id为空，无法执行删除验证设置操作！");
            return mav;
        } else {

            // 删除验证设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    // 判断是否删除成功
                    if (authCheckConfBizImpl.deleteAuthCheckConf(ids) >= 1) {
                        // 删除成功
                        mav.addObject(MSG, "删除验证设置成功！");
                    } else {
                        // 删除失败
                        mav.addObject(MSG, "删除验证设置失败！");
                    }
                    // 逻辑删除
                } else {
                    // 创建map
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    // 将删除状态放入map中
                    pa.put(DELETESTATUS, 1);
                    // 将IDS放入map中
                    pa.put(IDS, ids);
                    // 判断是否将删除验证设置到回收站
                    if (authCheckConfBizImpl.updateAuthCheckConfFieldById(pa) >= 1) {
                        // 删除验证设置到回收站成功
                        mav.addObject(MSG, "删除验证设置到回收站成功！");
                    } else {
                        // 删除验证设置到回收站失败
                        mav.addObject(MSG, "删除验证设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                // 日志输出
                LOGGER.error("删除验证设置对象异常！", e);
                mav.addObject(MSG, "删除验证设置失败！");
            }
        }
        // 删除成功返回验证设置列表
        return mav;
    }

    /**
     * 还原删除验证设置
     * 
     * @param ids
     *            验证设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreAuthCheckConf")
    public ModelAndView restoreAuthCheckConf(
            @RequestParam(value = IDS) String ids) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置视图
        mav.setView(new RedirectView(INIT_AUTHCHECKCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            // 查询对象id集合字符无效，添加msg
            mav.addObject(MSG, "对象id为空，无法执行还原删除验证设置操作！");
            return mav;
        } else {
            try {
                // 创建map
                Map<String, Object> pa = new HashMap<String, Object>(2);
                // 将删除状态放入map中
                pa.put(DELETESTATUS, 0);
                // 将IDS放入到map中
                pa.put(IDS, ids);
                // 判断还原删除验证设置是否成功
                if (authCheckConfBizImpl.updateAuthCheckConfFieldById(pa) >= 1) {
                    // 还原删除验证设置成功
                    mav.addObject(MSG, "还原删除验证设置成功！");
                } else {
                    // 还原删除验证设置失败
                    mav.addObject(MSG, "还原删除验证设置失败！");
                }
            } catch (Exception e) {
                // 日志输出
                LOGGER.error("还原删除验证设置对象异常！", e);
                mav.addObject(MSG, "还原删除验证设置失败！");
            }
        }
        // 删除成功返回验证设置列表
        return mav;
    }

    /**
     * 查询验证设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param title
     *            设置方案标题
     * @param setCode
     *            设置方案代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryAuthCheckConf")
    public ModelAndView queryAuthCheckConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = TITLE, required = false, defaultValue = "") String title,
            @RequestParam(value = SETCODE, required = false, defaultValue = "") String setCode,
            PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        // 判断title是否为空，不为空放入到查询条件
        if (title != null && title.trim().length() != 0) {
            param.put(TITLE, title);
            queryParam.put(TITLE, title);
            queryParam.put("searchField", TITLE);
            queryParam.put("searchValue", title);
            queryParam.put("searchName", "方案标题");
        }
        // 判断setCode是否为空
        if (setCode != null && setCode.trim().length() != 0) {
            param.put(SETCODE, setCode);
            queryParam.put(SETCODE, setCode);
            queryParam.put("searchField", SETCODE);
            queryParam.put("searchValue", setCode);
            queryParam.put("searchName", "方案代码");
        }
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 创建视图名称
        mav.setViewName(AUTHCHECKCONF_LIST_JSP);
        // 添加查询条件
        mav.addObject("queryParam", queryParam);
        // 删除状态
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询验证设置
            mav.addObject("pageBean", authCheckConfBizImpl
                    .getAuthCheckConfByField(param, pageBean));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("查询验证设置信息异常！",e);
        }
        return mav;
    }

    /**
     * 填充字典信息
     * 
     * @param mav
     *            视图对象
     */
    private void fullDictionarys(ModelAndView mav) {
        // 循环需要的字典
        for (String dep : DICTIONARYS_LIST) {
            try {
                // 添加字典
                mav.addObject(dep, dictionarysCache.getDictionaryByName(dep));
            } catch (Exception e) {
                // 日志输出
                LOGGER.error("获得字典缓存异常，字典名：" + dep, e);
            }
        }
    }

    /**
     * 获得验证设置
     * 
     * @return 验证设置对象
     */
    public final AuthCheckConf getAuthCheckConf() {
        return authCheckConf;
    }

    /**
     * 自动注入赋值验证设置对象
     * 
     * @param authCheckConf
     *            验证设置对象
     */
    @Resource(name = AUTHCHECKCONF)
    public void setAuthCheckConf(AuthCheckConf authCheckConf) {
        this.authCheckConf = authCheckConf;
    }

    /**
     * 获得验证设置业务对象
     * 
     * @return 验证设置对象业务对象
     */
    public IAuthCheckConfBiz getAuthCheckConfBizImpl() {
        return authCheckConfBizImpl;
    }

    /**
     * 自动注入赋值验证设置对象
     * 
     * @param currencyConfBizImpl
     *            验证设置对象
     */
    @Resource(name = "authCheckConfBizImpl")
    public void setAuthCheckConfBizImpl(IAuthCheckConfBiz authCheckConfBizImpl) {
        this.authCheckConfBizImpl = authCheckConfBizImpl;
    }

    /**
     * 获得字典缓存业务对象
     * 
     * @return 字典缓存业务对象
     */
    public IDictionarysCache getDictionarysCache() {
        return dictionarysCache;
    }

    /**
     * 自动注入赋值字典缓存业务对象
     * 
     * @param dictionarysCache
     *            字典缓存业务对象
     */
    @Resource(name = "dictionarysCache")
    public void setDictionarysCache(IDictionarysCache dictionarysCache) {
        this.dictionarysCache = dictionarysCache;
    }

}
