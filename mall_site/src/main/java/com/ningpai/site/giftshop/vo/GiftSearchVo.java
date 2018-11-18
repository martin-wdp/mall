package com.ningpai.site.giftshop.vo;

import com.ningpai.site.giftshop.bean.Gift;

/**
 * 积分商城商品搜索Vo
 * @author qiyuanyuan
 *
 */
public class GiftSearchVo extends Gift{
    //开始积分
    private Long startIntegral;
    //结束积分
    private Long endIntegral;
    //积分区间标记
    private Long integralFlag;
    public Long getStartIntegral() {
        return startIntegral;
    }
    public void setStartIntegral(Long startIntegral) {
        this.startIntegral = startIntegral;
    }
    public Long getEndIntegral() {
        return endIntegral;
    }
    public void setEndIntegral(Long endIntegral) {
        this.endIntegral = endIntegral;
    }
    public Long getIntegralFlag() {
        return integralFlag;
    }
    public void setIntegralFlag(Long integralFlag) {
        this.integralFlag = integralFlag;
    }
    
    
}
