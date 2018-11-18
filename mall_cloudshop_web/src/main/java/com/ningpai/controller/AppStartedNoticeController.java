package com.ningpai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 应用启动成功后，调用appStartedNotice，返回1表示可以访问了
 * 若容器未启动，会自动返回404
 * Created by HEHU on 2015/8/24.
 * @date 2015-9-21 19:51:43
 * @version 1.0
 *
 */
@Controller
public class AppStartedNoticeController {
    /** 返回1表示可以访问了*/
    private static final int START_SUCCESS = 1;

    /**
     * 应用启动成功后，调用appStartedNotice，
     * 返回1表示可以访问了，否则容器会自动返回404，
     * @return 返回1表示可以访问了
     */
    @RequestMapping("appStartedNotice")
    @ResponseBody
    public Integer appStartedNotice() {
        //返回启动状态
        return START_SUCCESS;
    }
}
