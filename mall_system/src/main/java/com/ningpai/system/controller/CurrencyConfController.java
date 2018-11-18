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

import com.ningpai.system.bean.CurrencyConf;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.ICurrencyConfBiz;
import com.ningpai.system.service.ISysDictionaryBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 货币设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 13:17:21
 * @version V1.0
 */
@Controller("currencyConfController")
public class CurrencyConfController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            CurrencyConfController.class);
    /** 货币设置 */
    private CurrencyConf currencyConf;
    /** 货币设置业务类 */
    private ICurrencyConfBiz currencyConfBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 系统字典业务类 */
    private ISysDictionaryBiz sysDictionaryBizImpl;
    /** 货币设置列表初始化URL */
    private static final String INIT_CURRENCYCONF = "initCurrencyConf.htm";
    /** 添加货币设置JSP页面 */
    private static final String ADD_CURRENCYCONF_JSP = "jsp/system/currency/currencyconf_add";
    /** 货币设置列表JSP页面 */
    private static final String CURRENCYCONF_LIST_JSP = "jsp/system/currency/currencyconf_list";
    /** 更新货币设置JSP页面 */
    private static final String UPDATE_CURRENCYCONF_JSP = "jsp/system/currency/currencyconf_update";
    /** 查询货币设置JSP页面 */
    private static final String READ_CURRENCYCONF_JSP = "jsp/system/currency/currencyconf_read";
    /** 常量DELETESTATUS */
    private static final String DELETESTATUS = "deleteStatus";
    /** 常量MSG */
    private static final String MSG = "msg";
    /** 常量CURRENCYCONF */
    private static final String CURRENCYCONF = "currencyConf";
    /** 常量IDS */
    private static final String IDS = "ids";
    /** 常量CURRENCYNAME */
    private static final String CURRENCYNAME = "currencyName";
    /** 常量CURRENCYCODE */
    private static final String CURRENCYCODE = "currencyCode";

    /**
     * 初始化货币设置列表
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
    @RequestMapping("/initCurrencyConf")
    public ModelAndView initCurrencyConf(
            PageBean pageBean,
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            HttpServletRequest request) {
        // 设置URL
        pageBean.setUrl(INIT_CURRENCYCONF);
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置视图名称
        mav.setViewName(CURRENCYCONF_LIST_JSP);
        // 初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        // 删除状态
        pa.put(DELETESTATUS, deleteStatus);
        // 获取货币设置列表
        mav.addObject("pageBean",
                currencyConfBizImpl.getCurrencyConfByField(pa, pageBean));
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
        // 设置删除状态为1
        pa.put(DELETESTATUS, 1);
        try {
            return currencyConfBizImpl.getCurrencyConfByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开货币设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddCurrencyConfPage")
    public ModelAndView openAddCurrencyConfPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_CURRENCYCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存货币设置
     * 
     * @param currencyConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addCurrencyConf")
    public ModelAndView addCurrencyConf(CurrencyConf currencyConf,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 设置添加时间
            currencyConf.setInsertDate(new Date());
            // 设置添加人
            currencyConf.setInsertId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 删除状态
            currencyConf.setDeleteStatus(0);
            // 添加货币设置
            boolean flag = currencyConfBizImpl.saveCurrencyConf(currencyConf);
            if (flag) {
                // 添加成功
                mav.addObject(MSG, "保存货币设置成功！");
            } else {
                // 添加失败
                mav.addObject(MSG, "保存货币设置失败！");
                return backAddPage(currencyConf, mav);
            }
            mav.setView(new RedirectView(INIT_CURRENCYCONF));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("保存货币设置对象异常！", e);
            mav.addObject(MSG, "保存货币设置失败！");
            return backAddPage(currencyConf, mav);
        }
        // 返回货币设置列表
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
    private ModelAndView backAddPage(CurrencyConf currencyConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(CURRENCYCONF, currencyConf);
        mav.setViewName(ADD_CURRENCYCONF_JSP);
        return mav;
    }

    /**
     * 打开货币设置修改页面
     * 
     * @param id
     *            货币设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateCurrencyConfPage")
    public ModelAndView openUpdateCurrencyConfPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改货币设置
            mav.addObject(CURRENCYCONF,
                    currencyConfBizImpl.getCurrencyConfById(id));
            mav.setViewName(UPDATE_CURRENCYCONF_JSP);
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("加载货币设置对象失败！", e);
            mav.addObject(MSG, "加载货币设置对象失败！");
            // 返回货币设置列表
            mav.setView(new RedirectView(INIT_CURRENCYCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改货币设置
     */
    /**
     * 修改货币设置
     * 
     * @param currencyConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateCurrencyConf")
    public ModelAndView updateCurrencyConf(CurrencyConf currencyConf,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            currencyConf.setModifyDate(new Date());
            currencyConf.setModifyId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 修改货币设置
            if (currencyConfBizImpl.updateCurrencyConf(currencyConf) >= 1) {
                // 修改成功
                mav.addObject(MSG, "修改货币设置成功！");
            } else {
                // 修改失败
                mav.addObject(MSG, "修改货币设置失败！");
                return backUpdatePage(currencyConf, mav);
            }
            mav.setView(new RedirectView(INIT_CURRENCYCONF));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("修改货币设置对象异常！", e);
            mav.addObject(MSG, "修改货币设置失败！");
            return backUpdatePage(currencyConf, mav);
        }
        // 返回货币设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param currencyConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(CurrencyConf currencyConf,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(CURRENCYCONF, currencyConf);
        mav.setViewName(UPDATE_CURRENCYCONF_JSP);
        return mav;
    }

    /**
     * 查看货币设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            货币设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readCurrencyConf")
    public ModelAndView readCurrencyConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam("id") int id) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询货币设置id是否为0
        if (id == 0) {
            // 货币设置对象为空
            mav.addObject(MSG, "货币设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_CURRENCYCONF));
        } else {
            // 获取货币设置对象
            mav.setViewName(READ_CURRENCYCONF_JSP);
            mav.addObject(CURRENCYCONF,
                    currencyConfBizImpl.getCurrencyConfById(id));
        }
        return mav;
    }

    /**
     * 删除货币设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            货币设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteCurrencyConf")
    public ModelAndView deleteCurrencyConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_CURRENCYCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除货币设置操作！");
            return mav;
        } else {

            // 删除货币设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (currencyConfBizImpl.deleteCurrencyConf(ids) >= 1) {
                        // 删除成功
                        mav.addObject(MSG, "删除货币设置成功！");
                    } else {
                        // 删除失败
                        mav.addObject(MSG, "删除货币设置失败！");
                    }
                    // 逻辑删除
                } else {
                    // 初始化map
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (currencyConfBizImpl.updateCurrencyConfFieldById(pa) >= 1) {
                        // 删除货币设置到回收站成功
                        mav.addObject(MSG, "删除货币设置到回收站成功！");
                    } else {
                        // 删除货币设置到回收站失败
                        mav.addObject(MSG, "删除货币设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                // 日志输出
                LOGGER.error("删除货币设置对象异常！", e);
                mav.addObject(MSG, "删除货币设置失败！");
            }
        }
        // 删除成功返回货币设置列表
        return mav;
    }

    /**
     * 还原删除货币设置
     * 
     * @param ids
     *            货币设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreCurrencyConf")
    public ModelAndView restoreCurrencyConf(
            @RequestParam(value = IDS) String ids) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_CURRENCYCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除货币设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (currencyConfBizImpl.updateCurrencyConfFieldById(pa) >= 1) {
                    // 删除货币设置到回收站成功
                    mav.addObject(MSG, "删除货币设置到回收站成功！");
                } else {
                    // 删除货币设置到回收站失败
                    mav.addObject(MSG, "删除货币设置到回收站失败！");
                }
            } catch (Exception e) {
                // 日志输出
                LOGGER.error("还原删除货币设置对象异常！", e);
                mav.addObject(MSG, "还原删除货币设置失败！");
            }
        }
        // 删除成功返回货币设置列表
        return mav;
    }

    /**
     * 查询货币设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param currencyName
     *            货币名称
     * @param currencyCode
     *            货币代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryCurrencyConf")
    public ModelAndView queryCurrencyConf(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = CURRENCYNAME, required = false, defaultValue = "") String currencyName,
            @RequestParam(value = CURRENCYCODE, required = false, defaultValue = "") String currencyCode,
            PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (currencyName != null && currencyName.trim().length() != 0) {
            param.put(CURRENCYNAME, currencyName);
            queryParam.put(CURRENCYNAME, currencyName);
            queryParam.put("searchField", CURRENCYNAME);
            queryParam.put("searchValue", currencyName);
            queryParam.put("searchName", "货币名称");
        }
        if (currencyCode != null && currencyCode.trim().length() != 0) {
            param.put(CURRENCYCODE, currencyCode);
            queryParam.put(CURRENCYCODE, currencyCode);
            queryParam.put("searchField", CURRENCYCODE);
            queryParam.put("searchValue", currencyCode);
            queryParam.put("searchName", "货币代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(CURRENCYCONF_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询货币设置
            mav.addObject("pageBean",
                    currencyConfBizImpl.getCurrencyConfByField(param, pageBean));
        } catch (Exception e) {
            // 日志输出
            LOGGER.error("查询货币设置信息异常！",e);
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
                mav.addObject(dep, dictionarysCache.getDictionaryByName(dep));
            } catch (Exception e) {
                LOGGER.error("获得字典缓存异常，字典名：" + dep, e);
            }
        }
        // 获得货币设置字典
        PageBean pg = new PageBean();
        int pageSize = 1000;
        pg.setPageSize(pageSize);
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put("parentId", 1);
        pa.put("status", 1);
        try {
            mav.addObject("sysDic", sysDictionaryBizImpl
                    .getSysDictionaryByField(pa, pg).getList());
        } catch (Exception e) {
            LOGGER.error("获得货币设置字典异常！", e);
        }
    }

    /**
     * 获得货币设置
     * 
     * @return 货币设置对象
     */
    public CurrencyConf getCurrencyConf() {
        return currencyConf;
    }

    /**
     * 自动注入赋值货币设置对象
     * 
     * @param currencyConf
     *            货币设置对象
     */
    @Resource(name = CURRENCYCONF)
    public void setCurrencyConf(CurrencyConf currencyConf) {
        this.currencyConf = currencyConf;
    }

    /**
     * 获得货币设置业务对象
     * 
     * @return 货币设置对象业务对象
     */
    public ICurrencyConfBiz getCurrencyConfBizImpl() {
        return currencyConfBizImpl;
    }

    /**
     * 自动注入赋值货币设置对象
     * 
     * @param currencyConfBizImpl
     *            货币设置对象
     */
    @Resource(name = "currencyConfBizImpl")
    public void setCurrencyConfBizImpl(ICurrencyConfBiz currencyConfBizImpl) {
        this.currencyConfBizImpl = currencyConfBizImpl;
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

    /**
     * 获得系统字典业务对象
     * 
     * @return 系统字典对象业务对象
     */
    public ISysDictionaryBiz getSysDictionaryBizImpl() {
        return sysDictionaryBizImpl;
    }

    /**
     * 自动注入赋值系统字典对象
     * 
     * @param currencyConfBizImpl
     *            系统字典对象
     */
    @Resource(name = "sysDictionaryBizImpl")
    public void setSysDictionaryBizImpl(ISysDictionaryBiz sysDictionaryBizImpl) {
        this.sysDictionaryBizImpl = sysDictionaryBizImpl;
    }
}
