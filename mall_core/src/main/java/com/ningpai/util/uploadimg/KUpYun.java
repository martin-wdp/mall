package com.ningpai.util.uploadimg;

import com.ningpai.util.MyLogger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.Map.Entry;

/**
 *
 * 重写又拍云，为了把异常抛出来
 * 
 * @Author:NP-HEHU
 * @date 2015-7-13 11:00:32
 */
public class KUpYun {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(KUpYun.class);

    /** 编码 */
    private static final String UTF8 = "UTF-8";
    /** 调试模式 */
    public boolean debug = false;
    /** 超时时长 */
    private int timeout = 30000;
    /** 接口跟地址 */
    private String apiDomain = "v0.api.upyun.com";
    /** MD5内容 */
    private String contentMD5 = null;
    /** 文件秘钥 */
    private String fileSecret = null;
    /** 缓存名字 */
    protected String bucketName = null;
    /** 用户名 */
    protected String userName = null;
    /** 密码 */
    protected String password = null;
    /** 图片宽度 */
    protected String picWidth = null;
    /** 图片高度 */
    protected String picHeight = null;
    /** 图片框架 */
    protected String picFrames = null;
    /** 图片类型 */
    protected String picType = null;
    /** 文件类型 */
    protected String fileType = null;
    /** 文件大小 */
    protected String fileSize = null;
    /** 文件创建日期 */
    protected String fileDate = null;

    private static final String X_UPYUN_FILE_TYPE = "x-upyun-file-type";

    /**
     * 构造方法
     *
     * @param bucketName
     *            bucketName
     * @param userName
     *            用户名
     * @param password
     *            密码
     */
    public KUpYun(String bucketName, String userName, String password) {
        this.bucketName = bucketName;
        this.userName = userName;
        this.password = md5(password);
    }

    /**
     * 设置有效时长
     *
     * @param second
     *            秒
     */
    public void setTimeout(int second) {
        this.timeout = second * 1000;
    }

    /**
     * 获取超市时长
     * 
     * @return 超市时长
     */
    public int getTimeout() {
        return this.timeout;
    }

    /**
     * 判断是否启动debug模式
     * 
     * @return 是否启动debug模式
     */
    public boolean isDebug() {
        return this.debug;
    }

    /**
     * 设置debug模式
     * 
     * @param debug
     *            debug模式
     */
    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    /**
     * 设置MD5加密
     * 
     * @param md5Value
     *            md5加密值
     */
    public void setContentMD5(String md5Value) {
        this.contentMD5 = md5Value;
    }

    /**
     * 设置文件秘钥
     * 
     * @param secret
     *            文件秘钥
     */
    public void setFileSecret(String secret) {
        this.fileSecret = secret;
    }

    /**
     * 版本
     * 
     * @return 版本
     */
    public String version() {
        return "2.0";
    }

    /**
     * 上传文件
     * 
     * @param filePath
     *            文件路径
     * @param file
     *            文件
     * @param auto
     *            是否自动上传
     * @return 上传结果
     * @throws IOException
     *             上传过程抛出的异常
     */
    public boolean writeFile(String filePath, File file, boolean auto) throws IOException {
        return this.writeFile(filePath, (File) file, auto, (Map) null);
    }

