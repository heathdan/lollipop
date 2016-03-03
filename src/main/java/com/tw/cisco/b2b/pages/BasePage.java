package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 18/01/16.
 */
public abstract class BasePage<P extends BasePage>{

    protected WebDriver driver;
    protected WebDriverWait waitTime;
    private static final String PAGE_TITLE="";
    protected static final long ELEMENT_WAIT=5;
    protected static final long IMPLICIT_WAIT=20;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;
    protected static final int SPINNER_TO_APPEAR_TIMEOUT=5;
    protected static final int SPINNER_TO_DISAPPEAR_TIMEOUT=30;
    protected static final int SPINNER_POLLING_RATE=50;

    static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    /**
     * Method to wait for page to get loaded
     * @param expectedCondition
     */
    protected void waitForPageToLoad(ExpectedCondition<?> expectedCondition) {
        try {
            LOGGER.trace(">> waitForPageToLoad()");
            Wait wait = new FluentWait(driver)
                    .withTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                    .pollingEvery(POLLING_RATE, TimeUnit.SECONDS);
            wait.until(getPageLoadCondition());
        }catch (Exception e) {
            LOGGER.error("-- Error in page loading");
        }
        LOGGER.trace("<< waitForPageToLoad()");
    }

    /**
     *
     * Method to get the page title.
     *
     * @return Title for the page loaded
     */
    public  String getPageTitle() {
        return PAGE_TITLE;
    }

    /**
     *
     * Method to get the condition for checking the page load
     *
     * @return ExpectedCondition for the element to be verified.
     */
    protected abstract ExpectedCondition<?> getPageLoadCondition();

    /**
     *
     * Method for child page instantiation
     *
     */
    protected abstract void instantiatePage(P page);

    /**
     * Method for waiting for element to be visible
     *
     */
    protected void waitForElement(ExpectedCondition expectedCondition) {
        try {
          LOGGER.trace(">> waitForElement()");
            waitTime = new WebDriverWait(driver, ELEMENT_WAIT);
            waitTime.until(expectedCondition);
        } catch (Exception e) {
            LOGGER.error("-- Error in waiting for element");
        }
        LOGGER.trace("<< waitForElement()");
    }


    public void enterText(WebElement webElement, String message) throws Exception{
        boolean result=false;
        if(!(webElement==null)) {
            if(webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(message);
                result= true;
            } else {
                throw new Exception();
            }
        }else {
            throw new Exception();
        }

    }

}


