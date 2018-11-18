/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service.impl;

import com.ningpai.goods.service.GoodsService;
import com.ningpai.third.goods.dao.ThirdGoodsMapper;
import com.ningpai.third.goods.service.ThirdGoodsService;
import com.ningpai.third.goods.util.ThirdGoodsSearchBean;
import com.ningpai.third.goods.vo.SalesProductVo;
import com.ningpai.third.seller.bean.StoreInfo;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三方商品Service实现
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月12日 下午3:07:03
 * @version 2.0
 */
@Service("ThirdGoodsService")
public class ThirdGoodsServiceImpl implements ThirdGoodsService {

    private static final String GOODSADDED = "goodsAdded";
    private static final String CONDITION = "condition";
    private static final String SEARCHTEXT = "searchText";
    private static final String GOODSNAME = "goodsName";
    private static final String GOODSNO = "goodsNo";
    private static final String LOWGOODSPRICE = "lowGoodsPrice";
    private static final String HIGHGOODSPRICE = "highGoodsPrice";
    private static final String LOWCREATETIME = "lowCreateTime";
    private static final String HIGHCREATETIME = "highCreateTime";
    private static final String NPCATEID = "npCateId";
    private static final String THIRDCATEID = "thirdCateId";
    private static final String THIRDID = "thirdId";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";

    /**
     * 第三方商品DAO接口
     */
    private ThirdGoodsMapper thirdGoodsMapper;

    @Resource(name = "sellerService")
    private SellerService sellerService;

    @Resource(name = "GoodsService")
    private GoodsService goodsService;

