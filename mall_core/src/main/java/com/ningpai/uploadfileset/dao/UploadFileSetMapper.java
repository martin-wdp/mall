package com.ningpai.uploadfileset.dao;

import com.ningpai.uploadfileset.bean.UploadFileSet;

/**
 * DAO-上传文件设置类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月16日下午4:10:50
 */
public interface UploadFileSetMapper {
    /**
     * 根据ID删除
     * 
     * @param uploadfilesetId
     * @return
     */
    int deleteByPrimaryKey(Long uploadfilesetId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(UploadFileSet record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(UploadFileSet record);

    /**
     * 根据ID查询
     * 
     * @param uploadfilesetId
     * @return
     */
    UploadFileSet selectByPrimaryKey(Long uploadfilesetId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UploadFileSet record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(UploadFileSet record);

    /**
     * 查询当前
     * 
     * @return
     */
    UploadFileSet selectUploadfileset();
}
