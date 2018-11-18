package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.ExpressConf;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IExpressConfBiz;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配送方式设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-26 16:38:32
 * @version V1.0
 */
@Controller("expressConfController")
public class ExpressConfController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ExpressConfController.class);
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 配送方式设置列表初始化URL */
    private static final String INIT_EXPRESSCONF = "initExpressConf.htm";
    /** 添加配送方式设置JSP页面 */
    private static final String ADD_EXPRESSCONF_JSP = "jsp/system/express/expressconf_add";
    /** 配送方式设置列表JSP页面 */
    private static final String EXPRESSCONF_LIST_JSP = "jsp/system/express/expressconf_list";
    /** 更新配送方式设置JSP页面 */
    private static final String UPDATE_EXPRESSCONF_JSP = "jsp/system/express/expressconf_update";
    /** 查询配送方式设置JSP页面 */
    private static final String READ_EXPRESSCONF_JSP = "jsp/system/express/expressconf_read";
    /** 常量DELETESTATUS */
    private static final String DELETESTATUS = "deleteStatus";
    /** 常量MSG */
    private static final String MSG = "msg";
    /** 常量EXPRESSCONF */
    private static final String EXPRESSCONF = "expressConf";
    /** 常量IDS */
    private static final String IDS = "ids";
    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final String INITEXPRESSCONF_HTM = "initExpressConf.htm";
    private static final String LOGGERINFO1 = ",用户名:";

    /** 配送方式设置业务类 */
    @Resource(name = "expressConfBizImpl")
    private IExpressConfBiz expressConfBizImpl;
    /** 字典缓存 */
    @Resource(name = "dictionarysCache")
    private IDictionarysCache dictionarysCache;
    /** 物流公司设置业务类 */
    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz logisticsCompanyBizImpl;

    /**
     * 初始化配送方式设置列表
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
    @RequestMapping("/initExpressConf")
    public ModelAndView initExpressConf(PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") Integer deleteStatus, HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 设置URL
        pageBean.setUrl(INIT_EXPRESSCONF);
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置视图名称
        mav.setViewName(EXPRESSCONF_LIST_JSP);
        // 初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        // 获取配送方式
        mav.addObject("pageBean", expressConfBizImpl.getExpressConfByField(pa, pageBean));
        // 删除状态
        mav.addObject(DELETESTATUS, deleteStatus);
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        // 填充字典信息
        fullDictionarys(mav);
        // 获得删除对象的数量
        mav.addObject("deleteObjectSize", getDeleteObjectSize());
        return mav;
    }

    /**
     * 修改上门自提功能启用
     * 
     * @param usedStatus
     *            启用状态
     * @param expressId
     *            上门自提记录id
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/changeUserdStatus")
    public ModelAndView changeUserdStatus(String usedStatus, int expressId, HttpServletRequest request) {
        try {
            // 获取配送方式
            ExpressConf expressConf = expressConfBizImpl.getExpressConfById(expressId);
            // 设置启用状态
            expressConf.setUsedStatus(usedStatus);
            // 修改配送方式
            expressConfBizImpl.updateExpressConf(expressConf);
            // 获取登录名称
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 日志输出
            OperaLogUtil.addOperaLog(request, customerName, "修改自提点启用状态", request.getSession().getAttribute(OPERAPATH).toString());
        } catch (Exception e) {
            // 日志输出
            LOGGER.info("修改自提点启用状态：=>");
        }
        return new ModelAndView(new RedirectView(INITEXPRESSCONF_HTM));
    }

    /**
     * 获得删除对象的数量
     * 
     * @return 删除对象的数量
     */
    private int getDeleteObjectSize() {
        // 初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, 1);
        try {
            // 获取删除配送方式的数量
            return expressConfBizImpl.getExpressConfByFieldTotal(pa);
        } catch (Exception e) {
            // 日志输出
            LOGGER.info("获得删除对象的数量异常！");
        }
        return 0;
    }

    /**
     * 打开配送方式设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddExpressConfPage")
    public ModelAndView openAddExpressConfPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_EXPRESSCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存配送方式设置
     * 
     * @param expressConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addExpressConf")
    public ModelAndView addExpressConf(@Valid ExpressConf expressConf, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INITEXPRESSCONF_HTM));
        }
        ModelAndView mav = new ModelAndView();
        try {
            // 添加日期
            expressConf.setInsertDate(new Date());
            // 登录id
            Long loginUserId = getLoginUserId(request);
            // 添加人
            expressConf.setInsertId(loginUserId.intValue());
            // 删除状态
            expressConf.setDeleteStatus(0);
            // 添加配送方式设置
            boolean flag = expressConfBizImpl.saveExpressConf(expressConf);
            if (flag) {
                // 添加成功
                // 登录名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志输出
                OperaLogUtil.addOperaLog(request, customerName, "添加配送方式", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                mav.addObject(MSG, "保存配送方式设置成功！");
            } else {
                // 添加失败
                mav.addObject(MSG, "保存配送方式设置失败！");
                return backAddPage(expressConf, mav);
            }
            mav.setView(new RedirectView(INIT_EXPRESSCONF));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("添加配送方式错误：=>",e);
            mav.addObject(MSG, "保存配送方式设置失败！");
            return backAddPage(expressConf, mav);
        }
        // 返回配送方式设置列表
        return mav;
    }

    /**
     * 获取登录用户的ID
     * 
     * @param request
     * @return
     */
    private Long getLoginUserId(HttpServletRequest request) {
        // 获取用户id
        Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        return loginUserId;
    }

    /**
     * 返还到添加页面
     * 
     * @param expressConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(ExpressConf expressConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(EXPRESSCONF, expressConf);
        mav.setViewName(ADD_EXPRESSCONF_JSP);
        return mav;
    }

    /**
     * 打开配送方式设置修改页面
     * 
     * @param id
     *            配送方式设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateExpressConfPage")
    public ModelAndView openUpdateExpressConfPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改配送方式设置
            mav.addObject(EXPRESSCONF, expressConfBizImpl.getExpressConfById(id));
            mav.setViewName(UPDATE_EXPRESSCONF_JSP);
        } catch (Exception e) {
            LOGGER.info("加载配送方式设置对象失败！");
            mav.addObject(MSG, "加载配送方式设置对象失败！");
            // 返回配送方式设置列表
            mav.setView(new RedirectView(INIT_EXPRESSCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 根据主键查询配送方式
     * 
     * @param expressId
     * @return
     */
    @RequestMapping("selectExpressConfById")
    @ResponseBody
    public ExpressConf selectExpressConf(Integer expressId) {
        return expressConfBizImpl.getExpressConfById(expressId);
    }

    /**
     * 修改配送方式设置
     * 
     * @param expressConf
     *            配送方式设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateExpressConf")
    public ModelAndView updateExpressConf(@Valid ExpressConf expressConf, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INITEXPRESSCONF_HTM));
        }
        ModelAndView mav = new ModelAndView();
        try {
            // 修改日期
            expressConf.setModifyDate(new Date());
            // 获取登录人id
            Long loginUserId = getLoginUserId(request);
            // 修改人
            expressConf.setModifyId(loginUserId.intValue());
            // 修改配送方式设置
            if (expressConfBizImpl.updateExpressConf(expressConf) >= 1) {
                // 修改成功
                // 登录名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志输出
                OperaLogUtil.addOperaLog(request, customerName, "修改配送方式", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                mav.addObject(MSG, "修改配送方式设置成功！");
            } else {
                // 修改失败
                mav.addObject(MSG, "修改配送方式设置失败！");
                return backUpdatePage(expressConf, mav);
            }
            mav.setView(new RedirectView(INIT_EXPRESSCONF));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("修改配送方式错误：=>",e);
            mav.addObject(MSG, "修改配送方式设置失败！");
            return backUpdatePage(expressConf, mav);
        }
        // 返回配送方式设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param expressConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(ExpressConf expressConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(EXPRESSCONF, expressConf);
        mav.setViewName(UPDATE_EXPRESSCONF_JSP);
        return mav;
    }

    /**
     * 查看配送方式设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            配送方式设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readExpressConf")
    public ModelAndView readExpressConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询配送方式设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "配送方式设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_EXPRESSCONF));
        } else {
            mav.setViewName(READ_EXPRESSCONF_JSP);
            mav.addObject(EXPRESSCONF, expressConfBizImpl.getExpressConfById(id));
        }
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 删除配送方式设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            配送方式设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteExpressConf")
    public ModelAndView deleteExpressConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam(value = IDS) String ids,
            HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_EXPRESSCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除配送方式设置操作！");
            return mav;
        } else {

            // 删除配送方式设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (expressConfBizImpl.deleteExpressConf(ids) >= 1) {
                        // 删除成功
                        mav.addObject(MSG, "删除配送方式设置成功！");
                    } else {
                        // 删除失败
                        mav.addObject(MSG, "删除配送方式设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (expressConfBizImpl.updateExpressConfFieldById(pa) >= 1) {
                        // 删除配送方式设置到回收站成功
                        // 登录名称
                        String customerName = (String) request.getSession().getAttribute(NAME);
                        // 日志输出
                        OperaLogUtil.addOperaLog(request, customerName, "删除配送方式", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                        mav.addObject(MSG, "删除配送方式设置到回收站成功！");
                    } else {
                        // 删除配送方式设置到回收站失败
                        mav.addObject(MSG, "删除配送方式设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);
            } catch (Exception e) {
                // 日志输出
                LOGGER.error("删除配送方式错误：=>" ,e);
                mav.addObject(MSG, "删除配送方式设置失败！");
            }
        }
        // 删除成功返回配送方式设置列表
        return mav;
    }

    /**
     * 还原删除配送方式设置
     * 
     * @param ids
     *            配送方式设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreExpressConf")
    public ModelAndView restoreExpressConf(@RequestParam(value = IDS) String ids) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_EXPRESSCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除配送方式设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (expressConfBizImpl.updateExpressConfFieldById(pa) >= 1) {
                    // 删除配送方式设置到回收站成功
                    mav.addObject(MSG, "删除配送方式设置到回收站成功！");
                } else {
                    // 删除配送方式设置到回收站失败
                    mav.addObject(MSG, "删除配送方式设置到回收站失败！");
                }
            } catch (Exception e) {
                // 日志输出
                LOGGER.info("还原删除配送方式设置对象异常！");
                mav.addObject(MSG, "还原删除配送方式设置失败！");
            }
        }
        // 删除成功返回配送方式设置列表
        return mav;
    }

    /**
     * 查询配送方式设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param price
     *            运费
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryExpressConf")
    public ModelAndView queryExpressConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = "price", required = false, defaultValue = "0") Double price, String expressName, PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        ModelAndView mav = new ModelAndView();
        mav.setViewName(EXPRESSCONF_LIST_JSP);
        if (price != null && price > 0) {
            param.put("price", price);
            mav.addObject("price", price);
        }
        param.put("name", expressName);
        mav.addObject("expressName", expressName);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        fullDictionarys(mav);
        try {
            // 查询配送方式设置
            mav.addObject("pageBean", expressConfBizImpl.getExpressConfByField(param, pageBean));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("查询配送方式设置信息异常！",e);
        }
        return mav;
    }

    /**
     * 修改配送方式启用状态
     * 
     * @param expressId
     * @return
     */
    @RequestMapping("/changeUserdStatusForExpress")
    public ModelAndView changeUserdStatusForExpress(Long expressId) {
        try {
            if (this.expressConfBizImpl.changeUserdStatus(expressId)) {
                // 日志输出
                LOGGER.debug("===============配送方式启用状态修改成功");
            } else {
                // 日志输出
                LOGGER.debug("===============配送方式启用状态修改失败");
            }
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("修改配送方式启用状态错误：=>",e);
        }
        return new ModelAndView(new RedirectView(INIT_EXPRESSCONF));
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
                mav.addObject(dep, dictionarysCache.getDictionaryByName(dep));
            } catch (Exception e) {
                // 日志输出
                LOGGER.info("获得字典缓存异常，字典名：" + dep);
            }
        }
        // 获得配置的货运公司
        PageBean pg = new PageBean();
        pg.setPageSize(1000);
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put("usedStatus", 1);
        pa.put(DELETESTATUS, 0);
        try {
            mav.addObject("sysDicExpress", logisticsCompanyBizImpl.getLogisticsCompanyByField(pa, pg).getList());
        } catch (Exception e) {
            // 日志输出
            LOGGER.info("获得配置的货运公异常！");
        }
    }

    /**
     * 查询所有可用的物流公司
     * 
     * @return
     */
    @RequestMapping("/selectAllLogistics")
    @ResponseBody
    public List<LogisticsCompany> selectAllLogistics() {
        return logisticsCompanyBizImpl.queryAllLogisticsCompany();
    }

    /**
     * 删除多个配送方式
     * 
     * @param expressIds
     *            配送方式id
     * @return
     */
    @RequestMapping("/deleteBatchExpressConf")
    public ModelAndView deleteBatchExpressConf(Integer[] expressIds) {
        // 循环删除配送方式
        for (Integer expressId : expressIds) {
            expressConfBizImpl.deleteOneExpressConf(expressId);
        }
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_EXPRESSCONF));
        return mav;
    }

    /**
     * 删除单个配送方式
     * 
     * @param expressId
     *            配送方式id
     * @return
     */
    @RequestMapping("/deleteOneExpressConf")
    public ModelAndView deleteOneExpressConf(Integer expressId) {
        // 删除单个配送方式
        expressConfBizImpl.deleteOneExpressConf(expressId);
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_EXPRESSCONF));
        return mav;
    }
}
