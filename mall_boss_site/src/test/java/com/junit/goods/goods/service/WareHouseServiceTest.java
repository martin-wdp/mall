package com.junit.goods.goods.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.goods.bean.FollowAndCityVo;
import com.ningpai.goods.bean.WareCity;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.dao.WareCityMapper;
import com.ningpai.goods.dao.WareHouseMapper;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.goods.service.impl.WareHouseServiceImpl;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.WareHouseVo;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.util.PageBean;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fengbin on 2015/10/13.
 */
public class WareHouseServiceTest extends UnitilsJUnit3{
    /**
     * 需要测试的service
     * */
    @TestedObject
    private WareHouseService wareHouseService = new WareHouseServiceImpl();

    /**
     * 模拟
     * */
    @InjectIntoByType
    Mock<WareHouseMapper> wareHouseMapperMock;
    @InjectIntoByType
    Mock<WareCityMapper> wareCityMapperMock;
    @InjectIntoByType
    Mock<ProductWareMapper> productWareMapperMock;
    @InjectIntoByType
    Mock<CustomerMapper> customerMapperMock;
    /**
     * JS数据
     * */
    @FileContent("wareHouseList.js")
    private String wareHouseListJs;
    @FileContent("followAndCityVoList.js")
    private String followAndCityVoListJs;
    @FileContent("wareCityList.js")
    private String wareCityListJs;
    @FileContent("wareHouseVoList.js")
    private String wareHouseVoListJs;
    @FileContent("districtBeanList.js")
    private String districtBeanListJs;
    @FileContent("cityBeanList.js")
    private String cityBeanListJs;
    /**
     * 共享数据
     * */
    private List<WareHouse> wareHouseList;
    private List<FollowAndCityVo> followAndCityVoList;
    private List<WareCity> wareCityList;
    private List<WareHouseVo> wareHouseVoList;
    private List<DistrictBean> districtBeanList;
    private List<CityBean> cityBeanList;



    /**
     * 初始化
     * */
    @Override
    protected void setUp() throws Exception {
        wareHouseList = JSON.parseArray(wareHouseListJs, WareHouse.class);
        followAndCityVoList = JSON.parseArray(followAndCityVoListJs, FollowAndCityVo.class);
        wareCityList = JSON.parseArray(wareCityListJs, WareCity.class);
        wareHouseVoList = JSON.parseArray(wareHouseVoListJs, WareHouseVo.class);
        districtBeanList = JSON.parseArray(districtBeanListJs, DistrictBean.class);
        cityBeanList = JSON.parseArray(cityBeanListJs, CityBean.class);
    }

    /**
     * 查询所有仓库
     *
     */
    public void testFindWares(){
        wareHouseMapperMock.returns(wareHouseList).findWares();
        assertEquals(2, wareHouseService.findWares().size());
    }

    /**
     * 判断该仓库下的商品是否被收藏
     *
     */
    public void testSelectFollow(){
        /*wareCityMapperMock.returns(followAndCityVoList).selectFollow(1L, BigDecimal.valueOf(22),33L);
        assertEquals(2,wareHouseService.selectFollow(1L, BigDecimal.valueOf(22),33L).size());*/
    }

    /**
     * 根据仓库查询该仓库对应的 区县
     *
     */
    public void testSelectByWareId(){
        wareCityMapperMock.returns(wareCityList).queryAllByWareId(1L);
        assertEquals(2,wareHouseService.selectByWareId(1L).size());
    }

    /**
     * 删除仓库信息
     *
     */
    public void testDeleteWareById(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wareId",1L);
        map.put("adminName", "fb");
        wareCityMapperMock.returns(1).deleteCityByWareId(1L);
        productWareMapperMock.returns(1).deleteWareCity(1L);
        wareHouseMapperMock.returns(1).deleteByPrimaryKey(map);
        assertEquals(1,wareHouseService.deleteWareById(1L, "fb"));
    }

    /**
     * 保存仓库信息
     *
     */
    public void testSaveWareHouse(){
        wareHouseMapperMock.returns(1L).insertSelective(wareHouseList.get(0));
        wareCityMapperMock.returns(1L).insertSelective(wareCityList.get(0));
        assertEquals(1L,wareHouseService.saveWareHouse(wareHouseList.get(0),"fb",new Long[]{1L,2L}).longValue());
    }

    /**
     * 根据主键查询仓库信息
     *
     */
    public void testSelectWareByWareId(){
        wareHouseMapperMock.returns(wareHouseVoList.get(0)).selectByPrimaryKey(1L);
        assertNotNull(wareHouseService.selectWareByWareId(1L));
    }

