package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.thirdaudit.bean.ApplyBrand;
import com.ningpai.thirdaudit.bean.GoodsBrand;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.thirdaudit.bean.GrandBrand;
import com.ningpai.thirdaudit.bean.StoreInfo;
/**
 * 商家签约接口
 * */
public interface StoreCommonMapper {

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
     * 根据商户ID查询用户Id
     * 
     * @param storeId
     * @return Long
     */
    Long selectCustomerIdByThirdId(Long storeId);

    /**
     * 查询申请品牌
     * 
     * @param customerId
     * @return List
     */
    List<ApplyBrand> selectApplyBrand(Long customerId);

    /**
     * 根据品牌id查询申请品牌
     * 
     * @param brandId
     * @return List
     */
    List<ApplyBrand> selectApplyBrandByBrandId(Long brandId);

    /**
     * 插入真正的品牌库
     * 
     * @param gb
     * @return int
     */
    int insertTrueBrand(GoodsBrand gb);

    /**
     * 查询刚刚插入的品牌Id
     * 
     * @return Long
     */
    Long selectLastBrandId();

    /**
     * 插入绑定表
     * 
     * @param tgb
     * @return int
     */
    int insertGrandBrand(GrandBrand tgb);

    /**
     * 查询商家建模信息
     * 
     * @param thirdId
     * @return StoreInfo
     */
    StoreInfo selectModelPrice(Long thirdId);

    /**
     * 修改建模信息
     * 
     * @param storeInfo
     * @return int
     */
    int updateStorePrice(StoreInfo storeInfo);

    /**
     * 修改分类
     * 
     * @param paramMap
     */
    void updateThridCate(Map<String, Object> paramMap);

    /**
     * 删除分类
     * 
     * @param paramMap
     */
    void deleteSellerinfoCate(Map<String, Object> paramMap);

    /**
     * 新根据店铺Id查询签约分类数目
     * 
     * @param storeId
     *            店铺Id{@link java.lang.Long}
     * @return 查询的数目
     */
    int newselectThirdCateCount(Long storeId);

    /**
     * 新根据店铺Id查询签约分类数目
     * 
     * @param paramMap
     *            查询条件参数
     * @return List
     */
    List<Object> newselectThirdCate(Map<String, Object> paramMap);

    /**
     * 删除签约品牌
     * 
     * @param storeId
     */
    void delThirdbrandBystoreId(Long storeId);

    /**
     * 修改自定义品牌
     * 
     * @param storeId
     * @return
     */
    int updateAppStatus(Long storeId);
}
