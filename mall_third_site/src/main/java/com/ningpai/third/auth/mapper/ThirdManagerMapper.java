package com.ningpai.third.auth.mapper;
import java.util.List;
import java.util.Map;
import com.ningpai.customer.bean.Customer;
import com.ningpai.third.auth.bean.ThirdManager;
import com.ningpai.third.auth.bean.ThirdPage;

/**
 * <p>第三方管理员底层</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
public interface ThirdManagerMapper {
    /**
     * 根据ID删除权限关系
     * @param id 权限关系ID
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增一条权限关系
     * @param record 权限关系对象
     *
     */
    int insert(ThirdManager record);

    /**
     * 新增一天权限关系
     * @param record 权限关系对象
     * @return
     */
    int insertSelective(ThirdManager record);

    /**
     * 根据ID查询单个的权限管辖对象
     * @param id 权限关系ID
     * @return
     */
    ThirdManager selectByPrimaryKey(Long id);

    /**
     * 修改权限管理对象
     * @param record  权限关系
     * @return
     */
    int updateByPrimaryKeySelective(ThirdManager record);

    /**
     * 修改权限管理对象
     * @param record 权限关系
     * @return
     */
    int updateByPrimaryKey(ThirdManager record);

    /**
     * 查询第三方用户信息
     * 
     * @param cid
     *            用户编号
     * @return Customer {@link com.ningpai.customer.bean.Customer}
     */
    Customer selectCustByCid(Long cid);

    /**
     * 根据商家编号查找商家角色列表
     * 
     * @param stordId
     *            商家编号 {@link java.lang.Long}
     * @return List<ThirdManager> 商家角色 {@link com.ningpai.third.auth.bean.ThirdManager}
     */
    List<ThirdManager> queryThirdManagerByStoreId(Long stordId);

    /**
     * 查找商家权限页面列表
     * 
     * @return List<ThirdPage> 商家角色 {@link ThirdPage}
     */
    List<ThirdPage> selectAllAuthority();

    /**
     * 根据商家编号 查询商家员工数量
     * 
     * @param stordId
     *            商家编号 {@link Long}
     * @return 商家员工数量 Long {@link Long}
     */
    Long queryEmployeeList(Long stordId);

    /**
     * 根据商家编号 查询商家员工集合
     * 
     * @param paramMap
     *            查询内容 {@link Map} 包含分页辅助类信息 和商家编号信息
     * @return {@link List} 商家员工集合
     */
    List<Object> queryEmployeeListByStotreId(Map<String, Object> paramMap);
}
