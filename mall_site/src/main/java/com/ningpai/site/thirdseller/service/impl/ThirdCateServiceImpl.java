package com.ningpai.site.thirdseller.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.ningpai.site.thirdseller.dao.ThirdCateMapper;
import com.ningpai.site.thirdseller.service.ThirdCateService;
import com.ningpai.site.thirdseller.vo.ThirdCateVo;

/**
 * 第三方店铺分类Service实现
 * 
 * @author qiyuanyuan
 *
 */
@Service("ThirdCateService")
public class ThirdCateServiceImpl implements ThirdCateService {

    private static final String THIRDID = "thirdId";
    private static final String CATID = "catId";

    private ThirdCateMapper thirdCateMapper;

    @Override
    public Long selectByCustomerId(Long customerId) {
        return thirdCateMapper.selectByCustomerId(customerId);
    }

    /**
     * 
     */
    @Override
    public List<ThirdCateVo> getAllCalcThirdCate(Long thirdId) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put(THIRDID, thirdId);
        // 创建需要返回的集合
        List<ThirdCateVo> cateVoList = new ArrayList<ThirdCateVo>();
        // 查询所有的分类的集合
        List<ThirdCateVo> parentList = this.thirdCateMapper.queryAllThirdCate(map);
        // 查询所有的分类信息
        List<ThirdCateVo> allCateList = this.thirdCateMapper.queryAllCateForList(thirdId);
        ThirdCateVo cate;
        ThirdCateVo cateVo;
        if (null != parentList && !parentList.isEmpty()) {
            // 循环父分类并进行整理
            for (int i = 0; i < parentList.size(); i++) {
                // 从父分类中取出每一个分类
                cate = parentList.get(i);
                cateVo = new ThirdCateVo();
                cateVo.setCatGrade(cate.getCatGrade());
                cateVo.setCatId(cate.getCatId());
                cateVo.setCatImg(cate.getCatImg());
                cateVo.setCatName(cate.getCatName());
                cateVo.setCatParentId(cate.getCatParentId());
                cateVo.setCatSort(cate.getCatSort());
                cateVo.setThirdId(cate.getThirdId());
                cateVo.setThirdName(cate.getThirdName());
                cateVo.setCatCreateTime(cate.getCatCreateTime());
                // 根据取得的父分类ID和所有的分类列表计算分类实体,并添加到需要返回的对象中
                cateVo.setCateVos(this.calcCateVo(cateVo.getCatId(), allCateList));
                cateVoList.add(cateVo);
            }
        }
        // 返回整理好的集合
        return cateVoList;

    }

    @Override
    public String findStoreFlag(Long thirdId) {
        return thirdCateMapper.findStoreFlag(thirdId);
    }

    /**
     * 
     */
    @Override
    public List<ThirdCateVo> calcCateVo(Long parentId, List<ThirdCateVo> allCateList) {
        List<ThirdCateVo> cateVoList = new ArrayList<ThirdCateVo>();
        // 需要返回的分类实体
        ThirdCateVo cateVo;
        // 循环中的迭代分类
        ThirdCateVo cate;
        // 进行递归
        for (int i = 0; i < allCateList.size(); i++) {
            if (parentId.equals(allCateList.get(i).getCatParentId())) {
                // 从父分类中取出每一个分类
                cate = allCateList.get(i);
                cateVo = new ThirdCateVo();
                cateVo.setCatGrade(cate.getCatGrade());
                cateVo.setCatId(cate.getCatId());
                cateVo.setCatImg(cate.getCatImg());
                cateVo.setCatName(cate.getCatName());
                cateVo.setCatParentId(cate.getCatParentId());
                cateVo.setCatSort(cate.getCatSort());
                cateVo.setThirdId(cate.getThirdId());
                cateVo.setThirdName(cate.getThirdName());
                cateVo.setCatCreateTime(cate.getCatCreateTime());
                // 根据取得的父分类ID和所有的分类列表计算分类实体,并添加到需要返回的对象中
                cateVo.setCateVos(this.calcCateVo(cateVo.getCatId(), allCateList));
                cateVoList.add(cateVo);
            }
        }
        // 返回计算好的所有的子分类列表
        return cateVoList;
    }

    /**
     * 
     */
    @Override
    public List<ThirdCateVo> calcCateVo(Long thirdId, Long cateId) {
        List<ThirdCateVo> cateVoRes = null;
        if (thirdId == null || cateId == null) {
            return new ArrayList<ThirdCateVo>();
        }
        List<ThirdCateVo> allCates = queryAllThirdCate(thirdId);
        if (allCates == null || allCates.isEmpty()) {
            return new ArrayList<ThirdCateVo>();
        }
        cateVoRes = calcCateVo(cateId, allCates);
        cateVoRes.add(0, thirdCateMapper.selectByPrimaryKey(cateId));
        return cateVoRes;
    }

    /**
     * 
     */
    @Override
    public List<ThirdCateVo> queryAllThirdCate(Long thirdId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(THIRDID, thirdId);
        return thirdCateMapper.queryAllThirdCate(paramMap);
    }

    /**
     * 
     */
    @Override
    public ThirdCateVo queryThirdCateById(Long cateId) {

        ThirdCateVo thirdCateVo = null;
        thirdCateVo = thirdCateMapper.selectByPrimaryKey(cateId);
        thirdCateVo.setParentThirdCateVo(thirdCateMapper.selectByPrimaryKey(thirdCateVo.getCatParentId()));
        return thirdCateVo;
    }

    /**
     * 
     */
    @Override
    public ThirdCateVo queryThirdCateByPraentCateId(Long parentId, Long thirdId) {
        ThirdCateVo thirdCateVo = thirdCateMapper.selectByPrimaryKey(parentId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(THIRDID, thirdId);
        paramMap.put(CATID, parentId);
        if (thirdCateVo != null) {
            thirdCateVo.setCateVos(this.thirdCateMapper.queryThirdCateByParentCateId(paramMap));
        }
        return thirdCateVo;
    }

    @Resource(name = "ThirdCateMapper")
    public void setThirdCateMapper(ThirdCateMapper thirdCateMapper) {
        this.thirdCateMapper = thirdCateMapper;
    }

}
