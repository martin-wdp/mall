package com.ningpai.cloudshop.service.impl;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.common.Commons;
import com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper;
import com.ningpai.cloudshop.service.CloudshopAuthorInfoService;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.response.TokenResponse;
import com.qianmi.open.api.tool.util.OAuthUtils;
import com.qianmi.open.api.tool.util.QianmiContext;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 云销用户授权信息service实现 Created by liangck on 15/6/25.
 */
@Service("CloudshopAuthorInfoService")
public class CloudshopAuthorInfoServiceImpl implements CloudshopAuthorInfoService {

    // private Logger logger =
    // Logger.getLogger(CloudshopAuthorInfoServiceImpl.class);
    /** 用户授权信息mapper **/
    @Resource(name = "CloudshopAuthorInfoMapper")
    private CloudshopAuthorInfoMapper authorInfoMapper;

    /**
     * 清楚授权账户信息
     *
     * @return 操作结果
     */
    @Override
    @CacheEvict(value = "authorinfo", key = "'queryAuthoredInfo'")
    public boolean clearAuthorInfo() {
        return authorInfoMapper.deleteAuthorInfo() == 1;
    }

    /**
     * 插入用户授权信息
     *
     * @param authorInfo
     *            用户授权信息
     * @return 操作结果
     */
    @Override
    public boolean insertAuthorInfo(CloudshopAuthorInfo authorInfo) {
        return authorInfoMapper.insertSelective(authorInfo) == 1;
    }

    /**
     * 更新用户授权信息
     *
     * @param authorInfo
     *            用户授权信息
     * @return 操作结果
     */
    @Override
    public boolean updateAuthorInfo(CloudshopAuthorInfo authorInfo) {
        return authorInfoMapper.updateByPrimaryKeySelective(authorInfo) == 1;
    }

    /**
     * 获取云销用户授权信息（user_id accessToken refresh_token 等信息）
     *
     * @param code
     *            授权码
     * @return 用户授权信息
     */
    @Override
    public TokenResponse getAuthodAccessInfo(String code) throws ApiException {
        /* 判断当前回传状态是否与传入状态一致 */
        QianmiContext context = OAuthUtils.getToken(Commons.APPKEY, Commons.APPSECRET, code);
        return context.getTokenResponse();
    }

    /**
     * 处理得到到授权信息
     *
     * @param response
     *            tokenResponse
     * @return 用户授权信息，为null时，获取授权失败
     */
    @Override
    @Transactional
    @CacheEvict(value = "authorinfo", key = "'queryAuthoredInfo'")
    public CloudshopAuthorInfo processTokenResponse(HttpServletRequest request, TokenResponse response) {
        CloudshopAuthorInfo authorInfo = null;
        /* errorcode 为0 ，标示调用成功 */
        // if("0".equals(response.getErrorCode())){
        authorInfo = new CloudshopAuthorInfo.Builder(response.getUserId()).setAccessToken(response.getAccessToken())
        // lastRefreshTime设置为当前请求时间
                .setExpiresIn(response.getExpiresIn()).setLastRefreshTime(new Date()).setReExpiresIn(response.getReExpiresIn()).setRefreshToken(response.getRefreshToken()).build();
        /* 设置access_token过期日期 */
        authorInfo.setAccessTokenExpiresDate(new Date(authorInfo.getLastRefreshTime().getTime() + authorInfo.getExpiresIn() * 1000));
        /* 设置refresh_token过期日期 */
        authorInfo.setRefreshTokenExpiresDate(new Date(authorInfo.getLastRefreshTime().getTime() + authorInfo.getReExpiresIn() * 1000));
        /* 更新授权信息，存在则更新，，不存在则插入 */
        CloudshopAuthorInfo curAuthorInfo = authorInfoMapper.selectAuthorInfo();
        if (curAuthorInfo == null) {
            authorInfoMapper.insertSelective(authorInfo);
        } else {
            curAuthorInfo.setExpiresIn(authorInfo.getExpiresIn());
            curAuthorInfo.setReExpiresIn(authorInfo.getReExpiresIn());
            curAuthorInfo.setAccessTokenExpiresDate(authorInfo.getAccessTokenExpiresDate());
            curAuthorInfo.setRefreshTokenExpiresDate(authorInfo.getRefreshTokenExpiresDate());
            curAuthorInfo.setLastRefreshTime(authorInfo.getLastRefreshTime());
            authorInfoMapper.updateByPrimaryKeySelective(curAuthorInfo);
        }
        return authorInfo;
    }

    /**
     * @return
     */
    @Override
    @Cacheable(value = "authorinfo", key = "'queryAuthoredInfo'")
    public CloudshopAuthorInfo queryAuthoredInfo() {
        return authorInfoMapper.selectAuthorInfo();
    }

}
