package com.ningpai.gift.vo;

import java.util.List;

import com.ningpai.gift.bean.GiftOrder;
import com.ningpai.gift.bean.GiftPic;

/**
 * 赠品订单VO
 * @author qiyuanyuan
 *
 */
public class GiftOrderVo extends GiftOrder{
      // 赠品名称
      private String giftName;
      
     // 兑换积分
      private Long giftIntegral;
      
      // 赠品图片
      private List<GiftPic> giftPicList;
      
      // 赠品价格
      private Long giftPrice;
      
    // 省份名称
     private String provinceName;
     
    // 城市名称
    private String cityName;
      
     // 区县名称
    private String districtName;
  //会员ID
    private Long customerId;

    //会员用户名
    private String customerUsername;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getGiftName() {
        return giftName;
    }
    
    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }
    
    public Long getGiftIntegral() {
        return giftIntegral;
    }
    
    public void setGiftIntegral(Long giftIntegral) {
        this.giftIntegral = giftIntegral;
    }
    
    public List<GiftPic> getGiftPicList() {
        return giftPicList;
    }
    
    public void setGiftPicList(List<GiftPic> giftPicList) {
        this.giftPicList = giftPicList;
    }

    public Long getGiftPrice() {
        return giftPrice;
    }

    public void setGiftPrice(Long giftPrice) {
        this.giftPrice = giftPrice;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }
}
