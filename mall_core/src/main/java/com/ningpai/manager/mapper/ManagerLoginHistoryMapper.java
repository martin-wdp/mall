package com.ningpai.manager.mapper;

import com.ningpai.manager.bean.ManagerLoginHistory;

import java.util.List;

/**
 *  管理员登录记录Mapper
 * @author hanxiaoying
 * @since
 */
public interface ManagerLoginHistoryMapper {

    /**
     * 插入管理员登录记录
     * @param loginHistory
     * @return  1表示成功 0表示失败
     */
    int insert(ManagerLoginHistory loginHistory);
}
