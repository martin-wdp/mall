package com.ningpai.site.goods.service.impl;

import com.ningpai.comment.bean.Comment;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.GoodsRelatedGoodsMapper;
import com.ningpai.goods.dao.GoodsReleExpandParamMapper;
import com.ningpai.goods.dao.GoodsReleParamMapper;
import com.ningpai.goods.dao.GoodsReleTagMapper;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsProductSuppService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.vo.GoodsRelatedGoodsVo;
import com.ningpai.marketing.bean.Groupon;
import com.ningpai.marketing.bean.Marketing;
import com.ningpai.marketing.bean.PreDiscountMarketing;
import com.ningpai.marketing.dao.GrouponMapper;
import com.ningpai.marketing.dao.PreDiscountMarketingMapper;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.controller.CustomerController;
import com.ningpai.site.customer.service.GoodsCommentService;
import com.ningpai.site.goods.bean.GoodsDetailBean;
import com.ningpai.site.goods.dao.GoodsProductMapper;
import com.ningpai.site.goods.service.GoodsCateService;
import com.ningpai.site.goods.service.GoodsProductService;
import com.ningpai.site.goods.util.ValueUtil;
import com.ningpai.site.goods.vo.GoodsCateVo;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.goods.vo.ListFinalBuyVo;
import com.ningpai.site.groupon.bean.GrouponUtil;
import com.ningpai.site.marketingrush.bean.MarketingRushUtil;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.CalcuStar;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 货品信息Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月20日 上午11:02:38
 * @version 1.0
 */
