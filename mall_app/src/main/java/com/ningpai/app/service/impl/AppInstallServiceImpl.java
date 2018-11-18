/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service.impl;

import com.ningpai.app.bean.AppInstall;
import com.ningpai.app.dao.AppInstallMapper;
import com.ningpai.app.service.AppClientService;
import com.ningpai.app.service.AppInstallService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_install的service的实现类:
 * @author Ningpai-HEHU
 * @date 2015-06-12 11:40:17
 * @version V1.0  
 */
@Service("AppInstallService") 
public class AppInstallServiceImpl implements AppInstallService {
    /**
     * 应用安装记录dao
     */
    @Resource(name="AppInstallMapper")
    private AppInstallMapper appInstallMapper;

    @Resource(name="AppClientService")
    private AppClientService appClientService;
    /*
    * @see com.ningpai.app.service.AppInstallService#delete(java.lang.Long)
    */
    @Override
    public int delete(Long installId) {
        return this.appInstallMapper.deleteByPrimaryKey(installId);
    }

    /*
    * @see com.ningpai.app.service.AppInstallService#insert(com.ningpai.app.bean.AppInstall)
    */
    @Override
    public int insert(AppInstall record) {
        return this.appInstallMapper.insertSelective(record);
    }

    /*
    * @see com.ningpai.app.service.AppInstallService#select(java.lang.Long)
    */
    @Override
    public AppInstall select(Long installId) {
        return this.appInstallMapper.selectByPrimaryKey(installId);
    }

    /*
    * @see com.ningpai.app.service.AppInstallService#update(com.ningpai.app.bean.AppInstall)
    */
    @Override
    public int update(AppInstall record) {

        return this.appInstallMapper.updateByPrimaryKeySelective(record);
    }

    /*
    * @see com.ningpai.app.service.AppInstallService#deleteMuilti(java.lang.Long[])
    */
    @Override
    public int deleteMuilti(Long[] installIds) {
        return this.appInstallMapper.deleteMuilti(installIds);
    }

    /*
    * @see com.ningpai.app.service.AppInstallService#selectList(com.ningpai.app.bean.AppInstall,PageBean pageBean)
    */
    @Override
    public PageBean selectList(AppInstall record,PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean",pageBean);
        pageBean.setRows(this.appInstallMapper.selectListCount(map));
        pageBean.setList(this.appInstallMapper.selectList(map));
        return pageBean;
    }

    @Override
    public AppInstall selectByAppId(Long appId) {
        return appInstallMapper.selectByAppId(appId);
    }



    @Override
    public List<AppInstall> queryAllInstallApps() {
        //先查询所有安装记录
        List<AppInstall> installApps = appInstallMapper.queryAllInstallApps();
        Map<String,Object> appVersions = new HashMap<String,Object>();
        //如果应用的更新状态是0，就去检查是否有新版本
        for(AppInstall app:installApps) {
            if("0".equals(app.getUpdateFlag())) {
                appVersions.put(app.getAppId().longValue()+"",app.getInstallVersion());
            }
        }
        //服务器端返回可更新的应用id
        List<Long> appIds = appClientService.queryUpdatableAppIds(appVersions);
        if(appIds!=null&&appIds.size()>0) {
            //将应用标记为可更新
            appInstallMapper.modifyUpdateFlag(appIds);

            for(AppInstall app:installApps) {
                if(appIds.contains(app.getAppId())) {
                    app.setUpdateFlag("1");
                }
            }
        }

        return installApps;
    }

    /**
     * 将应用的更新标记改为“0”，表示不用更新
     *
     * @param id 应用id
     */
    @Override
    public void modifyAppUpdateFlag(Long id) {
        appInstallMapper.modifyAppUpdateFlag(id);
    }


}