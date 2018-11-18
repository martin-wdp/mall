package com.ningpai.goods.site.goods.dao;

import java.util.List;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;

/**
 * 商品分类Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:26:02
 * @version 1.0
 */
public interface GoodsCateMapper {
    /**
     * 查询所有的商品分类
     * 
     * @return 分类列表 {@link java.util.List} {@link com.ningpai.goods.vo.GoodsCateVo}
     */
    List<GoodsCateVo> queryAllCate();

    /**
     * 查询最上级分类
     * 
     * @return 查询到的分类 {@link com.ningpai.goods.vo.GoodsCateVo}
     */
    GoodsCateVo queryTopParent();

    /**
     * 根据分类ID查询分类Vo
     * 
     * @param catId
     *            分类ID
     * @return 查询到的Vo实体
     */
    GoodsCateVo queryCateVoByCatId(Long catId);

    /**
     * 根据指定分类ID查询下一级的分类ID
     * 
     * @param catId
     *            分类ID
     * @return 查询到的集合
     */
    List<GoodsCate> querySonCateByCatId(Long catId);

    /**
     * 根据主键查询
     * 
     * @param catId
     *            主键ID
     * @return 查询到的分类信息
     */
    GoodsCate queryCateByPrimaryKey(Long catId);

    /**
     * 根据分类ID查询当前分类和父分类
     * 
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     */
    GoodsCateVo queryCateAndParCateByCatId(Long catId);

    /**
     * 根据分类ID查询面包屑辅助Bean
     * 
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的辅助Bean {@link GoodsBreadCrumbVo}
     */
    GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId);

}