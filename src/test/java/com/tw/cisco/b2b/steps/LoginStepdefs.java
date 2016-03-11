package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs {
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
        System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        commonMethodsHelper = new CommonMethodsHelper();
        loginPage.enterEmail(commonMethodsHelper.getPropValue(arg2)).enterCredentials(commonMethodsHelper.getPropValue(arg3));

    }

    @And("^User \"([^\"]*)\" logout$")
    public void User_logout(String arg1) throws Throwable {
        homePage= new HomePage(driver);
        homePage.getHeaderNav().CKlogout();
    }
}
