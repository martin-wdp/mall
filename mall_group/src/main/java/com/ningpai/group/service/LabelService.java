package com.ningpai.group.service;

import com.ningpai.group.bean.Label;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

import java.util.List;

/**
 * 标签Service
 * @author ggn
 *
 */
public interface LabelService {

    /**
     * 分页查询小组标签列表
     * 
     * @param pb
     * @param selectBean
     * @return pagebean
     */
    PageBean findByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 添加小组标签
     * 
     * @param label
     *            标签对象{@link com.ningpai.group.bean.Label}
     * @return int
     */
    int insertLabel(Label label);

    /**
     * 修改小组标签
     * 
     * @param label
     *            标签对象{@link com.ningpai.group.bean.Label}
     * @return int
     */
    int updateLabel(Label label);

    /**
     * 操作小组标签
     * 
     * @param groupLabelIds
     * @return
     */
    int operationLabel(Long[] groupLabelIds, String labelStatus);

    /**
     * 根据小组标签ID查询小组标签
     * 
     * @param groupLabelId
     * @return
     */
    Label selectByLabelId(Long groupLabelId);

    /**
     * 查询所有的小组标签
     * 
     * @return
     */
    List<Label> selectAll();

    /**
     * 检验小组标签是否存在
     * 
     * @param labelName
     * @return 对象
     */
    Label checkLabelName(String labelName);

}
