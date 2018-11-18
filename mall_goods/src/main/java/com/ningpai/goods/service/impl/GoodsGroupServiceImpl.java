/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.goods.service.impl;

import com.ningpai.goods.bean.GoodsGroup;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsGroupMapper;
import com.ningpai.goods.service.GoodsGroupService;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsGroupReleProductVo;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.system.dao.StockWarningMapper;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品组合Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月31日 下午3:58:11
 * @version 1.0
 */
@Service("GoodsGroupService")
public class GoodsGroupServiceImpl implements GoodsGroupService {
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(GoodsGroupServiceImpl.class);

    // 商品组合DAO
    private GoodsGroupMapper goodsGroupMapper;
    private CascDelMapper cascDelMapper;

    @Resource(name = "StockWarningMapper")
    private StockWarningMapper stockWarningMapper;

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsGroupMapper getGoodsGroupMapper() {
        return goodsGroupMapper;
    }

    @Resource(name = "GoodsGroupMapper")
    public void setGoodsGroupMapper(GoodsGroupMapper goodsGroupMapper) {
        this.goodsGroupMapper = goodsGroupMapper;
    }

    /**
     * 保存商品组合
     *
     * @param goodsGroup
     *            商品组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @param username
     *            操作人名称
     * @return 插入的最新ID {@link java.lang.Long}
     */
    @Transactional
    public Long saveGoodsGroup(GoodsGroup goodsGroup, String username) {
        goodsGroup.setGroupCreateName(username);
        // 如果选择的是特惠套装,就设置优惠价格为null
        if ("1".equals(goodsGroup.getGroupPrefertype())) {
            goodsGroup.setGroupPreferamount(BigDecimal.valueOf(0.0));
        }
        // 打印日志
        LOGGER.info(ValueUtil.SAVEGOODSGROUP + username);
        // 保存商品组合
        return this.goodsGroupMapper.insertSelective(goodsGroup);
    }

