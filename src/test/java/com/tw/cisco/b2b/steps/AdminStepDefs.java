package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.CommonMethodsHelper;
import com.tw.cisco.b2b.helper.UserDetails;
import com.tw.cisco.b2b.navigation.HeaderNav;
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
    RolesAndPermissionPage roleNpermission;
    HomePage homePage;
    RoleCreatePopupPage roleCreatePopupPage;
    LeftNav leftNav;
    HeaderNav headerNav;
    AssignRolesPopupPage assignRolesPopupPage;
    ProfilePage profilePage;
    AdminPage adminPage;
    TabbedNav tabbedNav;


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
        //Thread.sleep(2000);
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

    @And("^user navigates to Profile page$")
    public void userNavigatesToProfilePage() throws Throwable {
        headerNav = new HeaderNav(driver);
        headerNav.navToProfile();
    }

    @When("^he creates a new role named \"([^\"]*)\" and inherits permissions from \"([^\"]*)\" role$")
    public void he_creates_a_new_role_named_and_inherits_permissions_from_role(String arg1, String arg2) throws Throwable {
        roleNpermission = new RolesAndPermissionPage(driver);
        roleCreatePopupPage= roleNpermission.findRoleAndDelete(arg1).clickRoleCreation();
        roleCreatePopupPage.createNewInheritRole(arg1, arg2);
    }

    @When("^he assigns role \"([^\"]*)\" to \"([^\"]*)\"$")
    public void he_assigns_role_to(String arg1, String arg2) throws Throwable {
        String learner = new CommonMethodsHelper().getPropValue(arg2);
        assignRolesPopupPage= new UserPage(driver).searchUser("\""+learner+"\"").clickAssignRole(learner);
        assignRolesPopupPage.deleteAllRoles().clickAssignRole(learner).assignAllRoles(arg1);
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

    @And("^the csv \"([^\"]*)\" is updated for Organisation and title$")
    public void the_csv_is_updated_for_Organisation_and_title(String arg1) throws Throwable {
        UserDetails userData= CommonMethodsHelper.parseCSVData(arg1).get(0);
        profilePage =new LeftNav(driver).navToPeople().navToAllPeople().searchUser("\""+userData.getUname()+"\"").selectUser(userData.getUname());
        profilePage.verifyUserTitleAndOrg(userData.getOrg(), userData.getTitle());
    }

    @When("^he uploads a csv file \"([^\"]*)\" to on board user \"([^\"]*)\"$")
    public void he_uploads_a_csv_file_to_on_board_user(String arg1, String arg2) throws Throwable {
        new UserPage(driver).BulkUserUpload(CommonMethodsHelper.getCSVDataForUpload(arg1));
    }

    @Then("^user in \"([^\"]*)\" should be on boarded to the app$")
    public void user_in_should_be_on_boarded_to_the_app(String arg1) throws Throwable {
        String emailID = CommonMethodsHelper.parseCSVData(arg1).get(0).getUname();
        profilePage=new LeftNav(driver).navToPeople().navToAllPeople().searchUser("\""+emailID+ "\"").selectUser(emailID);
        profilePage.verifyEmailID(emailID);
    }

    @Then("^user in \"([^\"]*)\" should be on boarded to the app with no manager assigned.$")
    public void user_in_should_be_on_boarded_to_the_app_with_no_manager_assigned(String arg1) throws Throwable {
        UserDetails userData=CommonMethodsHelper.parseCSVData(arg1).get(0);
        profilePage=new LeftNav(driver).navToPeople().navToAllPeople().searchUser("\""+userData.getUname()+ "\"").selectUser(userData.getUname());
        profilePage.verifyManager(false);
    }

    @Then("^user in \"([^\"]*)\" should be on boarded to the app with manager assigned$")
    public void user_in_should_be_on_boarded_to_the_app_with_manager_assigned(String arg1) throws Throwable {
        UserDetails userData=CommonMethodsHelper.parseCSVData(arg1).get(0);
        profilePage=new LeftNav(driver).navToPeople().navToAllPeople().searchUser("\""+userData.getUname()+ "\"").selectUser(userData.getUname());
        profilePage.verifyManager(true);
    }

    @Then("^user should be assigned \"([^\"]*)\" permission under \"([^\"]*)\"$")
    public void userShouldBeAssignedPermissionUnder(String arg0, String arg1) throws Throwable {
        adminPage = new LeftNav(driver).navToAdmin();
        if(arg1.equals("User")) {
            tabbedNav = adminPage.navToUser();
            tabbedNav.getHeaderNav().waitForSpinnerToStop();
        } else if(arg1.equals("System")) {
            tabbedNav = adminPage.navToSystem();
        }
       tabbedNav.isTabPresent(arg0);
    }

    @Then("^delete \"([^\"]*)\" role created$")
    public void deleteRoleCreated(String arg0) throws Throwable {
        userNavigatesToRolesPermissionTab();
        roleNpermission = new RolesAndPermissionPage(driver).findRoleAndDelete(arg0);
    }
}

