package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import objectRepo.DocumentMapLocators;
import objectRepo.DocumentPageLocator;

public class DslocalValidation extends BasePage{
	private String _selectedParagraphText;
	String tagValue;
    
	public DslocalValidation(WebDriver driver) {
		super(driver);
		VerifyPageOpen();
		
	}
	
	private void VerifyPageOpen()
	{
		spinner(DocumentPageLocator.spinnerElmt);
		findElement(DocumentMapLocators.Toggle, 500).click();
		if(!IsHeaderDisplayed()) throw new NoSuchElementException("header is not present");
		
		System.out.println("Page display successfully");
	}
	private boolean IsHeaderDisplayed()
	{
		return findElement(DocumentMapLocators.Header,1000).isDisplayed();
		
	}
	public void ExpandOlTag()
	{   int sequence=1;
		
		
		while(!TagIsOl(sequence))
		{
			sequence++;
			
		}	
	}
	private boolean TagIsOl(int sequence) 
	{
		findElement(xpathReturn(DocumentMapLocators.elmtPos, sequence), 100).click();
		String text=findElement(xpathReturn(DocumentMapLocators.elmtPos, sequence), 100).getText();
		return text.equals("ol");
	}
	
	public boolean VerifyLiTagCount()
	{ 
	   if(IsLiTagAvailable() && IsLiTagMatchesForPageMapAndDocumentPage())
	   {
		  
			  System.out.println("li tag size matched");
			  return true;
		  
	   }
	   return false;
   }
	private boolean IsLiTagAvailable()
	{
		if(FindElementsSize(DocumentMapLocators.lilist,100)<=0)
		{
			System.out.println("li element is not avaiale in DOCUMENT MAP");
			return false;
		}
		if(FindElementsSize(DocumentPageLocator.liItems,100)<=0)
		{
			System.out.println("li element is not avaiale in Page MAP");
			return false;
		}
		return true;
	}
	private boolean IsLiTagMatchesForPageMapAndDocumentPage()
	{
		return (FindElementsSize(DocumentMapLocators.lilist,100)==FindElementsSize(DocumentPageLocator.liItems,100));
	}
	public String ReturnParaGraphtagColor()
	{    
		return ReturnHexTagColor(xpathReturn(DocumentMapLocators.highlight, LocateAndClickParaGraphTag(_selectedParagraphText)));
//		String tagvalue=findElement(xpathReturn(DocumentPageLocator.paragraph, value), 30).getAttribute("_wex:tag");
//		
//		findElement(xpathReturn(DocumentPageLocator.paragraph, value), 30).click();
		
//		if(findElement(xpathReturn(DocumentMapLocators.highlight, GetParaGraphTag(GetParaString())), 40).isDisplayed())
//		{
//			
//			String color=findElement(xpathReturn(DocumentMapLocators.highlight, GetParaGraphTag(GetParaString())), 40).getCssValue("color");
//			//System.out.println(driver.findElement(By.xpath(DocumentMapLocators.highlight+"'"+tagValue+"'"+"]")).getCssValue("color"));		
//			String tagColor=convertrgbatohex(color);
//			return tagColor;
//		}
//		else
//		{
//		return null;
//		}
		
		}
	 public void SetSelectedParagraphText(String selectedParagraphText)
	 {
		 _selectedParagraphText=selectedParagraphText;
	 }
	
	 public String LocateAndClickParaGraphTag(String paragraphText)
	 {
		 String tagValue=findElement(xpathReturn(DocumentPageLocator.paragraph, paragraphText), 30).getAttribute("_wex:tag");
		 findElement(xpathReturn(DocumentPageLocator.paragraph, paragraphText), 30).click();
		 return tagValue;
		 
	 }
	public boolean IsHighlightedParaTagColorMatch()
	{
		if(ReturnParaGraphtagColor().equals("#ffffff"))
		{
			return true;
		}
		else
		{

			return false;
		}
		
	}
	public boolean verifyTablePresent()
	{
		if(FindElementsSize(DocumentPageLocator.table, 50)==1)
		{
			
			return true;
		}
		else
		{
			return false;
		}
	}
	public boolean matchaddedRowTag(By value)
	{
		boolean flag=false;
		String tagname=findElement(value, 500).getAttribute("_wex:tag");
		if(findElement(xpathReturn(DocumentMapLocators.highlight, tagname), 40).isDisplayed())
		{
			String color=findElement(xpathReturn(DocumentMapLocators.highlight, tagname), 40).getCssValue("color");
			String tagColor=convertrgbatohex(color);
			if(tagColor.equals("#ffffff"))
			{
				flag=true;
			}
			else
			{
				flag=false;
			}
		}
		else
		{
			flag=false;
		}
		return flag;
		
	}
	
}
