/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.information.bean.Information;
import com.ningpai.information.dao.InformationMapper;
import com.ningpai.information.vo.InformationVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 资讯DAO实现
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:05:10
 * @version
 */
@Repository("InformationMapper")
public class InformationMapperImpl extends BasicSqlSupport implements InformationMapper {
    /**
     * 根据主键删除
     *
     * @param infoId
     * @return
     * @see com.ningpai.information.dao.InformationMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long infoId) {

        return this.delete("com.ningpai.information.dao.InformationMapper.deleteByPrimaryKey", infoId);
    }
    /**
     * 添加
     *
     * @param infoId
     * @return
     * @see com.ningpai.information.dao.InformationMapper#insert(com.ningpai.information.bean.Information)
     */
    public int insert(Information record) {

        return this.insert("com.ningpai.information.dao.InformationMapper.insert", record);
    }
    /**
     * 添加-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationMapper#insertSelective(com.ningpai.information.bean.Information)
     */
    public int insertSelective(Information record) {

        return this.insert("com.ningpai.information.dao.InformationMapper.insertSelective", record);
    }
    /**
     * 根据主键查询
     *
     * @param infoId
     * @return
     * @see com.ningpai.information.dao.InformationMapper#selectByPrimaryKey(java.lang.Long)
     */
    public Information selectByPrimaryKey(Long infoId) {

        return this.selectOne("com.ningpai.information.dao.InformationMapper.selectByPrimaryKey", infoId);
    }
    /**
     * 更新-字段可选-不包含内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.Information)
     */
    public int updateByPrimaryKeySelective(Information record) {

        return this.update("com.ningpai.information.dao.InformationMapper.updateByPrimaryKeySelective", record);
    }
    /**
     * 更新-包含内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationMapper#updateByPrimaryKeyWithBLOBs(com.ningpai.information.bean.Information)
     */
    public int updateByPrimaryKeyWithBLOBs(Information record) {

        return this.update("com.ningpai.information.dao.InformationMapper.updateByPrimaryKeyWithBLOBs", record);
    }
    /**
     * 更新-不包含内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationMapper#updateByPrimaryKey(com.ningpai.information.bean.Information)
     */
    public int updateByPrimaryKey(Information record) {

        return this.update("com.ningpai.information.dao.InformationMapper.updateByPrimaryKey", record);
    }
    /**
     * 查询全部
     *
     * @return
     * @see com.ningpai.information.dao.InformationMapper#selectAll()
     */
    public List<InformationVo> selectAll() {

        return this.selectList("com.ningpai.information.dao.InformationMapper.selectAll");
    }
    /**
     * 根据map参数查询所有行数
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InformationMapper#queryTotalCount(java.util.Map)
     */
    public Integer queryTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.information.dao.InformationMapper.queryTotalCount", map);
    }
    /**
     * 根据分页参数查询资讯列表
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InformationMapper#queryByPageBean(java.util.Map)
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {

        return this.selectList("com.ningpai.information.dao.InformationMapper.queryByPageBean", map);
    }

    /**
     * 根据栏目ID查询资讯数量
     *
     * @param infoTypeId
     * @return
     * @see com.ningpai.information.dao.InformationMapper#selectInfoCountByTypeId(java.lang.Long)
     */
    @Override
    public Integer selectInfoCountByTypeId(Long infoTypeId) {
        return this.selectOne("com.ningpai.information.dao.InformationMapper.selectInfoCountByTypeId", infoTypeId);
    }

    /**
     * 根据文章标题查询文章数量
     *
     * @param title
     * @return
     * @see com.ningpai.information.dao.InformationMapper#selectInfoCountByTitle(java.lang.String)
     */
    @Override
    public Integer selectInfoCountByTitle(String title) {
        return this.selectOne("com.ningpai.information.dao.InformationMapper.selectInfoCountByTitle", title);
    }

    /**
     * 根据栏目ID查询文章
     *
     * @param infoTypeId
     * @return
     * @see com.ningpai.information.dao.InformationMapper#selectByInfoType(java.lang.Long)
     */
    @Override
    public List<InformationVo> selectByInfoType(Long infoTypeId) {
        return this.selectList("com.ningpai.information.dao.InformationMapper.selectByInfoType", infoTypeId);
    }
}
