
package com.ningpai.goods.util;

/**
 * 封装常用的String参数
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午1:38:39
 * @version 1.0
 */
public final class ValueUtil {

    public static final String NULL = "null";
    public static final String CATIDS = "catIds";
    // 默认的删除标记
    // 未删除
    public static final String DEFAULTDELFLAG = "0";
    // 已经删除
    public static final String ALREADYDELFLAG = "1";
    // 获取管理员名称
    public static final String NAME = "name";

    /* 添加日志需要的获取菜单名称 */
    public static final String OPERAPATH = "operaPath";
    // 封装商品类型控制器中出现多次的字符串
    public static final String BRANDIDS = "brandIds";
    public static final String SPECIDS = "specIds";
    public static final String PARAMNAME = "paramname";
    public static final String PARAMNICKNAME = "paramnickname";
    public static final String EXPANDNAMES = "expandnames";
    public static final String EXPANDNICKNAMES = "expandnicknames";
    public static final String EXPANDPARAMSORT = "expandparamsort";
    public static final String ADMIN = "admin";
    // 封装商品控制器中出现多次的字符串
    public static final String ABOUTGOODSID = "aboutGoodsId";
    public static final String EXPANDPARAMID = "expandParamId";
    public static final String EXPANDPARAMVALUE = "expandparamValue";
    public static final String PARAMID = "paramId";
    public static final String PARAMVALUE = "paramValue";
    public static final String EXPANDVALUES = "expandvalues";
    public static final String BRANDLIST = "brandList";
    public static final String CATELIST = "cateList";
    public static final String TAGLIST = "tagList";
    // GoodsRelaGoodsService中的参数
    public static final String RELAGOODSGOODSID = "goodsId";

    public static final String TYPEID = "typeId";
    public static final String SPECID = "specId";
    // 小数点
    public static final String DECI = ".";
    // 反斜杠
    public static final String BACKSLASH = "/";
    public static final String BOTTOMLINE = "_";
    // jpg后缀
    public static final String JPGSUFFIX = ".jpg";

    public static final String STARTROWNUM = "startRowNum";
    public static final String ENDROWNUM = "endRowNum";

    public static final String CONDITION = "condition";
    public static final String SEARCHTEXT = "searchText";

    public static final String SELECTBEAN = "selectBean";

    public static final String SEARCHBEAN = "searchBean";

    // 商品品牌控制器
    // 查询所有的商品品牌 chooseTrademark
    public static final String LISTBRAND = "jsp/system/trademark/temptrademark_list_brand";
    // 查询所有的商品品牌 findAllBrand
    public static final String SEARCHGOODSBRAND = "分页查询商品品牌 ";
    // 查询所有的商品品牌 chooseBrand
    public static final String CHOOSEBRANDLIST = "jsp/channel/brand_list";
    // 查询所有品牌信息 queryallbrand
    public static final String SEARCHBRANDLIST = "查询所有的商品品牌";
    // 插入一条品牌数据 saveBrand
    public static final String SAVEBRANDINFO = "保存品牌信息";
    // 根据商品品牌ID删除商品品牌信息 delBrand
    public static final String DELBRANDINFO = "删除商品品牌信息";
    // 批量删除商品品牌 batchDelBrand
    public static final String BATCHDELBRAND = "批量删除商品品牌信息";
    // 根据ID查询商品品牌对象 Ajax方法 queryBrandById
    public static final String SEARCHBRANINFOBYID = "根据品牌ID查询品牌信息";
    // 更新商品实体 updateBrand
    public static final String UPDATEBRANDINFO = "更新品牌信息";
    // 查询所有的商品品牌并转换为JSON返回 loadAllBrand
    public static final String SEARCHBRANDLISTINFO = "AJAX查询所有的品牌";
    // 验证品牌名称名称是否已经存在 checkBrandName
    public static final String CHECKBRANDINFO = "验证品牌名称是否可用";
    // 去添加商品品牌 toAddBrand
    public static final String ADDBRANDINFO = "跳转到添加品牌的页面";
    public static final String GOODSBRANADDINFO = "jsp/goods/goodsBrandAdd";
    // 去修改商品品牌 toModifyBrand
    public static final String MODIFYBRANDINFO = "跳转到修改品牌的页面";
    public static final String GOODSBRANDMODIFYINFO = "jsp/goods/goodsBrandModify";
    // 商品品牌控制器END

