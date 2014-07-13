/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.common;

/**
 * Utility class for algorithm methods.
 * @author Cyber
 */
public abstract class AlgorithmUtils 
{
	/**
	 * Check if the input number is available credit card number.
	 * @param creditCardNumber
	 * @return
	 */
	public static boolean isAvailableCreditCardNumber(String creditCardNumber)
	{
		int length = creditCardNumber.length();
		int sumUp = 0;
		int num;
		for (int i = 0; i < length; i++)
		{
			num = Character.digit(creditCardNumber.charAt(i), 10);
			if (i % 2 != 0)
			{
				sumUp += 2 * num;
				// If num in 0~4, 2*num is still one digital. 
				if(num > 4)
				{
					// If 2*num gets two digitals, the sum up of two digitals = 2 * num - 9.
					sumUp -= 9;
				}
			}
			else
			{
				sumUp += num;
			}
		}
		
		return (0 == sumUp%10);
	}
}
