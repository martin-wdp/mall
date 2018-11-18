package com.ningpai.cloudshop.dao.impl;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 云销授权用户信息mapper实现类
 * Created by liangck on 15/6/25.
 */
@Repository("CloudshopAuthorInfoMapper")
public class CloudshopAuthorInfoMapperImpl extends BasicSqlSupport implements CloudshopAuthorInfoMapper {


    /**
     * 保存属性不为空的记录
     *
     * @param record
     */
    @Override
    public int insertSelective(CloudshopAuthorInfo record) {
        return insert("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.insertSelective",record);
    }

    /**
     * 根据主键查询记录
     *
     * @param cloudshopAuthorInfoId
     */
    @Override
    public CloudshopAuthorInfo selectByPrimaryKey(Integer cloudshopAuthorInfoId) {
        return selectOne("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.selectByPrimaryKey",cloudshopAuthorInfoId);
    }

    /**
     * 根据主键更新属性不为空的记录
     *
     * @param record
     */
    @Override
    @CacheEvict(value = "authorinfo",key = "'queryAuthoredInfo'")
    public int updateByPrimaryKeySelective(CloudshopAuthorInfo record) {
        return update("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.updateByPrimaryKeySelective",record);
    }

    /**
     * 查询现有授权信息
     *
     * @return 授权信息
     */
    @Override
    public CloudshopAuthorInfo selectAuthorInfo() {
        List<CloudshopAuthorInfo> infos= selectList("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.selectAuthorInfo");
        return (infos==null||infos.size()<=0)?null: infos.get(0);
    }

    /**
     * 获取accesstoken值
     *
     * @return token值
     */
    @Override
    public String getAccessToken() {
        return selectOne("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.getAccessTokenVal");
    }

    /**
     * 删除用户授权信息
     *
     * @return 受影响条数
     */
    @Override
    public int deleteAuthorInfo() {
        return delete("com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper.deleteAllInfo");
    }
}
