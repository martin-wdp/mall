package com.ningpai.imagemanage.service;

import java.util.List;

import com.ningpai.imagemanage.bean.InfoImageClassify;
import com.ningpai.imagemanage.vo.InfoImageClassifyVo;
import com.ningpai.util.PageBean;

/**
 * SERVICE-资源图片类型
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:28:45
 */
public interface InfoImageClassifyService {
    /**
     * 根据ID删除
     * 
     * @param classifyId
     * @return
     */
    int deleteInfoImageClassify(Long classifyId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveInfoImageClassify(InfoImageClassify record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateInfoImageClassify(InfoImageClassify record);

    /**
     * 根据主键查询
     * 
     * @param classifyId
     * @return
     */
    InfoImageClassify getInfoImageClassifyById(Long classifyId);

    /**
     * 查询图片管理分类
     * 
     * @param pb
     * @return
     */
    PageBean selectImageClassifyByParam(PageBean pb);

    /**
     * 查询所有图片管理分类
     * 
     * @return
     */
    List<InfoImageClassify> selectAllImageClassify();

    /**
     * 根据父分类查询子分类列表
     * 
     * @param classifyId
     * @return
     */
    List<InfoImageClassifyVo> selectByParentId(Long classifyId);

    /**
     * 查询所有图片管理分类<br/>
     * 上传图片选择分类用
     * 
     * @return
     */
    List<InfoImageClassify> selectAllImageClassifyForImg();
}
