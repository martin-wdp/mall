package com.ningpai.thirdproject.mapper;





import java.util.List;
import java.util.Map;

import com.ningpai.thirdproject.bean.ThirdProject;

/**
 * 第三方专题管理Mapper
 * @author zhangsl
 * @since 2015年1月15日 上午10:32:22
 * @version
 */
public interface ThirdProjectMapper {
    
 
    /**
     * 添加
     * @param record
     * @return
     */
    int insertSelective(ThirdProject record);
    /**
     * 根据Id查询
     * @param thirdProjectId
     * @return
     */
    ThirdProject selectByPrimaryKey(Long thirdProjectId);
    
    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ThirdProject record);

    /**
     * 分页查询专题信息
     * @param paraMap
     * @return
     */
    List<Object> queryThirdProjectByPage(Map<String,Object> paraMap );
    /**
     * 查询专题信息的总条数
     * @return
     */
    int queryThirdProjectCount(ThirdProject record);
    /**
     * 根据Id删除专题信息（逻辑删除）
     * @return
     */
    int updateDelflagstatu(Map<String,Object> map);

    /**
     * 获取最后id
     * @return
     */
    Long selectLastId();
}