    /**
     * 根据分页辅助Bean查询第三方商品列表
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            第三方店铺的签约ID {@link Long}
     * @param searchBean
     *            查询辅助Bean
     *            {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param goodsAddedSta
     *            查询商品列表的上下架状态 {@link Long}
     * @return
     */
    public PageBean queryThirdGoodsList(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta) {
        Long goodsAddedStaNew = goodsAddedSta;
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == goodsAddedStaNew) {
            goodsAddedStaNew = 1L;
        }
        map.put(GOODSADDED, goodsAddedStaNew.toString());
        if (null != searchBean) {
            // 如果查询标记不等于空,表示是简单查询
            if (null != searchBean.getCondition()) {
                // 条件
                map.put(CONDITION, searchBean.getCondition());
                // 搜索条件
                map.put(SEARCHTEXT, searchBean.getSearchText());
            } else {
                // 商品名称
                map.put(GOODSNAME, searchBean.getGoodsName());
                // 商品编号
                map.put(GOODSNO, searchBean.getGoodsNo());
                // 商品最低价格
                map.put(LOWGOODSPRICE, searchBean.getLowPrice());
                // 商品最高价格
                map.put(HIGHGOODSPRICE, searchBean.getHighPrice());
                // 起始日期
                map.put(LOWCREATETIME, searchBean.getLowCreateTime());
                // 结束日期
                map.put(HIGHCREATETIME, searchBean.getHighCreateTime());
                // 商品分类ID
                map.put(NPCATEID, searchBean.getNpCateId());
                // 商检分类ID
                map.put(THIRDCATEID, searchBean.getThirdCateId());
            }
        }
        try {
            // 第三方商家
            map.put(THIRDID, thirdId);
            // 获取分页的数据行数
            pb.setRows(this.thirdGoodsMapper.queryThirdGoodsCount(map));
            // 分页开始页
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 分页结束页
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 装载根据条件筛选出来的商品集合
            pb.setList(this.thirdGoodsMapper.queryThirdGoodsList(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 分页辅助Bean {@link com.ningpai.util.PageBean}
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            第三方店铺的签约ID {@link Long}
     * @param searchBean
     *            查询辅助Bean
     *            {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param goodsAddedSta
     *            查询商品列表的上下架状态 {@link Long}
     * @param flag
     *            商品审核状态标记
     * @return
     */
    public PageBean queryThirdGoodsListByFlag(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta, Long flag) {
        Long goodsAddedStaNew = goodsAddedSta;
        Map<String, Object> map = new HashMap<String, Object>();
        if (null == goodsAddedStaNew) {
            goodsAddedStaNew = 1L;
        }
        map.put(GOODSADDED, goodsAddedStaNew.toString());
        map.put("flag", flag.toString());
        if (null != searchBean) {
            // 如果查询标记不等于空,表示是简单查询
            if (null != searchBean.getCondition()) {
                map.put(CONDITION, searchBean.getCondition());
                map.put(SEARCHTEXT, searchBean.getSearchText());
            } else {
                // 如果价格上限小于价格下限
                if (searchBean.getHighPrice() != null && searchBean.getLowPrice() != null && searchBean.getHighPrice().compareTo(searchBean.getLowPrice()) < 0) {
                    BigDecimal lowPrice = searchBean.getLowPrice();
                    BigDecimal highPrice = searchBean.getHighPrice();
                    // 价格互换
                    searchBean.setHighPrice(lowPrice);
                    searchBean.setLowPrice(highPrice);
                    // 分页查询 最高价格
                    map.put("highPrice", searchBean.getHighPrice());
                    // 分页查询 最低价格
                    map.put("lowPrice", searchBean.getLowPrice());
                }
                // 商品名称
                map.put(GOODSNAME, searchBean.getGoodsName());
                // 商品编号
                map.put(GOODSNO, searchBean.getGoodsNo());
                // 筛选的最低价格
                map.put(LOWGOODSPRICE, searchBean.getLowPrice());
                // 筛选的最高价格
                map.put(HIGHGOODSPRICE, searchBean.getHighPrice());
                // 筛选的开始时间
                map.put(LOWCREATETIME, searchBean.getLowCreateTime());
                // 筛选的结束日记
                map.put(HIGHCREATETIME, searchBean.getHighCreateTime());
                // 分类ID
                map.put(NPCATEID, searchBean.getNpCateId());
                // 商家分类ID
                map.put(THIRDCATEID, searchBean.getThirdCateId());
            }
        }
        try {
            // 商家ID
            map.put(THIRDID, thirdId);
            // 设置要求分页的数据条数
            pb.setRows(this.thirdGoodsMapper.queryThirdGoodsCountByFlag(map));
            // 分页的开始页
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 分页的结束页
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 装载根据条件筛选出的商品集合
            pb.setList(this.thirdGoodsMapper.queryThirdGoodsListByFlag(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据第三方ID查询销量排行的商品
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public List<SalesProductVo> queryTopSalesGoods(Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 商家ID
            map.put(THIRDID, thirdId);
            // 查询店铺销量排行商品
            return this.thirdGoodsMapper.queryTopSalesByThirdId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据分页辅助Bean查询第三方商品复制列表
     * 
     * @param pb
     *            分页辅助Bean {@link com.ningpai.util.PageBean}
     * @param thirdId
     *            第三方店铺的签约ID {@link Long}
     * @param searchBean
     *            查询辅助Bean
     *            {@link com.ningpai.third.goods.util.ThirdGoodsSearchBean}
     * @param goodsAddedSta
     *            查询商品列表的上下架状态 {@link Long}
     * @return
     */
    @Override
    public PageBean queryThirdGoodsCopyList(PageBean pb, Long thirdId, ThirdGoodsSearchBean searchBean, Long goodsAddedSta) {
        Map<String, Object> map = new HashMap<String, Object>();
        Long goodsAddedStaNew = goodsAddedSta;
        if (null == goodsAddedStaNew) {
            goodsAddedStaNew = 1L;
        }
        map.put(GOODSADDED, goodsAddedStaNew.toString());
        if (null != searchBean) {
            // 如果查询标记不等于空,表示是简单查询
            if (null != searchBean.getCondition()) {
                map.put(CONDITION, searchBean.getCondition());
                map.put(SEARCHTEXT, searchBean.getSearchText());
            } else {
                // 商品名称
                map.put(GOODSNAME, searchBean.getGoodsName());
                // 商品编号
                map.put(GOODSNO, searchBean.getGoodsNo());
                // 筛选的最低价格
                map.put(LOWGOODSPRICE, searchBean.getLowPrice());
                // 筛选的最高价格
                map.put(HIGHGOODSPRICE, searchBean.getHighPrice());
                // 筛选的开始时间
                map.put(LOWCREATETIME, searchBean.getLowCreateTime());
                // 筛选数据的结束时间
                map.put(HIGHCREATETIME, searchBean.getHighCreateTime());
                // 分类ID
                map.put(NPCATEID, searchBean.getNpCateId());
                // 商家的分类ID
                map.put(THIRDCATEID, searchBean.getThirdCateId());
            }
        }
        try {
            // 商家ID
            map.put(THIRDID, thirdId);
            // 设置要分页的数据条数
            pb.setRows(this.thirdGoodsMapper.queryThirdGoodsCopyCount(map));
            // 分页开始的页数
            map.put(STARTROWNUM, pb.getStartRowNum());
            // 分页结束的页数
            map.put(ENDROWNUM, pb.getEndRowNum());
            // 装载根据条件筛选出来的 商品集合
            pb.setList(this.thirdGoodsMapper.queryThirdGoodsCopyList(map));
            return pb;
        } finally {
            map = null;
        }
    }

    /**
     * 根据商品id数组复制商品
     * 
     * @param goodsIds
     *            需要复制的商品id数组
     * @param request
     * @return
     */
    @Override
    @Transactional
    public int copyByGoodsIds(Long[] goodsIds, HttpServletRequest request) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 根据ID获取详细的店铺信息
        StoreInfo info = sellerService.selectByStoreId(thirdId);
        for (int i = 0; i < goodsIds.length; i++) {
            // 循环复制商品信息
            goodsService.copyGoods(goodsIds[i], thirdId, info.getStoreName());
        }
        return 0;
    }

    /**
     * 获取前台地址
     * 
     * @return
     */
    @Override
    public String bsetUrl() {
        // 获取前台地址
        return this.thirdGoodsMapper.bsetUrl();
    }

    public ThirdGoodsMapper getThirdGoodsMapper() {
        return thirdGoodsMapper;
    }

    @Resource(name = "ThirdGoodsMapper")
    public void setThirdGoodsMapper(ThirdGoodsMapper thirdGoodsMapper) {
        this.thirdGoodsMapper = thirdGoodsMapper;
    }

}
