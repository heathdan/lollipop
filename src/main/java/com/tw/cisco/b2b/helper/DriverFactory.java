package com.tw.cisco.b2b.helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by aswathyn on 04/02/16.
 */
public class DriverFactory {

    protected static WebDriver driver = new FirefoxDriver();
    protected static final String URL= "https://t2-qa.xkit.co/";
    protected static PageHelper helper = new PageHelper(driver);

}
