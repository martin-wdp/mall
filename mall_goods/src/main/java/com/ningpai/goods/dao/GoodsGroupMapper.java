package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsGroupVo;

/**
 * 商品组合DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 上午11:14:15
 * @version 1.0
 */
public interface GoodsGroupMapper {
    /**
     * 根据主键删除
     * 
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 删除的行数
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 新 删除商品组合
     * 
     * @param map
     * @return
     */
    int deleteByPrimaryKeyNew(Map<String, String> map);

    /**
     * 插入一条记录
     * 
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 插入的行数{@link java.lang.Long}
     */
    Long insertSelective(GoodsGroup record);

    /**
     * 根据主键查询商品组合信息
     * 
     * @param groupId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体{@link com.ningpai.goods.bean.GoodsGroup}
     */
    GoodsGroup selectByPrimaryKey(Long groupId);

    /**
     * 更新组合信息
     * 
     * @param record
     *            待更新的实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @return 更新的行数{@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsGroup record);

    /**
     * 根据分页参数分页查询列表
     * 
     * @param map
     *            封装了分页查询的参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     */
    List<Object> queryAllByPageBean(Map<String, Integer> map);

    /**
     * 查询所有的行数
     * 
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryTotalAcount();

    /**
     * 根据主键ID查询组合VO
     * 
     * @param groupId
     *            组合主键ID {@link java.lang.Long}
     * @returnx 查询到的VO信息
     */
    GoodsGroupVo queryVoByPrimaryKey(Long groupId);

    /**
     * 根据分页参数分页查询列表
     * 
     * @param map
     *            封装了分页查询的参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     */
    List<Object> queryAllByPageBeanAndSelBean(Map<String, Object> map);

    /**
     * 根据参数Bean查询行数
     * 
     * @param selectBean
     * @return 查询到的行数
     */
    int searchTotalCount(SelectBean selectBean);

    /**
     * 查询最新插入的主键ID
     * 
     * @return 查询到的主键ID
     */
    Long selectLastId();

    /**
     * 根据货品ID查询所在的组合或者是套装
     * 
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsGroupVo> queryGroupVoByProductId(Long productId);
}
