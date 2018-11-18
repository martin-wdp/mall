package com.ningpai.gift.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.dao.GiftOrderMapper;
import com.ningpai.gift.service.GiftOrderService;
import com.ningpai.gift.vo.GiftOrderVo;
import com.ningpai.util.PageBean;

/**
 * 积分订单查询service接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("GiftOrderWebService")
public class GiftOrderServiceImpl implements GiftOrderService {

    private GiftOrderMapper giftOrderMapper;

    /**
     */
    @Override
    public PageBean queryGiftOrder(GiftOrderVo giftOrder, PageBean pb) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("giftOrderCode", giftOrder.getGiftOrderCode());
        paramMap.put("giftOrderStatus", giftOrder.getGiftOrderStatus());
        paramMap.put("shoppingMobile", giftOrder.getShoppingMobile());
        paramMap.put("shoppingPerson", giftOrder.getShoppingPerson());
        int rows = giftOrderMapper.giftOrderCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(giftOrderMapper.giftOrderList(paramMap));
        return pb;
    }

    /**
     */
    @Override
    public GiftOrderVo orderDetail(Long giftOrderId) {

        return this.giftOrderMapper.selectByOrderId(giftOrderId);
    }

    /**
     *
     */
    @Override
    public int existOrderCode(String giftOrderCode) {
        if (Integer.parseInt(giftOrderCode) != 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     */
    @Override
    public int updateOrderVice(GiftOrder giftOrder) {

        return this.giftOrderMapper.updateGiftOrder(giftOrder);
    }

    /**
     */
    @Override
    public GiftOrderVo queryByOrderCode(String giftOrderCode) {

        return this.giftOrderMapper.selectByOrderCode(giftOrderCode);
    }

    @Resource(name = "GiftOrderWebMapper")
    public void setGiftOrderMapper(GiftOrderMapper giftOrderMapper) {
        this.giftOrderMapper = giftOrderMapper;
    }

}
