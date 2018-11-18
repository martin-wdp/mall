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

import com.ningpai.system.bean.ImageManager;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IImageManagerBiz;
import com.ningpai.system.service.ISysDictionaryBiz;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 图片管理Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 14:33:31
 * @version V1.0
 */
@Controller("imageManagerController")
public class ImageManagerController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            ImageManagerController.class);
    /** 图片管理 */
    private ImageManager imageManager;
    /** 图片管理业务类 */
    private IImageManagerBiz imageManagerBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] {};
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 系统字典业务类 */
    private ISysDictionaryBiz sysDictionaryBizImpl;
    /** 图标标签字典字典值 129 */
    public static final int DIC_IMAGE_TAG = 129;
    /** 图片管理列表初始化URL */
    private static final String INIT_IMAGEMANAGER = "initImageManager.htm";
    /** 添加图片管理JSP页面 */
    private static final String ADD_IMAGEMANAGER_JSP = "jsp/system/image/imagemanager_add";
    /** 图片管理列表JSP页面 */
    private static final String IMAGEMANAGER_LIST_JSP = "jsp/system/image/imagemanager_list";
    /** 更新图片管理JSP页面 */
    private static final String UPDATE_IMAGEMANAGER_JSP = "jsp/system/image/imagemanager_update";
    /** 查询图片管理JSP页面 */
    private static final String READ_IMAGEMANAGER_JSP = "jsp/system/image/imagemanager_read";
    /** 常量DELETESTATUS */
    private static final String DELETESTATUS = "deleteStatus";
    /** 常量MSG */
    private static final String MSG = "msg";
    /** 常量IMAGEMANAGER */
    private static final String IMAGEMANAGER = "imageManager";
    /** 常量IDS */
    private static final String IDS = "ids";
    /** 常量TITLE */
    private static final String TITLE = "title";
    /** 常量TAG */
    private static final String TAG = "tag";

    /**
     * 初始化图片管理列表
     * 
     * @param pageBean
     *            分页对象
     * @param deleteStatus
     *            对象删除标识
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/initImageManager")
    public final ModelAndView initImageManager(
            final PageBean pageBean,
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            final HttpServletRequest request) {
        // 设置URL
        pageBean.setUrl(INIT_IMAGEMANAGER);
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 设置视图名称
        mav.setViewName(IMAGEMANAGER_LIST_JSP);
        // 初始化map
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        // 获取图片管理
        mav.addObject("pageBean",
                imageManagerBizImpl.getImageManagerByField(pa, pageBean));
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
     * 获得删除对象的数量
     * 
     * @return 删除对象的数量
     */
    private int getDeleteObjectSize() {
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, 1);
        try {
            // 获得删除对象的数量
            return imageManagerBizImpl.getImageManagerByFieldTotal(pa);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开图片管理添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/openAddImageManagerPage")
    public final ModelAndView openAddImageManagerPage(
            final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_IMAGEMANAGER_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存图片管理
     * 
     * @param imageManager
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addImageManager")
    public final ModelAndView addImageManager(final ImageManager imageManager,
            final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 添加日期
            imageManager.setInsertDate(new Date());
            // 添加人
            imageManager.setInsertId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 删除状态
            imageManager.setDeleteStatus(0);
            // 添加图片管理
            boolean flag = imageManagerBizImpl.saveImageManager(imageManager);
            if (flag) {
                // 保存成功
                mav.addObject(MSG, "保存图片管理成功！");
            } else {
                // 保存失败
                mav.addObject(MSG, "保存图片管理失败！");
                return backAddPage(imageManager, mav);
            }
            mav.setView(new RedirectView(INIT_IMAGEMANAGER));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("保存图片管理对象异常！", e);
            mav.addObject(MSG, "保存图片管理失败！");
            return backAddPage(imageManager, mav);
        }
        // 返回图片管理列表
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
    private ModelAndView backAddPage(final ImageManager imageManager,
            final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(IMAGEMANAGER, imageManager);
        mav.setViewName(ADD_IMAGEMANAGER_JSP);
        return mav;
    }

    /**
     * 打开图片管理修改页面
     * 
     * @param id
     *            图片管理记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateImageManagerPage")
    public final ModelAndView openUpdateImageManagerPage(
            @RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改图片管理
            mav.addObject(IMAGEMANAGER,
                    imageManagerBizImpl.getImageManagerById(id));
            mav.setViewName(UPDATE_IMAGEMANAGER_JSP);
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("加载图片管理对象失败！", e);
            mav.addObject(MSG, "加载图片管理对象失败！");
            // 返回图片管理列表
            mav.setView(new RedirectView(INIT_IMAGEMANAGER));
            return mav;
        }
        return mav;
    }

    /**
     * 修改图片管理
     */
    /**
     * 修改图片管理
     * 
     * @param imageManager
     *            图片管理对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateImageManager")
    public final ModelAndView updateImageManager(
            final ImageManager imageManager, final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            // 修改日期
            imageManager.setModifyDate(new Date());
            // 修改人
            imageManager.setModifyId(((Long) request.getSession().getAttribute(
                    "loginUserId")).intValue());
            // 修改图片管理
            if (imageManagerBizImpl.updateImageManager(imageManager) >= 1) {
                // 修改成功
                mav.addObject(MSG, "修改图片管理成功！");
            } else {
                // 修改失败
                mav.addObject(MSG, "修改图片管理失败！");
                return backUpdatePage(imageManager, mav);
            }
            mav.setView(new RedirectView(INIT_IMAGEMANAGER));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("修改图片管理对象异常！", e);
            mav.addObject(MSG, "修改图片管理失败！");
            return backUpdatePage(imageManager, mav);
        }
        // 返回图片管理列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param imageManager
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(final ImageManager imageManager,
            final ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(IMAGEMANAGER, imageManager);
        mav.setViewName(UPDATE_IMAGEMANAGER_JSP);
        return mav;
    }

    /**
     * 查看图片管理
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            图片管理记录ID
     * @return 视图对象
     */
    @RequestMapping("/readImageManager")
    public final ModelAndView readImageManager(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam("id") final int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询图片管理id是否为0
        if (id == 0) {
            mav.addObject(MSG, "图片管理对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_IMAGEMANAGER));
        } else {
            mav.setViewName(READ_IMAGEMANAGER_JSP);
            mav.addObject(IMAGEMANAGER,
                    imageManagerBizImpl.getImageManagerById(id));
        }
        return mav;
    }

    /**
     * 删除图片管理
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            图片管理记录ID字符串
     * @return 视图对象
     */
    @RequestMapping("/deleteImageManager")
    public final ModelAndView deleteImageManager(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_IMAGEMANAGER));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除图片管理操作！");
            return mav;
        } else {

            // 删除图片管理
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (imageManagerBizImpl.deleteImageManager(ids) >= 1) {
                        // 删除图片管理成功
                        mav.addObject(MSG, "删除图片管理成功！");
                    } else {
                        // 删除图片管理失败
                        mav.addObject(MSG, "删除图片管理失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (imageManagerBizImpl.updateImageManagerFieldById(pa) >= 1) {
                        // 删除图片管理到回收站成功
                        mav.addObject(MSG, "删除图片管理到回收站成功！");
                    } else {
                        // 删除图片管理到回收站失败
                        mav.addObject(MSG, "删除图片管理到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                // 错误日志输出
                LOGGER.error("删除图片管理对象异常！", e);
                mav.addObject(MSG, "删除图片管理失败！");
            }
        }
        // 删除成功返回图片管理列表
        return mav;
    }

    /**
     * 还原删除图片管理
     * 
     * @param ids
     *            图片管理记录ID字符串
     * @return 视图对象
     */
    @RequestMapping("/restoreImageManager")
    public final ModelAndView restoreImageManager(
            @RequestParam(value = IDS) final String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_IMAGEMANAGER));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除图片管理操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (imageManagerBizImpl.updateImageManagerFieldById(pa) >= 1) {
                    // 删除图片管理到回收站成功
                    mav.addObject(MSG, "删除图片管理到回收站成功！");
                } else {
                    // 删除图片管理到回收站失败
                    mav.addObject(MSG, "删除图片管理到回收站失败！");
                }
            } catch (Exception e) {
                // 错误日志输出
                LOGGER.error("还原删除图片管理对象异常！", e);
                mav.addObject(MSG, "还原删除图片管理失败！");
            }
        }
        // 删除成功返回图片管理列表
        return mav;
    }

    /**
     * 查询图片管理
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param title
     *            图片标题
     * @param tag
     *            标签
     * @param pageBean
     *            分页对象
     * @return 视图对象
     */
    @RequestMapping("/queryImageManager")
    public final ModelAndView queryImageManager(
            @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") final int deleteStatus,
            @RequestParam(value = TITLE, required = false, defaultValue = "") final String title,
            @RequestParam(value = TAG, required = false, defaultValue = "0") final int tag,
            final PageBean pageBean) {
        // 保存数据库查询条件
        Map<String, Object> param = new HashMap<String, Object>();
        // 保存页面传入的查询值，以便在页面上显示
        Map<String, Object> queryParam = new HashMap<String, Object>();
        if (title != null && title.trim().length() != 0) {
            param.put(TITLE, title);
            queryParam.put(TITLE, title);
            queryParam.put("searchField", TITLE);
            queryParam.put("searchValue", title);
            queryParam.put("searchName", "图片标题");
        }
        if (tag != 0) {
            param.put(TAG, tag);
            queryParam.put(TAG, tag);
            queryParam.put("searchField", TAG);
            queryParam.put("searchValue", tag);
            queryParam.put("searchName", "标签");
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName(IMAGEMANAGER_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询图片管理
            mav.addObject("pageBean",
                    imageManagerBizImpl.getImageManagerByField(param, pageBean));
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("查询图片管理信息异常！",e);
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
    private void fullDictionarys(final ModelAndView mav) {
        // 循环需要的字典
        for (String dep : DICTIONARYS_LIST) {
            try {
                mav.addObject(dep, dictionarysCache.getDictionaryByName(dep));
            } catch (Exception e) {
                // 错误日志输出
                LOGGER.error("获得字典缓存异常，字典名：" + dep, e);
            }
        }
        // 获得图标标签字典
        PageBean pg = new PageBean();
        int pageSize = 200;
        pg.setPageSize(pageSize);
        Map<String, Object> pa = new HashMap<String, Object>(2);
        pa.put("parentId", DIC_IMAGE_TAG);
        pa.put("status", 1);
        try {
            mav.addObject("sysDicImageTag", sysDictionaryBizImpl
                    .getSysDictionaryByField(pa, pg).getList());
        } catch (Exception e) {
            // 错误日志输出
            LOGGER.error("获得货币设置字典异常！", e);
        }
    }

    /**
     * 获得图片管理
     * 
     * @return 图片管理对象
     */
    public final ImageManager getImageManager() {
        return imageManager;
    }

    /**
     * 自动注入赋值图片管理对象
     * 
     * @param imageManager
     *            图片管理对象
     */
    @Resource(name = IMAGEMANAGER)
    public final void setImageManager(final ImageManager imageManager) {
        this.imageManager = imageManager;
    }

    /**
     * 获得图片管理业务对象
     * 
     * @return 图片管理对象业务对象
     */
    public final IImageManagerBiz getImageManagerBizImpl() {
        return imageManagerBizImpl;
    }

    /**
     * 自动注入赋值图片管理对象
     * 
     * @param currencyConfBizImpl
     *            图片管理对象
     */
    @Resource(name = "imageManagerBizImpl")
    public final void setImageManagerBizImpl(
            final IImageManagerBiz imageManagerBizImpl) {
        this.imageManagerBizImpl = imageManagerBizImpl;
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
    public final void setDictionarysCache(
            final IDictionarysCache dictionarysCache) {
        this.dictionarysCache = dictionarysCache;
    }

    /**
     * 获得系统字典业务对象
     * 
     * @return 系统字典对象业务对象
     */
    public final ISysDictionaryBiz getSysDictionaryBizImpl() {
        return sysDictionaryBizImpl;
    }

    /**
     * 自动注入赋值系统字典对象
     * 
     * @param currencyConfBizImpl
     *            系统字典对象
     */
    @Resource(name = "sysDictionaryBizImpl")
    public final void setSysDictionaryBizImpl(
            final ISysDictionaryBiz sysDictionaryBizImpl) {
        this.sysDictionaryBizImpl = sysDictionaryBizImpl;
    }

}
