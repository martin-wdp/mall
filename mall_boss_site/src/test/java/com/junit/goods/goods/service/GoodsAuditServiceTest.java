package com.junit.goods.goods.service;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.dao.GoodsAuditMapper;
import com.ningpai.goods.service.GoodsAuditService;
import com.ningpai.goods.service.impl.GoodsAuditServiceImpl;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方商品审核测试接口
 *
 */
public class GoodsAuditServiceTest  extends UnitilsJUnit3 {


    @TestedObject
    private GoodsAuditService goodsAuditService=new GoodsAuditServiceImpl();

    @InjectIntoByType
    Mock<GoodsAuditMapper>  goodsAuditMapperMock;


    @Override
    public void setUp(){

    }

    /**
     * 获取审核商品信息
     */
    public void testSelectAuditGoods(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Goods> goodsList=new ArrayList<>();
        GoodsSearchBean searchBean=new GoodsSearchBean();
        // 设置基本查询为不可用
        //searchBean.setCondition("-1");
        //searchBean.setSearchText("");
        searchBean.setGoodsBrandId("-1");
        searchBean.setGoodsCateId("-1");
        searchBean.setGoodsKeyword("");
        searchBean.setGoodsName("");
        searchBean.setGoodsNo("");
        searchBean.setIsThird("3");
        searchBean.setShowFlag("0");
        searchBean.setStatus("-1");
        searchBean.setThirdName("");
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        // 把searchBean放进map中
        map.put("searchBean", searchBean);
        goodsAuditMapperMock.returns(1).selectAuditRows(map);
        goodsAuditMapperMock.returns(goodsList).selectAuditList(map);
        assertNotNull(goodsAuditService.selectAuditGoods(new PageBean(), searchBean));

    }

    /**
     * 获取审核开关标记
     */
    public void testSelectAuditAction(){
        goodsAuditMapperMock.returns("1").selectAuditAction();
        assertEquals("1", goodsAuditService.selectAuditAction());
   }

    /**
     * 开、关控制
     */
    public void testUpdateAuditAction(){
        goodsAuditMapperMock.returns(1).updateAuditAction("1");
        assertEquals(1,goodsAuditService.updateAuditAction("1"));
    }

    /**
     * 审核通过
     */
    public  void testAuditByGoodsId(){
        goodsAuditMapperMock.returns(1).auditByGoodsId(1L);
        assertEquals(1,goodsAuditService.auditByGoodsId(1L));
  }

    /**
     * 拒绝审核
     */
  public void testRefuseAuditByGoodsId(){
      // 定义一个HashMap集合
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("goodsId", 1L);
      map.put("refuseReason", "1");
      goodsAuditMapperMock.returns(1).refuseAuditByGoodsId(map);
      assertEquals(1,goodsAuditService.refuseAuditByGoodsId(1L,"1"));
  }

}
