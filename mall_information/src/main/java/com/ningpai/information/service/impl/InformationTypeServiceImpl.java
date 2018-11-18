/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service.impl;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.dao.InformationTypeMapper;
import com.ningpai.information.service.InformationTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 资讯类型SERVICE
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:18:35
 * @version
 */
@Service("InformationTypeService")
public class InformationTypeServiceImpl implements InformationTypeService {
    /** 资讯栏目DAO */
    private InformationTypeMapper informationTypeMapper;

    public InformationTypeMapper getInformationTypeMapper() {
        return informationTypeMapper;
    }

    @Resource(name = "InformationTypeMapper")
    public void setInformationTypeMapper(
            InformationTypeMapper informationTypeMapper) {
        this.informationTypeMapper = informationTypeMapper;
    }
    /**
     * 根据主键删除资讯类型
     *
     * @param loginUserId
     *            资讯类型编号
     * @return
     * @see com.ningpai.information.service.InformationTypeService#delInformation(java.lang.Long,java.lang.Long)
     */
    public void delInformation(Long infoTypeId, Long loginUserId) {
        /** 执行查询方法并把结果赋值给实体类 */
        InformationType infoType = this.selectByPrimaryKey(infoTypeId);
        /** 设置删除标记为1 */
        infoType.setDelflag("1");
        /**  设置更新者id */
        infoType.setUpdateUserId(loginUserId);
        /** 设置更新时间为当前时间 */
        infoType.setUpdateDate(new Date());
        /** 执行更新方法 */
        this.updateInformation(infoType);
    }

