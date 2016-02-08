package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.SystemClock;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.List;

/**
 * Created by aswathyn on 22/01/16.
 */
public class UserPage extends BasePage<UserPage> {

    @FindBy(className ="admin-table-content")
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

    @FindBy(xpath = ".//p[@class='item-email'and text()='mahone_7@mailinator.com']")
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
    private WebElement assignExpertisePopupicon ;

    @FindBy(xpath = ".//ul[@class='pagination']/li/a[text()='← Previous']")
    private WebElement paginationPrevious;

    @FindBy(xpath =".//ul[@class='pagination']//a[text()='Next →']")
    private WebElement paginationNext;

    public UserPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
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
        searchField.sendKeys(emailID);
        searchIcon.click();
        WebElement b = new WebDriverWait(driver,15).until(ExpectedConditions.visibilityOf(waitforsearch));
        return new UserPage(driver);

    }

    public AssignExpertisePopupPage clickAssignExpertise(){
        assignExpertisePopupicon.click();
        return new AssignExpertisePopupPage(driver);
    }

    public UserPage  getUserDetails(){

        System.out.println("chandra" + " " + userEmail.getText());
        System.out.println("Full Name" + " " +fullName.getText());
        System.out.println("Date Modified" + " " +dateModified.getText());
        System.out.println("Date Created" + " " +dateCreated.getText());
        System.out.println("LastLoggedIn" + " " +lastLoginDate.getText());
        System.out.println("Status" + " " +userStatus.getText());
        return new UserPage(driver);

    }



    /***********************GET/SET METHODS*********************/

    public WebElement getFilter() {
        return filter;
    }

    public WebElement getSortDescending() {
        return sortDescending;
    }

    public WebElement getSortAscending() {
        return sortAscending;
    }

    public WebElement getFilterByUserName() {
        return filterByUserName;
    }

    public WebElement getFilterByFullName() {
        return filterByFullName;
    }

    public WebElement getSearchIcon() {
        return searchIcon;
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public WebElement getBulkAssignExpertise() {
        return bulkAssignExpertise;
    }

    public WebElement getBulkCreateUsers() {
        return bulkCreateUsers;
    }

    public WebElement getFromTextField() {
        return fromTextField;
    }

    public WebElement getToTextField() {
        return toTextField;
    }

    public WebElement getCalendarFilter() {
        return calendarFilter;
    }

    public WebElement getCalendarClear() {
        return calendarClear;
    }

    public WebElement getDeactivateUser() {
        return deactivateUser;
    }

    public WebElement getActivateUser() {
        return activateUser;
    }

    public WebElement getUserEmail() {
        return userEmail;
    }

    public WebElement getFullName() {
        return fullName;
    }

    public WebElement getLastLoginDate() {
        return lastLoginDate;
    }

    public WebElement getDateCreated() {
        return dateCreated;
    }

    public WebElement getDateModified() {
        return dateModified;
    }

    public WebElement getUserStatus() {
        return userStatus;
    }

    public WebElement getPaginationPrevious() {
        return paginationPrevious;
    }

    public WebElement getPaginationNext() {
        return paginationNext;
    }
}
