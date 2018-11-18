package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.SeoConf;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.ISeoConfBiz;
import com.ningpai.system.sitemap.CreateSiteMap;
import com.ningpai.system.sitemap.HtmlParser;
import com.ningpai.system.sitemap.Url;
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
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SEO设置Controller
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @Description:
 * @since 2014-03-24 13:35:13
 */
@Controller("seoConfController")
public class SeoConfController extends BaseController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(SeoConfController.class);

    private static final String LOGGERINFO1 = ",用户名:";

    /**
     * 需要的字典集合
     */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /**
     * SEO设置列表初始化URL
     */
    private static final String INIT_SEOCONF = "initSeoConf.htm";
    /**
     * 添加SEO设置JSP页面
     */
    private static final String ADD_SEOCONF_JSP = "jsp/system/seo/seoconf_add";
    /**
     * SEO设置列表JSP页面
     */
    private static final String SEOCONF_LIST_JSP = "jsp/system/seo/seoconf_list";
    /**
     * 更新SEO设置JSP页面
     */
    private static final String UPDATE_SEOCONF_JSP = "jsp/system/seo/seoconf_update";
    /**
     * 更新SEO网站地图设置JSP页面
     */
    private static final String UPDATE_WEBSITEMAP_JSP = "jsp/system/seo/websitemap_update";
    /**
     * 更新SEORobots文件设置JSP页面
     */
    private static final String UPDATE_ROBOTS_JSP = "jsp/system/seo/robots_update";
    /**
     * 查询SEO设置JSP页面
     */
    private static final String READ_SEOCONF_JSP = "jsp/system/seo/seoconf_read";

    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String SEOCONF = "seoConf";

    private static final String IDS = "ids";

    private static final String SEOTITLE = "seoTitle";

    private static final String SEOCODE = "seoCode";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * SEO设置
     */
    private SeoConf seoConf;
    /**
     * SEO设置业务类
     */
    private ISeoConfBiz seoConfBizImpl;
    /**
     * 字典缓存
     */
    private IDictionarysCache dictionarysCache;

    /**
     * 初始化SEO设置列表
     *
     * @param pageBean
     *            分页对象
     * @param deleteStatus
     *            对象删除标识
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/initSeoConf")
    public ModelAndView initSeoConf(PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final Integer deleteStatus,
            final HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        pageBean.setUrl(INIT_SEOCONF);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SEOCONF_LIST_JSP);
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        mav.addObject("pageBean", seoConfBizImpl.getSeoConfByField(pa, pageBean));
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
            return seoConfBizImpl.getSeoConfByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开SEO设置添加页面
     *
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/openAddSeoConfPage")
    public ModelAndView openAddSeoConfPage(final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_SEOCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存SEO设置
     *
     * @param seoConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addSeoConf")
    public ModelAndView addSeoConf(@Valid final SeoConf seoConf, BindingResult bindingResult, final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_SEOCONF));
        }
        ModelAndView mav = new ModelAndView();
        try {
            seoConf.setInsertDate(new Date());
            seoConf.setInsertId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            seoConf.setDeleteStatus(0);
            // 添加SEO设置
            boolean flag = seoConfBizImpl.saveSeoConf(seoConf);
            if (flag) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加SEO设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                mav.addObject(MSG, "保存SEO设置成功！");
            } else {
                mav.addObject(MSG, "保存SEO设置失败！");
                return backAddPage(seoConf, mav);
            }
            mav.setView(new RedirectView(INIT_SEOCONF));
        } catch (Exception e) {
            LOGGER.error("添加SEO设置错误：=>",e);
            mav.addObject(MSG, "保存SEO设置失败！");
            return backAddPage(seoConf, mav);
        }
        // 返回SEO设置列表
        return mav;
    }

    /**
     * 返还到添加页面
     *
     * @param seoConf
     * @param mav
     *
     * @return mav
     */
    private ModelAndView backAddPage(final SeoConf seoConf, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SEOCONF, seoConf);
        mav.setViewName(ADD_SEOCONF_JSP);
        return mav;
    }

    /**
     * 打开SEO设置修改页面
     *
     * @param id
     *            SEO设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateSeoConfPage")
    public ModelAndView openUpdateSeoConfPage(@RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改SEO设置
            mav.addObject(SEOCONF, seoConfBizImpl.getSeoConfById(id));
            mav.setViewName(UPDATE_SEOCONF_JSP);
        } catch (Exception e) {
            LOGGER.error("加载SEO设置对象失败！", e);
            mav.addObject(MSG, "加载SEO设置对象失败！");
            // 返回SEO设置列表
            mav.setView(new RedirectView(INIT_SEOCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 打开网站地图设置
     *
     * @param id
     * @return
     */
    @RequestMapping("/openSeoWebsitemapPage")
    public ModelAndView openSeoPcPage(@RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改SEO设置
            mav.addObject(SEOCONF, seoConfBizImpl.getSeoConfById(id));
            mav.setViewName(UPDATE_WEBSITEMAP_JSP);
        } catch (Exception e) {
            LOGGER.error("加载SEO网站地图设置对象失败！", e);
            mav.addObject(MSG, "加载SEO网站地图设置对象失败！");
            // 返回SEO设置列表
            mav.setView(new RedirectView(INIT_SEOCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 打开Robots设置页面
     *
     * @param id
     * @return
     */
    @RequestMapping("/openSeoRobotsPage")
    public ModelAndView openSeoRobotsPage(@RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改SEO设置
            mav.addObject(SEOCONF, seoConfBizImpl.getSeoConfById(id));
            mav.setViewName(UPDATE_ROBOTS_JSP);
        } catch (Exception e) {
            LOGGER.error("加载SEORobots设置对象失败！", e);
            mav.addObject(MSG, "加载SEORobots设置对象失败！");
            // 返回SEO设置列表
            mav.setView(new RedirectView(INIT_SEOCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改SEO设置
     */
    /**
     * 修改SEO设置
     *
     * @param seoConf
     *            SEO设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateSeoConf")
    public ModelAndView updateSeoConf(@Valid final SeoConf seoConf, BindingResult bindingResult, final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_SEOCONF));
        }
        ModelAndView mav = new ModelAndView();
        try {
            seoConf.setModifyDate(new Date());
            seoConf.setModifyId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            seoConfBizImpl.updateSeoConf(seoConf);
            String customerName = (String) request.getSession().getAttribute(NAME);
            // SEO设置
            if ("1".equals(seoConf.getType())) {
                OperaLogUtil.addOperaLog(request, customerName, "修改SEO设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            } else if ("2".equals(seoConf.getType())) { // 网站地图设置
                OperaLogUtil.addOperaLog(request, customerName, "修改网站地图设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            } else if ("3".equals(seoConf.getType())) { // roborts.txt生成
                OperaLogUtil.addOperaLog(request, customerName, "修改Roborts文件设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                seoConfBizImpl.createRoborts(seoConf, request);
            }
            mav.setView(new RedirectView(INIT_SEOCONF));
        } catch (Exception e) {
            LOGGER.error("修改SEO设置错误：=>" ,e);
            return backUpdatePage(seoConf, mav);
        }
        // 返回SEO设置列表
        return mav;
    }

    /**
     * 生成sitemap.xml文件
     *
     * @param seoConf
     * @param request
     * @return
     */
    @RequestMapping("/createSitemap")
    @ResponseBody
    public String createSitemap(SeoConf seoConf, HttpServletRequest request) {
        try {
            HtmlParser a = new HtmlParser(seoConf.getUrl());
            Collection<Url> s = a.getHrefList();
            String path = request.getSession().getServletContext().getRealPath("/");
            // newboss和qpmall的前台在同一个服务器上,取得qpmall前台的上一级目录
            String qpmallpath = new File(path).getParent();
            // 得到qpmall前台的根目录
            File fl = new File(qpmallpath + "/site/sitemap.xml");

            if (!fl.exists()) {
                fl.createNewFile();
            }
            Writer wt = new FileWriter(fl);
            CreateSiteMap.writeUrlset(wt, s.iterator());
            wt.close();
            seoConfBizImpl.updateSeoConf(seoConf);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "生成网站地图", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            return "1";
        } catch (Exception e) {
            LOGGER.error("生成网站地图失败", e);
            return "0";
        }
    }

    /**
     * 返还到修改页面
     *
     * @param seoConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(final SeoConf seoConf, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(SEOCONF, seoConf);
        if ("1".equals(seoConf.getType())) {

            mav.setViewName(UPDATE_SEOCONF_JSP);
        } else if ("2".equals(seoConf.getType())) {
            mav.setViewName(UPDATE_WEBSITEMAP_JSP);
        } else {
            mav.setViewName(UPDATE_ROBOTS_JSP);
        }
        return mav;
    }

    /**
     * 查看SEO设置
     *
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            SEO设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/readSeoConf")
    public ModelAndView readSeoConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus, @RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询SEO设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "SEO设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_SEOCONF));
        } else {
            mav.setViewName(READ_SEOCONF_JSP);
            mav.addObject(SEOCONF, seoConfBizImpl.getSeoConfById(id));
        }
        return mav;
    }

    /**
     * 删除SEO设置
     *
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            SEO设置记录ID字符串
     * @return 视图对象
     */
    @RequestMapping("/deleteSeoConf")
    public ModelAndView deleteSeoConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = IDS) final String ids, final HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SEOCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除SEO设置操作！");
            return mav;
        } else {
            // 删除SEO设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (seoConfBizImpl.deleteSeoConf(ids) >= 1) {
                        String customerName = (String) request.getSession().getAttribute(NAME);
                        OperaLogUtil.addOperaLog(request, customerName, "删除SEO设置", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                        mav.addObject(MSG, "删除SEO设置成功！");
                    } else {
                        mav.addObject(MSG, "删除SEO设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (seoConfBizImpl.updateSeoConfFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除SEO设置到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除SEO设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除SEO设置错误：=>" ,e);
                mav.addObject(MSG, "删除SEO设置失败！");
            }
        }
        // 删除成功返回SEO设置列表
        return mav;
    }

    /**
     * 批量删除SEO设置
     *
     * @param seoIds
     *            SEO设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/deleteSeoConfBatch")
    public ModelAndView deleteSeoConf(Long[] seoIds, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SEOCONF));
        for (Long seoId : seoIds) {
            seoConfBizImpl.deleteSeoConf(seoId);
        }
        // 删除成功返回SEO设置列表
        return mav;
    }

    /**
     * 还原删除SEO设置
     *
     * @param ids
     *            SEO设置记录ID字符串
     * @return 视图对象
     */
    @RequestMapping("/restoreSeoConf")
    public ModelAndView restoreSeoConf(@RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SEOCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除SEO设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (seoConfBizImpl.updateSeoConfFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除SEO设置到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除SEO设置到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除SEO设置对象异常！", e);
                mav.addObject(MSG, "还原删除SEO设置失败！");
            }
        }
        // 删除成功返回SEO设置列表
        return mav;
    }

    /**
     * 修改启用状态
     *
     * @param seoId
     *            seo设置id
     * @return
     */
    @RequestMapping("/updateSeoByUsedStatus")
    public ModelAndView updateSeoByUsedStatus(int seoId, final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            mav.setView(new RedirectView(INIT_SEOCONF));
            SeoConf sc = seoConfBizImpl.getSeoConfById(seoId);
            sc.setUsedStatus("1");
            seoConfBizImpl.updateSeoConf(sc);

            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改SEO启用状态", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("修改SEO启用状态错误：=>" ,e);
        }
        return mav;
    }

    /**
     * 查询SEO设置
     *
     * @param deleteStatus
     *            对象删除标识
     * @param seoTitle
     *            设置方案标题
     * @param seoCode
     *            设置方案代码
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/querySeoConf")
    public ModelAndView querySeoConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = SEOTITLE, required = false, defaultValue = "") final String seoTitle,
            @RequestParam(value = SEOCODE, required = false, defaultValue = "") final String seoCode, final PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (seoTitle != null && seoTitle.trim().length() != 0) {
            param.put(SEOTITLE, seoTitle);
            queryParam.put(SEOTITLE, seoTitle);
            queryParam.put("searchField", SEOTITLE);
            queryParam.put("searchValue", seoTitle);
            queryParam.put("searchName", "方案标题");
        }
        if (seoCode != null && seoCode.trim().length() != 0) {
            param.put(SEOCODE, seoCode);
            queryParam.put(SEOCODE, seoCode);
            queryParam.put("searchField", SEOCODE);
            queryParam.put("searchValue", seoCode);
            queryParam.put("searchName", "方案代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SEOCONF_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询SEO设置
            mav.addObject("pageBean", seoConfBizImpl.getSeoConfByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询SEO设置信息异常！",e);
        }
        return mav;
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
                LOGGER.error("获得字典缓存异常，字典名：" + dep, e);
            }
        }
    }

    /**
     * 获得SEO设置
     *
     * @return SEO设置对象
     */
    public final SeoConf getSeoConf() {
        return seoConf;
    }

    /**
     * 自动注入赋值SEO设置对象
     *
     * @param seoConf
     *            SEO设置对象
     */
    @Resource(name = SEOCONF)
    public final void setSeoConf(final SeoConf seoConf) {
        this.seoConf = seoConf;
    }

    /**
     * 获得SEO设置业务对象
     *
     * @return SEO设置对象业务对象
     */
    public final ISeoConfBiz getSeoConfBizImpl() {
        return seoConfBizImpl;
    }

    /**
     * 自动注入赋值SEO设置对象
     *
     * @param seoConfBizImpl
     *            SEO设置对象
     */
    @Resource(name = "seoConfBizImpl")
    public final void setSeoConfBizImpl(final ISeoConfBiz seoConfBizImpl) {
        this.seoConfBizImpl = seoConfBizImpl;
    }

    /**
     * 获得字典缓存业务对象
     *
     * @return 字典缓存业务对象
     */
    public final IDictionarysCache getDictionarysCache() {
        return dictionarysCache;
    }

    /**
     * 自动注入赋值字典缓存业务对象
     *
     * @param dictionarysCache
     *            字典缓存业务对象
     */
    @Resource(name = "dictionarysCache")
    public final void setDictionarysCache(final IDictionarysCache dictionarysCache) {
        this.dictionarysCache = dictionarysCache;
    }
}