    /**
     * 根据主键删除资讯类型-调用存储过程级联删除
     *
     * @param infoTypeId
     *            资讯类型编号
     * @return
     * @see com.ningpai.information.service.InformationTypeService#delInformationPro(java.lang.Long)
     */
    @Override
    public void delInformationPro(Long infoTypeId) {
        /** 调用存储过程级联删除栏目下的子栏目和文章 */
        this.informationTypeMapper.deleteByPrimaryKeyPro(infoTypeId);
    }
    /**
     * 批量删除资讯类型
     *
     * @param ids
     *            资讯类型编号数组
     * @return
     * @see com.ningpai.information.service.InformationTypeService#batchDelInformation(java.lang.Long[],java.lang.Long)
     */
    public void batchDelInformation(Long[] ids, Long loginUserId) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                /** 根据主键遍历删除资讯类型 */
                delInformation(ids[i], loginUserId);
            }
        }
    }
    /**
     * 添加资讯类型
     *
     * @param record
     *            资讯类型对象
     * @return
     * @see com.ningpai.information.service.InformationTypeService#saveInformation(com.ningpai.information.bean.InformationType)
     */
    public void saveInformation(InformationType record) {
        if (record.getParentId() == 0) {
            record.setGrade(1);
        } else {
            /** 获取它父栏目的层级，+1后赋值给它本身 */
            record.setGrade(this.informationTypeMapper.selectByPrimaryKey(
                    record.getParentId()).getGrade() + 1);
        }
        /** 获取当前时间 */
        Date date = new Date();
        /** 设置创建时间为当前时间 */
        record.setCreateDate(date);
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(date);
        /** 执行添加方法并返回结果 */
        this.informationTypeMapper.insertSelective(record);
    }
    /**
     * 更新资讯类型
     *
     * @param record
     *            资讯类型对象
     * @return
     * @see com.ningpai.information.service.InformationTypeService#updateInformation(com.ningpai.information.bean.InformationType)
     */
    public void updateInformation(InformationType record) {
        if (record.getParentId() == 0) {
            record.setGrade(1);
        } else {
            /** 根据主键查找资讯栏目 */
            record.setGrade(this.informationTypeMapper.selectByPrimaryKey(record.getParentId()).getGrade() + 1);
        }
        record.setUpdateDate(new Date());
        /** 更新资讯栏目 */
        this.informationTypeMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 根据主键查找资讯类型
     *
     * @param infoTypeId
     *            资讯类型编号
     * @return InformationType 资讯类型对象
     * @see com.ningpai.information.service.InformationTypeService#selectByPrimaryKey(java.lang.Long)
     */
    public InformationType selectByPrimaryKey(Long infoTypeId) {
        /** 执查询方法并返回结果 */
        return informationTypeMapper.selectByPrimaryKey(infoTypeId);
    }
    /**
     * 根据分页参数查询资讯类型列表<br/>
     *
     * @param pb
     *            分页工具类
     * @param searchText
     *            查询工具类
     * @return
     * @see com.ningpai.information.service.InformationTypeService#queryByPageBean(com.ningpai.util.PageBean,java.lang.String)
     */
    public PageBean queryByPageBean(PageBean pb, String searchText) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("searchText", searchText);
            /** 查询数据的总行数并设置到PageBean中 */
            pb.setRows(this.informationTypeMapper.queryTotalCount(map));
            /** 设置开始行数 */
            map.put("startRowNum", pb.getStartRowNum());
            /** 设置结束行数 */
            map.put("endRowNum", pb.getEndRowNum());
            /** 根据分页参数查询资讯栏目列表 */
            pb.setList(this.informationTypeMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }
    /**
     * 查找所有的资讯类型
     *
     * @return List<InformationTypeVo> 资讯类型List集合
     * @see com.ningpai.information.service.InformationTypeService#selectAll()
     */
    public List<InformationTypeVo> selectAll() {
        /** 查找所有的资讯类型 */
        return informationTypeMapper.selectAll();
    }

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，添加文章用<br/>
     *
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.service.InformationTypeService#selectInfoTypeByAttrForAddInfo()
     */
    @Override
    public List<InformationTypeVo> selectInfoTypeByAttrForAddInfo() {
        /** 执行查询方法并返回结果 */
        return informationTypeMapper.selectInfoTypeByAttrForAddInfo();
    }

    /**
     * 根据栏目属性查找所有可发布文章的资讯类型，关联模板、频道用<br/>
     *
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     * @see com.ningpai.information.service.InformationTypeService#selectInfoTypeByAttrForTemp()
     */
    @Override
    public List<InformationTypeVo> selectInfoTypeByAttrForTemp() {
        /** 执行查询方法并返回结果 */
        return informationTypeMapper.selectInfoTypeByAttrForTemp();
    }
    /**
     * 判断资讯类型是否可删除
     *
     * @param infoTypeId
     *            资讯类型编号
     * @return 是否可以删除 false表示不可以删除
     * @see com.ningpai.information.service.InformationTypeService#checkDelWithInfoTypeId(java.lang.Long)
     */
    public boolean checkDelWithInfoTypeId(Long infoTypeId) {
        /** 根据父编号查找子资讯栏目数量 */
        return informationTypeMapper.selectCountByParentId(infoTypeId) > 0 ? false: true;
    }
    /**
     * 获得资讯类型下的所有的子类型
     *
     * @param pb
     *            分页辅助类 {@link com.ningpai.util.PageBean}
     * @return 整理好的资讯类型列表
     * @see com.ningpai.information.service.InformationTypeService#getInfoTypeList(com.ningpai.util.PageBean,java.lang.String)
     */
    public List<Object> getInfoTypeList(PageBean pb, String searchText) {
        /** 创建需要返回的集合 */
        List<Object> infoTypeVoList = new ArrayList<Object>();
        /** 根据分页查询所有的父分类的集合 */
        List<Object> parentList = this.queryByPageBean(pb, searchText).getList();
        /** 查询所有的资讯类型信息 */
        List<InformationTypeVo> allInfoTypeList = this.informationTypeMapper.selectAll();
        InformationTypeVo infoType = null;
        try {
            /** 根据父类型集合循环调用计算的方法，整理出二叉树 */
            for (int i = 0; i < parentList.size(); i++) {
                /** 从父类型中取出每一个分类 */
                infoType = (InformationTypeVo) parentList.get(i);
                infoType.setChilds(this.calcInfoTypeVo(infoType.getInfoTypeId(), allInfoTypeList));
                infoTypeVoList.add(infoType);
            }
            /** 返回整理好的类型集合 */
            return infoTypeVoList;
        } finally {
            /** 置空所有参数 */
            infoTypeVoList = null;
            parentList = null;
            allInfoTypeList = null;
            infoType = null;
        }
    }
    /**
     * 使用递归根据父分类ID计算所有的子分类
     *
     * @param parentId
     *            第一级的父分类ID
     * @param allInfoTypeVoList
     *
     * @return 计算好的分类实体
     * @see com.ningpai.information.service.InformationTypeService#calcInfoTypeVo(java.lang.Long,java.util.List)
     */
    public List<InformationTypeVo> calcInfoTypeVo(Long parentId, List<InformationTypeVo> allInfoTypeVoList) {
        /** 定义需要返回的list */
        List<InformationTypeVo> infoTypeVoList = new ArrayList<InformationTypeVo>();
        /** 返回的类型实体的所有子类型 */
        List<InformationTypeVo> allSonCate = null;
        /** 需要返回的类型实体 */
        InformationTypeVo infoType = null;
        try {
            /** 进行递归 */
            for (int i = 0; i < allInfoTypeVoList.size(); i++) {
                if (parentId.equals(allInfoTypeVoList.get(i).getParentId())) {
                    /** 必须在这里new对象 否则会覆盖原先的数据 */
                    infoType = allInfoTypeVoList.get(i);
                    /** 执行递归 */
                    allSonCate = calcInfoTypeVo(infoType.getInfoTypeId(),allInfoTypeVoList);
                    infoType.setChilds(allSonCate);
                    infoTypeVoList.add(infoType);
                }
            }
            /** 返回计算好的所有的子分类列表 */
            return infoTypeVoList;
        } finally {
            /** 置空所有参数 */
            infoTypeVoList = null;
            allSonCate = null;
            infoType = null;
        }
    }

    /**
     * 根据栏目名称验证是否可添加
     *
     * @param name
     *            栏目名称
     * @return
     * @see com.ningpai.information.service.InformationTypeService#checkAddInfoTypeByName(java.lang.String)
     */
    @Override
    public boolean checkAddInfoTypeByName(String name) {
        /** 返回结果  根据栏目名称查找资讯栏目数量 */
        return this.informationTypeMapper.selectInfoTypeCountByName(name) > 0 ? false: true;
    }

    /**
     * 修改栏目-根据栏目名称和栏目ID验证是否可修改
     *
     * @param name
     *            栏目名称
     * @param infoTypeId
     *            栏目ID
     * @return
     * @see com.ningpai.information.service.InformationTypeService#checkAddInfoTypeByName(java.lang.String,java.lang.Long)
     */
    @Override
    public boolean checkAddInfoTypeByName(String name, Long infoTypeId) {
        /** 根据主键查找资讯栏目 */
        InformationType infoType = informationTypeMapper.selectByPrimaryKey(infoTypeId);
        /** 判断 */
        if (null != infoType) {
            if (infoType.getName().equals(name)) {
                return true;
            } else {
                /** 返回结果  根据栏目名称查找资讯栏目数量 */
                return this.informationTypeMapper.selectInfoTypeCountByName(name) > 0 ? false : true;
            }
        } else {
            return false;
        }
    }

    /**
     * 查询栏目分类
     * @param searchText
     * @return PageBean
     * @see com.ningpai.information.service.InformationTypeService#queryByPageBeanList(com.ningpai.util.PageBean,java.lang.String)
     */
    @Override
    public PageBean queryByPageBeanList(PageBean pb, String searchText) {
        /** 定义需要返回的list */
        List<Object> typeList = new ArrayList<Object>();
        /** 查询所有list */
        List<InformationType> infolist = informationTypeMapper
                .selectAllType(searchText);
        /** 查询父List */
        List<InformationType> parentList = new ArrayList<InformationType>();
        /** 判断 */
        if (infolist != null) {
            for (int i = 0; i < infolist.size(); i++) {
                if (infolist.get(i).getParentId() == 0) {
                    parentList.add(infolist.get(i));
                }
            }
        }
        /** 循环添加 */
        try {
            /** 根据父分类集合循环调用计算的方法，整理出二叉树 */
            for (int i = 0; i < parentList.size(); i++) {
                /** 从父分类中取出每一个分类 */
                InformationType type = parentList.get(i);
                /** 根据取得的父分类ID和所有的分类列表计算分类实体，并添加到需要返回的集合中 */
                type.setTypeList(this.calcType(type.getInfoTypeId(), infolist));
                typeList.add(type);
            }
            /** 返回整理好的分类集合 */
            pb.setList(typeList);
            return pb;
        } finally {
            /** 置空所有参数 */
            typeList = null;
            parentList = null;
            infolist = null;
        }

    }

    /**
     * 根据取得的父分类ID和所有的分类列表计算分类实体
     * @param parentId
     * @param typeList
     * @return
     */
    public List<InformationType> calcType(Long parentId,List<InformationType> typeList) {
        /** 定义需要返回的list */
        List<InformationType> cateVoList = new ArrayList<InformationType>();
        /** 返回的分类实体的所有子分类 */
        List<InformationType> allSonCate = null;
        try {
            /** 进行递归 */
            for (int i = 0; i < typeList.size(); i++) {
                if (parentId.equals(typeList.get(i).getParentId())) {
                    /** 必须在这里new对象 否则会覆盖原先的数据 */
                    InformationType cate = typeList.get(i);
                    /** 执行递归 */
                    allSonCate = calcType(cate.getInfoTypeId(), typeList);
                    cate.setTypeList(allSonCate);
                    cateVoList.add(cate);
                }
            }
            /** 返回结果 */
            return cateVoList;
        } finally {
            /** 置空所有参数 */
            cateVoList = null;
            allSonCate = null;
        }

    }
}
