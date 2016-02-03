package com.tw.cisco.b2b.navigation;

import com.tw.cisco.b2b.pages.BasePage;
import com.tw.cisco.b2b.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by aswathyn on 20/01/16.
 */
public class HeaderNav extends BasePage<HeaderNav> {

    private FluentWait<WebDriver> waitForSpinnerAppear,waitForSpinnerDisappear;
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
        return ExpectedConditions.visibilityOf(topNavSearch);
    }

    public LoginPage CKlogout() {
        waitForSpinnerToStop();
        topNavMyProfile.click();
        logOut.click();
        implicitWaitMethod();
        return new LoginPage(driver);
    }

    public void waitForSpinnerToStop() {
        waitForSpinnerAppear= new WebDriverWait(driver, SPINNER_TO_APPEAR_TIMEOUT)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .pollingEvery(SPINNER_POLLING_RATE, TimeUnit.MILLISECONDS);

        waitForSpinnerDisappear = new WebDriverWait(driver, SPINNER_TO_DISAPPEAR_TIMEOUT)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .pollingEvery(SPINNER_POLLING_RATE, TimeUnit.MILLISECONDS);

        try {
            System.out.println("wait for spinner to appear");
            if (waitForSpinnerAppear.until(ExpectedConditions.visibilityOf(spinner)) != null) {
                System.out.println("wait for spinner to disappear");
                waitForSpinnerDisappear.until(ExpectedConditions
                        .invisibilityOfElementLocated(spinnerLocator));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
