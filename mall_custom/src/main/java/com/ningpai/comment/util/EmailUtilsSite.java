package com.ningpai.comment.util;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ningpai.comment.bean.EmailServerSite;
import com.ningpai.comment.dao.EmailServerSiteMapper;

/**
 * 邮件发送工具类
 * 
 * @author jiping
 * @since 2014年7月18日 上午10:43:40
 * @version 0.0.1
 */
@Service("emailSiteUtils")
public class EmailUtilsSite {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(EmailUtilsSite.class);

    private static EmailServerSite emailServerSite;
    // spring注入
    private EmailServerSiteMapper essm;

    @Resource(name = "emailSiteMapper")
    public void setEssm(EmailServerSiteMapper essm) {
        this.essm = essm;
    }

    /**
     * 用户反馈发送邮件
     */
    public int sendToStore(String toEmail, String content) {
        Session session = getSession();
        try {
            // Instantiate a message
            Message msg = new MimeMessage(session);
            // Set message attributes
            // 发件人地址
            msg.setFrom(new InternetAddress(emailServerSite.getSmtpaccount()));
            InternetAddress[] address = { new InternetAddress(toEmail) };
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("后台用户反馈");
            msg.setSentDate(new Date());
            msg.setContent(content, "text/html;charset=gbk");
            // Send the message
            Transport.send(msg);
            return 1;
        } catch (Exception mex) {
            LOGGER.error("用户反馈发送邮件失败，请查看原因：", mex);
            return 0;
        }
    }

    /**
     * 获取session
     * */
    public Session getSession() {
        // 读取数据库数据
        emailServerSite = essm.selectEmailServer();
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        // SMTP服务器
        props.setProperty("mail.smtp.host", emailServerSite.getSmtpserver());
        // SMTP端口号
        props.setProperty("mail.smtp.port", emailServerSite.getSmtpport());
        props.setProperty("mail.smtp.auth", "true");
        // 新添加的
        props.put("mail.smtp.socketFactory.fallback", "true");
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // SMTP账号、密码
                return new PasswordAuthentication(emailServerSite.getSmtpaccount(), emailServerSite.getSmtppass());
            }

        });
    }

    public EmailServerSiteMapper getEssm() {
        return essm;
    }

}
