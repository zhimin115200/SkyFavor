package com.zhimin115200.test.SkyFavor.persistent.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sf_folder", catalog = "test")
public class SF_Folder implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected long id;

	@Column(name = "folder_id", nullable = true)
	private String folderId;
	@Column(name = "folder_name", nullable = true)
	private String folderName;
	@Column(name = "email", nullable = true)
	private String email;
	@Column(name = "is_enable", nullable = true)
	private Integer isEnable;

	@Column(name = "gmt_create", nullable = true)
	private Date createTime;
	@Column(name = "gmt_modify", nullable = true)
	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
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

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
