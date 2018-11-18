package com.ningpai.cloudshop.service;

import com.ningpai.cloudshop.util.ItemPageBean;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.domain.cloudshop.Item;

import javax.servlet.http.HttpServletRequest;

/**
 * 云销商品service接口 Created by liangck on 15/6/26.
 */
public interface ICloudshopItemService {

    /**
     * 获取用户库存中的分销商品（已下架商品）
     * 
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    ItemPageBean getInventoryItems(ItemPageBean pb, HttpServletRequest request)
            throws ApiException;

    /**
     * 获取用户出售中（已上架的商品信息）
     * 
     * @param pb
     *            分页工具类
     * @return 填充好分页数据的分页工具类
     */
    ItemPageBean getOnsaleItems(ItemPageBean pb, HttpServletRequest request)
            throws ApiException;

    /**
     * 根据商品编号查询商品详细信息（包含sku）
     * 
     * @param numIid
     * @param request
     * @return
     */
    Item getItemDetail(String numIid, HttpServletRequest request)
            throws ApiException;

    /**
     * 导入商品到商品导入表
     * 
     * @param item
     *            云销商品
     * @param request
     * @return 操作结果
     */
    boolean importItem(Item item, HttpServletRequest request);
}
