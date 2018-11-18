package com.ningpai.system.controller;

import com.ningpai.system.bean.StatisticsCode;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IStatisticsCodeBiz;
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
 * 统计代码Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:03:45
 * @version V1.0
 */
@Controller
public class StatisticsCodeController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(StatisticsCodeController.class);
    /** 统计代码 */
    private StatisticsCode statisticsCode;
    /** 统计代码业务类 */
    private IStatisticsCodeBiz statisticsCodeBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 统计代码列表初始化URL */
    private static final String INIT_STATISTICSCODE = "initStatisticsCode.htm";
    /** 添加统计代码JSP页面 */
    private static final String ADD_STATISTICSCODE_JSP = "jsp/system/sta/statisticscode_add";
    /** 统计代码列表JSP页面 */
    private static final String STATISTICSCODE_LIST_JSP = "jsp/system/sta/statisticscode_list";
    /** 更新统计代码JSP页面 */
    private static final String UPDATE_STATISTICSCODE_JSP = "jsp/system/sta/statisticscode_update";
    /** 查询统计代码JSP页面 */
    private static final String READ_STATISTICSCODE_JSP = "jsp/system/sta/statisticscode_read";

    /** 对象删除标识 */
    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String STATISTICSCODE = "statisticsCode";

    private static final String IDS = "ids";

    private static final String STATITLE = "staTitle";

    private static final String MODULE = "module";

    private static final String STASTYLE = "staStyle";

    private static final String SEARCHFIELD = "searchField";

    private static final String SEARCHVALUE = "searchValue";

    private static final String SEARCHNAME = "searchName";

    /**
     * 初始化统计代码列表
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
    @RequestMapping("/initStatisticsCode")
    public ModelAndView initStatisticsCode(PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") Integer deleteStatus, HttpServletRequest request) {
        pageBean.setUrl(INIT_STATISTICSCODE);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(STATISTICSCODE_LIST_JSP);
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        mav.addObject("pageBean", statisticsCodeBizImpl.getStatisticsCodeByField(pa, pageBean));
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
            return statisticsCodeBizImpl.getStatisticsCodeByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开统计代码添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddStatisticsCodePage")
    public ModelAndView openAddStatisticsCodePage(final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_STATISTICSCODE_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存统计代码
     * 
     * @param statisticsCode
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addStatisticsCode")
    public ModelAndView addStatisticsCode(@Valid final StatisticsCode statisticsCode, BindingResult bindingResult, final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_STATISTICSCODE));
        }
        ModelAndView mav = new ModelAndView();
        try {
            statisticsCode.setInsertDate(new Date());
            statisticsCode.setInsertId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            statisticsCode.setDeleteStatus(0);
            // 添加统计代码
            boolean flag = statisticsCodeBizImpl.saveStatisticsCode(statisticsCode);
            if (flag) {
                mav.addObject(MSG, "保存统计代码成功！");
            } else {
                mav.addObject(MSG, "保存统计代码失败！");
                return backAddPage(statisticsCode, mav);
            }
            mav.setView(new RedirectView(INIT_STATISTICSCODE));
        } catch (Exception e) {
            LOGGER.error("保存统计代码对象异常！", e);
            mav.addObject(MSG, "保存统计代码失败！");
            return backAddPage(statisticsCode, mav);
        }
        // 返回统计代码列表
        return mav;
    }

    /**
     * 返还到添加页面
     * 
     * @param statisticsCode
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(final StatisticsCode statisticsCode, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(STATISTICSCODE, statisticsCode);
        mav.setViewName(ADD_STATISTICSCODE_JSP);
        return mav;
    }

    /**
     * 打开统计代码修改页面
     * 
     * @param id
     *            统计代码记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateStatisticsCodePage")
    public ModelAndView openUpdateStatisticsCodePage(@RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改统计代码
            mav.addObject(STATISTICSCODE, statisticsCodeBizImpl.getStatisticsCodeById(id));
            mav.setViewName(UPDATE_STATISTICSCODE_JSP);
        } catch (Exception e) {
            LOGGER.error("加载统计代码对象失败！", e);
            mav.addObject(MSG, "加载统计代码对象失败！");
            // 返回统计代码列表
            mav.setView(new RedirectView(INIT_STATISTICSCODE));
            return mav;
        }
        return mav;
    }

    /**
     * ajax获取编辑的统计代码信息
     * 
     * @param id
     * @return
     */
    @RequestMapping("/openUpdateStatisticsCodePageAjax")
    @ResponseBody
    public Map<String, Object> openUpdateStatisticsCodePageAjax(@RequestParam("id") final int id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("statisticsCode", statisticsCodeBizImpl.getStatisticsCodeById(id));
        return map;
    }

    /**
     * 修改统计代码
     * 
     * @param statisticsCode
     *            统计代码对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateStatisticsCode")
    public ModelAndView updateStatisticsCode(@Valid final StatisticsCode statisticsCode, BindingResult bindingResult, final HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_STATISTICSCODE));
        }
        ModelAndView mav = new ModelAndView();
        try {
            statisticsCode.setModifyDate(new Date());
            statisticsCode.setModifyId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            // 修改统计代码
            if (statisticsCodeBizImpl.updateStatisticsCode(statisticsCode) >= 1) {
                mav.addObject(MSG, "修改统计代码成功！");
            } else {
                mav.addObject(MSG, "修改统计代码失败！");
                return backUpdatePage(statisticsCode, mav);
            }
            mav.setView(new RedirectView(INIT_STATISTICSCODE));
        } catch (Exception e) {
            LOGGER.error("修改统计代码对象异常！", e);
            mav.addObject(MSG, "修改统计代码失败！");
            return backUpdatePage(statisticsCode, mav);
        }
        // 返回统计代码列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param statisticsCode
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(final StatisticsCode statisticsCode, final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(STATISTICSCODE, statisticsCode);
        mav.setViewName(UPDATE_STATISTICSCODE_JSP);
        return mav;
    }

    /**
     * 查看统计代码
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            统计代码记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readStatisticsCode")
    public ModelAndView readStatisticsCode(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus, @RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询统计代码id是否为0
        if (id == 0) {
            mav.addObject(MSG, "统计代码对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_STATISTICSCODE));
        } else {
            mav.setViewName(READ_STATISTICSCODE_JSP);
            mav.addObject(STATISTICSCODE, statisticsCodeBizImpl.getStatisticsCodeById(id));
        }
        return mav;
    }

    /**
     * 删除统计代码
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            统计代码记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteStatisticsCode")
    public ModelAndView deleteStatisticsCode(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_STATISTICSCODE));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除统计代码操作！");
            return mav;
        } else {

            // 删除统计代码
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (statisticsCodeBizImpl.deleteStatisticsCode(ids) >= 1) {
                        mav.addObject(MSG, "删除统计代码成功！");
                    } else {
                        mav.addObject(MSG, "删除统计代码失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (statisticsCodeBizImpl.updateStatisticsCodeFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除统计代码到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除统计代码到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除统计代码对象异常！", e);
                mav.addObject(MSG, "删除统计代码失败！");
            }
        }
        // 删除成功返回统计代码列表
        return mav;
    }

    /**
     * 还原删除统计代码
     * 
     * @param ids
     *            统计代码记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreStatisticsCode")
    public ModelAndView restoreStatisticsCode(@RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_STATISTICSCODE));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除统计代码操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (statisticsCodeBizImpl.updateStatisticsCodeFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除统计代码到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除统计代码到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除统计代码对象异常！", e);
                mav.addObject(MSG, "还原删除统计代码失败！");
            }
        }
        // 删除成功返回统计代码列表
        return mav;
    }

    /**
     * 查询统计代码
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param staTitle
     *            统计方案标题
     * @param staStyle
     *            统计方案代码
     * @param module
     *            模块名
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryStatisticsCode")
    public ModelAndView queryStatisticsCode(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = STATITLE, required = false, defaultValue = "") final String staTitle,
            @RequestParam(value = STASTYLE, required = false, defaultValue = "") final String staStyle,
            @RequestParam(value = MODULE, required = false, defaultValue = "") final String module, final PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (staTitle != null && staTitle.trim().length() != 0) {
            param.put(STATITLE, staTitle);
            queryParam.put(STATITLE, staTitle);
            queryParam.put(SEARCHFIELD, STATITLE);
            queryParam.put(SEARCHVALUE, staTitle);
            queryParam.put(SEARCHNAME, "统计方案标题");
        }
        if (staStyle != null && staStyle.trim().length() != 0) {
            param.put(STASTYLE, staStyle);
            queryParam.put(STASTYLE, staStyle);
            queryParam.put(SEARCHFIELD, STASTYLE);
            queryParam.put(SEARCHVALUE, staStyle);
            queryParam.put(SEARCHNAME, "统计方案代码");
        }
        if (module != null && module.trim().length() != 0) {
            param.put(MODULE, module);
            queryParam.put(MODULE, module);
            queryParam.put(SEARCHFIELD, MODULE);
            queryParam.put(SEARCHVALUE, module);
            queryParam.put(SEARCHNAME, "模块名");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(STATISTICSCODE_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询统计代码
            mav.addObject("pageBean", statisticsCodeBizImpl.getStatisticsCodeByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询统计代码信息异常！",e);
        }
        return mav;
    }

    /**
     * 修改统计代码启用状态
     * 
     * @param scodeId
     * @return
     */
    @RequestMapping("/changeUserdStatusForScode")
    public ModelAndView changeUserdStatusForScode(Long scodeId) {
        try {
            if (this.statisticsCodeBizImpl.changeUserdStatus(scodeId)) {
                LOGGER.debug("===============修改统计代码启用状态成功");
            } else {
                LOGGER.debug("===============修改统计代码启用状态失败");
            }
        } catch (Exception e) {
            LOGGER.error("修改统计代码启用状态错误：=>" ,e);
        }
        return new ModelAndView(new RedirectView(INIT_STATISTICSCODE));
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
     * 获得统计代码
     * 
     * @return 统计代码对象
     */
    public StatisticsCode getStatisticsCode() {
        return statisticsCode;
    }

    /**
     * 自动注入赋值统计代码对象
     * 
     * @param statisticsCode
     *            统计代码对象
     */
    @Resource(name = STATISTICSCODE)
    public void setStatisticsCode(StatisticsCode statisticsCode) {
        this.statisticsCode = statisticsCode;
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

    public IStatisticsCodeBiz getStatisticsCodeBizImpl() {
        return statisticsCodeBizImpl;
    }

    @Resource(name = "statisticsCodeBizImpl")
    public void setStatisticsCodeBizImpl(IStatisticsCodeBiz statisticsCodeBizImpl) {
        this.statisticsCodeBizImpl = statisticsCodeBizImpl;
    }

}
