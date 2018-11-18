package com.ningpai.util;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by fengal on 2015/5/22. 文件上传工具
 */
public final class FileUploadUtil {

    /** 记录日志对象 */
    private static final Logger LOGGER = Logger.getLogger(FileUploadUtil.class);

    /**
     * 保存文件
     * 
     * @param stream
     *            文件流，从前台传来
     * @param path
     *            文件路径，保存在什么地方
     * @param filename
     *            文件名
     * @throws IOException
     *             读写异常
     */
    public static void SaveFileFromInputStream(InputStream stream, String path, String filename) throws IOException {
        FileOutputStream fs = new FileOutputStream(path + "/" + filename);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();
        stream.close();
    }

    /**
     * 上传文件
     * 
     * @param file
     *            前台传来的文件
     * @return 上传文件后的路径
     */
    public static String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            return null;
        }
        // 得到文件名
        String filename = file.getOriginalFilename();
        // 当前毫秒值
        String newFileName = System.currentTimeMillis() + "";
        Properties properties = PropertieUtil.readPropertiesFile(FileUploadUtil.class.getClassLoader().getResourceAsStream("com/ningpai/web/config/app.properties"));
        // 文件所在文件夹路径
        String path = properties.getProperty("APP_UPLOAD_PATH");
        // 源文件扩展名
        String extendName = filename.indexOf(".") > 0 ? filename.substring(filename.lastIndexOf("."), filename.length()) : "";
        // 新文件全路径（绝对路径）
        String fullPath = path + newFileName + extendName;
        if (file.getSize() > 0) {
            FileOutputStream fs = null;
            InputStream stream = null;
            try {
                stream = file.getInputStream();
                fs = new FileOutputStream(fullPath);
                byte[] buffer = new byte[1024 * 1024];
                int byteread = 0;
                while ((byteread = stream.read(buffer)) != -1) {
                    fs.write(buffer, 0, byteread);
                    fs.flush();
                }

            } catch (FileNotFoundException e) {
                LOGGER.error("文件路径" + fullPath + "无法访问", e);
            } catch (IOException e) {
                LOGGER.error("文件读写失败", e);
            } finally {
                try {
                    if (fs != null) {
                        fs.close();
                    }
                    if (stream != null) {
                        stream.close();
                    }
                } catch (IOException e) {
                    LOGGER.error("文件流关闭失败", e);
                }
            }
        }
        return newFileName + extendName;
    }
}
