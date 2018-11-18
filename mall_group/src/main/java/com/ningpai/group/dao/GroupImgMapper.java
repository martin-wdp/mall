package com.ningpai.group.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.group.bean.GroupImg;

/**
 * 小组图片DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupImgMapper {
    /**
     * 根据主键删除
     * 
     * @param:groupImgId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    int deleteByPrimaryKey(Long groupImgId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupImg 对象{@link com.ningpai.group.bean.GroupImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    int insert(GroupImg groupImg);

    /**
     * 插入，空属性不会插入
     * 
     * @param:groupImg 对象{@link com.ningpai.group.bean.GroupImg}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    int insertSelective(GroupImg groupImg);

    /**
     * 根据主键查询
     * 
     * @param:groupImgId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    GroupImg selectByPrimaryKey(Long groupImgId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupImg 对象{@link com.ningpai.group.bean.GroupImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    int updateByPrimaryKeySelective(GroupImg groupImg);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupImg 对象{@link com.ningpai.group.bean.GroupImg}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:36
     */
    int updateByPrimaryKey(GroupImg groupImg);

    /**
     * 查询最新上传的小组图片
     * 
     * @param paramMap
     * @return
     */
    List<GroupImg> selectNewGroupImg(Map<String, Object> paramMap);

    /**
     * 批量修改图片
     * 
     * @param img
     *            小组图片 {@link com.ningpai.group.bean.GroupImg}
     * @return int
     */
    int updateGroupImg(List<GroupImg> imgs);

    /**
     * 根据条件查询小组图片
     * 
     * @param paramMap
     * @return List
     */
    List<Object> selectGrougImgList(Map<String, Object> paramMap);

    /**
     * 根据条件查询小组图片
     * 
     * @param paramMap
     * @return List
     */
    List<GroupImg> GrougImgList(Map<String, Object> paramMap);

    /**
     * 根据条件查询小组图片数量
     * 
     * @param paramMap
     * @return
     */
    int selectGroupImgListCount(Map<String, Object> paramMap);

    /**
     * 根据图片ID删除小组图片
     * 
     * @param imgId
     *            主键 {@link java.lang.Long}
     * @return int
     */
    int deleteGroupImgById(Long imgId);

    /**
     * 根据小组ID查询小组图片数量
     * 
     * @param groupId
     *            小组ID {@link java.lang.Long}
     * @return int
     */
    int groupPhotoCountById(Long groupId);

    /**
     * 根据限制条件查询小组图片
     * 
     * @param paramMap
     *            查询参数
     * @return List
     */
    List<GroupImg> selectGroupImgByLimit(Map<String, Object> paramMap);

    /**
     * 根据小组相片Id查询相片详情
     * 
     * @param groupImgId
     *            相片ID{@link java.lang.Long}
     * @return 对象
     */
    GroupImg groupImgDetail(Long groupImgId);
}
