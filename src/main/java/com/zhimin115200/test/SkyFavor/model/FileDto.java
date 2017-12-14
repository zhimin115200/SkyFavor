package com.zhimin115200.test.SkyFavor.model;

import java.io.Serializable;
import java.util.Date;

public class FileDto implements Serializable{

    private static final long serialVersionUID = -5338093617913709963L;

    private String fileId;
    private String folderId;

    private String fileName;
    private String content;
    private Integer visitAccount;
    private Integer fileType;
    private Integer isEnable;

    private Date createTime;
    private Date updateTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFolderId() {
        return folderId;
    }

    public void setFolderId(String folderId) {
        this.folderId = folderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Integer getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Integer isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVisitAccount() {
        return visitAccount;
    }

    public void setVisitAccount(Integer visitAccount) {
        this.visitAccount = visitAccount;
    }
}
