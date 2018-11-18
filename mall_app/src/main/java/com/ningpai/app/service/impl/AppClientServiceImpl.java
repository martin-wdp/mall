/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.app.service.impl;

import com.ningpai.app.bean.App;
import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.dao.AppMapper;
import com.ningpai.app.service.AppClientService;
import com.ningpai.app.service.AppInstallService;
import com.ningpai.app.service.AppMarketKeyService;
import com.ningpai.app.service.AppServerBundleService;
import com.ningpai.app.util.AppConstant;
import com.ningpai.app.util.HttpRequestUtil;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.util.JsonUtil;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.PropertieUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * np_app的service的实现类:
 * 
 * @author Ningpai-HEHU 2015-06-10 18:21:40
 * @version V1.0
 */
@Service("AppClientService")
public class AppClientServiceImpl implements AppClientService {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(AppClientServiceImpl.class);

    /** AppDao接口 */
    @Resource(name = "AppMapper")
    private AppMapper appMapper;

    /** App安装记录接口 */
    @Resource(name = "AppInstallService")
    private AppInstallService appInstallService;

    /** 应用市场秘钥接口 */
    @Resource(name = "AppMarketKeyService")
    private AppMarketKeyService appMarketKeyService;

    /** HttpRequestService，提供跨站访问服务 */
    @Resource(name = "HttpRequestService")
    private HttpRequestUtil httpRequestUtil;

    /** AppServerBundleService接口，提供模块和服务器地址之间关系的服务 */
    @Resource(name = "AppServerBundleService")
    private AppServerBundleService appServerBundleService;

    /**
     */
    @Override
    public int delete(Long id) {
        return this.appMapper.deleteByPrimaryKey(id);
    }

    /**
     */
    @Override
    public int insert(App record) {
        return this.appMapper.insertSelective(record);
    }

    /**
     */
    @Override
    public App select(Long id) {
        return this.appMapper.selectByPrimaryKey(id);
    }

    /**
     */
    @Override
    public int update(App record) {

        return this.appMapper.updateByPrimaryKeySelective(record);
    }

    /**
     */
    @Override
    public int deleteMuilti(Long[] ids) {
        return this.appMapper.deleteMuilti(ids);
    }

    /**
     */
    @Override
    public PageBean selectList(App record, PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pageBean", pageBean);
        pageBean.setRows(this.appMapper.selectListCount(map));
        pageBean.setList(this.appMapper.selectList(map));
        return pageBean;
    }

    /**
     * app详情，http访问查询获得
     *
     * @param id
     *            主键id
     * @return App信息
     */
    @Override
    public Object queryAppDetail(Long id) {
        Object app = null;
        try {
            Properties properties = PropertieUtil.readPropertiesFile(AppClientServiceImpl.class.getClassLoader().getResourceAsStream(AppConstant.APP_PROP_LOCATION));
            // app市场服务器地址
            String serverUrl = properties.getProperty(AppConstant.APP_SERVER);
            HttpClient client = new HttpClient();
            // 查询本地存储的密钥
            AppMarketKey marketKey = appMarketKeyService.select(1L);
            GetMethod getMethod = new GetMethod(serverUrl + "appdetailforinstall.htm?id=" + id + "&appMarketKey=" + marketKey.getAppMarketKey());
            client.executeMethod(getMethod);

            String str = new String(getMethod.getResponseBodyAsString().getBytes("UTF-8"));
            LOGGER.info("str============================" + str);
            app = JsonUtil.getDTO(str, App.class);
            getMethod.releaseConnection();
        } catch (IOException e) {
            LOGGER.error("应用查询失败", e);
        }
        return app;
    }
    /**
     * app详情，http访问查询获得
     *
     * @param id
     *            主键id
     * @return App信息
     */
    @Override
    public Object queryAppDetailForShow(Long id) {
        App app = (App) queryAppDetail(id);
        app.setLoadSql(null);
        app.setStartSql(null);
        app.setStopSql(null);
        app.setUnloadSql(null);
        return app;
    }
    /**
     * 从应用市场服务器获取秘钥，并保存
     *
     * @param basicSet
     *            网站基本信息
     * @return 获取到的秘钥信息
     */
    @Override
    public synchronized AppMarketKey getAppMarketKeyFromServer(BasicSet basicSet) {
        Properties properties = PropertieUtil.readPropertiesFile(AppClientServiceImpl.class.getClassLoader().getResourceAsStream(AppConstant.APP_PROP_LOCATION));
        // App市场服务器地址，可以是域名
        String serverUrl = properties.getProperty(AppConstant.APP_SERVER);
        AppMarketKey marketKey = new AppMarketKey();
        try {
            // 发送请求，获取秘钥。
            Map<String, Object> resultMap = httpRequestUtil.sendPost(serverUrl + "getAppMarketKey.htm", MapUtil.getParamsMap(basicSet));
            if (resultMap == null) {
                return null;
            }
            Object key = resultMap.get("appMarketKey");
            if (key == null) {
                return null;
            }
            // 得到秘钥
            marketKey.setId(1L);
            marketKey.setAppMarketKey(key.toString());
            marketKey.setCreateTime(new Date());
            // 默认有效期30天，超期会重新获取
            marketKey.setEndTime(new Date(Long.parseLong(resultMap.get("appMarketKeyEndTime").toString())));
            // 先把原来的记录删掉，再插入一条id为1的记录。这样就不用判断是更新还是新加的了。
            appMarketKeyService.delete(1L);
            // 保存获取的秘钥信息
            appMarketKeyService.insert(marketKey);
        } catch (IOException e) {
            LOGGER.error("请求访问失败", e);
        }
        return marketKey;
    }

    /**
     * 查询可以更新的应用
     *
     * @param appVersions appId-appVersion
     * @return 可以更新的应用的id
     */
    @Override
    public List<Long> queryUpdatableAppIds(Map<String, Object> appVersions) {
        Map<String,Object> appIdsMap = null;
        Map<String,Object> params = new HashMap<String,Object>();
        try {
            Properties properties = PropertieUtil.readPropertiesFile(AppClientServiceImpl.class.getClassLoader().getResourceAsStream(AppConstant.APP_PROP_LOCATION));
            // app市场服务器地址
            String serverUrl = properties.getProperty(AppConstant.APP_SERVER);
            // 查询本地存储的密钥
            AppMarketKey marketKey = appMarketKeyService.select(1L);
            params.put("appMarketKey",marketKey);
            params.put("versions", JsonUtil.getJSONString(appVersions));
            appIdsMap = httpRequestUtil.sendPost(serverUrl + "queryupdatableappids.htm",params );
        } catch (IOException e) {
            LOGGER.error("应用查询失败", e);
        }
        return appIdsMap==null||appIdsMap.get("appIds")==null?null:(List<Long>)appIdsMap.get("appIds");
    }

}