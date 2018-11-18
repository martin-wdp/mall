/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.GoodsCateSystem;
import com.ningpai.system.bean.Storey;
import com.ningpai.system.dao.StoreyMapper;
import com.ningpai.system.service.StoreyService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 楼层服务层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月4日 下午6:36:24
 * @version 1.0
 */
@Service("storeyService")
public class StoreyServiceImpl implements StoreyService {
    /** spring注解 */
    @Resource(name = "storeyMapper")
    private StoreyMapper storeyMapper;

    /**
     * 分页查询楼层信息
     * 
     * @param pageBean
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 分页查找楼层信息
        pageBean.setRows(storeyMapper.findTotalCount(selectBean));
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("startRowNum", pageBean.getStartRowNum());
        maps.put("endRowNum", pageBean.getEndRowNum());
        maps.put("condition", selectBean.getCondition());
        maps.put("searchText", selectBean.getSearchText());
        // 设置分页列表
        pageBean.setList(storeyMapper.findByPageBean(maps));
        return pageBean;
    }

    /**
     * 查询所有商品父分类
     * 
     * @return List
     */
    public List<GoodsCateSystem> findParentGoodsCate() {
        // 查询商品父分类
        return storeyMapper.findParentGoodsCate();
    }

    /**
     * 添加一条楼层信息
     * 
     * @param storey
     * @return int
     */
    public int insertStorey(Storey storey) {
        // 添加楼层信息
        storey.setDelFlag("0");

        return storeyMapper.insertSelective(storey);
    }

    /**
     * 根据楼层层数查询楼层信息
     * 
     * @param floorId
     * @return
     */
    public Storey findStoreyByFloorId(Long floorId) {
        return storeyMapper.findStoreyByFloorId(floorId);
    }

    /**
     * 删除楼层信息
     * 
     * @param parameterValues
     * @return
     */
    public int deleteStorey(String[] seIds) {
        int count = 0;
        // 删除楼层信息
        if (seIds != null) {

            // 循环删除
            for (String id : seIds) {

                count += storeyMapper.deleteByPrimaryKey(Long.parseLong(id));
            }
        }

        return count;
    }

    /**
     * 根据ID查询楼层信息
     * 
     * @param seId
     * @return Storey
     */
    public Storey findStoreyById(Long seId) {
        // 查询楼层信息
        return storeyMapper.selectByPrimaryKey(seId);
    }

    /**
     * 修改楼层信息
     * 
     * @param storey
     * @return int
     */
    public int updateStorey(Storey storey) {
        // 修改楼层信息
        storey.setModifyTime(new Date());
        return storeyMapper.updateByPrimaryKeySelective(storey);
    }

}
