package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.dao.LogisticsSingleMapper;
import com.ningpai.system.service.LogisticsSingleService;
import com.ningpai.util.PageBean;

/**
 * 物流单模板service实现类
 * 
 * @author jiping
 * @since 2015年8月19日 下午7:38:44
 * @version 0.0.1
 */
@Service("LogisticsSingleService")
@Transactional
public class LogisticsSingleServiceImpl implements LogisticsSingleService {

    private static final String THIRDID = "thirdId";

    /** 物流单模板操作 */
    private LogisticsSingleMapper logisticsSingleMapper;

    /*
     * 删除物流单模板
     * com.ningpai.system.service.LogisticsSingleService#delLogisticsSingleById
     * (java.lang.Long)
     */
    @Override
    public int delLogisticsSingleById(Long logisticsSingleId) {

        return logisticsSingleMapper.delLogisticsSingleById(logisticsSingleId);
    }

    /**
     * 删除物流单模板
     */
    @Override
    public int delLogisticsSingle(Long logisticsSingleId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logisticsSingleId", logisticsSingleId);
        map.put(THIRDID, thirdId);
        return logisticsSingleMapper.delLogisticsSingle(map);
    }

    /*
     * 根据Id 查询物流单模板信息
     * com.ningpai.system.service.LogisticsSingleService#selectLogisticsSingleById
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public LogisticsSingle selectLogisticsSingleById(Long logisticsSingleId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("logisticsSingleId", logisticsSingleId);
        map.put(THIRDID, thirdId);
        return logisticsSingleMapper.selectLogisticsSingle(map);
    }

    /*
     * 修改物流单模板信息
     * com.ningpai.system.service.LogisticsSingleService#updateLogisticsSingle
     * (com.ningpai.system.bean.LogisticsSingle)
     */
    @Override
    public int updateLogisticsSingle(LogisticsSingle logisticsSingle) {

        return logisticsSingleMapper.updateLogisticsSingle(logisticsSingle);
    }

    /*
     * 获得物流单模板列表
     * com.ningpai.system.service.LogisticsSingleService#getLogisticsSingleList
     * (com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean getLogisticsSingleList(PageBean pb, Long thirdId) {
        PageBean pbNew = pb;
        if (pbNew == null) {
            pbNew = new PageBean();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(THIRDID, thirdId);
        pbNew.setRows(logisticsSingleMapper.selectLogisticsSingleCount(map));
        map.put("startRowNum", pbNew.getStartRowNum());
        map.put("endRowNum", pbNew.getEndRowNum());
        pbNew.setList(logisticsSingleMapper.selectAll(map));
        return pbNew;
    }

    /*
     * 添加物流单模板信息
     * com.ningpai.system.service.LogisticsSingleService#addLogisticsSingle(
     * com.ningpai.system.bean.LogisticsSingle)
     */
    @Override
    public int addLogisticsSingle(LogisticsSingle logisticsSingle) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(THIRDID, logisticsSingle.getThirdId());
        map.put("companyId", logisticsSingle.getCompanyId());
        if (logisticsSingleMapper.selectLogisticsSingle(map) == null) {
            return logisticsSingleMapper.addLogisticsSingle(logisticsSingle);
        }
        return 0;
    }

    /*
     * 根据条件查询模板信息
     * com.ningpai.system.service.LogisticsSingleService#selectLogisticsSingle
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public LogisticsSingle selectLogisticsSingle(Long thirdId, Long companyId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(THIRDID, thirdId);
        map.put("companyId", companyId);
        return logisticsSingleMapper.selectLogisticsSingle(map);
    }

    public LogisticsSingleMapper getLogisticsSingleMapper() {
        return logisticsSingleMapper;
    }

    @Resource(name = "LogisticsSingleMapper")
    public void setLogisticsSingleMapper(LogisticsSingleMapper logisticsSingleMapper) {
        this.logisticsSingleMapper = logisticsSingleMapper;
    }

}
