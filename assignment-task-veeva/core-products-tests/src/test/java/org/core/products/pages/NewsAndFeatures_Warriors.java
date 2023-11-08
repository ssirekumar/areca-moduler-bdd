package org.core.products.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsAndFeatures_Warriors {

	@FindBy(xpath = "(//div[contains(@class, 'ColumnsComponents')])[2]//time//span")
	private List<WebElement> lbl_time_which_posted;

	private WebDriver driver;

	public NewsAndFeatures_Warriors(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int videosWhichIs(String condition, int conditionNumber) {
		List<Integer> daysHistory = new ArrayList<>();
		List<Integer> result = new ArrayList<>();

		Pattern p = Pattern.compile("\\d+");
		for (WebElement eleVideoPosted : lbl_time_which_posted) {
			if(eleVideoPosted.getText().trim().contains("h")) {
				continue;
			}
			Matcher m = p.matcher(eleVideoPosted.getText().trim());
			while (m.find()) {
				daysHistory.add(Integer.valueOf(m.group().trim()));
			}
		}

		for (Integer val : daysHistory) {
			if (condition.equals(">")) {
				if (val.intValue() > conditionNumber) {
					result.add(val);
				}
			} else if (condition.equals("<")) {
				if (val.intValue() < conditionNumber) {
					result.add(val);
				}
			} else if (condition.equals("<=")) {
				if (val.intValue() <= conditionNumber) {
					result.add(val);
				}
			} else if (condition.equals(">=")) {
				if (val.intValue() >= conditionNumber) {
					result.add(val);
				}
			}
		}
		return result.size();
	}
}
