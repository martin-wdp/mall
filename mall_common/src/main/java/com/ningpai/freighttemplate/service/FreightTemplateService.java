package com.ningpai.freighttemplate.service;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.freighttemplate.bean.FreightExpress;
import com.ningpai.freighttemplate.bean.FreightTemplate;


/**
 * 运费模板  2014-12-16
 * @author ggn
 *
 */
public interface FreightTemplateService {

    /**
     * 查询运费模板信息
     * @param freightTemplate
     * @return List
     */
    List<FreightTemplate> searchAllTemplate( FreightTemplate freightTemplate);
    /**
     * 复制模板
     * @param freightTemplateId
     * @return int
     */
    int copyFreightTemplate(Long freightTemplateId);
    /**
     * 删除运费模板
     * @param freightTemplateId,freightThirdId
     * @return int
     */
    int deleteFreightTemplate(Long freightTemplateId,Long freightThirdId);
    /**
     * 设置模板默认
     * @param freightTemplate
     * @return int
     */
    int defaultFreightTemplate(FreightTemplate freightTemplate);
    /**
     * 查询模板详细
     * @param freightTemplateId
     * @return FreightTemplate
     */
    FreightTemplate selectFreightTemplateDetail(Long freightTemplateId);
    /**
     * 保存运费模板
     * @param request
     * @return int
     */
    int saveFreight(HttpServletRequest request,FreightTemplate freightTemplate);
    
    /**
     * 添加运费模板
     * @param request
     * @param freightTemplate
     * @return int
     */
    int addFreight(HttpServletRequest request,FreightTemplate freightTemplate);

    /**
     * 根据城市Id 和第三方Id ,商品数量 查询运费
     * @param cityId
     * @param thirdId
     * @param num
     * @return BigDecimal
     */
    BigDecimal getExpressPrice(Long distributionId,Long cityId,Long thirdId,Integer num,BigDecimal weight);
    /**
     * 查询默认物流模板
     * @return List
     */
    List<FreightExpress> selectFreightTemplateDefault();
    /**
     * 查询默认物流模板 其他商家
     * @param thirdId
     * @return FreightExpress
     */
    FreightExpress selectFreightExpressByDistriThirdId(Long thirdId);

    /**
     * 查询运费模板名称和id
     * @author houyichang 2015/7/1
     * @return List FreightTemplate
     * */
    List<FreightTemplate> queryTemplate();
}
