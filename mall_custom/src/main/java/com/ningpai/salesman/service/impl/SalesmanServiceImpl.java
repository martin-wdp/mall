package com.ningpai.salesman.service.impl;

import com.ningpai.salesman.bean.Salesman;
import com.ningpai.salesman.dao.CustomerRelaSalesmanMapper;
import com.ningpai.salesman.dao.SalesmanMapper;
import com.ningpai.salesman.service.SalesmanService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ly-qpmall on 2015/12/16.
 */
@Service("salesmanService")
public class SalesmanServiceImpl implements SalesmanService {

    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String SALESMAN = "salesman";


    @Resource(name = "salesmanMapper")
    private SalesmanMapper salesmanMapper;

    @Resource(name = "customerRelaSalesmanMapper")
    private CustomerRelaSalesmanMapper customerRelaSalesmanMapper;


    @Override
    public Salesman selectByPrimaryKey(Long salesmanId) {
        return salesmanMapper.selectByPrimaryKey(salesmanId);
    }

    @Override
    public long checkMobileExist(String mobile) {
        return 0;
    }

    @Override
    public PageBean querySalesmanList(Salesman salesman, PageBean pageBean) {
        Map<String, Object> paramMap =null;
        int no = 0;
        try {
            paramMap = new HashMap<String,Object>();
            paramMap.put(SALESMAN, salesman);
            pageBean.setRows(salesmanMapper.selectCountBySalesman(salesman));
            no = no == 0 ? 1 : no;
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }
            paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
            paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
            pageBean.setList(salesmanMapper.selectBySalesman(paramMap));
            pageBean.setObjectBean(salesman);
        }finally{
                paramMap = null;
        }
        return pageBean;
    }

    @Override
    public int addSalesman(Salesman salesman) {
        return salesmanMapper.insertSelective(salesman);
    }

    @Override
    public int deleteSalesman(Long salesmanId) {
        return salesmanMapper.deleteByPrimaryKey(salesmanId);
    }

    @Override
    public int deleteAllSalesman(Long[] salesmanIds) {
        int count = 0 ;
        for(Long sala:salesmanIds){
            count += salesmanMapper.deleteByPrimaryKey(sala);
        }
        return count;
    }

    @Override
    public int updateSalesman(Salesman salesman) {
        return salesmanMapper.updateByPrimaryKeySelective(salesman);
    }

    @Override
    public Salesman querySalesmanInfo(Long salesmanId) {
        return salesmanMapper.selectByPrimaryKey(salesmanId);
    }

    @Override
    public int checkExistSalesmanName(String salesmanName) {
        return salesmanMapper.selectCountByName(salesmanName);
    }

    @Override
    public List<Salesman> seletAllEnableSala() {
        return salesmanMapper.seletAllEnableSala();
    }

    @Override
    public int checkUpdateEnable(Long salesmanId) {
        return 0;
    }
}
