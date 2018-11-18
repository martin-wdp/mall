package com.ningpai.salesman.service;

import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.salesman.bean.Salesman;
import com.ningpai.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
public interface SalesmanService {

    /**
     * 查询单个业务员信息 详细
     *
     * @param salesmanId
     *            业务员编号
     */
    Salesman selectByPrimaryKey(Long salesmanId);

    /**
     * 校验手机号的唯一性
     * @param mobile
     * @return
     */
    long checkMobileExist(String mobile);

    /**
     * 查询业务员列表 分页
     *
     */
    PageBean querySalesmanList(Salesman salesman, PageBean pageBean);

    /**
     * 添加业务员信息
     * @param salesman
     * @return
     */
    int addSalesman(Salesman salesman);

    /**
     * 删除业务员信息
     * @param salesmanId
     * @return
     */
    int deleteSalesman(Long salesmanId);
    /**
     * 批量删除业务员信息
     * @param salesmanIds
     * @return
     */
    int deleteAllSalesman(Long[] salesmanIds);

    int updateSalesman(Salesman salesman);

    Salesman querySalesmanInfo(Long salesmanId);

    int checkExistSalesmanName(String salesmanName);

    List<Salesman> seletAllEnableSala();

    int checkUpdateEnable(Long salesmanId);

}
