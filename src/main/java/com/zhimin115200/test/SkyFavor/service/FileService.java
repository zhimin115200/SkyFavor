package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.model.FolderDto;

import java.util.List;

public interface FileService {
	String add(String folderId,String fileName,String content);
	boolean delete(String fileId);
	FileDto get(String fileId);
	boolean modify(FileDto fileDto);
	boolean deleteAll(String folderId);
	List<FileDto> getAll(String folderId);
}
