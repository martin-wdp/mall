/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.mobile.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.mobile.service.MobCateBarService;
import com.ningpai.mobile.vo.MobCateBarVo;
import com.ningpai.util.PageBean;

/**
 * 控制器-选择内连接
 * 
 * @ClassName: MobChooseInnerJoinController
 * @Description: 选择移动版首页的内连接地址，分别是移动版分类和移动版商品商品
 * @author Wanghy
 * @date 2014年11月10日 上午11:13:45
 * 
 */
@Controller
public class MobChooseInnerJoinController {

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    /**
     * 查询移动版商品
     * 
     * @Title: ajaxQueryMobProductForInnerJoin
     * @Description: 根据分类ID、货品名称来查询货品列表，以支持内连接
     * @param cid
     *            分类ID
     * @param pname
     *            货品名称
     * @return
     */
    @RequestMapping("/ajaxQueryMobProductForInnerJoin")
    @ResponseBody
    public Map<String, Object> ajaxQueryMobProductForInnerJoin(PageBean pb, Long cid, String pname, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        String[] cateIds = null;
        if (null != cid) {
            cateIds = new String[] { cid.toString() };
        }
        map.put("cateId", cid);
        map.put("pname", pname);
        Map<String, String> extraParam = new HashMap<>();
        extraParam.put("showMobile", "1");
        pb.setObjectBean(extraParam);
        map.put("pb", goodsProductService.queryProductForCouponLife(pb, cateIds, null, (Long) request.getSession().getAttribute("thirdId"), null, pname, null, null, null,null));
        return map;
    }

    /**
     * 查询移动版分类
     * 
     * @Title: ajaxQueryMobCateBarForSite
     * @Description: Ajax查询已启用、未删除的移动版分类导航
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxQueryMobCateBarForSite")
    public List<MobCateBarVo> ajaxQueryMobCateBarForSite() {
        return this.mobCateBarService.selectMobCateBarForSite();
    }

}
