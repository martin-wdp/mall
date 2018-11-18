/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.report.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ningpai.report.bean.Report;
import com.ningpai.util.PageBean;

/**  
 * @Description: np_report的dao:
 * @author Ningpai-HEHU
 * @date 2014-12-17 14:06:51
 * @version V1.0  
 */
public interface ReportService {

    /**
    * 根据主键删除 
    * 参数:主键  
    * 返回:删除个数  
    * @date 2014-12-17 14:06:51
    */
    int delete(Long reportId);

    /**
    * 插入，空属性不会插入
    * 参数:pojo对象  
    * 返回:添加个数  
    * @date 2014-12-17 14:06:51
    */
    int insert(Report record);

    /**
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象  
    * @date 2014-12-17 14:06:51
    */
    Report select(Long reportId);

    /**
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数  
    * @date 2014-12-17 14:06:51
    */
    int update(Report record);

    /**
    * 删除多条记录
    * 参数:1.主键数组
    * 返回:影响行数  
    * @date 2014-12-17 14:06:51
    */
    int deleteMuilti(Long[] reportIds);

    /**
    * 分页查询列表
    * 参数:1.赛选条件bean对象
    * 参数:2.分页封装对象
    * 返回:pageBean封装的list的对象  
    * @date 2014-12-17 14:06:51
    */
    PageBean selectList(Report record, PageBean pageBean);

    /**
     * 根据时间段，查询结算周期在这个时间段内的商家的订单金额、凤凰卡金额、银联付款金额
     * 
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return  执行结果
     */
    Object generateReport(String startDate, String endDate);
    
    /**
     * 查询报表包含的订单
     * 
     * @param reportId
     *            报表id
     * @return 报表分页信息
     */
    PageBean selectReportOrders(Long reportId, PageBean pb);

    /**
     * 分页查询列表
     * 参数:1.赛选条件bean对象
     * 参数:2.分页封装对象
     * 返回:pageBean封装的list的对象  
     * @date 2014-12-17 14:06:51
     */
    PageBean selectReportCateList(Report report, PageBean pb);

    /**
     * 根据商家id，删除报表记录
     * @param storeId   商家id
     */
    void deleteByReportByStoreId(Long storeId);

    /**
     * 根据条件导出报表
     * @param report    报表查询参数
     * @param response
     */
    void exportSumReport(Report report, HttpServletResponse response);

    /**
     * 根据参数查询报表
     * @param record
     * @return
     */
    List<Object> selectSumListByParam(Report record);

    /**
     * 导出类目报表
     * @param report
     * @param response
     */
    void exportCateReport(Report report, HttpServletResponse response);

    /**
     * 根据商店id，删除报表记录
     * @param storeId   商店id
     */
    void settleReport(Long storeId);
    
    /**
     * 根据分类id，删除报表记录
     * @param storeId   商店id
     */
    void settleReportById(Long reportId);
}
