package com.tw.cisco.b2b.pages.userManagement.roleAssignment;

import com.tw.cisco.b2b.exceptions.*;
import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.navigation.TabbedNav;
import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 22/01/16.
 */
public class RoleCreatePopupPage extends BasePage<RoleCreatePopupPage> {

    /*@FindBy(xpath = ".//h4[text()='Create Custom Role']/parent::div/parent::div")*/
    @FindBy(xpath=".//h4[text()='Create Custom Role']")
    private WebElement createRoleHeader;

    @FindBy(xpath = ".//h4[text()='Create Custom Role']/parent::div/parent::div")
    private WebElement createRoleModal;

    @FindBy(xpath = ".//h4[text()='Create Custom Role']")
    private WebElement createRoleModalHeader;

    @FindBy(xpath = ".//h4[text()='Create Custom Role']/parent::div/parent::div//input[1]")
    private WebElement nameTextField;

    @FindBy(xpath = ".//option[text()='Please choose one']")
    private WebElement rolesDropdown;

    @FindBy(xpath = ".//option[text()='Learner']")
    private WebElement selectLearner;

    @FindBy(xpath = ".//option[text()='CatalogAdmin']")
    private WebElement selectCatalogAdmin;

    @FindBy(xpath = ".//option[text()='SystemAdmin']")
    private WebElement selectSystemAdmin;

    @FindBy(xpath = ".//button[text()='Cancel']")
    private WebElement cancelRoleCreation;

    @FindBy(xpath = ".//button[text()='Add Role']")
    private WebElement saveRoleCreation;

    @FindBy(xpath=".//select[@id='roleSelector']")
    private WebElement inheritRoles;

    @FindBy(xpath=".//div[@id='addRole']//h4[text()='Create Custom Role']")
    private WebElement roleSuccessPopupHeader;

    @FindBy(xpath = ".//div[contains(@class,'role-create-success')]/button")
    private WebElement roleSuccessPopUp;

    static final Logger LOGGER = LoggerFactory.getLogger(RoleCreatePopupPage.class);

    private TabbedNav tabNav;
    private HeaderNav headerNav;

    public RoleCreatePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(RoleCreatePopupPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(createRoleHeader);
    }

    public RolesAndPermissionPage createNewInheritRole(String roleName, String inheritRoleName) {
        LOGGER.trace(">> createNewInheritRole():"+roleName);
        try {
            enterText(nameTextField, roleName);
            clickButton(rolesDropdown);
            selectDropdownText(inheritRoles, inheritRoleName);
            clickButton(saveRoleCreation);
            waitForElement(ExpectedConditions.visibilityOf(roleSuccessPopupHeader),roleSuccessPopupHeader);
            clickButton(roleSuccessPopUp);
            new HeaderNav(driver).waitForSpinnerToStop();
        }
        catch(TextElementNotFoundException | ClickElementException |SpinnerNotFoundException|SpinnerNotDisappearException| ElementNotFoundException |ElementNotVisibleInUI ex) {
            LOGGER.error(roleName+" creation failed", ex);
        }
        catch(SelectDropDownNotFoundException ex) {
            LOGGER.error("Role selection failed",ex);
        }
        return new RolesAndPermissionPage(driver);
    }

    public RolesAndPermissionPage createNewRole(String roleName){
        LOGGER.trace(">> createNewRole():"+roleName);
        try {
            enterText(nameTextField, roleName);
            clickButton(saveRoleCreation);
            waitForElement(ExpectedConditions.visibilityOf(roleSuccessPopupHeader), roleSuccessPopupHeader);
            clickButton(roleSuccessPopUp);
            new HeaderNav(driver).waitForSpinnerToStop();
        }catch(TextElementNotFoundException | ClickElementException |SpinnerNotFoundException|SpinnerNotDisappearException| ElementNotFoundException |ElementNotVisibleInUI ex) {
            LOGGER.error(roleName + " creation failed", ex);
        }
        return new RolesAndPermissionPage(driver);
    }


    /***********************GET/SET METHODS*********************/
    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}