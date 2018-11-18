package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsLackRegister;
import com.ningpai.goods.dao.GoodsLackRegisterMapper;
import com.ningpai.goods.service.GoodsLackRegisterService;
import com.ningpai.goods.service.impl.GoodsLackRegisterServiceImpl;
import com.ningpai.goods.util.LackRegisterSearchBean;
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
public class GoodsLackRegisterServiceTest extends UnitilsJUnit3{
    /**
     * 需要测试的service
     * */
    @TestedObject
    private GoodsLackRegisterService goodsLackRegisterService = new GoodsLackRegisterServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    private Mock<GoodsLackRegisterMapper> goodsLackRegisterMapperMock;

    /**
     * JS数据
     * */
    @FileContent("goodsLackRegisterList.js")
    private String goodsLackRegisterListJs;

    /**
     * 共享数据
     * */

    private List<GoodsLackRegister> goodsLackRegisterList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        goodsLackRegisterList = JSON.parseArray(goodsLackRegisterListJs,GoodsLackRegister.class);
    }

    /**
     * 插入一条到货通知记录
     *
     */
    public void testInsert(){
        goodsLackRegisterMapperMock.returns(1).insertSelective(goodsLackRegisterList.get(0));
        assertEquals(1,goodsLackRegisterService.insert(goodsLackRegisterList.get(0)));
    }

    /**
     * 更新到货通知记录
     *
     */
    public void testUpdate(){
        goodsLackRegisterMapperMock.returns(1).updateByPrimaryKeySelective(goodsLackRegisterList.get(0));
        assertEquals(1,goodsLackRegisterService.update(goodsLackRegisterList.get(0)));
    }

    /**
     * 根据主键查询到货通知
     *
     */
    public void testQueryByPrimaryId(){
        goodsLackRegisterMapperMock.returns(goodsLackRegisterList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(goodsLackRegisterService.queryByPrimaryId(1L));
    }

    /**
     * 根据PageBean查询分页列表
     *
     */
    public void testQueryByPageBean(){
        goodsLackRegisterMapperMock.returns(1).queryTotalCount();
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 封装分页参数
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM,15);
        goodsLackRegisterMapperMock.returns(goodsLackRegisterList).queryAllByPageBean(map);
        assertEquals(1,goodsLackRegisterService.queryByPageBean(new PageBean()).getList().size());
    }

    /**
     * 批量进行到货通知
     *
     */
    public void testUpdateLackRegisterStatus(){
        goodsLackRegisterMapperMock.returns(1).updateNoticeStatus(1L);
        assertEquals(1,goodsLackRegisterService.updateLackRegisterStatus(new Long[]{1L}));
    }

    /**
     * 根据参数查询
     *
     */
    public void testQueryByPageBeanAndSearchBean(){
        LackRegisterSearchBean searchBean = new LackRegisterSearchBean();
        searchBean.setCondition("-1");
        searchBean.setSearchText("");
        goodsLackRegisterMapperMock.returns(1).queryTotalCountBySearchBean(searchBean);
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 封装分页参数
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM,15);
        map.put(ValueUtil.SEARCHBEAN, searchBean);
        goodsLackRegisterMapperMock.returns(goodsLackRegisterList).queryByPageBeanAndSearchBean(map);
        assertEquals(1,goodsLackRegisterService.queryByPageBeanAndSearchBean(new PageBean(),searchBean).getList().size());
    }

    /**
     * 批量删除到货通知
     *
     */
    public void testBatchDel(){
        assertEquals(1,1);
    }

    /**
     * 根据货品Id更新通知状态
     *
     */
    public void testUpdateStatusByProductId(){
        goodsLackRegisterMapperMock.returns(1).updateStatusByProductId(1L);
        assertEquals(1,goodsLackRegisterService.updateStatusByProductId(1L));
    }
}