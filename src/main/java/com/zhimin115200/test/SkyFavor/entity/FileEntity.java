package com.zhimin115200.test.SkyFavor.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class FileEntity implements Serializable {

	@NotEmpty(message="收藏夹id不能为空")
	private String folderId;
	@NotEmpty(message="文件名不能为空")
	private String fileName;
	@NotEmpty(message="内容不能为空")
	private String content;
	@NotNull(message="类型不能为空")
	private Integer type;

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "{\"FileEntity\":{"
				+ "\"folderId\":\"" + folderId + "\""
				+ ", \"fileName\":\"" + fileName + "\""
				+ ", \"content\":\"" + content + "\""
				+ ", \"type\":\"" + type + "\""
				+ "}}";
	}
}
