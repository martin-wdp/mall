/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.gift.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.gift.bean.GiftCate;
import com.ningpai.gift.dao.GiftCateMapper;
import com.ningpai.gift.service.GiftCateService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * @author ggn 赠品分类service实现类
 */
@Service("GiftCateService")
public class GiftCateServiceImpl implements GiftCateService {

    private GiftCateMapper giftCateMapper;

    @Override
    public List<GiftCate> searchGiftCateList(Long giftCateId) {
        return giftCateMapper.searchGiftCateList(giftCateId);
    }

    /**
     * 查询赠品分类列表
     * 
     * @see com.ningpai.gift.service.GiftCateService#searchGiftCateList(com.ningpai.gift.bean.GiftCate,
     *      com.ningpai.util.PageBean)
     */
    @Override
    public PageBean searchGiftCateList(GiftCate giftCate, PageBean pageBean) {
        giftCate.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(giftCate);
        // 查询总数
        pageBean.setRows(giftCateMapper.searchGiftCateListCount(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        no = no == 0 ? 1 : no;
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            pageBean.setObjectBean(giftCate);
        }
        // 查询条件封装
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 查询列表页
            List<GiftCate> parentList = giftCateMapper.searchGiftParentCateList(paramMap);

            List<GiftCate> allCateList = giftCateMapper.searchGiftCateList();
            List<Object> cateVoList = new ArrayList<Object>();
            GiftCate cate = null;
            GiftCate cateVo = null;
            try {
                // 根据父分类集合循环调用计算的方法，整理出二叉树
                for (int i = 0; i < parentList.size(); i++) {
                    // 从父分类中取出每一个分类
                    cate = (GiftCate) parentList.get(i);
                    // 创建最上级的分类，并把属性转移给这个分类
                    cateVo = new GiftCate();
                    cateVo.setGiftCateId(cate.getGiftCateId());
                    cateVo.setGiftCateName(cate.getGiftCateName());
                    cateVo.setGiftParentId(cate.getGiftParentId());
                    cateVo.setGiftCateRemark(cate.getGiftCateRemark());
                    // 根据取得的父分类ID和所有的分类列表计算分类实体，并添加到需要返回的集合中
                    cateVo.setCateVos(this.calcCateVo(cateVo.getGiftCateId(), allCateList));
                    cateVoList.add(cateVo);
                }
                // 返回整理好的分类集合
                pageBean.setList(cateVoList);
                return pageBean;
            } finally {
                // 置空所有参数
                cateVoList = null;
                parentList = null;
                allCateList = null;
                cate = null;
                cateVo = null;
            }
        } finally {
            paramMap = null;
        }
    }

    /**
     * 得到不带分页的积分商品分类
     * 
     * @param giftCate
     * @return List
     */
    @Override
    public List<Object> searchGiftCateListNoPage(GiftCate giftCate) {
        giftCate.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(giftCate);
        // 查询条件封装,为不和老版本冲突设为null即不带分页
        paramMap.put("start", null);
        paramMap.put("number", null);
        try {
            // 查询列表页
            List<GiftCate> parentList = giftCateMapper.searchGiftParentCateList(paramMap);

            List<GiftCate> allCateList = giftCateMapper.searchGiftCateList();
            List<Object> cateVoList = new ArrayList<Object>();
            GiftCate cate = null;
            GiftCate cateVo = null;
            try {
                // 根据父分类集合循环调用计算的方法，整理出二叉树
                for (int i = 0; i < parentList.size(); i++) {
                    // 从父分类中取出每一个分类
                    cate = (GiftCate) parentList.get(i);
                    // 创建最上级的分类，并把属性转移给这个分类
                    cateVo = new GiftCate();
                    cateVo.setGiftCateId(cate.getGiftCateId());
                    cateVo.setGiftCateName(cate.getGiftCateName());
                    cateVo.setGiftParentId(cate.getGiftParentId());
                    cateVo.setGiftCateRemark(cate.getGiftCateRemark());
                    // 根据取得的父分类ID和所有的分类列表计算分类实体，并添加到需要返回的集合中
                    cateVo.setCateVos(this.calcCateVo(cateVo.getGiftCateId(), allCateList));
                    cateVoList.add(cateVo);
                }
                // 返回整理好的分类集合

                return cateVoList;
            } finally {
                // 置空所有参数
                cateVoList = null;
                parentList = null;
                allCateList = null;
                cate = null;
                cateVo = null;
            }
        } finally {
            paramMap = null;
        }
    }

