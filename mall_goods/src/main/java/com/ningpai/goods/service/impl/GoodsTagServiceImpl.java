/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTagMapper;
import com.ningpai.goods.service.GoodsTagService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 商品标签业务层实现类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午2:32:27
 * @version
 */
@Service("GoodsTagServiceImpl")
public class GoodsTagServiceImpl implements GoodsTagService {
    @Resource(name = "GoodsTagMapperImpl")
    // 注入标签数据层实现类
    // 标签的数据层
    private GoodsTagMapper goodsTagMapper;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTagServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTagMapper getGoodsTagMapper() {
        return goodsTagMapper;
    }

    public void setGoodsTagMapper(GoodsTagMapper goodsTagMapper) {
        this.goodsTagMapper = goodsTagMapper;
    }

    /**
     * 根据标签ID查询标签实体
     *
     * @param tagId
     *            {@link java.lang.Long}
     * @return 查询到的标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     */
    public GoodsTag selectByPaimarykey(Long tagId) {
        return this.goodsTagMapper.selectByPrimaryKey(tagId);
    }

    /**
     * 查询所有的标签
     *
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     *         {@link java.util.List}
     * @see {@link com.ningpai.goods.bean.GoodsTag}
     */
    public PageBean selectAllTag(PageBean pageBean, SelectBean selectBean) {
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        //设置总行数
        pageBean.setRows(this.goodsTagMapper.queryTotalCount(selectBean));
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            //设置查询条件
            map.put(ValueUtil.CONDITION, selectBean.getCondition());
            map.put(ValueUtil.SEARCHTEXT, selectBean.getSearchText());
            //设置集合数据
            pageBean.setList(this.goodsTagMapper.selectAllTag(map));

        } finally {
            map = null;
        }
        //返回结果
        return pageBean;
    }

    /**
     * 根据主键删除
     *
     * @param tagId
     *            标签ID
     * @param delName
     *            删除人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int deleteByPrimaryKey(Long tagId, String delName) {
        //定义一个Hashmap 集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            // 封装参数
            map.put("tagId", tagId.toString());
            map.put("del_name", delName);
            //根据主键删除
            return this.goodsTagMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELETEGOODSTAG + delName);
            this.cascDelMapper.cascDel(delName);
            map = null;
        }
    }

    /**
     * 插入一个商品标签(可选属性)
     *
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int insertSelective(GoodsTag record) {
        // 设置创建人名称 因该为登陆用户
        record.setTagCreateName("admin");
        // 设置删除标记为未删除
        record.setTagDelflag("0");
        //打印日志
        LOGGER.info(ValueUtil.INSERTTAG);
        //插入一个商品标签
        return this.goodsTagMapper.insertSelective(record);
    }

    /**
     * 批量删除商品标签
     *
     * @param tagIds
     *            需要删除的标签ID
     * @param delName
     *            删除人名称 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchDeleteTag(Long[] tagIds, String delName) {
        // 受影响的行数
        int count = 0;
        // 把参数封装为Map
        Map<String, String> map = new HashMap<String, String>();
        try {
            for (int i = 0; i < tagIds.length; i++) {
                map.put("tagId", tagIds[i].toString());
                map.put("del_name", delName);
                //根据主键删除
                count += this.goodsTagMapper.deleteByPrimaryKey(map);
                map.clear();
            }
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.BATCHDELETETAG + delName);
            this.cascDelMapper.cascDel(delName);
            map = null;
        }
        return count;
    }

    /**
     * 根据条件模糊查询
     *
     * @param columnName
     *            模糊查询的列名
     * @param paramvalue
     *            模糊查询的值
     * @return {@link com.ningpai.goods.bean.GoodsTag} {@link java.util.List}
     */
    public List<GoodsTag> queryTagByParam(String columnName, String paramvalue) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放入map集合中
            map.put("columnName", columnName);
            map.put("paramvalue", "'%" + paramvalue + "%'");
        } finally {
            map = null;
        }
        //根据条件模糊查询
        return this.goodsTagMapper.queryTagByParam(map);
    }

    /**
     * 更新商品标签
     *
     * @param tag
     *            {@link com.ningpai.goods.bean.GoodsTag}
     *            {@link java.lang.Integer}
     * @param username
     *            修改人名称
     * @return
     */
    @Transactional
    public int updateTagSelective(GoodsTag tag, String username) {
        // 修改人名称 应该为当前登陆用户
        tag.setTagModifiedName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATETAGSELECTIVE + username);
        //更新商品标签
        return this.goodsTagMapper.updateByPrimaryKeySelective(tag);
    }

    /**
     * 查询所有的商品标签
     *
     * @return 查询到的标签列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTag}
     */
    public List<Object> queryAllTag() {
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //设置开始行数
            map.put(ValueUtil.STARTROWNUM, 0);
            //设置结束行数
            map.put(ValueUtil.ENDROWNUM,
                    this.goodsTagMapper.queryTotalCount(null));
            //查询所有的商品标签
            return this.goodsTagMapper.selectAllTag(map);
        } finally {
            map = null;
        }
    }

    /**
     * 验证标签名称是否已经存在
     *
     * @param tagName
     *            待验证的标签名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkTagName(String tagName) {
        //根据标签名称查询行数
        return this.goodsTagMapper.queryByTagName(tagName) > 0 ? false : true;
    }

}
