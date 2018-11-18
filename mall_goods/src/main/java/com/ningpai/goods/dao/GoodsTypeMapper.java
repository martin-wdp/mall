package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsType;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.vo.GoodsTypeVo;

/**
 * 商品类型DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:20:18
 * @version
 */
public interface GoodsTypeMapper {
    /**
     * 删除商品类型
     * 
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Map<String, String> map);

    /**
     * 插入一条记录 （全属性 不推荐）
     * 
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insert(GoodsType record);

    /**
     * 插入一条记录 （可选属性 推荐）
     * 
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int insertSelective(GoodsType record);

    /**
     * 根据主键查询
     * 
     * @param typeId
     *            类型主键 {@link java.lang.Long}
     * @return 查询到的类型实体Vo {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    GoodsTypeVo selectByPrimaryKey(Long typeId);

    /**
     * 更新 (可选属性 推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsType record);

    /**
     * 更新 (全属性 不推荐)
     * 
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsType}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsType record);

    /**
     * 查询所有的商品类型
     * 
     * @param map
     *            封装的分页参数
     * @return 查询到的类型列表 {@link com.ningpai.goods.bean.GoodsType}
     *         {@link java.util.Map}
     */
    List<Object> selectAllType(Map<String, Integer> map);

    /**
     * 查询所有的行数 用来分页
     * 
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryTotalCount();

    /**
     * 查询最新插入的ID
     * 
     * @return 查询到的ID {@link java.lang.Long}
     */
    Long selectLastId();

    /**
     * 根据商品分类ID查询商品类型VO
     * 
     * @param catId
     *            商品分类ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsTypeVo}
     */
    GoodsTypeVo queryTypeVoByCatId(Long catId);

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
    List<Object> searchAllType(Map<String, Object> map);

    /**
     * 根据类型名称查询行数
     * 
     * @param typeName
     *            类型名称 {@link java.lang.String}
     * @return 查询到的行数 {@link java.lang.Integer}
     */
    int queryCountByTypeName(String typeName);

    /**
     * 取出所有可用的类型
     * 
     * @return
     */
    public List<GoodsType> selectAll();
}
