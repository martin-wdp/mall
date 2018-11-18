package com.ningpai.group.dao;

import com.ningpai.group.bean.Label;
import com.ningpai.util.SelectBean;

import java.util.List;
import java.util.Map;

/**
 * 标签dao
 */
public interface LabelMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    int deleteByPrimaryKey(Long groupLabelId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    int insert(Label record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    int insertSelective(Label record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    Label selectByPrimaryKey(Long groupLabelId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    int updateByPrimaryKeySelective(Label record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    int updateByPrimaryKey(Label record);

    /**
     * 查询所有的小组标签
     * 
     * @return list
     */
    List<Label> allLabel();

    /**
     * 小组标签分页列表
     * 
     * @param paramMap
     *            查询参数{@link java.util.Map}
     * @return list
     */
    List<Object> labelList(Map<String, Object> paramMap);

    /**
     * 小组标签数目
     * 
     * @param selectBean
     * @return
     */
    int labelCount(SelectBean selectBean);

    /**
     * 根据标签Id删除标签
     * 
     * @param labelId
     *            标签Id{@link java.lang.Long}
     * @return
     */
    int deleteLabel(Long labelId);

    /**
     * 操作小组标签(停用or恢复)
     * 
     * @param paramMap
     *            条件参数{@link java.util.Map}
     * @return
     */
    int operation(Map<String, Object> paramMap);

    /**
     * 验证小组标签是否存在
     * 
     * @param labelName
     *            小组标签名称{@linnk java.lang.String}
     * @return 对象
     */
    Label checkName(String labelName);
}
