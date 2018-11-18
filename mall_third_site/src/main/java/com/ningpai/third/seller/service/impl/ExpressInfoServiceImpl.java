/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.seller.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.ningpai.third.seller.bean.Express;
import com.ningpai.third.seller.mapper.ExpressInfoMapper;
import com.ningpai.third.seller.service.ExpressInfoService;

/**
 * @see com.ningpai.third.seller.service.ExpressInfoService
 * @author Zhouh
 * @since 2014.5.21
 * @version 0.0.1
 * 
 */
@Service("expressInfoService")
public class ExpressInfoServiceImpl implements ExpressInfoService {
    /**
     * 物流信息接口
     */

    private ExpressInfoMapper expressInfoMapper;

    @Override
    public Express selectExpressByCom(String expCompany, Long storeId) {
        return expressInfoMapper.selectExpressByCom(expCompany,storeId);
    }

    /**
     * 查询所有的物流信息
     * @param express
     *            @<link Long>
     * @return
     */
    public List<Express> selectByStoreId(Express express) {
        return expressInfoMapper.selectByStoreId(express);
    }


    /**
     * 查询所有的物流信息
     * @param express
     *            @<link Long>
     * @return
     */
    public List<Express> selectByStoreIds(Express express) {
        return expressInfoMapper.selectByStoreIds(express);
    }

    /**
     * 插入物流信息
     * @param request
     * @param express
     *            <@link Express>
     * @return
     */
    public int insertExpress(HttpServletRequest request, Express express) {
        express.setStoreId((Long) request.getSession().getAttribute("thirdId"));
        return expressInfoMapper.insertExpress(express);
    }

    /**
     * 根据主键杀出物流信息
     * @param delflag
     *            =1 <@link Express>
     * @return
     */
    public int deleteByShoreExpIdUpdate(Express delflag) {
        return expressInfoMapper.deleteByShoreExpIdUpdate(delflag);
    }

    /**
     * 根据主键更新物流信息
     * @param express
     *            =1 <@link Express>
     * @return
     */
    public int updateStateByShoreExpId(Express express) {
        return this.expressInfoMapper.updateStateByShoreExpId(express);
    }

    /**
     * 更新物流信息
     * @param express
     *            =0 <@link Express>
     * @return
     */
    public int updateStateBackByShoreExpId(Express express) {
        return this.expressInfoMapper.updateStateBackByShoreExpId(express);
    }

    /**
     * 根据主键获取单个的物流信息
     * @param storeId
     *            @<link Long>
     * @return
     */
    public int selectDefaultRows(Long storeId) {
        return this.expressInfoMapper.selectDefaultRows(storeId);
    }

    /**
     * 更新物流信息
     * @param request
     * @param express
     *            @<link Long>
     * @return
     */
    public int updateExpress(HttpServletRequest request, Express express) {
        express.setStoreId((Long) request.getSession().getAttribute("thirdId"));
        return this.expressInfoMapper.updateExpress(express);
    }

    /**
     * 根据主键获取单个物流信息
     * @param shoreExpId
     *            <@link Long>
     * @return
     */
    public Express selectByshoreExpId(Long shoreExpId) {
        return this.expressInfoMapper.selectByshoreExpId(shoreExpId);
    }

    /**
     * 批量修改操作
     * @param list
     *            <@link List<Express>>
     * @return
     */
    public int updateBatch(List<Express> list) {
        return this.expressInfoMapper.updateBatch(list);
    }



    public ExpressInfoMapper getExpressInfoMapper() {
        return expressInfoMapper;
    }

    @Resource(name = "expressInfoMapper")
    public void setExpressInfoMapper(ExpressInfoMapper expressInfoMapper) {
        this.expressInfoMapper = expressInfoMapper;
    }

}
