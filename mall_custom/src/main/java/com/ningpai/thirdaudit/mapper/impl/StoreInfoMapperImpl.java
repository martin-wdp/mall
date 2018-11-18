/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreInfoMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.thirdaudit.mapper.StoreInfoMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午6:06:29
 * @version 0.0.1
 */
@Repository("storeMapper")
public class StoreInfoMapperImpl extends BasicSqlSupport implements StoreInfoMapper {

    /**
     * 设置改店铺是都在店铺街显示以及排序
     *
     * @param setTore
     *            ：排布
     * @param isShow
     *            ：是否显示 author zhanghl
     * @return
     */
    @Override
    public int setStore(Long setTore, String isShow, Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("set_tore", setTore);
        map.put("is_Show", isShow);
        map.put("storeId", storeId);
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.setStore", map);
    }

    /**
     * 查询会员id
     *
     * @param storeId
     * @return
     */
    @Override
    public int findcid(Long storeId) {
        return selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.findcid", storeId);
    }

    /**
     *
     * @param storeId
     * @return
     */
    public StoreInfo selectByStoreId(Long storeId) {
        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectByStoreId", storeId);
    }

    /**
     * 查询需要审核的数量
     *
     * @param storeInfo
     *            查询条件 {@link StoreInfo}
     * @return 总数量 {@link Long}
     */
    @Override
    public Long selectAuditListSize(StoreInfo storeInfo) {
        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectAuditListSize", storeInfo);
    }

    /**
     * 查询需要审核的列表
     *
     * @param paramMap
     *            查询条件 {@link Map}
     * @return 审核列表 {@link List}
     */
    @Override
    public List<Object> selectAuditList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectAuditList", paramMap);
    }

    /**
     * 审核商家信息
     *
     * @param paramMap
     *            商家编号 {@link Map}
     * @return 0修改失败 1修改成功 {@link Integer}
     */
    @Override
    public int updateStore(Map<String, Object> paramMap) {
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.updateStore", paramMap);
    }

    /**
     * 审核商家信息
     *
     * @param storeId
     *            商家编号 {@link Long}
     * @return 0修改失败 1修改成功 {@link Integer}
     */
    @Override
    public int auditBrand(Long storeId) {
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.auditBrand", storeId);
    }

    /**
     * 打回商家信息
     *
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return 0 失败 1成功
     */
    @Override
    public int refuseStore(StoreInfo storeInfo) {
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.refuseStore", storeInfo);
    }

    /**
     * 根据商家编号 查询商家店铺名称 和是否开启商家首页
     *
     * @param storeId
     *            商家编号 {@link Long}
     * @return 商家信息 只包含店铺名称和是否开启首页 {@link StoreInfo}
     */
    @Override
    public StoreInfo selectNameAndIsStoreBySId(Long storeId) {
        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectNameAndIsStoreBySId", storeId);
    }

    /**
     * 删除商家
     *
     * @param storeInfoIds
     * @return
     */
    @Override
    public int delStoreInfoById(Long[] storeInfoIds) {
        //
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.delStoreInfoById", storeInfoIds);
    }

    /**
     * 删除货品
     *
     * @param paramMap
     * @return
     */
    @Override
    public int deleGoodsInfo(Map<String, Object> paramMap) {
        //
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.deleGoodsInfo", paramMap);
    }

    /**
     * 删除商品
     *
     * @param paramMap
     * @return
     */
    @Override
    public int deleGoods(Map<String, Object> paramMap) {
        //
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.deleGoods", paramMap);
    }

    /**
     * 修改商家扣点
     *
     * @param paramMap
     * @return
     */
    @Override
    public Integer updatePayMent(Map<String, Object> paramMap) {

        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.updatePayMent", paramMap);
    }

    /**
     * 查询某商家的积分
     *
     * @param storeId
     * @return
     */
    @Override
    public StoreInfo queryStorePointByThirdId(Long storeId) {

        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.queryStorePointByThirdId", storeId);
    }

    /**
     * 减积分的动作
     *
     * @param paramMap
     * @return
     */
    @Override
    public int upStorePointByThirdId(Map<String, Object> paramMap) {

        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.upStorePointByThirdId", paramMap);
    }

    /**
     * 扣违约金的动作
     *
     * @param paramMap
     * @return
     */
    @Override
    public int upStoreBalanceByThirdId(Map<String, Object> paramMap) {

        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.upStoreBalanceByThirdId", paramMap);
    }

    /**
     * 查询某商家的账户余额
     *
     * @param storeId
     * @return
     */
    @Override
    public StoreInfo queryStoreBalanceByThirdId(Long storeId) {

        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.queryStoreBalanceByThirdId", storeId);
    }

    /**
     * 修改商铺期限
     *
     * @param param
     * @return
     */
    @Override
    public int updateStoreValidTime(Map<String, Object> param) {
        return this.update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.updateStoreValidTime", param);
    }

    /**
     * 查询第三方店铺关闭时间判断是否开启
     *
     * @param thirdId
     * @return
     */
    @Override
    public int selectStoreTimeByThirdId(Long thirdId) {
        return this.selectOne("com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectStoreTimeByThirdId", thirdId);
    }

    /**
     * 删除权限
     *
     * @param managerId
     * @return
     */
    @Override
    public int delmanagerauthority(Long managerId) {
        return this.update("com.ningpai.thirdaudit.mapper.ThirdManagerAuthorityMapper.delmanagerauthority", managerId);
    }

}
