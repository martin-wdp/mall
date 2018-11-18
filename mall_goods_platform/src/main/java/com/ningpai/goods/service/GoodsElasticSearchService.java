package com.ningpai.goods.service;

import com.ningpai.goods.bean.EsGoodsInfo;
import com.ningpai.goods.util.CommonConditions;
import com.ningpai.goods.util.SearchPageBean;

import java.util.List;
import java.util.Map;

/**
 * 商品索引Service
 * 
 * @author ggn
 *
 */
public interface GoodsElasticSearchService {

    /**
     * 查询所有商品信息简历索引
     * 
     * @return int
     */
    int createGoodsIndexToEs();

    /**
     * 查询所有商品信息简历索引
     *
     * @return int
     */
    int createGoodsIndexToEs1();

    /**
     * 插入单个索引
     * 
     * @param goodsId
     *            商品ID
     * @return int
     */
    int insertOneGoodsIndexToEs(Long goodsId);

    /**
     * 根据参数查询商品，并批量插入索引库
     * 2016-01-21 Wuyanbo添加
     *
     * @param goodsId
     *            商品ID
     * @return int
     */
    int insertBatchGoodsIndexToEs(Map params);

    /**
     * 修改 索 引
     * 
     * @param goodsId
     * @return int
     */
    int updateOneGoodsIndexToEs(Long goodsId);

    /**
     * 批量删除
     * 
     * @param list
     * @return int
     */
    int batchDeleteGoodsIndexToEs(List<Long> list);

    /**
     * 批量删除
     * 
     * @param goodsInfoId
     * @return int
     */
    int deleteGoodsIndexToEs(Long goodsInfoId);

    /**
     * 根据 关键词\品牌名称\分类\扩展参数\排序 进行商品查询
     * 
     * @param pageBean
     *            分页数据包装工具类
     * @param wareIds
     *            仓库ID列表
     * @param indices
     *            索引
     * @param types
     *            类型
     * @param keyWords
     *            输入的关键词
     * @param brands
     *            品牌名称
     * @param cats
     *            分类名称
     * @param params
     *            扩展参数
     * @param sort
     *            排序字段
     * @param priceMax
     *            价格过滤的上限
     * @param priceMin
     *            价格过滤的最小值
     * @param thirdId
     *            第三方ID
     * @param thirdCats
     *              第三方分类ID
     * @param showStock
     *              只显示有货
     * @param showMobile 手机端显示
     * @param isThird 是否为第三方商品 0:否,1:是
     * @return 查询结果数据 {@link Map} {pageBean:{@link SearchPageBean},params:}
     */
    Map<String, Object> searchGoods(SearchPageBean<EsGoodsInfo> pageBean,
            Long[] wareIds, String[] indices, String[] types, String keyWords,
            String[] brands, String[] cats, String[] params, String sort,
            String priceMin, String priceMax, Long thirdId,String[] thirdCats,String showStock,String showMobile,String isThird);



    Map<String, Object> searchGoods(SearchPageBean<EsGoodsInfo> pageBean,
                                    Long[] wareIds, String[] indices, String[] types, String keyWords,String AutoStyle,
                                    String[] brands, String[] cats, String[] params, String sort,
                                    String priceMin, String priceMax, Long thirdId,String[] thirdCats,String showStock,String showMobile,String isThird);

    /**
     * 2015.11.04 wuyanbo 添加会员价格查询字段
     * @param pageBean
     * @param wareIds
     * @param indices
     * @param types
     * @param keyWords
     * @param AutoStyle
     * @param brands
     * @param cats
     * @param params
     * @param sort
     * @param priceMin
     * @param priceMax
     * @param thirdId
     * @param thirdCats
     * @param showStock
     * @param showMobile
     * @param isThird
     * @return
     */
    Map<String, Object> searchGoods(SearchPageBean<EsGoodsInfo> pageBean,
                                    Long[] wareIds, String[] indices, String[] types, String keyWords,String AutoStyle,
                                    String[] brands, String[] cats, String[] params, String sort,
                                    String priceMin, String priceMax, Long thirdId,String[] thirdCats,String showStock,String showMobile,String isThird,
                                    String isVip);
    /**
     * 2016年1月4日 add by 付陈林
     * @param pageBean
     * @param wareIds
     * @param indices
     * @param types
     * @param keyWords
     * @param AutoStyle
     * @param brands
     * @param cats
     * @param params
     * @param sort
     * @param priceMin
     * @param priceMax
     * @param thirdId
     * @param thirdCats
     * @param showStock
     * @param showMobile
     * @param isThird
     * @return
     */
    Map<String, Object> searchGoodsByKey(SearchPageBean<EsGoodsInfo> pageBean,
                                    Long[] wareIds, String[] indices, String[] types, String keyWords,String AutoStyle,
                                    String[] brands, String[] cats, String[] params, String sort,
                                    String priceMin, String priceMax, Long thirdId,String[] thirdCats,String showStock,String showMobile,String isThird,
                                    String isVip);


    /**
     * 根据 关键词\品牌名称\分类\扩展参数\排序 进行商品查询
     * @param pageBean 分页工具类
     * @param indices 索引
     * @param types 类型
     * @param conditions 查询条件包装
     * @return 查询结果 {pageBean:{@link SearchPageBean},params:}
     */
    Map<String,Object> searchGoods(SearchPageBean<EsGoodsInfo> pageBean,String[] indices,String[] types,CommonConditions conditions);
}
