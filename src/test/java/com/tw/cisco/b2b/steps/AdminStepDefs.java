package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.SharedDriver;
import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

/**
 * Created by aswathyn on 16/02/16.
 */
public class AdminStepDefs {


    public class AdminStepdefs  {
        private WebDriver driver;
        AdminPage adminPage;
        RolesAndPermissionPage roleNpermission;
        HomePage homePage;
        RoleCreatePopupPage roleCreatePopupPage;
        LeftNav leftNav;

        public AdminStepdefs(SharedDriver driver){
            this.driver=driver;
            homePage= new HomePage(driver);
        }


        @Given("^user navigates to Define Expertise Tab$")
        public void userNavigatesToDefineExpertiseTab() throws Throwable {
            leftNav = new LeftNav(driver);
            leftNav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.DEFINEEXPERTISE);
        }

        @And("^user navigates to the Users tab$")
        public void userNavigatesToTheUsersTab() throws Throwable {
            leftNav = new LeftNav(driver);
            leftNav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.USERS);
        }

        @Then("^user navigates to Roles & Permission tab$")
        public void userNavigatesToRolesPermissionTab() throws Throwable {
            leftNav = new LeftNav(driver);
            leftNav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.ROLESPERMISSIONS);
        }

        @Given("^the user is on \"([^\"]*)\" tab on admin page$")
        public void the_user_is_on_tab_on_admin_page(String arg1) throws Throwable {
            //homePage = new HomePage(driver);
            adminPage=homePage.getLeftNav().navToAdmin();
            adminPage.navToUser().navToTab(TabbedNav.TabName.ROLESPERMISSIONS);
        }


        @When("^he creates a new role named \"([^\"]*)\" and inherits permissions from \"([^\"]*)\" role$")
        public void he_creates_a_new_role_named_and_inherits_permissions_from_role(String arg1, String arg2) throws Throwable {
            roleCreatePopupPage= roleNpermission.findRoleAndDelete(arg1).clickRoleCreation();
            roleCreatePopupPage.createNewInheritRole(arg1, arg2);
        }


    }
}