    // 商品关注Controller
    // 查询所有的商品集合 findallgoodsatte
    public static final String FINDALLGOODSATTEINFO = "查询所有的商品关注";
    public static final String GOODSATTEINFO = "jsp/goods/goods_atte";
    public static final String GOODSAUDIT = "jsp/goods/goodsAudit";
    // 根据货品ID查询 findGoodsAtteByProductId
    public static final String FINALGOODSATTEINFO = "根据货品ID查询关注信息";
    public static final String PRODUCTINFO = "jsp/goods/product_atte";
    // 删除商品关注 deletegoodsatte
    public static final String DELGOODSATTEINFO = "删除商品关注";
    public static final String FINDALLGOODSATTE = "findallgoodsatte.htm";
    // 批量删除商品关注 batchdeletegoodsatte
    public static final String BATCHDELGOODSATTEINFO = "批量删除商品关注信息";
    public static final String BATCHFINDALLGOODSATTE = "findallgoodsatte.htm";
    // 商品关注Controller END

    // 商品分类控制器
    // 根据pageBean分页查询所有的商品分类数据 findAllCate
    public static final String FINDALLCATEINFO = "查询所有的商品分类";
    // 查询所有商品分类 queryallgoodcate
    public static final String QUERYALLGOODCATEINFO = "AJAX查询所有的商品分类";
    // 删除商品分类(单个) delGoodsCate
    public static final String DELGOODSCATEINFO = "删除商品分类";
    // 批量删除商品分类 batchDelCate
    public static final String BATCHDELCATEINFO = "批量删除商品分类";
    // 添加商品分类 addGoodsCate
    public static final String ADDGOODSCATEINFO = "添加商品分类";
    // 更新商品分类 updateGoodsCate
    public static final String UPDATEGOODSCATEINFO = "更新商品分类";
    // 根据商品ID查询商品分类信息 AJAX queryCateById
    public static final String QUERYCATEBYIDINFO = "根据分类ID查询分类信息";
    // 查询商品分类 queryGoodsCateVo
    public static final String QUERYGOODSCATEVOINFO = "AJAX查询分类信息";
    // 根据传递的分类ID验证是否存在子类 checkDel
    public static final String CHECKDELINFO = "验证分类是否存在子分类,如果存在就不可删除";
    // 查询所有的商品分类 queryAllCate
    public static final String QUERYALLCATEINFO = "AJAX查询所有的分类信息";
    // 验证分类名称是否可以添加 checkCateName
    public static final String CHECKCATENAMEINFO = "验证分类名称是否已经存在";
    // 商品分类控制器 END

