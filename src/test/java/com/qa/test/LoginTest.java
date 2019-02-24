package com.qa.test;

import org.testng.annotations.Test;

import com.qa.engine.KeywordEngine;

public class LoginTest
{
	public KeywordEngine  keywordEngine;
	
	@Test
	public void loginTestScenario()
	{
		keywordEngine = new KeywordEngine();
		keywordEngine.startExecution("login");
	}
}
