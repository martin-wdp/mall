/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.service.impl;

import com.ningpai.third.goods.bean.ThirdCate;
import com.ningpai.third.goods.dao.ThirdCateMapper;
import com.ningpai.third.goods.service.ThirdCateService;
import com.ningpai.third.goods.util.ThirdValueBean;
import com.ningpai.third.goods.vo.ThirdCateVo;
import com.ningpai.util.SelectBean;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 第三方店家分类Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2014年5月6日 上午11:56:39
 */
@Service("ThirdCateService")
public class ThirdCateServiceImpl implements ThirdCateService {

    private static final String CATID = "catId";

    /**
     * 第三方分类
     */
    private ThirdCateMapper thirdCateMapper;

    /**
     * ¬ 车如第三方类型
     *
     * @param thirdCate
     *            待添加的第三方分类
     * @param username
     *            操作人ID
     * @return
     */
    @Transactional
    public int insertThirdCate(ThirdCate thirdCate, String username) {
        // 设置必须的属性
        thirdCate.setCatCreateName(username);
        thirdCate.setCatIsShow("1");
        thirdCate.setCatDelflag("0");
        // 根据主键查询
        thirdCate.setCatGrade(this.thirdCateMapper.selectByPrimaryKey(thirdCate.getCatParentId()).getCatGrade() + 1);
        return this.thirdCateMapper.insertSelective(thirdCate);
    }

