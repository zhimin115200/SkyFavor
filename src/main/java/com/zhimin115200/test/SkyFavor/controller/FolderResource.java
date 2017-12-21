package com.zhimin115200.test.SkyFavor.controller;

import com.alibaba.fastjson.JSON;
import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.common.util.Base64Util;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationResult;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationUtil;
import com.zhimin115200.test.SkyFavor.entity.FolderEntity;
import com.zhimin115200.test.SkyFavor.model.FolderDto;
import com.zhimin115200.test.SkyFavor.service.FolderService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/folder")
public class FolderResource {

	private static Log logger = LogFactory.getLog(FolderResource.class);

	@Resource
	private FolderService folderService;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public RestResponseObject add(@RequestBody FolderEntity folderEntity) {
		logger.info("add:"+folderEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(folderEntity);
		if (validateResult.isHasErrors()) {
			logger.info("isHasErrors:" + validateResult.getErrorMsg());
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String email = folderEntity.getEmail();
		String folderName = folderEntity.getFolderName();
		if(folderService.add(email,folderName)){
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
	@Path("/delete/{folderId}")
	public RestResponseObject delete(@PathParam("folderId") String folderId) {
		logger.info("delete:"+folderId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(folderId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		if(folderService.delete(folderId)){
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
	@Path("/modify/{folderId}")
	public RestResponseObject modify(@PathParam("folderId") String folderId,@RequestBody FolderEntity folderEntity) {
		logger.info("modify:"+folderId+","+folderEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(folderEntity);
		if (validateResult.isHasErrors()) {
			logger.info("isHasErrors:" + validateResult.getErrorMsg());
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String folderName = folderEntity.getFolderName();
		if(folderService.modify(folderId,folderName)){
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
	@Path("/getAll/{email}")
	public RestResponseObject getAll(@PathParam("email") String email) {
		logger.info("getAll:"+email);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(email)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
		}
		List<FolderDto> folderDtos =  folderService.getAll(email);
		responseObject.setData(JSON.toJSONString(folderDtos));
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get/{folderId}")
	public RestResponseObject get(@PathParam("folderId") String folderId) {
		logger.info("get:"+folderId);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(folderId)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
		}
		FolderDto folderDto =  folderService.get(folderId);
		responseObject.setData(folderDto);
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}
}
