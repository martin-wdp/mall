/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service.impl;

import com.ningpai.app.bean.AppServerBundle;
import com.ningpai.app.dao.AppServerBundleMapper;
import com.ningpai.app.service.AppServerBundleService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Description: np_app_server_bundle的service的实现类:
 * @author Ningpai-Heh
 * @date 2015-08-18 13:14:42
 * @version V1.0  
 */
@Service("AppServerBundleService") 
public class AppServerBundleServiceImpl implements AppServerBundleService {

    /**
     * 应用市场服务器-bundle关系DAO
     */
    @Resource(name="AppServerBundleMapper")
    private AppServerBundleMapper appServerBundleMapper;
    /**
    * @see com.ningpai.app.service.AppServerBundleService#delete(java.lang.Long)
    */
    @Override
    public int delete(Long serverBundleId) {
        return this.appServerBundleMapper.deleteByPrimaryKey(serverBundleId);
    }

    /**
    * @see com.ningpai.app.service.AppServerBundleService#insert(com.ningpai.app.bean.AppServerBundle)
    */
    @Override
    public int insert(AppServerBundle record) {
        return this.appServerBundleMapper.insertSelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppServerBundleService#select(java.lang.Long)
    */
    @Override
    public AppServerBundle select(Long serverBundleId) {
        return this.appServerBundleMapper.selectByPrimaryKey(serverBundleId);
    }

    /**
    * @see com.ningpai.app.service.AppServerBundleService#update(com.ningpai.app.bean.AppServerBundle)
    */
    @Override
    public int update(AppServerBundle record) {

        return this.appServerBundleMapper.updateByPrimaryKeySelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppServerBundleService#deleteMuilti(java.lang.Long[])
    */
    @Override
    public int deleteMuilti(Long[] serverBundleIds) {
        return this.appServerBundleMapper.deleteMuilti(serverBundleIds);
    }

    /**
    * @see com.ningpai.app.service.AppServerBundleService#selectList(com.ningpai.app.bean.AppServerBundle,PageBean pageBean)
    */
    @Override
    public PageBean selectList(AppServerBundle record,PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean",pageBean);
        pageBean.setRows(this.appServerBundleMapper.selectListCount(map));
        pageBean.setList(this.appServerBundleMapper.selectList(map));
        return pageBean;
    }

    /**
     * 根据应用ID和服务器ID查询
     *
     * @param id       appID
     * @param serverId 服务器ID
     * @return 一条记录
     */
    @Override
    public AppServerBundle selectByAppIdAndServerId(Long id, Long serverId) {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("appId",id);
        map.put("serverId",serverId);
        return appServerBundleMapper.selectByParam(map);
    }

}