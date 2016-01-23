package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 18/01/16.
 */
public abstract class BasePage<P extends BasePage>{

    protected WebDriver driver;
    protected static final long IMPLICIT_WAIT=20;
    protected static final int PAGE_LOAD_TIMEOUT = 30;
    protected static final int POLLING_RATE = 2;
    private static final String PAGE_TITLE="";

    protected static final int SPINNER_TO_APPEAR_TIMEOUT=5;
    protected static final int SPINNER_TO_DISAPPEAR_TIMEOUT=30;
    protected static final int SPINNER_POLLING_RATE=50;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void implicitWaitMethod() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    protected void waitForPageToLoad(ExpectedCondition expectedCondition) {
        Wait wait = new FluentWait(driver)
                .withTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(POLLING_RATE, TimeUnit.SECONDS);
        wait.until(getPageLoadCondition());
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
    protected abstract ExpectedCondition getPageLoadCondition();

    /**
     *
     * Method for child page instantiation
     *
     */
    protected abstract void instantiatePage(P page);

}


