/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.service;

import java.util.List;

import com.ningpai.marketing.bean.Codex;
import com.ningpai.util.PageBean;

/**
 * @author ggn 2014-03-21 规则service
 */
public interface CodexService {

    /**
     * 查询规则列表
     * 
     * @param codex
     *            {@link com.ningpai.marketing.bean.Codex}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    PageBean selectCodexList(Codex codex, PageBean pageBean);

    /**
     * 添加规则
     * 
     * @param codex
     *            {@link com.ningpai.marketing.bean.Codex}
     * @return int
     */
    int addCodex(Codex codex);

    /**
     * 查询所有促销规则
     * 
     * @return List
     */
    List<Codex> selectCodexListUseBox();

    /**
     * 查询规则明细
     * 
     * @param codexId
     * @return Codex
     */
    Codex queryCodexDetail(Long codexId);

    /**
     * 查询规则列表
     * 
     * @param codex
     *            {@link com.ningpai.marketing.bean.Codex}
     * @return
     */
    List<Codex> queryCodexListByParam(Long codexStatus);

}