    // 商品控制器
    // 分页查询列表 findAllGoods
    public static final String FINDALLGOODSINFO = "分页查询商品列表";
    // searchGoodsAll
    public static final String SEARCHGOODSALLINFO = "AJAX查询所有的商品列表";
    // 删除商品信息 delGoods
    public static final String DELGOODSINFO = "删除商品信息";
    // 批量删除商品 batchDelGoods
    public static final String BATCHDELGOODSINFO = "批量删除商品信息";
    // 保存商品 saveGoods
    public static final String SAVEGOODSINFO = "保存商品信息";
    // 根据商品分类ID查询相关的商品列表 findGoodByCatId
    public static final String FINDGOODBYCATIDINFO = "AJAX根据分类ID查询商品列表";
    // 根据商品ID查询修改商品时的VO findModifiedGoodsVoByGoodsId
    public static final String FINDMODIFIEDGOODSVOBYGOODSIDINFO = "AJAX根据商品ID查询商品详细信息";
    // 根据商品ID查询商品详情 findDetailGoodsVoByGoodsId
    public static final String FINDDETAILGOODSVOBYGOODSIDINFO = "根据商品ID查询商品详情";
    // 更新商品信息 updateGoods
    public static final String UPDATEGOODSINFO = "更新商品信息";
    // 高级查询 queryByParam
    public static final String QUERYBYPARAMINFO = "高级查询";
    // 验证商品编号是否已经使用 checkGoodsNo
    public static final String CHECKGOODSNOINFO = "AJAX验证商品编号是否可用";
    // 去添加商品 toAddGoods
    public static final String TOADDGOODSINFO = "跳转到添加商品的页面";
    // 去修改商品 toModifyGoods
    public static final String TOMODIFYGOODS = "跳转到修改商品信息的页面";
    // 生成商品的lucene搜索索引 createIndex
    public static final String CREATEINDEXINFO = "创建Lucence索引";
    // 导出商品列表为Excel文件 exportGoodsList
    public static final String EXPORTGOODSLISTINFO = "导出所有的商品信息";
    // 导出当前页显示的所有记录 exportGoodsPageList
    public static final String EXPORTGOODSPAGELISTINFO = "导出当前页的商品数据";
    // 根据选中的商品导出 exportGoodsCheck
    public static final String EXPORTGOODSCHECKINFO = "导出选中的商品数据";
    // 商品控制器 END

    // 商品组合控制器
    // 分页查询商品组合列表 findAllGroup
    public static final String FINDALLGROUPINFO = "分页查询所有的商品组合";
    // 删除组合信息 delGroup
    public static final String DELGROUPINFO = "删除商品组合";
    // 批量删除商品组合 batchDelGroup
    public static final String BATCHDELGROUPINFO = "批量删除商品组合";
    // 添加一个商品组合 saveGroup
    public static final String SAVEGROUPINFO = "保存商品组合";
    // 根据主键查询商品组合信息 queryGoodsGroupById
    public static final String QUERYGOODSGROUPBYIDINFO = "AJAX查询商品组合信息";
    // 更新商品组合信息 updateGroup
    public static final String UPDATEGROUPINFO = "更新商品组合信息";
    // 根据主键查询商品组合ID queryGroupByPrimaryKey
    public static final String QUERYGROUPBYPRIMARYKEYINFO = "根据主键查询商品组合信息";
    // 根据组合ID和productId删除组合关联的货品信息 delGroupReleProduct
    public static final String DELGROUPRELEPRODUCTINFO = "删除组合关联的货品信息";
    // 批量删除组合关联货品 batchDelGroupReleProduct
    public static final String BATCHDELGROUPRELEPRODUCTINFO = "批量删除商品组合关联的货品信息";
    // 根据组合ID和货品ID数组新建关联关系 saveGroupReleProduct
    public static final String SAVEGROUPRELEPRODUCTINFO = "保存商品组合关联的货品信息";
    // 商品组合控制器 END

    // 货品图片控制器
    // 根据货品ID查询关联的图片列表 queryImageListByProductId
    public static final String QUERYIMAGELISTBYPRODUCTIDINFO = "AJAX根据货品ID查询图片集合";
    // 更新货品图片 updateProductImage
    public static final String UPDATEPRODUCTIMAGEINFO = "批量进行操作图片";
    // 批量上传货品图片 batchSaveProductImage
    public static final String BATCHSAVEPRODUCTIMAGEINFO = "AJAX批量保存货品图片";
    // 货品图片控制器 END

