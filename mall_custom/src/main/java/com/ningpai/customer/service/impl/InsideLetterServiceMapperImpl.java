/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.dao.InsideLetterMapper;
import com.ningpai.customer.service.InsideLetterServiceMapper;
import com.ningpai.customer.vo.InsideLetterVo;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 站内信Service
 * 
 * @author jiping
 * @since 2014年8月7日 上午11:52:01
 * @version 0.0.1
 */
@Service("insideLetterServiceMapper")
public class InsideLetterServiceMapperImpl implements InsideLetterServiceMapper {
    // spring注解
    private InsideLetterMapper insideletter;

    public InsideLetterMapper getInsideletter() {
        return insideletter;
    }

    @Resource(name = "insideletter")
    public void setInsideletter(InsideLetterMapper insideletter) {
        this.insideletter = insideletter;
    }
    /**
     * 删除站内信
     *
     * @param letterId
     * @return 0 失败 1成功
     */
    @Override
    public int deleteByPrimaryKey(Long letterId) {
        return insideletter.deleteByPrimaryKey(letterId);
    }
    /**
     * 添加站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int insert(InsideLetter record) {
        return insideletter.insert(record);
    }
    /**
     * 添加站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int insertSelective(InsideLetter record) {
        return insideletter.insertSelective(record);
    }

    /**
     * 根据会员Id添加站内信
     *
     * @param record
     * @param customerIds
     * @return 0 失败 1成功
     */
    @Override
    public int insertNotices(InsideLetter record, Long[] customerIds) {
        List<InsideLetter> list = new ArrayList<InsideLetter>();
        if (customerIds != null && customerIds.length > 0) {
            for (Long id : customerIds) {
                InsideLetter insi = new InsideLetter();
                insi.setLetterTitle(record.getLetterTitle());
                insi.setLetterContent(record.getLetterContent());
                insi.setCustomerId(id);
                list.add(insi);
            }
        }
        return insideletter.insertNotices(list);
    }
    /**
     * 查询站内信
     *
     * @param letterId
     * @return {@link InsideLetter}
     */
    @Override
    public InsideLetter selectByPrimaryKey(Long letterId) {
        return insideletter.selectByPrimaryKey(letterId);
    }
    /**
     * 修改站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int updateByPrimaryKeySelective(InsideLetter record) {
        return insideletter.updateByPrimaryKeySelective(record);
    }
    /**
     * 修改站内信
     *
     * @param record
     * @return 0 失败 1成功
     */
    @Override
    public int updateByPrimaryKey(InsideLetter record) {
        return insideletter.updateByPrimaryKey(record);
    }
    /**
     * 查询全部站内信
     *
     * @param paramMap
     *            装在查询的条件
     * @param pb
     *            根据条件查询得到的 分页对象
     * @return
     */
    @Override
    public PageBean queryInsideLetter(Map<String, Object> paramMap, PageBean pb) {
        Long count = insideletter.queryInsideCount((Long) paramMap
                .get("customerId"));
        pb.setRows(Integer.parseInt(count == null ? "0" : count.toString()));
        if (pb.getPageNo() > pb.getLastPageNo()) {
            pb.setPageNo(pb.getLastPageNo());
        }
        if (pb.getPageNo() == 0) {
            pb.setPageNo(1);
        }
        paramMap.put(CustomerConstantStr.STARTNUM, pb.getStartRowNum());
        paramMap.put(CustomerConstantStr.ENDNUM, pb.getEndRowNum());
        pb.setList(insideletter.queryInsideLetter(paramMap));
        return pb;
    }

    /**
     * 标记为已读
     */
    @Override
    public int readedLetter(InsideLetterVo inside) {
        return insideletter.readedLetter(inside);
    }
    /**
     * 删除
     */
    @Override
    public int deleteLetter(Long relaId) {
        return insideletter.deleteLetter(relaId);
    }
    /**
     * 是否已读
     */
    @Override
    public Long letterIsRead(Map<String, Object> paramMap) {
        return insideletter.letterIsRead(paramMap);
    }
    /**
     * 删除未读
     */
    @Override
    public int deleteLetterNo(InsideLetterVo inside) {
        return insideletter.deleteLetterNo(inside);
    }

    /**
     * 根据会员Id和letteId删除
     */
    @Override
    public int deleteByLetterIdCustId(Map<String, Object> paramMap) {
        return insideletter.deleteByLetterIdCustId(paramMap);
    }

}
