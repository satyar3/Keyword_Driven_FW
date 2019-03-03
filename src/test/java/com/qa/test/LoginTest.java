package com.qa.test;

import org.testng.annotations.Test;

import com.qa.engine.KeywordEngine;

public class LoginTest
{
	public KeywordEngine  keywordEngine;
	
	@Test(enabled=false)
	public void loginTestScenario()
	{
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("login");
	}
	
	@Test
	public void signUpTestScenario()
	{
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("signup");
	}
}