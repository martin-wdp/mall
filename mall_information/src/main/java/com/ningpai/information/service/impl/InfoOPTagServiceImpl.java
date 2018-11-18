/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.dao.InfoOPTagMapper;
import com.ningpai.information.dao.InformationOnePageMapper;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.util.MyLogger;

/**
 * SERVICE实现类-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日下午1:44:28
 */
@Service("InfoOPTagService")
public class InfoOPTagServiceImpl implements InfoOPTagService {

    /** 单页标签DAO接口 */
    private InfoOPTagMapper infoOPTagMapper;

    /** 资讯单页DAO */
    @Resource(name = "InformationOnePageMapper")
    private InformationOnePageMapper infoOPMapper;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoOPTagServiceImpl.class);

    /**
     * 根据主键删除
     *
     * @param infoOPTagId
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#deleteInfoOPTagById(java.lang.Long)
     */
    @Override
    public int deleteInfoOPTagById(Long infoOPTagId) {
        int n = 0;
        try {
            /** 调用dao层修改方法 */
            infoOPMapper.updateTagByTagId(infoOPTagId);
            /** 根据infoOpTagId查询数据并赋值到INfoOpTag对象中 */
            InfoOPTag infoOPTag = this.infoOPTagMapper.selectByPrimaryKey(infoOPTagId);
            /** 设置删除标记为1 */
            infoOPTag.setDelflag("1");
            /** 设置更新时间为当前时间 */
            infoOPTag.setUpdateDate(new Date());
            /** 执行修改方法 */
            n = infoOPTagMapper.updateByPrimaryKeySelective(infoOPTag);
        } catch (Exception e) {
            LOGGER.error("删除单页标签异常：",e);
        }
        /** 返回结果 */
        return n;
    }

    /**
     * 添加
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#createInfoOPTag(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int createInfoOPTag(InfoOPTag record) {
        /** 获取当前时间 */
        Date date = new Date();
        /** 设置删除标记为0 */
        record.setDelflag("0");
        /** 设置创建时间为当前时间 */
        record.setCreateDate(date);
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(date);
        /** 执行添加方法并返回结果 */
        return infoOPTagMapper.insertSelective(record);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#updateInfoOPTag(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int updateInfoOPTag(InfoOPTag record) {
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 执行修改方法并返回结果 */
        return infoOPTagMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据模板ID查询
     *
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#findAllInfoOPTag(java.lang.String)
     */
    @Override
    public List<InfoOPTag> findAllInfoOPTag(String tempId) {
        /** 执行查询方法并返回结果 */
        return infoOPTagMapper.selectAll(tempId);
    }

    /**
     * 分页查询
     * 
     * @param pb
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#findAllTagPage(com.ningpai.util.PageBean)
     */
    @Override
    public PageBean findAllTagPage(PageBean pb) {
        /** 设置pageBean的行数 */
        pb.setRows(infoOPTagMapper.findAllPagecount());
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        /** 设置开始行数 */
        map.put("startRowNum", pb.getStartRowNum());
        /** 设置结束行数 */
        map.put("endRowNum", pb.getEndRowNum());
        /** 设置pageBean的集合数据 */
        pb.setList(infoOPTagMapper.findAllPage(map));
        /** 返回结果 */
        return pb;
    }

    /**
     * 查询所有
     *
     * @return
     * @see com.ningpai.information.service.InfoOPTagService#findAllTag()
     */
    @Override
    public List<InfoOPTag> findAllTag() {
        /** 查询所有 */
        return infoOPTagMapper.findAll();
    }

    public InfoOPTagMapper getInfoOPTagMapper() {
        return infoOPTagMapper;
    }

    @Resource(name = "InfoOPTagMapper")
    public void setInfoOPTagMapper(InfoOPTagMapper infoOPTagMapper) {
        this.infoOPTagMapper = infoOPTagMapper;
    }
}
