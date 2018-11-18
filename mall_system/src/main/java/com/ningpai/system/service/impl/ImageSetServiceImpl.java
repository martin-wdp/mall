/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.ImageSet;
import com.ningpai.system.dao.ImageSetMapper;
import com.ningpai.system.service.ImageSetService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 图片设置服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月17日 上午10:02:07
 * @version 1.0
 */
@Service("imageSetService")
public class ImageSetServiceImpl implements ImageSetService {
    /** spring注解 */
    @Resource(name = "imageSetMapper")
    private ImageSetMapper imageSetMapper;

    /**
     * 添加图片设置信息
     * 
     * @param imageSet
     * @return int
     */
    public int insertImageSet(ImageSet imageSet) {
        imageSet.setCreateTime(new Date());
        imageSet.setDelFlag("0");
        return imageSetMapper.insertSelective(imageSet);
    }

    /**
     * 删除图片设置信息
     * 
     * @param ruleId
     * @return int
     */
    public int deleteImageSet(String[] ruleIds) {
        int count = 0;
        // 判断是否为空
        if (ruleIds != null) {
            // 循环删除
            for (String id : ruleIds) {
                count += imageSetMapper.deleteByPrimaryKey(Long.parseLong(id));
            }
        }
        return count;
    }

    /**
     * 修改图片设置信息
     * 
     * @param imageSet
     * @return
     */
    public int updateImageSet(ImageSet imageSet) {
        imageSet.setModifyTime(new Date());
        return imageSetMapper.updateByPrimaryKeySelective(imageSet);
    }

    /**
     * 分页查询图片规则
     * 
     * @param currentPage
     * @param pageSize
     * @return List<ImageSet>
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 查询总行数
        pageBean.setRows(imageSetMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置列表
        pageBean.setList(imageSetMapper.findByPageBean(map));

        map = null;

        return pageBean;
    }

    /**
     * 按照ID查询数据
     * 
     * @param ruleId
     * @return
     */
    public ImageSet findByRuleId(Long ruleId) {
        return imageSetMapper.selectByPrimaryKey(ruleId);
    }
}
