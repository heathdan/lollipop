package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.navigation.HeaderNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 20/01/16.
 */

public class HomePage extends BasePage<HomePage> {

    private HeaderNav headerNav;
    private LeftNav leftNav;

    @FindBy(className="widget-body-inner")
    private WebElement landingPage;

    public static final String HOME_PAGE_TITLE="Cisco";
    private static final Logger LOGGER = LoggerFactory.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
       // headerNav = new HeaderNav(driver);
       // leftNav = new LeftNav(driver);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(landingPage);
    }

    @Override
    public String getPageTitle() {
        return this.HOME_PAGE_TITLE;
    }

    @Override
    protected void instantiatePage(HomePage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
        }
    }

    /***********************GET/SET METHODS*********************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav= headerNav;
    }

    public LeftNav getLeftNav() {
        return leftNav;
    }

    public void setLeftNav(LeftNav leftNav) {
        this.leftNav = leftNav;
    }
}
