package com.tw.cisco.b2b.navigation;

import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 20/01/16.
 */
public class HeaderNav extends BasePage<HeaderNav> {
    @FindBy(css="#navbar")
    private WebElement topNavBar;

    @FindBy(xpath=".//div[@id='navbar']//input[@id='top-nav-search-box']")
    private WebElement topNavSearch;

    @FindBy(xpath=".//i[@class='icon-caret-down']")
    private WebElement topNavMyProfile;

    @FindBy(xpath=".//li[@class='logout last']/a")
    private WebElement logOut;

    @FindBy(xpath=".//li[@class='ember-view active']/a")
    private WebElement myProfile;

    @FindBy(xpath=".//i[@class='icon-spin icon-spinner-dots loading-icon bigger-230']")
    private WebElement spinner;

    By spinnerLocator = By.xpath(".//i[@class='icon-spin icon-spinner-dots loading-icon bigger-230']");

    public HeaderNav(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(HeaderNav page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(this.getTopNavSearch());
    }

    /***********************GET/SET METHODS*********************/
    public WebElement getLogOut(){
        return logOut;
    }

    public WebElement getTopNavBar(){
        return topNavBar;
    }

    public WebElement getTopNavSearch() {
        return topNavSearch;
    }

    public WebElement getTopNavMyProfile() {
        return topNavMyProfile;
    }

    public WebElement getMyProfile() {
        return myProfile;
    }

    public WebElement getSpinner(){
        return spinner;
    }

    public By getSpinnerLocator() {
        return spinnerLocator;
    }
}
