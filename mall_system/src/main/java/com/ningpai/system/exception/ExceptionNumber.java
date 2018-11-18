package com.ningpai.system.exception;

/**
 * 异常编号类 作用：定义常用的异常常量编号，并根据异常编号获取异常说明信息
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-19 16:01:06
 * @version V1.0
 */
public final class ExceptionNumber {

    /** 未知错误 */
    public static final int ERROR_UNKNOWN = 0;

    /****************** public error ***************************************/
    /** Class对象为空 */
    public static final int ERROR_CLASS_NULL = 100;
    /** id为空或为0 */
    public static final int ERROR_ID_NULL_OR_ZERO = 101;
    /** SQL语句为空 */
    public static final int ERROR_SQL_NULL = 102;
    /** SQL语句赋值参数为空 */
    public static final int ERROR_SQL_PARAMETER_NULL = 103;
    /** 参数为null */
    public static final int ERROR_PARAMETER_NULL = 104;
    /** 加载JavaBean的字段信息异常 */
    public static final int ERROR_FULL_JAVABEAN_FIELD = 105;
    /** 保存对象异常 */
    public static final int SAVE_OBJECT_ERROR = 106;
    /** 更新对象异常 */
    public static final int UPDATE_OBJECT_ERROR = 107;
    /** 根据对象的ID查询对象信息异常 */
    public static final int SELECT_OBJECT_BY_ID_ERROR = 108;
    /** 根据对象的ID删除对象信息异常 */
    public static final int DELETE_OBJECT_BY_ID_ERROR = 109;
    /** 更新对象的字段信息异常 */
    public static final int UPDATE_OBJECT_FIELD_ERROR = 110;
    /** 根据对象的字段查询对象信息异常 */
    public static final int SELECT_OBJECT_FIELD_ERROR = 111;
    /** 查询对象的总数异常 */
    public static final int SELECT_OBJECT_TOTAL = 112;
    /** 分页查询对象异常 */
    public static final int SELECT_OBJECT_PAGE_ERROR = 113;

    /****************** 代理 error ***************************************/
    /** NP EXCEPTION异常 */
    public static final int ERROR_NPEXCEPTION = 1000;

    /****************** 系统模块 error ***************************************/

    /** 获得当前最大排序值异常 */
    public static final int SYSTEM_MAX_SORT_ERROR = 200;
    /** 第三方登录QQ异常 */
    public static final int LOGIN_BY_QQ_ERROR = 201;
    /** response跳转路径异常 */
    public static final int RESPONSE_SEND_REDIRECT_ERROR = 202;

    private ExceptionNumber() {
    }

    /**
     * 获得异常编号的说明信息
     * 
     * @param _nExceptionNum
     *            异常编号
     * @return 异常的说明信息
     */
    public static String getErrNoMsg(int nExceptionNum) {
        // 从异常信息说明配置文件中根据异常值获取异常说明信息
        return ExceptionMessages.getString(nExceptionNum);
    }
}
