package com.ningpai.system.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.system.bean.StockWarning;
import com.ningpai.system.service.StockWarningService;
import com.ningpai.system.util.StorkWarningUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 控制器-系统管理-库存预警
 * 
 * @author xiaogu
 *
 */

@Controller
public class StockWarningController {

    private static final String PAGEBEAN = "pageBean";

    private StockWarningService stockwarningService;

    /**
     * 查询库存预警下限
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/stockwarningsel")
    public ModelAndView select(HttpServletRequest request, HttpServletResponse response) {
        // 设置导航
        MenuSession.sessionMenu(request);
        return new ModelAndView("jsp/system/stockwarning").addObject("sw", stockwarningService.select());

    }

    /**
     * 更新库存预警下限
     * 
     * @param sw
     * @return
     */
    @RequestMapping("/updatesw")
    public ModelAndView update(StockWarning sw) {
        this.stockwarningService.update(sw);
        return new ModelAndView(new RedirectView("stockwarningsel.htm"));

    }

    /**
     * 查询库存预警商品信息
     * 
     * @param storkWarningUtil
     * @param pageBean
     * @return
     */
    @RequestMapping("/warninggoods")
    public ModelAndView selwarngoods(StorkWarningUtil storkWarningUtil, PageBean pageBean) {
        pageBean.setUrl("warninggoods.htm");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        storkWarningUtil.setIsThird("0");
        resultMap.put(PAGEBEAN, stockwarningService.selectGoods(storkWarningUtil, pageBean));
        return new ModelAndView("jsp/system/warninggoods").addAllObjects(resultMap).addObject("warn", storkWarningUtil);

    }

    /**
     * 查询库存预警商品的库存信息
     * 
     * @param storkWarningUtil
     * @param pageBean
     * @return
     */
    @RequestMapping("/showwarninggoods")
    public ModelAndView showwarninggoods(StorkWarningUtil storkWarningUtil, PageBean pageBean) {
        pageBean.setUrl("showwarninggoods.htm");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put(PAGEBEAN, stockwarningService.selectWare(storkWarningUtil, pageBean));
        return new ModelAndView("jsp/system/showwarninggoods").addAllObjects(resultMap);
    }

    /**
     * 查询库存预警商品的库存信息
     * 
     * @param storkWarningUtil
     * @param pageBean
     * @return
     */
    @RequestMapping("/showwarninnewggoods")
    @ResponseBody
    public PageBean showwarningnewgoods(StorkWarningUtil storkWarningUtil, PageBean pageBean) {
        return stockwarningService.selectWare(storkWarningUtil, pageBean);
    }

    /**
     * 根据id查询货品库存用于修改
     * 
     * @param wareid
     * @return
     */
    @RequestMapping("/selectgoodbyId")
    @ResponseBody
    public StorkWarningUtil selectbyId(Long wareid) {
        return stockwarningService.selectbyId(wareid);
    }

    /**
     * 跟新库存
     * 
     * @param stockid
     * @return
     */
    @RequestMapping("/updateproductwarn")
    public ModelAndView updateProductwarn(Long[] stockid, Long[] stock) {
        if (stockid != null && stockid.length != 0) {
            for (int i = 0; i < stockid.length; i++) {
                StorkWarningUtil sw = new StorkWarningUtil();
                sw.setStockid(stockid[i]);
                sw.setStock(stock[i]);
                this.stockwarningService.updatestock(sw);
            }
        }

        return new ModelAndView(new RedirectView("warninggoods.htm"));

    }

    /**
     * 跟新库存
     * 
     * @param storkWarningUtil
     * @return
     */
    @RequestMapping("/updatestock")
    @ResponseBody
    public ModelAndView update(StorkWarningUtil storkWarningUtil) {
        this.stockwarningService.updatestock(storkWarningUtil);
        return new ModelAndView(new RedirectView("showwarninggoods.htm")).addObject("id", storkWarningUtil.getId());

    }

    /**
     * 根据货品名字搜索
     *
     * @param pageBean
     * @param selectBean
     * @return
     */
    @RequestMapping("/querybyname")
    public ModelAndView queryBystu(PageBean pageBean, SelectBean selectBean) {
        // 设置页面跳转路径
        pageBean.setUrl("querybyname.htm");
        return new ModelAndView("jsp/system/warninggoods").addObject("selectBean", selectBean).addObject(PAGEBEAN, stockwarningService.selectgoodLists(pageBean, selectBean));

    }

    public StockWarningService getStockwarningService() {
        return stockwarningService;
    }

    @Resource(name = "StockWarningService")
    public void setStockwarningService(StockWarningService stockwarningService) {
        this.stockwarningService = stockwarningService;
    }

}
