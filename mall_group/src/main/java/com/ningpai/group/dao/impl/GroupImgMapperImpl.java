/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.GroupImg;
import com.ningpai.group.dao.GroupImgMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 2014年5月27日 下午2:41:11
 * @author qiyuanyuan
 */

@Repository("GroupImgMapper")
public class GroupImgMapperImpl extends BasicSqlSupport implements GroupImgMapper {

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long groupImgId) {
        return this.delete("com.ningpai.group.mapper.GroupImgMapper.deleteByPrimaryKey", groupImgId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#insert(com.ningpai.group.bean.GroupImg )
     */
    public int insert(GroupImg groupImg) {
        return this.insert("com.ningpai.group.mapper.GroupImgMapper.insert", groupImg);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper
     */
    public int insertSelective(GroupImg groupImg) {
        return this.insert("com.ningpai.group.mapper.GroupImgMapper.insertSelective", groupImg);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#selectByPrimaryKey(java.lang.Long)
     */
    public GroupImg selectByPrimaryKey(Long groupImgId) {
        return this.selectOne("com.ningpai.group.mapper.GroupImgMapper.selectByPrimaryKey", groupImgId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper
     */
    public int updateByPrimaryKeySelective(GroupImg groupImg) {
        return this.update("com.ningpai.group.mapper.GroupImgMapper.updateByPrimaryKeySelective", groupImg);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper
     */
    public int updateByPrimaryKey(GroupImg groupImg) {
        return this.update("com.ningpai.group.mapper.GroupImgMapper.updateByPrimaryKey", groupImg);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper
     */
    public List<GroupImg> selectNewGroupImg(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupImgMapper.selectNewGroupImg", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#updateGroupImg(java.util.List)
     */
    public int updateGroupImg(List<GroupImg> imgs) {
        int flag = 0;
        for (GroupImg groupImg : imgs) {
            flag += this.update("com.ningpai.group.mapper.GroupImgMapper.editgroupimg", groupImg);
        }
        return flag;
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#selectGrougImgList(java.util.Map)
     */
    public List<Object> selectGrougImgList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupImgMapper.selectGroupImgList", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#GrougImgList(java.util.Map)
     */
    public List<GroupImg> GrougImgList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupImgMapper.selectGroupImgList", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper
     */
    public int selectGroupImgListCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.GroupImgMapper.selectGroupImgListCount", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#deleteGroupImgById(java.lang.Long)
     */
    public int deleteGroupImgById(Long imgId) {
        return this.update("com.ningpai.group.mapper.GroupImgMapper.deleteGroupImgById", imgId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#groupPhotoCountById(java.lang.Long)
     */
    public int groupPhotoCountById(Long groupId) {
        return this.selectOne("com.ningpai.group.mapper.GroupImgMapper.groupPhotoCountById", groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#selectGroupImgByLimit(java.util.Map)
     */
    public List<GroupImg> selectGroupImgByLimit(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.GroupImgMapper.selectGroupImgByLimit", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupImgMapper#groupImgDetail(java.lang.Long)
     */
    public GroupImg groupImgDetail(Long groupImgId) {
        return this.selectOne("com.ningpai.group.mapper.GroupImgMapper.groupImgDetail", groupImgId);
    }

}
