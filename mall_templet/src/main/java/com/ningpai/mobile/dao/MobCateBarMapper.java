package com.ningpai.mobile.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.mobile.bean.MobCateBar;
import com.ningpai.mobile.vo.MobCateBarVo;

/**
 * DAO-移动版分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月19日上午11:56:30
 */
public interface MobCateBarMapper {

    /**
     * 查询移动端店铺页轮播大广告
     * 
     * @return 获取的移动端轮播大广告图片集合
     * @author zhanghl
     */
    List<ChannelAdver> selectStoreListImage(String userStatus);

    /**
     * 删除分类导航
     * 
     * @param cateBarId
     * @return
     */
    int deleteByPrimaryKey(Long cateBarId);

    /**
     * 添加分类导航
     * 
     * @param record
     * @return
     */
    int insert(MobCateBar record);

    /**
     * 添加分类导航-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(MobCateBar record);

    /**
     * 修改分类导航-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(MobCateBar record);

    /**
     * 修改分类导航
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(MobCateBar record);

    /**
     * 根据主键查询
     * 
     * @param cateBarId
     * @return
     */
    MobCateBar selectByPrimaryKey(Long cateBarId);

    /**
     * 根据父ID查询未删除的
     * 
     * @param parentId
     * @return
     */
    List<MobCateBar> selectByParentId(Long parentId);

    /**
     * 根据商品分类ID查询未删除的分类导航数量
     * 
     * @param cateId
     * @return
     */
    int selectCountByCateId(Long cateId);

    /**
     * 查询所有未删除、已启用的一级分类导航,前台用
     * 
     * @return
     */
    List<MobCateBarVo> selectAll();

    /**
     * 查询所有未删除、已启用的分类导航,前台用
     * 
     * @return
     */
    List<MobCateBar> selectAllForMobChoose();

    /**
     * 查询未删除的一级分类导航数量
     * 
     * @return
     */
    int selectCountByPb();

    /**
     * 分页查询未删除的一级分类导航
     * 
     * @param map
     * @return
     */
    List<Object> selectAllByPb(Map<String, Object> map);
}
