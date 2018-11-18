package com.ningpai.group.service;

import com.ningpai.group.bean.GroupImg;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 小组图片Service接口
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupImgSiteService {

    /**
     * 上传小组图片
     * 
     * @param img
     *            小组图片对象{@link com.ningpai.group.bean.GroupImg}
     * @param imgUrl
     *            图片上传路径{@link java.lang.String}
     * @param imgTitle
     *            图片上传标题{@link java.lang.String}
     * @return
     */
    int uploadGHroupImg(GroupImg img, String imgUrl, String imgTitle, int width, int height);

    /**
     * 查询最新上传的图片
     * 
     * @param groupImg
     *            小组图片{@link com.ningpai.group.bean.GroupImg}
     * @param count
     *            成功上传的数目{@link java.lang.Integer}
     * @return List
     */
    List<GroupImg> newUploadImg(GroupImg groupImg, Integer count);

    /**
     * 小组详情 小组相册显示的图片
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return list
     */
    List<GroupImg> selectGroupImgByLimit(GroupVo groupVo);

    /**
     * 小组相册中图片数目
     * 
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return int
     */
    int groupPhotoCountById(GroupVo groupVo);

    /**
     * 小组相册页
     * 
     * @param pb
     *            分页
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @param customerId
     *            用户ID {@link java.lang.Long}
     * @return pb
     */
    PageBean groupImgList(PageBean pb, GroupVo groupVo, Long customerId);

    /**
     * 批量保存上传图片
     * 
     * @param groupImgId
     *            图片Id {@link java.lang.Long}
     * @param groupImgTitle
     *            图片标题 {@link java.lang.String}
     * @param groupImgDes
     *            图片描述{@link java.lang.String}
     * @return int
     */
    int saveGroupImg(Long[] groupImgId, String[] groupImgTitle, String[] groupImgDes);

    /**
     * 删除小组图片
     * 
     * @param groupImgId
     *            图片ID{@link java.lang.Long}
     * @return int
     */
    int deleteGroupImgById(Long groupImgId);

    /**
     * 根据小组ID查询小组相片列表
     * 
     * @param groupId
     *            小组相片
     * @return List
     */
    List<GroupImg> groupImgList(Long groupId);

    /**
     * 根据小组相片Id查询小组相片详情
     * 
     * @param groupImgId
     *            小组相片Id
     * @return 小组相片
     */
    GroupImg groupImgById(Long groupImgId);

    /**
     * 编辑小组相片
     * 
     * @param img
     * @return
     */
    int editGroupImg(GroupImg img);

}
