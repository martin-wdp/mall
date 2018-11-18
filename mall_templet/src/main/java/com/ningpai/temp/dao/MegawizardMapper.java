package com.ningpai.temp.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.temp.bean.Megawizard;

/**
 * 页面说明数据操作接口
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014-07-24 17:43:53
 */
public interface MegawizardMapper {
    /**
     * 新建页面说明
     * 
     * @param record
     * @return
     */
    int insert(Megawizard record);

    /**
     * 根据模板id查询总行数
     * 
     * @param tempid
     * @return
     */
    int selectCountByTempId(int tempId);

    /**
     * 根据模板id分页查询页面说明
     * 
     * @param map
     * @return
     */
    List<Object> selectByTempId(Map<String, Object> map);

    /**
     * 根据id修改删除标记
     * 
     * @param id
     * @return
     */
    int updateById(List<Long> list);

    /**
     * 根据id修改页面说明内容
     * 
     * @param meg
     * @return
     */
    int updateByIdToContent(Megawizard meg);

    /**
     * 根据id查询单个页面说明
     * 
     * @param id
     * @return
     */
    Megawizard selectById(Long id);

    /**
     * 根据排序号查询
     * 
     * @param sort
     * @return
     */
    Megawizard selectBySort(Map<String, Object> map);
}
