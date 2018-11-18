package com.ningpai.core;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;

import com.ningpai.fastdfs.bean.FastDFSInfo;

/**
 * 
 * @author AthrunNatu
 * 
 * @since 2014年3月18日下午4:17:53
 */
public final class UploadClient {
    /** 静态变量 表示3 */
    private static final int THREE = 3;

    /**
     * 记录日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(UploadClient.class);

    /** 单例对象 */
    private static UploadClient uploads = null;

    /**
     * 构造方法
     */
    private UploadClient() {
    }

    /**
     * 测试方法
     * 
     * @param args
     *            参数
     */
    public static void main(String[] args) {
        try {
            new UploadClient().uploadToDFS("F:\\pic.jpg");
        } catch (final Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }
    }

    /**
     * 获取单例对象
     * 
     * @return UploadClient单例对象
     */
    public static UploadClient getUploads() {
        if (uploads == null) {
            uploads = new UploadClient();
        }
        return uploads;
    }

    /**
     * 上传图片到FastDFS
     * 
     * @param uploadFileURL
     *            图片路径
     * @return 上传后返回的路径
     * @throws Exception
     *             上传图片抛出的异常
     */
    public String uploadToDFS(String uploadFileURL) throws Exception {
        String[] results = null;
        try {
            String classPath = new File(UploadClient.class.getResource("/").getFile()).getCanonicalPath();
            String configFilePath = classPath + "/fdfs_client.conf";

            ClientGlobal.init(configFilePath);

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            NameValuePair[] metaList = new NameValuePair[THREE];
            metaList[0] = new NameValuePair("width", "120");
            metaList[1] = new NameValuePair("heigth", "120");
            metaList[2] = new NameValuePair("author", "Athrun");
            byte[] fileBuff = null;
            if (uploadFileURL.indexOf("http") != -1) {
                URL url = new URL(uploadFileURL);
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();
                BufferedInputStream fis = null;

                try {
                    fis = new BufferedInputStream(inStream);
                    if (fis != null) {
                        int len = fis.available();
                        fileBuff = new byte[len];
                        fis.read(fileBuff);
                    }
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                }

            } else {

                File file = new File(uploadFileURL);
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    if (fis != null) {
                        int len = fis.available();
                        fileBuff = new byte[len];
                        fis.read(fileBuff);
                    }
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                }
            }

            results = storageClient.upload_file(fileBuff, "jpg", metaList);
        } catch (final Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        if (results == null) {
            return "错误";
        }

        return results[1];
    }

    /**
     * 上传图片到FAStDFS，返回图片路径
     * 
     * @param uploadFileURL
     *            图片文件路径
     * @param fastdfs
     *            fastDFS配置
     * @return 图片上传后的路径
     * @throws Exception
     *             上传图片抛出的异常
     */
    public String uploadToDFS(String uploadFileURL, FastDFSInfo fastdfs) throws Exception {
        String[] results = null;
        try {
            // String classPath = new File(UploadClient.class.getResource("/")
            // .getFile()).getCanonicalPath();
            // log.debug("=======================项目路径："+classPath);
            // String configFilePath = classPath + "fdfs_client.conf";
            Properties pop = new Properties();
            try (InputStream inputStream = UploadClient.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/upload.properties")) {
                pop.load(inputStream);
            } catch (final Exception e) {
                LOGGER.error("读取upload.properties属性文件错误", e);
            }
            String configFilePath = null;
            try {
                configFilePath = pop.getProperty("FASTDFS");
            } catch (final Exception e) {
                LOGGER.error("读取FASTDFS配置文件地址错误", e);
            }
            if (null == configFilePath) {
                LOGGER.error("FASTDFS配置文件地址为空");
            }
            ClientGlobal.init(configFilePath);

            // tracker_server
            InetSocketAddress[] trackerServers = new InetSocketAddress[1];
            String[] parts = fastdfs.getServerPath().split("\\:", 2);
            trackerServers[0] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            TrackerGroup tg = new TrackerGroup(trackerServers);
            ClientGlobal.setG_tracker_group(tg);
            // g_tracker_http_port
            ClientGlobal.setG_tracker_http_port(fastdfs.getHttpPort());

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            NameValuePair[] metaList = new NameValuePair[THREE];
            metaList[0] = new NameValuePair("width", "120");
            metaList[1] = new NameValuePair("heigth", "120");
            metaList[2] = new NameValuePair("author", "Athrun");
            byte[] fileBuff = null;
            if (uploadFileURL.indexOf("http") != -1) {

                URL url = new URL(uploadFileURL);
                URLConnection conn = url.openConnection();
                InputStream inStream = conn.getInputStream();
                BufferedInputStream fis = null;

                try {
                    fis = new BufferedInputStream(inStream);
                    if (fis != null) {
                        int len = fis.available();
                        fileBuff = new byte[len];
                        fis.read(fileBuff);
                    }
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                }

            } else {
                File file = new File(uploadFileURL);
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    if (fis != null) {
                        int len = fis.available();
                        fileBuff = new byte[len];
                        fis.read(fileBuff);
                    }
                } finally {
                    if (null != fis) {
                        fis.close();
                    }
                }

            }

            results = storageClient.upload_file(fileBuff, "jpg", metaList);
        } catch (final Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        if (results == null) {
            return "错误";
        }

        return results[1];
    }

    /**
     * 从fastDfs上删除文件
     * 
     * @param fileName
     *            文件名称
     * @param fastdfs
     *            fastDFS配置
     * @return 删除结果
     * @throws Exception
     */
    public int delToDFS(String fileName, FastDFSInfo fastdfs) throws Exception {
        try {
            Properties pop = new Properties();
            try (InputStream inputStream = UploadClient.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/upload.properties")) {
                pop.load(inputStream);
            } catch (final Exception e) {
                LOGGER.error("读取upload.properties属性文件错误", e);
            }
            String configFilePath = null;
            try {
                configFilePath = pop.getProperty("FASTDFS");
            } catch (final Exception e) {
                LOGGER.error("读取FASTDFS配置文件地址错误", e);
            }
            if (null == configFilePath) {
                LOGGER.error("FASTDFS配置文件地址为空");
            }
            ClientGlobal.init(configFilePath);

            // tracker_server
            InetSocketAddress[] trackerServers = new InetSocketAddress[1];
            String[] parts = fastdfs.getServerPath().split("\\:", 2);
            trackerServers[0] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
            TrackerGroup tg = new TrackerGroup(trackerServers);
            ClientGlobal.setG_tracker_group(tg);
            // g_tracker_http_port
            ClientGlobal.setG_tracker_http_port(fastdfs.getHttpPort());

            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            StorageServer storageServer = null;
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            storageClient.delete_file(ClientGlobal.getG_tracker_group().toString(), fileName);
        } catch (final Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
        }

        return 1;
    }
}
