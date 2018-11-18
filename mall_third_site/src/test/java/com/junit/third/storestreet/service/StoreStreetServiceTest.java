package com.junit.third.storestreet.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.storestreet.bean.StoreStreetThirdImage;
import com.ningpai.third.storestreet.mapper.StoreStreetMapper;
import com.ningpai.third.storestreet.service.StoreStreetService;
import com.ningpai.third.storestreet.service.impl.StoreStreetServiceImpl;

public class StoreStreetServiceTest  extends UnitilsJUnit3
{
	/**
     * 需要测试的Service
     */
    @TestedObject
    private StoreStreetService storeStreetService = new StoreStreetServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<StoreStreetMapper> storeStreetMapperMock;
    
    /**
     * 店铺街图片实体类
     */
    private StoreStreetThirdImage storeStreetThirdImage = new StoreStreetThirdImage();
    
    /**
     * 批量改变店铺街图片的信息测试
     */
    @Test
    public void testUpdateImages()
    {
    	Long[] imageId = {1L};
    	storeStreetService.updateImages(imageId, 1L);
    }
    
    /**
     * 根据ID获取图片详信息测试
     */
    @Test
    public void testSelectStoreStreetImageById()
    {
    	storeStreetMapperMock.returns(storeStreetThirdImage).selectStoreStreetImageById(1L);
    	assertNotNull(storeStreetService.selectStoreStreetImageById(1L));
    }
    
    /**
     * 保存店铺街图片测试
     */
    @Test
    public void testUpdateStoreStreetImage()
    {
    	storeStreetMapperMock.returns(1).updateStoreStreetImage(storeStreetThirdImage);
    	assertEquals(1, storeStreetService.updateStoreStreetImage(storeStreetThirdImage));
    }
    
    /**
     * 保存店铺街图片测试
     */
    @Test
    public void testSaveStoreStreetImage()
    {
    	storeStreetMapperMock.returns(1).saveStoreStreetImage(storeStreetThirdImage);
    	assertEquals(1, storeStreetService.saveStoreStreetImage(storeStreetThirdImage));
    }
    
    /**
     * 查询该店铺下面的店铺街展示广告信息测试
     */
    @Test
    public void testSelectStoreStreetListImage()
    {
    	 List<Object>  lists = new ArrayList<>();
    	 lists.add(new Object());
    	 storeStreetMapperMock.returns(lists).selectStoreStreetListImage(1L);
    	 assertEquals(1, storeStreetService.selectStoreStreetListImage(1L).size());
    }
}
