/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.common;

/**
 * This status code is used for defining the status of each scenario.
 * 
 * @author Cyber
 *
 */
public enum StatusCode 
{
	PASS("pass"), FAIL("fail");
	
	private String m_statusCode;
	
	StatusCode(String statusCode)
	{
		m_statusCode = statusCode;
	}
	
	public String getValue()
	{
		return m_statusCode;
	}
	
	public static StatusCode fromValue(String value)
	{
		if (PASS.getValue().equals(value)) 
		{
			return PASS;
		}
		else if (FAIL.getValue().equals(value))
		{
			return FAIL;
		}
		
		return null;
	}
}
