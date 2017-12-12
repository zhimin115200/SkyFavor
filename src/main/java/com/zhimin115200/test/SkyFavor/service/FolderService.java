package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.model.FolderDto;

import java.util.List;

public interface FolderService {
	String add(String name);
	boolean delete(String folderId);
	FolderDto get(String folderId);
	boolean modify(FolderDto folderDto);

	List<FolderDto> getAll(String email);
}
