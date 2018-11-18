/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsSpecMapper;
import com.ningpai.goods.service.GoodsSpecDetailService;
import com.ningpai.goods.service.GoodsSpecService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsSpecVo;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 商品规格Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月20日 上午9:48:53
 * @version
 */
@Service("GoodsSpecService")
public class GoodsSpecServiceImpl implements GoodsSpecService {
    // 商品分类的DAO
    private GoodsSpecMapper goodsSpecMapper;
    // 商品规格值Service
    private GoodsSpecDetailService goodsSpecDetailService;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsSpecServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsSpecMapper getGoodsSpecMapper() {
        return goodsSpecMapper;
    }

    @Resource(name = "GoodsSpecMapper")
    public void setGoodsSpecMapper(GoodsSpecMapper goodsSpecMapper) {
        this.goodsSpecMapper = goodsSpecMapper;
    }

    public GoodsSpecDetailService getGoodsSpecDetailService() {
        return goodsSpecDetailService;
    }

    @Resource(name = "GoodsSpecDetailService")
    public void setGoodsSpecDetailService(
            GoodsSpecDetailService goodsSpecDetailService) {
        this.goodsSpecDetailService = goodsSpecDetailService;
    }

    /**
     * 保存商品规格
     *
     * @param goodsSpec
     *            商品规格对象 {@link com.ningpai.goods.bean.GoodsSpec}
     * @param detailnames
     *            详细值得名称数组
     * @param specDetailNicknames
     *            详细值得别名数组
     * @param specDetailImgs
     *            详细值得图片数组
     * @param specDetailSorts
     *            详细值得排序数组
     * @param username
     *            操作的用户名称
     * @return 影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int saveGoodsSpec(GoodsSpec goodsSpec, String[] detailnames,
            String[] specDetailNicknames, String[] specDetailImgs,
            String[] specDetailSorts, String username) {
        // 创建一个规格值集合
        List<GoodsSpecDetail> specDetails = new ArrayList<GoodsSpecDetail>();
        GoodsSpecDetail specDetail = null;
        for (int i = 0; i < detailnames.length; i++) {
            specDetail = new GoodsSpecDetail();
            specDetail.setSpecDetailName(detailnames[i]);
            // specDetail.setSpecDetailNickname(specDetailNicknames[i]);
            // if (null != specDetailImgs[i]) {
            // specDetail.setSpecDetailImg(specDetailImgs[i]);
            // }
            specDetail.setSpecDetailSort(Integer.parseInt(specDetailSorts[i]));
            specDetails.add(specDetail);
        }
        // 设置规格的创建人名称为传递过来的名称
        goodsSpec.setSpecCreateName(username);
        // 设置新添加的规格的删除标记为0
        goodsSpec.setSpecDelflag(ValueUtil.DEFAULTDELFLAG);
        // 保存商品规格并获得返回插入的规格的ID
        Long insertCount = this.goodsSpecMapper.insertSelective(goodsSpec);
        Long newSpecId = null;
        if (insertCount > 0) {
            newSpecId = this.goodsSpecMapper.selectLastId();
        }
        // 创建商品规格值迭代值
        GoodsSpecDetail goodsSpecDetail = null;
        try {
            // 循环遍历规格值数组并插入
            for (int i = 0; i < specDetails.size(); i++) {
                goodsSpecDetail = specDetails.get(i);
                // 设置规格ID为先前插入的规格ID
                goodsSpecDetail.setSpecId(newSpecId);
                // 设置删除标记为未删除
                goodsSpecDetail.setSpecDetailDelflag(ValueUtil.DEFAULTDELFLAG);
                // 添加规格值
                this.goodsSpecDetailService.saveSpecDetail(goodsSpecDetail,
                        username);
            }
            // 如果不出现错误，就返回1
            return 1;
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.SAVEGOODSSPEC + username);
            // 参数置空
            insertCount = null;
            newSpecId = null;
            goodsSpecDetail = null;
            specDetails = null;
            specDetail = null;
        }
    }

    /**
     * 删除商品分类
     *
     * @param specId
     *            商品分类ID{@link java.lang.Long}
     * @param username
     *            删除人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsSpec(Long specId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把用户名放进map集合
            map.put("delName", username);
            //把规格值放进map集合中
            map.put("specId", specId.toString());
            //根据主键删除商品规格
            return this.goodsSpecMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELGOODSSPEC + username);
            this.cascDelMapper.cascDel(username);
            // 参数真空
            map = null;
        }
    }

    /**
     * 批量删除商品规格信息
     *
     * @param specIds
     *            规格ID数组 {@link java.lang.Long}
     * @param username
     *            操作的用户名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDelGoodsSpec(Long[] specIds, String username) {
        Integer count = 0;
        try {
            // 循环取出需要删除的规格的值，然后用单个删除的方法循环删除
            for (int i = 0; i < specIds.length; i++) {
                count += delGoodsSpec(specIds[i], username);
            }
            //返回结果
            return count;
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.BATCHDELGOODSSPEC + username);
            this.cascDelMapper.cascDel(username);
            // 参数指控
            count = null;
        }
    }

    /**
     * 更新商品规格信息
     *
     * @param goodsSpec
     *            需要更新的商品规格的实体{@link com.ningpai.goods.bean.GoodsSpec}
     * @param specDetails
     *            更新商品规格下的所有的规格值 {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @param username
     *            执行操作的用户名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsSpec(GoodsSpec goodsSpec,
            List<GoodsSpecDetail> specDetails, String username) {
        goodsSpec.setSpecModifiedName(username);
        // 创建迭代规格值
        GoodsSpecDetail goodsSpecDetail = null;
        // 封装删除的参数
        try {
            // 循环遍历所有的规格值
            for (int i = 0; i < specDetails.size(); i++) {
                goodsSpecDetail = specDetails.get(i);
                // 如果规格值的ID不为-1，说明该规格值已经存在，则更新，否则就是插入
                if (goodsSpecDetail.getSpecDetailId() != -1) {
                    // 如果传递过来的规格值的删除标记为1，则进行删除
                    if (goodsSpecDetail.getSpecDetailDelflag().equals(
                            ValueUtil.ALREADYDELFLAG)) {
                        // 调用规格值得Service方法进行删除
                        this.goodsSpecDetailService.delSpecDetail(
                                goodsSpecDetail.getSpecDetailId(), username);
                    } else {
                        //更新规格值
                        this.goodsSpecDetailService.updateSpecDetail(
                                goodsSpecDetail, username);
                    }
                } else {
                    // 给规格值设置规格ID
                    goodsSpecDetail.setSpecId(goodsSpec.getSpecId());
                    // 如果规格值的ID为-1就设置为空
                    if (goodsSpecDetail.getSpecDetailId() == -1) {
                        goodsSpecDetail.setSpecDetailId(null);
                    }
                    // 设置删除标记为0
                    goodsSpecDetail
                            .setSpecDetailDelflag(ValueUtil.DEFAULTDELFLAG);
                    //添加规格值
                    this.goodsSpecDetailService.saveSpecDetail(goodsSpecDetail,
                            username);
                }
            }
            //更新规格
            return this.goodsSpecMapper.updateByPrimaryKeySelective(goodsSpec);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.UPDATEGOODSSPEC + username);
            // 参数置空
            goodsSpecDetail = null;
        }
    }

    /**
     * 根据主键查询商品规格信息
     *
     * @param specId
     *            规格ID
     * @return 查询到的商品规格Vo的实体 {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    public GoodsSpecVo queryBySpecPrimaryKey(Long specId) {
        //根据规格ID查询Vo
        return this.goodsSpecMapper.querySpecVoBySpecId(specId);
    }

    /**
     * 根据分页辅助Bean查询规格的分页列表
     *
     * @param pb
     *            分页帮助类 {@link com.ningpai.util.PageBean}
     * @return 塞进列表的分页辅助类 {@link com.ningpai.util.PageBean}
     */
    public PageBean qyerySpecListByPageBean(PageBean pb) {
        // 首先设置所有的行数
        pb.setRows(this.goodsSpecMapper.queryTotalCount());
        //定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
            //设置数据集合
            pb.setList(this.goodsSpecMapper.queryListByPageBean(map));
        } finally {
            // 参数指控
            map = null;
        }
        //返回结果
        return pb;
    }

    /**
     * 把传递过来的数组转换为规格值集合
     *
     * @param specDetailIds
     *            所有的规格值ID数组
     * @param specDetailDelflag
     *            所有的规格值的删除标记
     * @param specDetailName
     *            所有的规格值得名称
     * @param specDetailNickname
     *            所有的规格值得别名
     * @param specDetailImg
     *            所有的规格值的图片
     * @param specDetailSort
     *            所有的规格值的排序
     * @return 整理好的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsSpecDetail}
     */
    @Transactional
    public List<GoodsSpecDetail> changeSpecDetail(String[] specDetailIds,
            String[] specDetailDelflag, String[] specDetailName,
            String[] specDetailNickname, String[] specDetailImg,
            String[] specDetailSort) {
        //定义一个规格集合
        List<GoodsSpecDetail> detailList = new ArrayList<GoodsSpecDetail>();
        GoodsSpecDetail specDetail = null;
        for (int i = 0; i < specDetailIds.length; i++) {
            specDetail = new GoodsSpecDetail();
            //设置规格id
            specDetail.setSpecDetailId(Long.parseLong(specDetailIds[i]));
            //设置规格删除标记
            specDetail.setSpecDetailDelflag(specDetailDelflag[i]);
            //设置规格名称
            specDetail.setSpecDetailName(specDetailName[i]);
            // specDetail.setSpecDetailNickname(specDetailNickname[i]);
            // specDetail.setSpecDetailImg(specDetailImg[i]);
            specDetail.setSpecDetailSort(Integer.parseInt(specDetailSort[i]));
            detailList.add(specDetail);
        }
        //返回结果
        return detailList;
    }

    /**
     * 查询所有的商品规格
     *
     * @return 查询到的规格列表
     */
    public List<GoodsSpec> queryAllSpec() {
        //查询所有的商品规格
        return this.goodsSpecMapper.queryAllSpec();
    }

    /**
     * 根据商品ID查询关联的规格Vo
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    public List<GoodsSpecVo> querySpecVoByGoodsId(Long goodsId) {
        //根据商品ID查询关联的规格VO
        return this.goodsSpecMapper.querySpecVoByGoodsId(goodsId);
    }

    /**
     * 查询所有的商品规格包含删除的
     *
     * @return 查询到的规格列表
     */
    public List<GoodsSpec> queryAllSpecIncludeDel() {
        //查询所有的商品规格包含删除的
        return this.goodsSpecMapper.queryAllSpecIncludeDel();
    }
    /**
     * 参数查询
     *
     * @param pageBean
     * @param selectBean
     * @return PageBean
     */
    public PageBean searchSpecListByPageBean(PageBean pageBean,
            SelectBean selectBean) {
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        //分页查询总行数
        pageBean.setRows(this.goodsSpecMapper.searchTotalCount(selectBean));
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            //设置条件参数
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            //设置集合数据
            pageBean.setList(this.goodsSpecMapper.searchAllSpec(map));

        } finally {
            map = null;
        }
        //返回结果
        return pageBean;
    }

    /**
     * 验证规格名称是否可用
     *
     * @param specName
     *            待验证的规格名称 {@link java.lang.String}
     * @return 可用返回true 不可用返回false
     */
    public boolean checkSpecName(String specName) {
        //根据规格名称查询行数
        return this.goodsSpecMapper.queryCountBySpecName(specName) > 0 ? false
                : true;
    }

    /**
     * 保存规格信息
     *
     * @param goodsSpec
     *            规格信息
     * @param username
     *            用户名
     */
    @Override
    public void saveGoodsSpec(GoodsSpec goodsSpec, String username) {
        // 设置规格的创建人名称为传递过来的名称
        goodsSpec.setSpecDelflag("0");
        goodsSpec.setSpecCreateName(username);
        //插入规格记录
        this.goodsSpecMapper.insert(goodsSpec);
    }

    /**
     * 修改规格信息
     *
     * @param goodsSpec
     *            规格信息
     * @param username
     *            用户名
     */
    @Override
    public void updateGoodsSpec(GoodsSpec goodsSpec, String username) {
        goodsSpec.setSpecModifiedName(username);
        //更新规格
        this.goodsSpecMapper.updateByPrimaryKeySelective(goodsSpec);
    }

}
