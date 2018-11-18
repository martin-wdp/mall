package com.ningpai.cloudshop.util;

import com.ningpai.util.PageBean;
import com.qianmi.open.api.domain.cloudshop.Item;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 云销商品分页实体类，封装商品分页信息 Created by liangck on 15/6/26.
 */
@Component("itemPageBean")
public class ItemPageBean extends PageBean {
    /**
     * 商品列表数据
     */
    private List<Item> items;

    /**
     * 获取商品列表数据
     * 
     * @return
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 设置商品列表数据
     * 
     * @param items
     *            商品列表数据
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