    /**
     * 上传文件
     * 
     * @param filePath
     *            文件路径
     * @param file
     *            文件
     * @param auto
     *            是否自动上传
     * @return 上传结果
     * @throws IOException
     *             上传过程抛出的异常
     */
    public boolean writeFile(String filePath, File file, boolean auto, Map<String, String> params) throws IOException {
        String filePathNew = filePath;
        filePathNew = this.formatPath(filePathNew);
        FileInputStream is = null;
        OutputStream os = null;
        HttpURLConnection conn = null;

        try {
            is = new FileInputStream(file);
            URL e = new URL("http://" + this.apiDomain + filePathNew);
            conn = (HttpURLConnection) e.openConnection();
            conn.setConnectTimeout(this.timeout);
            conn.setRequestMethod("PUT");
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Date", this.getGMTDate());
            conn.setRequestProperty("Authorization", this.sign(conn, filePathNew, (long) is.available()));
            if (!this.isEmpty(this.contentMD5)) {
                conn.setRequestProperty("Content-MD5", this.contentMD5);
                this.contentMD5 = null;
            }

            if (!this.isEmpty(this.fileSecret)) {
                conn.setRequestProperty("Content-Secret", this.fileSecret);
                this.fileSecret = null;
            }

            if (auto) {
                conn.setRequestProperty("mkdir", "true");
            }

            if (params != null && !params.isEmpty()) {
                Iterator temp = params.entrySet().iterator();

                while (temp.hasNext()) {
                    Entry data = (Entry) temp.next();
                    conn.setRequestProperty((String) data.getKey(), (String) data.getValue());
                }
            }

            conn.connect();
            os = conn.getOutputStream();
            byte[] data1 = new byte[4096];
            // boolean temp1 = false;

            int temp2;
            while ((temp2 = is.read(data1)) != -1) {
                os.write(data1, 0, temp2);
            }

            this.getText(conn, false);
            return true;
        } finally {
            if (os != null) {
                os.close();
                os = null;
            }

            if (is != null) {
                is.close();
                is = null;
            }

            if (conn != null) {
                conn.disconnect();
                conn = null;
            }

        }
    }

    /**
     * 给字符串进行MD5加密
     *
     * @param str
     *            要进行MD5加密的字符串
     * @return MD5加密后的字符串
     */
    public static String md5(String str) {
        char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException var9) {
            LOGGER.error("",var9);
            throw new RuntimeException(var9.getMessage());
        } catch (UnsupportedEncodingException var10) {
            LOGGER.error("",var10);
            throw new RuntimeException(var10.getMessage());
        }

        byte[] encodedValue = md5.digest();
        int j = encodedValue.length;
        char[] finalValue = new char[j * 2];
        int k = 0;

        for (int i = 0; i < j; ++i) {
            byte encoded = encodedValue[i];
            finalValue[k++] = hexDigits[encoded >> 4 & 15];
            finalValue[k++] = hexDigits[encoded & 15];
        }

