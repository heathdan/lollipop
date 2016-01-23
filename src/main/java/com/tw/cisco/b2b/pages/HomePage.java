package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.navigation.LeftNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 20/01/16.
 */

public class HomePage extends BasePage<HomePage> {

    private HeaderNav headerNav;
    private LeftNav leftNav;

    @FindBy(xpath=".//div[@class='page-content dashboard-workspace default-landing navbar-fixed skin-4']")
    private WebElement landingPage;

    public static final String HOME_PAGE_TITLE="Cisco";

    public HomePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(this.getLandingPage());
    }

    @Override
    public String getPageTitle() {
        return this.HOME_PAGE_TITLE;
    }

    @Override
    protected void instantiatePage(HomePage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /***********************GET/SET METHODS*********************/

    public WebElement getLandingPage() {
        return landingPage;
    }

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav= headerNav;
    }

    public LeftNav getLeftNav() {
        return leftNav;
    }

    public void setLeftNavHelper(LeftNav leftNavHelper) {
        this.leftNav = leftNavHelper;
    }
}
