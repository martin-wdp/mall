/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service.impl;

import com.ningpai.site.customer.bean.Browserecord;
import com.ningpai.site.customer.mapper.BrowserecordMapper;
import com.ningpai.site.customer.service.BrowserecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 浏览记录Service
 * @see com.ningpai.site.customer.service.BrowserecordService
 * @author NINGPAI-zhangqiang
 * @since 2014年4月12日 下午4:49:04
 * @version
 */
@Service("browserecordService")
public class BrowserecordServiceImpl implements BrowserecordService {

    /**
     * 浏览记录
     */
    private BrowserecordMapper browserecordMapper;

    /**
     * 查询浏览记录
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public List<Browserecord> selectBrowserecord(Long customerId) {
        return browserecordMapper.selectBrowserecord(customerId);
    }

    /**
     * 根据主键删除
     * @param likeId
     *            浏览编号
     * @param customerId
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long likeId, Long customerId) {
        // 创建封装容器
        Map<String, Object> map = new HashMap<>();
        // 封装参数
        map.put("likeId", likeId);
        map.put("customerId", customerId);
        // 删除浏览记录
        return browserecordMapper.deleteByPrimaryKey(map);
    }
    /**
     * 根据货品编号删除
     *
     * @param goodInfoId
     *           货品编号
     * @return 0失败 1成功
     */
    @Override
    public int deleteByGoodsInfoId(Long goodInfoId, Long customerId) {
        // 创建封装容器
        Map<String, Object> map = new HashMap<>();
        // 封装参数
        map.put("goodInfoId", goodInfoId);
        map.put("customerId", customerId);
        // 删除浏览记录
        return browserecordMapper.deleteByPrimaryKey(map);
    }

    public BrowserecordMapper getBrowserecordMapper() {
        return browserecordMapper;
    }

    @Resource(name = "browserecordMapper")
    public void setBrowserecordMapper(BrowserecordMapper browserecordMapper) {
        this.browserecordMapper = browserecordMapper;
    }

}
