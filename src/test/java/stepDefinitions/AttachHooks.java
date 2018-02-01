package stepDefinitions;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import Pages.BasePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import objectRepo.DocumentPageLocator;
import utils.CommonFunctionLibrary;
import utils.ConfigManager;

public class AttachHooks {

private static WebDriver _driver;
//public CommonFunctionLibrary _functionLibrary;
private Scenario _scenario;
//private Logger _logger;
private FileHandler loggerFile=null;

public static WebDriver GetDriver() {return _driver;} 
@Before
public void setUp(Scenario scenario) throws InterruptedException 
{
	 CommonFunctionLibrary.Log("Execution started inside hook method || Scenario Name:"+ scenario.getName()+".");
	 _scenario=scenario;
	 ConfigManager.loadConfig();
		
	if(IsWebPlatform())
	{
		InitializeCurrentDriver();
		InitializeDriverDefaults();
		//_functionLibrary=new CommonFunctionLibrary(driver);
		return;
	}
	
	if(IsWebApi())
	{
		CommonFunctionLibrary.Log("excution of api started");
		System.out.println("Api Execution started");
		return;
	}
	CommonFunctionLibrary.Log(ConfigManager.getProperty("ExecutionPlatform")+ "execution platform not supported");
	
}
private boolean IsWebPlatform()
{
	return ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("web");
}
private void InitializeCurrentDriver()
{
	
	if(ConfigManager.getProperty("browserName").equalsIgnoreCase("firefox"))
		{
		_driver = new FirefoxDriver();
		return;
		}
	
	if (ConfigManager.getProperty("browserName").equalsIgnoreCase("chrome")) 
	{
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		_driver = new ChromeDriver(options);
		return;
	}
	CommonFunctionLibrary.Log(ConfigManager.getProperty("browserName") + "not supported yet.");
	
}
private void InitializeDriverDefaults() throws InterruptedException
{
	_driver.get(ConfigManager.getProperty("EnvironmentURL"));
	
	_driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	Thread.sleep(1000);
	_driver.manage().window().maximize();
    Thread.sleep(1000);
	System.out.println(_driver.getTitle());
}
private boolean IsWebApi()
{
	return ConfigManager.getProperty("ExecutionPlatform").equalsIgnoreCase("api");
}
//private void Log(String message) 
//{
//	DateFormat dateTimeInstance = SimpleDateFormat.getDateTimeInstance();
//
//	try {
//	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(LoggerFolderPath(), true)));
//	    out.println(dateTimeInstance.format(Calendar.getInstance().getTime())+" :- "+message);
//	    out.close();
//	} catch (IOException e) {
//	    //exception handling left as an exercise for the reader
//	}
//}
/* file writer will perform following
private void EnsureLoggerInitialize()
{      File file = null;
    try {
       file = new File(LoggerFolderPath());
        if (!file.exists()) {
            file.createNewFile();
        }
        
    } catch (IOException e) {
        e.printStackTrace();
    }
}
*/
//private String LoggerFolderPath()
//{
//	return "D:\\WebEditorAutomation\\WebEditor\\src\\main\\resources\\logger.log";
//}
@After
public void tearDown() throws InstantiationException, IllegalAccessException {
 if (IsWebPlatform())
 {
   System.out.println("Web excution stopped");
   if(_scenario.isFailed())
      {
       	_scenario.embed(((TakesScreenshot)_driver).getScreenshotAs(OutputType.BYTES), "image/png");
      }
	_driver.quit();
 }
 if(IsWebApi())
  {
	 System.out.println("Api execution stopped:");
  }
}
}
