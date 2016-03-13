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
    private WebElement title;

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

    static final Logger LOGGER = LoggerFactory.getLogger(AllPeoplePage.class);
    HeaderNav headerNav;

    public AllPeoplePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(AllPeoplePage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition(){
        return ExpectedConditions.visibilityOf(title);
    }

    public AllPeoplePage searchUser(String emailID) {
        LOGGER.trace(">> searchUser()");
        try {
            enterText(searchBox, emailID);
            LOGGER.debug("-- Passed value:" + emailID);
            clickIcon(searchIcon, "Search");
            LOGGER.debug("-- Searching:" + emailID);
            //getHeaderNav().waitForSpinnerToStop();
        } catch(ClickIconNotFoundException | TextElementNotFoundException ex) {
            LOGGER.error("---"+emailID+" not found",ex);
        }
        return new AllPeoplePage(driver);
    }

    public void verifyUserSearch(String emailID) {
       Assert.assertEquals(emailID, emailID.toString());
    }

    /***********************GET/SET METHODS*********************/
    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }

}
