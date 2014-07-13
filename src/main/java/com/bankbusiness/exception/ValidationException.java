/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.exception;

/**
 * Wrap the exceptions comes from validation.
 * @author Cyber
 */
public class ValidationException extends Exception
{
	/**
	 * Check the consistency of Object while serialization in the runtime.
	 */
	private static final long serialVersionUID = 7940942414327944320L;
	
	private int m_errorCode;
	
	public ValidationException(int errorCode, String errorMsg, Throwable t)
	{
		super(errorMsg, t);
		m_errorCode = errorCode;
		
	}
	
	public ValidationException(int errorCode, String errorMsg)
	{
		this(errorCode, errorMsg, null);
	}
	
	/**
	 * Retrieve the error code.
	 * @return
	 */
	public int getErrorCode()
	{
		return m_errorCode;
	}
}