    // 商品通知控制器
    // 根据分页参数查询通知列表的控制器 findAllLackRegister
    public static final String FINDALLLACKREGISTERINFO = "分页查询到货通知列表";
    // 批量发送到货通知 batchNotice
    public static final String BATCHNOTICEINFO = "批量进行通知";
    // 根据参数查询 queryLackRegisterByParam
    public static final String QUERYLACKREGISTERBYPARAMINFO = "查询到货通知";
    // 批量删除到货通知 batchDelLack
    public static final String BATCHDELLACKINFO = "批量删除到货通知";
    // 商品通知控制器 END

    // 货品控制器
    // 根据商品ID查询商品下的货品信息 queryAllByGoodsId
    public static final String QUERYALLBYGOODSIDINFO = "根据商品ID分页查询货品列表";
    // 删除货品 delProduct
    public static final String DELPRODUCTINFO = "删除货品";
    // 批量删除货品信息 batchDelProduct
    public static final String BATCHDELPRODUCTINFO = "批量删除货品信息";
    // 批量上架控制器 batchUpload
    public static final String BATCHUPLOADINFO = "批量执行上下架操作";
    // 批量下架控制器 batchDown
    public static final String BATCHDOWNINFO = "批量执行上下架操作";
    // 添加货品信息 saveProduct
    public static final String SAVEPRODUCTINFO = "保存货品信息";
    // 根据货品ID查询货品Vo信息 queryProductVoByProductId
    public static final String QUERYPRODUCTVOBYPRODUCTIDINFO = "AJAX根据货品ID查询货品信息";
    // 更新货品信息 updateProduct
    public static final String UPDATEPRODUCTINFO = "更新货品信息";
    // 更新货品信息 updateProduct
    public static final String UPDATEPRODUCTAUTOSTYLE = "修改适配车型";
    // 查询所有的货品详细信息 queryAllDetailProduct
    public static final String QUERYALLDETAILPRODUCTINFO = "查询对应组合下面所有的货品详细信息";
    // 根据分类ID和品牌ID查询货品列表 queryProductForCoupon
    public static final String QUERYPRODUCTFORCOUPONINFO = "根据分类ID和品牌ID查询货品ID";
    // 根据分类ID和品牌ID查询商品列表 queryProductForCoupon
    public static final String QUERYGOODSFORCOUPONINFO = "根据分类ID和品牌ID查询商品ID";
    // 根据分类ID和品牌ID查询货品列表 queryProductForChannel
    public static final String CHOOSEPRODUCE = "jsp/channel/choose_product";
    // 根据货品ID查询货品
    public static final String QUERYPRODUCTVIEWVOBYPRODUCTIDINFO = "根据货品ID查询货品";
    public static final String PRODUCTDETAIL = "jsp/goods/product_detail";
    // 验证货品编号是否可用 checkProductNo
    public static final String CHECKPRODUCTNOINFO = "AJAX验证货品编号是否可用";
    // 根据参数验证货品是否可以添加 checkParam
    public static final String CHECKPARAMINFO = "AJAX验证货品的参数是否已经生成过";
    // 根据查询标记查询货品行数 queryStockWarnCount
    public static final String QUERYSTOCKWARNCOUNTINFO = "根据查询标记查询货品行数";
    // 根据查询标记查询货品列表 queryProductListBySomeParam
    public static final String QUERYPRODUCTLISTBYSOMEPARAMINFO = "根据查询标记查询货品列表";
    public static final String WELPRODUCTLIST = "jsp/goods/wel_product_list";
    // 导出货品列表 exportProductList
    public static final String EXPORTPRODUCTLISTINFO = "导出货品列表";
    // 导出当前显示的货品页列表 exportProductPage
    public static final String EXPORTPRODUCTPAGEINFO = "导出当前显示的货品页列表";
    // 根据选中的货品导出Excel exportProductByChecked
    public static final String EXPORTPRODUCTBYCHECKEDINFO = "根据选中的货品导出Excel";
    // 货品控制器 END

