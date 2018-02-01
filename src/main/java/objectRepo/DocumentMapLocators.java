package objectRepo;

import org.openqa.selenium.By;

public class DocumentMapLocators 
{
  public static By Header=By.xpath("//span[text()='Document Map']");
  public static By Toggle=By.xpath(".//*[@id='docMapToggle']");
  public static By lilist=By.xpath("//span/span[text()='li']");
  public static By olid=By.xpath(".//span/span[text()='ol']");
  public static String elmtPos="//span[@id=";
  public static By highlighted=By.xpath("//li//.[contains(@class,'ui-state-highlight')]");
  public static String highlight=".//*[contains(@class,'ui-state-highlight')]//*[text()=";
  
}
