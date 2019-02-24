package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base
{
	public WebDriver driver;
	public Properties prop;
	
	public WebDriver intialize_driver(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			
			if(prop.getProperty("headless").equalsIgnoreCase("yes"))
			{
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				driver = new ChromeDriver();
			}
			else
				driver = new ChromeDriver();
		}
		
		return driver;
	}
	
	public Properties intialize_prop()
	{
		prop = new Properties();
		try
		{
			FileInputStream fs = new FileInputStream("src\\test\\resouces\\Properties\\config.properties");
			prop.load(fs);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return prop;
	}
	
	
}
