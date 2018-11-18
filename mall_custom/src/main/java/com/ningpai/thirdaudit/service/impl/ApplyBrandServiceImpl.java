package com.ningpai.thirdaudit.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.thirdaudit.bean.ApplyBrandVo;
import com.ningpai.thirdaudit.mapper.ApplyBrandMapper;
import com.ningpai.thirdaudit.service.ApplyBrandService;
import com.ningpai.util.PageBean;
/**
 * 应用品牌服务层接口实现类
 * */
@Service("applybrandservice")
public class ApplyBrandServiceImpl implements ApplyBrandService {
    private ApplyBrandMapper applyBrandMapper;

    /**
     * 查询未审核列表
     *
     * @param pb
     * @return
     */
    @Override
    public PageBean queryApplyBrand(PageBean pb) {

        Map<String, Object> map = new HashMap<String, Object>();
        ApplyBrandVo applyBrand = (ApplyBrandVo) pb.getObjectBean();
        map.put("applyBrandName", applyBrand.getApplyBrandName());
        map.put("storeName", applyBrand.getStoreName());
        pb.setRows(applyBrandMapper.queryApplyBrandCount(map));
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        pb.setList(applyBrandMapper.queryApplyBrand(map));
        return pb;
    }

    /**
     * 修改审核状态
     *
     * @param
     * @return
     */
    @Override
    public int updateApplyBrand(Long[] applyBrandIds, String reason,
            String applyStatuts) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("applyStatus", applyStatuts);
        map.put("applyRefusalReason", reason);
        map.put("applyBrandIds", applyBrandIds);
        return applyBrandMapper.updateApplyBrand(map);
    }

    public ApplyBrandMapper getApplyBrandMapper() {
        return applyBrandMapper;
    }

    @Resource(name = "applybrandmapper")
    public void setApplyBrandMapper(ApplyBrandMapper applyBrandMapper) {
        this.applyBrandMapper = applyBrandMapper;
    }

}
