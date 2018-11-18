package com.ningpai.third.grandbrand.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.third.grandbrand.bean.GrandBrand;

/**
 * <p>品牌授权管理Dao</p>
 * @author NINGPAI-LIH
 * @since 2014年5月23日19:27:42
 * @version 2.0
 */
public interface GrandBrandMapper {
    /**
     * 查询该品牌商品的数量
     * @param map
     * @return
     */
    int checkGoodCount(Map<String,Object> map);
    /**
     * 查询行数
     * 
     * @param map
     * @return
     */
    int searchGrandBrandCount(Map<String, Object> map);

    /**
     * 分页查询列表
     * 
     * @return
     */
    List<Object> queryAllThirdGrandBrand(Map<String, Object> map);

    /**
     * 查询第三方品牌列表
     * 
     * @return
     */
    List<Object> queryAllByThirdGoodsBrand(Map<String, Object> map);

    /**
     * 申请品牌
     * 
     * @param grandBrands
     */
    void forTheGoodsBrand(List<GrandBrand> grandBrands);

    /**
     * 未申请品牌列表
     * 
     * @param map
     * @return
     */
    List<Object> forQueryAllThirdGoodsBrand(Map<String, Object> map);

    /**
     * 更改品牌标记为删除
     * 
     * @param goodsBrand 商品品牌
     */
    void updateGrandBrand(GoodsBrand goodsBrand);

    /**
     * 批量修改品牌标记为删除
     * 
     * @param map
     */
    void updateGrandBrands(Map<String, Object> map);

}
