/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.Pay;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 支付方式设置服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月19日 上午9:35:15
 * @version 1.0
 */
public interface PayService {

    /**
     * 分页查询支付方式
     * 
     * @param pageBean
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);
    
    /**
     * 分页查询支付方式为微信支付的数据
     */
    PageBean findPayByPayType(PageBean pageBean, SelectBean selectBean);
    
    /**
     * 查询所有的支付方式
     * 
     * @return 查询到的支付方式的列表
     */
    List<Object> queryAllPaySet();

    /**
     * 添加支付方式
     * 
     * @param pay
     * @return int
     */
    int insertPay(Pay pay);

    /**
     * 删除支付方式
     * 
     * @param parameterValues
     * @return
     */
    int deletePay(String[] payIds);

    /**
     * 根据payId查询支付信息
     * 
     * @param payId
     * @return
     */
    Pay findByPayId(Long payId);

    /**
     * 修改支付设置信息
     * 
     * @param pay
     * @return
     */
    int updatePay(Pay pay);

    /**
     * 根据ID修改支付接口启用状态<br/>
     * 已启用改为不启用，不启用改为已启用
     * 
     * @param payId
     * @return
     */
    boolean updateUserdStatus(Long payId);

    /**
     * 根据ID修改为默认支付方式<br/>
     * 其他支付接口设置取消默认
     * 
     * @return
     */
    boolean changeDefault(Long payId);

    /**
     * 根据主键删除支付接口
     * @param payId 支付接口主键id
     */
    void deletePaySetById(Long payId);
    
    /**
     * 修改支付问题描述
     * @param pay 支付对象
     * @return int
     */
    int updatePayHelp(Pay pay);
}
