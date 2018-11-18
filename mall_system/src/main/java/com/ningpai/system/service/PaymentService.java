/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Payment;
import com.ningpai.util.PageBean;

/**
 * @ClassName: PaymentService
 * @Description: SERVICE-支付方式
 * @author Wanghy
 * @date 2014年10月11日 下午2:56:25
 */
public interface PaymentService {
    /**
     * @Title: deletePayment
     * @Description: 根据ID删除支付方式-修改删除标记
     * @param paymentId
     * @return
     */
    int deletePayment(Long paymentId);

    /**
     * @Title: createPayment
     * @Description: 添加支付方式
     * @param record
     * @return
     */
    int createPayment(Payment record);

    /**
     * @Title: getPayment
     * @Description: 根据ID查询支付方式
     * @param paymentId
     * @return
     */
    Payment getPayment(Long paymentId);

    /**
     * @Title: updatePayment
     * @Description: 修改支付方式
     * @param record
     * @return
     */
    int updatePayment(Payment record);

    /**
     * @Title: selectAllByPb
     * @Description: 分页查询支付方式
     * @param pb
     * @return
     */
    PageBean selectAllByPb(PageBean pb);

    /**
     * @Title: selectAllForSite
     * @Description: 查询所有前台可展示的支付方式
     * @return
     */
    List<Payment> selectAllForSite();

    /**
     * @Title: setPaymentOpenStatus
     * @Description: 修改支付方式启用状态
     * @param paymentId
     * @return
     */
    boolean setPaymentOpenStatus(Long paymentId);

    /**
     * @Title: checkOpenCount
     * @Description: 检查是启用的支付方式的数量，要修改的支付方式为唯一开启的支付方式时也不能关闭
     * @return
     */
    boolean checkOpenCount(Long paymentId);

    /**
     * @Title: checkDelete
     * @Description: 检查是否能删除支付方式，有且只有一个支付方式时不能删除。 要删除的支付方式为唯一开启的支付方式时也不能删除
     * @return
     */
    boolean checkDelete(Long paymentId);
}
