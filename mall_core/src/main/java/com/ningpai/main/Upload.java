package com.ningpai.main;

import com.ningpai.core.UploadClient;
import com.ningpai.fastdfs.bean.FastDFSInfo;
import com.ningpai.util.MyLogger;

/**
 * @author AthrunNatu
 * @since 2014年3月18日下午4:45:17
 */
public final class Upload {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(Upload.class);

    /**
     * 无参构造
     * */
    private Upload() {
    }

    /**
     * 上传至FastDFS
     *
     * @param path
     *            上传文件路径
     * @return 上传的文件在服务器上的路径
     */
    public static String uploadToFSD(String path) {
        try {
            return UploadClient.getUploads().uploadToDFS(path);
        } catch (Exception e) {
            LOGGER.error("",e);
            return "error";
        }
    }

    /**
     * 上传至FastDFS
     * */
    public static String uploadToFSD(String path, FastDFSInfo fastdfs) {
        try {
            return UploadClient.getUploads().uploadToDFS(path, fastdfs);
        } catch (Exception e) {
            LOGGER.error("",e);
            return "error";
        }
    }
}
