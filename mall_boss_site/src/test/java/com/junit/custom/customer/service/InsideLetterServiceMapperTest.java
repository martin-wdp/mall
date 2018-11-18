package com.junit.custom.customer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.dao.InsideLetterMapper;
import com.ningpai.customer.service.InsideLetterServiceMapper;
import com.ningpai.customer.service.impl.InsideLetterServiceMapperImpl;
import com.ningpai.customer.vo.InsideLetterVo;
import com.ningpai.util.PageBean;

/**
 * 站内信Service接口单元测试
 * @author qiyuanyuan
 *
 */
public class InsideLetterServiceMapperTest extends UnitilsJUnit3{
    
    /**
     * 需要测试的Service
     */
    @TestedObject
    private InsideLetterServiceMapper insideLetterServiceMapper = new InsideLetterServiceMapperImpl();

    /**
     * 模拟
     */    
    @InjectIntoByType
    Mock<InsideLetterMapper> insideLetterMapperMock;
    
    /**
     * js数据
     */
    @FileContent("insideLetterList.js")
    private String insideLetterListJS;
    @FileContent("insideLetter.js")
    private String insideLetterJS;
    
    
    /**
     * 共享数据
     */
    List<InsideLetter> insideLetterList;
    
    InsideLetter insideLetter;
    
    /**
     * 初始化
     */
    public void setUp(){
        insideLetterList = JSON.parseArray(insideLetterListJS, InsideLetter.class);
        insideLetter  = JSON.parseObject(insideLetterJS, InsideLetter.class);
    }
    
    /**
     * 测试删除站内信
     */
    @Test
    public void testDeleteByPrimaryKey(){
        insideLetterMapperMock.returns(1).deleteByPrimaryKey(1L);
        assertEquals(1, insideLetterServiceMapper.deleteByPrimaryKey(1L));
    }
    
    /**
     * 测试添加站内信
     */
    public void testInsert(){
        insideLetterMapperMock.returns(1).insert(insideLetterList.get(0));
        assertEquals(1, insideLetterServiceMapper.insert(insideLetterList.get(0)));
    }
    
    /**
     * 测试添加站内信
     */
    @Test
    public void testInsertSelective(){
        insideLetterMapperMock.returns(1).insertSelective(insideLetterList.get(0));
        assertEquals(1, insideLetterServiceMapper.insertSelective(insideLetterList.get(0)));
    }
    
    /**
     * 测试根据会员Id添加站内信
     */
    @Test
    public void testInsertNotices(){
        insideLetterMapperMock.returns(1).insertNotices(insideLetterList);
        assertEquals(1, insideLetterServiceMapper.insertNotices(insideLetterList.get(0), new Long[]{1L}));
    }
    
    /**
     * 测试查询站内信
     */
    @Test
    public void testSelectByPrimaryKey(){
        insideLetterMapperMock.returns(insideLetter).selectByPrimaryKey(1L);
        assertNotNull(insideLetterServiceMapper.selectByPrimaryKey(1L));
    }
    
    /**
     * 测试修改站内信
     */
    @Test
    public void testUpdateByPrimaryKeySelective(){
        insideLetterMapperMock.returns(1).updateByPrimaryKeySelective(new InsideLetter());
        assertEquals(1, insideLetterServiceMapper.updateByPrimaryKeySelective(new InsideLetter()));
    }
    
    /**
     * 测试修改站内信
     */
    @Test
    public void testUpdateByPrimaryKey(){
        insideLetterMapperMock.returns(1).updateByPrimaryKey(new InsideLetter());
        assertEquals(1, insideLetterServiceMapper.updateByPrimaryKey(new InsideLetter()));
    }
    
    /**
     * 测试 查询全部站内信
     */
    @Test
    public void testQueryInsideLetter(){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        paramMap.put("customerId", 1L);
        paramMap.put("startRowNum",0);
        paramMap.put("endRowNum",15);
        insideLetterMapperMock.returns(1L).queryInsideCount(1L);
        insideLetterMapperMock.returns(insideLetterList).queryInsideLetter(paramMap);
        assertEquals(1, insideLetterServiceMapper.queryInsideLetter(paramMap, new PageBean()).getList().size());
    }
    
    /**
     * 测试标记为已读
     */
    @Test
    public void testReadedLetter(){
        insideLetterMapperMock.returns(1).readedLetter(new InsideLetterVo());
        assertEquals(1, insideLetterServiceMapper.readedLetter(new InsideLetterVo()));
    }
    
    /**
     * 测试删除
     */
    @Test
    public void testDeleteLetter(){
        insideLetterMapperMock.returns(1).deleteLetter(1L);
        assertEquals(1, insideLetterServiceMapper.deleteLetter(1L));
    }
    
    /**
     * 测试已读
     */
    @Test
    public void testLetterIsRead(){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        insideLetterMapperMock.returns(1L).letterIsRead(paramMap);
        assertSame(1L, insideLetterServiceMapper.letterIsRead(paramMap));
    }
    
    /**
     * 测试删除未读
     */
    @Test
    public void testDeleteLetterNo(){
        insideLetterMapperMock.returns(1).deleteLetterNo(new InsideLetterVo());
        assertEquals(1, insideLetterServiceMapper.deleteLetterNo(new InsideLetterVo()));
    }
    
    /**
     * 测试根据会员Id和letteId删除
     */
    public void testDeleteByLetterIdCustId(){
        Map<String, Object> paramMap = new HashMap<String,Object>();
        insideLetterMapperMock.returns(1).deleteByLetterIdCustId(paramMap);
        assertEquals(1, insideLetterServiceMapper.deleteByLetterIdCustId(paramMap));
    }
    
}
