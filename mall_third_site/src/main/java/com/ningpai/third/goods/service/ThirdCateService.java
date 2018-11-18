/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service;

import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.vo.ThirdCateVo;
import com.ningpai.util.SelectBean;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * <p>第三方商品分类</p>
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月6日 上午11:46:06
 * @version 2.0
 */
public interface ThirdCateService {
    /**
     * 添加一个第三方分类
     * 
     * @param thirdCate
     *            待添加的第三方分类
     * @param username
     *            操作人ID
     * @return 插入的行数
     */
    int insertThirdCate(ThirdCate thirdCate, String username);

    /**
     * 删除第三方商家分类
     * 
     * @param catId
     *            待删除的分类ID
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    int delThirdCate(Long catId, String username);

    /**
     * 新删除第三方商家分类
     * @param catId
     * @param username
     * @param thirdId
     * @return
     */
    int delThirdCateNew(Long catId, String username,Long thirdId);

    /**
     * 批量删除分类
     * 
     * @param catIds
     *            待删除的分类数组
     * @param username
     *            操作人名称
     */
    int batchDelThirdCate(Long[] catIds, String username);

    /**
     * 更新第三方店铺分类信息
     * 
     * @param thirdCate
     *            待更新的第三方店铺分类
     * @param username
     *            操作人名称
     * @return 更新的行数
     */
    int updateThirdCate(ThirdCate thirdCate, String username);

    /**
     * 根据分类ID查询分类信息
     * 
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     */
    ThirdCateVo queryThirdCateById(Long catId);

    /**
     * 查询所有的分类信息
     * 
     * @return 查询到的集合
     */
    List<ThirdCate> queryAllCate(Long thirdId);

    /**
     * 查询所有的第三方分类Vo集合
     * 
     * @return 查询到的集合
     */
    List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map);

    /**
     * 验证是否可以删除,如果传递过来的分类ID下的子分类的数量不等于空返回false表示不可删除
     * 
     * @param cateId
     *            待验证的分类ID
     * @return 是否可以删除的标记
     */
    boolean checkDelWithCateId(Long cateId);

    /**
     * 根据分类名称查询分类信息
     * 
     * @param cateName
     *            分类名称
     * @return 查询到的分类信息
     */
    ThirdCate queyCateByCateName(String cateName, Long thirdId);

    /**
     * 获取第三方分类列表
     * 
     * @param selectBean
     *            查询参数Bean
     * @param thirdId
     *            第三方店铺的ID
     * @return 计算好的分类集合
     */
    List<Object> getAllCalcThirdCate(SelectBean selectBean, Long thirdId);

    /**
     * 计算第三方店家的分类关系
     * 
     * @param parentId
     *            父分类ID
     * @param allCateList
     *            所有的分类的集合
     * @return 计算好的分类集合
     */
    List<ThirdCateVo> calcCateVo(Long parentId, List<ThirdCateVo> allCateList);

    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合
     * 
     * @param catId
     *            父分类ID
     * @param thirdId
     *            第三方ID
     * @return 查询到的集合
     */
    List<ThirdCate> getThirdCateByParentId(Long catId, Long thirdId);

    /**
     * 根据父分类ID和第三方ID查询所有的子分类集合
     *
     * @param thirdId
     *            第三方ID
     * @return 查询到的集合
     */
    List<ThirdCate> getThirdCateByParentIdtwo(Long thirdId);

    /**
     * 根据分类分类名称,第三方ID,层级标记查询分类集合
     * 
     * @param catName
     *            分类名称
     * @param thirdId
     *            第三方ID
     * @return 查询到的分类集合
     */
    List<ThirdCate> getThirdCateByCateNameAndGrade(String catName, Long thirdId);

    /**
     * 把最近使用的记录保存到cookie中
     * 
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @param thirdCate
     *            带保存到cookie中的数据
     * @throws java.io.UnsupportedEncodingException
     */
    void saveToCookie(ThirdCateVo thirdCate, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 从cookie中取出最近使用的记录
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return 取出的分类集合
     * @throws java.io.UnsupportedEncodingException
     */
    List<ThirdCate> takeFormCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;

    /**
     * 清除最近使用的cookie记录
     * 
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return 是否删除成功 true 删除成功 false 删除失败
     */
    boolean clearThirdCateFromCookie(HttpServletRequest request, HttpServletResponse response);
    
    /**
     * 根据父分类id，分类名称和商家id查询子分类信息
     * 
     * @param thirdCateName
     *            分类名称
     * @return 查询到的分类信息
     */
    List<ThirdCate> queryThirdSonCateByCateIdAndName(Long thirdCateId, String thirdCateName, Long thirdId);
    
    

}
