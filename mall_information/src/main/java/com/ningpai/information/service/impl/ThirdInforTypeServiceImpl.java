/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.dao.ThirdInforTypeMapper;
import com.ningpai.information.service.ThirdInforTypeService;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.util.PageBean;

/**
 * 资讯类型SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:18:35
 * @version
 */
@Service("ThirdInforTypeService")
public class ThirdInforTypeServiceImpl implements ThirdInforTypeService {

    /** 资讯栏目DAO */
    @Resource(name = "ThirdInforTypeMapper")
    private ThirdInforTypeMapper thirdInforTypeMapper;

    private static final String TEMP2 = "temp2";

    /**
     * 根据主键删除文章栏目
     *
     * @param infoTypeId
     *            文章栏目编号
     * @param loginUserId
     *            用户编号
     * @see com.ningpai.information.service.InformationTypeService#delInformation(java.lang.Long,java.lang.Long)
     */
    public void delInformation(Long infoTypeId, Long loginUserId) {
        /** 执行查询方法并把结果赋值给实体类 */
        InformationType infoType = this.selectByPrimaryKey(infoTypeId);
        /** 设置删除标记为1 */
        infoType.setDelflag("1");
        /** 设置更新用户Id为登录id */
        infoType.setUpdateUserId(loginUserId);
        /** 设置更新时间为当前时间 */
        infoType.setUpdateDate(new Date());
        /** 执行更新方法 */
        this.updateInformation(infoType);
    }

    /**
     * 批量删除资讯类型
     *
     * @param ids
     *            资讯类型编号数组
     * @param loginUserId
     *            用户编号
     * @see com.ningpai.information.service.InformationTypeService#batchDelInformation(java.lang.Long[],java.lang.Long)
     */
    public void batchDelInformation(Long[] ids, Long loginUserId) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                /** 根据主键遍历删除文章栏目 */
                delInformation(ids[i], loginUserId);
            }
        }
    }

    /**
     * 添加资讯类型
     *
     * @param record
     *            栏目对象
     * @param thirdId
     *            第三方商家ID
     * @see com.ningpai.information.service.InformationTypeService#saveInformation(com.ningpai.information.bean.InformationType)
     */
    public void saveInformation(InformationType record, String thirdId) {
        if (record.getParentId() == 0) {
            record.setGrade(1);
        } else {
            /** 获取它父栏目的层级，+1后赋值给它本身 */
            record.setGrade(this.thirdInforTypeMapper.selectByPrimaryKey(record.getParentId()).getGrade() + 1);
        }
        record.setTemp1("1");
        record.setTemp2(thirdId);
        /** 获取当前时间 */
        Date date = new Date();
        /** 设置创建时间为当前时间 */
        record.setCreateDate(date);
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(date);
        /** 执行添加方法并返回结果 */
        this.thirdInforTypeMapper.insertSelective(record);
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
            record.setGrade(this.thirdInforTypeMapper.selectByPrimaryKey(record.getParentId()).getGrade() + 1);
        }
        record.setUpdateDate(new Date());
        /** 更新资讯栏目 */
        this.thirdInforTypeMapper.updateByPrimaryKeySelective(record);
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
        /** 根据主键查找资讯栏目 */
        return thirdInforTypeMapper.selectByPrimaryKey(infoTypeId);
    }

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表
     *
     * @param pb
     *            分页参数
     * @param typeName
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @return
     * @see com.ningpai.information.service.InformationTypeService#queryByPageBean(com.ningpai.util.PageBean,java.lang.String)
     */
    public PageBean queryByPageBean(PageBean pb, String typeName, String thirdId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null == typeName || "".equals(typeName)) {
                map.put(TEMP2, thirdId);
                /** 查询数据的总行数并设置到PageBean中 */
                pb.setRows(this.thirdInforTypeMapper.queryTotalCount(map));
                /** 设置开始行数 */
                map.put("startRowNum", pb.getStartRowNum());
                /** 设置结束行数 */
                map.put("endRowNum", pb.getEndRowNum());
                /** 根据分页参数和第三方商家ID查询资讯类型列表 */
                pb.setList(this.thirdInforTypeMapper.queryByPageBean(map));
            } else {
                map.put("searchText", typeName);
                map.put(TEMP2, thirdId);
                /** 查询数据的总行数并设置到PageBean中 */
                pb.setRows(this.thirdInforTypeMapper.queryTotalCountForSearch(map));
                /** 设置开始行数 */
                map.put("startRowNum", pb.getStartRowNum());
                /** 设置结束行数 */
                map.put("endRowNum", pb.getEndRowNum());
                /** 根据分页参数和第三方商家ID查询资讯类型列表（查询分页） */
                pb.setList(this.thirdInforTypeMapper.queryByPageBeanForSearch(map));
            }
        } finally {
            map = null;
        }
        /** 返回结果 */
        return pb;
    }

    /**
     * 根据第三方商家ID查找所有的资讯类型
     *
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯类型List集合
     *
     */
    public List<InformationTypeVo> selectAllByThirdId(String thirdId) {
        /** 执行方法并返回结果 */
        return thirdInforTypeMapper.selectAllByThirdId(thirdId);
    }

    /**
     * 根据第三方商家ID查找所有可发布文章的栏目<br/>
     *
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    @Override
    public List<InformationType> selectInfoTypeByAttr(String thirdId) {
        /** 执行方法并返回结果 */
        return thirdInforTypeMapper.selectInfoTypeByAttr(thirdId);
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
        /** 执行方法并返回结果 */
        return thirdInforTypeMapper.selectCountByParentId(infoTypeId) > 0 ? false : true;
    }

    /**
     * 根据第三方商家ID和栏目名称验证是否可添加
     *
     * @param name
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @return
     * @see com.ningpai.information.service.InformationTypeService#checkAddInfoTypeByName(java.lang.String)
     */
    @Override
    public boolean checkAddInfoTypeByName(String name, String thirdId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put(TEMP2, thirdId);
        /** 执行方法并返回结果 */
        return this.thirdInforTypeMapper.selectInfoTypeCountByName(map) > 0 ? false : true;
    }

    /**
     * 修改栏目-根据栏目名称和栏目ID验证是否可修改
     *
     * @param name
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @param infoTypeId
     *            栏目ID
     * @return
     * @see com.ningpai.information.service.InformationTypeService#checkAddInfoTypeByName(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkAddInfoTypeByName(String name, String thirdId, Long infoTypeId) {
        /** 根据主键查找资讯栏目 */
        InformationType infoType = thirdInforTypeMapper.selectByPrimaryKey(infoTypeId);
        if (null != infoType) {
            if (infoType.getName().equals(name)) {
                return true;
            } else {
                /** 定义一个HashMap集合 */
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name", name);
                map.put(TEMP2, thirdId);
                /** 执行方法并返回结果 */
                return this.thirdInforTypeMapper.selectInfoTypeCountByName(map) > 0 ? false : true;
            }
        } else {
            return false;
        }
    }

    /**
     * 获得资讯类型下的所有的子类型
     *
     * @param parentId
     * @return
     * @see com.ningpai.information.service.ThirdInforTypeService#getInfoTypeList(java.lang.Long)
     */
    @Override
    public List<InformationTypeVo> getInfoTypeList(Long parentId) {
        /** 执行方法并返回结果 */
        return thirdInforTypeMapper.selectByParentId(parentId);
    }
}
