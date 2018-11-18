/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service.impl;

import com.ningpai.app.bean.AppServer;
import com.ningpai.app.dao.AppServerMapper;
import com.ningpai.app.service.AppServerService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_server的service的实现类:
 * @author Ningpai-HEHU
 * @date 2015-08-06 09:24:42
 * @version V1.0  
 */
@Service("AppServerService") 
public class AppServerServiceImpl implements AppServerService {

    /**
     * 网站地址服务器DAO
     */
    @Resource(name="AppServerMapper")
    private AppServerMapper appServerMapper;
    /**
    * @see com.ningpai.app.service.AppServerService#delete(java.lang.Long)
    */
    @Override
    public int delete(Long appServerId) {
        return this.appServerMapper.deleteByPrimaryKey(appServerId);
    }

    /**
    * @see com.ningpai.app.service.AppServerService#insert(com.ningpai.app.bean.AppServer)
    */
    @Override
    public int insert(AppServer record) {
        if("1".equals(record.getIsMain())) {
            this.updateNoMainAll(record.getAppServerType());
        }
        return this.appServerMapper.insertSelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppServerService#select(java.lang.Long)
    */
    @Override
    public AppServer select(Long appServerId) {
        return this.appServerMapper.selectByPrimaryKey(appServerId);
    }

    /**
    * @see com.ningpai.app.service.AppServerService#update(com.ningpai.app.bean.AppServer)
    */
    @Override
    public int update(AppServer record) {
        if("1".equals(record.getIsMain())) {
            this.updateNoMainAll(record.getAppServerType());
        }
        return this.appServerMapper.updateByPrimaryKeySelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppServerService#deleteMuilti(java.lang.Long[])
    */
    @Override
    public int deleteMuilti(Long[] appServerIds) {
        return this.appServerMapper.deleteMuilti(appServerIds);
    }

    /**
    * @see com.ningpai.app.service.AppServerService#selectList(com.ningpai.app.bean.AppServer,PageBean pageBean)
    */
    @Override
    public PageBean selectList(AppServer record,PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean",pageBean);
        pageBean.setRows(this.appServerMapper.selectListCount(map));
        pageBean.setList(this.appServerMapper.selectList(map));
        return pageBean;
    }

    /**
     * 查询所有客户端网站地址
     *
     * @param serverType 1Boss，2商家，3前台
     * @return 所有客户端网站地址
     */
    @Override
    public List<AppServer> selectAllServersByType(String serverType) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("appServerType",serverType);
        return appServerMapper.selectByParam(map);
    }

    /**
     * 将所有网站地址标记为“非主机”
     *
     * @param serverType 1Boss，2商家，3前台
     */
    @Override
    public void updateNoMainAll(String serverType) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("appServerType",serverType);
        map.put("isMain","0");
        appServerMapper.updateByParam(map);
    }

    /**
     * 将网址标记为“主机”
     *
     * @param appServerId 网站地址ID
     */
    @Override
    public void updateMain(Long appServerId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("isMain","1");
        map.put("appServerId",appServerId);
        appServerMapper.updateByParam(map);
    }

}