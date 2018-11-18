package com.junit.third.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.goods.dao.ThirdWarnGoodMapper;
import com.ningpai.third.goods.service.ThirdWarnGoodService;
import com.ningpai.third.goods.service.impl.ThirdWarnGoodServiceImpl;
import com.ningpai.third.goods.vo.StockWarningVo;
import com.ningpai.util.PageBean;

/**
 * 货品预警测试 
 * @author djk
 *
 */
public class ThirdWarnGoodServiceTest extends UnitilsJUnit3
{
    /**
     * 需要测试的Service
     */
    @TestedObject
    private ThirdWarnGoodService thirdWarnGoodService = new ThirdWarnGoodServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<ThirdWarnGoodMapper> thirdWarnGoodMapperMock;
    
    /**
     * 库存警告的实体类
     */
    private StockWarningVo stockWarningVo = new StockWarningVo();
    
    /**
     * 查询第三方库存预警值测试
     */
    @Test
    public void testSelectstock()
    {
    	thirdWarnGoodMapperMock.returns(stockWarningVo).selectstock(1L);
    	assertNotNull(thirdWarnGoodService.selectstock(1L));
    }
    
    /**
     * 更新库存下限测试
     */
    @Test
    public void testUpdatestockgoods()
    {
    	thirdWarnGoodMapperMock.returns(1).updatestockgoods(stockWarningVo);
    	assertEquals(1, thirdWarnGoodService.updatestockgoods(stockWarningVo));
    }
    
    /**
     * 查询第三方预警货品测试
     */
    @Test
    public void testSelectwarngoods()
    {
    	PageBean pageBean = new PageBean();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        paramMap.put("infoid", stockWarningVo.getInfoid());
        paramMap.put("goodname", stockWarningVo.getGoodname());
        paramMap.put("number", stockWarningVo.getNumber());
        paramMap.put("salprice", stockWarningVo.getSalprice());
        paramMap.put("stock", stockWarningVo.getStock());
        paramMap.put("buyprice", stockWarningVo.getBuyprice());
        paramMap.put("thirdid", stockWarningVo.getThirdid());
        thirdWarnGoodMapperMock.returns(1).selectwarncount(paramMap);
        List<Object> lists = new ArrayList<>();
        lists.add(new Object());
        thirdWarnGoodMapperMock.returns(lists).selectwarngoods(paramMap);
    	assertEquals(1, thirdWarnGoodService.selectwarngoods(stockWarningVo, pageBean).getList().size());
    }
    
    /**
     * 更新库存测试
     */
    @Test
    public void testUpdatewarnstock()
    {
    	thirdWarnGoodMapperMock.returns(1).updatewarnstock(stockWarningVo);
    	assertEquals(1, thirdWarnGoodService.updatewarnstock(stockWarningVo));
    }
}
