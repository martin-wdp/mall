/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.junit.core.adv.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.adv.bean.Adv;
import com.ningpai.adv.dao.AdvMapper;
import com.ningpai.adv.service.AdvService;
import com.ningpai.adv.service.impl.AdvServiceImpl;
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
 * 广告测试类
 */
public class AdvServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     */
    @TestedObject
    private AdvService advService = new AdvServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<AdvMapper> advMapperMock;
    /**
     * JS数据
     */
    @FileContent("advList.js")
    private String advListJs;


    /**
     * 共享数据
     */
    List<Adv> advList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        advList = JSON.parseArray(advListJs, Adv.class);
    }




    /**
    * 根据主键删除
    */
    public void  testDelete(){
        advMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, advService.delete(1L));
    }

    /**
    * 插入，空属性不会插入
    */
    public void  testInsert(){
        advMapperMock.returns(1).insertSelective(advList.get(0));
        assertEquals(1, advService.insert(advList.get(0)));
    }

    /**
    * 根据主键查询
    */
    public void  testSelect(){
        advMapperMock.returns(advList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(advService.select(1L));
    }

    /**
    * 根据主键修改，空值条件不会修改成null
    */
    public void  testUpdate(){
        advMapperMock.returns(1).updateByPrimaryKeySelective(advList.get(0));
        assertEquals(1, advService.update(advList.get(0)));
    }

    /**
     * 分页查询列表
     */
    public void  testSelectPageList(){
        Map<String, Object> map = new HashMap<String, Object>();
        PageBean pb = new PageBean();
        pb.setObjectBean(new Adv());
        // 设置到Map中
        map.put("pageBean", pb);
        advMapperMock.returns(1).selectPageListCount(map);
        advMapperMock.returns(advList).selectPageList(map);
        assertEquals(1, advService.selectPageList(new PageBean(),new Adv()).getList().size());
    }

    /**
     * 批量删除
     */
    public void  testDeleteAll(){
        advMapperMock.returns(1).deleteMuilti(new Long[]{1L});
        assertEquals(1, advService.deleteAll(new Long[]{1L}));
    }

    
    /**
     * 查询广告列表
     */
    public void  testSelectAdvListByPosition(){
        advMapperMock.returns(advList).selectAdvListByPosition("1");
        assertEquals(1, advService.selectAdvListByPosition("1").size());
    }

}
