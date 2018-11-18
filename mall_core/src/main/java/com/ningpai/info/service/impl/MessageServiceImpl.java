/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.info.bean.MessageBean;
import com.ningpai.info.dao.MessageSendMapper;
import com.ningpai.info.service.MessageService;
import com.ningpai.util.PageBean;

/**
 * 消息设置servie实现类
 * 
 * @author huangyi
 *
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {

    /**
     * 发送消息服务接口
     */
    @Resource(name = "MessageSendMapper")
    public MessageSendMapper messageSendMapper;

    /*
     * 
     * @see com.ningpai.info.service.MessageService#selectInform(int, int)
     */
    @Override
    @Transactional
    public MessageBean selectInform(int informType, int informStatus) {
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("informType", informType);
        map.put("informStatus", informStatus);
        // 查询
        return messageSendMapper.selectInformMapper(map);
    }

    /*
     * 
     * @see
     * com.ningpai.info.service.MessageService#updateInform(com.ningpai.info
     * .bean.MessageBean)
     */
    @Override
    public int updateInform(MessageBean message) {
        // 修改
        return messageSendMapper.updateInformMapper(message);
    }

    /*
     * 
     * @see
     * com.ningpai.info.service.MessageService#selectList(com.ningpai.util.PageBean
     * )
     */
    @Override
    public PageBean selectList(PageBean pb) {
        // 查询列表
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询总数
        pb.setRows(messageSendMapper.selectAllSize(map));
        // 设置查询参数
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        // 查询列表
        pb.setList(messageSendMapper.selectListMapper(map));
        return pb;
    }

    /*
     * 
     * @see com.ningpai.info.service.MessageService#selectById(int)
     */
    @Override
    public MessageBean selectById(int informId) {
        // 根据ID查询
        return messageSendMapper.selectByIdMapper(informId);
    }

    /*
     * 
     * @see com.ningpai.info.service.MessageService#findSubject(int)
     */
    @Override
    public String findSubject(int informId) {
        // 根据ID查询
        return messageSendMapper.findSubjectMapper(informId);
    }

    /*
     * 
     * @see com.ningpai.info.service.MessageService#findText(int)
     */
    @Override
    public String findText(int informId) {
        // 根据ID查询
        return messageSendMapper.findTextMapper(informId);
    }
}
