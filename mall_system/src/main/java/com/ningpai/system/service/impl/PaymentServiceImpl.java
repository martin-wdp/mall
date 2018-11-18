/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ningpai.system.bean.Payment;
import com.ningpai.system.dao.PaymentMapper;
import com.ningpai.system.service.PaymentService;
import com.ningpai.util.PageBean;

/**
 * @ClassName: PaymentServiceImpl
 * @Description: SERVICE实现类-支付方式
 * @author Wanghy
 * @date 2014年10月11日 下午3:02:47
 */
@Service("PaymentService")
public class PaymentServiceImpl implements PaymentService {

    /** 支付方式dao */
    @Resource(name = "PaymentMapper")
    private PaymentMapper paymentMapper;

    /**
     * @Title: deletePayment
     * @Description: 根据ID删除支付方式-修改删除标记
     * @param paymentId
     * @return
     */
    @Override
    public int deletePayment(Long paymentId) {
        Payment payment = paymentMapper.selectByPrimaryKey(paymentId);
        payment.setDelflag("1");
        payment.setUpdateUserId(getLoginUserId());
        payment.setUpdateDate(new Date());
        return paymentMapper.updateByPrimaryKeySelective(payment);
    }

    /**
     * @Title: createPayment
     * @Description: 添加支付方式
     * @param record
     * @return
     */
    @Override
    public int createPayment(Payment record) {
        Date date = new Date();
        record.setCreateUserId(getLoginUserId());
        record.setCreateDate(date);
        record.setUpdateUserId(getLoginUserId());
        record.setUpdateDate(date);
        return paymentMapper.insertSelective(record);
    }

    /**
     * @Title: getPayment
     * @Description: 根据ID查询支付方式
     * @param paymentId
     * @return
     */
    @Override
    public Payment getPayment(Long paymentId) {

        return paymentMapper.selectByPrimaryKey(paymentId);
    }

    /**
     * @Title: updatePayment
     * @Description: 修改支付方式
     * @param record
     * @return
     */
    @Override
    public int updatePayment(Payment record) {
        record.setUpdateUserId(getLoginUserId());
        record.setUpdateDate(new Date());
        return paymentMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * @Title: selectAllByPb
     * @Description: 分页查询支付方式
     * @param pb
     * @return
     */
    @Override
    public PageBean selectAllByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(paymentMapper.selectAllCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(paymentMapper.selectAllByPb(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * @Title: selectAllForSite
     * @Description: 查询所有前台可展示的支付方式
     * @return
     */
    @Override
    public List<Payment> selectAllForSite() {

        return paymentMapper.selectAllForSite();
    }

    /**
     * @Title: setPaymentOpenStatus
     * @Description: 修改支付方式启用状态
     * @param paymentId
     * @return
     */
    @Override
    public boolean setPaymentOpenStatus(Long paymentId) {
        Payment payment = this.paymentMapper.selectByPrimaryKey(paymentId);
        if ("0".equals(payment.getIsOpen())) {
            payment.setIsOpen("1");
        } else {
            payment.setIsOpen("0");
        }
        payment.setUpdateUserId(getLoginUserId());
        payment.setUpdateDate(new Date());
        int n = paymentMapper.updateByPrimaryKeySelective(payment);
        return n > 0 ? true : false;
    }

    /**
     * @Title: checkOpenCount
     * @Description: 检查是启用的支付方式的数量，要修改的支付方式为唯一开启的支付方式时也不能关闭
     * @return
     */
    @Override
    public boolean checkOpenCount(Long paymentId) {
        boolean bl = true;
        Payment pm = paymentMapper.selectByPrimaryKey(paymentId);
        if ("1".equals(pm.getIsOpen()) && (paymentMapper.selectCountForSite() <= 1)) {
            bl = false;
        }
        return bl;
    }

    /**
     * @Title: checkDelete
     * @Description: 检查是否能删除支付方式，有且只有一个支付方式时不能删除。 要删除的支付方式为唯一开启的支付方式时也不能删除
     * @return
     */
    @Override
    public boolean checkDelete(Long paymentId) {
        boolean bl = true;
        // 只有一个支付方式时不能删除
        if (paymentMapper.selectAllCount() <= 1) {
            bl = false;
        }
        // 要删除的支付方式为唯一开启的支付方式时也不能删除
        Payment pm = paymentMapper.selectByPrimaryKey(paymentId);
        if ("1".equals(pm.getIsOpen()) && (paymentMapper.selectCountForSite() <= 1)) {
            bl = false;
        }
        return bl;
    }

    /*
     * 获取当前操作的用户ID
     */
    private Long getLoginUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (Long) request.getAttribute("loginUserId");
    }
}
