package com.zhimin115200.test.SkyFavor.service;

import com.zhimin115200.test.SkyFavor.model.FolderDto;

import java.util.List;

public interface FolderService {
	boolean add(String email,String name);
	boolean delete(String folderId);
	FolderDto get(String folderId);
	boolean modify(String folderId,String name);

	List<FolderDto> getAll(String email);
}
