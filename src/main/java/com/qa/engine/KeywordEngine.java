package com.qa.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.base.Base;

public class KeywordEngine
{
	public WebDriver driver;
	public Properties prop;
	public WebElement element;
	
	public static Workbook book;
	public static Sheet sheet;
	
	public final String SCENARIO_SHEET_PATH = "src\\main\\java\\com\\qa\\scenarios\\HubspotScenarios.xlsx";
	
	public Base base;
	
	public void startExecution(String sheetName)
	{
		String locatorName = null;
		String lcoatorVal = null;
		
		FileInputStream fs = null;
		
		try
		{
			fs = new FileInputStream(SCENARIO_SHEET_PATH);
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			book = WorkbookFactory.create(fs);
		}
		catch (InvalidFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet = book.getSheet(sheetName);
		
		int k = 0;
		//k = 0 points to test Step i.e. 1st cell value of the row
		
		for(int i = 0; i<sheet.getLastRowNum(); i++)
		{
			String locatorColValue = sheet.getRow(i+1).getCell(k+1).toString().trim();
			
			if(!locatorColValue.equalsIgnoreCase("na"))
			{
				locatorName = locatorColValue.split("=")[0].trim();
				lcoatorVal = locatorColValue.split("=")[1].trim();
				
			}
			
			String action = sheet.getRow(i+1).getCell(k+2).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+3).toString().trim();
			
			
			//Switch to respective keywords
			switch (action)
			{
			case "open browser":
				base = new Base();
				prop = base.intialize_prop();
				if(value.isEmpty() || value.equalsIgnoreCase("na"))
				{
					driver = base.intialize_driver(prop.getProperty("browser"));
				}
				else
					driver = base.intialize_driver(value);
				
				
				break;
			
			case "enter url" : 
				if(value.isEmpty() || value.equalsIgnoreCase("na"))
				{
					driver.get(prop.getProperty("url"));
				}
				else
					driver.get(value);
				
				break;
				
			case "quit":
				driver.quit();
				
				break;
				
			default:
				break;
			}
			
			
			try
			{
			switch (locatorName)
			{
			case "id":

				element = driver.findElement(By.id(lcoatorVal));
					
				if(action.equalsIgnoreCase("sendKeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				
				locatorName = null;
				break;

			case "name":

				break;

			case "className":

				break;

			case "xpath":

				break;

			case "linkText":
				
				WebElement element = driver.findElement(By.linkText(lcoatorVal));
				
				if(action.equalsIgnoreCase("sendKeys"))
				{
					element.clear();
					element.sendKeys(value);
				}
				else if(action.equalsIgnoreCase("click"))
				{
					element.click();
				}
				
				locatorName = null;
				break;

			case "partialLinkText":

				break;

			case "cssValue":

				break;

			case "tagName":

				break;

			default:
				break;
			}
			}
			catch(Exception ex)
			{
				
			}
		}
	}
}
