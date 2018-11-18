/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.imagemanage.service.impl;

import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.dao.InfoImageManageMapper;
import com.ningpai.imagemanage.service.InfoImageManageService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * SERVICE实现类-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日下午1:52:56
 */
@Service("InfoImageManageService")
public class InfoImageManageServiceImpl implements InfoImageManageService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoImageManageServiceImpl.class);

    private static final String CLASSIFYID = "classifyId";
    private static final String THIRDID = "thirdId";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String LOGGERINFO1 = "分页查询图片信息列表错误:";

    /** 数据访问层依赖 */
    private InfoImageManageMapper ifoImageManageMapper;

    /**
     * 根据主键删除
     * 
     * @param imageManageId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#deleteInfoImageManage(java.lang.Long)
     */
    @Override
    public int deleteInfoImageManage(Long imageManageId) {
        try {
            /** 调用dao层方法并返回结果 */
            return ifoImageManageMapper.deleteByPrimaryKey(imageManageId);
        } catch (Exception e) {
            LOGGER.error("删除图片信息错误:", e);
            /** 返回0 */
            return 0;
        }
    }

    /**
     * 添加
     * 
     * @param record
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#saveInfoImageManage(com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int saveInfoImageManage(InfoImageManage record) {
        try {
            /** 获取当前时间 */
            Date date = new Date();
            /** 设置创建时间为当前时间 */
            record.setCreateDate(date);
            /** 设置更新时间为当前时间 */
            record.setUpdateDate(date);
            /** 返回结果 */
            return ifoImageManageMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("添加图片信息错误:", e);
            /** 返回0 */
            return 0;
        }
    }

    /**
     * 修改
     * 
     * @param record
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#updateInfoImageManage
     *      (com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int updateInfoImageManage(InfoImageManage record) {
        try {
            /** 设置更新时间为当前时间 */
            record.setUpdateDate(new Date());
            /** 返回结果 */
            return ifoImageManageMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改图片信息错误:", e);
            /** 返回0 */
            return 0;
        }
    }

    /**
     * 根据ID查询
     * 
     * @param imageManageId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#getInfoImageManageById
     *      (java.lang.Long)
     */
    @Override
    public InfoImageManage getInfoImageManageById(Long imageManageId) {
        /** 创建对象InfoImageManage */
        InfoImageManage infoImageManage = null;
        try {
            /** 为图片资源对象赋值 */
            infoImageManage = ifoImageManageMapper.selectByPrimaryKey(imageManageId);
        } catch (Exception e) {
            LOGGER.error("根据ID查询图片信息错误:", e);
        }
        /** 返回结果 */
        return infoImageManage;
    }

    /**
     * 根据图片分类查询图片信息总行数
     * 
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      selectImageManageCountByClassifyId(java.lang.Long)
     */
    @Override
    public Integer selectImageManageCountByClassifyId(Long classifyId) {
        try {
            /** 定义一个HashMap集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            /** classifyId存在map中 */
            map.put(CLASSIFYID, classifyId);
            /** 调用dao层返回结果 */
            return ifoImageManageMapper.selectImageManageCountByParam(map);
        } catch (Exception e) {
            LOGGER.error("根据类型ID查询图片信息数量错误:", e);
            /** 返回-1 */
            return -1;
        }
    }

    /**
     * 根据图片分类查询图片信息
     * 
     * @param startDate
     *            开始日期
     * @param endDate
     *            结束日期
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      selectImageManageByParam(com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectImageManageByParam(PageBean pb, Long classifyId, String startDate, String endDate, Long thirdId) {
        try {
            /** 设置pageBean每页显示8条 */
            pb.setPageSize(8);
            /** 定义HashMap集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            /** map中存放classifyId */
            map.put(CLASSIFYID, classifyId);
            /** map中存放startDate */
            map.put("startDate", startDate);
            /** map中存放endDate */
            map.put("endDate", endDate);
            /** map中存放thirdId */
            map.put(THIRDID, thirdId);
            /** 设置pageBean的行数 */
            pb.setRows(ifoImageManageMapper.selectImageManageCountByParam(map));
            /** map中存放startRowNum开始行数 */
            map.put(STARTROWNUM, pb.getStartRowNum());
            /** map中存放endRowNum结束行数 */
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 设置pageBean 的集合数据 */
            pb.setList(ifoImageManageMapper.selectImageManageByParam(map));
            /** 返回结果 */
            return pb;
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
            /** 返回null */
            return null;
        }
    }

    public InfoImageManageMapper getIfoImageManageMapper() {
        return ifoImageManageMapper;
    }

    @Resource(name = "InfoImageManageMapper")
    public void setIfoImageManageMapper(InfoImageManageMapper ifoImageManageMapper) {
        this.ifoImageManageMapper = ifoImageManageMapper;
    }

    /**
     * 根据第三方ID获取图片信息
     * 
     * @param pb
     * @param thirdId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      selectImageByThirdId(com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectImageByThirdId(PageBean pb, Long thirdId) {
        try {
            /** 定义一个HashMap集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            map.put(THIRDID, thirdId);
            /** 设置pageBean的行数 */
            pb.setRows(ifoImageManageMapper.selectImageManageCountByParam(map));
            map.put(STARTROWNUM, pb.getStartRowNum());
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 设置pageBean 的集合数据 */
            pb.setList(ifoImageManageMapper.selectImageManageByParam(map));
            /** 返回结果 */
            return pb;
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
            /** 返回null */
            return null;
        }
    }

    /**
     * 根据地单方ID和图片编号删除
     * 
     * @param imageManageId
     * @param thirdId
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      updateImage(java.lang.Long, java.lang.Long)
     */
    @Override
    public Integer updateImage(Long imageManageId, Long thirdId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> param = new HashMap<String, Object>();
        /** 将imageManageId存到map中 */
        param.put("imageManageId", imageManageId);
        /** 将thirdId存到map中 */
        param.put(THIRDID, thirdId);
        /** 获取更新结果 count>0代表更新成功 */
        return ifoImageManageMapper.updateImage(param);
    }

    /**
     * 批量删除
     * 
     * @param imageManageIds
     * @param thirdId
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      updateImages(java.lang.Long, java.lang.Long)
     */
    @Override
    public void updateImages(Long[] imageManageIds, Long thirdId) {
        /** 定义一个ArrayList集合 */
        List<Long> imageIds = new ArrayList<Long>();
        /** 循环imageManageIds数组为list赋值 */
        for (int i = 0; i < imageManageIds.length; i++) {
            imageIds.add(imageManageIds[i]);
        }
        try {
            /** 定义一个HashMap集合 */
            Map<String, Object> param = new HashMap<String, Object>();
            /** 将imageId存到map中 */
            param.put("imageId", imageIds);
            /** 将thirdId存到map中 */
            param.put(THIRDID, thirdId);
            /** 根据参数进行更新 */
            ifoImageManageMapper.updateImages(param);
        } finally {
            imageIds = null;
        }
    }

    /**
     * 根据分类和第三方Id查询图片
     * 
     * @param pb
     * @param classifyId
     * @param thirdId
     * @return
     * @see com.ningpai.imagemanage.service.InfoImageManageService#
     *      updateImages(com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectImageManageByParam(PageBean pb, Long classifyId, Long thirdId) {
        try {
            /** 定义一个HashMao集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            /** 将classifyId存到map中 */
            map.put(CLASSIFYID, classifyId);
            /** 将thirdId存到map中 */
            map.put(THIRDID, thirdId);
            /** 设置oageBean的行数 */
            pb.setRows(ifoImageManageMapper.selectImageManageCountByParam(map));
            /** 将startRowNum存到map中 */
            map.put(STARTROWNUM, pb.getStartRowNum());
            /** 将endRowNum存到map中 */
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 设置PageBean的集合数据 */
            pb.setList(ifoImageManageMapper.selectImageManageByParam(map));
            /** 返回结果 */
            return pb;
        } catch (Exception e) {
            LOGGER.error(LOGGERINFO1, e);
            /** 返回null */
            return null;
        }
    }

}
