package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.util.PageBean;

/**
 * SERVICE-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日下午1:29:26
 */
public interface InfoOPTagService {
    /**
     * 根据主键删除
     * 
     * @param infoOPTagId
     * @return
     */
    int deleteInfoOPTagById(Long infoOPTagId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int createInfoOPTag(InfoOPTag record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateInfoOPTag(InfoOPTag record);

    /**
     * 根据模板ID查询
     * 
     * @return
     */
    List<InfoOPTag> findAllInfoOPTag(String tempId);

    /**
     * 查询所有
     * 
     * @return
     */
    List<InfoOPTag> findAllTag();

    /**
     * 分页查询
     * @param pb
     * @return
     */
    PageBean findAllTagPage(PageBean pb);
}
