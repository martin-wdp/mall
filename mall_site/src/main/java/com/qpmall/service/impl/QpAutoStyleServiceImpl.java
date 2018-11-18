package com.qpmall.service.impl;

import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.bean.QpGoodsAutoStyleBean;
import com.qpmall.dao.QpAutoStyleMapper;
import com.qpmall.service.QpAutoStyleService;
import com.qpmall.vo.AutoCarTypeVo;
import com.qpmall.vo.BrandBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pl on 2015/10/9.
 * Desc:
 */
@Service("qpAutoStyleService")
public class QpAutoStyleServiceImpl implements QpAutoStyleService {

    @Resource(name = "qpAutoStyleMapper")
    private QpAutoStyleMapper findGoodsMapper;

    @Override
    public List<QpAutoStyleBean> findAllList() {
        return findGoodsMapper.findAllList();
    }

    @Override
    public List<String> findListByYear() {
        return findGoodsMapper.findListByYear();
    }

    @Override
    public List<QpAutoStyleBean> findListByType(QpAutoStyleBean qpAutoStyleBean) {
        return findGoodsMapper.findListByType(qpAutoStyleBean);
    }

    @Override
    public List<QpAutoStyleBean> findObjByAutoID(String autoId) {
        return findGoodsMapper.findObjByAutoID(autoId);
    }
    @Override
    public List<AutoCarTypeVo> findVoObjByAutoID(String[] autoId) {
        return findGoodsMapper.findVoObjByAutoID(autoId);
    }

    @Override
    public List<BrandBean> findAutoStyleByBrand(String carYearStr) {
        return findGoodsMapper.findAutoStyleByBrand(carYearStr);
    }

    @Override
    public List<QpGoodsAutoStyleBean> selectGoodsAutoStyleByGoodsId(Long goodsId) {
        return findGoodsMapper.selectGoodsAutoStyleByGoodsId(goodsId);
    }
}
