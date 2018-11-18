/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.service;

import com.ningpai.info.bean.MessageBean;
import com.ningpai.util.PageBean;
/**
 * 消息设置Service借口
 * @author huangyi
 *
 */
public interface MessageService {
    /**
     * 查询消息设置
     * @param informType
     * @param informStatus
     * @return MessageBean
     */
    MessageBean selectInform(int informType,int informStatus);
    /**
     * 修改消息设置
     * @param message
     * @return int
     */
    int updateInform(MessageBean message);
    /**
     * 分页查询列表
     * @param pb
     * @return PageBean
     */
    PageBean selectList(PageBean pb);
    /**
     * 根据id查询
     * @param informId
     * @return MessageBean
     */
    MessageBean selectById(int informId);
    /**
     * 根据id查询出subject字段
     * @param informId
     * @return String
     */
    String findSubject(int informId);
    /**
     * 根据id查询出text字段
     * @param informId
     * @return String
     */
    String findText(int informId);
}
