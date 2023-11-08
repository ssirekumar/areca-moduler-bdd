package org.derived.productsone.pages;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.autoframework.ElementUtils;
import org.autoframework.utils.CSVDataUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage_Bulls {
	
	@FindBy(xpath = "//footer//nav//li[@data-testid='footer-list-item']//a")
	private List<WebElement> lbl_footer_href_urls;
	
	private WebDriver driver;

	public HomePage_Bulls(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<String> getAllTheFooterHyperLinks() {
		List<String> hrefUrls = new ArrayList<>();
		for (WebElement webFooterUrlElement : lbl_footer_href_urls) {
			hrefUrls.add(webFooterUrlElement.getAttribute("href").trim());
		}
	    return hrefUrls;
	}
	
	public List<String> getAllTheDuplicateFooterHyperLinksIfAny() {
		List<String> hrefUrls = getAllTheFooterHyperLinks();
		List<String> duplicates = new ArrayList<>();
		Set<String> set = new HashSet<>();
	    for (String eachStr : hrefUrls) {
	        if (set.contains(eachStr)) {
	            duplicates.add(eachStr);
	        } else {
	            set.add(eachStr);
	        }
	    }
	    return duplicates;
	}
	
	public void scrollToTheFooter() {
		new ElementUtils(driver).scrollToTheElement(lbl_footer_href_urls.get(0));
	}
	
	public void ValidateAndWriteAllFooterUrlDataIntoCSV() {
		CSVDataUtils.writeCSVFileWithUrlAndDuplicates(getAllTheFooterHyperLinks(), getAllTheDuplicateFooterHyperLinksIfAny(), true);
	}
}
