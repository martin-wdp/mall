package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsReleExpandParam;
import com.ningpai.goods.vo.GoodsReleExpandParamVo;

/**
 * 商品关联类型扩展属性
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午3:14:28
 * @version 1.0
 */
public interface GoodsReleExpandParamMapper {
    /**
     * 删除商品关联类型扩展属性
     * 
     * @param map
     *            封装相关参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入记录 (全属性 不推荐)
     * 
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insert(GoodsReleExpandParam record);

    /**
     * 插入记录 (可选属性 推荐)
     * 
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsReleExpandParam record);

    /**
     * 根据主键查询
     * 
     * @param releExpandparamId
     *            主键id {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     */
    GoodsReleExpandParam selectByPrimaryKey(Long releExpandparamId);

    /**
     * 更新信息 (可选属性 推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsReleExpandParam record);

    /**
     * 更新信息 (全属性 不推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsReleExpandParam record);

    /**
     * 根据商品ID查询关联的所有的扩展属性
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsReleExpandParamVo}
     */
    List<GoodsReleExpandParamVo> queryAllByGoodsId(Long goodsId);

    /**
     * 根据商品ID和扩展属性ID查询记录
     * 
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的记录 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     */
    GoodsReleExpandParam queryByGoodsIdAndExpandParamId(Map<String, Long> map);

    /**
     * 根据商品ID删除所有的扩展属性
     * 
     * @param map
     *            封装的参数
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int delAllExpandParamByGoodsId(Map<String, String> map);

}
