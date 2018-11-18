/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.EmailUtils;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.third.goods.util.ThirdPathUtil;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import com.ningpai.util.UtilDate;

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

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方货品控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月14日 下午3:25:06
 * @version 1.0
 */
@Controller
public class ThirdProductController {

    private static final String SPECID = "specId";
    private static final String SPECDETAILID = "specDetailId";
    private static final String THIRDID = "thirdId";
    private static final String SUPPORTIDS = "support";

    public static final MyLogger LOGGER = new MyLogger(ThirdProductController.class);

    // 货品信息Service
    private GoodsProductService goodsProductService;

    // 商品规格值Service
    private GoodsSpecService goodsSpecService;

    // 商品图片Service
    private GoodsImageService goodsImageService;

    // 商品开启规格的Service
    private GoodsOpenSpecService goodsOpenSpecService;

    // 商品Service
    private GoodsService goodsService;

    // 第三方商品审核接口
    private GoodsAuditService goodsAuditService;

    // 开关控制dao接口
    private GetOnOffMapper getOnOffMapper;

    // 会员Service
    private CustomerServiceMapper customerServiceMapper;

    // 会员商品关注接口
    private CustomerFollowServiceMapper customerFollowServiceMapper;

    /* 公共接口 */
    private GoodsPub goodsPub;

    @Resource(name = "GoodsProductSuppService")
    private GoodsProductSuppService goodsProductSuppService;

    private EmailUtils emailUtils;

