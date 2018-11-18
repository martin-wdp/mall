package com.ningpai.group.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.Label;
import com.ningpai.group.service.LabelService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import com.ningpai.util.ValueUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 小组标签控制器
 * 
 * @author qiyuanyuan
 * 
 */
@Controller
public class GroupLabelController {

    private static final MyLogger LOGGER = new MyLogger(GroupLabelController.class);

    private PrintWriter pw;

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String LABELLIST_HTM = "labellist.htm";

    private LabelService labelService;

    /**
     * 小组标签列表
     * 
     * @param request
     * @param pb
     *            分页{@link com.ningpai.util.PageBean}
     * @param selectBean
     *            搜索条件{@link com.ningpai.system.util.SelectBean}
     * @return
     */
    @RequestMapping("/labellist")
    public ModelAndView toGroupLabel(HttpServletRequest request, PageBean pb, SelectBean selectBean) {
        pb.setUrl(LABELLIST_HTM);
        // 判断查询文本是否为空 若为空 将条件也设置为空
        if ("".equals(selectBean.getSearchText())) {
            selectBean.setCondition("");
        }
        // 参数设置到作用域
        request.setAttribute("selectBean", selectBean);
        return new ModelAndView("jsp/social/group_label", "pb", labelService.findByPageBean(pb, selectBean));
    }

    /**
     * 添加小组标签
     * 
     * @param request
     * @param label
     *            小组标签{@link com.ningpai.group.bean.Label}
     * @return
     */
    // @RequestMapping("/addlabel")
    // public ModelAndView addGroupLabel(HttpServletRequest request, Label
    // label) {
    // if (labelService.insertLabel(label) == 1) {
    // // 非空验证 小组标签名称
    // if (null != label.getGroupLabelName()) {
    // LOGGER.info("添加小组标签【" + label.getGroupLabelName() + "】成功");
    // }
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "添加小组标签",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } else {
    // LOGGER.debug("添加小组标签失败");
    // }
    // return new ModelAndView(new RedirectView(LABELLIST_HTM));
    // }

    /**
     * 检验小组标签是否存在
     * 
     * @param groupLabelName
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/checklabelname")
    @ResponseBody
    public boolean checkLabelName(String groupLabelName) throws UnsupportedEncodingException {
        boolean bool;
        String groupLabelNameNew = groupLabelName;
        groupLabelNameNew = URLDecoder.decode(groupLabelNameNew, ConstantUtil.UTF);
        Label label = this.labelService.checkLabelName(groupLabelNameNew);
        if (null == label) {
            bool = true;
            LOGGER.info("小组标签【" + groupLabelName + "】已经存在！");
        } else {
            LOGGER.info("小组标签【" + groupLabelName + "】 不存在！");
            bool = false;
        }
        return bool;
    }

    /**
     * 修改标签
     * 
     * @param label
     *            小组标签{@link com.ningpai.group.bean.Label}
     * @return
     */
    // @RequestMapping("/modifylabel")
    // public ModelAndView updateLabel(HttpServletRequest request, Label label)
    // {
    // if (labelService.updateLabel(label) == 1) {
    // // 非空验证 标签名称
    // if (null != label.getGroupLabelName()) {
    // LOGGER.info("修改小组标签【" + label.getGroupLabelName() + "】成功");
    // }
    //
    // String customerName = (String) request.getSession().getAttribute(NAME);
    // OperaLogUtil.addOperaLog(request, customerName, "修改小组标签",
    // request.getSession().getAttribute(OPERAPATH) + ",用户名:" + customerName);
    // } else {
    // LOGGER.debug("修改小组标签失败");
    // }
    // return new ModelAndView(new RedirectView(LABELLIST_HTM));
    // }

    /**
     * 停用小组标签
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/unuselabel")
    public ModelAndView operationLabel(HttpServletRequest request, @RequestParam("groupLabelIds[]") Long[] groupLabelIds, HttpServletResponse response) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(labelService.operationLabel(groupLabelIds, "1"));
        } catch (IOException e) {
            LOGGER.error("停用小组标签失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 恢复小组标签
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/recoverylabel")
    public ModelAndView recoveryLabel(HttpServletRequest request, @RequestParam("groupLabelIds[]") Long[] groupLabelIds, HttpServletResponse response) {
        response.setContentType(ValueUtil.REQ_SETCONTENT);
        try {
            pw = response.getWriter();
            pw.print(labelService.operationLabel(groupLabelIds, "0"));
        } catch (IOException e) {
            LOGGER.error("恢复小组标签失败!", e);
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
        return null;
    }

    /**
     * 根据小组标签ID查询小组标签详情
     * 
     * @param groupLabelId
     * @return
     */
    @RequestMapping("/querygrouplabelbyid")
    @ResponseBody
    public Label queryLabelById(Long groupLabelId) {
        // 根据主键获取小组标签详细信息
        Label label = labelService.selectByLabelId(groupLabelId);
        // 非空验证 小组标签名称
        if (null != label.getGroupLabelName()) {
            LOGGER.info("获取【" + label.getGroupLabelName() + "】的详细信息");
        }
        return label;

    }

    public LabelService getLabelService() {
        return labelService;
    }

    @Resource(name = "LabelService")
    public void setLabelService(LabelService labelService) {
        this.labelService = labelService;
    }

}