    /**
     * 删除商品组合
     *
     * @param goodsGroupId
     *            商品组合ID {@link java.lang.Long }
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsGroup(Long goodsGroupId, String username) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("delName", username);
            map.put("groupId", goodsGroupId.toString());
            // 删除商品组合
            return this.goodsGroupMapper.deleteByPrimaryKey(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODSGROUP + username);
            this.cascDelMapper.cascDel(username);
        }
    }

    /**
     * 新删除商品组合
     * 
     * @param goodsGroupId
     * @param username
     * @param thirdId
     * @return
     */
    @Transactional
    public int delGoodsGroupNew(Long goodsGroupId, String username, Long thirdId) {
        // 定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            map.put("delName", username);
            map.put("groupId", goodsGroupId.toString());
            map.put("thirdId", thirdId.toString());
            // 删除商品组合
            return this.goodsGroupMapper.deleteByPrimaryKeyNew(map);
        } finally {
            // 打印日志
            LOGGER.info(ValueUtil.DELGOODSGROUP + username);
            this.cascDelMapper.cascDel(username);
        }
    }

    /**
     * 更新商品组合信息
     *
     * @param goodsGroup
     *            待更新的商品组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     * @param username
     *            操作人名称
     * @return 更新的行数
     */
    @Transactional
    public int updateGoodsGroup(GoodsGroup goodsGroup, String username) {
        goodsGroup.setGroupModifiedName(username);
        // 如果选择的是特惠套装就设置优惠价格为null
        if ("1".equals(goodsGroup.getGroupPrefertype())) {
            goodsGroup.setGroupPreferamount(BigDecimal.valueOf(0.00));
        }
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSGROUP + username);
        // 更新商品组合信息
        return this.goodsGroupMapper.updateByPrimaryKeySelective(goodsGroup);
    }

    /**
     * 根据组合ID查询商品组合实体
     *
     * @param goodsGroupId
     *            组合ID {@link java.lang.Long}
     * @return 查询到的组合实体{@link com.ningpai.goods.bean.GoodsGroup}
     */
    public GoodsGroup queryGoodsGroupByPrimaryKey(Long goodsGroupId) {
        // 根据组合id查询商品组合实体
        return this.goodsGroupMapper.selectByPrimaryKey(goodsGroupId);
    }

    /**
     * 根据pageBean分页查询
     *
     * @param pb
     *            分页辅助类{@link com.ningpai.util.PageBean}
     * @return 已经进行分页的PageBean
     */
    public PageBean queryGoodsGroupByPageBean(PageBean pb) {
        // 定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        // 设置总行数
        pb.setRows(this.goodsGroupMapper.queryTotalAcount());
        map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
        map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
        // 设置数据
        pb.setList(this.goodsGroupMapper.queryAllByPageBean(map));

        return pb;
    }

    /**
     * 批量删除商品组合
     *
     * @param groupIds
     *            待删除的商品组合的ID数组
     * @param username
     *            操作人名称
     * @return 删除的行数
     */
    @Transactional
    public int batchDelGroup(HttpServletRequest request, Long[] groupIds, String username) {
        Integer count = 0;
        try {
            if (null != groupIds && groupIds.length > 0) {
                for (int i = 0; i < groupIds.length; i++) {
                    // 判断是否是第三方
                    Long thirdId = (Long) request.getSession().getAttribute("thirdId");
                    if (thirdId != null && thirdId > 0) {
                        // 组合信息
                        GoodsGroupVo goodsGroupVo = this.queryVoByPrimaryKey(groupIds[i]);
                        // 判断权限
                        if (goodsGroupVo != null && goodsGroupVo.getThirdId().equals(thirdId)) {
                            // 批量删除商品组合
                            count += delGoodsGroupNew(groupIds[i], username, thirdId);
                        }
                    } else {
                        // 批量删除商品组合
                        count += delGoodsGroupNew(groupIds[i], username, Long.valueOf(0));
                    }

                }
            }
            return count;
        } finally {
            // 打印日志
            LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>批量删除商品组合by" + username);
            this.cascDelMapper.cascDel(username);
        }
    }

    /**
     * 根据主键ID查询VO信息
     *
     * @param groupId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的详细信息 {@link com.ningpai.goods.vo.GoodsGroupVo}
     */
    public GoodsGroupVo queryVoByPrimaryKey(Long groupId) {
        return this.goodsGroupMapper.queryVoByPrimaryKey(groupId);
    }

    /**
     * 根据PageBean和参数Bean查询分页查询列表
     *
     * @param pb
     *            分页Bean
     * @param selBean
     *            参数Bean
     * @return
     */
    public PageBean queryGoodsGroupByPageBeanAndSearchBean(PageBean pb, SelectBean selBean) {
        pb.setObjectBean(selBean);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selBean.getSearchText())) {
            selBean.setCondition("-1");
        }
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();

        // 设置总行数
        pb.setRows(this.goodsGroupMapper.searchTotalCount(selBean));
        map.put(ValueUtil.STARTROWNUM, pb.getStartRowNum());
        map.put(ValueUtil.ENDROWNUM, pb.getEndRowNum());
        map.put(ValueUtil.CONDITION, selBean.getCondition());
        map.put(ValueUtil.SEARCHTEXT, selBean.getSearchText());
        // 设置数据
        pb.setList(this.goodsGroupMapper.queryAllByPageBeanAndSelBean(map));

        return pb;
    }

    /**
     * 根据货品ID查询组合或者是套装列表
     *
     * @param productId
     *            货品ID
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsGroupVo> queryGroupVoListByProductId(Long productId) {
        // 返回结果
        return this.goodsGroupMapper.queryGroupVoByProductId(productId);
    }

    /**
     * 根据货品ID查询组合或者是套装列表,去除当前的ID
     *
     * @param productId
     *            货品ID
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsGroupVo> queryGroupVoListWithOutProductId(Long productId) {
        // 根据货品id查询组合或者是套装列表，去除当前的id
        List<GoodsGroupVo> groupList = this.goodsGroupMapper.queryGroupVoByProductId(productId);

        for (int i = 0; i < groupList.size(); i++) {
            List<GoodsGroupReleProductVo> list = new ArrayList<>();
            for (int j = 0; j < groupList.get(i).getProductList().size(); j++) {
                if (groupList.get(i).getProductList().get(j).getProductDetail() != null
                        && !groupList.get(i).getProductList().get(j).getProductDetail().getGoodsInfoId().toString().equals(productId.toString())) {
                    list.add(groupList.get(i).getProductList().get(j));
                }
            }
            if (list.size() == 0) {
                groupList.remove(i);
            } else {
                groupList.get(i).setProductList(list);
            }
        }

        // 返回结果
        return groupList;

    }

    /**
     * 获取首页商品组合和商品预警的数量
     * 
     * @return
     */
    @Override
    public Map<String, Object> getIndexCount() {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stockCount", stockWarningMapper.selectcount(null));
        map.put("groupCount", goodsGroupMapper.searchTotalCount(null));
        // 返回结果
        return map;
    }

}
