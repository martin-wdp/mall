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

import com.ningpai.system.vo.HelpCenterVo;
import org.springframework.stereotype.Service;

import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.dao.HelpCenterMapper;
import com.ningpai.system.service.HelpCenterService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 帮助分类server实现类
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 下午2:28:09
 * @version 1.0
 */
@Service("helpCenterService")
public class HelpCenterServiceImpl implements HelpCenterService {
    /** spring注解 */
    @Resource(name = "helpCenterMapper")
    private HelpCenterMapper helpCenterMapper;

    /**
     * 根据帮助分类名称获取分类ID helpcatename :帮助分类名称
     * 
     * @return
     */
    @Override
    public List<HelpCenterVo> selectCateByHelpname(String helpcatename) {
        return helpCenterMapper.selectCateByHelpname(helpcatename);
    }

    /**
     * 添加帮助中心
     * 
     * @param helpCenter
     * @return int
     */
    public int insertHelpCenter(HelpCenter helpCenter) {
        // 删除标记设置为‘未删除’
        helpCenter.setDelFlag("0");
        return helpCenterMapper.insertSelective(helpCenter);
    }

    /**
     * 修改帮助中心信息
     * 
     * @param helpCenter
     * @return int
     */
    public int updateHelpCenter(HelpCenter helpCenter) {
        // 设置修改时间
        helpCenter.setModifyTime(new Date());
        return helpCenterMapper.updateByPrimaryKeySelective(helpCenter);
    }

    /**
     * 分页查询帮助中心信息
     * 
     * @param cateid
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean) {
        // 设置总行数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        pageBean.setRows(helpCenterMapper.findTotalCount(map));
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        // 设置列表
        pageBean.setList(helpCenterMapper.findByPageBean(map));

        return pageBean;
    }

    /**
     * 分页查询帮助中心信息
     * 
     * @param cateid
     * @return PageBean
     */
    public PageBean findByPageBean(PageBean pageBean, SelectBean selectBean,
            Long helpcateId) {
        // 设置总行数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        pageBean.setRows(helpCenterMapper.findTotalCount(map));
        map.put("startRowNum", pageBean.getStartRowNum());
        map.put("endRowNum", pageBean.getEndRowNum());
        map.put("helpcateId", helpcateId);
        // 设置列表
        pageBean.setList(helpCenterMapper.findByPageBean(map));

        return pageBean;
    }

    /**
     * 根据helpid查询帮助中心信息
     * 
     * @param helpId
     * @return HelpCenter
     */
    public HelpCenter findByHelpId(Long helpId) {
        // 根据id查询帮助中心信息
        return helpCenterMapper.selectByPrimaryKey(helpId);
    }

    /**
     * 删除帮助中心信息
     * 
     * @param parameterValues
     * @return int
     */
    public int deleteHelpCenter(String[] helpIds) {

        int count = 0;
        // 判断参数是否为空
        if (helpIds != null) {
            // 循环删除
            for (String id : helpIds) {
                count += helpCenterMapper
                        .deleteByPrimaryKey(Long.parseLong(id));
            }
        }
        return count;
    }

    /**
     * 根据帮助类型获取帮助
     * 
     * @see
     * com.ningpai.system.service.HelpCenterService#findByCateId(java.lang.Long)
     */
    public List<HelpCenter> findByCateId(Long helpCateId) {
        return helpCenterMapper.findByCateId(helpCateId);
    }

    /**
     * 获取所有是底部的帮助
     * 
     * @see com.ningpai.system.service.HelpCenterService#selectAllIsFoot()
     */
    public List<HelpCenter> selectAllIsFoot() {
        return helpCenterMapper.selectAllIsFoot();
    }

    /**
     * 获取所有的帮助
     * 
     * @see com.ningpai.system.service.HelpCenterService#selectAll()
     */
    @Override
    public List<HelpCenter> selectAll() {
        return helpCenterMapper.selectAll();
    }
}
