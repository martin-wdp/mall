/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.temp.service;

import com.ningpai.temp.bean.Megawizard;
import com.ningpai.util.PageBean;

/**
 * SERVICE-页面说明
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014年7月24日下午4:10:00
 */
public interface MegawizardService {
    /**
     * 新建页面说明
     * 
     * @param record
     * @return
     */
    int insert(Megawizard record);

    /**
     * 根据模板id分页查询页面说明
     * 
     * @param map
     * @return
     */
    PageBean selectByTempId(PageBean pb, Long tempId);

    /**
     * 根据id修改删除标记
     * 
     * @param id
     * @return
     */
    int updateById(String[] id);

    /**
     * 根据id修改页面说明内容
     * 
     * @param meg
     * @return
     */
    int updateByIdToContent(Megawizard meg);

    /**
     * 根据id查询单个页面说明
     * 
     * @param id
     *            页面说明编号
     * @return
     */
    Megawizard selectById(Long id);

    /**
     * 根据页面类型和模板ID查询单个页面说明
     * 
     * @param typeId
     *            页面类型
     * @param tempId
     *            模板ID
     * @return
     */
    Megawizard selectByType(int typeId, Long tempId);

}
