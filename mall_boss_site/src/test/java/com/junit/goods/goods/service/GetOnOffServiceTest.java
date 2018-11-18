package com.junit.goods.goods.service;

import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.goods.service.GetOnOffService;
import com.ningpai.goods.service.impl.GetOnOffServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

/**
 * 开关控制service
 * 
 * @author zhoux
 *
 */
public class GetOnOffServiceTest extends UnitilsJUnit3 {


    @TestedObject
    private GetOnOffService getOnOffService = new GetOnOffServiceImpl();

    @InjectIntoByType
    Mock<GetOnOffMapper> getOnOffMapperMock;

    @Override
    public void setUp(){

    }

    /**
     * 测试获取商品审核开关标记
     * 
     * @return
     */
    @Test
    public void testGetOnOffFlag(){
       getOnOffMapperMock.returns("").getOnOffFlag();
        assertNotNull(getOnOffService.getOnOffFlag());
    }

    /**
     * 改变开关状态
     * @return
     */
    public void  testUpdateOnOffFlag(){
        getOnOffMapperMock.returns(1).updateOnOffFlag("1");
        assertEquals(1,getOnOffService.updateOnOffFlag("1"));
    }

}
