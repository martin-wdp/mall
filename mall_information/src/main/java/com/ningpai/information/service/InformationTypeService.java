/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.util.PageBean;

/**
 * 资讯类型SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:18:35
 * @version
 */
public interface InformationTypeService {
    /**
     * 根据主键删除资讯类型
     * 
     * @param loginUserId
     *            资讯类型编号
     * @return
     */
    void delInformation(Long infoTypeId, Long loginUserId);

    /**
     * 根据主键删除资讯类型-调用存储过程级联删除
     * 
     * @param infoTypeId
     *            资讯类型编号
     * @return
     */
    void delInformationPro(Long infoTypeId);

    /**
     * 批量删除资讯类型
     * 
     * @param ids
     *            资讯类型编号数组
     * @return
     */
    void batchDelInformation(Long[] ids, Long loginUserId);

    /**
     * 添加资讯类型
     * 
     * @param record
     *            资讯类型对象
     * @return
     */
    void saveInformation(InformationType record);

    /**
     * 更新资讯类型
     * 
     * @param record
     *            资讯类型对象
     * @return
     */
    void updateInformation(InformationType record);

    /**
     * 根据主键查找资讯类型
     * 
     * @param infoTypeId
     *            资讯类型编号
     * @return InformationType 资讯类型对象
     */
    InformationType selectByPrimaryKey(Long infoTypeId);

    /**
     * 根据分页参数查询资讯类型列表<br/>
     * 
     * @param pb
     *            分页工具类
     * @param searchText
     *            查询工具类
     * @return
     */
    PageBean queryByPageBean(PageBean pb, String searchText);

    /**
     * 查找所有的资讯类型
     * 
     * @return List<InformationTypeVo> 资讯类型List集合
     */
    List<InformationTypeVo> selectAll();

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，添加文章用<br/>
     * 
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationTypeVo> selectInfoTypeByAttrForAddInfo();

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，关联模板、频道用<br/>
     * 
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationTypeVo> selectInfoTypeByAttrForTemp();

    /**
     * 判断资讯类型是否可删除
     * 
     * @param infoTypeId
     *            资讯类型编号
     * @return 是否可以删除 false表示不可以删除
     */
    boolean checkDelWithInfoTypeId(Long infoTypeId);

    /**
     * 获得资讯类型下的所有的子类型
     * 
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 整理好的资讯类型列表
     */
    List<Object> getInfoTypeList(PageBean pb, String searchText);

    /**
     * 使用递归根据父分类ID计算所有的子分类
     * 
     * @param parentId
     *            第一级的父分类ID
     * @param infoTypeVoList
     *
     * @return 计算好的分类实体
     */
    List<InformationTypeVo> calcInfoTypeVo(Long parentId, List<InformationTypeVo> infoTypeVoList);

    /**
     * 根据栏目名称验证是否可添加
     * 
     * @param name
     *            栏目名称
     * @return
     */
    boolean checkAddInfoTypeByName(String name);

    /**
     * 修改栏目-根据栏目名称和栏目ID验证是否可修改
     * 
     * @param name
     *            栏目名称
     * @param infoTypeId
     *            栏目ID
     * @return
     */
    boolean checkAddInfoTypeByName(String name, Long infoTypeId);

    /**
     * 查询栏目分类
     * @param searchText
     * @return PageBean
     */
    PageBean queryByPageBeanList(PageBean pb, String searchText);
}
