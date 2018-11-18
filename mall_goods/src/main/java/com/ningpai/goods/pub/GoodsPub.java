package com.ningpai.goods.pub;

import com.ningpai.goods.service.*;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 *
 * 商品
 * */
@Repository("goodsPub")
public class GoodsPub {

    // 商品Service
    private GoodsService goodsService;
    // 商品品牌Service
    private GoodsBrandService goodsBrandService;
    // 商品分类Service
    private GoodsCateService goodsCateService;
    // 商品图片Service
    private GoodsImageService goodsImageService;
    // 货品信息Service
    private GoodsProductService goodsProductService;
    // 商品标签Service
    private GoodsTagService goodsTagService;
    // 商品类型Service
    private GoodsTypeService goodsTypeService;
    // 货品关联仓库Service
    private ProductWareService productWareService;
    // 仓库Service
    private WareHouseService wareHouseService;
    // 组合Service
    private GoodsGroupService goodsGroupService;
    // 商品关联标签Service
    private GoodsReleTagService goodsReleTagService;
    // 商品开启规格的Service
    private GoodsOpenSpecService goodsOpenSpecService;

    private GoodsProductSuppService goodsProductSuppService;

    /**
     * 获取商品Service
     * 
     * @return 商品Service {@link GoodsService}
     */
    public GoodsService getGoodsService() {
        return goodsService;
    }

    /**
     * 设置商品Service
     * 
     * @param goodsService
     *            注入的商品Service实体 {@link GoodsService}
     */
    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    /**
     * 获取商品品牌Service
     * 
     * @return 商品品牌Service {@link GoodsBrandService}
     */
    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    /**
     * 设置商品品牌Service
     * 
     * @param goodsBrandService
     *            注入的商品品牌的Service {@link GoodsBrandService}
     */
    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    /**
     * 获取商品分类Service
     * 
     * @return 商品分类Service {@link GoodsCateService}
     */
    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    /**
     * 注入商品分类Service
     * 
     * @param goodsCateService
     *            注入的商品分类的Service {@link GoodsCateService}
     */
    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    /**
     * 获取商品图片Service
     * 
     * @return 商品图片Service {@link GoodsImageService}
     */
    public GoodsImageService getGoodsImageService() {
        return goodsImageService;
    }

    /**
     * 注入商品图片Service
     * 
     * @param goodsImageService
     *            {@link GoodsImageService}
     */
    @Resource(name = "GoodsImageService")
    public void setGoodsImageService(GoodsImageService goodsImageService) {
        this.goodsImageService = goodsImageService;
    }

    /**
     * 获取货品Service
     * 
     * @return 货品Service {@link GoodsProductService}
     */
    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    /**
     * 注入货品Service
     * 
     * @param goodsProductService
     *            待注入的货品Service {@link GoodsProductService}
     */
    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    /**
     * 商品标签Service
     * 
     * @return 商品标签Service {@link GoodsTagService}
     */
    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    /**
     * 注入商品标签Service
     * 
     * @param goodsTagService
     *            待注入的商品标签的Service {@link GoodsTagService}
     */
    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    /**
     * 获取商品类型Service
     * 
     * @return 商品类型Service {@link GoodsTypeService}
     */
    public GoodsTypeService getGoodsTypeService() {
        return goodsTypeService;
    }

    /**
     * 注入商品类型Service
     * 
     * @param goodsTypeService
     *            待注入的商品类型的实体 {@link GoodsTypeService}
     */
    @Resource(name = "GoodsTypeService")
    public void setGoodsTypeService(GoodsTypeService goodsTypeService) {
        this.goodsTypeService = goodsTypeService;
    }

    /**
     * 货品关联仓库Service
     * 
     * @return 货品关联仓库Service {@link ProductWareService}
     */
    public ProductWareService getProductWareService() {
        return productWareService;
    }

    /**
     * 注入货品关联仓库Service
     * 
     * @param productWareService
     *            待注入的货品关联仓库的Service {@link ProductWareService}
     */
    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    /**
     * 获取仓库Service
     * 
     * @return 仓库Service {@link WareHouseService}
     */
    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    /**
     * 注入仓库Service
     * 
     * @param wareHouseService
     *            待注入的仓库Service的实体 {@link WareHouseService}
     */
    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public GoodsGroupService getGoodsGroupService() {
        return goodsGroupService;
    }

    @Resource(name = "GoodsGroupService")
    public void setGoodsGroupService(GoodsGroupService goodsGroupService) {
        this.goodsGroupService = goodsGroupService;
    }

    public GoodsReleTagService getGoodsReleTagService() {
        return goodsReleTagService;
    }

    @Resource(name = "GoodsReleTagService")
    public void setGoodsReleTagService(GoodsReleTagService goodsReleTagService) {
        this.goodsReleTagService = goodsReleTagService;
    }

    public GoodsOpenSpecService getGoodsOpenSpecService() {
        return goodsOpenSpecService;
    }

    @Resource(name = "GoodsOpenSpecService")
    public void setGoodsOpenSpecService(GoodsOpenSpecService goodsOpenSpecService) {
        this.goodsOpenSpecService = goodsOpenSpecService;
    }

    public GoodsProductSuppService getGoodsProductSuppService() {
        return goodsProductSuppService;
    }

    @Resource(name = "GoodsProductSuppService")
    public void setGoodsProductSuppService(GoodsProductSuppService goodsProductSuppService) {
        this.goodsProductSuppService = goodsProductSuppService;
    }
}
