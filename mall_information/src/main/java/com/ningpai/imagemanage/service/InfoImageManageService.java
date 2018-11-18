package com.ningpai.imagemanage.service;

import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.util.PageBean;

/**
 * SERVICE-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:34:55
 */
public interface InfoImageManageService {
    /**
     * 根据主键删除
     * 
     * @param imageManageId
     * @return
     */
    int deleteInfoImageManage(Long imageManageId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveInfoImageManage(InfoImageManage record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateInfoImageManage(InfoImageManage record);

    /**
     * 根据ID查询
     * 
     * @param imageManageId
     * @return
     */
    InfoImageManage getInfoImageManageById(Long imageManageId);

    /**
     * 根据图片分类查询图片信息总行数
     * @return
     */
    Integer selectImageManageCountByClassifyId(Long classifyId);

    /**
     * 根据图片分类查询图片信息
     *
     * @param startDate 开始日期
     *@param endDate 借宿日期
     * @return
     */
    PageBean selectImageManageByParam(PageBean pb, Long classifyId, String startDate, String endDate,Long thirdId);

    /**
     * 根据第三方ID获取图片信息
     * @param pb
     * @param thirdId
     * @return
     */
    PageBean selectImageByThirdId(PageBean pb, Long thirdId);

    /**
     * 根据地单方ID和图片编号删除
     * @param imageManageId
     * @param thirdId
     */
    Integer updateImage(Long imageManageId, Long thirdId);

    /**
     * 批量删除
     * @param imageManageIds
     * @param thirdId
     */
    void updateImages(Long[] imageManageIds, Long thirdId);

    /**
     * 根据分类和第三方Id查询图片
     * @param pb
     * @param classifyId
     * @param thirdId
     * @return
     */
    PageBean selectImageManageByParam(PageBean pb, Long classifyId, Long thirdId);
}
