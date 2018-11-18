package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.vo.DistrictVo;

/**
 * DAO-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午10:19:32
 */
public interface DeliveryPointMapper {
    /**
     * 删除
     * 
     * @param deliveryPointId
     * @return
     */
    int deleteByPrimaryKey(Long deliveryPointId);

    /**
     * 批量删除
     * 
     * @param deliveryPointId
     * @return
     */
    int batchDeleteByPrimaryKey(Long[] deliveryPointIds);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(DeliveryPoint record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(DeliveryPoint record);

    /**
     * 根据ID查询
     * 
     * @param deliveryPointId
     * @return
     */
    DeliveryPoint selectByPrimaryKey(Long deliveryPointId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(DeliveryPoint record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(DeliveryPoint record);

    /**
     * 根据区县ID查询所有未删除的数量-分页用
     * 
     * @param map
     * @return
     */
    int selectAllCount(Map<String, Object> map);

    /**
     * 根据区县ID分页查询所有未删除的-分页
     * 
     * @param map
     * @return
     */
    List<Object> selectAllByPb(Map<String, Object> map);

    /**
     * 根据区县ID查询未删除、已启用的自提点-前台用
     * 
     * @return
     */
    List<DeliveryPoint> selectByDistrictIdForSite(Map<String, Object> map);

    /**
     * 根据县id集合查询所有自提点
     * 
     * @param disvo
     * @return
     */
    List<DeliveryPoint> selectDeliveryPointBydistrictIds(List<DistrictVo> disvo);
}
