package com.ningpai.site.giftshop.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftCate;
import com.ningpai.site.giftshop.dao.GiftShopSiteMapper;

/**
 * 赠品分类接口实现类
 * @author qiyuanyuan
 *
 */
@Repository("GiftShopSiteMapper")
public class GiftShopSiteMapperImpl extends BasicSqlSupport implements GiftShopSiteMapper{

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#selectAllCate()
     */
    @Override
    public List<GiftCate> selectAllCate() {
         return this.selectList("com.ningpai.web.dao.GiftShopCateMapper.searchGiftCateAllList");
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#selectGiftParentCateList()
     */
    @Override
    public List<GiftCate> selectGiftParentCateList() {
         return this.selectList("com.ningpai.web.dao.GiftShopCateMapper.searchGiftCateList");
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#searchGiftListCount(java.util.Map)
     */
    @Override
    public int searchGiftListCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.web.dao.GiftSiteMapper.searchGiftListCount", paramMap);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#searchGiftList(java.util.Map)
     */
    @Override
    public List<Object> searchGiftList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.web.dao.GiftSiteMapper.searchGiftList", paramMap);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#queryDetailByGiftId(java.lang.Long)
     */
    @Override
    public Gift queryDetailByGiftId(Long giftId) {
        
        return this.selectOne("com.ningpai.web.dao.GiftSiteMapper.selectGiftDetailById", giftId);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#selectByCateId(java.lang.Long)
     */
    @Override
    public GiftCate selectByCateId(Long cateId) {
         
        return this.selectOne("com.ningpai.web.dao.GiftShopCateMapper.searchGiftCateById", cateId);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#updateGIftById(com.ningpai.site.giftshop.bean.Gift)
     */
    @Override
    public int updateGIftById(Gift gift) {
        
        return this.update("com.ningpai.web.dao.GiftSiteMapper.doUpdateGift", gift);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftShopSiteMapper#querysCateIdBypCateId(java.lang.Long)
     */
    @Override
    public Long querysCateIdBypCateId(Long cateId) {
        
        return this.selectOne("com.ningpai.web.dao.GiftShopCateMapper.selectsCateIdBypCateId", cateId);
    }
 
}
