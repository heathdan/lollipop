package com.tw.cisco.b2b.helper;

import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 04/02/16.
 */
public class DriverFactory {

    protected static WebDriver driver;
    protected static final String URL= "https://t2-qa.xkit.co/";

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
        driver = BasePage.geDriver(BasePage.Driver.FIREFOX,"40","MAC");
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
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            int Width = (int) toolkit.getScreenSize().getWidth();
            int Height = (int)toolkit.getScreenSize().getHeight();
            driver.manage().window().setSize(new org.openqa.selenium.Dimension(Width,Height));
        } catch (Exception e){
            System.out.println("Browser handle not available");
        }
    }

    public void destroyDriver() {
        driver.quit();
        driver=null;
    }
}