    /**
     * 车如第三方类型
     *
     * @param catId
     *            待删除的分类ID
     * @param username
     *            操作人名称
     * @return
     */
    @Transactional
    public int delThirdCate(Long catId, String username) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 类型ID
            map.put(CATID, catId);
            // 会员名称
            map.put("delName", username);
            return this.thirdCateMapper.deleteByPrimaryKey(map);
        } finally {
            map = null;
        }
    }

    /**
     * 删除第三方类型
     *
     * @param catId
     * @param username
     * @param thirdId
     * @return
     */
    @Transactional
    public int delThirdCateNew(Long catId, String username, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 类型ID
            map.put(CATID, catId);
            // 会员名称
            map.put("delName", username);
            map.put("thirdId", thirdId);
            return this.thirdCateMapper.deleteByPrimaryKeyNew(map);
        } finally {
            map = null;
        }
    }

    /**
     * 批量删除第三方类型
     *
     * @param catIds
     *            待删除的分类数组
     * @param username
     * @return
     */
    @Transactional
    public int batchDelThirdCate(Long[] catIds, String username) {
        Integer count = 0;
        try {
            if (ArrayUtils.isNotEmpty(catIds)) {
                for (int i = 0; i < catIds.length; i++) {
                    count += this.delThirdCate(catIds[i], username);
                }
            }
            return count;
        } finally {
            count = null;
        }
    }

    /**
     * 修改第三方类型
     *
     * @param thirdCate
     *            待更新的第三方店铺分类
     * @param username
     *            操作人名称
     * @return
     */
    @Transactional
    public int updateThirdCate(ThirdCate thirdCate, String username) {

        // 如果第三方分配没有父id则直接返回0
        if (null == thirdCate || null == thirdCate.getCatParentId()) {
            return 0;
        }

        thirdCate.setCatModifiedName(username);

        // 如果父id为0 则说明是顶层分类 则说明是第一及
        if (thirdCate.getCatParentId().equals(0L)) {
            thirdCate.setCatGrade(1);
        } else {
            thirdCate.setCatGrade(this.thirdCateMapper.selectByPrimaryKey(thirdCate.getCatParentId()).getCatGrade() + 1);
        }

        return this.thirdCateMapper.updateByPrimaryKeySelective(thirdCate);
    }

    /**
     * 根据ID查询第三方类型类
     *
     * @param catId
     *            分类ID
     * @return
     */
    public ThirdCateVo queryThirdCateById(Long catId) {
        return this.thirdCateMapper.selectByPrimaryKey(catId);
    }

    /**
     * 根据商家ID查询第三方类型集合
     *
     * @param thirdId
     * @return
     */
    public List<ThirdCate> queryAllCate(Long thirdId) {
        return this.thirdCateMapper.queryAllCate(thirdId);
    }

    /**
     * 根基类型ID验证类型信息
     *
     * @param cateId
     *            待验证的分类ID
     * @return
     */
    public boolean checkDelWithCateId(Long cateId) {
        return this.thirdCateMapper.querySonCountByParentId(cateId) > 0 ? false : true;
    }

    /**
     * 根据商家ID 类型名称查询商检类型信息
     *
     * @param cateName
     *            分类名称
     * @param thirdId
     * @return
     */
    public ThirdCate queyCateByCateName(String cateName, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("cateName", cateName);
            map.put(ThirdValueBean.THIRDID, thirdId);
            return this.thirdCateMapper.queryCateByCateName(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商家ID查询所有的商家类型集合
     *
     * @param selectBean
     *            查询参数Bean
     * @param thirdId
     *            第三方店铺的ID
     * @return
     */
    public List<Object> getAllCalcThirdCate(SelectBean selectBean, Long thirdId) {
        // 如果查询参数等于空,就设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置查询参数为空,表示查询所有的父分类
        map.put("condition", selectBean.getCondition().trim());
        map.put("searchText", selectBean.getSearchText().trim());
        map.put(ThirdValueBean.THIRDID, thirdId);
        // 创建需要返回的集合
        List<Object> cateVoList = new ArrayList<Object>();
        // 查询所有的分类的集合
        List<ThirdCateVo> parentList = this.thirdCateMapper.queryAllThirdCate(map);
        // 查询所有的分类信息
        List<ThirdCateVo> allCateList = this.thirdCateMapper.queryAllCateForList(thirdId);
        ThirdCateVo cate = null;
        ThirdCateVo cateVo = null;
        try {
            if (null != parentList && !parentList.isEmpty()) {
                // 循环父分类并进行整理
                for (int i = 0; i < parentList.size(); i++) {
                    // 从父分类中取出每一个分类
                    cate = parentList.get(i);
                    cateVo = new ThirdCateVo();
                    // 分类层级
                    cateVo.setCatGrade(cate.getCatGrade());
                    // 分类ID
                    cateVo.setCatId(cate.getCatId());
                    // 分类图片
                    cateVo.setCatImg(cate.getCatImg());
                    // 分类名称
                    cateVo.setCatName(cate.getCatName());
                    // 父分类ID
                    cateVo.setCatParentId(cate.getCatParentId());
                    // 分类排序
                    cateVo.setCatSort(cate.getCatSort());
                    // 商家ID
                    cateVo.setThirdId(cate.getThirdId());
                    // 商家名称
                    cateVo.setThirdName(cate.getThirdName());
                    // 创建时间
                    cateVo.setCatCreateTime(cate.getCatCreateTime());
                    // 根据取得的父分类ID和所有的分类列表计算分类实体,并添加到需要返回的对象中
                    cateVo.setCateVos(this.calcCateVo(cateVo.getCatId(), allCateList));
                    cateVoList.add(cateVo);
                }
            }
            // 返回整理好的集合
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            parentList = null;
            allCateList = null;
            cate = null;
            cateVo = null;
            map = null;
        }

    }

    /**
     * 根据一级分类查询下面的子分类
     *
     * @param parentId
     *            父分类ID
     * @param allCateList
     *            所有的分类的集合
     * @return
     */
    public List<ThirdCateVo> calcCateVo(Long parentId, List<ThirdCateVo> allCateList) {
        List<ThirdCateVo> cateVoList = new ArrayList<ThirdCateVo>();
        // 需要返回的分类实体
        ThirdCateVo cateVo = null;
        // 循环中的迭代分类
        ThirdCateVo cate = null;
        try {
            // 进行递归
            for (int i = 0; i < allCateList.size(); i++) {
                if (parentId.equals(allCateList.get(i).getCatParentId())) {
                    // 从父分类中取出每一个分类
                    cate = allCateList.get(i);
                    cateVo = new ThirdCateVo();
                    // 分类层级
                    cateVo.setCatGrade(cate.getCatGrade());
                    // 分类ID
                    cateVo.setCatId(cate.getCatId());
                    // 分类上传的图片
                    cateVo.setCatImg(cate.getCatImg());
                    // 分类名称
                    cateVo.setCatName(cate.getCatName());
                    // 父分类ID
                    cateVo.setCatParentId(cate.getCatParentId());
                    // 分类排序
                    cateVo.setCatSort(cate.getCatSort());
                    // 商家ID
                    cateVo.setThirdId(cate.getThirdId());
                    // 商家名称
                    cateVo.setThirdName(cate.getThirdName());
                    cateVo.setCatCreateTime(cate.getCatCreateTime());
                    // 根据取得的父分类ID和所有的分类列表计算分类实体,并添加到需要返回的对象中
                    cateVo.setCateVos(this.calcCateVo(cateVo.getCatId(), allCateList));
                    cateVoList.add(cateVo);
                }
            }
            // 返回计算好的所有的子分类列表
            return cateVoList;
        } finally {
            // 置空所有参数
            cateVoList = null;
            cateVo = null;
            cate = null;
        }
    }

    /**
     * 查询所有的商家类型
     *
     * @param map
     * @return
     */
    public List<ThirdCateVo> queryAllThirdCate(Map<String, Object> map) {
        return this.thirdCateMapper.queryAllThirdCate(map);
    }

    /**
     * 根据类型ID和商家ID查询所有的商家类型集合
     *
     * @param catId
     *            父分类ID
     * @param thirdId
     *            第三方ID
     * @return
     */
    public List<ThirdCate> getThirdCateByParentId(Long catId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ThirdValueBean.THIRDID, thirdId);
            return this.thirdCateMapper.queryThirdCateByParentId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据商家ID查询所有的商家类型
     *
     * @param thirdId
     *            第三方ID
     * @return
     */
    @Override
    public List<ThirdCate> getThirdCateByParentIdtwo(Long thirdId) {

        return this.thirdCateMapper.queryThirdCateByParentIdtwo(thirdId);
    }

    /**
     * 根据类型名称和商家ID查询对应的类型信息集合
     *
     * @param catName
     *            分类名称
     * @param thirdId
     *            第三方ID
     * @return
     */
    public List<ThirdCate> getThirdCateByCateNameAndGrade(String catName, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("catName", catName);
        map.put(ThirdValueBean.THIRDID, thirdId);
        List<ThirdCate> thirdCateList = new ArrayList<ThirdCate>();
        // 首先查最下级,如果查到的为空,就查询上一级,如果不为空就返回查询到的数据
        for (int i = 3; i >= 1; i--) {
            map.put("grade", i);
            thirdCateList = this.thirdCateMapper.queryThirdCateByCatNameAndGrade(map);
            if (null != thirdCateList && !thirdCateList.isEmpty()) {
                break;
            }
        }
        return thirdCateList;
    }

    /**
     * 把第三方的分类保存在Cookie中
     *
     * @param thirdCate
     *            带保存到cookie中的数据
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @throws UnsupportedEncodingException
     */
    public void saveToCookie(ThirdCateVo thirdCate, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        Cookie[] cookies = request.getCookies();
        String oldCatIdCookie = "";
        String oldCatNameCookie = "";
        Cookie cook;
        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && ThirdValueBean.USETHIRDCATIDINCOOKIE.equals(cookie.getName())) {
                        // 如果cookie中保存的记录中已经存在记录,就删除掉
                        oldCatIdCookie = URLDecoder.decode(cookie.getValue(), ThirdValueBean.UTF8);
                        if (oldCatIdCookie.indexOf("-" + thirdCate.getCatId() + "-") != -1) {
                            oldCatIdCookie = oldCatIdCookie.replace("-" + thirdCate.getCatId() + "-", "-");
                        }
                        if (oldCatIdCookie.indexOf("-" + thirdCate.getCatId() + "-") != -1) {
                            oldCatIdCookie = oldCatIdCookie.replace("-" + thirdCate.getCatId() + "-", "-");
                        }
                    }
                    if (null != cookie && ThirdValueBean.USETHIRDCATNAMEINCOOKIE.equals(cookie.getName())) {
                        oldCatNameCookie = URLDecoder.decode(cookie.getValue(), ThirdValueBean.UTF8);
                        if (oldCatNameCookie.indexOf("-" + thirdCate.getCatName() + "-") != -1) {
                            oldCatNameCookie = oldCatNameCookie.replace("-" + thirdCate.getCatName() + "-", "-");
                        }
                        if (oldCatNameCookie.indexOf("-" + thirdCate.getCatName() + "-") != -1) {
                            oldCatNameCookie = oldCatNameCookie.replace("-" + thirdCate.getCatName() + "-", "-");
                        }
                    }
                }
            }
            // 添加新的cookie记录
            if (oldCatIdCookie.indexOf("-" + thirdCate.getCatId()) == -1) {
                oldCatIdCookie += "-" + thirdCate.getCatId();
            }
            if (oldCatNameCookie.indexOf("-" + thirdCate.getCatName()) == -1) {
                oldCatNameCookie += "-" + thirdCate.getCatName();
            }
            // 保存catId cookie
            cook = new Cookie(ThirdValueBean.USETHIRDCATIDINCOOKIE, URLEncoder.encode(oldCatIdCookie, ThirdValueBean.UTF8));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath(request.getContextPath());
            response.addCookie(cook);
            // 保存catName cookie
            cook = new Cookie(ThirdValueBean.USETHIRDCATNAMEINCOOKIE, URLEncoder.encode(oldCatNameCookie, ThirdValueBean.UTF8));
            cook.setMaxAge(15 * 24 * 3600);
            cook.setPath(request.getContextPath());
            response.addCookie(cook);
        } finally {
            cookies = null;
            cook = null;
            oldCatIdCookie = null;
            oldCatNameCookie = null;
        }
    }

    /**
     * 从Cookie中获取分类ID
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return
     * @throws UnsupportedEncodingException
     */
    public List<ThirdCate> takeFormCookie(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        List<ThirdCate> catList = new ArrayList<ThirdCate>();
        Cookie[] cookies = request.getCookies();
        String catIdCookie = "";
        ThirdCate cate;
        String[] catIds;
        String[] catNames;
        String catNameCookie = "";
        try {
            if (null != cookies && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && ThirdValueBean.USETHIRDCATIDINCOOKIE.equals(cookie.getName())) {
                        // 如果cookie中保存的记录中已经存在记录,就删除掉
                        catIdCookie = URLDecoder.decode(cookie.getValue(), ThirdValueBean.UTF8);
                    }
                    if (null != cookie && ThirdValueBean.USETHIRDCATNAMEINCOOKIE.equals(cookie.getName())) {
                        // 如果cookie中保存的记录中已经存在记录,就删除掉
                        catNameCookie = URLDecoder.decode(cookie.getValue(), ThirdValueBean.UTF8);
                    }
                }
                // 循环取到的值,并处理
                if (null != catIdCookie && !"".equals(catIdCookie)) {
                    // 删除掉第一个-,并作分割处理
                    catIds = catIdCookie.substring(1, catIdCookie.length()).split("-");
                    catNames = catNameCookie.substring(1, catNameCookie.length()).split("-");
                    for (int i = 0; i < catIds.length; i++) {
                        cate = new ThirdCate();
                        cate.setCatId(Long.parseLong(catIds[i]));
                        cate.setCatName(catNames[i]);
                        catList.add(cate);
                    }
                }
            }
            return catList;
        } finally {
            catList = null;
            cookies = null;
            catIdCookie = null;
            cate = null;
            catIds = null;
            catNames = null;
            catNameCookie = null;
        }
    }

    /**
     * 清空 Cookie中的商家分类ID
     *
     * @param request
     *            请求对象
     * @param response
     *            响应对象
     * @return
     */
    public boolean clearThirdCateFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        try {
            if (null != cookies) {
                for (Cookie cookie : cookies) {
                    if (null != cookie && ThirdValueBean.USETHIRDCATIDINCOOKIE.equals(cookie.getName())) {
                        cookie = new Cookie(ThirdValueBean.USETHIRDCATIDINCOOKIE, null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                    if (null != cookie && ThirdValueBean.USETHIRDCATNAMEINCOOKIE.equals(cookie.getName())) {
                        cookie = new Cookie(ThirdValueBean.USETHIRDCATNAMEINCOOKIE, null);
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            }
            return true;
        } finally {
            cookies = null;
        }
    }

    public ThirdCateMapper getThirdCateMapper() {
        return thirdCateMapper;
    }

    @Resource(name = "ThirdCateMapper")
    public void setThirdCateMapper(ThirdCateMapper thirdCateMapper) {
        this.thirdCateMapper = thirdCateMapper;
    }

    /**
     * 根据分类ID 分类名称 商家ID 查询下面的分类信息
     *
     * @param thirdCateId
     * @param thirdCateName
     *            分类名称
     * @param thirdId
     * @return
     */

    @Override
    public List<ThirdCate> queryThirdSonCateByCateIdAndName(Long thirdCateId, String thirdCateName, Long thirdId) {
        // 装载查询信息
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 商家分类ID
        paramMap.put("cateParentId", thirdCateId);
        // 分类名称
        paramMap.put("cateName", thirdCateName);
        // 商家id
        paramMap.put("thirdId", thirdId);
        // 查询商家分类
        return this.thirdCateMapper.querySonCatByParm(paramMap);
    }

}
