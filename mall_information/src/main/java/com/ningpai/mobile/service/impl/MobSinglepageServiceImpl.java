/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.mobile.bean.MobSinglepage;
import com.ningpai.mobile.dao.MobSinglepageMapper;
import com.ningpai.mobile.service.MobSinglepageService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 移动版单页服务层接口 service层实现类
 *
 * @author zhangsl
 * @since 2014年11月21日 下午5:52:52
 * @version 0.0.1
 */
@Service("MobSinglepageService")
public class MobSinglepageServiceImpl implements MobSinglepageService {

    /** 移动版单页dao层接口 */
    @Resource(name = "MobSinglepageMapper")
    private MobSinglepageMapper mobSinglepageMapper;

    /**
     * 条件分页查询移动版单页信息
     *
     * @param pageBean
     *            分页参数
     * @param selectBean
     *            条件查询参数
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#queryMobByPage(com.ningpai.util.PageBean,
     *      com.ningpai.system.util.SelectBean)
     */
    @Override
    public PageBean queryMobByPage(PageBean pageBean, SelectBean selectBean) {
        /** 定义一个HashMap集合 */
        Map<Object, Object> paraMap = new HashMap<Object, Object>();
        try {
            /** 设置开始行数 */
            paraMap.put("startRowNum", pageBean.getStartRowNum());
            /** 设置结束行数 */
            paraMap.put("endRowNum", pageBean.getEndRowNum());
            paraMap.put("condition", selectBean.getCondition());
            /** 判断传入的参数是否为null，不为null就放进map集合中 */
            if (selectBean.getSearchText() != null) {
                paraMap.put("searchText", selectBean.getSearchText().trim());
            }
            /** 设置PageBean的行数 */
            pageBean.setRows(mobSinglepageMapper.newQueryMobAllCount(paraMap));
            /** 设置Pagebean的集合数据 */
            pageBean.setList(mobSinglepageMapper.newQueryMobByPage(paraMap));
        } finally {
            paraMap = null;
        }
        /** 返回结果 */
        return pageBean;
    }

    /**
     * 添加移动版单页信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#insertSelective(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int insertSelective(MobSinglepage record) {
        /**
         * 根据传入的对象参数来设置对应的数据
         */
        record.setTitle(record.getTitle().trim());
        record.setKeyword(record.getKeyword().trim());
        record.setDescription(record.getDescription().trim());
        record.setContent("");
        record.setDelflag("0");
        record.setCreateDate(new Date());
        record.setUpdateDate(new Date());
        record.setMerchantId(-1L);
        /** 执行方法并返回结果 */
        return mobSinglepageMapper.insertSelective(record);
    }

    /**
     * 根据主键查询移动版单页信息
     *
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobSinglepage selectByPrimaryKey(Long singlepageId) {
        /** 执行方法并返回结果 */
        return mobSinglepageMapper.selectByPrimaryKey(singlepageId);
    }

    /**
     * 根据主键修改移动版单页信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#updateByPrimaryKeySelective
     *      (com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int updateByPrimaryKeySelective(MobSinglepage record) {
        record.setTitle(record.getTitle().trim());
        record.setKeyword(record.getKeyword().trim());
        record.setDescription(record.getDescription().trim());
        record.setUpdateDate(new Date());
        /** 执行方法并返回结果 */
        return mobSinglepageMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 逻辑删除 根据主键ID修改delflag的状态 0：未删除 1：已删除
     *
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#updatedelstatus(java.lang.Long)
     */
    @Override
    public int updatedelstatus(Long singlepageId) {
        /** 执行方法返回结果 */
        return mobSinglepageMapper.updatedelstatus(singlepageId);
    }

    /**
     * 根据MarkId查询符合条件的总条数
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.service.MobSinglepageService#queryCountByMarkId(java.lang.Long)
     */
    @Override
    public int queryCountByMarkId(Long markId) {
        /** 　执行方法返回结果　 */
        return mobSinglepageMapper.queryCountByMarkId(markId);
    }
}
