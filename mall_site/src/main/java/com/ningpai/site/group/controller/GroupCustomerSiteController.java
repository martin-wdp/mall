package com.ningpai.site.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.bean.Mood;
import com.ningpai.group.bean.Visitors;
import com.ningpai.group.service.CustomerCenterService;
import com.ningpai.group.service.FansSiteService;
import com.ningpai.group.service.GroupCustomerSiteService;
import com.ningpai.group.service.GroupSiteService;
import com.ningpai.group.service.MoodService;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.IpUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;
import com.ningpai.util.ValueUtil;

/**
 * 个人主页Controller
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class GroupCustomerSiteController {

    private static final Logger LOGGER = Logger.getLogger(GroupCustomerSiteController.class);

    private static final String CUSTOMERID = "customerId";
    private static final String CUSID = "cusId";
    private static final String LOGGERINFO1 = "转让小组失败！";

    // 会员服务接口
    private CustomerServiceInterface customerServiceInterface;

    private PrintWriter pw;

    private GroupSiteService groupSiteService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    private FansSiteService fansSiteService;

    private MoodService moodService;

    private GroupCustomerSiteService customerSiteService;

    private CustomerCenterService customerCenterService;

    /**
     * 个人主页
     * 
     * @param pb
     * @param customerId
     *            当前主页用户ID{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/customerhomepage")
    public ModelAndView customerHomePage(HttpServletRequest request, Long customerId, PageBean pb) {

        Long cusId = (Long) request.getSession().getAttribute(CUSTOMERID);
        pb.setUrl("customerhomepage/" + customerId);
        if (customerId != null) {
            int checkCustomerId = customerSiteService.checkCustomerId(customerId);
            // 根据主键 获取单个会员对象
            CustomerAllInfo customerAllInfo = customerServiceInterface.queryAddressByCustomerId(customerId);
            // 非空验证 会员 用户名
            if (null != customerAllInfo.getCustomerUsername()) {
                LOGGER.info("进入会员【" + customerAllInfo.getCustomerUsername() + "】的个人主页");
            }
            if (checkCustomerId == 1) {
                if (cusId != null && cusId.equals(customerId)) {
                    return new ModelAndView(new RedirectView("../mycustomerhomepage.html")).addObject(CUSID, cusId);
                } else {
                    // 添加访问者
                    if (cusId != null && !cusId.equals(customerId)) {
                        Visitors visitors = customerCenterService.selectOneVisitors(cusId, customerId);
                        String customerIp = IpUtil.getIpAddr(request);
                        if (visitors == null) {
                            customerCenterService.insertVistitor(cusId, customerId, customerIp);
                        } else {
                            Date da = visitors.getVisitorsTime();
                            int sqltime = UtilDate.todayFormatInt(da);
                            int nowtime = UtilDate.todayFormatInt(da);
                            if (nowtime > sqltime) {
                                customerCenterService.insertVistitor(cusId, customerId, customerIp);
                            }
                        }
                    }
                    // 查询当前主页是否是粉丝状态
                    String fansFlag = fansSiteService.fansFlag(cusId, customerId);
                    PageBean pbsome = customerSiteService.myCommunityList(pb, "0", customerId);
                    return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/hiscustomerpage")).addObject(CUSID, cusId).addObject("fanscustomerId", customerId)
                            // 加入的小组
                            .addObject("joinedgroup", groupSiteService.joinedGroup(customerId)).addObject("fansFlag", fansFlag)
                            .addObject("map", customerCenterService.selectCenterMessage(customerId))
                            // 他的关注
                            .addObject("fanslist", fansSiteService.fansList(customerId)).addObject("pbsome", pbsome).addObject("mood", moodService.selectOneMood(customerId));
                    /*
                     * .addObject("pb",
                     * myModelService.myModelList(pb,customerId)) //他的社区页面广告位
                     * .addObject("advlist",
                     * advService.selectAdvListByPosition("8"))
                     */

                }
            } else {
                return new ModelAndView(new RedirectView("../login.html"));
            }
        } else {
            return new ModelAndView(new RedirectView("../login.html"));
        }
    }

    /**
     * 我的个人主页
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/mycustomerhomepage")
    public ModelAndView mycustomerhomepage(HttpServletRequest request, PageBean pb, HttpSession session) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        PageBean pbsome = customerSiteService.myCommunityList(pb, "0", customerId);
        // 用户推荐

        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/newcushomepage")).addObject("map", customerCenterService.selectCenterMessage(customerId))
                .addObject("pbsome", pbsome).addObject("guanzhu", fansSiteService.selectFocus(customerId, 4, "1"))
                .addObject("fans", fansSiteService.selectFocus(customerId, 4, "0")).addObject("mood", moodService.selectOneMood(customerId))
                .addObject("visitors", customerCenterService.selectVisitors(customerId, 12))
                // 用户推荐
                .addObject("recommend", customerSiteService.selectRecommended(customerId));
    }

    /**
     * 用户推荐
     * 
     * @param response
     * @param memberId
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/tjMember")
    public ModelAndView tjMember(HttpServletResponse response, Long memberId, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
            // 查询用户推荐
            Map<String, Object> liMap = new HashMap<String, Object>();
            List<GroupCustomer> tjmember = customerSiteService.selectRecommended(customerId);
            liMap.put("tjmember", tjmember);
            pw.print(JSONArray.fromObject(liMap));

        } catch (IOException e) {
            LOGGER.error("用户推荐失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
        return null;

    }

    /**
     * 个人主页--他的社区
     * 
     * @param httpServletRequest
     * @param customerId
     *            当前主页用户ID{@link java.lang.Long}
     * @return
     */
    /*
     * @RequestMapping("/hiscommunity") public ModelAndView
     * hisCommunityList(HttpServletRequest request,Long customerId,PageBean pb){
     * Long cusId= (Long) request.getSession().getAttribute(CUSTOMERID); //
     * PageBean pbsome =myCommunityService.myCommunityList(pb,"0",customerId);
     * //查询5条 // PageBean pbonly = new PageBean(); //查询最新 // pbonly =
     * myCommunityService.myCommunityList(pbonly,"1",customerId);
     * pb.setUrl("hiscommunity/"+customerId); if(customerId !=null &&
     * !"".equals(customerId)){ int checkCustomerId =
     * customerSiteService.checkCustomerId(customerId); if(checkCustomerId ==
     * 1){ //查询当前主页是否是粉丝状态 String fansFlag = fansSiteService.fansFlag(cusId,
     * customerId); return topAndBottomService.getTopAndBottom(new
     * ModelAndView("newsocial/hiscommunity"))
     * .addObject(CUSID,cusId).addObject("fanscustomerId",customerId) //加入的小组 .
     * addObject
     * ("joinedgroup",groupSiteService.joinedGroup(customerId)).addObject
     * ("fansFlag",fansFlag)
     * .addObject("map",customerCenterService.selectCenterMessage(customerId))
     * //他的关注 .addObject("fanslist",fansSiteService.fansList(customerId)) //
     * .addObject("pbonly",pbonly).addObject("pbsome",pbsome) //他的社区页面广告位
     * .addObject("advlist", advService.selectAdvListByPosition("8")); }else{
     * return new ModelAndView(new RedirectView("../login.html")); } }else{
     * return new ModelAndView(new RedirectView("../login.html")); } }
     */

    /**
     * 升级管理员发送消息
     * 
     * @param response
     * @param request
     * @param customer
     * @param customerId
     *            要升级的管理员ID{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/addmanagermsg")
    public ModelAndView addManagerMsg(HttpServletResponse response, HttpServletRequest request, GroupCustomer customer, Long[] customerId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            Long cid = (Long) request.getSession().getAttribute(CUSTOMERID);
            List<GroupCustomer> mlist = groupSiteService.customers(customer, "2");
            pw = response.getWriter();
            if (mlist != null && mlist.size() >= 4) {
                pw.print(2);
                return null;
            }
            pw.print(customerSiteService.addManagerMsg(customer.getGroupId(), customerId, cid));
        } catch (IOException e) {
            LOGGER.error("升级管理员发送消息失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        return null;
    }

    /**
     * 成为管理员
     * 
     * @param response
     * @param customer
     * @return
     */
    @RequestMapping("addgroupmanager")
    public ModelAndView addgroupManager(HttpServletResponse response, GroupCustomer customer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.addManager(customer));
        } catch (IOException e) {
            LOGGER.error("成为管理员失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 同意or拒绝担任管理员
     * 
     * @param response
     * @param messageVo
     *            消息Vo{@link com.ningpai.message.vo.MessageVo}
     * @param groupCustomerExamine
     *            同意or拒绝{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/managergroup")
    public ModelAndView managerGroup(HttpServletResponse response, MessageVo messageVo, String groupCustomerExamine) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            int myGroupCount = customerSiteService.myGroupCount(messageVo.getMessageAuthorId());
            int syscount = 4;
            if (myGroupCount >= syscount) {
                pw.print("-1");
                return null;
            }
            pw.print(customerSiteService.managerGroup(messageVo, groupCustomerExamine));
        } catch (IOException e) {
            LOGGER.error("同意or拒绝担任管理员失败！", e);
        }
        return null;
    }

    /**
     * 免去管理员
     * 
     * @param response
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @param customerId
     *            管理员ID{@link java.lang.Long}
     * @param createAuthorId
     *            小组组长Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/removemanagerbatch")
    public ModelAndView removeManagerBatch(HttpServletResponse response, Long groupId, Long[] customerId, Long createAuthorId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.removeManagementBatch(groupId, customerId, createAuthorId));
        } catch (IOException e) {
            LOGGER.error("免去管理员失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 转让小组
     * 
     * @param response
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @param customerId
     *            管理员ID{@link java.lang.Long}
     * @param createAuthorId
     *            小组组长Id{@link java.lang.Long}
     * @return
     */
    @RequestMapping("/transfergroupmsg")
    public ModelAndView transferGroupmsg(HttpServletResponse response, Long groupId, Long customerId, Long createAuthorId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.transferGroupMsg(groupId, customerId, createAuthorId));
        } catch (IOException e) {
            LOGGER.error(LOGGERINFO1, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 转让小组
     * 
     * @param response
     * @param messageVo
     * @param groupCustomerExamine
     * @return
     */
    @RequestMapping("/zhuangrgroup")
    public ModelAndView zhuangrGroup(HttpServletResponse response, MessageVo messageVo, String groupCustomerExamine) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            int myGroupCount = customerSiteService.myGroupCount(messageVo.getMessageAuthorId());
            int syscount = 4;
            if (myGroupCount >= syscount) {
                pw.print("-1");
                return null;
            }
            pw.print(customerSiteService.zhuanrGroup(messageVo, groupCustomerExamine));
        } catch (IOException e) {
            LOGGER.error(LOGGERINFO1, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 转让小组
     * 
     * @param response
     * @param groupId
     * @param customerId
     * @param createAuthorId
     * @return
     */
    @RequestMapping("/transfergroup")
    public ModelAndView transferGroup(HttpServletResponse response, Long groupId, Long customerId, Long createAuthorId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.transferGroup(groupId, customerId, createAuthorId));
        } catch (IOException e) {
            LOGGER.error(LOGGERINFO1, e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 管理员同意加入小组申请
     * 
     * @param response
     * @param customer
     *            小组用户
     * 
     * @return
     */
    @RequestMapping("/addgroupcustomer")
    public ModelAndView addGroupCustomer(HttpServletResponse response, GroupCustomer customer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.addGroupCustomer(customer));
        } catch (IOException e) {
            LOGGER.error("管理员同意加入小组申请失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 邀请好友
     * 
     * @param response
     * @param request
     * @param groupId
     * @param customerIds
     *            邀请的用户id
     * @param content
     * 
     * @return
     */
    @RequestMapping("/invitefriend")
    public ModelAndView inviteFriends(HttpServletResponse response, HttpServletRequest request, Long groupId, Long[] customerIds, String content) {
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            if (customerId != null) {
                pw.print(customerSiteService.inviteFriend(groupId, customerId, customerIds, content));
            } else {
                pw.print("-1");
            }
        } catch (IOException e) {
            LOGGER.error("邀请好友失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 邀请好友同意or拒绝
     * 
     * @param response
     * @param messageVo
     * @param groupCustomerExamine
     * @return
     */
    @RequestMapping("/invitegroupfriend")
    public ModelAndView inviteGroupFriend(HttpServletResponse response, MessageVo messageVo, String groupCustomerExamine) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.inviteGroupFriend(messageVo, groupCustomerExamine));
        } catch (IOException e) {
            LOGGER.error("邀请好友同意or拒绝失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 同意邀请加入小组
     * 
     * @param response
     * @param customer
     *            小组用户
     * @return
     */
    @RequestMapping("/addgroupmember")
    public ModelAndView addGroupMember(HttpServletResponse response, GroupCustomer customer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(customerSiteService.addGroupCustomer(customer));
        } catch (IOException e) {
            LOGGER.error("同意邀请加入小组失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        return null;
    }

    /**
     * 发表心情
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("sendmood")
    public ModelAndView sendMood(HttpServletResponse response, HttpServletRequest request, Mood mood) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (customerId != null) {
                mood.setCustomerId(customerId);
                pw.print(moodService.sendMood(mood));
            } else {
                // 未登录
                pw.print(-1);
            }
        } catch (IOException e) {
            LOGGER.error("发表心情失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 心情里列表
     * 
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/moodlist")
    public ModelAndView moodList(HttpServletRequest request, Long memberId, PageBean pb) {
        ModelAndView mav = new ModelAndView("newsocial/mood");
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 获取个人心情列表 和心情的评论
        mav.addObject("moodMap", moodService.moodList(memberId, pb));
        // 加载心情评论 8条最新
        mav.addObject("moodreply", moodService.moodReply(memberId, 8));
        // 查询最近访客
        // mav.addObject("visitors",mainCenterService.selectVisitors(sessionMemberId,memberId,12));
        mav.addObject("memberId", memberId);
        mav.addObject(CUSID, customerId);
        mav.addObject("visitors", customerCenterService.selectVisitors(memberId, 4));
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 删除心情
     * 
     * @param response
     * @param moodId
     * @return ModelAndView
     */
    @RequestMapping("/delmood")
    public ModelAndView delMood(HttpServletResponse response, Long moodId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(moodService.delMood(moodId));
        } catch (IOException e) {
            LOGGER.error("删除心情失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
        return null;
    }

    public GroupSiteService getGroupSiteService() {
        return groupSiteService;
    }

    @Resource(name = "GroupSiteService")
    public void setGroupSiteService(GroupSiteService groupSiteService) {
        this.groupSiteService = groupSiteService;
    }

    public GroupCustomerSiteService getCustomerSiteService() {
        return customerSiteService;
    }

    @Resource(name = "GroupCustomerSiteService")
    public void setCustomerSiteService(GroupCustomerSiteService customerSiteService) {
        this.customerSiteService = customerSiteService;
    }

    public FansSiteService getFansSiteService() {
        return fansSiteService;
    }

    @Resource(name = "FansSiteService")
    public void setFansSiteService(FansSiteService fansSiteService) {
        this.fansSiteService = fansSiteService;
    }

    public CustomerCenterService getCustomerCenterService() {
        return customerCenterService;
    }

    @Resource(name = "CustomerCenterService")
    public void setCustomerCenterService(CustomerCenterService customerCenterService) {
        this.customerCenterService = customerCenterService;
    }

    /*
     * private MyModelService myModelService;
     * 
     * public MyModelService getMyModelService() { return myModelService; }
     * 
     * @Resource(name="MyModelService") public void
     * setMyModelService(MyModelService myModelService) { this.myModelService =
     * myModelService; }
     */

    /*
     * //设计Service注入 private MyProjectService myProjectService;
     * 
     * 
     * 
     * public MyProjectService getMyProjectService() { return myProjectService;
     * }
     * 
     * @Resource(name="MyProjectService") public void
     * setMyProjectService(MyProjectService myProjectService) {
     * this.myProjectService = myProjectService; }
     * 
     * private MyGalleryService myGallerySerivce;
     * 
     * 
     * public MyGalleryService getMyGallerySerivce() { return myGallerySerivce;
     * }
     * 
     * @Resource(name="MyGalleryService") public void
     * setMyGallerySerivce(MyGalleryService myGallerySerivce) {
     * this.myGallerySerivce = myGallerySerivce; }
     * 
     * private MyCommunityService myCommunityService;
     * 
     * 
     * 
     * public MyCommunityService getMyCommunityService() { return
     * myCommunityService; }
     * 
     * @Resource(name="MyCommunityService") public void
     * setMyCommunityService(MyCommunityService myCommunityService) {
     * this.myCommunityService = myCommunityService; }
     */

    /*
     * private AdvService advService;
     * 
     * public AdvService getAdvService() { return advService; }
     * 
     * @Resource(name="AdvService") public void setAdvService(AdvService
     * advService) { this.advService = advService; }
     */

    public MoodService getMoodService() {
        return moodService;
    }

    @Resource(name = "MoodService")
    public void setMoodService(MoodService moodService) {
        this.moodService = moodService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

}
