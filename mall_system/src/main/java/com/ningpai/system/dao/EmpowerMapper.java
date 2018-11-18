package com.ningpai.system.dao;


import com.ningpai.system.bean.Empower;
import com.ningpai.system.bean.EmpowerLog;

import java.util.List;
import java.util.Map;

/**
 * 权限显示数据接口层
 * 
 * @author xiaogu
 * 
 */
public interface EmpowerMapper {

    /**
     * 查询权限信息
     * 
     * @return Empower
     */
    List<Object> list(Map<String,Object> map);


    /**
     * 查询总行数
     * @return 查询的总行数
     */
    int listCount();

    /**
     * 添加角色
     * @param empower 实体
     * @return 1:成功 0：失败
     */
    int insertEmpower(Empower empower);

    /**
     * 修改角色
     * @param map  status 0：开启 1：关闭 appId:主键id
     * @return 修改结果
     */
    int updateEmpower(Map<String,Object> map);


    /**
     * 删除角色
     * @param appId 主键id
     * @return 删除结果
     */
    int delEmpower(Long appId);

    /**
     * 验证名称是否存在
     * @param appUserName 名称
     * @return
     */
    int checkName(String appUserName);

    /**
     * 根据权限id查询日志
     * @param empowerId  权限id
     * @return 查询的集合
     */
    List<EmpowerLog> selectLog(Long empowerId);
}
