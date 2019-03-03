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
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
	WebDriverWait wait;
	
	public void startExecution(String sheetName)
	{
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
			String locatorName = sheet.getRow(i+1).getCell(k+1).toString().trim();
			String lcoatorVal = sheet.getRow(i+1).getCell(k+2).toString().trim();
				
			
			String action = sheet.getRow(i+1).getCell(k+3).toString().trim();
			String value = sheet.getRow(i+1).getCell(k+4).toString().trim();
			
			
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
				
				wait = new WebDriverWait(driver,20);
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
				switch (locatorName) {
				case "id":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.id(lcoatorVal)));
					element = driver.findElement(By.id(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}

					locatorName = null;
					break;

				case "name":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.name(lcoatorVal)));
					element = driver.findElement(By.name(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					
					break;

				case "className":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.className(lcoatorVal)));
					element = driver.findElement(By.className(lcoatorVal));
					
					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} 
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
							System.out.println("Element displayed");
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}

					locatorName = null;

					break;

				case "xpath":
										
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.xpath(lcoatorVal)));
					element = driver.findElement(By.xpath(lcoatorVal));
					
					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					} 
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
							System.out.println("Element Displayed");
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					
					break;

				case "linkText":

					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.linkText(lcoatorVal)));
					element = driver.findElement(By.linkText(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					break;

				case "partialLinkText":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(lcoatorVal)));
					element = driver.findElement(By.partialLinkText(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					
					break;

				case "cssSelector":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(lcoatorVal)));
					element = driver.findElement(By.cssSelector(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					
					break;

				case "tagName":
					
					wait.ignoring(NoSuchElementException.class).until(ExpectedConditions.presenceOfElementLocated(By.tagName(lcoatorVal)));
					element = driver.findElement(By.tagName(lcoatorVal));

					if (action.equalsIgnoreCase("sendKeys")) {
						element.clear();
						element.sendKeys(value);
					} else if (action.equalsIgnoreCase("click")) {
						element.click();
					}
					else if(action.equalsIgnoreCase("isDisplayed"))
					{
						try
						{	
							boolean elementDisplayed = element.isDisplayed();
						}
						catch(NoSuchElementException e)
						{
							System.out.println("Element not displayed");
						}
					}
					else if(action.equalsIgnoreCase("getText"))
					{
						String elementText = element.getText();
						System.out.println("Text from element is : "+elementText);
					}
					
					locatorName = null;
					
					break;

				default:
					break;
				}
			}
			catch(NullPointerException ex)
			{
				
			}
		}
	}
}