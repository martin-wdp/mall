/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.dao.ThirdInforTypeMapper;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 资讯类型DAO实现
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 16:50:17
 * @version
 */
@Repository("ThirdInforTypeMapper")
public class ThirdInforTypeMapperImpl extends BasicSqlSupport implements ThirdInforTypeMapper {
    /**
     * 根据主键删除资讯栏目
     *
     * @param infoTypeId
     *            资讯栏目编号
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long infoTypeId) {
        return this.delete("com.ningpai.third.information.dao.ThirdInforTypeMapper.deleteByPrimaryKey", infoTypeId);
    }

    /**
     * 添加资讯栏目
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#insert(com.ningpai.information.bean.InformationType)
     */
    public int insert(InformationType record) {
        return this.insert("com.ningpai.third.information.dao.ThirdInforTypeMapper.insert", record);
    }

    /**
     * 添加资讯栏目-字段可选择
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#insertSelective(com.ningpai.information.bean.InformationType)
     */
    public int insertSelective(InformationType record) {
        return this.insert("com.ningpai.third.information.dao.ThirdInforTypeMapper.insertSelective", record);
    }

    /**
     * 根据主键查找资讯栏目
     *
     * @param infoTypeId
     *            资讯栏目编号
     * @return InformationType 资讯栏目对象
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectByPrimaryKey(java.lang.Long)
     */
    public InformationType selectByPrimaryKey(Long infoTypeId) {
        return this.selectOne("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectByPrimaryKey", infoTypeId);
    }

    /**
     * 根据第三方商家ID查找所有文章栏目
     *
     * @param temp2
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectAllByThirdId(java.lang.String)
     */
    public List<InformationTypeVo> selectAllByThirdId(String temp2) {
        return this.selectList("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectAllByThirdId", temp2);
    }

    /**
     * 根据第三方商家ID查找所有可发布文章的栏目<br/>
     *
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectInfoTypeByAttr(java.lang.String)
     */
    @Override
    public List<InformationType> selectInfoTypeByAttr(String thirdId) {
        return this.selectList("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectInfoTypeByAttr", thirdId);
    }

    /**
     * 更新资讯栏目-字段可选择
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InformationType)
     */
    public int updateByPrimaryKeySelective(InformationType record) {
        return this.update("com.ningpai.third.information.dao.ThirdInforTypeMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 更新资讯栏目
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#updateByPrimaryKey(com.ningpai.information.bean.InformationType)
     */
    public int updateByPrimaryKey(InformationType record) {
        return this.update("com.ningpai.third.information.dao.ThirdInforTypeMapper#updateByPrimaryKey", record);
    }

    /**
     * 根据父编号查找子资讯栏目
     *
     * @param parentId
     *            资讯栏目编号
     * @return List<InformationType> 资讯栏目对象集合
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectByParentId(java.lang.Long)
     */
    public List<InformationTypeVo> selectByParentId(Long parentId) {
        return this.selectList("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectByParentId", parentId);
    }

    /**
     * 根据查询参数和第三方商家ID查询所有的行数（分页用）
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#queryTotalCount(java.util.Map)
     */
    public Integer queryTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.information.dao.ThirdInforTypeMapper.queryTotalCount");
    }

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#queryByPageBean(java.util.Map)
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.third.information.dao.ThirdInforTypeMapper.queryByPageBean", map);
    }

    /**
     * 根据父编号查找子资讯栏目数量
     *
     * @param parentId
     *            资讯栏目编号
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectCountByParentId(java.lang.Long)
     */
    public int selectCountByParentId(Long parentId) {
        return this.selectOne("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectCountByParentId", parentId);
    }

    /**
     * 根据栏目名称和第三方商家ID查找文章栏目数量
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#selectInfoTypeCountByName(java.util.Map)
     */
    @Override
    public Integer selectInfoTypeCountByName(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.information.dao.ThirdInforTypeMapper.selectInfoTypeCountByName", map);
    }

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表（查询分页）
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#queryByPageBeanForSearch(java.util.Map)
     */
    @Override
    public List<Object> queryByPageBeanForSearch(Map<String, Object> map) {
        return this.selectList("com.ningpai.third.information.dao.ThirdInforTypeMapper.queryByPageBeanForSearch", map);
    }

    /**
     * 根据查询参数和第三方商家ID查询所有的行数（查询分页用）
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.ThirdInforTypeMapper#queryTotalCountForSearch(java.util.Map)
     */
    @Override
    public Integer queryTotalCountForSearch(Map<String, Object> map) {
        return this.selectOne("com.ningpai.third.information.dao.ThirdInforTypeMapper.queryTotalCountForSearch", map);
    }
}
