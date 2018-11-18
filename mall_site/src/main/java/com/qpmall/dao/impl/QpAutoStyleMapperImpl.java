package com.qpmall.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.MyLogger;
import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.bean.QpGoodsAutoStyleBean;
import com.qpmall.dao.QpAutoStyleMapper;
import com.qpmall.unit.EHCacheUtil;
import com.qpmall.vo.AutoCarTypeVo;
import com.qpmall.vo.BrandBean;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by pl on 2015/10/9.
 * Desc:
 */
@Repository("qpAutoStyleMapper")
public class QpAutoStyleMapperImpl extends BasicSqlSupport implements QpAutoStyleMapper {

    private static final MyLogger LOGGER = new MyLogger(QpAutoStyleMapperImpl.class);
    private static String allAutoStyleList="ALLAUTOSTYLELIST";
    private static String allAutoStyleListByYear="ALLAUTOSTYLELISTBYYEAR";
    private static String allAutoStyleListByType="ALLAUTOSTYLELISTBYTYPE";
    private static String allAutoStyleByAutoId="ALLAUTOSTYLELISTBYAUTOID";
    private static String allAutoStyleByBrand="ALLAUTOSTYLELISTBYBRAND";

    @Override
    public List<QpAutoStyleBean> findAllList() {
        List<QpAutoStyleBean> list = (List<QpAutoStyleBean>) EHCacheUtil.get(allAutoStyleList);
        if (list==null || list.size() == 0)
        {
            list=this.selectList("com.qpmall.dao.QpAutoStyleMapper.findAllList");
            try {
                EHCacheUtil.setValue("AutoStyleCache",allAutoStyleList,list);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("EHCache缓存异常");
            }
        }
        return list;
    }

    @Override
    public List<String> findListByYear() {
        List<String> list = (List<String>) EHCacheUtil.get(allAutoStyleListByYear);
        if (list==null || list.size() == 0)
        {
            list=this.selectList("com.qpmall.dao.QpAutoStyleMapper.findListByYear");
            try {
                EHCacheUtil.setValue("AutoStyleCache",allAutoStyleListByYear,list);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("EHCache缓存异常");
            }
        }
        return list;
    }

    /***
     * 有查询条件的查询不用缓存
     * @param qpAutoStyleBean
     * @return
     */
    @Override
    public List<QpAutoStyleBean> findListByType(QpAutoStyleBean qpAutoStyleBean) {

        /*List<QpAutoStyleBean> list = (List<QpAutoStyleBean>) EHCacheUtil.get(allAutoStyleListByType);
        if (list==null)
        {
            list=this.selectList("com.qpmall.dao.QpAutoStyleMapper.findListByType",qpAutoStyleBean);
            try {
                EHCacheUtil.setValue("AutoStyleCache",allAutoStyleListByType,list);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("EHCache缓存异常");
            }
        }*/
        return this.selectList("com.qpmall.dao.QpAutoStyleMapper.findListByType",qpAutoStyleBean);
    }

    @Override
    public List<QpAutoStyleBean> findObjByAutoID(String autoId) {
        return this.selectList("com.qpmall.dao.QpAutoStyleMapper.findObjByAutoID", autoId);
    }
    @Override
    public List<AutoCarTypeVo> findVoObjByAutoID(String[] array) {
        return this.selectList("com.qpmall.dao.QpAutoStyleMapper.findVoObjByAutoID", array);
    }

    @Override
    public List<BrandBean> findAutoStyleByBrand(String carYearStr) {

        /*List<BrandBean> list = (List<BrandBean>) EHCacheUtil.get(allAutoStyleByBrand);
        if (list==null)
        {
            list=this.selectList("com.qpmall.dao.QpAutoStyleMapper.findAutoStyleByBrand", carYearStr);
            try {
                EHCacheUtil.setValue("AutoStyleCache",allAutoStyleByBrand,list);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("EHCache缓存异常");
            }
        }*/
        return this.selectList("com.qpmall.dao.QpAutoStyleMapper.findAutoStyleByBrand", carYearStr);
    }

    @Override
    public List<QpGoodsAutoStyleBean> selectGoodsAutoStyleByGoodsId(Long goodsId) {
        return this.selectList("com.qpmall.dao.QpAutoStyleMapper.selectGoodsAutoStyleByGoodsId", goodsId);
    }
}
