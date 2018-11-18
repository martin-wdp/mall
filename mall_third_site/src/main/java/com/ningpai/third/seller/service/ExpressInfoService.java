/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.third.seller.bean.Express;

/**
 * 物流支持Service
 * 
 * @author Zhouh
 * @since 2014.5.21
 * @version 0.0.1
 */
public interface ExpressInfoService {

    /**
     * 根据商家ID和物流编号 查询单个物流信息
     * @param expCompany
     * @param storeId
     * @return
     */
    Express selectExpressByCom(String expCompany,Long storeId);
    /**
     * 根据店铺查询物流信息
     * 
     * @param express
     *            @<link Long>
     * @return List<Express> 物流列表集合 @<link List> @<link com.ningpai.third.express.Express>
     */
    List<Express> selectByStoreId(Express express);
    /**
     * 根据店铺查询物流信息
     *
     * @param express
     *            @<link Long>
     * @return List<Express> 物流列表集合 @<link List> @<link com.ningpai.third.express.Express>
     */
    List<Express> selectByStoreIds(Express express);

    /**
     * 添加物流相关信息
     * 
     * @param request
     * @param express
     *            <@link Express>
     * @return 添加物流信息
     */
    int insertExpress(HttpServletRequest request, Express express);

    /**
     * 根据店铺查询物流信息
     * 
     * @param storeId
     *            @<link Long>
     * @return 数据条数
     */
    int selectDefaultRows(Long storeId);

    /**
     * 修改物流信息
     * 
     * @param request
     * @param express
     *            @<link Long>
     * @return
     */
    int updateExpress(HttpServletRequest request, Express express);

    /**
     * 修改物流相关信息
     * 
     * @param express
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateByShoreExpId(Express express);

    /**
     * 删除物流 修改标记
     * 
     * @param express
     *            =0 <@link Express>
     * @return 0失败 1成功
     */
    int updateStateBackByShoreExpId(Express express);

    /**
     * 删除物流,修改标记,设置为默认,默认唯一
     * 
     * @param delflag
     *            =1 <@link Express>
     * @return 0失败 1成功
     */
    int deleteByShoreExpIdUpdate(Express delflag);

    /**
     * 根据ID查询单条数据
     * 
     * @param shoreExpId
     *            <@link Long>
     * @return
     */
    Express selectByshoreExpId(Long shoreExpId);

    /**
     * 批量修改操作
     * 
     * @param list
     *            <@link List<Express>>
     * @return
     */
    int updateBatch(List<Express> list);
}