    // 商品索引
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;


    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    /**
     * 根据商品ID查询第三方货品管理列表
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param selectBean
     *            查询辅助Bean {@link com.ningpai.util.SelectBean}
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/thirdProductManager")
    public ModelAndView thirdProductManager(Long goodsId, PageBean pb, String flag, SelectBean selectBean, Long goodsAddedSta) throws UnsupportedEncodingException {
        // 进行中文转码操作
        selectBean.setSearchText(new String(selectBean.getSearchText().getBytes("ISO-8859-1"), ConstantUtil.UTF));
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取要现在到页面的 分页对象
            map.put("pb", this.goodsProductService.queryByGoodsId(goodsId, pb, selectBean));
            // 根据商品ID查询开启的规格集合
            map.put("specs", this.goodsOpenSpecService.queryOpenListByGoodsId(goodsId));
            // 商品ID
            map.put("goodsId", goodsId);
            // 状态
            map.put("flag", flag);
            // 商品状态
            map.put("goodsAddedSta", goodsAddedSta);
            // 要实现的分页对象
            map.put("selectBean", selectBean);
            // 获取审核商品开关标记
            map.put("isThirdAuditUsed", this.getOnOffMapper.getOnOffFlag());
            //所有的服务支持集合
            map.put("allProductSuppList", this.serviceSupportMapperService.selectAll());
            // 设置要跳转页面 以及要在页面显示的map集合的数据
            return new ModelAndView("goods/thirdProductManager", "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 保存货品信息
     * 
     * @param product
     *            需要保存的货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest request}
     */
    @RequestMapping("/saveThirdProduct")
    public ModelAndView saveThirdProduct(GoodsProduct product, HttpServletRequest request) {
        // 规格ID
        String[] specIds = request.getParameterValues(SPECID);
        // 规格的详细信息
        String[] specDetailId = request.getParameterValues(SPECDETAILID);
        try {
            // 是否为第三方商品
            product.setIsThird("1");
            // 商家ID
            product.setThirdId((Long) request.getSession().getAttribute(THIRDID));
            // 商家名称
            product.setThirdName((String) request.getSession().getAttribute("storeName"));
            // 添加货品信息
            this.goodsProductService.saveProduct(product, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), specIds, specDetailId, null);
            // 设置跳转的路径和要显示页面的数据
            return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + product.getGoodsId()));
        } finally {
            specIds = null;
            specDetailId = null;
        }
    }

    /**
     * 更新货品信息
     *
     * @param goodsProduct
     *            待更新的货品信息实体{@link com.ningpai.goods.bean.GoodsProduct}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest}
     */
    @RequestMapping("/updateThirdProduct")
    public ModelAndView updateThirdProduct(GoodsProduct goodsProduct, HttpServletRequest request, String oldPrice) {
        // 商家id
        Long thirdId = (Long) request.getSession().getAttribute("thirdId");
        Goods  goodthird = goodsService.queryGoodsByGoodsId(goodsProduct.getGoodsId());
            Long goodsBelo = null;
            Long goodsInfoThirdId= null;
        if (goodthird != null) {
            goodsBelo = goodthird.getGoodsBelo();
        }
        GoodsProduct product = goodsProductService.queryProductByGoodsId(goodsProduct.getGoodsInfoId());
        if(goodsProduct!=null){
            goodsInfoThirdId = product.getThirdId();
        }
        if (thirdId.equals(goodsBelo)&&thirdId.equals(goodsInfoThirdId)) {
            // 需要更新的规格id数组
            String[] specIds = request.getParameterValues(SPECID);
            // 需要更新的详细信息数组
            String[] specDetailId = request.getParameterValues(SPECDETAILID);
            //服务支持
            String[] supportIds = request.getParameterValues(SUPPORTIDS);
            //删除货品下的服务
            goodsProductSuppService.delAll(goodsProduct.getGoodsInfoId());
            if(supportIds!=null&& supportIds.length>0){
              //添加货品服务
                goodsProductSuppService.batchInsert(supportIds,  goodsProduct.getGoodsInfoId().intValue());
            }

            // 获取审核商品开关标记
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            if (Integer.parseInt(isThirdAuditUsed) == 1) {
                goodsProduct.setGoodsInfoAdded("0");
                goodsProduct.setAuditStatus("1");
            } else {
                // goodsProduct.setGoodsInfoAdded("1");
                goodsProduct.setAuditStatus("3");
            }
            try {
                // 更新货品信息
                this.goodsProductService.updateProduct(goodsProduct, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), specIds, specDetailId,
                        null);
                // 判断商品价格有没有降价
                BigDecimal oldprices = new BigDecimal(oldPrice);
                // 要操作的商品ID
                Long goodsInfoId = goodsProduct.getGoodsInfoId();
                // 判断关注的商品是否降价了
                if (oldprices.compareTo(goodsProduct.getGoodsInfoPreferPrice()) == 1) {
                    // 关注会员信息
                    List<CustomerFollow> customerIds = new ArrayList<CustomerFollow>();
                    // 查看关注这个货品的所有会员信息
                    customerIds = customerFollowServiceMapper.selectSendId(goodsInfoId);
                    // 遍历所有关注的会员ID
                    for (int a = 0; a < customerIds.size(); a++) {
                        // 单个的会员关注信息
                        CustomerFollow customerIdd = customerIds.get(a);
                        // 获取单个的会员id
                        Long customerId = customerIdd.getCustomerId();
                        // 获取单个的会员详细信息
                        CustomerInfo infoEmails = customerServiceMapper.email(customerId);
                        // 获取会员的手机号码
                        CustomerInfo mobiles = customerServiceMapper.mobile(customerId);
                        // 获取会员的邮箱
                        String infoEmail = infoEmails.getInfoEmail();
                        // 会员的手机号码
                        String mobile = mobiles.getInfoMobile();
                        if (infoEmail != null) {
                            // 发送降价信息到会员的邮箱
                            emailUtils.sendEmails(request, infoEmail, goodsProduct);
                        }
                        if (mobile != null) {
                            // 发送降价信息到会员的手机
                            emailUtils.sendPosts(request, goodsProduct, mobile);
                        }
                    }
                }
                // 修改索引
                goodsElasticSearchService.updateOneGoodsIndexToEs(goodsProduct.getGoodsId());
                // 设置跳转的页面 返回要在页面显示的数据
                return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsProduct.getGoodsId()));
            } finally {
                specIds = null;
                specDetailId = null;
            }
        }else{
            return null;
        }
       
    }

    /**
     * 批量上传货品图片
     *
     * @param productId
     *            货品ID {@link Long}
     * @param productId
     *            商品ID {@link Long}
     * @param request
     *            会话
     * @param request2
     *            包含上传文件的会话
     * @return 视图
     */
    @RequestMapping("/saveThirdProductImage")
    @ResponseBody
    public GoodsImage saveThirdProductImage(Long productId, HttpServletRequest request, MultipartHttpServletRequest request2) {
        // 获取所有的File控件
        List<MultipartFile> fileList = request2.getFiles("filedata");

        // 到Service进行添加
        return this.goodsImageService.uploadImage(productId, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), fileList, request);
    }

    /**
     * 更新货品图片
     *
     * @param productId
     *            货品ID {@link Long}
     * @param goodsId
     *            商品ID {@link Long}
     * @param request
     *            会话
     * @param request2
     *            包含上传文件的会话
     * @return 视图
     */
    @RequestMapping("/updateThirdProductImage")
    public ModelAndView updateThirdProductImage(Long goodsImgId, Long productId, Long goodsId, HttpServletRequest request, HttpServletResponse response,
            MultipartHttpServletRequest request2) {
        // 设置默认图片
        String defaultImage = request.getParameter("defaultImage");
        goodsImageService.setDefaultImage(productId, goodsImgId);
        // 获取删除的图片的ID的数组
        String[] delIamges = request.getParameterValues("delImages");
        try {
            // 删除
            this.goodsImageService.batchDelImage(delIamges, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
            // 设置默认图片
            this.goodsImageService.setDefaultImage(productId, defaultImage, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
            // 获取所有的File控件
            List<MultipartFile> fileList = request2.getFiles("filedata");
            // 到Service进行添加
            this.goodsImageService.uploadImage(productId, (String) request.getSession().getAttribute(ValueUtil.NAME), fileList, request);
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
            return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
        } finally {
            defaultImage = null;
            delIamges = null;
        }
    }

    /**
     * 删除货品信息控制器
     *
     * @param goodsId
     *            货品信息所属的商品ID {@link Long}
     * @param productId
     *            需要删除的货品信息的ID {@link Long}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest request}
     */
    @RequestMapping("/delThirdProduct")
    public ModelAndView delThirdProduct(Long goodsId, Long productId, HttpServletRequest request) {
        // 根据ID删除单个货品信息
        this.goodsProductService.delThirdProductByProductId(productId, (Long) request.getSession().getAttribute(THIRDID),
                ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());

        // 删除索引
        goodsElasticSearchService.deleteGoodsIndexToEs(productId);
        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
    }

    /**
     * 批量删除货品信息
     *
     * @param goodsId
     *            删除的货品信息的所属商品ID {@link Long}
     * @param productIds
     *            待删除的货品ID的数组 {@link Long}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest}
     */
    @RequestMapping("/batchDelThirdProduct")
    public ModelAndView batchDelThirdProduct(Long goodsId, Long[] productIds, HttpServletRequest request) {
        // 批量删除货品信息
        this.goodsProductService.batchDelProduct(productIds, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
        // 删除索引
        for (Long productId : productIds) {
            // 删除索引
            goodsElasticSearchService.deleteGoodsIndexToEs(productId);
        }

        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
    }

    /**
     * 批量上架货品信息
     *
     * @param goodsId
     *            删除的货品信息的所属商品ID {@link Long}
     * @param productId
     *            待删除的货品ID的数组 {@link Long}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest}
     * @return
     */
    @RequestMapping("/batchUploadThirdProduct")
    public ModelAndView batchUploadThirdProduct(Long goodsId, Long[] productId, HttpServletRequest request, String auditStatus) {
        String auditStatusNew = auditStatus;
        // 获取审核商品开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        if (Integer.parseInt(isThirdAuditUsed) == 1) {
            auditStatusNew = isThirdAuditUsed;
            // 批量审核上架
            this.goodsProductService.batchAuditUploadProduct(((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), productId, 1, auditStatusNew);
        } else {
            // 批量上下架货品
            this.goodsProductService.batchUploadProduct(((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), productId, 1);
        }
        // 重定向
        // 修改索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
    }

    /**
     * 批量下架货品信息
     *
     * @param goodsId
     *            删除的货品信息的所属商品ID {@link Long}
     * @param productId
     *            待删除的货品ID的数组 {@link Long}
     * @param request
     *            请求对象 {@link javax.servlet.http.HttpServletRequest}
     * @return
     */
    @RequestMapping("/batchDownThirdProduct")
    public ModelAndView batchDownThirdProduct(Long goodsId, Long[] productId, HttpServletRequest request) {
        this.goodsProductService.batchUploadProduct(((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), productId, 0);
        // 修改索引
        goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
    }

    /**
     * 根据第三方货品ID查询修改货品时需要的货品信息Vo
     *
     * @param thirdProductId
     *            第三方货品ID {@link Long}
     * @return 查询到的货品Vo信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    @RequestMapping("/queryThirdProductVoByProductId")
    @ResponseBody
    public Map<String,Object> queryThirdProductVoByProductId(Long thirdProductId) {
        Map<String ,Object> map=new HashMap<>();
        //该货品的服务支持
        map.put("productSuppList",this.goodsProductSuppService.queryAllSuppByProductId(thirdProductId));
        map.put("product", this.goodsProductService.queryByPrimaryId(thirdProductId));
        return map;
    }

    /**
     * 批量生成货品
     * 
     * @param goodsId
     *            所属的商品ID {@link Long}
     * @param request
     *            请求对象
     * @return 货品管理列表
     */
    @RequestMapping("/thirdCreateProduct")
    public ModelAndView thirdCreateProduct(Long goodsId, HttpServletRequest request) {
        this.goodsService.saveProductWhenClickBatchCreate(goodsId, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());

        // 生成索引
        goodsElasticSearchService.insertOneGoodsIndexToEs(goodsId);

        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + goodsId));
    }

    /**
     * 新流程添加货品
     * 
     * @param product
     *            需要添加的货品
     * @param request
     *            仓库数组 数组
     * @param addedTime
     *            仓库库存数组
     * @param addedTime
     *            仓库价格数组
     */
    @RequestMapping("/tNewUploadProduct")
    @ResponseBody
    public ModelAndView newUploadProduct(@Valid GoodsProduct product, HttpServletRequest request, String addedTime) {
        // 规格id集合
        String[] specIds = request.getParameterValues(SPECID);
        // 货品的规格详细信息
        String[] specDetailId = request.getParameterValues(SPECDETAILID);
        // 规格描述信息
        String[] specRemark = request.getParameterValues("specRemark");
        // 上传的商品图片
        String[] images = request.getParameterValues("image");
        // 支持信息的ids
        String[] supportId = request.getParameterValues("support");
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            /* 保存规格值别名 */
            map.put("specRemark", specRemark);
            /* 设置货品所属的boss商家 */
            product.setIsThird("1");
            // 商家ID
            product.setThirdId((Long) request.getSession().getAttribute(THIRDID));
            // 商家的店铺名称
            product.setThirdName((String) request.getSession().getAttribute("storeName"));
            /* 如果货品状态是定时上架,就设置上架时间 */
            if ("4".equals(product.getGoodsInfoAdded())) {
                product.setGoodsInfoAddedTime(UtilDate.stringToDate(addedTime));
            }
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            if (Integer.parseInt(isThirdAuditUsed) == 1) {
                product.setGoodsInfoAdded("0");
                product.setAuditStatus("1");
            } else {
                product.setGoodsInfoAdded("1");
                product.setAuditStatus("3");
            }
            // 添加货品信息
            int newId = this.goodsProductService.saveProduct(product, (String) request.getSession().getAttribute(ValueUtil.NAME), specIds, specDetailId, map);
            /* 保存图片 */
            this.goodsPub.getGoodsImageService().batchSaveImage(images, newId, (String) request.getSession().getAttribute(ValueUtil.NAME));
            /* 保存服务支持 */
            this.goodsProductSuppService.batchInsert(supportId, newId);

            // 生成索引
            goodsElasticSearchService.insertOneGoodsIndexToEs(product.getGoodsId());
            return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDPRODUCTMAPAGER + ThirdValueBean.GOODSID + product.getGoodsId()));
        } finally {
            supportId = null;
            images = null;
            specIds = null;
            specDetailId = null;
            map = null;
        }

    }

    /**
     * 第三方货品列表
     */
    @RequestMapping("/thirdproducts")
    @ResponseBody
    public PageBean goodsManger(PageBean pb, GoodsSearchBean searchBean, HttpServletRequest request, String goodsInfoAdded,BigDecimal offValue) {
        String goodsName;
        // 中文转换
        if (searchBean.getGoodsName() != null && !"".equals(searchBean.getGoodsName())) {
            try {
                // 商品名称
                goodsName = java.net.URLDecoder.decode(searchBean.getGoodsName(), "UTF-8");
                // 设置商品名称
                searchBean.setGoodsName(goodsName);
                } catch (UnsupportedEncodingException e) {
                LOGGER.error(""+e);
            }
        }
        return goodsProductService.newQueryProductList(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean,offValue);
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

    public GoodsImageService getGoodsImageService() {
        return goodsImageService;
    }

    @Resource(name = "GoodsImageService")
    public void setGoodsImageService(GoodsImageService goodsImageService) {
        this.goodsImageService = goodsImageService;
    }

    public GoodsOpenSpecService getGoodsOpenSpecService() {
        return goodsOpenSpecService;
    }

    @Resource(name = "GoodsOpenSpecService")
    public void setGoodsOpenSpecService(GoodsOpenSpecService goodsOpenSpecService) {
        this.goodsOpenSpecService = goodsOpenSpecService;
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
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

    public GetOnOffMapper getGetOnOffMapper() {
        return getOnOffMapper;
    }

    @Resource(name = "GetOnOffMapper")
    public void setGetOnOffMapper(GetOnOffMapper getOnOffMapper) {
        this.getOnOffMapper = getOnOffMapper;
    }

    public GoodsAuditService getGoodsAuditService() {
        return goodsAuditService;
    }

    @Resource(name = "goodsAuditService")
    public void setGoodsAuditService(GoodsAuditService goodsAuditService) {
        this.goodsAuditService = goodsAuditService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    public CustomerFollowServiceMapper getCustomerFollowServiceMapper() {
        return customerFollowServiceMapper;
    }

    @Resource(name = "customerFollowServiceMapper")
    public void setCustomerFollowServiceMapper(CustomerFollowServiceMapper customerFollowServiceMapper) {
        this.customerFollowServiceMapper = customerFollowServiceMapper;
    }
}
