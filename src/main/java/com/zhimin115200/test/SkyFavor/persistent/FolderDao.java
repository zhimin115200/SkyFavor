package com.zhimin115200.test.SkyFavor.persistent;

import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Folder;

import java.util.List;

public interface FolderDao {
	SF_Folder get(String folderId);
	boolean add(SF_Folder folder);
	boolean delete(String folderId);
	boolean modify(SF_Folder folder);
	List<SF_Folder> getAll(String email);
}
