/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.util;

import com.ningpai.common.bean.EmailServer;
import com.ningpai.common.dao.EmailServerMapper;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 用户反馈邮件工具类
 * 
 * @author jiping
 * @since 2014年7月17日 下午5:11:37
 * @version 0.0.1
 */
@Service("feedBackEmail")
public class FeedBackEmail {

    public static final MyLogger LOGGER = new MyLogger(FeedBackEmail.class);

    private static EmailServer emailServer;
    /**
     * 邮箱服务器数据接口层
     */
    private EmailServerMapper emailServerMapper;

    public EmailServerMapper getEmailServerMapper() {
        return emailServerMapper;
    }

    @Resource(name = "emailServerMapperSite")
    public void setEmailServerMapper(EmailServerMapper emailServerMapper) {
        this.emailServerMapper = emailServerMapper;
    }

    /**
     * 用户反馈发送邮件
     * 
     * @return
     */
    public int sendToStore(String toEmail, String content) {
        //获取session信息
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
            // Set message attributes
            // 发件人地址
            msg.setFrom(new InternetAddress(emailServer.getSmtpaccount()));
            InternetAddress[] address = { new InternetAddress(toEmail) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("用户反馈");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=gbk");
            // Send the message
            Transport.send(msg);
            return 1;
        } catch (Exception mex) {
            LOGGER.error("",mex);
            return 0;
        }
    }

    /**
     * 获取session
     * @return session
     */
    public Session getSession() {
        // 读取数据库数据
        emailServer = emailServerMapper.selectEmailServer();
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        // SMTP服务器
        props.setProperty("mail.smtp.host", emailServer.getSmtpserver());
        // SMTP端口号
        props.setProperty("mail.smtp.port", emailServer.getSmtpport());
        props.setProperty("mail.smtp.auth", "true");
        // 新添加的
        props.put("mail.smtp.socketFactory.fallback", "true");
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // SMTP账号、密码
                return new PasswordAuthentication(emailServer.getSmtpaccount(), emailServer.getSmtppass());
            }

        });
    }
}
