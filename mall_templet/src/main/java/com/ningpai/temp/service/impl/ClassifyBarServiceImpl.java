/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ningpai.temp.service.impl;

import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.dao.ClassifyBarCateMapper;
import com.ningpai.temp.dao.ClassifyBarMapper;
import com.ningpai.temp.dao.ClassifyBarQuickMapper;
import com.ningpai.temp.service.ClassifyBarService;
import com.ningpai.temp.vo.ClassifyBarVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SERVICE实现类-分类导航
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:15:46
 */
@Service("ClassifyBarService")
public class ClassifyBarServiceImpl implements ClassifyBarService {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(ClassifyBarServiceImpl.class);

    private static final String TEMP1 = "temp1";

    /* 数据访问层依赖 */
    @Resource(name = "ClassifyBarMapper")
    private ClassifyBarMapper classifyBarMapper;

    @Resource(name = "ClassifyBarCateMapper")
    private ClassifyBarCateMapper barCateMapper;

    @Resource(name = "ClassifyBarQuickMapper")
    private ClassifyBarQuickMapper barQuickMapper;

    @Resource(name = "ChannelGoodsCateService")
    private GoodsCateService goodsCateService;

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#deleteClassifyBar(java.lang
     * .Long)
     */
    @Override
    public int deleteClassifyBar(Long classifyBarId) {
        try {
            return classifyBarMapper.deleteByPrimaryKey(classifyBarId);
        } catch (Exception e) {
            LOGGER.error("删除分类导航错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#saveClassifyBar(com.ningpai
     * .temp.bean.ClassifyBar)
     */
    @Override
    public int saveClassifyBar(ClassifyBar record) {
        try {
            // 大Boss分类导航
            if (record.getTemp1() == null && record.getGoodsCatId() > -1) {
                // 是否自定义分类导航
                    // 获取当前商品分类
                    GoodsCate cate = this.goodsCateService.selectGoosCateById(record.getGoodsCatId());
                    if (record.getGrade() == 2) {
                        // 获取一级商品分类
                        String cateOne = cate.getCatParentId().toString();
                        record.setTemp3(cateOne);
                        // 获取第一个三级商品分类
                        List<GoodsCate> list = this.goodsCateService.queryGoosCateByParentId(cate.getCatId());
                        if (!list.isEmpty()) {
                            String cateThird = list.get(0).getCatId().toString();
                            record.setTemp4(cateThird);

                        } else {
                            record.setTemp4(cate.getCatId().toString());
                        }
                    } else if (record.getGrade() == 3) {
                        // 获取一级商品分类
                        String cateOne = this.goodsCateService.selectGoosCateById(cate.getCatParentId()).getCatParentId().toString();
                        record.setTemp3(cateOne);
                    }
            }
            Date date = new Date();
            record.setCreateDate(date);
            record.setUpdateDate(date);
            record.setDelflag("0");
            return classifyBarMapper.insertSelective(record);
        } catch (Exception e) {
            LOGGER.error("添加分类导航错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#updateClassifyBar(com.ningpai
     * .temp.bean.ClassifyBar)
     */
    @Override
    public int updateClassifyBar(ClassifyBar record) {
        try {
            // 大Boss分类导航
            if (record.getTemp1() == null && record.getGoodsCatId() > -1) {
                // 是否自定义分类导航
                    // 获取当前商品分类
                    GoodsCate cate = this.goodsCateService.selectGoosCateById(record.getGoodsCatId());
                    if (record.getGrade() == 2) {
                        // 获取一级商品分类
                        String cateOne = cate.getCatParentId().toString();
                        record.setTemp3(cateOne);
                        // 获取第一个三级商品分类
                        List<GoodsCate> list = this.goodsCateService.queryGoosCateByParentId(cate.getCatId());
                        if (!list.isEmpty()) {
                            String cateThird = list.get(0).getCatId().toString();
                            record.setTemp4(cateThird);

                        } else {
                            record.setTemp4(cate.getCatId().toString());
                        }
                    } else if (record.getGrade() == 3) {
                        // 获取一级商品分类
                        String cateOne = this.goodsCateService.selectGoosCateById(cate.getCatParentId()).getCatParentId().toString();
                        record.setTemp3(cateOne);
                    }
            }
            record.setUpdateDate(new Date());
            return classifyBarMapper.updateByPrimaryKeySelective(record);
        } catch (Exception e) {
            LOGGER.error("修改分类导航错误:", e);
            return 0;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#getClassifyBarById(java.lang
     * .Long)
     */
    @Override
    public ClassifyBar getClassifyBarById(Long classifyBarId) {
        ClassifyBar classifyBar = null;
        try {
            classifyBar = classifyBarMapper.selectByPrimaryKey(classifyBarId);
        } catch (Exception e) {
            LOGGER.error("根据ID查询分类导航错误:", e);
        }
        return classifyBar;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#selectClassifyBarByParam(
     * com.ningpai.util.PageBean, java.lang.Long, java.lang.Long)
     */
    @Override
    public PageBean selectClassifyBarByParam(PageBean pb, Long tempId, Long channelId, String thirdId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("channelId", channelId);
            map.put("tempId", tempId);
            map.put(TEMP1, thirdId);
            pb.setRows(classifyBarMapper.selectClassifyBarCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(classifyBarMapper.selectClassifyBarByParam(map));
            return pb;
        } catch (Exception e) {
            LOGGER.error("分页查询分类导航列表错误:", e);
            return null;
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#selectClassifyBarByParamSite
     * (java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public List<ClassifyBarVo> selectClassifyBarByParamSite(Long tempId, Long channelId, String thirdId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("channelId", channelId);
            map.put("tempId", tempId);
            map.put(TEMP1, thirdId);
            return classifyBarMapper.selectClassifyBarByParamSite(map);
        } catch (Exception e) {
            LOGGER.error("分页查询分类导航列表错误:", e);
            return Collections.emptyList();
        }
    }

    /*
     *
     *
     * @see
     * com.ningpai.temp.service.ClassifyBarService#selectClassifyBarByParamSite
     * (java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public List<ClassifyBarVo> selectClassifyBarByParamSite2(Long tempId, Long channelId, String thirdId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("channelId", channelId);
            map.put("tempId", tempId);
            map.put(TEMP1, thirdId);
            return classifyBarMapper.selectClassifyBarByParamSite2(map);
        } catch (Exception e) {
            LOGGER.error("分页查询分类导航列表错误:", e);
            return Collections.emptyList();
        }
    }

    /*
     *
     *
     * @see
     * com.ningpai.temp.service.ClassifyBarService#selectClassifyBarByParamSite
     * (java.lang.Long, java.lang.Long, java.lang.Long)
     */
    @Override
    public List<ClassifyBarVo> getIndexClassificationByfir(Long tempId) {
        try {

            return classifyBarMapper.getIndexClassificationByfir(tempId);
        } catch (Exception e) {
            LOGGER.error("分页查询分类导航列表错误:", e);
            return Collections.emptyList();
        }
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#saveClassifyBarAndCateAndQuick
     * (com.ningpai.temp.bean.ClassifyBar,
     * com.ningpai.temp.bean.ClassifyBarCate,
     * com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int saveClassifyBarAndCateAndQuick(ClassifyBar record, List<ClassifyBarCate> barCates, List<ClassifyBarQuick> barQuicks) {
        int count = 0;
        try {
            // 保存分类导航
            count = classifyBarMapper.insertSelective(record);
            if (null != barCates && !barCates.isEmpty()) {
                for (ClassifyBarCate barCate : barCates) {
                    // 保存分类导航关联商品分类
                    barCate.setClassifybarId(record.getClassifyBarId());
                    // 获取第二级子商品分类集合
                    List<GoodsCate> twolist = this.goodsCateService.queryGoosCateByParentId(barCate.getCateId());
                    if (!twolist.isEmpty()) {
                        // 获取第一个二级子商品分类
                        GoodsCate cateTwo = twolist.get(0);
                        // 获取第三级子商品分类集合
                        List<GoodsCate> thirdlist = this.goodsCateService.queryGoosCateByParentId(cateTwo.getCatId());
                        if (!thirdlist.isEmpty()) {
                            // 获取第一个三级子商品分类
                            GoodsCate cateThird = thirdlist.get(0);
                            // 设置分类导航关联商品分类的第一个三级子商品分类
                            barCate.setTemp1(cateThird.getCatId().toString());
                        } else {
                            // 设置分类导航关联商品分类的第一个三级子商品分类为它的第一个二级子商品分类
                            barCate.setTemp1(cateTwo.getCatId().toString());
                        }
                    } else {
                        // 设置分类导航关联商品分类的第一个三级子商品分类为它自己的商品分类
                        barCate.setTemp1(record.getClassifyBarId().toString());
                    }
                    barCate.setClassifybarId(record.getClassifyBarId());
                    barCateMapper.insertSelective(barCate);
                }
            }
            if (null != barQuicks && !barQuicks.isEmpty()) {
                for (ClassifyBarQuick barQuick : barQuicks) {
                    // 保存分类导航关联快捷分类
                    // 获取当前商品分类
                    GoodsCate cate = this.goodsCateService.selectGoosCateById(barQuick.getCateId());
                    // 获取一级商品分类Id
                    if (cate.getCatParentId() != null) {
                        Long cateId = this.goodsCateService.selectGoosCateById(cate.getCatParentId()).getCatParentId();
                        barQuick.setTemp1(cateId.toString());
                    }
                    barQuick.setClassifybarId(record.getClassifyBarId());
                    barQuickMapper.insertSelective(barQuick);
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
            count = 0;
        }
        return count;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#updateClassifyBarAndCateAndQuick
     * (com.ningpai.temp.bean.ClassifyBar,
     * com.ningpai.temp.bean.ClassifyBarCate,
     * com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int updateClassifyBarAndCateAndQuick(ClassifyBar record, List<ClassifyBarCate> barCates, List<ClassifyBarQuick> barQuicks) {
        int count = 0;
        try {
            // 删除原关联的商品分类和快捷分类
            barCateMapper.batchDeleteByClassifyBarId(record.getClassifyBarId());
            barQuickMapper.batchDeleteByClassifyBarId(record.getClassifyBarId());
            // 更新分类导航
            count = classifyBarMapper.updateByPrimaryKeySelective(record);
            if (null != barCates && !barCates.isEmpty()) {
                for (ClassifyBarCate barCate : barCates) {
                    // 保存分类导航关联商品分类
                    barCate.setClassifybarId(record.getClassifyBarId());
                    // 获取第二级子商品分类集合
                    List<GoodsCate> twolist = this.goodsCateService.queryGoosCateByParentId(barCate.getCateId());
                    if (!twolist.isEmpty()) {
                        // 获取第一个二级子商品分类
                        GoodsCate cateTwo = twolist.get(0);
                        // 获取第三级子商品分类集合
                        List<GoodsCate> thirdlist = this.goodsCateService.queryGoosCateByParentId(cateTwo.getCatId());
                        if (!thirdlist.isEmpty()) {
                            // 获取第一个三级子商品分类
                            GoodsCate cateThird = thirdlist.get(0);
                            // 设置分类导航关联商品分类的第一个三级子商品分类
                            barCate.setTemp1(cateThird.getCatId().toString());
                        } else {
                            // 设置分类导航关联商品分类的第一个三级子商品分类为它的第一个二级子商品分类
                            barCate.setTemp1(cateTwo.getCatId().toString());
                        }
                    } else {
                        // 设置分类导航关联商品分类的第一个三级子商品分类为它自己的商品分类
                        barCate.setTemp1(record.getClassifyBarId().toString());
                    }

                    barCate.setClassifybarId(record.getClassifyBarId());
                    barCateMapper.insertSelective(barCate);
                }
            }
            if (null != barQuicks && !barQuicks.isEmpty()) {
                for (ClassifyBarQuick barQuick : barQuicks) {
                    if (barQuick.getCateId() == 0) {
                        barQuick.setTemp1(barQuick.getCateId().toString());
                    } else {
                        // 保存分类导航关联快捷分类
                        // 获取当前商品分类
                        GoodsCate cate = this.goodsCateService.selectGoosCateById(barQuick.getCateId());
                        // 获取一级商品分类Id
                        Long cateId = this.goodsCateService.selectGoosCateById(cate.getCatParentId()).getCatParentId();
                        barQuick.setTemp1(cateId.toString());
                    }

                    barQuick.setClassifybarId(record.getClassifyBarId());
                    barQuickMapper.insertSelective(barQuick);
                }
            }
        } catch (Exception e) {
            LOGGER.error("", e);
            count = 0;
        }
        return count;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#deleteClassifyBarAndCateAndQuick
     * (com.ningpai.temp.bean.ClassifyBar,
     * com.ningpai.temp.bean.ClassifyBarCate,
     * com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int deleteClassifyBarAndCateAndQuick(Long classifyBarId) {
        int count = 0;
        try {
            // 删除分类导航
            count = classifyBarMapper.deleteByPrimaryKey(classifyBarId);
            // 删除关联的商品分类和快捷分类
            barCateMapper.batchDeleteByClassifyBarId(classifyBarId);
            barQuickMapper.batchDeleteByClassifyBarId(classifyBarId);
        } catch (Exception e) {
            LOGGER.error("", e);
            count = 0;
        }
        return count;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#deleteByPrimaryKeyAndPro(
     * java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyAndPro(Long classifyBarId) {
        return classifyBarMapper.deleteByPrimaryKeyAndPro(classifyBarId);
    }

    /*
     * 删除店铺分类导航
     * 
     * @see
     * com.ningpai.temp.service.ClassifyBarService#deleteClassBarById(java.lang
     * .Long, java.lang.Long)
     */
    @Override
    public int deleteClassBarById(Long classBarId, Long thirdId) {
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("classifyBarId", classBarId);
            map.put(TEMP1, thirdId.toString());
            return classifyBarMapper.deleteClassBarById(map);
        } catch (Exception e) {
            LOGGER.error("删除分类导航错误:", e);
            return 0;
        }
    }

}
