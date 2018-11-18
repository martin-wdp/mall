package com.ningpai.site.group.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.service.CustomerReplySiteService;
import com.ningpai.util.ValueUtil;

/**
 * 用户评论Controller
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class CustomerReplySiteController {

    private static final Logger LOGGER = Logger.getLogger(CustomerReplySiteController.class);

    private static final String QUOTEMT = "<div class=\"quote mt15\">";

    private static final String QUOTEFROM = "<p class=\"quote_from\">回复 ";

    private static final String PUITL = "：</p>";

    private static final String PU = "</p>";

    private static final String QUOTESTRING = "<p class=\"lh150 mt10\">";

    private static final String DIV = "</div>";

    private PrintWriter pw;

    private CustomerReplySiteService replySiteService;

    // spring 注解 会员service
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 发表小组相片评论
     * 
     * @param response
     * @param request
     * @param reply
     * @param flag
     * @return
     */
    @RequestMapping("/pubgroupimgreply")
    public ModelAndView pubGroupImgReply(HttpServletResponse response, HttpServletRequest request, CustomerReply reply, Long reId, String flag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        try {
            pw = response.getWriter();
            if (customerId != null) {
                CustomerReply re = replySiteService.selectReply(reId);
                if ("1".equals(flag)) {
                    String h = "";
                    h += QUOTEMT;
                    h += "<p class=\"quote_from\">引用  " + re.getCustomerName() + PUITL;
                    h += QUOTESTRING + re.getReplyContent() + PU;
                    h += DIV;
                    reply.setReplyRemark(h);
                } else if ("0".equals(flag)) {
                    String h = "";
                    h += QUOTEMT;
                    h += QUOTEFROM + re.getCustomerName() + PUITL;
                    h += QUOTESTRING + re.getReplyContent() + PU;
                    h += DIV;
                    reply.setReplyRemark(h);
                }
                pw.print(replySiteService.pubGroupImgReply(reply, customerId, flag, re != null ? re.getCustomerId() : null));
                // 非空验证 评论回复人
                if (null != re.getCustomerName()) {
                    // 日志记录
                    LOGGER.info("【" + re.getCustomerName() + "】发表小组相片评论成功");
                }
            } else {
                pw.print(-1);
            }
        } catch (IOException e) {
            LOGGER.error("发表小组相片评论失败！", e);
        }
        return null;
    }

    /**
     * 删除评论
     * 
     * @param response
     * @param request
     * @param replyId
     * @return
     */
    @RequestMapping("/deletereply")
    public ModelAndView deleteReply(HttpServletResponse response, HttpServletRequest request, Long replyId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        // 根据主键获取单个的评论对象
        CustomerReply re = replySiteService.selectReply(replyId);
        try {
            pw = response.getWriter();
            pw.print(replySiteService.delReply(replyId));
            // 非空验证 评论发布人
            if (null != re.getCustomerName()) {
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "删除评论",
                        "删除评论-->评论发布人【" + re.getCustomerName() + "】-->用户名：" + customerAllInfo.getCustomerUsername());
                // 日志记录
                LOGGER.info("删除【" + re.getCustomerName() + "】的评论");
            }

        } catch (IOException e) {
            LOGGER.error("删除评论失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 发表小组话题评论
     * 
     * @param response
     * @param request
     * @param reply
     * @param flag
     * @return
     */
    @RequestMapping("/pubgrouptopicreply")
    public ModelAndView pubGroupTopicReply(HttpServletResponse response, HttpServletRequest request, CustomerReply reply, Long reId, String flag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        try {
            pw = response.getWriter();
            if (customerId != null) {
                CustomerReply re = replySiteService.selectReply(reId);
                if ("1".equals(flag)) {
                    String h = "";
                    h += QUOTEMT;

                    h += "<p class=\"quote_from\">引用  " + re.getCustomerName() + PUITL;
                    h += QUOTESTRING + re.getReplyContent() + PU;
                    h += DIV;
                    reply.setReplyRemark(h);
                } else if ("0".equals(flag)) {
                    String h = "";
                    h += QUOTEMT;

                    h += QUOTEFROM + re.getCustomerName() + PUITL;
                    h += QUOTESTRING + re.getReplyContent() + PU;
                    h += DIV;
                    reply.setReplyRemark(h);
                }
                // 非空验证 评论发布人
                if (null != re.getCustomerName()) {
                    // 日志记录
                    LOGGER.info("【" + re.getCustomerName() + "】发表评论成功！");
                }

                pw.print(replySiteService.pubGroupTopicReply(reply, customerId, flag, re != null ? re.getCustomerId() : null));
            } else {
                pw.print(-1);
            }
        } catch (IOException e) {
            LOGGER.error("发表小组话题评论失败！", e);
        }
        return null;
    }

    /**
     * 发表心情评论
     * 
     * @param response
     * @param request
     * @param reply
     * @param flag
     * @return
     */
    @RequestMapping("/pubmoodreply")
    public ModelAndView pubMoodReply(HttpServletResponse response, HttpServletRequest request, CustomerReply reply, String flag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute("customerId");
        try {
            pw = response.getWriter();
            if (customerId != null) {
                CustomerReply re = replySiteService.selectReply(reply.getReplyParentId());
                if ("1".equals(flag)) {
                    String h = "";
                    h += QUOTEMT;

                    h += QUOTEFROM + re.getCustomerName() + PUITL;
                    h += QUOTESTRING + re.getReplyContent() + PU;
                    h += DIV;
                    reply.setReplyRemark(h);
                }
                pw.print(replySiteService.sendmoodreply(reply, customerId, flag, re != null ? re.getCustomerId() : null));
            } else {
                pw.print(-1);
            }
            // 非空验证 评论发布人
            if (null != reply.getCustomerName()) {
                // 日志记录
                LOGGER.info("【" + reply.getCustomerName() + "】发表心情评论成功！");
            }
        } catch (IOException e) {
            LOGGER.error("发表心情评论失败！", e);
        }
        return null;
    }

    /**
     * 删除并拉黑
     * 
     * @param response
     * @param reply
     *            用户评论 {@link com.ningpai.group.bean.CustomerReply}
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/delandblack")
    public ModelAndView delAndBlack(HttpServletRequest request, HttpServletResponse response, CustomerReply reply, Long groupId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            // 当前登录会员id
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
            // 当前登录成功的会员信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
            pw = response.getWriter();
            pw.print(replySiteService.delAndBlack(reply, groupId));
            // 非空验证 评论发布人
            if (null != reply.getCustomerName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "删除评论并拉黑",
                        "删除评论并拉黑-->评论发布人【" + reply.getCustomerName() + "】-->用户名：" + customerAllInfo.getCustomerUsername());
                // 日志记录
                LOGGER.info("删除【" + reply.getCustomerName() + "】发表评论。");
            }
        } catch (IOException e) {
            LOGGER.error("删除并拉黑失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    // spring 注入属性
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public CustomerReplySiteService getReplySiteService() {
        return replySiteService;
    }

    @Resource(name = "CustomerReplySiteService")
    public void setReplySiteService(CustomerReplySiteService replySiteService) {
        this.replySiteService = replySiteService;
    }
}
