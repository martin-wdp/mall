package com.ningpai.api.dao;

import com.ningpai.api.bean.OCustomer;
import com.ningpai.api.bean.OCustomerAllInfo;

import java.util.List;
import java.util.Map;

/**
 * 开放接口-商品dao
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
public interface ICustomerMapper {

    /**
     * 查询会员列表
     * @param map 分页参数
     * @return
     *        会员信息
     *
     */
    List<OCustomer> list(Map<String,Object> map);

    /**
     * 根据会员id查询会员详情
     * @param customerUserName 会员账号
     * @return 会员详情
     */
    OCustomerAllInfo get(String customerUserName);


    /**
     * 检查用户是否存在
     *
     * @param paramMap
     *            用户名 {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    Long checkExistsByCustNameAndType(Map<String, Object> paramMap);

    /**
     * 验证邮箱存在性
     *
     * @param email
     *            目标邮箱 {@link String}
     * @return 0不存在 1存在
     */
    Long checkEmailExist(String email);

    /**
     * 验证手机存在性
     *
     * @param mobile
     *            目标手机 {@link String}
     * @return 0不存在 1存在
     */
    Long checkMobileExist(String mobile);

    /**
     * 添加会员信息
     * @param customerAllInfo 全部会员信息
     * @return 1：添加成功 0：添加失败
     */
    int addCustomer(OCustomerAllInfo customerAllInfo);

    /**
     * 获取总行数
     * @return 总行数
     */
    int count();
}
