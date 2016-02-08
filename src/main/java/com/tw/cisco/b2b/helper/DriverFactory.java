package com.tw.cisco.b2b.helper;

import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by aswathyn on 04/02/16.
 */
public class DriverFactory {

    protected static WebDriver driver = BasePage.geDriver(BasePage.Driver.CHROME,"40","MAC");
    protected static final String URL= "https://t2-qa.xkit.co/";
    protected static PageHelper helper = new PageHelper(driver);

}
