/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.adv.dao.impl;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.adv.bean.Adv;
import com.ningpai.adv.dao.AdvMapper;

/**  
 * @Description: bb_adv的dao的实现类:
 * @author zhangyue
 * @date 2014-08-30 14:41:09
 * @version V1.0  
 */
@Repository("AdvMapper") 
public class AdvMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AdvMapper {

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @date 2014-08-30 14:41:09
     */
    @Override
    public int deleteByPrimaryKey(Long advId) {
        return this.update("com.ningpai.mybatis.mapper.site.AdvMapper.deleteByPrimaryKey", advId);
    }

    /*
    * 插入，空属性不会插入
    * 参数:pojo对象
    * 返回:添加个数
    * @date 2014-08-30 14:41:09
    * @see com.ningpai.adv.dao.AdvMapper#insertSelective(com.ningpai.adv.bean.Adv)
    */
    @Override
    public int insertSelective(Adv record) {
        return this.insert("com.ningpai.mybatis.mapper.site.AdvMapper.insertSelective", record);
    }

    /* 
    * 根据主键查询
    * 参数:查询条件,主键值
    * 返回:对象
    * @date 2014-08-30 14:41:09
    * @see com.ningpai.adv.dao.AdvMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public Adv selectByPrimaryKey(Long advId) {
        return this.selectOne("com.ningpai.mybatis.mapper.site.AdvMapper.selectByPrimaryKey", advId);
    }

    /* 
    * 根据主键修改，空值条件不会修改成null
    * 参数:1.要修改成的值
    * 返回:成功修改个数
    * @date 2014-08-30 14:41:09
    * @see com.ningpai.adv.dao.AdvMapper#updateByPrimaryKeySelective(com.ningpai.adv.bean.Adv)
    */
    @Override
    public int updateByPrimaryKeySelective(Adv record) {
        return this.update("com.ningpai.mybatis.mapper.site.AdvMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 查询分页列表总数
     * @param map 查询参数
     * @return int 记录条数
     * @see com.ningpai.adv.dao.AdvMapper#selectPageListCount(java.util.Map)
     */
    @Override
    public int selectPageListCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.site.AdvMapper.selectPageListCount", map);
    }

    /*
     * 查询分页列表
     * @param map 查询参数
     * @return List 记录列表
     * @see com.ningpai.adv.dao.AdvMapper#selectPageList(java.util.Map)
     */
    @Override
    public List<Object> selectPageList(Map<String, Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.site.AdvMapper.selectPageList", map);
    }

    /*
     * 删除多条记录
     * @param advIds 广告列表
     * @return int 返回结果
     * @see com.ningpai.adv.dao.AdvMapper#deleteMuilti(java.lang.Long[])
     */
    @Override
    public int deleteMuilti(Long[] advIds) {
        return this.update("com.ningpai.mybatis.mapper.site.AdvMapper.deleteMuilti", advIds);
    }

    /*
     * 通过广告位置查询广告列表
     * @param advPosition
     * @return List
     * @see com.ningpai.adv.dao.AdvMapper#selectAdvListByPosition(java.lang.String)
     */
    @Override
    public List<Adv> selectAdvListByPosition(String advPosition) {
        return this.selectList("com.ningpai.mybatis.mapper.site.AdvMapper.selectAdvListByPosition", advPosition);
    }
}
