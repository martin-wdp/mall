package com.ningpai.system.service.impl;

import javax.annotation.Resource;

import com.ningpai.system.bean.EmpowerLog;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import com.ningpai.system.bean.Empower;
import com.ningpai.system.dao.EmpowerMapper;
import com.ningpai.system.service.EmpowerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 权限显示服务层实现类
 * 
 * @author xiaogu
 * 
 */
@Service("EmpowerService")
public class EmpowerServiceImpl implements EmpowerService {
    /** spring注解 */
    @Resource(name = "EmpowerMapper")
    private EmpowerMapper empowerMapper;

    /**
     * 查询权限信息
     *
     * @param pageBean
     * @return Empower
     */
    @Override
    public PageBean list(PageBean pageBean) {
        // 设置总行数
        pageBean.setRows(empowerMapper.listCount());

        Map<String, Object> map = new HashMap<String, Object>();
        // 设置分页参数
        map.put("start", pageBean.getStartRowNum());
        map.put("end", pageBean.getEndRowNum());
        // 设置结果集
        pageBean.setList(empowerMapper.list(map));
        return pageBean;
    }

    /**
     * 插入信息
     *
     * @param empower
     *            权限信息
     * @return 添加结果
     */
    @Override
    public int insertEmpower(Empower empower) {
        // 自动生成key
        String base = "ABCDEFGHIJKLMNGPQRSTUVWXYZ";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 20; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        // 设置key
        empower.setAppKey(sb.toString());
        return empowerMapper.insertEmpower(empower);
    }

    /**
     * 修改角色
     *
     * @param status
     *            0：开启 1：关闭
     * @param appId
     *            主键id
     * @return 修改结果
     */
    @Override
    public int updateEmpower(String status, Long appId) {
        // 设置参数容器
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", status);
        map.put("appId", appId);
        return empowerMapper.updateEmpower(map);
    }

    /**
     * 删除角色
     *
     * @param appId
     *            主键id
     * @return
     */
    @Override
    public int delEmpower(Long appId) {
        return empowerMapper.delEmpower(appId);
    }

    /**
     * 验证名字是否存在
     *
     * @param appUserName
     *            用户名
     * @return
     */
    @Override
    public int checkUserName(String appUserName) {
        return empowerMapper.checkName(appUserName);
    }

    /**
     * 查询日志列表
     *
     * @param empowerId
     *            用户id
     * @return 某用户的日志列表
     */
    @Override
    public List<EmpowerLog> selectLog(Long empowerId) {
        return empowerMapper.selectLog(empowerId);
    }
}
