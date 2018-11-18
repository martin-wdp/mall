
package com.ningpai.goods.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.goods.bean.GoodsReleExpandParam;
import com.ningpai.goods.dao.CascDelMapper;
import com.ningpai.goods.dao.GoodsReleExpandParamMapper;
import com.ningpai.goods.service.GoodsReleExpandParamService;
import com.ningpai.goods.service.GoodsTypeExpandParamValueService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.util.MyLogger;

/**
 * 商品关联类型扩展参数Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午12:02:29
 * @version 1.0
 */
@Service("GoodsReleExpandParamService")
public class GoodsReleExpandParamServiceImpl implements
        GoodsReleExpandParamService {
    // 商品关联类型扩展参数DAO
    private GoodsReleExpandParamMapper releExpandParamMapper;
    // 商品类型扩展属性值Service
    private GoodsTypeExpandParamValueService goodsTypeExpandParamValueService;
    private CascDelMapper cascDelMapper;
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(
            GoodsReleExpandParamServiceImpl.class);

    public CascDelMapper getCascDelMapper() {
        return cascDelMapper;
    }

    @Resource(name = "CascDelMapper")
    public void setCascDelMapper(CascDelMapper cascDelMapper) {
        this.cascDelMapper = cascDelMapper;
    }

    public GoodsTypeExpandParamValueService getGoodsTypeExpandParamValueService() {
        return goodsTypeExpandParamValueService;
    }

    @Resource(name = "GoodsTypeExpandParamValueService")
    public void setGoodsTypeExpandParamValueService(
            GoodsTypeExpandParamValueService goodsTypeExpandParamValueService) {
        this.goodsTypeExpandParamValueService = goodsTypeExpandParamValueService;
    }

    public GoodsReleExpandParamMapper getReleExpandParamMapper() {
        return releExpandParamMapper;
    }

    @Resource(name = "GoodsReleExpandParamMapper")
    public void setReleExpandParamMapper(
            GoodsReleExpandParamMapper releExpandParamMapper) {
        this.releExpandParamMapper = releExpandParamMapper;
    }

    /**
     * 保存商品关联类型扩展参数
     *
     * @param username
     *            操作人名称
     * @param goodsId
     *            商品ID
     * @param expandParamIds
     *            扩展属性ID
     * @param expandParamValue
     *            扩展属性值ID
     * @return 插入的行数
     */
    @Transactional
    public int saveExpandParam(String username, Long goodsId,
            Long expandParamIds, Long expandParamValue) {
        // 创建一个商品关联类型扩展属性值对象
        GoodsReleExpandParam goodsReleExpandParam = new GoodsReleExpandParam();
        try {
            // 给临时对象赋值
            goodsReleExpandParam.setReleExpandparamCreateName(username);
            goodsReleExpandParam.setGoodsId(goodsId);
            goodsReleExpandParam.setExpandparamId(expandParamIds);
            goodsReleExpandParam.setExpangparamValueId(expandParamValue);
            goodsReleExpandParam
                    .setReleExpandparamDelflag(ValueUtil.DEFAULTDELFLAG);
            goodsReleExpandParam
                    .setExpandparamValueName(this.goodsTypeExpandParamValueService
                            .queryExpandParamValueByExpandParamId(
                                    expandParamValue).getExpandparamValueName());
            //返回结果
            return this.releExpandParamMapper
                    .insertSelective(goodsReleExpandParam);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.SAVEEXPANDPARAM + username);
            // 对象置空
            goodsReleExpandParam = null;
        }
    }

    /**
     * 根据商品ID和扩展属性ID查询商品关联扩展属性的记录
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param expandParamId
     *            扩展属性ID {@link java.lang.Long}
     * @return 查询到的记录
     */
    public GoodsReleExpandParam queryByGoodsIdAndExpandParamId(Long goodsId,
            Long expandParamId) {
        //定义一个HashMap集合
        Map<String, Long> map = new HashMap<String, Long>();
        try {
            //把商品id参数放进map集合中
            map.put("goodsId", goodsId);
            //把扩展属性id放进map集合中
            map.put("expandParamId", expandParamId);
            //返回结果
            return this.releExpandParamMapper
                    .queryByGoodsIdAndExpandParamId(map);
        } finally {
            // 对象置空
            map = null;
        }
    }

    /**
     * 更新商品关联扩展属性信息
     *
     * @param goodsReleExpandParam
     *            商品关联扩展属性信息实体
     *            {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int updateGoodsReleExpandParam(
            GoodsReleExpandParam goodsReleExpandParam, String username) {
        //对参数进行赋值
        goodsReleExpandParam.setReleExpandparamModifiedName(username);
        goodsReleExpandParam
                .setExpandparamValueName(this.goodsTypeExpandParamValueService
                        .queryExpandParamValueByExpandParamId(
                                goodsReleExpandParam.getExpangparamValueId())
                        .getExpandparamValueName());
        //打印日志
        LOGGER.info(ValueUtil.UPDATEGOODSRELEEXPANDPARAM + username);
        //返回结果
        return this.releExpandParamMapper
                .updateByPrimaryKeySelective(goodsReleExpandParam);
    }

    /**
     * 删除商品关联扩展属性记录
     *
     * @param expandParamId
     *            关联的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    @Transactional
    public int delGoodsReleExpandParam(Long expandParamId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放进map集合中
            map.put("releExpandparamId", expandParamId.toString());
            map.put("delName", username);
            //返回结果
            return this.releExpandParamMapper.deleteByPrimaryKey(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELGOODSRELEEXPANDPARAM + username);
            //执行方法
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

    /**
     * 根据商品ID删除所有的关联信息
     *
     * @param goodsId
     *            商品的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    @Transactional
    public int delAllExpandParamByGoodsId(Long goodsId, String username) {
        //定义一个HashMap集合
        Map<String, String> map = new HashMap<String, String>();
        try {
            //把参数放进Map集合中
            map.put("delName", username);
            map.put("goodsId", goodsId.toString());
            //执行方法，返回结果
            return this.releExpandParamMapper.delAllExpandParamByGoodsId(map);
        } finally {
            //打印日志
            LOGGER.info(ValueUtil.DELALLEXPANDPARAMBYGOODSID + username);
            this.cascDelMapper.cascDel(username);
            // 参数置空
            map = null;
        }
    }

}
