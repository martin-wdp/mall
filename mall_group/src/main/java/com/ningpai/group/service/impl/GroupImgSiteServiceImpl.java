package com.ningpai.group.service.impl;

import com.ningpai.group.bean.GroupImg;
import com.ningpai.group.dao.CustomerReplyMapper;
import com.ningpai.group.dao.GroupImgMapper;
import com.ningpai.group.service.GroupImgSiteService;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 小组图片Service接口实现
 * 
 * @author qiyuanyuan
 * 
 */
@Service("GroupImgSiteService")
public class GroupImgSiteServiceImpl implements GroupImgSiteService {

    private static final String GROUPID = "groupId";

    /**
     * 小组图片DAO
     */
    private GroupImgMapper imgMapper;

    /**
     * 客户回复DAO
     */
    private CustomerReplyMapper customerReplyMapper;

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
    public int uploadGHroupImg(GroupImg img, String imgUrl, String imgTitle, int width, int height) {
        // 标题长度
        int i = imgTitle.lastIndexOf(".");
        // 标题名称
        String imageName = null;
        if ((i > -1) && (i < (imgTitle.length()))) {
            imageName = imgTitle.substring(0, i);
        }
        // 给标题名称赋值
        img.setGroupImgTitle(imageName);
        // 图片地址
        img.setGroupImgUrl(imgUrl);
        // 图片创建时间
        img.setGroupImgCreateTime(new Date());
        // 图片状态 0 未删除 默认值
        img.setGroupImgDelFlag("0");
        // 图片宽度
        img.setGroupImgWidth(width);
        // 图片高度
        img.setGroupImgHeight(height);
        // 添加
        return this.imgMapper.insertSelective(img);
    }

    /**
     * 查询最新上传的图片
     *
     * @param groupImg
     *            小组图片{@link com.ningpai.group.bean.GroupImg}
     * @param count
     *            成功上传的数目{@link java.lang.Integer}
     * @return List
     */
    public List<GroupImg> newUploadImg(GroupImg groupImg, Integer count) {
        // 创建容器 查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 小组编号
        paramMap.put(GROUPID, groupImg.getGroupId());
        // 用户编号
        paramMap.put("customerId", groupImg.getCustomerId());
        // 数量
        paramMap.put("count", count);
        // 查询最新上传的小组图片
        return this.imgMapper.selectNewGroupImg(paramMap);
    }

    /**
     * 小组详情 小组相册显示的图片
     *
     * @param group
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return list
     */
    public List<GroupImg> selectGroupImgByLimit(GroupVo group) {
        // 创建容器 查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 小组编号
        paramMap.put(GROUPID, group.getGroupId());
        // 分页行数
        paramMap.put("number", 6);
        // 根据限制条件查询小组图片
        return this.imgMapper.selectGroupImgByLimit(paramMap);
    }

