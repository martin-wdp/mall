/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.gift.bean.GiftCate;
import com.ningpai.gift.dao.GiftCateMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 赠品分类接口实现类
 * 
 * @author ggn
 * 
 */
@Repository("GiftCateMapper")
public class GiftCateMapperImpl extends BasicSqlSupport implements GiftCateMapper {

    @Override
    public List<GiftCate> searchGiftCateList(Long giftCateId) {
        return this.selectList("com.ningpai.web.dao.GiftCateMapper.searchGiftCateListnew",giftCateId);
    }

    /*
         *
         *
         * @see com.ningpai.gift.dao.GiftCateMapper#searchGiftCateListCount(java.util .Map)
         */
    @Override
    public int searchGiftCateListCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.web.dao.GiftCateMapper.searchGiftCateListCount", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#searchGiftCateList(java.util.Map)
     */
    @Override
    public List<GiftCate> searchGiftParentCateList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.web.dao.GiftCateMapper.searchGiftCateList", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#searchGiftCateById(java.lang.Long)
     */
    @Override
    public GiftCate searchGiftCateById(Long giftCateId) {
        return this.selectOne("com.ningpai.web.dao.GiftCateMapper.searchGiftCateById", giftCateId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#addGiftCate(com.ningpai.gift.bean .GiftCate)
     */
    @Override
    public int addGiftCate(GiftCate giftCate) {
        return this.insert("com.ningpai.web.dao.GiftCateMapper.addGiftCate", giftCate);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#updateGiftCate(com.ningpai.gift.bean .GiftCate)
     */
    @Override
    public int updateGiftCate(GiftCate giftCate) {
        return this.update("com.ningpai.web.dao.GiftCateMapper.updateGiftCate", giftCate);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#delGiftCate(java.lang.Long)
     */
    @Override
    public int delGiftCate(Long giftCateId) {
        return this.update("com.ningpai.web.dao.GiftCateMapper.delGiftCate", giftCateId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#delAllGiftCate(java.util.List)
     */
    @Override
    public int delAllGiftCate(List<Long> list) {
        return this.update("com.ningpai.web.dao.GiftCateMapper.delAllGiftCate", list);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#selectGiftListUseSelect()
     */
    @Override
    public List<GiftCate> selectGiftListUseSelect() {
        return this.selectList("com.ningpai.web.dao.GiftCateMapper.selectGiftListUseSelect");
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#searchGiftCateList()
     */
    @Override
    public List<GiftCate> searchGiftCateList() {
        return this.selectList("com.ningpai.web.dao.GiftCateMapper.searchGiftCateAllList");
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftCateMapper#querySonCateByParentId(java.lang.Long)
     */
    @Override
    public int querySonCateByParentId(Long cateId) {
        
        return this.selectOne("com.ningpai.web.dao.GiftCateMapper.querygiftCateByCateId", cateId);
    }

}
