package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.util.Convert;
import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.model.FolderDto;
import com.zhimin115200.test.SkyFavor.persistent.FolderDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Folder;
import com.zhimin115200.test.SkyFavor.service.FileService;
import com.zhimin115200.test.SkyFavor.service.FolderService;
import com.zhimin115200.test.SkyFavor.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FolderServiceImpl implements FolderService {

	@Resource
	private FolderDao folderDao;
	@Resource
	private FileService fileService;
	@Resource
	private UserService userService;

	@Override
	public boolean add(String email,String name) {
		if(userService.isExist(email)){
			SF_Folder folder = new SF_Folder();
			folder.setFolderId(UUID.randomUUID().toString().replace("-", ""));
			folder.setEmail(email);
			folder.setFolderName(name);
			folder.setIsEnable(1);
			return folderDao.add(folder);
		}
		return false;
	}

	@Override
	public boolean delete(String folderId) {
		SF_Folder folder = folderDao.get(folderId);
		folder.setIsEnable(0);
		if(folderDao.modify(folder)){
			List<FileDto> fileDtos = fileService.getAll(folderId);
			for (FileDto dto: fileDtos) {
				fileService.delete(dto.getFileId());
			}
		}else return false;
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public FolderDto get(String folderId) {
		SF_Folder folder = folderDao.get(folderId);
		return Convert.toDto(folder);
	}

	@Override
	public boolean modify(FolderDto folderDto) {
		String folderId = folderDto.getFolderId();
		if(StringUtils.isNotEmpty(folderId)
				&&get(folderId)!=null){
			SF_Folder folder = Convert.toDto(folderDto);
			return folderDao.modify(folder);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FolderDto> getAll(String email) {
		List<SF_Folder> folders = folderDao.getAll(email);
		List<FolderDto> dtos = new ArrayList<>();
		for (SF_Folder folder: folders) {
			FolderDto dto = Convert.toDto(folder);
			dtos.add(dto);
		}
		return dtos;
	}
}
