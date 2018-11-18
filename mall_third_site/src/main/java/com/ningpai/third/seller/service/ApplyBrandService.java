package com.ningpai.third.seller.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ningpai.third.seller.bean.ApplyBrand;
import com.ningpai.util.PageBean;

/**
 * 申请品牌
 * 
 * @author ggn
 * 
 */
public interface ApplyBrandService {
    /**
     * 获取自定义品牌的集合
     * @param attribute
     * @return
     */
    List<ApplyBrand> selectApplyBrand(Long attribute);

    /**
     * 删除
     * 
     * @param applyBrandId
     * @return int
     */
    int delApplyBrand(Long applyBrandId, Long thirdId);


    /**
     * 添加
     * 
     * @param request
     * @param applyBrand
     * @return ApplyBrand
     */
    ApplyBrand addApplyBrand(MultipartHttpServletRequest request, ApplyBrand applyBrand);

    /**
     * 查询自定义品牌列表
     * @param pb
     * @param thirdId
     * @return
     */
    PageBean queryApplyBrand(PageBean pb,Long thirdId);
    
    /**
     * 批量删除
     * @param applyBrandIds
     * @return
     */
    int delApplyBrands(Long[] applyBrandIds,Long thirdId);
}
