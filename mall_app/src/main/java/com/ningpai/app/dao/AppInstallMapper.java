/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao;

import com.ningpai.app.bean.AppInstall;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_install的dao:
 * @author Ningpai-HEHU
 * @date 2015-06-12 11:40:17
 * @version V1.0  
 */
public interface AppInstallMapper {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-06-12 11:40:17
    */
    int deleteByPrimaryKey(Long installId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-06-12 11:40:17
    */
    int insertSelective(AppInstall record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-06-12 11:40:17
    */
    AppInstall selectByPrimaryKey(Long installId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-06-12 11:40:17
    */
    int updateByPrimaryKeySelective(AppInstall record);

    /**
    * 分页查询列表数量
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-12 11:40:17
    */
    Integer selectListCount(Map<String, Object> map);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-12 11:40:17
    */
    List<Object> selectList(Map<String, Object> map);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-06-12 11:40:17
    */
    int deleteMuilti(Long[] installIds);

    /**
    * 根据bundleId查询
    * 参数:查询条件,bundleId
    * 返回:对象
    * @date 2015-06-12 11:40:17
    */
    AppInstall selectByAppId(Long appId);

    /**
     * 查询自己安装的记录以及应用状态
     * @return 已安装应用的信息
     */
    List<AppInstall> queryAllInstallApps();

    /**
     * 修改应用跟新标记
     * @param appIds 应用id
     */
    void modifyUpdateFlag(List<Long> appIds);

    /**
     * 修改应用更新标记为0，表示已更新
     * @param appId 应用id
     */
    void modifyAppUpdateFlag(Long appId);
}