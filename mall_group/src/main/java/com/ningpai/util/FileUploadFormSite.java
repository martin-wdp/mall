package com.ningpai.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 小组
 * @author ggn
 *
 */
public class FileUploadFormSite extends FileUploadForm {

    //小组头像
    private MultipartFile groupPic;
    
    //小组背景
    private MultipartFile groupBackGroundPath;
    
    //教摆摆图片
    private MultipartFile teachImgInput;

    public MultipartFile getGroupPic() {
        return groupPic;
    }

    public void setGroupPic(MultipartFile groupPic) {
        this.groupPic = groupPic;
    }

    public MultipartFile getGroupBackGroundPath() {
        return groupBackGroundPath;
    }

    public void setGroupBackGroundPath(MultipartFile groupBackGroundPath) {
        this.groupBackGroundPath = groupBackGroundPath;
    }

    public MultipartFile getTeachImgInput() {
        return teachImgInput;
    }

    public void setTeachImgInput(MultipartFile teachImgInput) {
        this.teachImgInput = teachImgInput;
    }

    
}
