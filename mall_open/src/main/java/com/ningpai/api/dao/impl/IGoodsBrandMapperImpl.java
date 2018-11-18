package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OGoodsBrand;
import com.ningpai.api.dao.IGoodsBrandMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
@Repository("openGoodsBrandMapper")
public class IGoodsBrandMapperImpl extends BasicSqlSupport implements IGoodsBrandMapper {

    /**
     * 查询列表
     * @param map 参数集合
     * @return 集合
     */
    @Override
    public List<OGoodsBrand> list(Map<String, Object> map) {
        return this.selectList("com.ningpai.goods.dao.OGoodsBrandMapper.list",map);
    }

    /**
     * 查询总数
     *
     * @return 总行数
     */
    @Override
    public int count() {
      return this.selectOne("com.ningpai.goods.dao.OGoodsBrandMapper.count");
    }
}
