/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.creditcard;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import com.bankbusiness.common.AlgorithmUtils;
import com.bankbusiness.common.ErrorCode;
import com.bankbusiness.common.ErrorMessage;
import com.bankbusiness.common.SymbolPool;
import com.bankbusiness.exception.ValidationException;
import com.bankbusiness.validation.IValidation;

/**
 * This class is used for validating the account number of credit card.
 * 
 * To check the account number:
 * <ul>
 * 	<li>First, remove all the ignorable Characters if has.</li>
 * 	<li>Second, check the length of account number.</li>
 * 	<li>Third, check if contains unexpected character in the account number.</li>
 * 	<li>Fourth, check if account number followed Luhn algorithm.</li>
 * </ul>
 * 
 * If one of check point fail, <code>ValidationException</code> would be thrown.
 * 
 * @author Cyber
 *
 */
public class CreditCardValidator implements IValidation, InitializingBean
{
	private static final String[] DEFAULT_IGNORABLE_CHARS = {SymbolPool.WHITESPACE, SymbolPool.HYTHEN};
	private static final int DEFAULT_REQUIRED_NUMBER_LENGTH = 16;
	
	private int m_requiredNumberLength;
	private List<String> m_ignorableCharacterList;
	private Pattern m_ignorableCharsPattern;
	
	@Override
	public void afterPropertiesSet() throws Exception 
	{
		if (m_requiredNumberLength == 0)
		{
			m_requiredNumberLength = DEFAULT_REQUIRED_NUMBER_LENGTH;
		}
		
		if (m_ignorableCharacterList == null || m_ignorableCharacterList.isEmpty())
		{
			m_ignorableCharacterList = Arrays.asList(DEFAULT_IGNORABLE_CHARS);
		}
		
		StringBuilder ignorableCharsRegExpBuilder = new StringBuilder();
		ignorableCharsRegExpBuilder.append(SymbolPool.SQUARE_LEFT_BRACKET)
		.append(StringUtils.join(m_ignorableCharacterList, SymbolPool.EMPTY)).append(SymbolPool.SQUARE_RIGHT_BRACKET);
		
		m_ignorableCharsPattern = Pattern.compile(ignorableCharsRegExpBuilder.toString());
	}
	
    public void validate(String input) throws ValidationException
    {
    	String accountNumber = m_ignorableCharsPattern.matcher(input).replaceAll(SymbolPool.EMPTY);
    	
    	validateNumberLength(accountNumber);
    	validateIfContainsUnexpectedChars(accountNumber);
    	validateNumberAvailable(accountNumber);
    }
    
    /**
     * Check if credit card number is available, followed the Luhn Algorithm.
     * @param accountNumber
     * @throws ValidationException
     */
	private void validateNumberAvailable(String accountNumber) throws ValidationException 
	{
		if (!AlgorithmUtils.isAvailableCreditCardNumber(accountNumber))
		{
			throw new ValidationException(ErrorCode.CREDITCARD_NUMBER_INVALID_NUMBER.getErrorCode(), 
					ErrorMessage.INVALID_NUMBER);
		}
		
	}

	/**
	 * Check if credit card number contains unexpected characters.
	 * @param accountNumber
	 * @throws ValidationException
	 */
	private void validateIfContainsUnexpectedChars(String accountNumber) throws ValidationException 
	{
		for (int i = 0; i < m_requiredNumberLength; i++)
		{
			if (!Character.isDigit(accountNumber.charAt(i)))
			{
				throw new ValidationException(ErrorCode.CREDITCARD_NUMBER_INVALID_CHAR.getErrorCode(),
						ErrorMessage.INVALID_CHAR);
			}
		}
		
	}

	/**
     * Validate the length of account number.
     * @param accountNumber
     * @throws ValidationException 
     */
    private void validateNumberLength(String accountNumber) throws ValidationException 
    {
		if (accountNumber.length() != m_requiredNumberLength)
		{
			throw new ValidationException(ErrorCode.CREDITCARD_NUMBER_INVALID_FORMAT.getErrorCode(), ErrorMessage.INVALID_FORMAT);
		}
		
	}

	public void setRequiredNumberLength(int requiredNumberLength)
    {
    	m_requiredNumberLength = requiredNumberLength;
    }
    
    public void setIgnorableCharacters(List<String> ignorableCharacterList)
    {
    	m_ignorableCharacterList = ignorableCharacterList;
    }

}
