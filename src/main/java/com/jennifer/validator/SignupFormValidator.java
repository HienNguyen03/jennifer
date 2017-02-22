package com.jennifer.validator;

import com.jennifer.dao.UserInfoDao;
import com.jennifer.dto.SignupForm;
import com.jennifer.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * Additional validator for the sign up form
 */

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean {
	
	private UserInfoDao userInfoDao;
	
	@Autowired
	public void setUserRepo(UserInfoDao userInfoDao){
		this.userInfoDao = userInfoDao;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object target, Errors errors, Object... validationHints) {
		super.validate(target, errors, validationHints);
		
		if(!errors.hasErrors()){
			SignupForm signupForm = (SignupForm) target;
			UserInfo userInfo = userInfoDao.findByEmail(signupForm.getEmail());
			if(userInfo != null){
				errors.rejectValue("email", "emailIsUsed");
			}
		}
			
	}
	
}
