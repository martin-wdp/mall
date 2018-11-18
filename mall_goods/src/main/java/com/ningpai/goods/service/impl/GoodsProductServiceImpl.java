/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.goods.bean.*;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsBulkMapper;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.dao.ImageSetMapper;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.CalcStockUtil;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductDetailVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.goods.vo.GoodsSpecVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

/**
 * 货品信息Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午4:31:46
 * @version 1.0
 */
@Service("GoodsProductService")
public class GoodsProductServiceImpl implements GoodsProductService {

    private static final String GOODSID = "goodsId";
    private static final String GOODSNAME = "goodsName";
    private static final String GOODSNO = "goodsNo";
    private static final String GOODSINFOID = "goodsInfoId";
    private static final String THIRDID = "thirdId";
    private static final String USERNAME = "username";
    private static final String STATUS = "status";
    private static final String PRODUCTIDS = "productIds";
    private static final String SPECREMARK = "specRemark";
    private static final String PRODUCTID = "productId";
    private static final String PRODUCTNO = "productNo";
    private static final String SHOWMOBILE = "showMobile";
    private static final String GOODSINFONAME = "goodsInfoName";
    private static final String PAGENO = "pageNo";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String ISTHIRD = "isThird";

    private static final String LOGGERINFO1 = "分页工具bean的额外参数转换为Map类型错误=》";

