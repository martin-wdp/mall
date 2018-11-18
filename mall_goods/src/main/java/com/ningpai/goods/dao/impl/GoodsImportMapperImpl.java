/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsImport;
import com.ningpai.goods.dao.GoodsImportMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品导入临时表DAOIMpl
 *
 * @author NINGPAI-YuanKangKang
 * @since 2014年10月20日 上午9:33:31
 * @version 1.0
 */
@Repository("GoodsImportMapper")
public class GoodsImportMapperImpl extends BasicSqlSupport implements
        GoodsImportMapper {

    /**
     * 根据主键删除导入数据,此处修改的是del_flag为1.并不是物理删除
     *
     * @param id
     *            主键ID {@link Long}
     * @return 删除是否成功的标记 {@link Integer}
     */
    public int deleteByPrimaryKey(Long id) {

        return this.update(
                "com.ningpai.goods.dao.GoodsImportMapper.deleteByPrimaryKey",
                id);
    }

    /**
     * 保存数据
     *
     * @param record
     *            需要保存的实体 {@link GoodsImport}
     * @return 是否添加成功的标记 {@link Integer}
     */
    public int insertSelective(GoodsImport record) {

        return this.insert(
                "com.ningpai.goods.dao.GoodsImportMapper.insertSelective",
                record);
    }

    /**
     * 根据主键查询导入的数据,此处要验证是否深处的标记.已经删除的不查询
     *
     * @param id
     *            主键ID {@link Long}
     * @return 查询到的Bean的实体 {@link GoodsImport}
     */
    public GoodsImport selectByPrimaryKey(Long id) {

        return this.selectOne(
                "com.ningpai.goods.dao.GoodsImportMapper.selectByPrimaryKey",
                id);
    }

    /**
     * 分页查询所有的导入Bean
     *
     * @param map
     *            封装的分页参数,参考GoodsTagMapper里面的方法,传递参数
     * @return 查询到的Bean集合 {@link List}
     */
    public List<Object> queryAllGoodsImport(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.goods.dao.GoodsImportMapper.queryAllGoodsImport",
                map);
    }

    /**
     * 根据参数查询行数,用来分页使用
     *
     * @param map
     *            参数map {@link Map}
     * @return 查询到的行数 {@link Integer}
     */
    public int queryTotalCount(Map<?, ?> map) {

        return this.selectOne(
                "com.ningpai.goods.dao.GoodsImportMapper.queryTotalCount", map);
    }

    /**
     * 更新导入Bean为已添加状态,更新add_flag='1'
     *
     * @param id
     *            主键ID {@link Long}
     * @return 更新是否成功的标记 {@link Integer}
     */
    public int updateGoodsImportAdded(Long id) {

        return this
                .update("com.ningpai.goods.dao.GoodsImportMapper.updateGoodsImportAdded",
                        id);
    }

}
