/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.fastdfs.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.dao.FastDFSInfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-FastDFS设置信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日下午2:51:21
 */
@Repository("FastDFSInfoMapper")
public class FastDFSInfoMapperImpl extends BasicSqlSupport implements FastDFSInfoMapper {

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#deleteByPrimaryKey(java.lang .Long)
     */
    @Override
    public int deleteByPrimaryKey(Long fastdfsId) {

        return this.delete("com.ningpai.system.dao.FastDFSInfoMapper.deleteByPrimaryKey", fastdfsId);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#insert(com.ningpai.system.bean .FastDFSInfo)
     */
    @Override
    public int insert(FastDFSInfo record) {

        return this.insert("com.ningpai.system.dao.FastDFSInfoMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#insertSelective(com.ningpai. system.bean.FastDFSInfo)
     */
    @Override
    public int insertSelective(FastDFSInfo record) {

        return this.insert("com.ningpai.system.dao.FastDFSInfoMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#selectByPrimaryKey(java.lang .Long)
     */
    @Override
    public FastDFSInfo selectByPrimaryKey(Long fastdfsId) {

        return this.selectOne("com.ningpai.system.dao.FastDFSInfoMapper.selectByPrimaryKey", fastdfsId);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#updateByPrimaryKeySelective( com.ningpai.system.bean.FastDFSInfo)
     */
    @Override
    public int updateByPrimaryKeySelective(FastDFSInfo record) {

        return this.update("com.ningpai.system.dao.FastDFSInfoMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#updateByPrimaryKey(com.ningpai .system.bean.FastDFSInfo)
     */
    @Override
    public int updateByPrimaryKey(FastDFSInfo record) {

        return this.update("com.ningpai.system.dao.FastDFSInfoMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.FastDFSInfoMapper#selectAllFastDFS()
     */
    @Override
    public List<FastDFSInfo> selectAllFastDFS() {

        return this.selectList("com.ningpai.system.dao.FastDFSInfoMapper.selectAllFastDFS");
    }

}
