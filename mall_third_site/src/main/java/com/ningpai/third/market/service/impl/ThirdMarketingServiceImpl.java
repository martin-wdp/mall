package com.ningpai.third.market.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.ningpai.goods.dao.GoodsBrandMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.marketing.bean.MarketingScope;
import com.ningpai.marketing.dao.MarketingScopeMapper;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.market.service.ThirdMarketingService;

/**
 * 新第三方促销范围service接口实现类
 *
 * @author qiyuanyuan
 */
@Service("ThirdMarketingService")
public class ThirdMarketingServiceImpl implements ThirdMarketingService {

    // 营销范围接口
    @Resource(name = "MarketingScopeMapper")
    private MarketingScopeMapper marketingScopeMapper;

    // 第三方分类接口
    @Resource(name = "ThirdCateMapper")
    private ThirdCateMapper cateMapper;

    // 商品品牌接口
    @Resource(name = "GoodsBrandMapper")
    private GoodsBrandMapper goodsBrandMapper;

    // 商品接口
    @Resource(name = "GoodsMapper")
    private GoodsMapper goodsMapper;

    /*
     * 
     * @see com.ningpai.third.market.service.ThirdMarketingService#
     * selectThirdMarketingScope(java.lang.Long, java.lang.String,
     * java.lang.Long)
     */
    @Override
    public Object selectThirdMarketingScope(Long marketingId, String type, Long thirdId) {
        // 装载查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 营销ID
        paramMap.put("marketingId", marketingId);
        // 营销类型
        paramMap.put("scopeType", type);
        // 批量查询接口范围
        List<MarketingScope> scopelist = marketingScopeMapper.selectMarketingScope(paramMap);
        List<Object> list = null;
        List<Long> idList = new ArrayList<Long>();
        if (scopelist != null && !scopelist.isEmpty()) {
            for (int i = 0; i < scopelist.size(); i++) {
                idList.add(scopelist.get(i).getScopeId());
            }
        }
        // 分类
        if ("0".equals(type) && !idList.isEmpty()) {
                // 根据list查询所有的商品分类
                list = cateMapper.selectProductCateList(idList);
        }

        // 品牌
        if ("1".equals(type) && !idList.isEmpty()) {
                // 根据List查询多个商品品牌
                list = goodsBrandMapper.selectProductBrandList(idList);
        }

        // SKU
        if ("2".equals(type) && !idList.isEmpty()) {
                // 根据list 查询多个商品SKU
                list = goodsMapper.selectProductSkuList(idList);
        }
        return list;
    }
}
