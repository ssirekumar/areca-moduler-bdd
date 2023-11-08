package org.core.products.runner;

import org.autoframework.utils.Utils;
import org.autoframework.web.Browser;
import org.autoframework.web.WebDriverInitialization;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
features = {
	"src/test/resources/FeaturesFiles"
},
glue = {
	"helpers","org.core.products.defi"
},
plugin = {
    "pretty", "html:target/cucumber-reports/cucumber-pretty.html",
    "json:target/cucumber-reports/CucumberTestReport.json",
    "junit:target/cucumber-reports/Cucumber.xml",
    "rerun:target/cucumber-reports/rerun.txt"          
}
)
public class TestNgRunner extends AbstractTestNGCucumberTests{
	
	WebDriver driver;
	
	@Parameters("browserName")
	@BeforeMethod
	public void launchTheDesiredBrowser() {
		WebDriverInitialization.createDriver(Browser.valueOf("chrome".toUpperCase()), false);
		WebDriverInitialization.navigateToURL("web.url.core");
		driver = WebDriverInitialization.getDriver();
	}
	
	@AfterMethod
	public void quitBrowserDriver() {
		if (driver != null) {
			driver.quit();
			Utils.staticSleep(3);
		}
	}
}
