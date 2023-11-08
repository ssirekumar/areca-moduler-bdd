package org.autoframework.tests;

import org.autoframework.web.Browser;
import org.autoframework.web.WebDriverInitialization;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class BrowserLaunchTest {

	WebDriver driver;

	@Parameters("browserName")
	@BeforeMethod
	public void launchBrowser(String browserName) {
		WebDriverInitialization.createDriver(Browser.valueOf(browserName.toUpperCase()), false);
		WebDriverInitialization.navigateToURL("web.url.core");
		driver = WebDriverInitialization.getDriver();
	}

	@Parameters("browserName")
	@Test
	public void launchBrowserValidation(String browserName) {
		String browserTitle = driver.getTitle();
		Assert.assertEquals(driver.getTitle().isEmpty(), false, String.format("Desired %1$s browser Is launched and it's title %2$s, So Driver is working fine", browserName, browserTitle));
	}

	@AfterMethod
	public void quitBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
