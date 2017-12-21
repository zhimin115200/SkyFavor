package com.zhimin115200.test.SkyFavor.entity;

import org.hibernate.validator.constraints.NotEmpty;
import java.io.Serializable;

public class FolderEntity implements Serializable {

	@NotEmpty(message="收藏夹名不能为空")
	private String folderName;
	@NotEmpty(message="email不能为空")
	private String email;

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "{\"FolderEntity\":{"
				+ "\"folderName\":\"" + folderName + "\""
				+ ",\"email\":\"" + email + "\""
				+ "}}";
	}
}
