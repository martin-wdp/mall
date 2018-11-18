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

import com.ningpai.system.bean.ImageManager;
import com.ningpai.system.dao.IImageManagerDao;
import com.ningpai.system.service.IImageManagerBiz;
import com.ningpai.system.service.impl.ImageManagerBizImpl;
import com.ningpai.util.PageBean;

/**
 * 图片管理业务接口测试类
 * @author djk
 *
 */
public class IImageManagerBizTest extends UnitilsJUnit3
{
	/**
	 * 需要测试的接口
	 */
	@TestedObject
	private IImageManagerBiz iImageManagerBiz = new  ImageManagerBizImpl();
	
	/**
	 * 模拟MOCK
	 */
	@InjectIntoByType
	private Mock<IImageManagerDao> iImageManagerDaoMock;
	
	/**
	 *  图片管理实体
	 */
	private ImageManager imageManager;
	
	/**
	 * 分页辅助类实体
	 */
	private PageBean pageBean;
 	
	
	/**
	 * 参数map
	 */
	private Map<String,Object> map ;
	
	/**
	 * 查询的结果实体
	 */
	private List<Object> list ;
	
	@Override
	protected void setUp() throws Exception 
	{
		imageManager = new ImageManager();
		pageBean = new PageBean();
		
		map = new HashMap<>();
		
		map.put("startRowNum", pageBean.getStartRowNum());
		map.put("endRowNum", pageBean.getEndRowNum());
		
		list = new ArrayList<>();
		list.add(new Object());
	}
	
	/**
	 * 保存图片管理测试
	 */
	@Test
	public void testSaveImageManager()
	{
		iImageManagerDaoMock.returns(true).saveImageManager(imageManager);
		assertEquals(true, iImageManagerBiz.saveImageManager(imageManager));
	}
	
	/**
	 * 修改图片管理测试
	 */
	@Test
	public void testUpdateImageManager()
	{
		iImageManagerDaoMock.returns(1).updateImageManager(imageManager);
		assertEquals(1, iImageManagerBiz.updateImageManager(imageManager));
	}
	
	/**
	 * 根据图片管理对象的id查询图片管理对象测试
	 */
	@Test
	public void testgGetImageManagerById()
	{
		iImageManagerDaoMock.returns(imageManager).getImageManagerById(1);
		assertNotNull(iImageManagerBiz.getImageManagerById(1));
	}
	
	/**
	 * 根据图片管理对象的id字符集合查询图片管理对象测试
	 */
	@Test
	public void testGetImageManagerByIds()
	{
		List<ImageManager> lists = new ArrayList<>();
		lists.add(imageManager);
		iImageManagerDaoMock.returns(lists).getImageManagerByIds("1");
		assertEquals(1, iImageManagerBiz.getImageManagerByIds("1").size());
	}
	
	/**
	 * 根据图片管理对象的id字符集合删除图片管理对象
	 */
	@Test
	public void testDeleteImageManager()
	{
		iImageManagerDaoMock.returns(1).deleteImageManager("1");
		assertEquals(1, iImageManagerBiz.deleteImageManager("1"));
	}
	
	/**
	 * 更新图片管理对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
	 */
	@Test
	public void testIUpdateImageManagerFieldById()
	{
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("ids", "1,2");
		iImageManagerDaoMock.returns(1).updateImageManagerFieldById(map);
		assertEquals(1, iImageManagerBiz.updateImageManagerFieldById(map));
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		assertEquals(list, map.get("ids"));
	}
	
	
	/**
	 * 根据图片管理对象的单个字段查询图片管理对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
	 */
	@Test
	public void testGetImageManagerByFieldTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iImageManagerDaoMock.returns(1).getImageManagerByFieldTotal(map);
		assertEquals(1, iImageManagerBiz.getImageManagerByFieldTotal(map));
	}
	
	/**
     * 根据图片管理对象的单个字段查询图片管理对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
	 */
	@Test
	public void testGetImageManagerByField()
	{
		iImageManagerDaoMock.returns(1).getImageManagerByFieldTotal(map);
		iImageManagerDaoMock.returns(list).getImageManagerByField(map);
		assertEquals(1, iImageManagerBiz.getImageManagerByField(map, pageBean).getList().size());
	}
	
	/**
	 * 查询图片管理对象信息总数测试
	 */
	@Test
	public void testQueryImageManagerTotal()
	{
		Map<String, Object> map = new HashMap<>();
		map.put("1", "1");
		iImageManagerDaoMock.returns(1).queryImageManagerTotal(map);
		assertEquals(1, iImageManagerBiz.queryImageManagerTotal(map));
	}
	
	/**
	 * 分页查询图片管理对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum测试
	 */
	@Test
	public void testQueryImageManagerByPage()
	{
		iImageManagerDaoMock.returns(1).queryImageManagerTotal(map);
		iImageManagerDaoMock.returns(list).queryImageManagerByPage(map);
		assertEquals(1, iImageManagerBiz.queryImageManagerByPage(map, pageBean).getList().size());
	}
	
}
