package com.ningpai.m.store.controller;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.goods.util.ValueUtil;
import com.ningpai.m.store.bean.FinalStaticClass;
import com.ningpai.m.store.service.StoreListService;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 移动端 店铺街 Created by zhanghailong on 2015/6/9.
 */
@Controller
public class StoreListStreetController {

    private static final String STATUS = "status";

    // 获取当前的seo信息
    @Resource(name = "SeoService")
    private SeoService seoService;

    // 商铺列表
    @Resource(name = "storeListService")
    private StoreListService storeListService;

    // 获取移动版站点设置
    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    // 店铺街
    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;

    /**
     * 分页查询店铺信息
     * 
     * @param pb
     *            分页对象
     * @param request
     * @param cateId
     *            一级分类ID
     * @return
     */
    @RequestMapping("/storeliststreet")
    public ModelAndView storeliststreet(PageBean pb, HttpServletRequest request, Long cateId) {
        // 每页显示的数据条数
        pb.setPageSize(20);
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 装载返回到页面的数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 获取移动版站点设置
        MobSiteBasic msb = mobSiteBasicService.selectCurrMobSiteBasic(request);
        // 获取商品的一级分类
        map.put("cateGory", storeListService.selectgoodscatebyone());
        // 装载移动端获取的轮播大广告图片 1：只查询启用的大广告
        map.put("channelAdvers", this.mobCateBarService.selectStoreListImage("1"));
        // 获取的店铺信息
        map.put("pb", storeListService.selectStoreList(pb, cateId, customerId));
        // 装载站点信息
        map.put("mobSiteBasic", msb);
        map.put("cateId", cateId);
        // 一级分类ID
        map.put("cateId", cateId);
        ModelAndView mav = new ModelAndView(FinalStaticClass.STORELISTSTREET);
        mav.addObject(ValueUtil.MAP, map);
        return seoService.getCurrSeo(mav);
    }

    /**
     * 关注店铺
     */
    @RequestMapping("/addcollectionsellerStore")
    @ResponseBody
    public Map<String, Object> addcollectionsellerStore(HttpServletRequest request, Long storeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 判断是否登录成功
        if (customerId == null) {
            map.put(STATUS, 2);
            return map;
        }
        if (null != storeId && null != customerId && storeListService.selectController(storeId, customerId) > 0) {
            // 查看是否关注此店铺
                // 查询到有关注信息 就清除关注记录
                storeListService.deleteController(storeId, customerId);
                map.put(STATUS, 0);
                return map;
        }
        // 登录成功就保存 关注店铺信息
        map.put(STATUS, storeListService.addCollectionSeller(customerId, storeId));
        return map;
    }

    /**
     * 跳转到店铺街新上架的商品列表
     *
     * storeId:店铺ID
     */
    @RequestMapping("/storenewproduct")
    public ModelAndView storenewproduct(Long storeId, PageBean pb) {
        pb.setPageSize(10);
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询最近上架的商品集合
            map.put("pb", storeListService.setStoreNewProcudtList(pb, storeId));
            ModelAndView mav = new ModelAndView(FinalStaticClass.STORENEWPRODUCT);
            mav.addObject(ValueUtil.MAP, map);
            return seoService.getCurrSeo(mav);
        } finally {
            map = null;
        }
    }
}
