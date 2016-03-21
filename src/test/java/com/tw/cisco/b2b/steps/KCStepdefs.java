package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by chandrad on 3/20/16.
 */
public class KCStepdefs {
    static final Logger LOGGER = LoggerFactory.getLogger(KCStepdefs.class);
    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    LeftNav leftNav;
    CommonMethodsHelper commonMethodsHelper;


    public KCStepdefs(SharedDriver driver) {
        this.driver = driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);
    }

    @Then("^user navigates to \"([^\"]*)\" page$")
    public void userNavigatesToPage(String arg0) throws Throwable {
        leftNav = new LeftNav(driver);
        leftNav.navToKC().navToMyFiles();


    }
}
