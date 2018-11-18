/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ningpai.util.OrderInfoBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.manager.service.NoticeService;

/**
 * 消息控制类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 上午10:01:25
 * @version 0.0.1
 */
@Controller
public class NoticeController {

    private NoticeService noticeService;

    /**
     * 查询消息
     * 
     * @return 订单数量
     */
    @RequestMapping("/initNotice")
    @ResponseBody
    public Map<String, Object> initNoticeNum() {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long count = noticeService.selectNoticeNum();
        resultMap.put("count", count);
        List<OrderInfoBean> notice = noticeService.selectNotice(count);
        Date d = new Date();
        /*
         * 便利查询出的列表判断是否该订单超过7天
         */
        for (OrderInfoBean order : notice) {
            Date pt = order.getPayTime();
            Long id = order.getOrderId();
            /*
             * 如果超过7天则把该订单的新增消息状态改为‘1’
             */
            if ((pt.getTime() + 7 * 24 * 60 * 60 * 1000 - d.getTime()) < 0) {
                noticeService.updateById(id);
            }
        }
        // 从新查询赋值
        Long count1 = noticeService.selectNoticeNum();
        resultMap.put("list", noticeService.selectNotice(count1));
        return resultMap;
    }

    /**
     * 删除所有消息
     * 
     * @return
     */
    @RequestMapping("/updateNotice")
    @ResponseBody
    public int update() {
        return noticeService.updateNotice();
    }

    public NoticeService getNoticeService() {
        return noticeService;
    }

    @Resource(name = "noticeService")
    public void setNoticeService(NoticeService noticeService) {
        this.noticeService = noticeService;
    }

}
