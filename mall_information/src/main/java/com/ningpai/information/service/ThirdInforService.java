/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.Information;
import com.ningpai.information.vo.InformationVo;
import com.ningpai.util.PageBean;

/**
 * 资讯SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:22:35
 * @version
 */
public interface ThirdInforService {
    /**
     * 根据主键删除资讯
     * 
     * @param infoId
     *            资讯编号
     * @return
     */
    int delInfo(Long infoId);

    /**
     * 批量删除资讯
     * 
     * @param ids
     *            资讯类型编号数组
     * @return
     */
    void batchDelInfo(Long[] ids);

    /**
     * 添加资讯
     * 
     * @param record
     *            资讯对象
     * @return
     */
    int saveInfo(Information record);

    /**
     * 更新资讯
     * 
     * @param record
     * @return
     */
    int updateInfo(Information record);

    /**
     * 根据主键查找资讯
     * 
     * @param infoId
     *            资讯编号
     * @return Information 资讯类对象
     */
    Information selectByPrimaryKey(Long infoId);

    /**
     * 查找所有的资讯
     * 
     * @return List<Information> 资讯集合
     */
    List<InformationVo> selectAll(String thirdId);

    /**
     * 根据分页参数查询资讯列表<br/>
     * 
     * @param pb
     *            分页工具类
     * @param searchText
     *            查询工具类
     * @param typeId
     *            类型id
     * @param thirdId
     *            类型id
     * @return
     */
    PageBean queryByPageBean(PageBean pb, String searchText, Long typeId, String thirdId);

    /**
     * 根据栏目ID获取文章数量验证栏目是否能删除
     * 
     * @param infoTypeId
     * @return
     */
    boolean checkDelInfoTypeByInfoCount(Long infoTypeId);

    /**
     * 根据文章标题查询文章数量判断标题是否存在
     * 
     * @param title
     * @return
     */
    boolean checkAddInfoByTitle(String title, String thirdId);

    /**
     * 根据文章标题查询文章数量判断标题是否存在<br/>
     * 根据文章ID查询出文章标题，判断老标题和新标题是否一样<br>
     * 如果一样直接返回true，不一样查询数量判断是否存在
     * @param title
     * @param infoId
     *            文章ID
     * @return
     */
    boolean checkAddInfoByTitle(String title, String thirdId, Long infoId);

    /**
     * 根据栏目ID查询文章
     * 
     * @param infoTypeId
     * @return
     */
    List<InformationVo> selectByInfoType(Long infoTypeId);
}
