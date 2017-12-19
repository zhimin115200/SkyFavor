package com.zhimin115200.test.SkyFavor.controller;

import com.alibaba.fastjson.JSON;
import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.common.util.Base64Util;
import com.zhimin115200.test.SkyFavor.model.FolderDto;
import com.zhimin115200.test.SkyFavor.service.FolderService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/folder")
public class FolderResource {

	private static Log logger = LogFactory.getLog(FolderResource.class);

	@Resource
	private FolderService folderService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add/{email}/{folderName}")
	public RestResponseObject add(@PathParam("email") String email, @PathParam("folderName") String folderName) {
		folderName = Base64Util.decode(folderName);
		logger.info("add:"+email+","+folderName);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(email)
				||StringUtils.isEmpty(folderName)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{folderId}/{folderName}")
	public RestResponseObject modify(@PathParam("folderId") String folderId,@PathParam("folderName") String folderName) {
		folderName = Base64Util.decode(folderName);
		logger.info("modify:"+folderId+","+folderName);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(folderId)
				||StringUtils.isEmpty(folderName)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
		FolderDto folderDto =  folderService.get(folderId);
		responseObject.setData(folderDto);
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}
}
