/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.gift.bean.Gift;
import com.ningpai.gift.dao.GiftMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 赠品接口实现类
 * 
 * @author ggn
 * 
 */
@Repository("GiftMapper")
public class GiftMapperImpl extends BasicSqlSupport implements GiftMapper {

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#searchGiftListCount(java.util.Map)
     */
    @Override
    public int searchGiftListCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.web.dao.GiftMapper.searchGiftListCount", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#searchGiftList(java.util.Map)
     */
    @Override
    public List<Object> searchGiftList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.web.dao.GiftMapper.searchGiftList", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#doAddgift(com.ningpai.gift.bean.Gift)
     */
    @Override
    public int doAddGift(Gift gift) {
        return this.insert("com.ningpai.web.dao.GiftMapper.doAddGift", gift);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        return this.selectOne("com.ningpai.web.dao.GiftMapper.selectLastId");
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#selectGiftDetailById(java.lang.Long)
     */
    @Override
    public Gift selectGiftDetailById(Long giftId) {
        return this.selectOne("com.ningpai.web.dao.GiftMapper.selectGiftDetailById", giftId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#doUpdateGift(com.ningpai.gift.bean.Gift)
     */
    @Override
    public int doUpdateGift(Gift gift) {
        return this.update("com.ningpai.web.dao.GiftMapper.doUpdateGift", gift);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#delGift(java.lang.Long)
     */
    @Override
    public int delGift(Long giftId) {
        return this.update("com.ningpai.web.dao.GiftMapper.delGift", giftId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftMapper#delAllGift(java.util.List)
     */
    @Override
    public int delAllGift(List<Long> list) {
        return this.update("com.ningpai.web.dao.GiftMapper.delAllGift", list);
    }

    @Override
    public List<Gift> selectGiftByListId(List<Long> list) {
        return this.selectList("com.ningpai.web.dao.GiftMapper.selectGiftByListId", list);
    }

}
