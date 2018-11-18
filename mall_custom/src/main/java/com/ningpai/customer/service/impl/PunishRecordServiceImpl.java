/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.customer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.dao.PunishRecordMapper;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
/**
 * 处罚记录接口实现类
 *
 * */
@Service("PunishRecordService")
public class PunishRecordServiceImpl implements PunishRecordService {

    @Resource(name = "PunishRecordMapper")
    private PunishRecordMapper punishRecordMapper;
    /**
     * 添加一条记录
     *
     * @param punishRecord
     * */
    @Override
    public int addPunishRecord(PunishRecord punishRecord) {
        punishRecord.setCreateTime(new Date());
        return punishRecordMapper.insertSelective(punishRecord);
    }
    /**
     * 根据商家id查询
     *
     * @param thirdId
     * */
    @Override
    public PunishRecord queryInfoByThirdId(Long thirdId) {
        return punishRecordMapper.queryInfoByThirdId(thirdId);
    }
    /**
     * 查询记录
     *
     * @param thirdId
     * */
    @Override
    public List<PunishRecord> queryInfoByTidandDate(Long thirdId) {

        return punishRecordMapper.queryInfoByTidandDate(thirdId);
    }
    /**
     * 第三方显示处罚记录
     *
     * @param pageBean
     * @param thirdId
     * @return
     */
    @Override
    public PageBean selectRecordByPage(PageBean pageBean, Long thirdId) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("startRowNum", pageBean.getStartRowNum());
        paraMap.put("endRowNum", pageBean.getEndRowNum());
        paraMap.put("thirdId", thirdId);
        pageBean.setList(punishRecordMapper.selectRecordByPage(paraMap));
        pageBean.setRows(punishRecordMapper.selectAllCountByTid(thirdId));
        return pageBean;
    }

    /**
     * 后台显示处罚记录
     */
    @Override
    public PageBean selectPunishedRecordByPage(PageBean pageBean,
            SelectBean selectBean) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        try {
            paraMap.put("startRowNum", pageBean.getStartRowNum());
            paraMap.put("endRowNum", pageBean.getEndRowNum());
            paraMap.put("condition", selectBean.getCondition());
            if (selectBean.getSearchText() != null) {
                paraMap.put("searchText", selectBean.getSearchText().trim());

            }
            pageBean.setObjectBean(selectBean);
            pageBean.setList(punishRecordMapper
                    .selectPunishedRecordByPage(paraMap));
            pageBean.setRows(punishRecordMapper
                    .selectPunishedAllCountByTid(paraMap));

        } finally {
            paraMap = null;
        }
        return pageBean;
    }
}
