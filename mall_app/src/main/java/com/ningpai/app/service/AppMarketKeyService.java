/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service;

import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.util.PageBean;

/**  
 * @Description: np_app_market_key的dao:
 * @author Ningpai-HEHU
 * @date 2015-07-17 10:28:52
 * @version V1.0  
 */
public interface AppMarketKeyService {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-07-17 10:28:52
    */
    int delete(Long id);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-07-17 10:28:52
    */
    int insert(AppMarketKey record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-07-17 10:28:52
    */
    AppMarketKey select(Long id);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-07-17 10:28:52
    */
    int update(AppMarketKey record);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-07-17 10:28:52
    */
    int deleteMuilti(Long[] ids);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-07-17 10:28:52
    */
    PageBean selectList(AppMarketKey record, PageBean pageBean);
}