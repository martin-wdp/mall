package com.ningpai.system.controller;

import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.ISysErrorPageBiz;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常页面设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 10:10:44
 * @version V1.0
 */
@Controller("sysErrorPageController")
public class SysErrorPageController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            SysErrorPageController.class);
    /** 异常页面设置 */
    private SysErrorPage sysErrorPage;
    /** 异常页面设置业务类 */
    private ISysErrorPageBiz sysErrorPageBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 异常页面设置列表初始化URL */
    private static final String INIT_SYSERRORPAGE = "initSysErrorPage.htm";
    /** 添加异常页面设置JSP页面 */
    private static final String ADD_SYSERRORPAGE_JSP = "jsp/system/errorpage/syserrorpage_add";
    /** 异常页面设置列表JSP页面 */
    private static final String SYSERRORPAGE_LIST_JSP = "jsp/system/errorpage/syserrorpage_list";
    /** 更新异常页面设置JSP页面 */
    private static final String UPDATE_SYSERRORPAGE_JSP = "jsp/system/errorpage/syserrorpage_update";
    /** 查询异常页面设置JSP页面 */
    private static final String READ_SYSERRORPAGE_JSP = "jsp/system/errorpage/syserrorpage_read";
    /** 跳转异常页面 */
    private static final String SYS_SYSERRORPAGE = "error/comming_soon";

    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String SYSERRORPAGE = "sysErrorPage";

    private static final String IDS = "ids";

    private static final String TITLE = "title";

    private static final String PAGETITLE = "pageTitle";

    private static final String SETCODE = "setCode";

    /**
     * 初始化异常页面设置列表
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
    @RequestMapping("/initSysErrorPage")
    public ModelAndView initSysErrorPage(
            PageBean pageBean,
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") Integer deleteStatus,
            String pageName, HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        pageBean.setUrl(INIT_SYSERRORPAGE);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SYSERRORPAGE_LIST_JSP);
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        pa.put(PAGETITLE, pageName);
        mav.addObject("pageBean",
                sysErrorPageBizImpl.getSysErrorPageByField(pa, pageBean));
        mav.addObject(DELETESTATUS, deleteStatus);
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        mav.addObject(PAGETITLE, pageName);
        // 获得删除对象的数量
        mav.addObject("deleteObjectSize", getDeleteObjectSize());
        return mav;
    }

    /**
     * 查询单个异常页面
     * 
     * @param errorPageId
     *            主键id
     * @return 删除对象的数量
     */
    @RequestMapping("/queryErrorPageById")
    @ResponseBody
    public SysErrorPage queryErrorPageById(Integer errorPageId) {
        return sysErrorPageBizImpl.getSysErrorPageById(errorPageId);
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
            return sysErrorPageBizImpl.getSysErrorPageByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开异常页面设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddSysErrorPagePage")
    public ModelAndView openAddSysErrorPagePage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_SYSERRORPAGE_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存异常页面设置
     * 
     * @param sysErrorPage
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addSysErrorPage")
    public ModelAndView addSysErrorPage(SysErrorPage sysErrorPage,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            sysErrorPage.setInsertDate(new Date());
            sysErrorPage.setInsertId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            sysErrorPage.setDeleteStatus(0);
            // 添加异常页面设置
            boolean flag = sysErrorPageBizImpl.saveSysErrorPage(sysErrorPage);
            if (flag) {
                mav.addObject(MSG, "保存异常页面设置成功！");
            } else {
                mav.addObject(MSG, "保存异常页面设置失败！");
                return backAddPage(sysErrorPage, mav);
            }
            mav.setView(new RedirectView(INIT_SYSERRORPAGE));
        } catch (Exception e) {
            LOGGER.error("保存异常页面设置对象异常！", e);
            mav.addObject(MSG, "保存异常页面设置失败！");
            return backAddPage(sysErrorPage, mav);
        }
        // 返回异常页面设置列表
        return mav;
    }

    /**
     * 返还到添加页面
     * 
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(SysErrorPage sysErrorPage, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SYSERRORPAGE, sysErrorPage);
        mav.setViewName(ADD_SYSERRORPAGE_JSP);
        return mav;
    }

    /**
     * 打开异常页面设置修改页面
     * 
     * @param id
     *            异常页面设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateSysErrorPagePage")
    public ModelAndView openUpdateSysErrorPagePage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改异常页面设置
            mav.addObject(SYSERRORPAGE,
                    sysErrorPageBizImpl.getSysErrorPageById(id));
            mav.setViewName(UPDATE_SYSERRORPAGE_JSP);
        } catch (Exception e) {
            LOGGER.error("加载异常页面设置对象失败！", e);
            mav.addObject(MSG, "加载异常页面设置对象失败！");
            // 返回异常页面设置列表
            mav.setView(new RedirectView(INIT_SYSERRORPAGE));
            return mav;
        }
        return mav;
    }

    /**
     * 修改异常页面设置
     */
    /**
     * 修改异常页面设置
     * 
     * @param sysErrorPage
     *            异常页面设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateSysErrorPage")
    public ModelAndView updateSysErrorPage(SysErrorPage sysErrorPage,
            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            sysErrorPage.setModifyDate(new Date());
            sysErrorPage.setModifyId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 修改异常页面设置
            if (sysErrorPageBizImpl.updateSysErrorPage(sysErrorPage) >= 1) {
                mav.addObject(MSG, "修改异常页面设置成功！");
            } else {
                mav.addObject(MSG, "修改异常页面设置失败！");
                return backUpdatePage(sysErrorPage, mav);
            }
            mav.setView(new RedirectView(INIT_SYSERRORPAGE));
        } catch (Exception e) {
            LOGGER.error("修改异常页面设置对象异常！", e);
            mav.addObject(MSG, "修改异常页面设置失败！");
            return backUpdatePage(sysErrorPage, mav);
        }
        // 返回异常页面设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param sysErrorPage
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(SysErrorPage sysErrorPage,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SYSERRORPAGE, sysErrorPage);
        mav.setViewName(UPDATE_SYSERRORPAGE_JSP);
        return mav;
    }

    /**
     * 查看异常页面设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            异常页面设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readSysErrorPage")
    public ModelAndView readSysErrorPage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询异常页面设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "异常页面设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_SYSERRORPAGE));
        } else {
            mav.setViewName(READ_SYSERRORPAGE_JSP);
            mav.addObject(SYSERRORPAGE,
                    sysErrorPageBizImpl.getSysErrorPageById(id));
        }
        return mav;
    }

    /**
     * 删除异常页面设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            异常页面设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteSysErrorPage")
    public ModelAndView deleteSysErrorPage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SYSERRORPAGE));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除异常页面设置操作！");
            return mav;
        } else {

            // 删除异常页面设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (sysErrorPageBizImpl.deleteSysErrorPage(ids) >= 1) {
                        mav.addObject(MSG, "删除异常页面设置成功！");
                    } else {
                        mav.addObject(MSG, "删除异常页面设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (sysErrorPageBizImpl.updateSysErrorPageFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除异常页面设置到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除异常页面设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除异常页面设置对象异常！", e);
                mav.addObject(MSG, "删除异常页面设置失败！");
            }
        }
        // 删除成功返回异常页面设置列表
        return mav;
    }

    /**
     * 还原删除异常页面设置
     * 
     * @param ids
     *            异常页面设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreSysErrorPage")
    public ModelAndView restoreSysErrorPage(
            @RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SYSERRORPAGE));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除异常页面设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (sysErrorPageBizImpl.updateSysErrorPageFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除异常页面设置到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除异常页面设置到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除异常页面设置对象异常！", e);
                mav.addObject(MSG, "还原删除异常页面设置失败！");
            }
        }
        // 删除成功返回异常页面设置列表
        return mav;
    }

    /**
     * 查询异常页面设置
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
    @RequestMapping("/querySysErrorPage")
    public ModelAndView querySysErrorPage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = TITLE, required = false, defaultValue = "") String title,
            @RequestParam(value = SETCODE, required = false, defaultValue = "") String setCode,
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
        mav.setViewName(SYSERRORPAGE_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询异常页面设置
            mav.addObject("pageBean",
                    sysErrorPageBizImpl.getSysErrorPageByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询异常页面设置信息异常！",e);
        }
        return mav;
    }

    /**
     * 根据name查询错误
     * @param pageName
     * @return
     */
    @RequestMapping("/querySysErrorByPageName")
    public ModelAndView querySysErrorByPageName(String pageName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SYS_SYSERRORPAGE);
        mav.addObject("sysErrorPage",
                sysErrorPageBizImpl.querySysErrorByPageName(pageName));
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
     * 获得异常页面设置
     * 
     * @return 异常页面设置对象
     */
    public SysErrorPage getSysErrorPage() {
        return sysErrorPage;
    }

    /**
     * 自动注入赋值异常页面设置对象
     * 
     * @param sysErrorPage
     *            异常页面设置对象
     */
    @Resource(name = SYSERRORPAGE)
    public void setSysErrorPage(SysErrorPage sysErrorPage) {
        this.sysErrorPage = sysErrorPage;
    }

    /**
     * 获得异常页面设置业务对象
     * 
     * @return 异常页面设置对象业务对象
     */
    public ISysErrorPageBiz getSysErrorPageBizImpl() {
        return sysErrorPageBizImpl;
    }

    /**
     * 自动注入赋值异常页面设置对象
     * 
     *            异常页面设置对象
     */
    @Resource(name = "sysErrorPageBizImpl")
    public void setSysErrorPageBizImpl(ISysErrorPageBiz sysErrorPageBizImpl) {
        this.sysErrorPageBizImpl = sysErrorPageBizImpl;
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
