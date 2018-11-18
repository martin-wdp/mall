/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.marketing.bean.*;
import com.ningpai.util.PageBean;

/**
 * 促销serivce
 * 
 * @author NINGPAI-LIH
 * @since 2014年3月21日下午 17:18:00
 * 
 */
public interface MarketingService {
    /**
     *
     * @param marketingId
     * @return
     */
    int delleteMarkting(Long marketingId, Long businessId);

    /**
     * 查询促销列表
     * 
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @return PageBean
     */
    PageBean marketOrderList(PageBean pageBean, Marketing marketing,
                             String marketingBeginTime, String marketingEndTime);

    /**
     * 查询促销详细信息
     * 
     * @param marketingId
     *            {@link Long}
     * @return Marketing
     */
    Marketing marketingDetail(Long marketingId, Long goodsInfoId);

    /**
     * 查询促销详细信息
     * 
     * @param marketingId
     *            {@link Long}
     * @return Marketing
     */
    Marketing marketingDetail(Long marketingId);

    /**
     * 查询促销详细信息（不根据时间）
     * 
     * @param marketingId
     * @return
     */
    Marketing marketingDetailNotTime(Long marketingId);

    /**
     * 查询范围信息
     * 
     * @param marketingId
     *            {@link Long}
     * @param type
     *            {@link String}
     * @return Object
     */
    Object selectMarketingScope(Long marketingId, String type);

    /** 查询促销详细信息（根据时间）
     * @param marketingId
     * @return Marketing
     */
    Marketing marketingDetailForTime(Long marketingId);

    /**
     * 插入促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @param codexType
     *            {@link String}
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @return int
     */
    int doAddMarketing(Marketing marketing, String codexType,
                       HttpServletRequest request);

    /**
     * 插入促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @param codexType
     *            {@link String}
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @return int
     */
    int doAddMarketing(Marketing marketing, String codexType,
                       HttpServletRequest request, String status);

    /**
     * 新促销插入促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @param codexType
     *            {@link String}
     * @param lelvelId
     *            {@link Long}
     * @param promotionLogoId
     *            {@link Long}
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @return int
     */
    int newDoAddMarketing(Marketing marketing, String codexType,
                          HttpServletRequest request, String status, Long[] lelvelId,
                          Long[] promotionLogoId);

    /**
     * 修改促销信息
     * 
     * @param marketing
     * @param codexType
     * @param request
     * @param status
     * @return
     */
    int doModifyMarketing(Marketing marketing, String codexType,
                          HttpServletRequest request, String status);

    /**
     * 修改促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @param codexType
     *            {@link String}
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @return int
     */
    int doUpdateMarketing(Marketing marketing, String codexType,
                          HttpServletRequest request);

    /**
     * 删除促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @return int
     */
    int deleteMarketingById(Marketing marketing);

    /**
     * 批量删除促销
     * 
     * @param marketingIds
     *            {@link String}
     * @return int
     */
    int delAllMarketingByIds(Long[] marketingIds, Long businessId);

    /**
     * 根据货品查询
     * 
     * @param goodsInfoId
     *            商品id
     * @param brandId
     *            品牌id
     * @param cateId
     *            分类id
     * @return List
     */
    List<Marketing> selectMarketingByGoodsInfoId(Long goodsInfoId,
                                                 Long brandId, Long cateId);

    /**
     * 查询简易的促销活动
     * @param goodsInfoId
     * @param brandId
     * @param cateId
     * @return
     */
    List<Marketing> selectSimpleMarketingByGoodsInfoId(Long goodsInfoId,
                                                       Long brandId, Long cateId);
    /**
     * 商家促销数量
     * 
     * @param businessId
     *            商家ID
     * @param marketingType
     *            0商品促销 1订单促销
     * @return int
     */
    int sellerMarketingCount(Long businessId, String marketingType);

    /**
     * 即将过期的促销
     * 
     * @param businessId
     *            商家ID
     * @param marketingType
     *            0商品促销 1订单促销
     * @return int
     */
    int sellerMarketingOverdueCount(Long businessId, String marketingType);

    /**
     * 查询所有的促销分组
     * 
     * @return
     * @author NINGPAI-LIH
     */
    List<MarketingGroup> selectAll();

    /**
     * 按条件查询所有的分组信息
     * 
     * @return pb
     * @author NINGPAI-LIH
     */
    PageBean selectByPrimary(PageBean pageBean, String preferentialName);

    /**
     * 插入分组信息
     * 
     * @param group
     *            要插入的分组信息
     * @author NINGPAI-LIH
     */
    void insertMarketingGroup(MarketingGroup group);

    /**
     * 根据修改分组信息
     * 
     * @param group
     */
    void updateByPrimaryKeySelective(MarketingGroup group);

    /**
     * 查询分组是否能被删除
     * 
     * @param groupId
     *            要删除id
     * @return
     */
    int delGroupByCodexIs(Long groupId);

    /**
     * 根据id删除分组
     * 
     * @param groupId
     */
    void deleteByPrimaryKey(Long groupId);

    /**
     * 查询活动分组marketing
     * 
     * @param marketingId
     *            活动分组下的marketing
     * @return marketing
     * @author NINGPAI-LIH
     */
    Marketing marketingDetailByActive(Marketing marketing, Long marketingId,
                                      boolean isT);

    /**
     * 根据商品id查询商品参加的订单促销
     * 
     * @param goodsId
     *            商品id
     * @return 订单促销
     */
    List<Marketing> queryOrderMarketingByGoodsId(List<Long> goodsId,
                                                 Long thirdId);

