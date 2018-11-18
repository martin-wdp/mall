package com.ningpai.third.login.mapper;

import com.ningpai.third.login.bean.IpRecord;

/**
 * <p>ip详细信息 IPRecord Mapper</p>
 * @author NINGPAI-zhangqiang
 * @since 2014年5月5日 上午10:45:13
 * @version 2.0
 */
public interface IpRecordMapper {
    /**
     *根据编号删除IP信息
     * @param ipid IP编号
     * @return
     */
    int deleteByPrimaryKey(Long ipid);

    /**
     *新增一组IP信息
     * @param record
     * @return
     */
    int insert(IpRecord record);

    /**
     *新增一组IP信息
     * @param record
     * @return
     */
    int insertSelective(IpRecord record);

    /**
     * 根据id获取一组IP对象详细信息
     * @param ipid
     * @return
     */
    IpRecord selectByPrimaryKey(Long ipid);

    /**
     * 根据ID获取修改一组IP信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(IpRecord record);

    /**
     * 根据ID获取修改一组IP信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(IpRecord record);

    /**
     * 根据IP获取一组IP对象信息
     * @param ip
     * @return
     */
    IpRecord selectByIp(String ip);
}
