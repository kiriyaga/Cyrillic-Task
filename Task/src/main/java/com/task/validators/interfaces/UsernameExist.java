package com.task.validators.interfaces;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.task.validators.UsernameExistValidator;

@Target({FIELD})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { UsernameExistValidator.class })
public @interface UsernameExist {

	 String message() default "Username: {value} already exist!";
	    Class<?>[] groups() default { };
	    Class<? extends Payload>[] payload() default { };
	    
	    String value();
	    
}
