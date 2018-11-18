/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.controller;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.service.WareHouseService;
import com.ningpai.goods.util.PathUtil;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.WareHouseVo;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.other.bean.CityBean;
import com.ningpai.other.bean.DistrictBean;
import com.ningpai.other.bean.ProvinceBean;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库控制器
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月23日 上午11:06:17
 * @version 1.0
 */
@Controller
public class WareController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(WareController.class);

    private static final String LOGGERINFO1 = "-->仓库名称【";
    private static final String LOGGERINFO2 = "】,用户名：";

    private WareHouseService wareHouseService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 分页查询仓库信息
     * 
     * @param pb
     *            分页Bean
     * @param selectBean
     *            查询Bean
     */
    @RequestMapping("/queryWareHouseByPageBean")
    public ModelAndView queryWareHouseByPageBean(PageBean pb, SelectBean selectBean) {
        // 打印日志
        LOGGER.info(ValueUtil.QUERYWAREHOUSEBYPAGEBEANINFO);
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("selectBean", selectBean);
            map.put("pb", this.wareHouseService.queryAllWareHouseByPageBean(pb, selectBean));
            // 返回结果
            return new ModelAndView(PathUtil.WARELIST, "map", map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询所有省份
     * 
     * @return List<ProvinceBean> 省份集合
     * @see {@link com.ningpai.other.bean.ProvinceBean}
     */
    @RequestMapping("/getprovincebyware")
    @ResponseBody
    public List<ProvinceBean> getAllProvinceByWare() {
        return customerServiceMapper.selectAllProvince();
    }

    /**
     * 查询所有可用的地区
     * 
     * @param wareId
     *            仓库Id
     * 
     * @return
     */
    @RequestMapping("/selectalldistrict")
    @ResponseBody
    public List<DistrictBean> selectAllDistrict(Long wareId) {
        return wareHouseService.selectCityIdByWareId(wareId);
    }

    /**
     * 查询所有可用的城市
     * 
     * @param wareId
     *            仓库id
     * @return
     */
    @RequestMapping("/selectallcitydistrict")
    @ResponseBody
    public List<CityBean> selectAllCityDistrict(Long wareId) {
        return wareHouseService.selectCityIdByDid(wareId);
    }

    /**
     * 保存仓库信息
     * 
     * @param wareHouse
     *            待保存的仓库信息
     * @param request
     *            请求对象
     */
    @RequestMapping("/saveWareHouse")
    public ModelAndView saveWareHouse(@Valid WareHouse wareHouse, HttpServletRequest request, Long[] districtIds) {
        if (wareHouse.getWareAdmin() == null) {
            wareHouse.setWareAdmin((Long) request.getSession().getAttribute("loginUserId"));
        }
        // 验证仓库信息是否为空
        if (null != wareHouse.getWareName() && "".equals(wareHouse.getWareName())) {
            // 操作日志
            LOGGER.info(ValueUtil.SAVEWAREHOUSEINFO + ",保存的仓库名称为：" + wareHouse.getWareName());
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.SAVEWAREHOUSEINFO);
        }
        // 保存仓库信息
        this.wareHouseService.saveWareHouse(wareHouse, (String) request.getSession().getAttribute(ValueUtil.NAME), districtIds);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.SAVEWAREHOUSEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + wareHouse.getWareName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLWARE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 删除仓库信息
     */
    @RequestMapping("/delWareHouse")
    public ModelAndView delWareHouse(Long wareId, HttpServletRequest request) {
        // 根据主键获取仓库ID
        WareHouseVo wareHouseVos = this.wareHouseService.selectWareByWareId(wareId);
        // 打印日志
        LOGGER.info(ValueUtil.DELWAREHOUSEINFO);
        // 删除仓库信息
        this.wareHouseService.deleteWareById(wareId, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.DELWAREHOUSEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + wareHouseVos.getWareName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLWARE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 批量删除仓库信息
     * 
     * @param tagCheck
     *            待删除的仓库ID的数组
     * @param request
     */
    @RequestMapping("/batchDelWare")
    public ModelAndView batchDelWareHouse(Long[] tagCheck, HttpServletRequest request) {
        // 打印结果
        LOGGER.info(ValueUtil.BATCHDELWAREINFO);
        // 批量删除仓库信息
        this.wareHouseService.batchDelWare(tagCheck, (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(request, (String) request.getSession().getAttribute(ValueUtil.NAME), ValueUtil.BATCHDELWAREINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + ",用户名：" + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLWARE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 更新仓库信息
     * 
     * @param wareHouse
     *            待更新的实体
     */
    @RequestMapping("/updateWareHouse")
    public ModelAndView updateWareHouse(@Valid WareHouse wareHouse, HttpServletRequest request, Long[] districtIds) {
        // 打印日志
        LOGGER.info(ValueUtil.UPDATEWAREHOUSEINFO + "需要更新的仓库名称为:" + wareHouse.getWareName());
        // 更新仓库信息
        this.wareHouseService.updateWareHouse(wareHouse, (String) request.getSession().getAttribute(ValueUtil.NAME), districtIds);
        // 记录当前登录者的操作日志
        OperaLogUtil.addOperaLog(
                request,
                (String) request.getSession().getAttribute(ValueUtil.NAME),
                ValueUtil.UPDATEWAREHOUSEINFO,
                (String) request.getSession().getAttribute(ValueUtil.OPERAPATH) + LOGGERINFO1 + wareHouse.getWareName() + LOGGERINFO2
                        + (String) request.getSession().getAttribute(ValueUtil.NAME));
        // 返回结果
        return new ModelAndView(new RedirectView(PathUtil.ALLWARE + ValueUtil.TOKENPARAM + CSRFTokenManager.getTokenFromRequest(request)));
    }

    /**
     * 根据仓库ID查询仓库信息 并通过Ajax返回
     * 
     * @param wareId
     *            仓库ID
     * @return 查询到的仓库信息
     */
    @RequestMapping("/queryWareHouseByWareId")
    @ResponseBody
    public WareHouseVo queryWareHouseByWareId(Long wareId) {
        if (null != wareId) {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYWAREHOUSEBYWAREIDINFO + ",仓库ID为：" + wareId);
        } else {
            // 打印日志
            LOGGER.info(ValueUtil.QUERYWAREHOUSEBYWAREIDINFO);
        }
        // 返回结果
        return this.wareHouseService.selectWareByWareId(wareId);
    }

    /**
     * 验证仓库名称是否可用
     * 
     * @param wareName
     *            待验证的仓库名称
     * @return 返回true表示可用 false表示不可用
     */
    @RequestMapping("/checkWareName")
    @ResponseBody
    public boolean checkWareName(String wareName, Long wareId) {
        String wareNameNew = wareName;
        if (!"".equals(wareNameNew)) {
            try {
                wareNameNew = URLDecoder.decode(wareNameNew, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error(""+e);
            }
        }
        // 打印日志
        LOGGER.info(ValueUtil.CHECKWARENAMEINFO + ",验证的仓库名称为：" + wareNameNew);
        if (wareId != null) {
            // 验证仓库名称是否可用
            return this.wareHouseService.checkWareNameHaveId(wareNameNew, wareId);
        } else {
            // 验证仓库名称是否可用
            return this.wareHouseService.checkWareName(wareNameNew);
        }

    }

    /**
     * 验证标识是否可用
     * 
     * @param identityId
     *            标识
     * @return 查询到的结果条数
     */
    @RequestMapping("/checkidentityid")
    @ResponseBody
    public int checkIdentityId(String identityId) {
        return wareHouseService.identifyIsExist(identityId);
    }

    /**
     * 查询所有已经存在的仓库所选城市
     * 
     * @return
     */
    @RequestMapping("/getallwarehousedistrict")
    @ResponseBody
    public List<DistrictBean> getAllWareHouseDistrict() {
        return wareHouseService.getAllWareHouseDistrict();
    }

    public WareHouseService getWareHouseService() {
        return wareHouseService;
    }

    @Resource(name = "WareHouseService")
    public void setWareHouseService(WareHouseService wareHouseService) {
        this.wareHouseService = wareHouseService;
    }

}
