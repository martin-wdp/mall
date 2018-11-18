/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.service;

import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import java.util.Date;
/**
 * 数据分析Service层接口
 * @author zhangsl
 * @since 2014年12月19日 下午1:46:16
 * @version
 */
public interface DataAnalysisService {
    /**
     * 查询数据分析所需要的数据
     * @param pb
     * @param selectBean
     * @param startTime
     * @param endTime
     * @return
     */
    PageBean selectAllData(PageBean pb,SelectBean selectBean,Date startTime ,Date endTime);
    
    
}
