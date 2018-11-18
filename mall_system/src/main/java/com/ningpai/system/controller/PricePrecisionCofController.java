package com.ningpai.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.PricePrecisionCof;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IPricePrecisionCofBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 价格精度设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
@Controller("pricePrecisionCofController")
public class PricePrecisionCofController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            PricePrecisionCofController.class);
    /** 价格精度设置 */
    private PricePrecisionCof pricePrecisionCof;
    /** 价格精度设置业务类 */
    private IPricePrecisionCofBiz pricePrecisionCofBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {
            "sysDecimalDigits", "sysGetIntegerStyle" };
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 价格精度设置列表初始化URL */
    private static final String INIT_PRICEPRECISIONCOF = "initPricePrecisionCof.htm";
    /** 添加价格精度设置JSP页面 */
    private static final String ADD_PRICEPRECISIONCOF_JSP = "jsp/system/price/priceprecisioncof_add";
    /** 价格精度设置列表JSP页面 */
    private static final String PRICEPRECISIONCOF_LIST_JSP = "jsp/system/price/priceprecisioncof_list";
    /** 更新价格精度设置JSP页面 */
    private static final String UPDATE_PRICEPRECISIONCOF_JSP = "jsp/system/price/priceprecisioncof_update";
    /** 查询价格精度设置JSP页面 */
    private static final String READ_PRICEPRECISIONCOF_JSP = "jsp/system/price/priceprecisioncof_read";

    private static final String MSG = "msg";

    private static final String PRICEPRECISIONCOF = "pricePrecisionCof";

    private static final String CONFTITLE = "confTitle";

    private static final String CONFCODE = "confCode";

    /**
     * 初始化价格精度设置列表
     * 
     * @param pageBean
     *            分页对象
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/initPricePrecisionCof")
    public ModelAndView initPricePrecisionCof(PageBean pageBean,
            HttpServletRequest request) {
        pageBean.setUrl(INIT_PRICEPRECISIONCOF);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(PRICEPRECISIONCOF_LIST_JSP);
        mav.addObject("pageBean", pricePrecisionCofBizImpl
                .queryPricePrecisionCofByPage(new HashMap<String, Object>(1),
                        pageBean));
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        // 填充字典信息
        fullDictionarys(mav);
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
        pa.put("deleteStatus", 1);
        try {
            return pricePrecisionCofBizImpl
                    .getPricePrecisionCofByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开价格精度设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddPricePrecisionCofPage")
    public ModelAndView openAddPricePrecisionCofPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_PRICEPRECISIONCOF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存价格精度设置
     * 
     * @param pricePrecisionCof
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addPricePrecisionCof")
    public ModelAndView addPricePrecisionCof(
            PricePrecisionCof pricePrecisionCof, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 添加会员
            boolean flag = pricePrecisionCofBizImpl
                    .savePricePrecisionCof(pricePrecisionCof);
            if (flag) {
                mav.addObject(MSG, "保存价格精度设置成功！");
            } else {
                mav.addObject(MSG, "保存价格精度设置失败！");
                return backAddPage(pricePrecisionCof, mav);
            }
            mav.setView(new RedirectView(INIT_PRICEPRECISIONCOF));
        } catch (Exception e) {
            LOGGER.error("保存价格精度设置对象异常！", e);
            mav.addObject(MSG, "保存价格精度设置失败！");
            return backAddPage(pricePrecisionCof, mav);
        }
        // 返回价格精度设置列表
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
    private ModelAndView backAddPage(PricePrecisionCof pricePrecisionCof,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(PRICEPRECISIONCOF, pricePrecisionCof);
        mav.setViewName(ADD_PRICEPRECISIONCOF_JSP);
        return mav;
    }

    /**
     * 打开价格精度设置修改页面
     * 
     * @param id
     *            价格精度设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdatePricePrecisionCofPage")
    public ModelAndView openUpdatePricePrecisionCofPage(
            @RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改价格精度设置
            mav.addObject(PRICEPRECISIONCOF,
                    pricePrecisionCofBizImpl.getPricePrecisionCofById(id));
            mav.setViewName(UPDATE_PRICEPRECISIONCOF_JSP);
        } catch (Exception e) {
            LOGGER.error("加载价格精度设置对象失败！", e);
            mav.addObject(MSG, "加载价格精度设置对象失败！");
            // 返回价格精度设置列表
            mav.setView(new RedirectView(INIT_PRICEPRECISIONCOF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改价格精度设置
     */
    /**
     * 修改价格精度设置
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updatePricePrecisionCof")
    public ModelAndView updatePricePrecisionCof(
            PricePrecisionCof pricePrecisionCof, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 修改会员
            if (pricePrecisionCofBizImpl
                    .updatePricePrecisionCof(pricePrecisionCof) >= 1) {
                mav.addObject(MSG, "修改价格精度设置成功！");
            } else {
                mav.addObject(MSG, "修改价格精度设置失败！");
                return backUpdatePage(pricePrecisionCof, mav);
            }
            mav.setView(new RedirectView(INIT_PRICEPRECISIONCOF));
        } catch (Exception e) {
            LOGGER.error("修改价格精度设置对象异常！", e);
            mav.addObject(MSG, "修改价格精度设置失败！");
            return backUpdatePage(pricePrecisionCof, mav);
        }
        // 返回价格精度设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param pricePrecisionCof
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(PricePrecisionCof pricePrecisionCof,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(PRICEPRECISIONCOF, pricePrecisionCof);
        mav.setViewName(UPDATE_PRICEPRECISIONCOF_JSP);
        return mav;
    }

    /**
     * 查看价格精度设置
     * 
     * @param id
     *            价格精度设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readPricePrecisionCof")
    public ModelAndView readPricePrecisionCof(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        // 判断查询价格精度设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "价格精度设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_PRICEPRECISIONCOF));
        } else {
            mav.setViewName(READ_PRICEPRECISIONCOF_JSP);
            mav.addObject(PRICEPRECISIONCOF,
                    pricePrecisionCofBizImpl.getPricePrecisionCofById(id));
        }
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 删除价格精度设置
     * 
     * @param ids
     *            价格精度设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deletePricePrecisionCof")
    public ModelAndView deletePricePrecisionCof(
            @RequestParam(value = "ids") String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_PRICEPRECISIONCOF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除价格精度设置操作！");
            return mav;
        } else {
            // 删除价格精度设置
            try {
                if (pricePrecisionCofBizImpl.deletePricePrecisionCof(ids) >= 1) {
                    mav.addObject(MSG, "删除价格精度设置成功！");
                } else {
                    mav.addObject(MSG, "删除价格精度设置失败！");
                }
            } catch (Exception e) {
                LOGGER.error("删除价格精度设置对象异常！", e);
                mav.addObject(MSG, "删除价格精度设置失败！");
            }
        }
        // 删除成功返回价格精度设置列表
        return mav;
    }

    /**
     * 查询价格精度设置
     * 
     * @param confTitle
     *            设置方案标题
     * @param confCode
     *            设置方案代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryPricePrecisionCof")
    public ModelAndView queryPricePrecisionCof(
            @RequestParam(value = CONFTITLE, required = false, defaultValue = "") String confTitle,
            @RequestParam(value = CONFCODE, required = false, defaultValue = "") String confCode,
            PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (confTitle != null && confTitle.trim().length() != 0) {
            param.put(CONFTITLE, confTitle);
            queryParam.put(CONFTITLE, confTitle);
            queryParam.put("searchField", CONFTITLE);
            queryParam.put("searchValue", confTitle);
            queryParam.put("searchName", "方案标题");
        }
        if (confCode != null && confCode.trim().length() != 0) {
            param.put(CONFCODE, confCode);
            queryParam.put(CONFCODE, confCode);
            queryParam.put("searchField", CONFCODE);
            queryParam.put("searchValue", confCode);
            queryParam.put("searchName", "方案代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(PRICEPRECISIONCOF_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        try {
            // 查询价格精度设置
            mav.addObject("pageBean", pricePrecisionCofBizImpl
                    .getPricePrecisionCofByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询价格精度设置信息异常！",e);
        }
        // 填充字典信息
        fullDictionarys(mav);
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
    }

    /**
     * 获得价格精度设置
     * 
     * @return 价格精度设置对象
     */
    public PricePrecisionCof getPricePrecisionCof() {
        return pricePrecisionCof;
    }

    /**
     * 自动注入赋值价格精度设置对象
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     */
    @Resource(name = PRICEPRECISIONCOF)
    public void setPricePrecisionCof(PricePrecisionCof pricePrecisionCof) {
        this.pricePrecisionCof = pricePrecisionCof;
    }

    /**
     * 获得价格精度设置业务对象
     * 
     * @return 价格精度设置对象业务对象
     */
    public IPricePrecisionCofBiz getPricePrecisionCofBizImpl() {
        return pricePrecisionCofBizImpl;
    }

    /**
     * 自动注入赋值价格精度设置对象
     * 
     * @param currencyConfBizImpl
     *            价格精度设置对象
     */
    @Resource(name = "pricePrecisionCofBizImpl")
    public void setPricePrecisionCofBizImpl(
            IPricePrecisionCofBiz pricePrecisionCofBizImpl) {
        this.pricePrecisionCofBizImpl = pricePrecisionCofBizImpl;
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
