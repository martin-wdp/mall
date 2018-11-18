package com.ningpai.cloudshop.timer;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.ningpai.cloudshop.dao.CloudshopAuthorInfoMapper;
import com.ningpai.cloudshop.invoker.OpenSdkInvoker;
import com.qianmi.open.api.ApiException;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

/**
 * 定时刷新用户accessToken Created by liangck on 15/6/29.
 */
@Component("refreshTokenTask")
public class RefreshTokenTask {

    /** 日志输出 **/
    private static Logger logger = Logger.getLogger(RefreshTokenTask.class);

    /** 云销授权信息mapper **/
    @Resource(name = "CloudshopAuthorInfoMapper")
    private CloudshopAuthorInfoMapper authorInfoMapper;

    /** sdk调用 **/
    @Resource(name = "OpenSdkInvoker")
    private OpenSdkInvoker sdkInvoker;

    /**
     * 刷新用户accessToken任务
     */
    @Scheduled(cron = "0 0 0-1 * * ?")
    public void refreshToken() {
        try {
            isAuthoriedAndRefresh();
        } catch (ApiException e) {
            logger.info("更新accesstoken出错>>>error msg: ",e);
        }
    }

    /**
     * 验证是否有授权信息 and 是否需要刷新accessToken值
     * 
     * @return true:已经授权 false：还未有授权信息
     */
    private boolean isAuthoriedAndRefresh() throws ApiException {
        // 查询用户授权信息
        CloudshopAuthorInfo authorInfo = authorInfoMapper.selectAuthorInfo();
        // 如果用户授权信息为空 或 accessToken为空 或 refreshtoken为空 或 accessToken过期日期为空
        // 返回false
        if (authorInfo == null || authorInfo.getAccessToken() == null || authorInfo.getRefreshToken() == null || authorInfo.getAccessTokenExpiresDate() == null)
            return false;
        if (requireReshsh(authorInfo.getAccessTokenExpiresDate())) {
            CloudshopAuthorInfo minfo = sdkInvoker.refresgToken(authorInfo.getRefreshToken());
            if (minfo != null) {
                minfo.setCloudshopAuthorInfoId(authorInfo.getCloudshopAuthorInfoId());
                if ((authorInfoMapper.updateByPrimaryKeySelective(minfo)) == 1) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    /**
     * 验证是否需要刷新
     * 
     * @param accessTokenExpiresDate
     * @return true：需要刷新 false：不需要刷新
     */
    private boolean requireReshsh(Date accessTokenExpiresDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, +1);
        if (accessTokenExpiresDate.getTime() <= cal.getTime().getTime()) {
            return true;
        }
        return false;
    }

}
