package com.ningpai.goods.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.EsGoodsCategory;
import com.ningpai.goods.bean.EsThirdCate;
import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.EsGoodsInfo;
import com.ningpai.goods.dao.GoodsElasticMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品索引查询接口实现类
 * 
 * @author ggn
 *
 */
@Repository("GoodsElasticMapper")
public class GoodsElasticMapperImpl extends BasicSqlSupport implements
        GoodsElasticMapper {

    /**
     * 查询所有商品信息
     */
    @Override
    public List<EsGoodsInfo> selectGoodsElasticList(Long goodsId) {
        // 查询商品信息
        return this
                .selectList("com.qianmi.web.dao.EsGoodsInfoMapper.selectGoodsElasticList",goodsId);
    }
    /**
     * 查询所有商品信息
     *
     * @return List
     */
    public List<EsGoodsInfo> selectGoodsInfoElasticList(Long goodsInfoId){
        // 查询商品信息
        return this
                .selectList("com.qianmi.web.dao.EsGoodsInfoMapper.selectGoodsInfoElasticList",goodsInfoId);
    }
    /**
     * 根据参数查询商品，并批量插入索引库
     * 2016-01-21 Wuyanbo添加
     */
    @Override
    public List<EsGoodsInfo> selectGoodsElasticListByParams(Map params) {
        // 查询商品信息
        return this
                .selectList("com.qianmi.web.dao.EsGoodsInfoMapper.selectGoodsElasticListByParams",params);
    }

    /**
     * 查询总数
     * @return
     */
    @Override
    public Integer selectGoodsElasticListCount() {
        return this
                .selectOne("com.qianmi.web.dao.EsGoodsInfoMapper.selectGoodsElasticListCount");
    }

    /**
     * 分页查询
     * @param start
     * @param end
     * @return List
     */
    @Override
    public List<EsGoodsInfo> selectGoodsElasticListByPage(int start, int end) {
        // 查询商品信息
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("start",start);
        paramMap.put("end",end);
        return this
                .selectList("com.qianmi.web.dao.EsGoodsInfoMapper.selectGoodsElasticListByPage",paramMap);
    }

    /**
     * 查询分类
     * @param catId
     * @return
     */
    @Override
    public EsGoodsCategory selectGoodsCateList(Long catId) {
        return this
                .selectOne("com.qianmi.web.dao.EsGoodsCategoryMapper.selectGoodsCateListById", catId);
    }

    /**
     * 查询第三方分类
     * @param catId
     * @return EsThirdCate
     */
    @Override
    public EsThirdCate selectGoodsThirdCateList(Long catId) {

        return this
                .selectOne("com.qianmi.web.dao.EsThirdCateMapper.selectGoodsThirdCateListById", catId);
    }

}
