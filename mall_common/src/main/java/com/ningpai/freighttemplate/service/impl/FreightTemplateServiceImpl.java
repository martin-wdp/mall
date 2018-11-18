package com.ningpai.freighttemplate.service.impl;

import com.ningpai.freighttemplate.bean.*;
import com.ningpai.freighttemplate.dao.*;
import com.ningpai.freighttemplate.service.FreightTemplateService;
import com.ningpai.util.MapUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 运费模板 2014-12-16
 * 
 * @author ggn
 *
 */
@Service("FreightTemplateService")
public class FreightTemplateServiceImpl implements FreightTemplateService {

    private static final String FREIGHTTHIRDID = "freightThirdId";
    private static final String START = "_start";
    private static final String POSTAGE = "_postage";
    private static final String PLUS = "_plus";
    private static final String POSTAGEPLUS = "_postageplus";
    private static final String AREAS = "_areas";
    private static final String FREIGHTISDEFAULT = "freightIsDefault";

    // Spring注入
    @Resource(name = "FreightTemplateMapper")
    private FreightTemplateMapper freightTemplateMapper;

    // Spring注入
    @Resource(name = "FreightExpressMapper")
    private FreightExpressMapper freightExpressMapper;

    // Spring注入
    @Resource(name = "SysLogisticsCompanyMapper")
    private SysLogisticsCompanyMapper sysLogisticsCompanyMapper;

    // Spring注入
    @Resource(name = "SysCityMapper")
    private SysCityMapper sysCityMapper;

    // Spring注入
    @Resource(name = "FreightExpressAllMapper")
    private FreightExpressAllMapper freightExpressAllMapper;

    // Spring注入
    @Resource(name = "expressInfoMapperThird")
    private ExpressInfoMapper expressInfoMapper;

