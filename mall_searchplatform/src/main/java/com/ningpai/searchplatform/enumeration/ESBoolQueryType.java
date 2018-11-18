package com.ningpai.searchplatform.enumeration;

/**
 * <p>
 * bool查询类型 must/should/mustnot
 * </p>
 * 
 * @author liangck
 * @version 1.0
 * @since 15/8/10 14:51
 */
public enum ESBoolQueryType {

    /**
     * MUST
     */
    MUST(),

    /**
     * SHOULD
     */
    SHOULD(),

    /**
     * MUST_NOT
     */
    MUST_NOT();

    ESBoolQueryType() {
    }
}
