package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OEmpower;
import com.ningpai.api.dao.IEmpowerMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
@Repository("openEmpowerMapper")
public class EmpowerMapperImpl extends BasicSqlSupport implements IEmpowerMapper{

    /**
     * 获取秘钥
     *
     * @return 参与签名用到的秘钥
     */
    @Override
    public OEmpower getKey(String appUserName) {
        return this.selectOne("com.ningpai.web.dao.OEmpowerMapper.get",appUserName);
    }

    /**
     * 修改开放角色的token
     * @param map 用户名
     * @return 修改结果
     */
    public  int updateEmpower( Map<String,Object> map) {
        return this.update("com.ningpai.web.dao.OEmpowerMapper.updateEmpower",map);
    }
}
