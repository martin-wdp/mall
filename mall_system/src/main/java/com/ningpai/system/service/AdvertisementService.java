/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.Advertisement;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 首页广告设置服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午7:39:12
 * @version 1.0
 */
public interface AdvertisementService {

    /**
     * 广告分页查询
     * 
     * @param pageBean
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加首页广告图片
     * 
     * @param advertisement
     * @return int
     */
    int insertAdver(Advertisement advertisement);

    /**
     * 删除广告图片信息
     * 
     * @param adverIds
     * @return int
     */
    int deleteAdver(String[] adverIds);

    /**
     * 查询单条首页广告信息
     * 
     * @param adverId
     * @return Advertisement
     */
    Advertisement findByAdverId(Long adverId);

    /**
     * 修改首页广告信息
     * 
     * @param advertisement
     * @return int
     */
    int updateAdver(Advertisement advertisement);
}
