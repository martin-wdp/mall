package com.ningpai.thirdaudit.service;

import com.ningpai.thirdaudit.bean.ApplyBrand;
import com.ningpai.thirdaudit.bean.GoodsBrand;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 商家常见服务层接口
 *
 * */
public interface StoreCommonSerivce {

    /**
     * 查询签约分类
     * 
     * @param storeId
     * @return List
     */
    List<GoodsCateGory> selectThirdCate(Long storeId);

    /**
     * 查询签约品牌
     * 
     * @param storeId
     * @return List
     */
    List<GoodsBrand> selectThirdBrand(Long storeId);

    /**
     * 查询申请品牌
     * 
     * @param storeId
     * @return List
     */
    List<ApplyBrand> selectApplyBrand(Long storeId);

    /**
     * 查询申请品牌
     *
     * @param storeId
     * @return List
     */
    List<ApplyBrand> selectApplyBrandbyStoreId(Long storeId);

    /**
     * 批量把申请品牌变成真正的品牌
     * 
     * @param split
     * @return int
     */
    int applyBrandToTrueBrand(String[] split);

    /**
     * 查询价格和上架和数量
     * 
     * @param storeId
     * @return StoreInfo
     */
    StoreInfo selectModelPrice(Long storeId);

    /**
     * 修改价格模型
     * 
     * @param storeInfo
     * @return int
     */
    int updateStorePrice(StoreInfo storeInfo);

    /**
     * 修改签约分类
     * 
     * @param storeId
     * @param thirdCateId
     */
    void updateThridCate(Long storeId, Long[] thirdCateId);

    /**
     * 删除
     * 
     * @param thirdId
     * @param cateId
     */
    void deleteSellerinfoCate(Long thirdId, Long cateId);

    /**
     * 根据店铺Id查询店铺的签约分类
     * 
     * @param storeId
     *            店铺Id{@link java.lang.Long}
     * @param pb
     *            分页
     * @return 签约分类分页列表
     */
    PageBean newselectThirdCate(Long storeId, PageBean pb);

    /**
     * 修改自定义品牌审核状态
     * 
     * @param storeId
     * @return
     */
    int updateAppStatus(Long storeId);
}
