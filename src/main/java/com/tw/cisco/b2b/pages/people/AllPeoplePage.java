package com.tw.cisco.b2b.pages.people;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.exceptions.ElementNotVisibleInUI;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
import com.tw.cisco.b2b.pages.BasePage;
import com.tw.cisco.b2b.pages.userManagement.ProfilePage;
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
 * Created by aswathyn on 14/03/16.
 */
public class AllPeoplePage extends BasePage<AllPeoplePage> {

    @FindBy(xpath=".//input[@id='peopleSearchBox']")
    private WebElement searchBox;

    @FindBy(xpath=".//h1[text()='All People']")
    private WebElement pageTitle;

    @FindBy(xpath = ".//div[@id='view-option-buttons']/a[@id='catalog-grid-view']")
    private WebElement gridView;

    @FindBy(xpath = ".//div[@id='view-option-buttons']/a[@id='catalog-list-view']")
    private WebElement listView;

    @FindBy(xpath= ".//i[@class='icon icon-webex']")
    private WebElement webexIcon;

    @FindBy(xpath=".//i[@class='icon icon-chat-square status-offline']")
    private WebElement jabberIcon;

    @FindBy(xpath=".//div[@class='multi-contact-action']//a[@class='email']")
    private WebElement emailID;

    @FindBy(xpath = ".//i[@class='icon-search']")
    private WebElement searchIcon ;

    @FindBy(xpath=".//span[@class='profile-name']/a")
    private WebElement userName;

    private static final Logger LOGGER = LoggerFactory.getLogger(AllPeoplePage.class);

    public AllPeoplePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(AllPeoplePage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition(){
        return ExpectedConditions.visibilityOf(pageTitle);
    }

    public AllPeoplePage searchUser(String emailID) {
        LOGGER.trace(">> searchUser()");
        try {
            enterText(searchBox, emailID);
            LOGGER.debug("-- Passed value:" + emailID);
            clickIcon(searchIcon, "Search");
            LOGGER.debug("-- Searching:" + emailID);
        } catch(ClickIconNotFoundException | TextElementNotFoundException ex) {
            LOGGER.error("---"+emailID+" not found",ex);
        }
        return new AllPeoplePage(driver);
    }

    public AllPeoplePage verifyEmail(String email) {
        Assert.assertEquals(email, emailID.getText());
        return this;
    }

    public ProfilePage selectUser(String emailId) {
        try {
            waitForElement(ExpectedConditions.textToBePresentInElement(emailID, emailId), emailID);
            LOGGER.info("search and wait : user after wait is :  " +emailID.getText());
            clickIcon(userName, "User profile");
        }catch (ClickIconNotFoundException | ElementNotVisibleInUI ex){
            LOGGER.error("-- User not selectable",ex);
        }
        return new ProfilePage(driver);
    }


    /***********************GET/SET METHODS*********************/

}
