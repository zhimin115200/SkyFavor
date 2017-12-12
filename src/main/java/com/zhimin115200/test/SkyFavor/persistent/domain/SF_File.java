package com.zhimin115200.test.SkyFavor.persistent.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sf_file", catalog = "test")
public class SF_File implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected long id;


	@Column(name = "file_id", nullable = true)
	private String fileId;
	@Column(name = "folder_id", nullable = true)
	private String folderId;

	@Column(name = "content", nullable = true)
	private String content;
	@Column(name = "type", nullable = true)
	private Integer type;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