    /**
     * 更新仓库信息
     *
     */
    public void testUpdateWareHouse(){
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, Object> map2 = new HashMap<String, Object>();
        map.put("distinctIds", new Long[]{1L,2L});
        map.put("wareId", 1L);
        wareCityMapperMock.returns(1).delNotInChecked(map);
        map2.put("wareId", 1L);
        map2.put("distinctId",1L);
        wareCityMapperMock.returns(1).queryByWareIdAndDistinctId(map2);
        wareCityMapperMock.returns(1).insertSelective(wareCityList.get(0));
        wareHouseMapperMock.returns(1).updateByPrimaryKeySelective(wareHouseList.get(0));
        assertEquals(1, wareHouseService.updateWareHouse(wareHouseList.get(0), "fb",new Long[]{1L,2L}));
    }

    /**
     * 查询所有的仓库记录
     *
     */
    public void testQueryAllWareHouse(){
        wareHouseMapperMock.returns(wareHouseList).queryAllWareHouse();
        assertNotNull(wareHouseService.queryAllWareHouse());
    }

    /**
     * 根据参数查询仓库数量
     *
     */
    public void testQueryCountByParams(){
        wareHouseMapperMock.returns(1).queryCountByParams(null);
        assertNotNull(wareHouseService.queryCountByParams());
    }

    /**
     * 根据分页信息和查询参宿查询仓库集合
     *
     */
    public void testQueryAllWareHouseByPageBean(){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> list = new ArrayList();
        map.put(ValueUtil.CONDITION,"111");
        map.put(ValueUtil.SEARCHTEXT,"222");
        map.put(ValueUtil.STARTROWNUM,1);
        map.put(ValueUtil.ENDROWNUM,2);
        wareHouseMapperMock.returns(list).queryAllWareHouseByPageBean(map);
        assertNotNull(wareHouseService.queryAllWareHouseByPageBean(new PageBean(),new com.ningpai.util.SelectBean()));
    }

    /**
     * 根据参数查询仓库数量
     *
     */
    public void testCheckWareName(){
        wareHouseMapperMock.returns(wareHouseList).queryWareCountByWareName("南方仓");
        assertNotNull(wareHouseService.checkWareName("南方仓"));
    }

    /**
     * 查询所有已经保存的仓库的所在城市
     *
     */
    public void testGetAllWareHouseDistrict(){
        customerMapperMock.returns(districtBeanList).selectAllDistrict();
        wareHouseMapperMock.returns(wareCityList).getAllWareHouseDistrict();
        assertEquals(2, wareHouseService.getAllWareHouseDistrict().size());
    }

    /**
     * 批量删除仓库信息
     *
     */
    public void testBatchDelWare(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("wareIds", new Long[]{1L,2L});
        map.put("adminName","fb");
        wareHouseMapperMock.returns(1).batchDelWare(map);
        assertEquals(1,wareHouseService.batchDelWare(new Long[]{1L,2L},"fb"));
    }

    /**
     * 查询添加或者修改仓库不需要的id
     *
     */
    public void testSelectCityIdByWareId(){
        customerMapperMock.returns(districtBeanList).selectAllDistrict();
        assertEquals(2,wareHouseService.selectCityIdByWareId(1L).size());
    }

    /**
     * 查询添加或者修改仓库不需要的id
     *
     */
    public void testSelectCityIdByDid(){
        customerMapperMock.returns(cityBeanList).selectAllCity();
        assertEquals(2,wareHouseService.selectCityIdByDid(2L).size());
    }

    /**
     * 判断标识是否存在
     *
     */
    public void testIdentifyIsExist(){
        wareHouseMapperMock.returns(1).identifyIsExist("1");
        assertEquals(1,wareHouseService.identifyIsExist("1"));
    }

    /**
     * 检测仓库名称是否重复
     *
     */
    public void testCheckWareNameHaveId(){
        wareHouseMapperMock.returns(wareHouseList).queryWareCountByWareName("南方仓");
        assertNotNull(wareHouseService.checkWareNameHaveId("南方仓",1L));
    }

    /**
     * 查询仓库ID
     *
     */
    public void testSelectWareIdByDistinctId(){
        wareHouseMapperMock.returns(1L).selectWareIdByDistinctId(1L);
        assertEquals(1, wareHouseService.selectWareIdByDistinctId(1L).longValue());
    }


}
