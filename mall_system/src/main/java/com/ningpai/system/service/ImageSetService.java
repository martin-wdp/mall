/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 图片设置服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午9:56:53
 * @version 1.0
 */
public interface ImageSetService {

    /**
     * 分页查询图片规则
     * 
     * @param currentPage
     * @param pageSize
     * @return List<ImageSet>
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加图片设置信息
     * 
     * @param imageSet
     * @return int
     */
    int insertImageSet(ImageSet imageSet);

    /**
     * 删除图片设置信息
     * 
     * @param ruleId
     * @return int
     */
    int deleteImageSet(String[] ruIds);

    /**
     * 修改图片设置信息
     * 
     * @param imageSet
     * @return
     */
    int updateImageSet(ImageSet imageSet);

    /**
     * 按照ID查询数据
     * 
     * @param ruleId
     * @return
     */
    ImageSet findByRuleId(Long ruleId);
}
