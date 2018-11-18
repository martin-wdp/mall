/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.information.bean.InforSubject;
import com.ningpai.information.dao.InforSubjectMapper;
import com.ningpai.information.service.InforSubjectServcie;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-资讯专题
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月24日上午10:43:38
 */
@Service("InforSubjectServcie")
public class InforSubjectServcieImpl implements InforSubjectServcie {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(InforSubjectServcieImpl.class);

    /** DAO-资讯专题 */
    @Resource(name = "InforSubjectMapper")
    private InforSubjectMapper subjectMapper;

    /**
     * 根据主键删除
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#deleteSubject(java.lang.Long)
     */
    @Override
    public int deleteSubject(Long subjectId) {
        /** 执行删除方法并返回结果 */
        return this.subjectMapper.deleteByPrimaryKey(subjectId);
    }

    /**
     * 根据主键删除
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#saveSubject(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int saveSubject(InforSubject record) {
        /** 获取当前时间 */
        Date date = new Date();
        /** 设置创建时间为当前时间 */
        record.setCreateDate(date);
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(date);
        /** 执行添加方法并返回结果 */
        return this.subjectMapper.insertSelective(record);
    }

    /**
     * 根据主键查询
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#getSubject(java.lang.Long)
     */
    @Override
    public InforSubject getSubject(Long subjectId) {
        /** 执行查询方法并返回结果 */
        return this.subjectMapper.selectByPrimaryKey(subjectId);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#updateSubject(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int updateSubject(InforSubject record) {
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 执行更新方法并返回结果 */
        return this.subjectMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.information.service.InforSubjectServcie#selectSubjectByPageBean(com.ningpai.util.PageBean, java.lang.String)
     */
    @Override
    public PageBean selectSubjectByPageBean(PageBean pb, String title) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("title", title);
            /** 设置PageBean的行数 */
            pb.setRows(this.subjectMapper.selectCountByTitle(title));
            /** 设置开始行数 */
            map.put("startRowNum", pb.getStartRowNum());
            /** 设置PageBean的行数 */
            map.put("endRowNum", pb.getEndRowNum());
            /** 设置PageBean的集合数据 */
            pb.setList(this.subjectMapper.selectByPageBean(map));
        } catch (Exception e) {
            LOGGER.error("",e);
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#batchDeleteSubject(java.lang.Long[])
     */
    @Override
    public int batchDeleteSubject(Long[] ids) {
        /** 定义一个ArrayList集合 */
        List<Long> list = new ArrayList<Long>();
        for (Long long1 : ids) {
            list.add(long1);
        }
        /** 执行批量删除并返回结果 */
        return this.subjectMapper.batchDeleteByList(list);
    }

    /**
     * 查询所有专题
     *
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#selectAllForSite()
     */
    @Override
    public List<InforSubject> selectAllForSite() {
        /** 查询所有专题 */
        return this.subjectMapper.selectAllForSite();
    }

    /**
     * 验证url的唯一性
     *
     * @param url
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#checkByUrl(java.lang.String)
     */
    @Override
    public boolean checkByUrl(String url) {
        /** 根据url查询专题数量 */
        return this.subjectMapper.selectCountByUrl(url) > 0 ? false : true;
    }

    /**
     * 根据ID查看启用的专题
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.service.InforSubjectServcie#selectByIsShowAndId(java.lang.Long)
     */
    @Override
    public InforSubject selectByIsShowAndId(Long subjectId) {
        /** 根据ID查看启用的专题 */
        return subjectMapper.selectByIsShowAndId(subjectId);
    }
}
