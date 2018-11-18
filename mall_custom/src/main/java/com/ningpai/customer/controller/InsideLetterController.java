/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.customer.service.InsideLetterServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.smscommon.bean.Sms;
import com.ningpai.util.MSMSendUtil;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ningpai.smscommon.dao.SmsMapper;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 站内信控制层
 * 
 * @author jiping
 * @since 2014年8月7日 下午12:01:56
 * @version 0.0.1
 */
@Controller
public class InsideLetterController {
    // spring注解
    private InsideLetterServiceMapper insiderLetterService;
    @Resource(name = "smsMapperCore")
    private SmsMapper mapper;
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            InsideLetterController.class);

    public InsideLetterServiceMapper getInsiderLetterService() {
        return insiderLetterService;
    }

    @Resource(name = "insideLetterServiceMapper")
    public void setInsiderLetterService(
            InsideLetterServiceMapper insiderLetterService) {
        this.insiderLetterService = insiderLetterService;
    }

    /**
     * 发送站内信息
     * 
     * @return
     */
    @RequestMapping("/sendinsideletter")
    @ResponseBody
    public Object addInsideLetter(InsideLetter insideLetter) {
        // 非空验证 站内信息标题
        if (null != insideLetter.getLetterTitle()) {
            try {
                insideLetter.setLetterTitle(java.net.URLDecoder.decode(
                        insideLetter.getLetterTitle(), "UTF-8"));
                insideLetter.setLetterContent(java.net.URLDecoder.decode(
                        insideLetter.getLetterContent(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                // 日志记录
                LOGGER.info("发送站内信息错误" + e);
            }
            // 日志记录
            LOGGER.info("发送标题为:" + insideLetter.getLetterTitle() + "的站内信息！");
        }
        return insiderLetterService.insertSelective(insideLetter);
    }

    /**
     * 根据会员id添加站内信息
     *
     * @return
     */
    @RequestMapping("/insertNotices")
    @ResponseBody
    public Object insertNotices(InsideLetter insideLetter, Long[] customerIds) {
        // 非空验证 站内信息标题
        if (null != insideLetter.getLetterTitle()) {
            // 日志记录
            LOGGER.info("发送标题为:" + insideLetter.getLetterTitle() + "的站内信息！");
        }
        // 发送短信
        if (insideLetter.getIsSms() != null && insideLetter.getIsSms().equals("1")) {
            Sms sdem=new Sms();
            sdem=mapper.selectSms();
            //sms.setMsgContext(null);
            //短信接口信息和手机号码的基础验证
            if (sdem == null) {
                return 0;
            }
            String phone = "";
            String phones ="" ;
            CustomerAllInfo customerAllInfo =null;
            for(Long customerId :customerIds){
                customerAllInfo = customerServiceMapper.selectByPrimaryKey(customerId) ;
                phone = customerAllInfo==null?"":customerAllInfo.getCustomerUsername();
                if (isMobile(phone)) {
                    phones += ("," + phone );
                }
            }
            sdem.setSendSim(phones);
            sdem.setMsgContext(insideLetter.getLetterContent());
            boolean flag = false ;
            try {
                flag =  MSMSendUtil.sendMsm("", sdem.getLoginName(), sdem.getPassword(), new String[]{sdem.getSendSim()}, sdem.getMsgContext(), sdem.getSmsKind(), sdem.getHttpUrl());
            } catch (IOException e) {
                //e.printStackTrace();
            }finally {
                if(!flag){
                    return 0;
                }
            }
        }
        return insiderLetterService.insertNotices(insideLetter, customerIds);
    }


    /**
     * 手机号验证
     *
     * @param  str
     * @return 验证通过返回true
     */
    private  boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,7,5,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

}
