package com.qpmall.dao;


import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.bean.QpGoodsAutoStyleBean;
import com.qpmall.vo.AutoCarTypeVo;
import com.qpmall.vo.BrandBean;

import java.util.List;

/**
 * Created by pl on 2015/10/9.
 * Desc:
 */
public interface QpAutoStyleMapper {
    List<QpAutoStyleBean> findAllList();
    List<String> findListByYear();
    List<QpAutoStyleBean> findListByType(QpAutoStyleBean qpAutoStyleBean);
    List<QpAutoStyleBean> findObjByAutoID(String autoId);
    List<AutoCarTypeVo> findVoObjByAutoID(String[] autoId);
    List<BrandBean> findAutoStyleByBrand(String carYearStr);
    List<QpGoodsAutoStyleBean> selectGoodsAutoStyleByGoodsId(Long goodsId);
}
