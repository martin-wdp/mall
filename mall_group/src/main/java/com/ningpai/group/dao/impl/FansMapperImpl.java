package com.ningpai.group.dao.impl;


import com.ningpai.group.bean.Fans;
import com.ningpai.group.dao.FansMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 粉丝接口的实现类
 * @author qiyuanyuan
 *
 */
@Repository("FansMapper")
public class FansMapperImpl extends BasicSqlSupport implements FansMapper{

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long fansId) {
        
        return this.delete("com.ningpai.dao.FansMapper.deleteByPrimaryKey",fansId);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#insert(com.ningpai.group.bean.Fans)
     */
    public int insert(Fans record) {
        
        return this.insert("com.ningpai.dao.FansMapper.insert", record);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#insertSelective(com.ningpai.group.bean.Fans)
     */
    public int insertSelective(Fans record) {
        
        return this.insert("com.ningpai.dao.FansMapper.insertSelective", record);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#selectByPrimaryKey(java.lang.Long)
     */
    public Fans selectByPrimaryKey(Long fansId) {
        
        return this.selectOne("com.ningpai.dao.FansMapper.selectByPrimaryKey", fansId);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#updateByPrimaryKeySelective(com.ningpai.group.bean.Fans)
     */
    public int updateByPrimaryKeySelective(Fans record) {
        
        return this.update("com.ningpai.dao.FansMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#updateByPrimaryKey(com.ningpai.group.bean.Fans)
     */
    public int updateByPrimaryKey(Fans record) {
        
        return this.update("com.ningpai.dao.FansMapper.updateByPrimaryKey", record);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#fansFlag(java.util.Map)
     */
    public Fans fansFlag(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.dao.FansMapper.fansFlag", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#guanzhu(java.util.Map)
     */
    public int guanzhu(Map<String, Object> paramMap) {
        
        return this.update("com.ningpai.dao.FansMapper.guanzhu", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#fansi(java.util.Map)
     */
    public int fansi(Map<String, Object> paramMap) {
        
        return this.update("com.ningpai.dao.FansMapper.fansi", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#cancelGuanZhu(java.util.Map)
     */
    public int cancelGuanZhu(Map<String, Object> paramMap) {
        
        return this.update("com.ningpai.dao.FansMapper.cancelguanzhu",paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#cancelFansi(java.util.Map)
     */
    public int cancelFansi(Map<String, Object> paramMap) {
        
        return this.update("com.ningpai.dao.FansMapper.cancelfansi",paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#delFansFlag(java.util.Map)
     */
    public int delFansFlag(Map<String, Object> paramMap) {
        
        return this.delete("com.ningpai.dao.FansMapper.delFans", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#delOFansFlag(java.util.Map)
     */
    public int delOFansFlag(Map<String, Object> paramMap) {
        
        return this.delete("com.ningpai.dao.FansMapper.delOFans", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#fansList(java.util.Map)
     */
    public List<Fans> fansList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.dao.FansMapper.hisfans", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#eachfansByCustomerId(java.util.Map)
     */
    public List<Object> eachfansByCustomerId(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.dao.FansMapper.selectFansByCustomerId", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#eachfansCountByCustomerId(java.util.Map)
     */
    public int eachfansCountByCustomerId(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.dao.FansMapper.selectFansCountByCustomerId", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#selectMyMtual(java.util.Map)
     */
    public List<Object> selectMyMtual(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.dao.FansMapper.selectMyMtual", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#myMtualCount(java.util.Map)
     */
    public int myMtualCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.dao.FansMapper.selectMyMtualCount", paramMap);
    }

    /**
     * 
     * @see com.ningpai.group.dao.FansMapper#selectFocus(java.util.Map)
     */
    public List<Fans> selectFocus(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.dao.FansMapper.selectFocus", paramMap);
    }

}
