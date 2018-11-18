package com.ningpai.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.ServiceSupport;
import com.ningpai.system.dao.ServiceSupportMapper;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 服务支持服务层实现类
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014年7月28日 下午2:42:54
 */
@Service("serviceSupportMapperService")
public class ServiceSupportMapperServiceImpl implements ServiceSupportMapperService {
    @Resource(name = "serviceSupportMapper")
    private ServiceSupportMapper serviceSupportMapper;

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(ServiceSupportMapperServiceImpl.class);

    @Override
    /**
     * 添加服务支持
     * @param record 服务支持对象
     * @return   
     */
    public int insertSelective(ServiceSupport record) {
        if (record == null) {
            LOGGER.error("服务支持对象为空，无法执行保存操作！");
            return 0;
        }
        try {
            return serviceSupportMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("", e);
            return 0;
        }
    }

    /**
     * 查询单个服务支持对象
     * 
     * @param record
     *            服务支持对象
     */
    @Override
    public ServiceSupport selectByPrimaryKey(Long id) {
        if (id == null) {
            LOGGER.error("id为空，无法执行查询操作！");
        }
        try {
            return serviceSupportMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("", e);
            return null;
        }
    }

    /**
     * 分页显示单个服务支持对象
     * 
     * @param pb
     *            分页对象
     */
    @Override
    public PageBean selectAllServiceSupport(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(serviceSupportMapper.selectCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(serviceSupportMapper.selectAllServiceSupport(map));
            return pb;
        } catch (Exception e) {
            LOGGER.error("",e);
            return pb;
        }

    }

    /**
     * 修改服务支持对象
     * 
     * @param record
     *            服务支持对象
     */
    @Override
    public int updateByPrimaryKeySelective(ServiceSupport record) {
        if (record == null) {
            LOGGER.error("服务支持为空，无法执行修改操作！");
        }
        try {
            return serviceSupportMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("",e);
            return 0;
        }

    }

    /**
     * 改变删除标记状态
     * 
     * @param id
     *            服务支持对象id
     */
    @Override
    public int updateServiceSupportByDelfalg(String[] id) {
        if (id == null) {
            LOGGER.error("id为空，无法执行删除操作！");
        }
        try {
            List<Long> ids = new ArrayList<Long>();
            for (int i = 0; i < id.length; i++) {
                ids.add(Long.valueOf(id[i]));
            }
            return serviceSupportMapper.updateServiceSupportByDelfalg(ids);
        } catch (Exception e) {
            LOGGER.error("",e);
            return 0;
        }

    }

    /*
     * 
     * 
     * @see com.ningpai.system.service.ServiceSupportMapperService#selectAll()
     */
    @Override
    public List<ServiceSupport> selectAll() {
        return serviceSupportMapper.selectAll();
    }
}
