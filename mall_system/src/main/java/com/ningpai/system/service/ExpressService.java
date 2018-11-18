/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.Express;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 配送方式服务层接口
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月18日 下午4:21:21
 * @version 1.0
 */
public interface ExpressService {

    /**
     * 分页查询配送方式信息
     * 
     * @param pageBean
     * @return PageBean
     */
    PageBean findByPageBean(PageBean pageBean, SelectBean selectBean);

    /**
     * 添加配送
     * 
     * @param express
     * @return int
     */
    int insertExpress(Express express);

    /**
     * 删除配送信息
     * 
     * @param expIds
     * @return int
     */
    int deleteExpress(String[] expIds);

    /**
     * 按expid查询配送信息
     * 
     * @param expid
     * @return Express
     */
    Express findByExpid(Long expid);

    /**
     * 修改配送信息
     * 
     * @param express
     * @return int
     */
    int updateExpress(Express express);

}
