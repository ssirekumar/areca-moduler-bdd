package org.core.products.defi;

import org.autoframework.ElementUtils;
import org.autoframework.web.Browser;
import org.autoframework.web.WebDriverInitialization;
import org.core.products.pages.HomePage_Warriors;
import org.core.products.pages.NewsAndFeatures_Warriors;
import org.core.products.pages.ShopMens_Warriors;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCase1_CP1_StepDef {
	
	private WebDriver driver;
	
	private ShopMens_Warriors shopMensWarriors;
	private HomePage_Warriors homePageWarriors;
	private NewsAndFeatures_Warriors newsAndFeatures;
	
	@Given("^Initialize all the page objects$")
	public void launch_the_browser() {
		driver = WebDriverInitialization.getDriver();
		
		homePageWarriors = new HomePage_Warriors(driver);
		shopMensWarriors = new ShopMens_Warriors(driver);
		newsAndFeatures =  new NewsAndFeatures_Warriors(driver);
	}

	@When("^if any cookie policy popup comes up$")
	public void if_any_cookie_policy_accept() {
		homePageWarriors.clickOnThecookie_policy_iaccept();
	}

	@And("^Go to Shop Menu Mens$")
	public void go_to_shop_menu_mens() {
		homePageWarriors.clickOnThebtn_shop_menu_and_submenu_mens();
		new ElementUtils(driver).switchToLatestWindow();
	}
	
	@And("^Go to Men Menu Jackets$")
	public void go_to_men_menu_jackets() {
		shopMensWarriors.clickOnTheBtn_shop_men_menu_and_submenu_jackets();
	}

	@Then("^Get all the price and top seller comments$")
	public void validate_lnk_see_our_cookie_policy() {
		shopMensWarriors.getThePriceAndSellerMessageOfTheProductCardOnPage();
	}
	
	@And("^Go to Secondary menu and tap on News & Features$")
	public void go_to_secondary_menu_news_features() {
		homePageWarriors.clickOnThe_lnk_header_secondary_news_features();
	}
	
	@Then("Validate total number of {int} videos feeds and those are present in the page with {string}")
	public void vlidateFindingAllVideosWhichIs(Integer conditionNumber, String condition) {
		int countOfConditionVideos = newsAndFeatures.videosWhichIs(condition, conditionNumber);
		Reporter.log("Count total number of Videos Feeds and count the videos feeds those are"
				+ "present in the page is: " + countOfConditionVideos);
		Reporter.log("With this conditon: " + condition + conditionNumber);
		System.out.println("Count total number of Videos Feeds and count the videos feeds those are"
				+ "present in the page is: " + countOfConditionVideos);
		System.out.println("With this given conditon: " + condition + conditionNumber);
	}
	
	
}
