
package com.ningpai.goods.util;

/**
 * 封装所有的页面地址
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午4:19:27
 * @version
 */
public final class PathUtil {
    /*
     *商品标签视图JSP
      */
    public static final String GOODSTAG = "jsp/goods/goods_tag";
    /*
     *查询所有标签列表的Controller
      */
    public static final String ALLTAGCONTROLLER = "findAllTag.htm";
    /*
     *商品品牌视图JSP
      */
    public static final String GOODSBRAND = "jsp/goods/goods_brand";
    /*
     *查询所有品牌的控制器
      */
    public static final String ALLBRANDCONTROLLER = "findAllBrand.htm";
    /*
     *商品分类视图JSP
      */
    public static final String GOODSCATE = "jsp/goods/goods_cate";
    /*
     *分页查询商品分类
      */
    public static final String ALLCATECONTROLLER = "findAllCate.htm";
    /*
     *商品到货通知视图JSP
      */
    public static final String GOODSLACKREGISTER = "jsp/goods/goods_arrival_notice";
    /*
     *查询到货通知列表的
      */
    public static final String ALLLACKREGISTERCONTROLLER = "findAllLackRegister.htm";
    /*
     *查询所有规格的控制器
      */
    public static final String ALLSPECCONTROLLER = "findAllSpec.htm";
    /*
     *商品规格视图JSP
      */
    public static final String GOODSSPEC = "jsp/goods/goods_spec";
    /*
     *商品类型的视图
      */
    public static final String GOODSTYPE = "jsp/goods/goods_type";
    /*
     *查询所有商品类型的控制器
      */
    public static final String ALLTYPECONTROLLER = "findAllType.htm";
    /*
     *分页查询商品的控制器
      */
    public static final String ALLGOODSCONTROLLER = "findAllGoods.htm";
    /*
     *商品列表视图
      */
    public static final String GOODSLIST = "jsp/goods/goods_list";
    /*
     *根据商品ID查询货品信息的控制器
      */
    public static final String QUERYPRODUCTBYGOODSID = "queryAllByGoodsId.htm?goodsId=";
    /*
     *查询到的货品的视图
      */
    public static final String GOODSPRODUCT = "jsp/goods/goods_product";
    public static final String GOODSAUDITLIST = "jsp/goods/goodsAuditList";
    /*
     *分页查询商品组合的控制器
      */
    public static final String QUERYALLGROUP = "findAllGroup.htm";
    /*
     *商品组合的视图
      */
    public static final String GOODSGROUP = "jsp/goods/goods_group";
    /*
     *根据商品组合查询关联货品列表的控制器
      */
    public static final String GROUPRELEPRODUCT = "queryGroupByPrimaryKey.htm?groupId=";
    /*
     *商品组合查询关联货品视图
      */
    public static final String GROUPRELEPRODUCTVIEW = "jsp/goods/goods_groupProduct";
    /*
     *商品详情视图
      */
    public static final String GOODSDETAILVIEW = "jsp/goods/goods_detail";
    /*
     *根据参数查询货品列表
      */
    public static final String QUERYPRODUCTLISTBYSOMEPARAM = "queryProductListBySomeParam.htm?flag=";
    /*
     *库房列表
      */
    public static final String WARELIST = "jsp/wareHouse/ware_house";
    /*
     *查询库房信息
      */
    public static final String ALLWARE = "queryWareHouseByPageBean.htm";
    /*
     *查询
      */
    public static final String GOODSPRODUCTSALESRANK = "jsp/goods/goodsproduct_salesrank";
    /*
     *第三方商品列表
      */
    public static final String GOODSLISTISTHIRD = "jsp/goods/goods_list_isthird";
    /*
     *分页查询第三方商品的控制器
      */
    public static final String ALLGOODSCONTROLLERISTHIRD = "findAllGoodsisthird.htm";
    /*
     *分页查询促销LOGO列表
      */
    public static final String ALLLOGOINFO = "jsp/marketing/promotionlogolist";
    /*
     *查询所有促销LOGO列表的Controller
      */
    public static final String ALLLOGOCONTROLLER = "findalllogo.htm";

    private PathUtil() {
        super();
    }

}
