package com.tw.cisco.b2b.helper;

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

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidParameterException;

/**
 * Created by aswathyn on 04/02/16.
 */
public class DriverFactory {

    public enum Driver {
        FIREFOX,
        CHROME,
        IE,
        HTMLUNIT,
    }

    public static String gridHubUrl = null;

    /*  static {
          gridHubUrl = CommonHelper.getGridHubUrl();
      }
    */

    /**
     *
     * @param driverType
     * @param browserVersion
     * @param platform
     * @return browser
     */
    public static WebDriver getDriver(Driver driverType, String browserVersion, String platform){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (gridHubUrl==null) {
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
