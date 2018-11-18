package com.ningpai.index.bean;

/**
 * 商品类
 * 
 * @author AthrunNatu
 * 
 * @since 2014年4月9日下午3:26:42
 */

public class IndexGoodsBean {

    /**
     * 图片
     */
    private String urlpic;
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String number;
    /**
     * 价格
     */
    private String price;

    /**
     * 楼层商品类型
     */
    private String storeyGoodsFlag;

    /**
     * 编号
     */
    private String id;

    public String getUrlpic() {
        return urlpic;
    }

    public void setUrlpic(String urlpic) {
        this.urlpic = urlpic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreyGoodsFlag() {
        return storeyGoodsFlag;
    }

    public void setStoreyGoodsFlag(String storeyGoodsFlag) {
        this.storeyGoodsFlag = storeyGoodsFlag;
    }
}
