package com.tw.cisco.b2b.pages;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 18/01/16.
 */
public abstract class BasePage<P extends BasePage>{

    public static String gridHubUrl = null;

    /*  static {
          gridHubUrl = CommonHelper.getGridHubUrl();
      }
    */
    protected WebDriver driver;
    protected WebDriverWait waitTime;
    private static final String PAGE_TITLE="";
    protected static final long ELEMENT_WAIT=10;
    protected static final long IMPLICIT_WAIT=20;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;
    protected static final int SPINNER_TO_APPEAR_TIMEOUT=5;
    protected static final int SPINNER_TO_DISAPPEAR_TIMEOUT=30;
    protected static final int SPINNER_POLLING_RATE=50;

    public enum Driver {
        FIREFOX,
        CHROME,
        IE,
        HTMLUNIT,

    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * Method to wait for page to get loaded
     * @param expectedCondition
     */
    protected void waitForPageToLoad(ExpectedCondition expectedCondition) {
        Wait wait = new FluentWait(driver)
                .withTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(POLLING_RATE, TimeUnit.SECONDS);
        wait.until(getPageLoadCondition());
    }

    /**
     *
     * Method to get the page title.
     *
     * @return Title for the page loaded
     */
    public  String getPageTitle() {
        return PAGE_TITLE;
    }

    /**
     *
     * Method to get the condition for checking the page load
     *
     * @return ExpectedCondition for the element to be verified.
     */
    protected abstract ExpectedCondition getPageLoadCondition();

    /**
     *
     * Method for child page instantiation
     *
     */
    protected abstract void instantiatePage(P page);

    /**
     * Method for waiting for element to be visible
     *
     */
    protected void waitForElement(ExpectedCondition expectedCondition) {
        waitTime= new WebDriverWait(driver,ELEMENT_WAIT);
        waitTime.until(expectedCondition);
    }

    /**
     *
     * @param driverType
     * @param browserVersion
     * @param platform
     * @return
     */
    public static WebDriver geDriver(Driver driverType, String browserVersion, String platform){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (null==gridHubUrl) {
            //running on local
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }
        else {
            //running on Selenium Grid
            if(platform.equals("XP")){
                desiredCapabilities.setPlatform(Platform.XP);
            }
            else if(platform.equals("WIN8")){
                desiredCapabilities.setPlatform(Platform.WIN8);
            }
            else if(platform.equals("VISTA")){
                desiredCapabilities.setPlatform(Platform.VISTA);
            }
            else if(platform.equals("ANDROID")){
                desiredCapabilities.setPlatform(Platform.ANDROID);
            }
            desiredCapabilities.setVersion(browserVersion);
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }

        switch (driverType) {

            case FIREFOX:
                // Disable Native Events on Windows for Firefox Driver.
                try {
                    FirefoxProfile firefoxProfile = new FirefoxProfile();
                    firefoxProfile.setEnableNativeEvents(true);
                    desiredCapabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);

                    if (null==gridHubUrl) {
                        //running on local
                        return new FirefoxDriver(desiredCapabilities);
                    }
                    else{
                        //running on Selenium Grid
                        desiredCapabilities.setBrowserName(DesiredCapabilities.firefox().getBrowserName());
                        return new RemoteWebDriver(new URL(gridHubUrl), desiredCapabilities);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }
            case CHROME:
                if (null==System.getProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY)){
                    System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");

                }
                try {
                    if (null==gridHubUrl){
                        //running on local
                        return new ChromeDriver(desiredCapabilities);
                    }
                    else{
                        //running on Selenium Grid
                        desiredCapabilities.setBrowserName(DesiredCapabilities.chrome().getBrowserName());
                        return new RemoteWebDriver(new URL(gridHubUrl), desiredCapabilities);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }
            case IE:
                try {
                    desiredCapabilities.setCapability(InternetExplorerDriver.SILENT, true);
                    //desiredCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                    desiredCapabilities.setCapability("EnableNativeEvents", true);
                    desiredCapabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

                    if (null==gridHubUrl){
                        //running on local
                        return new InternetExplorerDriver(desiredCapabilities);
                    }
                    else{
                        //running on Selenium Grid
                        desiredCapabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
                        return new RemoteWebDriver(new URL(gridHubUrl), desiredCapabilities);
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
                }

            case HTMLUNIT:
                return new HtmlUnitDriver(true);
            default:
                throw new InvalidParameterException("You must choose one of the defined driver types");

        }
    }

}


