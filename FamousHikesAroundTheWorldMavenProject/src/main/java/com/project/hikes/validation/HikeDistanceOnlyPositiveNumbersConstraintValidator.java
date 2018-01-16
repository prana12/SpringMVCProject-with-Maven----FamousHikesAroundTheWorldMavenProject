package com.project.hikes.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.project.hikes.entity.HikeTrail;



public class HikeDistanceOnlyPositiveNumbersConstraintValidator implements ConstraintValidator<HikeDistanceOnlyPositiveNumbers, Float> {

	private float value;
	
	@Override
	public void initialize(HikeDistanceOnlyPositiveNumbers hikeDistanceOnlyPositiveNumbers) {
		value = hikeDistanceOnlyPositiveNumbers.value();
	}
	
	@Override
	public boolean isValid(Float inputDistance, ConstraintValidatorContext constraintValidatorContext) {
		
		//check if the distance entered is greater than zero;
		if(inputDistance == null){
			return true;  //returning true because @NotNull is being used on Entity class
		}
		
		if(inputDistance != null){
			if(inputDistance>value){
				System.out.println("Entered distance is greater than 0 here. The actual value is " + inputDistance );
				return true;
			}
		}
		
		return false;
	}

	/*@Override
	public boolean isValid(HikeTrail hikeTrail, ConstraintValidatorContext arg1) {
		boolean result=false;
		if(hikeTrail.getDistance() != null){
			Float floatDistance = hikeTrail.getDistance();
			result = floatDistance>value;
		}
		else{
			result= true;
		}

		return result;
	}*/

}