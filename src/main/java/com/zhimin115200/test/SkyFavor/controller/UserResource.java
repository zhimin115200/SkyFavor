package com.zhimin115200.test.SkyFavor.controller;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserResource {

	private static Log logger = LogFactory.getLog(UserResource.class);

	@Resource
	private UserService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/validateAccount/{email}/{password}")
	public RestResponseObject validateAccount(@PathParam("email") String email, @PathParam("password") String password) {
		logger.info("validateAccount:"+email+","+password);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(email)
				||StringUtils.isEmpty(password)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_NOT_NULL);
		}
		if(service.validateAccount(email,password)){
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.ACCOUNT_PASS_ERROR);
		}

		return responseObject;
	}
}
