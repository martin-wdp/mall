/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.uploadfileset.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.uploadfileset.bean.UploadFileSet;
import com.ningpai.uploadfileset.dao.UploadFileSetMapper;

/**
 * DAO实现类-上传文件设置类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:13:51
 */
@Repository("UploadFileSetMapper")
public class UploadFileSetMapperImpl extends BasicSqlSupport implements UploadFileSetMapper {

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#deleteByPrimaryKey( java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long uploadfilesetId) {

        return this.delete("com.ningpai.uploadfileset.dao.UploadFileSetMapper.deleteByPrimaryKey", uploadfilesetId);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#insert(com.ningpai. uploadfileset.bean.UploadFileSet)
     */
    @Override
    public int insert(UploadFileSet record) {

        return this.insert("com.ningpai.uploadfileset.dao.UploadFileSetMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#insertSelective(com .ningpai.uploadfileset.bean.UploadFileSet)
     */
    @Override
    public int insertSelective(UploadFileSet record) {

        return this.insert("com.ningpai.uploadfileset.dao.UploadFileSetMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#selectByPrimaryKey( java.lang.Long)
     */
    @Override
    public UploadFileSet selectByPrimaryKey(Long uploadfilesetId) {

        return this.selectOne("com.ningpai.uploadfileset.dao.UploadFileSetMapper.selectByPrimaryKey", uploadfilesetId);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#updateByPrimaryKeySelective (com.ningpai.uploadfileset.bean.UploadFileSet)
     */
    @Override
    public int updateByPrimaryKeySelective(UploadFileSet record) {

        return this.update("com.ningpai.uploadfileset.dao.UploadFileSetMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#updateByPrimaryKey( com.ningpai.uploadfileset.bean.UploadFileSet)
     */
    @Override
    public int updateByPrimaryKey(UploadFileSet record) {

        return this.update("com.ningpai.uploadfileset.dao.UploadFileSetMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.uploadfileset.dao.UploadFileSetMapper#selectAllUploadfileset ()
     */
    @Override
    public UploadFileSet selectUploadfileset() {

        return this.selectOne("com.ningpai.uploadfileset.dao.UploadFileSetMapper.selectUploadfileset");
    }

}
