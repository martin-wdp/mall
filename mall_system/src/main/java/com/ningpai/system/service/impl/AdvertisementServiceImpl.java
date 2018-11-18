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

import com.ningpai.system.bean.Advertisement;
import com.ningpai.system.dao.AdvertisementMapper;
import com.ningpai.system.service.AdvertisementService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 广告设置服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午7:42:29
 * @version 1.0
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {
    /** spring注解 */
    @Resource(name = "advertisementMapper")
    private AdvertisementMapper advertisementMapper;

    /**
     * 添加首页广告图片
     * 
     * @param advertisement
     * @return int
     */
    public int insertAdver(Advertisement advertisement) {

        advertisement.setDelFlag("0");

        return advertisementMapper.insertSelective(advertisement);
    }

    /**
     * 删除广告图片信息
     * 
     * @param adverIds
     * @return int
     */
    public int deleteAdver(String[] adverIds) {
        int count = 0;
        // 删除链接信息
        for (String id : adverIds) {
            count += advertisementMapper.deleteByPrimaryKey(Long.parseLong(id));
        }
        return count;
    }

    /**
     * 查询单条首页广告信息
     * 
     * @param adverId
     * @return Advertisement
     */
    public Advertisement findByAdverId(Long adverId) {

        return advertisementMapper.selectByPrimaryKey(adverId);
    }

    /**
     * 修改首页广告信息
     * 
     * @param advertisement
     * @return int
     */
    public int updateAdver(Advertisement advertisement) {

        advertisement.setModifyTime(new Date());
        return advertisementMapper.updateByPrimaryKeySelective(advertisement);
    }

    /**
     * 广告分页查询
     * 
     * @param pageBean
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {

        // 设置总行数
        pageBean.setRows(advertisementMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置显示列表
        pageBean.setList(advertisementMapper.findByPageBean(map));

        return pageBean;
    }

}
