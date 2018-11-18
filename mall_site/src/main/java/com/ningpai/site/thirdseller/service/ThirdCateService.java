package com.ningpai.site.thirdseller.service;

import java.util.List;

import com.ningpai.site.thirdseller.vo.ThirdCateVo;

/**
 * 第三方店铺分类Service实现
 * @author qiyuanyuan
 *
 */
public interface ThirdCateService {

    /**
     * 根据id获取单个店铺的信息
     * @param customerId
     * @return
     */
    Long selectByCustomerId(Long customerId);
     /**
     * 查询所有的分类信息
     * 
     * @return 查询到的集合
     */
    List<ThirdCateVo> getAllCalcThirdCate(Long thirdId);

    /**
     * 查询该商家是否被删除
     * @param thirdId
     * @return
     */
    String findStoreFlag(Long thirdId);
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
     * 根据第三方ID。分类ID 递归得到所有子级分类集合
     * @param thirdId
     * @return List<ThirdCateVo>
     */
    List<ThirdCateVo> calcCateVo(Long thirdId,Long cateId);
    
    /**
     * 查询所有 第三方分类
     * @param thirdId
     * @return List<ThirdCateVo>
     */
    List<ThirdCateVo> queryAllThirdCate(Long thirdId);
    
    /**
     * 据类型ID查询VO信息,仅是查询当前分类本身以及父分类
     * @param cateId
     *          分类Id
     * @return 查询到的Vo信息
     */
    ThirdCateVo queryThirdCateById(Long cateId);
    
    /**
     * 根据父类id查询子级分类
     * 
     * @param parentId
     *             父分类Id
     * @return 查询到的Vo信息
     */
    ThirdCateVo queryThirdCateByPraentCateId(Long parentId,Long thirdId);
}
