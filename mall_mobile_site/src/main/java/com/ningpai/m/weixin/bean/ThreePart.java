package com.ningpai.m.weixin.bean;

/**
 * 微信注册辅助类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月26日 下午6:01:12
 * @version 0.0.1
 */
public class ThreePart {
    // ID
    private Long threePartId;
    // UID
    private String threePartUid;
    // Token
    private String threePartToken;
    // 用户ID
    private Long threePartMemberId;

    public Long getThreePartId() {
        return threePartId;
    }

    public void setThreePartId(Long threePartId) {
        this.threePartId = threePartId;
    }

    public String getThreePartUid() {
        return threePartUid;
    }

    public void setThreePartUid(String threePartUid) {
        this.threePartUid = threePartUid;
    }

    public String getThreePartToken() {
        return threePartToken;
    }

    public void setThreePartToken(String threePartToken) {
        this.threePartToken = threePartToken;
    }

    public Long getThreePartMemberId() {
        return threePartMemberId;
    }

    public void setThreePartMemberId(Long threePartMemberId) {
        this.threePartMemberId = threePartMemberId;
    }
}
