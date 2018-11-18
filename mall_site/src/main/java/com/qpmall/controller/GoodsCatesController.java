package com.qpmall.controller;

import com.alibaba.fastjson.JSON;
import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.GoodsCateService;
import com.ningpai.goods.util.ValueUtil;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.service.IStatisticsCodeBiz;
import com.ningpai.system.service.ShopKuaiShangService;
import com.ningpai.util.MyLogger;
import com.qpmall.bean.QpAutoStyleBean;
import com.qpmall.unit.EHCacheUtil;
import com.qpmall.vo.BrandBean;
import com.qpmall.vo.DetitleCarStyleBean;
import com.qpmall.vo.NewGoodsBrandBean;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by pl on 2015/10/15.
 * Desc:
 */
@Controller
public class GoodsCatesController {

    private static final MyLogger LOGGER = new MyLogger(GoodsCatesController.class);
    @Resource(name="GoodsCateService")
    private GoodsCateService goodsCateService;
    @Resource(name = "GoodsBrandService")
    private GoodsBrandService goodsBrandService;
    /**
     * 快商通
     */
    @Resource(name = "ShopKuaiShangService")
    private ShopKuaiShangService shopKuaiShangService;
    /**
     * 统计代码业务类
     */
    @Resource(name = "statisticsCodeBizImpl")
    private IStatisticsCodeBiz statisticsCodeBizImpl;

    /**
     * 会员服务接口
     */
    @Resource(name = "customerServiceSite")
    private CustomerServiceInterface customerServiceInterface;
    /**
     * SERVICE-获取头部、底部信息
     */
    @Resource(name = "TopAndBottomService")
    private TopAndBottomService topAndBottomService;

    //所有分类
    private static String ALLGOODSCATE="allGoodsCate";
    //所有品牌路径
    private static String brandUrl="brand/brandIndex";
    //所有分类
    private static String goodsCateUrl="brand/goodscateIndex";


