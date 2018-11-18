/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupBean;
import com.ningpai.group.bean.GroupPermissions;
import com.ningpai.group.service.GroupService;
import com.ningpai.group.service.GroupTypeService;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.ValueUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 小组后台控制器
 * 
 * @version 2014年5月26日 下午2:12:16
 * @author qiyuanyuan
 */

@Controller
public class GroupController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GroupController.class);

    private static final String GROUPTYPELIST = "grouptypeList";
    private static final String PAGEBEAN = "pageBean";
    private static final String GROUPIDS = "groupIds[]";
    private static final String LOGGERINFO1 = "】的详细西信息";
    private static final String LOGGERINFO2 = "获取小组【";

    private GroupService groupService;

    private PrintWriter pw;

    private GroupTypeService groupTypeService;

    /**
     * 后台显示待审核小组列表
     * 
     * @param groupVo
     * @return mav
     */
    @RequestMapping("/checkgrouplist")
    public ModelAndView checkGroupList(GroupVo groupVo, PageBean pageBean) {
        pageBean.setUrl("checkgrouplist.htm");
        groupVo.setGroupCheckFlag("0");
        return new ModelAndView("jsp/social/checkgroup_list", PAGEBEAN, groupService.selectGroupList(groupVo, pageBean)).addObject(GROUPTYPELIST, groupTypeService.selectAll());
    }

    /**
     * 后台显示小组列表
     * 
     * @param groupVo
     * @return mav
     */
    @RequestMapping("/togroup")
    public ModelAndView togroup(GroupVo groupVo, PageBean pageBean) {
        pageBean.setUrl("togroup.htm");
        groupVo.setGroupCheckFlag("1");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(PAGEBEAN, groupService.selectGroupList(groupVo, pageBean));
        resultMap.put(GROUPTYPELIST, groupTypeService.selectAll());
        // resultMap.put("gro", groupService.groupMemberById());
        return new ModelAndView("jsp/social/group_list").addAllObjects(resultMap);
    }

    /**
     * 后台根据小组ID查询小组 实现修改数据展示
     * 
     * @param groupId
     *            小组编号
     * @return GroupVo 小组信息 {@link com.ningpai.group.vo.GroupVo}
     */
    @RequestMapping("/querygroupvobyid")
    @ResponseBody
    public GroupVo queryGroupVoById(Long groupId) {
        // 根据主键获取 单个小组对象
        GroupVo groupVo = groupService.selectGroupVoByGroupId(groupId);
        // 非空验证 小组名称
        if (null != groupVo.getGroupName()) {
            LOGGER.info(LOGGERINFO2 + groupVo.getGroupName() + LOGGERINFO1);
        }
        return groupVo;
    }

    /**
     * 后台显示活跃小组
     * 
     * @param groupVo
     *            小组信息 {@link com.ningpai.group.vo.GroupVo}
     * @param pageBean
     *            分页
     * @return
     */
    @RequestMapping("/activegrouplist")
    public ModelAndView activeGroupList(GroupVo groupVo, PageBean pageBean) {
        // 结果集
        pageBean.setUrl("activegrouplist.htm");
        groupVo.setGroupCheckFlag("1");
        groupVo.setGroupSecret("0");
        return new ModelAndView("jsp/social/activegroup_list", PAGEBEAN, groupService.selectGroupList(groupVo, pageBean));
    }

    /**
     * 后台根据小组ID查询小组详细信息
     *
     * @param groupId
     *            小组编号
     * @return GroupVo 小组信息 {@link com.ningpai.group.vo.GroupVo}
     */
    @RequestMapping("/querygroupvo")
    public ModelAndView queryGroupVo(Long groupId) {
        // 根据主键获取 单个小组对象
        GroupVo groupVo = groupService.selectGroupVoByGroupId(groupId);
        // 非空验证 小组名称
        if (null != groupVo.getGroupName()) {
            LOGGER.info(LOGGERINFO2 + groupVo.getGroupName() + LOGGERINFO1);
        }

        return new ModelAndView("/jsp/social/group_detail", "group", groupVo);
    }

    /**
     * 后台根据小组ID查询小组详细信息
     *
     * @param groupId
     *            小组编号
     * @return GroupVo 小组信息 {@link com.ningpai.group.vo.GroupVo}
     */
    @RequestMapping("/querygroupvoNew")
    @ResponseBody
    public GroupVo queryGroupVoNew(Long groupId) {
        // Long a = groupId;
        // 根据主键获取 单个小组对象
        GroupVo groupVo = groupService.selectGroupVoByGroupId(groupId);
        // 非空验证 小组名称
        if (null != groupVo.getGroupName()) {
            LOGGER.info(LOGGERINFO2 + groupVo.getGroupName() + LOGGERINFO1);
        }
        // ModelAndView mav = new ModelAndView("/jsp/social/group_detail",
        // "group", groupVo);
        return groupVo;
    }

    /**
     * 通过待审核的小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/passgroup")
    public ModelAndView passGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.passGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("审核小组成功");
        } catch (IOException e) {
            LOGGER.error("审核小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 拒绝待审核的小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/refusegroup")
    public ModelAndView refuseGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupIds = request.getParameterValues(GROUPIDS);
        String reason = request.getParameter("refuseReason");
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupIds.length; i++) {
                pw.print(groupService.refuseGroup(Long.valueOf(groupIds[i]), reason));
            }
            LOGGER.info("审核小组成功");
        } catch (IOException e) {
            LOGGER.error("审核小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 解散小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/dissolvegroup")
    public ModelAndView dissolveGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.dissolvGroupById(Long.valueOf(groupId[i])));
            }
            LOGGER.info("解散小组成功");
        } catch (IOException e) {
            LOGGER.error("解散小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置活跃小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/activegroup")
    public ModelAndView activeGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.activeGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置活跃小组成功！");
        } catch (IOException e) {
            LOGGER.error("后台设置活跃小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置活跃小组为一般小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/commongroup")
    public ModelAndView commonGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.commonGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置活跃小组为一般小组成功！");
        } catch (IOException e) {
            LOGGER.error("后台设置活跃小组为一般小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置热门小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/hotgroup")
    public ModelAndView hotGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.hotGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置热门小组成功！");
        } catch (IOException e) {
            LOGGER.error("后台设置热门小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置热门小组为一般小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/cancelhotgroup")
    public ModelAndView cancelHotGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.cancelHotGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置热门小组为一般小组成功!");
        } catch (IOException e) {
            LOGGER.error("后台设置热门小组为一般小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置特别推荐小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/recommendgroup")
    public ModelAndView recommendGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.recommendGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置特别推荐小组成功!");
        } catch (IOException e) {
            LOGGER.error("后台设置特别推荐小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 后台设置取消特别推荐小组
     * 
     * @param response
     * @param request
     * @return
     */
    @RequestMapping("/cancelrecommendgroup")
    public ModelAndView cancelRecommendGroup(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupId = request.getParameterValues(GROUPIDS);
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupId.length; i++) {
                pw.print(groupService.cancelrecommendGroup(Long.valueOf(groupId[i])));
            }
            LOGGER.info("后台设置取消特别推荐小组成功!");
        } catch (IOException e) {
            LOGGER.error("后台设置取消特别推荐小组失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 根据条件查询待审核小组
     * 
     * @param groupVo
     *            小组信息 {@link com.ningpai.group.vo.GroupVo}
     * @param pageBean
     * @param groupname
     *            小组名称
     * @return
     */
    @RequestMapping("/querybycheckedgroup")
    public ModelAndView queryBycheckedGroup(GroupVo groupVo, String createtime, String createtimeto, PageBean pageBean, String showflag, String groupname) {
        String groupnameNew = groupname;
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            groupnameNew = URLDecoder.decode(groupnameNew, ConstantUtil.UTF);
            // 将时间按照指定格式转换
            groupVo.setGroupCreateTime(createtime == null || "".equals(createtime) ? null : formatDate.parse(createtime));
            groupVo.setGroupCreateTimeTo(createtimeto == null || "".equals(createtimeto) ? null : formatDate.parse(createtimeto));
            LOGGER.info("根据条件查询待审核小组成功！");
        } catch (Exception e) {
            LOGGER.error("根据条件查询待审核小组失败!", e);
        }
        groupVo.setGroupCheckFlag("0");
        // 设置页面跳转路径
        pageBean.setUrl("querybycheckedgroup.htm");
        return new ModelAndView("jsp/social/checkgroup_list", PAGEBEAN, groupService.selectGroupList(groupVo, pageBean)).addObject("showflag", showflag)
                .addObject("groupname", groupnameNew).addObject(GROUPTYPELIST, groupTypeService.selectAll());
    }

    /**
     * 根据条件查询小组
     * 
     * @param groupVo
     *            小组信息 {@link com.ningpai.group.vo.GroupVo}
     * @param pageBean
     * @param groupBean
     *            小组名称
     * @return
     */
    @RequestMapping("/querybygroup")
    public ModelAndView queryByGroup(GroupVo groupVo, PageBean pageBean, String createtime, String createtimeto, GroupBean groupBean) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            groupBean.setConvalue(URLDecoder.decode(groupBean.getConvalue(), ConstantUtil.UTF));
            groupBean.setCondition(URLDecoder.decode(groupBean.getCondition(), ConstantUtil.UTF));
            // 将时间按照指定格式转换
            groupVo.setGroupCreateTime(createtime == null || "".equals(createtime) ? null : formatDate.parse(createtime));
            groupVo.setGroupCreateTimeTo(createtimeto == null || "".equals(createtimeto) ? null : formatDate.parse(createtimeto));
            LOGGER.info("根据条件查询小组成功!");
        } catch (Exception e) {
            LOGGER.error("根据条件查询小组失败!", e);
        }
        groupVo.setGroupCheckFlag("1");
        // 设置页面跳转路径
        pageBean.setUrl("querybygroup.htm");
        resultMap.put(PAGEBEAN, groupService.selectGroupList(groupVo, pageBean));
        resultMap.put("group", groupVo);
        resultMap.put("groupBean", groupBean);
        resultMap.put(GROUPTYPELIST, groupTypeService.selectAll());
        resultMap.put("convlaue", groupBean.getConvalue());
        resultMap.put("condition", groupBean.getCondition());
        // resultMap.put("gro", groupService.groupMemberById());
        return new ModelAndView("jsp/social/group_list").addAllObjects(resultMap).addObject("groupVo", groupVo);

    }

    /**
     * 按条件查询活跃小组
     * 
     * @param groupVo
     *            小组信息 {@link com.ningpai.group.vo.GroupVo}
     * @param pageBean
     * @return
     */
    @RequestMapping("/querybyactivegroup")
    public ModelAndView queryByActiveGroup(GroupVo groupVo, PageBean pageBean, String groupname, String groupisactive) {
        String groupnameNew = groupname;
        // 结果集
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            groupnameNew = URLDecoder.decode(groupnameNew, ConstantUtil.UTF);
            LOGGER.error("按条件查询活跃小组，转码成功");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("按条件查询活跃小组，转码失败!", e);
        }
        groupVo.setGroupSecret("0");
        // 设置页面跳转路径
        pageBean.setUrl("querybyactivegroup.htm");
        resultMap.put(PAGEBEAN, groupService.selectGroupList(groupVo, pageBean));
        resultMap.put("groupname", groupnameNew);
        resultMap.put("groupisactive", groupisactive);
        // resultMap.put("grpo", groupService.groupMemberById());
        return new ModelAndView("jsp/social/activegroup_list").addAllObjects(resultMap);

    }

    /**
     * 设置小组
     * 
     * @param group
     *            小组对象 {@link com.ningpai.group.bean.Group}
     * @param groupPermissions
     *            小组权限 {@link com.ningpai.group.bean.GroupPermissions}
     * @return
     */
    @RequestMapping("updategroup")
    public ModelAndView updateGroup(Group group, GroupPermissions groupPermissions) {
        LOGGER.info(">>>>>设置小组");
        groupService.updateGroup(group, groupPermissions);
        return new ModelAndView(new RedirectView("togroup.htm"));
    }

    public GroupService getGroupService() {
        return groupService;
    }

    @Resource(name = "GroupService")
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    public GroupTypeService getGroupTypeService() {
        return groupTypeService;
    }

    @Resource(name = "GroupTypeService")
    public void setGroupTypeService(GroupTypeService groupTypeService) {
        this.groupTypeService = groupTypeService;
    }

}
