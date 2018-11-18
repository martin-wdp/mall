package com.ningpai.system.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.OnLineServiceItem;
import com.ningpai.system.service.IOnLineServiceItemBiz;
import com.ningpai.util.MyLogger;

/**
 * 在线客服项Controller
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:44:30
 * @version V1.0
 */
@Controller("onLineServiceItemController")
public class OnLineServiceItemController extends BaseController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(OnLineServiceItemController.class);

    private static final String MSG = "msg";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 在线客服项业务类 */
    @Resource(name = "onLineServiceItemBizImpl")
    private IOnLineServiceItemBiz onLineServiceItemBizImpl;

    /**
     * 保存在线客服项
     * 
     * @param onLineServiceItem
     *            货币设置对象
     * @param request
     *            请求对象
     * @return 视图对象
     */
    @RequestMapping("/addOnLineServiceItem")
    public ModelAndView addOnLineServiceItem(@Valid final OnLineServiceItem onLineServiceItem, BindingResult bindingResult, final HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView("readOnLineService.htm?deleteStatus=0"));
        }
        try {
            // 获取客服总数，并设置排序
            onLineServiceItem.setOnlineSort(onLineServiceItemBizImpl.selectCountByOnLineService(onLineServiceItem.getOnLineServiceId()) + 1);
            onLineServiceItem.setInsertDate(new Date());
            Long loginUserId = (Long) request.getSession().getAttribute("loginUserId");
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            onLineServiceItem.setInsertId(loginUserId.intValue());
            // 添加在线客服项
            boolean flag = onLineServiceItemBizImpl.saveOnLineServiceItem(onLineServiceItem);
            if (flag) {
                mav.addObject(MSG, "保存在线客服项成功！");
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加在线客服项", request.getSession().getAttribute(OPERAPATH) + ",添加用户名:" + customerName);
            } else {
                mav.addObject(MSG, "保存在线客服项失败！");
            }
        } catch (Exception e) {
            LOGGER.error("添加在线客服项错误：=>",e);
            mav.addObject(MSG, "保存在线客服项失败！");
        }
        mav.setView(new RedirectView("readOnLineService.htm?deleteStatus=0"));
        // 返回在线客服项列表
        return mav;
    }

    /**
     * 排序上移
     */
    @ResponseBody
    @RequestMapping("/upItem")
    public boolean upItem(int id1, int id2) {
        try {
            return onLineServiceItemBizImpl.upItem(id1, id2);
        } catch (Exception e) {
            LOGGER.error("客服项上移异常！", e);
            return false;
        }
    }

    /**
     * 排序下移
     */
    @ResponseBody
    @RequestMapping("/downItem")
    public boolean downItem(int id1, int id2) {
        try {
            return onLineServiceItemBizImpl.downItem(id1, id2);
        } catch (Exception e) {
            LOGGER.error("客服项下移异常！", e);
            return false;
        }
    }

    /**
     * 删除在线客服项
     *
     * @param id
     *            在线客服项记录ID字符串
     * @return 视图对象
     */
    @ResponseBody
    @RequestMapping("/deleteOnLineServiceItem")
    public boolean deleteOnLineServiceItem(int id, HttpServletRequest request) {
        // 删除在线客服项
        try {
            int n = onLineServiceItemBizImpl.delOnLineServiceItem(id);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "删除在线客服项", request.getSession().getAttribute(OPERAPATH) + ",删除用户名:" + customerName);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("删除在线客服项错误：=>",e);
            return false;
        }
    }

    /**
     * 修改在线客服项
     *
     * @param item
     *            在线客服项记录ID字符串
     * @return 标识是否删除成功
     */
    @ResponseBody
    @RequestMapping("/updateOnLineServiceItem")
    public boolean updateOnLineServiceItem(OnLineServiceItem item, HttpServletRequest request) {
        // 修改在线客服项
        try {
            int n = onLineServiceItemBizImpl.updateOnLineServiceItem(item);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改在线客服项", request.getSession().getAttribute(OPERAPATH) + ",修改用户名:" + customerName);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("修改在线客服项错误：=>",e);
            return false;
        }
    }
}
