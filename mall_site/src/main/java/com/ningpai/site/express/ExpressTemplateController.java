package com.ningpai.site.express;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.shoppingcart.bean.ShoppingCart;
import com.ningpai.site.shoppingcart.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 运费模板
 * @Description 运费模板
 * @author Songhl
 * @since 2015年8月27日 22:16:26
 */
@Controller
public class ExpressTemplateController {
    /**
     * 运费模板
     */
    @Resource(name = "FreightTemplateService")
    private FreightTemplateService freightTemplateService;
    /**
     * 会员服务接口
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    /**
     * 购物车service
     */
    @Resource(name = "ShoppingCartService")
    private ShoppingCartService shoppingCartService;
    /**
     * 货品信息Service
     */
    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;
    /**
     * 商品组合Service
     */
    @Resource(name = "GoodsGroupService")
    private GoodsGroupService goodsGroupService;

    /**
     * 获取运费模板价格
     * @Description 获取运费模板价格
     * @param addressId 地址id
     * @param request 请求
     * @param distributionId
     * @return
     * @author Songhl
     * @since 2015年8月27日 22:20:47
     */
    @RequestMapping("/getexpressprice")
    @ResponseBody
    public String getExpressPrice(Long addressId, HttpServletRequest request, Long distributionId) {
        String thirdId = request.getParameter("thirdIds");
        String cartId = request.getParameter("cartIds");
        String[] thirdIds = thirdId.split(",");
        String[] cartIds = cartId.split(",");

        // 获取 收获地址城市
        CustomerAddress ca = customerServiceInterface.queryCustAddress(addressId);
        if (ca == null) {
            return "0";
        }
        BigDecimal price = new BigDecimal(0);
        // 判断商家订单
        for (int i = 0; i < thirdIds.length; i++) {
            if (thirdIds[i] != null && !"".equals(thirdIds[i])) {
                // 获取购物车商品个数和重量
                String s = cartIds[i];
                // 获取该订单的购物车Id
                List<Long> shopIds = new ArrayList<Long>();

                String[] ids = s.split("-");
                if (ids != null && ids.length != 0) {
                    for (String id : ids) {

                        shopIds.add(Integer.valueOf(id).longValue());

                    }
                }
                // 添加购物车id
                Long[] box = new Long[shopIds.size()];
                for (int j = 0; j < shopIds.size(); j++) {
                    box[j] = shopIds.get(j);
                }
                //查询购物车购买的商品信息
                List<ShoppingCart> shlist = shoppingCartService.searchByProduct(request, box);
                BigDecimal weight = new BigDecimal(0);
                Integer nums = 0;
                if (shlist != null && !shlist.isEmpty()) {
                    for (ShoppingCart sc : shlist) {
                        // 判断是否是套装
                        if (sc.getFitId() == null) {
                            // 如果是普通商品，执行普通商品的方法
                            GoodsProductVo goodsProduct = goodsProductService.queryByPrimaryId(sc.getGoodsInfoId());
                            if (goodsProduct != null && "0".equals(goodsProduct.getIsMailBay())) {
                                weight = weight.add(goodsProduct.getGoodsInfoWeight().multiply(
                                        new BigDecimal(sc.getGoodsNum())));
                                nums += Integer.parseInt(sc.getGoodsNum().toString());
                            }

                        } else {
                            // 套装运费计算
                            GoodsGroupVo goodsGroupVo = goodsGroupService.queryVoByPrimaryKey(sc.getFitId());
                            // 遍历套装下的商品
                            for (int j = 0; j < goodsGroupVo.getProductList().size(); j++) {

                                weight = weight.add(goodsGroupVo.getProductList().get(j).getProductDetail()
                                        .getGoodsInfoWeight().multiply(new BigDecimal(sc.getGoodsNum())));
                                nums += Integer.parseInt(sc.getGoodsNum().toString());

                            }

                        }
                    }

                }
                //根据城市Id 和第三方Id ,商品数量 查询运费
                price = price.add(freightTemplateService.getExpressPrice(distributionId, ca.getCity().getCityId(),
                        Long.valueOf(thirdIds[i]), nums, weight));
            }

        }

        // 商品数量 商品重量
        return price.toString();
    }

    /**
     * 新运费计算流程
     *
     * @param addressId
     * @param request
     * @param
     * @return
     */
    @RequestMapping("/getnewexpressprice")
    @ResponseBody
    public Map<String, Object> getnewexpressprice(Long addressId, HttpServletRequest request) {
        String[] shopCartIds = request.getParameterValues("shopIds[]");
        Long[] cartIds = new Long[shopCartIds.length];
        if (shopCartIds != null && shopCartIds.length != 0) {
            for (int i = 0; i < shopCartIds.length; i++) {
                cartIds[i] = Long.valueOf(shopCartIds[i]);
            }
        }
        // 获取 收获地址城市
        CustomerAddress ca = customerServiceInterface.queryCustAddress(addressId);
        if (ca == null) {
            return null;
        }
        Map<String, Object> freightMap = null;
        if (shopCartIds.length != 0 && shopCartIds != null && addressId != null) {
                //计算运费-不同地区的货品价格以及库存
                freightMap = shoppingCartService.getNewExpressPrice(request,ca.getDistrict().getDistrictId(), ca.getCity()
                        .getCityId(), cartIds);
        }

        return freightMap;
    }
}
