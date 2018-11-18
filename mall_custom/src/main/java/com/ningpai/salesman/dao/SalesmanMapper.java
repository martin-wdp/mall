package com.ningpai.salesman.dao;


import com.ningpai.salesman.bean.Salesman;

import java.util.List;
import java.util.Map;

public interface SalesmanMapper {

    int deleteByPrimaryKey(Long salesmanId);

    List<Salesman> seletAllEnableSala();

    int insert(Salesman record);

    int insertSelective(Salesman record);

    int selectCountByName(String salesmanName);

    List<Object> selectBySalesman(Map<String,Object> param);

    Integer selectCountBySalesman(Salesman salesman);

    Salesman selectByPrimaryKey(Long salesmanId);


    int updateByPrimaryKeySelective(Salesman record);

    int updateByPrimaryKey(Salesman record);
}