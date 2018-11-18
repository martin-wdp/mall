package com.qpmall.site.register.service.impl;

import com.ningpai.common.bean.Sms;
import com.ningpai.common.dao.SmsMapper;
import com.ningpai.common.util.SmsPost;
import com.ningpai.util.MyLogger;
import com.ningpai.util.StringUtil;
import com.qpmall.site.register.bean.VerificationInfo;
import com.qpmall.site.register.service.RegisterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * add by luyong
 * Created by ly-qpmall on 2015/9/21.
 */
@Service("registerServiceSite")
public class RegisterServiceImpl implements RegisterService {


    /**
     * 邮箱服务器数据接口层
     */
    private SmsMapper mapper;

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(RegisterServiceImpl.class);
    /**
     * 注册接口层
     */
//    private RegisterMapper registerMapper;

    /**
     * 0--短信接口信息获取失败  1--成功  -1 --手机格式不对
     * <p/>
     * -2 操作时间间隔短  -3 连续操作次数太多
     * -4 验证码发送失败 -5 发送验证码接口执行IO异常
     *
     * @param request
     * @param moblie  目标手机
     * @return
     */
    public int sendPost(HttpServletRequest request, String moblie) {
        // 查询短信信息
       // Sms sms = new Sms();

        Sms sdem=new Sms();
        sdem=mapper.selectSms();
        //sms.setMsgContext(null);
        //短信接口信息和手机号码的基础验证
        if (sdem == null) {
            return 0;
        }
        //2016-01-21 屏蔽后台手机号码校验
        /*if (!StringUtil.isMobile(moblie)) {
            return -1;
        }*/
        //session中的该手机用户的之前的获取验证码的相关信息的获取和初始化的处理
        //次数 最高为3次
        /*int getSmsTimes =
                request.getSession().getAttribute(moblie + "getSmsTimes") == null ? 3 : (int) request.getSession().getAttribute(moblie + "getSmsTimes");*/
        //最后执行时间
        Date lastSmsTime = (Date) request.getSession().getAttribute(moblie + "lastSmsTime");
        //对这些参数的逻辑校验
        Long timeMill = null;
        if (lastSmsTime != null) {
            timeMill = System.currentTimeMillis() - lastSmsTime.getTime();
        } else {
            timeMill = 10 * 60 * 1000l;
        }
        //验证码时间间隔小于一分钟
        if (timeMill < (60 * 1000)) {
            LOGGER.info("手机号码：" + moblie + "注册时候" + "发送验证码时操作时间离太短！");
            return -2;
        }
        //连续3次连续操作后该用户只能5分钟以后再进行发送验证码的操作
        /*if (timeMill < (1 * 60 * 1000) && getSmsTimes == 0) {
            LOGGER.info("手机号码：" + moblie + "注册时候" + "发送验证码时操作过于频繁！");
            return -3;
        }*/
        /*if (timeMill < (1 * 60 * 1000)) {
            LOGGER.info("手机号码：" + moblie + "注册时候" + "发送验证码时操作过于频繁！");
            return -3;
        }*/
        //超过5分钟重置连续尝试发送短信的次数
       /* if (timeMill >= (5 * 60 * 1000)) {
            request.getSession().setAttribute(moblie + "getSmsTimes", 3);
            getSmsTimes = 3;
        }*/
        //检验通过可以执行短信的发送
        //产生验证码
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        //初始化SMS短信结构相关参数
        sdem.setSendSim(moblie);
        //执行SMS的sendPost发送验证码
        //发送验证码失败
        sdem.setMsgContext(((Integer) num).toString());
        try {
            if (!SmsPost.sendPost(sdem,"1"))
            {
                //接口执行失败打印失败日志
                LOGGER.info("手机号码：" + moblie + "注册时候" + "发送验证码失败！");
                return -4;
            }
        } catch (Exception e) {
            //打印异常日志Exception
            LOGGER.error("手机号码：" + moblie + "注册时候" + "发送验证码失败！执行中出现异常，异常信息：" +
                    e.getMessage());
            return -5;
        }
        //验证码发送成功 打印成功日志保存更新验证码的相关信息返回1
        LOGGER.info("手机号码：" + moblie + "注册时候" + "发送验证码成功！");
        //发送短信发送前的session参数的更新
        //次数更新
      /*  request.getSession().setAttribute(moblie + "getSmsTimes", --getSmsTimes);*/
        //最后一次的发送时间
        request.getSession().setAttribute(moblie + "lastSmsTime", new Date(System.currentTimeMillis()));
        // 手机验证码 存入到session中
        request.getSession().setAttribute(moblie + "codeMobileNum", num);
        return 1;
    }


    /**
     * 更新验证码
     *
     * @param verificationInfo
     * @return
     */

    public int updateVerification(VerificationInfo verificationInfo) {
        return 0;
        //registerMapper.updateVerification(verificationInfo);
    }

    /**
     * 添加验证码
     *
     * @param verificationInfo
     * @return
     */
    public int insertVerification(VerificationInfo verificationInfo) {
        return 0;
        //registerMapper.insertVerification(verificationInfo);
    }

    /**
     * 根据手机号码查询验证码信息
     *
     * @param mobilePhone
     * @return
     */

    public VerificationInfo selectVerificationByNumber(String mobilePhone) {
        return null;
        //registerMapper.selectVerificationByNumber(mobilePhone);
    }


    public SmsMapper getMapper() {
        return mapper;
    }

    @Resource(name = "smsMapperSite")
    public void setMapper(SmsMapper mapper) {
        this.mapper = mapper;
    }

   /* public RegisterMapper getRegisterMapper() {
        return registerMapper;
    }

    @Resource(name = "lyregisterMapperSite")
    public void setRegisterMapper(RegisterMapper registerMapper) {
        this.registerMapper = registerMapper;
    }*/
}
