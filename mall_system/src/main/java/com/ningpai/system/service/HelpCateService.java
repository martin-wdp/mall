/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.HelpCate;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 帮助分类服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月20日 上午11:09:42
 * @version 1.0
 */
public interface HelpCateService {
    /**
     * 分页查询帮助中心分类
     * 
     * @param pb
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 添加帮助分类
     * 
     * @param helpCate
     * @return int
     */
    int insertHelpCate(HelpCate helpCate);

    /**
     * 删除帮助分类
     * 
     * @param helpcateId
     * @return
     */
    int deleteHelpCate(String[] helpcateId);

    /**
     * 根据Id查询帮助分类
     * 
     * @param helpcateId
     * @return HelpCate
     */
    HelpCate findByHelpcateId(Long helpcateId);

    /**
     * 修改帮助分类
     * 
     * @param helpCate
     * @return int
     */
    int updateHelpCate(HelpCate helpCate);

    /**
     * 查询所有帮助分类
     * 
     * @return List
     */
    List<HelpCate> findAll();
}
