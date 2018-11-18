package com.ningpai.businesscircle.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.businesscircle.bean.BusinessCircle;
import com.ningpai.businesscircle.dao.BusinessCircleMapper;
import com.ningpai.businesscircle.service.BusinessCircleService;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.util.PageBean;

/**
 * 商圈接口实现类
 * 
 * @author ggn 2015-01-20
 *
 */
@Service("BusinessCircleService")
public class BusinessCircleServiceImpl extends BasicSqlSupport implements
        BusinessCircleService {

    /**
     * spring 注入
     */
    @Resource(name = "BusinessCircleMapper")
    private BusinessCircleMapper businessCircleMapper;

    /*
     * 添加商圈
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#addBusinessCircle
     * (com.ningpai.businesscircle.bean.BusinessCircle)
     */
    @Override
    public int addBusinessCircle(BusinessCircle businessCircle) {
        // 添加商圈
        return businessCircleMapper.addBusinessCircle(businessCircle);
    }

    /*
     * 修改商圈的开启状态
     * @see com.ningpai.businesscircle.service.BusinessCircleService#
     * updateBusinessCircleById(java.lang.Long, java.lang.String)
     */
    @Override
    public int updateBusinessCircleById(Long businessCircleId,
            String businessCircleIsOpen) {
        // 修改商圈
        BusinessCircle businessCircle = new BusinessCircle();
        businessCircle.setBusinessCircleId(businessCircleId);
        businessCircle.setBusinessCircleIsOpen(businessCircleIsOpen);
        businessCircle.setBusinessCircleModifyTime(new Date());
        return businessCircleMapper.updatebusinessCircleIsOpen(businessCircle);
    }

    /*
     * 绑定商圈
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#bindBusinessCircle
     * (java.lang.Long, java.lang.Long)
     */
    @Override
    public int bindBusinessCircle(Long thirdId, Long businessCircleId) {
        // 根据商家的Id获得商圈信息
        BusinessCircle bs = null;
        bs = businessCircleMapper.findBusinessCircleById(thirdId);
        // 商家绑定过 要删除绑定商圈上的商家 在进行绑定
        if (bs != null) {
            bs.setBusinessCircleThirdId(null);
            businessCircleMapper.delThirdIdToBusinessCircle(bs);
        }
        // 绑定商家
        bs = new BusinessCircle();
        bs.setBusinessCircleId(businessCircleId);
        bs.setBusinessCircleThirdId(thirdId);

        return businessCircleMapper.updateBusinessCircle(bs);

    }

    /*
     * 查询商圈信息(根据条件)分页
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#findBusinessCircles
     * (com.ningpai.util.PageBean, java.lang.String, java.lang.String)
     */
    @Override
    public PageBean findBusinessCircles(PageBean pb, String searchId,
            String searchText) {
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        if ("1,1".equals(searchId)) {
            map.put("businessCircleName", "%" + searchText + "%");
        }
        if ("2,2".equals(searchId)) {
            map.put("provinceName", "%" + searchText + "%");
        }
        if ("3,3".equals(searchId)) {
            map.put("cityName", "%" + searchText + "%");
        }
        // 查询总数
        pb.setRows(businessCircleMapper.findBusinessCirclesCount(map));
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        // 查询列表
        pb.setList(businessCircleMapper.findBusinessCircles(map));
        return pb;
    }

    /*
     * 获得未分配的商圈
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#getAll(java.
     * lang.String, java.lang.String)
     */
    @Override
    public List<Object> getAll(String businessCircleIsOpen,
            String businessCircleThirdId) {
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("businessCircleIsOpen", businessCircleIsOpen);
        map.put("businessCircleThirdId", businessCircleThirdId);
        // 查询所有商圈
        return businessCircleMapper.findBusinessCirclesByMap(map);
    }

    /*
     * 根据商家编号查询商圈信息
     * @see com.ningpai.businesscircle.service.BusinessCircleService#
     * findBusinessCircleByThirdId(java.lang.Long)
     */
    @Override
    public BusinessCircle findBusinessCircleByThirdId(Long thirdId) {
        // 根据第三方ID查询商圈
        return businessCircleMapper.findBusinessCircleById(thirdId);
    }

    /*
     * 删除商圈
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#delBusinessCircle
     * (java.lang.Long)
     */
    @Override
    public int delBusinessCircle(Long businessCircleId) {
        // 删除商圈
        return businessCircleMapper.delBusinessCircleById(businessCircleId);
    }

    /*
     * 根据商圈名字获得商圈信息
     * @see com.ningpai.businesscircle.service.BusinessCircleService#
     * findBusinessCircleByName(java.lang.String)
     */
    @Override
    public BusinessCircle findBusinessCircleByName(String name) {
        // 根据名称查询商圈
        return businessCircleMapper.findBusinessCircleByName(name);
    }

    /*
     * 根据商圈Id 查询商圈信息
     * @see com.ningpai.businesscircle.service.BusinessCircleService#
     * findBusinessCircleByBusinessCircleId(java.lang.Long)
     */
    @Override
    public BusinessCircle findBusinessCircleByBusinessCircleId(
            Long businessCircleId) {
        // 根据商圈ID查询
        return businessCircleMapper
                .findBusinessCircleByBusinessCircleId(businessCircleId);
    }

    /*
     * 修改商圈
     * @see
     * com.ningpai.businesscircle.service.BusinessCircleService#updateBusinessCircle
     * (com.ningpai.businesscircle.bean.BusinessCircle)
     */
    @Override
    public int updateBusinessCircle(BusinessCircle businessCircle) {
        // 修改商圈
        return businessCircleMapper.updateBusinessCircle(businessCircle);
    }
}
