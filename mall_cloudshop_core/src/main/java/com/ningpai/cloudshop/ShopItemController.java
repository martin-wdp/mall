package com.ningpai.cloudshop;

import com.ningpai.cloudshop.service.ICloudshopItemService;
import com.ningpai.cloudshop.util.ItemPageBean;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.cloudshop.Item;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 商品控制器 Created by liangck on 15/6/26.
 */
@Controller
public class ShopItemController {

    // 日志打印
    private Logger logger = Logger.getLogger(ShopItemController.class);

    private static final String JSP_COMMON_INVOKER_ERROR = "jsp/common/invoker_error";
    private static final String RESULT = "result";

    /** 云销商品service **/
    @Resource(name = "CloudshopItemService")
    private ICloudshopItemService itemService;

    /**
     * 分页获取云销已下架分销商品列表
     * 
     * @param pb
     *            分页工具类
     * @param request
     * @param response
     * @return ModelAndView 已下架分销商品列表视图
     */
    @RequestMapping("/getInventoryItems")
    public ModelAndView getInventoryItems(ItemPageBean pb, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("jsp/item/item_list");
        /* 先检查是否已经进行账户授权，未授权则跳转到授权页面 */

        /* 调用开放平台接口，拉取商品数据 */
        try {
            pb.setUrl("getInventoryItems.htm");
            mav.addObject("pageBean", itemService.getInventoryItems(pb, request)).addObject("itemtype", "inventory");
        } catch (ApiException e) {
            logger.info("获取分销下架商品错误>>>>>>>>>>>" ,e);
            // 请求错误，跳转到请求错误页面
            mav.setViewName(JSP_COMMON_INVOKER_ERROR);
            mav.addObject("msg", e.getMessage());
        }
        return mav;
    }

    /**
     * 获取云销在销（上架）商品列表
     * 
     * @param pb
     *            分页工具类
     * @param request
     * @param response
     * @return 上架分销商品列表视图
     */
    @RequestMapping("/getOnsaleItems")
    public ModelAndView getOnsaleItems(ItemPageBean pb, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("jsp/item/item_list");
        try {
            pb.setUrl("getOnsaleItems.htm");
            mav.addObject("pageBean", itemService.getOnsaleItems(pb, request)).addObject("itemtype", "onsale");
        } catch (ApiException e) {
            logger.info("获取分销在售商品错误>>>>>>>>>>",e);
            // 请求错误，跳转到请求错误页面
            mav.setViewName(JSP_COMMON_INVOKER_ERROR);
            mav.addObject("msg", e.getMessage());
        }
        return mav;
    }

    /**
     * 查看商品详情
     * 
     * @param numIid
     *            商品编号ID
     * @param request
     * @return
     */
    @RequestMapping("/getItemDetail")
    public ModelAndView getItemDetail(String numIid, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("jsp/item/item_detail");
        try {
            mav.addObject("item", itemService.getItemDetail(numIid, request));
        } catch (ApiException e) {
            logger.info("获取分销商品详情错误>>>>>>>>>>>" ,e);
            /* 请求错误跳转请求错误页面 */
            mav.setViewName(JSP_COMMON_INVOKER_ERROR);
            mav.addObject("msg", e.getMessage());
        }
        return mav;
    }

    /**
     * 导入商品至商品导入表
     * 
     * @param numIid
     *            商品ID
     * @return 操作结果
     */
    @RequestMapping("/importItem")
    @ResponseBody
    public Map<String, Object> importItem(String numIid, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            Item item = itemService.getItemDetail(numIid, request);
            if (item != null) {
                result.put(RESULT, itemService.importItem(item, request));
                return result;
            }
            result.put(RESULT, false);
            result.put("msg", "未获取到该商品");
        } catch (Exception e) {
            logger.error("",e);
            result.put(RESULT, false);
            result.put("msg", e.getMessage());
        }
        return result;
    }

}
