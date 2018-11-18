/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleException;

import java.io.IOException;

/**  
 * @Description: np_app的dao:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0  
 */
public interface AppService {
    /**
     * 安装Boss端app，
     * @param id id
     * @param serverId
     * @return 安装后返回bundle信息
     * @throws BundleException 安装应用异常
     * @throws IOException 下载异常
     */
    Bundle downloadAndInstallApp(Long id, Long serverId) throws BundleException, IOException;

    /**
     * 安装，并启动APP
     * @param id appId
     */
    Bundle installApp(Long id);


    /**
     * 开启应用
     * @param id bundle的唯一标识
     * @param serverId 服务器ID
     * @return bundle信息
     */
    Bundle startApp(Long id, Long serverId);

    /**
     * 停止应用
     * @param id bundle的唯一标识
     * @param serverId 服务器ID
     * @return bundle信息
     */
    Bundle stopApp(Long id, Long serverId);

    /**
     * 卸载应用
     * @param id bundle的唯一标识
     * @param serverId 服务器id
     * @return bundle信息
     */
    Bundle uninstallapp(Long id, Long serverId);


    /**
     * 更新应用
     * @param id bundle的唯一标识
     * @param serverId 服务器id
     * @return bundle信息
     */
    Bundle updateApp(Long id, Long serverId) throws BundleException, IOException;

}