package com.junit.information.information.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.information.bean.Information;
import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.information.dao.InformationOnePageMapper;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.information.service.impl.InformationOnePageServiceImpl;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资讯单页SERVICE Test
 * @author lih
 * @version 2.0
 * @since 15/9/15
 */
public class InformationOnePageServiceTest extends UnitilsJUnit3 {

    @FileContent("InformationOnePage.js")
    private String informationOnePageJs;

    @FileContent("InformationOnePageList.js")
    private String informationOnePageListJs;
    //实体类
    InformationOnePage informationOnePage;

    //集合
    List<InformationOnePage> list;

    @InjectIntoByType
    Mock<InformationOnePageMapper> informationOnePageMapperMock;



    @TestedObject
    private InformationOnePageService informationOnePageService=new InformationOnePageServiceImpl();

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        list=JSON.parseArray(informationOnePageListJs, InformationOnePage.class);
        informationOnePage= JSON.parseObject(informationOnePageJs,InformationOnePage.class);
    }
    /**
     * 根据主键删除
     */
    @Test
    public void testDelInfoOnePage() {
        //模拟查询
        informationOnePageMapperMock.returns(informationOnePage).selectByPrimaryKey(1L);
        //创建实体类
        InformationOnePage onePage=new InformationOnePage();
        //模拟
        informationOnePageMapperMock.returns(1).updateByPrimaryKeySelective(onePage);
        //调用方法
        assertEquals(1, informationOnePageService.delInfoOnePage(1L, 1L));
    }

    /**
     * 批量删除资讯单页
     */
    @Test
    public void batchDelInfoOnePage() {
        informationOnePageMapperMock.returns(informationOnePage).selectByPrimaryKey(1L);
        //创建实体类
        InformationOnePage onePage=new InformationOnePage();
        //模拟
        informationOnePageMapperMock.returns(1).updateByPrimaryKeySelective(onePage);
        //批量删除
        informationOnePageService.batchDelInfoOnePage(new Long[1],1L);
    }

    /**
     * 添加资讯单页
     */
    @Test
    public void testSaveInfoOnePage() {
        informationOnePageMapperMock.returns(1).insert(new InformationOnePage());
        assertEquals(1, informationOnePageService.saveInfoOnePage(new InformationOnePage()));
    }

    /**
     * 更新资讯单页
     */
    @Test
    public void testUpdateInfoOnePage() {
        informationOnePageMapperMock.returns(1).updateByPrimaryKeySelective(new InformationOnePage());
        assertEquals(1, informationOnePageService.updateInfoOnePage(new InformationOnePage()));
    }

    /**
     * 根据主键查询
     */
    @Test
    public void testGetInfoOnePageByID() {
        informationOnePageMapperMock.returns(informationOnePage).selectByPrimaryKey(1L);
        assertEquals(informationOnePage, informationOnePageService.getInfoOnePageByID(1L));
    }

    /**
     * 根据分页参数查询资讯单页分页数据
     */
    @Test
    public void testQueryInfoOnePageByPageBean() {
        PageBean pageBean=new PageBean();

        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        /** 查询数据的总行数并设置到PageBean中 */
        informationOnePageMapperMock.returns(3).queryTotalCount(map);
        /** 设置开始行数 */
        map.put("startRowNum", 0);
        /** 设置结束行数 */
        map.put("endRowNum", 15);
        informationOnePageMapperMock.returns(list).queryByPageBean(map);
        SelectBean selectBean=new SelectBean();
        /** 根据分页参数查询资讯单页列表 */
       informationOnePageService.queryInfoOnePageByPageBean(pageBean, selectBean);
    }

    /**
     * 根据单页标题查询单页数量判断标题是否存在
     */
    @Test
    public void checkAddInfoOPByTitle() {/*
        informationOnePageMapperMock.returns(true).selectInfoOPCountByTitle("测试");
        assertEquals(true, informationOnePageService.checkAddInfoOPByTitle("测试", 1L));*/
    }

    /**
     * 根据单页标题查询单页数量判断标题是否存在<br/>
     * 根据单页ID查询出文章标题，判断老标题和新标题是否一样<br>
     * 如果一样直接返回true，不一样查询数量判断是否存在
     *
     * @param title
     * @param infoOPId 单页ID
     * @return
     */
    @Test
    public boolean checkAddInfoOPByTitle(String title, Long infoOPId) {
        return false;
    }

    /**
     * 根据模板ID和标签ID查询单页
     *
     * @param tempId      模板ID
     * @param infoOPTagId 标签ID
     * @return
     */
    @Test
    public List<InformationOnePage> selectInfoOPByTempAndTag(Long tempId, Long infoOPTagId) {
        return null;
    }

    /**
     * 根据单页标签查询单页数量，判断是否可删除标签
     *
     * @param infoOPTagId
     * @return
     */
    @Test
    public Integer selectInfoOPCountByTag(Long infoOPTagId) {
        return null;
    }
}
