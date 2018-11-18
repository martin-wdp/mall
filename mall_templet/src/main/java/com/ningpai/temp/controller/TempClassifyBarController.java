/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ClassifyBarCateService;
import com.ningpai.temp.service.ClassifyBarQuickService;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:23:51
 */
@Controller
public class TempClassifyBarController {

    // Map中的key值
    private static final String CLASSIFYBAR = "classifyBar";
    private static final String ISTOP = "isTop";
    private static final String CATELIST = "cateList";
    private static final String TEMPID = "tempId";
    private static final String CHANNELID = "channelId";
    private static final String PARENTID = "parentId";
    private static final String ISDEFIND = "isDefind";
    private static final String JUMPFORCLASSIFYBARVIEW1 = "jumpForClassifyBarView.htm?tempId=";
    private static final String JUMPFORCLASSIFYBARVIEW2 = "jumpForClassifyBarView.htm?channelId=";
    private static final String IMGSRC = "imgSrc";
    private static final String INFO1 = "添加分类导航";
    private static final String INFO2 = ",用户名:";
    private static final String INFO3 = "修改分类导航";

    /* 业务逻辑层依赖 */
    private TempService tempService;
    private ChannelService channelService;
    private ClassifyBarService classifyBarService;
    private GoodsCateService goodsCateService;

    @Resource(name = "ClassifyBarCateService")
    private ClassifyBarCateService barCateService;
    @Resource(name = "ClassifyBarQuickService")
    private ClassifyBarQuickService barQuickService;

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(TempClassifyBarController.class);

    /**
     * 跳转到分类导航列表视图
     */
    @RequestMapping("/jumpForClassifyBarView")
    public ModelAndView jumpForClassifyBarView(Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();

        if (null == channelId) {
            SysTemp temp = this.tempService.getSystempById(tempId);
            mav.addObject("temp", temp);
        } else {
            Channel channel = channelService.findChannelByID(channelId);
            mav.addObject("channel", channel);
        }
        mav.setViewName("jsp/temp/temp_classifyBar_list");
        return mav;
    }

    /**
     * 查询模板分类导航列表
     * 
     * @param pb
     * @param tempId
     * @return
     */
    @ResponseBody
    @RequestMapping("/queryTempClassifyBar")
    public PageBean queryTempClassifyBar(PageBean pb, Long tempId, Long channelId) {
        return classifyBarService.selectClassifyBarByParam(pb, tempId, channelId, null);
    }

    /**
     * 查看分类导航
     * 
     * @param classifyBarId
     * @return
     */
    @RequestMapping("/showTempClassifyBar")
    public ModelAndView showTempClassifyBar(Long classifyBarId, Long tempId, Long channelId, Long parentId) {
        Long parentIdNew = parentId;
        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * 获取商品分类
         */
        List<GoodsCate> cateList = null;

        // 修改
        if (null != classifyBarId) {
            ClassifyBar classifyBar = classifyBarService.getClassifyBarById(classifyBarId);
            map.put(CLASSIFYBAR, classifyBar);
            // 判断分类导航是否有父分类
            if (null != classifyBar.getParentId() && classifyBar.getParentId() > 0) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGoodsCatId();
                cateList = this.goodsCateService.queryGoosCateByParentId(parentIdCate);
                parentIdNew = classifyBar.getParentId();
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
            // 添加
        } else {
            if (null != parentIdNew) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(parentIdNew).getGoodsCatId();
                cateList = this.goodsCateService.queryGoosCateByParentId(parentIdCate);
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
        }
        map.put(CATELIST, cateList);
        map.put(TEMPID, tempId);
        map.put(CHANNELID, channelId);
        map.put(PARENTID, parentIdNew);
        return new ModelAndView("jsp/temp/show_temp_classifyBar", "map", map);
    }

