/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation;

import com.bankbusiness.exception.ValidationException;

/**
 * Validation interface for all the validators in the project.
 * @author Cyber
 */
public interface IValidation 
{
	public void validate(String input) throws ValidationException;
}
