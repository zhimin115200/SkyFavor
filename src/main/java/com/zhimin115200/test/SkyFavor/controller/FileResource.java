package com.zhimin115200.test.SkyFavor.controller;

import com.alibaba.fastjson.JSON;
import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.exception.UserException;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.model.FileDto;
import com.zhimin115200.test.SkyFavor.service.FileService;
import com.zhimin115200.test.SkyFavor.service.MailService;
import com.zhimin115200.test.SkyFavor.service.UserService;
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
@Path("/file")
public class FileResource {

	private static Log logger = LogFactory.getLog(FileResource.class);

	@Resource
	private FileService fileService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add/{folderId}/{fileName}/{content}/{type}")
	public RestResponseObject add(@PathParam("folderId") String folderId, @PathParam("fileName") String fileName
	,@PathParam("content") String content, @PathParam("type") Integer type) {
		logger.info("add:"+folderId+","+fileName+","+content+","+type);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(folderId)
				||StringUtils.isEmpty(fileName)
				||StringUtils.isEmpty(content)
				||type==null){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
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

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/modify/{fileId}/{fileName}/{content}")
	public RestResponseObject modify(@PathParam("fileId") String fileId,@PathParam("fileName") String fileName
			,@PathParam("content") String content) {
		logger.info("modify:"+fileId+","+fileName+","+content);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(fileId)
				||StringUtils.isEmpty(fileName)
				||StringUtils.isEmpty(content)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
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
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
		FileDto fileDto =  fileService.get(fileId);
		responseObject.setData(fileDto);
		responseObject.setCode(ResponseConstant.SUCCESS_CODE);
		responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		return responseObject;
	}
}
