package com.ningpai.system.service;

import com.ningpai.system.bean.ErrorPage;
import com.ningpai.util.PageBean;

/**
 * 异常页面SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月25日 16:17:25
 * @version 1.0
 */
public interface ErrorPageService {
    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     */
    int delErrorPage(Long id);

    /**
     * 批量删除异常页面
     * 
     * @param ids
     *            异常页面编号数组
     * @return
     */
    void batchDelErrorPage(Long[] ids);

    /**
     * 添加异常页面
     * 
     * @param record
     * @return
     */
    int saveErrorPage(ErrorPage record);

    /**
     * 更新异常页面
     * 
     * @param record
     * @return
     */
    int updateErrorPage(ErrorPage record);

    /**
     * 根据主键查询异常页面
     * 
     * @param id
     * @return
     */
    ErrorPage getErrorPage(Long id);

    /**
     * 根据分页参数查询异常页面列表
     * 
     * @param pb
     * @return
     */
    PageBean queryErrorPageByPageBean(PageBean pb);
}
