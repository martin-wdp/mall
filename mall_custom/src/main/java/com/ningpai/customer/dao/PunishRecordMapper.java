package com.ningpai.customer.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.customer.bean.PunishRecord;

/**
 *反馈接口
 */

public interface PunishRecordMapper {
    /**
     * 根据主键删除记录
     * @param id
     * @return 受影响的行数
     * */
    int deleteByPrimaryKey(Long id);
    /**
     * 插入一条记录
     *
     * @param record
     * */
    int insert(PunishRecord record);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insertSelective(PunishRecord record);
    /**
     * 根据主键id查询记录
     * @param id
     * */
    PunishRecord selectByPrimaryKey(Long id);
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    int updateByPrimaryKeySelective(PunishRecord record);
    /**
     * 根据主键进行修改
     *
     * @param record
     * */
    int updateByPrimaryKey(PunishRecord record);
    /**
     * 根据商家id查询
     *
     * @param thirdId
     * */
    PunishRecord queryInfoByThirdId(Long thirdId);

    /**
     * 根据商家id进行查询集合
     * @param thirdId
     * */
    List<PunishRecord> queryInfoByTidandDate(Long thirdId);

    /**
     * 第三方显示处罚记录用
     * 
     * @param paramMap
     * @return
     */
    List<Object> selectRecordByPage(Map<String, Object> paramMap);
    /**
     * 根据商家id查询所有行数
     *
     * @param thirdId
     * */
    int selectAllCountByTid(Long thirdId);

    /**
     * 后台显示处罚记录用
     */
    List<Object> selectPunishedRecordByPage(Map<String, Object> paramMap);

    /**
     * 查询所有根据参数
     *
     * @param paramMap
     * */
    int selectPunishedAllCountByTid(Map<String, Object> paramMap);
}
