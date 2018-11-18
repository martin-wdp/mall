/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.gift.bean.GiftPic;
import com.ningpai.gift.dao.GiftPicMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 赠品图片接口实现类
 * 
 * @author ggn
 * 
 */
@Repository("GiftPicMapper")
public class GiftPicMapperImpl extends BasicSqlSupport implements GiftPicMapper {

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#createGiftPic(java.util.List)
     */
    @Override
    public int createGiftPic(List<GiftPic> list) {
        return this.insert("com.ningpai.web.dao.GiftPicMapper.createGiftPic", list);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#selectGiftPicByGiftId(java.lang.Long)
     */
    @Override
    public List<GiftPic> selectGiftPicByGiftId(Long giftId) {
        return this.selectList("com.ningpai.web.dao.GiftPicMapper.selectGiftPicByGiftId", giftId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#deleteGiftPicByGiftId(java.lang.Long)
     */
    @Override
    public int deleteGiftPicByGiftId(Long giftId) {
        return this.update("com.ningpai.web.dao.GiftPicMapper.deleteGiftPicByGiftId", giftId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#deleteAllGiftPicByGiftId(java.util .List)
     */
    @Override
    public int deleteAllGiftPicByGiftId(List<Long> list) {
        return this.update("com.ningpai.web.dao.GiftPicMapper.deleteAllGiftPicByGiftId", list);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#delGiftPicByPicId(java.lang.Long)
     */
    @Override
    public int delGiftPicByPicId(Long picId) {
        return this.update("com.ningpai.web.dao.GiftPicMapper.delGiftPicByPicId", picId);
    }

    /*
     * 
     * 
     * @see com.ningpai.gift.dao.GiftPicMapper#savePic(com.ningpai.gift.bean.GiftPic)
     */
    @Override
    public Long savePic(GiftPic image) {
        Long picId = null;
        int s = this.insert("com.ningpai.web.dao.GiftPicMapper.savePic", image);
        if (s == 1) {
            picId = this.selectOne("com.ningpai.web.dao.GiftPicMapper.selectLastId");
        }
        return picId;
    }

}