        return new String(finalValue);
    }

    /**
     * MD5给文件加密
     *
     * @param file
     *            要加密的文件
     * @return 文件加密后的字符串
     * @throws IOException
     *             上传过程抛出的异常
     */
    public static String md5(File file) throws IOException {
        FileInputStream is = new FileInputStream(file);
        char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            // boolean encodedValue = false;
            byte[] j = new byte[1024];

            while (true) {
                int var14 = is.read(j);
                if (var14 > 0) {
                    md5.update(j, 0, var14);
                }

                if (var14 == -1) {
                    is.skip(0L);
                    break;
                }
            }
        } catch (NoSuchAlgorithmException var12) {
            LOGGER.error("",var12);
            throw new RuntimeException(var12.getMessage());
        } finally {
            is.close();
        }

        byte[] var15 = md5.digest();
        int var16 = var15.length;
        char[] finalValue = new char[var16 * 2];
        int k = 0;

        for (int i = 0; i < var16; ++i) {
            byte encoded = var15[i];
            finalValue[k++] = hexDigits[encoded >> 4 & 15];
            finalValue[k++] = hexDigits[encoded & 15];
        }

        return new String(finalValue);
    }

    /**
     * 格式化时间
     *
     * @return 格式化后的时间
     */
    private String getGMTDate() {
        SimpleDateFormat formater = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss \'GMT\'", Locale.US);
        formater.setTimeZone(TimeZone.getTimeZone("GMT"));
        return formater.format(new Date());
    }

    /**
     * 签名
     *
     * @param conn
     *            HttpURLConnection
     * @param uri
     *            URI路径
     * @param length
     *            长度
     * @return 签名后的字符串
     */
    private String sign(HttpURLConnection conn, String uri, long length) {
        String sign = conn.getRequestMethod() + "&" + uri + "&" + conn.getRequestProperty("Date") + "&" + length + "&" + this.password;
        return "UpYun " + this.userName + ":" + md5(sign);
    }

    /**
     * 发送http请求，上传文件
     *
     * @param method
     *            调用方法
     * @param uri
     *            URI路径
     * @param datas
     *            字节流数据
     * @param outFile
     *            上传的文件
     * @param auto
     *            是否自动上传
     * @return 返回调用结果
     */
    private String HttpAction(String method, String uri, byte[] datas, File outFile, boolean auto) {
        return this.HttpAction(method, uri, datas, outFile, auto, (Map) null);
    }

    /**
     * 发送http请求，上传文件
     *
     * @param method
     *            调用方法
     * @param uri
     *            URI路径
     * @param datas
     *            字节流数据
     * @param outFile
     *            上传的文件
     * @param auto
     *            是否自动上传
     * @param params
     *            参数
     * @return 返回调用结果
     */
    private String HttpAction(String method, String uri, byte[] datas, File outFile, boolean auto, Map<String, String> params) {
        String result = null;
        HttpURLConnection conn = null;
        Object os = null;
        InputStream is = null;

        try {
            URL e = new URL("http://" + this.apiDomain + uri);
            conn = (HttpURLConnection) e.openConnection();
            conn.setConnectTimeout(this.timeout);
            conn.setRequestMethod(method);
            conn.setUseCaches(false);
            conn.setDoOutput(true);
            conn.setRequestProperty("Date", this.getGMTDate());
            if (auto) {
                conn.setRequestProperty("mkdir", "true");
            }

            long contentLength = 0L;
            if (datas == null) {
                conn.setRequestProperty("Content-Length", "0");
            } else {
                contentLength = (long) datas.length;
                conn.setRequestProperty("Content-Length", String.valueOf(datas.length));
                if (!this.isEmpty(this.contentMD5)) {
                    conn.setRequestProperty("Content-MD5", this.contentMD5);
                    this.contentMD5 = null;
                }

                if (!this.isEmpty(this.fileSecret)) {
                    conn.setRequestProperty("Content-Secret", this.fileSecret);
                    this.fileSecret = null;
                }
            }

            conn.setRequestProperty("Authorization", this.sign(conn, uri, contentLength));
            boolean isFolder = false;
            if (params != null && !params.isEmpty()) {
                isFolder = !this.isEmpty((String) params.get(KUpYun.PARAMS.KEY_MAKE_DIR.getValue()));
                Iterator temp = params.entrySet().iterator();

                while (temp.hasNext()) {
                    Entry data = (Entry) temp.next();
                    conn.setRequestProperty((String) data.getKey(), (String) data.getValue());
                }
            }

            conn.connect();
            if (datas != null) {
                os = conn.getOutputStream();
                ((OutputStream) os).write(datas);
                ((OutputStream) os).flush();
            }

            if (isFolder) {
                os = conn.getOutputStream();
                ((OutputStream) os).flush();
            }

            if (outFile != null) {
                result = "";
                os = new FileOutputStream(outFile);
                byte[] data1 = new byte[4096];
                // boolean temp1 = false;
                is = conn.getInputStream();

                int temp2;
                while ((temp2 = is.read(data1)) != -1) {
                    ((OutputStream) os).write(data1, 0, temp2);
                }

                return result;
            }

            result = this.getText(conn, "HEAD".equals(method));
            return result;
        } catch (IOException var25) {
            LOGGER.error("",var25);
            if (this.debug) {
            }
        } finally {
            try {
                if (os != null) {
                    ((OutputStream) os).close();
                    os = null;
                }

                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException var24) {
                LOGGER.error("",var24);
            }

            if (conn != null) {
                conn.disconnect();
                conn = null;
            }

        }

        return null;
    }

    /**
     * 从http连接中获取返回信息
     *
     * @param conn
     *            http连接
     * @param isHeadMethod
     *            是否为HeadMethod
     * @return 从http连接中返回的信息
     * @throws IOException
     *             上传过程抛出的异常
     */
    private String getText(HttpURLConnection conn, boolean isHeadMethod) throws IOException {
        StringBuilder text = new StringBuilder();
        this.fileType = null;
        InputStream is = null;
        InputStreamReader sr = null;
        BufferedReader br = null;
        int code = conn.getResponseCode();

        try {
            is = code >= 400 ? conn.getErrorStream() : conn.getInputStream();
            if (!isHeadMethod) {
                sr = new InputStreamReader(is);
                br = new BufferedReader(sr);
                char[] chars = new char[4096];
                // boolean length = false;

                int length1;
                while ((length1 = br.read(chars)) != -1) {
                    text.append(chars, 0, length1);
                }
            }

            if (200 == code && conn.getHeaderField("x-upyun-width") != null) {
                this.picWidth = conn.getHeaderField("x-upyun-width");
                this.picHeight = conn.getHeaderField("x-upyun-height");
                this.picFrames = conn.getHeaderField("x-upyun-frames");
                this.picType = conn.getHeaderField(X_UPYUN_FILE_TYPE);
            } else {
                this.picWidth = this.picHeight = this.picFrames = this.picType = null;
            }

            if (200 == code && conn.getHeaderField(X_UPYUN_FILE_TYPE) != null) {
                this.fileType = conn.getHeaderField(X_UPYUN_FILE_TYPE);
                this.fileSize = conn.getHeaderField("x-upyun-file-size");
                this.fileDate = conn.getHeaderField("x-upyun-file-date");
            } else {
                this.fileType = this.fileSize = this.fileDate = null;
            }
        } finally {
            if (br != null) {
                br.close();
                br = null;
            }

            if (sr != null) {
                sr.close();
                sr = null;
            }

            if (is != null) {
                is.close();
                is = null;
            }

        }

        if (isHeadMethod) {
            return code >= 400 ? null : "";
        } else if (code >= 400) {
            throw new IOException(text.toString());
        } else {
            return text.toString();
        }
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     *            要判断的字符串
     * @return 为空返回true, 否则返回false
     */
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 路径格式化，加"/"
     *
     * @param path
     *            文件路径
     * @return 加"/"后的路径字符串
     */
    private String formatPath(String path) {
        String pathNew = path;
        if (!this.isEmpty(pathNew)) {
            pathNew = pathNew.trim();
            if (!pathNew.startsWith("/")) {
                return "/" + this.bucketName + "/" + pathNew;
            }
        }

        return "/" + this.bucketName + pathNew;
    }

    /**
     * 初始化一些参数
     */
    public static enum PARAMS {
        KEY_X_GMKERL_TYPE("x-gmkerl-type"), KEY_X_GMKERL_VALUE("x-gmkerl-value"), KEY_X_GMKERL_QUALITY("x-gmkerl-quality"), KEY_X_GMKERL_UNSHARP("x-gmkerl-unsharp"), KEY_X_GMKERL_THUMBNAIL(
                "x-gmkerl-thumbnail"), KEY_X_GMKERL_ROTATE("x-gmkerl-rotate"), KEY_X_GMKERL_CROP("x-gmkerl-crop"), KEY_X_GMKERL_EXIF_SWITCH("x-gmkerl-exif-switch"), KEY_MAKE_DIR(
                "folder"), VALUE_FIX_MAX("fix_max"), VALUE_FIX_MIN("fix_min"), VALUE_FIX_WIDTH_OR_HEIGHT("fix_width_or_height"), VALUE_FIX_WIDTH("fix_width"), VALUE_FIX_HEIGHT(
                "fix_height"), VALUE_SQUARE("square"), VALUE_FIX_BOTH("fix_both"), VALUE_FIX_SCALE("fix_scale"), VALUE_ROTATE_AUTO("auto"), VALUE_ROTATE_90("90"), VALUE_ROTATE_180(
                "180"), VALUE_ROTATE_270("270");
        /**
         * 值，又拍云上传用到的一个变量
         */
        private final String value;

        /**
         * 参数，又拍云上传用到的参数
         * 
         * @param val
         *            又拍云上传用到的参数
         */
        private PARAMS(String val) {
            this.value = val;
        }

        /**
         * 获取又拍云上传用到的变量
         * 
         * @return 又拍云上传用到的变量
         */
        public String getValue() {
            return this.value;
        }
    }
}
