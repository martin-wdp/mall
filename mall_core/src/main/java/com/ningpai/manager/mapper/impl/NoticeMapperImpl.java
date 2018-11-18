/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.mapper.NoticeMapper;
import com.ningpai.util.OrderInfoBean;

/**
 * @see com.ningpai.manager.mapper.NoticeMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 上午10:04:44
 * @version 0.0.1
 */
@Repository("noticeMapper")
public class NoticeMapperImpl extends BasicSqlSupport implements NoticeMapper {
    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.NoticeMapper#selectNotice()
     */
    @Override
    public List<OrderInfoBean> selectNotice(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.manager.mapper.NoticeMapper.selectNotice",
                paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.NoticeMapper#selectNoticeNum()
     */
    @Override
    public Long selectNoticeNum() {
        return this
                .selectOne("com.ningpai.manager.mapper.NoticeMapper.selectNoticeNum");
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.updateNotice
     */
    @Override
    public int updateNotice() {
        return this
                .update("com.ningpai.manager.mapper.NoticeMapper.updateNotice");
    }

    /**
     * 更具id查询新增消息状态
     * 
     * @param orderId
     * @return
     */
    @Override
    public int updateById(Long orderId) {
        return this.update(
                "com.ningpai.manager.mapper.NoticeMapper.updateById", orderId);
    }

}
