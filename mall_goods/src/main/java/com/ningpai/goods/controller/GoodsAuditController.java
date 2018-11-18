package com.ningpai.goods.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.service.*;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.util.GoodsSearchBean;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.system.service.ServiceSupportMapperService;
import com.ningpai.thirdaudit.service.AuditService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 商品审核Controller
 * 
 * @author zhouxu
 *
 */
@Controller
public class GoodsAuditController {

    private static final String GOODSAUDIT_HTM = "goodsAudit.htm";

    private static final String LOGGERINFO1 = "】-->用户名：";

    private GoodsAuditService goodsAuditService;

    // 货品信息Service
    private GoodsProductService goodsProductService;

    // 商品开启规格的Service
    private GoodsOpenSpecService goodsOpenSpecService;

    // 库房Service
    private WareHouseService wareHouseService;

    // 商品品牌Service
    private GoodsBrandService goodsBrandService;

    // 商品标签Service
    private GoodsTagService goodsTagService;
    // 商品分类Service
    private GoodsCateService goodsCateService;
    // 审核Service
    private AuditService auditService;
    // 审核开关
    private GetOnOffService getOnOffService;

    @Resource(name = "serviceSupportMapperService")
    private ServiceSupportMapperService serviceSupportMapperService;

    // 商品索引
    @Resource(name = "GoodsElasticSearchService")
    private GoodsElasticSearchService goodsElasticSearchService;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsAuditController.class);

    public AuditService getAuditService() {
        return auditService;
    }

    @Resource(name = "auditService")
    public void setAuditService(AuditService auditService) {
        this.auditService = auditService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "GoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
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

    public GoodsOpenSpecService getGoodsOpenSpecService() {
        return goodsOpenSpecService;
    }

    @Resource(name = "GoodsOpenSpecService")
    public void setGoodsOpenSpecService(GoodsOpenSpecService goodsOpenSpecService) {
        this.goodsOpenSpecService = goodsOpenSpecService;
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public GoodsAuditService getGoodsAuditService() {
        return goodsAuditService;
    }

    @Resource(name = "goodsAuditService")
    public void setGoodsAuditService(GoodsAuditService goodsAuditService) {
        this.goodsAuditService = goodsAuditService;
    }

    /**
     * 审核列表
     * 
     * @param pageBean
     * @param request
     * @return
     */
    @RequestMapping("/goodsAudit")
    public ModelAndView goodsAudit(PageBean pageBean, HttpServletRequest request, GoodsSearchBean searchBean, Long oneCateId, Long twoCateId) {
        // 设置PageBean的url为findAllGoods.htm
        pageBean.setUrl(PathUtil.ALLGOODSCONTROLLER);
        // 获取页面中的当前页码
        String pageNo = request.getParameter("pageNo");
        // 如果当前页码为null就设置当前页码为1
        if (pageNo == null) {
            pageNo = "1";
        }
        // 设置PageBean的开始页码为当前获取的页码
        pageBean.setFirstPageNo(Integer.parseInt(pageNo));
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 把数据集合查询出来放进map集合中
        map.put("pb", goodsAuditService.selectAuditGoods(pageBean, searchBean));
        map.put("isThirdAuditUsed", getOnOffService.getOnOffFlag());
        // 非空验证 商品名称
        if (null != searchBean.getGoodsName()) {
            LOGGER.info("获取商品名称为：" + searchBean.getGoodsName() + "的审核列表");
        }
        // 定义一个商品分类集合并调用查询方法进行赋值
        List<GoodsCate> oneCateList = goodsCateService.querySonCateByParentIdAndName(0L, null);

        ModelAndView mav = new ModelAndView(ValueUtil.GOODSAUDIT).addObject("map", map).addObject(ValueUtil.BRANDLIST, goodsBrandService.queryAllBrand())
                .addObject(ValueUtil.TAGLIST, goodsTagService.queryAllTag()).addObject("searchBean", searchBean).addObject("oneCateList", oneCateList)
                .addObject("oneCateId", oneCateId).addObject("twoCateId", twoCateId);
        // 判断分类Id是否为null并且是否为空
        if (oneCateId != null) {
            // 定义第二个商品分类集合并赋值
            List<GoodsCate> twoCateList = goodsCateService.querySonCateByParentIdAndName(oneCateId, null);
            // 把商品分类集合放进map集合中
            mav.addObject("twoCateList", twoCateList);
            // 判断分类id是否为null并且是否为空
            if (twoCateId != null) {
                // 定义商品分类集合并进行赋值
                List<GoodsCate> threeCateList = goodsCateService.querySonCateByParentIdAndName(twoCateId, null);
                // 把查询结果的商品分类集合放进map中
                mav.addObject("threeCateList", threeCateList);
            }
        }
        return mav;

    }

    /**
     * 审核控制开始、关闭页面
     * 
     * @return
     */
    @RequestMapping("auditaction")
    public ModelAndView auditAction(HttpServletRequest request) {
        // 通过requeset获取存储在页面中的token值
        String token = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        // 返回结果视图
        return new ModelAndView("jsp/goods/goodsAuditButtom").addObject("isThirdAuditUsed", getOnOffService.getOnOffFlag()).addObject("token", token);
    }

    /**
     * 审核开启、关闭控制
     * 
     * @param isThirdAuditUsed
     * @return
     */
    @RequestMapping("changeAuditAction")
    public ModelAndView changeAuditAction(String isThirdAuditUsed) {
        // 执行修改审核开关设置，
        // 返回更新结果并赋值到count变量中
        int count = getOnOffService.updateOnOffFlag(isThirdAuditUsed);
        // 判断count是否大于0
        // 如果大于0 就返回结果
        // 如果小于0就返回null
        if (count > 0) {
            return new ModelAndView(new RedirectView("auditaction.htm"));
        } else {
            return null;
        }
    }

    /**
     * 商品审核通过
     * 
     * @param goodsId
     * @return
     */
    @RequestMapping("auditByGoodsId")
    public ModelAndView auditByGoodsId(HttpServletRequest request, Long goodsId) {
        // 根据商品ID获取商品详细信息
        Goods goods = goodsProductService.selectGoodsByGoodsId(goodsId);
        // 获取当前登录的用户名
        String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
        // 更改审核状态
        int count = goodsAuditService.auditByGoodsId(goodsId);
        // 根据商品id查询所有货品信息
        List<GoodsProduct> goodsProducts = goodsProductService.queryProductsByGoodsId(goodsId);
        // 审核通过商品后，将货品也都改为已审核
        if (goodsProducts != null) {
            for (int i = 0; i < goodsProducts.size(); i++) {
                goodsProductService.auditProductAction(goodsProducts.get(i).getGoodsInfoId());
            }
        }

        // 修改索引
        goodsElasticSearchService.insertOneGoodsIndexToEs(goodsId);
        if (count > 0) {
            // 非空验证 商品名称
            if (null != goods.getGoodsName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "商品审核通过", "商品审核通过-->商品名称【" + goods.getGoodsName() + LOGGERINFO1 + name);
                LOGGER.info("商品" + goods.getGoodsName() + "商品审核通过！");
            }
            return new ModelAndView(new RedirectView(GOODSAUDIT_HTM));
        } else {
            return null;
        }
    }

    /**
     * 商品拒绝审核
     * 
     * @param goodsId
     * @return
     */
    @RequestMapping("refuseAuditByGoodsId")
    public ModelAndView refuseAuditByGoodsId(HttpServletRequest request, Long goodsId, String refuseReason) {
        // 根据商品ID获取商品详细信息
        Goods goods = goodsProductService.selectGoodsByGoodsId(goodsId);
        // 获取当前登录的用户名
        String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
        // 更改审核状态
        int count = goodsAuditService.refuseAuditByGoodsId(goodsId, refuseReason);
        if (count > 0) {
            // 非空验证 商品名称
            if (null != goods.getGoodsName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "商品审核拒绝", "商品审核拒绝-->商品名称【" + goods.getGoodsName() + LOGGERINFO1 + name);
                LOGGER.info("商品" + goods.getGoodsName() + "审核未通过！");
            }
            return new ModelAndView(new RedirectView(GOODSAUDIT_HTM));
        } else {
            return null;
        }
    }

    /**
     * 根据商品ID查询商品下的货品信息
     * 
     * @param goodsId
     * @param pb
     * @param selectBean
     * @return
     */
    @RequestMapping("/queryAuditByGoodsId")
    public ModelAndView queryAllByGoodsId(Long goodsId, PageBean pb, SelectBean selectBean) {
        return new ModelAndView(PathUtil.GOODSAUDITLIST, "pb", goodsProductService.queryAuditByGoodsId(goodsId, pb, selectBean))
                .addObject("specs", this.goodsOpenSpecService.queryOpenListByGoodsId(goodsId)).addObject("goodsId", goodsId).addObject("selectBean", selectBean)
                .addObject("wareHouse", this.wareHouseService.queryAllWareHouse()).addObject("support", serviceSupportMapperService.selectAll());
    }

    /**
     * 货品审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    @RequestMapping("auditProductAction")
    public ModelAndView auditProductAction(HttpServletRequest request, Long goodsInfoId) {
        // 根据商品ID获取详细信息
        GoodsProductVo goodsProductVo = goodsProductService.queryByPrimaryId(goodsInfoId);
        // 获取当前登录的用户名
        String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
        int count = goodsProductService.auditProductAction(goodsInfoId);
        // 修改索引
        goodsElasticSearchService.insertOneGoodsIndexToEs(goodsProductVo.getGoodsId());
        if (count > 0) {
            // 非空验证 商品名称
            if (null != goodsProductVo.getGoodsInfoName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "货品审核通过", "货品审核通过-->货品名称【" + goodsProductVo.getGoodsInfoName() + LOGGERINFO1 + name);
                LOGGER.info("货品" + goodsProductVo.getGoodsInfoName() + "审核通过！");
            }
            return new ModelAndView(new RedirectView(GOODSAUDIT_HTM));
        } else {
            return null;
        }
    }

    /**
     * 货品拒绝审核通过
     * 
     * @param goodsInfoId
     * @return
     */
    @RequestMapping("refuseAuditProductAction")
    public ModelAndView refuseAuditProductAction(HttpServletRequest request, Long goodsInfoId, String refuseReason) {
        // 根据商品ID获取详细信息
        GoodsProductVo goodsProductVo = goodsProductService.queryByPrimaryId(goodsInfoId);
        // 获取当前登录的用户名
        String name = (String) request.getSession().getAttribute(ValueUtil.NAME);
        int count = goodsProductService.refuseAuditProductAction(goodsInfoId, refuseReason);
        if (count > 0) {
            // 非空验证 商品名称
            if (null != goodsProductVo.getGoodsInfoName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, name, "货品审核拒绝", "货品审核拒绝-->货品名称【" + goodsProductVo.getGoodsInfoName() + LOGGERINFO1 + name);
                LOGGER.info("货品" + goodsProductVo.getGoodsInfoName() + "货品审核拒绝！");
            }
            return new ModelAndView(new RedirectView(GOODSAUDIT_HTM));
        } else {
            return null;
        }
    }

    public GetOnOffService getGetOnOffService() {
        return getOnOffService;
    }

    @Resource(name = "GetOnOffService")
    public void setGetOnOffService(GetOnOffService getOnOffService) {
        this.getOnOffService = getOnOffService;
    }

}
