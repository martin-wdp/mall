package com.junit.third.goods.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.third.goods.service.impl.ThirdCateServiceImpl;
import com.ningpai.third.goods.vo.ThirdCateVo;
import com.ningpai.util.SelectBean;

/**
 * 第三方店家分类Service实现测试
 * @author djk
 *
 */
public class ThirdCateServiceTest extends UnitilsJUnit3
{
    /**
     * 需要测试的Service
     */
    @TestedObject
    private ThirdCateService thirdCateService = new ThirdCateServiceImpl();
    
    /**
     * 模拟
     */
    @InjectIntoByType
    private Mock<ThirdCateMapper> thirdCateMapperMock;
    
    /**
     * 第三方店铺分类
     */
    private ThirdCate thirdCate = new ThirdCate();
    
    /**
     * 第三方店铺分类集合
     */
    private List<ThirdCate> thirdCateLists = new ArrayList<>();
    
    /**
     *  第三方商家分类Vo
     */
    private ThirdCateVo thirdCateVo = new ThirdCateVo();
    
    /**
     * 第三方商家分类Vo集合
     */
    private List<ThirdCateVo> thirdCateVoList = new ArrayList<>();
    
    
    @Override
    protected void setUp() throws Exception 
    {
    	thirdCateVoList.add(thirdCateVo);
    	thirdCateVo.setCatGrade(1);
    	thirdCateVo.setCatId(1L);
    	thirdCate.setCatParentId(1L);
    	thirdCateLists.add(thirdCate);
    }
    
    /**
     * 添加一个第三方分类测试
     */
    @Test
    public void testInsertThirdCate()
    {
    	thirdCateMapperMock.returns(thirdCateVo).selectByPrimaryKey(1L);
    	thirdCateMapperMock.returns(1).insertSelective(thirdCate);
    	assertEquals(1, thirdCateService.insertThirdCate(thirdCate, "a"));
    }
    