    /*
     * 查询运费模板信息
     * 
     * @see
     * com.ningpai.freighttemplate.service.FreightTemplateService#searchAllTemplate
     * (com.ningpai.util.PageBean,
     * com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    public List<FreightTemplate> searchAllTemplate(FreightTemplate freightTemplate) {
        // 格式化查询参数
        Map<String, Object> map = MapUtil.getParamsMap(freightTemplate);
        // 查询模板列表
        List<FreightTemplate> list = this.freightTemplateMapper.searchAllTemplateList(map);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                // 查询模板运送方式默认
                List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(list.get(i).getFreightTemplateId());
                for (int q = 0; q < fe.size(); q++) {
                    // 如果模板为平台的
                    if ("0".equals(freightTemplate.getFreightThirdId().toString())) {
                        // 获取平台的物流公司信息
                        fe.get(q).setSysLogisticsCompany(sysLogisticsCompanyMapper.selectCompanyById(fe.get(q).getLogComId()));
                    } else {
                        // 获取第三方的物流信息
                        fe.get(q).setExpress(expressInfoMapper.selectByshoreExpId(fe.get(q).getLogComId()));
                    }

                    // 如果其他配送区域不能null
                    if (fe.get(q).getFreightExpressAll() != null || !fe.get(q).getFreightExpressAll().isEmpty()) {
                        // 存在一个其他地区的物流设置
                        List<FreightExpressAll> fall = fe.get(q).getFreightExpressAll();
                        for (int j = 0; j < fall.size(); j++) {
                            String areaIds = fall.get(j).getExpressArea();
                            // 如果城市编码不为空
                            if (!"".equals(areaIds)) {
                                String[] cityIds = areaIds.split(",");
                                if (cityIds != null && cityIds.length != 0) {
                                    // 查询城市
                                    StringBuilder tempName = new StringBuilder();
                                    // 获取城市名称
                                    for (int n = 0; n < cityIds.length; n++) {
                                        SysCity city = sysCityMapper.selectCityById(Long.valueOf(cityIds[n]));
                                        if (city != null) {
                                            tempName.append(city.getCityName());
                                            if (n < cityIds.length - 1) {
                                                tempName.append(",");
                                            }
                                        }

                                    }
                                    // 设置城市名称
                                    fall.get(j).setAllCityName(tempName.toString());
                                }

                            }
                        }
                        // 存入新的fall
                        fe.get(q).setFreightExpressAll(fall);
                    }

                }

                // 查询所选择的物流公司

                list.get(i).setFreightExpressList(fe);
            }
        }
        return list;
    }

    /*
     * 复制模板
     * 
     * @see com.ningpai.freighttemplate.service.FreightTemplateService#
     * copyFreightTemplate(java.lang.Long)
     */
    @Override
    @Transactional
    public int copyFreightTemplate(Long freightTemplateId) {
        // 根据ID查询物流模板信息
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateById(freightTemplateId);
        int insertFlag = 0;
        // 如果物流模板不为null
        if (ft != null) {
            // 重新设置时间信息 删除标记，是否默认等
            ft.setFreightCreateTime(new Date());
            ft.setFreightModifyTime(new Date());
            ft.setFreightTemplateId(null);
            ft.setFreightNoDelete("0");
            ft.setFreightIsDefault("0");
            ft.setFreightTemplateName(ft.getFreightTemplateName() + "的副本");
            // 插入新的模板
            insertFlag = freightTemplateMapper.insertNewFreightTemplate(ft);
            // 插入成功
            if (insertFlag == 1) {
                // 获取新的模板ID
                Long newFreightTemplateId = freightTemplateMapper.selectFreightTemplateLastId();
                // 获取老模板的默认快递方式
                List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(freightTemplateId);
                // 如果默认快递公司不为null
                if (fe != null && !fe.isEmpty()) {
                    // 循环
                    for (int i = 0; i < fe.size(); i++) {
                        // 设置默认快递方式ID为null 设置最新的物流模板ID
                        fe.get(i).setDistributionId(null);
                        fe.get(i).setFreightTemplateId(newFreightTemplateId);
                        // 插入默认物流方式
                        int einsetFlag = freightExpressMapper.insertNewFreightExpress(fe.get(i));
                        // 插入成功
                        if (einsetFlag == 1) {
                            // 查询刚刚插入的默认设置ID
                            Long newDistributionId = freightExpressMapper.selectLastDistributionId();
                            // 获取其他地区设置
                            List<FreightExpressAll> falist = fe.get(i).getFreightExpressAll();
                            // 判断其他地区是否存在
                            if (falist != null && !falist.isEmpty()) {
                                for (int j = 0; j < falist.size(); j++) {
                                    falist.get(j).setDistributionId(newDistributionId);
                                    falist.get(j).setExpressAreaId(null);
                                }
                                // 插入新的其他地区设置
                                freightExpressAllMapper.insertFreightExpressAll(falist);
                            }

                        }
                    }

                }
            }

        }
        return insertFlag;
    }

    /*
     * 删除运费模板
     * 
     * @see com.ningpai.freighttemplate.service.FreightTemplateService#
     * deleteFreightTemplate(java.lang.Long)
     */
    @Override
    @Transactional
    public int deleteFreightTemplate(Long freightTemplateId, Long freightThirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("freightTemplateId", freightTemplateId);
        map.put(FREIGHTTHIRDID, freightThirdId);
        // 根据ID 删除物流模板
        int delFlag = freightTemplateMapper.deleteFreightTemplate(map);

        if (delFlag == 1) {
            // 查询默认运送配置
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(freightTemplateId);
            for (int i = 0; i < fe.size(); i++) {
                // 删除默认运送排至
                int f = freightExpressMapper.deleteTemplateExpress(fe.get(i).getDistributionId());
                if (f > 0) {
                    // 删除其他运送配置
                    freightExpressAllMapper.deleteTemplateExpressAll(fe.get(i).getDistributionId());
                }
            }

        }
        return delFlag;
    }

