package com.ningpai.OthKeyWord.service.impl;

import com.ningpai.OthKeyWord.bean.OthKeyWord;
import com.ningpai.OthKeyWord.dao.OthKeyWordMapper;
import com.ningpai.OthKeyWord.service.OthKeyWordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by pl on 2016/1/23.
 * Desc:
 */
@Service("othKeyWordService")
public class OthKeyWordServiceImpl implements OthKeyWordService {

    @Resource(name = "othKeyWordMapper")
    private OthKeyWordMapper othKeyWordMapper;

    private String ALLOTHKEYWORD="allOthKeyWord";

    @Override
    public List<OthKeyWord> GetAllOthKey() {
        /*List<OthKeyWord> list=EHCacheUtil.get(ALLOTHKEYWORD);
        if (list==null)
        {
            list=othKeyWordMapper.GetAllOthKey();
         *//*  Collections.sort(list, new Comparator<GoodsCateVo>() {
                public int compare(GoodsCateVo arg0, GoodsCateVo arg1) {
                    return arg0.getCatId().compareTo(arg1.getCatId());
                }
            });*//*
            try {
                EHCacheUtil.setValue("AllGoodsCateCache",ALLOTHKEYWORD,list);
            } catch (Exception e) {
                e.printStackTrace();
                //LOGGER.error("EHCache缓存异常");
            }
        }*/
        return othKeyWordMapper.GetAllOthKey();
    }
}
