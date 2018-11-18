package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsReleParam;
import com.ningpai.goods.vo.GoodsReleParamVo;

/**
 * 商品关联商品类型详细参数
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午3:44:06
 * @version 1.0
 */
public interface GoodsReleParamMapper {
    /**
     * 删除记录(更新删除标记)
     * 
     * @param map
     *            封装删除参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录（全属性 不推荐）
     * 
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 插入的行数{@link java.lang.Integer}
     */
    int insert(GoodsReleParam record);

    /**
     * 插入一条记录（可选属性 推荐）
     * 
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 插入的行数{@link java.lang.Integer}
     */
    int insertSelective(GoodsReleParam record);

    /**
     * 根据主键ID查询
     * 
     * @param releParamId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    GoodsReleParam selectByPrimaryKey(Long releParamId);

    /**
     * 更新一条记录（可选属性 推荐）
     * 
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 更新的行数{@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsReleParam record);

    /**
     * 更新一条记录（全属性 不推荐）
     * 
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 更新的行数{@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsReleParam record);

    /**
     * 根据商品ID查询所有关联的属性
     * 
     * @param goodsId
     *            商品主键ID {@link java.lang.Long}
     * @return 查询到的关联的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.util.GoodsReleParamVo}
     */
    List<GoodsReleParamVo> queryAllByGoodsId(Long goodsId);

    /**
     * 根据商品ID和详细参数ID查询对象
     * 
     * @param map
     *            封装的查询对象 {@link java.util.Map}
     * @return 查询到的实体对象 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    GoodsReleParam queryByGoodsIdAndParamId(Map<String, Long> map);

    /**
     * 根据商品ID删除所有的关联的详细参数的记录
     * 
     * @param map
     *            封装的所需要的参数
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delAllReleParamByGoodsId(Map<String, String> map);
}
