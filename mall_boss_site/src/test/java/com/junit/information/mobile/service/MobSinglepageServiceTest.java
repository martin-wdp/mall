package com.junit.information.mobile.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.mobile.bean.MobSinglepage;
import com.ningpai.mobile.dao.MobSinglepageMapper;
import com.ningpai.mobile.service.MobSinglepageService;
import com.ningpai.mobile.service.impl.MobSinglepageServiceImpl;
import com.ningpai.system.util.SelectBean;
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
 * 移动版单页服务层接口 service层实现类
 *
 * Created by houyichang on 2015/9/14.
 */
public class MobSinglepageServiceTest extends UnitilsJUnit3 {

    /**
     * 需要测试的service
     * */
    @TestedObject
    private MobSinglepageService mobSinglepageService = new MobSinglepageServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<MobSinglepageMapper> mobSinglepageMapperMock;

    /**
     * JS数据
     * */
    @FileContent("mobSinglepageList.js")
    private String mobSinglepageListJs;

    /**
     * 共享数据
     * */
    List<MobSinglepage> mobSinglepageList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp(){
        mobSinglepageList = JSON.parseArray(mobSinglepageListJs,MobSinglepage.class);
    }

    /**
     * 分页查询列表
     * */
    public void testSelectPageList(){
        /** 设置开始行数 */
        Map<Object, Object> paraMap = new HashMap<Object, Object>();
        paraMap.put("startRowNum", 0);
        /** 设置结束行数 */
        paraMap.put("endRowNum",15);
        paraMap.put("condition", "1");
        /** 判断传入的参数是否为null，不为null就放进map集合中 */
        paraMap.put("searchText", "qianmi");
        mobSinglepageMapperMock.returns(1).newQueryMobAllCount(paraMap);
        mobSinglepageMapperMock.returns(mobSinglepageList).newQueryMobByPage(paraMap);
        SelectBean sb= new SelectBean();
        sb.setCondition("1");
        sb.setSearchText("qianmi");
        assertEquals(1,mobSinglepageService.queryMobByPage(new PageBean(),sb).getList().size());
    }

    /**
     * 添加移动版单页信息
     * */
    public void testInsertSelective(){
        mobSinglepageMapperMock.returns(1).insertSelective(mobSinglepageList.get(0));
        assertEquals(1,mobSinglepageService.insertSelective(mobSinglepageList.get(0)));
    }

    /**
     * 根据主键查询移动版单页信息
     * */
    public void testSelectByPrimaryKey(){
        mobSinglepageMapperMock.returns(mobSinglepageList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(mobSinglepageService.selectByPrimaryKey(1L));
    }

    /**
     * 根据主键修改移动版单页信息
     * */
    public void testUpdateByPrimaryKeySelective(){
        mobSinglepageMapperMock.returns(1).updateByPrimaryKeySelective(mobSinglepageList.get(0));
        assertEquals(1,mobSinglepageService.updateByPrimaryKeySelective(mobSinglepageList.get(0)));
    }

    /**
     * 逻辑删除 根据主键ID修改delflag的状态 0：未删除 1：已删除
     * */
    public void testUpdatedelstatus(){
        mobSinglepageMapperMock.returns(1).updatedelstatus(1L);
        assertNotNull(mobSinglepageService.updatedelstatus(1L));
    }

    /**
     *根据MarkId查询符合条件的总条数
     * */
    public void testQueryCountByMarkId(){
        mobSinglepageMapperMock.returns(1).queryCountByMarkId(1L);
        assertNotNull(mobSinglepageService.queryCountByMarkId(1L));
    }

 }
