/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.adv.service;

import java.util.List;

import com.ningpai.adv.bean.Adv;
import com.ningpai.util.PageBean;

/**  
 * @Description: bb_adv的dao:
 * @author zhangyue
 * @date 2014-08-30 14:41:09
 * @version V1.0  
 */
public interface AdvService {

    /**
    * 根据主键删除 
    * 参数:主键  
    * 返回:删除个数  
    * @date 2014-08-30 14:41:09
    */
    int delete(Long advId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象  
    * 返回:添加个数  
    * @date 2014-08-30 14:41:09
    */
    int insert(Adv record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象  
    * @date 2014-08-30 14:41:09
    */
    Adv select(Long advId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数  
    * @date 2014-08-30 14:41:09
    */
    int update(Adv record);

    /**
     * 分页查询列表
     * @param pageBean
     * @param adv
     * @return PageBean
     */
    PageBean selectPageList(PageBean pageBean, Adv adv);

    /**
     * 批量删除
     * @param buildingIds
     * @return int
     */
    int deleteAll(Long[] advIds);

    
    /**
     * 查询广告列表 
     * @param flag 广告位置flag
     * @return
     */
    List<Adv> selectAdvListByPosition(String flag);

}
