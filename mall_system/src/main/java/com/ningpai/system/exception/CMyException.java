package com.ningpai.system.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import com.ningpai.util.MyLogger;

/**
 * 自定义异常父类 负责创建异常和打印异常信息
 *
 * @author NINGPAI_xiaomm
 * @version V1.0
 * @Description:
 * @since 2014-03-19 16:01:06
 */
public class CMyException extends Exception {
    private static final MyLogger LOGGER = new MyLogger(CMyException.class);
    /**
     * @Fields serialVersionUID :(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1382601970702567028L;

    private static final String ARROWS = "<-- ";

    /**
     * 异常错误代码
     */
    public int errNo = 0;
    /**
     * 异常根类
     */
    public final Throwable rootCause;

    /**
     * 构造函数1
     *
     * @param errNo
     *            自定义的异常值
     */
    public CMyException(int errNo) {
        rootCause = null;
        this.errNo = errNo;
    }

    /**
     * 构造函数2
     *
     * @param errNo
     *            自定义的异常值
     * @param sMsg
     *            自定义的异常信息
     */
    public CMyException(int errNo, String sMsg) {
        super(sMsg);
        rootCause = null;
        this.errNo = errNo;
    }

    /**
     * 构造函数3
     *
     * @param sMsg
     *            自定义的异常信息
     */
    public CMyException(String sMsg) {
        super(sMsg);
        errNo = 0;
        rootCause = null;
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
    public CMyException(int errNo, String sMsg, Throwable rootCause) {
        super(sMsg);
        this.errNo = errNo;
        this.rootCause = rootCause;
    }

    /**
     * 构造函数5
     *
     * @param sMsg
     *            自定义的异常信息
     * @param rootCause
     *            捕获的异常信息类
     */
    public CMyException(String sMsg, Throwable rootCause) {
        super(sMsg);
        errNo = 0;
        this.rootCause = rootCause;
    }

    /**
     * 获得自定义的异常值
     *
     * @return 自定义的异常值
     */
    public int getErrNo() {
        return errNo;
    }

    /**
     * 获得异常信息类
     *
     * @return 异常信息类
     */
    public Throwable getRootCause() {
        return rootCause;
    }

    /**
     * 根据异常编号获取异常的说明信息
     *
     * @return 异常的说明信息
     */
    public String getErrNoMsg() {
        // 转发到异常编号类中获取
        return ExceptionNumber.getErrNoMsg(errNo);
    }

    /**
     * 获取异常的信息
     */
    public String getMyMessage() {
        // 转发到父类中获取
        return super.getMessage();
    }

    /**
     * 重写toString()方法，获取异常系统的异常信息
     */
    public String toString() {
        return "[ERR-" + errNo + "] " + getMyMessage();
    }

    /**
     * 获得异常信息
     *
     * @return 异常信息
     */
    public String getMessage() {
        String sMessage = toString();
        if (rootCause != null) {
            sMessage = sMessage + "\r\n<-- " + rootCause.toString();
        }
        return sMessage;
    }

    /**
     * 获得异常本地消息
     *
     * @return 消息
     */
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * 打印栈中的异常跟踪信息，方式1
     *
     * @param ps
     *            打印流
     */
    public void printStackTrace(PrintStream ps) {
        if (rootCause == null) {
            super.printStackTrace(ps);
        } else {
            Throwable root = rootCause;
            synchronized (ps) {
                ps.println(toString());
                while (root instanceof CMyException) {
                    ps.println(ARROWS + root.toString());
                    root = ((CMyException) root).getRootCause();
                    if (root == null) {
                        LOGGER.error(ps.toString());
                        break;
                    }
                }
                if (root != null) {
                    ps.print(ARROWS);
                    LOGGER.error(ps.toString());
                }
            }
        }
    }

    /**
     * 打印栈中的异常跟踪信息，方式2
     *
     * @param pw
     *            打印流
     */
    public void printStackTrace(PrintWriter pw) {
        if (rootCause == null) {
            super.printStackTrace(pw);
        } else {
            Throwable root = rootCause;
            synchronized (pw) {
                pw.println(toString());
                Throwable preRoot = null;
                for (; root instanceof CMyException; pw.println(preRoot.toString())) {
                    pw.print(ARROWS);
                    preRoot = root;
                    root = ((CMyException) root).getRootCause();
                    if (root != null) {
                        continue;
                    }
                    LOGGER.error(pw.toString());
                    break;
                }

                if (root != null) {
                    pw.print(ARROWS);
                    LOGGER.error(pw.toString());
                }
            }
        }
    }

    /**
     * 获得异常栈中的异常跟踪信息文本
     *
     * @return 异常信息
     */
    public String getStackTraceText() {
        // 将自己放入处理方法中提取异常跟踪信息文本
        return getStackTraceText((Throwable) (this));
    }

    /**
     * 获得栈中的异常跟踪信息处理方法
     *
     * @param ex
     *            异常超级父类Throwable
     * @return 异常信息
     */
    public static String getStackTraceText(Throwable ex) {
        StringWriter strWriter;
        PrintWriter prtWriter;
        strWriter = null;
        prtWriter = null;
        String s;
        try {
            strWriter = new StringWriter();
            prtWriter = new PrintWriter(strWriter);
            LOGGER.error(prtWriter.toString());
            prtWriter.flush();
            s = strWriter.toString();
        } catch (Exception e) {
            LOGGER.error("",e);
            s = e.getMessage();
        } finally {
            if (strWriter != null) {
                try {
                    strWriter.close();
                } catch (Exception exception1) {
                    LOGGER.error("",exception1);
                    strWriter = null;
                }
            }
            if (prtWriter != null) {
                try {
                    prtWriter.close();
                } catch (Exception e) {
                    LOGGER.error("",e);
                    prtWriter = null;
                }
            }
        }
        return s;
    }

}
