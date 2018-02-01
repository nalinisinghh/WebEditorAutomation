package stepDefinitions.Api;

import org.junit.Assert;


import Apis.WebApiTest;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DynamicDocOdataStepDef {
	WebApiTest webApiTest=new WebApiTest();
	@Given("^when we hit (.*)$")
	public void when_we_hit_http_vagrant_ptcnet_ptc_com_Windchill_servlet_odata_DynamicDocMgmt_DynamicDocuments(String input) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		webApiTest.Initialize(input);
		
	  
	}

	@Then("^get reponse from server$")
	public void get_reponse_from_server() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(webApiTest.IsResponseOk())
		{
			System.out.println("status code:"+webApiTest.ResponseCode());
			System.out.println("URI request succeeded");
		}
		else
		{
			System.out.println("status code:"+webApiTest.ResponseCode()+"response not succedded");
			Assert.fail("not succedded");
		}
	   
	}
	@When("^i hit uri including filename$")
	public void i_hit_uri_including_filename() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(webApiTest.IsAllFileNameUrlValid())
		{
			System.out.println("hit uri successfully");
		}
		else
		{
			System.out.println("not hit");
			Assert.fail("Not all uri file url hit");
		}
	}

	@Then("^match with filename get in response$")
	public void match_with_filename_get_in_response() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(webApiTest.verifyCadNamewithResponse())
		{
			System.out.println("filename matched with reponsevalue");
		}
		else
		{
			System.out.println("filename not matched with reponse");
			Assert.fail("not succedded");
		}
	    
	}

@When("^i hit uri with filename for getting content$")
public void i_hit_uri_with_filename_for_getting_content() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
   if(webApiTest.getfilecontentReposne())
   {
	   System.out.println("reponse code 200 matched");
   }
   else
   {
	   Assert.fail("Not hit uri correctly");
   }
}

@Then("^verify reponse code$")
public void verify_reponse_code() throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    
}

}
