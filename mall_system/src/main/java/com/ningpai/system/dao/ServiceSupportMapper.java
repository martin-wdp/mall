package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.ServiceSupport;

/**
 * 服务支持接口
 * 
 * @author jiping
 * @since 2015年8月19日 下午6:33:10
 * @version 0.0.1
 */
public interface ServiceSupportMapper {

    /**
     * 添加服务支持
     * 
     * @param record
     * @return
     */
    int insertSelective(ServiceSupport record);

    /**
     * 根据id查询单个服务支持
     * 
     * @param id
     * @return
     */
    ServiceSupport selectByPrimaryKey(Long id);

    /**
     * 列表显示分页查询
     * 
     * @param map
     * @return
     */
    List<Object> selectAllServiceSupport(Map<String, Object> map);

    /**
     * 查询总条数
     * 
     * @return
     */
    int selectCount();

    /**
     * 修改单个服务支持
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ServiceSupport record);

    /**
     * 修改删除标记
     * 
     * @param record
     * @return
     */
    int updateServiceSupportByDelfalg(List<Long> list);

    /**
     * 查询所有
     * 
     * @return
     */
    List<ServiceSupport> selectAll();
}
