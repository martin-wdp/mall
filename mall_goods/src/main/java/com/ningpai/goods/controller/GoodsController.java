/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.excel.ExportGoodsList;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;
import org.springframework.stereotype.Controller;
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
 * 商品控制器
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月24日 下午5:00:29
 */
@Controller
public class GoodsController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GoodsController.class);

    private static final String SEARCHBEAN = "searchBean";
    private static final String ONECATELIST = "oneCateList";
    private static final String PAGENO = "pageNo";
    private static final String VARNAME = "】-->用户名：";
    private static final String VARPRODUCT = ",商品名称为:";
    private static final String VARNAME2 = ",用户名：";
    private static final String GOODSIMAGE = "goodsImage";
    private static final String EXPANDPARAMID = "expandParamId";
    private static final String EXPANDPARAMVALUE = "expandparamValue";
    private static final String PARAMID = "paramId";
    private static final String PARAMVALUE = "paramValue";
    private static final String ABOUTGOODSID = "aboutGoodsId";
    private static final String PAGENO2 = "&pageNo=";
    private static final String PAGESIZE = "&pageSize=";
    private static final String PRODUCTIDSPECIDSPECDETAILID = "productIdSpecIdSpecdetailId";
    private static final String BRANDLIST = "brandList";
    private static final String TAGLIST = "tagList";

    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    // 商品的Service
    private GoodsService goodsService;
    // 商品品牌Servicesearchproduct2
    private GoodsBrandService goodsBrandService;
    // 商品标签Service
    private GoodsTagService goodsTagService;
    // 商品分类Service
    private GoodsCateService goodsCateService;
    // 仓库
    private ProductWareService productWareService;
    // 审核开关
    private GetOnOffMapper getOnOffMapper;
    // 货品信息Service
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    // 库房Service
    private WareHouseService wareHouseService;
    // 商品索引
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;

    @Resource(name = "GoodsRelatedGoodsService")
    private GoodsRelatedGoodsService relatedGoodsService;

    /**
     * 商品导入
     */
    @Resource(name = "GoodsImportService")
    private GoodsImportService goodsImportService;

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 检查收藏商品下架
     *
     * @param goodsId
     *            商品id号
     * @return
     */
    @RequestMapping("/checkgoods")
    @ResponseBody
    public int checkgoods(int goodsId) {
        // 调用service层方法查询结果并赋值给变量flag
        String flag = goodsService.selectCheckGoods(goodsId);
        /*
         * 判读变量flag是否等于1 如果相等就返回1 如果不相等就返回0
         */
        if ("1".equals(flag)) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 分页查询列表
     *
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/findAllGoods")
    public ModelAndView test(PageBean pb, HttpServletRequest request, GoodsSearchBean searchBean, Long oneCateId, Long twoCateId) {
        if (searchBean.getQueryStatus() == null) {
            searchBean.setQueryStatus("1");
        }
        // 设置url
        pb.setUrl(PathUtil.ALLGOODSCONTROLLER);
        // 日志
        LOGGER.info(ValueUtil.FINDALLGOODSINFO);
        // 查询结果并赋值给商品分类集合
        List<GoodsCate> oneCateList = goodsCateService.querySonCateByParentIdAndName(0L, null);

        //2015-12-19 wuyanbo 注释
        /*ModelAndView mav = new ModelAndView(PathUtil.GOODSLIST, "pb", this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean)).addObject(SEARCHBEAN, searchBean)
                .addObject(ValueUtil.BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(ValueUtil.TAGLIST, this.goodsTagService.queryAllTag());*/

        ModelAndView mav = new ModelAndView(PathUtil.GOODSLIST, "pb", this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean, request)).addObject(SEARCHBEAN, searchBean)
                .addObject(ValueUtil.BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(ValueUtil.TAGLIST, this.goodsTagService.queryAllTag());

        mav.addObject(ONECATELIST, oneCateList);
        // 判读商品分类id是否为空并且是否为null
        if (oneCateId != null) {
            // 查询结果并赋值给商品分类集合
            List<GoodsCate> twoCateList = goodsCateService.querySonCateByParentIdAndName(oneCateId, null);
            mav.addObject("twoCateList", twoCateList);
            // 判读商品分类id是否为空并且是否为null
            if (twoCateId != null) {
                // 查询结果并赋值给商品分类集合
                List<GoodsCate> threeCateList = goodsCateService.querySonCateByParentIdAndName(twoCateId, null);
                mav.addObject("threeCateList", threeCateList);
            }
        }
        mav.addObject("oneCateId", oneCateId);
        mav.addObject("twoCateId", twoCateId);
        mav.addObject("bset", basicSetService.findBasicSet().getBsetAddress());
        return mav;
    }

    /**
     * 分页查询列表
     *
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/findAllGoodsisthird")
    public ModelAndView testisthird(PageBean pb, HttpServletRequest request) {
        // 设置PageBean的㘦路径
        pb.setUrl(PathUtil.ALLGOODSCONTROLLERISTHIRD);
        // 查询分类列表并赋值给商品分类集合
        List<GoodsCate> oneCateList = goodsCateService.querySonCateByParentIdAndName(0L, null);
        // 获取页面中的当前页码
        String pageNo = request.getParameter(PAGENO);
        // 判断页码是否为null
        // 如果页面为null就赋值为1
        if (pageNo == null) {
            pageNo = "1";
        }
        // 把PageBean的开始页码为获取的页码
        pb.setFirstPageNo(Integer.parseInt(pageNo));
        ModelAndView mav = new ModelAndView(PathUtil.GOODSLISTISTHIRD, "pb", this.goodsService.queryListVo(pb, "1"))
                .addObject(ValueUtil.BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(ValueUtil.TAGLIST, this.goodsTagService.queryAllTag())
                .addObject("bset", basicSetService.findBasicSet().getBsetAddress());

        mav.addObject(ONECATELIST, oneCateList);

        // 打印日志
        LOGGER.info(ValueUtil.FINDALLGOODSINFO);
        // 返回视图
        return mav;
    }

    /**
     * 查询所有商品
     */
    @RequestMapping("/searchGoodsAll")
    @ResponseBody
    public PageBean searchGoodsAll(PageBean pb) {
        // 打印日志
        LOGGER.info(ValueUtil.SEARCHGOODSALLINFO);
        // 查询结果并返回
        return goodsService.queryListVo(pb, "0");
    }

    /**
     * 查询所有第三方商品
     */
    @RequestMapping("/searchGoodsAllisthird")
    @ResponseBody
    public PageBean searchGoodsAllisthird(PageBean pb) {
        // 打印日志
        LOGGER.info(ValueUtil.SEARCHGOODSALLINFO);
        // 查询结果并返回
        return goodsService.queryListVo(pb, "1");
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/delGoods")
    public ModelAndView delGoods(Long goodsId, HttpServletRequest request) {
        // 删除商品信息
        int result = this.goodsService.delGoods(goodsId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 根据ID获取单个的商品信息
        GoodsMoifiedVo goodsMoifiedVo = this.goodsService.queryModeifiedVoByGoodsId(goodsId);

        Long[] goodsIds = new Long[] { goodsId };
        List<Long> sList = goodsProductService.selectInfoIdList(goodsIds);
        // 删除索引
        goodsElasticSearchService.batchDeleteGoodsIndexToEs(sList);
        // 非空验证 商品名称 是否删除成功
        if (null != goodsMoifiedVo.getGoodsName() && 1 == result) {
            // 获取当前登录的用户名
            String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
            // 操作日志
            OperaLogUtil.addOperaLog(request, name, "删除商品信息", "删除商品信息-->商品名称【" + goodsMoifiedVo.getGoodsName() + VARNAME + name);
            // 日志记录
            LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goodsMoifiedVo.getGoodsName());
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/delGoodsisthird")
    public ModelAndView delGoodsisthird(Long goodsId, HttpServletRequest request) {
        // 获取管理员名称
        int result = this.goodsService.delGoods(goodsId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 根据ID获取单个的商品信息
        GoodsMoifiedVo goodsMoifiedVo = this.goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 非空验证 商品名称 是否删除成功
        if (null != goodsMoifiedVo.getGoodsName() && 1 == result) {
            // 获取当前登录的用户名
            String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
            // 操作日志
            OperaLogUtil.addOperaLog(request, name, "删除商品信息", "删除商品信息-->商品名称【" + goodsMoifiedVo.getGoodsName() + VARNAME + name);
            // 日志记录
            LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goodsMoifiedVo.getGoodsName());
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLERISTHIRD + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品
     *
     * @param goodsIds
     *            需要删除的商品的ID的集合
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDelGoods")
    public ModelAndView batchDelGoods(Long[] goodsIds, HttpServletRequest request) {
        // 获取参数传入批量删除方法中并执行批量删除方法
        this.goodsService.batchDel(goodsIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 打印操作日志
        LOGGER.info(ValueUtil.BATCHDELGOODSINFO);
        // 查询所有货品
        List<Long> sList = goodsProductService.selectInfoIdList(goodsIds);
        // 删除索引
        goodsElasticSearchService.batchDeleteGoodsIndexToEs(sList);
        // 批量删除购物车里的商品
        this.goodsProductService.delShoppingGoodsByGoodsInfoIds(sList);
        // 记录操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELGOODSINFO,
                request.getSession().getAttribute(ValueUtil.OPERAPATH) + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除商品
     *
     * @param goodsIds
     *            需要删除的商品的ID的集合
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDelGoodsisthird")
    public ModelAndView batchDelGoodsisthird(Long[] goodsIds, HttpServletRequest request) {
        // 获取参数并把参数传入批量删除方法中执行批量删除操作
        this.goodsService.batchDel(goodsIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELGOODSINFO);
        // 记录当前用户的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELGOODSINFO,
                request.getSession().getAttribute(ValueUtil.OPERAPATH) + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLERISTHIRD + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 保存商品
     *
     * @param goods
     *            保存的商品的实体
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/saveGoods")
    public ModelAndView saveGoods(@Valid Goods goods, HttpServletRequest request, MultipartHttpServletRequest request2) {

        try {
            // 获取页面中aboutGoodsId的值并赋值给aboutGoodsId数组
            String[] aboutGoodsId = request.getParameterValues(ValueUtil.ABOUTGOODSID);
            // 获取页面中goods_tags的值并赋值给tags数组
            String[] tags = request.getParameterValues("goods_tags");
            // 获取页面中expandParamId的值并赋值给expandParamIds数组
            String[] expandParamIds = request.getParameterValues(ValueUtil.EXPANDPARAMID);
            // 获取页面中expandparamValue的值并赋值给expandparamValues数组
            String[] expandParamValues = request.getParameterValues(ValueUtil.EXPANDPARAMVALUE);
            // 获取页面中paramId的值并赋值给paramIds数组
            String[] paramIds = request.getParameterValues(ValueUtil.PARAMID);
            // 获取页面中paramValue的值并赋值给paramValue数组
            String[] paramValue = request.getParameterValues(ValueUtil.PARAMVALUE);
            // 获取页面中openSpecs的值并赋值给openSpecs数组
            String[] openSpecs = request.getParameterValues("openSpecs");
            // 获取页面中openSpecValue的值并赋值给openSpecValue数组
            String[] openSpecValue = request.getParameterValues("openSpecValue");
            // 封装需要的参数等到Map中
            Map<String, String[]> map = new HashMap<String, String[]>();
            // 把tags数组放进map集合中
            map.put("tags", tags);
            // 把expandParamIds数组放进map集合中
            map.put("expandParamIds", expandParamIds);
            // 把expandParamValues数组放进map集合中
            map.put("expandParamValues", expandParamValues);
            // 把paramIds数组放进map集合中
            map.put("paramIds", paramIds);
            // 把paramValue数组放进map集合中
            map.put(ValueUtil.PARAMVALUE, paramValue);
            // 把aboutGoodsId数组放进map集合中
            map.put(ValueUtil.ABOUTGOODSID, aboutGoodsId);
            // 判断openSpecs数组是否为空 并且openSpecValue数组是否为null
            // 如果不为空就把数组放进map集合中
            if (null != openSpecs && null != openSpecValue) {
                map.put("specId", openSpecs);
                map.put("specValues", openSpecValue);
                // 处理选择的开启的规格图片
                String[] openSpecValueImg = new String[openSpecValue.length];
                // 循环openSpecValue数组
                for (int i = 0; i < openSpecValue.length; i++) {
                    // 如果获取的图片资源不为空就进行赋值
                    // 否则就赋值为Null
                    if (!request2.getFile("specValueImages_" + openSpecValue[i]).isEmpty()) {
                        openSpecValueImg[i] = UploadUtil.uploadFile(request2.getFile("specValueImages_" + openSpecValue[i]), request).get("0");
                    } else {
                        openSpecValueImg[i] = null;
                    }
                }
                map.put("openSpecValueImg", openSpecValueImg);
            }
            // 获取上传的商品图片
            MultipartFile imageFile = request2.getFile(GOODSIMAGE);
            // 判断获取上传的商品图片是否为空
            // 如果不为空就给商品图片进行赋值
            if (!imageFile.isEmpty()) {
                goods.setGoodsImg(UploadUtil.uploadFile(imageFile, request).get("0"));
            }

            // 设置所属的BOSS商家
            goods.setIsThird("0");
            goods.setGoodsBelo(0L);
            goods.setGoodsBeloName("BOSS");
            // 新增商品
            Long result = this.goodsService.saveGoods(goods, (String) request.getSession().getAttribute(ValueUtil.NAME), map);
            // 非空验证 商品名称 和是否保存成功
            if (null != goods.getGoodsName() && 1L == result) {
                // 获取当前登录的用户名
                String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "新增商品信息", "新增商品信息-->商品名称【" + goods.getGoodsName() + VARNAME + name);
                // 日志记录
                LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goods.getGoodsName());
            }
        } catch (Exception e) {
            LOGGER.info("保存商品名称为:" + goods.getGoodsName() + "失败");
           // return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据商品分类ID查询相关的商品列表
     *
     * @param catId
     *            分类ID {@link java.lang.Long }
     * @return 查询到的列表 {@link java.util.List}
     */
    @RequestMapping("/findGoodByCatId")
    @ResponseBody
    public List<Object> queryGoodsByCatId(Long catId) {
        // 打印日志
        LOGGER.info(ValueUtil.FINDGOODBYCATIDINFO + "分类ID:为" + catId);
        // 执行查询方法并把结果返回
        return this.goodsService.queryGoodsListByCatId(catId);
    }

    /**
     * 根据商品ID查询修改商品时的VO
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    @RequestMapping("/findModifiedGoodsVoByGoodsId")
    @ResponseBody
    public GoodsMoifiedVo queryModifiedByGoodsId(Long goodsId) {
        // 根据商品ID查询单个的商品对象
        GoodsMoifiedVo goodsMoifiedVo = this.goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 非空验证 商品名称
        if (null != goodsMoifiedVo.getGoodsName()) {
            // 日志记录
            LOGGER.info(ValueUtil.FINDMODIFIEDGOODSVOBYGOODSIDINFO + ",查询的商品名称为:" + goodsMoifiedVo.getGoodsName());
        }
        return this.goodsService.queryModeifiedVoByGoodsId(goodsId);
    }

    /**
     * 根据商品ID查询商品详情
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    @RequestMapping("/findDetailGoodsVoByGoodsId")
    public ModelAndView findDetailGoodsVoByGoodsId(Long goodsId) {
        // 根据商品ID查询单个的商品对象
        GoodsMoifiedVo goodsMoifiedVo = this.goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 非空验证 商品名称
        if (null != goodsMoifiedVo.getGoodsName()) {
            // 日志记录
            LOGGER.info(ValueUtil.FINDDETAILGOODSVOBYGOODSIDINFO + ",查询的商品名称为:" + goodsMoifiedVo.getGoodsName());
        }
        return new ModelAndView(PathUtil.GOODSDETAILVIEW, "detailGoods", this.goodsService.queryModeifiedVoByGoodsId(goodsId));
    }

    /**
     * 更新商品信息
     *
     * @param goods
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/updateGoods")
    public ModelAndView updateGoods(@Valid Goods goods, HttpServletRequest request, MultipartHttpServletRequest request2, int pageNo, int pageSize) {
        try {
            // 定义一个HashMap集合
            Map<String, String[]> map = new HashMap<String, String[]>();
            // 获取页面中的update_goods_tags值并赋值给tags数组
            String[] tags = request.getParameterValues("update_goods_tags");
            // 获取页面中的expandParamId值并赋值给expandParamId数组
            String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
            // 获取页面中的expandparamValue值并赋值给expandparamValue数组
            String[] expandparamValue = request.getParameterValues(EXPANDPARAMVALUE);
            // 获取页面中的paramId值并赋值给paramId数组
            String[] paramId = request.getParameterValues(PARAMID);
            // 获取页面中的paramValue值并赋值给paramValue数组
            String[] paramValue = request.getParameterValues(PARAMVALUE);
            // 获取页面中的aboutGoodsId值并赋值给aboutGoodsId数组
            String[] aboutGoodsId = request.getParameterValues(ABOUTGOODSID);

            // 把tags数组放进map集合中
            map.put("tags", tags);
            // 把expandParamId数组放进map集合中
            map.put(EXPANDPARAMID, expandParamId);
            // 把expandparamValue数组放进map集合中
            map.put(EXPANDPARAMVALUE, expandparamValue);
            // 把paramId数组放进map集合中
            map.put(PARAMID, paramId);
            // 把paramValue数组放进map集合中
            map.put(PARAMVALUE, paramValue);
            // 把aboutGoodsId数组放进map集合中
            map.put(ABOUTGOODSID, aboutGoodsId);
            // 获取上传的商品图片
            MultipartFile imageFile = request2.getFile(GOODSIMAGE);
            // 如果获取的商品图片资源不为空
            // 就给商品的图片路劲赋值
            if (!imageFile.isEmpty()) {
                goods.setGoodsImg(UploadUtil.uploadFile(imageFile, request).get("0"));
            }
            // 获取审核开关并赋值给变量isThirdAuditUsed
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            // 同步修改货品副标题
            List<GoodsProduct> goodsProduct = goodsProductService.queryProductsByGoodsId(goods.getGoodsId());
            // 循环货品集合
            for (int i = 0; i < goodsProduct.size(); i++) {
                // 执行更新操作
                goodsProductService.updateGoodsSubtitleById(goodsProduct.get(i).getGoodsInfoId(), goods.getGoodsSubtitle());
            }
            // 更新指定商品
            int result = this.goodsService.updateGoods(goods, map, isThirdAuditUsed, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 非空判断商品名称 和是否修改商品成功
            if (null != goods.getGoodsName() && 1 == result) {
                // 获取当前登录的用户名
                String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "修改商品信息", "修改商品信息-->商品名称【" + goods.getGoodsName() + VARNAME + name);
                // 日志记录
                LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goods.getGoodsName());
            }
            // 更新索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goods.getGoodsId());
            // 后台boss的商品 1是第三方商家
            if ("0".equals(goods.getIsThird())) {
                return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                        + PAGESIZE + pageSize));
            }

        } catch (Exception e) {
            LOGGER.error(""+e);
            return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                    + PAGESIZE + pageSize));
        }
            LOGGER.info("修改商品报错");

        // 第三方商家的商品
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLERISTHIRD + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                + PAGESIZE + pageSize));
    }

    /**
     * 更新商品信息(新后台使用)
     *
     * @param goods
     *            需要更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param request
     *            请求对象
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/updateGoodsNew")
    public ModelAndView updateGoodsNew( Goods goods, HttpServletRequest request, MultipartHttpServletRequest request2, int pageNo, int pageSize) {
        try {
            // 定义一个HashMap集合
            Map<String, String[]> map = new HashMap<String, String[]>();
            // 获取页面中update_goods_tags的值并赋值给tags数组
            String[] tags = request.getParameterValues("update_goods_tags");
            // 获取页面中expandParamId的值并赋值给expandParamId数组
            String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
            // 获取页面中expandparamValue的值并赋值给expandparamValue数组
            String[] expandparamValue = request.getParameterValues(EXPANDPARAMVALUE);
            // 获取页面中paramId的值并赋值给paramId数组
            String[] paramId = request.getParameterValues(PARAMID);
            // 获取页面中paramValue的值并赋值给paramValue数组
            String[] paramValue = request.getParameterValues(PARAMVALUE);
            // 获取页面中aboutGoodsId的值并赋值给aboutGoodsId数组
            String[] aboutGoodsId = request.getParameterValues(ABOUTGOODSID);
            // 重新配置的规格，格式productId-specId_specdetailId-specId-specdetailId
            String[] productIdSpecIdSpecdetailId = request.getParameterValues(PRODUCTIDSPECIDSPECDETAILID);

            // 把tags数组放进map集合中
            map.put("tags", tags);
            // 把expandParamId数组放进map集合中
            map.put(EXPANDPARAMID, expandParamId);
            // 把expandparamValue数组放进map集合中
            map.put(EXPANDPARAMVALUE, expandparamValue);
            // 把paramId数组放进map集合中
            map.put(PARAMID, paramId);
            // 把paramValue数组放进map集合中
            map.put(PARAMVALUE, paramValue);
            // 把aboutGoodsId数组放进map集合中
            map.put(ABOUTGOODSID, aboutGoodsId);
            // 把productIdSpecIdSpecdetailId数组放进map集合中
            map.put(PRODUCTIDSPECIDSPECDETAILID, productIdSpecIdSpecdetailId);
            // 获取上传的商品图片
            MultipartFile imageFile = request2.getFile(GOODSIMAGE);
            // 如果上传的商品图片不为空
            // 就把商品的图片路劲进行赋值
            if (imageFile != null && !imageFile.isEmpty()) {
                goods.setGoodsImg(UploadUtil.uploadFile(imageFile, request).get("0"));
            }
            // 获取审核开关并赋值给变量isThirdAuditUsed
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            // 同步修改货品副标题
            List<GoodsProduct> goodsProduct = goodsProductService.queryProductsByGoodsId(goods.getGoodsId());
            // 循环货品集合
            for (int i = 0; i < goodsProduct.size(); i++) {
                // 执行修改操作
                goodsProductService.updateGoodsSubtitleById(goodsProduct.get(i).getGoodsInfoId(), goods.getGoodsSubtitle());
            }
            // 更新指定商品
            int result = this.goodsService.updateGoodsNew(goods, map, isThirdAuditUsed, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 非空判断商品名称 和是否修改商品成功
            if (null != goods.getGoodsName() && 1 == result) {
                // 获取当前登录的用户名
                String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "修改商品信息", "修改商品信息-->商品名称【" + goods.getGoodsName() + VARNAME + name);
                // 日志记录
                LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goods.getGoodsName());
            }
            // 后台boss的商品 1是第三方商家
            if ("0".equals(goods.getIsThird())) {
                return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                        + PAGESIZE + pageSize));
            }

        } catch (Exception e) {
            LOGGER.error(""+e);
            return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                    + PAGESIZE + pageSize));
        }
        // 第三方商家的商品
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLERISTHIRD + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo
                + PAGESIZE + pageSize));
    }

    /**
     * 修改商品规格
     */
    @RequestMapping("/updateGoodsParamSpec")
    public void updateGoodsParamSpec( Goods goods, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 定义一个HashMap集合
            Map<String, String[]> map = new HashMap<String, String[]>();
            // 获取页面中的expandParamId的值并赋值给expandParamId数组
            String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
            // 获取页面中的expandparamValue的值并赋值给expandparamValue数组
            String[] expandparamValue = request.getParameterValues(EXPANDPARAMVALUE);
            // 获取页面中的paramId的值并赋值给paramId数组
            String[] paramId = request.getParameterValues(PARAMID);
            // 获取页面中的paramValue的值并赋值给paramValue数组
            String[] paramValue = request.getParameterValues(PARAMVALUE);
            // 重新配置的规格，格式productId-specId_specdetailId-specId-specdetailId
            String[] productIdSpecIdSpecdetailId = request.getParameterValues(PRODUCTIDSPECIDSPECDETAILID);

            // 把expandParamId数组放进map集合中
            map.put(EXPANDPARAMID, expandParamId);
            // 把expandparamValue数组放进map集合中
            map.put(EXPANDPARAMVALUE, expandparamValue);
            // 把paramId数组放进map集合中
            map.put(PARAMID, paramId);
            // 把paramValue数组放进map集合中
            map.put(PARAMVALUE, paramValue);
            // 把productIdSpecIdSpecdetailId数组放进map集合中
            map.put(PRODUCTIDSPECIDSPECDETAILID, productIdSpecIdSpecdetailId);
            // 获取审核开关并赋值给变量isThirdAuditUsed
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            // 更新指定商品
            int result = this.goodsService.updateGoodsParamSpec(goods, map, isThirdAuditUsed, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 非空判断商品名称 和是否修改商品成功
            if (null != goods.getGoodsName() && 1 == result) {
                // 获取当前登录的用户名
                String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "修改商品参数和规格信息", "修改商品参数信息-->商品名称【" + goods.getGoodsName() + VARNAME + name);
                // 日志记录
                LOGGER.info(ValueUtil.DELGOODSINFO + VARPRODUCT + goods.getGoodsName());
            }

            out.append("<script>parent.call_update_goods();</script>");
        } catch (Exception e) {
            LOGGER.error(""+e);
            LOGGER.info("出错");
        }
    }

    /**
     * 更新商品上下架状态
     *
     * @param upGoods
     *            商品实体
     * @param request
     *            请求对象
     */
    @RequestMapping("/updateGoodsSta")
    public ModelAndView updateGoods(Goods upGoods, HttpServletRequest request) {
        // 实例化一个商品对象
        Goods goods = new Goods();
        String pageNo = request.getParameter(PAGENO);
        try {
            // 获取页面中的值并设置商品的商品id
            goods.setGoodsId(upGoods.getGoodsId());
            // 获取页面中的值并设置商品的是否上架
            goods.setGoodsAdded(upGoods.getGoodsAdded());
            // 获取页面中的值并设置商品的商品信息
            goods.setGoodsModifiedName((String) request.getSession().getAttribute(ValueUtil.NAME));
            // 执行修改商品信息并把结果赋值给变量result
            int result = this.goodsService.updateGoodsDesc(goods);
            // 获取页面中的当前页码

            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(upGoods.getGoodsId());
            // 非空验证 商品名称 和是否更新成功
            if (null != upGoods.getGoodsName() && 1 == result) {
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEGOODSINFO + "【" + upGoods.getGoodsName() + "】上下架商品",
                        request.getSession().getAttribute(ValueUtil.OPERAPATH) + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(""+e);
            // 更新商品上下架状态失败
            LOGGER.info(ValueUtil.UPDATEGOODSINFO + "，更新的商品名称为," + upGoods.getGoodsName());

        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo));
    }

    /**
     * 更新商品上下架状态
     *
     * @param upGoods
     *            商品实体
     * @param request
     *            请求对象
     */
    @RequestMapping("/updateGoodsStaisthird")
    public ModelAndView updateGoodsisthird(Goods upGoods, HttpServletRequest request) {
        // 实例化一个商品对象
        Goods goods = new Goods();
        // 获取页面中的当前页码
        String pageNo = request.getParameter(PAGENO);
        try {
            // 获取页面中的值并设置商品的商品id
            goods.setGoodsId(upGoods.getGoodsId());
            // 获取页面中的值并设置商品的是否上架
            goods.setGoodsAdded(upGoods.getGoodsAdded());
            // 获取页面中的值并设置商品的商品信息
            goods.setGoodsModifiedName((String) request.getSession().getAttribute(ValueUtil.NAME));
            // 执行修改商品信息并把结果赋值给变量result
            int result = this.goodsService.updateGoodsDesc(goods);
            // 非空验证 商品名称 和是否更新成功
            if (null != upGoods.getGoodsName() && 1 == result) {
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEGOODSINFO + "【" + upGoods.getGoodsName() + "】上下架商品",
                        request.getSession().getAttribute(ValueUtil.OPERAPATH) + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
            }


        } catch (Exception e) {
            LOGGER.error(""+e);
            // 更新商品上下架状态失败
            LOGGER.info(ValueUtil.UPDATEGOODSINFO + "，更新的第三方商品名称为," + upGoods.getGoodsName());

        } finally {
            goods = null;
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLERISTHIRD + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request) + PAGENO2 + pageNo));
    }

    /**
     * 高级查询
     *
     * @param pb
     *            分页参数Bean {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            高级查询Bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/queryByParam")
    public ModelAndView queryByParam(PageBean pb, GoodsSearchBean searchBean) {
        // 设置PageBean中的url路径
        pb.setUrl("queryByParam.htm");
        // 打印日志
        LOGGER.info(ValueUtil.QUERYBYPARAMINFO);
        // 返回结果
        return new ModelAndView(PathUtil.GOODSLIST, "pb", this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean)).addObject(SEARCHBEAN, searchBean)
                .addObject(ValueUtil.BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(ValueUtil.CATELIST, this.goodsCateService.queryAllCate())
                .addObject(ValueUtil.TAGLIST, this.goodsTagService.queryAllTag());
    }

    /**
     * ajax查询商品
     *
     * @param pb
     * @param searchBean
     * @return
     */

    @RequestMapping("/queryByParamAjax")
    @ResponseBody
    public PageBean queryByParamAjax(PageBean pb, GoodsSearchBean searchBean) {
        // 设置PageBean中的url路径
        pb.setUrl("queryByParamAjax.htm");
        // 打印结果
        LOGGER.info(ValueUtil.QUERYBYPARAMINFO);
        // 返回结果
        return this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean);
    }

    /**
     * 第三方上传商品时查询关联商品
     *
     * @param pb
     * @param searchBean
     * @return
     */
    @RequestMapping("/queryThirdGoodsByParamAjax")
    @ResponseBody
    public PageBean queryThirdGoodsByParamAjax(PageBean pb, GoodsSearchBean searchBean, HttpServletRequest request) {
        // 获取页面中的thirdId的值并赋值给searchBean的thirdId
        searchBean.setThirdId(String.valueOf(request.getSession().getAttribute("thirdId")));
        searchBean.setIsThird("1");
        // 设置PageBean的url路径
        pb.setUrl("queryThirdGoodsByParamAjax.htm");
        // 返回结果
        return this.goodsService.searchThirdBySearchBeanAndPageBean(pb, searchBean);
    }

    /**
     * 高级查询
     *
     * @param pb
     *            分页参数Bean {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            高级查询Bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/queryByParamisthird")
    public ModelAndView queryByParamisthird(PageBean pb, GoodsSearchBean searchBean, Long oneCateId, Long twoCateId) {
        // 设置PageBean中的url路径
        pb.setUrl("queryByParamisthird.htm");
        // 打印日志
        LOGGER.info(ValueUtil.QUERYBYPARAMINFO);

        ModelAndView mav = new ModelAndView(PathUtil.GOODSLISTISTHIRD, "pb", this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean)).addObject(SEARCHBEAN, searchBean)
                .addObject(ValueUtil.BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(ValueUtil.CATELIST, this.goodsCateService.queryAllCate())
                .addObject(ValueUtil.TAGLIST, this.goodsTagService.queryAllTag());
        // 查询结果并赋值给商品分类集合
        List<GoodsCate> oneCateList = goodsCateService.querySonCateByParentIdAndName(0L, null);
        // 把商品集合放进map集合中
        mav.addObject(ONECATELIST, oneCateList);
        // 判断商品分类id是否为空
        if (oneCateId != null) {
            // 查询结果并赋值给商品分类集合twoCateList
            List<GoodsCate> twoCateList = goodsCateService.querySonCateByParentIdAndName(oneCateId, null);
            // 把twoCaeList商品集合放进map集合中
            mav.addObject("twoCateList", twoCateList);
            // 判断twoCateId是否为空
            if (twoCateId != null) {
                // 查询结果并赋值给threeCateList商品集合
                List<GoodsCate> threeCateList = goodsCateService.querySonCateByParentIdAndName(twoCateId, null);
                // 把threeCateList商品集合放进Map集合中
                mav.addObject("threeCateList", threeCateList);
            }
        }
        mav.addObject("oneCateId", oneCateId);
        mav.addObject("twoCateId", twoCateId);

        return mav;
    }

    /**
     * 验证商品编号是否已经使用
     *
     * @param goodsNo
     *            待验证的商品编号
     */
    @RequestMapping("/checkGoodsNo")
    @ResponseBody
    public boolean checkGoodsNo(String goodsNo) {
        // 打印日志
        LOGGER.info(ValueUtil.CHECKGOODSNOINFO + ",验证的商品编号为" + goodsNo);
        // 执行操作返回结果
        return this.goodsService.queryCountByGoodsNo(goodsNo);
    }

    /**
     * 去添加商品
     */
    @RequestMapping("/toAddGoods")
    public ModelAndView toAddGoods() {
        // 打印日志
        LOGGER.info(ValueUtil.TOADDGOODSINFO);
        // 执行方法返回结果
        return new ModelAndView("jsp/goods/goodsAdd").addObject(BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject("cateList", this.goodsCateService.queryAllCate())
                .addObject(TAGLIST, this.goodsTagService.queryAllTag());
    }

    /**
     * 去修改商品
     */
    @RequestMapping("/toModifyGoods")
    public ModelAndView toModifyGoods(Long goodsId, int pageNo, int pageSize, Long isThird) {
        Long isThirdNew = isThird;
        // 打印日志
        LOGGER.info(ValueUtil.TOMODIFYGOODS);
        // 不是第三方的商品
        if (isThirdNew == null) {
            isThirdNew = 0L;
        }
        return new ModelAndView("jsp/goods/goodsModify").addObject(BRANDLIST, this.goodsBrandService.queryAllBrand())
                .addObject("goodsMoifiedVo", this.goodsService.queryModeifiedVoByGoodsId(goodsId)).addObject("cateList", this.goodsCateService.queryAllCate())
                .addObject(TAGLIST, this.goodsTagService.queryAllTag()).addObject("goodsId", goodsId).addObject(PAGENO, pageNo).addObject("pageSize", pageSize)
                .addObject("isThird", isThirdNew);
    }

    /**
     * 去修改商品
     */
    @RequestMapping("/toModifyGoodsNew")
    public ModelAndView toModifyGoodsNew(Long goodsId, int pageNo, int pageSize, Long isThird) {
        Long isThirdNew = isThird;
        // 打印日志
        LOGGER.info(ValueUtil.TOMODIFYGOODS);
        // 不是第三方的商品
        if (isThirdNew == null) {
            isThirdNew = 0L;
        }
        // 根据商品ID查询单个的商品对象
        GoodsMoifiedVo goodsMoifiedVo = this.goodsService.queryModeifiedVoByGoodsId(goodsId);
        List<GoodsProductVo> products = this.goodsProductService.queryProductListByGoodsId(goodsId);
        return new ModelAndView("jsp/goods/goodsModify").addObject("goodsMoifiedVo", goodsMoifiedVo).addObject(BRANDLIST, this.goodsBrandService.queryAllBrand())
                // .addObject("cateList", this.goodsCateService.queryAllCate())
                .addObject(TAGLIST, this.goodsTagService.queryAllTag()).addObject("goodsId", goodsId).addObject(PAGENO, pageNo).addObject("pageSize", pageSize)
                .addObject("isThird", isThirdNew).addObject("products", products);
    }

    /**
     * 生成商品的lucene搜索索引 索引生成地址：项目所在目录的"/htdocs/www/npstore/index1"
     *
     * @return
     */
    /*@RequestMapping("/createIndex")
    @ResponseBody
    public boolean createIndex() {
        // 打印日志
        LOGGER.info(ValueUtil.CREATEINDEXINFO);
        // 执行操作返回结果
        return this.goodsService.createIndex();
    }*/

    /**
     * 生成商品的lucene搜索索引 索引生成地址：项目所在目录的"/htdocs/www/npstore/index1"
     *
     * @return
     */
    @RequestMapping("/createIndex")
    @ResponseBody
    public boolean createIndex(HttpServletRequest request) {
        String[] tags = request.getParameterValues("goodsIds[]");
        Long[] goodsIds=null;
        if(tags.length>0&&tags !=null){
            goodsIds=new Long[tags.length];
            for(int i=0;i< tags.length;i++){
                goodsIds[i]=Long.valueOf(tags[i]);
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.CREATEINDEXINFO);
        // 执行操作返回结果
        if(goodsIds==null||goodsIds.length==0){
            return false;
        }
        boolean flag=true;
        for(int i=0;i<goodsIds.length;i++){
            if(goodsElasticSearchService.insertOneGoodsIndexToEs(goodsIds[i])!=0){
                flag=false;
            }
        }
        return flag;
    }

    /**
     * 导出商品列表为Excel文件
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping("/exportGoodsList")
    public void exportGoodsList(HttpServletResponse response, GoodsSearchBean searchBean) throws IOException {
        // 打印日志
        LOGGER.info(ValueUtil.EXPORTGOODSLISTINFO);
        // 执行创建Excel的方法并输出
        ExportGoodsList.exportGoodsList(this.goodsService.queryAllGoodsForExport(searchBean.getIsThird()), response);
    }

    /**
     * 导出当前页显示的所有记录
     *
     * @param pb
     *            分页参数
     * @param response
     *            相应参数
     * @param searchBean
     *            查询参数
     */
    @RequestMapping("/exportGoodsPageList")
    public void exportGoodsPageList(PageBean pb, HttpServletResponse response, GoodsSearchBean searchBean) {
        // 打印日志
        LOGGER.info(ValueUtil.EXPORTGOODSPAGELISTINFO);
        // 执行创建Excel的方法并输出
        ExportGoodsList.exportGoodsList(this.goodsService.searchBySearchBeanAndPageBean(pb, searchBean).getList(), response);
    }

    /**
     * 根据选中的商品导出
     *
     * @param response
     *            相应参数
     * @param goodsIds
     *            商品ID的数组
     */
    @RequestMapping("/exportGoodsCheck")
    public void exportGoodsCheck(HttpServletResponse response, Long[] goodsIds) {
        // 打印日志
        LOGGER.info(ValueUtil.EXPORTGOODSCHECKINFO);
        // 执行创建Excel的方法并输出
        ExportGoodsList.exportGoodsList(this.goodsService.queryGoodsListVoListForExportByGoodsIds(goodsIds), response);
    }

    /**
     * 批量生成货品
     *
     * @param goodsId
     *            商品ID {@link Long}
     */
    @RequestMapping("/batchCreateProduct")
    public ModelAndView batchCreateProduct(Long goodsId, HttpServletRequest request) {
        try {
            // 执行生成货品方法
            this.goodsService.saveProductWhenClickBatchCreate(goodsId, (String) request.getSession().getAttribute(ValueUtil.NAME));
            // 记录操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量生成货品", request.getSession().getAttribute(ValueUtil.OPERAPATH)
                    + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
            // 返回视图

        } catch (Exception e) {
            LOGGER.error(""+e);
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }
        return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 新的添加商品流程
     */
    @RequestMapping("/newUploadGoods")
    public ModelAndView newUploadGoods(HttpServletRequest request) {
        return new ModelAndView("jsp/goods/newupload").addObject(BRANDLIST, this.goodsBrandService.queryAllBrand()).addObject(TAGLIST, this.goodsTagService.queryAllTag())
                .addObject("wareHouse", this.wareHouseService.queryAllWareHouse()).addObject("support", serviceSupportMapperService.selectAll());
    }

    /**
     * 新流程添加商品
     *
     * @return 新添加的商品ID
     * @throws IOException
     */
    @RequestMapping("/newUploadGood")
    public void newUploadGood(Goods goods, HttpServletRequest request, HttpServletResponse resp, Long importGoodsId, String importGoodsIdArray) throws IOException {
        PrintWriter out = resp.getWriter();
        // 保存关联商品的ID
        String[] about = null;
        if (null != importGoodsIdArray && !"".equals(importGoodsIdArray)) {
            // 获取页面中about的值并赋值到about数组中
            about = importGoodsIdArray.split(",");
        }
        // 获取页面中tags的值并赋值到tags数组中
        String[] tags = request.getParameterValues("tags");
        // 获取页面中paramId的值并赋值到paramId数组中
        String[] paramId = request.getParameterValues(PARAMID);
        // 获取页面中paramValue的值并赋值到paramValue数组中
        String[] paramValue = request.getParameterValues(PARAMVALUE);
        // 获取页面中expandParamId的值并赋值到expandParamId数组中
        String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
        // 获取页面中expandParamValue的值并赋值到expandParamValue数组中
        String[] expandParamValue = request.getParameterValues(EXPANDPARAMVALUE);
        // 获取页面中specs的值并赋值到specs数组中
        String[] specs = request.getParameterValues("specs");
        // 获取页面中specsValue的值并赋值到specsValue数组中
        String[] specsValue = request.getParameterValues("specsValue");
        // 获取页面中specsValueImg的值并赋值到specsValueImg数组中
        String[] specsValueImg = request.getParameterValues("specsValueImg");
        // 获取页面中specsValueRemark的值并赋值到specsValueRemark数组中
        String[] specsValueRemark = request.getParameterValues("specsValueRemark");
        // 定义一个HashMap集合
        Map<String, String[]> map = new HashMap<String, String[]>();
        // 判断商品导入ID是否为空
        if (importGoodsId != null && this.goodsImportService.selectByPrimaryKey(importGoodsId) != null) {
            // 查询商品导入表中是否有记录
                // 有记录就更新是否发布字段为已发布
                this.goodsImportService.updateGoodsImportAdded(importGoodsId);
        }
        // 清空session中存储的商品导入id
        request.getSession().setAttribute("importGoodsId", "");
        Long newId = null;
        try {
            // 把tags数组放进map集合中
            map.put("tags", tags);
            // 把expandParamId数组放进map集合中
            map.put("expandParamIds", expandParamId);
            // 把expandParamValue数组放进map集合中
            map.put("expandParamValues", expandParamValue);
            // 把paramId数组放进map集合中
            map.put("paramIds", paramId);
            // 把paramValue数组放进map集合中
            map.put(ValueUtil.PARAMVALUE, paramValue);
            // 把about数组放进map集合中
            map.put(ValueUtil.ABOUTGOODSID, about);
            // 把specs数组放进map集合中
            map.put("specId", specs);
            // 把specsValue数组放进map集合中
            map.put("specValues", specsValue);
            // 把specsValueImg数组放进map集合中
            map.put("openSpecValueImg", specsValueImg);
            // 把specsValueRemark数组放进map集合中
            map.put("openSpecValueRemark", specsValueRemark);
            goods.setIsThird("0");
            goods.setGoodsBelo(0L);
            goods.setGoodsBeloName("BOSS");
            newId = this.goodsService.saveGoods(goods, (String) request.getSession().getAttribute(ValueUtil.NAME), map);
            out.append("<script>parent.call_save_goods('" + newId + "');</script>");

        } finally {
            // 记录操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.SAVEGOODSINFO,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + VARNAME2 + request.getSession().getAttribute(ValueUtil.NAME));
            out.flush();
            tags = null;
            paramId = null;
            paramValue = null;
            expandParamId = null;
            expandParamValue = null;
            specs = null;
            specsValue = null;
            specsValueImg = null;
            about = null;
            map = null;
            newId = null;
        }

    }

    /**
     * 新流程更新商品详细介绍
     *
     * @param goodsId
     *            商品ID
     * @param goodsDetailDesc
     *            详细介绍内容
     * @param request
     *            请求
     * @return 商品ID
     * @throws IOException
     */
    @RequestMapping("/newUploadSaveGoodsDesc")
    public void newUploadSaveGoodsDesc(Long goodsId, String goodsDetailDesc, String goodsMobileDesc, HttpServletResponse resp, HttpServletRequest request) throws IOException {
        PrintWriter out = resp.getWriter();
        // 实例化一个商品对象
        Goods goods = new Goods();
        // 设置商品的id为传过来的值
        goods.setGoodsId(goodsId);
        // 判断商品详情是否为null
        // 如果不是就把获取的商品详情赋值
        // 如果是就设置为""
        if (null != goodsDetailDesc && goodsDetailDesc.length() > 0 && !"".equals(goodsDetailDesc)) {
            goods.setGoodsDetailDesc(goodsDetailDesc);
        } else {
            goods.setGoodsDetailDesc("");
        }
        if (null != goodsMobileDesc && goodsMobileDesc.length() > 0 && !"".equals(goodsMobileDesc)) {
            goods.setMobileDesc(goodsMobileDesc);
        } else {
            goods.setMobileDesc("");
        }
        // 获取商品VO对象
        GoodsMoifiedVo goodsMoifiedVo = goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 修改商品成功 并且商品名称不为空
        if (1 == this.goodsService.updateGoodsDesc(goods) && null != goodsMoifiedVo.getGoodsName()) {
            LOGGER.info("更新的商品" + goodsMoifiedVo.getGoodsName() + "成功！");
        } else {
            this.goodsService.updateGoodsDesc(goods);
        }
        out.append("<script>parent.call_save_desc('" + goodsId + "');</script>");

    }

    /**
     * 根据条件查询商品信息
     *
     * @param request
     * @param pb
     * @param productNo
     * @return
     */
    @RequestMapping("/queryGoodsForCoupon")
    @ResponseBody
    public PageBean queryGoodsForCoupon(HttpServletRequest request, PageBean pb, String productNo) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYGOODSFORCOUPONINFO);
        // 执行操作并返回结果
        return this.goodsService.queryGoodsForCoupon(pb, (Long) request.getSession().getAttribute("thirdId"), productNo);
    }

    /**
     * 批量下架商品
     *
     * @param goodsIds
     *            需要下架的商品的ID的集合
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchDownGoods")
    public ModelAndView batchDownGoods(Long[] goodsIds, HttpServletRequest request) {
        // 执行批量下架方法
        this.goodsService.batchDown(goodsIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 批量修改索引
        for (Long goodsId : goodsIds) {
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量上架商品
     *
     * @param goodsIds
     *            需要上架的商品的ID的集合
     * @return 视图 {@link org.springframework.web.servlet.ModelAndView}
     */
    @RequestMapping("/batchUpGoods")
    public ModelAndView batchUpGoods(Long[] goodsIds, HttpServletRequest request) {
        // 执行批量上架方法
        this.goodsService.batchUp(goodsIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 批量修改索引
        for (Long goodsId : goodsIds) {
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        }

        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据关联商品Id和商品id删除关联商品
     *
     * @param relatedId
     *            关联商品Id
     * @param relatedProductId
     *            商品Id
     * @return
     */
    @RequestMapping("/deleteRelatedProduct")
    @ResponseBody
    public int deleteRelatedProduct(Long relatedId, Long relatedProductId) {
        if (null != relatedId && null != relatedProductId) {
            return relatedGoodsService.deleteRelatedProduct(relatedId, relatedProductId);
        }
        return 0;
    }

    /**
     * 批量修改库存 用于后台商品列表批量修改库存
     *
     * @param goodsIds
     * @author houyichang 2015/8/24
     */
    @RequestMapping("/batchUpdateStock")
    public ModelAndView batchUpdateStock(HttpServletRequest request, Long[] goodsIds, String stock) {
        // 判断前台传过来的库存值是否为空
        int num = 0;
        if (stock != null) {
            num = this.goodsService.batchUpdateStock(goodsIds, stock);
        }
        return new ModelAndView(new RedirectView(PathUtil.ALLGOODSCONTROLLER + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public GetOnOffMapper getGetOnOffMapper() {
        return getOnOffMapper;
    }

    @Resource(name = "GetOnOffMapper")
    public void setGetOnOffMapper(GetOnOffMapper getOnOffMapper) {
        this.getOnOffMapper = getOnOffMapper;
    }

}