    // 规格值控制器
    // 更新规格信息 updateSpec
    public static final String UPDATESPECINFO = "更新规格信息";
    // 根据分页参数查询规格 findAllSpec
    public static final String FINDALLSPECINFO = "分页查询所有的规格信息";
    // 单个删除规格 delSpec
    public static final String DELSPECINFO = "删除规格信息";
    // 批量删除商品规格 batchDelSpec
    public static final String BATCHDELSPECINFO = "批量删除规格信息";
    // 根据分类ID查询分类的Vo 返回json对象 queryCateVoById
    public static final String QUERYCATEVOBYIDINFO = "根据规格ID查询规格信息";
    // 查询所有的规格列表 queryAllSpec
    public static final String QUERYALLSPECINFO = "AJAX查询所有的规格信息";
    // 验证规格值是否可用 checkSpecName
    public static final String CHECKSPECNAMEINFO = "AJAX验证规格值是否可用";
    // 规格值控制器 END

    // 商品标签控制 器
    // 根据标签ID查询标签 findGoodsTag
    public static final String FINDGOODSTAGINFO = "根据标签ID查询所有的商品标签";
    // 查询所有的标签列表 findAllTag
    public static final String FINDALLTAGINFO = "分页查询商品标签";
    // 删除标签 deltag
    public static final String DELTAGINFO = "删除商品标签";
    // 添加标签 addTag
    public static final String ADDTAGINFO = "添加商品标签";
    // 批量删除的控制器 batchDelTag
    public static final String BATCHDELTAGINFO = "批量删除商品标签";
    // 根据参数进行模糊查询 {@link java.util.Map} queryTagByParam
    public static final String QUERYTAGBYPARAMINFO = "根据参数进行模糊查询";
    // 更新商品标签 updateTag
    public static final String UPDATETAGINFO = "更新商品标签";
    // 验证标签名称是否可用 checkTagName
    public static final String CHECKTAGNAMEINFO = "验证标签名称是否可用";
    // 商品标签控制器 END

    // 商品类型控制器
    // 根据PageBean 查询分页列表 findAllType
    public static final String FINDALLTYPEINFO = "分页查询类型";
    // 根据类型ID查询类型Vo findTypeVoByTypeId
    public static final String FINDTYPEVOBYTYPEIDINFO = "AJAX根据类型ID查询类型信息";
    // 删除商品类型 delTypeByTypeId
    public static final String DELTYPEBYTYPEIDINFO = "删除商品类型";
    // 批量删除商品类型 batchDelType
    public static final String BATCHDELTYPEINFO = "批量删除商品类型";
    // 保存商品类型 saveType
    public static final String SAVETYPEINFO = "保存商品类型";
    // 根据扩展属性ID 查询扩展属性值列表 queryParamValueByParamId
    public static final String QUERYPARAMVALUEBYPARAMIDINFO = "AJAX根据扩展属性ID查询扩展属性值的列表";
    // 更新商品类型扩展属性值 updateExpandParamValue
    public static final String UPDATEEXPANDPARAMVALUEINFO = "更新商品类型扩展属性值";
    // 根据商品类型ID查询扩展参数 queryParamListByTypeId
    public static final String QUERYPARAMLISTBYTYPEIDINFO = "根据类型ID查询扩展参数";
    // 更新商品类型 updateType
    public static final String UPDATETYPEINFO = "更新商品类型";
    // 根据商品分类ID查询商品类型 queryTypeVoByCatId
    public static final String QUERYTYPEVOBYCATIDINFO = "AJAX根据商品分类ID查询商品类型";
    // 验证商品类型名称是否可用 checkTypeName
    public static final String CHECKTYPENAMEINFO = "AJAX验证商品类型名称是否可用";
    // 商品类型控制器 END

