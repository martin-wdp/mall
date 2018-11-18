/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao;

import com.ningpai.app.bean.App;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app的dao:
 * @author Ningpai-HEHU
 * @date 2015-06-10 18:21:40
 * @version V1.0  
 */
public interface AppMapper {

    /**
    * 根据主键删除
    * 参数:主键
    * 返回:删除个数
    * @date 2015-06-10 18:21:40
    */
    int deleteByPrimaryKey(Long id);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2015-06-10 18:21:40
    */
    int insertSelective(App record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2015-06-10 18:21:40
    */
    App selectByPrimaryKey(Long id);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2015-06-10 18:21:40
    */
    int updateByPrimaryKeySelective(App record);

    /**
    * 分页查询列表数量
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-10 18:21:40
    */
    Integer selectListCount(Map<String, Object> map);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象
    * @date 2015-06-10 18:21:40
    */
    List<Object> selectList(Map<String, Object> map);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数
    * @date 2015-06-10 18:21:40
    */
    int deleteMuilti(Long[] ids);

    /**
     * 根据App名称查询
     * @param appName
     * @return
     */
    int checkAppName(String appName);

    /**
     * 查询endMenuId最大值
     * @return
     */
    Long selectMenuId();
}