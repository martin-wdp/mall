package com.ningpai.gift.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.dao.GiftOrderMapper;
import com.ningpai.gift.vo.GiftOrderVo;
import com.ningpai.manager.base.BasicSqlSupport;
/**
 * 积分订单查询DAO实现类
 * @author qiyuanyuan
 *
 */
@Repository("GiftOrderWebMapper")
public class GiftOrderMapperImpl extends BasicSqlSupport implements GiftOrderMapper{

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#giftOrderCount(java.util.Map)
     */
    @Override
    public int giftOrderCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.web.dao.GiftOrderMapper.giftordercount", paramMap);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#giftOrderList(java.util.Map)
     */
    @Override
    public List<Object> giftOrderList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.web.dao.GiftOrderMapper.giftorderlist", paramMap);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#updateGiftOrder(com.ningpai.gift.bean.GiftOrder)
     */
    @Override
    public int updateGiftOrder(GiftOrder giftOrder) {
        
        return this.update("com.ningpai.web.dao.GiftOrderMapper.updateByPrimaryKeySelective", giftOrder);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#selectByOrderId(java.lang.Long)
     */
    @Override
    public GiftOrderVo selectByOrderId(Long giftOrderId) {
        
        return this.selectOne("com.ningpai.web.dao.GiftOrderMapper.giftorderById", giftOrderId);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#selectByOrderCode(java.lang.String)
     */
    @Override
    public GiftOrderVo selectByOrderCode(String giftOrderCode) {
        
        return this.selectOne("com.ningpai.web.dao.GiftOrderMapper.selectByOrderCode", giftOrderCode);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#existOrderCode(java.lang.String)
     */
    @Override
    public Long existOrderCode(String orderCode) {
        
        return this.selectOne("com.ningpai.web.dao.GiftOrderMapper.existByOrderCode", orderCode);
    }

    /*
     * 
     * @see com.ningpai.gift.dao.GiftOrderMapper#updateByPrimaryKeySelective(com.ningpai.gift.bean.GiftOrder)
     */
    @Override
    public int updateByPrimaryKeySelective(GiftOrder giftOrder) {
        
        return this.update("com.ningpai.web.dao.GiftOrderMapper.updateByPrimaryKeySelective", giftOrder);
    }

}
