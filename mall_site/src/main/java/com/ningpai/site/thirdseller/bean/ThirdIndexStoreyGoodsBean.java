package com.ningpai.site.thirdseller.bean;

/**
 * 楼层-货品实体
 * @author qiyuanyuan
 *
 */
public class ThirdIndexStoreyGoodsBean {
    // 图片
    private String urlpic;
    // 名称
    private String name;
    // 编码
    private String number;
    // 价格
    private String price;

    // 楼层商品类型
    private String storeyGoodsFlag;

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

    public String getStoreyGoodsFlag() {
        return storeyGoodsFlag;
    }

    public void setStoreyGoodsFlag(String storeyGoodsFlag) {
        this.storeyGoodsFlag = storeyGoodsFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
