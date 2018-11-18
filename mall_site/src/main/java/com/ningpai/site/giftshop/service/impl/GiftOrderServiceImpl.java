package com.ningpai.site.giftshop.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerPointMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.site.giftshop.bean.Gift;
import com.ningpai.site.giftshop.bean.GiftOrder;
import com.ningpai.site.giftshop.dao.GiftOrderMapper;
import com.ningpai.site.giftshop.dao.GiftShopSiteMapper;
import com.ningpai.site.giftshop.service.GiftOrderService;
import com.ningpai.site.giftshop.vo.GiftOrderVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;

/**
 * 积分商城 service接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("GiftOrderService")
public class GiftOrderServiceImpl implements GiftOrderService {

    private GiftOrderMapper giftOrderMapper;

    private CustomerPointMapper customerPointMapper;

    private CustomerInfoMapper customerInfoMapper;

    private PointLevelServiceMapper pointLevelServiceMapper;

    private GiftShopSiteMapper giftShopSiteMapper;

    /*
     * 
     * @see
     * com.ningpai.site.giftshop.service.GiftOrderService#subOrder(com.ningpai
     * .site.giftshop.bean.GiftOrder)
     */
    @Override
    public int subOrder(GiftOrder giftOrder, Long giftStock) {

        int flag;
        // 主订单号
        String orderOldCode = UtilDate.mathString(new Date());
        Random random = new Random();
        int i= random.nextInt(901)+100;
        giftOrder.setGiftOrderCode(orderOldCode + i);
        giftOrder.setGiftOrderStatus("0");
        giftOrder.setDelFlag("0");
        giftOrder.setExpressPrice(new BigDecimal(0.00));
        giftOrder.setCreateTime(new Date());
        giftOrder.setPayTime(new Date());
        flag = giftOrderMapper.insertSelective(giftOrder);
        if (flag > 0) {
            Gift gift = giftShopSiteMapper.queryDetailByGiftId(Long.valueOf(giftOrder.getTemp1()));
            gift.setGiftStock(giftStock);
            giftShopSiteMapper.updateGIftById(gift);
            this.addIntegralByGIftOrder(giftOrder.getCustomerId(), "10", giftOrder.getOrderIntegral().intValue(), Long.valueOf(giftOrder.getTemp1()));
        }
        return flag;
    }

    /**
     * 积分兑换商品消耗的积分
     * 
     * @param customerId
     *            用户Id{@link java.lang.Long}
     * @param type
     * @param intetegral
     *            消耗的积分
     * @param giftId
     *            礼品Id{@link java.lang.Long}
     * @return int
     */
    public int addIntegralByGIftOrder(Long customerId, String type, int intetegral, Long giftId) {
        // 积分类型不为空 并且boss端积分状态是开启的
        if (type != null) {
            CustomerPoint customerPoint = new CustomerPoint();
            if ("10".equals(type)) {
                customerPoint.setPointDetail("积分兑换礼品");
                customerPoint.setPoint(intetegral);
            }
            customerPoint.setPointType("0");
            customerPoint.setDelFlag("0");
            customerPoint.setCreateTime(new Date());
            customerPoint.setCustomerId(customerId);
            customerPointMapper.insertSelective(customerPoint);
            CustomerInfo info = customerInfoMapper.selectCustInfoById(customerId);
            info.setInfoPointSum(info.getInfoPointSum() - customerPoint.getPoint());
            info.setCustomerId(customerId);

            // 积分增加完成后 判断积分是否满足升级要求 是否在下一等级的区间中
            // 若是在下一个升级区间中则自动升级等级
            // 遗留问题:按照积分升级是否会造成积分减少时,等级降低问题,要是等级不降低,那么怎么样处理积分减少造成的问题??
            for (CustomerPointLevel level : pointLevelServiceMapper.selectAllPointLevel()) {
                String[] points = level.getPointNeed().split("~");
                if (Integer.valueOf(points[0]) <= info.getInfoPointSum() && info.getInfoPointSum() <= Integer.valueOf(points[1])) {
                    info.setPointLevelName(level.getPointLevelName());
                }
            }

            customerInfoMapper.updateInfoByCustId(info);
        }
        return 0;
    }

    /*
     * 
     * @see com.ningpai.site.giftshop.service.GiftOrderService#orderList()
     */
    @Override
    public List<GiftOrderVo> orderList() {

        return giftOrderMapper.orderVoList();
    }

    /*
     * 
     * @see
     * com.ningpai.site.giftshop.service.GiftOrderService#queryGiftOrder(com
     * .ningpai.util.PageBean, java.util.Map)
     */
    @Override
    public PageBean queryGiftOrder(PageBean pb, Map<String, Object> paramMap) {

        int rows = giftOrderMapper.giftOrderCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        pb.setList(giftOrderMapper.giftOrderList(paramMap));
        return pb;
    }

    /*
     * 
     * @see
     * com.ningpai.site.giftshop.service.GiftOrderService#updateOrderVice(com
     * .ningpai.site.giftshop.bean.GiftOrder)
     */
    @Override
    public int updateOrderVice(Long giftOrderId) {
        GiftOrder giftOrder = giftOrderMapper.selectByPrimaryKey(giftOrderId);
        giftOrder.setGiftOrderStatus("2");

        return this.giftOrderMapper.updateByPrimaryKeySelective(giftOrder);
    }

    /*
     * 
     * @see
     * com.ningpai.site.giftshop.service.GiftOrderService#orderDetail(java.lang
     * .Long)
     */
    @Override
    public GiftOrderVo orderDetail(Long orderId) {

        return this.giftOrderMapper.selectByOrderId(orderId);
    }

    @Resource(name = "GiftOrderMapper")
    public void setGiftOrderMapper(GiftOrderMapper giftOrderMapper) {
        this.giftOrderMapper = giftOrderMapper;
    }

    @Resource(name = "customerPointMapper")
    public void setCustomerPointMapper(CustomerPointMapper customerPointMapper) {
        this.customerPointMapper = customerPointMapper;
    }

    @Resource(name = "customerInfoMapper")
    public void setCustomerInfoMapper(CustomerInfoMapper customerInfoMapper) {
        this.customerInfoMapper = customerInfoMapper;
    }

    @Resource(name = "pointLevelServiceMapper")
    public void setPointLevelServiceMapper(PointLevelServiceMapper pointLevelServiceMapper) {
        this.pointLevelServiceMapper = pointLevelServiceMapper;
    }

    @Resource(name = "GiftShopSiteMapper")
    public void setGiftShopSiteMapper(GiftShopSiteMapper giftShopSiteMapper) {
        this.giftShopSiteMapper = giftShopSiteMapper;
    }

}
