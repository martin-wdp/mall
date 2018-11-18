package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.EsGoodsCategory;
import com.ningpai.goods.bean.EsGoodsInfo;
import com.ningpai.goods.bean.EsThirdCate;

/**
 * 商品索引Dao接口
 * 
 * @author ggn
 *
 */
public interface GoodsElasticMapper {

    /**
     * 查询所有商品信息
     * 
     * @return List
     */
    List<EsGoodsInfo> selectGoodsElasticList(Long goodsId);
    /**
     * 查询所有商品信息
     *
     * @return List
     */
    List<EsGoodsInfo> selectGoodsInfoElasticList(Long goodsInfoId);
    /**
     * 根据参数查询商品，并批量插入索引库
     * 2016-01-21 Wuyanbo添加
     *
     * @return List
     */
    List<EsGoodsInfo> selectGoodsElasticListByParams(Map params);

    /**
     * 查询所有商品信息总数
     *
     * @return Integer
     */
    Integer selectGoodsElasticListCount();

    /**
     * 分页查询
     * @param start
     * @param end
     * @return List
     */
    List<EsGoodsInfo> selectGoodsElasticListByPage(int start,int end);

    /**
     * 查询分类
     * @param catId
     * @return EsGoodsCategory
     */
    EsGoodsCategory selectGoodsCateList(Long catId);

    /**
     * 查询第三方分类catId
     * @param catId
     * @return EsThirdCate
     */
    EsThirdCate selectGoodsThirdCateList(Long catId);
}
