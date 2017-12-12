package com.zhimin115200.test.SkyFavor.persistent;

import com.zhimin115200.test.SkyFavor.persistent.domain.SF_File;

import java.util.List;

public interface FileDao {
	SF_File get(String fileId);
	boolean add(SF_File file);
	boolean delete(String fileId);
	boolean modify(SF_File file);
	List<SF_File> getAll(String folderId);
}
