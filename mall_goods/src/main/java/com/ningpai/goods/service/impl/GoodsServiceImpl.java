/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.service.impl;

import com.ningpai.common.lucene.main.LuceneIKUtil;
import com.ningpai.goods.bean.*;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsMapper;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.dao.ImageSetMapper;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.*;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 商品Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月24日 下午5:19:36
 */
@Service("GoodsService")
public class GoodsServiceImpl implements GoodsService {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(GoodsServiceImpl.class);

    private static final String GOODSID = "goodsId";
    private static final String THIRDID = "thirdId";
    private static final String SPECID = "specId";
    private static final String PARAMVALUE = "paramValue";
    private static final String ABOUTGOODSID = "aboutGoodsId";
    private static final String MODIFIEDNAME = "modifiedName";
    private static final String AUDITSTATUS = "auditStatus";
    private static final String GOODSINFOADDED = "goodsInfoAdded";
    private static final String GOODSIDS = "goodsIds";

    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;
    @Resource(name = "GoodsProductSuppService")
    private GoodsProductSuppService goodsProductSuppService;
    // 商品DAO
    private GoodsMapper goodsMapper;
    // 商品关联标签Service
    private GoodsReleTagService goodsReleTagService;
    // 货品信息Service
    private GoodsProductService goodsProductService;
    // 商品分类Service
    private GoodsCateService goodsCateService;
    // 商品关联类型扩展属性值Service
    private GoodsReleExpandParamService goodsReleExpandParamService;
    // 商品关联类型详细参数Service
    private GoodsReleParamService goodsReleParamService;
    // 商品关联商品Service
    private GoodsRelatedGoodsService goodsRelatedGoodsService;
    // 标签Service
    private GoodsTagService goodsTagService;
    // 货品Service
    private GoodsProductMapper goodsProductMapper;
    // 商品规格值Service
    private GoodsSpecService goodsSpecService;
    // 保存开启规格的Service
    private GoodsOpenSpecService goodsOpenSpecService;
    // 商品开启规格值Service
    private GoodsOpenSpecValueService goodsOpenSpecValueService;
    // 货品关联规格值Service
    @Resource(name = "GoodsProductReleSpecService")
    private GoodsProductReleSpecService goodsProductReleSpecService;
    private CascDelMapper cascDelMapper;
    // 临时全局对象
    private GoodsProduct product;
    // 批量添加货品时的规格值ID和规格ID
    private String[] specIds, specDetailIds;
    // 规格值集合
    private List<GoodsSpecVo> specList = new ArrayList<GoodsSpecVo>();
    // 开启的规格集合
    private List<GoodsOpenSpecVo> openSpecList = new ArrayList<GoodsOpenSpecVo>();
    // 开启的规格值对象
    private GoodsOpenSpecValueVo openSpecDeta;
    // 规格值对象
    private GoodsSpecDetail specDeta;
    // 操作人名称
    private String username = "";
    // 规格值的数量
    private Integer count = 0;
    // 循环的下表
    private Integer index = 0;
    // 批量生成货品用到的临时商品
    private Goods goods;
    // 批量生成货品编号用到的日期处理类型
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    // 图片规则DAO
    @Resource(name = "GoodsImageSetMapper")
    private ImageSetMapper imageSetMapper;

    /**
     * 查询商品是否下架
     */
    @Override
    public String selectCheckGoods(int goodsId) {
        return goodsMapper.selectCheckGoodsMapper(goodsId);
    }

    /**
     * 根据pagebean查询GoodsListVo
     *
     * @param pb
     *            分页辅助对象 {@link com.ningpai.util.PageBean}
     * @return 封装好的分页对象
     */
    public PageBean queryListVo(PageBean pb, String isThird) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 是否是第三方
        map.put("isThird", isThird);
        // 设置PageBean的行数参数
        pb.setRows(this.goodsMapper.queryTotalCount(map));
        // 定义两个List集合
        List<Object> voList = null;
        List<Object> voList2 = new ArrayList<Object>();
        GoodsListVo listVo = null;

        try {
            // 封装分页参数
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());

