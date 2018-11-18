package com.junit.information.mobile.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.mobile.bean.MobSinglepageMark;
import com.ningpai.mobile.dao.MobSinglepageMarkMapper;
import com.ningpai.mobile.service.MobSinglepageMarkService;
import com.ningpai.mobile.service.impl.MobSinglepageMarkServiceImpl;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * Created by houyichang on 2015/9/17.
 */
public class MobSinglepageMarkServiceTest extends UnitilsJUnit3{

    /**
     * 需要测试的service
     * */
    @TestedObject
    private MobSinglepageMarkService mobSinglepageMarkService = new MobSinglepageMarkServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<MobSinglepageMarkMapper> mobSinglepageMarkMapperMock;

    /**
     * JS数据
     * */
    @FileContent("mobSinglepageMarkList.js")
    private String mobSinglepageMarkListJs;

    /**
     * 共享数据
     * */
    List<MobSinglepageMark> mobSinglepageMarkList;

    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        mobSinglepageMarkList = JSON.parseArray(mobSinglepageMarkListJs,MobSinglepageMark.class);
    }

    /**
     * 查询所有移动版单页标签信息列表
     * */
    public void testSelectAllMarkInfo(){
        mobSinglepageMarkMapperMock.returns(mobSinglepageMarkList).selectAllMarkInfo();
        assertEquals(1,mobSinglepageMarkService.selectAllMarkInfo().size());
    }

    /**
     * 添加移动单单页标签信息
     * */
    public void testInsertSelective(){
        mobSinglepageMarkMapperMock.returns(1).insertSelective(mobSinglepageMarkList.get(0));
        assertEquals(1,mobSinglepageMarkService.insertSelective(mobSinglepageMarkList.get(0)));
    }

    /**
     * 根据主键id查询移动版单页标签信息
     * */
    public void testSelectMobMarkById(){
        mobSinglepageMarkMapperMock.returns(mobSinglepageMarkList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(mobSinglepageMarkService.selectMobMarkById(1L));
    }

    /**
     * 根据主键ID更新移动版单页标签信息
     * */
    public void testUpdateMobMarkById(){
        mobSinglepageMarkMapperMock.returns(1).updateByPrimaryKeySelective(mobSinglepageMarkList.get(0));
        assertEquals(1,mobSinglepageMarkService.updateMobMarkById(mobSinglepageMarkList.get(0)));
    }

    /**
     * 根据主键ID逻辑删除 修改delflag的状态 0：未删除 1：已删除
     * */
    public void testUpdateDelStatus(){
        mobSinglepageMarkMapperMock.returns(1).updateDelStatus(1L);
        assertNotNull(mobSinglepageMarkService.updateDelStatus(1L));
    }


    /**
     *查询删除状态为不删除的所有移动版单页标签信息列表
     * */
    public void testQueryAllMarkInfoByDel(){
        mobSinglepageMarkMapperMock.returns(mobSinglepageMarkList).queryAllMarkInfoByDel();
        assertEquals(1,mobSinglepageMarkService.queryAllMarkInfoByDel().size());
    }

    /**
     * 验证name是否存在
     * */
    public void testCheckNameExist(){
        mobSinglepageMarkMapperMock.returns(1).checkNameExist("单元测试");
        assertEquals(1,mobSinglepageMarkService.checkNameExist("单元测试"));
    }

  }
