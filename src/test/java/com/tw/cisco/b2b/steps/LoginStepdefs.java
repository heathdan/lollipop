package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.SharedDriver;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs {
    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    LeftNav leftNav;

    public LoginStepdefs(SharedDriver driver){
        this.driver=driver;
        loginPage = new LoginPage(driver);
    }

    @Given("^user \"([^\"]*)\" logout$")
    public void user_logout(String arg1) throws Throwable {
        homePage= new HomePage(driver);
        homePage.getHeaderNav().CKlogout();
    }


    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        loginPage.enterEmail(arg2).enterCredentials(arg3);

    }
}
