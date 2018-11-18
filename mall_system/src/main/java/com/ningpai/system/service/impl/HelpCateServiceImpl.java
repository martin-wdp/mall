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

import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.dao.HelpCateMapper;
import com.ningpai.system.dao.HelpCenterMapper;
import com.ningpai.system.service.HelpCateService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 帮助分类服务层实现
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:10:21
 * @version 1.0
 */
@Service("helpCateService")
public class HelpCateServiceImpl implements HelpCateService {
    /** 帮助分类服务注解 */
    @Resource(name = "helpCateMapper")
    private HelpCateMapper helpCateMapper;
    /** spring注解 */
    @Resource(name = "helpCenterMapper")
    private HelpCenterMapper helpCenterMapper;

    /**
     * 分页查询帮助中心分类
     * 
     * @param pb
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pb, SelectBean selectBean) {
        // 查询总行数
        pb.setRows(helpCateMapper.findTotalCount(selectBean));
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        // 设置列表信息
        pb.setList(helpCateMapper.findByPageBean(map));
        return pb;
    }

    /**
     * 添加帮助分类
     * 
     * @param helpCate
     * @return int
     */
    public int insertHelpCate(HelpCate helpCate) {
        // 插入帮助分类数据并返回结果
        helpCate.setDelFlag("0");
        return helpCateMapper.insertSelective(helpCate);
    }

    /**
     * 删除帮助分类
     * 
     * @param helpcateId
     * @return
     */
    public int deleteHelpCate(String[] helpcateId) {
        // 删除帮助分类
        int count = 0;
        if (helpcateId != null) {
            // 根据分类ID删除分类
            for (String id : helpcateId) {
                // 删除帮助中心中的数据
                count += helpCenterMapper
                        .deleteByHelpCateId(Long.parseLong(id));
                // 删除帮助分类数据
                count += helpCateMapper.deleteByPrimaryKey(Long.parseLong(id));

            }
        }
        return count;
    }

    /**
     * 根据Id查询帮助分类
     * 
     * @param helpcateId
     * @return HelpCate
     */
    public HelpCate findByHelpcateId(Long helpcateId) {
        // 调用数据层查询
        return helpCateMapper.selectByPrimaryKey(helpcateId);
    }

    /**
     * 修改帮助分类
     * 
     * @param helpCate
     * @return int
     */
    public int updateHelpCate(HelpCate helpCate) {
        // 修改帮助分类
        helpCate.setModifyTime(new Date());
        return helpCateMapper.updateByPrimaryKeySelective(helpCate);

    }

    /**
     * 查询所有帮助分类
     * 
     * @return List
     */
    public List<HelpCate> findAll() {
        // 查询所有帮助分类
        return helpCateMapper.findAll();
    }

}
