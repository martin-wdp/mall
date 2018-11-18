/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.information.bean.Information;
import com.ningpai.information.dao.InformationMapper;
import com.ningpai.information.service.InformationService;
import com.ningpai.information.vo.InformationVo;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 资讯SERVICE实现
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月15日 9:20:35
 * @version
 */
@Service("InformationService")
public class InformationServiceImpl implements InformationService {

    private static final String TYPEID = "typeId";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";

    /** 资讯DAO */
    private InformationMapper informationMapper;

    public InformationMapper getInformationMapper() {
        return informationMapper;
    }

    @Resource(name = "InformationMapper")
    public void setInformationMapper(InformationMapper informationMapper) {
        this.informationMapper = informationMapper;
    }

    /**
     * 根据分页参数查询资讯列表<br/>
     *
     * @param pb
     *            分页工具类
     * @param selectBean
     *            查询工具类
     * @param typeId
     *            类型id
     * @return
     * @see com.ningpai.information.service.InformationService#queryByPageBean(com.ningpai.util.PageBean,com.ningpai.util.SelectBean,
     *      java.lang.Long)
     */
    public PageBean queryByPageBean(PageBean pb, SelectBean selectBean, Long typeId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("condition", selectBean.getCondition());
            map.put("searchText", selectBean.getSearchText());
            map.put(TYPEID, typeId);
            /** 查询数据的总行数并设置到PageBean中 */
            pb.setRows(this.informationMapper.queryTotalCount(map));
            /** 设置开始行数 */
            map.put(STARTROWNUM, pb.getStartRowNum());
            /** 设置结束行数 */
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 根据分页参数查询资讯列表 */
            pb.setList(this.informationMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 根据主键删除资讯
     *
     * @param infoId
     *            资讯编号
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public int delInfo(Long infoId) {
        /** 根据主键删除 */
        return informationMapper.deleteByPrimaryKey(infoId);
    }

    /**
     * 批量删除资讯
     *
     * @param ids
     *            资讯类型编号数组
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public void batchDelInfo(Long[] ids) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                /** 根据主键遍历删除资讯 */
                delInfo(ids[i]);
            }
        }
    }

    /**
     * 添加资讯
     *
     * @param record
     *            资讯对象
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public int saveInfo(Information record) {
        /** 获取当前时间 */
        Date date = new Date();
        /** 设置删除标记为0 */
        record.setDelflag("0");
        /** 设置创建时间为当前时间 */
        record.setCreateDate(date);
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(date);
        /** 调用添加方法并返回结果 */
        return informationMapper.insert(record);
    }

    /**
     * 更新资讯
     *
     * @param record
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public int updateInfo(Information record) {
        /** 设置更新时间为当前时间 */
        record.setUpdateDate(new Date());
        /** 调用更新方法并返回结果 */
        return informationMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键查找资讯
     *
     * @param infoId
     *            资讯编号
     * @return Information 资讯类对象
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public Information selectByPrimaryKey(Long infoId) {
        /** 根据主键查找资讯 */
        return informationMapper.selectByPrimaryKey(infoId);
    }

    /**
     * 查找所有的资讯
     *
     * @return List<Information> 资讯集合
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    public List<InformationVo> selectAll() {
        /** 查找所有的资讯 */
        return informationMapper.selectAll();
    }

    /**
     * 根据栏目ID获取文章数量验证栏目是否能删除
     *
     * @param infoTypeId
     * @return
     * @see com.ningpai.information.service.InformationService#checkDelInfoTypeByInfoCount(java.lang.Long)
     */
    @Override
    public boolean checkDelInfoTypeByInfoCount(Long infoTypeId) {
        /** 判断结果是否大于0，大于0返回false 否则返回true */
        return informationMapper.selectInfoCountByTypeId(infoTypeId) > 0 ? false : true;
    }

    /**
     * 根据文章标题查询文章数量判断标题是否存在
     *
     * @param title
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String)
     */
    @Override
    public boolean checkAddInfoByTitle(String title) {
        /** 判断结果是否大于0，大于0返回false 否则返回true */
        return informationMapper.selectInfoCountByTitle(title) > 0 ? false : true;
    }

    /**
     * 根据文章标题查询文章数量判断标题是否存在<br/>
     * 根据文章ID查询出文章标题，判断老标题和新标题是否一样<br>
     * 如果一样直接返回true，不一样查询数量判断是否存在
     *
     * @param title
     * @param infoId
     *            文章ID
     * @return
     * @see com.ningpai.information.service.InformationService#checkAddInfoByTitle(java.lang.String,
     *      java.lang.Long)
     */
    @Override
    public boolean checkAddInfoByTitle(String title, Long infoId) {
        /** 执行查询方法并把结果赋值给Information实体类 */
        Information info = informationMapper.selectByPrimaryKey(infoId);
        if (null != info) {
            if (info.getTitle().equals(title)) {
                return true;
            } else {
                /** 判断结果是否大于0，大于0返回false 否则返回true */
                return informationMapper.selectInfoCountByTitle(title) > 0 ? false : true;
            }
        } else {
            return false;
        }
    }

    /**
     * 根据栏目ID查询文章
     *
     * @param infoTypeId
     * @return
     * @see com.ningpai.information.service.InformationService#selectByInfoType(java.lang.Long)
     */
    @Override
    public List<InformationVo> selectByInfoType(Long infoTypeId) {
        /** 执行查询方法并返回结果 */
        return informationMapper.selectByInfoType(infoTypeId);
    }

    /**
     * 根据分页参数查询资讯列表-前台用<br/>
     *
     * @param pb
     *            分页工具类
     * @param selectBean
     *            查询工具类
     * @param typeId
     *            类型id
     * @return
     * @see com.ningpai.information.service.InformationService#queryByPageBeanForSite(com.ningpai.util.PageBean,com.ningpai.util.SelectBean,java.lang.Long)
     */
    public PageBean queryByPageBeanForSite(PageBean pb, SelectBean selectBean, Long typeId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("condition", selectBean.getCondition());
            map.put("searchText", selectBean.getSearchText());
            map.put(TYPEID, typeId);
            map.put("isShow", "1");
//            map.put("isThirdNews", "0");
//            map.put("temp3", "0");
            /** 查询数据的总行数并设置到PageBean中 */
            pb.setRows(this.informationMapper.queryTotalCount(map));
            /** 设置开始行数 */
            map.put(STARTROWNUM, pb.getStartRowNum());
            /** 设置结束行数 */
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 根据分页参数查询资讯列表 */
            pb.setList(this.informationMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        /** 返回结果 */
        return pb;
    }

    /**
     * 根据分页参数查询资讯列表-移动版前台用<br/>
     *
     * @param pb
     *            分页工具类
     * @param selectBean
     *            查询工具类
     * @param typeId
     *            类型id
     * @return
     * @see com.ningpai.information.service.InformationService#queryByPageBeanForMobSite(com.ningpai.util.PageBean,
     *      com.ningpai.util.SelectBean, java.lang.Long)
     */
    @Override
    public PageBean queryByPageBeanForMobSite(PageBean pb, SelectBean selectBean, Long typeId) {
        /** 定义一个HashMap集合 */
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(TYPEID, typeId);
            map.put("isShow", "1");
            map.put("isThirdNews", "0");
            map.put("temp3", "1");
            /** 查询数据的总行数并设置到PageBean中 */
            pb.setRows(this.informationMapper.queryTotalCount(map));
            /** 设置开始行数 */
            map.put(STARTROWNUM, pb.getStartRowNum());
            /** 设置结束行数 */
            map.put(ENDROWNUM, pb.getEndRowNum());
            /** 根据分页参数查询资讯列表 */
            pb.setList(this.informationMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        /** 返回结果 */
        return pb;
    }
}