    /***
     * 查询所有商品分类
     * zz：pl
     * @return
     */
    @RequestMapping(value = "/GetAllGoodsCateList",produces="text/plain;charset=UTF-8")
    public ModelAndView GetAllGoodsCateList(HttpServletRequest request)
    {
        List<GoodsCateVo> list = GetallGoodsCatelistTemp();

        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName(goodsCateUrl);
        // 获取当前统计代码
        modelAndView.addObject("sCodeList", statisticsCodeBizImpl.getCurrStatisticsCode());
        modelAndView.addObject("shopKuaiShang", shopKuaiShangService.selectInitone());
        // 查询通知内容数量
        modelAndView.addObject("notice", customerServiceInterface.selectNotice((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
        modelAndView.addObject("GoodsCatelist",list);
        //获得一级分类的内容
        List<GoodsCateVo> list2=goodsCateService.queryFirstLevelGoodsCate();
        modelAndView.addObject("firstLevelGoodsCate",list2);
        // 新加载底部信息
        modelAndView = topAndBottomService.getBottom(modelAndView);
        return modelAndView;
    }

    /***
     * 根据关键字查询商品分类
     * zz：pl
     * @param name
     * @return
     */
    @RequestMapping(value = "/findGoodsCateByName",produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String findGoodsCateByName(String name)
    {
        List<GoodsCateVo> list = GetallGoodsCatelistTemp();
        List<GoodsCateVo> newList=new ArrayList<>();
        for (GoodsCateVo goodsCateVo:list)
        {
            if (goodsCateVo.getCatName().toLowerCase().contains(name.toLowerCase())){
                long parentId=0;
                if(goodsCateVo.getCatParentId()!=0){
                    GoodsCateVo goodsCateVo1=getObj(list,goodsCateVo.getCatParentId());
                    parentId=goodsCateVo1.getCatId();
                }
                else {
                    parentId=goodsCateVo.getCatParentId();
                }
                goodsCateVo.setCatParentId(parentId);
                //Map<Long,GoodsCateVo>map=new HashedMap();
                //map.put(parentId,goodsCateVo);
                newList.add(goodsCateVo);
            }
        }
        return JSON.toJSONString(newList);
    }

    public static GoodsCateVo getObj(List<GoodsCateVo> list,Long parentId)
    {
        for(GoodsCateVo goodsCateVo2:list){
            if(goodsCateVo2.getCatId().equals(parentId)){
                if(goodsCateVo2.getCatParentId()!=0){
                    for(GoodsCateVo goodsCateVo3:list){
                        if(goodsCateVo3.getCatId().equals(goodsCateVo2.getCatParentId())){
                            if(goodsCateVo3.getCatParentId()!=0){
                                return null;
                            }
                            return goodsCateVo3;
                        }
                    }
                }
                return goodsCateVo2;
            }
        }
        return null;
    }

    /***
     * 查询所有商品分类
     * zz：pl
     * @return
     */
    public List<GoodsCateVo> GetallGoodsCatelistTemp()
    {
        List<GoodsCateVo> list = (List<GoodsCateVo>) EHCacheUtil.get(ALLGOODSCATE);
        if (list==null)
        {
            list=this.goodsCateService.queryAllCate();
         /*  Collections.sort(list, new Comparator<GoodsCateVo>() {
                public int compare(GoodsCateVo arg0, GoodsCateVo arg1) {
                    return arg0.getCatId().compareTo(arg1.getCatId());
                }
            });*/
            try {
                EHCacheUtil.setValue("AllGoodsCateCache",ALLGOODSCATE,list);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("EHCache缓存异常");
            }
        }
        return list;
    }

    /**
     * 查询所有品牌信息
     *
     * @return
     */
    @RequestMapping(value="/GetAllGoodsBrandList",produces="text/plain;charset=UTF-8")
    public ModelAndView GetAllGoodsBrandList(HttpServletRequest request) {
        // 打印日志
        LOGGER.info(ValueUtil.SEARCHBRANDLIST);
        // 执行方法并返回结果
        List<GoodsBrand> list = goodsBrandService.queryAllGoodsBrandBy_PL();
        List<DetitleCarStyleBean> list2= groupByType(list);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName(brandUrl);
        // 获取当前统计代码
        modelAndView.addObject("sCodeList", statisticsCodeBizImpl.getCurrStatisticsCode());
        modelAndView.addObject("shopKuaiShang", shopKuaiShangService.selectInitone());
        // 查询通知内容数量
        modelAndView.addObject("notice", customerServiceInterface.selectNotice((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID)));
        modelAndView.addObject("brandlist",list2);
        // 新加载底部信息
        modelAndView = topAndBottomService.getBottom(modelAndView);
        return modelAndView;
    }

    /***
     * 测试上传图片
     * zz：pl
     * @return
     */
    @RequestMapping(value="/GetTest",produces="text/plain;charset=UTF-8")
    public ModelAndView getTest()
    {
        ModelAndView modelAndView=new ModelAndView();

        modelAndView.setViewName("brand/uploadimage");
        return  modelAndView;
    }

    /**
     * 分组
     *
     * @param target
     *            <code>List</code>待分组的集合
     * @return <code>List</code>分组完成后的容器对象集合
     */
    public List<DetitleCarStyleBean> groupByType(List<GoodsBrand> target) {
        List<DetitleCarStyleBean> result = new ArrayList<DetitleCarStyleBean>();
        for (int i = 0; i < target.size(); i++) {
            GoodsBrand myObject = target.get(i);
            target.remove(myObject);
            i=i-1;
            DetitleCarStyleBean gc = new DetitleCarStyleBean();
            gc.getVlist3().add(myObject);
            gc.setKey(myObject.getBrandNameInitial());
            for (int j = 0; j < target.size();) {
                GoodsBrand _myObject = target.get(j);
                // 相同，分组，并加入到组容器集合
                if (_myObject.getBrandNameInitial().equals(myObject.getBrandNameInitial())) {
                    gc.getVlist3().add(_myObject);

                    target.remove(_myObject);
                } else {
                    //gc.setKey(_myObject.getGoodsBrandName());
                    j++;
                }
            }
            result.add(gc);
        }
        return result;
    }




}
