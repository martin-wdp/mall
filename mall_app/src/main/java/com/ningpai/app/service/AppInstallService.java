/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service;

import com.ningpai.app.bean.AppInstall;
import com.ningpai.util.PageBean;

import java.util.List;

/**  
 * @Description: np_app_install的dao:
 * @author Ningpai-HEHU
 * @date 2015-06-12 11:40:17
 * @version V1.0  
 */
public interface AppInstallService {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-06-12 11:40:17
    */
    int delete(Long installId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-06-12 11:40:17
    */
    int insert(AppInstall record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-06-12 11:40:17
    */
    AppInstall select(Long installId);

    /**
    * 根据BundleId 查询
    * 参数:查询条件,bundleId
    * 返回:对象
    * @date 2015-06-12 11:40:17
    */
    AppInstall selectByAppId(Long appId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-06-12 11:40:17
    */
    int update(AppInstall record);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-06-12 11:40:17
    */
    int deleteMuilti(Long[] installIds);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-12 11:40:17
    */
    PageBean selectList(AppInstall record, PageBean pageBean);

    /**
     * 查询自己安装的记录以及应用状态
     * @return 已安装应用的信息
     */
    List<AppInstall> queryAllInstallApps();

    /**
     * 将应用的更新标记改为“0”，表示不用更新
     * @param id 应用id
     */
    void modifyAppUpdateFlag(Long id);
}