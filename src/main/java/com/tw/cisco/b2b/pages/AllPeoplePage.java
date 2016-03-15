package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
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

    @FindBy(xpath="(.//span[@class='profile-name']//parent::div)")
    private WebElement userTitle;

    @FindBy(xpath="(.//div[@class='content-section']//span)[1]")
    private WebElement organisation;

    static final Logger LOGGER = LoggerFactory.getLogger(AllPeoplePage.class);
    HeaderNav headerNav;

    public AllPeoplePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        //headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(AllPeoplePage page) {
        try {
            LOGGER.info("Instanting "+page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
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

    public void verifyUserSearch(String emailId) {
        waitForElement(ExpectedConditions.textToBePresentInElement(emailID,emailId));
        Assert.assertEquals(emailId, emailID.toString());
    }

    public void verifyUserOrgAndTitle(String emailId,String orgID, String title) {
        waitForElement(ExpectedConditions.textToBePresentInElement(emailID,emailId));
        LOGGER.info("userTitle:"+userTitle.getText());
        LOGGER.info("orgid:" + organisation.getText());
        Assert.assertEquals(orgID, organisation.getText());
        Assert.assertTrue(userTitle.getText().contains(title));
    }

    /***********************GET/SET METHODS*********************/
    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }

}
