package org.autoframework.web;

import java.time.Duration;

import org.autoframework.utils.FileUtils;
import org.openqa.selenium.WebDriver;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverInitialization {

	private static WebDriver driverSeleWeb;
	
	private static final String NO_SANDBOX = "--no-sandbox";
	private static final String DISABLE_DEV_SHM = "--disable-dev-shm-usage";
	private static final String CUSTOM_WINDOW_SIZE = "--window-size=1050,600";
	private static final String HEADLESS = "--headless";

	private WebDriverInitialization() {
    	
    }
	
	private WebDriverInitialization(WebDriver driver) {
    	WebDriverInitialization.driverSeleWeb = driver;
    }

	public static void createDriver(Browser browser, boolean isHeadless) {
		switch (browser) {
			case FIREFOX -> setupFirefoxDriver(isHeadless);
			case EDGE -> setupEdgeDriver();
			case OPERA -> setupOperaDriver();
			case CHROME -> setupChromeDriver(isHeadless);
			default -> setupChromeDriver(false);
		}
		setupBrowserTimeouts();
		setMaximizeDriverBrowser();
	}
	
	
	public static WebDriver getDriver () {
        return driverSeleWeb;
    }
    
    private static void setDriver (WebDriver driver) {
    	driverSeleWeb = driver;
    }

    public static void quitDriver () {
        if (driverSeleWeb != null) {
            getDriver().quit();
        }
    }
    
    public static void navigateToURL(String navigateToURLKey) {
    	try {
			WebDriverInitialization.getDriver().navigate().to(FileUtils.readDataFromDriverConfigPropertiesFile(navigateToURLKey));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private static void setupBrowserTimeouts () {
        getDriver().manage ().timeouts ().implicitlyWait(Duration.ofSeconds(TimeOuts.TIMEOUT_SMALL.getTimeOutValue()));
        getDriver().manage ().timeouts ().pageLoadTimeout (Duration.ofSeconds(TimeOuts.TIMEOUT_DOUBLE_LONG.getTimeOutValue()));
        getDriver().manage ().timeouts ().scriptTimeout (Duration.ofSeconds(TimeOuts.TIMEOUT_LONG.getTimeOutValue()));
    }
    
    private static void setMaximizeDriverBrowser() {
    	getDriver().manage().window().maximize();
    }
    
    private static void setupChromeDriver (boolean isHeadless) {
        final var chromePrefs = new HashMap<String, Object> ();
        final var options = new ChromeOptions ();
        
        chromePrefs.put ("safebrowsing.enabled", "true");
        chromePrefs.put ("download.prompt_for_download", "false");
        chromePrefs.put ("download.default_directory", String.valueOf(Paths.get(System.getProperty ("user.home"), "Downloads")));

        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (CUSTOM_WINDOW_SIZE);
        options.addArguments ("--safebrowsing-disable-download-protection");
        //options.addArguments("--disable-notifications");
        
        if (isHeadless) {
            options.addArguments(HEADLESS);
        }
        options.setExperimentalOption ("prefs", chromePrefs);

        setDriver(WebDriverManager.chromedriver().capabilities(options).create());
    }
    
    private static void setupEdgeDriver () {
        setDriver (WebDriverManager.edgedriver().create ());
    }
    
    private static void setupFirefoxDriver (boolean isHeadless) {
        final var options = new FirefoxOptions ();
        options.addArguments (NO_SANDBOX);
        options.addArguments (DISABLE_DEV_SHM);
        options.addArguments (CUSTOM_WINDOW_SIZE);
        //options.addArguments("--disable-notifications");
        if (isHeadless) {
            options.addArguments(HEADLESS);
        }
        setDriver (WebDriverManager.firefoxdriver().capabilities (options).create ());
    }
    
    private static void setupOperaDriver () {
        setDriver (WebDriverManager.operadriver().create());
    }
    
}
