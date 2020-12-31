package com.infy.customer.utility.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BalanceAmountValidator implements ConstraintValidator<ValidateBalanceAmount, Double> {

	@Override
	public boolean isValid(Double balanceAmount, ConstraintValidatorContext constraintValidatorContext) {
		// TODO Auto-generated method stub
		if (balanceAmount < 5000) {
			return false;
		}
		return true;
	}

}