    /**
     * 查看分类导航
     *
     * @param classifyBarId
     * @return
     */
    @RequestMapping("/showtempclassifybarajax")
    @ResponseBody
    public Map<String, Object> showTempClassifyBarAjax(Long classifyBarId, Long tempId, Long channelId, Long parentId) {
        Map<String, Object> map = new HashMap<String, Object>();
        /*
         * 获取商品分类
         */
        List<GoodsCate> cateList = null;

        // 修改
        if (null != classifyBarId) {
            ClassifyBar classifyBar = classifyBarService.getClassifyBarById(classifyBarId);
            map.put(CLASSIFYBAR, classifyBar);
            // 判断分类导航是否有父分类
            if (null != classifyBar.getParentId() && classifyBar.getParentId() > 0) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGoodsCatId();
                cateList = this.goodsCateService.queryGoosCateByParentId(parentIdCate);
                parentId = classifyBar.getParentId();
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
            // 添加
        } else {
            if (null != parentId) {
                // 根据父分类关联的商品分类查找它的子商品分类列表
                Long parentIdCate = classifyBarService.getClassifyBarById(parentId).getGoodsCatId();
                cateList = this.goodsCateService.queryGoosCateByParentId(parentIdCate);
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
        }
        map.put(CATELIST, cateList);
        map.put(TEMPID, tempId);
        map.put(CHANNELID, channelId);
        map.put(PARENTID, parentId);
        return map;
    }

    /**
     * 新查看分类导航
     * 
     * @param classifyBarId
     * @param tempId
     * @return
     */
    @RequestMapping("/showTempClassifyBarAndCateAndQuick")
    public ModelAndView showTempClassifyBarAndCateAndQuick(Long classifyBarId, Long tempId, Long channelId, Long parentId) {
        Map<String, Object> map = new HashMap<String, Object>();

        /*
         * 获取商品分类
         */
        List<GoodsCate> cateList = new ArrayList<GoodsCate>();

        // 修改
        if (null != classifyBarId) {
            ClassifyBar classifyBar = classifyBarService.getClassifyBarById(classifyBarId);
            map.put(CLASSIFYBAR, classifyBar);
            // 判断分类导航是否有父分类
            if (null != classifyBar.getParentId() && classifyBar.getParentId() > 0) {
                ClassifyBar parent = classifyBarService.getClassifyBarById(classifyBar.getParentId());
                if (parent.getGoodsCatId() == -1) {// 自定义分类导航
                    map.put(ISDEFIND, 1);
                } else {
                    // 父分类不是一级分类导航，或者 是频道模板
                    if (parent.getGrade() > 1 || null != channelId) {
                        cateList = this.goodsCateService.queryGoosCateByParentId(parent.getGoodsCatId());
                        // 父分类是一级分类导航
                    } else {
                        List<ClassifyBarCate> list = barCateService.selectByClassifyBarId(parent.getClassifyBarId());
                        for (ClassifyBarCate barCate : list) {
                            cateList.addAll(this.goodsCateService.queryGoosCateByParentId(barCate.getCateId()));
                        }
                    }
                }
                parentId = classifyBar.getParentId();
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                    // 获取一级分类导航关联的商品分类
                    map.put("barCates", barCateService.selectByClassifyBarId(classifyBarId));
                    // 获取一级分类导航关联的快捷分类
                    map.put("barQuicks", barQuickService.selectByClassifyBarId(classifyBarId));
                }
            }
            // 添加
        } else {
            if (null != parentId) {
                ClassifyBar parent = classifyBarService.getClassifyBarById(parentId);
                if (parent.getGoodsCatId() == -1) {// 自定义分类导航
                    map.put(ISDEFIND, 1);
                } else {
                    // 父分类不是一级分类导航，或者 是频道模板
                    if (parent.getGrade() > 1 || null != channelId) {
                        cateList = this.goodsCateService.queryGoosCateByParentId(parent.getGoodsCatId());
                        // 父分类是一级分类导航
                    } else {
                        List<ClassifyBarCate> list = barCateService.selectByClassifyBarId(parent.getClassifyBarId());
                        for (ClassifyBarCate barCate : list) {
                            cateList.addAll(this.goodsCateService.queryGoosCateByParentId(barCate.getCateId()));
                        }
                    }

                }
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
        }
        map.put(CATELIST, cateList);
        map.put(TEMPID, tempId);
        map.put(CHANNELID, channelId);
        map.put(PARENTID, parentId);
        return new ModelAndView("jsp/temp/show_temp_classifyBar", "map", map);
    }

    /**
     * 新查看分类导航(ajax)
     *
     * @param classifyBarId
     * @return
     */
    @RequestMapping("/showtempclassifybarandcateandquickajax")
    @ResponseBody
    public Map<String, Object> showTempClassifyBarAndCateAndQuickAjax(Long classifyBarId, Long channelId, Long parentId) {
        Map<String, Object> map = new HashMap<String, Object>();

        /*
         * 获取商品分类
         */
        List<GoodsCate> cateList = new ArrayList<GoodsCate>();

        // 修改
        if (null != classifyBarId) {
            ClassifyBar classifyBar = classifyBarService.getClassifyBarById(classifyBarId);
            map.put(CLASSIFYBAR, classifyBar);
            // 判断分类导航是否有父分类
            if (null != classifyBar.getParentId() && classifyBar.getParentId() > 0) {
                ClassifyBar parent = classifyBarService.getClassifyBarById(classifyBar.getParentId());
                if (parent.getGoodsCatId() == -1) {// 自定义分类导航
                    map.put(ISDEFIND, 1);
                } else {
                    // 父分类不是一级分类导航，或者 是频道模板
                    if (parent.getGrade() > 1 || null != channelId) {
                        cateList = this.goodsCateService.queryGoosCateByParentId(parent.getGoodsCatId());
                        // 父分类是一级分类导航
                    } else {
                        List<ClassifyBarCate> list = barCateService.selectByClassifyBarId(parent.getClassifyBarId());
                        for (ClassifyBarCate barCate : list) {
                            cateList.addAll(this.goodsCateService.queryGoosCateByParentId(barCate.getCateId()));
                        }
                    }
                }
                parentId = classifyBar.getParentId();
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                    // 获取一级分类导航关联的商品分类
                    map.put("barCates", barCateService.selectByClassifyBarId(classifyBarId));
                    // 获取一级分类导航关联的快捷分类
                    map.put("barQuicks", barQuickService.selectByClassifyBarId(classifyBarId));
                }
            }
            // 添加
        } else {
            if (null != parentId) {
                ClassifyBar parent = classifyBarService.getClassifyBarById(parentId);
                if (parent.getGoodsCatId() == -1) {// 自定义分类导航
                    map.put(ISDEFIND, 1);
                } else {
                    // 父分类不是一级分类导航，或者 是频道模板
                    if (parent.getGrade() > 1 || null != channelId) {
                        cateList = this.goodsCateService.queryGoosCateByParentId(parent.getGoodsCatId());
                        // 父分类是一级分类导航
                    } else {
                        List<ClassifyBarCate> list = barCateService.selectByClassifyBarId(parent.getClassifyBarId());
                        for (ClassifyBarCate barCate : list) {
                            if (barCate.getCateId() != 0) {
                                cateList.addAll(this.goodsCateService.queryGoosCateByParentId(barCate.getCateId()));
                            }

                        }
                    }

                }
            } else {
                // 频道分类导航
                if (null != channelId) {
                    Channel channel = channelService.findChannelByID(channelId);
                    cateList = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
                    // 模板分类导航
                } else {
                    map.put(ISTOP, 1);
                    cateList = this.goodsCateService.queryAllFirstGradeGoosCate();
                }
            }
        }
        map.put(CATELIST, cateList);
        return map;
    }

