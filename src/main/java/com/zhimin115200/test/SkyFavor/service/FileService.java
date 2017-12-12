package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.model.FileDto;

import java.util.List;

public interface FileService {
	boolean add(String folderId,String fileName,String content);
	boolean delete(String fileId);
	FileDto get(String fileId);
	boolean modify(FileDto fileDto);
	List<FileDto> getAll(String folderId);
}
