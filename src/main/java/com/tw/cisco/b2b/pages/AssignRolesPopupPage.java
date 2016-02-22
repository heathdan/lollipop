package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by aswathyn on 01/02/16.
 */
public class AssignRolesPopupPage extends BasePage<AssignRolesPopupPage> {

    @FindBy(xpath=".//div[@id='assignRoles']//h4[@class='modal-title']")
    private WebElement assignRolePopupText;

    @FindBy(xpath=".//div[@class='row roles-footer']//input[@class='tt-query']")
    private WebElement rolePlaceholder;

    @FindBy(xpath=".//div[@class='tt-suggestion']/p")
    private WebElement roleSuggestions;

    @FindBy(xpath=".//div[@class='row roles-footer']//span[@class='tt-suggestions']")
    private WebElement roleSuggestionBox;

    @FindBy(xpath=".//div[@id='assignRoles']//button[@class='btn btn-default']")
    private WebElement saveButton;

    @FindBy(xpath=".//div[@id='assignRoles']//i[@class='icon2-close']")
    private WebElement closeButton;

    @FindBy(xpath=".//div[@id='assignRoles']//i[@class='icon2-delete']")
    private WebElement deleteButton;

    @FindBy(xpath=".//div[@id='assignRoles']//input[@placeholder='Search by Role']")
    private WebElement searchRole;

    @FindBy(xpath=".//div[@id='assignRoles']//div[@class='roles-scroller']//a")
    private List<WebElement> rolesAssigned;

    static final Logger LOGGER = LoggerFactory.getLogger(AssignRolesPopupPage.class);
    private List<String> roles;

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
            LOGGER.info("-- Instantiating "+page.toString());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    protected void assignRole(String roleName) {
        LOGGER.trace(">> AssignRole(): " + roleName);
        rolePlaceholder.sendKeys(roleName);
        waitForElement(ExpectedConditions.visibilityOf(roleSuggestionBox));
        try {
            roleSuggestions.click();
        } catch(Exception e) {
            LOGGER.error("-- Failed to click role from suggestions");
            e.printStackTrace();
        }
        LOGGER.trace("<< AssignRole()");
    }

    public UserPage assignAllRoles(String customRoleName) {
        LOGGER.trace(">> assignAllRoles()");
        assignRole("Learner");
        assignRole(customRoleName);
        saveButton.click();
        LOGGER.trace("<< assignAllRoles()");
        return new UserPage(driver);
    }

    protected void getRolesAssigned() {

    }

    public UserPage deleteAllRoles() {
        LOGGER.trace(">> deleteAllRoles()");
        if (!rolesAssigned.isEmpty()) {
            for (int i = 0; i < rolesAssigned.size(); i++) {
                try {
                    rolesAssigned.get(i).click();
                    LOGGER.debug("-- Deleting role :" + rolesAssigned.get(i).getText());
                    deleteButton.click();
                } catch (Exception e) {
                    LOGGER.error("-- " + rolesAssigned.get(i).getText() + " deletion failed");
                }
            }
            LOGGER.debug("-- Saving roles deleted");
            saveButton.click();
            LOGGER.trace("<< deleteAllRoles()");
            return new UserPage(driver);
        } else {
            LOGGER.debug("-- No roles assigned.Exiting popup");
            closeButton.click();
            LOGGER.trace("<< deleteAllRoles()");
            return new UserPage(driver);
        }
    }
    protected void saveAndCloseRolesPopup() {

    }
}