    /**
     *  删除第三方商家分类测试
     */
    @Test
    public void testDelThirdCate()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("catId", 1L);
        map.put("delName", "a");
        thirdCateMapperMock.returns(1).deleteByPrimaryKey(map);
        assertEquals(1, thirdCateService.delThirdCate(1L, "a"));
    }
    
    /**
     * 新删除第三方商家分类测试
     */
    @Test
    public void testDelThirdCateNew()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("catId", 1L);
        map.put("delName", "a");
        map.put("thirdId", 1L);
        thirdCateMapperMock.returns(1).deleteByPrimaryKeyNew(map);
    	assertEquals(1, thirdCateService.delThirdCateNew(1L, "a", 1L));
    }
    
    /**
     * 批量删除分类测试
     */
    @Test
    public void testBatchDelThirdCate()
    {
    	Long[] catIds = {1L};
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("catId", 1L);
        map.put("delName", "a");
        thirdCateMapperMock.returns(1).deleteByPrimaryKey(map);
    	assertEquals(1, thirdCateService.batchDelThirdCate(catIds, "a"));
    }
    
    /**
     *  更新第三方店铺分类信息测试
     */
    @Test
    public void testUpdateThirdCate()
    {
    	thirdCateMapperMock.returns(thirdCateVo).selectByPrimaryKey(1L);
    	thirdCateMapperMock.returns(1).updateByPrimaryKeySelective(thirdCate);
    	assertEquals(1, thirdCateService.updateThirdCate(thirdCate, "a"));
    }
    
    /**
     * 根据分类ID查询分类信息测试
     */
    @Test
    public void testQueryThirdCateById()
    {
    	thirdCateMapperMock.returns(thirdCateVo).selectByPrimaryKey(1L);
    	assertNotNull(thirdCateService.queryThirdCateById(1L));
    }
    
    /**
     * 查询所有的分类信息测试 
     */
    @Test
    public void testQueryAllCate()
    {
    	thirdCateMapperMock.returns(thirdCateLists).queryAllCate(1L);
    	assertEquals(1, thirdCateService.queryAllCate(1L).size());
    }
 
    /**
     * 查询所有的第三方分类Vo集合测试
     */
    @Test
    public void testQueryAllThirdCate()
    {
    	thirdCateMapperMock.returns(thirdCateVoList).queryAllThirdCate(new HashMap<String, Object>());
    	assertEquals(1, thirdCateService.queryAllThirdCate(new HashMap<String, Object>()).size());
    }
    
    /**
     * 验证是否可以删除,如果传递过来的分类ID下的子分类的数量不等于空返回false表示不可删除测试
     */
    @Test
    public void testCheckDelWithCateId()
    {
    	thirdCateMapperMock.returns(0).querySonCountByParentId(1L);
    	assertEquals(true, thirdCateService.checkDelWithCateId(1L));
    }
    
    /**
     * 根据分类名称查询分类信息测试
     */
    @Test
    public void testQueyCateByCateName()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cateName", "a");
        map.put("thirdId", 1L);
        thirdCateMapperMock.returns(thirdCate).queryCateByCateName(map);
    	assertNotNull(thirdCateService.queyCateByCateName("a", 1L));
    }
    
    /**
     * 获取第三方分类列表测试
     */
    @Test
    public void testGetAllCalcThirdCate()
    {
    	SelectBean selectBean = new SelectBean();
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", "");
        map.put("searchText", "");
        map.put("thirdId", 1L);
        thirdCateMapperMock.returns(thirdCateVoList).queryAllThirdCate(map);
        thirdCateMapperMock.returns(thirdCateVoList).queryAllCateForList(1L);
        
    	assertEquals(1, thirdCateService.getAllCalcThirdCate(selectBean, 1L).size());
    }
    
    /**
     * 计算第三方店家的分类关系测试
     */
    @Test
    public void testCalcCateVo()
    {
    	List<ThirdCateVo> allCateList = new ArrayList<>();
    	ThirdCateVo t = new ThirdCateVo();
    	 t.setCatId(1L);
    	t.setCatParentId(2L);
    	allCateList.add(t);
    	assertEquals(1, thirdCateService.calcCateVo(2L, allCateList).size());
    }
    
    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合测试
     */
    @Test
    public void testGetThirdCateByParentId()
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("catId", 1L);
        map.put("thirdId", 1L);
        thirdCateMapperMock.returns(thirdCateLists).queryThirdCateByParentId(map);
    	assertEquals(1, thirdCateService.getThirdCateByParentId(1L, 1L).size());
    }
    
    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合测试
     */
    @Test
    public void testGetThirdCateByParentIdtwo()
    {
    	thirdCateMapperMock.returns(thirdCateLists).queryThirdCateByParentIdtwo(1L);
    	assertEquals(1, thirdCateService.getThirdCateByParentIdtwo(1L).size());
    }
    
    /**
     * 根据分类分类名称,第三方ID,层级标记查询分类集合测试
     */
    @Test
    public void testGetThirdCateByCateNameAndGrade()
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	 map.put("catName", "a");
         map.put("thirdId", 1L);
         map.put("grade", 3);
         thirdCateMapperMock.returns(thirdCateLists).queryThirdCateByCatNameAndGrade(map);
    	assertEquals(1, thirdCateService.getThirdCateByCateNameAndGrade("a", 1L).size());
    }
    
    /**
     * 把最近使用的记录保存到cookie中测试
     */
    @Test
    public void testsaveToCookie()
    {
    	HttpServletRequest request = new MockHttpServletRequest();
    	HttpServletResponse response = new MockHttpServletResponse();
    	try {
    	   	thirdCateService.saveToCookie(thirdCateVo, request, response);
		} catch (Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    /**
     * 从cookie中取出最近使用的记录测试
     */
    @Test
    public  void testTakeFormCookie()
    {
    	HttpServletRequest request = new MockHttpServletRequest();
    	HttpServletResponse response = new MockHttpServletResponse();
    	try {
    		assertEquals(0, thirdCateService.takeFormCookie(request, response).size());
		} catch (Exception e) 
    	{
			e.printStackTrace();
		}
    }
    
    /**
     * 清除最近使用的cookie记录测试
     */
    @Test
    public void testClearThirdCateFromCookie()
    {
    	HttpServletRequest request = new MockHttpServletRequest();
    	HttpServletResponse response = new MockHttpServletResponse();
    	assertEquals(true, thirdCateService.clearThirdCateFromCookie(request, response));
    }
    
    /**
     * 根据父分类id，分类名称和商家id查询子分类信息测试
     */
    @Test
    public void testQueryThirdSonCateByCateIdAndName()
    {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cateParentId", 1L);
        paramMap.put("cateName", "a");
        paramMap.put("thirdId", 1L);
        thirdCateMapperMock.returns(thirdCateLists).querySonCatByParm(paramMap);
    	assertEquals(1, thirdCateService.queryThirdSonCateByCateIdAndName(1L, "a", 1L).size());
    }
}
