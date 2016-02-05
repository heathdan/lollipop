package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.DriverFactory;
import cucumber.api.java.en.Given;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs extends DriverFactory {

    @Given("^user \"([^\"]*)\" logout$")
    public void user_logout(String arg1) throws Throwable {
        helper.ckLogout();
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        helper.ckLogin(arg2, arg3);
    }
}
