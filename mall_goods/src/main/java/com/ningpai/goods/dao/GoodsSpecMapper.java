package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsSpecVo;

/**
 * 商品规格DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午5:33:01
 * @version 1.0
 */
public interface GoodsSpecMapper {
    /**
     * 根据主键删除商品规格
     * 
     * @param map
     *            删除的参数 包含删除的ID，删除人名称 {@link java.util.Map}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入规格记录 (全属性 不推荐)
     * 
     * @param record
     *            商品规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 插入的实体的id {@link java.lang.Integer}
     */
    int insert(GoodsSpec record);

    /**
     * 插入规格记录 (可选属性 推荐)
     * 
     * @param record
     *            商品规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 插入的实体的id {@link java.lang.long}
     */
    Long insertSelective(GoodsSpec record);

    /**
     * 根据主键查询
     * 
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @return 查询到的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     */
    GoodsSpec selectByPrimaryKey(Long specId);

    /**
     * 更新规格(可选属性 推荐)
     * 
     * @param record
     *            待更新的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsSpec record);

    /**
     * 更新规格(全属性 不推荐)
     * 
     * @param record
     *            待更新的规格实体 {@link com.ningpai.goods.bean.GoodsSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsSpec record);

    /**
     * 查询所有的行数
     * 
     * @return 所有的行数{@link java.lang.Integer}
     */
    int queryTotalCount();

    /**
     * 根据分页参数查询分页列表
     * 
     * @param map
     *            封装分页参数 开始行数和结束行数 {@link java.util.Map}
     * @return 查询到的集合 {@link java.util.List}
     */
    List<Object> queryListByPageBean(Map<String, Integer> map);

    /**
     * 根据规格ID查询Vo
     * 
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @return 查询到的Vo对象 {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    GoodsSpecVo querySpecVoBySpecId(Long specId);

    /**
     * 查询最新插入的ID
     * 
     * @return 查询到的ID {@link java.lang.Long}
     */
    Long selectLastId();

    /**
     * 查询所有的商品规格
     * 
     * @return 查询到的商品规格的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpec}
     */
    List<GoodsSpec> queryAllSpec();

    /**
     * 查询所有的商品规格包含删除的
     * 
     * @return 查询到的商品规格的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpec}
     */
    List<GoodsSpec> queryAllSpecIncludeDel();

    /**
     * 根据货品ID查询关联的规格
     * 
     * @param goodsId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    List<GoodsSpecVo> querySpecVoByGoodsInfoId(Long goodsId);

    /**
     * 根据商品ID查询关联的规格VO
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    List<GoodsSpecVo> querySpecVoByGoodsId(Long goodsId);

    /**
     * 分页查询总行数
     * 
     * @param selectBean
     * @return int
     */
    int searchTotalCount(SelectBean selectBean);

    /**
     * 分页查询信息
     * 
     * @param map
     * @return List
     */
    List<Object> searchAllSpec(Map<String, Object> map);

    /**
     * 根据规格名称查询行数
     * 
     * @param specName
     *            规格名称 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryCountBySpecName(String specName);

}