    // 仓库控制器
    // 分页查询仓库信息 queryWareHouseByPageBean
    public static final String QUERYWAREHOUSEBYPAGEBEANINFO = "分页查询仓库信息";
    // 保存仓库信息 saveWareHouse
    public static final String SAVEWAREHOUSEINFO = "保存仓库信息";
    // 删除仓库信息 delWareHouse
    public static final String DELWAREHOUSEINFO = "删除仓库信息";
    // 批量删除仓库信息 batchDelWare
    public static final String BATCHDELWAREINFO = "批量删除仓库信息";
    // 更新仓库信息 updateWareHouse
    public static final String UPDATEWAREHOUSEINFO = "更新仓库信息";
    // 根据仓库ID查询仓库信息 并通过Ajax返回 queryWareHouseByWareId
    public static final String QUERYWAREHOUSEBYWAREIDINFO = "AJAX根据仓库ID查询仓库信息";
    // 验证仓库名称是否可用 checkWareName
    public static final String CHECKWARENAMEINFO = "AJAX验证仓库名称是否可用";
    //
    // 仓库控制器 END

    // 商品关注Service
    public static final String DELETEATTE = "删除关注信息SUCC";
    public static final String BATCHDELATTE = "批量删除关注信息SUCC";
    // 商品关注ServiceEND

    // 商品品牌service实现类
    public static final String DELETEGOODSBRAND = "删除商品品牌SUCC";
    public static final String UPDATEGOODSBRAD = "修改商品品牌SUCC";
    public static final String INSERTGOODSBRAND = "新建商品品牌SUCC";
    public static final String BATCHDELETEGODOSBRAND = "批量删除商品品牌SUCC";
    // END

    // 商品分类service实现
    public static final String INSERTGOODSCATE = "添加商品分类SUCC";
    public static final String DELGOODSCATE = "删除商品分类SUCC";
    public static final String BATCHDELGOODSCATE = "批量删除商品分类SUCC";
    public static final String UPDATEGOODSCATE = "更新商品分类SUCC";
    // END

    // 商品组合关联货品Service
    public static final String DELGROUPRELEPRODUCTBYGROUPIDANDPRODUCTID = "删除商品组合关联货品信息SUCC";
    public static final String BATCHDELGROUPRELEPRODUCTBYGROUPIDANDPRODUCTIDS = "批量删除商品组合关联货品信息SUCC";
    public static final String ADDGROUPRELEPRODUCT = "添加商品组合关联货品信息SUCC";
    // END