    /**
     * 获取分类
     * 
     * @param parentId
     * @param allCateList
     * @return calcCateVo
     */
    public List<GiftCate> calcCateVo(Long parentId, List<GiftCate> allCateList) {
        List<GiftCate> cateVoList = new ArrayList<GiftCate>();
        // 需要返回的分类实体
        GiftCate cateVo = null;
        // 返回的分类实体的所有子分类
        List<GiftCate> allSonCate = null;
        // 循环中的迭代分类
        GiftCate cate = null;
        try {
            // 进行递归
            for (int i = 0; i < allCateList.size(); i++) {
                if (parentId.equals(allCateList.get(i).getGiftParentId())) {
                    // 必须在这里new对象 否则会覆盖原先的数据
                    cateVo = new GiftCate();
                    cate = allCateList.get(i);
                    cateVo.setGiftCateId(cate.getGiftCateId());
                    cateVo.setGiftCateName(cate.getGiftCateName());
                    cateVo.setGiftParentId(cate.getGiftParentId());
                    cateVo.setGiftCateRemark(cate.getGiftCateRemark());
                    // 执行递归
                    allSonCate = calcCateVo(cate.getGiftCateId(), allCateList);
                    cateVo.setCateVos(allSonCate);
                    cateVoList.add(cateVo);
                }
            }
            // 返回计算好的所有的子分类列表
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            cateVo = null;
            allSonCate = null;
            cate = null;
        }
    }

    /**
     * 查询根据ID
     *
     */
    @Override
    public GiftCate searchGiftCateById(Long giftCateId) {
        return giftCateMapper.searchGiftCateById(giftCateId);
    }

    /**
     * 添加
     *
     */
    @Override
    public int addGiftCate(GiftCate giftCate) {
        if (giftCate.getGiftParentId() == 0) {
            giftCate.setGiftCateRemark("1");
        } else {
            String grade = searchGiftCateById(giftCate.getGiftParentId()).getGiftCateRemark();
            int grade1 = Integer.parseInt(grade) + 1;
            giftCate.setGiftCateRemark(String.valueOf(grade1));
        }
        giftCate.setModifyTime(new Date());
        giftCate.setCreateTime(new Date());
        giftCate.setDelFlag("0");
        return giftCateMapper.addGiftCate(giftCate);
    }

    /**
     * 修改
     * 
     */
    @Override
    public int updateGiftCate(GiftCate giftCate) {
        if (giftCate.getGiftParentId() == 0) {
            giftCate.setGiftCateRemark("1");
        } else {
            String grade = searchGiftCateById(giftCate.getGiftParentId()).getGiftCateRemark();
            int grade1 = Integer.parseInt(grade) + 1;
            giftCate.setGiftCateRemark(String.valueOf(grade1));
        }
        giftCate.setModifyTime(new Date());
        giftCate.setDelFlag("0");
        return giftCateMapper.updateGiftCate(giftCate);
    }

    /**
     * 删除
     * 
     * @see com.ningpai.gift.service.GiftCateService#delGiftCate(java.lang.Long)
     */
    @Override
    public int delGiftCate(Long giftCateId) {
        return giftCateMapper.delGiftCate(giftCateId);
    }

    /**
     * 批量删除
     * 
     * @see com.ningpai.gift.service.GiftCateService#delAllGiftCate(java.lang.Long[])
     */
    @Override
    public int delAllGiftCate(Long[] giftCateId) {
        List<Long> list = new ArrayList<Long>();
        for (Long cateId : giftCateId) {
            list.add(cateId);
        }
        return giftCateMapper.delAllGiftCate(list);
    }

    /**
     * 查询列表
     * 
     * @see com.ningpai.gift.service.GiftCateService#searchGiftCateList()
     */
    @Override
    public List<GiftCate> searchGiftCateList() {
        return giftCateMapper.searchGiftCateList();
    }

    /**
     * 查询列表 select使用
     * 
     * @see com.ningpai.gift.service.GiftCateService#selectGiftListUseSelect()
     */
    @Override
    public List<GiftCate> selectGiftListUseSelect() {
        return giftCateMapper.selectGiftListUseSelect();
    }

    /**
     * 判断
     * 
     * @see com.ningpai.gift.service.GiftCateService#checkDelGiftCate(java.lang.Long)
     */
    @Override
    public boolean checkDelGiftCate(Long cateId) {

        return giftCateMapper.querySonCateByParentId(cateId) > 0 ? false : true;
    }

    public GiftCateMapper getGiftCateMapper() {
        return giftCateMapper;
    }

    // Spring注入
    @Resource(name = "GiftCateMapper")
    public void setGiftCateMapper(GiftCateMapper giftCateMapper) {
        this.giftCateMapper = giftCateMapper;
    }

}
