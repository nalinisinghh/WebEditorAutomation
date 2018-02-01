package Apis;

import com.jayway.restassured.response.Response;
import static com.jayway.restassured.RestAssured.given;
import java.util.List;
import utils.ConfigManager;

public class WebApiTest{
	
	public Response _response;
	private static List<String> cadName;
	private static String URI;
	private static int _documentCountInReponse;
		
	public WebApiTest() 
	{
		ConfigManager.loadConfig();
	}
	
	private String ApiAuthUserName(){return ConfigManager.getProperty("windchilluname");}
	private String ApiAuthPassword(){return ConfigManager.getProperty("windchillpass");}
	public int ResponseCode(){return _response.getStatusCode();}
	public Response GetResponse(){return _response ;}
	public boolean IsResponseOk(){ return ResponseCode() == 200;}
	
	public void Initialize(String webApiUrl)
	
	{   		
		URI=webApiUrl;
		_response= GetResponseFromURI(webApiUrl);
		if(IsResponseOk())
		{
			LogToConsole("response output:"+_response.getBody().asString());
			_documentCountInReponse=_response.jsonPath().getList("value").size();
			cadName=_response.jsonPath().getList("value.CADName");
			LogToConsole("list of cadname:"+cadName);
			return;
		}
		
		LogToConsole("invalid response:"+ResponseCode() );
	}
	
	
	private Response GetResponseFromURI(String uri)
	{
		return given().contentType("application/json").auth().basic(ApiAuthUserName(),ApiAuthPassword()).get(uri);
	}
	
	private void LogToConsole(String message )
	{
		System.out.println(message);
	}
    public int responsecode()
    {
    	return _response.getStatusCode();
    }
    
    public boolean IsAllFileNameUrlValid()
    {
    	for(int index=0;index<_documentCountInReponse;index++)
    	{
    	_response= GetResponseFromURI(GetFileNameUri(index));
    	if(!IsResponseOk())
    	{
    		return false;
    	}
    	}
    	return true;
    }
    private String GetFileNameUri(int fileNameSequence)
    {
    	return URI+"("+"'"+cadName.get(fileNameSequence)+"'"+")";
    }
    public boolean verifyCadNamewithResponse()
    {
    	ConfigManager.loadConfig();
    	boolean flag=false;
    	for(int i=0;i<_documentCountInReponse;i++)
    	{
    	
    	String input=URI+"("+"'"+cadName.get(i)+"'"+")";
    	_response=given().contentType("application/json").auth().basic(ConfigManager.getProperty("windchilluname"),ConfigManager.getProperty("windchillpass")).get(input);
        String filename=_response.jsonPath().getString("CADName").toString();
    	if(filename.equals(cadName.get(i)))
    	{
    		
    		flag=true;
    	}
    	else
    	{
    		flag=false;
    	}
    	}
    	return flag;
    }
    public boolean getfilecontentReposne()
    {   ConfigManager.loadConfig();
    	boolean f=false;
    	for(int i=0;i<_documentCountInReponse;i++)
    	{
    		String inputstr=URI+"("+"'"+cadName.get(i)+"'"+")"+"/PrimaryContent";
    		_response=given().contentType("application/json").auth().basic(ConfigManager.getProperty("windchilluname"),ConfigManager.getProperty("windchillpass")).get(inputstr);
    		if(_response.jsonPath().getString("FormatIcon.Tooltip").contains("XML"))
    		{
    		  Response respo;
    		  String input=inputstr+"/$value";
    		  
    		  
    		}
    		if(_response.getStatusCode()==200)
    		{
    			f=true;
    		}
    		else
    		{
    			f=false;
    		}
    			
    	}
		return f;
    	
    }
}

