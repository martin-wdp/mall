/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.thirdaudit.bean.StoreInfo;

/**
 * 店铺信息Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午5:35:58
 * @version 0.0.1
 */
public interface StoreInfoMapper {
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
     * 
     * @param storeId
     * @return
     */
    StoreInfo selectByStoreId(Long storeId);

    /**
     * 查询需要审核的数量
     * 
     * @param storeInfo
     *            查询条件 {@link StoreInfo}
     * @return 总数量 {@link Long}
     */
    Long selectAuditListSize(StoreInfo storeInfo);

    /**
     * 查询需要审核的列表
     * 
     * @param paramMap
     *            查询条件 {@link Map}
     * @return 审核列表 {@link List}
     */
    List<Object> selectAuditList(Map<String, Object> paramMap);

    /**
     * 审核商家信息
     * 
     * @param paramMap
     *            商家编号 {@link Map}
     * @return 0修改失败 1修改成功 {@link Integer}
     */
    int updateStore(Map<String, Object> paramMap);

    /**
     * 审核商家信息
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @return 0修改失败 1修改成功 {@link Integer}
     */
    int auditBrand(Long storeId);

    /**
     * 打回商家信息
     * 
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 0 失败 1成功
     */
    int refuseStore(StoreInfo storeInfo);

    /**
     * 根据商家编号 查询商家店铺名称 和是否开启商家首页
     * 
     * @param storeId
     *            商家编号 {@link Long}
     * @return 商家信息 只包含店铺名称和是否开启首页 {@link StoreInfo}
     */
    StoreInfo selectNameAndIsStoreBySId(Long storeId);

    /* *//**
     * 查询所有
     * 
     * @return
     */
    /*
     * List<Object> queryAllStoreInfo(Map<String, Object> pmap);
     *//**
     * 查询总条数
     * 
     * @return
     */
    /*
     * int selectCount(Map<String, Object> pmap);
     */

    /**
     * 删除商家
     * 
     * @param storeInfoIds
     * @return
     */
    int delStoreInfoById(Long[] storeInfoIds);

    /**
     * 删除货品
     * 
     * @param paramMap
     * @return
     */
    int deleGoodsInfo(Map<String, Object> paramMap);

    /**
     * 删除商品
     * 
     * @param paramMap
     * @return
     */
    int deleGoods(Map<String, Object> paramMap);

    /**
     * 修改商家扣点
     * 
     * @param paramMap
     * @return
     */
    Integer updatePayMent(Map<String, Object> paramMap);

    /**
     * 查询某商家的积分
     * 
     * @param storeId
     * @return
     */
    StoreInfo queryStorePointByThirdId(Long storeId);

    /**
     * 查询某商家的账户余额
     * 
     * @param storeId
     * @return
     */
    StoreInfo queryStoreBalanceByThirdId(Long storeId);

    /**
     * 减积分的动作
     * 
     * @param paramMap
     * @return
     */
    int upStorePointByThirdId(Map<String, Object> paramMap);

    /**
     * 扣违约金的动作
     * 
     * @param paramMap
     * @return
     */
    int upStoreBalanceByThirdId(Map<String, Object> paramMap);

    /**
     * 修改商铺期限
     * 
     * @param param
     * @return
     */
    int updateStoreValidTime(Map<String, Object> param);

    /**
     * 查询第三方店铺关闭时间判断是否开启
     * 
     * @param thirdId
     * @return
     */
    int selectStoreTimeByThirdId(Long thirdId);

    /**
     * 删除权限
     * 
     * @param managerId
     * @return
     */
    int delmanagerauthority(Long managerId);

}
