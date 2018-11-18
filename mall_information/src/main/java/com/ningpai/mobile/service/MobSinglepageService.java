/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.service;

import com.ningpai.mobile.bean.MobSinglepage;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 移动版单页服务层接口
 * 
 * @author zhangsl
 * @since 2014年11月21日 下午5:52:52
 * @version 0.0.1
 */
public interface MobSinglepageService {

    /**
     * 条件分页查询移动版单页信息
     * 
     * @param pageBean
     *            分页参数
     * @param selectBean
     *            条件查询参数
     * @return
     */
    PageBean queryMobByPage(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加移动版单页信息
     * 
     * @param record
     * @return
     */
    int insertSelective(MobSinglepage record);

    /**
     * 根据主键查询移动版单页信息
     * 
     * @param singlepageId
     * @return
     */
    MobSinglepage selectByPrimaryKey(Long singlepageId);

    /**
     * 根据主键修改移动版单页信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobSinglepage record);

    /**
     * 逻辑删除 根据主键ID修改delflag的状态 0：未删除 1：已删除
     * 
     * @param singlepageId
     * @return
     */
    int updatedelstatus(Long singlepageId);

    /**
     * 根据MarkId查询符合条件的总条数
     * 
     * @param markId
     * @return
     */
    int queryCountByMarkId(Long markId);
}
