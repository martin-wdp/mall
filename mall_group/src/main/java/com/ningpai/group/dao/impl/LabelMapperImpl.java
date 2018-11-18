package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.Label;
import com.ningpai.group.dao.LabelMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.SelectBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 小组标签DAO接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Repository("LabelMapper")
public class LabelMapperImpl extends BasicSqlSupport implements LabelMapper {

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public int deleteByPrimaryKey(Long groupLabelId) {
        return this.delete("com.ningpai.dao.GroupLabelMapper.deleteByPrimaryKey", groupLabelId);
    }


    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public int insert(Label label) {
        return this.insert("com.ningpai.dao.GroupLabelMapper.insert", label);
    }

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public int insertSelective(Label label) {
        return this.insert("com.ningpai.dao.GroupLabelMapper.insertSelective", label);
    }

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public Label selectByPrimaryKey(Long groupLabelId) {
        return this.selectOne("com.ningpai.dao.GroupLabelMapper.selectByPrimaryKey", groupLabelId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public int updateByPrimaryKeySelective(Label label) {
        return this.update("com.ningpai.dao.GroupLabelMapper.updateByPrimaryKeySelective", label);
    }

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-10-17 17:37:27
     */
    public int updateByPrimaryKey(Label label) {
        return this.update("com.ningpai.dao.GroupLabelMapper.updateByPrimaryKey", label);
    }
    /**
     * 查询所有的小组标签
     *
     * @return list
     */
    public List<Label> allLabel() {
        return this.selectList("com.ningpai.dao.GroupLabelMapper.selectAll");
    }

    /**
     * 小组标签分页列表
     *
     * @param paramMap
     *            查询参数{@link java.util.Map}
     * @return list
     */
    public List<Object> labelList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.dao.GroupLabelMapper.labellist", paramMap);
    }

    /**
     * 小组标签数目
     *
     * @param selectBean
     * @return
     */
    public int labelCount(SelectBean selectBean) {
        return this.selectOne("com.ningpai.dao.GroupLabelMapper.labelcount", selectBean);
    }

    /**
     * 根据标签Id删除标签
     *
     * @param labelId
     *            标签Id{@link java.lang.Long}
     * @return
     */
    public int deleteLabel(Long labelId) {
        return this.update("com.ningpai.dao.GroupLabelMapper.deletelabel", labelId);
    }

    /**
     * 操作小组标签(停用or恢复)
     *
     * @param paramMap
     *            条件参数{@link java.util.Map}
     * @return
     */
    public int operation(Map<String, Object> paramMap) {
        return this.update("com.ningpai.dao.GroupLabelMapper.operationlabel", paramMap);
    }

    /**
     * 验证小组标签是否存在
     *
     * @param labelName
     *            小组标签名称{@linnk java.lang.String}
     * @return 对象
     */
    public Label checkName(String labelName) {
        return this.selectOne("com.ningpai.dao.GroupLabelMapper.checkLabelName", labelName);
    }

}
