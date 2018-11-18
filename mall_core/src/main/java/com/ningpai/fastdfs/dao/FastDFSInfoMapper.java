package com.ningpai.fastdfs.dao;

import java.util.List;

import com.ningpai.fastdfs.bean.FastDFSInfo;

/**
 * DAO-FastDFS设置信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日上午11:43:49
 */
public interface FastDFSInfoMapper {
    /**
     * 根据ID删除
     * 
     * @param fastdfsId fastdfs的ID
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long fastdfsId);

    /**
     * 添加FastDFSInfo
     * 
     * @param record FastDFSInfo参数信息
     * @return 添加结果
     */
    int insert(FastDFSInfo record);

    /**
     * 添加-字段可选
     * 
     * @param record FastDFSInfo参数信息
     * @return 添加结果
     */
    int insertSelective(FastDFSInfo record);

    /**
     * 根据ID查询
     * 
     * @param fastdfsId FastDFS 记录ID
     * @return FastDFSInfo信息
     */
    FastDFSInfo selectByPrimaryKey(Long fastdfsId);

    /**
     * 修改-字段
     * 
     * @param record FastDFSInfo参数信息
     * @return 修改结果
     */
    int updateByPrimaryKeySelective(FastDFSInfo record);

    /**
     * 修改
     * 
     * @param record FastDFSInfo参数信息
     * @return 修改结果
     */
    int updateByPrimaryKey(FastDFSInfo record);

    /**
     * 查询所有
     * 
     * @return 所有FastDFSInfo信息
     */
    List<FastDFSInfo> selectAllFastDFS();
}
