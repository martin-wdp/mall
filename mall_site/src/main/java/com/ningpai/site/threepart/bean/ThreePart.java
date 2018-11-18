package com.ningpai.site.threepart.bean;

/**
 * 第三部分
 * @Description 第三部分
 * @author Songhl
 * @since 2015年8月28日 16:00:32
 */
public class ThreePart {
    /**
     * ID
     */
    private Long threePartId;
    /**
     * UID
     */
    private String threePartUid;
    /**
     * Token
     */
    private String threePartToken;
    /**
     * 用户ＩＤ
     */
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
