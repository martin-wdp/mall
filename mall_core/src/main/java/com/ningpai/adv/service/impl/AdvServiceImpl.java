/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.adv.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.adv.bean.Adv;
import com.ningpai.adv.dao.AdvMapper;
import com.ningpai.adv.service.AdvService;
import com.ningpai.util.PageBean;

/**
 * @Description: bb_adv的service的实现类:
 * @author zhangyue
 * @date 2014-08-30 14:41:09
 * @version V1.0
 */
@Service("AdvService")
public class AdvServiceImpl implements AdvService {

    // Spring注入
    @Resource(name = "AdvMapper")
    private AdvMapper advMapper;

    /*
     * 
     * @see com.ningpai.adv.service.AdvService#delete(java.lang.Long)
     */
    @Override
    public int delete(Long advId) {
        // 删除
        return this.advMapper.deleteByPrimaryKey(advId);
    }

    /*
     * 
     * @see com.ningpai.adv.service.AdvService#insert(com.ningpai.adv.bean.Adv)
     */
    @Override
    public int insert(Adv record) {
        // 初始化时间和删除标记
        record.setAdvCreateTime(new Date());
        record.setAdvModifyTime(new Date());
        record.setAdvDelFlag("0");
        return this.advMapper.insertSelective(record);
    }

    /*
     * 
     * @see com.ningpai.adv.service.AdvService#select(java.lang.Long)
     */
    @Override
    public Adv select(Long advId) {
        // 查询详细
        return this.advMapper.selectByPrimaryKey(advId);
    }

    /*
     * 
     * @see com.ningpai.adv.service.AdvService#update(com.ningpai.adv.bean.Adv)
     */
    @Override
    public int update(Adv record) {
        // 修改
        record.setAdvModifyTime(new Date());
        return this.advMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * @see
     * com.ningpai.adv.service.AdvService#selectPageList(com.ningpai.util.PageBean
     * , com.ningpai.adv.bean.Adv)
     */
    @Override
    public PageBean selectPageList(PageBean pageBean, Adv adv) {
        // 设置ObjectBean查询参数
        pageBean.setObjectBean(adv);
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置到Map中
        map.put("pageBean", pageBean);
        // 查询总数
        pageBean.setRows(advMapper.selectPageListCount(map));
        // 查询列表
        pageBean.setList(advMapper.selectPageList(map));
        return pageBean;
    }

    /*
     * 
     * @see com.ningpai.adv.service.AdvService#deleteAll(java.lang.Long[])
     */
    @Override
    public int deleteAll(Long[] advIds) {
        // 批量删除
        return advMapper.deleteMuilti(advIds);
    }

    /*
     * 
     * @see
     * com.ningpai.adv.service.AdvService#selectAdvListByPosition(java.lang.
     * String)
     */
    @Override
    public List<Adv> selectAdvListByPosition(String flag) {
        // 根据类型查询
        return advMapper.selectAdvListByPosition(flag);
    }

}
