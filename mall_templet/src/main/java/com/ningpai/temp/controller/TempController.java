/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.bean.TempToken;
import com.ningpai.temp.service.TempService;
import com.ningpai.temp.service.TempTokenService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器-模板
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月4日下午4:03:06
 */
@Controller
public class TempController {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(TempController.class);

    private static final String VARNAME = ",用户名:";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final Long TEMPTYPE1 = 137L;

    private static final Long TEMPTYPE2 = 139L;

    private static final Long TEMPTYPE3 = 141L;

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /* 业务逻辑层依赖 */
    @Resource(name = "TempService")
    private TempService tempService;

    /** 文章栏目业务接口 */
    @Resource(name = "InformationTypeService")
    private InformationTypeService infoTypeService;

    @Resource(name = "InfoOPTagService")
    private InfoOPTagService infoOPTagService;

    @Resource(name = "TempTokenService")
    private TempTokenService tempTokenService;

    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;

    @Resource(name = "ChannelGoodsCateService")
    private GoodsCateService goodsCateService;

    /**
     * 
     * 按模板类型查询模板信息
     * 
     * @return
     */
    @RequestMapping("/queryTempByType")
    public ModelAndView queryTempByType(HttpServletRequest request) {
        // 设置导航
        MenuSession.sessionMenu(request);
        Map<String, Object> map = new HashMap<String, Object>();
        // 根据模板类型查询模板
        // 首页模板
        List<SysTemp> indexList = tempService.querySystempByType(TEMPTYPE1);
        // 频道页模板
        List<SysTemp> channelList = tempService.querySystempByType(TEMPTYPE2);
        List<SysTemp> singleList = tempService.querySystempByType(TEMPTYPE3);
        // 查询站点信息
        BasicSet bs = basicSetService.findBasicSet();

        map.put("indexList", indexList);
        map.put("channelList", channelList);
        map.put("singleList", singleList);
        return new ModelAndView("jsp/temp/temp_list", "map", map).addObject("basicset", bs);
    }

    /**
     * 查看模板信息
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/showTempInfo")
    public ModelAndView showTempInfo(Long tempId, HttpServletRequest request, String status) {
        // 设置导航
        MenuSession.sessionMenu(request);
        // temp:模板信息
        // infoTypeList：新闻广告
        // opTagList活动
        return new ModelAndView("jsp/temp/show_temp", "temp", tempService.getSystempById(tempId)).addObject(ConstantUtil.CSRFTOKEN, request.getParameter(ConstantUtil.CSRFTOKEN))
                .addObject("infoTypeList", infoTypeService.selectInfoTypeByAttrForTemp()).addObject("opTagList", infoOPTagService.findAllInfoOPTag(tempId.toString()))
                .addObject("brand", goodsBrandService.queryAllBrand()).addObject("cate", goodsCateService.queryAllFirstGradeGoosCate()).addObject("status", status);
    }

    /**
     * 可视化操作视图
     * 
     * @param tempId
     *            模板id
     * @return
     */
    @RequestMapping("selectTempView")
    public ModelAndView selectTempView(Long tempId) {
        Map<String, Object> topmap = new HashMap<String, Object>();
        topmap.put("systembase", basicSetService.findBasicSet());
        return new ModelAndView("jsp/temp/temp_view").addObject("tempId", tempId).addObject("topmap", topmap);
    }

    /**
     * 跳到修改模板信息页面
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/toUpdateTempInfo")
    public ModelAndView toUpdateTempInfo(Long tempId) {
        return new ModelAndView("jsp/temp/update_temp", "temp", tempService.getSystempById(tempId));
    }

    /**
     * 跳到修改频道模板信息页面
     *
     * @param tempId
     * @return
     */
    @RequestMapping("/toUpdateChannelInfo")
    public ModelAndView toUpdateChannelInfo(Long tempId) {
        return new ModelAndView("jsp/channel/update_channel", "temp", tempService.getSystempById(tempId));
    }

