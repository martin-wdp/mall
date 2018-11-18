package com.ningpai.site.group.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.customer.bean.Customer;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.group.service.FansSiteService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.util.PageBean;
import com.ningpai.util.ValueUtil;

/**
 * 个人主页--粉丝
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class FansController {

    private static final Logger LOGGER = Logger.getLogger(FansController.class);

    private static final String CUSTOMERID = "customerId";

    private PrintWriter pw;

    // spring 注解 会员service
    private CustomerServiceInterface customerServiceInterface;

    private FansSiteService fansSiteService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    /**
     * 加关注
     * 
     * @param response
     * @param request
     * @param fansCustomerId
     *            粉丝用户Id
     * @param fansFlag
     *            粉丝状态
     * @return
     */
    @RequestMapping("/guanzhu")
    public ModelAndView guanZhu(HttpServletResponse response, HttpServletRequest request, Long fansCustomerId, String fansFlag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (customerId != null) {
                pw.print(fansSiteService.guanzhu(customerId, fansCustomerId, fansFlag));
            } else {
                pw.print("0");
            }
        } catch (IOException e) {
            LOGGER.error("加关注失败", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 取消关注
     * 
     * @param response
     * @param request
     * @param fansCustomerId
     *            粉丝用户Id
     * @param fansFlag
     *            粉丝状态
     * @return
     */
    @RequestMapping("/cancelguanzhu")
    public ModelAndView cancelGuanzhu(HttpServletResponse response, HttpServletRequest request, Long fansCustomerId, String fansFlag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (customerId != null) {
                if (fansSiteService.cancelGuanzhu(customerId, fansCustomerId, fansFlag) > 0) {
                    pw.print("1");
                } else {
                    pw.print("0");
                }
            } else {
                pw.print("0");
            }
            // 根据主键获取单个的会员信息
            Customer customer = customerServiceInterface.queryCustomerById(customerId);
            // 非空验证 会员登录名
            if (null != customer.getCustomerUsername()) {
                // 日志记录
                LOGGER.info("取消粉丝用户为：" + customer.getCustomerUsername() + "的关注");
            }
        } catch (IOException e) {
            LOGGER.error("取消关注失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 查询用户的关注和粉丝
     * 
     * @param request
     * @param pb
     *            用户对象{@link com.ningpai.customer.bean.Customer}
     * @param fansFlag
     *            粉丝状态 0我的粉丝 1我的关注 2互粉 {@link java.lang.String}
     * @return mav
     */
    @RequestMapping("/mymutual")
    public ModelAndView myMutual(HttpServletRequest request, PageBean pb, Long customerId, String fansFlag) {
        ModelAndView mav = new ModelAndView("newsocial/mymutual");
        Long custId = (Long) request.getSession().getAttribute(CUSTOMERID);
        mav.addObject("pb", fansSiteService.selectMyMtual(pb, customerId, fansFlag, custId));
        mav.addObject("cusId", customerId);
        mav.addObject("fansFlag", fansFlag);
        // 日志记录
        if (null != custId) {
            LOGGER.info("查询会员为：" + customerServiceInterface.queryCustomerById(custId).getCustomerNickname() + "下面所有的关注和粉丝");
        }

        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 批量取消关注
     * 
     * @param request
     * @param response
     * @param customerIds
     * @return ModelAndView
     */
    @RequestMapping("/outallfans")
    public ModelAndView outAllFans(HttpServletRequest request, HttpServletResponse response, String[] customerIds) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (customerId != null) {
                pw.print(fansSiteService.cancelAllGuanzhu(customerId, customerIds));
            } else {
                pw.print("0");
            }
        } catch (IOException e) {
            LOGGER.error("批量取消关注失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
        return null;

    }

    public FansSiteService getFansSiteService() {
        return fansSiteService;
    }

    @Resource(name = "FansSiteService")
    public void setFansSiteService(FansSiteService fansSiteService) {
        this.fansSiteService = fansSiteService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    // spring 注入属性
    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }
}
