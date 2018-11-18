/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.service.impl;

import com.ningpai.app.bean.AppMarketKey;
import com.ningpai.app.dao.AppMarketKeyMapper;
import com.ningpai.app.service.AppMarketKeyService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**  
 * @Description: np_app_market_key的service的实现类:
 * @author Ningpai-HEHU
 * @date 2015-07-17 10:28:52
 * @version V1.0  
 */
@Service("AppMarketKeyService") 
public class AppMarketKeyServiceImpl implements AppMarketKeyService {
    /**
     * 应用市场秘钥DAO
     */
    @Resource(name="AppMarketKeyMapper")
    private AppMarketKeyMapper appMarketKeyMapper;
    /*
    * @see com.ningpai.app.service.AppMarketKeyService#delete(java.lang.Long)
    */
    @Override
    public int delete(Long id) {
        return this.appMarketKeyMapper.deleteByPrimaryKey(id);
    }

    /**
    * @see com.ningpai.app.service.AppMarketKeyService#insert(com.ningpai.app.bean.AppMarketKey)
    */
    @Override
    public int insert(AppMarketKey record) {
        return this.appMarketKeyMapper.insertSelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppMarketKeyService#select(java.lang.Long)
    */
    @Override
    public AppMarketKey select(Long id) {
        return this.appMarketKeyMapper.selectByPrimaryKey(id);
    }

    /**
    * @see com.ningpai.app.service.AppMarketKeyService#update(com.ningpai.app.bean.AppMarketKey)
    */
    @Override
    public int update(AppMarketKey record) {

        return this.appMarketKeyMapper.updateByPrimaryKeySelective(record);
    }

    /**
    * @see com.ningpai.app.service.AppMarketKeyService#deleteMuilti(java.lang.Long[])
    */
    @Override
    public int deleteMuilti(Long[] ids) {
        return this.appMarketKeyMapper.deleteMuilti(ids);
    }

    /**
    * @see com.ningpai.app.service.AppMarketKeyService#selectList(com.ningpai.app.bean.AppMarketKey,PageBean pageBean)
    */
    @Override
    public PageBean selectList(AppMarketKey record,PageBean pageBean) {
        pageBean.setObjectBean(record);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageBean",pageBean);
        pageBean.setRows(this.appMarketKeyMapper.selectListCount(map));
        pageBean.setList(this.appMarketKeyMapper.selectList(map));
        return pageBean;
    }

}