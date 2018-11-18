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

import com.ningpai.system.bean.ShopConf;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IShopConfBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 购物设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 17:58:48
 * @version V1.0
 */
@Controller("shopConfController")
public class ShopConfController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ShopConfController.class);
    /** 购物设置 */
    private ShopConf shopConf;
    /** 购物设置业务类 */
    private IShopConfBiz shopConfBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 购物设置列表初始化URL */
    private static final String INIT_SHOPCONF = "initShopConf.htm";
    /** 添加购物设置JSP页面 */
    private static final String ADD_SHOPCONF_JSP = "jsp/system/shop/shopconf_add";
    /** 购物设置列表JSP页面 */
    private static final String SHOPCONF_LIST_JSP = "jsp/system/shop/shopconf_list";
    /** 更新购物设置JSP页面 */
    private static final String UPDATE_SHOPCONF_JSP = "jsp/system/shop/shopconf_update";
    /** 查询购物设置JSP页面 */
    private static final String READ_SHOPCONF_JSP = "jsp/system/shop/shopconf_read";

    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String SHOPCONF = "shopConf";

    private static final String IDS = "ids";

    private static final String TITLE = "title";

    private static final String SETCODE = "setCode";

    /**
     * 初始化购物设置列表
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
    @RequestMapping("/initShopConf")
    public ModelAndView initShopConf(PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            final HttpServletRequest request) {
        pageBean.setUrl(INIT_SHOPCONF);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SHOPCONF_LIST_JSP);
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        mav.addObject("pageBean", shopConfBizImpl.getShopConfByField(pa, pageBean));
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
            return shopConfBizImpl.getShopConfByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开购物设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddShopConfPage")
    public ModelAndView openAddShopConfPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_SHOPCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存购物设置
     * 
     * @param shopConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addShopConf")
    public ModelAndView addShopConf(ShopConf shopConf, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            shopConf.setInsertDate(new Date());
            shopConf.setInsertId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            shopConf.setDeleteStatus(0);
            // 添加购物设置
            boolean flag = shopConfBizImpl.saveShopConf(shopConf);
            if (flag) {
                mav.addObject(MSG, "保存购物设置成功！");
            } else {
                mav.addObject(MSG, "保存购物设置失败！");
                return backAddPage(shopConf, mav);
            }
            mav.setView(new RedirectView(INIT_SHOPCONF));
        } catch (Exception e) {
            LOGGER.error("保存购物设置对象异常！", e);
            mav.addObject(MSG, "保存购物设置失败！");
            return backAddPage(shopConf, mav);
        }
        // 返回购物设置列表
        return mav;
    }

    /**
     * 返还到添加页面
     * 
     * @param shopConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(ShopConf shopConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SHOPCONF, shopConf);
        mav.setViewName(ADD_SHOPCONF_JSP);
        return mav;
    }

    /**
     * 打开购物设置修改页面
     * 
     * @param id
     *            购物设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateShopConfPage")
    public ModelAndView openUpdateShopConfPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改购物设置
            mav.addObject(SHOPCONF, shopConfBizImpl.getShopConfById(id));
            mav.setViewName(UPDATE_SHOPCONF_JSP);
        } catch (Exception e) {
            LOGGER.error("加载购物设置对象失败！", e);
            mav.addObject(MSG, "加载购物设置对象失败！");
            // 返回购物设置列表
            mav.setView(new RedirectView(INIT_SHOPCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改购物设置
     */
    /**
     * 修改购物设置
     * 
     * @param shopConf
     *            购物设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateShopConf")
    public ModelAndView updateShopConf(ShopConf shopConf, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            shopConf.setModifyDate(new Date());
            shopConf.setModifyId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            // 修改购物设置
            if (shopConfBizImpl.updateShopConf(shopConf) >= 1) {
                mav.addObject(MSG, "修改购物设置成功！");
            } else {
                mav.addObject(MSG, "修改购物设置失败！");
                return backUpdatePage(shopConf, mav);
            }
            mav.setView(new RedirectView(INIT_SHOPCONF));
        } catch (Exception e) {
            LOGGER.error("修改购物设置对象异常！", e);
            mav.addObject(MSG, "修改购物设置失败！");
            return backUpdatePage(shopConf, mav);
        }
        // 返回购物设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param shopConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(ShopConf shopConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SHOPCONF, shopConf);
        mav.setViewName(UPDATE_SHOPCONF_JSP);
        return mav;
    }

    /**
     * 查看购物设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            购物设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readShopConf")
    public ModelAndView readShopConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询购物设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "购物设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_SHOPCONF));
        } else {
            mav.setViewName(READ_SHOPCONF_JSP);
            mav.addObject(SHOPCONF, shopConfBizImpl.getShopConfById(id));
        }
        return mav;
    }

    /**
     * 删除购物设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            购物设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteShopConf")
    public ModelAndView deleteShopConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SHOPCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除购物设置操作！");
            return mav;
        } else {

            // 删除购物设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (shopConfBizImpl.deleteShopConf(ids) >= 1) {
                        mav.addObject(MSG, "删除购物设置成功！");
                    } else {
                        mav.addObject(MSG, "删除购物设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (shopConfBizImpl.updateShopConfFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除购物设置到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除购物设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除购物设置对象异常！", e);
                mav.addObject(MSG, "删除购物设置失败！");
            }
        }
        // 删除成功返回购物设置列表
        return mav;
    }

    /**
     * 还原删除购物设置
     * 
     * @param ids
     *            购物设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreShopConf")
    public ModelAndView restoreShopConf(@RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SHOPCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除购物设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (shopConfBizImpl.updateShopConfFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除购物设置到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除购物设置到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除购物设置对象异常！", e);
                mav.addObject(MSG, "还原删除购物设置失败！");
            }
        }
        // 删除成功返回购物设置列表
        return mav;
    }

    /**
     * 查询购物设置
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
    @RequestMapping("/queryShopConf")
    public ModelAndView queryShopConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = TITLE, required = false, defaultValue = "") String title, @RequestParam(value = SETCODE, required = false, defaultValue = "") String setCode,
            PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (title != null && title.trim().length() != 0) {
            param.put(TITLE, title);
            queryParam.put(TITLE, title);
            queryParam.put("searchField", TITLE);
            queryParam.put("searchValue", title);
            queryParam.put("searchName", "方案标题");
        }
        if (setCode != null && setCode.trim().length() != 0) {
            param.put(SETCODE, setCode);
            queryParam.put(SETCODE, setCode);
            queryParam.put("searchField", SETCODE);
            queryParam.put("searchValue", setCode);
            queryParam.put("searchName", "方案代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SHOPCONF_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询购物设置
            mav.addObject("pageBean", shopConfBizImpl.getShopConfByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询购物设置信息异常！", e);
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
    }

    /**
     * 获得购物设置
     * 
     * @return 购物设置对象
     */
    public ShopConf getShopConf() {
        return shopConf;
    }

    /**
     * 自动注入赋值购物设置对象
     * 
     * @param shopConf
     *            购物设置对象
     */
    @Resource(name = SHOPCONF)
    public void setShopConf(ShopConf shopConf) {
        this.shopConf = shopConf;
    }

    /**
     * 获得购物设置业务对象
     * 
     * @return 购物设置对象业务对象
     */
    public IShopConfBiz getShopConfBizImpl() {
        return shopConfBizImpl;
    }

    /**
     * 自动注入赋值购物设置对象
     * 
     * @param shopConfBizImpl
     *            购物设置对象
     */
    @Resource(name = "shopConfBizImpl")
    public void setShopConfBizImpl(IShopConfBiz shopConfBizImpl) {
        this.shopConfBizImpl = shopConfBizImpl;
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
