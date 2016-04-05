package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.*;
import com.tw.cisco.b2b.navigation.HeaderNav;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by aswathyn on 22/01/16.
 */
public class UserPage extends BasePage<UserPage> {

    @FindBy(className ="admin-tab-users")
    private WebElement userTable;

    @FindBy(className = "icon-caret-down")
    private WebElement filter ;

    @FindBy(xpath = ".//a[@class='sorter-toggle'][@data-icon-before='sort-descending']")
    private WebElement sortDescending ;

    @FindBy(xpath = ".//a[@class='sorter-toggle'][@data-icon-before='sort-ascending']")
    private WebElement sortAscending ;

    @FindBy(id = "qa_automation_User Name")
    private WebElement filterByUserName ;

    @FindBy(id = "qa_automation_Full Name")
    private WebElement filterByFullName ;

    @FindBy(xpath = ".//i[@class='icon-search']")
    private WebElement searchIcon ;

    @FindBy(id="peopleSearchBox")
    private WebElement searchField;

    @FindBy(xpath = ".//button[@data-original-title='Bulk Assign Expertise']")
    private WebElement bulkAssignExpertise;

    @FindBy(xpath = ".//button[@data-original-title='Bulk Create Users']")
    private WebElement bulkCreateUsers;

    @FindBy(xpath=".//div[@class='modal-create-header'])")
    private WebElement bulkCreateUsersPopup;

    @FindBy(xpath=".//div[@class='modal-create-header']//i[@class='icon-remove close']")
    private WebElement bulkUploadClose;

    @FindBy(xpath=".//input[@type='file']")
    private WebElement uploadFileButton;

    @FindBy(xpath=".//div[@class='modal-body clearfix']//button[@type='submit']")
    private WebElement userUploadSubmit;

    @FindBy(id = "fromDate")
    private WebElement fromTextField;

    @FindBy(id = "toDate")
    private WebElement toTextField;

    @FindBy(xpath = ".//button[contains(text(),'Filter')]")
    private WebElement calendarFilter;

    @FindBy(xpath = ".//button[contains(text(),'Clear')]")
    private WebElement calendarClear;

    @FindBy(xpath = ".//button[contains(@class,'icon-x-thin')]")
    private WebElement deactivateUser;

    @FindBy(xpath = ".//button[contains(@class,'icon-checkmark-rounded')]")
    private WebElement activateUser;

    @FindBy(xpath = ".//h5[text()='Status']")
    private  WebElement status;

    @FindBy(xpath = ".//p[@class='item-email']")
    private WebElement userEmail;

    @FindBy(xpath = "(.//div[@class='item-wrap container']//p)[2]")
    private WebElement fullName;

    @FindBy(xpath = "(.//div[@class='item-wrap container']//p)[3]")
    private WebElement lastLoginDate;

    @FindBy(xpath = "(.//div[@class='item-wrap container']//p)[4]")
    private WebElement dateCreated;

    @FindBy(xpath = "(.//div[@class='item-wrap container']//p)[5]")
    private WebElement dateModified;

    @FindBy(xpath = "(.//div[@class='item-wrap container']//p)[6]")
    private WebElement userStatus;

    @FindBy(xpath = ".//button[@data-original-title='Assign Expertise']")
    private WebElement assignExpertisePopupicon;

    @FindBy(xpath=".//button[@data-original-title='Assign Roles']")
    private WebElement assignRolesPopupicon;

    @FindBy(xpath = ".//ul[@class='pagination']/li/a[text()='← Previous']")
    private WebElement paginationPrevious;

    @FindBy(xpath =".//ul[@class='pagination']//a[text()='Next →']")
    private WebElement paginationNext;

    static final Logger LOGGER = LoggerFactory.getLogger(UserPage.class);

    HeaderNav headerNav;
    public UserPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
      //  headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(UserPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(userTable);
    }

    public UserPage searchUser(String emailID) throws InterruptedException {
        LOGGER.trace(">> searchUser()");
        try {
            enterText(searchField,emailID);
            LOGGER.debug("-- Passed value:" + emailID);
            clickIcon(searchIcon, "Search");
            LOGGER.debug("-- Searching:" + emailID);
        } catch(ClickIconNotFoundException | TextElementNotFoundException ex) {
            LOGGER.error("---"+emailID+" not found",ex);
        }
        return new UserPage(driver);
    }

    public UserPage searchByExpertise(String expertise) {
        try {
            LOGGER.info("searching the expertise text" + expertise);
            enterText(searchField, expertise);
            clickIcon(searchIcon, "Search by Expertise");
            LOGGER.info("after search the value for page fatory for email is   \""+userEmail.getText()+" \" " );
        } catch (ClickIconNotFoundException | TextElementNotFoundException ex) {
            LOGGER.error("--- Expertise search failed");
        }
        return new UserPage(driver);
    }

    public AssignExpertisePopupPage clickAssignExpertise(String email){
        try {
            waitForElement(ExpectedConditions.textToBePresentInElement(userEmail,email),userEmail);
            LOGGER.info("after search the value for page fatory for email is   \""+userEmail.getText()+" \" " );
            LOGGER.info("clicking on assign expertise icon");
            clickIcon(assignExpertisePopupicon,"Expertise");
        } catch (ClickIconNotFoundException | ElementNotVisibleInUI ex) {
            LOGGER.error("--- Expertise popup failed", ex);
        }
        return new AssignExpertisePopupPage(driver);
    }

    public String getUserDetails(WebElement item){
        return item.getText();
    }

    public UserPage verifyExpertiseAsignment(String emailId) throws ElementNotVisibleInUI{
        waitForElement(ExpectedConditions.textToBePresentInElement(userEmail,emailId),userEmail);
        LOGGER.info("Verifying the search results matches the email of user");
        Assert.assertEquals("assigned expertise is ", getUserDetails(userEmail), emailId);
        return new UserPage(driver);
    }

    public AssignRolesPopupPage clickAssignRole(String email) throws ElementNotVisibleInUI{
        LOGGER.trace(">> clickAssignRole()");
        try {
            waitForElement(ExpectedConditions.textToBePresentInElement(userEmail,email),userEmail);
            LOGGER.info("after search the value for page fatory for email is   \""+userEmail.getText()+" \" " );
            LOGGER.info("clicking on assign roles icon");
            clickIcon(assignRolesPopupicon,"Roles icon");
        } catch (ClickIconNotFoundException ex) {
            LOGGER.error("--- Assign role popup not found", ex);
        }
        return new AssignRolesPopupPage(driver);
    }

    public UserPage BulkUserUpload(String fileName) throws IOException {
        try{
            LOGGER.trace(">> CSV bulk upload operation >>");
            clickIcon(bulkCreateUsers, "User Bulk Upload");
            waitForElement(ExpectedConditions.visibilityOf(bulkCreateUsersPopup),bulkCreateUsersPopup);
            LOGGER.info("bulk user upload pop up appeared");
            enterText(uploadFileButton, fileName);
            clickButton(userUploadSubmit);
            LOGGER.info("CSV file uploaded");
            Thread.sleep(2000);
            LOGGER.info("closing the bulk upload pop up");
            clickIcon(bulkUploadClose, "Popup Close");
        } catch(ClickIconNotFoundException | TextElementNotFoundException | ElementNotFoundException | ClickElementException | InterruptedException | ElementNotVisibleInUI ex) {
            LOGGER.error("--- CSV upload failed",ex);
        }
        return new UserPage(driver);
    }

    /***********************GET/SET METHODS*********************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
