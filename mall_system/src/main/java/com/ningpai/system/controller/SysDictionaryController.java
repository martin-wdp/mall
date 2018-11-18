package com.ningpai.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.SysDictionary;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.ISysDictionaryBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 系统字典Controller
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @Description:
 * @since 2014-03-20 11:03:23
 */
@Controller("sysDictionaryController")
public class SysDictionaryController extends BaseController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(
            SysDictionaryController.class);
    /**
     * 系统字典
     */
    private SysDictionary sysDictionary;
    /**
     * 系统字典业务类
     */
    private ISysDictionaryBiz sysDictionaryBizImpl;
    /**
     * 需要的字典集合
     */
    private static final String[] DICTIONARYS_LIST = new String[]{};
    /**
     * 字典缓存
     */
    private IDictionarysCache dictionarysCache;
    /**
     * 系统字典列表初始化URL
     */
    private static final String INIT_SYSDICTIONARY = "initSysDictionary.htm";
    /**
     * 添加系统字典JSP页面
     */
    private static final String ADD_SYSDICTIONARY_JSP = "jsp/system/dic/sysdictionary_add";
    /**
     * 系统字典列表JSP页面
     */
    private static final String SYSDICTIONARY_LIST_JSP = "jsp/system/dic/sysdictionary_list";
    /**
     * 更新系统字典JSP页面
     */
    private static final String UPDATE_SYSDICTIONARY_JSP = "jsp/system/dic/sysdictionary_update";
    /**
     * 查询系统字典JSP页面
     */
    private static final String READ_SYSDICTIONARY_JSP = "jsp/system/dic/sysdictionary_read";

    private static final String PARENTID = "parentId";

    private static final String MSG = "msg";

    private static final String NAME = "name";

    private static final String CODE = "code";

    /**
     * 初始化系统字典列表
     *
     * @param pageBean 分页对象
     * @param request  请求对象
     * @param parentId 父节点id
     * @return 视图对象
     */
    @RequestMapping("/initSysDictionary")
    public ModelAndView initSysDictionary(
            PageBean pageBean,
            HttpServletRequest request,
            @RequestParam(value = PARENTID, required = false, defaultValue = "0") int parentId) {
        pageBean.setUrl(INIT_SYSDICTIONARY + "?parentId=" + parentId);
        ModelAndView mav = new ModelAndView();
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(PARENTID, parentId);
        mav.setViewName(SYSDICTIONARY_LIST_JSP);
        mav.addObject("pageBean",
                sysDictionaryBizImpl.getSysDictionaryByField(pa, pageBean));
        mav.addObject(MSG, request.getAttribute(MSG));
        mav.addObject(MSG, request.getParameter(MSG));
        mav.addObject(PARENTID, parentId);
        if (parentId != 0) {
            mav.addObject("parentInfo", getParentInfo(parentId));
        }
        return mav;
    }

    /**
     * 打开系统字典添加页面
     *
     * @param request  请求对象
     * @param parentId 父节点id
     * @return 视图对象
     */
    @RequestMapping("/openAddSysDictionaryPage")
    public ModelAndView openAddSysDictionaryPage(HttpServletRequest request,
                                                 @RequestParam(value = PARENTID) int parentId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_SYSDICTIONARY_JSP);
        mav.addObject(PARENTID, parentId);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 获得父字典信息
     *
     * @param parentId 父节点id
     * @return 系统字典对象
     */
    private SysDictionary getParentInfo(int parentId) {
        return sysDictionaryBizImpl.getSysDictionaryById(parentId);
    }

    /**
     * 保存系统字典
     *
     * @param sysDictionary 货币设置对象
     * @param request       请求对象
     * @return 视图对象
     */
    @RequestMapping("/addSysDictionary")
    public ModelAndView addSysDictionary(SysDictionary sysDictionary,
                                         HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(PARENTID, sysDictionary.getParentId());
        try {
            // 添加会员
            boolean flag = sysDictionaryBizImpl
                    .saveSysDictionary(sysDictionary);
            if (flag) {
                mav.addObject(MSG, "保存系统字典成功！");
            } else {
                mav.addObject(MSG, "保存系统字典失败！");
            }
            mav.setView(new RedirectView(INIT_SYSDICTIONARY));
        } catch (Exception e) {
            LOGGER.error("保存系统字典对象异常！", e);
            mav.addObject(MSG, "保存系统字典失败！");
        }
        // 返回系统字典列表
        return mav;
    }

    /**
     * 打开系统字典修改页面
     *
     * @param id 系统字典记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateSysDictionaryPage")
    public ModelAndView openUpdateSysDictionaryPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改系统字典
            mav.addObject("sysDictionary",
                    sysDictionaryBizImpl.getSysDictionaryById(id));
            mav.setViewName(UPDATE_SYSDICTIONARY_JSP);
        } catch (Exception e) {
            LOGGER.error("加载系统字典对象失败！", e);
            mav.addObject(MSG, "加载系统字典对象失败！");
            // 返回系统字典列表
            mav.setView(new RedirectView(INIT_SYSDICTIONARY));
            return mav;
        }
        return mav;
    }

    /**
     * 修改系统字典
     *
     * @param sysDictionary 系统字典对象
     * @param request       请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateSysDictionary")
    public ModelAndView updateSysDictionary(SysDictionary sysDictionary,
                                            HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 修改会员
            if (sysDictionaryBizImpl.updateSysDictionary(sysDictionary) >= 1) {
                mav.addObject(MSG, "修改系统字典成功！");
            } else {
                mav.addObject(MSG, "修改系统字典失败！");
            }
            mav.setView(new RedirectView(INIT_SYSDICTIONARY));
        } catch (Exception e) {
            LOGGER.error("修改系统字典对象异常！", e);
            mav.addObject(MSG, "修改系统字典失败！");
        }
        // 返回系统字典列表
        return mav;
    }

    /**
     * 查看系统字典
     *
     * @param id 系统字典记录ID
     * @return 视图对象
     */
    @RequestMapping("/readSysDictionary")
    public ModelAndView readSysDictionary(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        // 判断查询系统字典id是否为0
        if (id == 0) {
            mav.addObject(MSG, "系统字典对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_SYSDICTIONARY));
        } else {
            mav.setViewName(READ_SYSDICTIONARY_JSP);
            mav.addObject("sysDictionary",
                    sysDictionaryBizImpl.getSysDictionaryById(id));
        }
        return mav;
    }

    /**
     * 删除系统字典
     *
     * @param ids      系统字典记录ID字符串
     * @param parentId 父节点id
     * @return 视图对象
     */
    @RequestMapping("/deleteSysDictionary")
    public ModelAndView deleteSysDictionary(
            @RequestParam(value = "ids") String ids,
            @RequestParam(value = PARENTID) int parentId) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_SYSDICTIONARY));
        mav.addObject(PARENTID, parentId);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除系统字典操作！");
            return mav;
        } else {
            // 删除系统字典
            try {
                // 如果是父字典，判断是否有子字典，否则不让删除
                if (parentId == 0) {
                    Map<String, Object> pa = new HashMap<String, Object>(1);
                    pa.put(PARENTID, parentId);
                    pa.put("status", 1);
                    PageBean pg = new PageBean();
                    int pageSize = 1000;
                    pg.setPageSize(pageSize);
                    List<Object> childList = sysDictionaryBizImpl
                            .getSysDictionaryByField(pa, pg).getList();
                    if (childList != null && !childList.isEmpty()) {
                        mav.addObject(MSG, "您删除的此字典信息，存在子字典信息，所以禁止删除！");
                        return mav;
                    }
                }
                if (sysDictionaryBizImpl.deleteSysDictionary(ids) >= 1) {
                    mav.addObject(MSG, "删除系统字典成功！");
                } else {
                    mav.addObject(MSG, "删除系统字典失败！");
                }
            } catch (Exception e) {
                LOGGER.error("删除系统字典对象异常！", e);
                mav.addObject(MSG, "删除系统字典失败！");
            }
        }
        // 删除成功返回系统字典列表
        return mav;
    }

    /**
     * 查询系统字典
     *
     * @param name     字典名称
     * @param code     字典代码
     * @param pageBean 分页对象
     * @param parentId 父节点id
     * @return 视图对象
     */
    @RequestMapping("/querySysDictionary")
    public ModelAndView querySysDictionary(
            @RequestParam(value = NAME, required = false, defaultValue = "") String name,
            @RequestParam(value = CODE, required = false, defaultValue = "") String code,
            PageBean pageBean, @RequestParam(value = PARENTID) int parentId) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        param.put(PARENTID, parentId);
        if (name != null && name.trim().length() != 0) {
            param.put(NAME, name);
            queryParam.put(NAME, name);
            queryParam.put("searchField", NAME);
            queryParam.put("searchValue", name);
            queryParam.put("searchName", "字典名称");
        }
        if (code != null && code.trim().length() != 0) {
            param.put(CODE, code);
            queryParam.put(CODE, code);
            queryParam.put("searchField", CODE);
            queryParam.put("searchValue", code);
            queryParam.put("searchName", "字典代码");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(SYSDICTIONARY_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        try {
            // 查询系统字典
            mav.addObject("pageBean", sysDictionaryBizImpl
                    .getSysDictionaryByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询系统字典信息异常！",e);
        }
        return mav;
    }

    /**
     * 填充字典信息
     *
     * @param mav 视图对象
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
     * 获得系统字典
     *
     * @return 系统字典对象
     */
    public SysDictionary getSysDictionary() {
        return sysDictionary;
    }

    /**
     * 自动注入赋值系统字典对象
     *
     * @param sysDictionary 系统字典对象
     */
    @Resource(name = "sysDictionary")
    public void setSysDictionary(SysDictionary sysDictionary) {
        this.sysDictionary = sysDictionary;
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
     * @param currencyConfBizImpl 系统字典对象
     */
    @Resource(name = "sysDictionaryBizImpl")
    public void setSysDictionaryBizImpl(ISysDictionaryBiz sysDictionaryBizImpl) {
        this.sysDictionaryBizImpl = sysDictionaryBizImpl;
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
     * @param dictionarysCache 字典缓存业务对象
     */
    @Resource(name = "dictionarysCache")
    public void setDictionarysCache(IDictionarysCache dictionarysCache) {
        this.dictionarysCache = dictionarysCache;
    }

}
