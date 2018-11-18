package com.ningpai.third.seller.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.third.seller.bean.ApplyBrand;
import com.ningpai.third.seller.service.ApplyBrandService;
import com.ningpai.util.PageBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自定义品牌控制器
 * </p>
 * 
 * @author zhanghl
 * @since 2014年6月11日 下午7:17:38
 * @version 2.0
 */
@Controller
public class ApplyBrandController {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(ApplyBrandController.class);

    private static final String THIRDID = "thirdId";

    /** 自定义品牌审核列表 */
    public static final String APPLYBRANDLIST = "/grandbrand/thirdapplyBrand";

    /** 申请品牌 */
    @Resource(name = "ApplyBrandService")
    private ApplyBrandService applyBrandService;

    /**
     * 删除
     * 
     * @param applyBrandId
     *            要删除的自定义品牌ID
     * @return
     */
    @RequestMapping("third/deleteapplybrand")
    @ResponseBody
    public int delApplyBrand(Long applyBrandId, HttpServletRequest request) {
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        return applyBrandService.delApplyBrand(applyBrandId, thirdId);
    }

    /**
     * 批量删除
     * 
     * @param applyBrandId
     *            批量删除的自定义品牌id数组
     * @return
     */
    @RequestMapping("third/deleteapplybrands")
    @ResponseBody
    public int delApplyBrands(HttpServletRequest request, Long[] applyBrandId) {
        // 商家编号
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 商家的自定义品牌集合
        List<ApplyBrand> brands = applyBrandService.selectApplyBrand(thirdId);
        // 创建一个集合用来保存需要删除的自定义品牌ID
        List<Long> brandIds = new ArrayList<>();
        // 遍历当前店铺所有的自定义品牌
        for (int i = 0; i < brands.size(); i++) {
            // 遍历所有的需要删除的自定义品牌ID
            for (int j = 0; j < applyBrandId.length; j++) {

                if (applyBrandId[j].equals(brands.get(i).getApplyBrandId())) {
                    // 把要删除的自定义品牌ID 装入要删除的集合了
                    brandIds.add(applyBrandId[j]);
                    break;
                }
            }
        }// 批量删除自定义品牌
        if (applyBrandService.delApplyBrands(brandIds.toArray(new Long[] {}), thirdId) > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 添加
     * 
     * @param request
     * @param applyBrand
     *            自定义品牌
     * @return
     */
    @RequestMapping("third/addapplybrand")
    @ResponseBody
    public ApplyBrand addApplyBrand(MultipartHttpServletRequest request, ApplyBrand applyBrand) {
        try {
            // 设置自定义品牌的名称
            applyBrand.setApplyBrandName(applyBrand.getApplyBrandName());
        } catch (Exception e) {
            LOGGER.error("商家申请品牌失败！", e);
        }
        // 保存一个自定义品牌
        return applyBrandService.addApplyBrand(request, applyBrand);
    }

    /**
     * 查询自定义列表
     * 
     * @param pb
     *            分页对象
     * @param applyBrand
     *            自定义品牌对象
     * @return
     */
    @RequestMapping("third/queryapplybrand")
    public ModelAndView queryApplyBrand(PageBean pb, ApplyBrand applyBrand, HttpServletRequest request) throws UnsupportedEncodingException {
        // post 提交中文转码
        if (null != applyBrand.getApplyBrandName()) {
            String str = new String(applyBrand.getApplyBrandName().getBytes("ISO8859-1"), ConstantUtil.UTF);
            applyBrand.setApplyBrandName(str);
        }
        // 商家ID
        Long thirdId = (Long) request.getSession().getAttribute(THIRDID);
        // 装载分页的对象
        pb.setObjectBean(applyBrand);
        // 设置要跳转的路径
        pb.setUrl("queryapplybrand.html");
        ModelAndView mav = new ModelAndView();
        // 设置要跳转的页面
        mav.setViewName(APPLYBRANDLIST);
        // 用来装载要返回页面的数据
        Map<String, Object> map = new HashMap<String, Object>();
        // 自定义品牌对象
        map.put("applyBrand", applyBrand);
        // 当前店铺的所有自定义品牌分页数据
        map.put("pb", applyBrandService.queryApplyBrand(pb, thirdId));
        mav.addObject("map", map);
        return mav;
    }
}
