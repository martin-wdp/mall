package com.ningpai.site.giftshop.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.giftshop.bean.GiftOrder;
import com.ningpai.site.giftshop.dao.GiftOrderMapper;
import com.ningpai.site.giftshop.vo.GiftOrderVo;

/**
 * 积分商城订单DAO接口实现类
 * @author qiyuanyuan
 *
 */
@Repository("GiftOrderMapper")
public class GiftOrderMapperImpl extends BasicSqlSupport implements GiftOrderMapper{

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long giftOrderId) {
        
        return this.delete("com.ningpai.dao.GiftOrderMapper.deleteByPrimaryKey", giftOrderId);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#insert(com.ningpai.site.giftshop.bean.GiftOrder)
     */
    @Override
    public int insert(GiftOrder giftOrder) {
        
        return this.insert("com.ningpai.dao.GiftOrderMapper.insert", giftOrder);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#insertSelective(com.ningpai.site.giftshop.bean.GiftOrder)
     */
    @Override
    public int insertSelective(GiftOrder giftOrder) {
        
        return this.insert("com.ningpai.dao.GiftOrderMapper.insertSelective", giftOrder);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public GiftOrder selectByPrimaryKey(Long giftOrderId) {
        
        return this.selectOne("com.ningpai.dao.GiftOrderMapper.selectByPrimaryKey", giftOrderId);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#updateByPrimaryKeySelective(com.ningpai.site.giftshop.bean.GiftOrder)
     */
    @Override
    public int updateByPrimaryKeySelective(GiftOrder giftOrder) {
        
        return this.update("com.ningpai.dao.GiftOrderMapper.updateByPrimaryKeySelective", giftOrder);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#updateByPrimaryKey(com.ningpai.site.giftshop.bean.GiftOrder)
     */
    @Override
    public int updateByPrimaryKey(GiftOrder giftOrder) {
        
        return this.update("com.ningpai.dao.GiftOrderMapper.updateByPrimaryKey", giftOrder);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#orderVoList()
     */
    @Override
    public List<GiftOrderVo> orderVoList() {
        
        return this.selectList("com.ningpai.dao.GiftOrderMapper.rankgiftorder");
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#giftOrderCount(java.util.Map)
     */
    @Override
    public int giftOrderCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.dao.GiftOrderMapper.giftordercount", paramMap);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#giftOrderList(java.util.Map)
     */
    @Override
    public List<Object> giftOrderList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.dao.GiftOrderMapper.giftorderlist", paramMap);
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.dao.GiftOrderMapper#selectByOrderId(java.lang.Long)
     */
    @Override
    public GiftOrderVo selectByOrderId(Long orderId) {
        
        return this.selectOne("com.ningpai.dao.GiftOrderMapper.giftorderById", orderId);
    }

}
