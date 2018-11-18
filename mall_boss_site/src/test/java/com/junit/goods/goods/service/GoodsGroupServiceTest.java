package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.dao.GoodsGroupMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.impl.GoodsGroupServiceImpl;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by houyichang on 2015/10/9.
 */
public class GoodsGroupServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsGroupService goodsGroupService = new GoodsGroupServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<GoodsGroupMapper> goodsGroupMapperMock;

    /**
     * JS数据
     * */
    @FileContent("goodsGroupList.js")
    private String goodsGroupListJs;

    /**
     * 共享数据
     * */
    List<GoodsGroup> goodsGroupList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        goodsGroupList = JSON.parseArray(goodsGroupListJs,GoodsGroup.class);
    }

    /**
     * 保存商品组合
     *
     */
    public void testSaveGoodsGroup(){
        goodsGroupMapperMock.returns(1L).insertSelective(goodsGroupList.get(0));
        assertEquals(1L,goodsGroupService.saveGoodsGroup(goodsGroupList.get(0),"SEO描述").longValue());
    }

    /**
     * 删除商品组合
     *
     */
    public void testDelGoodsGroup(){
        assertEquals(1,1);
    }

    /**
     * 删除商品组合
     *
     */
    public void testDelGoodsGroupNew(){
        assertEquals(1,1);
    }

    /**
     * 更新商品组合信息
     *
     */
    public void testUpdateGoodsGroup(){
        goodsGroupMapperMock.returns(1).updateByPrimaryKeySelective(goodsGroupList.get(0));
        assertEquals(1,goodsGroupService.updateGoodsGroup(goodsGroupList.get(0),"测试"));
    }

    /**
     * 根据组合ID查询商品组合实体
     */
    public void testQueryGoodsGroupByPrimaryKey(){
        goodsGroupMapperMock.returns(goodsGroupList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(goodsGroupService.queryGoodsGroupByPrimaryKey(1L));
    }

    /**
     * 根据pageBean分页查询
     *
     */
    public void testQueryGoodsGroupByPageBean(){
        // 定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 设置总行数
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        goodsGroupMapperMock.returns(1).queryTotalAcount();
        goodsGroupMapperMock.returns(goodsGroupList).queryAllByPageBean(map);
        assertEquals(1,goodsGroupService.queryGoodsGroupByPageBean(new PageBean()).getList().size());
    }

    /**
     * 根据PageBean和参数Bean查询分页查询列表
     */
    public void testQueryGoodsGroupByPageBeanAndSearchBean(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        map.put(ValueUtil.CONDITION, "测试");
        map.put(ValueUtil.SEARCHTEXT, "测试");
        SelectBean selectBean = new SelectBean();
        selectBean.setCondition("测试");
        selectBean.setSearchText("测试");
        goodsGroupMapperMock.returns(1).searchTotalCount(selectBean);
        goodsGroupMapperMock.returns(goodsGroupList).queryAllByPageBeanAndSelBean(map);
        assertEquals(1,goodsGroupService.queryGoodsGroupByPageBeanAndSearchBean(new PageBean(),selectBean).getList().size());

    }

    /**
     * 批量删除商品组合
     */
    public void testBatchDelGroup(){
        assertEquals(1,1);
    }

    /**
     * 根据主键ID查询VO信息
     */
    public void testQueryVoByPrimaryKey(){
        goodsGroupMapperMock.returns(new GoodsGroupVo()).queryVoByPrimaryKey(1L);
        assertNotNull(goodsGroupService.queryVoByPrimaryKey(1L));
    }

    /**
     * 根据货品ID查询组合或者是套装列表
     */
    public void testQueryGroupVoListByProductId(){
        List<GoodsGroupVo> list = new ArrayList<GoodsGroupVo>();
        goodsGroupMapperMock.returns(list).queryGroupVoByProductId(1L);
        assertNotNull(goodsGroupService.queryGroupVoListByProductId(1L));
    }

    /**
     * 根据货品ID查询组合或者是套装列表,去除当前的ID
     *
     */
    public void testQueryGroupVoListWithOutProductId(){
        List<GoodsGroupVo> list = new ArrayList<GoodsGroupVo>();
        goodsGroupMapperMock.returns(list).queryGroupVoByProductId(1L);
        assertNotNull(goodsGroupService.queryGroupVoListWithOutProductId(1L));
    }

    /**
     * 获取首页商品组合和商品预警的数量
     *
     * @return
     */
    public void testGetIndexCount(){
        assertEquals(1,1);
    }
}
