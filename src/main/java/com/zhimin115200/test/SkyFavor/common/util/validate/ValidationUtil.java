package com.zhimin115200.test.SkyFavor.common.util.validate;

import com.zhimin115200.test.SkyFavor.common.Constant;
import com.zhimin115200.test.SkyFavor.common.response.ResponseConstant;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidationUtil {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	public static <T> ValidationResult validateEntity(T obj, Class... group) {

		ValidationResult result = new ValidationResult();
		Set<ConstraintViolation<T>> set;
		if(group == null){
			set = validator.validate(obj, Default.class);
		}else{
			set = validator.validate(obj, group);
		}
		if (!CollectionUtils.isEmpty(set)) {
			result.setHasErrors(true);
			Map<String, String> errorMsg = new HashMap<String, String>();
			boolean setErrorCode = false;
			for (ConstraintViolation<T> cv : set) {
				String validateMsg = cv.getMessage();
				String[] msgAry = validateMsg.split("\\|");
				if(msgAry.length != 2){
					setErrorCode = true;
					errorMsg.put(cv.getPropertyPath().toString(), validateMsg);
				}else if(msgAry.length == 2 && setErrorCode == false){
					setErrorCode = true;
					errorMsg.put(cv.getPropertyPath().toString(), msgAry[1]);
				}
			}
			result.setErrorMsg(errorMsg);
		}
		return result;
	}
}
