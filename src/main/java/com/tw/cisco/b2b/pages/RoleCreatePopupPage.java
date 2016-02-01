package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by aswathyn on 22/01/16.
 */
public class RoleCreatePopupPage extends BasePage<RoleCreatePopupPage> {

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

    @FindBy(className = "role-create-success")
    private WebElement successRolePopUp;

    @FindBy(xpath = ".//div[contains(@class,'role-create-success')]/button")
    private WebElement roleSuccessPopUp;


    public RoleCreatePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(RoleCreatePopupPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    /***********************GET/SET METHODS*********************/

    public WebElement getCreateRoleModal() {
        return createRoleModal;
    }

    public WebElement getCreateRoleModalHeader() {
        return createRoleModalHeader;
    }

    public WebElement getNameTextField() {
        return nameTextField;
    }

    public WebElement getRolesDropdown() {
        return rolesDropdown;
    }

    public WebElement getSelectLearner() {
        return selectLearner;
    }

    public WebElement getSelectCatalogAdmin() {
        return selectCatalogAdmin;
    }

    public WebElement getSelectSystemAdmin() {
        return selectSystemAdmin;
    }

    public WebElement getCancelRoleCreation() {
        return cancelRoleCreation;
    }

    public WebElement getSaveRoleCreation() {
        return saveRoleCreation;
    }

    public WebElement getSuccessRolePopUp() {
        return successRolePopUp;
    }

    public WebElement getRoleSuccessPopUp() {
        return roleSuccessPopUp;
    }
}