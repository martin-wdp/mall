package com.ningpai.thirdaudit.mapper;

import java.util.List;
import java.util.Map;

/**
 * 第三方 角色权限关系Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月7日 下午4:19:16
 * @version 0.0.1
 */
public interface ThirdManagerAuthorityMapper {

    /**
     * 添加员工权限记录
     * 
     * @param paramMap
     *            {@link Map}
     * @return 0 失败 1成功
     */
    int addRecord(Map<String, Object> paramMap);

    /**
     * 添加职位
     * 
     * @param map
     *            {@link Map}
     * @return 0 失败 1成功
     */
    int insertAuthority(Map<String, Object> map);

    /**
     * 返回最新插入的id
     * 
     * @return
     */
    Long selectLastId();

    /**
     * 添加职位权限
     * 
     * @param map
     * @return
     */
    int insertPageList(Map<String, Object> map);

    /**
     * 查询第三方所有的权限
     * 
     * @return
     */
    List<Long> selectAllThirdPageId();
}
