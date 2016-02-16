package com.tw.cisco.b2b.helper;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 16/02/16.
 */

public class SharedDriver extends EventFiringWebDriver {
    private static final String URL ="https://t2-qa.xkit.co";
    private static WebDriver REAL_DRIVER;
    private static final Thread CLOSE_THREAD= new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.quit();
        }
    };

    private static WebDriver getRealDriver() {
        if(REAL_DRIVER==null) {
            REAL_DRIVER = DriverFactory.geDriver(DriverFactory.Driver.FIREFOX,"33","MAC");
        }
        return REAL_DRIVER;
    }

    /*static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }*/


    public SharedDriver() {
        super(getRealDriver());
        waitAndMaximize();
        navigateToURL(URL);
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    @Override
    public void close() {
        if(Thread.currentThread()!=CLOSE_THREAD) {
            throw new UnsupportedOperationException("Webdriver is shared. Shouldnot close");
        } try {
            super.close();
        }catch (Throwable e){
            e.printStackTrace();
        }
    }

    protected static void navigateToURL(String URL) {
        try {
            REAL_DRIVER.navigate().to(URL);
        } catch (Exception e) {
            System.out.println(URL + " not found/accessible");
        }
    }

    private static void waitAndMaximize() {
        try {
            REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            REAL_DRIVER.manage().window().maximize();
        } catch (Exception e){
            System.out.println("Browser handle not available");
        }
    }

    /*************************Before/After Cucumber Hooks***************************/

    @Before
    public void deleteAllCookies() {
        manage().deleteAllCookies();
    }

    @After
    public void embedScreenshot(Scenario scenario) {
        scenario.write("Current page URL is " + getCurrentUrl());
        try {
            byte[] screenshot = getScreenshotAs(OutputType.BYTES);
            scenario.embed(screenshot, "image/png");
        } catch (WebDriverException screenshotNotSupported) {
            System.err.println(screenshotNotSupported.getMessage());
        }
    }
}


