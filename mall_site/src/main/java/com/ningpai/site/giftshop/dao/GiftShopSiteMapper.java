package com.ningpai.site.giftshop.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftCate;


/**
 * 赠品分类DAO接口
 * @author qiyuanyuan
 *
 */
public interface GiftShopSiteMapper {

    /**
     * 查询所有赠品分类
     * @return
     */
    List<GiftCate> selectAllCate();
    
    /**
     * 查询赠品分类父分类
     * @return
     */
    List<GiftCate> selectGiftParentCateList();
    
    /**
     * 查询赠品列表数目
     * @param paramMap {@link java.util.Map}
     * @return int
     */
    int searchGiftListCount(Map<String, Object> paramMap);
    
    
    /**
     * 查询赠品列表
     * @param paramMap {@link java.util.Map}
     * @return List
     */
    List<Object> searchGiftList(Map<String, Object> paramMap);
    
    /**
     * 根据赠品Id查询赠品
     * @param giftId
     * @return
     */
    Gift queryDetailByGiftId(Long giftId);
    
    /**
     * 根据分类Id查询分类
     * @param cateId 分类ID{@link java.lang.Long}
     * @return GiftCate对象
     */
    GiftCate selectByCateId(Long cateId);
    
    /**
     * 修改赠品信息
     * @param gift
     * @return
     */
    int updateGIftById(Gift gift);
    
    /**
     * 根据父分类ID查询第一个子分类
     * 
     * @param cateId
     *            分类ID {@link Long}
     * @return 查询到的分类ID {@link Long}
     */
    Long querysCateIdBypCateId(Long cateId);
}
