package com.infy.customer.utility.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountTypeValidator implements ConstraintValidator<ValidateAccountType, String> {

	@Override
	public boolean isValid(String accountType, ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		if (accountType.equalsIgnoreCase("savings") || accountType.equalsIgnoreCase("current")) {
			return true;
		}
		return false;
	}

}
