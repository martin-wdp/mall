/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ningpai.system.vo.HelpCenterVo;
import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.dao.HelpCenterMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 帮助中心接口实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 下午2:25:16
 * @version 1.0
 */
@Repository("helpCenterMapper")
public class HelpCenterMapperImpl extends BasicSqlSupport implements
        HelpCenterMapper {

    /**
     * 根据帮助分类名称获取分类ID helpcatename :帮助分类名称
     * 
     * @return
     */
    @Override
    public List<HelpCenterVo> selectCateByHelpname(String helpcatename) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("helpcatename", helpcatename);
        return this.selectList(
                "com.ningpai.system.dao.HelpCenterMapper.selectCateByHelpname",
                map);
    }

    /**
     * 删除帮助中心
     * 
     * @param helpId
     * @return int
     */
    public int deleteByPrimaryKey(Long helpId) {

        return this.delete(
                "com.ningpai.system.dao.HelpCenterMapper.deleteByPrimaryKey",
                helpId);
    }

    /**
     * 添加帮助中心
     */
    public int insert(HelpCenter record) {
        return 0;
    }

    /**
     * 添加帮助中心
     */
    public int insertSelective(HelpCenter record) {

        return this.insert(
                "com.ningpai.system.dao.HelpCenterMapper.insertSelective",
                record);
    }

    /**
     * 根据id查询单条帮助中心信息
     * 
     * @param helpId
     * @return
     */
    public HelpCenter selectByPrimaryKey(Long helpId) {
        // 查询数据库信息
        return this.selectOne(
                "com.ningpai.system.dao.HelpCenterMapper.selectByPrimaryKey",
                helpId);
    }

    /**
     * 修改帮助中心信息
     * 
     * @param helpId
     * @return
     */
    public int updateByPrimaryKeySelective(HelpCenter record) {
        return this
                .update("com.ningpai.system.dao.HelpCenterMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改帮助中心信息
     */
    public int updateByPrimaryKeyWithBLOBs(HelpCenter record) {
        return 0;
    }

    /**
     * 修改帮助中心信息
     */
    public int updateByPrimaryKey(HelpCenter record) {
        return 0;
    }

    /**
     * 分页查询分类中心信息
     * 
     * @param cateid
     * @return List
     */
    public List<Object> findByPageBean(Map<String, Object> maps) {
        return this.selectList(
                "com.ningpai.system.dao.HelpCenterMapper.findByPageBean", maps);
    }

    /**
     * 根据cateid查询帮助中心总行数
     * 
     * @param cateid
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.HelpCenterMapper.findTotalCount",
                selectBean);
    }

    /**
     * 根据cateid查询帮助中心总行数
     * 
     * @param cateid
     * @return int
     */
    public int findTotalCount(Map<String, Object> maps) {

        return this.selectOne(
                "com.ningpai.system.dao.HelpCenterMapper.findTotalCount", maps);
    }

    /**
     * 根据分类ID删除帮助中心数据
     * 
     * @param helpCateId
     * @return int
     */
    public int deleteByHelpCateId(Long helpCateId) {
        // 根据分类删除ID
        return this.update(
                "com.ningpai.system.dao.HelpCenterMapper.deleteByHelpCateId",
                helpCateId);
    }

    /**
     * 获取所有是底部的帮助
     * 
     * @see com.ningpai.system.dao.HelpCenterMapper#selectAllIsFoot()
     */
    public List<HelpCenter> selectAllIsFoot() {
        return this
                .selectList("com.ningpai.system.dao.HelpCenterMapper.selectAllIsFoot");
    }

    /**
     * 根据帮助类型获取帮助
     * 
     * @see com.ningpai.system.dao.HelpCenterMapper#findByCateId(java.lang.Long)
     */
    public List<HelpCenter> findByCateId(Long helpCateId) {
        return this.selectList(
                "com.ningpai.system.dao.HelpCenterMapper.findByCateId",
                helpCateId);
    }

    /**
     * 获取所有的帮助
     * 
     * @see com.ningpai.system.dao.HelpCenterMapper#selectAll()
     */
    @Override
    public List<HelpCenter> selectAll() {
        return this
                .selectList("com.ningpai.system.dao.HelpCenterMapper.selectAll");
    }
}
