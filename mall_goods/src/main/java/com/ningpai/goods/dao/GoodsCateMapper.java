package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.vo.GoodsCateVo;

/**
 * 商品分类Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:26:02
 * @version 1.0
 */
public interface GoodsCateMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            分类主键ID和删除人名称 {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条数据(全属性，不推荐)
     * 
     * @param record
     *            插入的分类实体{@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insert(GoodsCate record);

    /**
     * 插入一条商品分类信息 (可选属性 推荐)
     * 
     * @param record
     *            待插入的商品分类实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsCate record);

    /**
     * 根据主键查询
     * 
     * @param catId
     *            分类ID {@link java.lang.Integer}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsCate}
     */
    GoodsCateVo selectByPrimaryKey(Long catId);

    /**
     * 更新商品分类 (可选属性 推荐)
     * 
     * @param record
     *            更新的商品实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsCate record);

    /**
     * 更新商品分类
     * 
     * @param record
     *            更新商品分类的实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsCate record);

    /**
     * 根据pageBean分页查询分类列表
     * 
     * @param map
     *            封装分页参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 查询所有的记录行数
     * 
     * @return {@link java.lang.Integer}
     */
    int queryTotalCount(Map<String, Object> map);

    /**
     * 查询所有的商品分类
     * 
     * @return 所有的商品分类列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCateVo> queryAllGoosCate();

    /**
     * 查询一级的商品分类
     *
     * @return 所有的商品分类列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCateVo> queryFirstLevelGoodsCate();



    /**
     * 根据父分类ID查询子分类的个数
     * 
     * @param cateId
     * @return
     */
    int querySonCountByParentId(Long cateId);

    /**
     * 根据分类名称搜索商品分类
     * 
     * @param cateName
     *            分类名称
     * @return 查询到的分类信息
     */
    GoodsCate queryCateByCateName(String cateName);

    /**
     * 根据list查询所有商品分类
     * 
     * @param list
     * @return List
     */
    List<Object> selectProductCateList(List<Long> list);

    /**
     * 查询所有商品分类
     * 
     * @return List
     */
    List<GoodsCate> queryAllGoodCate();

    /**
     * 根据分类查询所有的子分类
     * 
     * @param parentId
     *            父分类ID {@link Long}
     * @return 查询到的分类列表 {@link List}
     */
    List<GoodsCate> querySonCatByParentId(Long parentId);

    /**
     * 查询所有商品三级分类
     * 
     * @return List
     */
    List<GoodsCate> queryAllGoodThirdCate();

    /**
     * 根据参数查询商品分类
     * 
     * @param paramMap
     *            查询参数
     * @return 返回商品分类list
     */
    List<GoodsCate> querySonCatByParm(Map<String, Object> paramMap);

    /**
     * 根据参数查询商品分类
     * 
     * @param paramMap
     *            查询参数
     * @return 返回商品分类list
     */
    List<GoodsCateVo> querySonCatVoByParm(Map<String, Object> paramMap);
}
