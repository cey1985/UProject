/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.creditcard;

import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.bankbusiness.common.StatusCode;
import com.bankbusiness.common.SymbolPool;
import com.bankbusiness.exception.ValidationException;
import com.bankbusiness.validation.creditcard.CreditCardValidator;

public class CreditCardValidatorCheckSteps 
{
	private CreditCardValidator m_validator;
	private String m_accountNumber;
	private int m_requiredNumberLength;
	private List<String> m_ignorableCharacterList;
	
	@Given("the length of creditcard account number is $requiredNumberLength")
	public void theLengthOfCreditcardAccountNumber(int requiredNumberLength)
	{
		m_requiredNumberLength = requiredNumberLength;
	}
	
	@Given("character(s) that can be ignored: $symbol")
	public void ignorableChars(String symbol)
	{
		String[] ignorableCharacters = symbol.split(SymbolPool.COMMA);
		List<String> ignorableSymbolList = new ArrayList<String>();
		for (String ignorableChar : ignorableCharacters)
		{
			if (ignorableChar.equalsIgnoreCase("whitespace"))
			{
				ignorableSymbolList.add(SymbolPool.WHITESPACE);
			}
			else if (ignorableChar.equalsIgnoreCase("hyphen"))
			{
				ignorableSymbolList.add(SymbolPool.HYTHEN);
			}
		}
		
		m_ignorableCharacterList = ignorableSymbolList;
	}
	
	@When("an account number of creditcard '$accountNumber' is coming")
	public void accountNumberOfCreditcard(String accountNumber)
	{
		m_accountNumber = accountNumber;
	}
	
	@Then("this account number should be able to $status the verfication")
	@Alias("this account number should be able to $status the verfication: $errorCode-$errorMsg")
	public void passTheVerification(@Named("status")String status, @Named("errorCode")int errorCode,
			@Named("errorMsg")String errorMsg)
	{
		m_validator = new CreditCardValidator();
		m_validator.setRequiredNumberLength(m_requiredNumberLength);
		m_validator.setIgnorableCharacters(m_ignorableCharacterList);
		ValidationException exception = null;
		
		try
		{
			m_validator.validate(m_accountNumber);
		}
		catch(ValidationException e)
		{
			exception = e;
		}
		
		// positive case
		if (exception == null) 
		{
			Assert.assertEquals(StatusCode.PASS.getValue(), status);
		}
		// 
		else
		{
			Assert.assertEquals(StatusCode.FAIL.getValue(), status);
			Assert.assertEquals(errorCode, exception.getErrorCode());
			Assert.assertEquals(errorMsg, exception.getMessage());
		}
	}
}
