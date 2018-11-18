package com.ningpai.fastdfs.bean;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;

/**
 * 实体类-FastDFS设置信息
 *
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月15日上午11:32:39
 */
public class FastDFSInfo {
    /** 编号 */
    private Long fastdfsId;

    /** fastdfs服务器路径 */
    //@NotNull
    @Pattern(regexp = "^(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)\\:(\\d+)$")
    private String serverPath;

    /** fastdfs的http端口号 */
    //@NotNull
    @Digits(integer = 5, fraction = 0)
    private Integer httpPort;

    /** fastdfs保存文件的调用路径 */
    //@NotNull
    @Pattern(regexp = "^http:\\/\\/(\\d+)\\.(\\d+)\\.(\\d+)\\.(\\d+)\\:(\\d+)\\/$")
    private String resultPath;

    /** 是否启用fastdfs 0：不启用 1：启用 */
    private String userd;
    /** 创建时间 */
    private Date createDate;
    /** 修改时间 */
    private Date updateDate;
    /** 扩展字段1 */
    private String temp1;
    /** 扩展字段2 */
    private String temp2;
    /** 扩展字段3 */
    private String temp3;
    /** 扩展字段4 */
    private String temp4;
    /** 扩展字段5 */
    private String temp5;

    public Long getFastdfsId() {
        return fastdfsId;
    }

    public void setFastdfsId(Long fastdfsId) {
        this.fastdfsId = fastdfsId;
    }

    public String getServerPath() {
        return serverPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public Integer getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(Integer httpPort) {
        this.httpPort = httpPort;
    }

    public String getResultPath() {
        return resultPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public String getUserd() {
        return userd;
    }

    public void setUserd(String userd) {
        this.userd = userd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public String getTemp5() {
        return temp5;
    }

    public void setTemp5(String temp5) {
        this.temp5 = temp5;
    }
}
