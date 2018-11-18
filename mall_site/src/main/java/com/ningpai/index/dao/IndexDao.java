package com.ningpai.index.dao;

import java.util.List;

/**
 * index接口
 * @author Songhl
 */
public interface IndexDao {
    /**
     * 通过id获取楼层
     * @param id 编号
     * @return 集合
     */
    List<Long> getStoreys(Long id);
}
