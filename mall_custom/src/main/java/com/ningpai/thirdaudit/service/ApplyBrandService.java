package com.ningpai.thirdaudit.service;

import com.ningpai.util.PageBean;

/**
 * 应用品牌服务层接口
 * */
public interface ApplyBrandService {
    /**
     * 查询未审核列表
     * 
     * @param pb
     * @return
     */
    PageBean queryApplyBrand(PageBean pb);

    /**
     * 修改审核状态
     * 
     * @param
     * @return
     */
    int updateApplyBrand(Long[] applyBrandIds, String reason,
            String applyStatuts);
}
