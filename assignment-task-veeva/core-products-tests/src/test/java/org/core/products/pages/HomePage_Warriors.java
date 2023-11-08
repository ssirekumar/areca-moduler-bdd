package org.core.products.pages;

import java.time.Duration;

import org.autoframework.ElementUtils;
import org.autoframework.utils.Utils;
import org.autoframework.web.TimeOuts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_Warriors {

	@FindBy(xpath = "//button[@id='onetrust-accept-btn-handler' and text()='I Accept']")
	private WebElement btn_cookie_policy_iaccept;

	@FindBy(xpath = "//span[text()='Shop']")
	private WebElement btn_shop_menu;

	@FindBy(xpath = "//a[@title=\"Men's\"]")
	private WebElement btn_shop_menu_mens;

	@FindBy(xpath = "//button[text()='Show Purposes']")
	private WebElement btn_show_purposes;

	@FindBy(xpath = "//div[@id='onetrust-pc-sdk']//a[contains(text(), 'see our cookie policy')]")
	private WebElement lnk_see_our_cookie_policy;
	
	@FindBy(xpath = "//div[contains(@class, 'hover:cursor-pointer')]")
	private WebElement link_popup_ticket_access_cross_icon;
	
	@FindBy(xpath = "//nav[@aria-label='header-secondary-menu']//li[@role='menuitem']//preceding-sibling::a[@rel='noreferrer']")
	private WebElement lnk_header_secondary_menu;
	
	@FindBy(xpath = "//nav[@aria-label='header-secondary-menu']//li[@role='menuitem']//a[@title='News & Features']")
	private WebElement lnk_header_news_features;
	
	private WebDriver driver;

	public HomePage_Warriors(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnThecookie_policy_iaccept() {
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), btn_cookie_policy_iaccept);
		if(btn_cookie_policy_iaccept.isDisplayed()) {
			btn_cookie_policy_iaccept.click();
		}
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), link_popup_ticket_access_cross_icon);
		if(link_popup_ticket_access_cross_icon.isDisplayed()) {
			link_popup_ticket_access_cross_icon.click();
		}
		//After page, some of the ads are loading this leads to creating the problems for mouseover issues.
		//So to maintain the page consistency used the static wait.
		Utils.staticSleep(8);
	}
	
	public void clickOnThebtn_shop_menu_and_submenu_mens() {
		if(btn_shop_menu.isDisplayed()) {
			new ElementUtils(driver).mouseOverOnTheElemetAndClick(btn_shop_menu);
			new ElementUtils(driver).mouseOverOnTheElemetAndClick(btn_shop_menu_mens);
		}
	}
	
	public void clickOnThe_show_purposes() {
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), btn_show_purposes);
		if(btn_show_purposes.isDisplayed()) {
			btn_show_purposes.click();
		}
	}
	
	public String getTextOflnk_see_our_cookie_policy() {
		return lnk_see_our_cookie_policy.getText();
	}
	
	public void clickOnThe_lnk_header_secondary_menu() {
		new ElementUtils(driver).mouseOverOnTheElemetAndClick(lnk_header_secondary_menu);
	}
	
	public void clickOnThe_lnk_header_secondary_news_features() {
		new ElementUtils(driver).mouseOverOnTheElemetAndClickAndHold(lnk_header_secondary_menu);
		new ElementUtils(driver).mouseOverOnTheElemetAndClick(lnk_header_news_features);
		
	}
	
}
