package com.ningpai.system.dao.impl;

import com.ningpai.system.bean.Empower;
import com.ningpai.system.bean.EmpowerLog;
import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.dao.EmpowerMapper;

import java.util.List;
import java.util.Map;

/**
 * 权限接口实现累
 * 
 * @author xiaogu
 * 
 */
@Repository("EmpowerMapper")
public class EmpowerMapperImpl extends BasicSqlSupport implements EmpowerMapper {


    /**
     * 查询权限信息
     * @param map
     * @return Empower
     */
    @Override
    public List<Object> list(Map<String, Object> map) {
        return this.selectList("com.ningpai.system.dao.EmpowerMapper.list",map);
    }

    /**
     * 查询总行数
     *
     * @return 查询的总行数
     */
    @Override
    public int listCount() {
        return this.selectOne("com.ningpai.system.dao.EmpowerMapper.listCount");
    }

    /**
     * 添加角色
     *
     * @param empower 实体
     * @return 1:成功 0：失败
     */
    @Override
    public int insertEmpower(Empower empower) {
        return this.insert("com.ningpai.system.dao.EmpowerMapper.insertEmpower",empower);
    }


    /**
     * 修改角色
     * @param map  status 0：开启 1：关闭 appId:主键id
     * @return 修改结果
     */
    @Override
    public int updateEmpower(Map<String,Object> map) {
        return this.update("com.ningpai.system.dao.EmpowerMapper.updateEmpower", map);
    }

    /**
     * 删除角色
     *
     * @param appId 主键id
     * @return 删除结果
     */
    @Override
    public int delEmpower(Long appId) {
        return this.update("com.ningpai.system.dao.EmpowerMapper.delEmpower",appId);
    }

    /**
     * 验证名称是否存在
     *
     * @param appUserName 名称
     * @return
     */
    @Override
    public int checkName(String appUserName) {
        return this.selectOne("com.ningpai.system.dao.EmpowerMapper.checkName",appUserName);
    }

    /**
     * 根据权限id查询日志
     *
     * @param empowerId 权限id
     * @return 查询的集合
     */
    @Override
    public List<EmpowerLog>  selectLog(Long empowerId) {
        return this.selectList("com.ningpai.web.dao.EmpowerLogMapper.selectLog", empowerId);

    }
}
