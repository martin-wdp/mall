package com.ningpai.api.dao.impl;

import com.ningpai.api.dao.IEmpowerLogMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/18
 */
@Repository("openEmpowerLogMapper")
public class EmpowerLogMapperImpl extends BasicSqlSupport implements IEmpowerLogMapper{
    /**
     * 添加日志
     *
     * @param map 添加参数
     * @return 1:添加成功 0：添加失败
     */
    @Override
    public int addLog(Map<String, Object> map) {
        return this.update("com.ningpai.web.dao.OEmpowerLogMapper.addLog",map);
    }
}
