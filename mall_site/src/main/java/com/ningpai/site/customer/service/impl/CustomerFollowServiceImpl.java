/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.site.customer.bean.CustomerFollow;
import com.ningpai.site.customer.mapper.CustomerFollowMapper;
import com.ningpai.site.customer.service.CustomerFollowService;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.service.DefaultAddressService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @see com.ningpai.site.customer.service.CustomerFollowService
 * @since 2014年4月14日 下午2:28:22
 */
@Service("customerFollowServiceSite")
public class CustomerFollowServiceImpl implements CustomerFollowService {

    /**
     * 会员收藏Mapper
     */
    private CustomerFollowMapper customerFollowMapper;

    /**
     * 查询默认地址id
     * */
    @Resource(name = "DefaultAddressService")
    private DefaultAddressService defaultAddressService;

    /**
     * 查询分仓库存
     * */
    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;


    /**
     * 查询收藏记录
     * @param paramMap
     *            查询条件
     * @param pb
     * @return
     */
    @Override
    public PageBean selectCustomerFollow(Map<String, Object> paramMap,
                                         PageBean pb) {
        //查询收藏总数
        Long count = customerFollowMapper
                .selectCustomerFollowCount((Long) paramMap.get("customerId"));
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        //查询按条件查询消费记录
        List<Object> customerFollows = customerFollowMapper.selectCustFollowByCustId(paramMap);
        if (null != customerFollows && !customerFollows.isEmpty()) {
            for (int i = 0; i < customerFollows.size(); i++) {
                CustomerFollow childCustomerFollow = (CustomerFollow) customerFollows.get(i);
                //根据货品ID获取 该货品的评分
                childCustomerFollow.setUtilBean(customerFollowMapper.queryCommentCountAndScoreByProductId(childCustomerFollow.getGoodsId()));


                /**
                 * 下面1,2,3条是在前台点击收藏的商品时使用的 houyichag 2015/9/29
                 *
                 * */
                // 1.查询出后台设置的默认地区
                Long distinctId = this.defaultAddressService.getDefaultIdService();
                // 2.根据默认地区以及货品id去查询改货品的分仓库存
                ProductWare productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(childCustomerFollow.getGoodsId(),distinctId);
                // 3.对customerFollows中的goodBean对象中的库存进行赋值
                childCustomerFollow.getGood().setGoodStock(productWare.getWareStock());

            }
        }
        pb.setList(customerFollows);
        return pb;
    }

    /**
     * 商品列表专用 查询当前会员是否
     * @param paramMap
     * @return
     */
    @Override
    public List<String> selectCustomerFollowForList(Map<String, Object> paramMap) {
        //查询按条件查询消费记录
        return    customerFollowMapper.selectCustFollowByCustIdForList(paramMap);

    }

    /**
     * 取消关注
     * @param followId
     *            关注编号
     * @param customerId
     * @return
     */
    @Override
    public int deleteFollow(Long followId, Long customerId) {
        Map<String, Object> map = new HashMap<>();
        // 封装参数
        map.put("followId", followId);
        map.put("customerId", customerId);
        //根据主键删除
        return customerFollowMapper.deleteByPrimaryKey(map);
    }

    public CustomerFollowMapper getCustomerFollowMapper() {
        return customerFollowMapper;
    }

    @Resource(name = "customerFollowMapperSite")
    public void setCustomerFollowMapper(
            CustomerFollowMapper customerFollowMapper) {
        this.customerFollowMapper = customerFollowMapper;
    }

}