    /**
     * 添加分类导航
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/createTempClassifyBar")
    public ModelAndView createTempClassifyBar(MultipartHttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (null == classifyBar.getChannelId()) {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        if (null == classifyBar.getParentId()) {
            classifyBar.setGrade(1);
        } else {
            classifyBar.setGrade(classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGrade() + 1);
        }
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            classifyBar.setTemp2(UploadUtil.uploadFileOne(muFile, request));
        }

        classifyBar.setCreateUserId(loginUserId);
        classifyBarService.saveClassifyBar(classifyBar);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO1, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        String view;
        if (null == classifyBar.getChannelId()) {
            view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 添加分类导航(子分类)
     *
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/createtempclassifybarsubclassi")
    @ResponseBody
    public int createTempclassifyBarSubclassi(HttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        if (null == classifyBar.getParentId()) {
            classifyBar.setGrade(1);
        } else {
            classifyBar.setGrade(classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGrade() + 1);
        }
        // 设置创建人iD
        classifyBar.setCreateUserId(loginUserId);
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, INFO1, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        // 返回结果
        // 1:成功
        // 0：失败
        return classifyBarService.saveClassifyBar(classifyBar);
    }

    /**
     * 添加分类导航
     *
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/createtempclassifybarajax")
    @ResponseBody
    public int createTempClassifyBarAjax(HttpServletRequest request,ClassifyBar classifyBar, BindingResult bindingResult, Long[] barCateIds, String[] barCateNames,
            Long[] barQuickIds, String[] barQuickNames, Long[] barCIds, String[] barCNames, Long[] barQIds, String[] barQNames, Long goodsCatId, String[] bartemps,
            String[] barQTemps) {
        // 判断是否符合规则
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取操作用户的ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置分类导航的层级
        classifyBar.setGrade(1);
        // 设置关联商品分类集合
        List<ClassifyBarCate> barCates = new ArrayList<ClassifyBarCate>();
        // 设置关联快捷分类集合
        List<ClassifyBarQuick> barQuicks = new ArrayList<ClassifyBarQuick>();

        // 判断是否是自定义分类导航
        if (goodsCatId == -1) {
            for (int i = 0; i < barCIds.length; i++) {
                if (!"".equals(barCNames[i]) && barCNames[i] != null) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCIds[i]);
                    barCate.setCateName(barCNames[i]);
                    barCate.setTemp2(bartemps[i]);
                    barCates.add(barCate);
                }
            }
            for (int i = 0; i < barQIds.length; i++) {
                if (!"".equals(barQNames[i]) && barQNames[i] != null) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQIds[i]);
                    barQuick.setCateName(barQNames[i]);
                    barQuick.setTemp2(barQTemps[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCIds[0]);
        } else {
            for (int i = 0; i < barCateIds.length; i++) {
                if (barCateIds[i] > -1) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCateIds[i]);
                    barCate.setCateName(barCateNames[i]);
                    barCates.add(barCate);
                }

            }
            if(barQuickIds!=null) {
                // 保存快捷分类id
                for (int i = 0; i < barQuickIds.length; i++) {
                    if (barQuickIds[i] > 0) {
                        // 创建快捷分类列表
                        ClassifyBarQuick barQuick = new ClassifyBarQuick();
                        barQuick.setCateId(barQuickIds[i]);
                        barQuick.setCateName(barQuickNames[i]);
                        barQuicks.add(barQuick);
                    }
                }
            }

            classifyBar.setGoodsCatId(barCateIds[0]);
        }
        // 设置用户id
        classifyBar.setCreateUserId(loginUserId);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO1, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        // 保存分类导航和关联商品分类、关联快捷分类
        return classifyBarService.saveClassifyBarAndCateAndQuick(classifyBar, barCates, barQuicks);
    }

    /**
     * 新添加一级分类导航并同时添加关联商品分类和快捷分类
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/createTempClassifyBarAndCateAndQuick")
    public ModelAndView createTempClassifyBarAndCateAndQuick(MultipartHttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult, Long[] barCateIds,
            String[] barCateNames, Long[] barQuickIds, String[] barQuickNames, Long[] barCIds, String[] barCNames, Long[] barQIds, String[] barQNames, Long goodsCatId,
            String[] bartemps, String[] barQTemps) {
        if (bindingResult.hasErrors()) {
            if (null == classifyBar.getChannelId()) {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        // 获取操作用户的ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置分类导航的层级
        classifyBar.setGrade(1);
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            classifyBar.setTemp2(UploadUtil.uploadFileOne(muFile, request));
        }
        // 设置关联商品分类集合
        List<ClassifyBarCate> barCates = new ArrayList<ClassifyBarCate>();
        // 设置关联快捷分类集合
        List<ClassifyBarQuick> barQuicks = new ArrayList<ClassifyBarQuick>();

        // 判断是否是自定义分类导航
        if (goodsCatId == -1) {
            for (int i = 0; i < barCIds.length; i++) {
                if (!"".equals(barCNames[i]) && barCNames[i] != null) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCIds[i]);
                    barCate.setCateName(barCNames[i]);
                    barCate.setTemp2(bartemps[i]);
                    barCates.add(barCate);
                }
            }
            for (int i = 0; i < barQIds.length; i++) {
                if (!"".equals(barQNames[i]) && barQNames[i] != null) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQIds[i]);
                    barQuick.setCateName(barQNames[i]);
                    barQuick.setTemp2(barQTemps[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCIds[0]);
        } else {
            for (int i = 0; i < barCateIds.length; i++) {
                if (barCateIds[i] > -1) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCateIds[i]);
                    barCate.setCateName(barCateNames[i]);
                    barCates.add(barCate);
                }

            }
            for (int i = 0; i < barQuickIds.length; i++) {
                if (barQuickIds[i] > 0) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQuickIds[i]);
                    barQuick.setCateName(barQuickNames[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCateIds[0]);
        }
        classifyBar.setCreateUserId(loginUserId);
        // 保存分类导航和关联商品分类、关联快捷分类
        classifyBarService.saveClassifyBarAndCateAndQuick(classifyBar, barCates, barQuicks);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO1, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        String view;
        if (null == classifyBar.getChannelId()) {
            view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 修改分类导航
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/updateTempClassifyBar")
    public ModelAndView updateTempClassifyBar(MultipartHttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            if (null == classifyBar.getChannelId()) {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        if (null == classifyBar.getParentId()) {
            classifyBar.setGrade(1);
        } else {
            classifyBar.setGrade(classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGrade() + 1);
        }
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            classifyBar.setTemp2(UploadUtil.uploadFileOne(muFile, request));
        }
        classifyBar.setUpdateUserId(loginUserId);
        classifyBarService.updateClassifyBar(classifyBar);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO3, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        String view;
        if (null == classifyBar.getChannelId()) {
            view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 修改分类导航
     *
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/updatetempclassifybarajax")
    @ResponseBody
    public int updateTempClassifyBarAjax(HttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        if (null == classifyBar.getParentId()) {
            classifyBar.setGrade(1);
        } else {
            classifyBar.setGrade(classifyBarService.getClassifyBarById(classifyBar.getParentId()).getGrade() + 1);
        }
        classifyBar.setUpdateUserId(loginUserId);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO3, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        // String view;
        // if (null == classifyBar.getChannelId()) {
        // view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId()
        // + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        // } else {
        // view = JUMPFORCLASSIFYBARVIEW2 +
        // classifyBar.getChannelId() + ConstantUtil.CSRF +
        // request.getParameter(ConstantUtil.CSRFTOKEN);
        // }
        return classifyBarService.updateClassifyBar(classifyBar);
    }

    /**
     * 新修改一级分类导航并同时添加关联商品分类和快捷分类
     * 
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/updateTempClassifyBarAndCateAndQuick")
    public ModelAndView updateTempClassifyBarAndCateAndQuick(MultipartHttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult, Long[] barCateIds,
            String[] barCateNames, Long[] barQuickIds, String[] barQuickNames, Long[] barCIds, String[] barCNames, Long[] barQIds, String[] barQNames, Long goodsCatId,
            String[] bartemps, String[] barQTemps) {
        if (bindingResult.hasErrors()) {
            if (null == classifyBar.getChannelId()) {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                return new ModelAndView(new RedirectView(JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
            }
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 上传图片并保存地址
        MultipartFile muFile = request.getFile(IMGSRC);
        if (null != muFile && muFile.getSize() > 0) {
            classifyBar.setTemp2(UploadUtil.uploadFileOne(muFile, request));
        }
        // 设置关联商品分类集合
        List<ClassifyBarCate> barCates = new ArrayList<ClassifyBarCate>();
        // 设置关联快捷分类集合
        List<ClassifyBarQuick> barQuicks = new ArrayList<ClassifyBarQuick>();
        // 判断是否是自定义分类导航
        if (goodsCatId == -1) {
            for (int i = 0; i < barCIds.length; i++) {
                if (!"".equals(barCNames[i]) && barCNames[i] != null) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCIds[i]);
                    barCate.setCateName(barCNames[i]);
                    barCate.setTemp2(bartemps[i]);
                    barCates.add(barCate);
                }
            }
            for (int i = 0; i < barQIds.length; i++) {
                if (barQNames[i] != null && !"".equals(barQNames[i])) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQIds[i]);
                    barQuick.setCateName(barQNames[i]);
                    barQuick.setTemp2(barQTemps[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCIds[0]);
        } else {
            for (int i = 0; i < barCateIds.length; i++) {
                if (barCateIds[i] > -1) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCateIds[i]);
                    barCate.setCateName(barCateNames[i]);
                    barCates.add(barCate);
                }

            }
            for (int i = 0; i < barQuickIds.length; i++) {
                if (barQuickIds[i] > 0) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQuickIds[i]);
                    barQuick.setCateName(barQuickNames[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCateIds[0]);
        }
        classifyBar.setUpdateUserId(loginUserId);
        classifyBarService.updateClassifyBarAndCateAndQuick(classifyBar, barCates, barQuicks);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO3, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        String view;
        if (null == classifyBar.getChannelId()) {
            view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        } else {
            view = JUMPFORCLASSIFYBARVIEW2 + classifyBar.getChannelId() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        }
        return new ModelAndView(new RedirectView(view));
    }

    /**
     * 新修改一级分类导航并同时添加关联商品分类和快捷分类Ajax
     *
     * @param request
     * @param classifyBar
     * @return
     */
    @RequestMapping("/updatetempclassifybarandcateandquickajax")
    @ResponseBody
    public int updateTempClassifyBarAndCateAndQuickAjax(HttpServletRequest request, @Valid ClassifyBar classifyBar, BindingResult bindingResult, Long[] barCateIds,
            String[] barCateNames, Long[] barQuickIds, String[] barQuickNames, Long[] barCIds, String[] barCNames, Long[] barQIds, String[] barQNames, Long goodsCatId,
            String[] bartemps, String[] barQTemps) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }

        // 设置关联商品分类集合
        List<ClassifyBarCate> barCates = new ArrayList<ClassifyBarCate>();
        // 设置关联快捷分类集合
        List<ClassifyBarQuick> barQuicks = new ArrayList<ClassifyBarQuick>();
        // 判断是否是自定义分类导航
        if (goodsCatId == -1) {
            for (int i = 0; i < barCIds.length; i++) {
                if (!"".equals(barCNames[i]) && barCNames[i] != null) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCIds[i]);
                    barCate.setCateName(barCNames[i]);
                    barCate.setTemp2(bartemps[i]);
                    barCates.add(barCate);
                }
            }
            for (int i = 0; i < barQIds.length; i++) {
                if (barQNames[i] != null && !"".equals(barQNames[i])) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQIds[i]);
                    barQuick.setCateName(barQNames[i]);
                    barQuick.setTemp2(barQTemps[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCIds[0]);
        } else {
            for (int i = 0; i < barCateIds.length; i++) {
                if (barCateIds[i] > -1) {
                    ClassifyBarCate barCate = new ClassifyBarCate();
                    barCate.setCateId(barCateIds[i]);
                    barCate.setCateName(barCateNames[i]);
                    barCates.add(barCate);
                }

            }
            for (int i = 0; i < barQuickIds.length; i++) {
                if (barQuickIds[i] > 0) {
                    ClassifyBarQuick barQuick = new ClassifyBarQuick();
                    barQuick.setCateId(barQuickIds[i]);
                    barQuick.setCateName(barQuickNames[i]);
                    barQuicks.add(barQuick);
                }
            }

            classifyBar.setGoodsCatId(barCateIds[0]);
        }
        classifyBar.setUpdateUserId(loginUserId);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, INFO3, request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        // String view = "";
        // if (null == classifyBar.getChannelId()) {
        // view = JUMPFORCLASSIFYBARVIEW1 + classifyBar.getTempId()
        // + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN);
        // } else {
        // view = JUMPFORCLASSIFYBARVIEW2 +
        // classifyBar.getChannelId() + ConstantUtil.CSRF +
        // request.getParameter(ConstantUtil.CSRFTOKEN);
        // }
        return classifyBarService.updateClassifyBarAndCateAndQuick(classifyBar, barCates, barQuicks);
    }

    /**
     * 删除分页导航
     * 
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping("/deleteTempClassifyBar")
    public void deleteTempClassifyBar(Long classifyBarId, HttpServletRequest request, HttpServletResponse response) {
        classifyBarService.deleteClassifyBar(classifyBarId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除分类导航", request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
    }

    /**
     * 新删除一级分页导航
     * 
     * @param classifyBarId
     */
    @ResponseBody
    @RequestMapping("/deleteClassifyBarAndCateAndQuick")
    public void deleteClassifyBarAndCateAndQuick(Long classifyBarId, HttpServletRequest request) {
        try {
            classifyBarService.deleteByPrimaryKeyAndPro(classifyBarId);
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "删除分类导航", request.getSession().getAttribute(OPERAPATH) + INFO2 + customerName);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ClassifyBarService getClassifyBarService() {
        return classifyBarService;
    }

    @Resource(name = "ClassifyBarService")
    public void setClassifyBarService(ClassifyBarService classifyBarService) {
        this.classifyBarService = classifyBarService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

}
