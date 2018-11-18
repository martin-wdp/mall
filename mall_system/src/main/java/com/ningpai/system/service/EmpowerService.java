package com.ningpai.system.service;

import com.ningpai.system.bean.Empower;
import com.ningpai.system.bean.EmpowerLog;
import com.ningpai.util.PageBean;

import java.util.List;


/**
 * 权限信息服务层接口
 * 
 * @author xiaogu
 * 
 */
public interface EmpowerService {

    /**
     * 查询权限信息
     * 
     * @return Empower
     */
    PageBean list(PageBean pageBean);


    /**
     * 插入信息
     * @param empower 权限信息
     * @return 添加结果
     */
    int insertEmpower(Empower empower);

    /**
     * 修改角色
     * @param status 0：开启 1：关闭
     * @param appId 主键id
     * @return 修改结果
     */
    int updateEmpower(String status,Long appId);

    /**
     * 删除角色
     * @param appId 主键id
     * @return
     */
    int delEmpower(Long appId);

    /**
     * 验证名字是否存在
     * @param appUserName 用户名
     * @return
     */
    int checkUserName(String appUserName);

    /**
     * 查询日志列表
     * @param empowerId 用户id
     * @return 某用户的日志列表
     */
    List<EmpowerLog> selectLog(Long empowerId);

}
