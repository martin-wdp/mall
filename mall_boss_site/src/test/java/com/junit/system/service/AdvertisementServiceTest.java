package com.junit.system.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.system.bean.Advertisement;
import com.ningpai.system.dao.AdvertisementMapper;
import com.ningpai.system.service.AdvertisementService;
import com.ningpai.system.service.impl.AdvertisementServiceImpl;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 首页广告设置服务层接口测试类
 * @author djk
 *
 */
public class AdvertisementServiceTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口类
	 */
	@TestedObject
	private AdvertisementService advertisementService = new AdvertisementServiceImpl();
	
	/**
	 *  模拟MOCK
	 */
	 @InjectIntoByType
	 Mock<AdvertisementMapper>  advertisementMapperMock;
	 
	/**
	 * 测试广告分页查询
	 */
    @Test
	public void testFindByPageBean()
	{
    	// 生成分页参数Bean
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("1");
        selectBean.setSearchText("千米");
        
        // 生成分页辅助类
        PageBean pb = new PageBean();
        pb.setStartRowNum(0);
        pb.setEndRowNum(15);

        // 设置参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition",selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        
        // 设置返回的对象
        List<Object> list = new ArrayList<>();
        list.add(new Object());
        
    	advertisementMapperMock.returns(1).findTotalCount(selectBean);
    	advertisementMapperMock.returns(list).findByPageBean(map);
    	assertEquals(1, advertisementService.findByPageBean(pb, selectBean).getList().size());
	}
    
    /**
     * 测试添加首页广告图片
     */
    @Test
    public void testInsertAdver()
    {
    	// 测试的要插入的广告图片实体
    	Advertisement advertisement = new Advertisement();
    	advertisementMapperMock.returns(1).insertSelective(advertisement);
    	assertEquals(1, advertisementService.insertAdver(advertisement));
    }
    
    /**
     * 测试删除广告图片信息
     */
    @Test
    public void testDeleteAdver()
    {
    	// 测试的广告图片id
    	String [] adverIds = {"1"};
    	advertisementMapperMock.returns(1).deleteByPrimaryKey(1L);
    	assertEquals(1, advertisementService.deleteAdver(adverIds));
    }
    
    /**
     * 测试 查询单条首页广告信息
     */
    @Test
    public void testFindByAdverId()
    {
    	advertisementMapperMock.returns(new Advertisement()).selectByPrimaryKey(1L);
    	assertNotNull(advertisementService.findByAdverId(1L));
    }
    
    /**
     * 测试修改首页广告信息
     */
    @Test
    public void testUpdateAdver()
    {
    	advertisementMapperMock.returns(1).updateByPrimaryKeySelective(new Advertisement());
    	assertEquals(1, advertisementService.updateAdver(new Advertisement()));
    }
    
    
    
    
}