    /**
     * 小组相册中图片数目
     *
     * @param groupVo
     *            小组Vo{@link com.ningpai.group.vo.GroupVo}
     * @return int
     */
    public int groupPhotoCountById(GroupVo groupVo) {
        // 小组编号
        Long groupId = groupVo.getGroupId();
        // 根据小组ID查询小组图片数量
        return this.imgMapper.groupPhotoCountById(groupId);
    }

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
    public PageBean groupImgList(PageBean pb, GroupVo groupVo, Long customerId) {
        // 创建封装容器 查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 小组编号
        paramMap.put(GROUPID, groupVo.getGroupId());
        // 用户编号
        paramMap.put("customerId", customerId);
        // 根据条件查询小组图片数量
        int rows = this.imgMapper.selectGroupImgListCount(paramMap);
        // 给分页实体类填充 行数
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            // 给分页实体类填充 0
            pb.setRows(0);
        }
        // 分页实体类
        paramMap.put("pbFlag", "pb");
        // 分页行数2
        pb.setPageSize(20);
        // 开始
        paramMap.put("startRowNum", pb.getStartRowNum());
        // 结束
        paramMap.put("endRowNum", pb.getEndRowNum());
        // 根据条件查询小组图片
        pb.setList(imgMapper.selectGrougImgList(paramMap));
        // 返回分页实体类
        return pb;
    }

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
    public int saveGroupImg(Long[] groupImgId, String[] groupImgTitle, String[] groupImgDes) {
        // 存放图片的容器
        List<GroupImg> imgs = new ArrayList<GroupImg>();
        for (int i = 0; i < groupImgId.length; i++) {
            // 创建小组图片实体
            GroupImg img = new GroupImg();
            // 图片编号
            img.setGroupImgId(groupImgId[i]);
            // 图片标题
            if (groupImgTitle != null && groupImgTitle.length > 0) {
                img.setGroupImgTitle(groupImgTitle[i]);
            }
            // 图片描述
            if (groupImgDes != null && groupImgDes.length > 0) {
                img.setGroupImgDes(groupImgDes[i]);
            }
            // 修改时间
            img.setGroupImgModifyTime(new Date());
            // 加入容器
            imgs.add(img);
        }
        // 修改小组图片
        int flag = imgMapper.updateGroupImg(imgs);
        if (flag > 0) {
            // 成功
            flag = 1;
        } else {
            // 失败
            flag = 0;
        }
        return flag;
    }

    /**
     * 删除小组图片
     *
     * @param groupImgId
     *            图片ID{@link java.lang.Long}
     * @return int
     */
    public int deleteGroupImgById(Long groupImgId) {
        // 根据图片ID删除小组图片
        int f = imgMapper.deleteGroupImgById(groupImgId);
        if (f > 0) {
            // 创建容器
            Map<String, Object> paramMap = new HashMap<String, Object>();
            // 关联id
            paramMap.put("replyShipId", groupImgId);
            // 回复类型
            paramMap.put("replyType", "1");
            // 删除图片下的所有评论
            customerReplyMapper.delallReplyByShipId(paramMap);
        }
        return f;
    }

    /**
     * 根据小组ID查询小组相片列表
     *
     * @param groupId
     *            小组相片
     * @return List
     */
    public List<GroupImg> groupImgList(Long groupId) {
        // 创建容器
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 填充小组编号
        paramMap.put(GROUPID, groupId);
        // 根据小组编号查询小组相片
        return this.imgMapper.GrougImgList(paramMap);
    }

    /**
     * 根据小组相片Id查询小组相片详情
     *
     * @param groupImgId
     *            小组相片Id
     * @return 小组相片
     */
    public GroupImg groupImgById(Long groupImgId) {
        // 根据小组相片Id查询小组相片详情
        return this.imgMapper.groupImgDetail(groupImgId);
    }

    /**
     * 编辑小组相片
     *
     * @param img
     * @return
     */
    public int editGroupImg(GroupImg img) {
        // 根据小组相片Id查询相片详情
        GroupImg groupImg = imgMapper.groupImgDetail(img.getGroupImgId());
        // 图片标题
        img.setGroupImgTitle(img.getGroupImgTitle());
        // 图片描述
        img.setGroupImgDes(img.getGroupImgDes());
        // 图片ID
        img.setGroupId(groupImg.getGroupId());
        // 用户ID
        img.setCustomerId(groupImg.getCustomerId());
        // 创建时间
        img.setGroupImgCreateTime(groupImg.getGroupImgCreateTime());
        // 修改时间
        img.setGroupImgModifyTime(new Date());
        // 图片地址
        img.setGroupImgUrl(groupImg.getGroupImgUrl());
        // 图片状态 是否删除
        img.setGroupImgDelFlag("0");
        // 修改小组相片
        return this.imgMapper.updateByPrimaryKey(img);
    }

    public GroupImgMapper getImgMapper() {
        return imgMapper;
    }

    @Resource(name = "GroupImgMapper")
    public void setImgMapper(GroupImgMapper imgMapper) {
        this.imgMapper = imgMapper;
    }

    public CustomerReplyMapper getCustomerReplyMapper() {
        return customerReplyMapper;
    }

    @Resource(name = "CustomerReplyMapper")
    public void setCustomerReplyMapper(CustomerReplyMapper customerReplyMapper) {
        this.customerReplyMapper = customerReplyMapper;
    }

}
