package com.tw.cisco.b2b.stepDefinitions;

import com.tw.cisco.b2b.helper.PageHelper;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 03/02/16.
 */
public class LoginSteps {
    WebDriver driver;
    PageHelper helper;

    public LoginSteps() {
        this.driver=Hooks.driver;
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        helper= new PageHelper(driver);
        helper.ckLogin(arg2, arg3);
    }
}
