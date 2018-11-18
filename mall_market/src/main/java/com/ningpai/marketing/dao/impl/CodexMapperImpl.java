package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.Codex;
import com.ningpai.marketing.dao.CodexMapper;

/**
 * 
 * @author ggn
 * 
 * @since 2014年3月19日下午1:48:20
 */
@Repository("CodexMapper")
public class CodexMapperImpl extends BasicSqlSupport implements CodexMapper {

    /*
     * 查询规则列表
     * 
     * @param paramMap {@link java.util.Map}
     * 
     * @return List
     * 
     * @see com.ningpai.marketing.dao.CodexMapper#selectCodexList(java.util.Map)
     */
    @Override
    public List<Object> selectCodexList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.CodexMapper.selectCodexList", paramMap);
    }

    /*
     * 查询规则总数
     * 
     * @param paramMap {@link java.util.Map}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.CodexMapper#selectCodexListCount(java.util.Map)
     */
    @Override
    public int selectCodexListCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.web.dao.CodexMapper.selectCodexListCount",
                paramMap);
    }

    /*
     * 添加规则
     * 
     * @param codex {@link com.ningpai.marketing.bean.Codex}
     * 
     * @return int
     * 
     * @see
     * com.ningpai.marketing.dao.CodexMapper#addCodex(com.ningpai.marketing.
     * bean.Codex)
     */
    @Override
    public int addCodex(Codex codex) {
        return this.insert("com.ningpai.web.dao.CodexMapper.addCodex", codex);
    }

    /*
     * 查询所有规则
     * 
     * @return Codex {@link com.ningpai.marketing.bean.Codex}
     * 
     * @see com.ningpai.marketing.dao.CodexMapper#selectCodexListUseBox()
     */
    @Override
    public List<Codex> selectCodexListUseBox() {
        return this
                .selectList("com.ningpai.web.dao.CodexMapper.selectCodexListUseBox");
    }

    /**
     * 根据促销id的集合查询促销属于哪一种类型
     * 
     * @param marketingIds
     * @return
     *
     */
    @Override
    public List<Codex> queryCodeByMarketingIds(List<Long> marketingIds) {
        return this.selectList(
                "com.ningpai.web.dao.CodexMapper.queryCodeByMarketingIds",
                marketingIds);
    }

    /*
     * 查询规则内容
     * 
     * @param codexId
     * 
     * @return Codex
     * 
     * @see
     * com.ningpai.marketing.dao.CodexMapper#queryCodexDetail(java.lang.Long)
     */
    @Override
    public Codex queryCodexDetail(Long codexId) {
        return this.selectOne(
                "com.ningpai.web.dao.CodexMapper.queryCodexDetail", codexId);
    }

    /**
     * 根据分组id查询规则内容
     *
     * @param codexId
     * @return Codex
     * @author NINGPAI-LIH
     *
     */
    @Override
    public List<Codex> queryCodexListByParam(Long codexStatus) {
        return this.selectList(
                "com.ningpai.web.dao.CodexMapper.queryCodexListByParam",
                codexStatus);
    }

}
