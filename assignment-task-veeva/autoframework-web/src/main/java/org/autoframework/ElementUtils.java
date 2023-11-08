package org.autoframework;

import java.time.Duration;

import org.autoframework.utils.Utils;
import org.autoframework.web.TimeOuts;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtils {

	private WebDriver driver;

	public ElementUtils(WebDriver driver) {
		this.driver = driver;
	}

	public void mouseOverOnTheElemetAndClick(WebElement element) {
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
		Utils.staticSleep(3);
	}
	
	public void mouseOverOnTheElemetAndClickAndHold(WebElement element) {
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).clickAndHold().build().perform();
		Utils.staticSleep(3);
	}
	
	public void mouseOverOnTheElemetParentAndChildClick(WebElement parentElement, WebElement childElement) {
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), parentElement);
		new ElementUtils(driver).waitUntilvisibilityOfElement(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()), childElement);
		Actions actions = new Actions(driver);
		actions.moveToElement(parentElement);
		actions.moveToElement(childElement);
		actions.click().build().perform();
	}

	public void switchToFristFrame(int indexOfTheFrame) {
		driver.switchTo().frame(indexOfTheFrame);
	}

	public void waitUntilvisibilityOfElement(Duration waitTimeOut, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, waitTimeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void switchToLatestWindow() {
		String originalWindow = driver.getWindowHandle();
		if (driver.getWindowHandles().size() > 1) {
			for (String windowHandle : driver.getWindowHandles()) {
				if (!originalWindow.contentEquals(windowHandle)) {
					driver.switchTo().window(windowHandle);
					break;
				}
			}
		}
	}
	
	public void waitUntilThePageReturnCompleteAsItsStatus() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()));
		wait.until(webDriver -> ((JavascriptExecutor)webDriver).executeScript("return document.readyState").equals("complete"));
	}
	
	public void scrollToTheElement(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Utils.staticSleep(5);
	}
}
