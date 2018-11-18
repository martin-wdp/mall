package com.ningpai.channel.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 频道商品
 * 
 * @author ggn
 *
 */
public class ChannelGoods implements Serializable {
    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -5033618656810060141L;

    /**
     * np_channel_goods.channel_Goods_id (频道首页促销商品自增主键)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Long channelGoodsId;

    /**
     * np_channel_goods.channel_id (频道id)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Long channelId;

    /**
     * np_channel_goods.goodes_product_no
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String goodesProductNo;

    /**
     * np_channel_goods.goods_product_name (商品名称)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String goodsProductName;

    /**
     * np_channel_goods.goods_product_id (商品id)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Long goodsProductId;

    /**
     * np_channel_goods.goods_product_img
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String goodsProductImg;

    /**
     * np_channel_goods.goods_product_price (商品价格)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    @NotNull
    @Digits(integer = 20, fraction = 2)
    private BigDecimal goodsProductPrice;

    /**
     * np_channel_goods.channel_Goods_flag (0今日特价 1新品推荐 2人气明星)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private String channelGoodsFlag;

    /**
     * np_channel_goods.channel_Goods_del_flag (删除标记)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private String channelGoodsDelFlag;

    /**
     * np_channel_goods.channel_Goods_sort (排序)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Integer channelGoodsSort;

    /**
     * np_channel_goods.channel_Goods_status (是否启动)
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private String channelGoodsStatus;

    /**
     * np_channel_goods.channel_Goods_create_time
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Date channelGoodsCreateTime;

    /**
     * np_channel_goods.channel_Goods_modify_time
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Date channelGoodsModifyTime;

    /**
     * np_channel_goods.channel_Goods_create_user
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Long channelGoodsCreateUser;

    /**
     * np_channel_goods.channel_Goods_modify_user
     * 
     * @ibatorgenerated 2014-06-04 16:22:26
     */
    private Long channelGoodsModifyUser;
    /**
     * 频道商品名称
     */
    private String channelGoodsFlagName;

    public String getChannelGoodsFlagName() {
        return channelGoodsFlagName;
    }

    public void setChannelGoodsFlagName(String channelGoodsFlagName) {
        this.channelGoodsFlagName = channelGoodsFlagName;
    }

    public Long getChannelGoodsId() {
        return channelGoodsId;
    }

    public void setChannelGoodsId(Long channelGoodsId) {
        this.channelGoodsId = channelGoodsId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getGoodesProductNo() {
        return goodesProductNo;
    }

    public void setGoodesProductNo(String goodesProductNo) {
        this.goodesProductNo = goodesProductNo;
    }

    public String getGoodsProductName() {
        return goodsProductName;
    }

    public void setGoodsProductName(String goodsProductName) {
        this.goodsProductName = goodsProductName;
    }

    public Long getGoodsProductId() {
        return goodsProductId;
    }

    public void setGoodsProductId(Long goodsProductId) {
        this.goodsProductId = goodsProductId;
    }

    public String getGoodsProductImg() {
        return goodsProductImg;
    }

    public void setGoodsProductImg(String goodsProductImg) {
        this.goodsProductImg = goodsProductImg;
    }

    public BigDecimal getGoodsProductPrice() {
        return goodsProductPrice;
    }

    public void setGoodsProductPrice(BigDecimal goodsProductPrice) {
        this.goodsProductPrice = goodsProductPrice;
    }

    public String getChannelGoodsFlag() {
        return channelGoodsFlag;
    }

    public void setChannelGoodsFlag(String channelGoodsFlag) {
        this.channelGoodsFlag = channelGoodsFlag;
    }

    public String getChannelGoodsDelFlag() {
        return channelGoodsDelFlag;
    }

    public void setChannelGoodsDelFlag(String channelGoodsDelFlag) {
        this.channelGoodsDelFlag = channelGoodsDelFlag;
    }

    public Integer getChannelGoodsSort() {
        return channelGoodsSort;
    }

    public void setChannelGoodsSort(Integer channelGoodsSort) {
        this.channelGoodsSort = channelGoodsSort;
    }

    public String getChannelGoodsStatus() {
        return channelGoodsStatus;
    }

    public void setChannelGoodsStatus(String channelGoodsStatus) {
        this.channelGoodsStatus = channelGoodsStatus;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getChannelGoodsCreateTime() {
        if (null != this.channelGoodsCreateTime) {
            return new Date(channelGoodsCreateTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setChannelGoodsCreateTime(Date channelGoodsCreateTime) {
        if (null != channelGoodsCreateTime) {
            Date temp = (Date) channelGoodsCreateTime.clone();
            if (null != temp) {
                this.channelGoodsCreateTime = temp;
            }
        }
        this.channelGoodsCreateTime = channelGoodsCreateTime;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getChannelGoodsModifyTime() {
        if (null != this.channelGoodsModifyTime) {
            return new Date(this.channelGoodsModifyTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     */
    public void setChannelGoodsModifyTime(Date channelGoodsModifyTime) {
        if (null != channelGoodsModifyTime) {
            Date temp = (Date) channelGoodsModifyTime.clone();
            if (null != temp) {
                this.channelGoodsModifyTime = temp;
            }
        }
    }

    public Long getChannelGoodsCreateUser() {
        return channelGoodsCreateUser;
    }

    public void setChannelGoodsCreateUser(Long channelGoodsCreateUser) {
        this.channelGoodsCreateUser = channelGoodsCreateUser;
    }

    public Long getChannelGoodsModifyUser() {
        return channelGoodsModifyUser;
    }

    public void setChannelGoodsModifyUser(Long channelGoodsModifyUser) {
        this.channelGoodsModifyUser = channelGoodsModifyUser;
    }

}
