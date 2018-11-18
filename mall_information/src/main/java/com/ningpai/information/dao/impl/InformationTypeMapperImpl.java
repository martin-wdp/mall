/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.dao.impl;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.dao.InformationTypeMapper;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯类型DAO实现
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 16:50:17
 * @version
 */
@Repository("InformationTypeMapper")
public class InformationTypeMapperImpl extends BasicSqlSupport implements InformationTypeMapper {
    /**
     * 根据主键删除资讯栏目
     *
     * @param infoTypeId
     *            资讯栏目编号
     * @return
     * @see
     * com.ningpai.information.dao.InformationTypeMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long infoTypeId) {
        return this.delete("com.ningpai.information.dao.InformationTypeMapper.deleteByPrimaryKey", infoTypeId);
    }

    /**
     * 调用存储过程级联删除栏目下的子栏目和文章
     *
     * @param infoTypeId
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#deleteByPrimaryKeyPro(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyPro(Long infoTypeId) {
        return this.delete("com.ningpai.information.dao.InformationTypeMapper.deleteByPrimaryKeyPro", infoTypeId);
    }

    /**
     * 添加资讯栏目
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see  com.ningpai.information.dao.InformationTypeMapper#insert(com.ningpai.information.bean.InformationType)
     */
    public int insert(InformationType record) {
        return this.insert("com.ningpai.information.dao.InformationTypeMapper.insert",record);
    }

    /**
     * 添加资讯栏目-字段可选择
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#insertSelective(com.ningpai.information.bean.InformationType)
     */
    public int insertSelective(InformationType record) {
        return this.insert("com.ningpai.information.dao.InformationTypeMapper.insertSelective", record);
    }

    /**
     * 根据主键查找资讯栏目
     *
     * @param infoTypeId
     *            资讯栏目编号
     * @return InformationType 资讯栏目对象
     * @see  com.ningpai.information.dao.InformationTypeMapper#selectByPrimaryKey(java.lang.Long)
     */
    public InformationType selectByPrimaryKey(Long infoTypeId) {
        return this.selectOne(
                "com.ningpai.information.dao.InformationTypeMapper.selectByPrimaryKey",
                infoTypeId);
    }

    /**
     * 查找所有的资讯栏目
     *
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.dao.InformationTypeMapper#selectAll()
     */
    public List<InformationTypeVo> selectAll() {
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.selectAll");
    }

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，添加文章用<br/>
     *
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.dao.InformationTypeMapper#selectInfoTypeByAttrForAddInfo()
     */
    @Override
    public List<InformationTypeVo> selectInfoTypeByAttrForAddInfo() {
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.selectInfoTypeByAttrForAddInfo");
    }

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，关联模板、频道用<br/>
     *
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.dao.InformationTypeMapper#selectInfoTypeByAttrForTemp()
     */
    @Override
    public List<InformationTypeVo> selectInfoTypeByAttrForTemp() {
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.selectInfoTypeByAttrForTemp");
    }

    /**
     * 更新资讯栏目-字段可选择
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InformationType)
     */
    public int updateByPrimaryKeySelective(InformationType record) {
        return this.update("com.ningpai.information.dao.InformationTypeMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 更新资讯栏目
     *
     * @param record
     *            资讯栏目对象
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#updateByPrimaryKey(com.ningpai.information.bean.InformationType)
     */
    public int updateByPrimaryKey(InformationType record) {
        return this.update("com.ningpai.information.dao.InformationTypeMapper#updateByPrimaryKey", record);
    }

    /**
     * 根据父编号查找子资讯栏目
     *
     * @param parentId
     *            资讯栏目编号
     * @return List<InformationType> 资讯栏目对象集合
     * @see com.ningpai.information.dao.InformationTypeMapper#selectByParentId(java.lang.Long)
     */
    public List<InformationTypeVo> selectByParentId(Long parentId) {
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.selectByParentId", parentId);
    }

    /**
     * 查询所有的行数（分页用）
     *
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#queryTotalCount(java.util.Map)
     */
    public Integer queryTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.information.dao.InformationTypeMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询资讯栏目列表<br/>
     * startRowNum 起始行数<br/>
     * endRowNum 获取行数<br/>
     *
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#queryByPageBean(java.util.Map)
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.queryByPageBean", map);
    }

    /**
     * 根据父编号查找子资讯栏目数量
     *
     * @param parentId
     *            资讯栏目编号
     * @return
     * @see com.ningpai.information.dao.InformationTypeMapper#selectCountByParentId(java.lang.Long)
     */
    public int selectCountByParentId(Long parentId) {
        return this.selectOne("com.ningpai.information.dao.InformationTypeMapper.selectCountByParentId", parentId);
    }

    /**
     * 根据栏目名称查找资讯栏目数量
     *
     * @param name
     *            栏目名称
     * @return Integer 资讯栏目数量
     * @see com.ningpai.information.dao.InformationTypeMapper#selectInfoTypeCountByName(java.lang.String)
     */
    @Override
    public Integer selectInfoTypeCountByName(String name) {
        return this.selectOne("com.ningpai.information.dao.InformationTypeMapper.selectByName",name);
    }
    /**
     * 查询所有栏目
     *
     * @param searchText
     * @return Listn
     * @see com.ningpai.information.dao.InformationTypeMapper#selectAllType(java.lang.String)
     */
    @Override
    public List<InformationType> selectAllType(String searchText) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchText", searchText);
        return this.selectList("com.ningpai.information.dao.InformationTypeMapper.selectAllType",paramMap);
    }

}
