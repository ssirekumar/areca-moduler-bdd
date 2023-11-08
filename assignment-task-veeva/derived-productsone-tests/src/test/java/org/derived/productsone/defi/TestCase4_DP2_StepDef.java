package org.derived.productsone.defi;

import org.autoframework.web.WebDriverInitialization;
import org.derived.productsone.pages.HomePage_Bulls;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;

public class TestCase4_DP2_StepDef {
	
	private WebDriver driver;
	private HomePage_Bulls homePageBulls;

	@Given("^Initialize all the page objects in the bulls$")
	public void initializeAll_the_page_objects_in_the_bulls() {
		driver = WebDriverInitialization.getDriver();
		
		homePageBulls = new HomePage_Bulls(driver);
	}
	
	@When("^Page scroll to footer$")
	public void scrollTo_the_footer_bulls() {
		homePageBulls.scrollToTheFooter();
	}
	
	@Then("^Validate is any footer hyper links have duplicates$")
	public void validateIs_any_footer_hyper_links_have_duplicates() {
		homePageBulls.ValidateAndWriteAllFooterUrlDataIntoCSV();
	}
}
