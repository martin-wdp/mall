/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.service;

import com.ningpai.comment.bean.Infosetting;

/**
 * 消息设置接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:25:38
 * @version 1.0
 */
public interface InfoSettingServiceMapper {
    /**
     * 修改消息设置
     * 
     * @param infosetting
     * @return
     */
    int updateInfoSetting(Infosetting infosetting);

    /**
     * 查找当前设置
     * 
     * @return
     */
    Infosetting selectInfoSetting();
}
