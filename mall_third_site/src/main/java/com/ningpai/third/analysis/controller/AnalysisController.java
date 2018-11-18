package com.ningpai.third.analysis.controller;

import com.ningpai.third.analysis.bean.EchartsBean;
import com.ningpai.third.analysis.bean.OCustomerFollow;
import com.ningpai.third.analysis.bean.OOrder;
import com.ningpai.third.analysis.service.AnalysisService;
import com.ningpai.third.util.MenuOperationUtil;
import com.ningpai.util.UtilDate;
import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 数据分析
 * </p>
 *
 * @author zhanghl
 * @versuin 2.0
 * @since 2015.07.30
 */
@Controller
public class AnalysisController {

    private static final String THIRDID = "thirdId";
    private static final String STARTTIME = "startTime";
    private static final String SELECTINDEX = "selectindex";
    private static final String ISTHIRD = "isthird";
    private static final String ENDTIME = "endTime";

    // 数据分析接口
    @Resource(name = "AnalysisService")
    private AnalysisService analysisService;

    /**
     * 商品收藏排行
     *
     * @param request
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @param startTime
     *            起始时间
     * @param endTime
     *            结束时间
     * @param isthird
     *            第三方商家ID
     * @param catId
     *            类型ID
     * @return ModelAndView
     */
    @RequestMapping("followanalysis")
    public ModelAndView followAnalysis(HttpServletRequest request, String n, String l, String startTime, String endTime, String isthird, Long catId, Long selectIndex) {
        String startTimeNew = startTime;
        String endTimeNew = endTime;
        Long selectIndexNew = selectIndex;
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商家ID
        Long thirdId = null;
        // 获取第三方ID
        if (isthird == null || "".equals(isthird)) {
            thirdId = (Long) request.getSession().getAttribute(THIRDID);
        }
        /**************************** 日期格式化 ********************************************************/
        List<String> timelist = new ArrayList<String>();
        // 格式化日期 如果时间不存在 初始化当前日期的钱7天
        if (startTimeNew == null || endTimeNew == null || "".equals(startTimeNew) || "".equals(endTimeNew)) {
            // 起始日期
            endTimeNew = UtilDate.todayFormat(new Date());
            // 结束日期
            startTimeNew = UtilDate.addDay(endTimeNew, -7);

        }
        // 起始时间
        String searchStartTime = startTimeNew;
        // 结束时间
        String searchEndTime = endTimeNew;
        // 查询第三方 两个时间段内的排行
        List<OCustomerFollow> list = analysisService.selectThirdFollowGoods(thirdId, startTimeNew, endTimeNew, catId);

        // 格式化时间
        while (UtilDate.compare_date(startTimeNew, endTimeNew)) {
            timelist.add(startTimeNew);
            startTimeNew = UtilDate.addDay(startTimeNew, 1);
        }
        // 把日期转换为json对象
        JSONArray times = JSONArray.fromObject(timelist);
        List<EchartsBean> el = new ArrayList<EchartsBean>();
        // 遍历订单集合
        if (list != null) {
            for (OCustomerFollow g : list) {
                List<Long> longList = new ArrayList<Long>();
                EchartsBean e = new EchartsBean();
                e.setName(g.getGoodsInfoName());
                e.setStack("数量");
                e.setType("line");
                for (int j = 0; j < timelist.size(); j++) {
                    longList.add(g.getTimeMap().get(timelist.get(j)) == null ? 0L : g.getTimeMap().get(timelist.get(j)));
                }
                e.setData(longList);
                el.add(e);
            }
        }
        JSONArray json = JSONArray.fromObject(el);
        // 开始默认选中 "请选择"
        if (selectIndexNew== null) {
            selectIndexNew = 0L;
        }
        return new ModelAndView("analysis/followanalysis").addObject("el", json.toString()).addObject("times", times.toString()).addObject("goodsList", list)
                .addObject(STARTTIME, searchStartTime).addObject(ENDTIME, searchEndTime).addObject(ISTHIRD, isthird).addObject(SELECTINDEX, selectIndexNew.toString());
    }

