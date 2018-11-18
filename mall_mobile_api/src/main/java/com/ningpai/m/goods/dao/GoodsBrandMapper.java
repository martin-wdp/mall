package com.ningpai.m.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.util.SelectBean;

/**
 * 商品品牌Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月16日 下午7:29:09
 * @version 1.0
 */
public interface GoodsBrandMapper {
//    /**
//     * 根据主键删除商品品牌
//     * 
//     * @param map
//     *            品牌主键ID {@link java.lang.Long}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int deleteByPrimaryKey(Map<String, String> map);
//
//    /**
//     * 插入一条商品品牌（全属性）
//     * 
//     * @param record
//     *            商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int insert(GoodsBrand record);
//
//    /**
//     * 插入一条商品品牌(可选属性,包括详细介绍)
//     * 
//     * @param record
//     *            商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int insertSelective(GoodsBrand record);
//
//    /**
//     * 根据主键查询品牌
//     * 
//     * @param brandId
//     *            品牌主键ID {@link java.lang.Long}
//     * @return 商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     */
//    GoodsBrand selectByPrimaryKey(Long brandId);
//
//    /**
//     * 更新商品品牌(可选属性)
//     * 
//     * @param record
//     *            商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int updateByPrimaryKeySelective(GoodsBrand record);
//
//    /**
//     * 更新商品品牌(全属性)
//     * 
//     * @param record
//     *            商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int updateByPrimaryKeyWithBLOBs(GoodsBrand record);
//
//    /**
//     * 更新商品品牌(不更新商品介绍)
//     * 
//     * @param record
//     *            商品品牌实体 {@link com.ningpai.goods.bean.GoodsBrand}
//     * @return 受影响的行数 {@link java.lang.Integer}
//     */
//    int updateByPrimaryKey(GoodsBrand record);
//
//    /**
//     * 查询所有的记录数
//     * 
//     * @return {@link java.lang.Integer}
//     */
//    int queryTotalCount();
//
//    /**
//     * 根据分页参数查询分页列表
//     * 
//     * @param map
//     *            包括开始的条数和结束的条数
//     * @return 查询到的实体列表 {@link com.ningpai.goods.bean.GoodsBrand}
//     *         {@link java.util.List}
//     */
//    List<Object> queryByPageBean(Map<String, Integer> map);
//
//    /**
//     * 查询所有的商品品牌
//     * 
//     * @return 查询到的商品品牌的集合 {@link java.util.List}
//     *         {@link com.ningpai.goods.bean.GoodsBrand}
//     */
//    List<GoodsBrand> queryAllBrand();
//
//    /**
//     * 根据名称查询商品品牌
//     * 
//     * @return 查询到的商品品牌的集合 {@link java.util.List}
//     *         {@link com.ningpai.goods.bean.GoodsBrand}
//     */
//    List<GoodsBrand> queryallbrandbyName(String brandName);
//
//    /**
//     * 分页查询总行数
//     * 
//     * @param selectBean
//     * @return int
//     */
//    int searchTotalCount(SelectBean selectBean);
//
//    /**
//     * 分页查询信息
//     * 
//     * @param map
//     * @return List
//     */
//    List<Object> searchAllBrand(Map<String, Object> map);

    /**
     * 根据List查询多个商品品牌
     * 
     * @param idList
     * @return List
     */
    List<Object> selectProductBrandList(List<Long> idList);

//    /**
//     * 查询所有brand
//     * 
//     * @return List
//     */
//    List<GoodsBrand> queryAllBrandList();
//
//    /**
//     * 根据品牌名称查询行数
//     * 
//     * @param brandName
//     *            待查询的行数　{@link java.lang.String}
//     * @return 查询到的行数{@link java.lang.Integer}
//     */
//    int queryByBrandName(String brandName);
//
//    /**
//     * 根据品牌名称查询行数
//     *
//     * @param brandName
//     *            待查询的行数　{@link java.lang.String}
//     * @return 查询到的行数{@link java.lang.Integer}
//     */
//    int selectByBrandName(String brandName);
//
//    /**
//     * 根据品牌名称查询行数（后台添加品牌时调用）
//     *
//     * @param brandName
//     *            品牌名称
//     * @return int 查询到的行数 1有记录 0是没记录
//     * @author houyichang 2015/6/30
//     * */
//    int queryBrandByBrandName(String brandName);
}
