package com.ningpai.system.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.AreaPackage;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IAreaPackageBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 地区设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
@Controller("areaPackageController")
public class AreaPackageController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            AreaPackageController.class);
    /** 地区设置 */
    private AreaPackage areaPackage;
    /** 地区设置业务类 */
    private IAreaPackageBiz areaPackageBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 地区设置列表初始化URL */
    private static final String INIT_AREAPACKAGE = "initAreaPackage.htm";
    /** 添加地区设置JSP页面 */
    private static final String ADD_AREAPACKAGE_JSP = "jsp/system/area/areapackage_add";
    /** 地区设置列表JSP页面 */
    private static final String AREAPACKAGE_LIST_JSP = "jsp/system/area/areapackage_list";
    /** 更新地区设置JSP页面 */
    private static final String UPDATE_AREAPACKAGE_JSP = "jsp/system/area/areapackage_update";
    /** 查询地区设置JSP页面 */
    private static final String READ_AREAPACKAGE_JSP = "jsp/system/area/areapackage_read";

    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String AREAPACKAGE = "areaPackage";

    private static final String IDS = "ids";

    private static final String AREA = "area";

    /**
     * 初始化地区设置列表
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
    @RequestMapping("/initAreaPackage")
    public ModelAndView initAreaPackage(
            PageBean pageBean,
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        //设置url
        pageBean.setUrl(INIT_AREAPACKAGE);
        //初始化mav
        ModelAndView mav = new ModelAndView();
        //设置视图名称
        mav.setViewName(AREAPACKAGE_LIST_JSP);
        //初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        //删除状态
        pa.put(DELETESTATUS, deleteStatus);
        //获取pb
        mav.addObject("pageBean",
                areaPackageBizImpl.getAreaPackageByField(pa, pageBean));
        //设置删除状态
        mav.addObject(DELETESTATUS, deleteStatus);
        //设置消息
        mav.addObject(MSG, request.getAttribute(MSG));
        //设置消息
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
        //设置删除状态为1
        pa.put(DELETESTATUS, 1);
        try {
            //获得删除对象的数量
            return areaPackageBizImpl.getAreaPackageByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开地区设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddAreaPackagePage")
    public ModelAndView openAddAreaPackagePage(HttpServletRequest request) {
        //初始化mav
        ModelAndView mav = new ModelAndView();
        //视图名称
        mav.setViewName(ADD_AREAPACKAGE_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存地区设置
     * 
     * @param areaPackage
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addAreaPackage")
    public ModelAndView addAreaPackage(AreaPackage areaPackage,
            HttpServletRequest request) {
        //初始化mav
        ModelAndView mav = new ModelAndView();
        try {
            //设置插入时间
            areaPackage.setInsertDate(new Date());
            //设置添加人id
            areaPackage.setInsertId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            //设置删除状态
            areaPackage.setDeleteStatus(0);
            // 添加地区设置
            boolean flag = areaPackageBizImpl.saveAreaPackage(areaPackage);
            if (flag) {//是否添加成功
                //设置添加成功的信息
                mav.addObject(MSG, "保存地区设置成功！");
            } else {
                //设置添加失败的信息
                mav.addObject(MSG, "保存地区设置失败！");
                return backAddPage(areaPackage, mav);
            }
            mav.setView(new RedirectView(INIT_AREAPACKAGE));
        } catch (Exception e) {
            LOGGER.error("保存地区设置对象异常！", e);
            mav.addObject(MSG, "保存地区设置失败！");
            return backAddPage(areaPackage, mav);
        }
        // 返回地区设置列表
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
    private ModelAndView backAddPage(AreaPackage areaPackage, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        //添加货币信息
        mav.addObject(AREAPACKAGE, areaPackage);
        //设置视图名称
        mav.setViewName(ADD_AREAPACKAGE_JSP);
        return mav;
    }

    /**
     * 打开地区设置修改页面
     * 
     * @param id
     *            地区设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateAreaPackagePage")
    public ModelAndView openUpdateAreaPackagePage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改地区设置
            mav.addObject(AREAPACKAGE,
                    areaPackageBizImpl.getAreaPackageById(id));
            //设置视图名称
            mav.setViewName(UPDATE_AREAPACKAGE_JSP);
        } catch (Exception e) {
            LOGGER.error("加载地区设置对象失败！", e);
            //设置信息
            mav.addObject(MSG, "加载地区设置对象失败！");
            // 返回地区设置列表
            mav.setView(new RedirectView(INIT_AREAPACKAGE));
            return mav;
        }
        return mav;
    }

    /**
     * 根据主键查询地区包
     * 
     * @param areaPackageId
     *            地区包主键id
     * @return 地区包信息
     */
    @RequestMapping("/selectAreaPackageById")
    @ResponseBody
    public AreaPackage selectAreaPackageById(Integer areaPackageId) {
        return areaPackageBizImpl.getAreaPackageById(areaPackageId);
    }

    /**
     * 修改地区设置
     * 
     * @param areaPackage
     *            地区设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateAreaPackage")
    public ModelAndView updateAreaPackage(AreaPackage areaPackage,
            HttpServletRequest request) {
        //初始化mav
        ModelAndView mav = new ModelAndView();
        try {
            //设置修改时间
            areaPackage.setModifyDate(new Date());
            //设置修改人
            areaPackage.setModifyId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 修改地区设置
            if (areaPackageBizImpl.updateAreaPackage(areaPackage) >= 1) {
                //设置修改地区成功信息
                mav.addObject(MSG, "修改地区设置成功！");
            } else {
                //设置修改地区失败信息
                mav.addObject(MSG, "修改地区设置失败！");
                return backUpdatePage(areaPackage, mav);
            }
            mav.setView(new RedirectView(INIT_AREAPACKAGE));
        } catch (Exception e) {
            LOGGER.error("修改地区设置对象异常！", e);
            mav.addObject(MSG, "修改地区设置失败！");
            return backUpdatePage(areaPackage, mav);
        }
        // 返回地区设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param areaPackage
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(AreaPackage areaPackage,
            ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        //货币信息
        mav.addObject(AREAPACKAGE, areaPackage);
        //设置视图名称
        mav.setViewName(UPDATE_AREAPACKAGE_JSP);
        return mav;
    }

    /**
     * 查看地区设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            地区设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readAreaPackage")
    public ModelAndView readAreaPackage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam("id") int id) {
        //初始化mav
        ModelAndView mav = new ModelAndView();
        //设置删除状态
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询地区设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "地区设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_AREAPACKAGE));
        } else {
            mav.setViewName(READ_AREAPACKAGE_JSP);
            mav.addObject(AREAPACKAGE,
                    areaPackageBizImpl.getAreaPackageById(id));
        }
        return mav;
    }

    /**
     * 删除地区设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            地区设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteAreaPackage")
    public ModelAndView deleteAreaPackage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_AREAPACKAGE));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除地区设置操作！");
            return mav;
        } else {

            // 删除地区设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (areaPackageBizImpl.deleteAreaPackage(ids) >= 1) {
                        mav.addObject(MSG, "删除地区设置成功！");
                    } else {
                        mav.addObject(MSG, "删除地区设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    //设置删除状态为1
                    pa.put(DELETESTATUS, 1);
                    //设置ids
                    pa.put(IDS, ids);
                    if (areaPackageBizImpl.updateAreaPackageFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除地区设置到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除地区设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除地区设置对象异常！", e);
                mav.addObject(MSG, "删除地区设置失败！");
            }
        }
        // 删除成功返回地区设置列表
        return mav;
    }

    /**
     * 还原删除地区设置
     * 
     * @param ids
     *            地区设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreAreaPackage")
    public ModelAndView restoreAreaPackage(@RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_AREAPACKAGE));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除地区设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                //设置删除状态
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (areaPackageBizImpl.updateAreaPackageFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除地区设置到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除地区设置到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除地区设置对象异常！", e);
                mav.addObject(MSG, "还原删除地区设置失败！");
            }
        }
        // 删除成功返回地区设置列表
        return mav;
    }

    /**
     * 根据主键删除地区包
     * 
     * @param areaPackageId
     *            地区包id
     * @return 地区包列表页面
     */
    @RequestMapping("/deleteAreaPackageOne")
    public ModelAndView deleteAreaPackageOne(Integer areaPackageId) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_AREAPACKAGE));
        //删除地区包
        areaPackageBizImpl.deleteById(areaPackageId);
        return mav;
    }

    /**
     * 批量删除地区包
     * 
     * @param areaPackageIds
     *            地区包id
     * @return 地区包列表页面
     */
    @RequestMapping("/deleteAreaPackageBatch")
    public ModelAndView deleteAreaPackageBatch(Integer[] areaPackageIds) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_AREAPACKAGE));
        //批量删除地区包
        for (Integer areaPackageId : areaPackageIds) {
            areaPackageBizImpl.deleteById(areaPackageId);
        }
        return mav;
    }

    /**
     * 查询地区设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param area
     *            地区设置
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryAreaPackage")
    public ModelAndView queryAreaPackage(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
            @RequestParam(value = AREA, required = false, defaultValue = "") String area,
            PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        //判断地区是否为空
        if (area != null && area.trim().length() != 0) {
            //param中设置地区
            param.put(AREA, area);
            //queryParam中设置地区
            queryParam.put(AREA, area);
            //queryParam中设置搜索字段
            queryParam.put("searchField", AREA);
            //queryParam中设置搜索值
            queryParam.put("searchValue", area);
            //queryParam中设置搜索名称
            queryParam.put("searchName", "地区设置");
        }
        //初始化mav
        ModelAndView mav = new ModelAndView();
        //设置视图名称
        mav.setViewName(AREAPACKAGE_LIST_JSP);
        //视图中添加map
        mav.addObject("queryParam", queryParam);
        //param中添加删除状态
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询地区设置
            mav.addObject("pageBean",
                    areaPackageBizImpl.getAreaPackageByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询地区设置信息异常！",e);
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
     * 获得地区设置
     * 
     * @return 地区设置对象
     */
    public AreaPackage getAreaPackage() {
        return areaPackage;
    }

    /**
     * 自动注入赋值地区设置对象
     * 
     * @param areaPackage
     *            地区设置对象
     */
    @Resource(name = AREAPACKAGE)
    public void setAreaPackage(AreaPackage areaPackage) {
        this.areaPackage = areaPackage;
    }

    /**
     * 获得地区设置业务对象
     * 
     * @return 地区设置对象业务对象
     */
    public IAreaPackageBiz getAreaPackageBizImpl() {
        return areaPackageBizImpl;
    }

    /**
     * 自动注入赋值地区设置对象
     * 
     * @param currencyConfBizImpl
     *            地区设置对象
     */
    @Resource(name = "areaPackageBizImpl")
    public void setAreaPackageBizImpl(IAreaPackageBiz areaPackageBizImpl) {
        this.areaPackageBizImpl = areaPackageBizImpl;
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