    // 商品组合Service实现
    public static final String SAVEGOODSGROUP = "保存商品组合SUCC";
    public static final String DELGOODSGROUP = "删除商品组合SUCC";
    public static final String UPDATEGOODSGROUP = "更新商品组合SUCC";
    // END
    // 货品图片Service实现
    public static final String SAVEGOODSIMAGE = "保存商品图片SUCC";
    public static final String DELGOODSIMAGE = "删除商品图片SUCC";
    public static final String UPDATEGOODSIMAGE = "更新商品图片SUCC";
    public static final String UPLOADIMAGE = "上传商品图片SUCC";
    public static final String SETDEFAULTIMAGE = "设置默认商品图片SUCC";
    public static final String BATCHDELIMAGE = "批量删除商品图片SUCC";
    // END
    // 到货通知Service实现
    public static final String INSERTLACKREG = "新建到货通知SUCC";
    public static final String UPDATELACKREG = "更新到货通知SUCC";
    public static final String UPDATELACKREGISTERSTATUS = "更新到货通知状态SUCC";
    public static final String BATCHDELLACKREG = "批量删除到货通知SUCC";
    public static final String UPDATESTATUSBYPRODUCTID = "根据货品ID更新到货通知状态SUCC";
    // END
    // 商品关联规格值Service实现
    public static final String SAVEPRODUCTRELESPEC = "保存货品关联规格信息";
    public static final String UPDATEPRODUCTRELESPEC = "更新货品关联规格信息SUCC";
    // END
    // 货品信息Service实现
    public static final String QUERYBYGOODSID = "根据商品ID查询货品列表";
    public static final String DELPRODUCTBYPRODUCTID = "根据货品ID删除货品信息";
    public static final String BATCHDELPRODUCT = "批量删除货品SUCC";
    public static final String BATCHUPLOADPRODUCT = "批量上架活泼你SUCC";
    public static final String SAVEPRODUCT = "保存货品SUCC";
    public static final String UPDATEPRODUCT = "更新活泼你SUCC";
    // END
    // 商品关联商品Service实现
    public static final String SAVERELAGODOS = "保存商品关联记录SUCC";
    public static final String DELALLRELAGOODSBYGOODSID = "删除商品关联记录SUCC";
    public static final String UPDATERELAGOODS = "更新商品关联记录SUCC";
    public static final String DELRELAGOODSBYGOODSIDANDRELAGOODSIDS = "删除商品关联记录SUCC";
    // END
    // 商品关联类型扩展参数Service实现
    public static final String SAVEEXPANDPARAM = "保存扩展属性SUCC";
    public static final String UPDATEGOODSRELEEXPANDPARAM = "更新商品关联扩展属性SUCC";
    public static final String DELGOODSRELEEXPANDPARAM = "删除商品关联扩展属性SUCC";
    public static final String DELALLEXPANDPARAMBYGOODSID = "删除商品关联扩展属性SUCC";
    // END
    // 商品关联类型详细参数Service
    public static final String SAVEGOODSRELEPARAM = "保存商品关联类型属性SUCC";
    public static final String UPDATERELEPARAM = "更新商品关联类型属性SUCC";
    public static final String DELALLRELEPARAMBYGOODSID = "删除商品关联类型属性SUCC";
    // END
    // 商品关联标签Service实现
    public static final String SAVERELETAG = "保存关联规格SUCC";
    public static final String UPDATERELETAG = "更新关联规格SUCC";
    public static final String DELETERELETAG = "删除关联规格SUCC";
    // END
    // 商品Service实现
    public static final String DELGOODS = "删除商品信息SUCC";
    public static final String BATCHDELGOODS = "批量删除商品信息SUCC";
    public static final String SAVEGOODS = "保存商品信息SUCC";
    public static final String UPDATEGOODS = "更新商品信息SUCC";
    public static final String BATCHUPLOADORDOWNGOODS = "批量上下架商品信息";
    // END
    // 规格值Service实现
    public static final String SAVESPECDETAIL = "保存规格值SUCC";
    public static final String DELSPECDETAIL = "删除规格值SUCC";
    public static final String UPDATESPECDETAIL = "更新规格值SUCC";
    // END
    // 商品规格Service实现
    public static final String SAVEGOODSSPEC = "保存商品规格SUCC";
    public static final String DELGOODSSPEC = "删除商品规格SUCC";
    public static final String BATCHDELGOODSSPEC = "批量删除商品规格SUCC";
    public static final String UPDATEGOODSSPEC = "更新商品规格SUCC";
    // END
    // 商品标签业务层实现类
    public static final String DELETEGOODSTAG = "删除商品标签SUCC";
    public static final String INSERTTAG = "新建商品标签SUCC";
    public static final String BATCHDELETETAG = "批量删除商品标签SUCC";
    public static final String UPDATETAGSELECTIVE = "更新商品标签SUCC";
    // END
    // 商品关联品牌Service实现
    public static final String INSERTTYPEBRAND = "新建类型关联品牌SUCC";
    public static final String DELTYPEBRAND = "删除类型关联品牌SUCC";
    public static final String UPDATETYPEBRAND = "更新类型关联品牌SUCC";
    public static final String BATCHUPDATETYPEBRAND = "删除类型关联品牌SUCC";
    public static final String UPDATEUNCHECKEDBRAND = "更新类型关联品牌SUCC";
    // END
    // 类型扩展属性Service实现
    public static final String SAVEEXPANDPARAM2 = "保存商品类型扩展属性SUCC";
    public static final String DELEXPANDPARAM = "删除商品类型扩展属性SUCC";
    public static final String UPDATEEXPANDPARAM = "更新商品类型扩展属性SUCC";
    public static final String BATCHUPDATEEXPANDPARAM = "批量更新商品类型扩展属性SUCC";
    public static final String SAVEEXPANDPARAMVALUEWHENINSERTEXPANDPARAM = "保存商品类型扩展属性SUCC";
    // END
    // 商品扩展属性值Service实现
    public static final String SAVEPARAMVALUE = "保存类型扩展属性值SUCC";
    public static final String DELPARAMVALUE = "删除类型扩展属性值SUCC";
    public static final String UPDATEPARAMVALUE = "更新类型扩展属性值SUCC";
    public static final String UPDATEPARAMVALUES = "更新类型扩展属性值SUCC";
    // END
    // 商品类型详细参数Service实现
    public static final String SAVETYPEPARAM = "保存类型参数SUCC";
    public static final String DELTYPEPARAM = "删除类型参数SUCC";
    public static final String UPDATETYPEPARAM = "更新类型参数SUCC";
    public static final String BATCHUPATEPARAM = "批量删除类型参数SUCC";
    // END
    // 商品类型Service
    public static final String SAVEGOODSTYPE = "保存商品类型SUCC";
    public static final String DELGOODSTYPE = "删除商品类型SUCC";
    public static final String UPDATEGOODSTYPE = "更新商品类型SUCC";
    public static final String BATCHDELTYPE = "批量删除商品类型SUCC";
    public static final String SAVETYPEANDPARAM = "保存商品类型SUCC";
    // END
    // 商品类型关联商品规格Service实现
    public static final String SAVETYPESPEC = "保存类型关联规格SUCC";
    public static final String DELTYPESPEC = "删除类型关联规格SUCC";
    public static final String UPDATETYPESPEC = "更新类型关联规格SUCC";
    public static final String BATCHUPDATETYPESPEC = "批量更新类型关联规格SUCC";
    public static final String DELALLRELESPEC = "删除类型关联规格SUCC";
    // END
    // 货品分仓
    public static final String CALCPRODUCTWARE = "保存货品库存信息SUCC";
    // END
    // 仓库Service实现
    public static final String SAVEWAREHOUSE = "保存仓库信息SUCC";
    public static final String UPDATEWAREHOUSE = "更新仓库信息SUCC";
    public static final String BATCHDELWARE = "批量删除仓库信息SUCC";
    // END
    public static final Object DOWNIMPORTEXCEL = "下载导入商品的模板";