    /*
     * 设置模板默认
     * 
     * @see com.ningpai.freighttemplate.service.FreightTemplateService#
     * defaultFreightTemplate(com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    @Transactional
    public int defaultFreightTemplate(FreightTemplate freightTemplate) {

        // 设置其他模板为不默认
        int s = freightTemplateMapper.noDefaultFreightTemplate(freightTemplate);
        int defaultFlag = 0;
        if (s > 0) {
            // 设置物流模板默认
            defaultFlag = freightTemplateMapper.defaultFreightTemplate(freightTemplate);
        }
        return defaultFlag;
    }

    /*
     * 查询模板详细
     * 
     * @see com.ningpai.freighttemplate.service.FreightTemplateService#
     * selectFreightTemplateDetail(java.lang.Long)
     */
    @Override
    public FreightTemplate selectFreightTemplateDetail(Long freightTemplateId) {
        // 查询物流模板详细
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateById(freightTemplateId);
        if (ft != null) {
            // 查询默认运送方式配置
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());
            for (int q = 0; q < fe.size(); q++) {
                // 0 代表是平台自营的物流模板
                if ("0".equals(ft.getFreightThirdId().toString())) {
                    // 查询物流公司信息
                    fe.get(q).setSysLogisticsCompany(sysLogisticsCompanyMapper.selectCompanyById(fe.get(q).getLogComId()));
                } else {
                    // 查询第三方物流信息
                    fe.get(q).setExpress(expressInfoMapper.selectByshoreExpId(fe.get(q).getLogComId()));
                }
                // 如果其他配送信息不为null
                if (fe.get(q).getFreightExpressAll() != null || !fe.get(q).getFreightExpressAll().isEmpty()) {
                    // 存在一个其他地区的物流设置
                    List<FreightExpressAll> fall = fe.get(q).getFreightExpressAll();
                    for (int j = 0; j < fall.size(); j++) {
                        String areaIds = fall.get(j).getExpressArea();
                        // 如果城市编码不为空
                        if (!"".equals(areaIds)) {
                            String[] cityIds = areaIds.split(",");
                            if (cityIds != null && cityIds.length != 0) {
                                // 查询城市
                                StringBuilder tempName = new StringBuilder();
                                for (int n = 0; n < cityIds.length; n++) {
                                    SysCity city = sysCityMapper.selectCityById(Long.valueOf(cityIds[n]));
                                    if (city != null) {
                                        tempName.append(city.getCityName());
                                        if (n < cityIds.length - 1) {
                                            tempName.append(",");
                                        }
                                    }

                                }
                                // 设置城市名称
                                fall.get(j).setAllCityName(tempName.toString());
                            }

                        }
                    }
                    // 存入新的fall
                    fe.get(q).setFreightExpressAll(fall);
                }

            }

            // 查询所选择的物流公司

            ft.setFreightExpressList(fe);

        }

