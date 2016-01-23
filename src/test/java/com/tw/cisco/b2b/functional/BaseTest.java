package com.tw.cisco.b2b.functional;

import com.tw.cisco.b2b.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;


/**
 * Created by aswathyn on 19/01/16.
 */
public class BaseTest {
    private static final String URL= "https://t2-qa.xkit.co/";

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() throws Exception {
        driver = new FirefoxDriver();
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @AfterClass
    public void teardown() throws Exception {
        driver.close();
    }
}
