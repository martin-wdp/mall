package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.ISysDictionaryBiz;
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
import java.util.Map;

/**
 * 物流公司设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 10:48:06
 * @version V1.0
 */
@Controller("logisticsCompanyController")
public class LogisticsCompanyController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LogisticsCompanyController.class);

    public static final String OPERAPATH = "operaPath";
    private static final String PAGEBEAN = "pageBean";
    private static final String LOGGERINFO1 = ",用户名:";

    /** 物流公司设置业务类 */
    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz logisticsCompanyBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    @Resource(name = "dictionarysCache")
    private IDictionarysCache dictionarysCache;
    /** 系统字典业务类 */
    @Resource(name = "sysDictionaryBizImpl")
    private ISysDictionaryBiz sysDictionaryBizImpl;
    /** 物流公司设置列表初始化URL */
    private static final String INIT_LOGISTICSCOMPANY = "initLogisticsCompany.htm";
    /** 添加物流公司设置JSP页面 */
    private static final String ADD_LOGISTICSCOMPANY_JSP = "jsp/system/logistics/logisticscompany_add";
    /** 物流公司设置列表JSP页面 */
    private static final String LOGISTICSCOMPANY_LIST_JSP = "jsp/system/logistics/logisticscompany_list";
    /** 更新物流公司设置JSP页面 */
    private static final String UPDATE_LOGISTICSCOMPANY_JSP = "jsp/system/logistics/logisticscompany_update";
    /** 查询物流公司设置JSP页面 */
    private static final String READ_LOGISTICSCOMPANY_JSP = "jsp/system/logistics/logisticscompany_read";
    /** 常量DELETESTATUS */
    private static final String DELETESTATUS = "deleteStatus";
    /** 常量MSG */
    private static final String MSG = "msg";
    /** 常量LOGISTICSCOMPANY */
    private static final String LOGISTICSCOMPANY = "logisticsCompany";
    /** 常量IDS */
    private static final String IDS = "ids";
    /** 常量NAME */
    private static final String NAME = "name";
    /** 常量CODE */
    private static final String CODE = "code";

    /**
     * 初始化物流公司设置列表
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
    @RequestMapping("/initLogisticsCompany")
    public ModelAndView initLogisticsCompany(final PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final Integer deleteStatus,
            final HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // 设置URL
        pageBean.setUrl(INIT_LOGISTICSCOMPANY);
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 视图名称
        mav.setViewName(LOGISTICSCOMPANY_LIST_JSP);
        // 初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        // 获取物流公司
        mav.addObject(PAGEBEAN, logisticsCompanyBizImpl.getLogisticsCompanyByField(pa, pageBean));
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
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, 1);
        try {
            return logisticsCompanyBizImpl.getLogisticsCompanyByFieldTotal(pa);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.info("获得删除对象的数量异常！");
        }
        return 0;
    }

    /**
     * 打开物流公司设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddLogisticsCompanyPage")
    public ModelAndView openAddLogisticsCompanyPage(final HttpServletRequest request) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_LOGISTICSCOMPANY_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存物流公司设置
     * 
     * @param logisticsCompany
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addLogisticsCompany")
    public ModelAndView addLogisticsCompany(LogisticsCompany logisticsCompany, BindingResult bindingResult, final HttpServletRequest request) {
        /*
         * if (bindingResult.hasErrors()) { return new ModelAndView(new
         * RedirectView("initLogisticsCompany.htm")); }
         */
        ModelAndView mav = new ModelAndView();
        try {
            // 添加日期
            logisticsCompany.setInsertDate(new Date());
            // 获取登录id
            Long loginUserId = getLoginUserId(request);
            // 添加人
            logisticsCompany.setInsertId(loginUserId.intValue());
            // 删除状态
            logisticsCompany.setDeleteStatus(0);
            // 排序,添加时未填，则添加到最后
            if(logisticsCompany.getSort()==0){
                logisticsCompany.setSort(logisticsCompanyBizImpl.getLogisticsCompanyMaxSort() + 1);
            }
            // 添加物流公司设置
            boolean flag = logisticsCompanyBizImpl.saveLogisticsCompany(logisticsCompany);
            if (flag) {
                // 添加成功
                // 会员名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志输出
                OperaLogUtil.addOperaLog(request, customerName, "添加物流公司", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                mav.addObject(MSG, "保存物流公司设置成功！");
            } else {
                // 添加失败
                mav.addObject(MSG, "保存物流公司设置失败！");
                return backAddPage(logisticsCompany, mav);
            }
            mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("添加物流公司错误：=>",e);
            mav.addObject(MSG, "保存物流公司设置失败！");
            return backAddPage(logisticsCompany, mav);
        }
        // 返回物流公司设置列表
        return mav;
    }


    /**
     *  货币设置
     *  返还到添加页面
     * @param logisticsCompany
     * @param mav
     * @return ModelAndView
     */
    private ModelAndView backAddPage(final LogisticsCompany logisticsCompany, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(LOGISTICSCOMPANY, logisticsCompany);
        mav.setViewName(ADD_LOGISTICSCOMPANY_JSP);
        return mav;
    }

    /**
     * 打开物流公司设置修改页面
     * 
     * @param id
     *            物流公司设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateLogisticsCompanyPage")
    public ModelAndView openUpdateLogisticsCompanyPage(@RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改物流公司设置
            mav.addObject(LOGISTICSCOMPANY, logisticsCompanyBizImpl.getLogisticsCompanyById(id));
            mav.setViewName(UPDATE_LOGISTICSCOMPANY_JSP);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.info("加载物流公司设置对象失败！");

        }
        return mav;
    }

    /**
     * 根据主键查询物流公司
     * 
     * @param companyId
     *            物流公司id
     * @return 物流公司信息
     */
    @RequestMapping("/selectLogisticsCompanyById")
    @ResponseBody
    public LogisticsCompany selectLogisticsCompanyById(Integer companyId) {
        return logisticsCompanyBizImpl.getLogisticsCompanyById(companyId);
    }

    /**
     * 修改物流公司设置
     * 
     * @param logisticsCompany
     *            物流公司设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateLogisticsCompany")
    public ModelAndView updateLogisticsCompany(@Valid final LogisticsCompany logisticsCompany, BindingResult bindingResult, final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("initLogisticsCompany.htm"));
        }
        ModelAndView mav = new ModelAndView();
        try {
            // 修改日期
            logisticsCompany.setModifyDate(new Date());
            // 获取登录id
            Long loginUserId = getLoginUserId(request);
            // 修改id
            logisticsCompany.setModifyId(loginUserId.intValue());
            // 修改物流公司设置
            if (logisticsCompanyBizImpl.updateLogisticsCompany(logisticsCompany) >= 1) {
                // 添加成功
                // 登录名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 日志记录
                OperaLogUtil.addOperaLog(request, customerName, "修改物流公司", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                mav.addObject(MSG, "修改物流公司设置成功！");
            } else {
                // 添加失败
                mav.addObject(MSG, "修改物流公司设置失败！");
                return backUpdatePage(logisticsCompany, mav);
            }
            mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("修改物流公司错误：=>" ,e);
            mav.addObject(MSG, "修改物流公司设置失败！");
            return backUpdatePage(logisticsCompany, mav);
        }
        // 返回物流公司设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param logisticsCompany
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(final LogisticsCompany logisticsCompany, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(LOGISTICSCOMPANY, logisticsCompany);
        mav.setViewName(UPDATE_LOGISTICSCOMPANY_JSP);
        return mav;
    }

    /**
     * 查看物流公司设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            物流公司设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readLogisticsCompany")
    public ModelAndView readLogisticsCompany(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus, @RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询物流公司设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "物流公司设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        } else {
            mav.setViewName(READ_LOGISTICSCOMPANY_JSP);
            mav.addObject(LOGISTICSCOMPANY, logisticsCompanyBizImpl.getLogisticsCompanyById(id));
        }
        return mav;
    }

    /**
     * 删除物流公司设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            物流公司设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteLogisticsCompany")
    public ModelAndView deleteLogisticsCompany(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = IDS) final String ids, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除物流公司设置操作！");
            return mav;
        } else {
            // 删除物流公司设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (logisticsCompanyBizImpl.deleteLogisticsCompany(ids) >= 1) {
                        // 删除物流公司设置成功
                        // 登录名称
                        String customerName = (String) request.getSession().getAttribute(NAME);
                        // 日志记录
                        OperaLogUtil.addOperaLog(request, customerName, "删除物流公司", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                        mav.addObject(MSG, "删除物流公司设置成功！");
                    } else {
                        // 删除物流公司设置失败
                        mav.addObject(MSG, "删除物流公司设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (logisticsCompanyBizImpl.updateLogisticsCompanyFieldById(pa) >= 1) {
                        // 删除物流公司设置到回收站成功
                        mav.addObject(MSG, "删除物流公司设置到回收站成功！");
                    } else {
                        // 删除物流公司设置到回收站失败
                        mav.addObject(MSG, "删除物流公司设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                // 错误日志输出
                LOGGER.error("删除物流公司错误：=>" ,e);
                mav.addObject(MSG, "删除物流公司设置失败！");
            }
        }
        // 删除成功返回物流公司设置列表
        return mav;
    }

    /**
     * 还原删除物流公司设置
     * 
     * @param ids
     *            物流公司设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreLogisticsCompany")
    public ModelAndView restoreLogisticsCompany(@RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除物流公司设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (logisticsCompanyBizImpl.updateLogisticsCompanyFieldById(pa) >= 1) {
                    // 删除物流公司设置到回收站成功
                    mav.addObject(MSG, "删除物流公司设置到回收站成功！");
                } else {
                    // 删除物流公司设置到回收站失败
                    mav.addObject(MSG, "删除物流公司设置到回收站失败！");
                }
            } catch (Exception e) {
                // 错误日志记录
                LOGGER.info("还原删除物流公司设置对象异常！");
                mav.addObject(MSG, "还原删除物流公司设置失败！");
            }
        }
        // 删除成功返回物流公司设置列表
        return mav;
    }

    /**
     * 查询物流公司设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param name
     *            物流公司名称
     * @param code
     *            物流公司代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryLogisticsCompany")
    public ModelAndView queryLogisticsCompany(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = NAME, required = false, defaultValue = "") final String name, @RequestParam(value = CODE, required = false, defaultValue = "") final String code,
            final PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (name != null && name.trim().length() != 0) {
            param.put(NAME, name);
            queryParam.put(NAME, name);
            queryParam.put("searchField", NAME);
            queryParam.put("searchValue", name);
            queryParam.put("searchName", "物流公司名称");
        }
        if (code != null && code.trim().length() != 0) {
            param.put(CODE, code);
            queryParam.put(CODE, code);
            queryParam.put("searchField", CODE);
            queryParam.put("searchValue", code);
            queryParam.put("searchName", "物流公司代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(LOGISTICSCOMPANY_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询物流公司设置
            mav.addObject(PAGEBEAN, logisticsCompanyBizImpl.getLogisticsCompanyByField(param, pageBean));
        } catch (Exception e) {
            // 错误日志记录
            LOGGER.error("查询物流公司设置信息异常！",e);
        }
        return mav;
    }

    /**
     * 查询物流公司设置(新)
     * 
     * @param companyName
     *            物流公司名称
     * @param companyCode
     *            物流公司代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryLogisticsCompanyNew")
    public ModelAndView queryLogisticsCompanyNew(String companyName, String companyCode, final PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(NAME, companyName);
        param.put(CODE, companyCode);
        param.put(DELETESTATUS, 0);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(LOGISTICSCOMPANY_LIST_JSP);
        try {
            // 查询物流公司设置
            mav.addObject(PAGEBEAN, logisticsCompanyBizImpl.getLogisticsCompanyByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询物流公司设置信息异常！",e);
        }
        mav.addObject("companyName", companyName);
        mav.addObject("companyCode", companyCode);
        return mav;
    }

    /**
     * 修改物流公司启用状态
     * 
     * @param logComId
     * @return
     */
    @RequestMapping("/changeUserdStatucForLogCom")
    public ModelAndView changeUserdStatucForLogCom(Long logComId) {
        try {
            if (logisticsCompanyBizImpl.changeUserdStatus(logComId)) {
                LOGGER.debug("===============配送方式启用状态修改成功");
            } else {
                LOGGER.debug("===============配送方式启用状态修改失败");
            }
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("修改物流公司启用状态错误：=>",e);
        }
        return new ModelAndView(new RedirectView(INIT_LOGISTICSCOMPANY));
    }

    /**
     * 检查物流公司相关的配送方式数量
     * 
     * @param logComId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkExpressCount")
    public boolean checkExpressCount(Long logComId) {
        return this.logisticsCompanyBizImpl.checkDeleteLogistics(logComId);
    }

    /**
     * 填充字典信息
     * 
     * @param mav
     *            视图对象
     */
    private void fullDictionarys(final ModelAndView mav) {
        // 循环需要的字典
        for (String dep : DICTIONARYS_LIST) {
            try {
                mav.addObject(dep, dictionarysCache.getDictionaryByName(dep));
            } catch (Exception e) {
                // 错误日志输出
                LOGGER.info("获得字典缓存异常，字典名：" + dep);
            }
        }
        // 获得物流设置字典
        PageBean pg = new PageBean();
        int pageSize = 100;
        pg.setPageSize(pageSize);
        Map<String, Object> pa = new HashMap<String, Object>(2);
        int parentId = 113;
        pa.put("parentId", parentId);
        pa.put("status", 1);
        try {
            mav.addObject("sysDicLogisticsCode", sysDictionaryBizImpl.getSysDictionaryByField(pa, pg).getList());
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.info("获得货币设置字典异常！");
        }
    }

    /**
     * 获取登录用户的ID
     * 
     * @param request
     * @return
     */
    private Long getLoginUserId(final HttpServletRequest request) {
        Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        return loginUserId;
    }

    /**
     * 删除单个物流公司
     * 
     * @param companyId
     *            物流公司主键id
     * @return 物流公司列表页面
     */
    @RequestMapping("/deleteLogisticsCompanyOne")
    public ModelAndView deleteLogisticsCompanyOne(Integer companyId) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        // 删除单个物流公司
        logisticsCompanyBizImpl.deleteLogisticsCompanyOne(companyId);
        return mav;
    }

    /**
     * 批量删除物流公司
     * 
     * @param companyIds
     *            物流公司主键id
     * @return 物流公司列表页面
     */
    @RequestMapping("/deleteLogisticsCompanyBatch")
    public ModelAndView deleteLogisticsCompanyBatch(Integer[] companyIds) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_LOGISTICSCOMPANY));
        // 批量删除物流公司
        for (Integer companyId : companyIds) {
            logisticsCompanyBizImpl.deleteLogisticsCompanyOne(companyId);
        }
        return mav;
    }
}
