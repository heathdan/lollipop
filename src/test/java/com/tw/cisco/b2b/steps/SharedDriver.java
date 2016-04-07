package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.helper.DriverFactory;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 16/02/16.
 */
public class SharedDriver extends EventFiringWebDriver {

    static final Logger LOGGER = LoggerFactory.getLogger(SharedDriver.class);
    private static WebDriver REAL_DRIVER;
    CommonMethodsHelper commonMethodsHelper;

    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            try {
                REAL_DRIVER.close();
            } catch (WebDriverException e) {
                LOGGER.error("Could not close driver");
            }
        }
    };

    private static WebDriver getRealDriver() {
        if (REAL_DRIVER == null) {
            REAL_DRIVER = DriverFactory.getDriver(DriverFactory.Driver.FIREFOX, "33", "MAC");
        }
        return REAL_DRIVER;
    }

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }


    public SharedDriver() {
        super(getRealDriver());
        maximizeBrowser();
        try {
            navigateToURL(getURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        if (Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("Webdriver is shared. Shouldnot close");
        }
        try {
            super.close();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public String getURL() throws IOException {

        return commonMethodsHelper.getPropValue("env");
    }

    protected static void navigateToURL(String URL) {
        try {
            REAL_DRIVER.navigate().to(URL);
        } catch (Exception e) {
            LOGGER.error("--" + URL + " not found/accessible");
        }
    }

    public void maximizeBrowser() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int Width = (int) toolkit.getScreenSize().getWidth();
        int Height = (int) toolkit.getScreenSize().getHeight();
        REAL_DRIVER.manage().window().setSize(new Dimension(Width, Height));
    }

    private static void waitAndMaximize() {
        try {
            REAL_DRIVER.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            REAL_DRIVER.manage().window().maximize();
        } catch (Exception e) {
            LOGGER.error("-- Browser handle not available");
        }
    }

    /*************************
     * Before/After Cucumber Hooks
     ***************************/

    @Before
    public void setUp(Scenario scenario) {
        this.manage().deleteAllCookies();
        MDC.put("logFileName", scenario.getSourceTagNames().iterator().next());
        LOGGER.info("SCENARIO ===" + scenario.getName());
    }

    @After
    /**
     * Embed a screenshot in test report if test is marked as failed
     */
    public void embedScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            this.manage().deleteAllCookies();

            scenario.write("Current page URL is " + getCurrentUrl());
            try {
                File screenshot = getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot,new File("./target/"+CommonMethodsHelper.timeStamp("screenshot_")+".png"));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } else {
            this.manage().deleteAllCookies();
        }
    }

}
