package com.ningpai.api.dao.impl;

import com.ningpai.api.bean.OCustomer;
import com.ningpai.api.bean.OCustomerAllInfo;
import com.ningpai.api.dao.ICustomerMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 开放接口-会员dao
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
@Repository("openCustomerMapper")
public class CustomerMapperImpl extends BasicSqlSupport implements ICustomerMapper{


    /**
     * 查询会员列表
     *
     * @param map 分页参数
     * @return 会员信息
     */
    @Override
    public List<OCustomer> list(Map<String,Object> map) {
        return this.selectList("com.ningpai.customer.dao.OCustomerMapper.list",map);
    }

    /**
     * 根据会员id查询会员详情
     *
     * @param customerUserName 会员账号
     * @return 会员详情
     */
    @Override
    public OCustomerAllInfo get(String customerUserName) {
        return this.selectOne("com.ningpai.customer.dao.OCustomerMapper.get", customerUserName);
    }

    /**
     * 检查用户是否存在
     *
     * @param
     *            paramMap {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    @Override
    public Long checkExistsByCustNameAndType(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.OCustomerMapper.checkExistsByCustNameAndType",
                        paramMap);
    }


    /**
     * 验证手机存在性
     *
     * @param
     *
     * @return 0不存在 1存在
     */
    @Override
    public Long checkMobileExist(String mobile) {
        return this.selectOne(
                "com.ningpai.customer.dao.OCustomerMapper.checkMobileExist",
                mobile);
    }

    /**
     * 添加会员信息
     *
     * @param customerAllInfo 全部会员信息
     * @return 1：添加成功 0：添加失败
     */
    @Override
    public int addCustomer(OCustomerAllInfo customerAllInfo) {
        int i = this.insert(
                "com.ningpai.customer.dao.OCustomerMapper.insertSelective",
                customerAllInfo);
        if (i == 1) {
            Long customerId = this
                    .selectOne("com.ningpai.customer.dao.OCustomerMapper.selectLastId");
            customerAllInfo.setCustomerId(customerId);
            return this
                    .insert("com.ningpai.customer.dao.OCustomerMapper.insertSelectiveAllInfo",
                            customerAllInfo);
        } else {
            return 0;
        }
    }

    /**
     * 获取总行数
     *
     * @return 总行数
     */
    @Override
    public int count() {
        return this.selectOne("com.ningpai.customer.dao.OCustomerMapper.count");
    }

    /**
     * 验证邮箱存在性
     *
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    @Override
    public Long checkEmailExist(String email) {
        return this.selectOne(
                "com.ningpai.customer.dao.OCustomerMapper.checkEmailExist",
                email);
    }
}
