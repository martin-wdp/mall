/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.controller;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.PunishRecord;
import com.ningpai.customer.service.PunishRecordService;
import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.dao.GetOnOffMapper;
import com.ningpai.goods.service.*;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsMoifiedVo;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.third.goods.service.ThirdGoodsService;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.third.goods.util.ThirdGoodsSearchBean;
import com.ningpai.third.goods.util.ThirdPathUtil;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.third.goods.vo.ThirdCateVo;
import com.ningpai.third.seller.service.SellerService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 第三发商品控制器
 * </p>
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月8日 上午10:49:07
 * @version 2.0
 */
@Controller
public class ThirdGoodsController {

    private static final String THIRDID = "thirdId";
    private static final String ISTHIRDAUDITUSED = "isThirdAuditUsed";
    private static final String PARAMID = "paramId";
    private static final String EXPANDPARAMID = "expandParamId";
    private static final String SEARCHBEAN = "searchBean";
    private static final String GOODSADDEDSTA = "goodsAddedSta";
    private static final String GOODS_THIRDGOODSLIST = "goods/thirdgoodslist";

    // 商品Service接口
    private GoodsService goodsService;
    // 第三方商品Service接口
    private ThirdGoodsService thirdGoodsService;
    // 第三方商品分类Service接口
    private ThirdCateService cateService;
    // 第三方用到的其他接口
    private ThirdOtherService thirdOtherService;
    // 第三方类型接口
    private ThirdCateService thirdCateService;
    // 商品标签接口
    private GoodsTagService goodsTagService;
    // 商品品牌接口
    private GoodsBrandService goodsBrandService;
    // 仓库Service接口卡
    private WareHouseService wareHouseService;
    // 商品类型接口
    private GoodsCateService goodsCateService;
    // 货品仓库接口
    private ProductWareService productWareService;
    // 第三方用到的支持service接口
    private ServiceSupportMapperService serviceSupportMapperService;
    // 开关控制接口
    private GetOnOffMapper getOnOffMapper;
    // 运费模板接口
    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;
    // 商品索引
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;
    @Resource(name = "sellerService")
    private SellerService sellerService;
    @Resource(name = "PunishRecordService")
    private PunishRecordService punishRecordService;

