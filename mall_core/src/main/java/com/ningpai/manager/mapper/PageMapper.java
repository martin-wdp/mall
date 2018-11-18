package com.ningpai.manager.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.manager.bean.Page;
import com.ningpai.manager.bean.valuebean.MenuVo;

/**
 * 页面Mapper
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午2:47:45
 */
public interface PageMapper {
    /**
     * 根据ID删除页面信息
     * 
     * @param id
     *            页面ID
     * @return int 1表示成功 0表示失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入页面信息
     * 
     * @param record
     *            <code>Page</code>对象 {@link com.ningpai.manager.bean.Page}
     * @return int 1表示成功 0表示失败
     */
    int insertSelective(Page record);

    /**
     * 根据ID选取页面信息
     * 
     * @param id
     *            页面ID
     * @return <code>Page</code>对象 {@link com.ningpai.manager.bean.Page}
     */
    Page selectByPrimaryKey(Long id);

    /**
     * 根据id选取结果
     * */
    List<Long> selectByPrimaryKeys(List<Long> list);

    /**
     * 修改页面信息
     * 
     * @param record
     *            <code>Page</code>对象 {@link com.ningpai.manager.bean.Page}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKeySelective(Page record);

    /**
     * 根据ID选取页面信息
     * 
     * @param id
     *            页面ID
     * @return <code>MenuVo</code>对象
     */
    List<MenuVo> selectByParentId(Long id);

    /**
     * 根据pageBean分页查询分类列表
     * 
     * @param map
     *            封装分页参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 查询所有的记录行数
     * 
     * @return {@link java.lang.Integer}
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 查询所有的菜单
     * 
     * @return 所有的商品分类列表 {@link java.util.List}
     */
    List<MenuVo> queryAllMenuVo();

    /**
     * 根据父分类ID查询子分类的个数
     * 
     * @param pageId
     * @return
     */
    int querySonCountByParentId(Long pageId);

    /**
     * 查询所有的菜单 节点
     * 
     * @return 查询到的列表
     */
    List<MenuVo> queryAllParentMenuVo();

    /**
     * 查询所有的菜单 页面
     * 
     * @return 查询到的列表
     */
    List<MenuVo> queryAllChildrenMenuVo();

    /**
     * chauxn 
     * @param managerId
     * @return List
     */
    List<MenuVo> queryAllParentMenuVoByLogin(Long managerId);

    /**
     * 查询id范围在 startId 和 endId 之间的菜单
     *
     * @param paramMap 里面装bundleName
     * @return 菜单集合 List
     */
    List<MenuVo> queryMenuByBundleName(Map<String, Object> paramMap);

    /**
     * 查询id范围在 startId 和 endId 之间的菜单
     *
     * @param paramMap 里面装开始startId和结束endId
     */
    void deleteMenuByBundleName(Map<String, Object> paramMap);

}