@Service("HsiteGoodsProductService")
public class GoodsProductServiceImpl implements GoodsProductService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CustomerController.class);

    private static final String CATID = "catId";
    private static final String ROWCOUNT = "rowCount";
    private static final String PRODUCTID = "productId";
    private static final String DISTINCTID = "distinctId";
    private static final String CHADDRESS = "chAddress";
    private static final String GOODSPREFERPRICE = "goodsPreferPrice";
    private static final String BRANDID = "brandId";

    private GoodsProductMapper goodsProductMapper;

    private GoodsCateService cateService;

    private GoodsReleTagMapper goodsReleTagMapper;

    private GoodsBrandService goodsBrandService;

    private GoodsReleExpandParamMapper expandParamMapper;

    private GoodsReleParamMapper goodsReleParamMapper;

    private GoodsRelatedGoodsMapper goodsRelatedGoodsMapper;

    private ProductWareService productWareService;

    private GoodsPub goodsPub;

    // Spring注入
    @Resource(name = "GoodsProductSuppService")
    private GoodsProductSuppService goodsProductSuppService;

    // Spring注入
    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    // Spring注入
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService addressService;

    // Spring注入
    @Resource(name = "auditService")
    private AuditService auditService;

    // Spring注入
    @Resource(name = "goodsCommentService")
    private GoodsCommentService commentService;

    // Spring注入
    @Resource(name = "PreDiscountMarketingMapper")
    private PreDiscountMarketingMapper preDiscountMarketingMapper;

    @Resource(name = "GrouponMapper")
    private GrouponMapper grouponMapper;

    /**
     * 新增积分兑换 对象
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#insertExchangeCusmomer(java.util.Map)
     */
    @Override
    public int insertExchangeCusmomer(Map<String, Object> map) {
        return this.goodsProductMapper.insertExchangeCusmomer(map);
    }

    /**
     * 根据商品ID查询货品列表
     *
     * @see com.ningpai.goods.service.GoodsProductService (java.lang.Long)
     */
    public List<Object> queryAllProductListByGoodsId(Long goodsId) {
        return this.goodsProductMapper.queryProductByGoodsId(goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     *
     * @see com.ningpai.goods.service.GoodsProductService (java.lang.Long)
     */
    public GoodsProductVo queryProductByProductId(Long productId) {
        return this.goodsProductMapper.queryPrductByProductId(productId);
    }

    /**
     * 根据分类ID查询货品信息的集合
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryTopSalesByCatIds
     *      (java.lang.Long, java.lang.Integer)
     */
    public List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByCatIds(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询最近一月该分类下的热销商品
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryHotSalesTopSix
     *      (java.lang.Long, java.lang.Integer)
     */
    public List<GoodsProduct> queryHotSalesTopSix(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryHotSalesByCatId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询最近一月该分类下同一价格的热销商品
     *
     * @param catId
     * @param rowCount
     * @param brandId
     * @return
     */
    @Override
    public List<GoodsProduct> queryHotSalesByCatIdandBrand(Long catId, Integer rowCount, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            map.put(BRANDID, brandId);
            return this.goodsProductMapper.queryHotSalesByCatIdandBrand(map);
        } finally {
            map = null;
        }
    }

    @Override
    public List<GoodsProduct> queryHotSalesByCatIdandPrice(Long catId, Integer rowCount, BigDecimal price) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            map.put(GOODSPREFERPRICE, price);
            return this.goodsProductMapper.queryHotSalesByCatIdandPrice(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据分类ID查询最新上架的货品
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryTopNewByCatIds
     *      (java.lang.Long, java.lang.Integer)
     */
    public List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount) {
        GoodsCateVo cate = this.cateService.queryCateById(catId);
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> catIds = new ArrayList<Long>();
        try {
            cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopNewInfoByCatIds(map);
        } finally {
            cate = null;
            map = null;
            catIds = null;
        }
    }

    /**
     * 查询详细
     *
     * @param productId
     * @param distinctId
     * @return GoodsDetailBean
     */
    public GoodsDetailBean queryDetailBeanByProductIdForGroupon(Long productId, Long distinctId) {
        GoodsDetailBean detailBean = new GoodsDetailBean();
        GoodsProductVo productVo;
        ProductWare productWare;
        try {
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
            // 如果不是第三方商品就去查分仓库存
            if ("0".equals(detailBean.getProductVo().getThirdId().toString()) && null != distinctId && distinctId > 0) {
                /* 根据选择的地区查询库存及价格信息 */
                productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
                /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                if (null != productWare) {
                    productVo = detailBean.getProductVo();
                    productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                    productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice());
                    productVo.setGoodsInfoStock(productWare.getWareStock());
                    detailBean.setProductVo(productVo);
                } else {
                    productVo = detailBean.getProductVo();
                    productVo.setGoodsInfoStock(0L);
                    detailBean.setProductVo(productVo);
                }
            }
            return detailBean;
        } finally {
            productVo = null;
            productWare = null;
            detailBean = null;
        }
    }

    /**
     * 不同地区货品价格以及库存不同 结算页面用
     *
//     * @see com.ningpai.site.goods.service.GoodsProductService#queryGoodsByproductIdAndDistinctId(java.lang.Long,
//     *      java.lang.Long, java.lang.Long,Marketing)
     */
    @Override
    public GoodsDetailBean queryGoodsByproductIdAndDistinctId(Long productId, Long distinctId, Long marketingId,Long goodsGroupId,List<Marketing> marketingList) {
        GoodsDetailBean detailBean = new GoodsDetailBean();
        GoodsProductVo productVo;
        ProductWare productWare;
        // 货品折扣率
        BigDecimal goodsrate = BigDecimal.ONE;
        BigDecimal goodsVipRate = BigDecimal.ONE;
        // 货品的价格中间变量
        BigDecimal goodsPrice = BigDecimal.ZERO;
        BigDecimal goodsVipPrice=BigDecimal.ZERO;//企业会员价
        Map<String, Object> para = new HashMap<>();
        String discountFlag = "";
        DecimalFormat myformat = null;
        myformat = new DecimalFormat("0.00");

        //单该货品同时参与了团购和折扣时,优先级团购优先 2015-12-09 wuyanbo 调整团购价位置
        /*if(goodsGroupId!=null){
            // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
            Marketing mark = marketingService.querySimpleMarketingById(goodsGroupId);
            if(mark!=null){
                Groupon groupon=grouponMapper.selectByMarketId(mark.getMarketingId());
                if(groupon!=null){
                    goodsrate= groupon.getGrouponDiscount();
                    goodsVipRate= groupon.getGrouponVipDiscount();
                }
            }

        }*/
        if (goodsGroupId==null&&marketingId != null && 0 != marketingId) {
            // 促销id重新从数据库查询,防止当前(折扣促销)已经过期;
            Marketing mark = marketingService.marketingDetail(marketingId, productId);
            if (mark != null) {
                para.put("marketingId", mark.getMarketingId());
                para.put("goodsId", productId);
                PreDiscountMarketing premark = preDiscountMarketingMapper.selectByMarketId(para);
                if (premark != null) {
                    // 货品折扣率
                    goodsrate = premark.getDiscountInfo();
                    goodsVipRate = premark.getVipdiscountInfo();
                    discountFlag = premark.getDiscountFlag();
                    // 抹掉分
                    if ("1".equals(discountFlag)) {
                        myformat = new DecimalFormat("0.0");
                    } else if ("2".equals(discountFlag)) {
                        myformat = new DecimalFormat("0");
                    } else {
                        myformat = new DecimalFormat("0.00");
                    }
                }

            }

        }

        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
        productVo = detailBean.getProductVo();
        goodsPrice = productVo.getGoodsInfoPreferPrice().multiply(goodsrate);
        goodsVipPrice=productVo.getGoodsInfoVipPrice().multiply(goodsVipRate);
        // 不四舍五入
        myformat.setRoundingMode(RoundingMode.FLOOR);
        // 如果不是第三方商品就去查分仓库存
        if ("0".equals(detailBean.getProductVo().getThirdId().toString()) && null != distinctId && distinctId > 0) {
            /* 根据选择的地区查询库存及价格信息 */
            productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
            /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
            if (null != productWare) {

                // 设置仓库地区价格
                productVo.setGoodsInfoPreferPrice(productWare.getWarePrice().multiply(goodsrate));
                productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice().multiply(goodsVipRate));
                // 货品参与了折扣促销
                goodsPrice = productVo.getGoodsInfoPreferPrice();
                goodsVipPrice=productVo.getGoodsInfoVipPrice();

                productVo.setGoodsInfoStock(productWare.getWareStock());
            } else {
                productVo.setGoodsInfoStock(0L);
            }
        }
        goodsPrice = BigDecimal.valueOf(Double.valueOf(myformat.format(goodsPrice)));
        goodsVipPrice= BigDecimal.valueOf(Double.valueOf(myformat.format(goodsVipPrice)));
        productVo.setGoodsInfoPreferPrice(goodsPrice);
        productVo.setGoodsInfoVipPrice(goodsVipPrice);
        //        add by 付陈林 判断是否参加抢购活动
        if((goodsGroupId==null||goodsGroupId==0)&&marketingList!=null){
            for(Marketing marketing:marketingList){
                if("11".equals(marketing.getCodexType())){
                    if(marketing.getRushs()!=null&&marketing.getRushs().size()>0){
                        /*productVo.setGoodsInfoPreferPrice(productVo.getGoodsInfoPreferPrice().multiply(marketing.getRushs().get(0).getRushDiscount()));
                        productVo.setGoodsInfoVipPrice(productVo.getGoodsInfoVipPrice().multiply(marketing.getRushs().get(0).getRushVipDiscount()));*/
                        //2015-12-09 wuyanbo 修改抢购价格计算方式
                        productVo.setGoodsInfoPreferPrice(marketing.getRushs().get(0).getRushPrice());
                        productVo.setGoodsInfoVipPrice(marketing.getRushs().get(0).getRushVipPrice());
                    }
                }
            }
        }
        //单该货品同时参与了团购和折扣时,优先级团购优先
        //2015-12-09 wuyanbo 修改抢购价格计算方式
        if(goodsGroupId!=null&&goodsGroupId!=0){
            // 从购物车里得到促销id重新从数据库查询,防止当前(团购促销)已经过期;
            Marketing mark = marketingService.querySimpleMarketingById(goodsGroupId);
            if(mark!=null){
                Groupon groupon=grouponMapper.selectByMarketId(mark.getMarketingId());
                if(groupon!=null){
                    goodsrate= groupon.getGrouponDiscount();
                    goodsVipRate= groupon.getGrouponVipDiscount();
                    productVo.setGoodsInfoPreferPrice(groupon.getGrouponPrice());
                    productVo.setGoodsInfoVipPrice(groupon.getGrouponVipPrice());
                }
            }

        }
        //end edit
        detailBean.setProductVo(productVo);
        return detailBean;
    }

    /**
     * 根据货品id查询货品和商品信息
     *
     * @param productId
     * @return
     */
    public GoodsProductVo getGoodsProductVoWithGoods(Long productId) {
        if (null == productId) {
            return null;
        }

        return goodsProductMapper.queryDetailByProductId(productId);
    }

    /**
     * 查询简易的商品详情
     *
     * @param productId
     * @return
     */
    public GoodsDetailBean querySimpleDetailBeanByProductId(Long productId) {
        GoodsDetailBean detailBean = new GoodsDetailBean();
        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
        return detailBean;
    }

    /**
     * 查询简易的商品详情
     *
     * @param productId
     * @param distinctId
     * @return
     */
    public GoodsDetailBean querySimpleDetailBeanWithWareByProductId(Long productId,Long distinctId) {
        GoodsDetailBean detailBean = new GoodsDetailBean();
        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
        ProductWare productWare = null;
        // 如果不是第三方商品就去查分仓库存
        if ("0".equals(detailBean.getProductVo().getThirdId().toString()) && null != distinctId && distinctId > 0) {
            /* 根据选择的地区查询库存及价格信息 */
            productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
            /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
            if (null != productWare) {
            	detailBean.getProductVo().setGoodsInfoPreferPrice(productWare.getWarePrice());
                detailBean.getProductVo().setGoodsInfoVipPrice(productWare.getWareVipPrice());
            	detailBean.getProductVo().setGoodsInfoStock(productWare.getWareStock());
            } else {
            	detailBean.getProductVo().setGoodsInfoStock(0L);
            }
        }
        return detailBean;
    }

    /**
     * 根据货品ID查询
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryDetailBeanByProductId
     *      (java.lang.Long)
     */
    public GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId) {
        GoodsDetailBean detailBean = new GoodsDetailBean();
        List<GoodsProduct> relaProduct = new ArrayList<GoodsProduct>();
        GoodsProductVo productVo;
        ProductWare productWare;
        try {
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
            if (null != detailBean.getProductVo()) {
                // 如果查询到的货品信息不为空就查询所属的分类信息
                detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                // 查询商品关联的标签
                detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询品牌
                detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                // 查询商品关联的扩展属性
                detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的详细参数
                detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的货品
                List<GoodsRelatedGoodsVo> relaProducts = this.goodsRelatedGoodsMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId());
                // 如果关联的不为空
                if (null != relaProducts && !relaProducts.isEmpty()) {
                    for (int i = 0; i < relaProducts.size(); i++) {
                        relaProduct.add(this.goodsProductMapper.queryFirstProductByGoodsId(relaProducts.get(i).getReleatedGoods().get(0).getGoodsId()));
                    }
                    detailBean.setReleProductList(relaProduct);
                }
                // 查询组合集合
                detailBean.setGroupVos(this.goodsPub.getGoodsGroupService().queryGroupVoListWithOutProductId(detailBean.getProductVo().getGoodsInfoId()));
                // 如果不是第三方商品就去查分仓库存
                if ("0".equals(detailBean.getProductVo().getThirdId().toString()) && null != distinctId && distinctId > 0) {
                    /* 根据选择的地区查询库存及价格信息 */
                    productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                    if (null != productWare) {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                        productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice());
                        productVo.setGoodsInfoStock(productWare.getWareStock());
                        detailBean.setProductVo(productVo);
                    } else {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoStock(0L);
                        detailBean.setProductVo(productVo);
                    }
                }
                /* 查询所有的关联的服务支持 */
                detailBean.setSuppList(this.goodsProductSuppService.queryAllSuppVoByProId(detailBean.getProductVo().getGoodsInfoId()));
                // 查询第三方店铺信息
                if ("1".equals(detailBean.getProductVo().getIsThird().toString())) {
                    detailBean.setStoreInfo(auditService.selectByCustomerId(detailBean.getProductVo().getThirdId()));
                }

                // 店铺动态评分
                if ("1".equals(detailBean.getProductVo().getIsThird().toString())) {
                    Comment comment = commentService.selectSellerComment(detailBean.getProductVo().getThirdId());
                    if (comment != null) {

                        if (comment.getCscoreAvg() != null) {
                            comment.setCscoreAvg(CalcuStar.getNumber(comment.getCscoreAvg()));
                        }
                        if (comment.getAttscoteAvg() != null) {
                            comment.setAttscoteAvg(CalcuStar.getNumber(comment.getAttscoteAvg()));
                        }
                        if (comment.getDscoreAvg() != null) {
                            comment.setDscoreAvg(CalcuStar.getNumber(comment.getDscoreAvg()));
                        }
                        detailBean.setComment(comment);
                    }

                }
            }

            return detailBean;
        } finally {
            productVo = null;
            productWare = null;
            detailBean = null;
        }
    }

    /**
     * 保存商品咨询
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#saveProductCommentAsk
     *      (int, java.lang.String)
     */
    public int saveProductCommentAsk(int type, String comment, Long custId, Long productId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("ip", IPAddress.getIpAddr(request));
            map.put("type", type);
            map.put("comment", comment);
            map.put("custId", custId);
            map.put(PRODUCTID, productId);
            return this.goodsProductMapper.saveAskComment(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据分类ID查询最终购买的列表
     *
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询的行数 {@link Long}
     * @return
     */
    public List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Long catId, Long rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.browCatFinalBuyAndPrecent(map);
        } finally {
            map = null;
        }
    }

    /**
     * 进行对比
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#execCompProduct(java.util.List)
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds) {
        List<GoodsDetailBean> lists = new ArrayList<GoodsDetailBean>();
        try {
            if (null != productIds && !productIds.isEmpty()) {
                for (int i = 0; i < productIds.size(); i++) {
                    GoodsDetailBean detailBean = new GoodsDetailBean();
                    try {
                        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productIds.get(i)));
                        if (null != detailBean.getProductVo()) {
                            // 如果查询到的货品信息不为空就查询所属的分类信息
                            detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                            // 查询商品关联的标签
                            detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询品牌
                            detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                            // 查询商品关联的扩展属性
                            detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询商品关联的详细参数
                            detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                        }
                        lists.add(detailBean);
                    } finally {
                        detailBean = null;
                    }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    /**
     * 进行对比,传递请求
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#execCompProduct(java.util.List,
     *      javax.servlet.http.HttpServletRequest)
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds, HttpServletRequest request) {
        List<GoodsDetailBean> lists = new ArrayList<GoodsDetailBean>();
        try {
            if (null != productIds && !productIds.isEmpty()) {
                for (int i = 0; i < productIds.size(); i++) {
                    GoodsDetailBean detailBean = new GoodsDetailBean();
                    try {
                        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productIds.get(i)));
                        if (null != detailBean.getProductVo()) {
                            // 如果查询到的货品信息不为空就查询所属的分类信息
                            detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                            // 查询商品关联的标签
                            detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询品牌
                            detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                            // 查询商品关联的扩展属性
                            detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询商品关联的详细参数
                            detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                        }
                        lists.add(detailBean);
                    } finally {
                        detailBean = null;
                    }
                }
            }
            /* 取出session中的 */
            // 如果不是第三方商品就去查分仓库存
            Long distinctId = null;
            if (null != request.getSession().getAttribute(DISTINCTID)) {
                distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
            } else {
                Long dId = addressService.getDefaultIdService();
                if (dId == null) {
                    distinctId = addressService.getDefaultIdService();
                    if (distinctId == null) {
                        distinctId = 1103L;
                    }
                }
            }
            GoodsDetailBean detailBean;
            ProductWare productWare;
            GoodsProductVo productVo;
            for (int i = 0; i < lists.size(); i++) {
                detailBean = lists.get(i);
                if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                    /* 根据选择的地区查询库存及价格信息 */
                    productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(detailBean.getProductVo().getGoodsInfoId(), distinctId);
                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                    if (null != productWare) {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                        productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice());// 企业会员价
                        productVo.setGoodsInfoStock(productWare.getWareStock());
                        detailBean.setProductVo(productVo);
                    } else {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoStock(0L);
                        detailBean.setProductVo(productVo);
                    }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    /**
     * 根据货品ID和查询行数查询销售最高的几条记录
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryTopSalesByProductId(Long,
     *      Integer)
     */
    public List<GoodsProduct> queryTopSalesByProductId(Long productId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(PRODUCTID, productId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询团购商品列表
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#(com.ningpai.util.PageBean,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PageBean selectGrouponList(PageBean pb, HttpServletRequest request,Long parentId,String isAsc) {
        // ADD BY LY
        pb.setRows(goodsProductMapper.selectGrouponCount(parentId));
        //pb.setRows(goodsProductMapper.selectGrouponCount());
        // ADD BY LY
        String vip = request.getSession().getAttribute("vip")==null?"0":(String) request.getSession().getAttribute("vip");
        Map<String, Object> map = new HashMap<String, Object>();
        if("1".equals(vip)){
            map.put("isEnterprise","1");
        }
        if("1".equals(isAsc)){
            map.put("isAsc","1");
        }
        map.put("parentId",parentId);
        map.put("start", pb.getStartRowNum());
        map.put("number", pb.getEndRowNum());
        // 参加的团购商品信息
        List<GoodsProductVo> productVos = goodsProductMapper.selectGrouponList(map);
        // 团购商品信息及优惠信息
        List<Object> grouponUtils = new ArrayList<Object>();
        Long distinctId;
        if (null != request.getSession().getAttribute(ValueUtil.ADDRESS) && null == request.getSession().getAttribute(CHADDRESS)) {
            distinctId = ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getDistrict().getDistrictId();
        } else {
            if (null == request.getSession().getAttribute(CHADDRESS)) {
                // 默认设置为南京建邺区仓库
                distinctId = new Long(1103L);
            } else {
                /* 取session的数据 */
                distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
            }
        }
        for (GoodsProductVo vo : productVos) {
            Marketing marketing = marketingService.selectGrouponMarket(vo.getMarketingId());
            GoodsDetailBean detailBean = this.queryDetailBeanByProductIdForGroupon(vo.getGoodsInfoId(), distinctId);
            if (marketing != null) {
                GrouponUtil grouponUtil = new GrouponUtil();
                // 设置分仓价格、库存
                vo.setGoodsInfoPreferPrice(detailBean.getProductVo().getGoodsInfoPreferPrice());
                vo.setGoodsInfoVipPrice(detailBean.getProductVo().getGoodsInfoVipPrice());
                vo.setGoodsInfoStock(detailBean.getProductVo().getGoodsInfoStock());
                grouponUtil.setGoodsProductVo(vo);
                grouponUtil.setMarketing(marketing);
                grouponUtils.add(grouponUtil);
            }
        }
        pb.setList(grouponUtils);
        return pb;
    }

    /**
     * 根据货品ID 查询这个货品是否是上架状态以及未删除状态
     */
    @Override
    public int queryProductByGoodsInfoId(Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 填充货品号
        map.put(PRODUCTID, productId);
        return goodsProductMapper.queryProductByGoodsInfoId(map);
    }

    /**
     * 查询抢购秒杀商品列表
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#selectMarketingRushList(com.ningpai.util.PageBean,
     *      javax.servlet.http.HttpServletRequest)
     */
    @Override
    public PageBean selectMarketingRushList(PageBean pb, HttpServletRequest request) {
        pb.setRows(goodsProductMapper.selectMarketingRushCount());
        /*Map<String, Object> map = new HashMap<String, Object>();
        map.put("start", pb.getStartRowNum());
        map.put("number", pb.getEndRowNum());*/
        // 参加的抢购秒杀商品信息
        List<GoodsProductVo> productVos = goodsProductMapper.selectMarketingRushAllList();
        // 抢购秒杀商品信息及优惠信息
        List<Object> marketingRushUtils = new ArrayList<Object>();
        Long distinctId;
        if (null != request.getSession().getAttribute(ValueUtil.ADDRESS) && null == request.getSession().getAttribute(CHADDRESS)) {
            distinctId = ((CustomerAddress) request.getSession().getAttribute(ValueUtil.ADDRESS)).getDistrict().getDistrictId();
        } else {
            if (null == request.getSession().getAttribute(CHADDRESS)) {
                // 默认设置为南京建邺区仓库
                distinctId = new Long(1103L);
            } else {
                /* 取session的数据 */
                distinctId = Long.parseLong(request.getSession().getAttribute(DISTINCTID).toString());
            }
        }
        for (GoodsProductVo vo : productVos) {
            Marketing marketing = marketingService.selectRushMarket(vo.getMarketingId());
            GoodsDetailBean detailBean = this.queryDetailBeanByProductIdForGroupon(vo.getGoodsInfoId(), distinctId);
            if (marketing != null) {
                MarketingRushUtil mrUtils = new MarketingRushUtil();
                String rushs = compareTime(request, marketing.getMarketingBegin(), marketing.getMarketingEnd());

                mrUtils.setRushTime(rushs);
                // 设置分仓价格、库存
                vo.setGoodsInfoPreferPrice(detailBean.getProductVo().getGoodsInfoPreferPrice());
                vo.setGoodsInfoVipPrice(detailBean.getProductVo().getGoodsInfoVipPrice());
                vo.setGoodsInfoStock(detailBean.getProductVo().getGoodsInfoStock());
                mrUtils.setGoodsProductVo(vo);
                mrUtils.setMarketing(marketing);
                marketingRushUtils.add(mrUtils);

            }
        }
        // pb.setRows(marketingRushUtils.size());
        pb.setList(marketingRushUtils);
        return pb;
    }

    /**
     * 比较时间大小
     *
     * @param begin
     * @param end
     * @return String
     */
    public String compareTime(HttpServletRequest request, Date begin, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(new Date().getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date sysdate = df.parse(dateFormat.format(c.getTime()));
            Date begindate = df.parse(dateFormat.format(begin.getTime()));
            Date enddate = df.parse(dateFormat.format(end.getTime()));
            // long
            // between=(sysdate.getTime()-enddate.getTime())/1000;//除以1000是为了转换成秒
            // long day1=between/(24*3600);
            if (sysdate.getTime() <= begindate.getTime()) {
                // 没有开始
                return "1";
            } else if (sysdate.getTime() >= enddate.getTime()) {
                // if(day1>=1){
                // return "0";//结束超过一天
                // }
                // 已结束在一天内
                return "3";
            } else if (sysdate.getTime() >= begindate.getTime() && sysdate.getTime() <= enddate.getTime()) {
                // 已开始未结束
                return "2";
            } else {
                return "0";
            }
        } catch (ParseException e) {
            LOGGER.error("比较时间大小出错：" + e);
        }
        return "0";
    }

    /**
     * 根据组合id查询商品id
     *
     * @see com.ningpai.site.goods.service.GoodsProductService#queryDetailByGroupId
     *      (java.lang.Long)
     */
    @Override
    public List<GoodsProductVo> queryDetailByGroupId(Long groupId) {
        return goodsProductMapper.queryDetailByGroupId(groupId);
    }

    /**
     * 根据地区ID查询关联的仓库ID
     *
     * @param distinctId
     *            地区ID
     * @return 仓库ID
     */
    @Override
    public Long selectWareIdByDistinctId(Long distinctId) {
        WareHouse wareHouse = productWareService.findWare(distinctId);
        return wareHouse == null ? null : wareHouse.getWareId();
    }

    public GoodsCateService getCateService() {
        return cateService;
    }

    // Spring注入
    @Resource(name = "HsiteGoodsCateService")
    public void setCateService(GoodsCateService cateService) {
        this.cateService = cateService;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    // Spring注入
    @Resource(name = "HsiteGoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

    public GoodsReleTagMapper getGoodsReleTagMapper() {
        return goodsReleTagMapper;
    }

    // Spring注入
    @Resource(name = "GoodsReleTagMapper")
    public void setGoodsReleTagMapper(GoodsReleTagMapper goodsReleTagMapper) {
        this.goodsReleTagMapper = goodsReleTagMapper;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    // Spring注入
    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsReleExpandParamMapper getExpandParamMapper() {
        return expandParamMapper;
    }

    // Spring注入
    @Resource(name = "GoodsReleExpandParamMapper")
    public void setExpandParamMapper(GoodsReleExpandParamMapper expandParamMapper) {
        this.expandParamMapper = expandParamMapper;
    }

    public GoodsReleParamMapper getGoodsReleParamMapper() {
        return goodsReleParamMapper;
    }

    // Spring注入
    @Resource(name = "GoodsReleParamMapper")
    public void setGoodsReleParamMapper(GoodsReleParamMapper goodsReleParamMapper) {
        this.goodsReleParamMapper = goodsReleParamMapper;
    }

    public GoodsRelatedGoodsMapper getGoodsRelatedGoodsMapper() {
        return goodsRelatedGoodsMapper;
    }

    // Spring注入
    @Resource(name = "GoodsRelatedGoodsMapper")
    public void setGoodsRelatedGoodsMapper(GoodsRelatedGoodsMapper goodsRelatedGoodsMapper) {
        this.goodsRelatedGoodsMapper = goodsRelatedGoodsMapper;
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    // Spring注入
    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public GoodsPub getGoodsPub() {
        return goodsPub;
    }

    // Spring注入
    @Resource(name = "goodsPub")
    public void setGoodsPub(GoodsPub goodsPub) {
        this.goodsPub = goodsPub;
    }

    @Override
    public GoodsProductVo querySimpleProductByProductId(Long productId) {
        return goodsProductMapper.querySimpleProductByProductId(productId);
    }

}
