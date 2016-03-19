package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.navigation.TabbedNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 08/02/16.
 */
public class AdminPage extends BasePage<AdminPage> {


    @FindBy(xpath = "//span[text()='Admin']/parent::button[@data-icon2-after='triangle-open']")
    private WebElement adminWait;

    @FindBy(xpath = ".//ul[@class='submenu']//span[text()='User']/parent::a")
    private WebElement user;

    @FindBy(xpath = ".//ul[@class='submenu']//span[text()='System']/parent::a")
    private WebElement system;

    @FindBy(xpath = ".//ul[@class='submenu']//span[text()='Mobile']/parent::a")
    private WebElement mobile;

    @FindBy(xpath = ".//ul[@class='submenu']//span[text()='Collaborate']/parent::a']")
    private WebElement collaborate;

    @FindBy(xpath = "..//ul[@class='submenu']//span[text()='Reporting']/parent::a")
    private WebElement reporting;

    @FindBy(xpath =".//ul[@class='submenu']//span[text()='Personalize Site']/parent::a")
    private WebElement personalieSite;

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminPage.class);

    public AdminPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
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
        LOGGER.trace(">> waiting for Admin dropdown to appear >>");
        return ExpectedConditions.visibilityOf(adminWait);
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
