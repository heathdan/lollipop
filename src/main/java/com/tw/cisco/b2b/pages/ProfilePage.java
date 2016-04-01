package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickElementException;
import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.exceptions.ElementNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
import com.tw.cisco.b2b.navigation.HeaderNav;
import org.junit.Assert;
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

    @FindBy(xpath = ".//h5[@id='qa-automation-username']//following-sibling::p[2]")
    private WebElement org;

    @FindBy(xpath = ".//p[@class='manager-details']//a")
    private WebElement manager;

    @FindBy(xpath = ".//div[@id='contact-info']//a[@class='profile-info-text']")
    private WebElement email;

    @FindBy(xpath = ".//h4[text()=' Expertise']/parent::div//button")
    private WebElement editSaveIcon;

    @FindBy(xpath = ".//input[@class='tt-query' and @placeholder='Start typing to choose an Area of Expertise...']")
    private WebElement expertiseTextField;

    @FindBy(xpath = ".//div[@class='tt-suggestion']//p")
    private WebElement expertiseSuggest;

    @FindBy(xpath = ".//div[@class='success' and text()='Area of Expertise added successfully.']")
    private WebElement expertiseSuccessMessage;

    HeaderNav headerNav;

    public ProfilePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(ProfilePage page) {
        try {
            LOGGER.info("** instantiating page: " + page.getClass().getSimpleName());
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

    public void verifyManager(boolean value) {
        if(value) {
            Assert.assertTrue(isElementPresent(manager));
        } else {
            Assert.assertFalse(isElementPresent(manager));
        }
    }

    public HomePage selfTagExpertise(String expertise) {
        LOGGER.trace(">> Self Tagging expertise on Profile page");
        try {
            LOGGER.info("Editing the expertise section on profile page");
            clickIcon(editSaveIcon, "Edit Expertise icon on Profile Page");
            LOGGER.info("entering the time stamped expertise");
            enterText(expertiseTextField, expertise);
            LOGGER.info("waiting for the expertise auto suggest");
            waitForElement(ExpectedConditions.visibilityOf(expertiseSuggest), expertiseSuggest);
            clickButton(expertiseSuggest);
            LOGGER.info("waiting for success message");
            waitForElement(ExpectedConditions.visibilityOf(expertiseSuccessMessage), expertiseSuccessMessage);
            getHeaderNav().navToHome();
        } catch (ClickIconNotFoundException | TextElementNotFoundException | ElementNotFoundException | ClickElementException ex) {
            LOGGER.error("--error in self tagging expertise", ex);
        }
        return new HomePage(driver);
    }

    /************************GET/SET METHODS******************************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
