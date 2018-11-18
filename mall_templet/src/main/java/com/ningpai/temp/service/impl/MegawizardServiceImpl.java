/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.temp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.temp.bean.Megawizard;
import com.ningpai.temp.dao.MegawizardMapper;
import com.ningpai.temp.service.MegawizardService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * /** SERVICE实现类-页面说明
 * 
 * @author NINGPAI-ZhuoYu
 * @since 2014年5月4日下午4:13:32
 */
@Service("MegawizardService")
public class MegawizardServiceImpl implements MegawizardService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MegawizardServiceImpl.class);

    private static final String LOGGERINFO = "查询页面说明错误:";

    @Resource(name = "MegawizardMapper")
    private MegawizardMapper megawizardMapper;

    @Override
    public int insert(Megawizard record) {
            record.setCreateTime(new Date());
            return megawizardMapper.insert(record);
    }

    @Override
    public PageBean selectByTempId(PageBean pb, Long tempId) {
            Map<String, Object> map = new HashMap<String, Object>();
            pb.setRows(megawizardMapper.selectCountByTempId(tempId.intValue()));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            map.put("tempId", tempId);
            pb.setList(megawizardMapper.selectByTempId(map));
        return pb;
    }

    @Override
    public int updateById(String[] id) {

            List<Long> ids = new ArrayList<Long>();
            for (int i = 0; i < id.length; i++) {
                ids.add(Long.valueOf(id[i]));
            }
        return megawizardMapper.updateById(ids);

    }

    @Override
    public int updateByIdToContent(Megawizard meg) {
            meg.setUpdateTime(new Date());
            return megawizardMapper.updateByIdToContent(meg);
    }

    @Override
    public Megawizard selectById(Long id) {
            return megawizardMapper.selectById(id);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.service.MegawizardService#selectByType(int,
     * java.lang.Long)
     */
    @Override
    public Megawizard selectByType(int typeId, Long tempId) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sort", typeId);
            map.put("tempId", tempId);
            return megawizardMapper.selectBySort(map);

    }

}
