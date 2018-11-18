package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.PromotionLogo;
/**
 * LOgo
 * @author ggn
 *
 */
public interface PromotionLogoMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    int deleteByPrimaryKey(Long promotionLogoId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    int insert(PromotionLogo promotionLogo);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    int insertSelective(PromotionLogo promotionLogo);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    PromotionLogo selectByPrimaryKey(Long promotionLogoId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    int updateByPrimaryKeySelective(PromotionLogo promotionLogo);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2015-05-21 13:15:54
     */
    int updateByPrimaryKey(PromotionLogo promotionLogo);

    /**
     * 查询所有促销LOGO
     * 
     * @return List
     */
    List<Object> queryPromotionLogoList(Map<String, Object> paramMap);

    /**
     * 查询促销LOGO数目
     * 
     * @return int
     */
    int queryPromotionLogoCount(Map<String, Object> paramMap);

    /**
     * 根据促销logoid删除促销LOGO
     * 
     * @param list
     *            促销LOGO Id
     * @return int
     */
    int delAllPromotionLogo(List<Long> list);

    /**
     * 根据促销Logo名称查询行数
     * 
     * @param promotionLogoName
     *            促销logo名称
     * @return int
     */
    int checkLogoName(String promotionLogoName);

    /**
     * 查询所有促销LOGO
     * 
     * @return
     */
    List<PromotionLogo> queryAllLogoList();

    /**
     * 查询刚刚插入的LOGO的ID
     * 
     * @return Long
     */
    Long selectLastId();
}