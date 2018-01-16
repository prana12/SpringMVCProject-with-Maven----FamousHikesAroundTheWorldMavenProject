package com.project.hikes.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=HikeDistanceOnlyPositiveNumbersConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HikeDistanceOnlyPositiveNumbers {

	//define default course code
	public float value() default 0;

	//define default error msg
	public String message() default "Distance must be greater than zero";

	//define default groups
	public Class<?>[] groups() default {};

	//define default payloads
	public Class<? extends Payload>[] payload() default {};


}
