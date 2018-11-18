package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.Payment;

/**
 * @ClassName: PaymentMapper
 * @Description: DAO-支付方式
 * @author Wanghy
 * @date 2014年10月11日 下午2:46:25
 */
public interface PaymentMapper {
    /**
     * @Title: deleteByPrimaryKey
     * @Description: 根据ID删除
     * @param paymentId
     * @return
     */
    int deleteByPrimaryKey(Long paymentId);

    /**
     * @Title: insert
     * @Description: 添加
     * @param record
     * @return
     */
    int insert(Payment record);

    /**
     * @Title: insertSelective
     * @Description: 添加-字段可选
     * @param record
     * @return
     */
    int insertSelective(Payment record);

    /**
     * @Title: selectByPrimaryKey
     * @Description: 根据ID查询
     * @param paymentId
     * @return
     */
    Payment selectByPrimaryKey(Long paymentId);

    /**
     * @Title: updateByPrimaryKeySelective
     * @Description: 修改-字段可选
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Payment record);

    /**
     * @Title: updateByPrimaryKey
     * @Description: 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(Payment record);

    /**
     * @Title: selectAllCount
     * @Description: 查询所有未删除的数量-分页用
     * @return
     */
    int selectAllCount();

    /**
     * @Title: selectAllByPb
     * @Description: 分页查询所有未删除的
     * @param map
     * @return
     */
    List<Object> selectAllByPb(Map<String, Object> map);

    /**
     * @Title: selectAllForSite
     * @Description: 查询所有已启用、未删除的
     * @return
     */
    List<Payment> selectAllForSite();

    /**
     * @Title: selectCountForSite
     * @Description: 查询所有已启用未删除的数量
     * @return
     */
    int selectCountForSite();
}
