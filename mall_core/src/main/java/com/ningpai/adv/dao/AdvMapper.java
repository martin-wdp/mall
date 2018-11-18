/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.adv.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.adv.bean.Adv;

/**  
 * @Description: bb_adv的dao:
 * @author zhangyue
 * @date 2014-08-30 14:41:09
 * @version V1.0  
 */
public interface AdvMapper {

    /**
    * 根据主键删除 
    * 参数:主键  
    * 返回:删除个数  
    * @date 2014-08-30 14:41:09
    */
    int deleteByPrimaryKey(Long advId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象  
    * 返回:添加个数  
    * @date 2014-08-30 14:41:09
    */
    int insertSelective(Adv record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象  
    * @date 2014-08-30 14:41:09
    */
    Adv selectByPrimaryKey(Long advId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数  
    * @date 2014-08-30 14:41:09
    */
    int updateByPrimaryKeySelective(Adv record);

    /**
     * 查询分页列表总数
     * @param map 查询参数
     * @return int 记录条数
     */
    int selectPageListCount(Map<String, Object> map);

    /**
     * 查询分页列表
     * @param map 查询参数
     * @return List 记录列表
     */
    List<Object> selectPageList(Map<String, Object> map);

    /**
     * 删除多条记录
     * @param advIds 广告列表
     * @return int 返回结果
     */
    int deleteMuilti(Long[] advIds);

    
    /**
     * 通过广告位置查询广告列表
     * @param advPosition
     * @return List
     */
    List<Adv> selectAdvListByPosition(String advPosition);
}
