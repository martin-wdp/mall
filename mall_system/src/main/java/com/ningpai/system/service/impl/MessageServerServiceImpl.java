/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.system.bean.MessageServer;
import com.ningpai.system.dao.MessageServerMapper;
import com.ningpai.system.service.MessageServerService;
import com.ningpai.util.PageBean;

/**
 * 短信服务器服务实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 下午5:18:49
 * @version
 */
@Service("messageServerService")
public class MessageServerServiceImpl implements MessageServerService {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(MessageServerServiceImpl.class);

    /** 短信服务器dao层 */
    @Resource(name = "messageServerMapper")
    private MessageServerMapper messageServerMapper;

    /**
     * 查询短信接口信息
     * 
     * @return MessageServer
     */
    public MessageServer findMessage() {

        return messageServerMapper.findMessage();
    }

    /**
     * 修改短信接口信息
     */

    public int updateMessage(MessageServer messageServer) {
        return messageServerMapper.updateByPrimaryKeySelective(messageServer);
    }

    /*
     * 根据ID查询短信接口
     * 
     * @see
     * com.ningpai.system.service.MessageServerService#getMessage(java.lang.
     * Long)
     */
    @Override
    public MessageServer getMessage(Long smsId) {
        return messageServerMapper.selectByPrimaryKey(smsId);
    }

    /*
     * 分页查询所有短信接口
     * 
     * @see
     * com.ningpai.system.service.MessageServerService#selectAllByPb(com.ningpai
     * .util.PageBean)
     */
    @Override
    public PageBean selectAllByPb(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        pb.setRows(this.messageServerMapper.selectAllCount());
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        pb.setList(this.messageServerMapper.selectAllByPb(map));
        return pb;
    }

    /*
     * 修改启用状态
     * 
     * @see
     * com.ningpai.system.service.MessageServerService#updateSmsUsedStatus(java
     * .lang.Long)
     */
    @Override
    public int updateSmsUsedStatus(Long smsId) {
        try {
            this.messageServerMapper.closeAll();
            MessageServer sms = this.messageServerMapper
                    .selectByPrimaryKey(smsId);
            sms.setIsOpen("1");
            return this.messageServerMapper.updateByPrimaryKeySelective(sms);
        } catch (Exception e) {
            LOGGER.error("",e);
            return -1;
        }
    }
}
