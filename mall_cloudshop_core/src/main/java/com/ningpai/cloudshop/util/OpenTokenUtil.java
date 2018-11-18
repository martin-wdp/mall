package com.ningpai.cloudshop.util;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.common.Commons;
import com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 开放平台授权token值存取工具类 Created by liangck on 15/6/26.
 */
@Component("openTokenUtil")
public class OpenTokenUtil {

    /** 云销授权信息mapper **/
    @Resource(name = "CloudshopAuthorInfoMapper")
    private CloudshopAuthorInfoMapper authorInfoMapper;

    /**
     * 设置并获取token值
     * 
     * @param request
     * @return token值
     */
    public String getAccessTokenVal(HttpServletRequest request) {
        return authorInfoMapper.getAccessToken();
    }

    /**
     * 设置token值
     * 
     * @param request
     * @param token
     *            token值
     */
    public void setAccessTokenVal(HttpServletRequest request, String token) {
        request.getSession().setAttribute(Commons.ACCESSTOKEN_SESSION_NAME, token);
    }

    /**
     * 检查用户账户授权信息是否已经设置及是否有效
     * 
     * @return true:设置且有效 false：未设置或无效
     */
    public boolean checkAccessToken() {
        CloudshopAuthorInfo authorInfo = authorInfoMapper.selectAuthorInfo();
        /* 检查授权用户信息是否为空 检查用户授权accessToken值及accessToken的过期日期是否为空 过期日期是否已过 */
        if (authorInfo == null || authorInfo.getAccessToken() == null || authorInfo.getAccessTokenExpiresDate() == null
                || (authorInfo.getAccessTokenExpiresDate().getTime() <= new Date().getTime()))
            return false;
        return true;
    }

}
