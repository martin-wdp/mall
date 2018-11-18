package com.ningpai.site.thirdseller.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.thirdseller.bean.ThirdStoreInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.thirdseller.bean.CollectionSeller;
import com.ningpai.site.thirdseller.service.CollectionSellerService;
import com.ningpai.util.PageBean;

/**
 * 店铺收藏
 * 
 * @author ggn
 *
 */
@Controller
public class CollectionSellerController {

    private static final String CUSTOMERID = "customerId";

    @Resource(name = "CollectionSellerService")
    private CollectionSellerService collectionSellerService;

    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    // spring 注解 会员service
    private CustomerServiceInterface customerServiceInterface;

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    /**
     * 收藏店铺
     * 
     * @param request
     * @param collectionSeller
     * @return int 3未登录 2已经存在 1成功
     */
    @RequestMapping("addcollectionseller")
    @ResponseBody
    public int addCollectionSeller(HttpServletRequest request, CollectionSeller collectionSeller) {
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        // 根据主键ID获取店铺信息
        ThirdStoreInfo thirdStoreInfo = collectionSellerService.selectStoreByCustomerId(collectionSeller.getCollectionThirdId());
        // 判断用户是否登录
        if (customerId != null) {
            collectionSeller.setCollectionCustomerId(customerId);
            // 收藏店铺
            int result = collectionSellerService.addCollectionSeller(collectionSeller);
            // 非空验证 商铺名称
            if (null != thirdStoreInfo.getStoreName()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "收藏店铺成功",
                        "收藏店铺成功-->店铺名称【" + thirdStoreInfo.getStoreName() + "】-->用户名：" + customerAllInfo.getCustomerUsername());
            }
            return result;
        } else {
            return 3;
        }

    }

    /**
     * 我的收藏店铺
     * 
     * @param request
     * @param PageBean
     * @return PageBean
     */
    @RequestMapping("sellermyfollw")
    public ModelAndView sellerMyFollw(HttpServletRequest request, PageBean pageBean) {
        ModelAndView mav = new ModelAndView("customer/newsellerfollow");
        pageBean.setUrl("customer/sellermyfollw");
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        mav.addObject("pb", collectionSellerService.sellerMyFollw(customerId, pageBean));
        return topAndBottomService.getTopAndBottom(mav);

    }

    /**
     * 移除收藏的店铺
     * 
     * @param request
     * @param collectionSellerId
     * @return ModelAndView
     */
    @RequestMapping("customer/delmyfollw")
    public ModelAndView delMyFollw(HttpServletRequest request, Long collectionSellerId) {
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CUSTOMERID);
        // 当前登录成功的会员信息
        // CustomerAllInfo customerAllInfo =
        // customerServiceInterface.selectByPrimaryKey(customerId);
        // 根据主键ID获取店铺信息
        // ThirdStoreInfo thirdStoreInfo =
        // collectionSellerService.selectStoreByCustomerId(collectionSellerId);
        // 非空验证 商铺名称
        // if(null != thirdStoreInfo.getStoreName()){
        // //操作日志
        // OperaLogUtil.addOperaLog(request,
        // customerAllInfo.getCustomerUsername(), "移除收藏的店铺成功",
        // "移除收藏的店铺成功-->店铺名称【" + thirdStoreInfo.getStoreName() + "】-->用户名：" +
        // customerAllInfo.getCustomerUsername());
        // }
        collectionSellerService.delMyFollw(customerId, collectionSellerId);
        return new ModelAndView(new RedirectView("sellermyfollw.html"));

    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

}
