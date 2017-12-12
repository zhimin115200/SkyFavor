package com.zhimin115200.test.SkyFavor.common.util;

import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.model.FolderDto;
import com.zhimin115200.test.SkyFavor.model.UserDto;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_File;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_Folder;
import com.zhimin115200.test.SkyFavor.persistent.domain.SF_User;

public class Convert {

    public static UserDto toDto(SF_User user){
        UserDto dto = new UserDto();
        dto.setEmail(user.getEmail());
//        dto.setPassword(user.getPassword());
        dto.setId(user.getId());
        dto.setCreateTime(user.getCreateTime());
        dto.setUpdateTime(user.getUpdateTime());
        return dto;
    }

    public static FolderDto toDto(SF_Folder folder){
        FolderDto dto = new FolderDto();
        dto.setId(folder.getId());
        dto.setCreateTime(folder.getCreateTime());
        dto.setEmail(folder.getEmail());
        dto.setFolderId(folder.getFolderId());
        dto.setFolderName(folder.getFolderName());
        dto.setUpdateTime(folder.getUpdateTime());
        return dto;
    }

    public static SF_Folder toDto(FolderDto dto){
        SF_Folder folder = new SF_Folder();
        folder.setId(dto.getId());
        folder.setCreateTime(dto.getCreateTime());
        folder.setEmail(dto.getEmail());
        folder.setFolderId(dto.getFolderId());
        folder.setFolderName(dto.getFolderName());
        folder.setUpdateTime(dto.getUpdateTime());
        return folder;
    }

    public static FileDto toDto(SF_File file){
        FileDto dto = new FileDto();
        dto.setContent(file.getContent());
        dto.setCreateTime(file.getCreateTime());
        dto.setFileId(file.getFileId());
        dto.setFolderId(file.getFolderId());
        dto.setId(file.getId());
        dto.setFileType(file.getFileType());
        dto.setUpdateTime(file.getUpdateTime());
        return dto;
    }

    public static SF_File toDto(FileDto dto){
        SF_File file = new SF_File();
        file.setContent(dto.getContent());
        file.setCreateTime(dto.getCreateTime());
        file.setFileId(dto.getFileId());
        file.setFolderId(dto.getFolderId());
        file.setId(dto.getId());
        file.setFileType(dto.getFileType());
        file.setUpdateTime(dto.getUpdateTime());
        return file;
    }
}
