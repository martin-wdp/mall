/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.report.dao;

import com.ningpai.report.bean.Report;

import java.util.Map;
import java.util.List;

/**  
 * @Description: np_report的dao:
 * @author Ningpai-HEHU
 * @date 2014-12-17 14:06:51
 * @version V1.0  
 */
public interface ReportMapper {

    /**
    * 根据主键删除 
    * 参数:主键  
    * 返回:删除个数  
    * @date 2014-12-17 14:06:51
    */
    int deleteByPrimaryKey(Long reportId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象  
    * 返回:添加个数  
    * @date 2014-12-17 14:06:51
    */
    int insertSelective(Report record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象  
    * @date 2014-12-17 14:06:51
    */
    Report selectByPrimaryKey(Long reportId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数  
    * @date 2014-12-17 14:06:51
    */
    int updateByPrimaryKeySelective(Report record);

    /**
    * 分页查询列表数量
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象  
    * @date 2014-12-17 14:06:51
    */
    Integer selectListCount(Map<String, Object> map);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象  
    * @date 2014-12-17 14:06:51
    */
    List<Object> selectList(Map<String, Object> map);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数  
    * @date 2014-12-17 14:06:51
    */
    int deleteMuilti(Long[] reportIds);

    /**
     * 查询店铺的销售金额情况，包括凤凰卡，银联以及各平台费率
     * 
     * @param paramMap
     * @return
     */
    List<Report> queryReportData(Map<String, Object> paramMap);

    /**
     * 根据storeId，startDate,endDate查询一条报表
     * 
     * @param paramMap
     * @return
     */
    Report selectOneByParam(Map<String, Object> paramMap);

    /**
     * 分页查询列表数量
     * 参数:1.赛选条件bean对象
     * 参数:2.分页封装对象
     * 返回:pageBean封装的list的对象  
     * @date 2014-12-17 14:06:51
     */
    Integer selectReportCateListCount(Map<String, Object> map);
    
    /**
     * 分页查询列表
     * 参数:1.赛选条件bean对象
     * 参数:2.分页封装对象
     * 返回:pageBean封装的list的对象  
     * @date 2014-12-17 14:06:51
     */
     List<Object> selectReportCateList(Map<String, Object> map);

     /**
      * 根据商家id，删除报表记录
      * @param storeId  商家id
      */
    void deleteByStoreId(Long storeId);

    /**
     * 根据参数查询报表记录
     * @param map 查询所需的参数
     * @return
     */
    List<Object> selectSumListByParam(Map<String, Object> map);

    
    /**
     * 根据参数查询类目报表
     * @param map
     * @return
     */
    List<Object> selectStoreCateReportList(Map<String, Object> map);

    /**
     * 根据商店id和分类id，删除报表记录
     * @param map   商店id 和
     */
    void settleReport(Map<String, Object> map);
}
