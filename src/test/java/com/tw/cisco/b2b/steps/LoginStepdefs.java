package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs {
    static final Logger LOGGER = LoggerFactory.getLogger(AdminStepDefs.class);
    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    LeftNav leftNav;
    CommonMethodsHelper commonMethodsHelper;

    public LoginStepdefs(SharedDriver driver){
        this.driver=driver;
        loginPage = new LoginPage(driver);
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        commonMethodsHelper = new CommonMethodsHelper();
        loginPage = new LoginPage(driver);
        loginPage.enterEmail(commonMethodsHelper.getPropValue(arg2)).enterCredentials(commonMethodsHelper.getPropValue(arg3));

    }

    @And("^User \"([^\"]*)\" logout$")
    public void User_logout(String arg1) throws Throwable {
        new HeaderNav(driver).CKlogout();
    }

}
