/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.imagemanage.service.impl;

import com.ningpai.imagemanage.bean.InfoImageClassify;
import com.ningpai.imagemanage.dao.InfoImageClassifyMapper;
import com.ningpai.imagemanage.service.InfoImageClassifyService;
import com.ningpai.imagemanage.vo.InfoImageClassifyVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-资源图片类型
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:56:44
 */
@Service("InfoImageClassifyService")
public class InfoImageClassifyServiceImpl implements InfoImageClassifyService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoImageClassifyServiceImpl.class);

    /** 数据访问层依赖 */
    private InfoImageClassifyMapper infoImageClassifyMapper;

    /**
     * 根据ID删除
     * 
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      deleteInfoImageClassify(java.lang.Long)
     */
    @Override
    public int deleteInfoImageClassify(Long classifyId) {
        try {
            /** 根据参数classifyId查询出InfoImageClassify对象 */
            InfoImageClassify infoImageClassify = infoImageClassifyMapper.selectByPrimaryKey(classifyId);
            /** 设置删除标记值为1 */
            infoImageClassify.setDelflag("1");
            /** 设置更新时间为当前时间 */
            infoImageClassify.setUpdateDate(new Date());
            /** 返回更新结果 */
            return infoImageClassifyMapper.updateByPrimaryKeySelective(infoImageClassify);
        } catch (Exception e) {
            LOGGER.error("删除图片管理类型错误:" ,e);
            /** 返回0 */
        }
        return 0;
    }

    /**
     * 添加
     * 
     * @param record
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      saveInfoImageClassify(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int saveInfoImageClassify(InfoImageClassify record) {
        try {
            /**
             * 如果资源图片类型的父类Id等于-1 就设置grade的值为1
             */
            if (record.getParentId() == -1) {
                record.setGrade(1);
            } else {
                /** 获取它父栏目的层级，+1后赋值给它本身 */
                record.setGrade(this.infoImageClassifyMapper.selectByPrimaryKey(record.getParentId()).getGrade() + 1);
            }
            /** 获取当前时间 */
            Date date = new Date();
            /** 把创建时间赋值为当前时间 */
            record.setCreateDate(date);
            /** 设置更新时间为当前时间 */
            record.setUpdateDate(date);
            /** 返回处理结果 */
            return infoImageClassifyMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("添加图片管理类型错误:",e);
            /** 返回0 */
        }
        return 0;
    }

    /**
     * 修改
     * 
     * @param record
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      updateInfoImageClassify(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int updateInfoImageClassify(InfoImageClassify record) {
        try {
            /**
             * 如果资源图片类型的父类Id等于0 就设置grade的值为1
             */
            if (record.getParentId() == -1) {
                record.setGrade(1);
            } else {
                /** 获取它父栏目的层级，+1后赋值给它本身 */
                if (this.infoImageClassifyMapper.selectByPrimaryKey(record.getParentId()) != null) {
                    record.setGrade(this.infoImageClassifyMapper.selectByPrimaryKey(record.getParentId()).getGrade() + 1);
                } else {
                    // 一级
                    record.setGrade(1);
                }
            }
            /** 设置更新时间为当前时间 */
            record.setUpdateDate(new Date());
            /** 返回结果 */
            return infoImageClassifyMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改图片管理类型错误:",e);
            /** 返回0 */
        }
        /** 返回0 */
        return 0;
    }

    /**
     * 根据主键查询
     * 
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      getInfoImageClassifyById(java.lang.Long)
     */
    @Override
    public InfoImageClassify getInfoImageClassifyById(Long classifyId) {
        /** 声明一个图片资源 */
        InfoImageClassify infoImageClassify = null;
        try {
            /** 为图片资源实体类进行赋值 */
            infoImageClassify = infoImageClassifyMapper.selectByPrimaryKey(classifyId);
        } catch (Exception e) {
            LOGGER.error("根据ID查询图片管理类型错误:" ,e);
        }
        /** 返回结果 */
        return infoImageClassify;
    }

    /**
     * 查询图片管理分类
     * 
     * @param pb
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      selectImageClassifyByParam(com.ningpai.util.PageBean)
     */
    @Override
    public PageBean selectImageClassifyByParam(PageBean pb) {
        try {
            /** 定义一个HashMap集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            /** 设置pageBean 的行数 */
            pb.setRows(infoImageClassifyMapper.selectImageClassifyCountByParam());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            /** 设置pageBean 的数据集合 */
            pb.setList(infoImageClassifyMapper.selectImageClassifyByParam(map));
            /** 返回结果 */
            return pb;
        } catch (Exception e) {
            LOGGER.error("分页查询图片管理分类列表错误:",e);
            /** 返回null */
        }
        /** 返回null */
        return null;
    }

    /**
     * 查询所有图片管理分类
     * 
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      selectAllImageClassify()
     */
    @Override
    public List<InfoImageClassify> selectAllImageClassify() {
        /**
         * 查询所有图片管理分类 分类管理选择父分类
         */
        return infoImageClassifyMapper.selectAllImageClassify();
    }

    public InfoImageClassifyMapper getInfoImageClassifyMapper() {
        return infoImageClassifyMapper;
    }

    @Resource(name = "InfoImageClassifyMapper")
    public void setInfoImageClassifyMapper(InfoImageClassifyMapper infoImageClassifyMapper) {
        this.infoImageClassifyMapper = infoImageClassifyMapper;
    }

    /**
     * 根据父分类查询子分类列表
     * 
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#selectByParentId(java.lang.Long)
     */
    @Override
    public List<InfoImageClassifyVo> selectByParentId(Long classifyId) {
        /*** 根据分类ID查询子分类 */
        return infoImageClassifyMapper.selectByParentId(classifyId);
    }

    /**
     * 查询所有图片管理分类 上传图片选择分类用
     * 
     * @see com.ningpai.imagemanage.service.InfoImageClassifyService#
     *      selectAllImageClassifyForImg()
     */
    @Override
    public List<InfoImageClassify> selectAllImageClassifyForImg() {
        /**
         * 查询所有图片管理分类 上传图片选择分类用
         */
        return infoImageClassifyMapper.selectAllImageClassifyForImg();
    }
}
