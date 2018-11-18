/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.service;

import java.util.List;

import com.ningpai.thirdaudit.bean.DeduBrokeage;
import com.ningpai.thirdaudit.bean.DeduBrokeageVo;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.util.PageBean;

/**
 * 商家审核接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月26日 下午4:33:21
 * @version 0.0.1
 */
public interface AuditService {

    /**
     * 设置改店铺是都在店铺街显示以及排序
     * 
     * @param setTore
     *            ：排布
     * @param isShow
     *            ：是否显示 author zhanghl
     * @return
     */
    int setStore(Long setTore, String isShow, Long storeId);

    /**
     * 查询会员id
     * 
     * @param storeId
     * @return
     */
    int findcid(Long storeId);

    /**
     * 查询商家审核列表
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @param pageBean
     *            分页辅助类 {@link PageBean}
     * @return 分页辅助类 {@link PageBean}
     */
    PageBean selectAuditList(StoreInfo storeInfo, PageBean pageBean);

    /**
     * 审核商家信息
     * 
     * @param parameterValues
     *            商家编号
     * @return 0修改失败 1修改成功 {@link Integer}
     */
    Integer updateStore(String[] parameterValues, DeduBrokeage brokeage, String time, String payIds, String storeQi);

    /**
     * 根据卖家编号查找店铺信息
     * 
     * @param storeId
     *            商家编号
     * @return SotreInfo {@link StoreInfo}
     */
    StoreInfo selectByCustomerId(Long storeId);

    /**
     * 打回商家提交信息
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return
     */
    Integer refuseStore(StoreInfo storeInfo);

    /**
     * 根据商家编号 查询商家店铺名称 和是否开启商家首页
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @return 商家信息 只包含店铺名称和是否开启首页 {@link StoreInfo}
     */
    StoreInfo selectNameAndIsStoreBySId(Long storeId);

    /**
     * 查询商家扣率
     * 
     * @param storeId
     * @return
     */
    List<DeduBrokeageVo> selectBrokeageByStoreId(Long storeId);

    /**
     * 修改商家结算信息中的支付方式
     * 
     * @param storeId
     *            商家id
     * @param payIds
     *            支付方式id
     * @param deduction
     *            扣点
     * @param brokerage
     *            平台扣点
     * @return 执行结果
     */
    Integer updatePayWay(Long storeId, Long[] payIds, String deduction, String brokerage);

    /**
     * 修改支付支付方式扣点
     * 
     * @param storeId
     * @param billingCycle
     * @return
     */
    Integer updatePayMent(Long storeId, String billingCycle);

    /**
     * 修改商铺期限
     * 
     * @param bussDate
     * @param goodsBelo
     * @return
     */
    int updateStoreValidTime(String bussDate, Long goodsBelo);

    /**
     * 查询第三方店铺关闭时间判断是否开启
     * 
     * @param thirdId
     * @return
     */
    int selectStoreTimeByThirdId(Long thirdId);
}
