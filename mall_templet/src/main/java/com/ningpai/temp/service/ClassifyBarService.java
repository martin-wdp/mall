/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.service;

import java.util.List;

import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.vo.ClassifyBarVo;
import com.ningpai.util.PageBean;

/**
 * SERVICE-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:10:00
 */
public interface ClassifyBarService {
    /**
     * 删除分类导航
     * 
     * @param classifyBarId
     * @return
     */
    int deleteClassifyBar(Long classifyBarId);

    /**
     * 添加分类导航
     * 
     * @param record
     * @return
     */
    int saveClassifyBar(ClassifyBar record);

    /**
     * 修改分类导航
     * 
     * @param record
     * @return
     */
    int updateClassifyBar(ClassifyBar record);

    /**
     * 根据主键查询分类导航
     * 
     * @param classifyBarId
     * @return
     */
    ClassifyBar getClassifyBarById(Long classifyBarId);

    /**
     * 根据分页参数和频道ID、模板ID查询分类导航
     * 
     * @param pb
     *            分页参数
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return
     */
    PageBean selectClassifyBarByParam(PageBean pb, Long tempId, Long channelId, String thirdId);

    /**
     * 根据频道ID、模板ID查询分类导航
     * 
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return
     */
    List<ClassifyBarVo> selectClassifyBarByParamSite(Long tempId, Long channelId, String thirdId);

    /**
     * 根据频道ID、模板ID查询分类导航
     *
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return
     */
    List<ClassifyBarVo> selectClassifyBarByParamSite2(Long tempId, Long channelId, String thirdId);

    /**
     * 根据频道ID、模板ID查询分类导航
     *
     * @param tempId
     *            模板ID
     * @return
     */
    List<ClassifyBarVo> getIndexClassificationByfir(Long tempId);

    /**
     * 添加分类导航，并添加关联的商品分类和快捷分类
     * 
     * @param record
     *            分类导航
     * @param barCate
     *            分类导航关联商品分类
     * @param barQuick
     *            分类导航关联快捷分类
     * @return
     */
    int saveClassifyBarAndCateAndQuick(ClassifyBar record, List<ClassifyBarCate> barCates, List<ClassifyBarQuick> barQuicks);

    /**
     * 修改分类导航，并删除原关联的商品分类和快捷分类重新添加
     * 
     * @param record
     *            分类导航
     * @param barCate
     *            分类导航关联商品分类
     * @param barQuick
     *            分类导航关联快捷分类
     * @return
     */
    int updateClassifyBarAndCateAndQuick(ClassifyBar record, List<ClassifyBarCate> barCates, List<ClassifyBarQuick> barQuicks);

    /**
     * 删除分类导航，并删除原关联的商品分类和快捷分类重新添加
     * 
     * @param record
     *            分类导航
     * @param barCate
     *            分类导航关联商品分类
     * @param barQuick
     *            分类导航关联快捷分类
     * @return
     */
    int deleteClassifyBarAndCateAndQuick(Long classifyBarId);

    /**
     * 调用存储过程级联删除分类导航
     * 
     * @param classifyBarId
     * @return
     */
    int deleteByPrimaryKeyAndPro(Long classifyBarId);
    
    /**
     * 删除店铺分类导航
     * @param classBarId 分类导航id {@link java.lang.Long}
     * @param thirdId 店铺id
     * @return
     */
    int deleteClassBarById(Long classBarId,Long thirdId);
    
}
