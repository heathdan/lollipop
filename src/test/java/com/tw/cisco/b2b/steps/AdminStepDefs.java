package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
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

    @Then("^user navigates to Roles & Permission tab$")
    public void userNavigatesToRolesPermissionTab() throws Throwable {
        leftNav = new LeftNav(driver);
        leftNav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.ROLESPERMISSIONS);
    }

    @Given("^the user is on Roles and Permissions tab on admin page$")
    public void the_user_is_on_tab_on_admin_page() throws Throwable {
        userNavigatesToRolesPermissionTab();
    }

    @When("^he creates a new role named \"([^\"]*)\" and inherits permissions from \"([^\"]*)\" role$")
    public void he_creates_a_new_role_named_and_inherits_permissions_from_role(String arg1, String arg2) throws Throwable {
        roleCreatePopupPage= roleNpermission.findRoleAndDelete(arg1).clickRoleCreation();
        roleCreatePopupPage.createNewInheritRole(arg1, arg2);
    }

    @When("^he assigns role \"([^\"]*)\" to \"([^\"]*)\"$")
    public void he_assigns_role_to(String arg1, String arg2) throws Throwable {
        assignRolesPopupPage= new UserPage(driver).searchUser(new CommonMethodsHelper().getPropValue(arg2)).clickAssignRole();
        assignRolesPopupPage.deleteAllRoles().clickAssignRole().assignAllRoles(arg1);
    }

    @Given("^the user is on Users tab on admin page$")
    public void the_user_is_on_Users_tab_on_admin_page() throws Throwable {
        leftNav = new LeftNav(driver);
        leftNav.navToAdmin().navToUser().navToTab(TabbedNav.TabName.USERS);
    }

    @And("^user navigates to the Users tab$")
    public void user_navigates_to_the_Users_tab() throws Throwable {
        the_user_is_on_Users_tab_on_admin_page();
    }

    @When("^he uploads a csv file \"([^\"]*)\" to on board user \"([^\"]*)\"$")
    public void he_uploads_a_csv_file_to_on_board_user(String arg1, String arg2) throws Throwable {
        new UserPage(driver).BulkUserUpload(CommonMethodsHelper.getCSVDataForUpload(arg1));
    }

    @Then("^user in \"([^\"]*)\" should be on boarded to the app$")
    public void user_in_should_be_on_boarded_to_the_app(String arg1) throws Throwable {
        String emailID= CommonMethodsHelper.parseCSVData(arg1).get(0).getUname();
        new LeftNav(driver).navToPeople().navToAllPeople().searchUser("\""+emailID+"\"").verifyUserSearch(emailID);


    }
}

