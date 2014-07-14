/**
 * Copyright $copy; 2014 Cyber. All rights reserved.
 */
package com.bankbusiness.validation.integrationtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bankbusiness.common.ErrorCode;
import com.bankbusiness.common.ErrorMessage;
import com.bankbusiness.exception.ValidationException;
import com.bankbusiness.validation.IValidation;

/**
 * Integration testing for CreditCardValidator.
 * @author Cyber
 */
public class CreditCardValidatorIntegrationTest 
{
	private static final String FAILURE_MSG = "Oops! Failure encountered unexpectedly";
	private static final String SPRING_XML_FILE_NAME = "application.context.spring.xml";
	private static final String CREDIT_CARD_VALIDATOR_BEAN_NAME = "com.bankbusiness.validation.creditcard.CreditCardValidator";
	private static List<String> s_storeValidators = null;
	private static ApplicationContext s_context = null;
	
	@BeforeClass
	public static void initialze()
	{
		s_context = new ClassPathXmlApplicationContext(SPRING_XML_FILE_NAME);
	}
	
	@Test
	public void testRunningBaseOnRealEnv()
	{
		runInOneThread(true);
	}

	private StringBuilder runInOneThread(boolean isThrowable) {
		List<String> inputs = initInputs();
		IValidation validator = s_context.getBean(CREDIT_CARD_VALIDATOR_BEAN_NAME, IValidation.class);
		
		if (s_storeValidators != null)
		{
			s_storeValidators.add(validator.toString());
		}
		StringBuilder result = new StringBuilder();
		int index = 0;
		for(String input : inputs)
		{
			ValidationException exception = null;
			try
			{
				validator.validate(input);
			}
			catch(ValidationException e)
			{
				exception = e;
			}
			
			try
			{
				switch(index)
				{
				case 0:
				case 1:
					MatcherAssert.assertThat(FAILURE_MSG, null == exception);
					break;
				case 2:
				case 3:
					MatcherAssert.assertThat(FAILURE_MSG, null != exception);
					MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_FORMAT, 
							Matchers.equalToIgnoringCase(exception.getMessage()));
		        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_FORMAT.getErrorCode(), 
		        			Matchers.equalTo(exception.getErrorCode()));
		        	break;
				case 4:
					MatcherAssert.assertThat(FAILURE_MSG, null != exception);
					MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_CHAR, 
							Matchers.equalToIgnoringCase(exception.getMessage()));
		        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_CHAR.getErrorCode(), 
		        			Matchers.equalTo(exception.getErrorCode()));
		        	break;
				case 5:
					MatcherAssert.assertThat(FAILURE_MSG, null != exception);
					MatcherAssert.assertThat(FAILURE_MSG, ErrorMessage.INVALID_NUMBER, 
							Matchers.equalToIgnoringCase(exception.getMessage()));
		        	MatcherAssert.assertThat(FAILURE_MSG, ErrorCode.CREDITCARD_NUMBER_INVALID_NUMBER.getErrorCode(), 
		        			Matchers.equalTo(exception.getErrorCode()));
		        	break;
				default:
					break;
				}
			}
			catch(Throwable t)
			{
				result.append("Case ").append(index+1).append(" Failure!").append("\n");
				
				if (isThrowable)
				{
					throw t;
				}
			}
			finally
			{
				index++;
			}
			
		}
		
		return result;
	}

	private List<String> initInputs() 
	{
		List<String> inputs = new ArrayList<String>();
		inputs.add("1234567812347698");
		inputs.add(" - 12345-6781  2347-698   -");
		inputs.add(" 2- 5-78123  65 ");
		inputs.add("12345678");
		inputs.add("A234567T1234769Z");
		inputs.add("1234567812345678");
		
		return inputs;
	}
	
	@Test
	public void testRunningInMultipleThreads()
	{
		int createMultipleThreadCount = 20;
		s_storeValidators = new Vector<String>();
		List<User> userContainer = new ArrayList<User>();
		
		ThreadPoolExecutor threadPool =  new ThreadPoolExecutor(createMultipleThreadCount, 
				createMultipleThreadCount * 2, 
				60, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(createMultipleThreadCount));
		
		for (int i = 0; i < createMultipleThreadCount; i++)
		{
			User oneUser = new User(i);
			userContainer.add(oneUser);
			threadPool.execute(oneUser);
		}
		threadPool.shutdown();
		while(!threadPool.isTerminated());
		
		// Verify if any case in any user failed
		for (User oneUser : userContainer)
		{
			MatcherAssert.assertThat("User "+oneUser.getName() + ":\n" + oneUser.getResult().toString(), 0 == oneUser.getResult().length());
		}
		
		// Verify only generate one instance of CreditCardValidator
		String validatorObj = s_storeValidators.get(0);
		
		for (int i = 1; i < s_storeValidators.size(); i++)
		{
			MatcherAssert.assertThat("More than one validator have been generated!!", validatorObj.equals(s_storeValidators.get(i)));
		}
	}
	
	class User implements Runnable
	{
		private int m_mark;
		private StringBuilder m_result = null;
		
		public User(int mark)
		{
			m_mark = mark;
		}
		
		@Override
		public void run() 
		{
			m_result = runInOneThread(false);
		}
		
		public int getName()
		{
			return m_mark;
		}
		
		public StringBuilder getResult()
		{
			return m_result;
		}
	}
}
