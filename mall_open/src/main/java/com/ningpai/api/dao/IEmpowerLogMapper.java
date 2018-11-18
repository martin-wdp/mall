package com.ningpai.api.dao;

import java.util.Map;

/**
 * 权限日志类，记录请求信息
 * @author lih
 * @version 2.0
 * @since 15/9/18
 */
public interface IEmpowerLogMapper {

    /**
     * 添加日志
     * @param map 添加参数
     * @return 1:添加成功 0：添加失败
     */
    int addLog(Map<String,Object> map);
}
