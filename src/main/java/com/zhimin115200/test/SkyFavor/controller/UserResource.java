package com.zhimin115200.test.SkyFavor.controller;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.exception.MailException;
import com.zhimin115200.test.SkyFavor.common.exception.UserException;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import com.zhimin115200.test.SkyFavor.common.response.RestResponseObject;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationResult;
import com.zhimin115200.test.SkyFavor.common.util.validate.ValidationUtil;
import com.zhimin115200.test.SkyFavor.entity.ResistEntity;
import com.zhimin115200.test.SkyFavor.entity.UserEntity;
import com.zhimin115200.test.SkyFavor.model.UserDto;
import com.zhimin115200.test.SkyFavor.service.MailService;
import com.zhimin115200.test.SkyFavor.service.UserService;
import com.zhimin115200.test.SkyFavor.service.UserTokenService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Component
@Path("/user")
public class UserResource {

	private static Log logger = LogFactory.getLog(UserResource.class);

	@Resource
	private UserService userService;

	@Resource
	private UserTokenService tokenService;

	@Resource
	private MailService mailService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/validateAccount")
	public RestResponseObject validateAccount(@RequestBody UserEntity userEntity) {
		logger.info("validateAccount:"+userEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(userEntity);
		if (validateResult.isHasErrors()) {
			logger.info("isHasErrors:"+validateResult.getErrorMsg());
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String email = userEntity.getEmail();
		String password = userEntity.getPassword();
		if(userService.validateAccount(email,password)){
			String tk = tokenService.add(email);
			if(StringUtils.isNotEmpty(tk)){
				UserDto dto = new UserDto();
				dto.setEmail(email);
				dto.setToken(tk);
				responseObject.setData(dto);
				responseObject.setCode(ResponseConstant.SUCCESS_CODE);
				responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
			}else{
				responseObject.setCode(ResponseConstant.ERROR_CODE);
				responseObject.setMsg(Constant.ACCOUNT_PASS_ERROR);
			}
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.ACCOUNT_PASS_ERROR);
		}
		return responseObject;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/sendVerify/{email}")
	public RestResponseObject sendVerify(@PathParam("email") String email) {
		logger.info("sendVerify:"+email);
		RestResponseObject responseObject = new RestResponseObject();
		if(StringUtils.isEmpty(email)){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		try{
			mailService.sendVerifyCode(email);
			responseObject.setCode(ResponseConstant.SUCCESS_CODE);
			responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
		}catch(MailException e){
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(e.getMessage());
		}
		return responseObject;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/reset")
	public RestResponseObject reset(@RequestBody ResistEntity registEntity) {
		logger.info("reset:"+registEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(registEntity);
		if (validateResult.isHasErrors()) {
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String email = registEntity.getEmail();
		String password = registEntity.getPassword();
		String verifyCode = registEntity.getVerifyCode();
		if(mailService.validateVerifyCode(email,verifyCode)){
			try{
				userService.resetPass(email,password);
				responseObject.setCode(ResponseConstant.SUCCESS_CODE);
				responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
			}catch (UserException e){
				responseObject.setCode(ResponseConstant.ERROR_CODE);
				responseObject.setMsg(e.getMessage());
			}
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.VERIFY_ERROR);
		}

		return responseObject;
	}


	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/regist")
	public RestResponseObject regist(@RequestBody ResistEntity registEntity) {
		logger.info("regist:"+registEntity);
		RestResponseObject responseObject = new RestResponseObject();
		ValidationResult validateResult = ValidationUtil.validateEntity(registEntity);
		if (validateResult.isHasErrors()) {
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.PARAM_ERROR);
			return responseObject;
		}
		String email = registEntity.getEmail();
		String password = registEntity.getPassword();
		String verifyCode = registEntity.getVerifyCode();
		if(mailService.validateVerifyCode(email,verifyCode)){
			try{
				userService.createAccount(email,password);
				responseObject.setCode(ResponseConstant.SUCCESS_CODE);
				responseObject.setMsg(ResponseConstant.SUCCESS_MSG_BASIC);
			}catch (UserException e){
				responseObject.setCode(ResponseConstant.ERROR_CODE);
				responseObject.setMsg(e.getMessage());
			}
		}else{
			responseObject.setCode(ResponseConstant.ERROR_CODE);
			responseObject.setMsg(Constant.VERIFY_ERROR);
		}
		return responseObject;
	}
}
