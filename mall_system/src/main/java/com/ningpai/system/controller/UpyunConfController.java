package com.ningpai.system.controller;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.UpyunConf;
import com.ningpai.system.cache.IDictionarysCache;
import com.ningpai.system.service.IUpyunConfBiz;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 又拍云设置Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 15:44:09
 * @version V1.0
 */
@Controller("upyunConfController")
public class UpyunConfController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(UpyunConfController.class);

    /** 又拍云设置 */
    private UpyunConf upyunConf;
    /** 又拍云设置业务类 */
    private IUpyunConfBiz upyunConfBizImpl;
    /** 需要的字典集合 */
    private static final String[] DICTIONARYS_LIST = new String[] { "sysUpyunOpType", };
    /** 字典缓存 */
    private IDictionarysCache dictionarysCache;
    /** 又拍云设置列表初始化URL */
    private static final String INIT_UPYUNCONF = "initUpyunConf.htm";
    /** 添加又拍云设置JSP页面 */
    private static final String ADD_UPYUNCONF_JSP = "jsp/system/upyun/upyunconf_add";
    /** 又拍云设置列表JSP页面 */
    private static final String UPYUNCONF_LIST_JSP = "jsp/system/upyun/upyunconf_list";
    /** 更新又拍云设置JSP页面 */
    private static final String UPDATE_UPYUNCONF_JSP = "jsp/system/upyun/upyunconf_update";
    /** 查询又拍云设置JSP页面 */
    private static final String READ_UPYUNCONF_JSP = "jsp/system/upyun/upyunconf_read";

    private static final String DELETESTATUS = "deleteStatus";

    private static final String MSG = "msg";

    private static final String UPYUNCONF = "upyunConf";

    private static final String IDS = "ids";

    private static final String TITLE = "title";

    private static final String SETCODE = "setCode";

    private static final String JS_SCRIPT = "')</script>";



    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /**
     * 初始化又拍云设置列表
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
    @RequestMapping("/initUpyunConf")
    public ModelAndView initUpyunConf(PageBean pageBean, @RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") Integer deleteStatus, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        fullDictionarys(mav);
        mav.setViewName(UPDATE_UPYUNCONF_JSP);
        Map<String, Object> pa = new HashMap<String, Object>(1);
        pa.put(DELETESTATUS, deleteStatus);
        mav.addObject("upyunConf", upyunConfBizImpl.getUpyunConfByField(pa, pageBean).getList().get(0));
        mav.addObject("flag", 0);
        // 填充字典信息

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
            return upyunConfBizImpl.getUpyunConfByFieldTotal(pa);
        } catch (Exception e) {
            LOGGER.error("获得删除对象的数量异常！", e);
        }
        return 0;
    }

    /**
     * 打开又拍云设置添加页面
     * 
     * @param request
     *            请求对象
     * @return 视图对象
     * 
     */
    @RequestMapping("/openAddUpyunConfPage")
    public ModelAndView openAddUpyunConfPage(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(ADD_UPYUNCONF_JSP);
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 保存又拍云设置
     * 
     * @param upyunConf
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addUpyunConf")
    public ModelAndView addUpyunConf(@Valid UpyunConf upyunConf, BindingResult bindingResult, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_UPYUNCONF));
        }
        ModelAndView mav = new ModelAndView();
        try {
            upyunConf.setInsertDate(new Date());
            upyunConf.setInsertId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            upyunConf.setDeleteStatus(0);
            // 添加又拍云设置
            boolean flag = upyunConfBizImpl.saveUpyunConf(upyunConf);
            if (flag) {
                mav.addObject(MSG, "保存又拍云设置成功！");
            } else {
                mav.addObject(MSG, "保存又拍云设置失败！");
                return backAddPage(upyunConf, mav);
            }
            mav.setView(new RedirectView(INIT_UPYUNCONF));
        } catch (Exception e) {
            LOGGER.error("保存又拍云设置对象异常！", e);
            mav.addObject(MSG, "保存又拍云设置失败！");
            return backAddPage(upyunConf, mav);
        }
        // 返回又拍云设置列表
        return mav;
    }

    /**
     * 返还到添加页面
     * 
     * @param upyunConf
     *            又拍云设置对象
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backAddPage(UpyunConf upyunConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(UPYUNCONF, upyunConf);
        mav.setViewName(ADD_UPYUNCONF_JSP);
        return mav;
    }

    /**
     * 打开又拍云设置修改页面
     * 
     * @param id
     *            又拍云设置记录ID
     * @return 视图对象
     */
    @RequestMapping("/openUpdateUpyunConfPage")
    public ModelAndView openUpdateUpyunConfPage(@RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        try {
            // 填充字典信息
            fullDictionarys(mav);
            // 获得修改又拍云设置
            mav.addObject(UPYUNCONF, upyunConfBizImpl.getUpyunConfById(id));
            mav.addObject("flag", 1);
            mav.setViewName(UPDATE_UPYUNCONF_JSP);
        } catch (Exception e) {
            LOGGER.error("加载又拍云设置对象失败！", e);
            mav.addObject(MSG, "加载又拍云设置对象失败！");
            // 返回又拍云设置列表
            mav.setView(new RedirectView(INIT_UPYUNCONF));
            return mav;
        }
        return mav;
    }

    /**
     * 修改又拍云设置
     * 
     * @param upyunConf
     *            又拍云设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/updateUpyunConf")
    public ModelAndView updateUpyunConf(@Valid UpyunConf upyunConf, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(INIT_UPYUNCONF));
        }
        ModelAndView mav = new ModelAndView();
        try {
            upyunConf.setModifyDate(new Date());
            upyunConf.setModifyId(((Long) request.getSession().getAttribute("loginUserId")).intValue());
            // 修改又拍云设置
            if (upyunConfBizImpl.updateUpyunConf(upyunConf) >= 1) {
                mav.addObject(MSG, "修改又拍云设置成功！");
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改又拍云设置", request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
            } else {
                mav.addObject(MSG, "修改又拍云设置失败！");
                return backUpdatePage(upyunConf, mav);
            }
            mav.addObject("flag", 0);
            mav.setView(new RedirectView(INIT_UPYUNCONF));
        } catch (Exception e) {
            LOGGER.error("修改又拍云设置错误：=>",e);
            mav.addObject(MSG, "修改又拍云设置失败！");
            return backUpdatePage(upyunConf, mav);
        }
        // 返回又拍云设置列表
        return mav;
    }

    /**
     * 返还到修改页面
     * 
     * @param upyunConf
     *            货币设置
     * @param mav
     *            视图对象
     * @return mav 视图对象
     */
    private ModelAndView backUpdatePage(UpyunConf upyunConf, ModelAndView mav) {
        // 填充字典信息
        fullDictionarys(mav);
        mav.addObject(UPYUNCONF, upyunConf);
        mav.setViewName(UPDATE_UPYUNCONF_JSP);
        return mav;
    }

    /**
     * 查看又拍云设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param id
     *            又拍云设置记录ID
     * @return 视图对象
     * 
     */
    @RequestMapping("/readUpyunConf")
    public ModelAndView readUpyunConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam("id") int id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject(DELETESTATUS, deleteStatus);
        // 判断查询又拍云设置id是否为0
        if (id == 0) {
            mav.addObject(MSG, "又拍云设置对象id为空，无法执行查询！");
            mav.setView(new RedirectView(INIT_UPYUNCONF));
        } else {
            mav.setViewName(READ_UPYUNCONF_JSP);
            mav.addObject(UPYUNCONF, upyunConfBizImpl.getUpyunConfById(id));
        }
        // 填充字典信息
        fullDictionarys(mav);
        return mav;
    }

    /**
     * 删除又拍云设置
     * 
     * @param deleteStatus
     *            对象删除标识
     * @param ids
     *            又拍云设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/deleteUpyunConf")
    public ModelAndView deleteUpyunConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus, @RequestParam(value = IDS) String ids) {

        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_UPYUNCONF));
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行删除又拍云设置操作！");
            return mav;
        } else {
            // 删除又拍云设置
            try {
                // 物理删除
                if (deleteStatus == 1) {
                    if (upyunConfBizImpl.deleteUpyunConf(ids) >= 1) {
                        mav.addObject(MSG, "删除又拍云设置成功！");
                    } else {
                        mav.addObject(MSG, "删除又拍云设置失败！");
                    }
                    // 逻辑删除
                } else {
                    Map<String, Object> pa = new HashMap<String, Object>(2);
                    pa.put(DELETESTATUS, 1);
                    pa.put(IDS, ids);
                    if (upyunConfBizImpl.updateUpyunConfFieldById(pa) >= 1) {
                        mav.addObject(MSG, "删除又拍云设置到回收站成功！");
                    } else {
                        mav.addObject(MSG, "删除又拍云设置到回收站失败！");
                    }
                }
                mav.addObject(DELETESTATUS, deleteStatus);

            } catch (Exception e) {
                LOGGER.error("删除又拍云设置对象异常！", e);
                mav.addObject(MSG, "删除又拍云设置失败！");
            }
        }
        // 删除成功返回又拍云设置列表
        return mav;
    }

    /**
     * 还原删除又拍云设置
     * 
     * @param ids
     *            又拍云设置记录ID字符串
     * @return 视图对象
     * 
     */
    @RequestMapping("/restoreUpyunConf")
    public ModelAndView restoreUpyunConf(@RequestParam(value = IDS) String ids) {
        ModelAndView mav = new ModelAndView();
        mav.setView(new RedirectView(INIT_UPYUNCONF));
        mav.addObject(DELETESTATUS, 1);
        // 判断查询对象id集合字符是否有效
        if (ids == null || ids.trim().length() == 0) {
            mav.addObject(MSG, "对象id为空，无法执行还原删除又拍云设置操作！");
            return mav;
        } else {
            try {
                Map<String, Object> pa = new HashMap<String, Object>(2);
                pa.put(DELETESTATUS, 0);
                pa.put(IDS, ids);
                if (upyunConfBizImpl.updateUpyunConfFieldById(pa) >= 1) {
                    mav.addObject(MSG, "删除又拍云设置到回收站成功！");
                } else {
                    mav.addObject(MSG, "删除又拍云设置到回收站失败！");
                }
            } catch (Exception e) {
                LOGGER.error("还原删除又拍云设置对象异常！", e);
                mav.addObject(MSG, "还原删除又拍云设置失败！");
            }
        }
        // 删除成功返回又拍云设置列表
        return mav;
    }

    /**
     * 查询又拍云设置
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
    @RequestMapping("/queryUpyunConf")
    public ModelAndView queryUpyunConf(@RequestParam(value = DELETESTATUS, required = false, defaultValue = "0") int deleteStatus,
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
        mav.setViewName(UPYUNCONF_LIST_JSP);
        mav.addObject("queryParam", queryParam);
        param.put(DELETESTATUS, deleteStatus);
        mav.addObject(DELETESTATUS, deleteStatus);
        try {
            // 查询又拍云设置
            mav.addObject("pageBean", upyunConfBizImpl.getUpyunConfByField(param, pageBean));
        } catch (Exception e) {
            LOGGER.error("查询又拍云设置信息异常！");
        }
        return mav;
    }

    /**
     * 测试又拍云
     *
     * @param request
     *            HttpServletRequest
     * @param response
     *            HttpServletResponse
     * @param multipartRequest
     *            MultipartRequest
     */
    @RequestMapping("testUpyun")
    public void previewUpyun(HttpServletRequest request, HttpServletResponse
    response, MultipartRequest multipartRequest) {
        PrintWriter out = null;
        try {
        out = response.getWriter();
        Map<String, String> picMap =
        UploadUtil.testUpYun(multipartRequest.getFile("picFile"), request);
        out.println("<script>parent.testCallback('200','" + picMap.get("oldimg")
        + "," + picMap.get("352") + "," + picMap.get("160") + "," +
        picMap.get("56") + JS_SCRIPT);
        } catch (IOException e) {
        // 401，用户名密码不正确
        if (e.getLocalizedMessage().contains("401")) {
        out.println("<script>parent.testCallback('401','" +
        e.getLocalizedMessage() + JS_SCRIPT);
        } else if (e.getLocalizedMessage().contains("404")) {
        // 404，尺寸设置不正确
        out.println("<script>parent.testCallback('404','" +
        e.getLocalizedMessage() + JS_SCRIPT);
        } else {
        // 其他异常
        out.println("<script>parent.testCallback('500','" +
        e.getLocalizedMessage() + JS_SCRIPT);
        }
        LOGGER.error("又拍云测试异常", e);
        }

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
     * 获得又拍云设置
     * 
     * @return 又拍云设置对象
     */
    public UpyunConf getUpyunConf() {
        return upyunConf;
    }

    /**
     * 自动注入赋值又拍云设置对象
     * 
     * @param upyunConf
     *            又拍云设置对象
     */
    @Resource(name = UPYUNCONF)
    public void setUpyunConf(UpyunConf upyunConf) {
        this.upyunConf = upyunConf;
    }

    /**
     * 获得又拍云设置业务对象
     * 
     * @return 又拍云设置对象业务对象
     */
    public IUpyunConfBiz getUpyunConfBizImpl() {
        return upyunConfBizImpl;
    }

    /**
     * 自动注入赋值又拍云设置对象
     * 
     * @param upyunConfBizImpl
     *            又拍云设置对象
     */
    @Resource(name = "upyunConfBizImpl")
    public void setUpyunConfBizImpl(IUpyunConfBiz upyunConfBizImpl) {
        this.upyunConfBizImpl = upyunConfBizImpl;
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
