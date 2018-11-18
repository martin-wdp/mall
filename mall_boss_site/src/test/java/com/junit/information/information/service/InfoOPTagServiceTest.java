package com.junit.information.information.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.unitils.UnitilsJUnit3;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;
import org.unitils.io.annotation.FileContent;
import org.unitils.mock.Mock;

import com.alibaba.fastjson.JSON;
import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.dao.InfoOPTagMapper;
import com.ningpai.information.dao.InformationOnePageMapper;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.information.service.impl.InfoOPTagServiceImpl;
import com.ningpai.util.PageBean;

/**
 *
 * Created by lih on 2015/9/9.
 */
public class InfoOPTagServiceTest extends UnitilsJUnit3 {

    @InjectIntoByType
    Mock<InfoOPTagMapper> infoOPTagMapper;

    /** 资讯单页DAO */
    @InjectIntoByType
    Mock<InformationOnePageMapper>  infoOPMapper;

    /**
     * 需要测试的接口类
     */
    @TestedObject
    private InfoOPTagService infoOPTaService = new InfoOPTagServiceImpl();

    /**
     * 共享数据
     */
    InfoOPTag infoOPTag;

    //js
    @FileContent("information.js")
    private String informationJs;
    //js
    @FileContent("informationList.js")
    private String informationListJs;



    //测试数据
    private List<InfoOPTag> list;
    /**
     * 初始化数据
     */
    @Override
    public void setUp(){
        //创建集合对象
        list = JSON.parseArray(informationListJs, InfoOPTag.class);
        infoOPTag=JSON.parseObject(informationJs,InfoOPTag.class);
    }


        /**
         * 根据主键删除
         * @return
         */
        @Test
        public void testDeleteInfoOPTagById(){
            //设置查询条件
            InfoOPTag infoOPTag=new InfoOPTag();
            // 调用dao层修改方法
            infoOPMapper.returns(1).updateTagByTagId(1L);
            //根据infoOpTagId查询数据
            infoOPTagMapper.returns(infoOPTag).selectByPrimaryKey(1L);
            // 设置删除标记为1
            infoOPTag.setDelflag("1");
            // 设置更新时间为当前时间
            infoOPTag.setUpdateDate(new Date());
            // 执行修改方法
            infoOPTagMapper.returns(1).updateByPrimaryKeySelective(infoOPTag);
            assertEquals(1, infoOPTaService.deleteInfoOPTagById(1L));
        }

        /**
         * 添加
         * @return
         */
        @Test
        public void testCreateInfoOPTag() {
            InfoOPTag infoOPTag=new InfoOPTag();
            infoOPTag.setInfoopTagId(4L);
            //设置模板id
            infoOPTag.setTemp1("1");
            //设置创建时间
            infoOPTag.setCreateDate(new Date());
            //设置删除标记
            infoOPTag.setDelflag("0");
            //设置排序
            infoOPTag.setSort(4);
            //设置名称
            infoOPTag.setName("测试4");
            //设置修改时间
            infoOPTag.setUpdateDate(new Date());
            //设定值
            infoOPTagMapper.returns(1).insertSelective(infoOPTag);
            assertEquals(1,infoOPTaService.createInfoOPTag(infoOPTag));
        }

        /**
         * 修改
         *
         * @param record
         * @return
         */
        @Test
        public void testUpdateInfoOPTag(InfoOPTag record) {
            InfoOPTag infoOPTag=new InfoOPTag();
            infoOPTag.setInfoopTagId(4L);
            //设置模板id
            infoOPTag.setTemp1("1");
            //设置创建时间
            infoOPTag.setCreateDate(new Date());
            //设置删除标记
            infoOPTag.setDelflag("0");
            //设置排序
            infoOPTag.setSort(4);
            //设置名称
            infoOPTag.setName("测试4");
            //设置修改时间
            infoOPTag.setUpdateDate(new Date());
            //设定值
            infoOPTagMapper.returns(1).updateByPrimaryKeySelective(infoOPTag);
            assertEquals(1,infoOPTaService.updateInfoOPTag(infoOPTag));
        }

        /**
         * 根据模板ID查询
         * @return
         */
        @Test
        public void testFindAllInfoOPTag() {
            //模拟dao
            infoOPTagMapper.returns(list).selectAll("1");
            //判断是否正确
            assertEquals(3, infoOPTaService.findAllInfoOPTag("1").size());
        }

        /**
         * 查询所有
         *
         * @return
         */
        @Test
        public void testFindAllTag() {
            infoOPTagMapper.returns(list).findAll();
            assertEquals(3, infoOPTaService.findAllTag().size());

        }

        /**
         * 分页查询
         * @return
         */
        @Test
        public void testFindAllTagPage() {
            PageBean pb =new PageBean();
            /** 设置pageBean的行数 */
            pb.setRows(infoOPTagMapper.returns(3).findAllPagecount());
            /** 定义一个HashMap集合 */
            Map<String, Object> map = new HashMap<String, Object>();
            /** 设置开始行数 */
            map.put("startRowNum", 0);
            /** 设置结束行数 */
            map.put("endRowNum", 15);
            /** 设置pageBean的集合数据 */
            infoOPTagMapper.returns(list).findAllPage(map);
            /** 返回结果 */
            assertEquals(3, infoOPTaService.findAllTagPage(pb).getList().size());

        }





}
