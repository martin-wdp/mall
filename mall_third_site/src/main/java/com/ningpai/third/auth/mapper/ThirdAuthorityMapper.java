package com.ningpai.third.auth.mapper;

import java.util.List;
import com.ningpai.third.auth.bean.ThirdAuthority;

/**
 * <p>第三方商家权限Mapper</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface ThirdAuthorityMapper {
    /***
     * 根据主键获取单个的职位对象
     * @param id
     * @return
     */
    ThirdAuthority selectAuthorById(Long id);
    /**
     * 删除权限
     * @param id 权限ID thirdId 商家ID
     * @return
     */
    int deleteByPrimaryKey(Long id,Long thirdId);

    /**
     * 新增一条权限
     * @param record 第三方权限Bean
     * @return
     */
    int insert(ThirdAuthority record);

    /**
     * 根据对象插入信息
     * @param record
     *            第三方权限角色 {@link ThirdAuthority}
     * @return 0 失败 1成功 {@link Integer}
     */
    int insertSelective(ThirdAuthority record);

    /**
     * 根据权限ID获取权限对象
     * @param id 权限ID
     * @return
     */
    ThirdAuthority selectByPrimaryKey(Long id);

    /**
     * 修改权限
     * @param record 权限对象
     * @return
     */
    int updateByPrimaryKeySelective(ThirdAuthority record);

    /**
     * 修改权限
     * @param record 权限对象
     * @return
     */
    int updateByPrimaryKey(ThirdAuthority record);

    /**
     * 根据商家编号查询商家权限列表
     * 
     * @param storeId
     *            商家编号 {@link java.lang.Long}
     * @return List<ThirdAuthority> 商家权限 {@link ThirdAuthority}
     */
    List<ThirdAuthority> queryThirdAuthorityByStotreId(Long storeId);

    /**
     * 根据角色名称查找角色
     * @param designation
     *            角色名称 {@link java.lang.String}
     * @return ThirdAuthority 第三方权限角色 {@link ThirdAuthority}
     */
    List<ThirdAuthority> selectByDesignation(String designation,Long thirdId);
}
