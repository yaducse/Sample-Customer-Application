package com.infy.customer.utility.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;


import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = AccountTypeValidator.class )
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ FIELD, METHOD, PARAMETER })
public @interface ValidateAccountType {
	
	String message();
    
    Class<?>[] groups() default {};
     
    Class<? extends Payload>[] payload() default {};
    
}
