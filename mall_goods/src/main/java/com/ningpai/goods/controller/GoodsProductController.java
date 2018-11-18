/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.excel.ExportGoodsList;
import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.EmailUtils;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.District;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import com.ningpai.util.UtilDate;
import com.qpmall.auto.service.AutoStyleService;
import com.qpmall.bean.QpAutoOem;
import com.qpmall.service.QpAutoOemService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

/**
 * 货品控制器
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月27日 下午4:27:21
 */
@Controller
public class GoodsProductController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GoodsProductController.class);

    private static final String SPECS = "specs";
    private static final String WAREHOUSE = "wareHouse";
    private static final String SELECTBEAN = "selectBean";
    private static final String GOODSID = "goodsId";
    private static final String SUPPORT = "support";
    private static final String LOGGERINFO1 = ",用户名：";
    private static final String SPECID = "specId";
    private static final String SPECDETAILID = "specDetailId";
    private static final String SPECREMARK = "specRemark";
    private static final String CATEID = "cateId";
    private static final String THIRDID = "thirdId";

    private EmailUtils emailUtils;

    /* 公共接口 */
    private GoodsPub goodsPub;

    // 货品信息Service
    private GoodsProductService goodsProductService;

    // 商品规格值Service
    private GoodsSpecService goodsSpecService;

    // 库房Service
    private WareHouseService wareHouseService;

    // 区县实体类
    private DistrictService districtService;

    // 商品的Service
    private GoodsService goodsService;

    // 商品品牌
    private GoodsBrandService goodsBrandService;

    // 商品分类
    private GoodsCateService goodsCateService;

    // 会员Service
    private CustomerServiceMapper customerServiceMapper;

    // 货品id查询到关注的会员id
    private CustomerFollowServiceMapper customerFollowServiceMapper;

    private ProductWareService productWareService;

    // 商品开启规格的Service
    private GoodsOpenSpecService goodsOpenSpecService;

    // 商品索引
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;
    // oem信息
