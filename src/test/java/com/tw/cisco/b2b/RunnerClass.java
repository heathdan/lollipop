package com.tw.cisco.b2b;

import com.tw.cisco.b2b.pages.LoginPage;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;


/**
 * Created by aswathyn on 23/01/16.
 */
@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/com.tw.cisco.b2b",
        glue = {"src/test/java/com/tw/cisco/b2b/stepDefinitions"},
        format= {"pretty","json:target/cucumber-report.json", "html:targer/cucumber-report"})

public class RunnerClass {
        private static final String URL= "https://t2-qa.xkit.co/";

        WebDriver driver;
        LoginPage loginPage;

        @BeforeClass
        public void start() throws Exception {
                driver = new FirefoxDriver();
                driver.navigate().to(URL);
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                driver.manage().window().maximize();
        }

        @AfterClass
        public void shutDown() throws Exception{
                driver.close();
        }

}
