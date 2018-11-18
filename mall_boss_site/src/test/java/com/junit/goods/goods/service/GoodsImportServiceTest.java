package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.dao.GoodsImportMapper;
import com.ningpai.goods.service.GoodsImportService;
import com.ningpai.goods.service.impl.GoodsImportServiceImpl;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/12.
 */
public class GoodsImportServiceTest extends UnitilsJUnit3{
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsImportService goodsImportService = new GoodsImportServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsImportMapper> goodsImportMapperMock;

    /**
     * JS数据
     * */
    @FileContent("goodsImportList.js")
    private String goodsImportListJs;

    /**
     * 共享数据
     * */
    private List<GoodsImport> goodsImportList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        goodsImportList = JSON.parseArray(goodsImportListJs, GoodsImport.class);
    }

    /**
     * 根据主键查询导入Bean
     *
     */
    public void testSelectByPrimaryKey(){
        goodsImportMapperMock.returns(goodsImportList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(goodsImportService.selectByPrimaryKey(1L));
    }

    /**
     * 根据参数和分页条件查询所有的Bean
     *
     */
    public void testSelectAllGoodsImport(){
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("condition", "测试");
        map.put(ValueUtil.SEARCHTEXT, "测试");
        map.put("thirdId", 1L);
        goodsImportMapperMock.returns(1).queryTotalCount(map);

        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        goodsImportMapperMock.returns(goodsImportList).queryAllGoodsImport(map);
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("测试");
        selectBean.setSearchText("测试");
        PageBean pageBean = new PageBean();
        pageBean.setStartRowNum(0);
        pageBean.setEndRowNum(15);
        assertEquals(1, goodsImportService.selectAllGoodsImport(pageBean, selectBean, 1L).getList().size());
    }

    /**
     * 根据主键更新删除状态
     *
     */
    public void testDeleteByPrimary(){
        goodsImportMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1,goodsImportService.deleteByPrimary(1L));
    }

    /**
     * 批量删除商品导入数据
     *
     */
    public void testBatchDelGoodsImport(){
        goodsImportMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1,goodsImportService.batchDelGoodsImport(new Long[]{1L}));
    }

    /**
     * 批量保存商品导入数据
     *
     */
    public void testSaveGoodsImport(){
        goodsImportMapperMock.returns(1).insertSelective(goodsImportList.get(0));
        assertEquals(1,goodsImportService.saveGoodsImport(goodsImportList));
    }

    /**
     * 更新商品导入状态
     */
    public void testUpdateGoodsImportAdded(){
        goodsImportMapperMock.returns(1).updateGoodsImportAdded(1L);
        assertEquals(1,goodsImportService.updateGoodsImportAdded(1L));
    }

    /**
     * 导入商品列表
     *
     */
    public void testImportGoodsByExcel(){
        assertEquals(1,1);
    }

    /**
     * 执行导入商品
     *
     */
    public void testExecImport(){
        assertEquals(1,1);
    }
}
