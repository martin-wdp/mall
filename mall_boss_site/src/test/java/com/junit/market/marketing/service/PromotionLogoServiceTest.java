package com.junit.market.marketing.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.marketing.bean.PromotionLogo;
import com.ningpai.marketing.dao.PromotionLogoMapper;
import com.ningpai.marketing.service.PromotionLogoService;
import com.ningpai.marketing.service.impl.PromotionLogoServiceImpl;
import com.ningpai.util.PageBean;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.*;


/**
 * 促销LOGO 接口
 * 
 * @author fengal
 *
 */
public class  PromotionLogoServiceTest extends UnitilsJUnit3 {
    /**
     * 需要测试的Service
     */
    @TestedObject
    private PromotionLogoService promotionLogoService = new PromotionLogoServiceImpl();
    /**
     * 模拟Mock
     */
    @InjectIntoByType
    Mock<PromotionLogoMapper> logoMapperMock;

    @FileContent("promotionLogoList.js")
    private String promotionLogoListJs;
    /**
     * 共享数据
     */
    List<PromotionLogo> promotionLogoList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        promotionLogoList= JSON.parseArray(promotionLogoListJs, PromotionLogo.class);

    }

    /**
     * 添加促销LOGO
     */
    @Test
    public void testAddPromotionLogo(){
        PromotionLogo promotionLogo = new PromotionLogo();
        promotionLogo.setPromotionLogoId(1L);
        promotionLogo.setPromotionLogoName("logo");
        promotionLogo.setPromotionLogoUrl("1");
        promotionLogo.setCreateTime(new Date());
        promotionLogo.setModifyTime(new Date());
        promotionLogo.setDelFlag("0");
        logoMapperMock.returns(1).insertSelective(promotionLogo);
        assertEquals(1,promotionLogoService.addPromotionLogo(promotionLogo));

    }

    /**
     * 修改促销LOGO
     */
    @Test
    public void testUpdatePromotionLogo(){
        PromotionLogo promotionLogo = new PromotionLogo();
        promotionLogo.setPromotionLogoId(1L);
        promotionLogo.setPromotionLogoName("logo");
        promotionLogo.setPromotionLogoUrl("1");
        promotionLogo.setCreateTime(new Date());
        promotionLogo.setModifyTime(new Date());
        promotionLogo.setDelFlag("0");
        logoMapperMock.returns(1).updateByPrimaryKeySelective(promotionLogo);
        assertEquals(1,promotionLogoService.updatePromotionLogo(promotionLogo));

    }

    /**
     * 根据促销LOGO ID删除促销LOGO
     */
    @Test
    public void testDelAllPromotionLogo(){
        List<Long> list = new ArrayList<Long>();
        list.add(1L);
        Long[] promotionLogoId={1L};
        logoMapperMock.returns(1).delAllPromotionLogo(list);
        assertEquals(1,promotionLogoService.delAllPromotionLogo(promotionLogoId));
    }

    /**
     * 查询促销LOHGO列表
     */
    @Test
    public void testQueryAllPromotionLogo(){
        PromotionLogo logo = new PromotionLogo();
        /** 定义一个分页对象 */
        PageBean pageBean =new PageBean();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        pageBean.setRows(logoMapperMock.returns(1).queryPromotionLogoCount(paramMap));
        // 开始数目
        paramMap.put("start", pageBean.getStartRowNum());
        // 结束数目
        paramMap.put("number", pageBean.getEndRowNum());
        pageBean.setList(logoMapperMock.returns(promotionLogoList).queryPromotionLogoList(paramMap));
        assertEquals(1,promotionLogoService.queryAllPromotionLogo(pageBean,logo).getList().size());


    }

    /**
     * 验证促销LOGO名称是否可用
     */
    public void testCheckLogoName(){
        logoMapperMock.returns(0).checkLogoName("logo");
        assertEquals(true,promotionLogoService.checkLogoName("logo"));

    }

    /**
     * 根据logoId查询logo详情
     */
    @Test
    public void testSelectByLogoId(){
        logoMapperMock.returns(promotionLogoList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(promotionLogoService.selectByLogoId(1L));
    }

    /**
     * 查询所有的促销logo集合
     */
    @Test
    public void testQueryAllLogoList(){
        logoMapperMock.returns(promotionLogoList).queryAllLogoList();
        assertEquals(1,promotionLogoService.queryAllLogoList().size());

    }

    /**
     * 查询最新插入的LOGO对象
     */
    @Test
    public void testSelectLastLogo(){
        logoMapperMock.returns(1L).selectLastId();
        logoMapperMock.returns(promotionLogoList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(promotionLogoService.selectLastLogo());

    }
}
