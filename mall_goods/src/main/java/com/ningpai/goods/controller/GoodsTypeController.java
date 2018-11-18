/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.GoodsType;
import com.ningpai.goods.bean.GoodsTypeExpandParamValue;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsTypeExpandParamVo;
import com.ningpai.goods.vo.GoodsTypeVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类型控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午11:10:41
 * @version 1.0
 */
@Controller
public class GoodsTypeController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsTypeController.class);

    private static final String LOGGERINFO1 = "-->类型名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    // 商品类型Service
    private GoodsTypeService goodsTypeService;
    // 商品扩展属性值Service
    private GoodsTypeExpandParamValueService goodsTypeExpandParamValueService;
    // 商品类型扩展属性Service
    private GoodsTypeExpandParamService goodsTypeExpandParamService;
    // 商品类型详细参数Service
    private GoodsTypeParamService goodsTypeParamService;
    // 商品类型关联品牌Service
    private GoodsTypeBrandService goodsTypeBrandService;
    // 商品类型关联类型Service
    private GoodsTypeSpecService goodsTypeSpecService;

    public GoodsTypeSpecService getGoodsTypeSpecService() {
        return goodsTypeSpecService;
    }

    @Resource(name = "GoodsTypeSpecService")
    public void setGoodsTypeSpecService(GoodsTypeSpecService goodsTypeSpecService) {
        this.goodsTypeSpecService = goodsTypeSpecService;
    }

    public GoodsTypeBrandService getGoodsTypeBrandService() {
        return goodsTypeBrandService;
    }

    @Resource(name = "GoodsTypeBrandService")
    public void setGoodsTypeBrandService(GoodsTypeBrandService goodsTypeBrandService) {
        this.goodsTypeBrandService = goodsTypeBrandService;
    }

    public GoodsTypeParamService getGoodsTypeParamService() {
        return goodsTypeParamService;
    }

    @Resource(name = "GoodsTypeParamService")
    public void setGoodsTypeParamService(GoodsTypeParamService goodsTypeParamService) {
        this.goodsTypeParamService = goodsTypeParamService;
    }

    public GoodsTypeExpandParamService getGoodsTypeExpandParamService() {
        return goodsTypeExpandParamService;
    }

    @Resource(name = "GoodsTypeExpandParamService")
    public void setGoodsTypeExpandParamService(GoodsTypeExpandParamService goodsTypeExpandParamService) {
        this.goodsTypeExpandParamService = goodsTypeExpandParamService;
    }

    public GoodsTypeService getGoodsTypeService() {
        return goodsTypeService;
    }

    @Resource(name = "GoodsTypeService")
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    public GoodsTypeExpandParamValueService getGoodsTypeExpandParamValueService() {
        return goodsTypeExpandParamValueService;
    }

    @Resource(name = "GoodsTypeExpandParamValueService")
    public void setGoodsTypeExpandParamValueService(GoodsTypeExpandParamValueService goodsTypeExpandParamValueService) {
        this.goodsTypeExpandParamValueService = goodsTypeExpandParamValueService;
    }

    /**
     * 根据PageBean 查询分页列表
     * 
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/findAllType")
    public ModelAndView findTypeList(HttpServletRequest request, HttpServletResponse response, PageBean pb, SelectBean selectBean) {
        // //打印日志
        LOGGER.info(ValueUtil.FINDALLTYPEINFO);
        // 参数设置到作用域中
        request.setAttribute("selectBean", selectBean);
        // 返回结果
        return new ModelAndView(PathUtil.GOODSTYPE, "pb", this.goodsTypeService.searchListByPageBean(pb, selectBean));
    }

    /**
     * 根据类型ID查询类型Vo
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的Vo {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    @RequestMapping("/findTypeVoByTypeId")
    @ResponseBody
    public GoodsTypeVo queryByTypeId(Long typeId) {
        // 根据类型id查询类型vo
        GoodsTypeVo goodsTypeVo = this.goodsTypeService.queryTypeVoByTypeId(typeId);
        // 非空验证 商品类型名称
        if (null != goodsTypeVo.getTypeName()) {
            // 打印日志
            LOGGER.info(ValueUtil.FINDTYPEVOBYTYPEIDINFO + ",类型名称为：" + goodsTypeVo.getTypeName());
        }
        // 返回结果
        return goodsTypeVo;
    }

    /**
     * 删除商品类型
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/delTypeByTypeId")
    public ModelAndView delType(Long typeId, HttpServletRequest request, SelectBean selectBean) {
        // 根据主键获取商品类型对象
        GoodsTypeVo goodsTypeVo = this.goodsTypeService.queryTypeVoByTypeId(typeId);
        // 执行删除操作，删除人名称应该是当前登录的用户名称，暂且设置为admin3
        this.goodsTypeService.delGoodsType(typeId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.DELTYPEBYTYPEIDINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsTypeVo.getTypeName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 重定向处理中文乱码
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("searchText", selectBean.getSearchText());
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLTYPECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)), modelMap);
    }

    /**
     * 批量删除商品类型
     * 
     * @param typeIds
     *            需要删除的商品类型的ID数组 {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDelType")
    public ModelAndView batchDelType(Long[] typeIds, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELTYPEINFO);
        // 执行批量删除 删除人名称应该是当前登陆的用户名称 暂且设置为admin
        this.goodsTypeService.batchDelType(typeIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELTYPEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLTYPECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 保存商品类型
     * 
     * @param goodsType
     *            商品类型实体 {@link com.ningpai.goods.bean.GoodsType}
     * @param request
     *            请求对象 获取请求中的参数
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/saveType")
    public ModelAndView saveType(@Valid GoodsType goodsType, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 打印日志
        LOGGER.info(ValueUtil.SAVETYPEINFO + ",保存的类型名称为:" + goodsType.getTypeName());
        // // 获取类型图片并上传
        // MultipartFile imageFile = request2.getFile("typeImage");
        // if (!imageFile.isEmpty()) {
        // goodsType.setTypeImg(UploadUtil.uploadFile(imageFile,
        // request).get("oldimg"));
        // }
        // 从请求中获得所有传递的参数
        String[] brandIds = request.getParameterValues(ValueUtil.BRANDIDS);
        String[] specIds = request.getParameterValues(ValueUtil.SPECIDS);
        String[] paramname = request.getParameterValues(ValueUtil.PARAMNAME);
        String[] paramnickname = request.getParameterValues(ValueUtil.PARAMNICKNAME);
        String[] expandnames = request.getParameterValues(ValueUtil.EXPANDNAMES);
        String[] expandnicknames = request.getParameterValues(ValueUtil.EXPANDNICKNAMES);
        String[] expandparamsort = request.getParameterValues(ValueUtil.EXPANDPARAMSORT);
        String[] expandparamisshow = request.getParameterValues("expandparamisshow");
        String[] expandvalues = request.getParameterValues(ValueUtil.EXPANDVALUES);
        // 由于方法限制参数个数，所以只能封装在map中传递
        Map<String, String[]> map = new HashMap<String, String[]>();
        try {
            map.put("brandIds", brandIds);
            map.put("specIds", specIds);
            map.put("paramname", paramname);
            map.put("paramnickname", paramnickname);
            map.put(ValueUtil.EXPANDNAMES, expandnames);
            map.put(ValueUtil.EXPANDNICKNAMES, expandnicknames);
            map.put(ValueUtil.EXPANDPARAMSORT, expandparamsort);
            map.put("expandparamisshow", expandparamisshow);
            map.put(ValueUtil.EXPANDVALUES, expandvalues);
            if (null != goodsType.getTypeName() && 1 == this.goodsTypeService.saveTypeAndParam(goodsType, map, (String) request.getSession().getAttribute(ValueUtil.NAME))) {
                LOGGER.info("新增商品类型成功！类型名称为" + goodsType.getTypeName());
            } else {
                this.goodsTypeService.saveTypeAndParam(goodsType, map, (String) request.getSession().getAttribute(ValueUtil.NAME));
            }
            // 传递到Service中处理
        } finally {
            // 把所有参数置空
            brandIds = null;
            specIds = null;
            paramname = null;
            paramnickname = null;
            expandnames = null;
            expandnicknames = null;
            expandparamsort = null;
            expandparamisshow = null;
            expandvalues = null;
            map = null;
        }
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.SAVETYPEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsType.getTypeName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLTYPECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据扩展属性ID 查询扩展属性值列表
     * 
     * @param paramId
     *            扩展属性ID {@link java.lang.Long}
     * @return 查询到的扩展属性值列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    @RequestMapping("/queryParamValueByParamId")
    @ResponseBody
    public List<GoodsTypeExpandParamValue> queryExpandParamValueByParamId(Long paramId) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPARAMVALUEBYPARAMIDINFO + "拓展属性ID是：" + paramId);
        // 返回结果
        return this.goodsTypeExpandParamValueService.queryParamValueByParamId(paramId);
    }

    /**
     * 更新商品类型扩展属性值
     * 
     * @param request
     *            请求参数
     */
    @RequestMapping("/updateExpandParamValue")
    @ResponseBody
    public int updateExpandParamValue(HttpServletRequest request) {
        LOGGER.info(ValueUtil.UPDATEEXPANDPARAMVALUEINFO);
        // 获取类型扩展属性ID并赋值
        String[] expandParamId = request.getParameterValues("expandParamId[]");
        String[] paramIds = request.getParameterValues("paramId[]");
        // 获取扩展属性值ID并赋值
        String[] expandParamValueId = request.getParameterValues("expandParamValueId[]");
        // 获取属性值删除标记并赋值
        String[] expandParamValueDelFlag = request.getParameterValues("expandParamValueDelFlag[]");
        // 获取属性值名称并赋值
        String[] expandParamValueName = request.getParameterValues("expandParamValueName[]");
        // 获取属性值排序并赋值
        String[] expandParamValueSort = request.getParameterValues("expandParamValueSort[]");
        boolean paramFlag = false;
        for (int i = 0; i < paramIds.length; i++) {
            if (Long.parseLong(paramIds[i]) > -1) {
                paramFlag = true;
                break;
            }
        }
        if (!paramFlag) {
            paramIds = expandParamId;
        }
        // 更新商品类型扩展属性值
        this.goodsTypeExpandParamValueService.updateParamValues(paramIds, expandParamValueId, expandParamValueDelFlag, expandParamValueName, expandParamValueSort, (String) request
                .getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEEXPANDPARAMVALUEINFO, (String) request.getSession()
                .getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return 1;
    }

    /**
     * 根据商品类型ID查询扩展参数
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的扩展属性的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsTypeExpandParamVo}
     */
    @RequestMapping("/queryParamListByTypeId")
    @ResponseBody
    public List<GoodsTypeExpandParamVo> queryParamListByTypeId(Long typeId) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPARAMLISTBYTYPEIDINFO + ",商品类型ID为：" + typeId);
        // 返回结果
        return this.goodsTypeExpandParamService.queryParamListByTypeId(typeId);
    }

    /**
     * 更新商品类型
     * 
     * @param goodsType
     *            商品类型 {@link com.ningpai.goods.bean.GoodsType}
     * @param request
     *            回话对象
     * @param pb
     *            记录列表页状态 页数
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/updateType")
    public ModelAndView updateType(@Valid GoodsType goodsType, HttpServletRequest request, PageBean pb, SelectBean selectBean) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATETYPEINFO);
        // // 获取类型图片
        // MultipartFile imageFile = request2.getFile("typeImage");
        // if (!imageFile.isEmpty()) {
        // goodsType.setTypeImg(UploadUtil.uploadFile(imageFile,
        // request).get("oldimg"));
        // }
        // 获取品牌id并赋值
        String[] brandList = request.getParameterValues("brandIds");
        // 获取规格id并赋值
        String[] specIds = request.getParameterValues("specIds");
        // 获取类型扩展属性ID并赋值
        String[] expandparamId = request.getParameterValues("expandparamId");
        // 获取类型扩展属性删除标记并赋值
        String[] expandparamDelflag = request.getParameterValues("expandparamDelflag");
        String[] expandnames = request.getParameterValues("expandnames");
        String[] expandnicknames = request.getParameterValues("expandnicknames");
        String[] expandparamIsshow = request.getParameterValues("expandparamIsshow");
        String[] expandparamsort = request.getParameterValues("expandparamsort");
        String[] typeParamId = request.getParameterValues("typeParamId");
        String[] paramDelflag = request.getParameterValues("paramDelflag");
        String[] paramname = request.getParameterValues("paramname");
        String[] paramnickname = request.getParameterValues("paramnickname");
        String[] expandvalues = request.getParameterValues(ValueUtil.EXPANDVALUES);
        // 更新商品类型基础表
        if (null != goodsType.getTypeName() && 1 == this.goodsTypeService.updateGoodsType(goodsType, (String) request.getSession().getAttribute(ValueUtil.NAME))) {
            LOGGER.info("更新商品类型【" + goodsType.getTypeName() + "】成功！");
        } else {
            this.goodsTypeService.updateGoodsType(goodsType, (String) request.getSession().getAttribute(ValueUtil.NAME));
        }
        // 封装扩展属性参数并进行操作
        Map<String, String[]> expandParamMap = new HashMap<String, String[]>();
        expandParamMap.put("expandparamId", expandparamId);
        expandParamMap.put("expandparamDelflag", expandparamDelflag);
        expandParamMap.put("expandnames", expandnames);
        expandParamMap.put("expandnicknames", expandnicknames);
        expandParamMap.put("expandparamIsshow", expandparamIsshow);
        expandParamMap.put("expandparamsort", expandparamsort);
        expandParamMap.put(ValueUtil.EXPANDVALUES, expandvalues);
        // 进行批量更新扩展属性操作
        this.goodsTypeExpandParamService.batchUpdateExpandParam((String) request.getSession().getAttribute(ValueUtil.NAME), goodsType.getTypeId(), expandParamMap, request);
        // 封装详细参数并进行操作
        this.goodsTypeParamService.batchUpateParam(goodsType.getTypeId(), "amin1", typeParamId, paramDelflag, paramname, paramnickname);
        // 执行更新关联品牌的操作
        this.goodsTypeBrandService.batchUpdateTypeBrand(goodsType.getTypeId(), (String) request.getSession().getAttribute(ValueUtil.NAME), brandList);
        // 执行更新关联规格的操作
        this.goodsTypeSpecService.batchUpdate(goodsType.getTypeId(), (String) request.getSession().getAttribute(ValueUtil.NAME), specIds);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.UPDATETYPEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + goodsType.getTypeName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 处理重定向中文乱码
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("searchText", selectBean.getSearchText());
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLTYPECONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + "&pageNo=" + pb.getPageNo()
                + "&pageSize=" + pb.getPageSize() + "&condition=" + 1), modelMap);
    }

    /**
     * 根据商品分类ID查询商品类型
     * 
     * @param catId
     *            分类ID {@link java.lang.Long}
     * @return 查询到的类型Vo
     */
    @RequestMapping("/queryTypeVoByCatId")
    @ResponseBody
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        // 根据商品分类ID查询商品类型
        GoodsTypeVo goodsTypeVo = this.goodsTypeService.queryTypeVoByCatId(catId);
        // 非空验证 商品类型名称
        if (null != goodsTypeVo.getTypeName()) {
            // 日志记录
            LOGGER.info(ValueUtil.QUERYTYPEVOBYCATIDINFO + ",类型名称为：" + goodsTypeVo.getTypeName());
        }
        // 返回结果
        return goodsTypeVo;
    }

    /**
     * 验证商品类型名称是否可用
     * 
     * @param typeName
     *            待验证的商品名称
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checkTypeName")
    @ResponseBody
    public boolean checkTypeName(String typeName) {
        // 验证商品类型名称是否可用
        if (this.goodsTypeService.checkTypeName(typeName)) {
            // 打印日志
            LOGGER.info("商品类型【" + this.goodsTypeService.checkTypeName(typeName) + "】不可用！");
        }
        // 返回结果
        return this.goodsTypeService.checkTypeName(typeName);
    }

    /**
     * 导出商品类型
     * 
     * @param response
     */
    @RequestMapping("exportGoodsType")
    public void exportGoodsType(HttpServletResponse response) {
        goodsTypeService.exportGoodsType(response);
    }

}
