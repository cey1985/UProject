/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.common;

/**
 * Stores all the error codes related in the project.
 * @author Cyber
 */
public enum ErrorCode 
{
	CREDITCARD_NUMBER_INVALID_CHAR			(1000),
	CREDITCARD_NUMBER_INVALID_FORMAT		(1001),
	CREDITCARD_NUMBER_INVALID_NUMBER		(1002)
	;
	private int m_errorCode;
	
	private ErrorCode(int errorCode)
	{
		m_errorCode = errorCode;
	}
	
	public int getErrorCode()
	{
		return m_errorCode;
	}
}
