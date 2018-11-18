package com.ningpai.system.cache;

import com.ningpai.system.exception.NPException;

/**
 * 字典缓存接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-19 16:01:06
 * @version V1.0
 */
public interface IDictionarysCache {

    /**
     * 根据字段的名称获得字典
     * 
     * @param name
     *            字典名称
     * @return 字典
     * @throws com.ningpai.system.exception.NPException
     *             自定义异常
     * @author system
     * @date 2013-06-20 13:02
     */
    Object getDictionaryByName(String name) throws NPException;
}
