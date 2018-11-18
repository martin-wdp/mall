
package com.ningpai.marketing.service.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import uitl.MarketingValue;

import com.ningpai.common.util.DateUtil;
import com.ningpai.coupon.dao.CouponMapper;
import com.ningpai.gift.dao.GiftCateMapper;
import com.ningpai.gift.dao.GiftMapper;
import com.ningpai.gift.dao.GiftOrderMapper;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.marketing.bean.*;
import com.ningpai.marketing.dao.*;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;

/**
 * @author ggn 2014-03-24 促销信息service
 */
@Service("MarketingService")
public class MarketingServiceImpl implements MarketingService {

    // Map中使用的key值
    private static final String MARKETINGID = "marketingId";
    private static final String BUSINESSID = "businessId";
    private static final String START = "start";
    private static final String NUMBER = "number";
    private static final String GOODSID = "goodsId";
    private static final String CODEXID = "codexId";
    private static final String CATEIDP = "cateIdp";
    private static final String THIRDCATE_IDS = "thirdcate_ids";
    private static final String BRANDIDP = "brandIdP";
    private static final String BRAND_ALLIDS = "brand_allids";
    private static final String GOODSIDP = "goodsIdP";
    private static final String PRODUCT_ALLIDS = "product_allids";
    private static final String SCOPEID = "scopeId";
    private static final String GIFTIDS = "giftIds";
    private static final String COUPONIDS = "couponIds";
    private static final String GROUPID = "groupId";
    private static final String CATEID = "cateId";
    private static final String GOODSINFOID = "goodsInfoId";
    private static final String LIMITCOUNT = "limitCount";
    private static final String DISCOUNTFLAG = "discountFlag";
    private static final String DISCOUNTINFO = "discountInfo";
    private static final String OFFVALUE = "offValue";
    private static final String DISCOUNTVALUE = "discountValue";
    private static final String REDUCEPRICE = "reducePrice";
    private static final String FULLBUYDISCOUNT = "fullbuyDiscount";
    private static final String PACKAGESNO = "packagesNo";
    private static final String PACKAGEBUYDISCOUNT = "packagebuyDiscount";
    private static final String COUNTNO = "countNo";
    private static final String COUNTMONEY = "countMoney";
    private static final String DISCOUNTPRICE = "discountPrice";

    @Resource(name = "GiftOrderWebMapper")
    private GiftOrderMapper giftOrderMapper;
    @Resource(name = "GiftCateMapper")
    private GiftCateMapper cateMapper;

    @Resource(name = "MarketingRushTimeMapper")
    private MarketingRushTimeMapper rushTimeMapper;

    @Resource(name = "MarketingGroupMapper")
    private MarketingGroupMapper groupMapper;

    @Resource(name = "MarketingScopeMapper")
    private MarketingScopeMapper marketingScopeMapper;


    @Resource(name = "GoodsMapper")
    private GoodsMapper goodsMapper;

    @Resource(name = "GoodsBrandMapper")
    private GoodsBrandMapper goodsBrandMapper;

    @Resource(name = "GoodsCateMapper")
    private GoodsCateMapper goodsCateMapper;

    @Resource(name = "PriceOffMarketingMapper")
    private PriceOffMarketingMapper priceOffMarketingMapper;

    @Resource(name = "PresentationMarketingMapper")
    private PresentationMarketingMapper presentationMarketingMapper;

    @Resource(name = "GiftMapper")
    private GiftMapper giftMapper;

    @Resource(name = "MarketingMapper")
    private MarketingMapper marketingMapper;

    @Resource(name = "CouponMapper")
    private CouponMapper couponMapper;

    @Resource(name = "CouponMarketingMapper")
    private CouponMarketingMapper couponMarketingMapper;

    @Resource(name = "DiscountMarketingMapper")
    private DiscountMarketingMapper discountMarketingMapper;

    @Resource(name = "FullbuyReduceMarketingMapper")
    private FullbuyReduceMarketingMapper fullbuyReduceMarketingMapper;

    @Resource(name = "FullbuyPresentMarketingMapper")
    private FullbuyPresentMarketingMapper fullbuyPresentMarketingMapper;

    @Resource(name = "FullbuyCouponMarketingMapper")
    private FullbuyCouponMarketingMapper fullbuyCouponMarketingMapper;

    @Resource(name = "FullbuyDiscountMarketingMapper")
    private FullbuyDiscountMarketingMapper fullbuyDiscountMarketingMapper;

    @Resource(name = "MarketingCodexMapper")
    private MarketingCodexMapper marketingCodexMapper;

    @Resource(name = "CodexService")
    private CodexService codexService;

    @Resource(name = "LimitBuyMarketingMapper")
    private LimitBuyMarketingMapper limitBuyMarketingMapper;

    // 插入团购信息
    @Resource(name = "GrouponMapper")
    private GrouponMapper grouponMapper;

    // 团购进度
    @Resource(name = "GrouponParticipationMapper")
    private GrouponParticipationMapper grouponParticipationMapper;

    // 限购
    @Resource(name = "MarketingRushMapper")
    private MarketingRushMapper rushMapper;

    // 满x件打y折
    @Resource(name = "FullbuyNoDiscountMarketingMapper")
    private FullbuyNoDiscountMarketingMapper fullbuyNoDiscountMarketingMapper;

    // 满件数多少钱
    @Resource(name = "FullbuyNoCountMarketingMapper")
    private FullbuyNoCountMarketingMapper fullbuyNoCountMarketingMapper;

    @Resource(name = "PreDiscountMarketingMapper")
    private PreDiscountMarketingMapper preDiscountMarketingMapper;

