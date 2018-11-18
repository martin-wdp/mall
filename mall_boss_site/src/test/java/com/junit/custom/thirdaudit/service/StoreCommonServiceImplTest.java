package com.junit.custom.thirdaudit.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.thirdaudit.bean.*;
import com.ningpai.thirdaudit.mapper.StoreCommonMapper;
import com.ningpai.thirdaudit.service.StoreCommonSerivce;
import com.ningpai.thirdaudit.service.impl.StoreCommonServiceImpl;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreCommonServiceImplTest extends UnitilsJUnit3 {
    //测试类
    @TestedObject
    StoreCommonSerivce storeCommonService=new StoreCommonServiceImpl();
    @InjectIntoByType
    Mock<StoreCommonMapper> storeCommonMapperMock;

    List<GoodsCateGory> goodsCateGoryList;
    @FileContent("goodsCateGoryList.js")
    private String goodsCateGoryListJS;
    List<GoodsBrand> goodsBrandList;
    @FileContent("goodsBrandList.js")
    private String goodsBrandListJS;
    List<ApplyBrand> applyBrandList;
    @FileContent("applyBrand.js")
    private String applyBrandListJS;

    //测试数据
    List<StoreInfo> storeInfoList;
    @FileContent("storeInfoList.js")
    private String storeInfoListJS;

    public void setUp(){
        goodsCateGoryList= JSON.parseArray(goodsCateGoryListJS, GoodsCateGory.class);
        goodsBrandList= JSON.parseArray(goodsBrandListJS, GoodsBrand.class);
        applyBrandList= JSON.parseArray(applyBrandListJS, ApplyBrand.class);
        storeInfoList= JSON.parseArray(storeInfoListJS, StoreInfo.class);
    }
/**
 * 查询签约分类
 */
 public void testSelectThirdCate() {
     storeCommonMapperMock.returns(goodsCateGoryList).selectThirdCate(1L);
     assertEquals(1,storeCommonService.selectThirdCate(1L).size());
    }
    /**
     * 查询签约品牌
     */
    public void testSelectThirdBrand(){
        storeCommonMapperMock.returns(goodsBrandList).selectThirdBrand(1L);
        assertEquals(1,storeCommonService.selectThirdBrand(1L).size());
    }
    /**
     * 查询申请品牌
     */
    public void testSelectApplyBrand(){
        storeCommonMapperMock.returns(applyBrandList).selectApplyBrandByBrandId(1L);
        assertEquals(1,storeCommonService.selectApplyBrand(1L).size());
    }
/**
 * 批量把申请品牌变成真正的品牌
 */
    public void testApplyBrandToTrueBrand(){
        storeCommonMapperMock.returns(applyBrandList).selectApplyBrandByBrandId(1L);
        GoodsBrand gb = new GoodsBrand();
        gb.setBrandCreateName("Boss");
        gb.setBrandDelflag("0");
        gb.setBrandDelName("");
        gb.setBrandDelTime(new Date());
        gb.setBrandDesc(applyBrandList.get(0).getApplyBrandName());
        gb.setBrandLogo(applyBrandList.get(0).getApplyBrandPic());
        gb.setBrandName(applyBrandList.get(0).getApplyBrandName());
        gb.setBrandNickname(applyBrandList.get(0).getApplyBrandName());
        gb.setBrandSort(1);
        gb.setBrandUrl(applyBrandList.get(0).getApplyUrl());
        storeCommonMapperMock.returns(1).insertTrueBrand(gb);
        storeCommonMapperMock.returns(1L).selectLastBrandId();
        GrandBrand tgb = new GrandBrand();
        tgb.setBrandId(gb.getBrandId());
        tgb.setDelFlag("0");
        tgb.setModifyTime(new Date());
        tgb.setRateStatus("1");
        tgb.setRateTime(new Date());
        tgb.setThirdId(applyBrandList.get(0).getApplyThirdId());
        storeCommonMapperMock.returns(1L).insertGrandBrand(tgb);
        assertEquals(0,storeCommonService.applyBrandToTrueBrand(new String[]{"1"}));

    }
/**
 * 查询价格和上架和数量
 */
    public void testSelectModelPrice(){
        storeCommonMapperMock.returns(storeInfoList.get(0)).selectModelPrice(1L);
        assertNotNull(storeCommonService.selectModelPrice(1L));
    }

/**
 * 修改价格模型
 */
    public void testUpdateStorePrice(){
        storeCommonMapperMock.returns(1).updateStorePrice(storeInfoList.get(0));
        assertEquals(1,storeCommonService.updateStorePrice(storeInfoList.get(0)));
    }
    /**
     * 修改签约分类
     */
    public void testUpdateThridCate(){
        storeCommonService.updateThridCate(1L,new Long[]{1L});
    }
    /**
     * 删除
     */
    public void testDeleteSellerinfoCate(){
        storeCommonService.deleteSellerinfoCate(1L,1L);
    }

    /**
     * 根据店铺Id查询店铺的签约分类
     */
    public void testNewselectThirdCate(){
        storeCommonMapperMock.returns(1).newselectThirdCateCount(1L);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("storeId", 1L);
        paramMap.put("startRowNum",0);
        paramMap.put("endRowNum",15);
       storeCommonMapperMock.returns(goodsCateGoryList).newselectThirdCate(paramMap);
        assertEquals(1,storeCommonService.newselectThirdCate(1L,new PageBean()).getList().size());
    }
/**
 * 修改自定义品牌审核状态
 */
    public void testUpdateAppStatus(){
        storeCommonMapperMock.returns(1).updateAppStatus(1L);
        assertEquals(1,storeCommonService.updateAppStatus(1L));
    }
}