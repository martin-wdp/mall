/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.gift.service.GiftPicService;

/**
 * @author ggn 2014-03-21 赠品图片控制器
 */
@Controller
public class GiftPicController {

    private GiftPicService giftPicService;

    /**
     * 根据图片Id删除图片
     * 
     * @param picId
     * @return ModelAndView
     */
    @RequestMapping("/delgiftpicbypicid")
    @ResponseBody
    public int delGiftPicByPicId(Long picId) {
        return giftPicService.delGiftPicByPicId(picId);
    }

    public GiftPicService getGiftPicService() {
        return giftPicService;
    }

    @Resource(name = "GiftPicService")
    public void setGiftPicService(GiftPicService giftPicService) {
        this.giftPicService = giftPicService;
    }

}
