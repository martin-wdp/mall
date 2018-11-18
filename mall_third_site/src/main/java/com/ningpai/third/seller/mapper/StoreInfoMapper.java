/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdStoreMess;
import com.ningpai.third.register.bean.Sms;
/**
 * 店铺信息Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午5:35:58
 * @version 0.0.1
 */
public interface StoreInfoMapper {

    /**
     * 根据店铺员工ID获取店铺信息
     *
     * @param customerId
     *            商家会员编号
     * @return SotreInfo {@link com.ningpai.third.seller.bean.StoreInfo}
     */
    StoreInfo selectByEmployeeId(Long customerId);
    /**
     * 如果当前登录的会员是商家角色下面创建的员工，改员工
     * 对应的角色信息如果已经删除，就禁止该会员登录商家 结果大于1就是角色还存在可以正常登陆
     * 小于1就限制其登陆商家
     * @author zhanghl
     * @param username 用户名
     * @return
     */
    int checkThirdAuthority(String username);
    /**
     * 查询短信信息
     *
     * @return BasicEmailServer
     */
    Sms selectSms();
    /**
     *
     * @param storeId
     * @return
     */
    int deleteByPrimaryKey(Long storeId);

    /**
     *
     * @param record
     * @return
     */
    int insert(StoreInfo record);

    /**
     *
     * @param record
     * @return
     */
    int insertSelective(StoreInfo record);

    /**
     * 获取第三方店铺信息
     * @param storeId
     * @return
     */
    StoreInfo selectByPrimaryKey(Long storeId);

    /**
     * 根据店铺名称查找
     * @param storeName
     * @return
     */
    StoreInfo selectByStoreName(String storeName);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(StoreInfo record);

    /**
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(StoreInfo record);

    /**
     * 
     * @param customerId
     * @return
     */
    StoreInfo selectByCustomerId(Long customerId);

    /**
     * 查询当前要设置的消息设置
     * 
     * @param request
     * @param mid
     *            模块编号
     * @return ThirdStoreMess 消息设置 {@link ThirdStoreMess}
     */
    ThirdStoreMess queryStoreMessBySidAndMid(Map<String, Object> paramMap);

    /**
     * 修改消息接收设置
     * 
     * @param request
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return 0失败 1成功
     */
    int updateStoreMess(ThirdStoreMess mess);

    /**
     * 添加消息接收设置
     * 
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return 0失败 1成功
     */
    int addStoreMess(ThirdStoreMess mess);

    /**
     * 查询打回信息
     * 
     * @param customerId
     *            会员编号 {@link Long}
     * @return 包含打回信息的商家信息 {@link StoreInfo}
     */
    StoreInfo selectRefuseInfo(Long customerId);

    /**
     * 修改拒绝信息
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 0失败 1成功
     */
    int updateRefuseInfo(StoreInfo storeInfo);

    /**
     * 查询所有的第三方商家信息
     * 
     * @return 商家信息
     */
    List<StoreInfo> selectAll();

    /**
     * 查询店铺名称是否已使用
     *第三方后台开通店铺时调用
     *
     * @param storeName    页面传入的店铺名称
     * @return              查询出的数量
     * @author houyichang  2015/9/18
     * */
    int queryCountByStoreName(String storeName);
 }
