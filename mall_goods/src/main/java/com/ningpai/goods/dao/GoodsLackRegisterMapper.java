package com.ningpai.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsLackRegister;
import com.ningpai.goods.util.LackRegisterSearchBean;

/**
 * 到货通知DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午10:20:05
 * @version 1.0
 */
public interface GoodsLackRegisterMapper {
    /**
     * 根据主键删除到货通知
     * 
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Long lackRegisterId);

    /**
     * 插入一条数据(全属性 不推荐)
     * 
     * @param record
     *            到货通知实体{@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    int insert(GoodsLackRegister record);

    /**
     * 插入一条数据 (可选属性 推荐)
     * 
     * @param record
     *            到货通知实体{@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 插入的主键ID {@link java.lang.Integer}
     */
    int insertSelective(GoodsLackRegister record);

    /**
     * 根据主键查询到货通知实体
     * 
     * @param lackRegisterId
     *            {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsLackRegister}
     */
    GoodsLackRegister selectByPrimaryKey(Long lackRegisterId);

    /**
     * 更新到货通知 (可选属性 推荐)
     * 
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKeySelective(GoodsLackRegister record);

    /**
     * 更新到货通知 (全属性 不推荐)
     * 
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsLackRegister}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateByPrimaryKey(GoodsLackRegister record);

    /**
     * 查询所有的行数
     * 
     * @return {@link java.lang.Integer}
     */
    int queryTotalCount();

    /**
     * 根据分页参数查询列表
     * 
     * @param map
     *            封装分页参数 {@link java.util.Map}
     * @return {@link java.util.List}
     */
    List<Object> queryAllByPageBean(Map<String, Integer> map);

    /**
     * 更改通知状态
     * 
     * @param lackId
     *            {@link java.lang.Long}
     * @return {@link java.lang.Integer}
     */
    int updateNoticeStatus(Long lackId);

    /**
     * 根据查询参数查询行数
     * 
     * @param searchBean
     * @return
     */
    int queryTotalCountBySearchBean(LackRegisterSearchBean searchBean);

    /**
     * 高级查询
     * 
     * @param map
     *            封装参数 {@link java.util.Maps}
     * @return 查询到的列表
     */
    List<Object> queryByPageBeanAndSearchBean(Map<String, Object> map);

    /**
     * 根据货品ID更新到货通知状态
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateStatusByProductId(Long productId);
}