            // 设置列表
            voList = this.goodsMapper.queryGoodsListByPageBean(map);
            // 循环集合列表
            for (int i = 0; i < voList.size(); i++) {
                listVo = (GoodsListVo) voList.get(i);
                // 判断商品库存总和是否为空
                if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                    listVo.setStock(0L);
                } else {
                    listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                }
                voList2.add(listVo);
            }
            pb.setList(voList2);
        } finally {
            // 参数置空
            map = null;
            voList = null;
            voList2 = null;
            listVo = null;
        }
        // 返回pageBean
        return pb;
    }

    /**
     * 删除商品信息
     *
     * @param goodsId
     *            商品信息ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoods(Long goodsId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 把参数放进Map集合中
            map.put("delName", username);
            map.put(GOODSID, goodsId.toString());
            // 执行删除方法
            this.goodsProductMapper.delProductWithGoodsId(map);
            // 根据主键删除信息
            return this.goodsMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODS + username);
            //2015-12-30 wuyanbo close
            /*this.cascDelMapper.cascDel(username);*/
            // 参数置空
            map = null;
        }
    }

    /**
     * 删除第三方商家商品信息
     *
     * @param goodsId
     * @param thirdId
     * @param username
     * @return
     */
    @Transactional
    public int delThirdGoods(Long goodsId, Long thirdId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 把参数放进map集合中
            map.put("delName", username);
            map.put(GOODSID, goodsId.toString());
            map.put(THIRDID, thirdId.toString());
            // 执行删除方法
            this.goodsProductMapper.delThirdProductWithGoodsId(map);
            // 根据主键执行删除
            return this.goodsMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODS + username);
            //2015-12-30 wuyanbo close
            /*this.cascDelMapper.cascDel(username);*/
            // 参数置空
            map = null;
        }
    }

    /**
     * 批量删除商品信息
     *
     * @param goodsIds
     *            商品ID数组 {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int batchDel(Long[] goodsIds, String username) {
        Integer count = Integer.valueOf(0);
        try {
            for (int i = 0; i < goodsIds.length; i++) {
                count += delGoods(goodsIds[i], username);
            }
            // 执行批量删除方法
            //2015-12-30 wuyanbo close
            /*this.cascDelMapper.cascDel(username);*/
            // 打印日志
            LOGGER.info(ValueUtil.BATCHDELGOODS + username);
            // 返回结果
            return count;
        } finally {
            count = null;
        }
    }

    /**
     * 保存商品
     *
     * @param goods
     *            待保存的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param map
     *            封装了所有需要用的参数 {@link java.util.Map}
     * @return 最新的ID {@link java.lang.Long}
     */
    @Transactional
    public Long saveGoods(Goods goods, String username, Map<String, String[]> map) {
        // 设置操作人的名称
        goods.setGoodsCreateName(username);
        // 创建接受商品最新ID的对象
        Long newId = 0L;
        // 设置删除标记
        goods.setGoodsDelflag("0");
        // 根据分类ID查询分类对象并把关联的类型ID设置到商品中
        goods.setTypeId(this.goodsCateService.queryGoodsCateById(goods.getCatId()).getTypeId());

        // 从图库查询图片
        if (goods.getGoodsImg() != null && !"".equals(goods.getGoodsImg())) {
            // 查询图片
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("url", goods.getGoodsImg());
            InfoImageManage infoImageManage = imageSetMapper.queryImageByUrl(paramMap);
            // 判断图片是否存在
            if (infoImageManage != null && infoImageManage.getMiddleImgUrl() != null && !"".equals(infoImageManage.getMiddleImgUrl())) {
                goods.setGoodsImg(infoImageManage.getSmallImgUrl());
            }

        }

        // 如果添加商品时受影响的行数大于0，则表示添加成功
        if (this.goodsMapper.insertSelective(goods) > 0) {
            newId = this.goodsMapper.selectLastId();
            // 从Map中取出参数
            String[] tagIds = map.get("tags");
            if (null != tagIds && tagIds.length > 0) {
                for (int i = 0; i < tagIds.length; i++) {
                    this.goodsReleTagService.saveReleTag(Long.parseLong(tagIds[i]), newId, username);
                }
            }
            // 取出商品关联类型扩展属性的参数
            String[] expandParamIds = map.get("expandParamIds");
            String[] expandParamValues = map.get("expandParamValues");
            if (null != expandParamIds && null != expandParamValues) {
                for (int i = 0; i < expandParamIds.length; i++) {
                    this.goodsReleExpandParamService.saveExpandParam(username, newId, Long.parseLong(expandParamIds[i]), Long.parseLong(expandParamValues[i]));
                }
            }
            // 保存商品的时候保存详细参数
            saveGoodsSaveParam(username, map, newId);
            // 保存商品相关商品
            saveGoodsSaveAboutGoods(username, map, newId);
            if (null != map.get(SPECID)) {
                // 从map中取出值
                String[] specId = map.get(SPECID);
                String[] specValues = map.get("specValues");
                String[] openSpecValueImg = map.get("openSpecValueImg");
                String[] openSpecValueRemark = map.get("openSpecValueRemark");
                // 临时变量用来分割传递的值
                String[] specValSpli = null;
                // 从分割的值中取出对应的值,此处声明 的是临时变量
                Long spec = null;
                Long specValueId = null;
                try {
                    for (int i = 0; i < specId.length; i++) {
                        // 保存到数据库
                        this.goodsOpenSpecService.saveOpenSpec(newId, Long.parseLong(specId[i]));
                    }
                    for (int i = 0; i < specValues.length; i++) {
                        // 分割传递的值,并赋值给之前声明的临时变量
                        specValSpli = specValues[i].split("-");
                        specValueId = Long.parseLong(specValSpli[0]);
                        spec = Long.parseLong(specValSpli[1]);
                        // 保存到数据库
                        this.goodsOpenSpecValueService.saveOpenSpecVal(newId, spec, specValueId, openSpecValueImg[i], openSpecValueRemark[i]);
                    }
                } finally {
                    specId = null;
                    specValues = null;
                    spec = null;
                    specValueId = null;
                    specValSpli = null;
                    openSpecValueRemark = null;
                }
            }

            // 添加所有的货品
           //saveProductWhenSaveGoods(newId,goods,username);
        }
        // 打印日志
        LOGGER.info(ValueUtil.SAVEGOODS + username);
        // 返回结果
        return newId;
    }

    /**
     * 保存商品详细参数
     *
     * @param username
     *            操作人名称
     * @param map
     *            参数
     * @param newId
     *            商品ID {@link java.lang.Long}
     */
    @Transactional
    private void saveGoodsSaveParam(String username, Map<String, String[]> map, Long newId) {
        // 取出商品关联类型详细参数的参数
        String[] paramIds = map.get("paramIds");
        String[] paramValue = map.get(PARAMVALUE);
        // 判断商品关联类型详细参数的参数是否为空
        if (null != paramIds) {
            // 不为空就循环遍历
            for (int i = 0; i < paramIds.length; i++) {
                // 执行保存方法
                this.goodsReleParamService.saveGoodsReleParam(newId, Long.parseLong(paramIds[i]), paramValue[i], username);
            }
        }
    }

    /**
     * 保存商品同时保存相关商品
     *
     * @param username
     *            操作人名称
     * @param map
     *            参数
     * @param newId
     *            添加的商品ID
     */
    @Transactional
    private void saveGoodsSaveAboutGoods(String username, Map<String, String[]> map, Long newId) {
        // 获取商品id
        String[] aboutGoodsId = map.get(ABOUTGOODSID);
        // 判断商品id数组是否为空
        if (null != aboutGoodsId) {
            // 不为空就循环列表
            for (int i = 0; i < aboutGoodsId.length; i++) {
                // 执行保存相关商品方法
                this.goodsRelatedGoodsService.save(newId, Long.parseLong(aboutGoodsId[i]), username);
            }
        }
    }

    /**
     * 根据分类ID查询相关商品
     *
     * @param catId
     *            商品分类ID {@link java.lang.Long }
     * @return 查询到的商品列表 {@link java.util.List}
     */
    public List<Object> queryGoodsListByCatId(Long catId) {
        // 根据分类id查询相关商品并赋值给list集合
        List<Object> list = this.goodsMapper.queryGoodsListByCatId(catId);
        // 定义一个ArrayList集合
        List<Object> list2 = new ArrayList<Object>();
        GoodsListVo listVo = null;
        try {
            // 循环list集合
            for (int i = 0; i < list.size(); i++) {
                listVo = (GoodsListVo) list.get(i);
                // 判断商品库存总和是否为空
                if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                    listVo.setStock(0L);
                } else {
                    // 查询商品库存总和
                    listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                }
                list2.add(listVo);
            }
            return list2;
        } finally {
            // 参数置空
            list = null;
            list2 = null;
            listVo = null;
        }
    }

    /**
     * 根据商品ID查询修改时的Vo
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的VO {@link com.ningpai.goods.vo.GoodsMoifiedVo}
     */
    public GoodsMoifiedVo queryModeifiedVoByGoodsId(Long goodsId) {
        GoodsMoifiedVo moifiedVo;
        try {
            // 根据商品IDc查询修改商品的VO
            moifiedVo = this.goodsMapper.queryModeifiedVoByGoodsId(goodsId);
            // 根据商品ID查询所属货品的库存总和
            if (null == this.goodsMapper.queryStockByGoodsId(moifiedVo.getGoodsId())) {
                moifiedVo.setStock(0);
            } else {
                // 根据商品ID查询所属货品的库存总和
                moifiedVo.setStock(Integer.parseInt(this.goodsMapper.queryStockByGoodsId(moifiedVo.getGoodsId()).toString()));
            }
            // 根据商品ID查询开启的规格集合
            moifiedVo.setOpenSpecList(this.goodsOpenSpecService.queryOpenListByGoodsId(goodsId));
            // 返回结果
            return moifiedVo;
        } finally {
            moifiedVo = null;
        }
    }

    /**
     * 更新商品信息
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoods(Goods goods, Map<String, String[]> map, String isThirdAuditUsed, String username) {
        // 更新商品关联的标签
        updateGodsReleTag(goods, map, username);
        // 判断商品分类是否被修改，并操作扩展参数和详细参数
        String[] expandParamId = map.get("expandParamId");
        String[] expandparamValue = map.get("expandparamValue");
        String[] paramId = map.get("paramId");
        String[] paramValue = map.get(PARAMVALUE);
        String[] aboutGoodsId = map.get(ABOUTGOODSID);
        String[] productIdSpecIdSpecdetailId = map.get("productIdSpecIdSpecdetailId");
        Long catId = goods.getCatId();
        // 根据主键查询商品信息
        Long nowCatId = this.goodsMapper.selectByPrimaryKey(goods.getGoodsId()).getCatId();
        if (null == catId || catId.equals(nowCatId)) {
            if (null != expandParamId && expandParamId.length > 0) {
                // 更新商品的时候更新商品的扩展参数信息
                updateExpandParamWhenUpdateGoods(goods, username, expandParamId, expandparamValue);
            }
            if (null != paramId && paramId.length > 0) {
                // 更新商品的时候更新商品的详细参数信息
                updateParamWhenUpdateGoods(goods, username, paramId, paramValue);
            }
            // 更新商品关联商品
            updateRelaGoodsWhenUpdateGoods(goods, username, aboutGoodsId);
        } else {
            // 进入到ELSE中说明选择的分类已经改变，之前关联的属性就要全部清除并重新添加
            // 根据分类ID为商品设置新的商品类型
            goods.setTypeId(this.goodsCateService.queryGoodsCateById(goods.getCatId()).getTypeId());
            // 根据商品ID删除所有的属性
            delAllParamByGoodsId(goods, username);
            // 添加新的属性
            saveNewParamByGoodsIdWhenChangeCate(goods, username, expandParamId, expandparamValue, paramId, paramValue);

            // 重置货品的规格
            resetGoodsInfoSpec(productIdSpecIdSpecdetailId, goods.getGoodsId(), username);

            // 保存商品相关商品
            saveRelaGoodsWhenChangeCate(goods, username, aboutGoodsId);
        }
        // 给商品设置修改人名称
        goods.setGoodsModifiedName(username);
        // 如果商品修改为下架状态那么把对应的货品也修改为下架
        if ("0".equals(goods.getGoodsAdded())) {
            Map<String, String> upProMap = new HashMap<String, String>();
            upProMap.put(GOODSID, goods.getGoodsId().toString());
            upProMap.put(MODIFIEDNAME, username);
            this.goodsProductMapper.updateProductAddedWithGoodsId(upProMap);
        }
        // 判断是否是第三方商品
        Goods g = goodsMapper.selectByPrimaryKey(goods.getGoodsId());
        if (g != null) {
            goods.setIsThird(g.getIsThird());
        }
        if (Integer.parseInt(goods.getIsThird()) == 1) {
            if (Integer.parseInt(isThirdAuditUsed) == 1) {// 审核开关开启，去审核商品时，将货品也都改为去审核
                // 定义一个HashMap集合
                Map<String, String> param = new HashMap<String, String>();
                // 把参数放进map集合中
                param.put(GOODSID, goods.getGoodsId().toString());
                param.put(MODIFIEDNAME, username);
                param.put(AUDITSTATUS, "1");
                param.put(GOODSINFOADDED, "0");
                // 批量审核上架商品时把货品也设置审核
                this.goodsProductMapper.updateProductAddedWithAudit(param);
            } else {
                // 定义一个HashMap集合
                Map<String, String> param = new HashMap<String, String>();
                // 把参数放进map集合
                param.put(GOODSID, goods.getGoodsId().toString());
                param.put(MODIFIEDNAME, username);
                param.put(AUDITSTATUS, "3");
                param.put(GOODSINFOADDED, "1");
                // 批量审核上架商品时把货品也设置审核
                this.goodsProductMapper.updateProductAddedWithAudit(param);
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODS + username);
        // 更新商品信息
        return this.goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 更新商品信息
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsNew(Goods goods, Map<String, String[]> map, String isThirdAuditUsed, String username) {
        // 更新商品关联的标签
        updateGodsReleTag(goods, map, username);
        String[] aboutGoodsId = map.get(ABOUTGOODSID);
        // 更新商品关联商品
        updateRelaGoodsWhenUpdateGoods(goods, username, aboutGoodsId);
        // 给商品设置修改人名称
        goods.setGoodsModifiedName(username);
        // 如果商品修改为下架状态那么把对应的货品也修改为下架
        if ("0".equals(goods.getGoodsAdded())) {
            // 定义一个HashMap集合
            Map<String, String> upProMap = new HashMap<String, String>();
            // 把参数放进map集合
            upProMap.put(GOODSID, goods.getGoodsId().toString());
            upProMap.put(MODIFIEDNAME, username);
            // 修改商品为下架状态的时候把货品信息页修改为下架状态
            this.goodsProductMapper.updateProductAddedWithGoodsId(upProMap);
        }
        /*
         * if(Integer.parseInt(isThirdAuditUsed) == 1) {//
         * 审核开关开启，去审核商品时，将货品也都改为去审核 Map<String, String> param = new
         * HashMap<String, String>(); param.put(GOODSID,
         * goods.getGoodsId().toString()); param.put(MODIFIEDNAME, username);
         * param.put(AUDITSTATUS, "1"); param.put(GOODSINFOADDED, "0");
         * this.goodsProductMapper.updateProductAddedWithAudit(param); }else {
         * Map<String, String> param = new HashMap<String, String>();
         * param.put(GOODSID, goods.getGoodsId().toString());
         * param.put(MODIFIEDNAME, username); param.put(AUDITSTATUS, "0");
         * param.put(GOODSINFOADDED, "1");
         * this.goodsProductMapper.updateProductAddedWithAudit(param); }
         */
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODS + username);
        // 更新商品信息
        return this.goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 更新商品信息
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param map
     *            分装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsParamSpec(Goods goods, Map<String, String[]> map, String isThirdAuditUsed, String username) {
        // 判断商品分类是否被修改，并操作扩展参数和详细参数
        String[] expandParamId = map.get("expandParamId");
        String[] expandparamValue = map.get("expandparamValue");
        String[] paramId = map.get("paramId");
        String[] paramValue = map.get(PARAMVALUE);
        String[] aboutGoodsId = map.get(ABOUTGOODSID);
        String[] productIdSpecIdSpecdetailId = map.get("productIdSpecIdSpecdetailId");
        Long catId = goods.getCatId();
        Long nowCatId = this.goodsMapper.selectByPrimaryKey(goods.getGoodsId()).getCatId();
        if (null == catId || catId.equals(nowCatId)) {
            if (null != expandParamId && expandParamId.length > 0) {
                // 更新商品的时候更新商品的扩展参数信息
                updateExpandParamWhenUpdateGoods(goods, username, expandParamId, expandparamValue);
            }
            if (null != paramId && paramId.length > 0) {
                // 更新商品的时候更新商品的详细参数信息
                updateParamWhenUpdateGoods(goods, username, paramId, paramValue);
            }
        } else {
            // 进入到ELSE中说明选择的分类已经改变，之前关联的属性就要全部清除并重新添加
            // 根据分类ID为商品设置新的商品类型
            goods.setTypeId(this.goodsCateService.queryGoodsCateById(goods.getCatId()).getTypeId());
            // 根据商品ID删除所有的属性
            delAllParamByGoodsId(goods, username);
            // 添加新的属性
            saveNewParamByGoodsIdWhenChangeCate(goods, username, expandParamId, expandparamValue, paramId, paramValue);

            // 重置货品的规格
            resetGoodsInfoSpec(productIdSpecIdSpecdetailId, goods.getGoodsId(), username);

            // 保存商品相关商品
            saveRelaGoodsWhenChangeCate(goods, username, aboutGoodsId);
        }
        // 给商品设置修改人名称
        goods.setGoodsModifiedName(username);
        return this.goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 重置商品规格
     */
    public void resetGoodsInfoSpec(String[] productIdSpecIdSpecdetailIds, Long goodsId, String ursename) {
        // 根据商品id，删除商品与规格之间关系
        goodsOpenSpecService.deleteByGoodsId(goodsId);
        // 根据商品id，删除商品与规格之间关系
        goodsOpenSpecValueService.deleteByGoodsId(goodsId);
        if (productIdSpecIdSpecdetailIds == null) {
            return;
        }
        Set<String> openSpecSet = new HashSet<String>();
        for (int i = 0; i < productIdSpecIdSpecdetailIds.length; i++) {
            String productIdSpecIdSpecdetailId = productIdSpecIdSpecdetailIds[i];
            String[] obj = productIdSpecIdSpecdetailId.split("-");
            Long goodsInfoId = Long.parseLong(obj[0]);
            // 先把货品的规格都删除
            goodsProductReleSpecService.deleteByProductId(goodsInfoId);
            for (int j = 1; j < obj.length; j++) {
                String[] specIdSpecdetailIds = obj[j].split("_");
                Long specId = Long.parseLong(specIdSpecdetailIds[0]);
                Long specdetailId = Long.parseLong(specIdSpecdetailIds[1]);
                String specRemark = specIdSpecdetailIds[2];
                openSpecSet.add(obj[j]);
                // 保存到数据库
                if (i == 0) {
                    this.goodsOpenSpecService.saveOpenSpec(goodsId, specId);
                }
                // 插入新的规格和规格值
                this.goodsProductReleSpecService.saveProductReleSpec(goodsInfoId, specId, specdetailId, specRemark, ursename);
            }
        }

        for (String specIdValueRemark : openSpecSet) {
            String[] specIdSpecdetailIds = specIdValueRemark.split("_");
            Long specId = Long.parseLong(specIdSpecdetailIds[0]);
            Long specdetailId = Long.parseLong(specIdSpecdetailIds[1]);
            String specRemark = specIdSpecdetailIds[2];
            String specImg = null;
            if (specIdSpecdetailIds.length > 3) {
                specImg = specIdSpecdetailIds[3];
            }
            // 保存商品开启规格值记录
            this.goodsOpenSpecValueService.saveOpenSpecVal(goodsId, specId, specdetailId, specImg, specRemark);
        }
    }

    /**
     * 只更新商品详细介绍
     *
     * @param goods
     *            待更新的实体
     * @return 更新的行数
     */
    public int updateGoodsDesc(Goods goods) {
        // 如果商品修改为下架状态那么把对应的货品也修改为下架
        if (null != goods.getGoodsAdded() && "0".equals(goods.getGoodsAdded())) {
            // 定义一个HashMap集合
            Map<String, String> upProMap = new HashMap<String, String>();
            // 把参数放进Map集合中
            upProMap.put(GOODSID, goods.getGoodsId().toString());
            upProMap.put(MODIFIEDNAME, goods.getGoodsModifiedName());
            // 修改商品为下架状态的时候把货品信息页修改为下架状态
            this.goodsProductMapper.updateProductAddedWithGoodsId(upProMap);
        }
        // 更新商品信息
        return this.goodsMapper.updateByPrimaryKeySelective(goods);
    }

    /**
     * 更新商品时更新商品关联商品的记录
     *
     * @param goods
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param aboutGoodsId
     *            关联的商品ID的数组
     */
    @Transactional
    private void updateRelaGoodsWhenUpdateGoods(Goods goods, String username, String[] aboutGoodsId) {
        // 删除未被选中的记录
        this.goodsRelatedGoodsService.delRelaGoodsByGoodsIdAndRelaGoodsIds(goods.getGoodsId(), aboutGoodsId, username);
        if (null != aboutGoodsId && aboutGoodsId.length > 0) {
            GoodsRelatedGoods relaGoods = null;
            for (int i = 0; i < aboutGoodsId.length; i++) {
                relaGoods = this.goodsRelatedGoodsService.queryByGoodsIdAndRelaGoodsIdIncludeDel(goods.getGoodsId(), Long.parseLong(aboutGoodsId[i]));
                if (relaGoods == null) {
                    // 如果查询到的为空，就新增记录
                    this.goodsRelatedGoodsService.save(goods.getGoodsId(), Long.parseLong(aboutGoodsId[i]), username);
                } else {
                    // 如果记录已经存在就设置删除标记为0并更新
                    relaGoods.setRelaDelflag(ValueUtil.DEFAULTDELFLAG);
                    this.goodsRelatedGoodsService.updateRelaGoods(relaGoods, username);
                }
            }
        }
    }

    /**
     * 更新商品的时候更新商品的详细参数信息
     *
     * @param goods
     *            商品实体{@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param paramId
     *            详细参数ID
     * @param paramValue
     *            详细参数值
     */
    @Transactional
    private void updateParamWhenUpdateGoods(Goods goods, String username, String[] paramId, String[] paramValue) {
        // 更新详细参数
        GoodsReleParam releParam = null;
        if (null != paramId) {
            for (int i = 0; i < paramId.length; i++) {
                // 根据商品ID和详细参数ID查询
                releParam = this.goodsReleParamService.queryReleParamByGoodsIdAndParamId(goods.getGoodsId(), Long.parseLong(paramId[i]));
                if (null == releParam) {
                    // 保存商品关联类型详细参数
                    this.goodsReleParamService.saveGoodsReleParam(goods.getGoodsId(), Long.parseLong(paramId[i]), paramValue[i], username);
                } else {
                    releParam.setParamValue(paramValue[i]);
                    // 更新商品关联类型详细参数信息
                    this.goodsReleParamService.updateReleParam(releParam, username);
                }
            }
        }
    }

    /**
     * 更新商品的时候 更新商品的扩展参数信息
     *
     * @param goods
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param expandParamId
     *            扩展参数ID
     * @param expandparamValue
     *            扩展参数值ID
     */
    @Transactional
    private void updateExpandParamWhenUpdateGoods(Goods goods, String username, String[] expandParamId, String[] expandparamValue) {
        GoodsReleExpandParam expandParam = null;
        // 更新扩展参数
        if (null != expandParamId) {
            for (int i = 0; i < expandParamId.length; i++) {
                // 根据商品ID和扩展属性ID查询商品关联扩展属性的记录
                expandParam = this.goodsReleExpandParamService.queryByGoodsIdAndExpandParamId(goods.getGoodsId(), Long.parseLong(expandParamId[i]));
                // 如果记录不存在 则添加
                if (null == expandParam) {
                    // 保存商品关联类型扩展参数
                    this.goodsReleExpandParamService.saveExpandParam(username, goods.getGoodsId(), Long.parseLong(expandParamId[i]), Long.parseLong(expandparamValue[i]));
                } else {
                    expandParam.setExpangparamValueId(Long.parseLong(expandparamValue[i]));
                    // 更新商品关联扩展属性信息
                    this.goodsReleExpandParamService.updateGoodsReleExpandParam(expandParam, username);
                }
            }
        }
    }

    /**
     * 改变商品分类的时候保存商品关联商品信息
     *
     * @param goods
     *            商品实体{@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param aboutGoodsId
     *            关联的商品的数组
     */
    @Transactional
    private void saveRelaGoodsWhenChangeCate(Goods goods, String username, String[] aboutGoodsId) {
        if (null != aboutGoodsId) {
            for (int i = 0; i < aboutGoodsId.length; i++) {
                // 添加商品关联商品记录
                this.goodsRelatedGoodsService.save(goods.getGoodsId(), Long.parseLong(aboutGoodsId[i]), username);
            }
        }
    }

    /**
     * 当改变商品分类的时候 保存新的属性
     *
     * @param goods
     *            商品实体{@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param expandParamId
     *            扩展属性ID
     * @param expandparamValue
     *            扩展属性值
     * @param paramId
     *            详细参数ID
     * @param paramValue
     *            详细参数值
     */
    @Transactional
    private void saveNewParamByGoodsIdWhenChangeCate(Goods goods, String username, String[] expandParamId, String[] expandparamValue, String[] paramId, String[] paramValue) {
        // 保存商品关联类型扩展属性的参数
        if (null != expandParamId) {
            for (int i = 0; i < expandParamId.length; i++) {
                // 保存商品关联类型扩展参数
                this.goodsReleExpandParamService.saveExpandParam(username, goods.getGoodsId(), Long.parseLong(expandParamId[i]), Long.parseLong(expandparamValue[i]));
            }
        }
        // 保存商品关联类型详细参数的参数
        if (null != paramId) {
            for (int i = 0; i < paramId.length; i++) {
                // 保存商品关联类型详细参数
                this.goodsReleParamService.saveGoodsReleParam(goods.getGoodsId(), Long.parseLong(paramId[i]), paramValue[i], username);
            }
        }
    }

    /**
     * 根据商品ID删除所有的属性关联
     *
     * @param goods
     *            商品实体{@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     */
    @Transactional
    private void delAllParamByGoodsId(Goods goods, String username) {
        // 删除所有的扩展属性
        this.goodsReleExpandParamService.delAllExpandParamByGoodsId(goods.getGoodsId(), username);
        // 删除所有的详细参数
        this.goodsReleParamService.delAllReleParamByGoodsId(goods.getGoodsId(), username);
        // 删除所有的关联商品
        this.goodsRelatedGoodsService.delAllRelaGoodsByGoodsId(goods.getGoodsId(), username);
    }

    /**
     * 更新商品关联标签信息
     *
     * @param goods
     *            商品实体{@link com.ningpai.goods.bean.Goods}
     * @param map
     *            封装参数 {@link java.util.Map}
     * @param username
     *            操作人名称
     */
    @Transactional
    private void updateGodsReleTag(Goods goods, Map<String, String[]> map, String username) {
        // 临时变量
        String[] tags = map.get("tags");
        GoodsReleTag releTag = null;
        // 更新或者添加商品关联标签
        updateOrSaveReleTag(goods, username, tags);
        // 查询所有的标签
        List<Object> list = this.goodsTagService.queryAllTag();
        List<Long> allTagIds = new ArrayList<Long>();
        GoodsTag tag = null;
        for (int i = 0; i < list.size(); i++) {
            tag = (GoodsTag) list.get(i);
            allTagIds.add(tag.getTagId());
        }
        if (null != tags && tags.length > 0) {
            for (int i = 0; i < allTagIds.size(); i++) {
                for (int k = 0; k < tags.length; k++) {
                    // 如果所有的标签ID中包含传递过来的 那么就表示已经选中，否则就是未选中
                    if (allTagIds.get(i) == Long.parseLong(tags[k])) {
                        allTagIds.remove(i);
                    }
                }
            }
        }
        // 循环未被选中的标签ID
        for (int i = 0; i < allTagIds.size(); i++) {
            // 根据商品ID和标签ID查询实体
            releTag = this.goodsReleTagService.queryByGoodsIdAndTagId(goods.getGoodsId(), allTagIds.get(i));
            if (null != releTag) {
                // 删除关联标签的记录
                this.goodsReleTagService.deleteByPrimaryKey(releTag.getRelaTagId(), username);
            }
        }
    }

    /**
     * 更新或保存商品关联标签信息
     *
     * @param goods
     *            商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param username
     *            操作人名称
     * @param tags
     *            标签ID集合
     */
    @Transactional
    private void updateOrSaveReleTag(Goods goods, String username, String[] tags) {
        GoodsReleTag releTag;
        if (null != tags) {
            for (int i = 0; i < tags.length; i++) {
                // 根据商品ID和标签ID查询实体
                releTag = this.goodsReleTagService.queryByGoodsIdAndTagId(goods.getGoodsId(), Long.parseLong(tags[i]));
                if (null == releTag) {
                    // 保存关联标签
                    this.goodsReleTagService.saveReleTag(Long.parseLong(tags[i]), goods.getGoodsId(), ValueUtil.DEFAULTDELFLAG);
                } else if ("1".equals(releTag.getRelaTagDelflag())) {
                    releTag.setRelaTagDelflag(ValueUtil.DEFAULTDELFLAG);
                    // 更新商品关联标签记录
                    this.goodsReleTagService.update(releTag, username);
                }
            }
        }
    }


    /**
     *
     * @param goodsId
     * @param goods
     * @param username
     * @param specIds
     * @param specDetailIds
     * @return
     */
    public int saveProductBySelect(Long goodsId,
                                    Goods goods,
                                    String username,
                                    String[] specIds,
                                    String[] specDetailIds,
                                    Map<String, Object> map) {
        try {
            product = new GoodsProduct();
            // 设置货品名称
            product.setGoodsInfoName(goods.getGoodsName());
            // 设置商品编号
            product.setGoodsId(goodsId);
            // 设置是否上架
            product.setGoodsInfoAdded("0");
            // 设置成本价
            product.setGoodsInfoCostPrice(goods.getGoodsPrice());
            // 设置创建人
            product.setGoodsInfoCreateName(username);
            // 设置是否删除
            product.setGoodsInfoDelflag("0");
            // 设置市场价
            product.setGoodsInfoMarketPrice(goods.getGoodsPrice());
            // 设置销售价
            product.setGoodsInfoPreferPrice(goods.getGoodsPrice());
            // 设置会员价
            product.setGoodsInfoVipPrice(goods.getGoodsVipPrice());
            // 设置重量
            product.setGoodsInfoWeight(BigDecimal.valueOf(0.00));
            // 设置货品副标题
            product.setGoodsInfoSubtitle(goods.getGoodsSubtitle());
            // 设置货品库存
            product.setGoodsInfoStock(0L);
            // 设置第三方id
            product.setThirdId(goods.getGoodsBelo());
            // 设置第三方名称
            product.setThirdName(goods.getGoodsBeloName());
            // 设置是否是第三方
            product.setIsThird(goods.getIsThird());

            product.setGoodsInfoItemNo(UUID.randomUUID().toString());
            this.username = username;
            this.goodsProductService.saveProduct(product, username, specIds, specDetailIds, map);

            return 1;
        } finally {
            product = null;
        }
    }


    /**
     * 保存商品的同时创建所有的规格组合的货品
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @param goods
     *            商品{@link Goods}
     * @param username
     *            操作的用户名 {@link String}
     * @return 创建的个数{@link Integer}
     */
    public int saveProductWhenSaveGoods(Long goodsId, Goods goods, String username) {
        try {
            product = new GoodsProduct();
            // 设置货品名称
            product.setGoodsInfoName(goods.getGoodsName());
            // 设置商品编号
            product.setGoodsId(goodsId);
            // 设置是否上架
            product.setGoodsInfoAdded("0");
            // 设置成本价
            product.setGoodsInfoCostPrice(goods.getGoodsPrice());
            // 设置创建人
            product.setGoodsInfoCreateName(username);
            // 设置是否删除
            product.setGoodsInfoDelflag("0");
            // 设置市场价
            product.setGoodsInfoMarketPrice(goods.getGoodsPrice());
            // 设置销售价
            product.setGoodsInfoPreferPrice(goods.getGoodsPrice());
            // 设置会员价
            product.setGoodsInfoVipPrice(goods.getGoodsVipPrice());
            // 设置重量
            product.setGoodsInfoWeight(BigDecimal.valueOf(0.00));
            // 设置货品副标题
            product.setGoodsInfoSubtitle(goods.getGoodsSubtitle());
            // 设置货品库存
            product.setGoodsInfoStock(0L);
            // 设置第三方id
            product.setThirdId(goods.getGoodsBelo());
            // 设置第三方名称
            product.setThirdName(goods.getGoodsBeloName());
            // 设置是否是第三方
            product.setIsThird(goods.getIsThird());
            // 循环查询到的规格ID并批量插入货品信息
            specList = this.goodsSpecService.querySpecVoByGoodsId(goodsId);
            this.username = username;
            if (null != specList && !specList.isEmpty()) {
                specDetailIds = new String[specList.size()];
                count = specList.size();
                specIds = new String[specList.size()];
                loopSaveProduct(specList.get(index).getSpecDetails());
            }
            return 1;
        } finally {
            product = null;
        }
    }

    /**
     * 点击批量生成货品的时候
     *
     * @param goodsId
     *            需要批量生成货品的商品ID {@link Long}
     * @param username
     *            操作人名称 {@link String}
     * @return 执行标记 {@link Integer}
     */
    public int saveProductWhenClickBatchCreate(Long goodsId, String username) {
        try {
            index = 0;
            // 根据主键查询商品信息
            goods = this.goodsMapper.selectByPrimaryKey(goodsId);
            product = new GoodsProduct();
            // 设置货品名称
            product.setGoodsInfoName(goods.getGoodsName());
            // 设置商品名称
            product.setGoodsId(goodsId);
            // 设置是否上架
            product.setGoodsInfoAdded("0");
            // 设置成本价
            product.setGoodsInfoCostPrice(goods.getGoodsPrice());
            // 设置货品创建人名称
            product.setGoodsInfoCreateName(username);
            // 设置货品是否删除标记
            product.setGoodsInfoDelflag("0");
            // 设置市场价
            product.setGoodsInfoMarketPrice(goods.getGoodsPrice());
            // 设置销售价
            product.setGoodsInfoPreferPrice(goods.getGoodsPrice());
            // 设置会员价
            product.setGoodsInfoVipPrice(goods.getGoodsVipPrice());
            // 设置重量
            product.setGoodsInfoWeight(BigDecimal.valueOf(0.00));
            // 设置货品副标题
            product.setGoodsInfoSubtitle(goods.getGoodsSubtitle());
            // 设置库存
            product.setGoodsInfoStock(0L);
            // 设置第三方id
            product.setThirdId(goods.getGoodsBelo());
            // 设置第三方名称
            product.setThirdName(goods.getGoodsBeloName());
            // 设置是否是第三方
            product.setIsThird(goods.getIsThird());
            // 循环查询到的规格ID并批量插入货品信息
            openSpecList = this.goodsOpenSpecService.queryOpenListByGoodsId(goodsId);
            this.username = username;
            if (null != openSpecList && !openSpecList.isEmpty()) {
                specDetailIds = new String[openSpecList.size()];
                count = openSpecList.size();
                specIds = new String[openSpecList.size()];
                loopSaveProductWhenClick(openSpecList.get(index).getSpecValList());
            }
            return 1;
        } finally {
            goods = null;
            product = null;
        }
    }

    /**
     * 循环执行插入
     *
     * @param list
     *            规格值集合 {@link ArrayList}
     */
    public void loopSaveProduct(List<Object> list) {
        index += 1;
        for (int i = 0; i < list.size(); i++) {
            // 循环获取规格值
            specDeta = (GoodsSpecDetail) list.get(i);
            // 如果不是最后一个就循环取值
            if (index < count) {
                specIds[index - 1] = specDeta.getSpecId().toString();
                specDetailIds[index - 1] = specDeta.getSpecDetailId().toString();
                // 递归获取值
                loopSaveProduct(specList.get(index).getSpecDetails());
            } else {
                specIds[index - 1] = specDeta.getSpecId().toString();
                specDetailIds[index - 1] = specDeta.getSpecDetailId().toString();
                product.setGoodsInfoItemNo(UUID.randomUUID().toString());
                this.goodsProductService.saveProduct(product, username, specIds, specDetailIds, null);
            }
        }
        // 如果不是第一个,执行完一个方法体之后就设置为上一层的下标,继续循环
        if (specIds.length > 0) {
            specIds[specIds.length - 1] = "0";
            specDetailIds[specDetailIds.length - 1] = "0";
        }
        index -= 1;
    }

    /**
     * 点击生成的时候循环执行插入
     *
     * @param list
     *            规格值集合 {@link ArrayList}
     */
    public void loopSaveProductWhenClick(List<GoodsOpenSpecValueVo> list) {
        index += 1;
        for (int i = 0; i < list.size(); i++) {
            // 循环获取规格值
            openSpecDeta = list.get(i);
            // 如果不是最后一个就循环取值
            if (index < count) {
                specIds[index - 1] = openSpecDeta.getSpecId().toString();
                specDetailIds[index - 1] = openSpecDeta.getSpecDetail().getSpecDetailId().toString();
                // 递归获取值
                loopSaveProductWhenClick(openSpecList.get(index).getSpecValList());
            } else {

                specIds[index - 1] = openSpecDeta.getSpecId().toString();
                specDetailIds[index - 1] = openSpecDeta.getSpecDetail().getSpecDetailId().toString();
                // 生成货品编号
                String curr = String.valueOf(System.currentTimeMillis());
                try {
                    product.setGoodsInfoItemNo(sdf.format(new Date()).replace("-", "") + goods.getGoodsId() + goods.getCatId() + curr.substring(curr.length() - 4, curr.length()));
                    this.goodsProductService.saveProduct(product, username, specIds, specDetailIds, null);
                } finally {
                    curr = null;
                }
            }
        }
        // 如果不是第一个,执行完一个方法体之后就设置为上一层的下标,继续循环
        if (specIds.length > 0) {
            specIds[specIds.length - 1] = "0";
            specDetailIds[specDetailIds.length - 1] = "0";
        }
        index -= 1;
    }

    /**
     * 根据pageBean和SearchBean高级查询
     *
     * @param pb
     *            分页实体 {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return PageBean
     */
    public PageBean searchBySearchBeanAndPageBean(PageBean pb, GoodsSearchBean searchBean) {

        if (searchBean.getQueryStatus() == null) {
            searchBean.setQueryStatus("1");
        }
        if (null != searchBean) {
            if ("1".equals(searchBean.getShowFlag())) {
                // 设置基本查询为不可用
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
            } else {
                searchBean.setGoodsBrandId("-1");
                searchBean.setGoodsCateId("-1");
                searchBean.setGoodsKeyword("");
                searchBean.setGoodsName("");
                searchBean.setGoodsNo("");
                searchBean.setShowFlag("0");
                searchBean.setStatus("-1");
                searchBean.setThirdName("");
            }
            // 如果是否第三方标记为0,设置第三方名称为空
            if (null != searchBean.getIsThird() && "0".equals(searchBean.getIsThird())) {
                    searchBean.setThirdName("");
            }
        }

        if ("3".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的缺货商品的行数
            pb.setRows(this.goodsMapper.queryStockTotalCountBySearchBean(searchBean));
        } else if ("4".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的预警商品的行数
            pb.setRows(this.goodsMapper.queryElaryTotalCountBySearchBean(searchBean));
        } else if ("1".equals(searchBean.getQueryStatus()) || "2".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的行数
            pb.setRows(this.goodsMapper.queryTotalCountBySearchBean(searchBean));
        }
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> voList = null;
        GoodsListVo listVo = null;
        List<Object> voList2 = new ArrayList<Object>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            map.put(ValueUtil.SEARCHBEAN, searchBean);

            if ("3".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有的缺货商品
                voList = this.goodsMapper.queryStockByPageBeanAndSearchBean(map);
            } else if ("4".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有的预警商品
                voList = this.goodsMapper.queryElaryByPageBeanAndSearchBean(map);
            } else if ("1".equals(searchBean.getQueryStatus()) || "2".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有
                voList = this.goodsMapper.queryByPageBeanAndSearchBean(map);
            }

            // 非空判断
            if (voList != null) {
                for (int i = 0; i < voList.size(); i++) {
                    // 查询分仓库存
                    listVo = (GoodsListVo) voList.get(i);
                    // 库存为空
                    if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                        listVo.setStock(0L);
                    } else {
                        // 设置库存
                        listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                    }
                    voList2.add(listVo);
                }
            }
            pb.setList(voList2);
            // 返回结果
            return pb;
        } finally {
            // 参数置空
            map = null;
            voList = null;
            listVo = null;
            voList2 = null;
        }
    }

    /**
     * 根据pageBean和SearchBean高级查询
     *
     * 2015-12-19 wuyanbo add
     * @param pb
     *            分页实体 {@link com.ningpai.util.PageBean}
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @param request
     * @return PageBean
     */
    public PageBean searchBySearchBeanAndPageBean(PageBean pb, GoodsSearchBean searchBean, HttpServletRequest request) {

        if (searchBean.getQueryStatus() == null) {
            searchBean.setQueryStatus("1");
        }
        if (null != searchBean) {
            if ("1".equals(searchBean.getShowFlag())) {
                // 设置基本查询为不可用
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
            } else {
                searchBean.setGoodsBrandId("-1");
                searchBean.setGoodsCateId("-1");
                searchBean.setGoodsKeyword("");
                searchBean.setGoodsName("");
                searchBean.setGoodsNo("");
                searchBean.setShowFlag("0");
                searchBean.setStatus("-1");
                searchBean.setThirdName("");
            }
            // 如果是否第三方标记为0,设置第三方名称为空
            if (null != searchBean.getIsThird() && "0".equals(searchBean.getIsThird())) {
                searchBean.setThirdName("");
            }
        }

        int productCnt = 0;//货品记录数
        if ("3".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的缺货商品的行数
            pb.setRows(this.goodsMapper.queryStockTotalCountBySearchBean(searchBean));
            productCnt = this.goodsMapper.queryStockProductTotalCountBySearchBean(searchBean);
        } else if ("4".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的预警商品的行数
            pb.setRows(this.goodsMapper.queryElaryTotalCountBySearchBean(searchBean));
            productCnt = this.goodsMapper.queryElaryProductTotalCountBySearchBean(searchBean);
        } else if ("1".equals(searchBean.getQueryStatus()) || "2".equals(searchBean.getQueryStatus())) {
            // 根据查询参数查询所有的行数
            pb.setRows(this.goodsMapper.queryTotalCountBySearchBean(searchBean));
            productCnt = this.goodsMapper.queryProductTotalCountBySearchBean(searchBean);
        }
        request.setAttribute("productCnt", productCnt);

        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> voList = null;
        GoodsListVo listVo = null;
        List<Object> voList2 = new ArrayList<Object>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            map.put(ValueUtil.SEARCHBEAN, searchBean);

            if ("3".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有的缺货商品
                voList = this.goodsMapper.queryStockByPageBeanAndSearchBean(map);
            } else if ("4".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有的预警商品
                voList = this.goodsMapper.queryElaryByPageBeanAndSearchBean(map);
            } else if ("1".equals(searchBean.getQueryStatus()) || "2".equals(searchBean.getQueryStatus())) {
                // 根据查询参数查询所有
                voList = this.goodsMapper.queryByPageBeanAndSearchBean(map);
            }

            // 非空判断
            if (voList != null) {
                for (int i = 0; i < voList.size(); i++) {
                    // 查询分仓库存
                    listVo = (GoodsListVo) voList.get(i);
                    // 库存为空
                    if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                        listVo.setStock(0L);
                    } else {
                        // 设置库存
                        listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                    }
                    voList2.add(listVo);
                }
            }
            pb.setList(voList2);
            // 返回结果
            return pb;
        } finally {
            // 参数置空
            map = null;
            voList = null;
            listVo = null;
            voList2 = null;
        }
    }

    /**
     * 根据SearchBean高级查询
     *
     * 2015-12-19 wuyanbo add
     *
     * @param searchBean
     *            参数bean {@link com.ningpai.goods.util.GoodsSearchBean}
     * @return 查询货品的总数
     */
    public int queryProductTotalCountBySearchBean(GoodsSearchBean searchBean){
        return goodsMapper.queryProductTotalCountBySearchBean(searchBean);
    }

    /**
     * 第三方上传商品时查询关联商品
     *
     * @param pb
     * @param searchBean
     * @return
     */
    public PageBean searchThirdBySearchBeanAndPageBean(PageBean pb, GoodsSearchBean searchBean) {
        if (null != searchBean) {
            if ("1".equals(searchBean.getShowFlag())) {
                // 设置基本查询为不可用
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
            } else {
                searchBean.setGoodsBrandId("-1");
                searchBean.setGoodsCateId("-1");
                searchBean.setGoodsKeyword("");
                searchBean.setGoodsName("");
                searchBean.setGoodsNo("");
                searchBean.setShowFlag("0");
                searchBean.setStatus("-1");
                searchBean.setThirdName("");
            }
            // 如果是否第三方标记为0,设置第三方名称为空
            if (null != searchBean.getIsThird() && "0".equals(searchBean.getIsThird())) {
                    searchBean.setThirdName("");
            }
        }
        // 第三方查询行数
        pb.setRows(this.goodsMapper.queryThirdTotalCountBySearchBean(searchBean));
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        List<Object> voList = null;
        GoodsListVo listVo = null;
        List<Object> voList2 = new ArrayList<Object>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            voList = this.goodsMapper.queryThirdByPageBeanAndSearchBean(map);
            // 非空判断
            if (voList != null) {
                for (int i = 0; i < voList.size(); i++) {
                    // 查询分仓库存
                    listVo = (GoodsListVo) voList.get(i);
                    // 库存为空
                    if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                        listVo.setStock(0L);
                    } else {
                        // 设置库存
                        listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                    }
                    voList2.add(listVo);
                }
            }
            // 设置pagebean的集合数据
            pb.setList(voList2);
            // 返回结果
            return pb;
        } finally {
            // 参数置空
            map = null;
            voList = null;
            listVo = null;
            voList2 = null;
        }
    }

    /**
     * 根据商品编号查询商品是否可用
     *
     * @param goodsNo
     * @return 可用 返回true 不可用返回false
     */
    public boolean queryCountByGoodsNo(String goodsNo) {
        // 根据商品编号查询商品是否已经存在
        return this.goodsMapper.queryCountByGoodsNo(goodsNo.trim()) == 0 ? true : false;
    }

    /**
     * 生成前台搜索引擎索引
     */
    public boolean createIndex() {
        // LuceneDirceotry dirceotry = new LuceneDirceotry();
        LuceneIKUtil dirceotry = new LuceneIKUtil();
        try {
            dirceotry.index("select * from np_goods_info where goods_info_delflag='0' and goods_info_added='1'");
            return true;
        } catch (Exception e) {
            // 打印日志
            LOGGER.error("创建索引失败", e);
            return false;
        } finally {
            dirceotry = null;
        }
    }

    /**
     * 查询所有的商品列表进行导出
     *
     * @return 查询到的商品列表
     */
    public List<Object> queryAllGoodsForExport(GoodsSearchBean searchBean) {
        if (null != searchBean && null != searchBean.getGoodsName() && !"".equals(searchBean.getGoodsName())) {
                // 设置基本查询为不可用
                searchBean.setCondition("-1");
                searchBean.setSearchText("");
        }
        // d定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置map集合的参数
        map.put(ValueUtil.STARTROWNUM, 0);
        map.put(ValueUtil.ENDROWNUM, 10000000);
        map.put(ValueUtil.SEARCHBEAN, searchBean);
        // 高级查询商品列表
        List<Object> voList = this.goodsMapper.queryByPageBeanAndSearchBean(map);
        // 定义一个List集合
        List<Object> list = new ArrayList<Object>();
        GoodsListVo listVo;
        try {
            for (int i = 0; i < voList.size(); i++) {
                listVo = (GoodsListVo) voList.get(i);
                // 根据商品ID查询所属货品的库存总和
                if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                    listVo.setStock(0L);
                } else {
                    // 根据商品ID查询所属货品的库存总和
                    listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                }
                list.add(listVo);
            }
            // 返回结果
            return list;
        } finally {
            map = null;
            voList = null;
            list = null;
            listVo = null;
        }
    }

    public GoodsSpecService getGoodsSpecService() {
        return goodsSpecService;
    }

    @Resource(name = "GoodsSpecService")
    public void setGoodsSpecService(GoodsSpecService goodsSpecService) {
        this.goodsSpecService = goodsSpecService;
    }

    /**
     * 根据商品ID数组查询商品列表
     *
     * @param goodsIds
     *            商品ID数组
     * @return 查询到的集合
     */
    public List<Object> queryGoodsListVoListForExportByGoodsIds(Long[] goodsIds) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置参数
        map.put(GOODSIDS, goodsIds);
        // 根据商品ID数组查询商品列表FOR EXPORT
        List<Object> voList = this.goodsMapper.queryGoodsListVoListForExportByGoodsIds(map);
        // 定义一个List集合
        List<Object> list = new ArrayList<Object>();
        GoodsListVo listVo;
        try {
            for (int i = 0; i < voList.size(); i++) {
                listVo = (GoodsListVo) voList.get(i);
                // 根据商品ID查询所属货品的库存总和
                if (null == this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId())) {
                    listVo.setStock(0L);
                } else {
                    // 根据商品ID查询所属货品的库存总和
                    listVo.setStock(this.goodsMapper.queryStockByGoodsId(listVo.getGoodsId()));
                }
                list.add(listVo);
            }
            // 返回结果
            return list;
        } finally {
            map = null;
        }
    }

    /**
     * 批量上下架商品
     *
     * @param goodsIds
     *            商品ID数组 {@link Long}
     * @param addedSta
     *            待更新的状态 1:上架状态 0:下架状态 {@link String}
     * @param username
     *            操作人名称 {@link String}
     * @return 更新的行数 {@link Integer}
     */
    @Transactional
    public int batchUploadOrDownGoods(Long[] goodsIds, String addedSta, String username, String auditStatus, Long thirdId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 设置map集合的参数
            map.put("goodsAddedSta", addedSta);
            map.put("userName", username);
            map.put(GOODSIDS, goodsIds);
            map.put(AUDITSTATUS, auditStatus);
            // 批量上下架商品
            return this.goodsMapper.batchUploadOrDownGoods(map);
        } finally {
            if ("0".equals(addedSta)) {
                // 如果商品修改为下架状态那么把对应的货品也修改为下架
                Map<String, String> upProMap = new HashMap<String, String>();
                for (int i = 0; i < goodsIds.length; i++) {
                    // 设置map集合的参数
                    upProMap.put(GOODSID, goodsIds[i].toString());
                    upProMap.put(MODIFIEDNAME, username);
                    upProMap.put(THIRDID, thirdId.toString());
                    // 修改商品为下架状态的时候把货品信息页修改为下架状态
                    this.goodsProductMapper.updateProductAddedWithGoodsId(upProMap);
                }
            }
            if ("1".equals(addedSta)) {
                // 审核关闭，批量上架商品，则对应货品也都上架
                Map<String, String> param = new HashMap<String, String>();
                for (int i = 0; i < goodsIds.length; i++) {
                    // 设置param的参数
                    param.put(GOODSID, goodsIds[i].toString());
                    param.put(MODIFIEDNAME, username);
                    param.put(GOODSINFOADDED, "1");
                    param.put(AUDITSTATUS, "3");
                    param.put(THIRDID, thirdId.toString());
                    // 批量审核上架商品时把货品也设置审核
                    this.goodsProductMapper.updateProductAddedWithAudit(param);
                }
            }
            // 打印日志
            LOGGER.info(ValueUtil.BATCHUPLOADORDOWNGOODS);
            map = null;
        }
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public GoodsOpenSpecService getGoodsOpenSpecService() {
        return goodsOpenSpecService;
    }

    @Resource(name = "GoodsOpenSpecService")
    public void setGoodsOpenSpecService(GoodsOpenSpecService goodsOpenSpecService) {
        this.goodsOpenSpecService = goodsOpenSpecService;
    }

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "GoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    public GoodsRelatedGoodsService getGoodsRelatedGoodsService() {
        return goodsRelatedGoodsService;
    }

    @Resource(name = "GoodsRelatedGoodsService")
    public void setGoodsRelatedGoodsService(GoodsRelatedGoodsService goodsRelatedGoodsService) {
        this.goodsRelatedGoodsService = goodsRelatedGoodsService;
    }

    public GoodsReleParamService getGoodsReleParamService() {
        return goodsReleParamService;
    }

    @Resource(name = "GoodsReleParamService")
    public void setGoodsReleParamService(GoodsReleParamService goodsReleParamService) {
        this.goodsReleParamService = goodsReleParamService;
    }

    public GoodsReleExpandParamService getGoodsReleExpandParamService() {
        return goodsReleExpandParamService;
    }

    @Resource(name = "GoodsReleExpandParamService")
    public void setGoodsReleExpandParamService(GoodsReleExpandParamService goodsReleExpandParamService) {
        this.goodsReleExpandParamService = goodsReleExpandParamService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public GoodsReleTagService getGoodsReleTagService() {
        return goodsReleTagService;
    }

    @Resource(name = "GoodsReleTagService")
    public void setGoodsReleTagService(GoodsReleTagService goodsReleTagService) {
        this.goodsReleTagService = goodsReleTagService;
    }

    public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }

    @Resource(name = "GoodsMapper")
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    public GoodsOpenSpecValueService getGoodsOpenSpecValueService() {
        return goodsOpenSpecValueService;
    }

    @Resource(name = "GoodsOpenSpecValueService")
    public void setGoodsOpenSpecValueService(GoodsOpenSpecValueService goodsOpenSpecValueService) {
        this.goodsOpenSpecValueService = goodsOpenSpecValueService;
    }

    /**
     * 查询所有的商品信息用于导出
     *
     * @return 查询到的集合
     */
    public List<Object> queryAllGoodsForExport(String isThird) {
        return this.goodsMapper.queryAllGoodsToExport(isThird);
    }

    /**
     * 复制商品
     *
     * @param goodsId
     *            商品id
     * @param thirdId
     *            第三方商家id
     * @param thirdName
     *            商家名称
     * @author NINGPAI-LIH
     */
    @Override
    public void copyGoods(Long goodsId, Long thirdId, String thirdName) {
        // 所需的所有信息
        GoodsMoifiedVo goods = queryModeifiedVoByGoodsId(goodsId);
        // 定义一个Hashmap集合
        Map<String, String[]> map = new HashMap<>();

        // 筛选参数Id
        String[] paramvos = new String[goods.getExpandParamVoList().size()];
        // 筛选参数值id
        String[] parValue = new String[goods.getExpandParamVoList().size()];
        for (int i = 0; i < goods.getExpandParamVoList().size(); i++) {
            paramvos[i] = goods.getExpandParamVoList().get(i).getExpandParamVo().getExpandparamId().toString();
            parValue[i] = goods.getExpandParamVoList().get(i).getExpangparamValue().getExpandparamValueId().toString();
        }

        // 商品推荐点id
        String[] tags = new String[goods.getTags().size()];
        for (int i = 0; i < goods.getTags().size(); i++) {
            tags[i] = goods.getTags().get(i).getGoodsTag().getTagId().toString();
        }
        // 商品信息
        Goods g = goodsMapper.selectByPrimaryKey(goodsId);
        // 设置商品编号
        g.setGoodsId(null);
        // 第三方标示
        if (thirdId != 0L) {
            g.setIsThird("1");
        } else {
            g.setIsThird("0");
        }
        // 设置商家编号
        g.setGoodsBelo(thirdId);
        // 设置商家名称
        g.setGoodsBeloName(thirdName);
        g.setGoodsAdded("0");

        // 规格参数
        String[] paramValue = new String[goods.getParamVoList().size()];
        String[] paramIds = new String[goods.getParamVoList().size()];
        for (int i = 0; i < paramIds.length; i++) {
            paramIds[i] = goods.getParamVoList().get(i).getParam().getParamId().toString();
            paramValue[i] = goods.getParamVoList().get(i).getParamValue();
        }

        // 规格
        String[] specId = new String[goods.getOpenSpecList().size()];

        List<String> valuesImgLists = new ArrayList<String>();
        List<String> valueRemarkLists = new ArrayList<String>();
        List<String> specValueId = new ArrayList<String>();
        for (int i = 0; i < specId.length; i++) {

            specId[i] = goods.getOpenSpecList().get(i).getSpecId().toString();
            for (int j = 0; j < goods.getOpenSpecList().get(i).getSpecValList().size(); j++) {
                valueRemarkLists.add(goods.getOpenSpecList().get(i).getSpecValList().get(j).getSpecValueRemark());
                valuesImgLists.add(goods.getOpenSpecList().get(i).getSpecValList().get(j).getImgUrl());
                specValueId.add(goods.getOpenSpecList().get(i).getSpecValList().get(j).getSpecValueId() + "-" + goods.getOpenSpecList().get(i).getSpecValList().get(j).getSpecId());
            }
        }
        String[] openSpecValueImg = new String[valuesImgLists.size()];
        String[] openSpecValueRemark = new String[valueRemarkLists.size()];
        String[] specValues = new String[specValueId.size()];
        for (int i = 0; i < openSpecValueImg.length; i++) {
            openSpecValueImg[i] = valuesImgLists.get(i);
        }
        for (int i = 0; i < openSpecValueRemark.length; i++) {
            openSpecValueRemark[i] = valueRemarkLists.get(i);
        }
        for (int i = 0; i < specValues.length; i++) {
            specValues[i] = specValueId.get(i);
        }
        String[] aboutGoodsId = new String[goods.getRelaGoodsVo().size()];
        for (int i = 0; i < aboutGoodsId.length; i++) {
            aboutGoodsId[i] = goods.getRelaGoodsVo().get(i).getRelatedId().toString();
        }
        // 把paramvos放进map集合中
        map.put("expandParamIds", paramvos);
        // 把parValue放进map集合中
        map.put("expandParamValues", parValue);
        // 把tags放进map集合中
        map.put("tags", tags);
        // 把paramIds放进map集合中
        map.put("paramIds", paramIds);
        // 把paramvos放进map集合中
        map.put(PARAMVALUE, paramValue);
        // 把specId放进map集合中
        map.put(SPECID, specId);
        // 把openSpecValueImg放进map集合中
        map.put("openSpecValueImg", openSpecValueImg);
        // 把specValues放进map集合中
        map.put("specValues", specValues);
        // 把openSpecValueRemark放进map集合中
        map.put("openSpecValueRemark", openSpecValueRemark);
        // 把aboutGoodsId放进map集合中
        map.put(ABOUTGOODSID, aboutGoodsId);
        // 获取当前时间
        String goodsNo = UtilDate.mathString(new Date());
        boolean isT = queryCountByGoodsNo(goodsNo);
        while (!isT) {
            goodsNo = UtilDate.mathString(new Date());
            isT = queryCountByGoodsNo(goodsNo);
        }
        g.setGoodsNo(goodsNo);
        Long newGoodId = this.saveGoods(g, username, map);
        // 根据商品id查询货品列表
        List<GoodsProductVo> goodsProductVos = goodsProductMapper.queryProductListByGoodsId(goods.getGoodsId());
        // 循环遍历goodsProductVoss集合
        for (int i = 0; i < goodsProductVos.size(); i++) {
            GoodsProductVo goodsProductVo = goodsProductVos.get(i);
            String[] specIds = new String[goodsProductVo.getSpecVo().size()];
            String[] specDetailId = new String[goodsProductVo.getSpecVo().size()];
            String[] specRemark = new String[goodsProductVo.getSpecVo().size()];
            for (int j = 0; j < specIds.length; j++) {
                specIds[j] = goodsProductVo.getSpecVo().get(j).getSpec().getSpecId().toString();
                specDetailId[j] = goodsProductVo.getSpecVo().get(j).getGoodsSpecDetail().getSpecDetailId().toString();
                specRemark[j] = goodsProductVo.getSpecVo().get(j).getSpecValueRemark();
            }
            /* 保存服务支持信息 */
            String[] suppIds = null;
            if (goodsProductVo.getSuppList() != null) {
                suppIds = new String[goodsProductVo.getSuppList().size()];
                for (int j = 0; j < suppIds.length; j++) {
                    suppIds[j] = goodsProductVo.getSuppList().get(j).getSuppId().toString();
                }
            }
            Long[] wareId = null;
            Long[] productStocks = null;
            BigDecimal[] productPrices = null;
            BigDecimal[] productVipPrices = null;
            if (goodsProductVo.getProductWares() != null) {
                wareId = new Long[goodsProductVo.getProductWares().size()];
                productStocks = new Long[goodsProductVo.getProductWares().size()];
                productPrices = new BigDecimal[goodsProductVo.getProductWares().size()];
                productVipPrices = new BigDecimal[goodsProductVo.getProductWares().size()];
                for (int j = 0; j < wareId.length; j++) {
                    wareId[j] = goodsProductVo.getProductWares().get(j).getWareId();
                    productStocks[j] = goodsProductVo.getProductWares().get(j).getWareStock();
                    productPrices[j] = goodsProductVo.getProductWares().get(j).getWarePrice();
                    productVipPrices[j] = goodsProductVo.getProductWares().get(j).getWareVipPrice();
                }
            }
            // 定义一个HashMap集合
            Map<String, Object> mapInfo = new HashMap<String, Object>();
            // 把wareId参数放进map集合中
            mapInfo.put("wareId", wareId);
            // 把productStocks参数放进map集合中
            mapInfo.put("productStocks", productStocks);
            // 把productPrices参数放进map集合中
            mapInfo.put("productPrices", productPrices);
            // 把productVipPrices参数放进map集合中
            mapInfo.put("productVipPrices", productVipPrices);
            // 把specRemark参数放进map集合中
            mapInfo.put("specRemark", specRemark);

            // 根据货品id查询货品详细信息
            GoodsProduct product = goodsProductMapper.selectByGoodsInfoId(goodsProductVo.getGoodsInfoId());
            product.setGoodsId(newGoodId);
            product.setGoodsInfoId(null);
            /* 设置货品所属的boss商家 */
            if (thirdId != 0L) {
                product.setIsThird("1");
            } else {
                product.setIsThird("0");
            }
            goodsProductVo.setThirdId(thirdId);
            goodsProductVo.setThirdName(thirdName);
            // 添加货品信息
            int newId = this.goodsProductService.saveProduct(product, thirdName, specIds, specDetailId, mapInfo);
            String goodsInfoNo = UtilDate.mathString(new Date());
            // 验证货品编号是否可用
            boolean isTg = goodsProductService.checkProuctNo(goodsInfoNo);
            while (!isTg) {
                goodsInfoNo = UtilDate.mathString(new Date());
                // 验证货品编号是否可用
                isT = goodsProductService.checkProuctNo(goodsInfoNo);
            }
            product.setGoodsInfoItemNo(goodsInfoNo);
            if (thirdId == 0) {
                /* 保存分仓信息 */
                // 保存关联信息
                this.productWareService.calcProductWare(Long.parseLong(String.valueOf(newId)), productStocks, productPrices, productVipPrices, wareId);
            }
            // 批量保存关联信息
            this.goodsProductSuppService.batchInsert(suppIds, newId);
        }

    }

    /**
     * 获取商品审核开关标记
     *
     * @return
     */
    @Override
    public String selectAuditAction() {
        // 返回结果
        return goodsMapper.selectAuditAction();
    }

    /**
     * 批量审核上架商品
     *
     * @param goodsIds
     * @param addedSta
     * @param auditStatus
     * @param username
     * @return
     */
    @Override
    public int batchAuditUploadOrDownGoods(Long[] goodsIds, String addedSta, String auditStatus, String username, Long thirdId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数addedSta放进map集合中
            map.put("goodsAddedSta", addedSta);
            // 把参数username放进map集合中
            map.put("userName", username);
            // 把参数auditStatus放进map集合中
            map.put(AUDITSTATUS, auditStatus);
            // 把参数goodsIds放进map集合中
            map.put(GOODSIDS, goodsIds);
            // 批量审核上架商品
            return this.goodsMapper.batchAuditUploadOrDownGoods(map);
        } finally {
            if ("0".equals(addedSta)) {
                // 如果商品修改为下架状态那么把对应的货品也修改为下架
                Map<String, String> upProMap = new HashMap<String, String>();
                for (int i = 0; i < goodsIds.length; i++) {
                    // 把goodsId放进map集合中
                    upProMap.put(GOODSID, goodsIds[i].toString());
                    // 把username放进map集合中
                    upProMap.put(MODIFIEDNAME, username);
                    // 把gthirdId放进map集合中
                    upProMap.put(THIRDID, thirdId.toString());
                    // 修改商品为下架状态的时候把货品信息页修改为下架状态
                    this.goodsProductMapper.updateProductAddedWithGoodsId(upProMap);
                }
            }
            // 审核开启，取审核商品，则对应货品也都去审核
            Map<String, String> param = new HashMap<String, String>();
            for (int i = 0; i < goodsIds.length; i++) {
                // 把goodsId参数放进map集合修改商品为下架状态的时候把货品信息页修改为下架状态
                param.put(GOODSID, goodsIds[i].toString());
                // 把username参数放进map集合
                param.put(MODIFIEDNAME, username);
                // 把g1参数放进map集合
                param.put(AUDITSTATUS, "1");
                // 把g0参数放进map集合
                param.put(GOODSINFOADDED, "0");
                // 把thirdId参数放进map集合
                param.put(THIRDID, thirdId.toString());
                // 批量审核上架商品时把货品也设置审核
                this.goodsProductMapper.updateProductAddedWithAudit(param);
            }
            // 打印日志
            LOGGER.info(ValueUtil.BATCHUPLOADORDOWNGOODS);
            map = null;
        }
    }

    /**
     * 根据条件查询商品信息
     *
     * @param pb
     * @param thirdId
     * @param productNo
     * @return
     */
    @Override
    public PageBean queryGoodsForCoupon(PageBean pb, Long thirdId, String productNo) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 如果第三方id不为空
        // 把第三方id放进map集合中
        if (null != thirdId) {
            map.put(THIRDID, thirdId.toString());
        }
        // 如果货品编号不为空
        // 就把货品编号放进map集合中
        if (null != productNo && !"".equals(productNo)) {
            map.put("productNo", productNo);
        }
        // 设置PageBean的总行数
        pb.setRows(this.goodsMapper.queryCountForCoupon(map));
        // 把当前行放进map集合中
        map.put("pageNo", pb.getPageNo());
        // 把开始行数放进map集合中
        map.put("startRowNum", pb.getStartRowNum());
        // 把结束行数放进map集合中
        map.put("endRowNum", pb.getEndRowNum());
        // 设置PageBean的集合数据
        pb.setList(this.goodsMapper.queryGoodsForCoupon(map));
        return pb;
    }

    /**
     * 根据ID获取商品信息
     *
     * @param goodsId
     * @return
     */
    @Override
    public Goods queryGoodsByGoodsId(Long goodsId) {
        // 根据id查询信息
        return goodsMapper.queryGoodsByGoodsId(goodsId);
    }

    /**
     * 批量下架商品
     *
     * @param goodsIds
     *            需要下架的商品的ID的集合
     * @return 操作结果
     */
    @Override
    public int batchDown(Long[] goodsIds, String username) {
        // 定义一个map集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把商品id放进map集合中
        map.put(GOODSIDS, goodsIds);
        // 把用户名称放进map集合中
        map.put(MODIFIEDNAME, username);
        // 批量下架
        goodsMapper.batchDown(map);
        // 批量下架商品
        return goodsMapper.batchDownGoods(map);
    }

    /**
     * 批量上架商品
     *
     * @param goodsIds
     * @param username
     * @return
     */
    @Override
    public int batchUp(Long[] goodsIds, String username) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把商品id放进map集合中
        map.put(GOODSIDS, goodsIds);
        // 把用户名称放map集合中
        map.put(MODIFIEDNAME, username);
        // 批量上架
        goodsMapper.batchUp(map);
        // 批量上架商品
        return goodsMapper.batchUpGoods(map);
    }

    /**
     * 批量修改库存 用于后台批量修改库存
     *
     * @param goodsId
     * @auhor houyichang 2015/8/24
     */
    @Transactional
    @Override
    public int batchUpdateStock(Long[] goodsId, String stock) {
        // 定义一个Map集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stock", stock);
        try {
            // 判读传入的商品id数组是否为空
            if (goodsId != null && goodsId.length > 0) {
                // 循环商品id数组
                for (int i = 0; i < goodsId.length; i++) {
                    map.put(GOODSID, goodsId[i]);
                    // 查询商品下是否有货品
                    int productNum = this.goodsMapper.queryProductByGoodsId(goodsId[i]);
                    // 如果商品下有货品就执行修改库存操作
                    if (productNum > 0) {
                        this.goodsMapper.batchUpdateStock(map);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(""+e);
            return 0;
        } finally {
            map = null;
        }
        return 1;
    }
}
