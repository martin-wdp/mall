package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ServiceSupport;
import com.ningpai.system.dao.ServiceSupportMapper;

/**
 * 服务支持实现类
 * 
 * @author jiping
 * @since 2015年8月19日 下午6:33:35
 * @version 0.0.1
 */
@Repository("serviceSupportMapper")
public class ServiceSupportMapperImpl extends BasicSqlSupport implements
        ServiceSupportMapper {
    /**
     * 添加服务支持
     * 
     * @param record
     * @return
     */
    @Override
    public int insertSelective(ServiceSupport record) {
        return this.insert(
                "com.ningpai.system.dao.ServiceSupportMapper.insertSelective",
                record);
    }

    /**
     * 根据id查询单个服务支持
     * 
     * @param id
     * @return
     */
    @Override
    public ServiceSupport selectByPrimaryKey(Long id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ServiceSupportMapper.selectByPrimaryKey",
                        id);
    }

    /**
     * 列表显示分页查询
     * 
     * @param map
     * @return
     */
    @Override
    public List<Object> selectAllServiceSupport(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.system.dao.ServiceSupportMapper.selectAllServiceSupport",
                        map);
    }

    /**
     * 查询总条数
     * 
     * @return
     */
    @Override
    public int selectCount() {
        return this
                .selectOne("com.ningpai.system.dao.ServiceSupportMapper.selectCount");
    }

    /**
     * 修改单个服务支持
     * 
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(ServiceSupport record) {
        return this
                .update("com.ningpai.system.dao.ServiceSupportMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改删除标记
     * 
     * @param record
     * @return
     */
    @Override
    public int updateServiceSupportByDelfalg(List<Long> list) {
        return this
                .update("com.ningpai.system.dao.ServiceSupportMapper.updateServiceSupportByDelfalg",
                        list);
    }

    /*
     * 查询所有
     * 
     * @see com.ningpai.system.dao.ServiceSupportMapper#selectAll()
     */
    @Override
    public List<ServiceSupport> selectAll() {
        return this
                .selectList("com.ningpai.system.dao.ServiceSupportMapper.selectAll");
    }
}
