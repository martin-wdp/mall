/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InforSubject;
import com.ningpai.util.PageBean;

/**
 * SERVICE-资讯专题
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月24日上午10:37:04
 */
public interface InforSubjectServcie {
    /**
     * 根据主键删除
     * 
     * @param subjectId
     * @return
     */
    int deleteSubject(Long subjectId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveSubject(InforSubject record);

    /**
     * 根据主键查询
     * 
     * @param subjectId
     * @return
     */
    InforSubject getSubject(Long subjectId);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateSubject(InforSubject record);

    /**
     * 根据分页参数查询
     * 
     * @param pb
     * @param title
     * @return
     */
    PageBean selectSubjectByPageBean(PageBean pb, String title);

    /**
     * 批量删除
     * 
     * @param ids
     * @return
     */
    int batchDeleteSubject(Long[] ids);

    /**
     * 查询所有专题
     * 
     * @return
     */
    List<InforSubject> selectAllForSite();

    /**
     * 验证url的唯一性
     * 
     * @param url
     * @return
     */
    boolean checkByUrl(String url);

    /**
     * 根据ID查看启用的专题
     * 
     * @param subjectId
     * @return
     */
    InforSubject selectByIsShowAndId(Long subjectId);
}
