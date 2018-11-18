/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.manager.mapper.NoticeMapper;
import com.ningpai.manager.service.NoticeService;
import com.ningpai.util.OrderInfoBean;

/**
 * @see com.ningpai.manager.service.NoticeService
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 上午10:05:39
 * @version 0.0.1
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

    private NoticeMapper noticeMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.manager.service.NoticeService#selectNotice(java.lang.Long)
     */
    @Override
    public List<OrderInfoBean> selectNotice(Long count) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            if (count > 9) {
                paramMap.put("qFlag", "1");
            } else {
                paramMap.put("qFlag", "0");
            }
            paramMap.put("count", count);
            return noticeMapper.selectNotice(paramMap);
        } finally {
            paramMap = null;
        }

    }

    /*
     * 
     * 
     * @see com.ningpai.manager.service.NoticeService#selectNoticeNum()
     */
    @Override
    public Long selectNoticeNum() {
        return noticeMapper.selectNoticeNum();
    }

    /**
     * 删除所有消息
     * 
     * @return
     */
    @Override
    public int updateNotice() {
        return noticeMapper.updateNotice();
    }

    /**
     * 根据id修改新增消息状态
     * 
     * @param id
     * @return
     */
    @Override
    public int updateById(Long id) {
        return noticeMapper.updateById(id);
    }

    public NoticeMapper getNoticeMapper() {
        return noticeMapper;
    }

    @Resource(name = "noticeMapper")
    public void setNoticeMapper(NoticeMapper noticeMapper) {
        this.noticeMapper = noticeMapper;
    }

}
