package com.ningpai.thirdaudit.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.thirdaudit.bean.ApplyBrand;
import com.ningpai.thirdaudit.bean.GoodsBrand;
import com.ningpai.thirdaudit.bean.GoodsCateGory;
import com.ningpai.thirdaudit.bean.GrandBrand;
import com.ningpai.thirdaudit.bean.StoreInfo;
import com.ningpai.thirdaudit.mapper.StoreCommonMapper;
import com.ningpai.thirdaudit.service.StoreCommonSerivce;
import com.ningpai.util.PageBean;

/**
 * 商家常见服务层接口实现类
 *
 * */
@Service("StoreCommonSerivce")
public class StoreCommonServiceImpl implements StoreCommonSerivce {

    @Resource(name = "StoreCommonMapper")
    private StoreCommonMapper storeCommonMapper;

    /**
     * 查询签约分类
     *
     * @param storeId
     * @return List
     */
    @Override
    public List<GoodsCateGory> selectThirdCate(Long storeId) {
        //
        return storeCommonMapper.selectThirdCate(storeId);
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
        return storeCommonMapper.selectThirdBrand(storeId);
    }

    /**
     * 查询申请品牌
     *
     * @param brandId
     * @return List
     */
    @Override
    public List<ApplyBrand> selectApplyBrand(Long brandId) {
        //
        // Long customerId =
        // storeCommonMapper.selectCustomerIdByThirdId(storeId);
        return storeCommonMapper.selectApplyBrandByBrandId(brandId);
    }

    /**
     * 查询申请品牌
     *
     * @param storeId
     * @return List
     */
    @Override
    public List<ApplyBrand> selectApplyBrandbyStoreId(Long storeId) {
        return storeCommonMapper.selectApplyBrand(storeId);
    }

    /**
     * 批量把申请品牌变成真正的品牌
     *
     * @param split
     * @return int
     */
    @Override
    public int applyBrandToTrueBrand(String[] split) {
        //
        if (split != null && split.length != 0) {
            for (String applybrandId : split) {
                List<ApplyBrand> brandlist = selectApplyBrand(Long.valueOf(applybrandId));
                if (brandlist != null && !brandlist.isEmpty()) {
                    for (ApplyBrand ab : brandlist) {
                        // 插入真实品牌库
                        GoodsBrand gb = new GoodsBrand();
                        gb.setBrandCreateName("Boss");
                        gb.setBrandDelflag("0");
                        gb.setBrandDelName("");
                        gb.setBrandDelTime(new Date());
                        gb.setBrandDesc(ab.getApplyBrandName());
                        gb.setBrandLogo(ab.getApplyBrandPic());
                        gb.setBrandName(ab.getApplyBrandName());
                        gb.setBrandNickname(ab.getApplyBrandName());
                        gb.setBrandSort(1);
                        gb.setBrandUrl(ab.getApplyUrl());
                        storeCommonMapper.insertTrueBrand(gb);
                        Long brandId = storeCommonMapper.selectLastBrandId();
                        // 插入绑定库
                        GrandBrand tgb = new GrandBrand();
                        tgb.setBrandId(brandId);
                        tgb.setDelFlag("0");
                        tgb.setModifyTime(new Date());
                        tgb.setRateStatus("1");
                        tgb.setRateTime(new Date());
                        tgb.setThirdId(ab.getApplyThirdId());
                        storeCommonMapper.insertGrandBrand(tgb);
                    }
                }

            }
        }
        return 0;
    }

    /**
     * 查询价格和上架和数量
     *
     * @param storeId
     * @return StoreInfo
     */
    @Override
    public StoreInfo selectModelPrice(Long storeId) {
        //
        return storeCommonMapper.selectModelPrice(storeId);
    }

    /**
     * 修改价格模型
     *
     * @param storeInfo
     * @return int
     */
    @Override
    public int updateStorePrice(StoreInfo storeInfo) {
        //
        return storeCommonMapper.updateStorePrice(storeInfo);
    }

    /**
     * 修改签约分类
     *
     * @param storeId
     * @param thirdCateId
     */
    @Override
    public void updateThridCate(Long storeId, Long[] thirdCateId) {
        //
        if (thirdCateId != null && thirdCateId.length != 0) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("cateIds", thirdCateId);
            paramMap.put("thirdId", storeId);
            storeCommonMapper.updateThridCate(paramMap);
        }

    }

    /**
     * 删除
     *
     * @param thirdId
     * @param cateId
     */
    @Override
    public void deleteSellerinfoCate(Long thirdId, Long cateId) {
        //
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("thirdId", thirdId);
        paramMap.put("cateId", cateId);
        storeCommonMapper.deleteSellerinfoCate(paramMap);
    }

    /**
     * 根据店铺Id查询店铺的签约分类
     *
     * @param storeId
     *            店铺Id{@link java.lang.Long}
     * @param pb
     *            分页
     * @return 签约分类分页列表
     */
    @Override
    public PageBean newselectThirdCate(Long storeId, PageBean pb) {
        // 根据店铺ID查询签约分类数目
        int rows = storeCommonMapper.newselectThirdCateCount(storeId);
        if (rows > 0) {
            // 如果查询数目大于0
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeId", storeId);
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(storeCommonMapper.newselectThirdCate(paramMap));
        return pb;
    }

    /**
     * 修改自定义品牌审核状态
     *
     * @param storeId
     * @return
     */
    @Override
    public int updateAppStatus(Long storeId) {
        return storeCommonMapper.updateAppStatus(storeId);
    }

}
