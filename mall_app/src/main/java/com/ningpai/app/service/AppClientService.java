/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service;

import com.ningpai.app.bean.App;
import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.system.bean.BasicSet;
import com.ningpai.util.PageBean;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app的dao:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0  
 */
public interface AppClientService {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-06-10 18:21:40
    */
    int delete(Long id);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-06-10 18:21:40
    */
    int insert(App record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-06-10 18:21:40
    */
    App select(Long id);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-06-10 18:21:40
    */
    int update(App record);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-06-10 18:21:40
    */
    int deleteMuilti(Long[] ids);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-10 18:21:40
    */
    PageBean selectList(App record, PageBean pageBean);

    /**
     * app详情，http访问查询获得
     * @param id 主键id
     * @return
     */
    Object queryAppDetail(Long id);

    /**
     * app详情，http访问查询获得
     * @param id 主键id
     * @return
     */
    Object queryAppDetailForShow(Long id);
    /**
     * 从应用市场服务器获取秘钥，并保存
     * @param basicSet 网站基本信息
     * @return 获取到的秘钥信息
     */
    AppMarketKey getAppMarketKeyFromServer(BasicSet basicSet);

    /**
     * 查询可以更新的应用
     * @param appVersions appId-appVersion
     * @return 可以更新的应用的id
     */
    List<Long> queryUpdatableAppIds(Map<String,Object> appVersions);
}