    // 货品信息Mapper
    @Resource(name = "GoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;

    // 注册营销Mapper
    @Resource(name = "RegisterMarketingMapper")
    private RegisterMarketingMapper registerMarketingMapper;

    // 促销等级Mapper
    @Resource(name = "MarketLelvelMapper")
    private MarketLelvelMapper marketLelvelMapper;

    // 促销LogoMappper
    @Resource(name = "MarketLogoMapper")
    private MarketLogoMapper marketLogoMapper;

    @Override
    public Marketing selectGrouponMarket(Long marketingId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(MARKETINGID, marketingId);
        List<Marketing> marketing = marketingMapper.queryByCreatimeMarketingGroupon(map);
        return marketing.get(0);
    }

    @Override
    public int delleteMarkting(Long marketingId, Long businessId) {
        return marketingMapper.delleteMarkting(marketingId, businessId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketOrderList(com.ningpai
     * .util.PageBean, com.ningpai.marketing.bean.Marketing)
     */
    @Override
    public PageBean marketOrderList(PageBean pageBean, Marketing marketing, String marketingBeginTime, String marketingEndTime) {
        marketing.setFlag("0");
        Map<String, Object> paramMap = MapUtil.getParamsMap(marketing);
        // 分装实体类属性
        if (marketingBeginTime != null && !"".equals(marketingBeginTime)) {
            marketing.setMarketingBegin(DateUtil.stringToDate(marketingBeginTime, null));
            paramMap.put("marketingBegin", marketingBeginTime);

        }
        if (marketingEndTime != null && !"".equals(marketingEndTime)) {
            marketing.setMarketingEnd(DateUtil.stringToDate(marketingEndTime, null));
            paramMap.put("marketingEnd", marketingEndTime);

        }
        if (marketing.getBusinessId() == null) {
            marketing.setBusinessId(0L);
            paramMap.put(BUSINESSID, marketing.getBusinessId());
        }
        // 查询总数
        pageBean.setRows(marketingMapper.marketOrderListCount(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        if (no == 0) {
            no = 1;
        }
        try {
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
                pageBean.setObjectBean(marketing);
            }
            // 查询条件封装
            paramMap.put(START, pageBean.getStartRowNum());
            paramMap.put(NUMBER, pageBean.getEndRowNum());
            // 查询列表页
            pageBean.setList(marketingMapper.marketOrderList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketingDetail(java.lang
     * .Long)
     */
    @Override
    public Marketing marketingDetail(Long marketingId, Long goodsInfoId) {
        Marketing market = marketingMapper.marketingDetail(marketingId);
        if (market != null) {
            // 直降
            if ("1".equals(market.getCodexType())) {
                market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
            }
            // 买送赠品
            if ("2".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 买送优惠券
            if ("3".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 买折扣 折扣多少
            if ("4".equals(market.getCodexType())) {
                market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
            }
            // 满减优惠
            if ("5".equals(market.getCodexType())) {
                market.setFullbuyReduceMarketings(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingsByMarketingId(marketingId));

            }
            // 满赠赠品
            if ("6".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 满赠优惠券
            if ("7".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }
            // 满折
            if ("8".equals(market.getCodexType())) {
                market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
            }
            // 限购
            if ("9".equals(market.getCodexType())) {
                market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
            // 团购
            if ("10".equals(market.getCodexType())) {
                market.setGroupon(grouponMapper.selectByMarketId(marketingId));
            }
            // 抢购
            if ("11".equals(market.getCodexType())) {
                market.setRushs(rushMapper.selectByMarketId(marketingId));
            }
            // 满x件打y折
            if ("13".equals(market.getCodexType())) {
                market.setFullbuyNoDiscountMarketing(fullbuyNoDiscountMarketingMapper.selectByMarketId(marketingId));
            }
            // 满购件数多少钱
            if ("14".equals(market.getCodexType())) {
                market.setFullbuyNoCountMarketing(fullbuyNoCountMarketingMapper.selectByMarketId(marketingId));
            }
            // 折扣促销
            if ("15".equals(market.getCodexType())) {
                Map<String, Object> param = new HashMap<String, Object>();
                param.put(GOODSID, goodsInfoId);
                param.put(MARKETINGID, marketingId);
                market.setPreDiscountMarketing(preDiscountMarketingMapper.selectByMarketId(param));
            }
        }
        return market;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#queryMarketingDetail(java
     * .lang.Long)
     */
    @Override
    public Marketing queryMarketingDetail(Long marketingId, Long codexId) {
        Marketing market = new Marketing();
        // 直降
        if (1 == codexId) {
            market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
        }
        // 买送赠品
        if (2 == codexId) {
            // 查询所有此促销的赠品级联list
            List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
            if (pmList != null && !pmList.isEmpty()) {
                List<Long> list = new ArrayList<Long>();
                for (int i = 0; i < pmList.size(); i++) {
                    list.add(pmList.get(i).getProductId());
                }
                market.setGiftList(giftMapper.selectGiftByListId(list));
            }
        }
        // 买送优惠券
        if (3 == codexId) {
            // 查询所有此促销的赠品级联list
            List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
            if (pmList != null && !pmList.isEmpty()) {
                List<Long> list = new ArrayList<Long>();
                for (int i = 0; i < pmList.size(); i++) {
                    list.add(pmList.get(i).getCouponId());
                }
                market.setCouponList(couponMapper.selectCouponByListId(list));
            }

        }

        // 买折扣 折扣多少
        if (4 == codexId) {
            market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
        }
        // 满减优惠
        if (5 == codexId) {
            market.setFullbuyReduceMarketing(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingByMarketingId(marketingId));
        }
        // 满赠赠品
        if (6 == codexId) {
            // 查询所有此促销的赠品级联list
            List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
            if (pmList != null && !pmList.isEmpty()) {
                market.setFullbuyPresentMarketing(pmList.get(0));
                List<Long> list = new ArrayList<Long>();
                for (int i = 0; i < pmList.size(); i++) {
                    list.add(pmList.get(i).getProductId());
                }
                market.setGiftList(giftMapper.selectGiftByListId(list));
            }
        }
        // 满赠优惠券
        if (7 == codexId) {
            // 查询所有此促销的赠品级联list
            List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
            if (pmList != null && !pmList.isEmpty()) {
                market.setFullbuyCouponMarketing(pmList.get(0));
                List<Long> list = new ArrayList<Long>();
                for (int i = 0; i < pmList.size(); i++) {
                    list.add(pmList.get(i).getCouponId());
                }
                market.setCouponList(couponMapper.selectCouponByListId(list));
            }

        }
        // 满折
        if (8 == codexId) {
            market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
        }
        // 限购
        if (9 == codexId) {
            market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
        }
        // 团购
        if (10 == codexId) {
            market.setGroupon(grouponMapper.selectByMarketId(marketingId));
        }
        // 抢购
        if (11 == codexId) {
            market.setRushs(rushMapper.selectByMarketId(marketingId));
        }
        // 满x件打y折
        if (13 == codexId) {
            market.setFullbuyNoDiscountMarketing(fullbuyNoDiscountMarketingMapper.selectByMarketId(marketingId));
        }
        // 满购件数多少钱
        if (14 == codexId) {
            market.setFullbuyNoCountMarketing(fullbuyNoCountMarketingMapper.selectByMarketId(marketingId));
        }
        return market;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketingDetail(java.lang
     * .Long)
     */
    @Override
    public List<MarketingScope> queryMarketingScope(Long marketingId) {
        return marketingScopeMapper.queryMarketingScope(marketingId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketingDetail(java.lang
     * .Long)
     */
    @Override
    public Integer removeGoodsByMidAndGid(Long goodsId, Long marketingId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSID, goodsId);
        map.put(MARKETINGID, marketingId);
        int count = marketingScopeMapper.countGoodsByMidAndGid(map);
        if (count > 0) {
            return marketingScopeMapper.removeGoodsByMidAndGid(map);
        } else {
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketingDetail(java.lang
     * .Long)
     */
    @Override
    public Integer removeMarketByMidAndGid(Long goodsId, Long marketingId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSID, goodsId);
        map.put(MARKETINGID, marketingId);
        int count = marketingScopeMapper.countGoodsByMidAndGid(map);
        if (count > 0) {
            // 删除范围
            int a = marketingScopeMapper.removeGoodsByMidAndGid(map);
            // 范围删除成功，再删除折扣促销表里的数据
            if (a > 0) {
                int b = preDiscountMarketingMapper.countPreGoodsByMidAndGid(map);
                if (b > 0) {
                    return preDiscountMarketingMapper.removePreGoodsByMidAndGid(map);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketingDetail(java.lang
     * .Long)
     */
    @Override
    public Marketing marketingDetailByActive(Marketing market, Long marketingId, boolean isT) {
        Marketing marketNew = market;
        Marketing marketing = marketingMapper.marketingDetail(marketingId);
        if (marketNew == null) {
            marketNew = marketing;
        }
        if (marketing != null) {
            marketNew.setMarketingName(marketing.getMarketingName());
            // 买送赠品
            if ("2".equals(marketing.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    marketNew.setGiftList(giftMapper.selectGiftByListId(list));
                }

            }

            // 买送优惠券
            if ("3".equals(marketing.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    marketNew.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 满赠赠品
            if ("6".equals(marketing.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    marketNew.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    marketNew.setGiftList(giftMapper.selectGiftByListId(list));
                }

            }
            // 满赠优惠券
            if ("7".equals(marketing.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    marketNew.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    marketNew.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 限购
            if ("9".equals(marketing.getCodexType())) {
                marketNew.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
        }
        return marketNew;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#selectMarketingScope(java
     * .lang.Long, java.lang.String)
     */
    @Override
    public Object selectMarketingScope(Long marketingId, String type) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MARKETINGID, marketingId);
        paramMap.put("scopeType", type);
        List<MarketingScope> scopelist = marketingScopeMapper.selectMarketingScope(paramMap);
        List<Object> list = null;
        List<Long> idList = new ArrayList<Long>();
        if (scopelist != null && !scopelist.isEmpty()) {
            for (int i = 0; i < scopelist.size(); i++) {
                idList.add(scopelist.get(i).getScopeId());
            }
        }
        // 分类
        if ("0".equals(type) && !idList.isEmpty()) {
            list = goodsCateMapper.selectProductCateList(idList);
        }

        // 品牌
        if ("1".equals(type) && !idList.isEmpty()) {
            list = goodsBrandMapper.selectProductBrandList(idList);
        }

        // SKU
        if ("2".equals(type) && !idList.isEmpty()) {
            list = goodsMapper.selectProductSkuList(idList);
        }
        return list;
    }

    @Override
    public Marketing marketingDetail(Long marketingId) {
        Marketing market = marketingMapper.marketingDetail(marketingId);
        if (market != null) {
            // 直降
            if ("1".equals(market.getCodexType())) {
                market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
            }
            // 买送赠品
            if ("2".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 买送优惠券
            if ("3".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 买折扣 折扣多少
            if ("4".equals(market.getCodexType())) {
                market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
            }
            // 满减优惠
            if ("5".equals(market.getCodexType())) {

                market.setFullbuyReduceMarketings(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingsByMarketingId(marketingId));
            }
            // 满赠赠品
            if ("6".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 满赠优惠券
            if ("7".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }
            // 满折
            if ("8".equals(market.getCodexType())) {
                market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
            }
            // 限购
            if ("9".equals(market.getCodexType())) {
                market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
            // 团购
            if ("10".equals(market.getCodexType())) {
                market.setGroupon(grouponMapper.selectByMarketId(marketingId));
            }
            // 抢购
            if ("11".equals(market.getCodexType())) {
                market.setRushs(rushMapper.selectByMarketId(marketingId));
            }
            // 满x件打y折
            if ("13".equals(market.getCodexType())) {
                market.setFullbuyNoDiscountMarketings(fullbuyNoDiscountMarketingMapper.selectdiscountsByMarketId(marketingId));
            }
            // 满购件数多少钱
            if ("14".equals(market.getCodexType())) {
                market.setFullbuyNoCountMarketings(fullbuyNoCountMarketingMapper.selectCountsByMarketId(marketingId));
            }
            // 折扣
            if ("15".equals(market.getCodexType())) {
                market.setPreDiscountMarketings(preDiscountMarketingMapper.selectCountsByMarketId(marketingId));
            }
        }
        return market;
    }

    /**
     * 查询促销详细信息（不根据时间）
     * 
     * @param marketingId
     *            {@link Long}
     * @return
     */
    @Override
    public Marketing marketingDetailNotTime(Long marketingId) {
        Marketing market = marketingMapper.marketingDetailNotTime(marketingId);
        if (market.getCouponId() != null) {
            market.setCoupon(couponMapper.searchCouponById(market.getCouponId()));
        }
        if (market != null) {
            // 直降
            if ("1".equals(market.getCodexType())) {
                market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
            }
            // 买送赠品
            if ("2".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 买送优惠券
            if ("3".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 买折扣 折扣多少
            if ("4".equals(market.getCodexType())) {
                market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
            }
            // 满减优惠
            if ("5".equals(market.getCodexType())) {
                market.setFullbuyReduceMarketings(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingsByMarketingId(marketingId));
            }
            // 满赠赠品
            if ("6".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 满赠优惠券
            if ("7".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }
            // 满折
            if ("8".equals(market.getCodexType())) {
                market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
            }
            // 限购
            if ("9".equals(market.getCodexType())) {
                market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
            // 团购
            if ("10".equals(market.getCodexType())) {
                market.setGroupon(grouponMapper.selectByMarketId(marketingId));
            }
            // 抢购
            if ("11".equals(market.getCodexType())) {
                market.setRushs(rushMapper.selectByMarketId(marketingId));
            }
            // 满x件打y折
            if ("13".equals(market.getCodexType())) {
                market.setFullbuyNoDiscountMarketings(fullbuyNoDiscountMarketingMapper.selectdiscountsByMarketId(marketingId));
            }
            // 满购件数多少钱
            if ("14".equals(market.getCodexType())) {
                market.setFullbuyNoCountMarketings(fullbuyNoCountMarketingMapper.selectCountsByMarketId(marketingId));
            }
            // 折扣
            if ("15".equals(market.getCodexType())) {
                market.setPreDiscountMarketings(preDiscountMarketingMapper.selectCountsByMarketId(marketingId));
            }
        }
        return market;
    }

    /**
     * 为了在抢购促销里添加 该抢购促销的会员允许购买的数量
     * @param marketingId
     * @return
     */
    public Marketing newmarketingDetailForTime(Long goodsInfoId, Long marketingId) {
        Marketing market = marketingMapper.marketingDetailForTime(marketingId);

        if (market != null) {

            if (market.getCouponId() != null) {
                market.setCoupon(couponMapper.searchCouponById(market.getCouponId()));
            }

            // 直降
            if ("1".equals(market.getCodexType())) {
                market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
            }
            // 买送赠品
            if ("2".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 买送优惠券
            if ("3".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 买折扣 折扣多少
            if ("4".equals(market.getCodexType())) {
                market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
            }
            // 满减优惠
            if ("5".equals(market.getCodexType())) {
                market.setFullbuyReduceMarketings(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingsByMarketingId(marketingId));
            }
            // 满赠赠品
            if ("6".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 满赠优惠券
            if ("7".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }
            // 满折
            if ("8".equals(market.getCodexType())) {
                market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
            }
            // 限购
            if ("9".equals(market.getCodexType())) {
                market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
            // 团购
            if ("10".equals(market.getCodexType())) {
                market.setGroupon(grouponMapper.selectByMarketId(marketingId));
            }
            // 抢购
            if ("11".equals(market.getCodexType())) {
                market.setRushs(rushMapper.selectByMarketId(marketingId));

            }
            // 满x件打y折
            if ("13".equals(market.getCodexType())) {
                market.setFullbuyNoDiscountMarketings(fullbuyNoDiscountMarketingMapper.selectdiscountsByMarketId(marketingId));
            }
            // 满购件数多少钱
            if ("14".equals(market.getCodexType())) {
                market.setFullbuyNoCountMarketings(fullbuyNoCountMarketingMapper.selectCountsByMarketId(marketingId));
            }
            // 折扣
            if ("15".equals(market.getCodexType())) {
                market.setPreDiscountMarketings(preDiscountMarketingMapper.selectCountsByMarketId(marketingId));
            }
        }
        return market;
    }
    /**
     * 查询促销详细信息（根据时间）
     *
     * @param marketingId
     *            {@link Long}
     * @return
     */
    @Override
    public Marketing marketingDetailForTime(Long marketingId) {
        Marketing market = marketingMapper.marketingDetailForTime(marketingId);

        if (market != null) {

            if (market.getCouponId() != null) {
                market.setCoupon(couponMapper.searchCouponById(market.getCouponId()));
            }

            // 直降
            if ("1".equals(market.getCodexType())) {
                market.setPriceOffMarketing(priceOffMarketingMapper.selectPriceOffMarketingByMarketingId(marketingId));
            }
            // 买送赠品
            if ("2".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<PresentationMarketing> pmList = presentationMarketingMapper.selectPresentationMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 买送优惠券
            if ("3".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<CouponMarketing> pmList = couponMarketingMapper.selectCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }

            // 买折扣 折扣多少
            if ("4".equals(market.getCodexType())) {
                market.setDiscountMarketing(discountMarketingMapper.selectDiscountMarketingByMarketingId(marketingId));
            }
            // 满减优惠
            if ("5".equals(market.getCodexType())) {
                market.setFullbuyReduceMarketings(fullbuyReduceMarketingMapper.selectFullbuyReduceMarketingsByMarketingId(marketingId));
            }
            // 满赠赠品
            if ("6".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyPresentMarketing> pmList = fullbuyPresentMarketingMapper.selectFullbuyPresentMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyPresentMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getProductId());
                    }
                    market.setGiftList(giftMapper.selectGiftByListId(list));
                }
            }
            // 满赠优惠券
            if ("7".equals(market.getCodexType())) {
                // 查询所有此促销的赠品级联list
                List<FullbuyCouponMarketing> pmList = fullbuyCouponMarketingMapper.selectFullbuyCouponMarketingListByMarketingId(marketingId);
                if (pmList != null && !pmList.isEmpty()) {
                    market.setFullbuyCouponMarketing(pmList.get(0));
                    List<Long> list = new ArrayList<Long>();
                    for (int i = 0; i < pmList.size(); i++) {
                        list.add(pmList.get(i).getCouponId());
                    }
                    market.setCouponList(couponMapper.selectCouponByListId(list));
                }

            }
            // 满折
            if ("8".equals(market.getCodexType())) {
                market.setFullbuyDiscountMarketings(fullbuyDiscountMarketingMapper.selectFullbuyDiscountMarketingsByMarketingId(marketingId));
            }
            // 限购
            if ("9".equals(market.getCodexType())) {
                market.setLimitBuyMarketing(limitBuyMarketingMapper.selectLimitBuyMarketingByMarketingId(marketingId));
            }
            // 团购
            if ("10".equals(market.getCodexType())) {
                market.setGroupon(grouponMapper.selectByMarketId(marketingId));
            }
            // 抢购
            if ("11".equals(market.getCodexType())) {
                market.setRushs(rushMapper.selectByMarketId(marketingId));
            }
            // 满x件打y折
            if ("13".equals(market.getCodexType())) {
                market.setFullbuyNoDiscountMarketings(fullbuyNoDiscountMarketingMapper.selectdiscountsByMarketId(marketingId));
            }
            // 满购件数多少钱
            if ("14".equals(market.getCodexType())) {
                market.setFullbuyNoCountMarketings(fullbuyNoCountMarketingMapper.selectCountsByMarketId(marketingId));
            }
            // 折扣
            if ("15".equals(market.getCodexType())) {
                market.setPreDiscountMarketings(preDiscountMarketingMapper.selectCountsByMarketId(marketingId));
            }
        }
        return market;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#doAddMarketing(com.ningpai
     * .marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public int doAddMarketing(Marketing marketing, String codexType, HttpServletRequest request) {
        // 初始化促销
        marketing.setCreateTime(new Date());
        marketing.setModTime(new Date());
        marketing.setFlag("0");
        // 插入促销信息
        Codex codex = codexService.queryCodexDetail(Long.parseLong(codexType));
        if (codex.getCodexStatus() != null) {
            marketing.setMarketGroupId(codex.getCodexStatus().toString());
        }
        String flage = "0";
        // 插入促销信息
        int insertFlag = marketingMapper.insertMarketing(marketing);
        // 获取促销Id
        Long marketingId = marketingMapper.selectLastId();
        if (insertFlag == 1) {
            // 创建促销规则级联数据
            MarketingCodex mc = new MarketingCodex();
            mc.setCreateTime(new Date());
            mc.setFlag(flage);
            mc.setMarketingId(marketingId);
            mc.setModTime(new Date());
            mc.setCodexId(Long.valueOf(request.getParameter(CODEXID)));
            marketingCodexMapper.insertMarketingCodex(mc);
        }
        String price = request.getParameter(MarketingValue.FULLPRICE);
        BigDecimal fullPrice = null;
        if (price != null && !"".equals(price)) {
            fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
        }

        judgeCodexType(codexType, request, flage, marketingId, fullPrice);

        String[] goodsId = request.getParameterValues(GOODSID);
        List<MarketingScope> mslist = new ArrayList<MarketingScope>();
        if (goodsId != null && goodsId.length != 0) {
            for (int i = 0; i < goodsId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag(flage);
                ms.setMarketingId(marketingId);
                ms.setScopeId(Long.valueOf(goodsId[i]));
                ms.setScopeType("2");
                mslist.add(ms);
            }
        }

        if (!mslist.isEmpty()) {
            marketingScopeMapper.insertMarketingScope(mslist);
        }

        return insertFlag;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#doAddMarketing(com.ningpai
     * .marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public int doAddMarketing(Marketing marketing, String codexType, HttpServletRequest request, String status) {
        // 初始化促销
        marketing.setCreateTime(new Date());
        marketing.setModTime(new Date());
        marketing.setFlag("0");
        String flage = "0";
        // 插入促销信息
        Codex codex = codexService.queryCodexDetail(Long.parseLong(codexType));
        if (codex.getCodexStatus() != null) {
            marketing.setMarketGroupId(codex.getCodexStatus().toString());
        }
        int insertFlag = marketingMapper.insertMarketing(marketing);

        // 获取促销Id
        Long marketingId = marketingMapper.selectLastId();

        if (insertFlag == 1) {
            // 创建促销规则级联数据
            MarketingCodex mc = new MarketingCodex();
            mc.setCreateTime(new Date());
            mc.setFlag(flage);
            mc.setMarketingId(marketingId);
            mc.setModTime(new Date());
            mc.setCodexId(Long.valueOf(request.getParameter(CODEXID)));
            marketingCodexMapper.insertMarketingCodex(mc);
        }
        String price = request.getParameter(MarketingValue.FULLPRICE);
        BigDecimal fullPrice = null;
        if (price != null && !"".equals(price)) {
            fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
        }
        judgeCodexType(codexType, request, flage, marketingId, fullPrice);
        List<MarketingScope> mslist = new ArrayList<MarketingScope>();
        if ("1".equals(status)) {
            // 分类插入
            String[] cateId;
            // 判断是否是全场促销 0 不是，1是
            if ("0".equals(marketing.getIsAll())) {
                cateId = request.getParameterValues(CATEIDP);
            } else {
                cateId = request.getParameterValues(THIRDCATE_IDS);
            }
            for (int i = 0; i < cateId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag(flage);
                ms.setMarketingId(marketingId);
                ms.setScopeId(Long.valueOf(cateId[i]));
                ms.setScopeType("0");
                mslist.add(ms);
            }
        } else if ("2".equals(status)) {
            // 品牌插入
            String[] brandId;
            // 判断是否是全场促销 0 不是，1是
            if ("0".equals(marketing.getIsAll())) {
                brandId = request.getParameterValues(BRANDIDP);
            } else {
                brandId = request.getParameterValues(BRAND_ALLIDS);
            }

            for (int i = 0; i < brandId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag(flage);
                ms.setMarketingId(marketingId);
                ms.setScopeId(Long.valueOf(brandId[i]));
                ms.setScopeType("1");
                mslist.add(ms);
            }
        } else {
            if (Integer.parseInt(codexType) == 15) {
                String[] goodsIds = request.getParameterValues(GOODSIDP);
                if (goodsIds != null && goodsIds.length != 0) {
                    for (int i = 0; i < goodsIds.length; i++) {
                        List<GoodsProduct> goodsInfo = goodsProductMapper.queryGoodsInfoIdByGoodsId(Long.valueOf(goodsIds[i]));
                        for (int j = 0; j < goodsInfo.size(); j++) {
                            MarketingScope ms = new MarketingScope();
                            ms.setCreateTime(new Date());
                            ms.setFlag(flage);
                            ms.setMarketingId(marketingId);
                            ms.setScopeId(goodsInfo.get(j).getGoodsInfoId());
                            ms.setScopeType("2");
                            mslist.add(ms);
                        }

                    }
                }
            } else {
                String[] goodsIds = request.getParameterValues(GOODSIDP);
                if (goodsIds != null && goodsIds.length != 0) {
                    for (int i = 0; i < goodsIds.length; i++) {
                        GoodsProduct goodsInfo = goodsProductMapper.selectByGoodsInfoId(Long.valueOf(goodsIds[i]));
                        if (goodsInfo != null) {
                            MarketingScope ms = new MarketingScope();
                            ms.setCreateTime(new Date());
                            ms.setFlag(flage);
                            ms.setMarketingId(marketingId);
                            ms.setScopeId(goodsInfo.getGoodsInfoId());
                            ms.setScopeType("2");
                            mslist.add(ms);
                        }

                    }
                }
            }

        }
        if (!mslist.isEmpty()) {
            marketingScopeMapper.insertMarketingScope(mslist);
        }

        return insertFlag;
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#newDoAddMarketing(com.
     * ningpai.marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest, java.lang.String,
     * java.lang.Long[], java.lang.Long[])
     */
    @Override
    @Transactional
    public int newDoAddMarketing(Marketing marketing, String codexType, HttpServletRequest request, String status, Long[] lelvelId, Long[] promotionLogoId) {
        // 初始化促销
        marketing.setCreateTime(new Date());
        marketing.setModTime(new Date());
        marketing.setFlag("0");
        String flage = "0";
        if (marketing.getBusinessId() == null) {
            marketing.setBusinessId(0L);
        }
        // 插入促销信息
        Codex codex = codexService.queryCodexDetail(Long.parseLong(codexType));
        if (codex.getCodexStatus() != null) {
            marketing.setMarketGroupId(codex.getCodexStatus().toString());
        }
        String money = request.getParameter("shippingMoney");
        BigDecimal shippingMoney = null;
        if (money != null && !"".equals(money)) {
            shippingMoney = BigDecimal.valueOf(Double.valueOf(request.getParameter("shippingMoney")));
            marketing.setShippingMoney(shippingMoney);
        }
        int insertFlag = marketingMapper.insertMarketing(marketing);

        // 获取促销Id
        Long marketingId = marketingMapper.selectLastId();

        if (insertFlag == 1) {
            // 创建促销规则级联数据
            MarketingCodex mc = new MarketingCodex();
            mc.setCreateTime(new Date());
            mc.setFlag(flage);
            mc.setMarketingId(marketingId);
            mc.setModTime(new Date());
            mc.setCodexId(Long.valueOf(request.getParameter(CODEXID)));
            marketingCodexMapper.insertMarketingCodex(mc);
            // 创建促销等级级联数据
            if (lelvelId != null && lelvelId.length > 0) {
                for (int i = 0; i < lelvelId.length; i++) {
                    // new一个促销会员等级对象
                    MarketLelvel lelvel = new MarketLelvel();
                    // 促销ID
                    lelvel.setMarketingId(marketingId);
                    // 等级ID
                    lelvel.setLelvelId(lelvelId[i]);
                    // 删除标记为0 未删除
                    lelvel.setLelvelDelFlag("0");
                    // 执行添加促销等级操作
                    marketLelvelMapper.insertSelective(lelvel);
                }
            }
            // 添加促销LOGO级联数据
            if (promotionLogoId != null && promotionLogoId.length > 0) {
                for (int i = 0; i < promotionLogoId.length; i++) {
                    // new 一个促销LOGO的对象
                    MarketLogo logo = new MarketLogo();
                    // 促销ID
                    logo.setMarketingId(marketingId);
                    // 促销LOGOId
                    logo.setPromotionLogoId(promotionLogoId[i]);
                    // 删除标记为0 未删除
                    logo.setDelFlag("0");
                    // 执行添加促成LOGO级联操作
                    marketLogoMapper.insertSelective(logo);
                }
            }

        }
        String price = request.getParameter(MarketingValue.FULLPRICE);
        BigDecimal fullPrice = null;
        if (price != null && !"".equals(price)) {
            fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
        }
        newJudgeCodexType(marketing, codexType, request, flage, marketingId, fullPrice);
        List<MarketingScope> mslist = new ArrayList<MarketingScope>();
        if ("1".equals(status)) {
            // 分类插入
            String[] cateId;
            // 判断是否是全场促销 0 不是，1是
            if ("0".equals(marketing.getIsAll())) {
                cateId = request.getParameterValues(CATEIDP);
            } else {
                cateId = request.getParameterValues(THIRDCATE_IDS);
            }
            for (int i = 0; i < cateId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag(flage);
                ms.setMarketingId(marketingId);
                ms.setScopeId(Long.valueOf(cateId[i]));
                ms.setScopeType("0");
                mslist.add(ms);
            }
        } else if ("2".equals(status)) {
            // 品牌插入
            String[] brandId;
            // 判断是否是全场促销 0 不是，1是
            if ("0".equals(marketing.getIsAll())) {
                brandId = request.getParameterValues(BRANDIDP);
            } else {
                brandId = request.getParameterValues(BRAND_ALLIDS);
            }

            for (int i = 0; i < brandId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag(flage);
                ms.setMarketingId(marketingId);
                ms.setScopeId(Long.valueOf(brandId[i]));
                ms.setScopeType("1");
                mslist.add(ms);
            }
        } else {
            // 商品插入
            String[] goodsIds;
            // 判断是否全场商品促销
            if ("0".equals(marketing.getIsAll())) {
                goodsIds = request.getParameterValues(GOODSIDP);
            } else {
                goodsIds = request.getParameterValues(PRODUCT_ALLIDS);
            }
            if (goodsIds != null && goodsIds.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    GoodsProduct goodsInfo = goodsProductMapper.selectByGoodsInfoId(Long.valueOf(goodsIds[i]));
                    if (goodsInfo != null) {
                        MarketingScope ms = new MarketingScope();
                        ms.setCreateTime(new Date());
                        ms.setFlag(flage);
                        ms.setMarketingId(marketingId);
                        ms.setScopeId(goodsInfo.getGoodsInfoId());
                        ms.setScopeType("2");
                        mslist.add(ms);
                    }

                }
            }

        }
        if (!mslist.isEmpty()) {
            marketingScopeMapper.insertMarketingScope(mslist);
        }

        return insertFlag;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#doModifyMarketing(com.
     * ningpai.marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public int doModifyMarketing(Marketing marketing, String codexType, HttpServletRequest request, String status) {
        int insertFlag = marketingMapper.modifyMarketing(marketing);
        String price = request.getParameter(MarketingValue.FULLPRICE);
        BigDecimal fullPrice = null;
        if (price != null && !"".equals(price)) {
            fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
        }
        modifyCodexType(codexType, request, marketing.getMarketingId(), fullPrice);

        if (Integer.parseInt(codexType) == 15) {
            String[] goodsIds = request.getParameterValues(GOODSIDP);
            if (goodsIds != null && goodsIds.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    List<GoodsProduct> goodsInfo = goodsProductMapper.queryGoodsInfoIdByGoodsId(Long.valueOf(goodsIds[i]));
                    for (int j = 0; j < goodsInfo.size(); j++) {
                        MarketingScope ms = new MarketingScope();
                        ms.setModTime(new Date());
                        ms.setMarketingId(marketing.getMarketingId());
                        ms.setScopeId(goodsInfo.get(j).getGoodsInfoId());
                        ms.setScopeType("2");
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(MARKETINGID, marketing.getMarketingId());
                        map.put(SCOPEID, goodsInfo.get(j).getGoodsInfoId());
                        int a = marketingScopeMapper.queryScopeByMarketingIdAndScopeId(map);
                        if (a > 0) {// 已存在，修改即可
                            marketingScopeMapper.modifyMarketingScope(ms);
                        } else {// 不存在，插入
                            ms.setFlag("0");
                            marketingScopeMapper.insertMarketingScopeSingle(ms);
                        }
                    }
                }
            }
        } else {
            String[] goodsIds = request.getParameterValues(GOODSIDP);
            if (goodsIds != null && goodsIds.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    GoodsProduct goodsInfo = goodsProductMapper.selectByGoodsInfoId(Long.valueOf(goodsIds[i]));
                    if (goodsInfo != null) {
                        MarketingScope ms = new MarketingScope();
                        ms.setModTime(new Date());
                        ms.setMarketingId(marketing.getMarketingId());
                        ms.setScopeId(goodsInfo.getGoodsInfoId());
                        ms.setScopeType("2");
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put(MARKETINGID, marketing.getMarketingId());
                        map.put(SCOPEID, goodsInfo.getGoodsInfoId());
                        int a = marketingScopeMapper.queryScopeByMarketingIdAndScopeId(map);
                        if (a > 0) {// 已存在，修改即可
                            marketingScopeMapper.modifyMarketingScope(ms);
                        } else {// 不存在，插入
                            ms.setFlag("0");
                            marketingScopeMapper.insertMarketingScopeSingle(ms);
                        }
                    }

                }
            }
        }
        return insertFlag;
    }

    private void modifyCodexType(String codexType, HttpServletRequest request, Long marketingId, BigDecimal fullPrice) {
        // 判断促销类型 修改不同的数据表
        if ("1".equals(codexType)) {
            PriceOffMarketing poff = new PriceOffMarketing();
            poff.setOffValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(OFFVALUE))));
            poff.setMarketingId(marketingId);
            // 修改直降信息表
            priceOffMarketingMapper.modifyPriceOffMarketing(poff);
        } else if ("4".equals(codexType)) {
            DiscountMarketing dm = new DiscountMarketing();
            dm.setDiscountValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(DISCOUNTVALUE))));
            dm.setMarketingId(marketingId);
            // 修改买折
            discountMarketingMapper.modifyDiscountMarketing(dm);
        } else if ("5".equals(codexType)) {
            FullbuyReduceMarketing frm = new FullbuyReduceMarketing();
            frm.setFullPrice(fullPrice);
            frm.setReducePrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCEPRICE))));
            frm.setMarketingId(marketingId);
            // 插入满减
            fullbuyReduceMarketingMapper.modifyFullbuyReduceMarketing(frm);
        } else if ("8".equals(codexType)) {
            FullbuyDiscountMarketing fdm = new FullbuyDiscountMarketing();
            fdm.setFullPrice(fullPrice);
            fdm.setFullbuyDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLBUYDISCOUNT))));
            fdm.setMarketingId(marketingId);
            // 插入满折
            fullbuyDiscountMarketingMapper.modifyFullbuyDiscountMarketing(fdm);
        } else if ("13".equals(codexType)) {
            // 满x件打y折
            FullbuyNoDiscountMarketing fbuyno = new FullbuyNoDiscountMarketing();
            fbuyno.setMarketingId(marketingId);
            // 满多少件
            fbuyno.setPackagesNo(Long.valueOf(request.getParameter("packagesNo")));
            fbuyno.setPackagebuyDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter(PACKAGEBUYDISCOUNT))));
            fullbuyNoDiscountMarketingMapper.modifySelective(fbuyno);
        } else if ("14".equals(codexType)) {
            // 满购件数多少钱
            FullbuyNoCountMarketing fcountno = new FullbuyNoCountMarketing();
            fcountno.setMarketingId(marketingId);
            fcountno.setCountNo(Long.valueOf(request.getParameter(COUNTNO)));
            fcountno.setCountMoney(BigDecimal.valueOf(Double.valueOf(request.getParameter(COUNTMONEY))));
            fullbuyNoCountMarketingMapper.modifySelective(fcountno);
        } else if ("15".equals(codexType)) {
            // 折扣促销
            String[] goodsIds = request.getParameterValues(GOODSIDP);
            String[] discountInfo = request.getParameterValues(DISCOUNTINFO);
            String[] discountPrice = request.getParameterValues(DISCOUNTPRICE);
            String[] discountFlag = request.getParameterValues(DISCOUNTFLAG);
            if (goodsIds != null && goodsIds.length != 0 && discountInfo != null && discountInfo.length != 0 && discountFlag != null && discountFlag.length != 0
                    && discountPrice != null && discountPrice.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    List<GoodsProduct> goodsInfo = goodsProductMapper.queryGoodsInfoIdByGoodsId(Long.valueOf(goodsIds[i]));

                    if (goodsInfo != null) {
                        for (int j = 0; j < goodsInfo.size(); j++) {
                            PreDiscountMarketing pdm = new PreDiscountMarketing();
                            pdm.setMarketingId(marketingId);
                            pdm.setGoodsId(goodsInfo.get(j).getGoodsInfoId());
                            pdm.setDiscountInfo(new BigDecimal(discountInfo[i]));
                            pdm.setDiscountFlag(discountFlag[i]);
                            pdm.setDiscountPrice(new BigDecimal(discountPrice[i]));
                            Map<String, Object> map = new HashMap<String, Object>();
                            map.put(GOODSID, goodsInfo.get(j).getGoodsInfoId());
                            map.put(MARKETINGID, marketingId);
                            int a = preDiscountMarketingMapper.queryByGoodsIdAndMarketingId(map);
                            if (a > 0) {// 已存在，修改即可
                                preDiscountMarketingMapper.modifySelective(pdm);
                            } else {// 不存在，添加
                                pdm.setDelFlag("0");
                                preDiscountMarketingMapper.insertSelectiveSingle(pdm);
                            }
                        }
                    }
                }
            }
        }
    }

    private void judgeCodexType(String codexType, HttpServletRequest request, String flage, Long marketingId, BigDecimal fullPrice) {
        // 判断促销类型 分插不同的数据表
        // 直降
        if ("1".equals(codexType)) {
            PriceOffMarketing poff = new PriceOffMarketing();
            poff.setDelFlag(flage);
            poff.setOffValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(OFFVALUE))));
            poff.setMarketingId(marketingId);
            // 插入直降信息表
            priceOffMarketingMapper.insertPriceOffMarketing(poff);
            // 买送赠品
        } else if ("2".equals(codexType)) {
            String[] pmGiftIds = request.getParameterValues(GIFTIDS);
            if (pmGiftIds != null && pmGiftIds.length != 0) {
                List<PresentationMarketing> list = new ArrayList<PresentationMarketing>();
                for (int i = 0; i < pmGiftIds.length; i++) {
                    PresentationMarketing pm = new PresentationMarketing();
                    pm.setDelFlag(flage);
                    pm.setMarketingId(marketingId);
                    pm.setProductId(Long.valueOf(pmGiftIds[i]));
                    list.add(pm);
                }
                // 插入买赠表
                presentationMarketingMapper.insertPresentationMarketing(list);
            }
            // 买送优惠券
        } else if ("3".equals(codexType)) {
            String[] couponIds = request.getParameterValues(COUPONIDS);
            if (couponIds != null && couponIds.length != 0) {
                List<CouponMarketing> list = new ArrayList<CouponMarketing>();
                for (int i = 0; i < couponIds.length; i++) {
                    CouponMarketing cm = new CouponMarketing();
                    cm.setCouponId(Long.valueOf(couponIds[i]));
                    cm.setDelFlag(flage);
                    cm.setMarketingId(marketingId);
                    list.add(cm);
                }
                // 插入买送优惠券表
                couponMarketingMapper.insertCouponMarketing(list);
            }
            // 买折扣
        } else if ("4".equals(codexType)) {
            DiscountMarketing dm = new DiscountMarketing();
            dm.setDelFlag(flage);
            dm.setDiscountValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(DISCOUNTVALUE))));
            dm.setMarketingId(marketingId);
            // 插入买折
            discountMarketingMapper.insertDiscountMarketing(dm);
            // 满减
        } else if ("5".equals(codexType)) {
            FullbuyReduceMarketing frm = new FullbuyReduceMarketing();
            frm.setDelFlag(flage);
            frm.setFullPrice(fullPrice);
            frm.setReducePrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCEPRICE))));
            frm.setMarketingId(marketingId);
            // 插入满减
            fullbuyReduceMarketingMapper.insertFullbuyReduceMarketing(frm);
            // 满赠赠品
        } else if ("6".equals(codexType)) {

            String[] productIds = request.getParameterValues(GIFTIDS);
            if (productIds != null && productIds.length != 0) {
                List<FullbuyPresentMarketing> list = new ArrayList<FullbuyPresentMarketing>();
                for (int i = 0; i < productIds.length; i++) {
                    FullbuyPresentMarketing fpm = new FullbuyPresentMarketing();
                    fpm.setDelFlag(flage);
                    fpm.setFullPrice(fullPrice);
                    fpm.setMarketingId(marketingId);
                    fpm.setProductId(Long.valueOf(productIds[i]));
                    list.add(fpm);
                }
                // 插入满赠
                fullbuyPresentMarketingMapper.insertFullbuyPresentMarketing(list);
            }
            // 满送优惠券
        } else if ("7".equals(codexType)) {

            String[] couponId = request.getParameterValues(COUPONIDS);
            if (couponId != null && couponId.length != 0) {
                List<FullbuyCouponMarketing> list = new ArrayList<FullbuyCouponMarketing>();
                for (int i = 0; i < couponId.length; i++) {
                    FullbuyCouponMarketing fcm = new FullbuyCouponMarketing();
                    fcm.setDelFlag(flage);
                    fcm.setFullPrice(fullPrice);
                    fcm.setMarketingId(marketingId);
                    fcm.setCouponId(Long.valueOf(couponId[i]));
                    list.add(fcm);
                }
                // 批量插入满送优惠券
                fullbuyCouponMarketingMapper.insertFullbuyCouponMarketing(list);
            }
            // 满折
        } else if ("8".equals(codexType)) {
            FullbuyDiscountMarketing fdm = new FullbuyDiscountMarketing();
            fdm.setDelFlag(flage);
            fdm.setFullPrice(fullPrice);
            fdm.setFullbuyDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLBUYDISCOUNT))));
            fdm.setMarketingId(marketingId);
            // 插入满折
            fullbuyDiscountMarketingMapper.insertFullbuyDiscountMarketing(fdm);
            // 限购
        } else if ("9".equals(codexType)) {
            LimitBuyMarketing limit = new LimitBuyMarketing();
            limit.setDelFlag(flage);
            limit.setLimitCount(Long.valueOf(request.getParameter(LIMITCOUNT)));
            limit.setMarketingId(marketingId);
            limitBuyMarketingMapper.insertLimitBuyMarketing(limit);
            // 团购
        } else if ("10".equals(codexType)) {
            Groupon g = new Groupon();
            g.setFlag(flage);
            g.setGrouponStatus("0");
            // 促销id
            g.setMarketId(marketingId);
            g.setParticipateCount(0L);
            // 折扣率
            g.setGrouponDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter("grouponDiscount"))));
            //add by ly start 增加团购的会员折扣，折扣价和会员折扣价
            g.setGrouponVipDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter("grouponVipDiscount"))));
            g.setGrouponPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("goodsInfoPreferTgPrice"))));
            g.setGrouponVipPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("goodsInfoVipTgPrice"))));
            //add by ly end
            // 团购需要人数
            grouponMapper.insertSelective(g);
        } else if ("11".equals(codexType)) {
            MarketingRush rush = new MarketingRush();
            rush.setRushDiscount(new BigDecimal(request.getParameter("rushDiscount")));
            rush.setRushLimitation(Integer.valueOf(request.getParameter("rushLimitation")));
            rush.setRushImage(request.getParameter("rushImage"));
            rush.setMarketId(marketingId);
            //ADD BY LUYONG  start
            rush.setRushVipDiscount(new BigDecimal(request.getParameter("rushVipDiscount")));
            rush.setRushPrice(new BigDecimal(request.getParameter("rushPrice")));
            rush.setRushVipPrice(new BigDecimal(request.getParameter("rushVipPrice")));
            //ADD BY LUYONG  end
            rushMapper.insertRush(rush);

        } else if ("13".equals(codexType)) {
            // 满x件打y折
            FullbuyNoDiscountMarketing fbuyno = new FullbuyNoDiscountMarketing();
            fbuyno.setDelFlag(flage);
            fbuyno.setMarketingId(marketingId);
            // 满多少件
            fbuyno.setPackagesNo(Long.valueOf(request.getParameter(PACKAGESNO)));
            fbuyno.setPackagebuyDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter(PACKAGEBUYDISCOUNT))));
            fullbuyNoDiscountMarketingMapper.insertSelective(fbuyno);
        } else if ("14".equals(codexType)) {
            // 满购件数多少钱
            FullbuyNoCountMarketing fcountno = new FullbuyNoCountMarketing();
            fcountno.setDelFlag(flage);
            fcountno.setMarketingId(marketingId);
            fcountno.setCountNo(Long.valueOf(request.getParameter(COUNTNO)));
            fcountno.setCountMoney(BigDecimal.valueOf(Double.valueOf(request.getParameter(COUNTMONEY))));
            fullbuyNoCountMarketingMapper.insertSelective(fcountno);
        } else if ("15".equals(codexType)) {
            // 折扣促销
            String[] goodsIds = request.getParameterValues(GOODSIDP);
            String[] discountInfo = request.getParameterValues(DISCOUNTINFO);
            String[] discountPrice = request.getParameterValues(DISCOUNTPRICE);
            String[] discountFlag = request.getParameterValues(DISCOUNTFLAG);
            List<PreDiscountMarketing> pdmlist = new ArrayList<PreDiscountMarketing>();
            if (goodsIds != null && goodsIds.length != 0 && discountInfo != null && discountInfo.length != 0 && discountFlag != null && discountFlag.length != 0
                    && discountPrice != null && discountPrice.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    List<GoodsProduct> goodsInfo = goodsProductMapper.queryGoodsInfoIdByGoodsId(Long.valueOf(goodsIds[i]));

                    if (goodsInfo != null) {
                        for (int j = 0; j < goodsInfo.size(); j++) {
                            PreDiscountMarketing pdm = new PreDiscountMarketing();
                            pdm.setDelFlag(flage);
                            pdm.setMarketingId(marketingId);
                            pdm.setGoodsId(goodsInfo.get(j).getGoodsInfoId());
                            pdm.setDiscountInfo(new BigDecimal(discountInfo[i]));
                            pdm.setDiscountFlag(discountFlag[i]);
                            pdm.setDiscountPrice(new BigDecimal(discountPrice[i]));
                            pdmlist.add(pdm);
                        }
                    }
                }
                preDiscountMarketingMapper.insertSelective(pdmlist);

            }
        }
    }

    /**
     * 根据促销类型添加促销信息
     * 
     * @param codexType
     * @param request
     * @param flage
     * @param marketingId
     * @param fullPrice
     */
    private void newJudgeCodexType(Marketing marketing, String codexType, HttpServletRequest request, String flage, Long marketingId, BigDecimal fullPrice) {
        // 判断促销类型 分插不同的数据表
        // 直降
        if ("1".equals(codexType)) {
            PriceOffMarketing poff = new PriceOffMarketing();
            poff.setDelFlag(flage);
            poff.setOffValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(OFFVALUE))));
            poff.setMarketingId(marketingId);
            poff.setOffVipValue(BigDecimal.valueOf(Double.valueOf(request.getParameter("offVipValue"))));
            // 插入直降信息表
            priceOffMarketingMapper.insertPriceOffMarketing(poff);
            // 买送赠品
        } else if ("2".equals(codexType)) {
            String[] pmGiftIds = request.getParameterValues(GIFTIDS);
            if (pmGiftIds != null && pmGiftIds.length != 0) {
                List<PresentationMarketing> list = new ArrayList<PresentationMarketing>();
                for (int i = 0; i < pmGiftIds.length; i++) {
                    PresentationMarketing pm = new PresentationMarketing();
                    pm.setDelFlag(flage);
                    pm.setMarketingId(marketingId);
                    pm.setProductId(Long.valueOf(pmGiftIds[i]));
                    list.add(pm);
                }
                // 插入买赠表
                presentationMarketingMapper.insertPresentationMarketing(list);
            }
            // 买送优惠券
        } else if ("3".equals(codexType)) {
            String[] couponIds = request.getParameterValues(COUPONIDS);
            if (couponIds != null && couponIds.length != 0) {
                List<CouponMarketing> list = new ArrayList<CouponMarketing>();
                for (int i = 0; i < couponIds.length; i++) {
                    CouponMarketing cm = new CouponMarketing();
                    cm.setCouponId(Long.valueOf(couponIds[i]));
                    cm.setDelFlag(flage);
                    cm.setMarketingId(marketingId);
                    list.add(cm);
                }
                // 插入买送优惠券表
                couponMarketingMapper.insertCouponMarketing(list);
            }
            // 买折扣
        } else if ("4".equals(codexType)) {
            DiscountMarketing dm = new DiscountMarketing();
            dm.setDelFlag(flage);
            dm.setDiscountValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(DISCOUNTVALUE))));
            dm.setMarketingId(marketingId);
            // 插入买折
            discountMarketingMapper.insertDiscountMarketing(dm);
            // 满减
        } else if ("5".equals(codexType)) {
            // 获取多级满多少元
            String[] prices = request.getParameterValues(MarketingValue.FULLPRICE);
            // 获取多级减多少元
            String[] reduceprices = request.getParameterValues(REDUCEPRICE);
            // 获取会员多级满多少元
            String[] vipPrices = request.getParameterValues("vipFullPrice");
            // 获取会员多级减多少元
            String[] vipReduceprices = request.getParameterValues("vipReducePrice");
            // 判断是否为空
            if (prices != null && prices.length > 0) {
                // 循环变量多级促销
                for (int i = 0; i < prices.length; i++) {
                    for (int j = 0; j < reduceprices.length; j++) {
                        // 满多少元减多少元对应
                        if (i == j && prices[i] != null && !"".equals(prices[i]) && reduceprices[j] != null && !"".equals(reduceprices[j])) {
                            // 定义满减对象
                            FullbuyReduceMarketing frm = new FullbuyReduceMarketing();
                            // 删除标记
                            frm.setDelFlag(flage);
                            // 满多少元
                            frm.setFullPrice(BigDecimal.valueOf(Double.valueOf(prices[i])));
                            // 减多少元
                            frm.setReducePrice(BigDecimal.valueOf(Double.valueOf(reduceprices[j])));
                            // 会员满多少元
                            frm.setVipFullPrice(BigDecimal.valueOf(Double.valueOf(vipPrices[i])));
                            // 会员减多少元
                            frm.setVipReducePrice(BigDecimal.valueOf(Double.valueOf(vipReduceprices[j])));
                            // 关联的促销ID
                            frm.setMarketingId(marketingId);
                            // 插入满减
                            fullbuyReduceMarketingMapper.insertFullbuyReduceMarketing(frm);
                        }
                    }
                }
            }
            // 满赠赠品
        } else if ("6".equals(codexType)) {

            String[] productIds = request.getParameterValues(GIFTIDS);
            if (productIds != null && productIds.length != 0) {
                List<FullbuyPresentMarketing> list = new ArrayList<FullbuyPresentMarketing>();
                for (int i = 0; i < productIds.length; i++) {
                    FullbuyPresentMarketing fpm = new FullbuyPresentMarketing();
                    fpm.setDelFlag(flage);
                    fpm.setFullPrice(fullPrice);
                    fpm.setMarketingId(marketingId);
                    fpm.setProductId(Long.valueOf(productIds[i]));
                    list.add(fpm);
                }
                // 插入满赠
                fullbuyPresentMarketingMapper.insertFullbuyPresentMarketing(list);
            }
            // 满送优惠券
        } else if ("7".equals(codexType)) {

            String[] couponId = request.getParameterValues(COUPONIDS);
            if (couponId != null && couponId.length != 0) {
                List<FullbuyCouponMarketing> list = new ArrayList<FullbuyCouponMarketing>();
                for (int i = 0; i < couponId.length; i++) {
                    FullbuyCouponMarketing fcm = new FullbuyCouponMarketing();
                    fcm.setDelFlag(flage);
                    fcm.setFullPrice(fullPrice);
                    fcm.setMarketingId(marketingId);
                    fcm.setCouponId(Long.valueOf(couponId[i]));
                    list.add(fcm);
                }
                // 批量插入满送优惠券
                fullbuyCouponMarketingMapper.insertFullbuyCouponMarketing(list);
            }
            // 满折
        } else if ("8".equals(codexType)) {
            // 获取多级满多少元
            String[] prices = request.getParameterValues(MarketingValue.FULLPRICE);
            String[] discounts = request.getParameterValues(FULLBUYDISCOUNT);
            String[] vipPrices = request.getParameterValues("vipFullPrice");
            String[] vipDiscounts = request.getParameterValues("vipFullbuyDiscount");
            if (prices != null && prices.length > 0) {
                for (int i = 0; i < prices.length; i++) {
                    for (int j = 0; j < discounts.length; j++) {
                        if (i - 1 == j && prices[i] != null && !"".equals(prices[i]) && discounts[j] != null && !"".equals(discounts[j])) {
                            // new一个满折对象
                            FullbuyDiscountMarketing fdm = new FullbuyDiscountMarketing();
                            fdm.setDelFlag(flage);
                            fdm.setFullPrice(BigDecimal.valueOf(Double.valueOf(prices[i])));
                            fdm.setFullbuyDiscount(BigDecimal.valueOf(Double.valueOf(discounts[j])));
                            fdm.setVipFullPrice(BigDecimal.valueOf(Double.valueOf(vipPrices[i])));
                            fdm.setVipFullbuyDiscount(BigDecimal.valueOf(Double.valueOf(vipDiscounts[j])));
                            fdm.setMarketingId(marketingId);
                            // 插入满折
                            fullbuyDiscountMarketingMapper.insertFullbuyDiscountMarketing(fdm);
                        }
                    }
                }
            }
            // 限购
        } else if ("9".equals(codexType)) {
            LimitBuyMarketing limit = new LimitBuyMarketing();
            limit.setDelFlag(flage);
            limit.setLimitCount(Long.valueOf(request.getParameter(LIMITCOUNT)));
            limit.setMarketingId(marketingId);
            limitBuyMarketingMapper.insertLimitBuyMarketing(limit);
            // 团购
        } else if ("10".equals(codexType)) {
            Groupon g = new Groupon();
            g.setFlag(flage);
            g.setGrouponStatus("0");
            // 促销id
            g.setMarketId(marketingId);
            g.setParticipateCount(0L);
            // 折扣率
            g.setGrouponDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter("grouponDiscount"))));
            //add by ly start 增加团购的会员折扣，折扣价和会员折扣价
            g.setGrouponVipDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter("grouponVipDiscount"))));
            g.setGrouponPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("goodsInfoPreferTgPrice"))));
            g.setGrouponVipPrice(BigDecimal.valueOf(Double.valueOf(request.getParameter("goodsInfoVipTgPrice"))));
            //add by ly end
            // 团购需要人数
            grouponMapper.insertSelective(g);
        } else if ("11".equals(codexType)) {//抢购
            MarketingRush rush = new MarketingRush();
            rush.setRushDiscount(new BigDecimal(request.getParameter("rushDiscount")));
            rush.setRushLimitation(Integer.valueOf(request.getParameter("rushLimitation")));
            rush.setRushImage(request.getParameter("rushImage"));
            rush.setMarketId(marketingId);
            //ADD BY LUYONG  start
            rush.setRushVipDiscount(new BigDecimal(request.getParameter("rushVipDiscount")));
            rush.setRushPrice(new BigDecimal(request.getParameter("rushPrice")));
            rush.setRushVipPrice(new BigDecimal(request.getParameter("rushVipPrice")));
            //ADD BY LUYONG  end
            rushMapper.insertRush(rush);

        } else if ("13".equals(codexType)) {
            // 获取满件信息
            String[] packagesNos = request.getParameterValues(PACKAGESNO);
            // 获取打折信息
            String[] packagebuyDiscount = request.getParameterValues(PACKAGEBUYDISCOUNT);
            if (packagesNos != null && packagesNos.length > 0) {
                // 循环变量满多少件打多少折对应的信息
                for (int i = 0; i < packagesNos.length; i++) {
                    for (int j = 0; j < packagebuyDiscount.length; j++) {
                        // 判断不为空
                        if (i == j && packagesNos[i] != null && !"".equals(packagesNos[i]) && packagebuyDiscount[j] != null && !"".equals(packagebuyDiscount[j])) {
                            // 满x件打y折
                            FullbuyNoDiscountMarketing fbuyno = new FullbuyNoDiscountMarketing();
                            // 删除标记
                            fbuyno.setDelFlag(flage);
                            // 促销id
                            fbuyno.setMarketingId(marketingId);
                            // 满多少件
                            fbuyno.setPackagesNo(Long.valueOf(packagesNos[i]));
                            // 打多少折
                            fbuyno.setPackagebuyDiscount(BigDecimal.valueOf(Double.valueOf(packagebuyDiscount[j])));
                            // 插入满多少件打多少折
                            fullbuyNoDiscountMarketingMapper.insertSelective(fbuyno);
                        }
                    }
                }
            }
        } else if ("14".equals(codexType)) {
            // 获取满件信息
            String[] countNos = request.getParameterValues(COUNTNO);
            // 共多少元
            String[] countMoneys = request.getParameterValues(COUNTMONEY);
            if (countNos != null && countNos.length > 0) {
                // 循环变量满多少件共多少元对应的信息
                for (int i = 0; i < countNos.length; i++) {
                    for (int j = 0; j < countMoneys.length; j++) {
                        // 判断不为空
                        if (i == j && countNos[i] != null && !"".equals(countNos[i]) && countMoneys[j] != null && !"".equals(countMoneys[j])) {
                            // 满购件数多少钱
                            FullbuyNoCountMarketing fcountno = new FullbuyNoCountMarketing();
                            // 删除标记
                            fcountno.setDelFlag(flage);
                            // 促销ID
                            fcountno.setMarketingId(marketingId);
                            // 满多少件
                            fcountno.setCountNo(Long.valueOf(countNos[i]));
                            // 共多少元
                            fcountno.setCountMoney(BigDecimal.valueOf(Double.valueOf(countMoneys[j])));
                            // 插入满多少件共多少元
                            fullbuyNoCountMarketingMapper.insertSelective(fcountno);
                        }
                    }
                }
            }
            /*
             * //满购件数多少钱 FullbuyNoCountMarketing fcountno = new
             * FullbuyNoCountMarketing(); fcountno.setDelFlag(flage);
             * fcountno.setMarketingId(marketingId);
             * fcountno.setCountNo(Long.valueOf
             * (request.getParameter(COUNTNO)));
             * fcountno.setCountMoney(BigDecimal
             * .valueOf(Double.valueOf(request.getParameter(COUNTMONEY))));
             * fullbuyNoCountMarketingMapper.insertSelective(fcountno);
             */
        } else if ("15".equals(codexType)) {
            // 折扣促销
            String[] goodsIds;
            // 判断是否全场商品促销
            if ("0".equals(marketing.getIsAll())) {
                goodsIds = request.getParameterValues(GOODSIDP);
            } else {
                goodsIds = request.getParameterValues(PRODUCT_ALLIDS);
            }

            String[] discountInfo = request.getParameterValues(DISCOUNTINFO);
            String[] discountFlag = request.getParameterValues(DISCOUNTFLAG);
            String[] discountPrice = request.getParameterValues(DISCOUNTPRICE);
            String[] vipDiscountInfo = request.getParameterValues("vipDiscountInfo");
            //String[] vipDiscountPrice = request.getParameterValues(DISCOUNTPRICE);
            String[] vipDiscountPrice = request.getParameterValues("vipDiscountPrice");
            List<PreDiscountMarketing> pdmlist = new ArrayList<PreDiscountMarketing>();
            if (goodsIds != null && goodsIds.length != 0 && discountInfo != null && discountInfo.length != 0) {
                for (int i = 0; i < goodsIds.length; i++) {
                    PreDiscountMarketing pdm = new PreDiscountMarketing();
                    pdm.setDelFlag(flage);
                    pdm.setMarketingId(marketingId);
                    pdm.setGoodsId(Long.valueOf(goodsIds[i]));
                    if (discountFlag != null && discountFlag.length != 0) {
                        pdm.setDiscountFlag(discountFlag[i]);
                    }
                    // 全部商品
                    if ("1".equals(marketing.getIsAll())) {
                        pdm.setDiscountPrice(goodsProductMapper.selectByPrimaryKey(Long.valueOf(goodsIds[i])).getGoodsInfoPreferPrice().multiply(new BigDecimal(discountInfo[0])));
                        pdm.setDiscountInfo(new BigDecimal(discountInfo[0]));
                        pdm.setDiscountInfo(new BigDecimal(vipDiscountInfo[0]));
                    } else {
                        pdm.setDiscountInfo(new BigDecimal(discountInfo[i]));
                        pdm.setVipdiscountInfo(new BigDecimal(vipDiscountInfo[i]));

                        pdm.setDiscountPrice(BigDecimal.valueOf(Double.valueOf(discountPrice[i])));
                        pdm.setVipdiscountPrice(BigDecimal.valueOf(Double.valueOf(vipDiscountPrice[i])));
                    }

                    pdmlist.add(pdm);
                }
                preDiscountMarketingMapper.insertSelective(pdmlist);

            }
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#doUpdateMarketing(com.
     * ningpai.marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest)
     */
    @Override
    @Transactional
    public int doUpdateMarketing(Marketing marketing, String codexType, HttpServletRequest request) {
        // 查询促销
        Marketing mt = this.marketingMapper.marketingDetail(marketing.getMarketingId());
        this.delAllShip(mt.getCodexType(), marketing.getMarketingId());

        // 修改时间
        marketing.setModTime(new Date());

        // 插入促销信息
        int updateFlag = marketingMapper.updateMarketing(marketing);

        if (updateFlag == 1) {
            // 创建促销规则级联数据
            MarketingCodex mc = new MarketingCodex();
            mc.setCreateTime(new Date());
            mc.setFlag("0");
            mc.setMarketingId(marketing.getMarketingId());
            mc.setModTime(new Date());
            mc.setCodexId(Long.valueOf(request.getParameter(CODEXID)));
            marketingCodexMapper.insertMarketingCodex(mc);
        }
        String price = request.getParameter(MarketingValue.FULLPRICE);
        BigDecimal fullPrice = null;
        if (price != null && !"".equals(price)) {
            fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
        }

        judgeCodexType(marketing, codexType, request, fullPrice);
        String[] goodsId = request.getParameterValues(GOODSID);
        List<MarketingScope> mslist = new ArrayList<MarketingScope>();
        if (goodsId != null && goodsId.length != 0) {
            for (int i = 0; i < goodsId.length; i++) {
                MarketingScope ms = new MarketingScope();
                ms.setCreateTime(new Date());
                ms.setFlag("0");
                ms.setMarketingId(marketing.getMarketingId());
                ms.setScopeId(Long.valueOf(goodsId[i]));
                ms.setScopeType("2");
                mslist.add(ms);
            }
        }

        if (!mslist.isEmpty()) {
            marketingScopeMapper.insertMarketingScope(mslist);
        }

        return updateFlag;
    }

    /**
     * 
     * @param marketing
     * @param codexType
     * @param request
     * @param fullPrice
     */
    private void judgeCodexType(Marketing marketing, String codexType, HttpServletRequest request, BigDecimal fullPrice) {
        // 判断促销类型 分插不同的数据表
        if ("1".equals(codexType)) {
            // 直降
            PriceOffMarketing poff = new PriceOffMarketing();
            poff.setDelFlag("0");
            poff.setOffValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(OFFVALUE))));
            poff.setMarketingId(marketing.getMarketingId());
            // 插入直降信息表
            priceOffMarketingMapper.insertPriceOffMarketing(poff);

        } else if ("2".equals(codexType)) {
            // 买送赠品
            String[] pmGiftIds = request.getParameterValues(GIFTIDS);
            if (pmGiftIds != null && pmGiftIds.length != 0) {
                List<PresentationMarketing> list = new ArrayList<PresentationMarketing>();
                for (int i = 0; i < pmGiftIds.length; i++) {
                    PresentationMarketing pm = new PresentationMarketing();
                    pm.setDelFlag("0");
                    pm.setMarketingId(marketing.getMarketingId());
                    pm.setProductId(Long.valueOf(pmGiftIds[i]));
                    list.add(pm);
                }
                // 插入买赠表
                presentationMarketingMapper.insertPresentationMarketing(list);
            }

        } else if ("3".equals(codexType)) {
            // 买送优惠券
            String[] couponIds = request.getParameterValues(COUPONIDS);
            if (couponIds != null && couponIds.length != 0) {
                List<CouponMarketing> list = new ArrayList<CouponMarketing>();
                for (int i = 0; i < couponIds.length; i++) {
                    CouponMarketing cm = new CouponMarketing();
                    cm.setCouponId(Long.valueOf(couponIds[i]));
                    cm.setDelFlag("0");
                    cm.setMarketingId(marketing.getMarketingId());
                    list.add(cm);
                }
                // 插入买送优惠券表
                couponMarketingMapper.insertCouponMarketing(list);
            }

        } else if ("4".equals(codexType)) {
            // 买折扣
            DiscountMarketing dm = new DiscountMarketing();
            dm.setDelFlag("0");
            dm.setDiscountValue(BigDecimal.valueOf(Double.valueOf(request.getParameter(DISCOUNTVALUE))));
            dm.setMarketingId(marketing.getMarketingId());
            // 插入买折
            discountMarketingMapper.insertDiscountMarketing(dm);
        } else if ("5".equals(codexType)) {
            // 满减
            FullbuyReduceMarketing frm = new FullbuyReduceMarketing();
            frm.setDelFlag("0");
            frm.setFullPrice(fullPrice);
            frm.setReducePrice(BigDecimal.valueOf(Double.valueOf(request.getParameter(REDUCEPRICE))));
            frm.setMarketingId(marketing.getMarketingId());
            // 插入满减
            fullbuyReduceMarketingMapper.insertFullbuyReduceMarketing(frm);
        } else if ("6".equals(codexType)) {
            // 满赠赠品
            String[] productIds = request.getParameterValues(GIFTIDS);
            if (productIds != null && productIds.length != 0) {
                List<FullbuyPresentMarketing> list = new ArrayList<FullbuyPresentMarketing>();
                for (int i = 0; i < productIds.length; i++) {
                    FullbuyPresentMarketing fpm = new FullbuyPresentMarketing();
                    fpm.setDelFlag("0");
                    fpm.setFullPrice(fullPrice);
                    fpm.setMarketingId(marketing.getMarketingId());
                    fpm.setProductId(Long.valueOf(productIds[i]));
                    list.add(fpm);
                }
                // 插入满赠
                fullbuyPresentMarketingMapper.insertFullbuyPresentMarketing(list);
            }

        } else if ("7".equals(codexType)) {
            // 满送优惠券
            String[] couponIds = request.getParameterValues(COUPONIDS);
            if (couponIds != null && couponIds.length != 0) {
                List<FullbuyCouponMarketing> list = new ArrayList<FullbuyCouponMarketing>();
                for (int i = 0; i < couponIds.length; i++) {
                    FullbuyCouponMarketing fcm = new FullbuyCouponMarketing();
                    fcm.setDelFlag("0");
                    fcm.setFullPrice(fullPrice);
                    fcm.setMarketingId(marketing.getMarketingId());
                    fcm.setCouponId(Long.valueOf(couponIds[i]));
                    list.add(fcm);
                }
                // 批量插入满送优惠券
                fullbuyCouponMarketingMapper.insertFullbuyCouponMarketing(list);
            }
        } else if ("8".equals(codexType)) {
            // 满折
            FullbuyDiscountMarketing fdm = new FullbuyDiscountMarketing();
            fdm.setDelFlag("0");
            fdm.setFullPrice(fullPrice);
            fdm.setFullbuyDiscount(BigDecimal.valueOf(Double.valueOf(request.getParameter(FULLBUYDISCOUNT))));
            fdm.setMarketingId(marketing.getMarketingId());
            // 插入满折
            fullbuyDiscountMarketingMapper.insertFullbuyDiscountMarketing(fdm);
        } else if ("9".equals(codexType)) {
            // 限购
            LimitBuyMarketing limit = new LimitBuyMarketing();
            limit.setDelFlag("0");
            limit.setLimitCount(Long.valueOf(request.getParameter(LIMITCOUNT)));
            limit.setMarketingId(marketing.getMarketingId());
            limitBuyMarketingMapper.insertLimitBuyMarketing(limit);

        }
    }

    /**
     * 删除所有促销级联信息
     * 
     * @param codexType
     */
    private void delAllShip(String codexType, Long marketingId) {
        // 判断促销类型 分插不同的数据表
        if ("1".equals(codexType)) {
            // 直降
            priceOffMarketingMapper.deletePriceOffMarketing(marketingId);

        } else if ("2".equals(codexType)) {
            // 买送赠品

            presentationMarketingMapper.deletePresentationMarketing(marketingId);

        } else if ("3".equals(codexType)) {
            // 买送优惠券
            couponMarketingMapper.deleteCouponMarketing(marketingId);

        } else if ("4".equals(codexType)) {
            // 买折扣
            discountMarketingMapper.deleteDiscountMarketing(marketingId);

        } else if ("5".equals(codexType)) {
            // 满减
            fullbuyReduceMarketingMapper.deleteFullbuyReduceMarketing(marketingId);

        } else if ("6".equals(codexType)) {
            // 满赠赠品
            fullbuyPresentMarketingMapper.updateFullbuyPresentMarketing(marketingId);

        } else if ("7".equals(codexType)) {
            // 满送优惠券
            fullbuyCouponMarketingMapper.deleteFullbuyCouponMarketing(marketingId);
        } else if ("8".equals(codexType)) {
            // 满折
            fullbuyDiscountMarketingMapper.deleteFullbuyDiscountMarketing(marketingId);

        } else if ("9".equals(codexType)) {// 固定价格

            limitBuyMarketingMapper.deleteLimitBuyMarketing(marketingId);
        }
        else if("10".equals(codexType)){//团购
            grouponMapper.deleteGroupMarketing(marketingId);
        }else if("11".equals(codexType)){//抢购
            rushMapper.deleteRushMarketing(marketingId);
        }else if("15".equals(codexType)){//折扣
            preDiscountMarketingMapper.removePreCountByMid(marketingId);
        }
        // 删除范围信息
        marketingScopeMapper.deleteMarketingScope(marketingId);
        // 删除此促销规则信息
        marketingCodexMapper.deleteMarketingCodex(marketingId);
    }

    /**
     * 删除所有促销级联信息
     * 
     * @param codexType
     */
    private void newDelAllShip(String codexType, Long marketingId) {
        // 删除促销关联会员等级
        marketLelvelMapper.deleteMarketingLevelBy(marketingId);
        // 删除促销关联logo
        marketLogoMapper.delLogoByMarketingId(marketingId);
        // 判断促销类型 分插不同的数据表
        if ("1".equals(codexType)) {
            // 直降
            priceOffMarketingMapper.deletePriceOffMarketing(marketingId);

        } else if ("2".equals(codexType)) {
            // 买送赠品
            presentationMarketingMapper.deletePresentationMarketing(marketingId);

        } else if ("3".equals(codexType)) {
            // 买送优惠券
            couponMarketingMapper.deleteCouponMarketing(marketingId);

        } else if ("4".equals(codexType)) {
            // 买折扣
            discountMarketingMapper.deleteDiscountMarketing(marketingId);

        } else if ("5".equals(codexType)) {
            // 满减
            fullbuyReduceMarketingMapper.deleteFullbuyReduceMarketing(marketingId);

        } else if ("6".equals(codexType)) {
            // 满赠赠品
            fullbuyPresentMarketingMapper.updateFullbuyPresentMarketing(marketingId);

        } else if ("7".equals(codexType)) {
            // 满送优惠券
            fullbuyCouponMarketingMapper.deleteFullbuyCouponMarketing(marketingId);
        } else if ("8".equals(codexType)) {
            // 满折
            fullbuyDiscountMarketingMapper.deleteFullbuyDiscountMarketing(marketingId);

        } else if ("9".equals(codexType)) {// 固定价格

            limitBuyMarketingMapper.deleteLimitBuyMarketing(marketingId);
        } else if ("10".equals(codexType)) {// 团购
            grouponMapper.deleteGroupMarketing(marketingId);
        }
        else if("11".equals(codexType)){//抢购
           rushMapper.deleteRushMarketing(marketingId);
        }
       else if ("13".equals(codexType)) {// 满件打X折
            fullbuyNoDiscountMarketingMapper.delFullbuyNoDiscountByNarketingId(marketingId);
        }
        else if ("14".equals(codexType)) {// 满件共X元
            fullbuyNoCountMarketingMapper.delFullbuyNoCounttByNarketingId(marketingId);
        } else if ("15".equals(codexType)) {// 折扣
            preDiscountMarketingMapper.removePreCountByMid(marketingId);
        }
        // 删除范围信息
        marketingScopeMapper.deleteMarketingScope(marketingId);
        // 删除此促销规则信息
        marketingCodexMapper.deleteMarketingCodex(marketingId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#deleteMarketingById(com
     * .ningpai.marketing.bean.Marketing)
     */
    @Override
    @Transactional
    public int deleteMarketingById(Marketing marketing) {
        this.delAllShip(marketing.getCodexType(), marketing.getMarketingId());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MARKETINGID, marketing.getMarketingId());
        paramMap.put(BUSINESSID, marketing.getBusinessId());
        return marketingMapper.deleteMarketingById(paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#delAllMarketingByIds(java
     * .lang.Long[])
     */
    @Override
    @Transactional
    public int delAllMarketingByIds(Long[] marketingIds, Long businessId) {
        List<Long> list = new ArrayList<Long>();
        for (Long marketingId : marketingIds) {
            Marketing mk = marketingMapper.marketingDetailNotTime(marketingId);
            this.delAllShip(mk.getCodexType(), mk.getMarketingId());
            list.add(marketingId);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("list", list);
        paramMap.put(BUSINESSID, businessId);
        return marketingMapper.delAllMarketingByIds(paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#selectMarketingByGoodsInfoId
     * (java.lang.Long)
     */
    @Override
    public List<Marketing> selectMarketingByGoodsInfoId(Long goodsInfoId, Long brandId, Long cateId) {

        List<MarketingScope> scopeList = queryMarketingScopes(goodsInfoId, brandId, cateId);
        List<Marketing> marketList = new ArrayList<Marketing>();

        if (!CollectionUtils.isEmpty(scopeList)) {
            for (MarketingScope scope : scopeList) {
                Marketing marketing = this.marketingDetailForTime(scope.getMarketingId());
                if (marketing != null) {
                    marketList.add(marketing);
                }
            }
        }
        return marketList;
    }

    @Override
    public List<Marketing> selectSimpleMarketingByGoodsInfoId(Long goodsInfoId, Long brandId, Long cateId) {

        List<Marketing> marketList = new ArrayList<Marketing>();
        // 查询促销范围
        List<MarketingScope> scopeList = queryMarketingScopes(goodsInfoId, brandId, cateId);

        if (!CollectionUtils.isEmpty(scopeList)) {
            for (MarketingScope scope : scopeList) {
                Marketing marketing = querySimpleMarketingById(scope.getMarketingId());

                if (null != marketing) {
                    marketList.add(marketing);
                }
            }
        }

        return marketList;
    }

    /**
     * 查询简要的促销信息
     * 
     * @param marketingId
     * @return
     */
    public Marketing querySimpleMarketingById(Long marketingId) {
        return marketingMapper.marketingDetail(marketingId);
    }

    /**
     * 查询促销范围
     * 
     * @param goodsInfoId
     * @param brandId
     * @param cateId
     * @return
     */
    private List<MarketingScope> queryMarketingScopes(Long goodsInfoId, Long brandId, Long cateId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GOODSINFOID, goodsInfoId);
        paramMap.put(BRANDIDP, brandId);
        paramMap.put(CATEID, cateId);
        // 查询所在范围的促销
        return marketingScopeMapper.selectMarketScopeByMap(paramMap);
    }

    public MarketingMapper getMarketingMapper() {
        return marketingMapper;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#sellerMarketingCount(java
     * .lang.Long)
     */
    @Override
    public int sellerMarketingCount(Long businessId, String marketingType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("flag", "0");
        paramMap.put(BUSINESSID, businessId);
        paramMap.put("marketingType", marketingType);
        return marketingMapper.marketOrderListCount(paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#sellerMarketingOverdueCount
     * (java.lang.Long, java.lang.String)
     */
    @Override
    public int sellerMarketingOverdueCount(Long businessId, String marketingType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("flag", "0");
        paramMap.put(BUSINESSID, businessId);
        paramMap.put("marketingType", marketingType);
        // 当前时间加七天
        int marketingEnd = 7;
        paramMap.put("marketingEnd", UtilDate.nextNumberDate(marketingEnd));
        return marketingMapper.sellerMarketingOverdueCount(paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.marketing.service.MarketingService#selectAll()
     */
    @Override
    public List<MarketingGroup> selectAll() {
        return groupMapper.selectAll();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#selectByPrimary(com.ningpai
     * .util.PageBean, java.lang.String)
     */
    @Override
    public PageBean selectByPrimary(PageBean pb, String preferentialName) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("preferentialName", preferentialName);
        pb.setRows(groupMapper.selectGourpListCount(map));
        map.put(START, pb.getStartRowNum());
        map.put("endNo", pb.getEndRowNum());
        pb.setList(groupMapper.selectByPrimary(map));
        return pb;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#insertMarketingGroup(com
     * .ningpai.marketing.bean.MarketingGroup)
     */
    @Override
    public void insertMarketingGroup(MarketingGroup group) {
        groupMapper.insertMarketingGroup(group);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.MarketingGroup)
     */
    @Override
    public void updateByPrimaryKeySelective(MarketingGroup group) {
        groupMapper.updateByPrimaryKeySelective(group);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#delGroupByCodexIs(int)
     */
    @Override
    public int delGroupByCodexIs(Long groupId) {
        return groupMapper.delGroupByCodexIs(groupId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#deleteByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public void deleteByPrimaryKey(Long groupId) {
        groupMapper.deleteByPrimaryKey(groupId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#queryOrderMarketingByGoodsId
     * (java.util.List)
     */
    @Override
    public List<Marketing> queryOrderMarketingByGoodsId(List<Long> goodsId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", goodsId);
        if (thirdId != null) {
            map.put("thirdId", thirdId);
        }
        return marketingMapper.queryOrderMarketingByGoodsId(map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#queryByCreatimeMarketing
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public long queryByCreatimeMarketing(Long goodsId, Long groupId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSID, goodsId);
        map.put(GROUPID, groupId);
        Marketing marketing = marketingMapper.queryByCreatimeMarketing(map);
        // 判断该商品是否存在优惠
        if (marketing == null) {
            return 0;
        }
        return marketing.getMarketingId();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#queryByCreatimeMarketings
     * (java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public long queryByCreatimeMarketings(Long goodsId, Long groupId, Long cateId, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSINFOID, goodsId);
        map.put(GROUPID, groupId);
        map.put(CATEID, cateId);
        map.put(BRANDIDP, brandId);
        List<Marketing> marketing = marketingMapper.queryByCreatimemarketings(map);
        // 判断该商品是否存在优惠
        if (marketing == null || marketing.isEmpty()) {
            return 0;
        }
        return marketing.get(0).getMarketingId();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#selectGrouponMarket(java
     * .lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public Marketing selectGrouponMarket(Long goodsId, Long groupId, Long cateId, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSINFOID, goodsId);
        map.put(GROUPID, groupId);
        map.put(CATEID, cateId);
        map.put("brandId", brandId);
        List<Marketing> marketing = marketingMapper.queryByCreatimeMarketingGroupon(map);
        // 判断该商品是否存在优惠
        if (marketing == null || marketing.isEmpty()) {
            return null;
        }
        return marketing.get(0);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#addGrouponParticipation
     * (java.lang.Long)
     */
    @Override
    public int addGrouponParticipation(Long marketId, Long custId) {
        // 查询团购信息
        Groupon g = grouponMapper.selectByMarketId(marketId);
        // 团购进度
        GrouponParticipation grouponParticipation = new GrouponParticipation();
        // 创建时间
        grouponParticipation.setCreateTime(new Date());
        // 客户id
        grouponParticipation.setCustId(custId);
        // 删除标记
        grouponParticipation.setFlag("0");
        // 设置团购编号
        grouponParticipation.setGrouponId(g.getGrouponId());
        // 当前参团人数加1
        grouponMapper.updateCountByMarkertId(marketId);
        // 添加团购进度
        return grouponParticipationMapper.insertSelective(grouponParticipation);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#queryRushTime(com.ningpai
     * .util.PageBean)
     */
    @Override
    public PageBean queryRushTime(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询总数
        pb.setRows(rushTimeMapper.queryRushTimeCount());
        // 查询条件封装
        map.put(START, pb.getStartRowNum());
        map.put(NUMBER, pb.getEndRowNum());
        pb.setList(rushTimeMapper.queryRustTimeList(map));
        return pb;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#addRushTime(com.ningpai
     * .marketing.bean.MarketingRushTime)
     */
    @Override
    public int addRushTime(MarketingRushTime rushTime) {
        rushTime.setCreateTime(new Date());
        rushTime.setDeflag("0");
        return rushTimeMapper.insertSelective(rushTime);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#updateRushTime(com.ningpai
     * .marketing.bean.MarketingRushTime)
     */
    @Override
    public int updateRushTime(MarketingRushTime rushTime) {
        return rushTimeMapper.updateByPrimaryKeySelective(rushTime);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#delRushTime(java.lang.
     * Long)
     */
    @Override
    public int delRushTime(Long tId) {
        return rushTimeMapper.deleteByPrimaryKey(tId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#delRushTimes(java.lang
     * .Long[])
     */
    @Override
    public int delRushTimes(Long[] tId) {
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < tId.length; i++) {
            list.add(tId[i]);
        }
        return rushTimeMapper.delRushTimes(list);
    }

    /*
     * 
     * 
     * @see com.ningpai.marketing.service.MarketingService#queryRushByFlag()
     */
    @Override
    public List<MarketingRushTime> queryRushByFlag() {
        return rushTimeMapper.queryRustTimeListByFlag();
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#selectRushMarket(java.
     * lang.Long, java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public Marketing selectRushMarket(Long goodsId, Long groupId, Long cateId, Long brandId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(GOODSINFOID, goodsId);
        map.put(GROUPID, groupId);
        map.put(CATEID, cateId);
        map.put("brandId", brandId);
        List<Marketing> marketing = marketingMapper.queryByCreatimeMarketingRush(map);
        // 判断该商品是否存在优惠
        if (marketing == null || marketing.isEmpty()) {
            return null;
        }
        return marketing.get(0);
    }

    @Override
    public Marketing selectRushMarket(Long marketId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("marketId", marketId);
        List<Marketing> marketing = marketingMapper.queryByCreatimeMarketingRush(map);

        return marketing.get(0);
    }

    /*
     * 
     * @see com.ningpai.marketing.service.MarketingService#selectMarketCount()
     */
    @Override
    public Map<String, Object> selectMarketCount() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("marketCount", marketingMapper.marketOrderListCount(null));
        map.put("couponCount", couponMapper.selectCouponListCount(null));
        map.put("giftCount", giftMapper.searchGiftListCount(null));
        map.put("giftCatCount", cateMapper.searchGiftCateListCount(null));
        map.put("giftOrderCount", giftOrderMapper.giftOrderCount(null));
        return map;
    }

    @Override
    public RegisterMarketing findRegisterMarketing() {
        return registerMarketingMapper.findRegisterMarketing();
    }

    @Override
    public int updateRegisterCoupon(RegisterMarketing registerMarketing) {
        return registerMarketingMapper.updateRegisterCoupon(registerMarketing);
    }

    @Override
    public Marketing searchMarketByMarketingId(Long marketingId) {
        return marketingMapper.searchMarketByMarketingId(marketingId);
    }

    @Override
    public MarketingCodex searchMarketRuleByMarketingId(Long marketingId) {
        return marketingCodexMapper.searchMarketRuleByMarketingId(marketingId);
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#marketOrderList(com.ningpai
     * .util.PageBean, com.ningpai.marketing.bean.Marketing, java.lang.String)
     */
    @Override
    public PageBean marketList(PageBean pageBean, Marketing marketing, String marketingFlag) {
        marketing.setFlag("0");
        Map<String, Object> paramMap = MapUtil.getParamsMap(marketing);
        paramMap.put("marketingFlag", marketingFlag);
        // 查询总数
        pageBean.setRows(marketingMapper.marketingListCount(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        if (no == 0) {
            no = 1;
        }
        try {
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
                pageBean.setObjectBean(marketing);
            }
            // 查询条件封装
            paramMap.put(START, pageBean.getStartRowNum());
            paramMap.put(NUMBER, pageBean.getEndRowNum());
            // 查询列表页
            pageBean.setList(marketingMapper.marketingList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * @see
     * com.ningpai.marketing.service.MarketingService#newDoUpdateMarketing(com
     * .ningpai.marketing.bean.Marketing, java.lang.String,
     * javax.servlet.http.HttpServletRequest, java.lang.Long[],
     * java.lang.Long[], java.lang.String)
     */
    @Transactional
    @Override
    public int newDoUpdateMarketing(Marketing marketing, String codexType, String status, HttpServletRequest request, Long[] lelvelId, Long[] promotionLogoId) {
        String flage = "0";
        // 查询促销
        Marketing mt = this.marketingMapper.marketingDetailNotTime(marketing.getMarketingId());
        if ((mt.getBusinessId()).equals(marketing.getBusinessId())) {

            // 删除促销关联信息
            this.newDelAllShip(mt.getCodexType(), marketing.getMarketingId());
            // 修改时间
            marketing.setModTime(new Date());
            int modifyFlag = marketingMapper.modifyMarketing(marketing);
            if (modifyFlag == 1) {
                // 创建促销规则级联数据
                MarketingCodex mc = new MarketingCodex();
                mc.setCreateTime(new Date());
                mc.setFlag("0");
                mc.setMarketingId(marketing.getMarketingId());
                mc.setModTime(new Date());
                mc.setCodexId(Long.valueOf(request.getParameter(CODEXID)));
                marketingCodexMapper.insertMarketingCodex(mc);
                if (lelvelId != null && lelvelId.length > 0) {
                    for (int i = 0; i < lelvelId.length; i++) {
                        // new一个促销会员等级对象
                        MarketLelvel lelvel = new MarketLelvel();
                        // 促销ID
                        lelvel.setMarketingId(marketing.getMarketingId());
                        // 等级ID
                        lelvel.setLelvelId(lelvelId[i]);
                        // 删除标记为0 未删除
                        lelvel.setLelvelDelFlag("0");
                        // 执行添加促销等级操作
                        marketLelvelMapper.insertSelective(lelvel);
                    }
                }
                // 添加促销LOGO级联数据
                if (promotionLogoId != null && promotionLogoId.length > 0) {
                    for (int i = 0; i < promotionLogoId.length; i++) {
                        // new 一个促销LOGO的对象
                        MarketLogo logo = new MarketLogo();
                        // 促销ID
                        logo.setMarketingId(marketing.getMarketingId());
                        // 促销LOGOId
                        logo.setPromotionLogoId(promotionLogoId[i]);
                        // 删除标记为0 未删除
                        logo.setDelFlag("0");
                        // 执行添加促成LOGO级联操作
                        marketLogoMapper.insertSelective(logo);
                    }
                }
            }

            String price = request.getParameter(MarketingValue.FULLPRICE);
            BigDecimal fullPrice = null;
            if (price != null && !"".equals(price)) {
                fullPrice = BigDecimal.valueOf(Double.valueOf(request.getParameter(MarketingValue.FULLPRICE)));
            }
            newJudgeCodexType(marketing, codexType, request, flage, marketing.getMarketingId(), fullPrice);

            List<MarketingScope> mslist = new ArrayList<MarketingScope>();
            if ("1".equals(status)) {
                // 分类插入
                String[] cateId;
                // 判断是否是全场促销 0 不是，1是
                if ("0".equals(marketing.getIsAll())) {
                    cateId = request.getParameterValues("cateIdp");
                } else {
                    cateId = request.getParameterValues("thirdcate_ids");
                }
                for (int i = 0; i < cateId.length; i++) {
                    MarketingScope ms = new MarketingScope();
                    ms.setCreateTime(new Date());
                    ms.setFlag(flage);
                    ms.setMarketingId(marketing.getMarketingId());
                    ms.setScopeId(Long.valueOf(cateId[i]));
                    ms.setScopeType("0");
                    mslist.add(ms);
                }
            } else if ("2".equals(status)) {
                // 品牌插入
                String[] brandId;
                // 判断是否是全场促销 0 不是，1是
                if ("0".equals(marketing.getIsAll())) {
                    brandId = request.getParameterValues("brandIdP");
                } else {
                    brandId = request.getParameterValues("brand_allids");
                }

                for (int i = 0; i < brandId.length; i++) {
                    MarketingScope ms = new MarketingScope();
                    ms.setCreateTime(new Date());
                    ms.setFlag(flage);
                    ms.setMarketingId(marketing.getMarketingId());
                    ms.setScopeId(Long.valueOf(brandId[i]));
                    ms.setScopeType("1");
                    mslist.add(ms);
                }
            } else {
                // 商品插入
                String[] goodsIds;
                // 判断是否全场商品促销
                if ("0".equals(marketing.getIsAll())) {
                    goodsIds = request.getParameterValues(GOODSIDP);
                } else {
                    goodsIds = request.getParameterValues(PRODUCT_ALLIDS);
                }
                if (goodsIds != null && goodsIds.length != 0) {
                    for (int i = 0; i < goodsIds.length; i++) {
                        if (goodsIds[i] != null && !"".equals(goodsIds[i])) {
                            GoodsProduct goodsInfo = goodsProductMapper.selectByGoodsInfoId(Long.valueOf(goodsIds[i]));
                            if (goodsInfo != null) {
                                MarketingScope ms = new MarketingScope();
                                ms.setCreateTime(new Date());
                                ms.setFlag(flage);
                                ms.setMarketingId(marketing.getMarketingId());
                                ms.setScopeId(goodsInfo.getGoodsInfoId());
                                ms.setScopeType("2");
                                mslist.add(ms);
                            }
                        }
                    }
                }

            }
            if (!mslist.isEmpty()) {
                marketingScopeMapper.insertMarketingScope(mslist);
            }

            return modifyFlag;
        }
        return 0;
    }

}