    /**
     * 查询促销id
     * 
     * @param goodsId
     *            商品主键
     * @param groupId
     *            分组主键
     * @return 促销id
     * @author NINGPAI-LIH
     */
    long queryByCreatimeMarketing(Long goodsId, Long groupId);

    /**
     * 查询促销加入的促销id
     * 
     * @param goodsId
     *            商品主键
     * @param groupId
     *            分组主键
     * @param cateId
     *            分组id
     * @param brandId
     *            品牌id
     * @return 促销id
     * @author NINGPAI-LIH
     */
    long queryByCreatimeMarketings(Long goodsId, Long groupId, Long cateId,
                                   Long brandId);

    /**
     * 根据优惠id修改参团人数
     * 
     * @param marketId
     *            优惠id
     * @param custId
     *            客户id
     * @return
     */
    int addGrouponParticipation(Long marketId, Long custId);

    /**
     * 抢购场次列表
     * 
     * @param pb
     *            分页参数
     * @return
     */
    PageBean queryRushTime(PageBean pb);

    /**
     * 添加抢购时间
     * 
     * @param rushTime
     *            抢购时间参数
     * @return
     */
    int addRushTime(MarketingRushTime rushTime);

    /**
     * 修改抢购时间
     * 
     * @param rushTime
     *            修改参数
     * @return
     */
    int updateRushTime(MarketingRushTime rushTime);

    /**
     * 删除抢购时间
     * 
     * @param tId
     * @return
     */
    int delRushTime(Long tId);

    /**
     * 批量删除抢购时间
     * 
     * @param tId
     * @return
     */
    int delRushTimes(Long[] tId);

    /**
     * 查询可用的抢购时间
     * 
     * @return
     */
    List<MarketingRushTime> queryRushByFlag();

    /**
     * 查询团购商品的促销信息
     * 
     * @param goodsId
     *            商品id
     * @param groupId
     *            分组id
     * @param cateId
     *            分类id
     * @param brandId
     *            品牌id
     * @return 促销信息
     */
    Marketing selectGrouponMarket(Long goodsId, Long groupId, Long cateId,
                                  Long brandId);

    /**
     * 根据id查询团购促销信息
     * 
     * @param marketingId
     * @return
     */

    Marketing selectGrouponMarket(Long marketingId);

    /**
     * 查询抢购秒杀商品的促销信息
     * 
     * @param goodsId
     *            商品id
     * @param groupId
     *            分组id
     * @param cateId
     *            分类id
     * @param brandId
     *            品牌id
     * @return 促销信息
     */
    Marketing selectRushMarket(Long goodsId, Long groupId, Long cateId,
                               Long brandId);

    /**
     * 根据促销Id查询促销信息
     * 
     * @param marketId
     *            促销编号
     * @return Marketing
     */
    Marketing selectRushMarket(Long marketId);

    /**
     * 查询商品促销，优惠劵总数量
     * 
     * @return
     */
    Map<String, Object> selectMarketCount();

    /**
     * 查询注册营销信息
     * 
     * @return
     */
    RegisterMarketing findRegisterMarketing();

    /**
     * 更新注册营销信息
     * 
     * @param registerMarketing
     * @return
     */
    int updateRegisterCoupon(RegisterMarketing registerMarketing);

    /**
     * 根据marketingId查询促销信息
     * 
     * @param marketingId
     * @return
     */
    Marketing searchMarketByMarketingId(Long marketingId);

    /**
     * 获取促销信息级联信息
     * 
     * @param marketingId
     * @return
     */
    MarketingCodex searchMarketRuleByMarketingId(Long marketingId);

    /**
     * 获取促销详细信息
     * 
     * @param marketingId
     * @param codexId
     * @return
     */
    Marketing queryMarketingDetail(Long marketingId, Long codexId);

    /**
     * 查询促销范围信息
     * 
     * @param marketingId
     * @return
     */
    List<MarketingScope> queryMarketingScope(Long marketingId);

    /**
     * 移除范围商品
     * 
     * @param goodsId
     * @param marketingId
     * @return
     */
    Integer removeGoodsByMidAndGid(Long goodsId, Long marketingId);

    /**
     * 移除范围商品
     * 
     * @param goodsId
     * @param marketingId
     * @return
     */
    Integer removeMarketByMidAndGid(Long goodsId, Long marketingId);

    /**
     * 分页查询促销列表
     * 
     * @param pageBean
     *            分页{@link com.ningpai.util.PageBean}
     * @param marketing
     *            促销对象{@link com.ningpai.marketing.bean.Marketing}
     * @param marketingFlag
     *            促销标记{@link String}
     * @return pageBean
     */
    PageBean marketList(PageBean pageBean, Marketing marketing,
                        String marketingFlag);

    /**
     * 修改促销信息
     * 
     * @param marketing
     *            {@link com.ningpai.marketing.bean.Marketing}
     * @param codexType
     *            {@link String}
     * @param request
     *            {@link javax.servlet.http.HttpServletRequest}
     * @return int
     */
    int newDoUpdateMarketing(Marketing marketing, String codexType,
                             String status, HttpServletRequest request, Long[] lelvelId,
                             Long[] promotionLogoId);
    
    
    /**
     * 查询简易的促销信息
     * @param marketingId
     * @return
     */
    Marketing querySimpleMarketingById(Long marketingId);

}
