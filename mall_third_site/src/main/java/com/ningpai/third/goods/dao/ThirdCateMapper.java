package com.ningpai.third.goods.dao;

import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.vo.ThirdCateVo;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方分类
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月6日 上午11:12:46
 * @version 2.0
 */
public interface ThirdCateMapper {
    /**
     * 删除商家分类
     * 
     * @param map
     *            删除的参数
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Map<String, Object> map);

    /**
     * 新删除商家分类
     * 
     * @param map
     * @return
     */
    int deleteByPrimaryKeyNew(Map<String, Object> map);

    /**
     * 插入记录
     * 
     * @param record
     *            待插入的实体
     * @return 插入的行数
     */
    int insertSelective(ThirdCate record);

    /**
     * 根据主键查询
     * 
     * @param catId
     *            分类ID {@link Integer}
     * @return 查询到的实体
     */
    ThirdCateVo selectByPrimaryKey(Long catId);

    /**
     * 更新商品分类 (可选属性 推荐)
     *
     * @param record
     *            更新的商品实体 {@link com.ningpai.third.goods.bean.ThirdCate}
     * @return 受影响的行数 {@link Integer}
     */
    int updateByPrimaryKeySelective(ThirdCate record);

    /**
     * 查询所有的商品分类
     * 
     * @return 所有的商品分类列表
     */
    List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map);

    /**
     * 根据父分类ID查询子分类的个数
     * 
     * @param cateId
     *            父分类ID
     * @return 查询到的行数
     */
    int querySonCountByParentId(Long cateId);

    /**
     * 根据分类名称查询第三方分类
     * 
     * @return 查询到的第三方分类
     */
    ThirdCate queryCateByCateName(Map<String, Object> map);

    /**
     * 根据list查询所有的商品分类
     * 
     * @param list
     * @return
     */
    List<Object> selectProductCateList(List<Long> list);

    /**
     * 查询所有的第三方分类
     */
    List<ThirdCate> queryAllCate(Long thirdId);

    /**
     * 查询所有的父分类集合
     * 
     * @return 查询到的父分类的集合
     */
    List<ThirdCateVo> queryAllCateForList(Long thirdId);

    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合
     * 
     * @param map
     *            封装参数 catId 父分类ID thirdId 第三方店家ID
     * @return 查询到的子分类集合
     */
    List<ThirdCate> queryThirdCateByParentId(Map<String, Object> map);

    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合
     * 
     * @param thirdId
     *            封装参数 catId 父分类ID thirdId 第三方店家ID
     * @return 查询到的子分类集合
     */
    List<ThirdCate> queryThirdCateByParentIdtwo(Long thirdId);

    /**
     * 根据分类分类名称,第三方ID,层级标记查询分类集合
     * 
     * @param map
     *            封装参数 thirdId 第三方ID catName 分类名称 grade 层级标记
     * @return 查询到的分类集合
     */
    List<ThirdCate> queryThirdCateByCatNameAndGrade(Map<String, Object> map);

    /**
     * 查询商家分类
     * 
     * @param map
     * @return
     */
    List<ThirdCate> querySonCatByParm(Map<String, Object> map);
}
