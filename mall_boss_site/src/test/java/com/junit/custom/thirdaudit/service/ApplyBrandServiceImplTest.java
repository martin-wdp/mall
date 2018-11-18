package com.junit.custom.thirdaudit.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.thirdaudit.bean.ApplyBrandVo;
import com.ningpai.thirdaudit.mapper.ApplyBrandMapper;
import com.ningpai.thirdaudit.service.ApplyBrandService;
import com.ningpai.thirdaudit.service.impl.ApplyBrandServiceImpl;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用品牌服务层接口实现类测试类
 * */
public class ApplyBrandServiceImplTest extends UnitilsJUnit3 {
    //测试类
    @TestedObject
   private ApplyBrandService applyBrandService=new ApplyBrandServiceImpl();
    //数据模拟
    @InjectIntoByType
    Mock<ApplyBrandMapper> applyBrandMapperMock;


    //测试数据
    List<ApplyBrandVo> applyBrands;
    @FileContent("applyBrand.js")
   private String  applyBrandJS;

    public void setUp() throws Exception {
        applyBrands= JSON.parseArray(applyBrandJS, ApplyBrandVo.class);

    }

    /**
     * 查询未审核列表
     * @throws Exception
     */
    @Test
    public void testQueryApplyBrand() throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("applyBrandName", applyBrands.get(0).getApplyBrandName());
        paramMap.put("storeName",  applyBrands.get(0).getStoreName());
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 15);
        applyBrandMapperMock.returns(1).queryApplyBrandCount(paramMap);
        PageBean pb  = new  PageBean();
        pb.setObjectBean(applyBrands.get(0));
        applyBrandMapperMock.returns(applyBrands).queryApplyBrand(paramMap);
        assertEquals(1, applyBrandService.queryApplyBrand(pb).getList().size());



    }
    /**
     * 修改审核状态
     *
     */

    public void testUpdateApplyBrand() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyStatus", "1");
        map.put("applyRefusalReason","1");
        map.put("applyBrandIds",new Long[]{1L});
        applyBrandMapperMock.returns(1).updateApplyBrand(map);
        assertEquals(1, applyBrandService.updateApplyBrand(new Long[]{1L},"1","1"));
    }

}