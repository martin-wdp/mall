/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.punishrecord.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.thirdproject.bean.ThirdProject;
import com.ningpai.util.PageBean;

/**
 * 惩罚记录的控制器
 */
@Controller
public class PunishRecordController {

    /**
     * 惩罚记录接口
     */
      @Resource(name="PunishRecordService")
      private PunishRecordService punishRecordService;

        /**
         *
         * @param request
         * @param pageBean 分页对象
         * @param thirdProject  第三方专题
         * @param n 头部导航栏索引
         * @param l 左侧导航栏索引
         * @return
         */
      @RequestMapping("queryPunishRecord")
      public ModelAndView queryPunishRecord(HttpServletRequest request,PageBean pageBean,ThirdProject thirdProject,String n,String l){
            //填充导航/左侧菜单索引 用于页面控制css选中
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
            //商家ID
            Long thirdId = (Long) request.getSession().getAttribute("thirdId");
            //设置跳转路径
             return new ModelAndView("punishrecord/punishrecordlist")
             //第三方显示处罚记录
             .addObject("pageBean", punishRecordService.selectRecordByPage(pageBean, thirdId));
      }

}
