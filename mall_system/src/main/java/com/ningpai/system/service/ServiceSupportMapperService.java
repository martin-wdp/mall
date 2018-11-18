package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.ServiceSupport;
import com.ningpai.util.PageBean;

/**
 * 服务支持服务层
 * @author jiping 
 * @since 2015年8月21日 下午7:45:29 
 * @version 0.0.1
 */
public interface ServiceSupportMapperService {
    /**
     * 添加服务支持
     * 
     * @param record
     *            服务支持对象
     * @return
     */
    int insertSelective(ServiceSupport record);

    /**
     * 根据id查询单个服务支持
     * 
     * @param id
     *            服务支持对象id
     * @return
     */
    ServiceSupport selectByPrimaryKey(Long id);

    /**
     * 列表显示分页查询
     * 
     * @param pb
     *            分页对象
     * @return
     */
    PageBean selectAllServiceSupport(PageBean pb);

    /**
     * 修改单个服务支持
     * 
     * @param record
     *            服务支持对象
     * @return
     */
    int updateByPrimaryKeySelective(ServiceSupport record);

    /**
     * 修改删除标记
     * 
     * @param id
     *            服务支持对象id
     * @return
     */
    int updateServiceSupportByDelfalg(String[] id);

    /**
     * 查询所有
     * 
     * @return
     */
    List<ServiceSupport> selectAll();
}
