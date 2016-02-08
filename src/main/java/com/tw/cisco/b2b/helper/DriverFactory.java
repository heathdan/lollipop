package com.tw.cisco.b2b.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 04/02/16.
 */
public class DriverFactory {

    protected static WebDriver driver;
    protected static final String URL= "https://t2-qa.xkit.co/";
    //protected static PageHelper helper;

    public DriverFactory() {
        initialize();
        waitAndMaximize();
    }

    public void initialize() {
        if(driver==null) {
            createNewDriverInstance();
        }
    }

    private void createNewDriverInstance() {
        driver = new FirefoxDriver();
    }

    public WebDriver getDriver() {
        return driver;
    }

    protected static void navigateToURL(String URL) {
        try {
            driver.navigate().to(URL);
        } catch (Exception e) {
            System.out.println(URL+" not found/accessible");
        }
    }

    private void waitAndMaximize() {
        try {
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.manage().window().maximize();
        } catch (Exception e){
            System.out.println("Browser handle not available");
        }
    }

    public void destroyDriver() {
        driver.quit();
        driver=null;
    }
}
