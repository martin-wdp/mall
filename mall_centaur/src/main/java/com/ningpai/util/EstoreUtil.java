/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.ningpai.system.estore.bean.EStore;
import com.ningpai.system.estore.service.EStoreService;

/**
 * 工具类
 * 
 * @author ggn
 *
 */
@Controller
public class EstoreUtil {

    /**
     * 静态E店宝service
     */
    private static EStoreService estoreService;
    /**
     * E店宝service
     */
    private EStoreService estoreServiceNext;

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        estoreService = this.estoreServiceNext;
    }

    /**
     * GET
     * 
     * @return
     */
    public EStoreService getEstoreServiceNext() {
        return estoreServiceNext;
    }

    @Resource(name = "estoreService")
    public void setEstoreServiceNext(EStoreService estoreServiceNext) {
        this.estoreServiceNext = estoreServiceNext;
    }

    /**
     * 将请求放入TreeMap
     * 
     * @return apiparamsMap
     */
    public static TreeMap<String, String> eStoreConfig() {
        // 定义一个TreeMap
        TreeMap<String, String> apiparamsMap = new TreeMap<String, String>();
        // 查询E店宝信息
        EStore eStore = estoreService.findEStore();
        if (eStore != null) {
            // 添加请求参数——主帐号
            apiparamsMap.put("dbhost", eStore.getDbhost());
            // 添加请求参数——appkey
            apiparamsMap.put("appkey", eStore.getAppkey());
            // 添加请求参数——appscret
            apiparamsMap.put("appscret", eStore.getAppscret());
            // 添加请求参数——token
            apiparamsMap.put("token", eStore.getToken());
        }
        // 返回
        return apiparamsMap;

    }

    /**
     * 查询E店宝信息
     * 
     * @return String
     */
    public static String eStoreUrl() {
        // 查询E店宝信息
        EStore eStore = estoreService.findEStore();
        if (eStore != null) {
            // 如果E店宝信息不为空
            // 获取E店宝的server
            return eStore.getServer();
        } else {
            // 否则返回""
            return "";
        }

    }

}