        return ft;
    }

    /*
     * 保存运费模板
     * 
     * @see
     * com.ningpai.freighttemplate.service.FreightTemplateService#saveFreight
     * (javax.servlet.http.HttpServletRequest,
     * com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    @Transactional
    public int saveFreight(HttpServletRequest request, FreightTemplate freightTemplate) {
        Long freightTemplateId = Long.valueOf(request.getParameter("freightTemplateId"));
        // 先根据freightTemplateId查询表np_freight_express
        List<FreightExpress> freightExpress = freightExpressMapper.selectTemplateExpress(freightTemplateId);
        // 根据distributionId删除表np_freight_express_all中数据
        if (freightExpress != null) {
            for (int i = 0; i < freightExpress.size(); i++) {
                if (freightExpress.get(i) != null) {
                    freightExpressAllMapper.deleteTemplateExpressAll(freightExpress.get(i).getDistributionId());
                }
            }
        }
        // 然后根据freightTemplateId删除表np_freight_express中信息
        freightExpressMapper.deleteFreExpByTid(freightTemplateId);

        // 通过request 获取去修改版本主信息
        freightTemplate.setFreightTemplateId(freightTemplateId);
        freightTemplate.setFreightModifyTime(new Date());
        int s = freightTemplateMapper.updateByPrimaryKeySelective(freightTemplate);
        // 修改模板成功
        if (s == 1) {
            String[] logComIds = request.getParameterValues("logComId");
            if (logComIds != null && logComIds.length > 0) {
                for (int i = 0; i < logComIds.length; i++) {
                    // 获取所选择的快递

                    String excode = "";
                    Long exComId;
                    // 如果不是第三方
                    if (request.getSession().getAttribute("thirdId") != null) {
                        Express ex = expressInfoMapper.selectByshoreExpId(Long.valueOf(logComIds[i]));
                        excode = ex.getExpCompany();
                        exComId = ex.getShoreExpId();
                        // 获取所选择的快递
                        if (ex != null) {
                            // 获取配置信息
                            String[] starts = request.getParameterValues(excode + START);
                            String[] postages = request.getParameterValues(excode + POSTAGE);
                            String[] pluss = request.getParameterValues(excode + PLUS);
                            String[] postageplus = request.getParameterValues(excode + POSTAGEPLUS);
                            String[] areass = request.getParameterValues(excode + AREAS);
                            if (starts != null && starts.length > 0) {
                                FreightExpress fe = new FreightExpress();
                                fe.setLogComId(exComId);
                                fe.setFreightTemplateId(freightTemplateId);
                                fe.setExpressStart(Long.valueOf(starts[0]));
                                fe.setExpressPostage(new BigDecimal(postages[0].trim()));
                                fe.setExpressPlusN1(Long.valueOf(pluss[0]));
                                fe.setExpressPostageplus(new BigDecimal(postageplus[0].trim()));

                                // 插入到np_freight_express表中
                                freightExpressMapper.insertNewFreightExpress(fe);
                                Long distributionId = freightExpressMapper.selectLastDistributionId();
                                if (starts.length > 1) {
                                    for (int j = 1; j < starts.length; j++) {
                                        FreightExpressAll fea = new FreightExpressAll();
                                        fea.setExpressArea(areass[j]);
                                        fea.setExpressStart(Long.valueOf(starts[j]));
                                        fea.setExpressPostage(new BigDecimal(postages[j].trim()));
                                        fea.setExpressPlusN1(Long.valueOf(pluss[j]));
                                        fea.setExpressPostageplus(new BigDecimal(postageplus[j].trim()));
                                        fea.setDistributionId(distributionId);
                                        // 插入到expressall表里--------------------------------
                                        freightExpressAllMapper.insertSelective(fea);

                                    }
                                }
                            }
                        }

                    } else {
                        // 获取所选择的快递
                        SysLogisticsCompany sc = sysLogisticsCompanyMapper.selectCompanyById(Long.valueOf(logComIds[i]));

                        if (sc != null) {
                            // 获取配置信息
                            String[] starts = request.getParameterValues(sc.getCode() + START);
                            String[] postages = request.getParameterValues(sc.getCode() + POSTAGE);
                            String[] pluss = request.getParameterValues(sc.getCode() + PLUS);
                            String[] postageplus = request.getParameterValues(sc.getCode() + POSTAGEPLUS);
                            String[] areass = request.getParameterValues(sc.getCode() + AREAS);
                            if (starts != null && starts.length > 0) {
                                FreightExpress fe = new FreightExpress();
                                fe.setLogComId(sc.getLogComId());
                                fe.setFreightTemplateId(freightTemplateId);
                                fe.setExpressStart(Long.valueOf(starts[0]));
                                fe.setExpressPostage(new BigDecimal(postages[0].trim()));
                                fe.setExpressPlusN1(Long.valueOf(pluss[0]));
                                fe.setExpressPostageplus(new BigDecimal(postageplus[0].trim()));

                                // 插入到np_freight_express表中
                                freightExpressMapper.insertNewFreightExpress(fe);
                                Long distributionId = freightExpressMapper.selectLastDistributionId();
                                if (starts.length > 1) {
                                    for (int j = 1; j < starts.length; j++) {
                                        FreightExpressAll fea = new FreightExpressAll();
                                        fea.setExpressArea(areass[j]);
                                        fea.setExpressStart(Long.valueOf(starts[j]));
                                        fea.setExpressPostage(new BigDecimal(postages[j].trim()));
                                        fea.setExpressPlusN1(Long.valueOf(pluss[j]));
                                        fea.setExpressPostageplus(new BigDecimal(postageplus[j].trim()));
                                        fea.setDistributionId(distributionId);
                                        // 插入到expressall表里--------------------------------
                                        freightExpressAllMapper.insertSelective(fea);

                                    }
                                }
                            }
                        }

                    }

                }
            }
        }
        return s;
    }

    /*
     * 添加运费模板
     * 
     * @see
     * com.ningpai.freighttemplate.service.FreightTemplateService#addFreight
     * (javax.servlet.http.HttpServletRequest,
     * com.ningpai.freighttemplate.bean.FreightTemplate)
     */
    @Override
    @Transactional
    public int addFreight(HttpServletRequest request, FreightTemplate freightTemplate) {
        // 插入到np_freight_template表中
        freightTemplate.setFreightCreateTime(new Date());
        freightTemplate.setFreightModifyTime(new Date());
        int count = freightTemplateMapper.insertNewFreightTemplate(freightTemplate);
        Long ferightTemplateId = freightTemplateMapper.selectFreightTemplateLastId();
        if (count == 1) {
            String[] logComIds = request.getParameterValues("logComId");
            if (logComIds != null && logComIds.length > 0) {
                for (int i = 0; i < logComIds.length; i++) {
                    // 获取所选择的快递

                    String excode = "";
                    Long exComId;
                    // 如果不是第三方
                    if (request.getSession().getAttribute("thirdId") != null) {

                        // 是第三方
                        Express ex = expressInfoMapper.selectByshoreExpId(Long.valueOf(logComIds[i]));
                        excode = ex.getExpCompany();
                        exComId = ex.getShoreExpId();
                        if (ex != null) {
                            // 获取配置信息
                            String[] starts = request.getParameterValues(excode + START);
                            String[] postages = request.getParameterValues(excode + POSTAGE);
                            String[] pluss = request.getParameterValues(excode + PLUS);
                            String[] postageplus = request.getParameterValues(excode + POSTAGEPLUS);
                            String[] areass = request.getParameterValues(excode + AREAS);
                            if (starts != null && starts.length > 0) {
                                FreightExpress fe = new FreightExpress();
                                fe.setLogComId(exComId);
                                fe.setFreightTemplateId(ferightTemplateId);
                                fe.setExpressStart(Long.valueOf(starts[0]));
                                fe.setExpressPostage(new BigDecimal(postages[0].trim()));
                                fe.setExpressPlusN1(Long.valueOf(pluss[0]));
                                fe.setExpressPostageplus(new BigDecimal(postageplus[0].trim()));

                                // 插入到np_freight_express表中
                                freightExpressMapper.insertNewFreightExpress(fe);
                                Long distributionId = freightExpressMapper.selectLastDistributionId();

                                if (starts.length > 1) {
                                    for (int j = 1; j < starts.length; j++) {
                                        FreightExpressAll fea = new FreightExpressAll();
                                        fea.setExpressArea(areass[j]);
                                        fea.setExpressStart(Long.valueOf(starts[j]));
                                        fea.setExpressPostage(new BigDecimal(postages[j].trim()));
                                        fea.setExpressPlusN1(Long.valueOf(pluss[j]));
                                        fea.setExpressPostageplus(new BigDecimal(postageplus[j].trim()));
                                        fea.setDistributionId(distributionId);
                                        // 插入到expressall表里--------------------------------
                                        freightExpressAllMapper.insertSelective(fea);
                                    }
                                }
                            }

                        }
                    } else {

                        SysLogisticsCompany sc = sysLogisticsCompanyMapper.selectCompanyById(Long.valueOf(logComIds[i]));
                        excode = sc.getCode();
                        exComId = sc.getLogComId();
                        if (sc != null) {
                            // 获取配置信息
                            String[] starts = request.getParameterValues(excode + START);
                            String[] postages = request.getParameterValues(excode + POSTAGE);
                            String[] pluss = request.getParameterValues(excode + PLUS);
                            String[] postageplus = request.getParameterValues(excode + POSTAGEPLUS);
                            String[] areass = request.getParameterValues(excode + AREAS);
                            if (starts != null && starts.length > 0) {
                                FreightExpress fe = new FreightExpress();
                                fe.setLogComId(exComId);
                                fe.setFreightTemplateId(ferightTemplateId);
                                fe.setExpressStart(Long.valueOf(starts[0]));
                                fe.setExpressPostage(new BigDecimal(postages[0].trim()));
                                fe.setExpressPlusN1(Long.valueOf(pluss[0]));
                                fe.setExpressPostageplus(new BigDecimal(postageplus[0].trim()));

                                // 插入到np_freight_express表中
                                freightExpressMapper.insertNewFreightExpress(fe);
                                Long distributionId = freightExpressMapper.selectLastDistributionId();

                                if (starts.length > 1) {
                                    for (int j = 1; j < starts.length; j++) {
                                        FreightExpressAll fea = new FreightExpressAll();
                                        fea.setExpressArea(areass[j]);
                                        fea.setExpressStart(Long.valueOf(starts[j]));
                                        fea.setExpressPostage(new BigDecimal(postages[j].trim()));
                                        fea.setExpressPlusN1(Long.valueOf(pluss[j]));
                                        fea.setExpressPostageplus(new BigDecimal(postageplus[j].trim()));
                                        fea.setDistributionId(distributionId);
                                        // 插入到expressall表里--------------------------------
                                        freightExpressAllMapper.insertSelective(fea);
                                    }
                                }
                            }

                        }

                    }

                }

            }
        }
        return count;
    }

    /*
     * 根据城市Id 和第三方Id ,商品数量 查询运费
     * 
     * @see
     * com.ningpai.freighttemplate.service.FreightTemplateService#getExpressPrice
     * (java.lang.Long, java.lang.Long, java.lang.Integer)
     */
    @Override
    public BigDecimal getExpressPrice(Long distributionId, Long cityId, Long thirdId, Integer num, BigDecimal weight) {

        // 查询物流模板信息 根据thirdId 查询默认的模板
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(FREIGHTISDEFAULT, "1");
        paramMap.put(FREIGHTTHIRDID, thirdId);
        // 获取默认模板
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
        if (ft != null) {
            // 查询默认全国设置
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());
            // 如果全国设置不为空
            if (fe != null && !fe.isEmpty()) {
                if (distributionId != null && thirdId == 0) {
                    for (int i = 0; i < fe.size(); i++) {
                        if (distributionId.equals(fe.get(i).getDistributionId())) {
                            // 获取其他地区设置
                            List<FreightExpressAll> fall = fe.get(i).getFreightExpressAll();
                            // 其他地区设置不为空
                            if (fall != null && !fall.isEmpty()) {

                                for (int j = 0; j < fall.size(); j++) {
                                    // 获取其他地区设置
                                    String area = fall.get(j).getExpressArea();
                                    String[] cityIds = area.split(",");
                                    // 标识
                                    int flag = 0;
                                    // 判断是否存在此城市单独设置
                                    for (String ciId : cityIds) {
                                        if (ciId.equals(cityId.toString())) {
                                            flag = 1;
                                            break;
                                        }

                                    }

                                    if (flag == 1) {

                                        return computeFreightAll(ft.getFreightMethods(), fall.get(j), num, weight);

                                    }

                                }

                                // 如果上述判断没有返回return
                                // 那么去默认全国设置
                                return computeFreight(ft.getFreightMethods(), fe.get(i), num, weight);

                            } else {
                                // 如果默认其他地区没有
                                return computeFreight(ft.getFreightMethods(), fe.get(i), num, weight);
                            }

                        }

                    }

                } else {
                    // 获取其他地区设置
                    List<FreightExpressAll> fall = fe.get(0).getFreightExpressAll();
                    // 其他地区设置不为空
                    if (fall != null && !fall.isEmpty()) {

                        for (int j = 0; j < fall.size(); j++) {
                            // 获取其他地区设置
                            String area = fall.get(j).getExpressArea();
                            String[] cityIds = area.split(",");
                            // 标识
                            int flag = 0;
                            // 判断是否存在此城市单独设置
                            for (String ciId : cityIds) {
                                if (ciId.equals(cityId.toString())) {
                                    flag = 1;
                                    break;
                                }

                            }

                            if (flag == 1) {

                                return computeFreightAll(ft.getFreightMethods(), fall.get(j), num, weight);

                            }

                        }

                        // 如果上述判断没有返回return
                        // 那么去默认全国设置
                        return computeFreight(ft.getFreightMethods(), fe.get(0), num, weight);

                    } else {

                        return computeFreight(ft.getFreightMethods(), fe.get(0), num, weight);
                    }
                }
            }
            return new BigDecimal(0);
        } else {
            return new BigDecimal(0);
        }

    }

    /**
     * 计算其他地区价格
     * 
     * @param motheds
     * @param frall
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreightAll(String motheds, FreightExpressAll frall, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(frall.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = frall.getExpressPostageplus().multiply(new BigDecimal(a));
                price = frall.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(frall.getExpressStart().toString());
                BigDecimal temp = frall.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(frall.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = frall.getExpressPostageplus().multiply(a);
                price = frall.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(frall.getExpressStart()));
                BigDecimal temp = frall.getExpressPostageplus().multiply(a.divide(new BigDecimal(frall.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = frall.getExpressPostage().add(temp);
            }

            return price;
        }

    }

    /**
     * 计算全国价格
     * 
     * @param motheds
     * @param fe
     * @param num
     * @param weight
     * @return BigDecimal
     */
    public BigDecimal computeFreight(String motheds, FreightExpress fe, Integer num, BigDecimal weight) {
        BigDecimal price = new BigDecimal(0);
        if (num == 0) {
            return price;
        }
        // 计件
        if ("0".equals(motheds)) {
            // 如果购买的数量在首件之内
            if (num < Integer.parseInt(fe.getExpressStart().toString())) {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = 0;
                BigDecimal temp = fe.getExpressPostageplus().multiply(new BigDecimal(a));
                price = fe.getExpressPostage().add(temp);
            } else {
                // 价格 = 首件价格 + （ 总件 -首件）*续件价格
                int a = num - Integer.parseInt(fe.getExpressStart().toString());
                BigDecimal temp = fe.getExpressPostageplus().multiply((new BigDecimal(a)).divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        } else {
            // 如果购买重量大于或者等与会
            if (weight.compareTo(new BigDecimal(fe.getExpressStart())) == -1) {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = new BigDecimal(0);
                BigDecimal temp = fe.getExpressPostageplus().multiply(a);
                price = fe.getExpressPostage().add(temp);
            } else {
                // 计重 价格= 首重价格 + （总重-首重）*续重价格
                BigDecimal a = weight.subtract(new BigDecimal(fe.getExpressStart()));
                BigDecimal temp = fe.getExpressPostageplus().multiply(a.divide(new BigDecimal(fe.getExpressPlusN1()), 0, BigDecimal.ROUND_UP));
                price = fe.getExpressPostage().add(temp);
            }

            return price;
        }
    }

    /*
     * 查询默认物流模板
     * 
     * @see com.ningpai.freighttemplate.service.FreightTemplateService#
     * selectFreightTemplateDefault()
     */
    @Override
    public List<FreightExpress> selectFreightTemplateDefault() {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(FREIGHTISDEFAULT, "1");
        paramMap.put(FREIGHTTHIRDID, "0");
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
        if (ft != null) {
            List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());
            if (fe != null) {
                for (int i = 0; i < fe.size(); i++) {
                    fe.get(i).setSysLogisticsCompany(sysLogisticsCompanyMapper.selectCompanyById(fe.get(i).getLogComId()));
                    fe.get(i).setFreightTemplate(ft);
                }
            }

            return fe;
        }

        return new ArrayList<FreightExpress>();
    }

    /**
     * 查询默认物流模板 其他商家
     * 
     * @param thirdId
     * @return
     */
    @Override
    public FreightExpress selectFreightExpressByDistriThirdId(Long thirdId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(FREIGHTISDEFAULT, "1");
        paramMap.put(FREIGHTTHIRDID, thirdId);
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
        if (ft != null) {
            // 判断是否是卖家承担运费
            if ("1".equals(ft.getFreightPackageMail())) {
                return new FreightExpress();
            } else {
                List<FreightExpress> fe = freightExpressMapper.selectTemplateExpress(ft.getFreightTemplateId());
                if (fe != null && !fe.isEmpty()) {
                    fe.get(0).setExpress(expressInfoMapper.selectByshoreExpId(fe.get(0).getLogComId()));
                    return fe.get(0);
                } else {
                    return null;
                }
            }

        }
        return null;
    }

    /**
     * 查询运费模板名称和id
     *
     * @return List FreightTemplate
     * @author houyichang 2015/7/1
     */
    @Override
    public List<FreightTemplate> queryTemplate() {
        return this.freightTemplateMapper.queryTemplate();
    }

}
