package com.project.hikes.validation;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.project.hikes.entity.HikeUser;

@Component
public class HikePasswordAndEmailValidator implements Validator {

	@Override
	public boolean supports(Class<?> paramClass) {
		return HikeUser.class.equals(paramClass);
		//return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		HikeUser user = (HikeUser)obj;
		
		//check email is invalid
		if(user.getEmail()!=null){
		Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
	            Pattern.CASE_INSENSITIVE);
		      if (!(emailPattern.matcher(user.getEmail()).matches())) {
		         errors.rejectValue("email", "invalid.email", "Email is Invalid");
		      }
		}
		
		//to make sure both passwords are same
		if(user.getPassword()!=null && user.getConfirmPassword()!=null){
			if (!user.getPassword().equals(user.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "different.passwords");
	
			}
		}
		
	}

}
