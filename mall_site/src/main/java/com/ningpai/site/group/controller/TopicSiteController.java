package com.ningpai.site.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.adv.service.AdvService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.service.CustomerReplySiteService;
import com.ningpai.group.service.GroupSiteService;
import com.ningpai.group.service.GroupTypeSiteService;
import com.ningpai.group.service.impl.CustomerReplySiteServiceImpl;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.bean.TopicVo;
import com.ningpai.topic.service.TopicSiteService;
import com.ningpai.util.PageBean;
import com.ningpai.util.ValueUtil;

/**
 * 话题控制器
 * 
 * @author qiyuanyuan
 *
 */
@Controller
public class TopicSiteController {

    private static final Logger LOGGER = Logger.getLogger(CustomerReplySiteServiceImpl.class);

    private static final String CUSTOMER_ID = "customerId";

    private PrintWriter pw;

    private GroupSiteService groupSiteService;

    private TopicSiteService topicSiteService;

    private CustomerReplySiteService customerReplySiteService;

    private GroupTypeSiteService groupTypeSiteService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    private AdvService advService;

    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;

    private static final String GROUP = "group";

    private static final String OTHERTOPICS = "othertopics";

    private static final String TOPIC = "topic";

    /**
     * 跳转发表话题页面
     * 
     * @return ModelAndView
     */
    @RequestMapping("/topubtopic")
    public ModelAndView topubTopic(GroupVo groupVo, TopicVo topic) {
        ModelAndView mav = new ModelAndView("topic/pubtopic");
        mav.addObject(GROUP, groupSiteService.selectGroup(groupVo).get(0));
        mav.addObject(OTHERTOPICS, topicSiteService.otherTopic(topic));
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 发表话题
     * 
     * @param request
     * @param response
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.TopicVo}
     * @return
     */
    @RequestMapping("/pubtopic")
    public ModelAndView pubTopic(HttpServletRequest request, HttpServletResponse response, TopicVo topic) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        if (customerId != null) {
            try {
                pw = response.getWriter();
                topic.setCustomerId(customerId);
                String flag = topicSiteService.saveTopic(topic);
                pw.print(flag);
                // 非空验证 话题标题
                if (null != topic.getTopicTitle()) {
                    LOGGER.info("发表话题【" + topic.getTopicTitle() + "】。");
                }
            } catch (IOException e) {
                LOGGER.error("发表话题失败！", e);
            } catch (Exception e) {
                LOGGER.error("发表话题失败！", e);
            } finally {
                if (pw != null) {
                    pw.close();
                }
            }
        }
        return null;
    }

    /**
     * 话题详情页
     * 
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param topic
     *            话题Vo {@link com.ningpai.topic.bean.TopicVo}
     * @param customer
     *            小组用户 {@link com.ningpai.group.bean.GroupCustomer}
     * @param ta
     * @return
     */
    @RequestMapping("/topicdetail")
    public ModelAndView topicDetail(HttpServletRequest request, PageBean pb, GroupVo groupVo, GroupTopic topic, GroupCustomer customer, Long ta) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (custId != null) {
            customer.setCustomerId(custId);
            resultMap.put("custome", groupSiteService.searchByCustomerId(customer, "1"));
            resultMap.put("cinfo", customerServiceInterface.queryCustomerById(custId));
        }
        GroupTopic t = topicSiteService.selectTopicById(topic.getTopicId());
        if (t != null) {
            t.setTopicHot(t.getTopicHot() + 1);
            topicSiteService.editTopic(t);
        }
        if (t == null) {
            return new ModelAndView(ValueUtil.NOTFOUND);
        }
        if (!groupSiteService.selectGroup(groupVo).isEmpty()) {
            resultMap.put(GROUP, groupSiteService.selectGroup(groupVo).get(0));
        } else {
            return new ModelAndView(ValueUtil.NOTFOUND);
        }
        resultMap.put(TOPIC, t);
        resultMap.put(OTHERTOPICS, topicSiteService.otherTopic(topic));
        resultMap.put("pb", customerReplySiteService.topicReply(pb, topic, ta));
        resultMap.put("ta", ta);
        resultMap.put("custId", custId);
        // 非空验证 话题标题
        if (null != topic.getTopicTitle()) {
            LOGGER.info("查看话题【" + topic.getTopicTitle() + "】的详情页！");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("topic/topicdetail")).addAllObjects(resultMap);
    }

    /**
     * 修改小组话题
     * 
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param topic
     *            话题Vo {@link com.ningpai.topic.bean.TopicVo}
     * @param customer
     *            小组用户 {@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    @RequestMapping("/updatetopic")
    public ModelAndView updateTopic(HttpServletRequest request, GroupVo groupVo, TopicVo topic, GroupCustomer customer) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (custId != null) {
            customer.setCustomerId(custId);
            resultMap.put("custome", groupSiteService.searchByCustomerId(customer, "1"));
        }
        resultMap.put(TOPIC, topicSiteService.selectTopicById(topic.getTopicId()));
        resultMap.put(GROUP, groupSiteService.selectGroup(groupVo).get(0));
        resultMap.put(OTHERTOPICS, topicSiteService.otherTopic(topic));
        resultMap.put("topicCateId", topicSiteService.selectTopicById(topic.getTopicId()).getTopicCateId());
        // 非空验证 话题标题
        if (null != topic.getTopicTitle()) {
            LOGGER.info("修改小组话题【" + topic.getTopicTitle() + "】");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("topic/updatetopic")).addAllObjects(resultMap);
    }

    /**
     * 更新小组话题
     * 
     * @param response
     * @param topic
     * @return
     */
    @RequestMapping("/edittopic")
    public ModelAndView editTopic(HttpServletResponse response, TopicVo topic) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.editTopic(topic));
            // 非空验证 话题标题
            if (null != topic.getTopicTitle()) {
                LOGGER.info("更新小组话题【" + topic.getTopicTitle() + "】");
            }
        } catch (IOException e) {
            LOGGER.error("更新小组话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 删除话题
     * 
     * @param response
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/deltopic")
    public ModelAndView delTopic(HttpServletResponse response, HttpServletRequest request, GroupTopic topic) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.delTopic(topic, customerId));
            // 非空验证 话题ID
            if (null != topic.getTopicId()) {
                // 根据主键获取 小组话题实体
                GroupTopic groupTopic = topicSiteService.selectTopicById(topic.getTopicId());
                LOGGER.info("删除话题【" + groupTopic.getTopicTitle() + "】");
            }
        } catch (IOException e) {
            LOGGER.error("删除话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 还原回收站的话题
     * 
     * @return
     */
    @RequestMapping("/restoretopic")
    public ModelAndView restoreTopic(HttpServletResponse response, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.restore(topicId));
            LOGGER.info("还原回收站的话题！");
        } catch (IOException e) {
            LOGGER.error("还原回收站的话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 批量删除话题
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/deltopicbatch")
    public ModelAndView deltopicbatch(HttpServletResponse response, HttpServletRequest request, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.delTopicBatch(topicId, customerId));
        } catch (IOException e) {
            LOGGER.error("批量删除话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 禁止回应话题
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/unreplytopicbatch")
    public ModelAndView unreplyTopicBatch(HttpServletResponse response, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.unreplyTopicBatch(topicId));
        } catch (IOException e) {
            LOGGER.error("禁止回应话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 允许回应话题
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/replytopicbatch")
    public ModelAndView replyTopicBatch(HttpServletResponse response, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.replyTopicBatch(topicId));
        } catch (IOException e) {
            LOGGER.error("允许回应话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 申请到首页
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/recommendtopicbatch")
    public ModelAndView recommendTopicBatch(HttpServletResponse response, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.recommendTopicBatch(topicId));
        } catch (IOException e) {
            LOGGER.error("申请到首页失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 设精华
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/essencetopicbatch")
    public ModelAndView essenceTopicBatch(HttpServletResponse response, Long[] topicId, String essence) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.essenceTopicBatch(topicId, essence));
        } catch (IOException e) {
            LOGGER.error("设精华失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 热帖
     * 
     * @param response
     * @param topicId
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/fevertopicbatch")
    public ModelAndView feverTopicBatch(HttpServletResponse response, Long[] topicId, String fever) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.feverTopicBatch(topicId, fever));
        } catch (IOException e) {
            LOGGER.error("热帖失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 话题置顶设置
     * 
     * @param request
     * @param response
     * @param topicId
     * @param top
     * @return
     */
    @RequestMapping("/toptopicbatch")
    public ModelAndView topTopicBatch(HttpServletRequest request, HttpServletResponse response, Long[] topicId, String top) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.topTopicBatch(topicId, top));
        } catch (IOException e) {
            LOGGER.error("话题置顶设置失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 彻底删除
     * 
     * @param response
     * @param topicId
     *            话题ID{@link java.lang.Long}
     * @return
     */
    @RequestMapping("deletetopic")
    public ModelAndView deleteTopic(HttpServletResponse response, Long[] topicId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.delTopic(topicId));
        } catch (IOException e) {
            LOGGER.error("彻底删除失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 推荐话题
     * 
     * @param request
     * @param response
     * @param topic
     * @return
     */
    @RequestMapping("/recounttopic")
    public ModelAndView recountTopic(HttpServletRequest request, HttpServletResponse response, GroupTopic topic) {
        Long custId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            if (custId == null) {
                pw.print(2);
            } else {
                // 查询该用户是否已经推荐过该小组的该话题
                int recommendCount = topicSiteService.topicRecommendCount(topic.getTopicId(), custId, "0");
                if (recommendCount == 0) {
                    pw.print(topicSiteService.updateTopicRecommend(topic, custId));
                    topicSiteService.insertTopicRecommend(topic.getTopicId(), custId, "0");
                    // 非空验证 话题标题
                    if (null != topic.getTopicTitle()) {
                        LOGGER.info("推荐小组话题【" + topic.getTopicTitle() + "】");
                    }
                } else {
                    pw.print(-1);
                }
            }
        } catch (IOException e) {
            LOGGER.error("推荐话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 话题列表
     * 
     * @param pb
     *            pagebean
     * @param groupVo
     *            小组Vo
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param flag
     *            搜索标记{@link java.lang.String}
     * @return
     */
    @RequestMapping("/topiclist")
    public ModelAndView topicList(PageBean pb, GroupVo groupVo, TopicVo topic, String flag, String keyword) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // Long groupTypeId = groupVo.getGroupTypeId();
        // 人气小组
        resultMap.put("hotlist", groupSiteService.findGroupList(groupVo, "2"));
        // 小组分类
        resultMap.put("grouptypelist", groupTypeSiteService.selectAll());
        // 标签分类
        // resultMap.put("grouplabel",
        // groupSiteService.groupLabelList(groupTypeId!=null
        // &&!"".equals(groupTypeId)?groupTypeId:null));
        // 话题列表
        resultMap.put("pb", topicSiteService.topicList(pb, topic, flag, keyword));
        resultMap.put("topictitle", topic.getTopicTitle());
        if (keyword != null && !"".equals(keyword)) {
            /*
             * try { String keyvalue = new
             * String(keyword.getBytes("ISO-8859-1"),ConstantUtil.UTF);
             * resultMap.put("keyvalue",keyvalue); } catch
             * (UnsupportedEncodingException e) { e.printStackTrace(); }
             */
            resultMap.put("keyvalue", keyword);
        }
        resultMap.put("flag", flag);
        // resultMap.put("groupTypeId", groupTypeId);
        // 所以关于话题的评论数
        resultMap.put("topicCount", topicSiteService.topicCount());
        resultMap.put("topicReplyCount", customerReplySiteService.customerReplyCount("0"));
        // 查询社区搜索页轮播广告
        resultMap.put("advlist", advService.selectAdvListByPosition("1"));
        return topAndBottomService.getTopAndBottom(new ModelAndView("topic/topiclist")).addAllObjects(resultMap);
    }

    /**
     * 查看精选热门话题
     * 
     * @param pb
     *            PageBean
     * @return
     */
    @RequestMapping("/topicessencelist")
    @ResponseBody
    public PageBean topicEssenceList(PageBean pb, String pageNo) {
        // resultMap.put(TOPIC, topicSiteService.topicEssence(pb));
        return topicSiteService.topicEssence(pb, pageNo);
    }

    /**
     * 申请恢复话题
     * 
     * @param response
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @RequestMapping("/applytopic")
    public ModelAndView applyTopic(HttpServletResponse response, HttpServletRequest request, GroupTopic topic) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        // Long customerId = (Long)
        // request.getSession().getAttribute(CUSTOMER_ID);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.applyTopic(topic));
            // 非空验证 话题标题
            if (null != topic.getTopicTitle()) {
                LOGGER.info("申请恢复话题【" + topic.getTopicTitle() + "】");
            }
        } catch (IOException e) {
            LOGGER.error("申请恢复话题失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 跳转处理恢复话题详情页
     * 
     * @param request
     * @param topicId
     * @return
     */
    @RequestMapping("/applytopicdetail")
    public ModelAndView operationTopicApply(HttpServletRequest request, Long topicId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        GroupTopic groupTopic = topicSiteService.applyTopicDetail(topicId);
        resultMap.put(TOPIC, groupTopic);
        // 非空验证 话题标题
        if (null != groupTopic.getTopicTitle()) {
            LOGGER.info(" 跳转处理恢复话题【" + groupTopic.getTopicTitle() + "】");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("topic/applytopic")).addAllObjects(resultMap);
    }

    /**
     * 处理申诉详情
     * 
     * @param response
     * @param request
     * @param topic
     * @return
     */
    @RequestMapping("/applydetail")
    public ModelAndView applyDetail(HttpServletResponse response, HttpServletRequest request, GroupTopic topic) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMER_ID);
        try {
            pw = response.getWriter();
            pw.print(topicSiteService.applyDetail(topic, customerId));
            // 非空验证 话题标题
            if (null != topic.getTopicTitle()) {
                LOGGER.info(" 处理申诉话题【" + topic.getTopicTitle() + "】");
            }
        } catch (IOException e) {
            LOGGER.error("处理申诉详情失败！", e);
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

    public TopicSiteService getTopicSiteService() {
        return topicSiteService;
    }

    @Resource(name = "TopicSiteService")
    public void setTopicSiteService(TopicSiteService topicSiteService) {
        this.topicSiteService = topicSiteService;
    }

    public CustomerReplySiteService getCustomerReplySiteService() {
        return customerReplySiteService;
    }

    @Resource(name = "CustomerReplySiteService")
    public void setCustomerReplySiteService(CustomerReplySiteService customerReplySiteService) {
        this.customerReplySiteService = customerReplySiteService;
    }

    public GroupTypeSiteService getGroupTypeSiteService() {
        return groupTypeSiteService;
    }

    @Resource(name = "GroupTypeSiteService")
    public void setGroupTypeSiteService(GroupTypeSiteService groupTypeSiteService) {
        this.groupTypeSiteService = groupTypeSiteService;
    }

    public AdvService getAdvService() {
        return advService;
    }

    @Resource(name = "AdvService")
    public void setAdvService(AdvService advService) {
        this.advService = advService;
    }

}