    /**
     * 订单数据分析
     *
     * @param request
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @param startTime
     *            筛选商家订单的开始时间
     * @param endTime
     *            筛选商家订单的结束时间
     * @param isthird
     *            店铺Id
     * @param selectIndex
     *            选择分类的 下拉列表值
     * @return
     */
    @RequestMapping("queryOrderCountByDay")
    public ModelAndView queryOrderCountByDay(HttpServletRequest request, String n, String l, String startTime, String endTime, String isthird, Long selectIndex) {
        String startTimeNew =  startTime;
        String endTimeNew = endTime;
        Long selectIndexNew = selectIndex;
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商家ID
        Long thirdId = null;
        // 获取第三方ID
        if (isthird == null || "".equals(isthird)) {
            thirdId = (Long) request.getSession().getAttribute(THIRDID);
        }

        /**************************** 日期格式化 ********************************************************/
        // 格式化日期 如果时间不存在 初始化当前日期的前7天
        if (startTimeNew == null || endTimeNew == null || "".equals(startTimeNew) || "".equals(endTimeNew)) {
            endTimeNew = UtilDate.todayFormat(new Date());
            startTimeNew = UtilDate.addDay(endTime, -7);
        }
        // 起始时间
        String sttime = startTimeNew;
        // 结束时间
        String entime = endTimeNew;
        // 根据商家ID查询订单量
        List<OOrder> ooderList = analysisService.queryCountByDay(thirdId);
        List<String> ctime = new ArrayList<String>();
        // 格式化时间
        while (UtilDate.compare_date(startTimeNew, endTimeNew)) {
            ctime.add(startTimeNew);
            startTimeNew = UtilDate.addDay(startTimeNew, 1);
        }
        // 把日期格式 转换为json格式
        JSONArray times = JSONArray.fromObject(ctime);
        // 记录订单的数量
        List<Long> ccount = new ArrayList<Long>();
        for (int i = 0; i < ctime.size(); i++) {
            boolean flg = true;
            // 遍历订单的数量
            for (int j = 0; j < ooderList.size(); j++) {
                OOrder oOder = ooderList.get(j);
                if (ctime.get(i).equals(oOder.getCtime())) {
                    ccount.add(oOder.getCountSum());
                    flg = false;
                    break;
                }
            }
            // 是否成功
            if (flg) {
                ccount.add(0L);
            }
        }
        JSONArray counts = JSONArray.fromObject(ccount);
        // 开始默认选中 "请选择"
        if (selectIndexNew== null) {
            selectIndexNew = 0L;
        }
        return new ModelAndView("analysis/ordercount").addObject("orderList", ooderList).addObject("times", times.toString()).addObject("ccounts", counts.toString())
                .addObject(STARTTIME, sttime).addObject(ENDTIME, entime).addObject(ISTHIRD, isthird).addObject(SELECTINDEX, selectIndexNew);

    }

    /**
     * 转化率分析
     *
     * @param request
     * @param n
     *            导航栏索引
     * @param l
     *            导航栏索引
     * @param startTime
     *            起始时间
     * @param endTime
     *            结束时间
     * @param isthird
     *            商家ID
     * @return
     */
    @RequestMapping("queryTakeRates")
    public ModelAndView queryTakeRates(HttpServletRequest request, String n, String l, String startTime, String endTime, String isthird, Long selectIndex) {
        Long selectIndexNew = selectIndex;
        String startTimeNew = startTime;
        String endTimeNew = endTime;
        // 填充导航/左侧菜单索引 用于页面控制css选中
        MenuOperationUtil.fillSessionMenuIndex(request, n, l);
        // 商家ID
        Long thirdId = null;
        // 获取第三方ID
        if (isthird == null || "".equals(isthird)) {
            thirdId = (Long) request.getSession().getAttribute(THIRDID);
        }

        // 格式化日期 如果时间不存在 初始化当前日期的前7天
        if (startTimeNew == null || endTimeNew == null || "".equals(startTimeNew) || "".equals(endTimeNew)) {
            // 起始日期
            endTimeNew = UtilDate.todayFormat(new Date());
            // 终止日期
            startTimeNew = UtilDate.addDay(endTimeNew, -7);
        }
        // 起始日期
        String sttime = startTimeNew;
        // 结束日期
        String entime = endTimeNew;
        // //根据商家ID查询一段时间内成功的订单
        OOrder succOrder = analysisService.querySuccCountByTime(startTimeNew, endTimeNew, thirdId);
        // 根据商家ID查询一段时间内不成功的订单
        OOrder noSuccOrder = analysisService.queryNoSuccCountByDay(startTimeNew, endTimeNew, thirdId);
        // 开始默认选中 "请选择"
        if (selectIndexNew == null) {
            selectIndexNew = 0L;
        }
        return new ModelAndView("analysis/querytakerates").addObject("succOrder", succOrder).addObject("noSuccOrder", noSuccOrder).addObject(STARTTIME, sttime)
                .addObject(ENDTIME, entime).addObject(ISTHIRD, isthird).addObject(SELECTINDEX, selectIndexNew);
    }

}