    // 货品信息Mapper
    private GoodsProductMapper goodsProductMapper;
    // 货品关联规格值Service
    private GoodsProductReleSpecService goodsProductReleSpecService;
    // 到货通知Service
    private GoodsLackRegisterService lackRegisterService;
    private CascDelMapper cascDelMapper;
    private ProductWareService productWareService;
    /* 服务支持Service */
    @Resource(name = "GoodsProductSuppService")
    private GoodsProductSuppService goodsProductSuppService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsProductServiceImpl.class);

    @Resource(name = "GoodsBulkMapper")
    private GoodsBulkMapper goodsBulkMapper;

    @Resource(name = "GoodsImageSetMapper")
    private ImageSetMapper imageSetMapper;

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsLackRegisterService getLackRegisterService() {
        return lackRegisterService;
    }

    @Resource(name = "GoodsLackRegisterService")
    public void setLackRegisterService(GoodsLackRegisterService lackRegisterService) {
        this.lackRegisterService = lackRegisterService;
    }

    public GoodsProductReleSpecService getGoodsProductReleSpecService() {
        return goodsProductReleSpecService;
    }

    @Resource(name = "GoodsProductReleSpecService")
    public void setGoodsProductReleSpecService(GoodsProductReleSpecService goodsProductReleSpecService) {
        this.goodsProductReleSpecService = goodsProductReleSpecService;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "GoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

    /**
     * 根据商品ID查询货品信息列表
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param pb
     *            封装分页参数的PageBean {@link com.ningpai.util.PageBean}
     * @return {@link com.ningpai.util.PageBean}
     */
    public PageBean queryByGoodsId(Long goodsId, PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 往map集合中放值
            map.put(GOODSID, goodsId);
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            map.put(GOODSNAME, selectBean.getGoodsName());
            map.put(GOODSNO, selectBean.getGoodsNo());
            // int
            // num=this.goodsProductMapper.queryCountByGoodsAndSelectParam(map);
            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryCountByGoodsAndSelectParam(map));
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, Long.parseLong(String.valueOf(pb.getStartRowNum())));
            // 设置结束行
            map.put(ValueUtil.ENDROWNUM, Long.parseLong(String.valueOf(pb.getEndRowNum())));
            // 设置集合数据
            pb.setList(this.goodsProductMapper.queryProductByGoodsId(map));
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYBYGOODSID);
            // 参数置空
            map = null;
        }
        // 返回结果
        return pb;
    }

    /**
     * 
     * 新版》店铺商品列表》查看商品页面搜索
     *
     */
    public PageBean queryByGoodsIdNew(Long goodsId, PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 往map集合放参数
            map.put(GOODSID, goodsId);
            map.put(GOODSNAME, selectBean.getGoodsName());
            map.put(GOODSNO, selectBean.getGoodsNo());
            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryCountByGoodsAndSelectParam(map));
            // 设置开始行
            map.put(ValueUtil.STARTROWNUM, Long.parseLong(String.valueOf(pb.getStartRowNum())));
            // 设置结束行
            map.put(ValueUtil.ENDROWNUM, Long.parseLong(String.valueOf(pb.getEndRowNum())));
            // 设置数据集合
            pb.setList(this.goodsProductMapper.queryProductByGoodsIdNew(map));
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYBYGOODSID);
            // 参数置空
            map = null;
        }
        // 返回结果
        return pb;
    }

    /**
     * 删除货品信息
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int delProductByProductId(Long productId, String username) {
        // 定义一个HashMap
        Map<String, String> map = new HashMap<String, String>();
        int result = 0;
        try {
            // 根据货品ID查询关注表对象
            List<CustomerFollow> customerFollow = goodsProductMapper.selectFollowByGoods(productId);
            if (null != customerFollow && !customerFollow.isEmpty()) {
                for (int i = 0; i < customerFollow.size(); i++) {
                    // 循环删除关注表对应的商品
                    result = goodsProductMapper.deleteFolloById(customerFollow.get(i).getFollowId());
                }
            }
            // 往Map集合中放数据
            map.put("delName", username);
            map.put(GOODSINFOID, productId.toString());
            // 删除货品信息
            result = this.goodsProductMapper.deleteByPrimaryKey(map);
            return result;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELPRODUCTBYPRODUCTID);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 删除第三方单个货品信息
     *
     * @param productId
     * @param thirdId
     * @param username
     * @return
     */
    @Transactional
    public int delThirdProductByProductId(Long productId, Long thirdId, String username) {
        // 定义一个HashMap
        Map<String, String> map = new HashMap<String, String>();
        int result = 0;
        try {
            // 根据货品ID查询关注表对象
            List<CustomerFollow> customerFollow = goodsProductMapper.selectFollowByGoods(productId);
            if (null != customerFollow && !customerFollow.isEmpty()) {
                for (int i = 0; i < customerFollow.size(); i++) {
                    // 循环删除关注表对应的商品
                    result = goodsProductMapper.deleteFolloById(customerFollow.get(i).getFollowId());
                }
            }
            // 往Map集合中放数据
            map.put("delName", username);
            map.put(GOODSINFOID, productId.toString());
            map.put(THIRDID, thirdId.toString());
            // 删除货品信息
            result = this.goodsProductMapper.deleteThirdProductByPrimaryKey(map);
            return result;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELPRODUCTBYPRODUCTID);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 批量删除货品
     * 
     * @param products
     *            待删除的货品ID的集合
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int batchDelProduct(Long[] products, String username) {
        Integer count = 0;
        try {
            for (int i = 0; i < products.length; i++) {
                // 批量删除货品
                count = count + this.delProductByProductId(products[i], username);
            }
            // 返回受影响的行数
            return count;
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELPRODUCT + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            count = null;
        }
    }

    /**
     * 批量上下架货品
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            待修改的货品的ID数组
     * @param status
     *            上架状态 0：下架 1：上架
     * @return 更新的行数
     */
    @Transactional
    public int batchUploadProduct(String username, Long[] productIds, Integer status) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 往map集合中放入参数
            map.put(USERNAME, username);
            map.put(STATUS, status.toString());
            map.put(PRODUCTIDS, productIds);
            // 批量上下架货品
            return this.goodsProductMapper.batchUploadProduct(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHUPLOADPRODUCT + username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 批量显示或者隐藏在列表页
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            货品ID数组
     * @param status
     *            显示状态 0:隐藏 1:显示
     * @return 更新的行数
     */
    public int batchShowOrHide(String username, Long[] productIds, Integer status) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入Map集合中
            map.put(USERNAME, username);
            map.put(STATUS, status.toString());
            map.put(PRODUCTIDS, productIds);
            // 批量显示或者隐藏在列表页方法
            return this.goodsProductMapper.batchShow(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHUPLOADPRODUCT + username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 批量显示或隐藏到手机版
     * 
     * @param username
     *            操作人名称
     * @param productIds
     *            货品ID数组
     * @param status
     *            显示状态 0:隐藏 1:显示
     * @return 更新的行数
     */
    public int batchShowOrHideMobile(String username, Long[] productIds, Integer status) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放进map集合中
            map.put(USERNAME, username);
            map.put(STATUS, status.toString());
            map.put(PRODUCTIDS, productIds);
            // 批量显示或隐藏到手机版方法
            return this.goodsProductMapper.batchShowMobile(map);
        } finally {
            // 打印日志
            LOGGER.info("批量显示到手机版" + username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 添加货品信息
     * 
     * @param product
     *            货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param username
     *            操作人名称
     * @return 插入的货品ID
     */
    @Transactional
    public int saveProduct(GoodsProduct product, String username, String[] specIds, String[] specDetailIds, Map<String, Object> map) {
        // 设置参数
        product.setGoodsInfoCreateName(username);
        product.setGoodsInfoDelflag(ValueUtil.DEFAULTDELFLAG);
        String[] specRemark = null;
        if (map != null && map.get(SPECREMARK) != null) {
                specRemark = (String[]) map.get(SPECREMARK);
        }
        Integer newId = 0;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 从图库中 获取图片
        if (product.getGoodsInfoImgId() != null && !"".equals(product.getGoodsInfoImgId())) {
            // 设置查询条件
            paramMap.put("url", product.getGoodsInfoImgId());
            // 查询图片信息
            InfoImageManage infoImageManage = imageSetMapper.queryImageByUrl(paramMap);
            // 判断是否存在
            if (infoImageManage != null && infoImageManage.getMiddleImgUrl() != null && !"".equals(infoImageManage.getMiddleImgUrl())) {
                // 取中图
                product.setGoodsInfoImgId(infoImageManage.getMiddleImgUrl());
            }

        }

        try {
            // 如果添加货品成功
            if (this.goodsProductMapper.insertSelective(product) > 0) {
                // 获取货品id
                newId = this.goodsProductMapper.selectLastId();
                if (null != specIds && specIds.length > 0) {
                    for (int i = 0; i < specIds.length; i++) {
                        String specR = null;
                        if (specRemark != null && specRemark[i] != null) {
                                specR = specRemark[i];
                        }
                        // 插入一条货品关联规格值记录
                        this.goodsProductReleSpecService.saveProductReleSpec(Long.parseLong(newId.toString()), Long.parseLong(specIds[i]), Long.parseLong(specDetailIds[i]), specR,
                                username);
                    }
                }
            }
            // 打印日志
            LOGGER.info(ValueUtil.SAVEPRODUCT + username);
            return newId;
        } finally {
            newId = null;
            specRemark = null;
        }
    }

    /**
     * 根据主键查询ProductVo
     * 
     * @param productId
     *            货品信息的主键{@link java.lang.Long}
     * @return 查询到的货品VO信息 {@link com.ningpai.goods.vo.GoodsProductVo}
     */
    public GoodsProductVo queryByPrimaryId(Long productId) {
        // 根据主键查询货品Vo
        GoodsProductVo vo = this.goodsProductMapper.selectByPrimaryKey(productId);
        // 根据货品ID查询关联的规格
        List<GoodsSpecVo> goodsSpecVos = goodsProductReleSpecService.querySpecVoByGoodsInfoId(productId);
        if (!goodsSpecVos.isEmpty()) {
            // 查询货品的规格
            vo.setGoodsSpecVo(goodsSpecVos);
        }

        List<GoodsProductSupp> list = null;
        if (null != vo) {
            // 根据货品ID查询所有的关联的支持列表
            list = this.goodsProductSuppService.queryAllSuppByProductId(productId);
        }
        try {
            if (null != list && !list.isEmpty()) {
                vo.setSuppList(list);
            }
            return vo;
        } finally {
            list = null;
            vo = null;
        }
    }

    /**
     * 根据货品ID查询货品适配车型
     *
     * @param productId
     *            货品ID的数组
     * @return 查询到的集合
     */
    public GoodsProduct queryProductAutoStyleByProductId(Long productId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("goodsInfoId", productId);
            // 根据货品id数组查询货品集合
            return this.goodsProductMapper.queryProductAutoStyleByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 更新货品信息
     * 
     * @param goodsProduct
     *            待更新的货品实体 {@link com.ningpai.goods.bean.GoodsProduct}
     * @param username
     *            操作人名称
     * @param specId
     *            规格ID的数组
     * @param specDetailIds
     *            规格值得ID数组
     * @return 更新影响的行数
     */
    @Transactional
    public int updateProduct(GoodsProduct goodsProduct, String username, String[] specId, String[] specDetailIds, String[] specRemark) {
        goodsProduct.setGoodsInfoModifiedName(username);
        // 如果关联的规格不等于空，则更新规格
        if (null != specId && specId.length > 0) {
            for (int i = 0; i < specId.length; i++) {
                String specR = null;
                if (specRemark != null && specRemark[i] != null) {
                        specR = specRemark[i];
                }
                // 更新货品规格信息
                this.goodsProductReleSpecService.updateProductReleSpec(goodsProduct.getGoodsInfoId(), Long.parseLong(specId[i]), Long.parseLong(specDetailIds[i]), specR);
            }
        }
        // 如果库存大于0就发送点击了到货通知的会员
        if (null != goodsProduct.getGoodsInfoStock() && goodsProduct.getGoodsInfoStock() > 0) {
            // 发送到货通知
            this.lackRegisterService.updateStatusByProductId(goodsProduct.getGoodsInfoId());
        }
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEPRODUCT + username);
        // 更新货品信息，并返回结果
        return this.goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
    }

    /**
     * 更新货品适配车型信息
     *
     * @param goodsInfoId
     *            货品编号
     * @param autoStyleIdLiYangID OEM的适配车型(力扬ID)
     * @param goodsInfoAutoStyle 力扬压缩ID
     * @return 更新影响的行数
     */
    public int updateProductAutoStyle(String goodsInfoId, String autoStyleIdLiYangID,String goodsInfoAutoStyle){
        if(StringUtils.isEmpty(autoStyleIdLiYangID) && StringUtils.isEmpty(goodsInfoAutoStyle)){
            return 0;
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(GOODSINFOID,goodsInfoId);
        map.put("autoStyleIdLiYangID",autoStyleIdLiYangID);
        map.put("goodsInfoAutoStyle",goodsInfoAutoStyle);
        return goodsProductMapper.updateAutoStyleByGoodsInfoId(map);
    }

    /**
     * 根据PageBean查询货品详细信息
     * 
     * @param pb
     *            PageBean {@link com.ningpai.util.PageBean}
     * @return pageBean {@link com.ningpai.util.PageBean}
     */
    public PageBean queryProductDetailInfoByPageBean(Long groupId, PageBean pb) {
        // 定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            // 把参数放入Map集合中
            map.put(ValueUtil.STARTROWNUM, Long.parseLong(String.valueOf(pb.getStartRowNum())));
            map.put(ValueUtil.ENDROWNUM, Long.parseLong(String.valueOf(pb.getEndRowNum())));
            map.put("groupId", groupId);
            // 设置行数
            pb.setRows(this.goodsProductMapper.queryTotalCountWithGroupId(map));
            // 设置数据集合
            pb.setList(this.goodsProductMapper.queryProductListByPageBean(map));
            // 返回结果
            return pb;
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据货品ID查询货品预览页的Vo
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    public GoodsProductDetailViewVo queryViewVoByProductId(Long productId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put(PRODUCTID, productId);
            // 根据货品id查询货品预览页的Vo
            return this.goodsProductMapper.queryByProductId(map);
        } finally {
            map = null;
        }
    }
    /**
     * 根据货品ID查询货品预览页的Vo
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的Vo {@link com.ningpai.goods.vo.GoodsProductDetailViewVo}
     */
    public GoodsProductDetailViewVo simpleQueryViewVoByProductId(Long productId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put(PRODUCTID, productId);
            // 根据货品id查询货品预览页的Vo
            return this.goodsProductMapper.simpleQueryByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 验证货品编号是否可用
     * 
     * @param productNo
     *            待验证的货品编号 {@link java.lang.String}
     * @return 是否已经存在 true表示可用 false表示不可用
     */
    public boolean checkProuctNo(String productNo) {
        // 验证货品编号是否可用
        return this.goodsProductMapper.queryCountByProductNo(productNo) > 0 ? false : true;
    }

    /**
     * 验证所选的参数是否已经生成了货品
     * 
     * @param map
     *            参数 {@link java.util.Map}
     * @return 可用返回true 不可用返回false
     */
    public boolean checkProductParams(Map<String, Object> map) {
        // 验证所选的参数是否已经生成了货品
        return this.goodsProductMapper.queryCountByParam(map) > 0 ? false : true;
    }

    /**
     * 根据分类ID和品牌Id查询货品列表
     * 
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     */
    public PageBean queryProductForCoupon(PageBean pb, String[] catIds, String[] brandIds, String goodsInfoName, Long thirdId, String productNo) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (catIds != null && catIds.length != 0) {
                List<Long> list1 = new ArrayList<Long>();
                String[] temp = catIds[0].split(",");
                if (temp != null && temp.length != 0) {
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null && !"".equals(temp[i]) && !ValueUtil.NULL.equals(temp[i])) {
                            list1.add(Long.valueOf(temp[i]));
                        }
                    }
                }

                map.put(ValueUtil.CATIDS, !list1.isEmpty() ? list1 : null);
            } else {
                map.put(ValueUtil.CATIDS, null);
            }
            if (brandIds != null && brandIds.length != 0) {
                List<Long> list2 = new ArrayList<Long>();
                String[] temp1 = brandIds[0].split(",");
                if (temp1 != null && temp1.length != 0) {
                    for (int i = 0; i < temp1.length; i++) {
                        if (temp1[i] != null && !"".equals(temp1[i]) && !ValueUtil.NULL.equals(temp1[i])) {
                            list2.add(Long.valueOf(temp1[i]));
                        }
                    }
                }

                map.put(ValueUtil.BRANDIDS, !list2.isEmpty() ? list2 : null);
            } else {
                map.put(ValueUtil.BRANDIDS, null);
            }
            if (null != thirdId) {
                map.put(THIRDID, thirdId.toString());
            }
            if (null != productNo && !"".equals(productNo)) {
                map.put(PRODUCTNO, productNo);
            }
            // 判断是否只显示手机商品
            if (null != pb.getObjectBean() && pb.getObjectBean() instanceof Map) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, String> extraParam = (Map<String, String>) pb.getObjectBean();
                    String showMobile = extraParam.get(SHOWMOBILE);
                    if ("1".equals(showMobile)) {
                        map.put(SHOWMOBILE, 1);
                    }
                } catch (Exception e) {
                    // 打印日志
                    LOGGER.error("分页工具参数转换为Map类型错误=》" + e.getLocalizedMessage(), e);
                }
            }
            if (goodsInfoName != null) {
                map.put(GOODSINFONAME, goodsInfoName);
            }
            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryCountForCoupon(map));
            // 把参数放进map集合中
            map.put(PAGENO, pb.getPageNo());
            map.put(STARTROWNUM, pb.getStartRowNum());
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 设置集合数据
            pb.setList(this.goodsProductMapper.queryProductForCoupon(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 查询第三方货品
     *
     * @param pb
     * @return pageBean
     * */
    @Override
    public PageBean queryThirdProduct(PageBean pb, Map<String, Object> map) {
        // 新third使用 -焦冬至
        // 设置总行数
        pb.setRows(this.goodsProductMapper.queryCountThirdProduct(map));
        // 设置当前页码
        map.put(PAGENO, pb.getPageNo());
        // 设置开始行数
        map.put(STARTROWNUM, pb.getStartRowNum());
        // 设置结束行数
        map.put(ENDROWNUM, pb.getEndRowNum());
        // 设置集合数据
        pb.setList(this.goodsProductMapper.queryThirdProduct(map));
        // 返回结果
        return pb;
    }

    /**
     * 根据分类ID和品牌Id查询货品列表
     * 
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     */
    public PageBean queryProductForCouponLife(PageBean pb, String[] catIds, String[] brandIds, Long thirdId, String productNo, String productName, Long brandId, Long catId,
            Integer haveStock,String flag) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (catIds != null && catIds.length != 0) {
                List<Long> list1 = new ArrayList<Long>();
                String[] temp = catIds[0].split(",");
                if (temp != null && temp.length != 0) {
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null && !"".equals(temp[i]) && !ValueUtil.NULL.equals(temp[i])) {
                            list1.add(Long.valueOf(temp[i]));
                        }
                    }
                }

                map.put(ValueUtil.CATIDS, !list1.isEmpty() ? list1 : null);
            } else {
                map.put(ValueUtil.CATIDS, null);
            }
            if (brandIds != null && brandIds.length != 0) {
                List<Long> list2 = new ArrayList<Long>();
                String[] temp1 = brandIds[0].split(",");
                if (temp1 != null && temp1.length != 0) {
                    for (int i = 0; i < temp1.length; i++) {
                        if (temp1[i] != null && !"".equals(temp1[i]) && !ValueUtil.NULL.equals(temp1[i])) {
                            list2.add(Long.valueOf(temp1[i]));
                        }
                    }
                }

                map.put(ValueUtil.BRANDIDS, !list2.isEmpty() ? list2 : null);
            } else {
                map.put(ValueUtil.BRANDIDS, null);
            }

            if(!"1".equals(flag)){


            if (null != thirdId && thirdId !=-1) {
                map.put(THIRDID, thirdId.toString());
                map.put(ISTHIRD, '1');
            } else {
                map.put(ISTHIRD, '0');
            }
            }
            if (null != productNo && !"".equals(productNo)) {
                map.put(PRODUCTNO, productNo);
            }
            if (null != productName && !"".equals(productName)) {
                map.put(GOODSINFONAME, productName);
            }
            if (null != productName && !"".equals(productName)) {
                map.put(GOODSINFONAME, productName);
            }
            if (null != haveStock) {
                map.put("haveStock", haveStock);
            }
            // 判断是否只显示手机商品
            if (null != pb.getObjectBean() && pb.getObjectBean() instanceof Map) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, String> extraParam = (Map<String, String>) pb.getObjectBean();
                    String showMobile = extraParam.get(SHOWMOBILE);
                    if ("1".equals(showMobile)) {
                        map.put(SHOWMOBILE, 1);
                    }
                } catch (Exception e) {
                    // 打印日志
                    LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage(), e);
                }
            }
            // 新Boss使用 -郭广楠

            List<Long> bd = new ArrayList<Long>();
            if (brandId != null) {
                bd.add(brandId);
                map.put(ValueUtil.BRANDIDS, !bd.isEmpty() ? bd : null);
            }

            List<Long> cs = new ArrayList<Long>();
            if (catId != null) {
                cs.add(catId);
                map.put(ValueUtil.CATIDS, !cs.isEmpty() ? cs : null);
            }
            if(catIds != null && catIds.length > 0){
                    map.put("cateIds",catIds);
            }

            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryCountForCoupon(map));
            // 设置当前页码
            map.put(PAGENO, pb.getPageNo());
            // 设置开始行数
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 设置结束行数
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 设置数据集合
            pb.setList(this.goodsProductMapper.queryProductForCoupon(map));
            // 返回结果
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据分类ID和品牌Id查询货品列表
     * 
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     */
    public PageBean queryProductForCoupon(PageBean pb, String[] catIds, String[] brandIds, Long thirdId, String productNo, Long marketType, String sTime, String eTime,
            String searchText) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != marketType) {
                map.put("marketType", marketType);
            }
            if (null != sTime) {
                map.put("sTime", sTime);
            }
            if (null != eTime) {
                map.put("eTime", eTime);
            }
            if (catIds != null && catIds.length != 0) {
                List<Long> list1 = new ArrayList<Long>();
                String[] temp = catIds[0].split(",");
                if (temp != null && temp.length != 0) {
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null && !"".equals(temp[i]) && !ValueUtil.NULL.equals(temp[i])) {
                            list1.add(Long.valueOf(temp[i]));
                        }
                    }
                }

                map.put(ValueUtil.CATIDS, !list1.isEmpty() ? list1 : null);
            } else {
                map.put(ValueUtil.CATIDS, null);
            }
            if (brandIds != null && brandIds.length != 0) {
                List<Long> list2 = new ArrayList<Long>();
                String[] temp1 = brandIds[0].split(",");
                if (temp1 != null && temp1.length != 0) {
                    for (int i = 0; i < temp1.length; i++) {
                        if (temp1[i] != null && !"".equals(temp1[i]) && !ValueUtil.NULL.equals(temp1[i])) {
                            list2.add(Long.valueOf(temp1[i]));
                        }
                    }
                }

                map.put(ValueUtil.BRANDIDS, !list2.isEmpty() ? list2 : null);
            } else {
                map.put(ValueUtil.BRANDIDS, null);
            }
            if (null != thirdId) {
                map.put(THIRDID, thirdId.toString());
                map.put(ISTHIRD, '1');
            } else {
                map.put(ISTHIRD, '0');
            }
            if (null != searchText) {
                map.put("searchText", searchText);
            }
            if (null != productNo && !"".equals(productNo)) {
                map.put(PRODUCTNO, productNo);
            }
            // 判断是否只显示手机商品
            if (null != pb.getObjectBean() && pb.getObjectBean() instanceof Map) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, String> extraParam = (Map<String, String>) pb.getObjectBean();
                    String showMobile = extraParam.get(SHOWMOBILE);
                    if ("1".equals(showMobile)) {
                        map.put(SHOWMOBILE, 1);
                    }
                } catch (Exception e) {
                    // 打印日志
                    LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage(), e);
                }
            }
            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryMarketingProductCount(map));
            // 设置开始行数
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 设置结束行数
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 设置集合数据
            pb.setList(this.goodsProductMapper.queryMarketingProduct(map));
            // 返回结果
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 查询库存预警额货品个数
     * 
     * @return
     */
    public int queryStockWarnCount(Integer flag, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put("flag", flag);
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            // 查询出库存的预警货品个数
            return this.goodsProductMapper.queryStockWarnCount(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据参数查询货品的列表并返回PageBean
     */
    public PageBean queryProductListBySomeParam(Integer flag, PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放进map集合中
            map.put("flag", flag);
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            // 设置总行数
            pb.setRows(this.goodsProductMapper.queryStockWarnCount(map));
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            // 设置结束行
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            // 设置数据集合
            pb.setList(this.goodsProductMapper.queryProductListBySomeParam(map));
            // 返回结果
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品Id和查询参数查询货品列表for 导出数据
     * 
     * @param goodsId
     *            商品ID
     * @param selectBean
     *            查询帮助Bean
     * @return 查询到的集合
     */
    public List<Object> queryAllProductByGoodsIdForExport(Long goodsId, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put(GOODSID, goodsId);
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            map.put(ValueUtil.STARTROWNUM, 0);
            map.put(ValueUtil.ENDROWNUM, 1000000);
            // 执行 根据商品Id和查询参数查询货品列表for 导出数据 方法
            return this.goodsProductMapper.queryProductByGoodsId(map);
        } finally {
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据货品ID数组查询货品集合
     * 
     * @param productIds
     *            货品ID的数组
     * @return 查询到的集合
     */
    public List<Object> queryAllProductByProductIdsForExport(Long[] productIds) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(PRODUCTIDS, productIds);
            // 根据货品id数组查询货品集合
            return this.goodsProductMapper.queryProductsByProductIds(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询今天上架的商品
     * 
     * @param thirdId
     *            第三方标记{@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    public int queryTodayProCount(Long thirdId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(THIRDID, thirdId);
            // 查询今天上架的商品
            return this.goodsProductMapper.queryTodayProCount(map);
        } finally {
            map = null;
        }
    }

    /**
     * 
     * @param pb
     *            分页bean
     * @param goodsInfoIds
     *            商品id数组
     * @return 查询到的集合
     * @author NINGPAI-LIH
     */
    @Override
    public PageBean queryProductForCouponByGoodsInfoIds(PageBean pb, Long[] goodsInfoIds) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放进map集合中
        map.put("goodsInfoIds", goodsInfoIds);
        // 设置pageBean总行数
        pb.setRows(this.goodsProductMapper.queryCountForCouponByGoodsInfoIds(map));
        // 设置PageBean的开始行
        map.put(STARTROWNUM, pb.getStartRowNum());
        // 设置PageBean的结束行
        map.put(ENDROWNUM, pb.getEndRowNum());
        // 设置PageBean的集合数据
        pb.setList(this.goodsProductMapper.queryProductForCouponByGoodsInfoIds(map));
        // 返回PageBean
        return pb;
    }

    /**
     * 根据分类ID和品牌Id查询货品列表 查询到的是boss的商品
     * 
     * @param pb
     *            分页参数
     * @param catIds
     *            分类ID
     * @param brandIds
     *            品牌ID数组
     * @return 分页参数
     * 
     * @author NINGPAI-LIH
     */
    @Override
    public PageBean queryProductForCouponByThird(PageBean pb, String[] catIds, String[] brandIds, Long thirdId, Long productNo) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (catIds != null && catIds.length != 0) {
                List<Long> list1 = new ArrayList<Long>();
                String[] temp = catIds[0].split(",");
                if (temp != null && temp.length != 0) {
                    for (int i = 0; i < temp.length; i++) {
                        if (temp[i] != null && !"".equals(temp[i]) && !ValueUtil.NULL.equals(temp[i])) {
                            list1.add(Long.valueOf(temp[i]));
                        }
                    }
                }
                map.put(ValueUtil.CATIDS, !list1.isEmpty() ? list1 : null);
            } else {
                map.put(ValueUtil.CATIDS, null);
            }
            if (brandIds != null && brandIds.length != 0) {
                List<Long> list2 = new ArrayList<Long>();
                String[] temp1 = brandIds[0].split(",");
                if (temp1 != null && temp1.length != 0) {
                    for (int i = 0; i < temp1.length; i++) {
                        if (temp1[i] != null && !"".equals(temp1[i]) && !ValueUtil.NULL.equals(temp1[i])) {
                            list2.add(Long.valueOf(temp1[i]));
                        }
                    }
                }

                map.put(ValueUtil.BRANDIDS, !list2.isEmpty() ? list2 : null);
            } else {
                map.put(ValueUtil.BRANDIDS, null);
            }
            if (null != thirdId) {
                map.put(THIRDID, thirdId);
            }
            if (null != productNo) {
                map.put(PRODUCTNO, productNo);
            }
            // 设置PageBean的集合数据
            pb.setList(this.goodsProductMapper.queryProductForCouponByThird(map));
            // 返回PageBean
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 减少库存
     * 
     * @param list
     *            计算库存辅助Bean集合
     * @return 更新的行数
     */
    public int minStock(List<CalcStockUtil> list) {
        Integer count = 0;
        CalcStockUtil stoUtil = null;
        // 定义一个Hashmap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    map.clear();
                    stoUtil = list.get(i);
                    map.put(PRODUCTID, stoUtil.getProductId());
                    map.put("stock", stoUtil.getStock());
                    if ("1".equals(stoUtil.getIsThird())) {
                        // 执行减少 库存方法
                        count += this.goodsProductMapper.minBaseStock(map);
                    } else {
                        map.put("distinctId", stoUtil.getDistinctId());
                        // 执行减少 库存方法
                        count += this.goodsProductMapper.minStockToWare(map);
                    }
                }
            }
            return count;
        } finally {
            count = null;
            stoUtil = null;
            map = null;
        }
    }

    /**
     * 增加库存
     * 
     * @param list
     *            计算库存辅助Bean集合
     * @return 更新的行数
     */
    public int plusStock(List<CalcStockUtil> list) {
        Integer count = 0;
        CalcStockUtil stoUtil = null;
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    map.clear();
                    stoUtil = list.get(i);
                    map.put(PRODUCTID, stoUtil.getProductId());
                    map.put("stock", stoUtil.getStock());
                    if ("1".equals(stoUtil.getIsThird())) {
                        // 执行 增加库存方法
                        count += this.goodsProductMapper.plusBaseStock(map);
                    } else {
                        map.put("distinctId", stoUtil.getDistinctId());
                        // 执行 增加库存方法
                        count += this.goodsProductMapper.plusStockToWare(map);
                    }
                }
            }
            return count;
        } finally {
            count = null;
            stoUtil = null;
            map = null;
        }
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    @Override
    public int updateFollow(CustomerFollow customerFollow) {
        return this.goodsProductMapper.updateFollow(customerFollow);
    }

    @Override
    public int auditProductAction(Long goodsInfoId) {
        return this.goodsProductMapper.auditProductAction(goodsInfoId);
    }

    /**
     * 拒绝审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    @Override
    public int refuseAuditProductAction(Long goodsInfoId, String refuseReason) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put(GOODSINFOID, goodsInfoId);
        map.put("refuseReason", refuseReason);
        // 拒绝审核通过
        return this.goodsProductMapper.refuseAuditProductAction(map);
    }

    /**
     * 获取货品审核信息
     * 
     * @param goodsId
     * @param pb
     * @param selectBean
     * @return
     */
    @Override
    public PageBean queryAuditByGoodsId(Long goodsId, PageBean pb, SelectBean selectBean) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(GOODSID, goodsId);
            // 设置查询条件
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            // 设置查询文本
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            map.put(GOODSNAME, selectBean.getGoodsName());
            map.put(GOODSNO, selectBean.getGoodsNo());
            // 查询总行数
            pb.setRows(this.goodsProductMapper.queryCountByGoodsAndSelectParam(map));
            // 设置开始行数
            map.put(ValueUtil.STARTROWNUM, Long.parseLong(String.valueOf(pb.getStartRowNum())));
            // 设置结束行数
            map.put(ValueUtil.ENDROWNUM, Long.parseLong(String.valueOf(pb.getEndRowNum())));
            // 设置PageBean的集合数据
            pb.setList(this.goodsProductMapper.queryAuditProductByGoodsId(map));
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYBYGOODSID);
            // 参数置空
            map = null;
        }
        return pb;
    }

    /**
     * 批量审核上架
     * 
     * @param username
     * @param productIds
     * @param status
     * @param auditStatus
     * @return
     */
    @Override
    public int batchAuditUploadProduct(String username, Long[] productIds, Integer status, String auditStatus) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put(USERNAME, username);
            map.put(STATUS, status.toString());
            map.put("auditStatus", auditStatus);
            map.put(PRODUCTIDS, productIds);
            // 批量审核上架
            return this.goodsProductMapper.batchAuditUploadProduct(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.BATCHUPLOADPRODUCT + username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 查询货品销售排行
     * 
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public PageBean queryGoodsProductSalesRank(PageBean pageBean, String startTime, String endTime) {
        // 定义一个HashMap集合
        Map<String, Object> paraMap = new HashMap<String, Object>();
        // 把参数放入map集合中
        paraMap.put("startTime", startTime);
        paraMap.put("endTime", endTime);
        paraMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paraMap.put(ENDROWNUM, pageBean.getEndRowNum());
        // 设置PageBean的总行数
        pageBean.setRows(goodsProductMapper.selectAllSize(paraMap));
        // 设置PageBean的集合数据
        pageBean.setList(goodsProductMapper.queryGoodsProductSalesRank(paraMap));
        // 返回PageBean
        return pageBean;
    }

    /**
     * 供应商上传货品
     * 
     * @param product
     * @param username
     * @param specIds
     * @param specDetailIds
     * @param map
     * @return
     */
    @Override
    public int saveSupplierProduct(GoodsProduct product, String username, String[] specIds, String[] specDetailIds, Map<String, Object> map, Long thirdId) {
        // 设置参数
        product.setGoodsInfoCreateName(username);
        product.setGoodsInfoDelflag(ValueUtil.DEFAULTDELFLAG);
        String[] specRemark = null;
        if (map != null && map.get(SPECREMARK) != null) {
                specRemark = (String[]) map.get(SPECREMARK);
        }
        Integer newId = 0;
        try {
            if (this.goodsProductMapper.insertSelective(product) > 0) {
                newId = this.goodsProductMapper.selectLastId();
                if (null != specIds && specIds.length > 0) {
                    for (int i = 0; i < specIds.length; i++) {
                        String specR = null;
                        if (specRemark != null && specRemark[i] != null) {
                                specR = specRemark[i];
                        }
                        this.goodsProductReleSpecService.saveProductReleSpec(Long.parseLong(newId.toString()), Long.parseLong(specIds[i]), Long.parseLong(specDetailIds[i]), specR,
                                username);
                    }
                }
                /* 获取起批设置 */
                String[] bulkStart = (String[]) map.get("bulkStart");
                String[] bulkEnd = (String[]) map.get("bulkEnd");
                String[] bulkPrice = (String[]) map.get("bulkPrice");
                if (bulkStart != null && bulkStart.length > 0 && bulkEnd != null && bulkEnd.length > 0 && bulkPrice != null && bulkPrice.length > 0) {
                    for (int i = 0; i < bulkStart.length; i++) {
                        GoodsBulk goodsBulk = new GoodsBulk();
                        goodsBulk.setBulkStart(Long.parseLong(bulkStart[i]));
                        goodsBulk.setBulkEnd(Long.parseLong(bulkEnd[i]));
                        goodsBulk.setBulkPrice(new BigDecimal(bulkPrice[i]));
                        goodsBulk.setGoodsInfoId(Long.parseLong(newId.toString()));
                        goodsBulk.setThirdId(thirdId);
                        goodsBulk.setDelFlag("0");
                        goodsBulk.setCreateTime(new Date());
                        // 插入数据
                        goodsBulkMapper.insertGoodsBulk(goodsBulk);
                    }
                }
            }
            // 打印日志
            LOGGER.info(ValueUtil.SAVEPRODUCT + username);
            return newId;
        } finally {
            newId = null;
            specRemark = null;
        }
    }

    /**
     * 根据商品ID 获取商品对象
     * 
     * @param goodsId
     * @return
     */
    @Override
    public Goods selectGoodsByGoodsId(Long goodsId) {
        // 根据商品ID 获取商品对象
        return goodsProductMapper.selectGoodsByGoodsId(goodsId);
    }

    /**
     * 根据map获取货品信息
     * 
     * @param map
     * @return
     */
    @Override
    public GoodsProduct queryProductById(Map<String, Object> map) {
        // 根据map获取货品信息
        return goodsProductMapper.queryProductById(map);
    }

    /**
     * 根据productId获取货品信息
     * 
     * @param productId
     * @return
     */
    @Override
    public GoodsProduct queryProductByGoodsId(Long productId) {
        // 根据productId获取货品信息
        return goodsProductMapper.queryProductByGoodsId(productId);
    }

    /**
     * 根据商品id查询所有货品信息
     * 
     * @return
     */
    @Override
    public List<GoodsProduct> queryProductsByGoodsId(Long goodsId) {
        // 根据商品id查询所有货品信息
        return goodsProductMapper.queryProductsByGoodsId(goodsId);
    }

    /**
     * 同步修改
     * 
     * @param goodsInfoId
     * @param goodsSubtitle
     * @return
     */
    @Override
    public int updateGoodsSubtitleById(Long goodsInfoId, String goodsSubtitle) {
        // 定义一个Hashmap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合中
        map.put(GOODSINFOID, goodsInfoId);
        map.put("goodsSubtitle", goodsSubtitle);
        // 同步修改
        return goodsProductMapper.updateGoodsSubtitleById(map);
    }

    /**
     * 根据商品id，查询货品详细列表
     * 
     * @param goodsId
     *            商品id
     * @return
     */
    @Override
    public List<GoodsProductVo> queryProductListByGoodsId(Long goodsId) {
        // 根据商品id，查询货品详细列表
        return goodsProductMapper.queryProductListByGoodsId(goodsId);
    }

    /**
     * 查询第三方货品列表
     * 
     * @param thirdId
     *            第三方商家Id
     * @return list
     */
    @Override
    public List<GoodsProductDetailVo> queryProductForMarketing(Long thirdId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合
        map.put(THIRDID, thirdId);
        // 查询第三方货品列表
        return goodsProductMapper.queryProductForMarketing(map);
    }

    /**
     * 新查询货品列表 分页
     * 
     * @param pb
     *            PageBean{@link com.ningpai.util.PageBean}
     * @param thirdId
     *            店铺ID{@link java.lang.Long}
     * @param searchBean
     *            查询条件对象{@link com.ningpai.goods.util.GoodsSearchBean}
     * @return pb
     */
    @Override
    public PageBean newQueryProductList(PageBean pb, Long thirdId, GoodsSearchBean searchBean,BigDecimal offValue) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把参数放入map集合
        map.put(THIRDID, thirdId);
        map.put(GOODSNAME, searchBean.getGoodsName());
        map.put("goodstNo", searchBean.getGoodsNo());
        map.put(PRODUCTNO, searchBean.getGoodsInfoItemNo());
        map.put("goodsInfoAdded", "1");
        map.put("lowGoodsInfoPrice", searchBean.getLowGoodsInfoPrice());
        map.put("highGoodsInfoPrice", searchBean.getHighGoodsInfoPrice());
        map.put("offValue",offValue);
        // 查询货品数目
        int rows = goodsProductMapper.newQueryProductForMarketingCount(map);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        map.put(STARTROWNUM, pb.getStartRowNum());
        map.put(ENDROWNUM, pb.getEndRowNum());
        // 设置PageBean的集合数据
        pb.setList(goodsProductMapper.newQueryProductForMarketing(map));
        // 返回PageBean
        return pb;
    }

    /**
     * 
     * @param pb
     * @param thirdId
     * @param searchBean
     * @return
     */
    @Override
    public PageBean queryBossProductList(PageBean pb, Long thirdId, GoodsSearchBean searchBean, Long marketType) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != marketType) {
                map.put("marketType", marketType);
            }
            // 第三方ID
            if (null != thirdId) {
                map.put(THIRDID, thirdId.toString());
                // 第三方id不为空查询地方货品
                map.put(ISTHIRD, '1');
            } else {
                // 否则查询boss货品
                map.put(ISTHIRD, '0');
            }
            // 商品名称
            map.put(GOODSNAME, searchBean.getGoodsName());
            // 商品编号
            map.put("goodstNo", searchBean.getGoodsNo());
            // 货品编号
            map.put(PRODUCTNO, searchBean.getGoodsInfoItemNo());
            // 价格查询区间 最低价
            map.put("lowGoodsInfoPrice", searchBean.getLowGoodsInfoPrice());
            // 最高价
            map.put("highGoodsInfoPrice", searchBean.getHighGoodsInfoPrice());
            // 判断是否只显示手机商品
            if (null != pb.getObjectBean() && pb.getObjectBean() instanceof Map) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, String> extraParam = (Map<String, String>) pb.getObjectBean();
                    String showMobile = extraParam.get(SHOWMOBILE);
                    if ("1".equals(showMobile)) {
                        map.put(SHOWMOBILE, 1);
                    }
                } catch (Exception e) {
                    // 打印日志
                    LOGGER.error(LOGGERINFO1 + e.getLocalizedMessage(), e);
                }
            }
            // 设置PageBean的总行数
            pb.setRows(this.goodsProductMapper.queryCountForCoupon(map));
            // 把当前页码放入map集合中
            map.put(PAGENO, pb.getPageNo());
            // 把开始行放入map集合中
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 把结束行放入map集合中
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 设置PageBean的集合数据
            pb.setList(this.goodsProductMapper.queryProductForCoupon(map));
            // 返回PageBean
            return pb;
        } finally {
            map = null;
        }
    }

    /*
     * 查询商品ID下的所有货品
     * 
     * @see
     * com.ningpai.goods.service.GoodsProductService#selectInfoIdList(java.lang
     * .Long[])
     */
    @Override
    public List<Long> selectInfoIdList(Long[] goodsIds) {
        // 申明
        List<Long> list = new ArrayList<Long>();
        for (Long s : goodsIds) {
            // 加入ID
            list.add(s);
        }
        // 查询
        return this.goodsProductMapper.selectInfoIdList(list);
    }

    /**
     * 删除购物车的货品
     *
     * @param goodsIds
     *            货品编号
     * @return int
     */
    @Override
    public int delShoppingGoodsByGoodsInfoIds(List<Long> goodsIds) {
        return this.goodsProductMapper.delShoppingGoodsByGoodsInfoIds(goodsIds);
    }
}
