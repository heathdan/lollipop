package com.tw.cisco.b2b.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 15/03/16.
 */
public class ProfilePage extends BasePage<ProfilePage> {

    @FindBy(xpath = ".//div[@id='user-profile']//h1")
    private WebElement profileHeading;

    @FindBy(xpath = ".//h5[@id='qa-automation-username']")
    private WebElement userName;

    @FindBy(xpath = ".//h5[@id='qa-automation-username']//following-sibling::p[1]")
    private WebElement title;

    @FindBy(xpath = "//h5[@id='qa-automation-username']//following-sibling::p[2]")
    private WebElement org;

    @FindBy(xpath = ".//p[@class='manager-details']//a")
    private WebElement manager;

    @FindBy(xpath = ".//div[@id='contact-info']//a[@class='profile-info-text']")
    private WebElement email;

    By managerElement = By.xpath(".//p[@class='manager-details']//a");

    public ProfilePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(ProfilePage page) {
        try {
            LOGGER.info("Instantiating page: " + page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :" + page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(profileHeading);
    }

    public void verifyUserTitleAndOrg(String orgID, String userTitle) {
        LOGGER.info("userTitle:" + title.getText());
        LOGGER.info("orgid:" + org.getText());
        Assert.assertEquals(orgID, org.getText());
        Assert.assertEquals(userTitle, title.getText());
    }

    public void verifyEmailID(String emailID) {
        LOGGER.info("email:" + email.getText());
        Assert.assertEquals(emailID, email.getText());
    }

    public void verifyManager() {
        Assert.assertTrue(isElementPresent());
    }

    public boolean isElementPresent() {
        boolean value=false;
        try {
            value=ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(managerElement)).apply(driver);
        } catch (NoSuchElementException ex) {
            value=true;
        }
        return value;
    }
}
