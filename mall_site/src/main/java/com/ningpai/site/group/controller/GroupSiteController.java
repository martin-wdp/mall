/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.group.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.adv.service.AdvService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupBlack;
import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.bean.GroupImg;
import com.ningpai.group.bean.GroupPermissions;
import com.ningpai.group.service.CustomerReplySiteService;
import com.ningpai.group.service.FansSiteService;
import com.ningpai.group.service.GroupCustomerSiteService;
import com.ningpai.group.service.GroupImgSiteService;
import com.ningpai.group.service.GroupSiteService;
import com.ningpai.group.service.GroupTypeSiteService;
import com.ningpai.group.service.LabelService;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.group.vo.NextVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.topic.bean.TopicVo;
import com.ningpai.topic.service.TopicSiteService;
import com.ningpai.util.FileUploadFormSite;
import com.ningpai.util.ImageTools;
import com.ningpai.util.JsonDateValueProcessor;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;
import com.ningpai.util.ValueUtil;

/**
 * 前台小组控制器
 * 
 * @version 2014年5月26日 下午2:12:16
 * @author qiyuanyuan
 */

@Controller
public class GroupSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GroupSiteController.class);
    private GroupSiteService groupService;
    private PrintWriter pw;
    private GroupTypeSiteService groupTypeService;
    private GroupImgSiteService imgSiteService;
    private CustomerReplySiteService replySiteService;
    private TopicSiteService topicSiteService;
    private GroupCustomerSiteService customerSiteService;
    private FansSiteService fansSiteService;
    private AdvService advService;
    private LabelService labelService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    private static final String GROUPCOUNT = "groupcount";
    private static final String GROUPMEMBER = "groupmember";
    private static final String CUSTOMEID = "customeId";
    private static final String GROUPTYPELIST = "grouptypelist";
    private static final String CUSID = "cusId";
    private static final String ADVLIST = "advlist";
    private static final String CUSTOME = "custome";
    private static final String CINFO = "cinfo";
    private static final String GROUP = "group";
    private static final String IMGCOUNT = "imgcount";
    private static final String CUSTOMERNAME = "customerName";
    private static final String GROUPINFO = "groupInfo";
    private static final String CUSTOMERIDS = "customerIds[]";

    /**
     * 跳转小组主页面
     * 
     * @return mav
     */
    @RequestMapping("/group")
    public ModelAndView group(HttpServletRequest request, PageBean pb, GroupVo groupVo) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        ModelAndView mav = new ModelAndView("newsocial/groupmain3").addObject(GROUPCOUNT, groupService.groupCount()).addObject(GROUPMEMBER, groupService.groupMember());
        if (customerId != null) {
            mav.addObject(CUSTOMEID, customerId);
        }
        // 特别推荐小组
        mav.addObject("recommendlist", groupService.findGroupList(groupVo, "1"));
        // 热门小组
        mav.addObject("hotlist", groupService.findGroupList(groupVo, "2"));
        // 活跃
        mav.addObject("activelist", groupService.findGroupList(groupVo, "3"));
        // 其他小组
        mav.addObject("otherlist", groupService.findGroupList(groupVo, "4"));
        // 达人
        // mav.addObject("masterlist", masterService.masterList());
        mav.addObject(GROUPTYPELIST, groupTypeService.selectAll());
        // 精选话题
        mav.addObject("topicEssence", topicSiteService.topicEssence(pb, "1"));
        mav.addObject(CUSID, customerId);
        // 查询社区主页右侧广告
        mav.addObject(ADVLIST, advService.selectAdvListByPosition("1"));
        // /查询社区主页轮播广告
        mav.addObject("topadvlist", advService.selectAdvListByPosition("3"));
        // 非空判断 小组名称
        if (null != groupVo.getGroupName()) {
            LOGGER.info("访问小组【" + groupVo.getGroupName() + "】的主页");
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 
     * @param request
     * @param pb
     *            PageBean 分页
     * @param groupVo
     * @param sort
     *
     * @return
     */
    @RequestMapping("/grouplist")
    public ModelAndView allGroup(HttpServletRequest request, PageBean pb, GroupVo groupVo, String sort) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        Long groupTypeId = groupVo.getGroupTypeId();
        if (groupVo.getGroupLabelIds() != null && !"".equals(groupVo.getGroupLabelIds())) {
            List<Long> s = new ArrayList<Long>();
            for (String v : groupVo.getGroupLabelIds().split(",")) {
                s.add(Long.valueOf(v));
            }
            map.put("labelIdlist", s);
            map.put("labelIds", groupVo.getGroupLabelIds());
        }
        try {
            if (customerId != null) {
                map.put(CUSTOMEID, customerId);
            }
            if (groupTypeId != null) {
                map.put("groupType", groupTypeService.selectByGroupTypeId(groupTypeId));
                map.put("groupTypeId", groupTypeId != null ? groupTypeId : null);
            }
            map.put(GROUPCOUNT, groupService.groupCount());
            map.put(GROUPMEMBER, groupService.groupMember());
            map.put(ConstantUtil.PB, groupService.labelGroup(pb, groupVo, sort));
            map.put(GROUPTYPELIST, groupTypeService.selectAll());
            map.put("grouplabellist", labelService.selectAll());
            // map.put("grouplabel",
            // groupService.groupLabelList(groupTypeId!=null
            // &&!"".equals(groupTypeId)?groupTypeId:null));
            /*
             * if(groupVo.getGroupLabel() !=null &&
             * !"".equals(groupVo.getGroupLabel())){ try { String label = new
             * String
             * (groupVo.getGroupLabel().getBytes(ConstantUtil.ISOUTIL),ConstantUtil
             * .UTFUTIL); map.put("label", label); } catch
             * (UnsupportedEncodingException e) { e.printStackTrace(); } }
             */
            /*
             * if(groupVo.getGroupLabel() !=null &&
             * !"".equals(groupVo.getGroupLabel())){
             * 
             * //String label = new
             * String(groupVo.getGroupLabel().getBytes(ConstantUtil
             * .ISOUTIL),ConstantUtil.UTFUTIL); map.put("label",
             * groupVo.getGroupLabel()); }
             */
            map.put("sort", sort != null && !"".equals(sort) ? sort : null);
            map.put(CUSID, customerId);
            // 查询社区搜索页轮播广告
            map.put(ADVLIST, advService.selectAdvListByPosition("1"));
            return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/grouplist")).addAllObjects(map);
        } finally {
            map = null;
        }
    }

    /**
     * 小组搜索列表
     * 
     * @param request
     * @param response
     * @param pb
     * @param group
     * @return
     */
    @RequestMapping("/groupsearchlist")
    public ModelAndView groupSearchList(HttpServletRequest request, HttpServletResponse response, PageBean pb, GroupVo group) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        group.setCustomerId(customerId);
        ModelAndView mav = new ModelAndView("newsocial/searchgroup");
        if (group.getGroupName() != null && !"".equals(group.getGroupName())) {
            /*
             * try { String name = new
             * String(group.getGroupName().getBytes(ConstantUtil
             * .ISOUTIL),ConstantUtil.UTFUTIL); mav.addObject("groupName",
             * name); group.setGroupName(name); } catch
             * (UnsupportedEncodingException e) { e.printStackTrace(); }
             */
            mav.addObject("groupName", group.getGroupName());
        }
        // 小组总数
        mav.addObject(GROUPCOUNT, groupService.groupCount());
        // 成员总数
        mav.addObject(GROUPMEMBER, groupService.groupMember());
        // 小组分类
        mav.addObject(GROUPTYPELIST, groupTypeService.selectAll());
        // 小组搜索列表
        mav.addObject(ConstantUtil.PB, groupService.groupSearchList(pb, group));
        // 查询社区搜索页轮播广告
        mav.addObject(ADVLIST, advService.selectAdvListByPosition("1"));
        mav.addObject(CUSID, customerId);
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 跳转创建小组页面
     * 
     * @return
     */
    @RequestMapping("/togroupcreate")
    public ModelAndView createGroup(HttpServletRequest request) {
        // 跳转创建小组页面
        ModelAndView mav = null;
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        // 登陆验证
        if (customerId == null) {
            mav = new ModelAndView(new RedirectView("login.html"));
        } else {
            mav = new ModelAndView("newsocial/grouptype").addObject(CUSTOMEID, customerId);
        }
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 跳转创建小组类型页面
     * 
     * @param type
     *            0:公开小组 1：私密小组
     * @return
     */
    @RequestMapping("/togrouptype")
    public ModelAndView groupType(HttpServletRequest request, String type) {
        ModelAndView mav = null;
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        // 登陆验证
        if (customerId != null) {
            if ("0".equals(type)) {
                mav = new ModelAndView("newsocial/publicgroup", CUSTOMEID, customerId);
            } else if ("1".equals(type)) {
                mav = new ModelAndView("newsocial/privategroup", CUSTOMEID, customerId);
            }
        } else {
            mav = new ModelAndView(new RedirectView("login.html"));
        }
        mav.addObject("type", type);
        mav.addObject(GROUPTYPELIST, groupTypeService.selectAll());
        mav.addObject("label", labelService.selectAll());
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 保存小组
     * 
     * @param group
     *            小组对象{@link com.ningpai.group.bean.Group;}
     * @return
     */
    @RequestMapping("/savegroup")
    public ModelAndView saveGroup(HttpServletResponse response, HttpServletRequest request, Group group, GroupPermissions permissions, String type,
            @ModelAttribute("uploadForm") FileUploadFormSite uploadForm) {
        LOGGER.info(ValueUtil.SAVAGROUP);
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupLabelIds = request.getParameterValues("groupLabelId");
        group.setGroupLabelIds(groupLabelIds);
        try {
            pw = response.getWriter();
            if (request.getContentLength() > 2 * 1024 * 1024) {
                pw.print(-1);
            } else {
                String groupPic = UploadUtil.uploadFileCustomerHeadOne(uploadForm.getGroupPic(), request);
                group.setGroupHead(groupPic);
                // 非空验证 小组名称
                if (null != group.getGroupName()) {
                    LOGGER.info("保存【" + group.getGroupName() + "】小组成功！");
                }
                pw.print(groupService.saveGroup(group, permissions, type));
            }
        } catch (IOException e) {
            LOGGER.error("",e);
            new ModelAndView("error");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 跳转到我管理/加入的小组
     * 
     * @param request
     * @param group
     *            用户ID
     * @return mav
     */
    @RequestMapping("/myjoinedgroup")
    public ModelAndView myjoinedGroup(HttpServletRequest request, GroupVo group, PageBean pb) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        ModelAndView mav = new ModelAndView("newsocial/myjoinedgroup");
        mav.addObject(CUSID, customerId);
        // 小组总数
        mav.addObject(GROUPCOUNT, groupService.groupCount());
        // 成员总数
        mav.addObject(GROUPMEMBER, groupService.groupMember());
        // 小组分类
        mav.addObject(GROUPTYPELIST, groupTypeService.selectAll());
        // 我管理的小组
        mav.addObject("mymanagergroup", groupService.myManagerGroups(customerId, group));
        // 我加入的小组
        mav.addObject("myjoinedgroup", groupService.myJoinedGroup(pb, customerId, group));
        // 查询社区轮播广告
        mav.addObject(ADVLIST, advService.selectAdvListByPosition("1"));
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 跳转到小组详情页
     * 
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/groupdetail")
    public ModelAndView groupDetail(HttpServletRequest request, PageBean pb, GroupVo groupVo, GroupCustomer customer, TopicVo topic, String sort, String screening) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        ModelAndView mav = new ModelAndView("newsocial/groupdetail");
        if (customerId != null) {
            customer.setCustomerId(customerId);
            mav.addObject(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            mav.addObject(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        if (!groupService.selectGroup(groupVo).isEmpty()) {
            mav.addObject(GROUP, groupService.selectGroup(groupVo).get(0));
        } else {
            return new ModelAndView(ValueUtil.NOTFOUND);
        }
        // 人气小组
        mav.addObject("hotlist", groupService.findGroupList(groupVo, "3"));
        // 最新加入小组成员
        mav.addObject(GROUPMEMBER, groupService.customers(customer));
        // 小组相册图片数目
        mav.addObject(IMGCOUNT, imgSiteService.groupPhotoCountById(groupVo));
        // 小组相册
        mav.addObject("groupImgs", imgSiteService.selectGroupImgByLimit(groupVo));
        // 特别置顶话题
        mav.addObject("specialtopic", topicSiteService.specialHotTopic(topic));
        // 一般置顶
        mav.addObject("commontopic", topicSiteService.commHotTopic(topic));
        if (topic.getTopicTitle() != null && !"".equals(topic.getTopicTitle())) {
            try {
                String title = new String(topic.getTopicTitle().getBytes(ConstantUtil.ISOUTIL), ConstantUtil.UTFUTIL);
                mav.addObject("topictitle", title);
                topic.setTopicTitle(title);
                if (null != groupVo.getGroupName()) {
                    LOGGER.info("跳转到【" + groupVo.getGroupName() + "】小组详情页！");
                }
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("跳转到小组详情页失败！", e);
            }
        }
        // 普通话题
        mav.addObject(ConstantUtil.PB, topicSiteService.groupTopics(pb, topic, sort, screening));
        mav.addObject("sort", sort != null && !"".equals(sort) ? sort : null);
        mav.addObject("screening", screening != null && !"".equals(screening) ? screening : null);
        // 小组详情页页面广告
        mav.addObject(ADVLIST, advService.selectAdvListByPosition("1"));
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 加入小组
     * 
     * @param request
     * @return
     */
    @RequestMapping("/addgroup")
    @ResponseBody
    public Object addGroup(HttpServletRequest request, HttpServletResponse response, GroupVo groupVo, GroupCustomer groupcustomer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        int status = 0;
        String url = null;
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId == null) {
            url = request.getContextPath() + "/login.html";
            return url;
        } else {
            CustomerAllInfo info = groupService.selectById(customerId);
            status = groupService.addCustomer(customerId, groupVo, groupcustomer, info);
            if (null != groupVo.getGroupName()) {
                LOGGER.info("加入【" + groupVo.getGroupName() + "】小组！");
            }

            return status;
        }
    }

    /**
     * 退出小组
     * 
     * @param response
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    @RequestMapping("/outgroup")
    public ModelAndView outGroup(HttpServletResponse response, GroupCustomer customer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(groupService.updateCustomer(customer));
            // 小组成员名称 非空验证
            if (null != customer.getCustomerName()) {
                LOGGER.info("加入【" + customer.getCustomerName() + "】小组！");
            }
        } catch (IOException e) {
            LOGGER.error("退出小组失败！", e);
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
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    @RequestMapping("/invitefriends")
    public ModelAndView inviteFriends(HttpServletRequest request, PageBean pb, GroupVo groupVo, GroupCustomer customer, String keyvalue) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        String keyvalue1 = keyvalue;
        customer.setCustomerId(customerId);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 小组详情
        resultMap.put(GROUP, groupService.selectGroup(groupVo).get(0));
        // 最新加入小组成员
        resultMap.put(GROUPMEMBER, groupService.customers(customer));
        if (keyvalue1 != null && !"".equals(keyvalue1)) {
            try {
                keyvalue1 = new String(keyvalue1.getBytes(ConstantUtil.ISOUTIL), ConstantUtil.UTFUTIL);
                resultMap.put(CUSTOMERNAME, keyvalue1);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("邀请好友失败！", e);
            }
        }
        // 相互关注的好友
        resultMap.put(ConstantUtil.PB, fansSiteService.fansCustomer(pb, customerId, keyvalue1));

        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/invitefriend")).addAllObjects(resultMap);
    }

    /**
     * 小组所有成员列表
     * 
     * @param pb
     * @param customer
     * @return
     */
    @RequestMapping("/groupmemberlist")
    public ModelAndView groupMemberList(GroupVo groupVo, PageBean pb, GroupCustomer customer) {
        Map<String, Object> resultMasp = new HashMap<String, Object>();
        if (customer.getCustomerName() != null && !"".equals(customer.getCustomerName())) {
            try {
                String name = new String(customer.getCustomerName().getBytes(ConstantUtil.ISOUTIL), ConstantUtil.UTFUTIL);
                resultMasp.put(CUSTOMERNAME, name);
                customer.setCustomerName(name);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("小组所有成员列表失败！", e);
            }
        }
        // 最新加入小组成员
        resultMasp.put(GROUPMEMBER, groupService.customers(customer));
        // 小组组长
        resultMasp.put("groupLeader", groupService.customers(customer, "1").get(0));
        // 小组管理员
        resultMasp.put("groupManager", groupService.customers(customer, "2"));
        // 小组其他成员
        resultMasp.put(ConstantUtil.PB, groupService.groupMember(pb, customer));
        // 小组详情
        resultMasp.put(GROUP, groupService.selectGroup(groupVo).get(0));
        // resultMasp.put(CUSTOMERNAME,customer.getCustomerName());

        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupmember")).addAllObjects(resultMasp);
    }

    /**
     * 上传小组图片页面
     * 
     * @param request
     * @param group
     *            小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/toaddgroupimg")
    public ModelAndView toAddGroupImg(HttpServletRequest request, GroupVo group) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (groupService.selectGroup(group) != null && !groupService.selectGroup(group).isEmpty()) {
            resultMap.put(GROUP, groupService.selectGroup(group).get(0));
        }
        resultMap.put(CUSID, customerId);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/addgroupimg")).addAllObjects(resultMap);
    }

    /**
     * 上传小组图片
     * 
     * @param response
     * @param multipartRequest
     * @param groupImg
     * @return
     */
    @RequestMapping("/uploadgroupimg")
    public ModelAndView uploadGroupImg(HttpServletResponse response, MultipartHttpServletRequest multipartRequest, GroupImg groupImg) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            int width = 0;
            int height = 0;
            pw = response.getWriter();
            MultipartFile multiparFile = multipartRequest.getFile("filedata");
            // 获取图片名字
            String imageTitle = multiparFile.getOriginalFilename();
            String imgUrl = UploadUtil.uploadFileOne(multiparFile, multipartRequest);
            File file = new File(imageTitle);
            multiparFile.transferTo(file);
            width = ImageTools.getImgWidth(file);
            height = ImageTools.getImgHeight(file);
            pw.print(imgSiteService.uploadGHroupImg(groupImg, imgUrl, imageTitle, width, height));
            // 非空验证 小组名称
            if (null != groupImg.getCustomerName()) {
                LOGGER.info("【" + groupImg.getCustomerName() + "】小组上传图片");
            }
        } catch (IOException e) {
            LOGGER.error("上传小组图片失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 编辑最新上传图片
     * 
     * @param request
     * @param img
     *            小组图片
     * @param count
     * @return
     */
    @RequestMapping("/editgroupnewupload")
    public ModelAndView editGroupNewUpload(HttpServletRequest request, GroupImg img, Integer count) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("imglist", imgSiteService.newUploadImg(img, count));
        resultMap.put("groupId", img.getGroupId());
        // 非空验证 小组名称
        if (null != img.getCustomerName()) {
            LOGGER.info("编辑【" + img.getCustomerName() + "】小组最新上传的图片");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/editgroupimg")).addAllObjects(resultMap);
    }

    /**
     * 保存小组图片
     * 
     * @param response
     * @param groupImgId
     *            图片Id
     * @param groupImgTitle
     *            图片标题
     * @param groupImgDes
     *            图片描述
     * @return
     */
    @RequestMapping("/savegroupimg")
    public ModelAndView saveGroupImg(HttpServletResponse response, Long[] groupImgId, String[] groupImgTitle, String[] groupImgDes) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(imgSiteService.saveGroupImg(groupImgId, groupImgTitle, groupImgDes));
        } catch (IOException e) {
            LOGGER.error("保存小组图片失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 小组相册
     * 
     * @param request
     * @param pb
     *            分页
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return mav
     */
    @RequestMapping("/groupimgalbums")
    public ModelAndView groupImgAlbums(HttpServletRequest request, PageBean pb, GroupVo groupVo, GroupCustomer customer) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        if (groupService.selectGroup(groupVo) != null && !groupService.selectGroup(groupVo).isEmpty()) {
            resultMap.put(GROUP, groupService.selectGroup(groupVo).get(0));
        }
        // 小组相册图片总数
        resultMap.put(IMGCOUNT, imgSiteService.groupPhotoCountById(groupVo));
        // 小组相册图片列表
        resultMap.put(ConstantUtil.PB, imgSiteService.groupImgList(pb, groupVo, null));

        resultMap.put(CUSID, customerId);
        // 最新评论
        resultMap.put("reply", replySiteService.groupImgLastestReply(groupVo.getGroupId()));
        // 非空验证 小组名称
        if (null != groupVo.getGroupName()) {
            LOGGER.info("查询小组【" + groupVo.getGroupName() + "】的图片列表");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupalbums")).addAllObjects(resultMap);
    }

    /**
     * 小组相册(我的图片)
     * 
     * @param request
     * @param pb
     *            分页
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return mav
     */
    @RequestMapping("/mygroupimg")
    public ModelAndView mygroupImg(HttpServletRequest request, PageBean pb, GroupVo groupVo, GroupCustomer customer) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
        }
        resultMap.put(GROUP, groupService.selectGroup(groupVo).get(0));
        // 小组相册图片总数
        resultMap.put(IMGCOUNT, imgSiteService.groupPhotoCountById(groupVo));
        // 小组相册图片列表
        resultMap.put(ConstantUtil.PB, imgSiteService.groupImgList(pb, groupVo, customerId));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupalbums")).addAllObjects(resultMap);
    }

    /**
     * 根据图片Id删除图片
     * 
     * @param response
     * @param request
     * @param groupImgId
     *            小组图片ID {@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deletegroupimgs")
    public ModelAndView deleteGroupImgs(HttpServletResponse response, HttpServletRequest request, Long groupImgId) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupImgIds = request.getParameterValues("groupImgIds[]");
        try {
            pw = response.getWriter();
            if (groupImgId != null) {
                pw.print(imgSiteService.deleteGroupImgById(groupImgId));
            }
            if (groupImgIds != null) {
                for (String imgId : groupImgIds) {
                    pw.print(imgSiteService.deleteGroupImgById(Long.valueOf(imgId)));
                }
            }
            // 根据小组图片ID获取单个的小组图片对象
            GroupImg groupImg = imgSiteService.groupImgById(groupImgId);
            // 非空验证 小组名称
            if (null != groupImg.getGroupImgTitle()) {
                LOGGER.info("删除标题为【" + groupImg.getGroupImgTitle() + "】的图片");
            }
        } catch (IOException e) {
            LOGGER.error("根据图片Id删除图片失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 小组相片详情页
     * 
     * @param request
     * @param pb
     *            分页
     * @param groupImg
     *            小组相片{@link com.ningpai.group.bean.GroupImg}
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @param groupVo
     *            小组Vo @{link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/groupimgdetail")
    public ModelAndView groupImgDetail(HttpServletRequest request, PageBean pb, GroupImg groupImg, GroupCustomer customer, GroupVo groupVo, Long ta) {
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        // 小组相册列表
        List<GroupImg> imgList = imgSiteService.groupImgList(groupImg.getGroupId());
        // 小组相片详情
        GroupImg img = imgSiteService.groupImgById(groupImg.getGroupImgId());
        if (img == null || groupService.selectGroup(groupVo).isEmpty()) {
            return new ModelAndView(ValueUtil.NOTFOUND);
        }
        for (int i = 0; i < imgList.size(); i++) {
            if (img.getGroupImgId().equals(imgList.get(i).getGroupImgId())) {
                if (i == 0) {
                    resultMap.put("prev", imgList.get(imgList.size() - 1).getGroupImgId());
                } else {
                    resultMap.put("prev", imgList.get(i - 1).getGroupImgId());
                }
                resultMap.put("startSlide", i + 1);
                if (i == imgList.size() - 1) {
                    resultMap.put("next", imgList.get(0).getGroupImgId());
                } else {
                    resultMap.put("next", imgList.get(i + 1).getGroupImgId());
                }
            }
        }
        // 小组相片回复
        resultMap.put(ConstantUtil.PB, replySiteService.groupImgReply(pb, groupImg, ta));
        resultMap.put("imglist", imgList);
        resultMap.put("img", img);
        resultMap.put(GROUP, groupService.selectGroup(groupVo).get(0));
        resultMap.put("ta", ta);
        resultMap.put(CUSID, customerId);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupimgdetail")).addAllObjects(resultMap);
    }

    /**
     * 修改图片描述信息
     * 
     * @param response
     * @param img
     * @return ModelAndView
     */
    @RequestMapping("editgrougimg")
    public ModelAndView editGroupImg(HttpServletResponse response, GroupImg img) {
        try {
            response.setContentType(ValueUtil.REQ_SETCONTENT);
            pw = response.getWriter();
            pw.print(imgSiteService.editGroupImg(img));
            // 非空验证 图片标题
            if (null != img.getGroupImgTitle()) {
                LOGGER.info("修改标题为【" + img.getGroupImgTitle() + "】的图片描述信息！");
            }
        } catch (IOException e) {
            LOGGER.error("修改图片描述信息失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 查询小组相册的所有相片
     * 
     * @param response
     * @param request
     * @param img
     *            小组相片 {@link com.ningpai.group.bean.GroupImg}
     * @param pb
     *            分页
     * @return
     */
    @RequestMapping("/nextgroupimg")
    public ModelAndView nextGroupImg(HttpServletResponse response, HttpServletRequest request, GroupImg img, PageBean pb, Long ta) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        // Long customerId = (Long)
        // request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        try {
            pw = response.getWriter();
            // 小组相册列表
            List<GroupImg> imgList = imgSiteService.groupImgList(img.getGroupId());
            NextVo nv = new NextVo();
            nv.setImg(imgSiteService.groupImgById(img.getGroupImgId()));
            for (int i = 0; i < imgList.size(); i++) {
                if (img.getGroupImgId().equals(imgList.get(i).getGroupImgId())) {
                    if (i == 0) {
                        nv.setPrev(imgList.get(imgList.size() - 1).getGroupImgId());
                    } else {
                        nv.setPrev(imgList.get(i - 1).getGroupImgId());
                    }
                    if (i == imgList.size() - 1) {
                        nv.setNext(imgList.get(0).getGroupImgId());
                    } else {
                        nv.setNext(imgList.get(i + 1).getGroupImgId());
                    }
                }
            }
            nv.setNowId(img.getGroupImgId());
            nv.setPb(replySiteService.groupImgReply(pb, img, null));
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
            pw.print(JSONArray.fromObject(nv, jsonConfig));
            // 非空验证 图片标题
            if (null != img.getGroupImgTitle()) {
                // 日志记录
                LOGGER.info("查询小组【" + img.getGroupImgTitle() + "】相册的所有相片");
            }
        } catch (IOException e) {
            LOGGER.error("查询小组相册的所有相片失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 跳转到我管理的小组的小组信息页面
     * 
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/groupinfo")
    public ModelAndView groupInfo(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 1);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupinfo")).addAllObjects(resultMap);
    }

    /**
     * 更新小组资料
     * 
     * @param response
     * @param group
     *            小组对象 {@link com.ningpai.group.bean.Group}
     * @return
     */
    @RequestMapping("/updategroupinfo")
    public ModelAndView updateGroupInfo(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadFormSite uploadForm, Group group) {
        MultipartFile groupBackGroundFile = uploadForm.getGroupBackGroundPath();
        if (groupBackGroundFile != null && groupBackGroundFile.getSize() != 0) {
            group.setGroupBackground(UploadUtil.uploadFileOne(groupBackGroundFile, request));
        }
        MultipartFile groupHead = uploadForm.getGroupPic();
        if (groupHead != null && groupHead.getSize() != 0) {
            group.setGroupHead(UploadUtil.uploadFileOne(groupHead, request));
        }
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(groupService.updateGroup(group));
            // 非空验证 小组名称
            if (null != group.getGroupName()) {
                // 日志记录
                LOGGER.info("更新小组【" + group.getGroupName() + "】的资料");
            }
        } catch (IOException e) {
            LOGGER.error("更新小组资料失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 小组头像
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/grouphead")
    public ModelAndView groupHead(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 2);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/grouphead")).addAllObjects(resultMap);
    }

    /**
     * 上传小组头像
     * 
     * @param response
     * @param request
     * @param uploadForm
     * @return
     */
    @RequestMapping("/updategrouphead")
    public String saveGroupHead(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadFormSite uploadForm) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        MultipartFile mFile = uploadForm.getGroupPic();
        String url = "";
        if (mFile != null && mFile.getSize() != 0) {
            url = UploadUtil.uploadFileOne(mFile, request);
        }
        try {
            pw = response.getWriter();
            pw.print(url);

        } catch (IOException e) {
            LOGGER.error("上传小组头像失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 小组背景
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/background")
    public ModelAndView backgroudGroup(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 3);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/background")).addAllObjects(resultMap);
    }

    /**
     * 上传小组背景
     * 
     * @param response
     * @param request
     * @param uploadForm
     * @return ModelAndView
     */
    @RequestMapping("/uploadbackground")
    public ModelAndView uploadBackground(HttpServletResponse response, HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadFormSite uploadForm) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            MultipartFile mFile = uploadForm.getGroupBackGroundPath();
            String url = "";
            if (mFile != null && mFile.getSize() != 0) {
                // Settings setting =settingService.selectSet("9");
                url = UploadUtil.uploadFileOne(mFile, request);
            }
            pw.print(url);
        } catch (IOException e) {
            LOGGER.error("上传小组背景失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }

        }
        return null;

    }

    /**
     * 修改小组背景
     * 
     * @param request
     * @param response
     * @param groupVo
     * @return null
     */
    @RequestMapping("/editgroupbg")
    public ModelAndView editGroupbg(HttpServletRequest request, HttpServletResponse response, GroupVo groupVo) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(groupService.editGroupBg(groupVo));
            // 小组名称 非空验证
            if (null != groupVo.getGroupName()) {
                LOGGER.info("修改小组【" + groupVo.getGroupName() + "】背景");
            }
        } catch (IOException e) {
            LOGGER.error("修改小组背景失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 访问权限
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/accesslimit")
    public ModelAndView accessLimit(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 4);
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupaccess")).addAllObjects(resultMap);
    }

    /**
     * 设置小组访问权限
     * 
     * @param response
     * @param group
     * @param groupPermissions
     * @return
     */
    @RequestMapping("/updateaccsee")
    public ModelAndView updateAccessLimit(HttpServletResponse response, Group group, GroupPermissions groupPermissions) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(groupService.updateGroupLimit(group, groupPermissions));
            // 小组名称 非空验证
            if (null != group.getGroupName()) {
                LOGGER.info("设置小组【" + group.getGroupName() + "】权限");
            }
        } catch (IOException e) {
            LOGGER.error("设置小组访问权限失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 删除权限
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/deletelimit")
    public ModelAndView deleteLimit(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 5);
        // 小组名称 非空验证
        if (null != groupVo.getGroupName()) {
            LOGGER.info("删除小组【" + groupVo.getGroupName() + "】权限");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/deleteaccess")).addAllObjects(resultMap);
    }

    /**
     * 管理员权限
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/managerlimit")
    public ModelAndView managerLimit(HttpServletRequest request, GroupVo groupVo, PageBean pb, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 6);
        resultMap.put(ConstantUtil.PB, groupService.selectByGroupId(pb, customer, "2"));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupmanager")).addAllObjects(resultMap);
    }

    /**
     * 添加管理员界面
     * 
     * @param request
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param pb
     *            PageBean
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    @RequestMapping("/groupaddmanager")
    public ModelAndView groupAddManager(HttpServletRequest request, GroupVo groupVo, PageBean pb, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 6);
        if (customer.getCustomerName() != null && !"".equals(customer.getCustomerName())) {
            try {
                String name = new String(customer.getCustomerName().getBytes(ConstantUtil.ISOUTIL), ConstantUtil.UTFUTIL);
                resultMap.put(CUSTOMERNAME, name);
                customer.setCustomerName(name);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("设置小组访问权限失败！", e);
            }
        }
        resultMap.put(ConstantUtil.PB, groupService.selectByGroupId(pb, customer, "1"));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupaddmanager")).addAllObjects(resultMap);
    }

    /**
     * 解散小组页面
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/dissolvedgroup")
    public ModelAndView dissolvedGroup(HttpServletRequest request, GroupVo groupVo, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 7);
        // 小组名称 非空验证
        if (null != groupVo.getGroupName()) {
            // 日志记录
            LOGGER.info("解散小组【" + groupVo.getGroupName() + "】");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/groupdissolve")).addAllObjects(resultMap);
    }

    /**
     * 设置解散小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/dissolvegroupbyid")
    public ModelAndView dissolveGroupById(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String groupId = request.getParameter("groupId");
        try {
            pw = response.getWriter();
            pw.print(groupService.dissolveGroupById(Long.valueOf(groupId)));

        } catch (IOException e) {
            LOGGER.error("设置解散小组失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 小组成员
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return
     */
    @RequestMapping("/groupmember")
    public ModelAndView groupMember(HttpServletRequest request, GroupVo groupVo, PageBean pb, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 8);
        resultMap.put(ConstantUtil.PB, groupService.selectByGroupId(pb, customer, "1"));
        // 小组名称 非空验证
        if (null != groupVo.getGroupName()) {
            // 日志记录
            LOGGER.info("获取小组【" + groupVo.getGroupName() + "】的成员");
        }
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/gmember")).addAllObjects(resultMap);
    }

    /**
     * 踢出小组
     * 
     * @param response
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    @RequestMapping("/delgroupgustomer")
    public ModelAndView delGroupCustomer(HttpServletResponse response, HttpServletRequest request, GroupCustomer customer) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] customerIds = request.getParameterValues(CUSTOMERIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < customerIds.length; i++) {
                customer.setCustomerId(Long.valueOf(customerIds[i]));
                pw.print(groupService.delGroupcustomer(customer));
            }
        } catch (IOException e) {
            LOGGER.error("踢出小组失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 加入黑名单
     * 
     * @param response
     * @param request
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @param black
     *            小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return
     */
    @RequestMapping("/blackmember")
    public ModelAndView blackMember(HttpServletResponse response, HttpServletRequest request, GroupCustomer customer, GroupBlack black) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] customerIds = request.getParameterValues(CUSTOMERIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < customerIds.length; i++) {
                customer.setCustomerId(Long.valueOf(customerIds[i]));
                pw.print(groupService.addBlack(customer, black));
            }
        } catch (IOException e) {
            LOGGER.error("加入黑名单失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 黑名单列表
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo} black 小组黑名单
     *            {@link com.ningpai.group.bean.GroupBlack}
     * @return
     */
    @RequestMapping("/blacklist")
    public ModelAndView blackList(HttpServletRequest request, GroupVo groupVo, PageBean pb, GroupBlack black, GroupCustomer customer) {
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 9);
        resultMap.put(ConstantUtil.PB, groupService.balckList(pb, black));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/blacklist")).addAllObjects(resultMap);
    }

    /**
     * 解除黑名单
     * 
     * @param response
     * @param request
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @param black
     *            小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return
     */
    @RequestMapping("/dissolvedblack")
    public ModelAndView dissolvedBlack(HttpServletResponse response, HttpServletRequest request, GroupCustomer customer, GroupBlack black) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] customerIds = request.getParameterValues(CUSTOMERIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < customerIds.length; i++) {
                customer.setCustomerId(Long.valueOf(customerIds[i]));
                black.setCustomerId(Long.valueOf(customerIds[i]));
                pw.print(groupService.dissolvedBlack(customer, black));
            }
        } catch (IOException e) {
            LOGGER.error("解除黑名单失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 禁止回应的话题
     * 
     * @param groupVo
     *            小组VO{@link com.ningpai.group.vo.GroupVo}
     * @param pb
     *            pageBean 分页
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.TopicVo}
     * @return
     */
    @RequestMapping("/noresponsetopic")
    public ModelAndView groupNoResponse(HttpServletRequest request, GroupVo groupVo, PageBean pb, TopicVo topic, GroupCustomer customer) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 10);
        resultMap.put(ConstantUtil.PB, topicSiteService.noResponseTopic(pb, topic));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/noresponsetopic")).addAllObjects(resultMap);
    }

    /**
     * 回收站
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param pb
     *            pageBean
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.TopicVo}
     * @return
     */
    @RequestMapping("/topicrecycle")
    public ModelAndView groupRecycle(HttpServletRequest request, GroupVo groupVo, PageBean pb, TopicVo topic, GroupCustomer customer) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = (Long) request.getSession().getAttribute(ConstantUtil.CUSTOMERID);
        if (customerId != null) {
            customer.setCustomerId(customerId);
            resultMap.put(CUSTOME, groupService.searchByCustomerId(customer, "1"));
            resultMap.put(CINFO, customerServiceInterface.queryCustomerById(customerId));
        }
        resultMap.put(GROUPINFO, groupService.selectGroup(groupVo).get(0));
        resultMap.put("flag", 11);
        resultMap.put(ConstantUtil.PB, topicSiteService.topicRecycle(pb, topic));
        return topAndBottomService.getTopAndBottom(new ModelAndView("newsocial/topicrecycle")).addAllObjects(resultMap);
    }

    /**
     * 好友主动发送加入好友请求，作为管理员的允许和加入
     * 
     * @param response
     * @param groupVo
     * @param request
     * @param groupCustomerExamine
     * @param managerId
     * @param flag
     * 
     * @return ModelAndView
     */
    @RequestMapping("/applyjoin")
    public ModelAndView applyJoin(HttpServletResponse response, GroupVo groupVo, HttpServletRequest request, Long customerId, String groupCustomerExamine, Long managerId,
            String flag) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            // 拒绝
            /*
             * if("2".equals(groupMemberExamine)){
             * customerSiteService.applyJoin(
             * groupVo.getGroupId(),customerId,"2",managerId,""); }else{
             */
            // 同意
            // 判断可加入小组上限
            // int syscount = 0;
            /*
             * int myGroupCount = groupMemberService.myJoinGroupCount(memberId);
             * if(settings!=null){ syscount =
             * Integer.parseInt(settings.getSettingsFive()); } if(syscount<=
             * myGroupCount){ pw.print("-1"); return null; }
             */
            // 判断系统小组组员上限
            // Group group = (Group) groupService.selectGroup(groupVo).get(0);
            /*
             * int sysMember=0; Long nowMember=group.getGroupLimitMember();
             */
            /*
             * Settings settings1 = settingsService.groupSet("4");
             * if(settings1!=null){ sysMember =
             * Integer.parseInt(settings1.getSettingsThree()); } if(sysMember<=
             * nowMember){ pw.print("-2"); return null; }
             */

            pw.print(customerSiteService.applyJoin(groupVo.getGroupId(), customerId, groupCustomerExamine, managerId, flag));
            // }

        } catch (IOException e) {
            LOGGER.error("好友主动发送加入好友请求，作为管理员的允许和加入失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    public GroupSiteService getGroupService() {
        return groupService;
    }

    @Resource(name = "GroupSiteService")
    public void setGroupService(GroupSiteService groupService) {
        this.groupService = groupService;
    }

    public GroupTypeSiteService getGroupTypeService() {
        return groupTypeService;
    }

    @Resource(name = "GroupTypeSiteService")
    public void setGroupTypeService(GroupTypeSiteService groupTypeService) {
        this.groupTypeService = groupTypeService;
    }

    public GroupImgSiteService getImgSiteService() {
        return imgSiteService;
    }

    @Resource(name = "GroupImgSiteService")
    public void setImgSiteService(GroupImgSiteService imgSiteService) {
        this.imgSiteService = imgSiteService;
    }

    public CustomerReplySiteService getReplySiteService() {
        return replySiteService;
    }

    @Resource(name = "CustomerReplySiteService")
    public void setReplySiteService(CustomerReplySiteService replySiteService) {
        this.replySiteService = replySiteService;
    }

    public TopicSiteService getTopicSiteService() {
        return topicSiteService;
    }

    @Resource(name = "TopicSiteService")
    public void setTopicSiteService(TopicSiteService topicSiteService) {
        this.topicSiteService = topicSiteService;
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

    public AdvService getAdvService() {
        return advService;
    }

    @Resource(name = "AdvService")
    public void setAdvService(AdvService advService) {
        this.advService = advService;
    }

    public LabelService getLabelService() {
        return labelService;
    }

    @Resource(name = "LabelService")
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }

    /*
     * private MasterService masterService;
     * 
     * public MasterService getMasterService() { return masterService; }
     * 
     * @Resource(name="MasterService") public void
     * setMasterService(MasterService masterService) { this.masterService =
     * masterService; }
     */

}
