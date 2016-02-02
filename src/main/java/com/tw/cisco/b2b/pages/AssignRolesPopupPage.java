package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by aswathyn on 01/02/16.
 */
public class AssignRolesPopupPage extends BasePage<AssignRolesPopupPage> {

    @FindBy(xpath=".//div[@id='assignRoles']//h4[@class='modal-title']")
    private WebElement assignRolePopupText;

    @FindBy(xpath=".//div[@class='row roles-footer']//input[@class='tt-query']")
    private WebElement rolePlaceholder;

    @FindBys(@FindBy(xpath=".//div[@class='row roles-footer']//span[@class='tt-suggestions'])//p"))
    private List<WebElement> roleSuggestions;

    @FindBy(xpath=".//div[@id='assignRoles']//button[@class='btn btn-default']")
    private WebElement saveButton;

    @FindBy(xpath=".//div[@id='assignRoles']//i[@class='icon2-close']")
    private WebElement closeButton;

    @FindBy(xpath=".//div[@id='assignRoles']//i[@class='icon2-delete']")
    private WebElement deleteButton;

    @FindBy(xpath=".//div[@id='assignRoles']//input[@placeholder='Search by Role']")
    private WebElement searchRole;

    @FindBys(@FindBy(xpath=".//div[@id='assignRoles']//div[@class='roles-scroller']//a"))
    private List<WebElement> rolesAssigned;

    public AssignRolesPopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(this.assignRolePopupText);
    }

    @Override
    protected void instantiatePage(AssignRolesPopupPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    protected void assignNewRole(String roleName ) {


    }

    protected void getRolesAssigned() {

    }

    protected void deleteAllRoles() {

    }

    protected void saveAndCloseRolesPopup() {

    }
}
