package com.ningpai.cloudshop.bean;

import java.io.Serializable;
import java.util.Date;
/**
 *云销授权
 * */
public class CloudshopAuthorInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 云销用户授权信息ID
     */
    private Integer cloudshopAuthorInfoId;

    /**
     * 云销授权用户user_id
     */
    private String cloudshopUserId;

    /**
     * 目前accessToken类型只支持(Bearer)
     */
    private String tokenType;

    /**
     * 用户授权Access Token值
     */
    private String accessToken;

    /**
     * 用户授权刷新access_token的Refresh Token
     */
    private String refreshToken;

    /**
     * 最后一次刷新access_token值的时间（即最后一次获取到access_token值的时间）
     */
    private Date lastRefreshTime;

    /**
     * access token 过期时间，单位 ：秒
     */
    private long expiresIn;

    /**
     * refresh_token 过期时间，单位：秒
     */
    private long reExpiresIn;

    /**
     * access_token的当前过期时间（即最后一次刷新时间加上access_token过期时长）
     */
    private Date accessTokenExpiresDate;

    /**
     * 当前refresh_token的过期时间（即最后一次刷新access_token 时间加上refresh_token的过期时长）
     */
    private Date refreshTokenExpiresDate;

    /**
     * 无参数构造器
     */
    public CloudshopAuthorInfo() {
    }

    /**
     * 参数构造器
     *
     * @param cloudshopAuthorInfoId
     * @param cloudshopUserId
     * @param tokenType
     * @param accessToken
     * @param refreshToken
     * @param lastRefreshTime
     */
    public CloudshopAuthorInfo(Integer cloudshopAuthorInfoId, String cloudshopUserId, String tokenType, String accessToken, String refreshToken, Date lastRefreshTime) {
        this.cloudshopAuthorInfoId = cloudshopAuthorInfoId;
        this.cloudshopUserId = cloudshopUserId;
        this.tokenType = tokenType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.lastRefreshTime = lastRefreshTime == null ? null : (Date) lastRefreshTime.clone();
    }

    /**
    *下面写一个authorinfo构建器，当一个类参数较多的时候可以采用该方式进行构造实例>>>>>>>>builder begin
     *  */
    public static class Builder {

        /**
         * 云销授权用户user_id
         */
        private String cloudshopUserId;

        /**
         * 目前accessToken类型只支持(Bearer)
         */
        private String tokenType;

        /**
         * 用户授权Access Token值
         */
        private String accessToken;

        /**
         * 用户授权刷新access_token的Refresh Token
         */
        private String refreshToken;

        /**
         * 最后一次刷新access_token值的时间（即最后一次获取到access_token值的时间）
         */
        private Date lastRefreshTime;
        /**
         * access token 过期时间，单位 ：秒
         */
        private long expiresIn;

        /**
         * refresh_token 过期时间，单位：秒
         */
        private long reExpiresIn;

        /**
         * 构造
         * */
        public Builder(String cloudshopUserid) {
            this.cloudshopUserId = cloudshopUserid;
        }
        /**
         * 设置Token类型
         * */
        public Builder setTokenType(String tokenType) {
            this.tokenType = tokenType;
            return this;
        }
        /**
         * 设置AccessToken
         * */
        public Builder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }
        /**
         * 设置RefreshTOken
         * */
        public Builder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }
        /**
         * 设置LastRefreshTime
         * */
        public Builder setLastRefreshTime(Date lastRefreshTime) {
            this.lastRefreshTime = lastRefreshTime == null ? null : (Date) lastRefreshTime.clone();
            return this;
        }
        /**
         * 设置ExpiresIn
         * */
        public Builder setExpiresIn(long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }
        /**
         * 设置ReExpiresIn
         * */
        public Builder setReExpiresIn(long reExpiresIn) {
            this.reExpiresIn = reExpiresIn;
            return this;
        }

        /**
         * 构建cloushopAtuhorInfo实例
         *
         * @return
         */
        public CloudshopAuthorInfo build() {
            return new CloudshopAuthorInfo(this);
        }
    }

    /**
     * 参数构造器，此处应为私有
     *
     * @param builder
     *            构建器
     */
    private CloudshopAuthorInfo(Builder builder) {
        cloudshopUserId = builder.cloudshopUserId;
        tokenType = builder.tokenType;
        accessToken = builder.accessToken;
        refreshToken = builder.refreshToken;
        lastRefreshTime = builder.lastRefreshTime;
        expiresIn = builder.expiresIn;
        reExpiresIn = builder.reExpiresIn;
    }

    /* <<<<<<<<<<<<<<builder end<<<<<<<<<< */
    /**
     * 获取ExpiresIn
     * */
    public long getExpiresIn() {
        return expiresIn;
    }
    /**
     * 设置ExpiresIn
     * */
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
    /**
     * 获取ReExpiresIn
     * */
    public long getReExpiresIn() {
        return reExpiresIn;
    }
    /**
     * 设置ReExpiresIn
     * */
    public void setReExpiresIn(long reExpiresIn) {
        this.reExpiresIn = reExpiresIn;
    }

    /**
     * @return 云销用户授权信息ID
     */
    public Integer getCloudshopAuthorInfoId() {
        return cloudshopAuthorInfoId;
    }

    /**
     * @param cloudshopAuthorInfoId
     *            云销用户授权信息ID
     */
    public void setCloudshopAuthorInfoId(Integer cloudshopAuthorInfoId) {
        this.cloudshopAuthorInfoId = cloudshopAuthorInfoId;
    }

    /**
     * @return 云销授权用户user_id
     */
    public String getCloudshopUserId() {
        return cloudshopUserId;
    }

    /**
     * @param cloudshopUserId
     *            云销授权用户user_id
     */
    public void setCloudshopUserId(String cloudshopUserId) {
        this.cloudshopUserId = cloudshopUserId == null ? null : cloudshopUserId.trim();
    }

    /**
     * @return 目前accessToken类型只支持(Bearer)
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * @param tokenType
     *            目前accessToken类型只支持(Bearer)
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType == null ? null : tokenType.trim();
    }

    /**
     * @return 用户授权Access Token值
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken
     *            用户授权Access Token值
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    /**
     * @return 用户授权刷新access_token的Refresh Token
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * @param refreshToken
     *            用户授权刷新access_token的Refresh Token
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken == null ? null : refreshToken.trim();
    }

    /**
     * @return 最后一次刷新access_token值的时间（即最后一次获取到access_token值的时间）
     */
    public Date getLastRefreshTime() {
        return (Date) lastRefreshTime.clone();
    }

    /**
     * @param lastRefreshTime
     *            最后一次刷新access_token值的时间（即最后一次获取到access_token值的时间）
     */
    public void setLastRefreshTime(Date lastRefreshTime) {
        this.lastRefreshTime = lastRefreshTime == null ? null : (Date) lastRefreshTime.clone();
    }

    /**
     * @return access_token的当前过期时间（即最后一次刷新时间加上access_token过期时长）
     */
    public Date getAccessTokenExpiresDate() {
        return accessTokenExpiresDate==null?null: (Date) accessTokenExpiresDate.clone();
    }

    /**
     * @param accessTokenExpiresDate
     *            access_token的当前过期时间（即最后一次刷新时间加上access_token过期时长）
     */
    public void setAccessTokenExpiresDate(Date accessTokenExpiresDate) {
        this.accessTokenExpiresDate = accessTokenExpiresDate == null ? null : (Date) accessTokenExpiresDate.clone();
    }

    /**
     * @return 当前refresh_token的过期时间（即最后一次刷新access_token 时间加上refresh_token的过期时长）
     */
    public Date getRefreshTokenExpiresDate() {
        return refreshTokenExpiresDate==null?null:(Date) refreshTokenExpiresDate.clone();
    }

    /**
     * @param refreshTokenExpiresDate
     *            当前refresh_token的过期时间（即最后一次刷新access_token
     *            时间加上refresh_token的过期时长）
     */
    public void setRefreshTokenExpiresDate(Date refreshTokenExpiresDate) {
        this.refreshTokenExpiresDate = refreshTokenExpiresDate == null ? null : (Date) refreshTokenExpiresDate.clone();
    }
    /**
     * 重写父类方法
     * */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CloudshopAuthorInfo other = (CloudshopAuthorInfo) that;
        return (this.getCloudshopAuthorInfoId() == null ? other.getCloudshopAuthorInfoId() == null : this.getCloudshopAuthorInfoId().equals(other.getCloudshopAuthorInfoId()))
                && (this.getCloudshopUserId() == null ? other.getCloudshopUserId() == null : this.getCloudshopUserId().equals(other.getCloudshopUserId()))
                && (this.getTokenType() == null ? other.getTokenType() == null : this.getTokenType().equals(other.getTokenType()))
                && (this.getAccessToken() == null ? other.getAccessToken() == null : this.getAccessToken().equals(other.getAccessToken()))
                && (this.getRefreshToken() == null ? other.getRefreshToken() == null : this.getRefreshToken().equals(other.getRefreshToken()))
                && (this.getLastRefreshTime() == null ? other.getLastRefreshTime() == null : this.getLastRefreshTime().equals(other.getLastRefreshTime()))
                && (this.getAccessTokenExpiresDate() == null ? other.getAccessTokenExpiresDate() == null : this.getAccessTokenExpiresDate().equals(
                        other.getAccessTokenExpiresDate()))
                && (this.getRefreshTokenExpiresDate() == null ? other.getRefreshTokenExpiresDate() == null : this.getRefreshTokenExpiresDate().equals(
                        other.getRefreshTokenExpiresDate()));
    }
    /**
     * 重写父类方法
     * */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCloudshopAuthorInfoId() == null) ? 0 : getCloudshopAuthorInfoId().hashCode());
        result = prime * result + ((getCloudshopUserId() == null) ? 0 : getCloudshopUserId().hashCode());
        result = prime * result + ((getTokenType() == null) ? 0 : getTokenType().hashCode());
        result = prime * result + ((getAccessToken() == null) ? 0 : getAccessToken().hashCode());
        result = prime * result + ((getRefreshToken() == null) ? 0 : getRefreshToken().hashCode());
        result = prime * result + ((getLastRefreshTime() == null) ? 0 : getLastRefreshTime().hashCode());
        result = prime * result + ((getAccessTokenExpiresDate() == null) ? 0 : getAccessTokenExpiresDate().hashCode());
        result = prime * result + ((getRefreshTokenExpiresDate() == null) ? 0 : getRefreshTokenExpiresDate().hashCode());
        return result;
    }
}