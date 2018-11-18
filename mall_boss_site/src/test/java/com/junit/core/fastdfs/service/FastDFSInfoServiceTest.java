package com.junit.core.fastdfs.service;

import com.alibaba.fastjson.JSON;
import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.fastdfs.dao.FastDFSInfoMapper;
import com.ningpai.fastdfs.service.FastDFSInfoService;
import com.ningpai.fastdfs.service.impl.FastDFSInfoServiceImpl;
import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import java.util.List;

/**
 * SERVICE-FastDFS设置信息 测试
 */
public class FastDFSInfoServiceTest extends UnitilsJUnit3{

    /**
     * 需要测试的Service
     */
    @TestedObject
    private FastDFSInfoService fastDFSInfoService = new FastDFSInfoServiceImpl();

    /**
     * 模拟
     */
    @InjectIntoByType
    Mock<FastDFSInfoMapper> fastDFSInfoMapperMock;
    /**
     * JS数据
     */
    @FileContent("fastDfsInfoList.js")
    private String fastDfsInfoListJs;


    /**
     * 共享数据
     */
    List<FastDFSInfo> fastDFSInfoList;
    /**
     * 初始化
     */
    @Override
    public void setUp(){
        fastDFSInfoList = JSON.parseArray(fastDfsInfoListJs, FastDFSInfo.class);
    }



    /**
     * 获取当前使用的FastDFS设置
     */
    @Test
    public void  testGetFastDFSInfoByCurr(){
        fastDFSInfoMapperMock.returns(fastDFSInfoList).selectAllFastDFS();
        assertNotNull(fastDFSInfoService.getFastDFSInfoByCurr());
    }

    /**
     * 修改FastDFS设置
     */
    @Test
    public void  testUpdateFastDFSInfo(){
        fastDFSInfoMapperMock.returns(1).updateByPrimaryKeySelective(fastDFSInfoList.get(0));
        assertEquals(1, fastDFSInfoService.updateFastDFSInfo(fastDFSInfoList.get(0)));
    }

}
