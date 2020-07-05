package com.task.validators.interfaces;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.task.validators.UsernameExistValidator;

@Target({ ElementType.LOCAL_VARIABLE, ElementType.FIELD })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = { UsernameExistValidator.class })
public @interface UsernameExist {

	String message() default "Username doesn't exist!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
