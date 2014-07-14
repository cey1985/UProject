/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.creditcard;
import java.util.Arrays;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.bankbusiness.common.AlgorithmUtils;
import com.bankbusiness.common.ErrorCode;
import com.bankbusiness.common.ErrorMessage;
import com.bankbusiness.common.SymbolPool;
import com.bankbusiness.exception.ValidationException;
import com.bankbusiness.validation.creditcard.CreditCardValidator;

/**
 * Unit test cases for CreditCardValidator.
 * @author Cyber
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({AlgorithmUtils.class})
public class CreditCardValidatorTest
{
	private static final String FAILURE_MSG = "Oops! Failure encountered unexpectedly";
	private static final int DEFAULT_REQUIRED_NUMBER_LENGTH = 16;
	private static final String[] DEFAULT_IGNORABLE_CharacterS_LIST = {SymbolPool.WHITESPACE, SymbolPool.HYTHEN};
    private CreditCardValidator validator;

    @Before
    public void setup() throws Exception 
    {
        validator = new CreditCardValidator();
        validator.setRequiredNumberLength(DEFAULT_REQUIRED_NUMBER_LENGTH);
        validator.setIgnorableCharacters(Arrays.asList(DEFAULT_IGNORABLE_CharacterS_LIST));
        validator.afterPropertiesSet();
        
        PowerMockito.mockStatic(AlgorithmUtils.class);
    }

    @Test
    public void testAvailableAccountNumber() 
    {
    	PowerMockito.when(AlgorithmUtils.isAvailableCreditCardNumber(Mockito.anyString())).thenReturn(true);
    	ValidationException exception = null;
    	try
    	{
    		validator.validate("1234567812347698");
    	}
        catch(ValidationException e)
        {
        	exception = e;
        }
    	
    	MatcherAssert.assertThat(FAILURE_MSG, null == exception);
    	
    }
    
    @Test
    public void testAvailableAccountNumberContainedExtraIgnorableCharacters()
    {
    	PowerMockito.when(AlgorithmUtils.isAvailableCreditCardNumber(Mockito.anyString())).thenReturn(true);
    	ValidationException exception = null;
    	try
    	{
    		validator.validate(" - 12345-6781  2347-698   -");
    	}
        catch(ValidationException e)
        {
        	exception = e;
        }
    	
    	MatcherAssert.assertThat(FAILURE_MSG, null == exception);
    }
    
    @Test
    public void testInvalidAccountNumberWithLessLength()
    {
    	try
    	{
    		validator.validate("123456789");
    		Assert.fail(FAILURE_MSG);
    	}
        catch(ValidationException e)
        {
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_FORMAT, Matchers.equalToIgnoringCase(e.getMessage()));
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_FORMAT.getErrorCode(), Matchers.equalTo(e.getErrorCode()));
        }
    	
    }
    
    @Test
    public void testInvalidAccountNumberContainsSomeUnignorableCharacters()
    {
    	try
    	{
    		validator.validate("  123-45_67834_7698   ");
    		Assert.fail(FAILURE_MSG);
    	}
        catch(ValidationException e)
        {
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_CHAR, Matchers.equalToIgnoringCase(e.getMessage()));
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_CHAR.getErrorCode(), Matchers.equalTo(e.getErrorCode()));
        }
    	
    }
    
    @Test
    public void testInvalidAccountNumberContainsSomeIgnorableCharacters()
    {
    	try
    	{
    		validator.validate("  12345 - 6789 - ");
    		Assert.fail(FAILURE_MSG);
    	}
        catch(ValidationException e)
        {
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_FORMAT, Matchers.equalToIgnoringCase(e.getMessage()));
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_FORMAT.getErrorCode(), Matchers.equalTo(e.getErrorCode()));
        }
    	
    }
    
    @Test
    public void testInvalidAccountNumberBreakTheAlgorithm()
    {
    	PowerMockito.when(AlgorithmUtils.isAvailableCreditCardNumber(Mockito.anyString())).thenReturn(false);
    	try
    	{
    		validator.validate("1234567812345658");
    		Assert.fail(FAILURE_MSG);
    	}
        catch(ValidationException e)
        {
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_NUMBER, Matchers.equalToIgnoringCase(e.getMessage()));
        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_NUMBER.getErrorCode(), Matchers.equalTo(e.getErrorCode()));
        }
    }
    
}
