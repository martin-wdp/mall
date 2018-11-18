package com.ningpai.comment.dao;

import com.ningpai.comment.bean.Infosetting;

/**
 * 消息设置
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月31日 下午6:37:36
 * @version 0.0.1
 */
public interface InfosettingMapper {
    /**
     * 修改消息状态
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Infosetting record);

    /**
     * 查找当前设置
     * 
     * @return
     */
    Infosetting selectInfoSetting();
}
