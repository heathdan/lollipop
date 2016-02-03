package com.tw.cisco.b2b.stepDefinitions;

import com.tw.cisco.b2b.helper.PageHelper;
import com.tw.cisco.b2b.stepDefinitions.BaseSteps;
import cucumber.api.java.en.Given;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 03/02/16.
 */
public class LoginSteps extends BaseSteps {
    private WebDriver driver;
    PageHelper pageHelper;
    public LoginSteps(WebDriver driver) {
        this.driver = driver;
        pageHelper = new PageHelper(driver);
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        pageHelper.ckLogin(arg2,arg3);
    }
}
