package Pages;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.CommonFunctionLibrary;

public abstract class BasePage 
{
	protected WebDriver webDriver;
	protected CommonFunctionLibrary functionLibrary;
	public BasePage(WebDriver driver) 
	{
		webDriver=driver;
		//functionLibrary=new CommonFunctionLibrary(webDriver);
	}
	public boolean isElementPresent(By locator, int timeoutInSeconds) 
	{
		try 
		{
			WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeoutInSeconds);
			WebElement elem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return (elem != null);

		} 
		catch (Exception e) { return false;	}
	}
	public  void spinner(By elemt)
	{  int i=0;
		while(findElement(elemt, 30).isDisplayed())
		{
			i++;
		}
		
	}
	public WebElement findElement(By locator, int timeoutSeconds) 
	{
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, timeoutSeconds);
		return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
		
	}
	public void sendKeys(By locator, String str) 
	{
		findElement(locator, 10).clear();
		findElement(locator, 10).sendKeys(str);
	}
  
	public int FindElementsSize(By locator,int timeoutSeconds)
	{
		WebDriverWait webDriverWait=new WebDriverWait(webDriver, timeoutSeconds);
		
		if(IsElementAvailiable(webDriverWait, locator)) { return webDriver.findElements(locator).size();}
		return 0;
		
		 
	}
	private boolean IsElementAvailiable(WebDriverWait webDriverWait, By elementName)
	{
	  return (webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementName))!= null);
	}
	protected String convertrgbatohex(String colorvalue)
	{
		colorvalue=colorvalue.replace("rgba(", "").replace(")", "");
		String[] rgbValues = colorvalue.split(",");
		int redHex = Integer.parseInt(rgbValues[0].trim());
		int greenHex = Integer.parseInt(rgbValues[1].trim());
		int blueHex = Integer.parseInt(rgbValues[2].trim());
		return "#" + Integer.toHexString(redHex) + Integer.toHexString(greenHex) + Integer.toHexString(blueHex);
	}
    public void rightclick(By elemt,String value)
    {
    	Actions action=new Actions(webDriver);
    	action.moveToElement(findElement(elemt, 100));
    	action.contextClick(findElement(elemt, 100)).build().perform();
    	WebElement webElement = webDriver.findElement(By.linkText(value)); /*This will select menu after right click */
    	webElement.click();
    	
    }
    public String convertToUTF(String value )
    {
    	byte ptext[]=value.getBytes();
    	String output = null;
		try {
			output = new String(ptext,"UTF8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
    	
    }
    public By xpathReturn(String input1,String input2 )
    {
    	By value=By.xpath(input1+"'"+input2+"'"+"]");
    	return value;
    }
    public By xpathReturn(String input1,int input2)
   {
	   return xpathReturn(input1, String.valueOf(input2));
   }
   
   public String ReturnHexTagColor(By input)
   {
	   if(findElement(input, 400).isDisplayed())
	   {
		   String elementColor=findElement(input, 400).getCssValue("color");
		   return convertrgbatohex(elementColor);
	   }
	   return null;
   }
}
