package org.core.products.pages;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

import org.autoframework.ElementUtils;
import org.autoframework.utils.FileUtils;
import org.autoframework.utils.Utils;
import org.autoframework.web.TimeOuts;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopMens_Warriors {

	@FindBy(xpath = "//div[contains(@class, 'product-card')]//a//img[@alt]")
	private List<WebElement> img_top_seller_message;

	@FindBy(xpath = "//div[contains(@class, 'product-card')]//span[@class='money-value']//span[@class='sr-only']")
	private List<WebElement> lbl_product_card_price;

	@FindBy(xpath = "//div[contains(@class, 'product-card row')]")
	private List<WebElement> lbl_total_products;

	@FindBy(xpath = "//nav[contains(@aria-label, 'Navigation')]//a[text()='men']")
	private WebElement nav_men_link;

	@FindBy(xpath = "(//nav[contains(@aria-label, 'Navigation')]//div[text()='Jackets']//parent::a)[1]")
	private WebElement nav_jackets_link;

	@FindBy(xpath = "//div[contains(@class, 'product-grid-top-area')]//div[@data-talos='pageCount']")
	private WebElement lbl_page_count;

	@FindBy(xpath = "//div[@class='pagination-component']//li[@class='show-for-large']")
	private List<WebElement> lbl_list_of_pagination;

	@FindBy(xpath = "//div[@class='pagination-component']//a[@aria-label='next page']//i[@aria-disabled='false']")
	private WebElement lbl_pagination_next_page;

	private WebDriver driver;

	public ShopMens_Warriors(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnTheBtn_shop_men_menu_and_submenu_jackets() {
		if (nav_men_link.isDisplayed()) {
			new ElementUtils(driver).mouseOverOnTheElemetAndClickAndHold(nav_men_link);
			new ElementUtils(driver).mouseOverOnTheElemetAndClick(nav_jackets_link);
		}
	}

	public int totalNumberOfProductsOnThePage() {
		return lbl_total_products.size();
	}

	public String getThePriceOfTheProductCardOnPage(int productCount) {
		return lbl_product_card_price.get(productCount).getText();
	}

	public StringBuilder getAllThePricesOfTheProductCardOnPage() {
		StringBuilder strBuildr = new StringBuilder();
		for (WebElement eachProduct : lbl_product_card_price) {
			strBuildr.append(eachProduct.getText().trim() + "\n");
		}
		return strBuildr;
	}

	public String getTheSellerMessageOfTheProductCardOnPage(int productCount) {
		return img_top_seller_message.get(productCount).getAttribute("alt");
	}

	public StringBuilder getAllTheSellerMessageOfTheProductCardOnPage() {
		StringBuilder strBuildr = new StringBuilder();
		for (WebElement eachProduct : img_top_seller_message) {
			strBuildr.append(eachProduct.getAttribute("alt") + "\n");
		}
		return strBuildr;
	}

	public StringBuilder getThePriceAndSellerMessageOfTheProductCardOnPage() {
		int productsCount = 0; 
		int increment = 0;
		StringBuilder strBuildr;
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), lbl_pagination_next_page);
		do {
			strBuildr = new StringBuilder();
			if(!lbl_pagination_next_page.isDisplayed()) {
				break;
			}else if(increment >= 1) {
				lbl_pagination_next_page.click();
			}
			productsCount = totalNumberOfProductsOnThePage();
			for (int j = 0; j < productsCount; j++) {
				strBuildr.append("---------" + "Product-" + j + "---------" + "\n");
				strBuildr.append(getThePriceOfTheProductCardOnPage(j) + "\n");
				strBuildr.append(getTheSellerMessageOfTheProductCardOnPage(j) + "\n");
				strBuildr.append("----------------------------------------" + "\n");
			}
			FileUtils.writeDataIntoTextFile(strBuildr, false);
			increment++;
		} while (lbl_pagination_next_page.isDisplayed()); 
		return strBuildr;
	}

	public Integer getTheListOfPagenationCount() {
		new ElementUtils(driver).waitUntilvisibilityOfElement(
				Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), lbl_page_count);
		String input = lbl_page_count.getText().trim();
		System.out.println("Total Number of pages after getText()" + input);
		Integer number = null;
		String[] parts = input.split(" of ");
		if (parts.length == 2) {
			number = Integer.parseInt(parts[1]);
			System.out.println("Total Number of pages" + number.intValue());
		}
		return number;
	}

}