    // 促销LOGO控制 器
    // 根据标签ID查询标签 findGoodsTag
    public static final String FINDLOGOINFO = "根据logoID查询所有的促销logo";
    // 查询所有的标签列表 findAllTag
    public static final String FINDALLLOGOINFO = "分页查询促销LOGO";
    // 删除标签 deltag
    public static final String DELLOGOINFO = "删除促销LOGO";
    // 添加标签 addTag
    public static final String ADDLOGOINFO = "添加促销LOGO";
    // 批量删除的控制器 batchDelTag
    public static final String BATCHDELLOGOINFO = "批量删除促销LOGO";
    // 根据参数进行模糊查询 {@link java.util.Map} queryTagByParam
    public static final String QUERYLOGOBYINFO = "根据参数进行模糊查询";
    // 更新商品标签 updateTag
    public static final String UPDATETLOGOINFO = "更新促销LOGO";
    // 验证标签名称是否可用 checkTagName
    public static final String CHECKLOGONAMEINFO = "验证促销LOGO名称是否可用";
    // 促销LOGO控制器 END
    // 拼接token的字符串
    public static final String TOKENPARAM = "?CSRFToken=";
    public static final String PAGEBEANPARAM = "&pageNo=";
    public static final String TOKENPARAM2 = "&CSRFToken=";
    public static final String PAGENO = "&pageNo=";
    public static final String PAGESIZE = "&pageSize=";

    private ValueUtil() {
        super();
    }
}
