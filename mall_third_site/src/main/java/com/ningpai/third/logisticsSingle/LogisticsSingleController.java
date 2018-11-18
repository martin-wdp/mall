package com.ningpai.third.logisticsSingle;

import com.ningpai.system.bean.LogisticsSingle;
import com.ningpai.system.service.LogisticsSingleService;
import com.ningpai.third.seller.bean.Express;
import com.ningpai.third.seller.service.ExpressInfoService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 第三方物流单模板控制器
 * </p>
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
@Controller("thirdLogisticsSingleController")
public class LogisticsSingleController {
    // 物流单模板
    @Resource(name = "LogisticsSingleService")
    private LogisticsSingleService logisticsSingleService;
    // 物流公司
    @Resource(name = "expressInfoService")
    private ExpressInfoService expressInfoService;

    private static final String THIRDID = "thirdId";

    /**
     * 查询所有的物流单列表
     * 
     * @param pb
     *            分页类
     * @param request
     * @param n
     *            头部导航索引
     * @param l
     *            左侧导航索引
     * @return
     */
    @RequestMapping("/logisticssingleThirdList")
    public ModelAndView getLogisticsSingleList(PageBean pb, HttpServletRequest request, String n, String l) {
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 用来装载返回到页面的数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 物流单列表分页对象
        map.put("pb", logisticsSingleService.getLogisticsSingleList(pb, thirdId));
        // 设置需要跳转的路径
        pb.setUrl("logisticssingleThirdList.htm?n=" + n + "&l=" + l);
        // 设置要跳转的页面路径 以及要返回到页面的数据
        return new ModelAndView("logisticsSingle/third_logisticsSingle_List").addObject("map", map);
    }

    /**
     * 跳转添加物流单模板
     * 
     * @return ModelAndView
     */
    @RequestMapping("/toAddThirdLogisticsSingle")
    public ModelAndView toAddThirdLogisticsSingle(HttpServletRequest request) {
        // 创建一个物流公司对象
        Express expressmap = new Express();
        // 商家编号
        expressmap.setStoreId((Long) request.getSession().getAttribute(THIRDID));
        // 设置要跳转的页面
        return new ModelAndView("logisticsSingle/addThirdLogisticsSingle")
        // 当前店铺的物流信息
                .addObject("companys", expressInfoService.selectByStoreId(expressmap));
    }

    /**
     * 跳转到修改页面
     * 
     * @param logisticsSingleId
     * @return
     */
    @RequestMapping("/toUpdateThirdLogisticsSingle")
    public ModelAndView toUpdateLogisticsSingle(Long logisticsSingleId, HttpServletRequest request) {
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 根据Id 查询物流单模板信息
        LogisticsSingle logisticsSingle = logisticsSingleService.selectLogisticsSingleById(logisticsSingleId, thirdId);
        // 设置要跳转的页面
        return new ModelAndView("logisticsSingle/updateThirdLogisticsSingle")
        // 根据Id 查询物流单模板信息
                .addObject("logisticsSingle", logisticsSingle);
    }

    /**
     * 修改物流单模板信息
     * 
     * @param logisticsSingle
     * @return ModelAndView
     */
    @RequestMapping("/updatelogisticssingles")
    public ModelAndView updateLogisticsSingle(LogisticsSingle logisticsSingle) {
        // 修改成功
        if (logisticsSingleService.updateLogisticsSingle(logisticsSingle) > 0 && logisticsSingle.getThirdId() > 0) {
            // 返回第三放的物流单模板列表
                // 重定向到模板列表
                return new ModelAndView(new RedirectView("logisticssingleThirdList.htm?n=2&l=98"));
        }
        return null;
    }

    /**
     * 添加物流单模板信息
     * 
     * @param logisticsSingle
     * @return ModelAndView
     */
    @RequestMapping("/addThirdlogisticssingle")
    public ModelAndView addLogisticsSingle(LogisticsSingle logisticsSingle, HttpServletRequest request) {
        // 设置创建时间
        logisticsSingle.setCreateTime(new Date());
        // 设置当前物流单的状态
        logisticsSingle.setDelFlag("0");
        // 设置物流单所属于的商家
        logisticsSingle.setThirdId((Long) request.getSession().getAttribute(THIRDID));
        // 添加物流单模板信息
        if (logisticsSingleService.addLogisticsSingle(logisticsSingle) > 0) {
            // 保存成功 跳转到物流模板列表页
            return new ModelAndView("redirect:logisticssingleThirdList.htm?n=2&l=98");
        }
        // 添加不成功 跳转到添加物流单页面
        return new ModelAndView(new RedirectView("toAddThirdLogisticsSingle.htm"));
    }

    /**
     * 删除物流模板
     * 
     * @return logisticsSingleId 要删除的运费模板ID
     */
    @RequestMapping("/dellogisticssingles")
    @ResponseBody
    public int delLogisticsSingle(Long logisticsSingleId, HttpServletRequest request, Long thirdId) {
        Long thirdIdNew = thirdId;
        thirdIdNew = (Long) request.getSession().getAttribute(THIRDID);
        // 删除物流模板
        return logisticsSingleService.delLogisticsSingle(logisticsSingleId, thirdIdNew);
    }
}
