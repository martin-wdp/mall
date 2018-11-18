/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service;

import java.util.List;

import com.ningpai.information.bean.InformationType;
import com.ningpai.information.vo.InformationTypeVo;
import com.ningpai.util.PageBean;

/**
 * 资讯类型SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月14日 17:18:35
 * @version
 */
public interface ThirdInforTypeService {
    /**
     * 根据主键删除文章栏目
     * 
     * @param infoTypeId
     *            文章栏目编号
     * @param loginUserId
     *            用户编号
     */
    void delInformation(Long infoTypeId, Long loginUserId);

    /**
     * 批量删除资讯类型
     * 
     * @param ids
     *            资讯类型编号数组
     * @param loginUserId
     *            用户编号
     */
    void batchDelInformation(Long[] ids, Long loginUserId);

    /**
     * 添加资讯类型
     * 
     * @param record
     *            栏目对象
     * @param thirdId
     *            第三方商家ID
     */
    void saveInformation(InformationType record, String thirdId);

    /**
     * 更新资讯类型
     * 
     * @param record
     *            资讯类型对象
     * @return
     */
    void updateInformation(InformationType record);

    /**
     * 根据主键查找资讯类型
     * 
     * @param infoTypeId
     *            资讯类型编号
     * @return InformationType 资讯类型对象
     */
    InformationType selectByPrimaryKey(Long infoTypeId);

    /**
     * 根据分页参数和第三方商家ID查询资讯类型列表
     * 
     * @param pb
     *            分页参数
     * @param typeName
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @return
     */
    PageBean queryByPageBean(PageBean pb, String typeName, String thirdId);

    /**
     * 根据第三方商家ID查找所有的资讯类型
     * 
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯类型List集合
     */
    List<InformationTypeVo> selectAllByThirdId(String thirdId);

    /**
     * 根据第三方商家ID查找所有可发布文章的栏目<br/>
     * 
     * @param thirdId
     *            第三方商家ID
     * @return List<InformationTypeVo> 资讯栏目VO List集合
     */
    List<InformationType> selectInfoTypeByAttr(String thirdId);

    /**
     * 判断资讯类型是否可删除
     * 
     * @param infoTypeId
     *            资讯类型编号
     * @return 是否可以删除 false表示不可以删除
     */
    boolean checkDelWithInfoTypeId(Long infoTypeId);

    /**
     * 获得资讯类型下的所有的子类型
     * 
     * @param parentId
     * @return
     */
    List<InformationTypeVo> getInfoTypeList(Long parentId);

    /**
     * 根据第三方商家ID和栏目名称验证是否可添加
     * 
     * @param name
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @return
     */
    boolean checkAddInfoTypeByName(String name, String thirdId);

    /**
     * 修改栏目-根据栏目名称和栏目ID验证是否可修改
     * 
     * @param name
     *            栏目名称
     * @param thirdId
     *            第三方商家ID
     * @param infoTypeId
     *            栏目ID
     * @return
     */
    boolean checkAddInfoTypeByName(String name, String thirdId, Long infoTypeId);
}
