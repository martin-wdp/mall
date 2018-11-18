package com.ningpai.third.register.service.impl;

import com.ningpai.third.register.bean.Sms;
import com.ningpai.third.register.bean.SmsPost;
import com.ningpai.third.register.mapper.RegisterMapper;
import com.ningpai.third.register.service.RegisterService;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by zhanghailong on 2015/5/7.
 */
@Service("registerService")
public class RegisterServiceImpl implements RegisterService {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(RegisterServiceImpl.class);

    @Resource(name = "registerMapper")
    private RegisterMapper registerMapper;

    @Override
    public int sendPost(HttpServletRequest request, String moblie) {
        Sms sms = registerMapper.selectSms();
        if (sms == null) {
            return 0;
        }
        sms.setSendSim(moblie);
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        request.getSession().setAttribute("mcCode", num);
        request.getSession().setAttribute("userMobile", moblie);
        sms.setMsgContext(((Integer) num).toString());
        try {
            if (SmsPost.sendPost(sms)) {
                return num;
            }
            return 0;
        } catch (IOException e) {
            LOGGER.error(""+e);
            return 0;
        }

    }

    @Override
    public int newsendPost(HttpServletRequest request, String mobile) {
        Sms sms = registerMapper.selectSms();
        if (sms == null) {
            return 0;
        }
        sms.setSendSim(mobile);
        int num = (int) ((Math.random() * 9 + 1) * 100000);

        sms.setMsgContext(((Integer) num).toString());
        try {
            SmsPost.sendPost(sms);
            request.getSession().setAttribute("mcCode", num);
            request.getSession().setAttribute("userMobile", mobile);
                return 1;
        } catch (IOException e) {
            LOGGER.error(""+e);
            return -1;
        }

    }
}
