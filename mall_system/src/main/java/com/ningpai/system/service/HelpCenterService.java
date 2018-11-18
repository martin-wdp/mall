/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.HelpCenter;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.HelpCenterVo;
import com.ningpai.util.PageBean;

/**
 * 帮助中心服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 下午2:27:14
 * @version 1.0
 */
public interface HelpCenterService {
    /**
     * 根据帮助分类名称获取分类ID
     * helpcatename :帮助分类名称
     * @return
     */
    List<HelpCenterVo> selectCateByHelpname(String helpcatename);

    /**
     * 添加帮助中心
     * 
     * @param helpCenter
     * @return int
     */
    int insertHelpCenter(HelpCenter helpCenter);

    /**
     * 修改帮助中心信息
     * 
     * @param helpCenter
     * @return int
     */
    int updateHelpCenter(HelpCenter helpCenter);

    /**
     * 分页查询帮助中心信息
     * 
     * @param cateid
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 分页查询帮助中心信息
     * 
     * @param cateid
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean, Long helpcateId);

    /**
     * 根据helpid查询帮助中心信息
     * 
     * @param helpId
     * @return HelpCenter
     */
    HelpCenter findByHelpId(Long helpId);

    /**
     * 删除帮助中心信息
     * 
     * @param parameterValues
     * @return int
     */
    int deleteHelpCenter(String[] helpIds);

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
