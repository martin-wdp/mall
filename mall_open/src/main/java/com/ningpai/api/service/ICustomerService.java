package com.ningpai.api.service;

/**
 * 开放接口-会员service
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
public interface ICustomerService {
    /**
     * 获取会员列表
     *
     * @param pageNo   页数 （默认1）
     * @param pageSize 每页数量（默认15）
     * @return 会员列表
     */
    String list(Integer pageNo, Integer pageSize);

    /**
     * 获取会员详情
     * @param customerUserName 会员账号
     * @return 会员详情
     */
    String get(String customerUserName);

    /**
     * 检查用户是否存在
     *
     * @param userName
     *            用户名 {@link String}
     * @return 0 不存在 1 存在 {@link Long}
     */
    Long checkExistsByCustNameAndType(String userName);


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
     * 添加会员
     *
     * @param customerUsername 会员名
     * @param customerPassword 密码
     * @param customerNickname 会员别名
     * @param phone            手机
     * @param emile            邮箱
     * @return 0:异常 1：添加成功 2：会员已存在 3：手机或邮箱已存在 4：名称以及密码为空或格式不正确 5：签名异常
     */
    int addCustomer(String customerUsername,String customerPassword,String customerNickname,String phone,String emile);
}
