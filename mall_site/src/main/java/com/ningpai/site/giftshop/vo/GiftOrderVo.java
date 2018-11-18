package com.ningpai.site.giftshop.vo;

import java.util.List;

import com.ningpai.gift.bean.GiftPic;
import com.ningpai.site.giftshop.bean.GiftOrder;

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
