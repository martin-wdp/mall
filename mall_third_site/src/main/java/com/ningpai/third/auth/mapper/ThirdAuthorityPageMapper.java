package com.ningpai.third.auth.mapper;

import java.util.List;
import java.util.Map;
import com.ningpai.third.auth.bean.ThirdAuthorityPage;
import com.ningpai.third.auth.bean.ThirdPage;

/**
 * <p>权限页面关系Mapper</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface ThirdAuthorityPageMapper {
    /**
     * 根据ID删除页面权限关系
     * @param id 第三方权限页面id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增一条权限关系
     * @param record 第三方权限页面
     * @return
     */
    int insert(ThirdAuthorityPage record);

    /**
     * 插入一条权限关系
     * @param record 第三方权限页面
     * @return
     */
    int insertSelective(ThirdAuthorityPage record);

    /**
     * 根据ID查询权限页面对象
     * @param id 第三方权限页面id
     * @return
     */
    ThirdAuthorityPage selectByPrimaryKey(Long id);

    /**
     * 修改第三方权限对象
     * @param record 权限对象
     * @return
     */
    int updateByPrimaryKeySelective(ThirdAuthorityPage record);

    /**
     * 修改第三方权限
     * @param record 权限对象
     * @return
     */
    int updateByPrimaryKey(ThirdAuthorityPage record);

    /**
     * 根据权限编号查询权限页面
     * @param num
     *            权限编号
     * @return List<ThirdPage> 权限页面 {@link ThirdPage}
     */
    List<ThirdPage> selectByAuthorityID(Long num);

    /**
     * 查询子菜单
     * 
     * @param num
     *            权限编号
     * @return List<ThirdPage> 权限页面 {@link ThirdPage}
     */
    List<ThirdPage> selectAllMenuVos(Long num);

    /**
     * 根据权限编号查询所有的页面
     * 
     * @param authorityId
     *            权限编号
     * @return List<ThirdPage> 权限页面 {@link ThirdPage}
     */
    List<ThirdPage> selectAllPageByAuthorityID(Long authorityId);

    /**
     * 新增一条权限
     * @param paramMap 状态要新增的数据
     * @return
     */
    int addPageToAuthority(Map<String, Object> paramMap);

    /**
     * 根据条件删除权限
     * @param paramMap
     * @return
     */
    int delPageToAuthority(Map<String, Object> paramMap);

    /**
     * 根据权限编号 页面父编号查找页面
     * 
     * @param parmaMap
     * @return List<ThirdPage> 权限页面 {@link ThirdPage}
     */
    List<ThirdPage> selectPageByAuthIdAndParentId(Map<String, Object> parmaMap);
}
