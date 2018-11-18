package com.ningpai.manager.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;
import com.ningpai.manager.mapper.PageMapper;

/**
 * @see com.ningpai.manager.mapper.PageMapper
 * @author AthrunNatu
 * @since 2013年11月20日下午4:40:05
 */
@Repository("PageMapperImpl") 
public final class PageMapperImpl extends BasicSqlSupport implements PageMapper {
    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long id) {

        return this.delete("com.ningpai.manager.mapper.PageMapper.deleteByPrimaryKey", id);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#insertSelective(com.ningpai.manager .bean.Page)
     */
    @Override
    public int insertSelective(Page record) {

        return this.insert("com.ningpai.manager.mapper.PageMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public Page selectByPrimaryKey(Long id) {

        return this.selectOne("com.ningpai.manager.mapper.PageMapper.selectByPrimaryKey", id);
    }

    /**
     * 根据id选取结果
     * */
    @Override
    public List<Long> selectByPrimaryKeys(List<Long> pageList) {

        return this.selectList("com.ningpai.manager.mapper.PageMapper.selectByPrimaryKeys", pageList);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#updateByPrimaryKeySelective(com .ningpai.manager.bean.Page)
     */
    @Override
    public int updateByPrimaryKeySelective(Page record) {
        return this.update("com.ningpai.manager.mapper.PageMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#selectByParentId(java.lang.Long)
     */
    @Override
    public List<MenuVo> selectByParentId(Long id) {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.selectByParentId", id);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryByPageBean(java.util.Map)
     */
    @Override
    public List<Object> queryByPageBean(Map<String, Object> map) {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.queryByPageBean", map);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryTotalCount(java.util.Map)
     */
    @Override
    public Integer queryTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.manager.mapper.PageMapper.queryTotalCount", map);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryAllMenuVo()
     */
    @Override
    public List<MenuVo> queryAllMenuVo() {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.queryAllMenuVo");
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#querySonCountByParentId(java.lang .Long)
     */
    @Override
    public int querySonCountByParentId(Long pageId) {
        return this.selectOne("com.ningpai.manager.mapper.PageMapper.querySonCountByParentId", pageId);
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryAllParentMenuVo()
     */
    @Override
    public List<MenuVo> queryAllParentMenuVo() {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.queryAllParentMenuVo");
    }

    /**
     * 
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryAllChildrenMenuVo()
     */
    @Override
    public List<MenuVo> queryAllChildrenMenuVo() {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.queryAllChildrenMenuVo");
    }

    /**
     * 
     * @see com.ningpai.manager.mapper.PageMapper#queryAllParentMenuVoByLogin(java.lang.Long)
     */
    @Override
    public List<MenuVo> queryAllParentMenuVoByLogin(Long managerId) {
        
           return this.selectList("com.ningpai.manager.mapper.PageMapper.queryAllParentMenuVoByLogin",managerId);
    }

    /**
     * 查询id范围在 startId 和 endId 之间的菜单
     *
     * @param paramMap 里面装开始startId和结束endId
     * @return 菜单集合 List
     */
    @Override
    public List<MenuVo> queryMenuByBundleName(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.manager.mapper.PageMapper.queryMenuByBundleName",paramMap);
    }

    /**
     * 查询id范围在 startId 和 endId 之间的菜单
     *
     * @param paramMap 里面装开始startId和结束endId
     */
    @Override
    public void deleteMenuByBundleName(Map<String, Object> paramMap) {
        this.delete("com.ningpai.manager.mapper.PageMapper.deleteMenuByBundleName", paramMap);
    }

}
