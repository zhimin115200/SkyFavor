package com.zhimin115200.test.SkyFavor.service.impl;

import com.zhimin115200.test.SkyFavor.common.util.Convert;
import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.persistent.FileDao;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_File;
import com.zhimin115200.test.SkyFavor.service.FileService;
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
public class FileServiceImpl implements FileService {

	@Resource
	private FileDao fileDao;
	@Resource
	private UserService userService;

	@Override
	public boolean add(String folderId, String fileName, String content) {
		SF_File file = new SF_File();
		file.setFileName(fileName);
		file.setContent(content);
		file.setFileId(UUID.randomUUID().toString().replace("-", ""));
		file.setFolderId(folderId);
		file.setVisitAccount(0);
		file.setFileType(1);
		file.setIsEnable(1);
		return fileDao.add(file);
	}

	@Override
	public boolean delete(String fileId) {
		SF_File file = fileDao.get(fileId);
		file.setIsEnable(0);
		return fileDao.modify(file);
	}

	@Override
	@Transactional(readOnly = true)
	public FileDto get(String fileId) {
		SF_File file = fileDao.get(fileId);
		if(file!=null){
			return Convert.toDto(file);
		}else{
			return null;
		}
	}

	@Override
	public boolean modify(String fileId,String fileName,String content) {
		if(StringUtils.isNotEmpty(fileId)
				&&get(fileId)!=null){
			SF_File file = fileDao.get(fileId);
			file.setFileName(fileName);
			file.setContent(content);
			return fileDao.modify(file);
		}
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public List<FileDto> getAll(String folderId) {
		List<SF_File> files =  fileDao.getAll(folderId);
		List<FileDto> dtos = new ArrayList<>();
		for (SF_File file: files) {
			dtos.add(Convert.toDto(file));
		}
		return dtos;
	}

	@Override
	public boolean visitPlus(String fileId) {
		if(StringUtils.isNotEmpty(fileId)
				&&get(fileId)!=null){
			SF_File file = fileDao.get(fileId);
			file.setVisitAccount(file.getVisitAccount()+1);
			return fileDao.modify(file);
		}
		return false;
	}
}
