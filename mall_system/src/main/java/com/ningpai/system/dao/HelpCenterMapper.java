/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.HelpCenterVo;

/**
 * 帮助中心接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 下午2:24:41
 * @version 1.0
 */
public interface HelpCenterMapper {

    /**
     * 根据帮助分类名称获取分类ID helpcatename :帮助分类名称
     * 
     * @return
     */
    List<HelpCenterVo> selectCateByHelpname(String helpcatename);

    /**
     * 删除帮助中心
     * 
     * @param helpId
     * @return int
     */
    int deleteByPrimaryKey(Long helpId);

    /**
     * 添加帮助中心
     * 
     * @param record
     * @return int
     */
    int insert(HelpCenter record);

    /**
     * 添加帮助中心
     * 
     * @param record
     * @return int
     */
    int insertSelective(HelpCenter record);

    /**
     * 根据id查询单条帮助中心信息
     * 
     * @param helpId
     * @return
     */
    HelpCenter selectByPrimaryKey(Long helpId);

    /**
     * 修改帮助中心
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(HelpCenter record);

    /**
     * 修改帮助中心
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(HelpCenter record);

    /**
     * 修改帮助中心
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(HelpCenter record);

    /**
     * 分页查询分类中心信息
     * 
     * @param cateid
     * @return List
     */
    List<Object> findByPageBean(Map<String, Object> maps);

    /**
     * 根据cateid查询帮助中心总行数
     * 
     * @param cateid
     * @return int
     */
    int findTotalCount(SelectBean selectBean);

    /**
     * 根据cateid查询帮助中心总行数
     * 
     * @param cateid
     * @return int
     */
    int findTotalCount(Map<String, Object> maps);

    /**
     * 根据分类ID删除帮助中心数据
     * 
     * @param helpCateId
     * @return int
     */
    int deleteByHelpCateId(Long helpCateId);

    /**
     * 获取所有是底部的帮助
     * 
     * @return
     */
    List<HelpCenter> selectAllIsFoot();

    /**
     * 获取所有的帮助
     * 
     * @return
     */
    List<HelpCenter> selectAll();

    /**
     * 根据帮助类型获取帮助
     * 
     * @param helpCateId
     * @return
     */
    List<HelpCenter> findByCateId(Long helpCateId);
}
