package com.ningpai.common.util;

import com.ningpai.common.bean.EmailServer;
import com.ningpai.common.dao.EmailServerMapper;
import com.ningpai.info.dao.MessageSendMapper;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * 邮件发送工具类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月13日 下午5:08:11
 * @version 1.0
 */
@Service("emailUtilsSite")
public class EmailUtils {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(EmailUtils.class);

    private static final String PEX_HREF = "<a href='";
    private static final String USERNAME = "--username--";
    private static final String URL = "--url--";

    /**
     * 消息设置DAO接口
     */
    @Resource(name = "MessageSendMapper")
    public MessageSendMapper messageSendMapper;

    private static EmailServer emailServer;

    /**
     * 查询邮箱服务器信息
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
     * 账户中心 验证邮件
     * 
     * @param request
     * @param user
     * @return
     */
    public int sendBindEmail(HttpServletRequest request, CustomerAllInfo user) {
        // 根据id查询Subject字段
        String subject = messageSendMapper.findSubjectMapper(13);
        try {
            // 获得客户端发送请求的完整url
            String url = request.getRequestURL().toString();
            // 截取字符串
            url = url.substring(0, url.lastIndexOf("/"));
            url = url.substring(0, url.lastIndexOf("/"));
            // 生成账户激活链接
            String str = GenerateLinkUtils.generateActivateLink(request, user);
            // 根据id查询Text字段
            String a = messageSendMapper.findTextMapper(13);
            // 定义链接标签
            String href = PEX_HREF + str + "'style='font:14px tahoma,arial,\5b8b\4f53;color:#005aa0;line-height:180%;text-decoration:none;'>邮箱验证链接</a>";
            // 替换text文本
            String f1 = a.replace(USERNAME, user.getCustomerUsername());
            String f2 = f1.replace(URL, href);
            // 发送短信
            TransportUtil.sendMessage(user.getInfoEmail(), f2, subject, request);
            return 1;
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 忘记密码： 验证邮件
     *
     * @param request
     * @param user
     * @return
     */
    public int forgetsendBindEmail(HttpServletRequest request, CustomerAllInfo user) {
        // 根据id查询Subject字段
        String subject = messageSendMapper.findSubjectMapper(13);
        try {
            // 获得客户端发送请求的完整url
            String url = request.getRequestURL().toString();
            // 截取字符串
            url = url.substring(0, url.lastIndexOf("/"));
            url = url.substring(0, url.lastIndexOf("/"));
            // 生成账户激活链接
            String str = GenerateLinkUtils.forgetgenerateActivateLink(request, user);
            // 根据id查询Text字段
            String a = messageSendMapper.findTextMapper(13);
            // 定义链接标签
            String href = PEX_HREF + str + "'style='font:14px tahoma,arial,\5b8b\4f53;color:#005aa0;line-height:180%;text-decoration:none;'>邮箱验证链接</a>";
            // 替换text文本
            String f1 = a.replace(USERNAME, user.getCustomerUsername());
            String f2 = f1.replace(URL, href);
            // 发送短信
            TransportUtil.sendMessage(user.getInfoEmail(), f2, subject, request);
            return 1;
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 找回密码 验证邮件
     * 
     * @param request
     * @param user
     * @return
     */
    public int sendFindPwdEmail(HttpServletRequest request, CustomerAllInfo user) {
        // 根据id查询Subject字段
        String subject = messageSendMapper.findSubjectMapper(3);
        try {
            // 获得客户端发送请求的完整url
            String url = request.getRequestURL().toString();
            // 截取字符串
            url = url.substring(0, url.lastIndexOf("/"));
            url = url.substring(0, url.lastIndexOf("/"));
            // 生成账户激活链接
            String str = GenerateLinkUtils.generateFindPwdLink(request, user);
            // 根据id查询Text字段
            String a = messageSendMapper.findTextMapper(3);
            // 定义链接标签
            String href = PEX_HREF + str + "'style='font:14px tahoma,arial,\5b8b\4f53;color:#005aa0;line-height:180%;text-decoration:none;'>找回密码验证链接</a>";
            // 替换text文本
            String f1 = a.replace(USERNAME, user.getCustomerUsername());
            String f2 = f1.replace(URL, href);
            // 发送短信
            TransportUtil.sendMessage(user.getInfoEmail(), f2, subject, request);
            return 1;
        } catch (Exception e) {
            LOGGER.info(e);
            return 0;
        }
    }

    /**
     * 安全中心 验证邮件
     * 
     * @param request
     * @param user
     * @return
     */
    public int sendCheckIdEmail(HttpServletRequest request, CustomerAllInfo user) {
        // 根据id查询Subject字段
        String subject = messageSendMapper.findSubjectMapper(11);
        String str = null;
        try {
            // 生成找回密码链接
            str = GenerateLinkUtils.generateCheckEmailLink(request, user);
        } catch (Exception e) {
            LOGGER.error("验证邮箱出错！" + e);
            str = null;
        }
        // 返回地址
        String url = request.getRequestURL().toString();
        // 截取字符串
        url = url.substring(0, url.lastIndexOf("/"));
        url = url.substring(0, url.lastIndexOf("/"));
        // 根据id查询Text字段
        String a = messageSendMapper.findTextMapper(11);
        // 定义链接标签
        String href = PEX_HREF + str + "'style='font:14px tahoma,arial,\5b8b\4f53;color:#005aa0;line-height:180%;text-decoration:none;'>安全中心验证链接</a>";
        // 替换text文本
        String f1 = a.replace(USERNAME, user.getCustomerUsername());
        String f2 = f1.replace(URL, href);
        try {
            // 发送短信
            TransportUtil.sendMessage(user.getInfoEmail(), f2, subject, request);
        } catch (MessagingException e) {
            LOGGER.error("验证邮箱出错！" + e);
            return 0;
        }
        return 1;
    }

    /**
     * 获取session
     */
    public void getSession() {

        // 读取数据库数据
        emailServer = emailServerMapper.selectEmailServer();

    }

}
