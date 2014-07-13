/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.common;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

import com.bankbusiness.common.AlgorithmUtils;

/**
 * Unit test cases for AlgorithmUtils.
 * @author Cyber
 */
public class AlgorithmUtilsTest 
{
	private static final String FAILURE_MSG = "Oops! Failure encountered unexpectedly";
	
	@Test
	public void testIsAvailableCreditCardNumber()
	{
		boolean result = AlgorithmUtils.isAvailableCreditCardNumber("1234567812347698");
		MatcherAssert.assertThat(FAILURE_MSG, true == result);
	}
	
	@Test
	public void testIsAvailableCreditCardNumberWithInvalidNumber()
	{
		boolean result = AlgorithmUtils.isAvailableCreditCardNumber("1234567812337698");
		MatcherAssert.assertThat(FAILURE_MSG, false == result);
	}
}
