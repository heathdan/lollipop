package com.tw.cisco.b2b.stepDefinitions;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 03/02/16.
 */
public class Hooks {
    private static final String URL= "https://t2-qa.xkit.co/";
    public static WebDriver driver;

    @Before
    public void start() throws Exception {
        driver = new FirefoxDriver();
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

   @After
    public void shutDown() throws Exception{
        driver.close();
    }
}
