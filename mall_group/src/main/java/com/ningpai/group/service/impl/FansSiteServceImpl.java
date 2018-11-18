package com.ningpai.group.service.impl;

import com.ningpai.group.bean.Fans;
import com.ningpai.group.dao.FansMapper;
import com.ningpai.group.service.FansSiteService;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 粉丝service接口实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("FansSiteService")
public class FansSiteServceImpl implements FansSiteService {

    private FansMapper fansMapper;

    private static final String CUSTOMERID = "customerId";
    private static final String FANSCUSTOMERID = "fansCustomerId";
    private static final String FANSFLAG = "fansFlag";

    /**
     * 前台消息Servcie接口
     */
    private MessageSiteService messageSiteService;

    private MessageTemplateMapper templateMapper;

    /**
     * 查询当前登录用户与当前主页的粉丝状态
     * 
     * @param customerId
     *            {@link java.lang.Long}
     * @param fansCustomerId
     *            {@link java.lang.Long}
     * @return
     */
    public String fansFlag(Long customerId, Long fansCustomerId) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        paramMap.put(FANSCUSTOMERID, fansCustomerId);
        Fans fans = fansMapper.fansFlag(paramMap);
        if (fans != null) {
            // 如果已经是有关系的粉丝
            return fans.getFansFlag();
        } else {
            // 当前没有关系
            return null;
        }
    }

    /**
     * 加关注
     * 
     * @param customerId
     *            登陆用户ID {@link java.lang.Long}
     * @param fansCustomerId
     *            粉丝用户Id {@link java.lang.Long}
     * @param fansFlag
     *            粉丝状态 {@link java.lang.String}
     * @return int
     */
    public int guanzhu(Long customerId, Long fansCustomerId, String fansFlag) {
        // 如果fansFlag=0 加关注后状态变为2 相互关注
        if (fansFlag != null && !"".equals(fansFlag)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(FANSCUSTOMERID, fansCustomerId);
            if ("0".equals(fansFlag)) {
                paramMap.put(FANSFLAG, "2");
                // 修改状态为2
                int flag = fansMapper.guanzhu(paramMap);
                if (flag == 1) {
                    fansMapper.fansi(paramMap);
                    sendMessage(fansCustomerId, customerId);
                }
                return flag;
            }
            return 0;
        } else {
            // 如果当前两用户之间没有关系 之间新增一条数据
            Fans fans = new Fans();
            fans.setCustomerId(customerId);
            fans.setFansCustomerId(fansCustomerId);
            fans.setCreateTime(new Date());
            // 我的关注 状态 1
            fans.setFansFlag("1");
            fansMapper.insertSelective(fans);

            Fans fans2 = new Fans();
            fans2.setCustomerId(fansCustomerId);
            fans2.setFansCustomerId(customerId);
            fans2.setCreateTime(new Date());
            // 我是别人的粉丝
            fans2.setFansFlag("0");
            int flag = fansMapper.insertSelective(fans2);
            if (flag == 1) {
                sendMessage(fansCustomerId, customerId);
            }
            return flag;
        }

    }

    /**
     * 发送信息
     * 
     * @param fansCustomerId
     * @param customerId
     */

    public void sendMessage(Long fansCustomerId, Long customerId) {
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("messageType", '4');
        paramMap.put("messageOperation", "21");
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setMessageTitle(template.getMessageTemplate());
        messageVo.setMessageType("0");
        messageVo.setMessageDelFlag("0");
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setCustomerRecId(new Long[] { fansCustomerId });
        messageSiteService.addMessage(messageVo, customerId);
    }

    /**
     * 取消关注
     * 
     * @param customerId
     *            登陆用户ID {@link java.lang.Long}
     * @param fansCustomerId
     *            粉丝用户Id {@link java.lang.Long}
     * @param fansFlag
     *            粉丝状态 {@link java.lang.String}
     * @return int
     */
    public int cancelGuanzhu(Long customerId, Long fansCustomerId, String fansFlag) {
        if (fansFlag != null && !"".equals(fansFlag)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(FANSCUSTOMERID, fansCustomerId);
            // 当前状态 2 互粉状态
            if ("2".equals(fansFlag)) {
                paramMap.put(FANSFLAG, "0");
                int flag = fansMapper.cancelGuanZhu(paramMap);
                if (flag == 1) {
                    paramMap.put(FANSFLAG, "1");
                    fansMapper.cancelFansi(paramMap);
                }
                return flag;
            }
            // 当前状态 1 仅为我关注的 取消关注时删除数据
            if ("1".equals(fansFlag)) {
                int flag = fansMapper.delFansFlag(paramMap);
                if (flag == 1) {
                    return fansMapper.delOFansFlag(paramMap);
                }
            }
        }
        return 0;
    }

    /**
     * 他的关注
     * 
     * @param customerId
     *            {@link java.lang.String}
     * @return list
     */
    public List<Fans> fansList(Long customerId) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        paramMap.put("number", 4);
        return fansMapper.fansList(paramMap);
    }

    /**
     * 查询相互关注的好友
     * 
     * @param pb
     *            分页
     * @param customerId
     *            用户id {@link java.lang.Long}
     * @param customerName
     *            用户姓名 {@link java.lang.String}
     * @return pb
     */
    public PageBean fansCustomer(PageBean pb, Long customerId, String customerName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        paramMap.put("customerName", customerName);
        int rows = fansMapper.eachfansCountByCustomerId(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(24);
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(fansMapper.eachfansByCustomerId(paramMap));
        return pb;
    }

    /**
     * 用户的关注和粉丝
     * 
     * @param pb
     *            分页
     * @param customerId
     *            用户id {@link java.lang.Long}
     * @param fansFlag
     *            粉丝状态 0我的粉丝 1我的关注 2互粉 {@link java.lang.String}
     * @return
     */
    public PageBean selectMyMtual(PageBean pb, Long customerId, String fansFlag, Long cusId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        paramMap.put(FANSFLAG, fansFlag);
        paramMap.put("customerIdi", cusId);
        int rows = fansMapper.myMtualCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(24);
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(fansMapper.selectMyMtual(paramMap));
        return pb;
    }

    /**
     * 批量取消关注
     * 
     * @param customerId
     *            用户id {@link java.lang.Long}
     * @param customerIds
     * @return
     */
    public int cancelAllGuanzhu(Long customerId, String[] customerIds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);

        for (int i = 0; i < customerIds.length; i++) {
            String[] str = customerIds[i].split(",");
            // 当前状态 2 互粉状态
            if ("2".equals(str[1])) {
                paramMap.put(FANSFLAG, "0");
                paramMap.put(FANSCUSTOMERID, Long.parseLong(str[0]));
                int flag = fansMapper.cancelGuanZhu(paramMap);
                if (flag == 1) {
                    paramMap.put(FANSFLAG, "1");
                    fansMapper.cancelFansi(paramMap);
                }
                continue;
            }
            // 当前状态 1 仅为我关注的 取消关注时删除数据
            if ("1".equals(str[1])) {
                paramMap.put(FANSCUSTOMERID, Long.parseLong(str[0]));
                int flag = fansMapper.delFansFlag(paramMap);
                if (flag == 1) {
                    fansMapper.delOFansFlag(paramMap);
                }
                continue;
            }

            if ("-1".equals(str[1])) {
                // 没有关系的取消关注 不做操作
                continue;
            }
            if ("1".equals(str[1])) {
                // 我的粉丝 取消关注 不做操作
                continue;
            }
        }
        return 1;
    }

    /**
     * 我的关注和我的粉丝
     * 
     * @param customerId
     *            用户ID {@link java.lang.Long}
     * @param number
     *            显示数目
     * @param flag
     *            标志
     * @return
     */
    public List<Fans> selectFocus(Long customerId, int number, String flag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 用户Id
        paramMap.put(CUSTOMERID, customerId);
        // 查询数量
        paramMap.put("number", number);
        // 粉丝状态
        paramMap.put(FANSFLAG, flag);
        return fansMapper.selectFocus(paramMap);
    }

    public FansMapper getFansMapper() {
        return fansMapper;
    }

    @Resource(name = "FansMapper")
    public void setFansMapper(FansMapper fansMapper) {
        this.fansMapper = fansMapper;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

    public MessageTemplateMapper getTemplateMapper() {
        return templateMapper;
    }

    @Resource(name = "MessageTemplateMapper")
    public void setTemplateMapper(MessageTemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }
}
