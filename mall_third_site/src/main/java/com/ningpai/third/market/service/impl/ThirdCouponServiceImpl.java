package com.ningpai.third.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.dao.CouponRangeMapper;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.market.service.ThirdCouponService;

/**
 * 第三方优惠券service接口实现类
 *
 * @author qiyuanyuan
 * @version 2.0
 * @since 20150803
 */
@Service("ThirdCouponService")
public class ThirdCouponServiceImpl extends BasicSqlSupport implements ThirdCouponService {

    // 优惠券范围接口
    @Resource(name = "CouponRangeMapper")
    private CouponRangeMapper couponRangeMapper;

    // >第三方分类<
    @Resource(name = "ThirdCateMapper")
    private ThirdCateMapper cateMapper;

    // 商品品牌Dao
    @Resource(name = "GoodsBrandMapper")
    private GoodsBrandMapper goodsBrandMapper;

    // 商品DAO
    @Resource(name = "GoodsMapper")
    private GoodsMapper goodsMapper;

    /*
     * 
     * @see
     * com.ningpai.third.market.service.ThirdCouponService#selectThirdMarketingScope
     * (java.lang.Long, java.lang.String)
     */
    @Override
    public List<Object> selectThirdMarketingScope(Long couponId, String type) {
        // 装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 优惠劵ID
        paramMap.put("couponId", couponId);
        // 优惠劵类型
        paramMap.put("couponRangeType", type);
        // 查询优惠券范围
        List<CouponRange> scopelist = couponRangeMapper.selectCouponRange(paramMap);
        List<Object> list = null;
        List<Long> idList = new ArrayList<Long>();
        if (scopelist != null && !scopelist.isEmpty()) {
            for (int i = 0; i < scopelist.size(); i++) {
                idList.add(scopelist.get(i).getCouponRangeFkId());
            }
        }

        // 分类
        if ("0".equals(type) && !idList.isEmpty()) {
                list = cateMapper.selectProductCateList(idList);
        }
        // 品牌
        if ("1".equals(type) && !idList.isEmpty()) {
                list = goodsBrandMapper.selectProductBrandList(idList);
        }
        // SKU
        if ("2".equals(type) && !idList.isEmpty()) {
                list = goodsMapper.selectProductSkuList(idList);
        }
        return list;

    }
}
