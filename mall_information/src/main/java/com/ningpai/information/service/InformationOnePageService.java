package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 资讯单页Service
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:35:10
 * @version
 */
public interface InformationOnePageService {
    /**
     * 根据主键删除
     * 
     * @param
     * @return
     */
    int delInfoOnePage(Long infoOPId, Long userId);

    /**
     * 批量删除资讯单页
     * 
     * @param ids
     *            资讯单页编号数组
     * @return
     */
    void batchDelInfoOnePage(Long[] ids, Long userId);

    /**
     * 添加资讯单页
     * 
     * @param record
     * @return
     */
    int saveInfoOnePage(InformationOnePage record);

    /**
     * 更新资讯单页
     * 
     * @param record
     * @return
     */
    int updateInfoOnePage(InformationOnePage record);

    /**
     * 根据主键查询
     * 
     * @param
     * @return
     */
    InformationOnePage getInfoOnePageByID(Long infoOPId);

    /**
     * 根据分页参数查询资讯单页分页数据
     * 
     * @param pb
     * @param selectBean
     * @return
     */
    PageBean queryInfoOnePageByPageBean(PageBean pb, SelectBean selectBean);

    /**
     * 根据单页标题查询单页数量判断标题是否存在
     * 
     * @param title
     * @return
     */
    boolean checkAddInfoOPByTitle(String title);

    /**
     * 根据单页标题查询单页数量判断标题是否存在<br/>
     * 根据单页ID查询出文章标题，判断老标题和新标题是否一样<br>
     * 如果一样直接返回true，不一样查询数量判断是否存在
     * 
     * @param title
     * @param infoOPId
     *            单页ID
     * @return
     */
    boolean checkAddInfoOPByTitle(String title, Long infoOPId);

    /**
     * 根据模板ID和标签ID查询单页
     * 
     * @param tempId
     *            模板ID
     * @param infoOPTagId
     *            标签ID
     * @return
     */
    List<InformationOnePage> selectInfoOPByTempAndTag(Long tempId, Long infoOPTagId);

    /**
     * 根据单页标签查询单页数量，判断是否可删除标签
     * 
     * @param infoOPTagId
     * @return
     */
    Integer selectInfoOPCountByTag(Long infoOPTagId);
}
