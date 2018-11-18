package com.ningpai.salesman.service.impl;

import com.ningpai.salesman.bean.CustomerRelaSalesman;
import com.ningpai.salesman.dao.CustomerRelaSalesmanMapper;
import com.ningpai.salesman.service.CustomerRelaSalesmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
@Service("customerRelaSalesmanService")
public class CustomerRelaSalesmanServiceImpl implements CustomerRelaSalesmanService {
    @Resource(name = "customerRelaSalesmanMapper")
    private CustomerRelaSalesmanMapper qpCustomerRelaSalesmanMapper;

    @Override
    public Long getSalesmanIdByCustId(Long customerId) {
        return qpCustomerRelaSalesmanMapper.getSalesmanIdByCustId(customerId);
    }

    @Override
    public int getSalesmanIdBySalaId(Long salesmanId) {
        return qpCustomerRelaSalesmanMapper.getSalesmanIdBySalaId(salesmanId);
    }

    @Override
    public int getSalesmanIdBySalaIds(Long[] salesmanIds) {
        int cout = 0;
        for(Long sala:salesmanIds){
            cout += qpCustomerRelaSalesmanMapper.getSalesmanIdBySalaId(sala);
        }
        return cout;
    }

    @Override
    public int insertSelective(CustomerRelaSalesman customerRelaSalesman) {
        return qpCustomerRelaSalesmanMapper.insertSelective(customerRelaSalesman);
    }
}
