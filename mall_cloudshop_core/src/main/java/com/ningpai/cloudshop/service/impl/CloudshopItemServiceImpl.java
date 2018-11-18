package com.ningpai.cloudshop.service.impl;

import com.ningpai.cloudshop.invoker.OpenSdkInvoker;
import com.ningpai.cloudshop.service.ICloudshopItemService;
import com.ningpai.cloudshop.util.ItemPageBean;
import com.ningpai.cloudshop.util.OpenTokenUtil;
import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.dao.GoodsImportMapper;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.cloudshop.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 云销商品service实现类 Created by liangck on 15/6/26.
 */
@Service("CloudshopItemService")
public class CloudshopItemServiceImpl implements ICloudshopItemService {

    /**
     * sdk调用的封装，相当于dao吧
     */
    @Resource(name = "OpenSdkInvoker")
    private OpenSdkInvoker invoker;

    /**
     * 用户授权 accessToken存取值工具类
     */
    @Resource(name = "openTokenUtil")
    private OpenTokenUtil tokenUtil;

    /** 商品导入表mapper **/
    @Resource(name = "GoodsImportMapper")
    private GoodsImportMapper goodsImportMapper;
    /**
     * 获取用户库存中的分销商品（已下架商品）
     *
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    @Override
    public ItemPageBean getInventoryItems(ItemPageBean pb,
            HttpServletRequest request) throws ApiException {
        return invoker.getInventoryItems(pb,
                tokenUtil.getAccessTokenVal(request));
    }

    /**
     * 获取用户出售中（已上架的商品信息）
     *
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    @Override
    public ItemPageBean getOnsaleItems(ItemPageBean pb,
            HttpServletRequest request) throws ApiException {

        return invoker.getOnsaleItems(pb, tokenUtil.getAccessTokenVal(request));
    }

    /**
     * 根据商品编号查询商品详细信息（包含sku）
     *
     * @param numIid
     * @param request
     * @return
     */
    @Override
    public Item getItemDetail(String numIid, HttpServletRequest request)
            throws ApiException {
        return invoker.getItemDetail(numIid,
                tokenUtil.getAccessTokenVal(request));
    }

    /**
     * 导入商品到商品导入表
     *
     * @param item
     *            云销商品
     * @param request
     * @return 操作结果
     */
    @Override
    @Transactional
    public boolean importItem(Item item, HttpServletRequest request) {
        GoodsImport goodsImport = convertToImportGoods(item);
        goodsImport.setThirdId(0L);
        goodsImport.setThirdName("BOSS");
        return goodsImportMapper.insertSelective(goodsImport) == 1;
    }

    /**
     * 将开放平台item转换为商品导入数据
     * 
     * @param item
     *            开放平台item信息
     * @return GoodsImport 商品导入信息bean
     */
    private GoodsImport convertToImportGoods(Item item) {
        GoodsImport goods = new GoodsImport();
        goods.setGoodsName(item.getTitle());
        goods.setGoodsSubtitle(item.getTitle());
        goods.setGoodsPrice(BigDecimal.valueOf(Double.parseDouble(item
                .getPrice())));
        goods.setGoodsCostPrice(item.getCostPrice() == null ? goods
                .getGoodsPrice() : BigDecimal.valueOf(Double.parseDouble(item
                .getCostPrice())));
        goods.setGoodsMarketPrice(item.getMktPrice() == null ? goods
                .getGoodsPrice() : BigDecimal.valueOf(Double.parseDouble(item
                .getMktPrice())));
        goods.setSeoTit(item.getTitle());
        goods.setSeoKey(item.getKeywords());
        goods.setSeoDes(item.getDesc());
        goods.setGoodsDes(item.getDesc());
        return goods;
    }

}
