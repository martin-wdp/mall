/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 头像上传类
 * 
 * @author jiping
 * @since 2014年7月22日 下午1:53:45
 * @version 1.0
 */
@Repository("uploadImgMapper")
public class UploadImgMapper extends BasicSqlSupport {
    /**
     * 修改图片
     *
     * @param paramMap
     * */
    public int updateImg(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.customer.dao.CustomerMapper.updateImgByPrimaryKeySelective",
                        paramMap);
    }
}
