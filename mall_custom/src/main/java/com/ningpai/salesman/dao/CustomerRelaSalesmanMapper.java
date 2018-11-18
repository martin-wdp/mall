package com.ningpai.salesman.dao;

import com.ningpai.salesman.bean.CustomerRelaSalesman;

import java.util.List;

public interface CustomerRelaSalesmanMapper {


    int deleteByPrimaryKey(Long relaId);

    Long getSalesmanIdByCustId(Long customerId);

    int getSalesmanIdBySalaId(Long salesmanId);

    int deleteByCustId(Long customerId);

    int insertSelective(CustomerRelaSalesman customerRelaSalesman);


}