//    @Resource(name = "qpAutoOemService")
//    private QpAutoOemService qpAutoOemService;

    @Resource(name = "GoodsProductSuppService")
    private GoodsProductSuppService goodsProductSuppService;

    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    @Resource(name = "GoodsOpenSpecValueService")
    private GoodsOpenSpecValueService goodsOpenSpecValueService;

    /*@Resource(name = "autoStyleService")
    public AutoStyleService autoStyleService;*/

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public CustomerFollowServiceMapper getCustomerFollowServiceMapper() {
        return customerFollowServiceMapper;
    }

    @Resource(name = "customerFollowServiceMapper")
    public void setCustomerFollowServiceMapper(CustomerFollowServiceMapper customerFollowServiceMapper) {
        this.customerFollowServiceMapper = customerFollowServiceMapper;
    }

    public GoodsSpecService getGoodsSpecService() {
        return goodsSpecService;
    }

    @Resource(name = "GoodsSpecService")
    public void setGoodsSpecService(GoodsSpecService goodsSpecService) {
        this.goodsSpecService = goodsSpecService;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /**
     * 根据商品ID查询商品下的货品信息
     *
     * @param goodsId
     *            商品ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/queryAllByGoodsId")
    public ModelAndView queryAllByGoodsId(Long goodsId, PageBean pb, SelectBean selectBean) {
        PageBean pbNew = pb;
        // 根据商品id，PageBean，selectBean执行查询货品方法
        pbNew = this.goodsProductService.queryByGoodsId(goodsId, pbNew, selectBean);
        // 执行查询商品方法并赋值给商品实体bean
        Goods g = goodsService.queryGoodsByGoodsId(goodsId);
        // 判断是否是第三方
        // boss的商品
        if ("0".equals(g.getIsThird())) {
            // 返回结果
            return new ModelAndView(PathUtil.GOODSPRODUCT, "pb", pbNew).addObject(SPECS, this.goodsOpenSpecService.queryOpenListByGoodsIdInBoss(goodsId)).addObject(GOODSID, goodsId)
                    .addObject(SELECTBEAN, selectBean).addObject(WAREHOUSE, this.wareHouseService.queryAllWareHouse()).addObject(SUPPORT, serviceSupportMapperService.selectAll());
        }
        // 第三方
        return new ModelAndView("jsp/goods/third_goods_product", "pb", pbNew).addObject(SPECS, this.goodsOpenSpecService.queryOpenListByGoodsIdInBoss(goodsId))
                .addObject(GOODSID, goodsId).addObject(SELECTBEAN, selectBean).addObject(WAREHOUSE, this.wareHouseService.queryAllWareHouse())
                .addObject(SUPPORT, serviceSupportMapperService.selectAll());
    }

    /**
     * 根据商品ID查询商品下的货品信息 新版》店铺商品列表》查看商品页面搜索
     *
     * @param goodsId
     *            商品ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/queryAllByGoodsIdNew")
    public ModelAndView queryAllByGoodsIdNew(Long goodsId, PageBean pb, SelectBean selectBean) {
        PageBean pbNew = pb;
        // 新版的
        pbNew = this.goodsProductService.queryByGoodsIdNew(goodsId, pbNew, selectBean);
        // 根据商品id调用查询商品信息并赋值给商品实体bean
        Goods g = goodsService.queryGoodsByGoodsId(goodsId);
        // 判断是否是第三方
        // boss的商品
        if ("0".equals(g.getIsThird())) {
            // 返回结果
            return new ModelAndView(PathUtil.GOODSPRODUCT, "pb", pbNew).addObject(SPECS, this.goodsOpenSpecService.queryOpenListByGoodsIdInBoss(goodsId)).addObject(GOODSID, goodsId)
                    .addObject(SELECTBEAN, selectBean).addObject(WAREHOUSE, this.wareHouseService.queryAllWareHouse()).addObject(SUPPORT, serviceSupportMapperService.selectAll());
        }
        // 返回结果
        return new ModelAndView("jsp/goods/third_goods_product", "pb", pbNew).addObject(SPECS, this.goodsOpenSpecService.queryOpenListByGoodsIdInBoss(goodsId))
                .addObject(GOODSID, goodsId).addObject(SELECTBEAN, selectBean).addObject(WAREHOUSE, this.wareHouseService.queryAllWareHouse())
                .addObject(SUPPORT, serviceSupportMapperService.selectAll());
    }

    /**
     * 删除货品 后台
     *
     * @param productId
     *            待删除的货品ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/delProduct")
    public ModelAndView delProduct(Long productId, Long goodsId, Integer flag, HttpServletRequest request) {
        // 根据主键获取单个的货品信息
        GoodsProductVo goodsProductVo = this.goodsProductService.queryByPrimaryId(productId);
        // 执行删除
        this.goodsProductService.delProductByProductId(productId, (String) request.getSession().getAttribute(ValueUtil.NAME));

        //保存商品编号容器
        List<Long> goodsInfoIds=new ArrayList<>();
        //保存商品编号
        goodsInfoIds.add(productId);
        //删除购物车中的此商品
        this.goodsProductService.delShoppingGoodsByGoodsInfoIds(goodsInfoIds);
        // 删除索引
        goodsElasticSearchService.deleteGoodsIndexToEs(productId);

        if (null == flag && null != goodsId && null != goodsProductVo.getGoodsInfoName()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.DELPRODUCTINFO,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + "名称【" + goodsProductVo.getGoodsInfoName() + "】-->用户名："
                            + request.getSession().getAttribute(ValueUtil.NAME));
            // 重定向到根据商品IDc查询所有货品的控制器
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 返回结果
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }

    }

    /**
     * 批量删除货品信息
     *
     * @param productIds
     *            待删除的货品ID的集合
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchDelProduct")
    public ModelAndView batchDelProduct(Long[] productIds, Long goodsId, Integer flag, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDELPRODUCTINFO);
        // 调用批量删除货品方法
        this.goodsProductService.batchDelProduct(productIds, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 定义索引集合
        List<Long> list = new ArrayList<Long>();
        // 数组转换
        for (int i = 0; i < productIds.length; i++) {
            list.add(productIds[i]);
        }
        //批量删除购物车里的商品
        this.goodsProductService.delShoppingGoodsByGoodsInfoIds(list);
        // 删除索引
        goodsElasticSearchService.batchDeleteGoodsIndexToEs(list);


        if (null == flag && null != goodsId) {
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELPRODUCTINFO, request.getSession()
                    .getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
            // 重定向到queryAllByGoodsId.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 重定向到queryProductListBySomeParam.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }

    }

    /**
     * 批量上架控制器
     *
     * @param productIds
     *            待操作的货品Id数组
     * @param goodsId
     *            货品所属的商品的ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchUpload")
    public ModelAndView batchUploadedProduct(Long[] productIds, Long goodsId, Integer flag, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHUPLOADINFO);
        // 批量上架，设置状态为1 表示上架状态
        this.goodsProductService.batchUploadProduct((String) request.getSession().getAttribute(ValueUtil.NAME), productIds, 1);
        // 更新索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        if (null == flag && null != goodsId) {
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHUPLOADINFO,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
            // 重定向到queryAllByGoodsId.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 重定向到queryProductListBySomeParam.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }
    }

    /**
     * 批量下架控制器
     *
     * @param productIds
     *            带操作的货品ID数组
     * @param goodsId
     *            商品ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchDown")
    public ModelAndView batchDown(Long[] productIds, Long goodsId, Integer flag, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDOWNINFO);
        // 批量上架，设置状态为0 表示下架状态
        this.goodsProductService.batchUploadProduct((String) request.getSession().getAttribute(ValueUtil.NAME), productIds, 0);
        // 修改索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        if (null == flag && null != goodsId) {
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDOWNINFO,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
            // 重定向到queryAllByGoodsId.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 重定向到queryProductListBySomeParam。htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }
    }

    /**
     * 批量显示隐藏控制器
     *
     * @param productIds
     *            带操作的货品ID数组
     * @param goodsId
     *            商品ID
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/batchShow")
    public ModelAndView batchShow(Long[] productIds, Long goodsId, Integer flag, Integer sta, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDOWNINFO);
        // 批量显示或隐藏在列表页，设置状态为0 表示显示状态 否则隐藏
        this.goodsProductService.batchShowOrHide((String) request.getSession().getAttribute(ValueUtil.NAME), productIds, sta);
        // 修改索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量显示隐藏货品到列表页",
                request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
        if (null == flag && null != goodsId) {
            // 重定向到queryAllByGoodsId。htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 重定向到queryProductListBySomeParam.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }
    }

    /**
     * 批量显示或隐藏到手机版
     *
     * @param productIds
     *            货品ID数组
     * @param goodsId
     *            商品ID
     * @param flag
     *            标记
     * @param sta
     *            状态
     */
    @RequestMapping("/batchShowMobile")
    public ModelAndView batchShowMobile(Long[] productIds, Long goodsId, Integer flag, Integer sta, HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.BATCHDOWNINFO);
        // 批量显示或隐藏在手机版，设置状态为0 表示显示状态 否则是隐藏
        this.goodsProductService.batchShowOrHideMobile((String) request.getSession().getAttribute(ValueUtil.NAME), productIds, sta);
        // 插入索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), "批量显示隐藏货品到手机版",
                request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
        if (null == flag && null != goodsId) {
            // 重定向到queryAllByGoodsId.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsId + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        } else {
            // 重定向到queryProductListBySomeParam.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)));
        }
    }

    /**
     * 添加货品信息
     *
     * @param product
     *            待添加的货品信息实体{@link com.ningpai.goods.bean.GoodsProduct}
     * @param request
     *            请求对象
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/saveProduct")
    public ModelAndView saveProduct(@Valid GoodsProduct product, HttpServletRequest request, Long[] wareId, Long[] productStocks,
                                    BigDecimal[] productPrices, BigDecimal[] productVipPrices, PageBean pb) {
        // 打印日志
        LOGGER.info(ValueUtil.SAVEPRODUCTINFO);
        // Long goodsId = product.getGoodsId();
        // 获取页面中name为specId的值并赋值给specIds数组
        String[] specIds = request.getParameterValues(SPECID);
        // 获取页面中name为specDetailId的值并赋值给specDetailId数组
        String[] specDetailId = request.getParameterValues(SPECDETAILID);
        // 获取页面中name为specRemark的值并赋值给specRemark数组
        String[] specRemark = request.getParameterValues(SPECREMARK);
        /* 保存服务支持信息 */
        String[] suppIds = request.getParameterValues("productSupp");
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wareId", wareId);
        map.put("productStocks", productStocks);
        map.put("productPrices", productPrices);
        map.put("productVipPrices", productVipPrices);
        map.put(SPECREMARK, specRemark);
        // 判断加入的规格值是否开启，如果没开启则开启该规格值
        goodsOpenSpecValueService.queryOpenListByGoodsAndSpecValueId(product.getGoodsId(), specDetailId);
        /* 设置货品所属的boss商家 */
        product.setIsThird("0");
        product.setThirdId(0L);
        product.setThirdName("BOSS");
        int newId = this.goodsProductService.saveProduct(product, (String) request.getSession().getAttribute(ValueUtil.NAME), specIds, specDetailId, map);
        /* 保存分仓信息 */
        this.productWareService.calcProductWare(Long.parseLong(String.valueOf(newId)), productStocks, productPrices, productVipPrices, wareId);
        // 批量保存关联信息
        this.goodsProductSuppService.batchInsert(suppIds, newId);
        // 插入索引
        goodsElasticSearchService.insertOneGoodsIndexToEs(product.getGoodsId());

        /* 记录当前登录者的操作信息 */
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.SAVEPRODUCTINFO,
                request.getSession().getAttribute(ValueUtil.OPERAPATH) + "名称为【" + product.getGoodsInfoName() + "】,用户名："
                        + request.getSession().getAttribute(ValueUtil.NAME));
        // return new ModelAndView(new
        // RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + product.getGoodsId() +
        // ValueUtil.TOKENPARAM2 +
        // CSRFTokenManager.getTokenFromRequest(request)));
        /* 重定向到queryAllByGoodsId.htm中 */
        return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + product.getGoodsId() + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)
                + ValueUtil.PAGENO + pb.getPageNo() + ValueUtil.PAGESIZE + pb.getPageSize()));
    }

    /**
     * 根据货品ID查询货品Vo信息
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    @RequestMapping("/queryProductVoByProductId")
    @ResponseBody
    public GoodsProductVo queryProductVoByProductId(Long productId) {
        // 根据主键获取单个的货品对象
        GoodsProductVo goodsProductVo = this.goodsProductService.queryByPrimaryId(productId);
        // 非空验证 货品名称
        if (null != goodsProductVo.getGoodsInfoName()) {
            LOGGER.info("获取货品【" + goodsProductVo.getGoodsInfoName() + "】的详细信息！");
        }
        // 返回货品信息
        return goodsProductVo;
    }

    /**
     * 根据货品ID查询货品适配车型信息
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    @RequestMapping("/queryProductAutoStyleByProductId")
    @ResponseBody
    public Object queryProductAutoStyleByProductId(Long productId) {
        // 根据主键获取货品适配车型信息
       return this.goodsProductService.queryProductAutoStyleByProductId(productId);
    }

    @RequestMapping("/chooseAutoStyle")
    public ModelAndView chooseAutoStyle(){
        return new ModelAndView("jsp/goods/goods_autoStyle");
    }


    /**
     * 更新货品信息
     *
     * @param goodsProduct
     *            待更新的货品实体{@link com.ningpai.goods.bean.GoodsProduct}
     * @param request
     *            请求
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    @RequestMapping("/updateProduct")
    public ModelAndView updateProduct(@Valid GoodsProduct goodsProduct, Integer flag, HttpServletRequest request, Long[] wareId, Long[] productStocks,
                                      BigDecimal[] productPrices, BigDecimal[] productVipPrices, String oldPrice, String oldVipPrice, PageBean pb) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEPRODUCTINFO);
        // 获取规格值并保存
        String[] specId = request.getParameterValues(SPECID);
        // 获取规格详细值并报错
        String[] specDetailId = request.getParameterValues(SPECDETAILID);
        // 获取规格描述并保存
        String[] specRemark = request.getParameterValues(SPECREMARK);
        /* 保存服务支持信息 */
        String[] suppIds = request.getParameterValues("productSupp");
        // 调用货品更新方法
