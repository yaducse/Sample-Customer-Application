package com.infy.customer.utility;

public enum CustomerConstants {
	
	GENERAL_EXCEPTION_MESSAGE("general.exception.message"),
	INPUT_PARAM_MISSING("input.parameter.missing"),
	REQUEST_BODY_MISSING("request.body.missing"),
	DATE_FORMAT_INVALID("date.format.invalid"),
	CUSTOMER_NOT_FOUND("customer.not.found"),
	CUSTOMER_NOT_UNIQUE("customer.not.unique"),
	CUSTOMER_REGISTER_SUCCESS("customer.register.success"),
	CUSTOMER_DELETE_SUCCESS("customer.delete.success"),
	CUSTOMER_UPDATE_SUCCESS("customer.update.success"),
	CUSTOMER_LOGIN_SUCCESS("customer.login.success"),
	CUSTOMER_LOGIN_FAILURE("customer.login.failure"),
	CUSTOMER_UPDATE_FAILURE("customer.update.failure");
	
	private final String type;

	CustomerConstants(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

}
