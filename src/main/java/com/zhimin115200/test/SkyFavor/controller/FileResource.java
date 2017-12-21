package com.zhimin115200.test.SkyFavor.controller;

import com.alibaba.fastjson.JSON;
import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.common.util.Base64Util;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationResult;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationUtil;
import com.zhimin115200.test.SkyFavor.entity.FileEntity;
import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.persistence.annotations.CascadeOnDelete;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/file")
public class FileResource {

	private static Log logger = LogFactory.getLog(FileResource.class);

	@Resource
	private FileService fileService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public RestResponseObject add(@RequestBody FileEntity fileEntity) {

		logger.info("add:"+fileEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(fileEntity);
		if (validateResult.isHasErrors()) {
			logger.info("isHasErrors:" + validateResult.getErrorMsg());
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String folderId = fileEntity.getFolderId();
		String fileName = fileEntity.getFileName();
		String content = fileEntity.getContent();
		if(fileService.add(folderId,fileName,content)){
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(ResponseConstant.ERROR_MSG_BASIC);
		}

		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/delete/{fileId}")
	public RestResponseObject delete(@PathParam("fileId") String fileId) {
		logger.info("delete:"+fileId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(fileId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		if(fileService.delete(fileId)){
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(ResponseConstant.ERROR_MSG_BASIC);
		}

		return responseObject;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{fileId}")
	public RestResponseObject modify(@PathParam("fileId") String fileId ,@RequestBody FileEntity fileEntity) {
		logger.info("modify:"+fileEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(fileEntity);
		if (validateResult.isHasErrors()) {
			logger.info("isHasErrors:" + validateResult.getErrorMsg());
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String fileName = fileEntity.getFileName();
		String content = fileEntity.getContent();
		if(fileService.modify(fileId,fileName,content)){
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(ResponseConstant.ERROR_MSG_BASIC);
		}

		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAll/{folderId}")
	public RestResponseObject getAll(@PathParam("folderId") String folderId) {
		logger.info("getAll:"+folderId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(folderId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		List<FileDto> fileDtos =  fileService.getAll(folderId);
		responseObject.setData(JSON.toJSONString(fileDtos));
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{fileId}")
	public RestResponseObject get(@PathParam("fileId") String fileId) {
		logger.info("get:"+fileId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(fileId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		FileDto fileDto =  fileService.get(fileId);
		responseObject.setData(fileDto);
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/visitPlus/{fileId}")
	public RestResponseObject visitPlus(@PathParam("fileId") String fileId) {
		logger.info("visitPlus:"+fileId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(fileId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		if(fileService.visitPlus(fileId)){
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(ResponseConstant.ERROR_MSG_BASIC);
		}

		return responseObject;
	}
}
