/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.version.service;

import java.util.List;

import com.ningpai.util.PageBean;
import com.ningpai.version.bean.Version;

/**
 * 系统日志Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月26日 上午10:25:49
 * @version 0.0.1
 */
public interface VersionService {
    /**
     * 查询版本信息
     * 
     * @return 版本信息 {@link List}
     * @see Version
     */
    List<Version> seleceVersion();

    /**
     * 查询所有版本 分页
     * 
     * @see PageBean 分页辅助类 {@link com.ningpai.util.PageBean}
     * @see Version {@link Version}
     * @return PageBean {@link com.ningpai.util.PageBean}
     */
    PageBean selectAllVersion(PageBean pageBean, Version version);

    /**
     * 增加版本信息
     * 
     * @param version
     * @return
     */
    int addVersion(Version version, String date);

    /**
     * 修改版本信息
     * 
     * @param version
     * @param date
     * @return
     */
    int updateVersion(Version version, String date);

    /**
     * 查询版本信息
     * 
     * @param versionId
     *            版本号
     * @return
     */
    Version showVersion(Long versionId);

    /**
     * 查询最新版本
     * 
     * @return
     */
    Version showNewVersion();

}
