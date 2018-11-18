/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.bean.CustomerFollow;
import com.ningpai.site.customer.mapper.CustomerFollowMapper;
import com.ningpai.site.goods.util.ProductCommentUtilBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.site.customer.mapper.CustomerFollowMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月14日 下午2:29:11
 * @version 0.0.1
 */
@Repository("customerFollowMapperSite")
public class CustomerFollowMapperImpl extends BasicSqlSupport implements
        CustomerFollowMapper {

    /**
     * 根据货品ID获取 该货品的评分
     * @param productId
     * @return
     */
    @Override
    public ProductCommentUtilBean queryCommentCountAndScoreByProductId(Long productId) {
        return this.selectOne("com.ningpai.site.customer.dao.CustomerFollowMapper.queryCommentCountAndScoreByProductId",productId);
    }

    /**
     * 根据主键删除
     * @param map
     *            收藏编号
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .update("com.ningpai.site.customer.dao.CustomerFollowMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入数据 要插入的数据的对象
     * @param record
     * @return
     */
    @Override
    public int insert(CustomerFollow record) {
        return 0;
    }

    /**
     * 按条件插入数据
     * @param record
     *            要插入的数据的对象
     * @return
     */
    @Override
    public int insertSelective(CustomerFollow record) {
        return 0;
    }

    /**
     * 按照主键编号查找
     * @param followId
     *            收藏编号
     * @return
     */
    @Override
    public CustomerFollow selectByPrimaryKey(Long followId) {
        return null;
    }

    @Override
    public CustomerFollow queryByCustIdAndProId(Map<String,Object> map) {
        return this.selectOne("com.ningpai.site.customer.dao.CustomerFollowMapper.queryByCustIdAndProId",
                map);
    }

    /**
     * 按条件修改信息
     * @param record
     *            要修改的对象
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerFollow record) {
        return 0;
    }

    /**
     * 根据主键编号修改信息
     * @param record
     *            要修改的对象
     * @return
     */
    @Override
    public int updateByPrimaryKey(CustomerFollow record) {
        return 0;
    }

    /**
     * 查询按条件查询消费记录
     * @param paramMap
     *            查询条件
     * @return
     */
    @Override
    public List<Object> selectCustFollowByCustId(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerFollowMapper.selectCustFollowByCustId",
                        paramMap);
    }

    /**
     * 商品列表专用 查询当前会员是否
     * @param paramMap
     * @return
     */
    @Override
    public List<String> selectCustFollowByCustIdForList(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.site.customer.dao.CustomerFollowMapper.selectCustFollowByCustIdForList",
                        paramMap);
    }

    /**
     * 查询收藏总数
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public Long selectCustomerFollowCount(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.dao.CustomerFollowMapper.selectCustFollowCount",
                        customerId);
    }

}
