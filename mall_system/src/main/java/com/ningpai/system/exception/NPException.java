package com.ningpai.system.exception;

import com.ningpai.system.util.Logger;

/**
 * 自定义异常类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-19 16:01:06
 * @version V1.0
 */
public class NPException extends CMyException {

    /**
     * @Fields serialVersionUID : (用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -7300498889540349862L;
    /** Logger日志工具类 */
    private static final Logger APPLOGGER = Logger.getDefaultLogger();

    /**
     * 构造函数1 默认异常值0： 未知错误异常
     * 
     * @param errNo
     *            自定义的异常值
     */
    public NPException(int errNo) {
        super(errNo);
    }

    /**
     * 构造函数2
     * 
     * @param errNo
     *            自定义的异常值
     * @param sMsg
     *            自定义的异常信息
     */
    public NPException(int errNo, String sMsg) {
        super(errNo, sMsg);
    }

    /**
     * 构造函数3 默认异常值1000： WCMException异常
     * 
     * @param sMsg
     *            自定义的异常信息
     */
    public NPException(String sMsg) {
        super(sMsg);
        super.errNo = ExceptionNumber.ERROR_NPEXCEPTION;
    }

    /**
     * 构造函数4
     * 
     * @param errNo
     *            自定义的异常值
     * @param sMsg
     *            自定义的异常信息
     * @param rootCause
     *            捕获的异常信息类
     */
    public NPException(int errNo, String sMsg, Throwable rootCause) {
        super(errNo, sMsg, rootCause);
    }

    /**
     * 构造函数5 默认异常值1000： WCMException异常
     * 
     * @param sMsg
     *            自定义的异常信息
     * @param rootCause
     *            捕获的异常信息类
     */
    public NPException(String sMsg, Throwable rootCause) {
        super(sMsg, rootCause);
        super.errNo = ExceptionNumber.ERROR_NPEXCEPTION;
    }

    /**
     * 捕获异常
     * 
     * @param strDesc
     *            描述信息
     * @param exCaught
     *            异常类
     * @param bSeverity
     *            是否抛出，true抛出，false不抛出
     * @throws com.ningpai.system.exception.NPException
     */
    public static void catchException(String strDesc, Exception exCaught, boolean bSeverity) throws NPException {
        APPLOGGER.error(strDesc, exCaught);
        if (bSeverity) {
            throw new NPException(ExceptionNumber.ERROR_NPEXCEPTION, strDesc, exCaught);
        } else {
            return;
        }
    }
}
