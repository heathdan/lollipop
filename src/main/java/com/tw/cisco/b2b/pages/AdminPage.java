package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.navigation.TabbedNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

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


    public AdminPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(AdminPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    public TabbedNav navToUser() {
        user.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToSystem() {
        system.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToMobile() {
        mobile.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToCollaborate() {
        collaborate.click();
        return new TabbedNav(driver);
    }

    public TabbedNav navToReporting() {
        reporting.click();
        return new TabbedNav(driver);
    }
}
