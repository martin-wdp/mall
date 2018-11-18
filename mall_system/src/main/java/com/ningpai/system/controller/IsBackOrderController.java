package com.ningpai.system.controller;

import com.ningpai.system.bean.SystemsSet;
import com.ningpai.system.service.IsBackOrderService;
import com.ningpai.util.MenuSession;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/***
 * 控制器-系统管理-订单设置
 * 
 * @author zhanghailong
 * 
 */
@Controller
public class IsBackOrderController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(IsBackOrderController.class);

    private static final String RESULT = "result";

    /** 是否允许退单service **/
    private IsBackOrderService isbackOrderService;

    /***
     * 是否允许退单
     * 
     * @return
     */
    @RequestMapping("/setBackOrder")
    public ModelAndView toAdvancedSet(HttpServletRequest request, @RequestParam(value = "flag", required = false, defaultValue = "0") final Integer flag) {
        // 设置导航
        MenuSession.sessionMenu(request);
        SystemsSet systemsSet = null;
        try {
            // 获取是否允许退单
            systemsSet = isbackOrderService.getIsBackOrder();
        } catch (Exception e) {
            LOGGER.error("",e);
            return null;
        }
        // 去订单设置页面
        return new ModelAndView("jsp/system/isbackorder_set").addObject(systemsSet).addObject("flag", flag);
    }

    /***
     * 修改允许退单的状态
     * 
     * @return
     * @throws java.io.IOException
     */
    @ResponseBody
    @RequestMapping("/updatesetBackOrder")
    public Map<String, Object> updatesetBackOrder(Long setId, Long isBackOrder, Long receiptTime) throws IOException {
        SystemsSet systemsSet = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 获取是否允许退单
            systemsSet = isbackOrderService.getIsBackOrderById(setId);
            // 是否允许退单设置
            systemsSet.setIsBackOrder(isBackOrder);
            // 订单自动退货设置
            systemsSet.setReceiptTime(receiptTime);
            // 修改是否允许退单
            isbackOrderService.updatesetBackOrder(systemsSet);

            map.put(RESULT, 1);
        } catch (Exception e) {
            // 错误日志输出
            map.put(RESULT, 0);
            LOGGER.error("是否允许退货状态修改失败:=>",e);
        }
        return map;
    }

    /**
     * 设置系统自动更新未付款订单作废时间
     * 
     * @param bsetOrderTime
     * @return
     */
    @RequestMapping("/updatesetOrderTime")
    @ResponseBody
    public Map<String, Object> updatesetOrderTime(Long bsetOrderTime) {
        // 设置系统自动更新未付款订单作废时间
        int count = isbackOrderService.updatesetOrderTime(bsetOrderTime);
        Map<String, Object> map = new HashMap<String, Object>();
        if (count > 0) {
            map.put(RESULT, 1);
        } else {
            map.put(RESULT, 0);
        }
        return map;
    }

    /**
     * 新后台跟新退货说明
     * 
     * @return
     */
    @RequestMapping("/updatebackremark")
    @ResponseBody
    public int updateBackRemark(SystemsSet systemsSet) {
        // 新后台跟新退货说明
        int count = isbackOrderService.updateBackRemark(systemsSet);
        if (count > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 更新退货说明
     * 
     * @param backInfoRemark
     * @return
     */
    @RequestMapping("/updateBackInfo")
    public ModelAndView updateBackInfo(String backInfoRemark) {
        if (backInfoRemark != null) {
            isbackOrderService.updateBackInfo(backInfoRemark);
        }
        return new ModelAndView(new RedirectView("setBackOrder.htm"));
    }

    public IsBackOrderService getIsbackOrderService() {
        return isbackOrderService;
    }

    @Resource(name = "IsBackOrderService")
    public void setIsbackOrderService(IsBackOrderService isbackOrderService) {
        this.isbackOrderService = isbackOrderService;
    }

}
