/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.dao.CodexMapper;
import com.ningpai.marketing.service.CodexService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * @author ggn 2014-03-21 规则service实现类
 */
@Service("CodexService")
public class CodexServiceImpl implements CodexService {

    @Resource(name = "CodexMapper")
    private CodexMapper codexMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.CodexService#selectCodexList(com.ningpai
     * .marketing.bean.Codex, com.ningpai.util.PageBean)
     */
    @Override
    public PageBean selectCodexList(Codex codex, PageBean pageBean) {
        codex.setFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(codex);
        // 查询总数
        pageBean.setRows(codexMapper.selectCodexListCount(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        if (no == 0) {
            no = 1;
        }
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            pageBean.setObjectBean(codex);
        }
        // 查询条件封装
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 查询列表页
            pageBean.setList(codexMapper.selectCodexList(paramMap));
            pageBean.setObjectBean(codex);
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.CodexService#addCodex(com.ningpai.marketing
     * .bean.Codex)
     */
    @Override
    public int addCodex(Codex codex) {
        codex.setCreateTime(new Date());
        codex.setFlag("0");
        codex.setModTime(new Date());
        return codexMapper.addCodex(codex);
    }

    /*
     * 
     * 
     * @see com.ningpai.marketing.service.CodexService#selectCodexListUseBox()
     */
    @Override
    public List<Codex> selectCodexListUseBox() {
        return codexMapper.selectCodexListUseBox();
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.CodexService#queryCodexDetail(java.lang
     * .Long)
     */
    @Override
    public Codex queryCodexDetail(Long codexId) {
        return codexMapper.queryCodexDetail(codexId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.marketing.service.CodexService#queryCodexListByParam(java
     * .lang.Long)
     */
    @Override
    public List<Codex> queryCodexListByParam(Long codexStatus) {
        return codexMapper.queryCodexListByParam(codexStatus);
    }

}
