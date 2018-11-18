/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.publicpackage;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.information.service.InformationService;
import com.ningpai.information.vo.InformationVo;

/**
 * 资讯文章接口
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月13日下午4:28:50
 */
@Service("InformationPublic")
public final class InformationPublic {

    /** 第三方最新通知的栏目ID */
    public static final Long LATEST_NOTICE = 63L;
    /** 第三方新功能的栏目ID */
    public static final Long NEW_FUNCTION = 65L;
    /** 第三方帮助中心的栏目ID */
    public static final Long HELP_CENTER = 67L;

    /** 资讯service */
    @Resource(name = "InformationService")
    private InformationService inforService;

    /**
     * 根据栏目ID获取文章列表
     * 
     * @param infoTypeId
     * @return
     */
    public List<InformationVo> getInforListByInfoTypeId(Long infoTypeId) {
        /** 根据栏目ID查询文章列表 */
        return this.inforService.selectByInfoType(infoTypeId);
    }

    /**
     * 根据栏目ID和获取条数获取文章列表
     * 
     * @param infoTypeId
     *            栏目ID
     * @param top
     *            获取的条数
     * @return
     */
    public List<InformationVo> getInforListByTypeIdAndTop(Long infoTypeId, int top) {
        /** 创建对象rlist */
        List<InformationVo> rlist = new ArrayList<InformationVo>();
        /** 根据栏目ID获取文章列表 */
        List<InformationVo> list = this.getInforListByInfoTypeId(infoTypeId);
        /** 遍历文章列表放到集合rlist中 */
        for (int i = 0; i < top && i < list.size(); i++) {
            rlist.add(list.get(i));
        }
        /** 返回rlist */
        return rlist;
    }

}
