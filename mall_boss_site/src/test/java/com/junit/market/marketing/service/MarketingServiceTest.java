/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.junit.market.marketing.service;

import com.alibaba.fastjson.JSON;
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
import com.ningpai.marketing.service.impl.MarketingServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.math.BigDecimal;
import java.util.*;

/**
 * 促销serivce
 * 
 * @author NINGPAI-fengal
 * @since 2015年9月15日下午 19:18:00
 * 
 */
public class MarketingServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     */
    @TestedObject
    private MarketingService marketingService = new MarketingServiceImpl();
    /**
     * 模拟Mock
     */
    @InjectIntoByType
    Mock<GiftOrderMapper> giftOrderMapperMock;
    @InjectIntoByType
    Mock<GiftCateMapper> cateMapperMock;
    @InjectIntoByType
    Mock<MarketingRushTimeMapper> rushTimeMapperMock;
    @InjectIntoByType
    Mock<MarketingGroupMapper> groupMapperMock;
    @InjectIntoByType
    Mock<MarketingScopeMapper> marketingScopeMapperMock;
    @InjectIntoByType
    Mock<GoodsMapper> goodsMapperMock;
    @InjectIntoByType
    Mock<GoodsBrandMapper> goodsBrandMapperMock;
    @InjectIntoByType
    Mock<GoodsCateMapper> goodsCateMapperMock;
    @InjectIntoByType
    Mock<PriceOffMarketingMapper> priceOffMarketingMapperMock;
    @InjectIntoByType
    Mock<PresentationMarketingMapper> presentationMarketingMapperMock;
    @InjectIntoByType
    Mock<GiftMapper> giftMapperMock;
    @InjectIntoByType
    Mock<MarketingMapper> marketingMapperMock;
    @InjectIntoByType
    Mock<CouponMapper> couponMapperMock;
    @InjectIntoByType
    Mock<CouponMarketingMapper> couponMarketingMapperMock;
    @InjectIntoByType
    Mock<DiscountMarketingMapper> discountMarketingMapperMock;
    @InjectIntoByType
    Mock<FullbuyReduceMarketingMapper> fullbuyReduceMarketingMapperMock;
    @InjectIntoByType
    Mock<FullbuyPresentMarketingMapper> fullbuyPresentMarketingMapperMock;
    @InjectIntoByType
    Mock<FullbuyCouponMarketingMapper> fullbuyCouponMarketingMapperMock;
    @InjectIntoByType
    Mock<FullbuyDiscountMarketingMapper> fullbuyDiscountMarketingMapperMock;
    @InjectIntoByType
    Mock<MarketingCodexMapper> marketingCodexMapperMock;
    @InjectIntoByType
    Mock<CodexService> codexServiceMock;
    @InjectIntoByType
    Mock<LimitBuyMarketingMapper> limitBuyMarketingMapperMock;
    /** 插入团购信息 */
    @InjectIntoByType
    Mock<GrouponMapper> grouponMapperMock;
    /**  团购进度 */
    @InjectIntoByType
    Mock<GrouponParticipationMapper> grouponParticipationMapperMock;
    /** 限购 */
    @InjectIntoByType
    Mock<MarketingRushMapper> rushMapperMock;
    /** 满x件打y折 */
    @InjectIntoByType
    Mock<FullbuyNoDiscountMarketingMapper> fullbuyNoDiscountMarketingMapperMock;
    /** 满件数多少钱 */
    @InjectIntoByType
    Mock<FullbuyNoCountMarketingMapper> fullbuyNoCountMarketingMapperMock;
    @InjectIntoByType
    Mock<PreDiscountMarketingMapper> preDiscountMarketingMapperMock;
    /** 货品信息Mapper */
    @InjectIntoByType
    Mock<GoodsProductMapper> goodsProductMapperMock;
    /** 注册营销Mapper */
    @InjectIntoByType
    Mock<RegisterMarketingMapper> registerMarketingMapperMock;
    /** 促销等级Mapper */
    @InjectIntoByType
    Mock<MarketLelvelMapper> marketLelvelMapperMock;
    /** 促销LogoMappper */
    @InjectIntoByType
    Mock<MarketLogoMapper> marketLogoMapperMock;

    @FileContent("marketingList.js")
    private String marketingListJs;
    @FileContent("marketingScopeList.js")
    private String marketingScopeListJs;
    @FileContent("goodsProductList.js")
    private String goodsProductListJs;
    @FileContent("marketingCodexList.js")
    private String marketingCodexListJs;
    @FileContent("marketLelvelList.js")
    private String marketLelvelListJs;
    @FileContent("marketLogoList.js")
    private String marketLogoListJs;
    /**
     * 共享数据
     */
    List<Marketing> marketingList;
    List<MarketingScope> marketingScopeList;
    List<GoodsProduct> goodsProductList;
    List<MarketingCodex> marketingCodexList;
    List<MarketLelvel> marketLelvelList;
    List<MarketLogo> marketLogoList;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        marketingList = JSON.parseArray(marketingListJs,Marketing.class);
        marketingScopeList = JSON.parseArray(marketingScopeListJs,MarketingScope.class);
        goodsProductList = JSON.parseArray(goodsProductListJs,GoodsProduct.class);
        marketingCodexList = JSON.parseArray(marketingCodexListJs,MarketingCodex.class);
        marketLelvelList = JSON.parseArray(marketLelvelListJs,MarketLelvel.class);
        marketLogoList = JSON.parseArray(marketLogoListJs,MarketLogo.class);
    }


    /**
     * 删除
     */
    public void testDelleteMarkting(){
        marketingMapperMock.returns(1).delleteMarkting(1L,0L);
        assertEquals(1,marketingService.delleteMarkting(1L,0L));

    }

    /**
     * 查询促销列表
     */
    public void testMarketOrderList(){

    }

    /**
     * 查询促销详细信息
     */
    public void testMarketingDetailOne(){

    }

    /**
     * 查询促销详细信息
     */
    public void testMarketingDetail(){

    }

    /**
     * 查询促销详细信息（不根据时间）
     */
    public void testMarketingDetailNotTime(){

    }

    /**
     * 查询范围信息
     */
    public void testSelectMarketingScope(){

    }

    /** 查询促销详细信息（根据时间）
     */
    public void testMarketingDetailForTime(){

    }

    /**
     * 插入促销信息
     */
    public void testDoAddMarketingOne(){

    }

    /**
     * 插入促销信息
     */
    public void testDoAddMarketing(){

    }

    /**
     * 新促销插入促销信息
     */
    public void testNewDoAddMarketing(){

    }

    /**
     * 修改促销信息
     */
    public void testDoModifyMarketing(){

    }

    /**
     * 修改促销信息
     */
    public void testDoUpdateMarketing(){

    }

    /**
     * 删除促销信息
     */
    public void testDeleteMarketingById(){

    }

    /**
     * 批量删除促销
     */
    public void testDelAllMarketingByIds(){

    }

    /**
     * 根据货品查询
     */
    public void testSelectMarketingByGoodsInfoId(){

    }

    /**
     * 商家促销数量
     */
    public void testSellerMarketingCount(){

    }

    /**
     * 即将过期的促销
     */
    public void testSellerMarketingOverdueCount(){

    }

    /**
     * 查询所有的促销分组
     */
    public void testSelectAll(){

    }

    /**
     * 按条件查询所有的分组信息
     */
    public void testSelectByPrimary(){

    }

    /**
     * 插入分组信息
     */
    public void testInsertMarketingGroup(){

    }

    /**
     * 根据修改分组信息
     */
    public void testUpdateByPrimaryKeySelective(){

    }

    /**
     * 查询分组是否能被删除
     */
    public void testDelGroupByCodexIs(){

    }

    /**
     * 根据id删除分组
     */
    public void testDeleteByPrimaryKey(){

    }

    /**
     * 查询活动分组marketing
     */
    public void testMarketingDetailByActive(){

    }

    /**
     * 根据商品id查询商品参加的订单促销
     */
    public void testQueryOrderMarketingByGoodsId(){

    }

    /**
     * 查询促销id
     */
    public void testQueryByCreatimeMarketing(){

    }

    /**
     * 查询促销加入的促销id
     */
    public void testQueryByCreatimeMarketings(){

    }

    /**
     * 根据优惠id修改参团人数
     */
    public void testAddGrouponParticipation(){

    }

    /**
     * 抢购场次列表
     */
    public void testQueryRushTime(){

    }

    /**
     * 添加抢购时间
     */
    public void testAddRushTime(){

    }

    /**
     * 修改抢购时间
     */
    public void testUpdateRushTime(){

    }

    /**
     * 删除抢购时间
     */
    public void testDelRushTime(){

    }

    /**
     * 批量删除抢购时间
     */
    public void testDelRushTimes(){

    }

    /**
     * 查询可用的抢购时间
     */
    public void testQueryRushByFlag(){

    }

    /**
     * 查询团购商品的促销信息
     */
    public void testSelectGrouponMarketOne(){

    }

    /**
     * 根据id查询团购促销信息
     */
    public void testSelectGrouponMarket(){

    }

    /**
     * 查询抢购秒杀商品的促销信息
     */
    public void TestSelectRushMarketOne(){

    }

    /**
     * 根据促销Id查询促销信息
     */
    public void testSelectRushMarket(){

    }

    /**
     * 查询商品促销，优惠劵总数量
     */
    public void testSelectMarketCount(){

    }

    /**
     * 查询注册营销信息
     */
    public void testFindRegisterMarketing(){

    }

    /**
     * 更新注册营销信息
     */
    public void testUpdateRegisterCoupon(){

    }

    /**
     * 根据marketingId查询促销信息
     */
    public void testSearchMarketByMarketingId(){

    }

    /**
     * 获取促销信息级联信息
     */
    public void testSearchMarketRuleByMarketingId(){

    }

    /**
     * 获取促销详细信息
     */
    public void testQueryMarketingDetail(){

    }

    /**
     * 查询促销范围信息
     */
    public void testQueryMarketingScope(){
        marketingScopeMapperMock.returns(marketingScopeList).queryMarketingScope(1L);
        assertEquals(1,marketingService.queryMarketingScope(1L).size());

    }

    /**
     * 移除范围商品
     */
    public void testRemoveGoodsByMidAndGid(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId",1L);
        map.put("marketingId",1L);
        marketingScopeMapperMock.returns(1).countGoodsByMidAndGid(map);
        marketingScopeMapperMock.returns(1).removeGoodsByMidAndGid(map);
        assertEquals(1,marketingService.removeGoodsByMidAndGid(1L,1L).intValue());

    }

    /**
     * 移除范围商品
     */
    public void testRemoveMarketByMidAndGid(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("goodsId",1L);
        map.put("marketingId",1L);
        marketingScopeMapperMock.returns(1).countGoodsByMidAndGid(map);
        marketingScopeMapperMock.returns(new Integer(1)).removeGoodsByMidAndGid(map);
        preDiscountMarketingMapperMock.returns(1).countPreGoodsByMidAndGid(map);
        preDiscountMarketingMapperMock.returns(new Integer(1)).removePreGoodsByMidAndGid(map);
        assertEquals(1,marketingService.removeMarketByMidAndGid(1L,1L).intValue());
    }

    /**
     * 分页查询促销列表
     */
    public void testMarketList(){
        Map<String, Object> paramMap = MapUtil.getParamsMap(marketingList.get(0));
        PageBean pageBean =new PageBean();
        paramMap.put("marketingFlag", "0");
        marketingMapperMock.returns(1).marketingListCount(paramMap);
        /** 设置开始行数 */
        paramMap.put("start", pageBean.getStartRowNum());
        /** 设置结束行数 */
        paramMap.put("number", pageBean.getEndRowNum());
        marketingMapperMock.returns(marketingList).marketingList(paramMap);
        assertEquals(1,marketingService.marketList(pageBean,marketingList.get(0),"0").getList().size());

    }

    /**
     * 修改促销信息
     */
    public void testNewDoUpdateMarketing(){
        List<MarketingScope> mslist = new ArrayList<MarketingScope>();
        mslist.add(marketingScopeList.get(0));
        MockHttpServletRequest request = new MockHttpServletRequest("POST","");
        request.setParameter("codexId","1");
        marketingMapperMock.returns(marketingList.get(0)).marketingDetailNotTime(1L);
        marketingMapperMock.returns(1).modifyMarketing(marketingList.get(0));
        marketingCodexMapperMock.returns(1).insertMarketingCodex(marketingCodexList.get(0));
        marketLelvelMapperMock.returns(1).insertSelective(marketLelvelList.get(0));
        marketLogoMapperMock.returns(1).insertSelective(marketLogoList.get(0));
        request.setParameter("offValue","1");
        PriceOffMarketing poff = new PriceOffMarketing();
        poff.setDelFlag("0");
        poff.setOffValue(BigDecimal.valueOf(Double.valueOf(request.getParameter("offValue"))));
        poff.setMarketingId(1L);
        // 插入直降信息表
        priceOffMarketingMapperMock.returns(1).insertPriceOffMarketing(poff);
        goodsProductMapperMock.returns(goodsProductList.get(0)).selectByGoodsInfoId(1L);
        marketingScopeMapperMock.returns(1).insertMarketingScope(mslist);
        assertEquals(1,marketingService.newDoUpdateMarketing(marketingList.get(0),"1","0",request,new Long[]{1L},new Long[]{1L}));

    }

}
