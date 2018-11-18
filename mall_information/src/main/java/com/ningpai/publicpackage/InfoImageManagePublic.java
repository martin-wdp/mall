/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.publicpackage;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.service.InfoImageManageService;

/**
 * 资讯图片管理接口
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月13日下午4:28:50
 */
@Service("InfoImageManagePublic")
public final class InfoImageManagePublic {

    /** 资源图片信息service */
    @Resource(name = "InfoImageManageService")
    private InfoImageManageService infoImageManageService;

    /**
     * 根据图片路径保存图片信息
     * 
     * @param imageSrc
     *            图片保存路径
     */
    public void saveImage(String imageSrc) {
        /** new一个InfoImageManage实体类 */
        InfoImageManage infoImageManage = new InfoImageManage();
        /** 设置图片链接为传入参数 */
        infoImageManage.setImageManageUrl(imageSrc);
        /** 设置时间为当前时间 */
        infoImageManage.setImageOnlineDate(new Date());
        /** 执行保存方法 */
        infoImageManageService.saveInfoImageManage(infoImageManage);
    }

}
