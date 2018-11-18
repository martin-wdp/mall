package com.ningpai.information.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.information.bean.InforSubject;

/**
 * DAO-资讯专题
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月24日上午10:38:19
 */
public interface InforSubjectMapper {
    /**
     * 根据主键删除
     * 
     * @param subjectId
     * @return
     */
    int deleteByPrimaryKey(Long subjectId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(InforSubject record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(InforSubject record);

    /**
     * 根据主键查询
     * 
     * @param subjectId
     * @return
     */
    InforSubject selectByPrimaryKey(Long subjectId);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(InforSubject record);

    /**
     * 修改-包括内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeyWithBLOBs(InforSubject record);

    /**
     * 修改-不包括内容
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(InforSubject record);

    /**
     * 查询总行数-分页用
     * 
     * @param title
     * @return
     */
    int selectCountByTitle(String title);

    /**
     * 根据分页参数查询
     * 
     * @param map
     * @return
     */
    List<Object> selectByPageBean(Map<String, Object> map);

    /**
     * 批量删除
     * 
     * @param list
     * @return
     */
    int batchDeleteByList(List<Long> list);

    /**
     * 查询所有专题
     * 
     * @return
     */
    List<InforSubject> selectAllForSite();

    /**
     * 根据url查询专题数量
     * 
     * @param url
     * @return
     */
    int selectCountByUrl(String url);

    /**
     * 根据ID查看启用的专题
     * 
     * @param subjectId
     * @return
     */
    InforSubject selectByIsShowAndId(Long subjectId);
}
