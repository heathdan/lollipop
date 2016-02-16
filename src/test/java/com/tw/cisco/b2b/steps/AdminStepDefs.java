package com.tw.cisco.b2b.steps;

import com.tw.cisco.b2b.helper.SharedDriver;
import com.tw.cisco.b2b.pages.AdminPage;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.RoleCreatePopupPage;
import com.tw.cisco.b2b.pages.RolesAndPermissionPage;
import cucumber.api.java.en.Given;
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

        public AdminStepdefs(SharedDriver driver){
            this.driver=driver;
            homePage= new HomePage(driver);
        }


        @Given("^the user is on \"([^\"]*)\" tab on admin page$")
        public void the_user_is_on_tab_on_admin_page(String arg1) throws Throwable {
            adminPage=homePage.getLeftNav().navToAdmin();
            //roleNpermission=(RolesAndPermissionPage)adminPage.navToUser().navToTab(arg1);
        }


        @When("^he creates a new role named \"([^\"]*)\" and inherits permissions from \"([^\"]*)\" role$")
        public void he_creates_a_new_role_named_and_inherits_permissions_from_role(String arg1, String arg2) throws Throwable {
            roleCreatePopupPage= roleNpermission.findRoleAndDelete(arg1).clickRoleCreation();
            roleCreatePopupPage.createNewInheritRole(arg1, arg2);
        }
    }
}
