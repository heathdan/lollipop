package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.SharedDriver;
import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LeftNav;
import com.tw.cisco.b2b.pages.LoginPage;
import com.tw.cisco.b2b.pages.UserPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 04/02/16.
 */
public class LoginStepdefs {
    private WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;

    public LoginStepdefs(SharedDriver driver){
        this.driver=driver;
        loginPage = new LoginPage(driver);
    }

    @Given("^user \"([^\"]*)\" logout$")
    public void user_logout(String arg1) throws Throwable {
        homePage= new HomePage(driver);
        homePage.getHeaderNav().cklogout();
    }

    @Given("^that the user \"([^\"]*)\" logged in as \"([^\"]*)\" and \"([^\"]*)\"$")
    public void that_the_user_logged_in_as_and(String arg1, String arg2, String arg3) throws Throwable {
        loginPage.enterEmail(arg2).enterCredentials(arg3);

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
