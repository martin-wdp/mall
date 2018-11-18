/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.information.dao.InformationOnePageMapper;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 资讯单页SERVICE实现
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:50:35
 * @version
 */
@Service("InformationOnePageService")
public class InformationOnePageServiceImpl implements InformationOnePageService {
    /** 资讯单页DAO */
    private InformationOnePageMapper infoOnePageMapper;

    public InformationOnePageMapper getInfoOnePageMapper() {
        return infoOnePageMapper;
    }

    @Resource(name = "InformationOnePageMapper")
    public void setInfoOnePageMapper(InformationOnePageMapper infoOnePageMapper) {
        this.infoOnePageMapper = infoOnePageMapper;
    }
    /**
     * 根据主键删除
     *
     * @param
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#delInfoOnePage(java.lang.Long,java.lang.Long)
     */
    public int delInfoOnePage(Long infoOPId, Long userId) {
        /**
         * 根据参数调用查询方法
         * 并把查询的结果赋值给InfomationOnePage实体类
         */
        InformationOnePage infoOnePage = infoOnePageMapper.selectByPrimaryKey(infoOPId);
        /**设置删除标记为1*/
        infoOnePage.setDelflag("1");
        /**设置修改者id*/
        infoOnePage.setUpdateUserId(userId);
        /**设置修改时间为当前时间*/
        infoOnePage.setUpdateDate(new Date());
        /**执行修改方法并返回结果*/
        return infoOnePageMapper.updateByPrimaryKeySelective(infoOnePage);
    }
    /**
     * 批量删除资讯单页
     *
     * @param ids
     *            资讯单页编号数组
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#batchDelInfoOnePage(java.lang.Long[],java.lang.Long)
     */
    public void batchDelInfoOnePage(Long[] ids, Long userId) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                /** 根据主键遍历删除 */
                delInfoOnePage(ids[i], userId);
            }
        }
    }
    /**
     * 添加资讯单页
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#saveInfoOnePage(com.ningpai.information.bean.InformationOnePage)
     */
    public int saveInfoOnePage(InformationOnePage record) {
        /** 设置删除标记为0 */
        record.setDelflag("0");
        /** 获取当前时间*/
        Date date = new Date();
        /** 设置创建时间为当前时间*/
        record.setCreateDate(date);
        /** 设置修改时间为当前时间*/
        record.setUpdateDate(date);
        /** 执行添加方法并返回结果*/
        return infoOnePageMapper.insert(record);
    }
    /**
     * 更新资讯单页
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#updateInfoOnePage(com.ningpai.information.bean.InformationOnePage)
     */
    public int updateInfoOnePage(InformationOnePage record) {
        /** 设置修改时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 执行更新方法并返回结果 */
        return infoOnePageMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 根据主键查询
     *
     * @param
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#getInfoOnePageByID(java.lang.Long)
     */
    public InformationOnePage getInfoOnePageByID(Long infoOPId) {
        /** 执行查询方法并返回结果 */
        return infoOnePageMapper.selectByPrimaryKey(infoOPId);
    }
    /**
     * 根据分页参数查询资讯单页分页数据
     *
     * @param pb
     * @param selectBean
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#queryInfoOnePageByPageBean(com.ningpai.util.PageBean,com.ningpai.util.SelectBean)
     */
    public PageBean queryInfoOnePageByPageBean(PageBean pb, SelectBean selectBean) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("condition", selectBean.getCondition());
            map.put("searchText", selectBean.getSearchText());
            /** 查询数据的总行数并设置到PageBean中 */
            pb.setRows(this.infoOnePageMapper.queryTotalCount(map));
            /** 设置开始行数 */
            map.put("startRowNum", pb.getStartRowNum());
            /** 设置结束行数 */
            map.put("endRowNum", pb.getEndRowNum());
            /** 根据分页参数查询资讯单页列表 */
            pb.setList(this.infoOnePageMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        /** 返回结果 */
        return pb;
    }

    /**
     * 根据单页标题查询单页数量判断标题是否存在
     *
     * @param title
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#checkAddInfoOPByTitle(java.lang.String)
     */
    @Override
    public boolean checkAddInfoOPByTitle(String title) {
        /** 调用查询方法并返回结果 */
        return infoOnePageMapper.selectInfoOPCountByTitle(title) > 0 ? false : true;
    }

    /**
     * 根据单页标题查询单页数量判断标题是否存在<br/>
     * 根据单页ID查询出文章标题，判断老标题和新标题是否一样<br>
     * 如果一样直接返回true，不一样查询数量判断是否存在
     *
     * @param title
     * @param infoOPId
     *            单页ID
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#checkAddInfoOPByTitle(java.lang.String, java.lang.Long)
     */
    @Override
    public boolean checkAddInfoOPByTitle(String title, Long infoOPId) {
        /** 根据参数调用查询方法，并把结果赋值给InfomationOnePage实体类 */
        InformationOnePage infoOP = infoOnePageMapper.selectByPrimaryKey(infoOPId);
        /** 判断infoOp是否为null */
        if (null != infoOP) {
            if (infoOP.getTitle().equals(title)) {
                return true;
            } else {
                /** 如果查询结果大于0就返回false 否则就返回true */
                return infoOnePageMapper.selectInfoOPCountByTitle(title) > 0 ? false : true;
            }
        } else {
            return false;
        }
    }

    /**
     * 根据模板ID和标签ID查询单页
     *
     * @param tempId
     *            模板ID
     * @param infoOPTagId
     *            标签ID
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#selectInfoOPByTempAndTag(java.lang.Long,java.lang.Long)
     */
    @Override
    public List<InformationOnePage> selectInfoOPByTempAndTag(Long tempId, Long infoOPTagId) {
        /** 定义一个HashMao集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("infoTempId", tempId);
        map.put("infoOPTagId", infoOPTagId);
        /** 执行查询方法并返回结果 */
        return infoOnePageMapper.selectByTempTag(map);
    }

    /**
     * 根据单页标签查询单页数量，判断是否可删除标签
     *
     * @param infoOPTagId
     * @return
     * @see com.ningpai.information.service.InformationOnePageService#selectInfoOPCountByTag(java.lang.Long)
     */
    @Override
    public Integer selectInfoOPCountByTag(Long infoOPTagId) {
        /** 执行查询方法并返回结果 */
        return infoOnePageMapper.selectInfoOPCountByTag(infoOPTagId);
    }
}
