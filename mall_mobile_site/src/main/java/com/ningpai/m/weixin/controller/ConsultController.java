/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.weixin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
/**
 * 咨询报价控制层
 * @author zhangsl
 * @since 2015年1月13日 下午5:05:50
 * @version
 */
@Controller
public class ConsultController {

    /**
     *
     * consultMoney
     * */
    @RequestMapping("consultMoney")
      public ModelAndView consultMoney() {
          return new ModelAndView("consult/xqlReg");  
      }
}
