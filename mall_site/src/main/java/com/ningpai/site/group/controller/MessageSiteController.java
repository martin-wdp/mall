package com.ningpai.site.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.message.bean.Message;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.ValueUtil;

/**
 * 
 * 消息前台控制器
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class MessageSiteController {

    private static final Logger LOGGER = Logger.getLogger(MessageSiteController.class);

    private static final String CUSTOMERID = "customerId";

    private static final String LOGIN_HTML = "login.html";

    private PrintWriter pw;

    private MessageSiteService messageSiteService;

    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 系统消息
     * 
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/systemmsg")
    public ModelAndView systemMsg(HttpServletRequest request, PageBean pb) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId == null) {
            return new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.SYSTEMMSG);
        } else {
            resultMap.put("msg", 1);
            // 系统消息
            resultMap.put("pb", messageSiteService.messagePb(customerId, "0", pb, null, "1"));
            return topAndBottomService.getTopAndBottom(new ModelAndView("message/mymessage")).addAllObjects(resultMap);
        }
    }

    /**
     * 已收的私信
     * 
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/lettermsg")
    public ModelAndView letterMsg(HttpServletRequest request, PageBean pb) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 根据主键 获取会员详细信息
        CustomerAllInfo customerAllInfo = customerServiceMapper.queryCustomerInfo(customerId);
        // 非空 验证用户名
        if (null != customerAllInfo.getCustomerUsername()) {
            LOGGER.info("查询【" + customerAllInfo.getCustomerUsername() + "】已经收到的私信！");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        } else {
            resultMap.put("msg", 2);
            // 已收私信
            resultMap.put("pb", messageSiteService.messagePb(customerId, "5", pb, null, "1"));
            return topAndBottomService.getTopAndBottom(new ModelAndView("message/lettermsg")).addAllObjects(resultMap);
        }
    }

    /**
     * 评论
     * 
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/commentmsg")
    public ModelAndView commentsMsg(HttpServletRequest request, PageBean pb) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        } else {
            // 根据主键 获取会员详细信息
            CustomerAllInfo customerAllInfo = customerServiceMapper.queryCustomerInfo(customerId);
            // 非空 验证用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                LOGGER.info("查询【" + customerAllInfo.getCustomerUsername() + "】下面的消息列表！");
            }
            resultMap.put("msg", 3);
            resultMap.put("pb", messageSiteService.messagePb(customerId, "4", pb, null, "1"));
            return topAndBottomService.getTopAndBottom(new ModelAndView("message/commentmsg")).addAllObjects(resultMap);
        }
    }

    /**
     * 已发的私信
     * 
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/sendlettermsg")
    public ModelAndView sendletterMsg(HttpServletRequest request, PageBean pb) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId == null) {
            return new ModelAndView(new RedirectView(LOGIN_HTML));
        } else {
            // 根据主键 获取会员详细信息
            CustomerAllInfo customerAllInfo = customerServiceMapper.queryCustomerInfo(customerId);
            // 非空 验证用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                LOGGER.info("查询【" + customerAllInfo.getCustomerUsername() + "】已发的私信！");
            }
            resultMap.put("msg", 4);
            resultMap.put("customer", customerServiceMapper.queryCustomerInfo(customerId));
            // 已发私信
            resultMap.put("pb", messageSiteService.messagePb(null, "5", pb, customerId, "3"));
            return topAndBottomService.getTopAndBottom(new ModelAndView("message/sendlettermsg")).addAllObjects(resultMap);
        }
    }

    /**
     * 根据消息接收id删除消息
     * 
     * @param response
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deletemsg")
    public ModelAndView deleteMessage(HttpServletResponse response, Long messageCustomerId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.deleteMessage(messageCustomerId));
            // 根据消息ID获取单个消息对象
            Message message = messageSiteService.selectMessageById(messageCustomerId);
            // 非空验证 消息标题
            if (null != message.getMessageTitle()) {
                LOGGER.info("删除标题为【" + message.getMessageTitle() + "】的消息！");
            }
        } catch (IOException e) {
            LOGGER.error("根据消息接收id删除消息失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return new ModelAndView();
    }

    /**
     * 批量删除消息
     * 
     * @param response
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deletebatchmsg")
    public ModelAndView delBatchMessage(HttpServletResponse response, @RequestParam("messageCustomerId[]") Long[] messageCustomerId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.deleteBatchMessage(messageCustomerId));
        } catch (IOException e) {
            LOGGER.error("批量删除消息失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return new ModelAndView();
    }

    /**
     * 单个标记已读状态
     * 
     * @param response
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/readmsg")
    public ModelAndView readMessage(HttpServletResponse response, Long messageCustomerId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.readMessage(messageCustomerId));
            // 根据消息ID获取单个消息对象
            Message message = messageSiteService.selectMessageById(messageCustomerId);
            // 非空验证 消息标题
            if (null != message.getMessageTitle()) {
                LOGGER.info("标记标题为【" + message.getMessageTitle() + "】的消息为已读状态！");
            }
        } catch (IOException e) {
            LOGGER.error("单个标记已读状态失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 批量标记已读标记
     * 
     * @param response
     * @param messageCustomerIds
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/readbatchmsg")
    public ModelAndView readBatchMessage(HttpServletResponse response, @RequestParam("messageCustomerId[]") Long[] messageCustomerId) {

        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.readBatchMessage(messageCustomerId));
        } catch (IOException e) {
            LOGGER.error("批量标记已读标记失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 补全用户名
     * 
     * @param response
     * @param customerUsername
     *            用户名{@link java.lang.String}
     * @return
     */
    @RequestMapping("/findcustomername")
    public List<Customer> findCustomerName(HttpServletResponse response, String customerUsername) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            List<Customer> cusList = customerServiceMapper.customerList(customerUsername);
            StringBuilder html = new StringBuilder();
            if (cusList != null && !cusList.isEmpty()) {
                html.append("<ul>");
                for (int i = 0; i < cusList.size(); i++) {
                    Customer c = cusList.get(i);
                    html.append("<li>" + c.getCustomerNickname() + "</li>");
                }
                html.append("</ul>");
            }
            LOGGER.info("根据用户名【" + customerUsername + "】模糊查询用户");
            pw.print(html.toString());
        } catch (IOException e) {
            LOGGER.error("补全用户名失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 发送私信
     * 
     * @param request
     * @param response
     * @param messageVo
     * @return
     */
    @RequestMapping("/sendmessageother")
    public ModelAndView sendMessageOther(HttpServletRequest request, HttpServletResponse response, MessageVo messageVo) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long cusId = (Long) request.getSession().getAttribute(CUSTOMERID);
            Customer cus = customerServiceMapper.customer(messageVo.getCustomerUsername());
            // 判断用户是否发给自己
            if (cusId != null) {
                if (cus != null) {
                    if (cusId.equals(cus.getCustomerId())) {
                        // 不能发给自己
                        pw.print("-1");
                    } else {
                        messageVo.setMessageType("5");// 私信
                        messageVo.setCustomerRecId(new Long[] { cus.getCustomerId() });
                        pw.print(messageSiteService.addMessage(messageVo, cusId));
                    }
                } else {
                    pw.print("-3");// 改用户名不存在
                }
            } else {
                pw.print("-2"); // 未登录
            }
        } catch (IOException e) {
            LOGGER.error("发送私信失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 转发
     * 
     * @param request
     * @param response
     * @param messageVo
     * @return
     */
    @RequestMapping("/sendmessagemyproject")
    public ModelAndView sendMessageMyProject(HttpServletRequest request, HttpServletResponse response, MessageVo messageVo, Long myProjectId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long cusId = (Long) request.getSession().getAttribute(CUSTOMERID);
            Customer cus = customerServiceMapper.customer(messageVo.getCustomerUsername());
            // 判断用户是否发给自己
            if (cusId != null) {
                if (cus != null) {
                    if (cusId.equals(cus.getCustomerId())) {
                        // 不能发给自己
                        pw.print("-1");
                    } else {
                        String context = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
                                + "/custormer/querymyprojectbymyprojectid-" + myProjectId + ".html";
                        messageVo.setMessageContent(messageVo.getMessageContent() + "<a href=\"" + context + "\" >点击查看</a>");
                        messageVo.setMessageType("5");// 私信
                        messageVo.setCustomerRecId(new Long[] { cus.getCustomerId() });
                        pw.print(messageSiteService.addMessage(messageVo, cusId));
                    }
                } else {
                    pw.print("-3");// 改用户名不存在
                }
            } else {
                pw.print("-2"); // 未登录
            }
        } catch (IOException e) {
            LOGGER.error("转发私信失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 发送消息
     * 
     * @param response
     * @param messageVo
     *            消息VO{@link com.ningpai.message.vo.MessageVo}
     * @return
     */
    @RequestMapping("/sendmessage")
    public ModelAndView sendMessage(HttpServletResponse response, MessageVo messageVo) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            messageVo.setMessageType("5");// 私信
            messageVo.setCustomerRecId(new Long[] { messageVo.getMessageRecCustomerId() });
            pw.print(messageSiteService.addMessage(messageVo, messageVo.getMessageAuthorId()));
        } catch (IOException e) {
            LOGGER.error("发送消息失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 批量删除已发私信
     * 
     * @param response
     * @param messageId
     *            消息Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deletebatchsendmsg")
    public ModelAndView delBatchSendMessage(HttpServletResponse response, Long[] messageId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.deleteBatchByMessageId(messageId));
        } catch (IOException e) {
            LOGGER.error("批量删除已发私信失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 删除已发私信
     * 
     * @param response
     * @param messageId
     * @return
     */
    @RequestMapping("/deletesendmsg")
    public ModelAndView delSendMessage(HttpServletResponse response, Long messageId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(messageSiteService.deleteByMessageId(messageId));

        } catch (IOException e) {
            LOGGER.error("删除已发私信失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 未读消息数目
     * 
     * @param request
     * @return
     */
    @RequestMapping("/messagecount")
    @ResponseBody
    public Map<String, Object> messageCount(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long cusId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 系统消息未读数目
        resultMap.put("syscount", messageSiteService.messageCount(cusId, "0"));
        // 私信未读数目
        resultMap.put("lettercount", messageSiteService.messageCount(cusId, "5"));
        // 评论未读数目
        resultMap.put("commentcount", messageSiteService.messageCount(cusId, "4"));
        // 所有消息未读数目
        resultMap.put("noreadcount", messageSiteService.messageCount(cusId, null));
        return resultMap;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }
}
