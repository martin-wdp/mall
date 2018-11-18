package com.ningpai.third.seller.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ningpai.third.seller.bean.ApplyBrand;
import com.ningpai.third.seller.mapper.ApplyBrandMapper;
import com.ningpai.third.seller.service.ApplyBrandService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 自定义品牌的接口实现
 */
@Service("ApplyBrandService")
public class ApplyBrandServiceImpl implements ApplyBrandService {

    /**
     * 自定义品牌接口
     */
    private ApplyBrandMapper applyBrandMapper;

    /**
     * 自定义品牌接口GET方法
     * @return
     */
    public ApplyBrandMapper getApplyBrandMapper() {
        return applyBrandMapper;
    }

    /**
     * 自定义品牌接口 SET方法
     * @param applyBrandMapper
     */
    @Resource(name = "ApplyBrandMapper")
    public void setApplyBrandMapper(ApplyBrandMapper applyBrandMapper) {
        this.applyBrandMapper = applyBrandMapper;
    }

    /**
     * 获取自定义品牌的集合
     * @param thirdId
     * @return
     */
    @Override
    public List<ApplyBrand> selectApplyBrand(Long thirdId) {

        return applyBrandMapper.selectApplyBrand(thirdId);
    }


    /**
     * 删除自定义品牌
     * @param applyBrandId
     * @param thirdId
     * @return
     */
    @Override
    public int delApplyBrand(Long applyBrandId, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyBrandId",applyBrandId);
        map.put("applyThirdId",thirdId);
        return applyBrandMapper.delApplyBrand(map);
    }

    /**
     * 添加自定义品牌
     * @param request
     * @param applyBrand
     * @return
     */
    @Override
    public ApplyBrand addApplyBrand(MultipartHttpServletRequest request, ApplyBrand applyBrand) {
        //
        MultipartFile file = request.getFile("applyPic");
        if (file != null && file.getSize() != 0) {
            applyBrand.setApplyBrandPic(UploadUtil.uploadFileOne(file, request));
        }
        MultipartFile file2 = request.getFile("certificate");
        if (file2 != null && file2.getSize() != 0) {
            applyBrand.setApplyCertificate(UploadUtil.uploadFileOne(file2, request));
        }
        applyBrand.setApplyBrandCreateTime(new Date());
        applyBrand.setApplyBrandDelFlag("0");
        applyBrand.setApplyThirdId((Long) request.getSession().getAttribute("thirdId"));
        int s = applyBrandMapper.addApplyBrand(applyBrand);
        if (s > 0) {

            applyBrand.setApplyBrandId(applyBrandMapper.selectLastId());
        }

        return applyBrand;
    }

    /**
     * 查询当前商家的自定义品牌
     * @param pb
     * @param thirdId
     * @return
     */
    @Override
    public PageBean queryApplyBrand(PageBean pb,Long thirdId) {
        
        ApplyBrand applyBrand = (ApplyBrand)pb.getObjectBean();
        Map<String, Object> pmap = new HashMap<String, Object>();
        pmap.put("thirdId", thirdId);
        pmap.put("applyBrandName", applyBrand.getApplyBrandName());
        pmap.put("applyStatus", applyBrand.getApplyStatus());
        pb.setRows(applyBrandMapper.queryApplyBrandCount(pmap));
        pmap.put("startRowNum", pb.getStartRowNum());
        pmap.put("endRowNum", pb.getEndRowNum());
        pb.setList(applyBrandMapper.queryApplyBrand(pmap));
        return pb;
    }

    /**
     * 批量删除当前商家的自定义品牌
     * @param applyBrandIds
     * @param thirdId
     * @return
     */
    @Override
    public int delApplyBrands(Long[] applyBrandIds,Long thirdId) {
        Map<String, Object> map = new HashMap();
        map.put("applyBrand",applyBrandIds);
        map.put("applyThirdId",thirdId);
        return applyBrandMapper.delApplyBrands(map);
    }

}
