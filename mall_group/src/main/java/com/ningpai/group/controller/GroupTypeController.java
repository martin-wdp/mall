/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.GroupType;
import com.ningpai.group.service.GroupTypeService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.ValueUtil;

/**
 * 小组分类控制器
 * 
 * @version 2014年5月30日 上午10:16:44
 * @author qiyuanyuan
 */

@Controller
public class GroupTypeController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GroupTypeController.class);

    private static final String GROUPTYPE_HTM = "grouptype.htm";

    private PrintWriter pw;

    private GroupTypeService groupTypeService;

    /**
     * 根据菜单导航跳转小组分类页面
     * 
     * @param pageBean
     * @return
     */
    @RequestMapping("/grouptype")
    public ModelAndView toGroupType(PageBean pageBean) {
        pageBean.setUrl(GROUPTYPE_HTM);
        return new ModelAndView("jsp/social/group_type", "pageBean", groupTypeService.selectGroupTypeList(null, pageBean));
    }

    /**
     * 添加小组分类
     * 
     * @param groupType
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @return
     */
    @RequestMapping("/addgrouptype")
    public ModelAndView addGroupType(HttpServletRequest request, GroupType groupType) {
        this.groupTypeService.addGroupTyope(groupType);
        if (null != groupType.getGroupTypeName() && "".equals(groupType.getGroupTypeName())) {
            LOGGER.info("添加小组分类，类型名称为：" + groupType.getGroupTypeName());
        } else {
            LOGGER.info("添加小组分类");
        }

        return new ModelAndView(new RedirectView(GROUPTYPE_HTM));
    }

    /**
     * 根据分类ID查询小组分类
     * 
     * @param groupTypeId
     *            分类ID
     * @return 对象
     */
    @RequestMapping("/querygrouptypebyid")
    @ResponseBody
    public GroupType queryGroupTypeById(Long groupTypeId) {
        LOGGER.info("根据分类ID查询小组分类，ID为:" + groupTypeId);
        return groupTypeService.selectByGroupTypeId(groupTypeId);
    }

    /**
     * 更新小组分类
     * 
     * @param groupType
     *            小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @return
     */
    @RequestMapping("/updategrouptype")
    public ModelAndView updateGroupType(GroupType groupType) {
        // 根据主键获取单个的分类对象
        GroupType groupTypes = groupTypeService.selectByGroupTypeId(groupType.getGroupTypeId());
        this.groupTypeService.updateGroupType(groupType);
        LOGGER.info(">>>>>>>更新小组分类，小组的分类名称为：" + groupTypes.getGroupTypeName());
        return new ModelAndView(new RedirectView(GROUPTYPE_HTM));
    }

    /**
     * 删除小组分类
     *
     * @param groupTypeId
     *            小组分类ID {@link java.lang.Long}
     * @return
     */
    @RequestMapping("/deletebyid")
    public ModelAndView deleteByGroupTypeId(Long groupTypeId) {
        // 根据主键获取单个的分类对象
        GroupType groupTypes = groupTypeService.selectByGroupTypeId(groupTypeId);
        if (null != groupTypes.getGroupTypeName() && !"".equals(groupTypes.getGroupTypeName())) {
            LOGGER.info("删除小组分类的名称为：" + groupTypes.getGroupTypeName());
        } else {
            LOGGER.info("删除小组分类");
        }

        this.groupTypeService.deleteGroupType(groupTypeId);

        return new ModelAndView(new RedirectView(GROUPTYPE_HTM));
    }

    /**
     * 停用小组分类
     *
     * @param request
     * @param response
     *
     * @return
     */
    @RequestMapping("/disablegroup")
    public ModelAndView updateDisgroupById(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupTypeIds = request.getParameterValues("groupTypeIds[]");
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupTypeIds.length; i++) {
                pw.print(groupTypeService.updateDisGroupByid(Long.valueOf(groupTypeIds[i])));
            }
            LOGGER.info("停用小组分类成功！");
        } catch (IOException e) {
            LOGGER.error("停用小组分类失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 恢复停用小组
     * 
     * @param request
     * @param response
     * 
     * @return
     */
    @RequestMapping("/recoverygroup")
    public ModelAndView updateGroupById(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        String[] groupTypeIds = request.getParameterValues("groupTypeIds[]");
        try {
            pw = response.getWriter();
            for (int i = 0; i < groupTypeIds.length; i++) {
                pw.print(groupTypeService.updateGroupById(Long.valueOf(groupTypeIds[i])));
            }
            LOGGER.info("恢复停用小组成功！");
        } catch (IOException e) {
            LOGGER.error("恢复停用小组失败！", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }

        return null;
    }

    /**
     * 验证分类名称是否可以添加
     * 
     * @param groupTypeName
     *            分类名称
     * 
     * @return 是否可以添加的标记 可以添加返回true 否则返回 false
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checktypename")
    @ResponseBody
    public boolean checkTypeName(String groupTypeName) throws UnsupportedEncodingException {
        String groupTypeNameNew = groupTypeName;
        // String typeName = request.getParameter("typeName");
        LOGGER.info(">>>>>>验证小组分类名称[" + groupTypeName + "]是否存在");
        // 如果查询到的为空 返回true 否则返回false
        groupTypeNameNew = URLDecoder.decode(groupTypeNameNew, ConstantUtil.UTF);
        return this.groupTypeService.queryTypeByTypeName(groupTypeNameNew) == null ? true : false;
    }

    /**
     * 按条件查询小组分类
     * 
     * @param groupType
     *            小组分类小组分类对象{@link com.ningpai.group.bean.GroupType}
     * @param pageBean
     * @return
     */
    @RequestMapping("/querybygrouptype")
    public ModelAndView queryByGroupType(GroupType groupType, PageBean pageBean, String attr) {
        String attrNew = attr;
        try {
            attrNew = URLDecoder.decode(attrNew, ConstantUtil.UTF);
            LOGGER.error("按条件查询小组分类,转码成功");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("按条件查询小组分类,转码失败！", e);
        }
        // 设置页面跳转路径
        pageBean.setUrl("querybygrouptype.htm");
        return new ModelAndView("jsp/social/group_type", "pageBean", groupTypeService.selectGroupTypeList(groupType, pageBean)).addObject("attr", attrNew);
    }

    public GroupTypeService getGroupTypeService() {
        return groupTypeService;
    }

    @Resource(name = "GroupTypeService")
    public void setGroupTypeService(GroupTypeService groupTypeService) {
        this.groupTypeService = groupTypeService;
    }

}
