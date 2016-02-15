package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.DriverFactory;
import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs extends DriverFactory {

    @Given("^user \"([^\"]*)\" logout$")
    public void user_logout(String arg1) throws Throwable {
        HeaderNav nav = new HeaderNav(driver);
        nav.cklogout();
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        driver.navigate().to(URL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        LoginPage page = new LoginPage(driver);
        page.enterEmail(arg2).enterCredentials(arg3);

    }

    @Given("^user navigates to the Users tab$")
    public void userNavigatesToTheUsersTab() throws Throwable {
        LeftNav nav = new LeftNav(driver);
        nav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.USERS);
    }

    @Then("^user navigates to Roles & Permission tab$")
    public void userNavigatesToRolesPermissionTab() throws Throwable {
        LeftNav nav = new LeftNav(driver);
        nav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.ROLESPERMISSIONS);
    }

    @Then("^user navigates to Define Expertise Tab$")
    public void userNavigatesToDefineExpertiseTab() throws Throwable {
        LeftNav nav = new LeftNav(driver);
        nav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.DEFINEEXPERTISE);
    }

    @Then("^user searches for a user \"([^\"]*)\" and print its details$")
    public void userSearchesForAUserAndPrintItsDetails(String arg0) throws Throwable {
        UserPage page = new UserPage(driver);
        page.searchUser("\""+arg0+"\"");
        page.getUserDetails();
    }

    @And("^user performs operations on expertise popup$")
    public void userPerformsOperationsOnExpertisePopup() throws Throwable {
        UserPage page = new UserPage(driver);
        page.clickAssignExpertise().expertiseOperation("API");

    }

}
