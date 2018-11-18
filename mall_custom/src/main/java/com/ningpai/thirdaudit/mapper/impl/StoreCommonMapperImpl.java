package com.ningpai.thirdaudit.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.bean.ApplyBrand;
import com.ningpai.thirdaudit.bean.GoodsBrand;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.thirdaudit.bean.GrandBrand;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreCommonMapper;
/**
 * 商家签约接口实现类
 * */
@Repository("StoreCommonMapper")
public class StoreCommonMapperImpl extends BasicSqlSupport implements
        StoreCommonMapper {

    /**
     * 查询签约分类
     *
     * @param storeId
     * @return List
     */
    @Override
    public List<GoodsCateGory> selectThirdCate(Long storeId) {
        //
        return this.selectList(
                "com.ningpai.web.dao.GoodsCateGoryMapper.selectThirdCate",
                storeId);
    }

    /**
     * 查询签约品牌
     *
     * @param storeId
     * @return List
     */
    @Override
    public List<GoodsBrand> selectThirdBrand(Long storeId) {
        //
        return this.selectList(
                "com.ningpai.web.dao.GoodsBrandMapper.selectThirdBrand",
                storeId);
    }

    /**
     * 根据商户ID查询用户Id
     *
     * @param storeId
     * @return Long
     */
    @Override
    public Long selectCustomerIdByThirdId(Long storeId) {
        //
        return this
                .selectOne(
                        "com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectCustomerIdByThirdId",
                        storeId);
    }

    /**
     * 查询申请品牌
     *
     * @param customerId
     * @return List
     */
    @Override
    public List<ApplyBrand> selectApplyBrand(Long applyThridId) {
        //
        return this.selectList(
                "com.ningpai.web.dao.ApplyBrandMapper.selectApplyBrand",
                applyThridId);
    }

    /**
     * 插入真正的品牌库
     *
     * @param gb
     * @return int
     */
    @Override
    public int insertTrueBrand(GoodsBrand gb) {
        //
        return this.insert(
                "com.ningpai.web.dao.GoodsBrandMapper.insertSelective", gb);
    }

    /**
     * 查询刚刚插入的品牌Id
     *
     * @return Long
     */
    @Override
    public Long selectLastBrandId() {
        //
        return this
                .selectOne("com.ningpai.web.dao.GoodsBrandMapper.selectLastBrandId");
    }

    /**
     * 插入绑定表
     *
     * @param tgb
     * @return int
     */
    @Override
    public int insertGrandBrand(GrandBrand tgb) {
        //
        return this.insert(
                "com.ningpai.web.dao.GrandBrandMapper.insertGrandBrand", tgb);
    }

    /**
     * 查询商家建模信息
     *
     * @param thirdId
     * @return StoreInfo
     */
    @Override
    public StoreInfo selectModelPrice(Long storeId) {
        //
        return this
                .selectOne(
                        "com.ningpai.thirdaudit.mapper.StoreInfoMapper.selectByStoreId",
                        storeId);
    }

    /**
     * 修改建模信息
     *
     * @param storeInfo
     * @return int
     */
    @Override
    public int updateStorePrice(StoreInfo storeInfo) {
        //
        return this
                .update("com.ningpai.thirdaudit.mapper.StoreInfoMapper.updateStoreInfo",
                        storeInfo);
    }

    /**
     * 修改分类
     *
     * @param paramMap
     */
    @Override
    public void updateThridCate(Map<String, Object> paramMap) {
        //
        this.insert("com.ningpai.web.dao.GoodsCateGoryMapper.updateThridCate",
                paramMap);
    }

    /**
     * 删除分类
     *
     * @param paramMap
     */
    @Override
    public void deleteSellerinfoCate(Map<String, Object> paramMap) {
        //
        this.update(
                "com.ningpai.web.dao.GoodsCateGoryMapper.deleteSellerinfoCate",
                paramMap);
    }

    /**
     * 新根据店铺Id查询签约分类数目
     *
     * @param storeId
     *            店铺Id{@link java.lang.Long}
     * @return 查询的数目
     */
    @Override
    public int newselectThirdCateCount(Long storeId) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.GoodsCateGoryMapper.newselectThirdCateCount",
                        storeId);
    }

    /**
     * 新根据店铺Id查询签约分类数目
     *
     * @param paramMap
     *            查询条件参数
     * @return List
     */
    @Override
    public List<Object> newselectThirdCate(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.GoodsCateGoryMapper.newselectThirdCate",
                paramMap);
    }

    /**
     * 删除签约品牌
     *
     * @param storeId
     */
    @Override
    public void delThirdbrandBystoreId(Long storeId) {
        this.update(
                "com.ningpai.web.dao.GoodsBrandMapper.delThirdbrandBystoreId",
                storeId);
    }
    /**
     * 修改自定义品牌
     *
     * @param
     * @return
     */
    @Override
    public int updateAppStatus(Long applyThridId) {
        return this.update(
                "com.ningpai.web.dao.ApplyBrandMapper.updateAppStatus",
                applyThridId);
    }
    /**
     * 根据品牌id查询申请品牌
     *
     * @param brandId
     * @return List
     */
    @Override
    public List<ApplyBrand> selectApplyBrandByBrandId(Long brandId) {
        return this.selectList(
                "com.ningpai.web.dao.ApplyBrandMapper.selectApplyBrandById",
                brandId);
    }

}
