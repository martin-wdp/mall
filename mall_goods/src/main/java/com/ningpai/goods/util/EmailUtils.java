package com.ningpai.goods.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Authenticator;
import javax.mail.Message.RecipientType;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.Customer;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.Sms;
import com.ningpai.goods.dao.EmailServersMapper;
import com.ningpai.goods.dao.SmsMapper;
import com.ningpai.goods.vo.EmailServer;
import com.ningpai.logger.util.OperaLogUtil;

/**
 * 邮件发送工具类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月13日 下午5:08:11
 * @version 1.0
 */
@Service("emailUtilsSites")
public class EmailUtils {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(EmailUtils.class);

    private static final String HTMLTAG = "<a href='";

    private static EmailServer emailServer;

    private EmailServersMapper emailServerMapper;

    private SmsMapper mapper;

    /**
     * 降价发邮件通知
     * 
     * @param request
     * @param infoEmail
     * @return
     */
    public int sendEmails(HttpServletRequest request, String infoEmail, GoodsProduct goodsProduct) {
        Session session = getSession();
        session.setDebug(true);
        MimeMessage message = new MimeMessage(session);
        try {
            // 取消价格后面的小数
            String price = goodsProduct.getOfollowPrice().toString();
            // 按逗号截取价格
            price = price.substring(0, price.indexOf("."));
            // 把价格转换成BigDecimal格式
            BigDecimal decima = new BigDecimal(price);
            // 关注商品收藏的价格赋值
            goodsProduct.setOfollowPrice(decima);

            message.setSubject("降价通知");
            message.setSentDate(new Date());
            message.setFrom(new InternetAddress(emailServer.getSmtpaccount()));
            message.setRecipient(RecipientType.TO, new InternetAddress(infoEmail));
            // 获得客户端发送请求的完整url
            String url = request.getRequestURL().toString();
            url = url.substring(0, url.lastIndexOf("/"));
            url = url.substring(0, url.lastIndexOf("/"));
            String a = "<!DOCTYPE html><html><head><meta charset='UTF-8'><title>邮箱验证</title></head><body><div style='width:800px;margin:20px auto;padding:0;'><table style='width:100%;'><tr><td style='text-align:left;'>"
                    + HTMLTAG + url + "'><img alt=''src='http://101.200.195.229:10000/M00/00/1A/wKgVRlYvA6KAeTpQAAAcsAFfVw0536.jpg'/></a></td><td style='text-align:right;'>";
            a += HTMLTAG
                    + url
                    + "/customer/index.html'style='font-family:microsoft YaHei;font-size:14px;color:#555;background:url(images/member_center.png) no-repeat left center;padding-left:20px;text-decoration:none;margin-left:10px;font-weight:700;'>个人中心</a></td></tr></table>"
                    + "<div style='margin-top:10px;overflow:hidden;text-align:center'><a href='demo.ningpai.com'><img alt=''src='http://youtuding.b0.upaiyun.com/1405417459486.jpg'/></a></div><div style='margin:0;padding:20px;'><h1 style='font-family:microsoft YaHei;font-size:18px;color:#333;font-weight:500;margin:0 0 10px;padding:0;'>";

            if (null != goodsProduct.getOfollowPrice()) {
                a += "尊敬的千品猫商城用户，您好:</h1><p style='font:14px tahoma,arial,\5b8b\4f53;color:#555;margin:0;padding:0;line-height:180%;'>您好亲！您所关注的" + goodsProduct.getDisName()
                        + "地区的,商品名为：" + goodsProduct.getGoodsInfoName() + "的商品，由" + goodsProduct.getOfollowPrice() + "元,降到了" + goodsProduct.getNfollowPrice() + "元,快来选购吧!</p>";
            } else {
                a += "尊敬的千品猫商城用户，您好:</h1><p style='font:14px tahoma,arial,\5b8b\4f53;color:#555;margin:0;padding:0;line-height:180%;'>我们的" + goodsProduct.getGoodsInfoName()
                        + "商品降价啦，快来选购吧!</p>";
            }
            a += "<p style='font:14px tahoma,arial,\5b8b\4f53;color:#555;margin:0;padding:0;line-height:180%;'><br/>如有任何疑问，请拔打热线：400-087-6599</p></div><div style='font:18px microsoft YaHei;color:#0054a7;height:44px;line-height:44px;text-align:center;background:#dbedff;'>感谢您使用qpmall，祝您购物愉快</div><p style='font:14px tahoma,arial,\5b8b\4f53;color:#555;margin:0;padding:20px;line-height:180%;'>收到此邮件，说明您已是千品猫商城尊贵的会员。<br/>为确保您接受的服务信息不被当做垃圾邮件处理，请将<a href='javascript:;'style='color:#005aa0;'>no-reply@qpmall.com</a>添加为联系人。</p><div style='font:14px tahoma,arial,\5b8b\4f53;color:#999;text-align:center;margin-top:10px;'>北京千品猫科技有限公司版权所有 京ICP备15056593号-1</div></div></body></html>";
            message.setContent(a, "text/html;charset=utf-8");
            // HTMLTAG + str+"'>亲爱的用户，请点击验证邮箱</a>","text/html;charset=utf-8"
            Transport.send(message);
            return 1;
        } catch (Exception e) {
            LOGGER.error(""+e);
            return 0;
        }
    }

    /**
     * 获取session
     *
     * */
    public Session getSession() {

        // 读取数据库数据
        emailServer = emailServerMapper.selectEmailServer();
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", emailServer.getSmtpserver());
        props.setProperty("mail.smtp.port", emailServer.getSmtpport());
        props.setProperty("mail.smtp.auth", "true");
        // 新添加的
        props.put("mail.smtp.socketFactory.fallback", "true");
        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(emailServer.getSmtpaccount(), emailServer.getSmtppass());
            }

        });
    }

    /**
     * 提交方式
     *
     * */
    public int sendPosts(HttpServletRequest request, GoodsProduct goodsProduct, String mobile) {
        Sms sms = mapper.selectSms();
        if (sms == null) {
            return 0;
        }
        try {
            SmsPosts.sendPost(sms, goodsProduct, mobile);
        } catch (IOException e) {
            LOGGER.error(""+e);
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        return 1;
    }

    public EmailServersMapper getEmailServersMapper() {
        return emailServerMapper;
    }

    @Resource(name = "emailServerMapperSites")
    public void setEmailServersMapper(EmailServersMapper emailServerMapper) {
        this.emailServerMapper = emailServerMapper;
    }

    public SmsMapper getMapper() {
        return mapper;
    }

    @Resource(name = "smsMapperSites")
    public void setMapper(SmsMapper mapper) {
        this.mapper = mapper;
    }
}
