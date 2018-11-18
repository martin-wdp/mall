/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service.impl;

import com.ningpai.goods.bean.GoodsSpec;
import com.ningpai.goods.bean.GoodsTypeSpec;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsTypeSpecMapper;
import com.ningpai.goods.service.GoodsSpecService;
import com.ningpai.goods.service.GoodsTypeSpecService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsTypeSpecVo;
import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品类型关联商品规格Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午9:20:16
 * @version 1.0
 */
@Service("GoodsTypeSpecService")
public class GoodsTypeSpecServiceImpl implements GoodsTypeSpecService {
    private GoodsTypeSpecMapper goodsTypeSpecMapper;
    private GoodsSpecService goodsSpecService;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsTypeSpecServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsSpecService getGoodsSpecService() {
        return goodsSpecService;
    }

    @Resource(name = "GoodsSpecService")
    public void setGoodsSpecService(GoodsSpecService goodsSpecService) {
        this.goodsSpecService = goodsSpecService;
    }

    public GoodsTypeSpecMapper getGoodsTypeSpecMapper() {
        return goodsTypeSpecMapper;
    }

    @Resource(name = "GoodsTypeSpecMapper")
    public void setGoodsTypeSpecMapper(GoodsTypeSpecMapper goodsTypeSpecMapper) {
        this.goodsTypeSpecMapper = goodsTypeSpecMapper;
    }

    /**
     * 增加一条记录
     *
     * @param goodsTypeSpec
     *            实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 插入的条数 {@link java.lang.Integer}
     */
    @Transactional
    public int saveTypeSpec(GoodsTypeSpec goodsTypeSpec, String username) {
        goodsTypeSpec.setTypeSpecCreateName(username);
        //打印日志
        LOGGER.info(ValueUtil.SAVETYPESPEC + username);
        //执行添加方法
        return this.goodsTypeSpecMapper.insertSelective(goodsTypeSpec);
    }

    /**
     * 删除一条记录
     *
     * @param typeSpecId
     *            主键ID {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 删除的条数 {@link java.lang.Integer}
     */
    @Transactional
    public int delTypeSpec(Long typeSpecId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //设置参数
            map.put("delName", username);
            map.put("typeSpecId", typeSpecId.toString());
            //执行删除方法
            return this.goodsTypeSpecMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELTYPESPEC + username);
            this.cascDelMapper.cascDel(username);
            map = null;
        }
    }

    /**
     * 更新记录
     *
     * @param goodsTypeSpec
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @param username
     *            操作人名称
     * @return 更新的条数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateTypeSpec(GoodsTypeSpec goodsTypeSpec, String username) {
        goodsTypeSpec.setTypeSpecModifiedName(username);
        //打印日志
        LOGGER.info(ValueUtil.UPDATETYPESPEC + username);
        //执行修改方法
        return this.goodsTypeSpecMapper
                .updateByPrimaryKeySelective(goodsTypeSpec);
    }

    /**
     * 根据类型ID查询商品类型关联的规格VO
     *
     * @param typeId
     * @return
     */
    public List<GoodsTypeSpecVo> queryTypeSpecByTypeId(Long typeId) {
        //执行查询方法
        return this.goodsTypeSpecMapper.queryTypeSpecBytypeId(typeId);
    }

    /**
     * 批量更新商品类型关联规格
     *
     * @param typeId
     *            商品类型的iD {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param specIds
     *            类型ID {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int batchUpdate(Long typeId, String username, String[] specIds) {
        // 临时变量
        GoodsTypeSpec typeSpec = null;
        Map<String, Long> map = new HashMap<String, Long>();
        // 查询所有的规格
        List<GoodsSpec> specList = this.goodsSpecService
                .queryAllSpecIncludeDel();
        // 获取所有的商品规格的ID
        List<Long> allspecsIds = new ArrayList<Long>();
        if(specList!=null&&!specList.isEmpty()){
        for (int i = 0; i < specList.size(); i++) {
            allspecsIds.add(specList.get(i).getSpecId());
        }
        }
        if (null != specIds && specIds.length > 0) {
            // 循环传递过来的品牌ID的数组，并查询对象，执行相应的操作
            for (int i = 0; i < specIds.length; i++) {
                map.put(ValueUtil.TYPEID, typeId);
                map.put(ValueUtil.SPECID, Long.parseLong(specIds[i]));
                typeSpec = this.goodsTypeSpecMapper
                        .queryTypeSpecByTypeIdAndSpecId(map);
                if (null == typeSpec) {
                    typeSpec = new GoodsTypeSpec();
                    typeSpec.setSpecId(Long.parseLong(specIds[i]));
                    typeSpec.setTypeSpecDelflag(ValueUtil.DEFAULTDELFLAG);
                    typeSpec.setTypeId(typeId);
                    saveTypeSpec(typeSpec, username);
                } else if ("1".equals(typeSpec.getTypeSpecDelflag())) {
                    typeSpec.setTypeSpecDelflag(ValueUtil.DEFAULTDELFLAG);
                    updateTypeSpec(typeSpec, username);
                }
            }
            for (int i = 0; i < allspecsIds.size(); i++) {
                for (int k = 0; k < specIds.length; k++) {
                    // 如果包含 就移除，剩下的就是为选中的
                    if (allspecsIds.get(i) == Long.parseLong(specIds[k])) {
                        allspecsIds.remove(i);
                    }
                }
            }
        }
        //打印日志
        LOGGER.info(ValueUtil.BATCHUPDATETYPESPEC + username);
        // 删除所有的关联记录
        delAllreleSpec(typeId, username, map, allspecsIds);
        return 0;
    }

    /**
     * 如果未选中任何规格值 就删除所有已经关联的
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @param map
     *            封装参数 {@link java.util.Map}
     * @param allspecsIds
     *            所有的规格ID
     */
    @Transactional
    private void delAllreleSpec(Long typeId, String username,
            Map<String, Long> map, List<Long> allspecsIds) {
        GoodsTypeSpec typeSpec;
        // 如果为空 ，就循环所有的进行删除
        for (int i = 0; i < allspecsIds.size(); i++) {
            map.put(ValueUtil.TYPEID, typeId);
            map.put(ValueUtil.SPECID, allspecsIds.get(i));
            //执行查询方法
            typeSpec = this.goodsTypeSpecMapper
                    .queryTypeSpecByTypeIdAndSpecId(map);
            if (null != typeSpec) {
                delTypeSpec(typeSpec.getTypeSpecId(), username);
            }
        }
        //打印日志
        LOGGER.info(ValueUtil.DELALLRELESPEC + username);
        this.cascDelMapper.cascDel(username);
    }

}
