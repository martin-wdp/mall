/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao;

import com.ningpai.app.bean.AppServer;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_server的dao:
 * @author Ningpai-HEHU
 * @date 2015-08-06 09:24:42
 * @version V1.0  
 */
public interface AppServerMapper {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-08-06 09:24:42
    */
    int deleteByPrimaryKey(Long appServerId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-08-06 09:24:42
    */
    int insertSelective(AppServer record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-08-06 09:24:42
    */
    AppServer selectByPrimaryKey(Long appServerId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-08-06 09:24:42
    */
    int updateByPrimaryKeySelective(AppServer record);

    /**
    * 分页查询列表数量
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-08-06 09:24:42
    */
    Integer selectListCount(Map<String, Object> map);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-08-06 09:24:42
    */
    List<Object> selectList(Map<String, Object> map);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-08-06 09:24:42
    */
    int deleteMuilti(Long[] appServerIds);

    /**
     * 查询所有客户端网站地址
     *
     * @param map serverType 1Boss，2商家，3前台
     * @return 所有客户端网站地址
     */
    List<AppServer> selectByParam(Map<String, Object> map);

    /**
     * 根据参数更改主机类型
     *
     * @param map 网站地址ID
     */
    void updateByParam(Map<String, Object> map);
}