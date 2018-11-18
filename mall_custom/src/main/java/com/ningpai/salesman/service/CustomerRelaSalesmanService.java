package com.ningpai.salesman.service;

import com.ningpai.salesman.bean.CustomerRelaSalesman;

import java.util.List;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
public interface CustomerRelaSalesmanService {

    Long getSalesmanIdByCustId(Long customerId);

    int getSalesmanIdBySalaId(Long salesmanId);

    int getSalesmanIdBySalaIds(Long[] salesmanIds);

    int insertSelective(CustomerRelaSalesman customerRelaSalesman);
}
