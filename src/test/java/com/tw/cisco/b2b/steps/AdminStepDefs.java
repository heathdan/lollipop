package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.*;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by aswathyn on 16/02/16.
 */
public class AdminStepDefs  {
    static final Logger LOGGER = LoggerFactory.getLogger(AdminStepDefs.class);
    private WebDriver driver;
    AdminPage adminPage;
    RolesAndPermissionPage roleNpermission;
    HomePage homePage;
    RoleCreatePopupPage roleCreatePopupPage;
    LeftNav leftNav;
    AssignRolesPopupPage assignRolesPopupPage;
    UserPage userPage;

    public AdminStepDefs(SharedDriver driver){
        this.driver=driver;
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        LOGGER.info("Started in thread: " + threadId + ", in JVM: " + processName);
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
       roleNpermission=(RolesAndPermissionPage) adminPage.navToUser().navToTab(TabbedNav.TabName.ROLESPERMISSIONS);
    }


    @When("^he creates a new role named \"([^\"]*)\" and inherits permissions from \"([^\"]*)\" role$")
    public void he_creates_a_new_role_named_and_inherits_permissions_from_role(String arg1, String arg2) throws Throwable {
        roleCreatePopupPage= roleNpermission.findRoleAndDelete(arg1).clickRoleCreation();
        roleCreatePopupPage.createNewInheritRole(arg1, arg2);
    }

    @When("^he assigns role \"([^\"]*)\" to \"([^\"]*)\"$")
    public void he_assigns_role_to(String arg1, String arg2) throws Throwable {
        assignRolesPopupPage= new UserPage(driver).searchUser("\"" + arg2 + "\"").clickAssignRole();
        assignRolesPopupPage.deleteAllRoles().clickAssignRole().assignAllRoles(arg1);


    }
}

