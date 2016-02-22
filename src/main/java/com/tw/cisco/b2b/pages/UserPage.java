package com.tw.cisco.b2b.pages;

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
    private WebElement waitforsearch;

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
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(UserPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(userTable);
    }

    public UserPage searchUser(String emailID){
        LOGGER.trace(">> searchUser()");
        searchField.sendKeys(emailID);
        LOGGER.debug("-- Passed value:" + emailID);
        searchIcon.click();
        LOGGER.debug("-- Searching:" + emailID);
        LOGGER.info("------"+waitforsearch.getText());
        waitForElement(ExpectedConditions.textToBePresentInElement(waitforsearch,emailID));
        //waitForElement(ExpectedConditions.visibilityOf(waitforsearch));
        return new UserPage(driver);
    }

    public UserPage searchByExpertise(String expertise){
        searchField.clear();
        searchField.sendKeys(expertise);
        searchIcon.click();
        return new UserPage(driver);
    }

    public AssignExpertisePopupPage clickAssignExpertise(){
        assignExpertisePopupicon.click();
        return new AssignExpertisePopupPage(driver);
    }

    public String  getUserDetails(WebElement item){

       /* System.out.println("chandra" + " " + userEmail.getText());
        System.out.println("Full Name" + " " +fullName.getText());
        System.out.println("Date Modified" + " " +dateModified.getText());
        System.out.println("Date Created" + " " +dateCreated.getText());
        System.out.println("LastLoggedIn" + " " +lastLoginDate.getText());
        System.out.println("Status" + " " +userStatus.getText());
        return new UserPage(driver);
     */ return item.getText();
    }

    public UserPage verifyExpertiseAsignment(String emailId){
        //Assert.assertEquals(getUserDetails(userEmail),emailId);
        Assert.assertEquals("assigned expertise is ", getUserDetails(userEmail), emailId);
        return new UserPage(driver);
    }

    public AssignRolesPopupPage clickAssignRole(){
        LOGGER.trace(">> clickAssignRole()");
        assignRolesPopupicon.click();
        return new AssignRolesPopupPage(driver);
    }

    /***********************GET/SET METHODS*********************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
