package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTypeBrand;
import com.ningpai.goods.vo.GoodsTypeBrandVo;

/**
 * 商品关联品牌表DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午9:57:30
 * @version 1.0
 */
public interface GoodsTypeBrandMapper {
    /**
     * 根据主键ID删除
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 新增一条记录 （全属性 不推荐）
     * 
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insert(GoodsTypeBrand record);

    /**
     * 新增一条记录 （可选属性 推荐）
     * 
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsTypeBrand record);

    /**
     * 根据主键查询
     * 
     * @param typeBrandId
     *            主键ID {@link java.lang.Long}
     * @return 类型关联品牌类实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    GoodsTypeBrand selectByPrimaryKey(Long typeBrandId);

    /**
     * 更新实体（可选属性 推荐）
     * 
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsTypeBrand record);

    /**
     * 更新实体 （全属性 不推荐）
     * 
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsTypeBrand record);

    /**
     * 根据类型ID查询所有的类型关联品牌实体
     * 
     * @param typeId
     *            商品类型主键ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    List<GoodsTypeBrandVo> queryAllTypeBrand(Long typeId);

    /**
     * 根据类别的集合查询出所有的品牌集合
     * @param cate
     * @return
     */
    List<GoodsTypeBrandVo> queryBrandByCatIds(List<GoodsCate> cate);


    /**
     * 根据类型ID和品牌ID查询实体
     * 
     * @param map
     *            {@link java.util.Map}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    GoodsTypeBrand queryTypeBrandByTypeIdAndBrandId(Map<String, Long> map);
}
