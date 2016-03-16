package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.navigation.TabbedNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 08/02/16.
 */
public class AdminPage extends BasePage<AdminPage> {

    @FindBy(xpath = ".//span[text()='User']")
    private WebElement user;

    @FindBy(xpath = ".//span[text()='System']")
    private WebElement system;

    @FindBy(xpath = ".//span[text()='Mobile']")
    private WebElement mobile;

    @FindBy(xpath = ".//span[text()='Collaborate']")
    private WebElement collaborate;

    @FindBy(xpath = ".//span[text()='Reporting']")
    private WebElement reporting;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPage.class);

    public AdminPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(AdminPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
           LOGGER.error("--- Error in instantiating page: ",page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    public TabbedNav navToUser() {
        try {
            LOGGER.info("Navigating to User");
            clickIcon(user, "Users");
        } catch(ClickIconNotFoundException ex) {
            LOGGER.error("Users not found", ex);
        }
        return new TabbedNav(driver);
    }

    public TabbedNav navToSystem() {
        LOGGER.info("Navigating to System");
        system.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToMobile() {
        LOGGER.info("Navigating to Mobile");
        mobile.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToCollaborate() {
        LOGGER.info("Navigating to Collaborate");
        collaborate.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToReporting() {
        LOGGER.info("Navigating to Reporting");
        reporting.click();
        return new TabbedNav(driver);
    }
}
