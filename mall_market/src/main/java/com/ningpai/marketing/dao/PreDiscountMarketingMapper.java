package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.PreDiscountMarketing;

/**
 * 折扣促销dao
 * 
 * @author zhoux
 *
 */
public interface PreDiscountMarketingMapper {

    /**
     * 插入折扣信息
     * 
     * @param pdmlist
     * @return
     */
    int insertSelective(List<PreDiscountMarketing> pdmlist);

    /**
     * 查询促销信息
     * 
     * @param param
     * @return
     */
    PreDiscountMarketing selectByMarketId(Map<String, Object> param);

    /**
     * 查询折扣促销是否存在
     * 
     * @return
     */
    Integer queryByGoodsIdAndMarketingId(Map<String, Object> map);

    /**
     * 修改
     * 
     * @param pdmlist
     * @return
     */
    int modifySelective(PreDiscountMarketing pdmlist);

    /**
     * 添加信息
     * 
     * @param pdmlist
     * @return
     */
    int insertSelectiveSingle(PreDiscountMarketing pdmlist);

    /**
     * 查询促销折扣是否存在
     * 
     * @param map
     * @return
     */
    Integer countPreGoodsByMidAndGid(Map<String, Object> map);

    /**
     * 移除促销折扣信息
     * 
     * @param map
     * @return
     */
    Integer removePreGoodsByMidAndGid(Map<String, Object> map);

    /**
     * 根据促销ID查询数量
     * 
     * @param marketingId
     * @return
     */
    List<PreDiscountMarketing> selectCountsByMarketId(Long marketingId);

    /**
     * 根据促销活动id删除关联的折扣信息
     * 
     * @param marketingId
     *            促销活动ID{@link java.lang.Long}
     * 
     * @return int
     * 
     */
    int removePreCountByMid(Long marketingId);

}
