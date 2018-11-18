

package com.junit.goods.goods.service;

import com.ningpai.goods.bean.GoodsAtte;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsAtteMapper;
import com.ningpai.goods.service.GoodsAtteService;
import com.ningpai.goods.service.impl.GoodsAtteServiceImpl;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 测试商品关注Service
 *
 */
public class GoodsAtteServiceTest extends UnitilsJUnit3 {

    @TestedObject
    private GoodsAtteService goodsAtteService = new GoodsAtteServiceImpl();

    @InjectIntoByType
    Mock<GoodsAtteMapper> goodsAtteMapperMock;

    @InjectIntoByType
    Mock<CascDelMapper>  cascDelMapperMock;
    @Override
    public void setUp(){

    }

    /**
     * 删除商品关注信息测试
     */
    public  void testDeleteAtte(){

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("atteId", 1L);
        goodsAtteMapperMock.returns(1).deleteByPrimaryKey(map);
         assertEquals(1,goodsAtteService.deleteAtte(1L));

    }

    /**
     * 测试批量删除
     */
    public void  testBatchDelAtte(){
        Map<String, Object> map = new HashMap<String, Object>();
        Long [] atteIds={1L,2L,3L};
        map.put("atteIds",atteIds);
        goodsAtteMapperMock.returns(3).batchDelete(map);
        assertEquals(3,goodsAtteService.batchDelAtte(atteIds));
    }

    /**
     * 分页查询
     */
    public void testQueryByPageAndParam(){
        Map<String, Object> map = new HashMap<String, Object>();
        SelectBean selectBean=new SelectBean();
        GoodsAtte goodsAtte=new GoodsAtte();
        List<GoodsAtte> goodsAtteList=new ArrayList<>();
        goodsAtteList.add(goodsAtte);
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        map.put(ValueUtil.CONDITION,
                "".equals(selectBean.getCondition()) ? null : selectBean
                        .getCondition());
        map.put(ValueUtil.SEARCHTEXT,
                "".equals(selectBean.getSearchText()) ? null : selectBean
                        .getSearchText());

        goodsAtteMapperMock.returns(1).queryTotalCount(map);
        goodsAtteMapperMock.returns(goodsAtteList).queryByParam(map);
       assertNotNull(goodsAtteService.queryByPageAndParam(new PageBean(), selectBean));
    }

    /**
     * 根据货品id分页查询关注信息
     */

    public  void testQuerybyProductId(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<GoodsAtte> goodsAtteList=new ArrayList<>();
        map.put("productId", 1L);
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 15);
        goodsAtteMapperMock.returns(1).queryTotalCountToProduct(map);
        goodsAtteMapperMock.returns(goodsAtteList).queryByProductId(map);
        assertNotNull(goodsAtteService.querybyProductId(new PageBean(),1L));
    }

    /**
     * 查询商品关注列表
     */
    public void testSelectGoodsAtteCount(){
        goodsAtteMapperMock.returns(1).queryTotalCount(null);
        assertEquals(1,goodsAtteService.selectGoodsAtteCount());
    }
}
