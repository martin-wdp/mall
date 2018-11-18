/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.mapper.impl;

import java.util.List;
import java.util.Map;
import com.ningpai.third.register.bean.Sms;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.bean.ThirdStoreMess;
import com.ningpai.third.seller.mapper.StoreInfoMapper;

/**
 * @see com.ningpai.third.seller.mapper.StoreInfoMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 下午6:06:29
 * @version 0.0.1
 */
@Repository("sotreInfoMapper")
public class StoreInfoMapperImpl extends BasicSqlSupport implements StoreInfoMapper {
    /**
     * 根据会员编号 获取单个商铺信息
     * @param customerId
     *            商家会员编号
     * @return
     */
    @Override
    public StoreInfo selectByEmployeeId(Long customerId) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectByEmployeeId", customerId);
    }

    /**
     * 验证用户名称是否存在
     * @param username 用户名
     * @return
     */

    @Override
    public int checkThirdAuthority(String username) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.checkThirdAuthority",username);
    }

    /**
     * 商家注册验证手机号码
     * @return
     */
    @Override
    public Sms selectSms() {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectSms");
    }

    /**
     * 根据主键删除单个的店铺
     * @param storeId
     * @return
     */
    public int deleteByPrimaryKey(Long storeId) {
        return 0;
    }

    /**
     * 保存店铺信息
     * @param record
     * @return
     */
    public int insert(StoreInfo record) {
        return 0;
    }

    /**
     * 保存店铺信息
     * @param record
     * @return
     */
    public int insertSelective(StoreInfo record) {
        return this.insert("com.ningpai.third.seller.mapper.SotreInfoMapper.insertSelective", record);
    }

    /**
     * 根据商家ID获取店铺信息
     * @param storeId
     * @return
     */
    public StoreInfo selectByPrimaryKey(Long storeId) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectByPrimaryKey", storeId);
    }
    @Override
   public StoreInfo selectByStoreName(String storeName) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectByStoreName", storeName);
    }

    /**
     * 更新单个店铺信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(StoreInfo record) {
        return this.update("com.ningpai.third.seller.mapper.SotreInfoMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 更新单个店铺信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(StoreInfo record) {
        return 0;
    }

    /**
     * 根据会员ID 获取单个的店铺ID
     * @param customerId
     * @return
     */
    public StoreInfo selectByCustomerId(Long customerId) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectByCustomerId", customerId);
    }

    /**
     * 获取模块消息接收方式
     * @param paramMap
     * @return
     */
    public ThirdStoreMess queryStoreMessBySidAndMid(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.third.seller.mapper.ThirdStoreMessMapper.queryStoreMessBySidAndMid", paramMap);
    }

    /**
     * 更新模块消息接收方式
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return
     */
    public int updateStoreMess(ThirdStoreMess mess) {
        return this.update("com.ningpai.third.seller.mapper.ThirdStoreMessMapper.updateStoreMess", mess);
    }

    /**
     * 新增模块消息接收方式
     * @param mess
     *            消息设置 {@link ThirdStoreMess}
     * @return
     */
    public int addStoreMess(ThirdStoreMess mess) {
        return this.insert("com.ningpai.third.seller.mapper.ThirdStoreMessMapper.insertSelective", mess);
    }

    /**
     * 根据会员ID获取单个的店铺ID
     * @param customerId
     *            会员编号 {@link Long}
     * @return
     */

    public StoreInfo selectRefuseInfo(Long customerId) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectRefuseInfo", customerId);
    }

    /**
     * 修改单个的店铺信息
     * @param storeInfo
     *            商家信息 {@link StoreInfo}
     * @return
     */
    @Override
    public int updateRefuseInfo(StoreInfo storeInfo) {
        return this.update("com.ningpai.third.seller.mapper.SotreInfoMapper.updateRefuseInfo", storeInfo);
    }

    /**
     * 查询所有的店铺信息
     * @return
     */
    @Override
    public List<StoreInfo> selectAll() {
        return this.selectList("com.ningpai.third.seller.mapper.SotreInfoMapper.selectAll");
    }

    /**
     * 查询店铺名称是否已使用
     * 第三方后台开通店铺时调用
     *
     * @param storeName 页面传入的店铺名称
     * @return 查询出的数量
     * @author houyichang  2015/9/18
     */
    @Override
    public int queryCountByStoreName(String storeName) {
        return this.selectOne("com.ningpai.third.seller.mapper.SotreInfoMapper.selectCountByStoreName",storeName);
    }

}
