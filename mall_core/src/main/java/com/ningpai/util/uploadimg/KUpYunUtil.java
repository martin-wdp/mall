package com.ningpai.util.uploadimg;

import com.upyun.UpYun;
import com.upyun.UpYunUtil;
import com.upyun.YunBean;

import java.io.File;
import java.io.IOException;

/**
 *
 * 重写又拍云工具类，为了把异常跑出来
 * 
 * @Author:NP-HEHU
 * @date 2015-7-13 11:00:32
 */
public class KUpYunUtil extends UpYunUtil {

    /**
     * 封装的又拍云的一个对象，重写的
     */
    private static KUpYun upyun = null;

    /**
     * 构造方法
     */
    public KUpYunUtil() {
        super();
    }

    /**
     * 构造方法
     * 
     * @param filePath
     *            文件路径
     * @param fileName
     *            文件名
     * @param yunbean
     *            又拍云基本信息类
     * @param suffix
     *            文件扩展名
     * @throws IOException
     */
    public static void kYunUp(String filePath, String fileName, YunBean yunbean, String suffix) throws IOException {
        upyun = new KUpYun(yunbean.getBucketName().trim(), yunbean.getUserName().trim(), yunbean.getPassword());
        upyun.setDebug(true);
        testWriteFile(filePath, fileName, suffix);

    }

    /**
     * 测试上传文件
     * 
     * @param picPath
     *            文件路径
     * @param picName
     *            图片文件名
     * @param suffix
     *            图片扩展名
     * @throws IOException
     */
    public static void testWriteFile(String picPath, String picName, String suffix) throws IOException {
        String filePath = "/" + picName + suffix;
        File file = new File(picPath);
        upyun.setContentMD5(UpYun.md5(file));
        upyun.setFileSecret("");
        upyun.writeFile(filePath, file, true);
    }
}
