package com.tw.cisco.b2b.navigation;


import com.tw.cisco.b2b.exceptions.*;
import com.tw.cisco.b2b.pages.BasePage;
import com.tw.cisco.b2b.pages.HomePage;
import com.tw.cisco.b2b.pages.LoginPage;
import com.tw.cisco.b2b.pages.ProfilePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by aswathyn on 20/01/16.
 */
public class HeaderNav extends BasePage<HeaderNav> {

    private FluentWait<WebDriver> waitForSpinnerAppear,waitForSpinnerDisappear;

    @FindBy(css="#navbar")
    private WebElement topNavBar;

    @FindBy(className = "navbar-header")
    private WebElement homeIcon;

    @FindBy(xpath=".//div[@id='navbar']//input[@id='top-nav-search-box']")
    private WebElement topNavSearch;

    @FindBy(xpath=".//i[@class='icon-caret-down']")
    private WebElement topNavMyProfile;

    @FindBy(xpath=".//span[@class='user-name']")
    private WebElement userName;

    @FindBy(xpath = "//li/a[text()='My Account ']")
    private WebElement myAccount;

    @FindBy(xpath = "//li/a[text()='Profile ']")
    private WebElement profile;

    @FindBy(xpath=".//li[@class='logout last']/a")
    private WebElement logOut;

    @FindBy(xpath=".//li[@class='ember-view active']/a")
    private WebElement myProfile;

    @FindBy(xpath=".//i[@class='icon-spin icon-spinner-dots loading-icon bigger-230']")
    private WebElement spinner;

    By spinnerLocator = By.xpath(".//i[@class='icon-spin icon-spinner-dots loading-icon bigger-230']");

    static final Logger LOGGER = LoggerFactory.getLogger(HeaderNav.class);

    public HeaderNav(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(HeaderNav page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error in instantiating "+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(topNavSearch);
    }

    public LoginPage CKlogout()  {
        LOGGER.trace(">> CKlogout()");
        try {
            LOGGER.info("Logging out of CK...");
            clickButton(userName);
            clickButton(logOut);
        } catch (ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--Error in logging out", ex);
        }
        return new LoginPage(driver);
    }

    public void waitForSpinnerToStop() throws SpinnerNotDisappearException, SpinnerNotFoundException{
        waitForSpinnerAppear = new WebDriverWait(driver, SPINNER_TO_APPEAR_TIMEOUT)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .pollingEvery(SPINNER_POLLING_RATE, TimeUnit.MILLISECONDS);

        waitForSpinnerDisappear = new WebDriverWait(driver, SPINNER_TO_DISAPPEAR_TIMEOUT)
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .pollingEvery(SPINNER_POLLING_RATE, TimeUnit.MILLISECONDS);

        try {
            LOGGER.info("wait for spinner to appear");
            if (waitForSpinnerAppear.until(ExpectedConditions.visibilityOf(spinner)) != null) {
                LOGGER.info("wait for spinner to disappear");
                waitForSpinnerDisappear.until(ExpectedConditions.invisibilityOfElementLocated(spinnerLocator));
            } else {
                throw new SpinnerNotFoundException("Spinner not found");
            }
        }catch ( NoSuchElementException |ElementNotVisibleException ex ) {
            throw new SpinnerNotDisappearException("Timeout for spinner to disappear",ex);
        }
    }

    public ProfilePage navToProfile(){
        LOGGER.trace(">> Navigating to Profile Page");
        try{
            LOGGER.info("navigating to profile page from TopNav");
            clickButton(userName);
            clickButton(profile);
        } catch (ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--Error in clicking profile link from TopNav", ex);
        }
        return new ProfilePage(driver);
    }

    public HomePage navToHome(){
        try{
            LOGGER.info("navigating to home page via home Icon");
            clickIcon(homeIcon,"Favicon");
        }catch(ClickIconNotFoundException ex){
            LOGGER.error("-- error in clicking favicon to navigate to homepage");
        }
        return new HomePage(driver);
    }

    public TabbedNav navToMyActivity() throws Exception {
        LOGGER.trace(">>navToMyActivity() ");
        clickButton(userName);
        clickButton(profile);
        return new TabbedNav(driver);
    }




    /***********************GET/SET METHODS*********************/

}