//        QpAutoOem qpAutoOem = null;
//        if(goodsProduct.getGoodsInfoAutoPartsType()!=null&&"1".equals(goodsProduct.getGoodsInfoAutoPartsType())){
//            qpAutoOem = qpAutoOemService.selectByAutoOemCode(goodsProduct.getGoodsInfoOem());
//        }
//        if(qpAutoOem != null){
//            goodsProduct.setGoodsInfoAutoStyle(qpAutoOem.getAutoStyleId());
//            goodsProduct.setAutoStyleIdLiYangID(qpAutoOem.getAutoStyleIdLiyangId());
//        }else {
//            goodsProduct.setGoodsInfoAutoStyle("");
//            goodsProduct.setAutoStyleIdLiYangID("");
//        }
        this.goodsProductService.updateProduct(goodsProduct, (String) request.getSession().getAttribute(ValueUtil.NAME), specId, specDetailId, specRemark);
        // 判断加入的规格值是否开启，如果没开启则开启该规格值
        goodsOpenSpecValueService.queryOpenListByGoodsAndSpecValueId(goodsProduct.getGoodsId(), specDetailId);
        // 遍历指定仓库 判断该仓下面的商品是否有被收藏的
        for (int i = 0; i < wareId.length; i++) {
            // 判断该仓下面降价的商品对象
            List<FollowAndCityVo> andCityVo = wareHouseService.selectFollow(wareId[i], productPrices[i], productVipPrices[i], goodsProduct.getGoodsInfoId());
            // 该仓对应的商品有被收藏
            if (null != andCityVo && !andCityVo.isEmpty()) {
                for (int j = 0; j < andCityVo.size(); j++) {
                    FollowAndCityVo cityVo = andCityVo.get(j);
                    CustomerInfo infoEmails = customerServiceMapper.email(cityVo.getCustId());
                    CustomerInfo mobiles = customerServiceMapper.mobile(cityVo.getCustId());
                    District district = districtService.findDistrictByPrimaryKey(cityVo.getDistrictId());
                    goodsProduct.setOfollowPrice(cityVo.getFollowPrice());// 关注商品的老价格
                    goodsProduct.setNfollowPrice(productPrices[i]); // 降价后的新价格
                    goodsProduct.setDisName(district.getDistrictName());// 关注商品的地区

                    if (infoEmails != null) {
                        String infoEmail = infoEmails.getInfoEmail();
                        emailUtils.sendEmails(request, infoEmail, goodsProduct);
                    }
                    if (mobiles != null) {
                        String mobile = mobiles.getInfoMobile();
                        emailUtils.sendPosts(request, goodsProduct, mobile);
                    }
                    // 每次降价 把降低的价格更新到客户收藏的商品价格
                    CustomerFollow customerFollow = new CustomerFollow();
                    customerFollow.setFollowId(cityVo.getAtteId()); // 关注ID
                    customerFollow.setFollowPrice(productPrices[i]);// 关注商品价格
                    customerFollow.setCreateTime(new Date()); // 修改创建时间
                    this.goodsProductService.updateFollow(customerFollow);
                }
            }
        }
        // 保存关联信息
        this.productWareService.calcProductWare(goodsProduct.getGoodsInfoId(), productStocks, productPrices, productVipPrices, wareId);
        /* 保存服务支持信息 */
        this.goodsProductSuppService.delAll(goodsProduct.getGoodsInfoId());
        // 批量保存关联信息
        this.goodsProductSuppService.batchInsert(suppIds, Integer.parseInt(goodsProduct.getGoodsInfoId().toString()));
        // 更新索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsProduct.getGoodsId());
        if (null == flag) {
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEPRODUCTINFO,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + request.getSession().getAttribute(ValueUtil.NAME));
            // 重定向到queryAllByGoodsId.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + goodsProduct.getGoodsId() + ValueUtil.TOKENPARAM2
                    + CSRFTokenManager.getTokenFromRequest(request) + ValueUtil.PAGENO + pb.getPageNo() + ValueUtil.PAGESIZE + pb.getPageSize()));
        } else {
            // 重定向到queryProductListBySomeParam.htm中
            return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)
                    + ValueUtil.PAGENO + pb.getPageNo() + ValueUtil.PAGESIZE + pb.getPageSize()));
        }

    }

    /**
     * 更新货品适配车型信息
     *
     * @param request
     *            请求
     * @return 视图{@link org.springframework.web.servlet.ModelAndView;}
     */
    /*@RequestMapping(value="/updateProductAutoStyle",produces = "application/json; charset=utf-8")
    @ResponseBody
    public Object updateProductAutoStyle(HttpServletRequest request,String goodsInfoId,String goodsId,String autoStyle) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEPRODUCTAUTOSTYLE);
        Map map =new HashMap();
        String operaName = (String) request.getSession().getAttribute(ValueUtil.NAME);
        JSONArray styleInfo =JSON.parseArray(autoStyle);
        if(styleInfo == null){
            map.put("success",false);
            map.put("msg","适配车型信息为空!");
            return map;
        }
        StringBuffer autoStyleIdLiYangID = new StringBuffer();
        StringBuffer goodsInfoAutoStyle = new StringBuffer();
        for(int i =0;i < styleInfo.size();i++){
            JSONObject jsonObject = (JSONObject) styleInfo.get(i);
            String levelNum =jsonObject.getString("levelNum");
            String nodeId = jsonObject.getString("id");
            autoStyleService.getAutoStyleId(levelNum, nodeId, autoStyleIdLiYangID, goodsInfoAutoStyle);
        }

        // 调用货品更新方法
        int num =this.goodsProductService.updateProductAutoStyle(goodsInfoId, autoStyleIdLiYangID.toString(),goodsInfoAutoStyle.toString());
        if(StringUtils.isEmpty(goodsId)){
            GoodsProduct goodsProduct =goodsProductService.queryProductByGoodsId(Long.valueOf(goodsInfoId));
            if(goodsProduct!=null){
                goodsId = goodsProduct.getGoodsId().toString();
            }
        }
        // 更新索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(Long.valueOf(goodsId));
        if(num>0){
            // 记录当前登录者的操作日志
            OperaLogUtil.addOperaLog(request, operaName, ValueUtil.UPDATEPRODUCTAUTOSTYLE,
                    request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + operaName);

        }
        map.put("success",true);

        return map;
    }*/

    /**
     * 更新货品状态
     *
     * @param goodsProduct
     *            更新的货品实体
     * @param flag
     *            标记,是否首页进入
     */
    @RequestMapping("/updateProSta")
    public ModelAndView updateProductSta(GoodsProduct goodsProduct, Integer flag, HttpServletRequest request, PageBean pb) {
        GoodsProductVo goodsProductVo = this.goodsProductService.queryByPrimaryId(goodsProduct.getGoodsInfoId());
        /* 保存服务支持信息 */
        GoodsProduct pro = new GoodsProduct();
        try {
            // 给货品id赋值
            pro.setGoodsInfoId(goodsProduct.getGoodsInfoId());
            // 给商品id赋值
            pro.setGoodsId(goodsProduct.getGoodsId());
            // 判断货品是否上架列是否为空
            // 如果不为空就给货品上架列赋值
            if (null != goodsProduct.getGoodsInfoAdded() && !"".equals(goodsProduct.getGoodsInfoAdded())) {
                pro.setGoodsInfoAdded(goodsProduct.getGoodsInfoAdded());
                // 判断是否是上架操作
                if ("1".equals(goodsProduct.getGoodsInfoAdded())) {
                    // 实例化一个商品对象
                    Goods goods = new Goods();
                    // 获取页面中的值并设置商品的商品id
                    goods.setGoodsId(goodsProductVo.getGoodsId());
                    // 获取页面中的值并设置商品的是否上架
                    goods.setGoodsAdded("1");
                    // 商品上架
                    this.goodsService.updateGoodsDesc(goods);
                }
            }
            // 如果列表也显示不为空
            // 就给货品列表页显示赋值
            if (null != goodsProduct.getShowList() && !"".equals(goodsProduct.getShowList())) {
                pro.setShowList(goodsProduct.getShowList());
            }
            // 如果手机端是否显示不为空
            // 就给手机端是否显示赋值
            if (null != goodsProduct.getShowMobile() && !"".equals(goodsProduct.getShowMobile())) {
                pro.setShowMobile(goodsProduct.getShowMobile());
            }
            // 执行更新货品方法
            this.goodsProductService.updateProduct(pro, (String) request.getSession().getAttribute(ValueUtil.NAME), null, null, null);
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsProduct.getGoodsId());
            if (null == flag && null != goodsProductVo.getGoodsInfoName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.UPDATEPRODUCTINFO, request.getSession()
                        .getAttribute(ValueUtil.OPERAPATH) + "货品名称【" + goodsProductVo.getGoodsInfoName() + "】,用户名：" + request.getSession().getAttribute(ValueUtil.NAME));
                // 重定向到queryAllByGoodsId.htm中
                return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTBYGOODSID + pro.getGoodsId() + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)
                        + "&pageNo=" + pb.getPageNo() + "&pageSize=" + pb.getPageSize()));
            } else {
                // 重定向到queryProductListBySomeParam.htm中
                return new ModelAndView(new RedirectView(PathUtil.QUERYPRODUCTLISTBYSOMEPARAM + flag + ValueUtil.TOKENPARAM2 + CSRFTokenManager.getTokenFromRequest(request)
                        + "&pageNo=" + pb.getPageNo() + "&pageSize=" + pb.getPageSize()));
            }
        } finally {
            pro = null;
        }
    }

    /**
     * 查询所有的货品详细信息
     *
     * @param pb
     * @return
     */
    @RequestMapping("/queryAllDetailProduct")
    @ResponseBody
    public PageBean queryAllProduct(Long groupId, PageBean pb) {
        // 验证货品ID 输出操作日志
        if (null != groupId) {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYALLDETAILPRODUCTINFO + "组合ID为:" + groupId);
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYALLDETAILPRODUCTINFO);
        }
        // 返回结果
        return this.goodsProductService.queryProductDetailInfoByPageBean(groupId, pb);
    }

    /**
     * 根据分类ID和品牌ID查询货品列表
     *
     * @param request
     * @param pb
     * @param productNo
     *            当前页
     * @param marketType
     *            促销类型
     * @param sTime
     *            开始时间
     * @param eTime
     *            结束时间
     * @param productName
     *            商品名称
     * @param brandId
     *            品牌ID
     * @param catId
     *            分类ID
     * @return PageBean
     */
    @RequestMapping("/queryProductForCoupon")
    @ResponseBody
    public PageBean queryProductForCoupon(HttpServletRequest request, PageBean pb, String productNo, Long marketType, String sTime, String eTime, String productName, Long brandId,
            Long catId, Integer haveStock, String searchText,String flag) {
        String productNoNew = productNo;
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPRODUCTFORCOUPONINFO);
        // try {
        // if(productName != null && !productName.equals("")) {
        // productName = new String(productName.getBytes("ISO-8859-1"),
        // "UTF-8");
        // }
        // } catch (UnsupportedEncodingException e) {
        // //日志
        // LOGGER.error("字符转换失败！",e);
        // }
        // if(searchBean.getGoodsName() == null &&
        // searchBean.getGoodsName().equals("")) {
        //
        // }else{
        // try {
        // productName = URLDecoder.decode(searchBean.getGoodsName(), "UTF-8");
        // } catch (UnsupportedEncodingException e) {
        // e.printStackTrace();
        // }
        // }
        // 获取分类id并保存
        String[] cateIds = request.getParameterValues("cateId");
        // 获取品牌id并保存
        String[] brandIds = request.getParameterValues("brandIds");
        // 中文转换
        if (productNoNew != null && !"".equals(productNoNew)) {
            try {
                // 把获取的货品编号进行转码
                productNoNew = java.net.URLDecoder.decode(productNoNew, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                // 日志
                LOGGER.error("字符转换失败！", e);
            }
        }
        if (marketType != null) {
            // 执行查询货品信息方法
            return this.goodsProductService.queryProductForCoupon(pb, cateIds, brandIds, (Long) request.getSession().getAttribute(THIRDID), productNoNew, marketType, sTime, eTime,
                    searchText);
        }
        // 执行查询货品信息方法
        return this.goodsProductService.queryProductForCouponLife(pb, cateIds, brandIds, (Long) request.getSession().getAttribute(THIRDID), productNoNew, productName, brandId, catId,
                haveStock,flag);
    }



    /**
     * 新促销 查询boss货品
     *
     * @param request
     * @param pb
     * @param searchBean
     * @param marketType
     * @return
     */
    @RequestMapping("/newqueryProductForCoupon")
    @ResponseBody
    public PageBean newqueryProductForCoupon(HttpServletRequest request, PageBean pb, GoodsSearchBean searchBean, Long marketType) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPRODUCTFORCOUPONINFO);
        // 中文转换
        if (searchBean.getGoodsName() != null && !"".equals(searchBean.getGoodsName())) {
            try {
                // 把获取的商品名称进行转码
                String goodsName = java.net.URLDecoder.decode(searchBean.getGoodsName(), "UTF-8");
                // 设置商品名称
                searchBean.setGoodsName(goodsName);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(""+e);
            }
        }
        /*
         * if (marketType != null) { return
         * this.goodsProductService.queryProductForCoupon(pb, null, null, (Long)
         * request.getSession().getAttribute(THIRDID), null, marketType, null,
         * null, null); }
         */
        // 返回结果
        return this.goodsProductService.queryBossProductList(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, marketType);
    }

    /**
     * 根据分类ID和品牌ID查询货品列表
     *
     * @param pb
     * @param cateId
     * @param brandId
     * @return
     * @author NINGPAI-WangHaiYang
     */
    @RequestMapping("/queryProductForChannel")
    public ModelAndView queryProductForChannel(PageBean pb, Long cateId, Long brandId, String goodsInfoName, HttpServletRequest request) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        String[] cateIds = null;
        // 判断分类id是否为空并且判断是否大于0
        if (null != cateId && cateId > 0) {
            cateIds = new String[] { cateId.toString() };
        }
        String[] brandIds = null;
        // 判断品牌Id是否为空
        if (null != brandId) {
            brandIds = new String[] { brandId.toString() };
        }
        // 把分类id放进map中
        map.put(CATEID, cateId);
        // 把品牌id放map中
        map.put("brandId", brandId);
        // 把货品名称放进map中
        map.put("goodsInfoName", goodsInfoName);
        // 把查询到的货品信息放进map集合中
        map.put("pb",
                goodsProductService.queryProductForCouponLife(pb, cateIds, brandIds, (Long) request.getSession().getAttribute(THIRDID), null, goodsInfoName, null, null, null,null));
        // 把查询出来的品牌信息放进map集合中
        map.put("brandList", this.goodsBrandService.queryAllBrand());
        // 返回结果
        return new ModelAndView(ValueUtil.CHOOSEPRODUCE, "map", map);
    }

    // 商品品牌Service结束

    /**
     * 根据货品ID查询货品
     *
     * @param productId
     *            货品Id {@link java.lang.Long}
     */
    @RequestMapping("/queryProductViewVoByProductId")
    public ModelAndView queryProductViewVoByProductId(Long productId) {
        // 根据主键获取单个的货品信息
        GoodsProductDetailViewVo goodsProductDetailViewVo = this.goodsProductService.queryViewVoByProductId(productId);
        // 非空验证 货品名称
        if (null != goodsProductDetailViewVo.getGoodsInfoName()) {
            // 打印日志
            LOGGER.info("获取【" + goodsProductDetailViewVo.getGoodsInfoName() + "】的详细信息！");
        }
        // 返回结果
        return new ModelAndView(ValueUtil.PRODUCTDETAIL).addObject("productDetail", goodsProductDetailViewVo);
    }

    /**
     * 验证货品编号是否可用
     *
     * @param productNo
     *            待验证的货品编号
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checkProductNo")
    @ResponseBody
    public boolean checkProductNo(String productNo) {
        // 打印日志
        LOGGER.info(ValueUtil.CHECKPRODUCTNOINFO + ",编号为:" + productNo);
        // 返回结果
        return this.goodsProductService.checkProuctNo(productNo);
    }

    /**
     * 根据参数验证货品是否可以添加
     *
     * @param paramLength
     *            参数长度
     * @param paramIds
     *            参数数组
     * @param goodsId
     *            商品ID
     * @return 可用返回true 不可用返回false
     */
    @RequestMapping("/checkParam")
    @ResponseBody
    public boolean checkParam(Long paramLength, Long[] paramIds, Long goodsId) {
        // 打印日志
        LOGGER.info(ValueUtil.CHECKPARAMINFO);
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("params", paramIds);
            map.put("paramLength", paramLength);
            map.put(GOODSID, goodsId);
            // 验证所选的参数是否已经生成了货品
            return this.goodsProductService.checkProductParams(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据查询标记查询货品行数
     *
     * @param flag
     *            查询标记 1:所有货品 2：已下架货品 3：缺货货品 4：库存报警货品
     * @return 查询到的个数
     */
    @RequestMapping("/queryStockWarnCount")
    @ResponseBody
    public Integer queryStockWarnCount(Integer flag, SelectBean selectBean) {
        // 查询标记
        if (null != flag) {
            if (flag == 1) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYSTOCKWARNCOUNTINFO + ",标记是：所有商品");
            } else if (flag == 2) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYSTOCKWARNCOUNTINFO + ",标记是：已下架商品");
            } else if (flag == 3) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYSTOCKWARNCOUNTINFO + ",标记是：缺货商品");
            } else if (flag == 4) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYSTOCKWARNCOUNTINFO + ",标记是：库存报警商品");
            }
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYSTOCKWARNCOUNTINFO);
        }
        // 返回结果
        return this.goodsProductService.queryStockWarnCount(flag, selectBean);
    }

    /**
     * 根据查询标记查询货品列表
     *
     * @param flag
     *            查询标记 1:所有货品 2：已下架货品 3：缺货货品 4：库存报警货品
     * @return
     */
    @RequestMapping("/queryProductListBySomeParam")
    public ModelAndView queryProductListBySomeParam(Integer flag, PageBean pageBean, SelectBean selectBean) {
        // 查询标记
        if (null != flag) {
            if (flag == 1) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYPRODUCTLISTBYSOMEPARAMINFO + ",标记是：所有商品");
            } else if (flag == 2) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYPRODUCTLISTBYSOMEPARAMINFO + ",标记是：已下架商品");
            } else if (flag == 3) {
                LOGGER.info(ValueUtil.QUERYPRODUCTLISTBYSOMEPARAMINFO + ",标记是：缺货商品");
            } else if (flag == 4) {
                // 打印日志
                LOGGER.info(ValueUtil.QUERYPRODUCTLISTBYSOMEPARAMINFO + ",标记是：库存报警商品");
            }
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYPRODUCTLISTBYSOMEPARAMINFO);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pb", this.goodsProductService.queryProductListBySomeParam(flag, pageBean, selectBean));
        map.put("flag", flag);
        map.put(SELECTBEAN, selectBean);
        // 返回结果
        return new ModelAndView(ValueUtil.WELPRODUCTLIST, "map", map);
    }

    /**
     * 导出货品列表
     *
     * @throws Exception
     */
    @RequestMapping("/exportProductList")
    public void exportProductList(Long goodsId, SelectBean selectBean, HttpServletResponse response) {
        // 根据主键 获取单个的商品对象
        GoodsMoifiedVo goods = goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 商品名称 非空验证
        if (null != goods.getGoodsName()) {
            // 打印日志
            LOGGER.info("获取【" + goods.getGoodsName() + "】下面所有的货品信息！");
        }
        try {
            ExportGoodsList.exportProductList(this.goodsProductService.queryAllProductByGoodsIdForExport(goodsId, selectBean), response);
        } catch (Exception e) {
            // 打印日志
            LOGGER.error("导出货品列表错误" + e);
        }
    }

    /**
     * 导出当前显示的货品页列表
     *
     * @param goodsId
     *            商品ID
     * @param pb
     *            分页帮助Bean
     * @param selectBean
     *            查询参数Bean
     * @param response
     *            相应对象
     * @throws Exception
     */
    @RequestMapping("/exportProductPage")
    public void exportProductPage(Long goodsId, PageBean pb, SelectBean selectBean, HttpServletResponse response) {
        // 打印日志
        LOGGER.info(ValueUtil.EXPORTPRODUCTPAGEINFO);
        try {
            ExportGoodsList.exportProductList(this.goodsProductService.queryByGoodsId(goodsId, pb, selectBean).getList(), response);
        } catch (Exception e) {
            // 打印日志
            LOGGER.error("导出当前显示的货品列表错误" + e);
        }
    }

    /**
     * 根据选中的货品导出Excel
     *
     * @param productIds
     * @param response
     * @throws Exception
     */
    @RequestMapping("/exportProductByChecked")
    public void exportProductByChecked(Long[] productIds, HttpServletResponse response) {
        // 打印日志
        LOGGER.info(ValueUtil.EXPORTPRODUCTBYCHECKEDINFO);
        try {
            ExportGoodsList.exportProductList(this.goodsProductService.queryAllProductByProductIdsForExport(productIds), response);
        } catch (Exception e) {
            // 打印日志
            LOGGER.error("导出Excel错误" + e);
        }
    }

    /**
     * 根据分类ID和品牌ID查询BOSS货品列表
     *
     * @param pb
     * @param
     * @param productNo
     * @return
     */
    @RequestMapping("/queryProductForCouponByThird")
    @ResponseBody
    public PageBean queryProductForCouponByThird(HttpServletRequest request, PageBean pb, Long productNo) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPRODUCTFORCOUPONINFO);
        // 获取分类id的值并保存
        String[] cateIds = request.getParameterValues(CATEID);
        // 获取品牌id的值并保存
        String[] brandIds = request.getParameterValues("brandIds");
        // 返回结果
        return this.goodsProductService.queryProductForCouponByThird(pb, cateIds, brandIds, (long) 0, productNo);
    }

    /**
     * 根据商品id数组查询数据
     *
     * @param pb
     * @param goodsInfoIds
     * @return
     */
    @RequestMapping("/queryProductForCouponByGoodsIds")
    @ResponseBody
    public PageBean queryProductForCouponByGoodsIds(HttpServletRequest request, PageBean pb, Long[] goodsInfoIds) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYPRODUCTFORCOUPONINFO);
        // 返回结果
        return this.goodsProductService.queryProductForCouponByGoodsInfoIds(pb, goodsInfoIds);
    }

    /**
     * 新流程添加货品
     *
     * @param product
     *            需要添加的货品
     * @param ware
     *            仓库数组 数组
     * @param wareStock
     *            仓库库存数组
     * @param warePrice
     *            仓库价格数组
     * @param warePrice
     *            仓库会员价格数组
     */
    @RequestMapping("/newUploadProduct")
    @ResponseBody
    public int newUploadProduct(@Valid GoodsProduct product, HttpServletRequest request, Long[] ware, Long[] wareStock, BigDecimal[] warePrice, BigDecimal[] wareVipPrice, String addedTime) {
        // 获取规格id并保存
        String[] specIds = request.getParameterValues(SPECID);
        // 获取规格值id并保存
        String[] specDetailId = request.getParameterValues(SPECDETAILID);
        // 获取规格描述并保存
        String[] specRemark = request.getParameterValues(SPECREMARK);
        // 获取图片并保存
        String[] images = request.getParameterValues("image");
        String[] supportId = request.getParameterValues(SUPPORT);
       /* QpAutoOem qpAutoOem = null;
        if(product.getGoodsInfoAutoPartsType()!=null&&"1".equals(product.getGoodsInfoAutoPartsType())){
             qpAutoOem = qpAutoOemService.selectByAutoOemCode(product.getGoodsInfoOem());
        }
        if(qpAutoOem != null){
            product.setGoodsInfoAutoStyle(qpAutoOem.getAutoStyleId());
            product.setAutoStyleIdLiYangID(qpAutoOem.getAutoStyleIdLiyangId());
        }*/
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("wareId", ware);
            map.put("productStocks", wareStock);
            map.put("productPrices", warePrice);
            map.put("productVipPrices", wareVipPrice);
            /* 保存规格值别名 */
            map.put(SPECREMARK, specRemark);
            /* 设置货品所属的boss商家 */
            product.setIsThird("0");
            product.setThirdId(0L);
            product.setThirdName("BOSS");
            /* 如果货品状态是定时上架,就设置上架时间 */
            if ("4".equals(product.getGoodsInfoAdded())) {
                product.setGoodsInfoAddedTime(UtilDate.stringToDate(addedTime));
            }
            int newId = this.goodsProductService.saveProduct(product, (String) request.getSession().getAttribute(ValueUtil.NAME), specIds, specDetailId, map);
            // 非空验证 货品名称
            if (1 == newId && null != product.getGoodsInfoName()) {
                LOGGER.info("保存【" + product.getGoodsInfoName() + "】成功");
            }
            /* 保存分仓信息 */
            this.productWareService.calcProductWare(Long.parseLong(String.valueOf(newId)), wareStock, warePrice, wareVipPrice, ware);
            /* 保存图片 */
            this.goodsPub.getGoodsImageService().batchSaveImage(images, newId, (String) request.getSession().getAttribute(ValueUtil.NAME));
            /* 保存服务支持 */
            this.goodsProductSuppService.batchInsert(supportId, newId);

            /**
             * 插入索引
             */
            goodsElasticSearchService.insertOneGoodsIndexToEs(product.getGoodsId());
            return newId;
        } finally {
            supportId = null;
            images = null;
            specIds = null;
            specDetailId = null;
            map = null;
        }

    }

    /**
     * 查询商品销售排行
     *
     * @param pageBean
     * @param startTime
     * @param endTime
     * @param request
     * @return
     */
    @RequestMapping("/goodsProductSalesRank")
    public ModelAndView queryGoodsProductSalesRank(PageBean pageBean, String startTime, String endTime, HttpServletRequest request) {
        request.setAttribute("startTime", startTime);
        request.setAttribute("endTime", endTime);
        ModelAndView mav = new ModelAndView();
        mav.setViewName(PathUtil.GOODSPRODUCTSALESRANK);
        mav.addObject("pageBean", goodsProductService.queryGoodsProductSalesRank(pageBean, startTime, endTime));
        return mav;
    }

    /**
     * 添加选中的货品
     *
     * @param productIds
     * @param response
     * @throws Exception
     */
    @RequestMapping("/chooseProductByChecked")
    @ResponseBody
    public List<Object> chooseProductByChecked(String[] productIds, HttpServletResponse response) {
        Long[] proIds = null;
        if (productIds != null && productIds.length > 0) {
            proIds = new Long[productIds.length];
            for (int i = 0; i < productIds.length; i++) {
                proIds[i] = Long.parseLong(productIds[i]);
            }
        }
        // 返回结果
        return this.goodsProductService.queryAllProductByProductIdsForExport(proIds);
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public GoodsOpenSpecService getGoodsOpenSpecService() {
        return goodsOpenSpecService;
    }

    @Resource(name = "GoodsOpenSpecService")
    public void setGoodsOpenSpecService(GoodsOpenSpecService goodsOpenSpecService) {
        this.goodsOpenSpecService = goodsOpenSpecService;
    }

    public GoodsPub getGoodsPub() {
        return goodsPub;
    }

    @Resource(name = "goodsPub")
    public void setGoodsPub(GoodsPub goodsPub) {
        this.goodsPub = goodsPub;
    }

    public EmailUtils getEmailUtils() {
        return emailUtils;
    }

    @Resource(name = "emailUtilsSites")
    public void setEmailUtils(EmailUtils emailUtils) {
        this.emailUtils = emailUtils;
    }

    public DistrictService getDistrictService() {
        return districtService;
    }

    @Resource(name = "DistrictService")
    public void setDistrictService(DistrictService districtService) {
        this.districtService = districtService;
    }

}
