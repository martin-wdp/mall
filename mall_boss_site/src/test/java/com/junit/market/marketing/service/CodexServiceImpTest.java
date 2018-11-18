/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.junit.market.marketing.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.dao.CodexMapper;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.marketing.service.impl.CodexServiceImpl;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 规则service
 */
public class CodexServiceImpTest   extends UnitilsJUnit3 {

    /**
     * 需要测试的Service
     */
    @TestedObject
    private CodexService codexService = new CodexServiceImpl();

    /**
     * 模拟Mock
     */
    @InjectIntoByType
    Mock<CodexMapper> codexMapperMock;

    @FileContent("codexList.js")
    private String codexListJs;
    /**
     * 共享数据
     */
    List<Codex> codexList;

    /**
     * 初始化
     */
    @Override
    public void setUp(){
        codexList= JSON.parseArray(codexListJs,Codex.class);

    }

    /**
     * 查询规则列表
     */
    @Test
    public void testSelectCodexList(){
        /** 定义一个分页对象 */
        PageBean pb =new PageBean();
        /** 定义一个codex对象 */
        Codex codex = new Codex();
        codex.setFlag("0");
        /** 定义一个HashMap集合 */
        Map<String, Object> paramMap = MapUtil.getParamsMap(codex);
        /** 设置pageBean的行数 */
        pb.setRows(codexMapperMock.returns(1).selectCodexListCount(paramMap));
        /** 设置开始行数 */
        paramMap.put("start", pb.getStartRowNum());
        /** 设置结束行数 */
        paramMap.put("number", pb.getEndRowNum());
        /** 设置pageBean的集合数据 */
        pb.setList(codexMapperMock.returns(codexList).selectCodexList(paramMap));
        /** 返回结果 */
        assertEquals(1, codexService.selectCodexList(codex,pb).getList().size());

    }

    /**
     * 添加规则
     */
    @Test
    public void testAddCodex(){
        /** 定义一个codex对象 */
        Codex codex = new Codex();
        /** 设置codeId */
        codex.setCodexId(2L);
        /** 设置codexName */
        codex.setCodexName("qpmall");
        /** 设置codexDes */
        codex.setCodexDes("qpmallAdd");
        /** 设置createTime */
        codex.setCreateTime(new Date());
        /** 设置modTime */
        codex.setModTime(new Date());
        /** 设置flag */
        codex.setFlag("0");
        /** 设置codexType */
        codex.setCodexType("1");
        /** 设置codexImg */
        codex.setCodexImg("images/marketing/2.png");
        /** 设置codexStatus */
        codex.setCodexStatus(1L);
        /** 模拟dao */
        codexMapperMock.returns(1).addCodex(codex);
        /** 返回结果 */
        assertEquals(1,codexService.addCodex(codex));
    }

    /**
     * 查询所有促销规则
     */
    @Test
    public void testSelectCodexListUseBox(){
        /** 模拟dao */
        codexMapperMock.returns(codexList).selectCodexListUseBox();
        /** 返回结果 */
        assertEquals(1,codexService.selectCodexListUseBox().size());
    }

    /**
     * 查询规则明细
     */
    @Test
    public void testQueryCodexDetail(){
        /** 模拟dao */
        codexMapperMock.returns(codexList.get(0)).queryCodexDetail(1L);
        /** 返回结果 */
        assertNotNull(codexService.queryCodexDetail(1L));

    }

    /**
     * 查询规则列表
     */
    @Test
    public void testQueryCodexListByParam(){
        /** 模拟dao */
        codexMapperMock.returns(codexList).queryCodexListByParam(1L);
        /** 返回结果 */
        assertEquals(1,codexService.queryCodexListByParam(1L).size());
    }

}