    /**
     * 修改模板信息
     * 
     * @param temp
     * @return
     */
    @RequestMapping("/updateTempInfo")
    public void updateTempInfo(MultipartHttpServletRequest request, HttpServletResponse response, SysTemp temp) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            MultipartFile imageFile = request.getFile("imgSrc");
            if (!imageFile.isEmpty()) {
                temp.setTempImageUrl(UploadUtil.uploadFile(imageFile, request).get("oldimg"));
            }
            int result = this.tempService.updateSystemp(temp);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改模板信息", request.getSession().getAttribute(OPERAPATH) + VARNAME + customerName);
            if (result > 0) {
                out.append("<script>parent.callReload()</script>");
            }
        } catch (IOException e) {
            LOGGER.error("",e);
        }

    }

    /**
     * 模板编译图片上传回调
     */
    @RequestMapping("/uploadChannelShowImg")
    public void uploadChannelShowImg(MultipartHttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 待文件上传区
            MultipartFile file = request.getFile("imgSrc");
            // 若有数据则上传文件
            if (!file.isEmpty()) {
                String picPath = UploadUtil.uploadFileOne(file, request);
                out.append("<script>parent.callback('" + picPath + "')</script>");
            }
        } catch (IOException e) {
            LOGGER.error("",e);
        }
    }

    /**
     * 修改模板新闻公告
     * 
     * @param temp
     * @return
     */
    @RequestMapping("/updateTempInfoType")
    public ModelAndView updateTempInfoType(@Valid SysTemp temp, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = null;
        // 获取返回结果
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("setTemp.htm?tempId=" + temp.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 修改分类导航设置
        this.tempService.updateSystemp(temp);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改模板新闻公告", request.getSession().getAttribute(OPERAPATH) + VARNAME + customerName);
        pw = response.getWriter();
        pw.append("<script>parent.updateOk()</script>");
        pw.flush();
        return null;
    }

    /**
     * 修改模板新闻公告
     *
     * @param temp
     * @return
     */
    @RequestMapping("/updatetempinfotypeajax")
    @ResponseBody
    public int updateTempInfoTypeAjax(@Valid SysTemp temp, BindingResult bindingResult, HttpServletRequest request) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 如果有异常
            return 0;
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改分类导航", request.getSession().getAttribute(OPERAPATH) + VARNAME + customerName);
        // 返回结果
        return this.tempService.updateSystemp(temp);
    }

    /**
     * 配置首页模板内容
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/setTemp")
    public ModelAndView setTemp(Long tempId) {
        // 获取可发布文章的文章类型
        List<InformationTypeVo> infoTypeList = infoTypeService.selectInfoTypeByAttrForTemp();
        List<InfoOPTag> opTagList = infoOPTagService.findAllInfoOPTag(tempId.toString());
        return new ModelAndView("jsp/temp/set_temp", "temp", tempService.getSystempById(tempId)).addObject("infoTypeList", infoTypeList).addObject("opTagList", opTagList);
    }

    /**
     * 设置首页模板为当前使用
     * 
     * @param tempId
     * @return
     */
    @RequestMapping("/setUsedStatus")
    public ModelAndView setUsedStatus(Long tempId, HttpServletRequest request) {
        tempService.setUserd(tempId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改当前使用模板", request.getSession().getAttribute(OPERAPATH) + VARNAME + customerName);
        return new ModelAndView(new RedirectView("queryTempByType.htm"));
    }

    /**
     * 刷新
     * 
     * @param token
     */
    @RequestMapping("/refurbish")
    public ModelAndView refurbish(TempToken token) {
        tempTokenService.updateTokenValue(token);
        return new ModelAndView(new RedirectView("queryTempByType.htm"));
    }

    /**
     * 获取模板详情（ajax）
     * 
     * @param tempId
     *            模板id
     * @return
     */
    @RequestMapping("/getsystempdetailbyid")
    @ResponseBody
    public SysTemp getSysTempDetailById(Long tempId) {
        // 获取模板信息并返回
        return tempService.getSystempById(tempId);
    }

}
