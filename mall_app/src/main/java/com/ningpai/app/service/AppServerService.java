/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service;

import com.ningpai.app.bean.AppServer;
import com.ningpai.util.PageBean;

import java.util.List;

/**  
 * @Description: np_app_server的Service:
 * @author Ningpai-HEHU
 * @date 2015-08-06 09:24:42
 * @version V1.0  
 */
public interface AppServerService {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-08-06 09:24:42
    */
    int delete(Long appServerId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-08-06 09:24:42
    */
    int insert(AppServer record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-08-06 09:24:42
    */
    AppServer select(Long appServerId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-08-06 09:24:42
    */
    int update(AppServer record);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-08-06 09:24:42
    */
    int deleteMuilti(Long[] appServerIds);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-08-06 09:24:42
    */
    PageBean selectList(AppServer record, PageBean pageBean);

    /**
     * 查询所有客户端网站地址
     * @param serverType 1Boss，2商家，3前台
     * @return 所有客户端网站地址
     */
    List<AppServer> selectAllServersByType(String serverType);

    /**
     * 将所有网站地址标记为“非主机”
     * @param serverType 1Boss，2商家，3前台
     */
    void updateNoMainAll(String serverType);

    /**
     * 将网址标记为“主机”
     * @param appServerId 网站地址ID
     */
    void updateMain(Long appServerId);

}