    /**
     *
     * @param request
     * @param response
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @return
     * @throws IOException
     */
    @RequestMapping("/upthirdgoods")
    public ModelAndView upThirdGoods(HttpServletRequest request, HttpServletResponse response, String n, String l) throws IOException {
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商家Id
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 查询默认物流模板 其他商家
        FreightExpress fe = freightTemplateService.selectFreightExpressByDistriThirdId(thirdId);
        // 查询处罚的商家信息
        List<PunishRecord> punishRecordList = punishRecordService.queryInfoByTidandDate(thirdId);
        // 判断是否有处罚的商家
        if (punishRecordList != null) {
            // 遍历需要处罚的商家
            for (int i = 0; i < punishRecordList.size(); i++) {
                // 创建单个处罚的商家对象
                PunishRecord punishRecord = punishRecordList.get(i);
                // 如果处罚的规则为7 就限制该商家不能上传商品
                if (punishRecord.getPunishId() == 7) {
                    response.setContentType("text/html;charset=utf-8");
                    // 创建一个读写器
                    PrintWriter out = response.getWriter();
                    // 用于页面显示
                    out.write("<script>window.location.href='" + request.getContextPath() + "/thirdgoodsmanager.html';</script>");
                    return null;

                }
            }
        }
        String s = "0";
        // 判断是否有默认模板
        if (fe != null) {
            s = "1";
        }
        // 该方法执行完跳转的页面
        return new ModelAndView("goods/upthirdgoods")
        // 查询所有的商品标签
                .addObject("tagList", this.goodsTagService.queryAllTag())
                // 根据第三方ID查询签约的品牌
                .addObject("brandList", this.thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute(ThirdValueBean.THIRDID)))
                // 查询所有的仓库记录
                .addObject("wareHouse", this.wareHouseService.queryAllWareHouse())
                // 产寻所有的服务支持
                .addObject("support", this.serviceSupportMapperService.selectAll())
                // 根据是否有默认模板来定
                .addObject("haveFreight", s)
                // 获取审核商品开关标记
                .addObject(ISTHIRDAUDITUSED, this.getOnOffMapper.getOnOffFlag());
    }

    /**
     *
     * @param goods
     *            商品信息
     * @param request
     * @param resp
     * @throws IOException
     *             抛出的异常信息
     */
    @RequestMapping("/sathirdgoods")
    public void saveThirdGoods(Goods goods, HttpServletRequest request, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String[] tags = request.getParameterValues("tags");
        String[] paramId = request.getParameterValues(PARAMID);
        String[] paramValue = request.getParameterValues("paramValue");
        // 拓展ID
        String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
        // 拓展详细信息
        String[] expandParamValue = request.getParameterValues("expandParamValue");
        // 规格
        String[] specs = request.getParameterValues("specs");
        // 规格值
        String[] specsValue = request.getParameterValues("specsValue");
        // 规格照片
        String[] specsValueImg = request.getParameterValues("specsValueImg");
        // 规格信息
        String[] specsValueRemark = request.getParameterValues("specsValueRemark");
        // 相关信息
        String[] about = request.getParameterValues("about");
        /* 第三方分类ID */
        String goodsThirdCateId = request.getParameter("goodsThirdCateId");
        // 判断分类ID是否为空
        if (null != goodsThirdCateId) {
            // 商家分类ID
            goods.setThirdCateId(Long.parseLong(goodsThirdCateId));
        }
        // 第三方商品审核开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        // 装载返回到页面的数据
        Map<String, String[]> map = new HashMap<String, String[]>();
        Long newId = null;
        try {
            map.put("tags", tags);
            // 拓展的id
            map.put("expandParamIds", expandParamId);
            // 拓展的详细信息
            map.put("expandParamValues", expandParamValue);
            map.put("paramIds", paramId);
            // 参数值
            map.put(ValueUtil.PARAMVALUE, paramValue);
            // 相关信息
            map.put(ValueUtil.ABOUTGOODSID, about);
            // 规格ID
            map.put("specId", specs);
            // 规格名称
            map.put("specValues", specsValue);
            // 规格上传额图片
            map.put("openSpecValueImg", specsValueImg);
            // 规格的相关介绍
            map.put("openSpecValueRemark", specsValueRemark);
            // 商家ID
            goods.setIsThird("1");
            // 商品所属的商家id
            goods.setGoodsBelo((Long) request.getSession().getAttribute(THIRDID));
            // 商品所属的商家名称
            goods.setGoodsBeloName((String) request.getSession().getAttribute("storeName"));
            // 是否立即上架0不上架 1上架
            if(Integer.parseInt(goods.getGoodsAdded())==0){
                goods.setAuditStatus("0");
                goods.setGoodsAdded("0");
            }else if(Integer.parseInt(goods.getGoodsAdded())==1 && Integer.parseInt(isThirdAuditUsed) == 1){
                goods.setAuditStatus("1");
                goods.setGoodsAdded("0");
            }else {
                goods.setAuditStatus("3");
                goods.setGoodsAdded("1");
            }
            // 保存商品
            newId = this.goodsService.saveGoods(goods, (String) request.getSession().getAttribute(ValueUtil.NAME), map);
            out.append("<script>parent.call_save_goods('" + newId + "');</script>");
        } finally {
            // 根据分类ID查询分类信息
            ThirdCateVo tcv = this.cateService.queryThirdCateById(goods.getCatId());
            if (tcv != null) {
                // 把最近使用的记录保存到cookie中
                this.cateService.saveToCookie(tcv, request, resp);
            }
            out.flush();
            tags = null;
            paramId = null;
            paramValue = null;
            expandParamId = null;
            expandParamValue = null;
            specs = null;
            specsValue = null;
            specsValueImg = null;
            about = null;
            map = null;
            newId = null;
        }
    }

    /**
     * 商品复制列表
     *
     * @param pb
     *            分页参数
     * @param searchBean
     *            查询参数
     * @param request
     *            请求
     * @param n
     *            头部导航索引
     *
     * @param l
     *            左侧导航索引
     * @return
     */
    @RequestMapping("/thirdcopylist")
    public ModelAndView thirdCopyList(PageBean pb, ThirdGoodsSearchBean searchBean, HttpServletRequest request, String n, String l, Long thirdId) {

        if (null != n && null != l) {
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        }
        // 设置分页辅助bean的地址为当前控制器的地址
        pb.setUrl("thirdcopylist.html");
        // 装载相关信息 用于页面显示
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 根据分页辅助Bean查询第三方商品复制列表
            map.put("pb", this.thirdGoodsService.queryThirdGoodsCopyList(pb, thirdId, searchBean, null));
            // 第三方商品查询辅助Bean
            map.put(SEARCHBEAN, searchBean);
            // 添加的状态
            map.put(GOODSADDEDSTA, 0);
            // 商家id
            map.put(THIRDID, thirdId);
            // 查询所有已开通的店铺信息
            map.put("storeList", sellerService.selectAll());
            // 设置要跳转的页面 与传到页面的值
            return new ModelAndView("goods/thirdgoodscopylist", "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 复制商品
     *
     * @param thirdGoodsId
     *            商品id数组
     * @param request
     *            请求
     * @return
     */
    @RequestMapping("copygoodsbygoodsids")
    @ResponseBody
    public ModelAndView copyGoodsByGoodsIds(Long[] thirdGoodsId, HttpServletRequest request) {
        // 根据商品id数组复制商品
        thirdGoodsService.copyByGoodsIds(thirdGoodsId, request);
        return new ModelAndView("redirect:/thirdcopylist.html");
    }

    /**
     * 新流程更新商品详细介绍
     *
     * @param goodsId
     *            商品ID
     * @param goodsDetailDesc
     *            详细介绍内容
     * @param request
     *            请求
     * @return 商品ID
     * @throws java.io.IOException
     */
    @RequestMapping("/tNewUploadSaveGoodsDesc")
    public void newUploadSaveGoodsDesc(Long goodsId, String goodsDetailDesc, String goodsMobileDesc, HttpServletResponse resp, HttpServletRequest request) throws IOException {
        // 创建一个读写器
        PrintWriter out = resp.getWriter();
        // 创建单个商品对象
        Goods goods = new Goods();
        // 设置商品的iD
        goods.setGoodsId(goodsId);
        // 验证商品的详细介绍信息
        if (null != goodsDetailDesc && goodsDetailDesc.length() > 0 && !"".equals(goodsDetailDesc)) {
            goods.setGoodsDetailDesc(goodsDetailDesc);
        } else {
            goods.setGoodsDetailDesc("");
        }
        // 移动端的商品详细信息
        if (null != goodsMobileDesc && goodsMobileDesc.length() > 0 && !"".equals(goodsMobileDesc)) {
            goods.setMobileDesc(goodsMobileDesc);
        } else {
            goods.setMobileDesc("");
        }
        // 只更新商品详细介绍
        this.goodsService.updateGoodsDesc(goods);
        // 第三方商品审核开关标记
        // Long isThirdAuditUsed =
        // Long.valueOf(this.getOnOffMapper.getOnOffFlag());
        // 放入session中 用于JS做判断使用
        request.getSession().setAttribute(ISTHIRDAUDITUSED, ISTHIRDAUDITUSED);
        // 返回数据 用于页面显示
        out.append("<script>parent.call_save_desc('" + goodsId + "');</script>");
    }

    /**
     * 去修改商品页面
     *
     * @param goodsId
     *            待修改的商品ID {@link Long}
     */
    @RequestMapping("/toModifiedThirdGoods")
    public ModelAndView toModifiedThirdGoods(Long goodsId, Long goodsAddedSta, HttpServletRequest request) {
        // 创建一个集合 装载数据 传到页面 用于页面展示
        Map<String, Object> map = new HashMap<String, Object>();
        // 根据商品ID查询修改时的Vo
        GoodsMoifiedVo goods = this.goodsService.queryModeifiedVoByGoodsId(goodsId);
        // 获取审核商品开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        try {
            map.put(GOODSADDEDSTA, goodsAddedSta);
            // 根据商品ID查询修改时的Vo
            map.put("goodsModifiedVo", goods);
            // 根据分类ID查询分类信息
            Object thirdCate = this.thirdCateService.queryThirdCateById(goods.getThirdCateId());
            // 第三方分类ID
            map.put("thirdCate", thirdCate);
            // 第三方审核是否开启 0 不开启 1 开启
            map.put(ISTHIRDAUDITUSED, isThirdAuditUsed);
            // 根据第三方ID查询签约的品牌
            map.put("brandList", this.thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute(ThirdValueBean.THIRDID)));
            // 商品规格
            map.put("Cate", goods.getGoodsCate());
            // 设置跳转路径 装载要在页面显示的数据信息
            return new ModelAndView("goods/modifiedThirdgoods", "map", map);
        } finally {
            map = null;
            goods = null;
        }
    }

    /**
     * 修改第三方商品
     *
     * @param goods
     *            待更新的商品实体 {@link com.ningpai.goods.bean.Goods}
     * @param goodsAddedSta
     *            商品列表的标记 0:待售列表 1:在售列表
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/modithirdgoods")
    public ModelAndView modiThirdGoods(Goods goods, HttpServletRequest request, MultipartHttpServletRequest request2, Long goodsAddedSta, HttpServletResponse response)
            throws UnsupportedEncodingException {
            // 商家id
        Long thirdId = (Long) request.getSession().getAttribute("thirdId");
        Goods  goodthird = goodsService.queryGoodsByGoodsId(goods.getGoodsId());
            Long goodsBelo = null;
        if (goodthird != null) {
            goodsBelo = goodthird.getGoodsBelo();
        }
        if (thirdId.equals(goodsBelo)) {
            // 商品规格
            String[] tags = request.getParameterValues("goods_tag");
            // 推展id
            String[] expandParamId = request.getParameterValues(EXPANDPARAMID);
            // 拓展的详细信息
            String[] expandparamValue = request.getParameterValues("expandparamValue");
            String[] paramId = request.getParameterValues(PARAMID);
            String[] paramValue = request.getParameterValues("paramValue");
            // 相关的商品ID
            String[] aboutGoodsId = request.getParameterValues("aboutGoodsId");
            Map<String, String[]> map = new HashMap<String, String[]>();
            // 规格
            map.put("tags", tags);
            // 装载拓展ID
            map.put(EXPANDPARAMID, expandParamId);
            // 装载拓展详细信息
            map.put("expandparamValue", expandparamValue);
            map.put(PARAMID, paramId);
            map.put(ValueUtil.PARAMVALUE, paramValue);
            map.put(ValueUtil.ABOUTGOODSID, aboutGoodsId);
            // 第三方审核开启时，改变审核状态
            String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
            if(Integer.parseInt(goods.getGoodsAdded())==0){
                goods.setAuditStatus("0");
                goods.setGoodsAdded("0");
            }else if(Integer.parseInt(goods.getGoodsAdded())==1 && Integer.parseInt(isThirdAuditUsed) == 1){
                goods.setAuditStatus("1");
                goods.setGoodsAdded("0");
            }else {
                goods.setAuditStatus("3");
                goods.setGoodsAdded("1");
            }
            // 更新商品信息
            this.goodsService.updateGoods(goods, map, isThirdAuditUsed, ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
            // 把当前使用的第三方分类添加到cookie中
            ThirdCateVo tcv = this.cateService.queryThirdCateById(goods.getCatId());
            if (tcv != null) {
                this.cateService.saveToCookie(tcv, request2, response);
            }
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goods.getGoodsId());

            if (goodsAddedSta == 1L) {
                // 商品列表控制器
                return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDGOODSMANAGER));
            } else {
                // 待售商品列表控制器
                return new ModelAndView(new RedirectView(ThirdPathUtil.WAITSALETHIRDGOODS));
            }
        } else {
            return null;
        }
    }

    /**
     * 获取自动生成商品编号
     * 
     * @param catId
     * @param request
     * @return
     */
    @RequestMapping("/getGoodNumber")
    @ResponseBody
    public String getGoodNumber(String catId, HttpServletRequest request) {
        StringBuilder goodsNumber = new StringBuilder();
        String mands;
        goodsNumber.append(catId);
        // 根据相关的分类判断 设置随机数
        if (catId.length() <= 3) {
            mands = (String.valueOf(Math.round(Math.random() * 10000000000000L)));
        } else if (catId.length() <= 6) {
            mands = (String.valueOf(Math.round(Math.random() * 1000000000L)));
        } else if (catId.length() <= 9) {
            mands = (String.valueOf(Math.round(Math.random() * 10000000L)));
        } else {
            mands = (String.valueOf(Math.round(Math.random() * 100000L)));
        }
        goodsNumber.append(mands);
        return goodsNumber.toString();
    }

    /**
     * 在售商品列表
     */
    @RequestMapping("/thirdgoodsmanager")
    public ModelAndView goodsManger(PageBean pb, ThirdGoodsSearchBean searchBean, HttpServletRequest request, String n, String l) {
        if (null != n && null != l) {
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        }
        // 获取审核商品开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        // 设置分页辅助bean的地址为当前控制器的地址
        pb.setUrl(ThirdPathUtil.THIRDGOODSMANAGER);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (Integer.parseInt(isThirdAuditUsed) == 1) {
                map.put("pb", this.thirdGoodsService.queryThirdGoodsListByFlag(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, 1L, 1L));
            } else {
                map.put("pb", this.thirdGoodsService.queryThirdGoodsList(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, 1L));
            }
            // 第三方商品查询辅助Bean
            map.put(SEARCHBEAN, searchBean);
            // 获取审核商品开关标记
            map.put(ISTHIRDAUDITUSED, isThirdAuditUsed);
            map.put(GOODSADDEDSTA, 1);
            // 获取前台地址
            map.put("bset", this.thirdGoodsService.bsetUrl());
            map.put("flag", 0);
            // 设置跳转的路径 与传到前台的集合数据
            return new ModelAndView(GOODS_THIRDGOODSLIST, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 待售商品列表
     */
    @RequestMapping("/waitSaleThirdGoods")
    public ModelAndView waitSaleThirdGoods(PageBean pb, ThirdGoodsSearchBean searchBean, HttpServletRequest request, String n, String l) {
        if (null != n && null != l) {
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        }
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        // 设置分页辅助bean的地址为当前控制器的地址
        pb.setUrl(ThirdPathUtil.WAITSALETHIRDGOODS);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (Integer.parseInt(isThirdAuditUsed) == 1) {
                // 分页辅助Bean
                map.put("pb", this.thirdGoodsService.queryThirdGoodsListByFlag(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, 0L, 2L));
            } else {
                // 根据分页辅助Bean查询第三方商品列表
                map.put("pb", this.thirdGoodsService.queryThirdGoodsList(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, 0L));
            }
            // 第三方商品查询辅助Bean
            map.put(SEARCHBEAN, searchBean);
            map.put(ISTHIRDAUDITUSED, isThirdAuditUsed);
            map.put(GOODSADDEDSTA, 0);
            // 获取前台地址
            map.put("bset", this.thirdGoodsService.bsetUrl());
            map.put("flag", 1);
            // 设置跳转路径 返回装载到页面的数据
            return new ModelAndView(GOODS_THIRDGOODSLIST, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 审核商品列表
     * 
     * @param pb
     * @param searchBean
     * @param request
     * @param n
     *            头部的导航索引
     * @param l
     *            左侧的导航索引
     * @return
     */
    @RequestMapping("/auditgoodslist")
    public ModelAndView auditGoodsList(PageBean pb, ThirdGoodsSearchBean searchBean, HttpServletRequest request, String n, String l) {
        if (null != n && null != l) {
            MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        }
        // 获取审核商品开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        // 设置分页辅助bean的地址为当前控制器的地址
        pb.setUrl(ThirdPathUtil.AUDITGOODSLIST);
        // 装载要返回到页面的集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (Integer.parseInt(isThirdAuditUsed) == 1) {
                // 分页辅助Bean
                map.put("pb", this.thirdGoodsService.queryThirdGoodsListByFlag(pb, (Long) request.getSession().getAttribute(THIRDID), searchBean, 0L, 3L));
            }
            // 商品查询
            map.put(SEARCHBEAN, searchBean);
            map.put(ISTHIRDAUDITUSED, isThirdAuditUsed);
            map.put(GOODSADDEDSTA, 2);
            // 根据商品id数组复制商品
            map.put("bset", this.thirdGoodsService.bsetUrl());
            map.put("flag", 2);
            // 设置要跳转的页面
            return new ModelAndView(GOODS_THIRDGOODSLIST, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 删除第三方商品
     * 
     * @param thirdGoodsId
     *            待删除的第三方商品ID {@link Long}
     * @param goodsAddedSta
     *            状态标记 {@link Long}
     */
    @RequestMapping("/delThirdGoods")
    public ModelAndView delThirdGoods(Long thirdGoodsId, Long goodsAddedSta, HttpServletRequest request) {
        this.goodsService.delThirdGoods(thirdGoodsId, (Long) request.getSession().getAttribute(THIRDID),
                ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername());
        Long[] ls = new Long[] { thirdGoodsId };
        List<Long> sList = goodsProductService.selectInfoIdList(ls);
        // 删除索引
        goodsElasticSearchService.batchDeleteGoodsIndexToEs(sList);
        if (null == goodsAddedSta || goodsAddedSta == 1) {
            return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDGOODSMANAGER));
        } else {
            return new ModelAndView(new RedirectView(ThirdPathUtil.WAITSALETHIRDGOODS));
        }
    }

    /**
     * 批量下架第三方商品
     * 
     * @param thirdGoodsId
     *            第三方商品ID数组 {@link Long}
     */
    @RequestMapping("/batchDownThirdGoods")
    public ModelAndView batchDownThirdGoods(Long[] thirdGoodsId, HttpServletRequest request) {
        // 批量上下架商品
        this.goodsService.batchUploadOrDownGoods(thirdGoodsId, "0", ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), "0", (Long) request
                .getSession().getAttribute(THIRDID));
        // 循环修改索引
        for (Long goodsId : thirdGoodsId) {
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        }

        return new ModelAndView(new RedirectView(ThirdPathUtil.THIRDGOODSMANAGER));
    }

    /**
     * 批量上架第三方商品 若商品审核开启，则默认去审核所有货品
     * 
     * @param thirdGoodsId
     *            第三方商品ID数组 {@link Long}
     */
    @RequestMapping("/batchUploadThirdGoods")
    public ModelAndView batchUploadThirdGoods(Long[] thirdGoodsId, HttpServletRequest request, String auditStatus) {
        String auditStatusNew = auditStatus;
        // 获取审核商品开关标记
        String isThirdAuditUsed = this.getOnOffMapper.getOnOffFlag();
        if (Integer.parseInt(isThirdAuditUsed) == 1) {// 审核开启
            auditStatusNew = "1";
            // 批量审核上架商品
            this.goodsService.batchAuditUploadOrDownGoods(thirdGoodsId, "0", auditStatusNew,
                    ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), (Long) request.getSession().getAttribute(THIRDID));
        } else {// 审核关闭
            this.goodsService.batchUploadOrDownGoods(thirdGoodsId, "1", ((Customer) request.getSession().getAttribute(ThirdValueBean.CUST)).getCustomerUsername(), "3",
                    (Long) request.getSession().getAttribute(THIRDID));
        }
        // 循环修改索引
        for (Long goodsId : thirdGoodsId) {
            // 修改索引
            goodsElasticSearchService.updateOneGoodsIndexToEs(goodsId);
        }
        return new ModelAndView(new RedirectView(ThirdPathUtil.WAITSALETHIRDGOODS));
    }

    public GoodsService getGoodsService() {
        return goodsService;
    }

    @Resource(name = "GoodsService")
    public void setGoodsService(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    public ThirdGoodsService getThirdGoodsService() {
        return thirdGoodsService;
    }

    @Resource(name = "ThirdGoodsService")
    public void setThirdGoodsService(ThirdGoodsService thirdGoodsService) {
        this.thirdGoodsService = thirdGoodsService;
    }

    public ThirdCateService getCateService() {
        return cateService;
    }

    @Resource(name = "ThirdCateService")
    public void setCateService(ThirdCateService cateService) {
        this.cateService = cateService;
    }

    public ThirdOtherService getThirdOtherService() {
        return thirdOtherService;
    }

    @Resource(name = "ThirdOtherService")
    public void setThirdOtherService(ThirdOtherService thirdOtherService) {
        this.thirdOtherService = thirdOtherService;
    }

    public ThirdCateService getThirdCateService() {
        return thirdCateService;
    }

    @Resource(name = "ThirdCateService")
    public void setThirdCateService(ThirdCateService thirdCateService) {
        this.thirdCateService = thirdCateService;
    }

    public GoodsTagService getGoodsTagService() {
        return goodsTagService;
    }

    @Resource(name = "GoodsTagServiceImpl")
    public void setGoodsTagService(GoodsTagService goodsTagService) {
        this.goodsTagService = goodsTagService;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public ServiceSupportMapperService getServiceSupportMapperService() {
        return serviceSupportMapperService;
    }

    @Resource(name = "serviceSupportMapperService")
    public void setServiceSupportMapperService(ServiceSupportMapperService serviceSupportMapperService) {
        this.serviceSupportMapperService = serviceSupportMapperService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public GetOnOffMapper getGetOnOffMapper() {
        return getOnOffMapper;
    }

    @Resource(name = "GetOnOffMapper")
    public void setGetOnOffMapper(GetOnOffMapper getOnOffMapper) {
        this.getOnOffMapper = getOnOffMapper;
    }
}
