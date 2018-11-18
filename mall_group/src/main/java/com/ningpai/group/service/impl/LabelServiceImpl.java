package com.ningpai.group.service.impl;

import com.ningpai.group.bean.Label;
import com.ningpai.group.dao.LabelMapper;
import com.ningpai.group.service.LabelService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台小组标签service接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Service("LabelService")
public class LabelServiceImpl implements LabelService {

    private LabelMapper labelMapper;

    /**
     * 分页查询小组标签列表
     *
     * @param pb
     * @param selectBean
     * @return pagebean
     */
    public PageBean findByPageBean(PageBean pb, SelectBean selectBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        int rows = this.labelMapper.labelCount(selectBean);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        map.put("condition", selectBean.getCondition());
        map.put("searchText", selectBean.getSearchText());
        pb.setList(labelMapper.labelList(map));
        return pb;
    }

    /**
     * 添加小组标签
     *
     * @param label
     *            标签对象{@link com.ningpai.group.bean.Label}
     * @return int
     */
    @Transactional
    public int insertLabel(Label label) {
        label.setGroupLabelCreateTime(new Date());
        label.setGroupLabelDelFlag("0");
        return this.labelMapper.insertSelective(label);
    }

    /**
     * 修改小组标签
     *
     * @param label
     *            标签对象{@link com.ningpai.group.bean.Label}
     * @return int
     */
    @Transactional
    public int updateLabel(Label label) {
        label.setGroupLabelModifyTime(new Date());
        return this.labelMapper.updateByPrimaryKeySelective(label);
    }

    /**
     * 操作小组标签
     *
     * @param groupLabelIds
     * @return
     */
    @Transactional
    public int operationLabel(Long[] groupLabelIds, String labelStatus) {
        //
        int flag = 1;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupLabelStatus", labelStatus);
        for (Long groupLabel : groupLabelIds) {
            paramMap.put("groupLabelId", groupLabel);
            paramMap.put("groupLabelModifyTime", new Date());
            flag += this.labelMapper.operation(paramMap);
        }
        return flag;
    }

    /**
     * 根据小组标签ID查询小组标签
     *
     * @param groupLabelId
     * @return
     */
    public Label selectByLabelId(Long groupLabelId) {
        return this.labelMapper.selectByPrimaryKey(groupLabelId);
    }

    /**
     * 查询所有的小组标签
     *
     * @return
     */
    public List<Label> selectAll() {
        return this.labelMapper.allLabel();
    }

    /**
     * 检验小组标签是否存在
     *
     * @param labelName
     * @return 对象
     */
    public Label checkLabelName(String labelName) {
        return this.labelMapper.checkName(labelName);
    }

    public LabelMapper getLabelMapper() {
        return labelMapper;
    }

    @Resource(name = "LabelMapper")
    public void setLabelMapper(LabelMapper labelMapper) {
        this.labelMapper = labelMapper;
    }

}
