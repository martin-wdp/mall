/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.util;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

/**
 * 邮件工具
 * 
 * @author NINGPAI-LIH
 * @since 2015年2月12日16:31:09
 *
 */
public class TransportUtil {
    private TransportUtil() {
    }

    /**
     * 发送短信
     * 
     * @param request
     * @param smtpaccount
     *            SMTP账号
     * @param infoEmail
     *            发送邮件地址
     * @param session
     * @param 邮件正文
     * @throws MessagingException
     */
    public static void sendMessage(HttpServletRequest request,
            String smtpaccount, String infoEmail, Session session, String a)
            throws MessagingException {
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        message.setSubject("验证身份");
        message.setSentDate(new Date());
        // 设置SMTP账号
        message.setFrom(new InternetAddress(smtpaccount));
        message.setRecipient(RecipientType.TO, new InternetAddress(infoEmail));
        message.setContent(a, "text/html;charset=utf-8");
        // 邮件发送
        Transport.send(message);
    }
}
