package stepDefinitions;

import org.junit.Assert;

import Pages.BasePage;
import Pages.DslocalValidation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import objectRepo.DocumentPageLocator;
import utils.CommonFunctionLibrary;

public class DSlocalValidationStepdef {
	DslocalValidation Obj;
	
	@Given("^web editor page is open$")
	public void web_editor_page_is_open() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Obj=new DslocalValidation(AttachHooks.GetDriver());
	    
	}        
	
	
	@Given("^list item count match on Documentpage with DocumnetMap$")
	public void list_item_count_match_on_Documentpage_with_DocumnetMap() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		 Obj.ExpandOlTag();
		if(Obj.VerifyLiTagCount())
		{
			CommonFunctionLibrary.Log("DocumentPage listitem count Matched with DocumentMap area");
		}
		else
		{
			System.out.println("not matched");
			Assert.fail("DocumentPage listitem count not matched with DocumentMap area");
		}
	    
	}
	@When("^select (.*) in EditorPage$")
	public void select_Why_doesn_t_my_World_Car_change_gears_in_EditorPage(String Value) throws Throwable {
		
	    // Write code here that turns thconvertrgbatohex(colorvalue)
		
		Obj.SetSelectedParagraphText(Value);	
		
		
	    
	}

	@Then("^corresponding tag should highlighted$")
	public void corresponding_tag_should_highlighted() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	   if(Obj.IsHighlightedParaTagColorMatch())
	   {
		   CommonFunctionLibrary.Log("tag matched with selected para");
	   }
	   else
	   {
		   System.out.println("not matched");
		   Assert.fail("tag not matched with selected para");
	   }
	}
	@When("^table is exist on page$")
	public void table_is_exist_on_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Obj.verifyTablePresent())
		{
			CommonFunctionLibrary.Log("table is presnt on the page");
		}
		else
		{
			CommonFunctionLibrary.Log("table is not present on the page");
			Assert.fail("table is not present on the page");
		}
	    
	}

	@Then("^perform add row functionality$")
	public void perform_add_row_functionality() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		if(Obj.verifyTablePresent())
		{
			Obj.rightclick(DocumentPageLocator.firsttdElement, "Insert Row Before");
			if(Obj.matchaddedRowTag(DocumentPageLocator.firsttdElement))
			{
				CommonFunctionLibrary.Log("added row tag matched in docmap");
			}
			else
			{
				Assert.fail("Added row tag does't match");
			}
		}
		else
		{
			CommonFunctionLibrary.Log("table element not found");
			Assert.fail();
		}
		
	    
	}

}
