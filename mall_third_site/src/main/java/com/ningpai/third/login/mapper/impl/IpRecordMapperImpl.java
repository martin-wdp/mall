/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.login.mapper.impl;

import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.login.bean.IpRecord;
import com.ningpai.third.login.mapper.IpRecordMapper;

/**
 * <p>IP详细信息实现接口</p>
 * @see com.ningpai.third.login.mapper.IpRecordMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 上午10:58:57
 * @version 0.0.1
 */
@Repository("ipRecordMapper")
public class IpRecordMapperImpl extends BasicSqlSupport implements IpRecordMapper {


    /**
     * 根据编号删除IP信息
     * @param ipid IP编号
     * @return
     */
    public int deleteByPrimaryKey(Long ipid) {
        return 0;
    }

    /**
     * 新增一组IP信息
     * @param record
     * @return
     */
    public int insert(IpRecord record) {
        return 0;
    }

    /**
     * 新增一组IP信息
     * @param record
     * @return
     */
    public int insertSelective(IpRecord record) {
        return this.insert("com.ningpai.third.login.mapper.IpRecordMapper.insertSelective", record);
    }

    /**
     * 根据id获取一组IP对象详细信息
     * @param ipid
     * @return
     */
    public IpRecord selectByPrimaryKey(Long ipid) {
        return null;
    }

    /**
     * 根据ID获取修改一组IP信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(IpRecord record) {
        return this.update("com.ningpai.third.login.mapper.IpRecordMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 根据ID获取修改一组IP信息
     * @param record
     * @return
     */
    public int updateByPrimaryKey(IpRecord record) {
        return 0;
    }

    /**
     * 根据IP获取一组IP对象信息
     * @param ip
     * @return
     */
    public IpRecord selectByIp(String ip) {
        return this.selectOne("com.ningpai.third.login.mapper.IpRecordMapper.selectByIp", ip);
    }

}
