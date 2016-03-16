package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aswathyn on 18/01/16.
 */
public abstract class BasePage<P extends BasePage>{

    protected WebDriver driver;
    protected WebDriverWait waitTime;
    private static final String PAGE_TITLE="";
    protected static final long ELEMENT_WAIT=10;
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
    protected void waitForElement(ExpectedCondition expectedCondition,WebElement element) {
        try {
          LOGGER.trace(">> waitForElement()");
            waitTime = new WebDriverWait(driver, ELEMENT_WAIT);
            waitTime.until(expectedCondition);
        } catch (Exception e) {
            LOGGER.error("-- Error in waiting for element",element.toString());
        }
        LOGGER.trace("<< waitForElement()");
    }


    public void enterText(WebElement webElement, String message) throws TextElementNotFoundException {
        if(!(webElement==null)) {
            if(webElement.isDisplayed()) {
                webElement.clear();
                webElement.sendKeys(message);
            } else {
                throw new TextElementNotFoundException("Text element not found");
            }
        }
    }

    public void clickButton(WebElement webElement) throws ClickElementException,ElementNotFoundException {
        if(!(webElement==null)) {
            if(webElement.isDisplayed()) {
                if(webElement.isEnabled()) {
                    webElement.click();
                } else {
                    throw new ClickElementException(webElement.toString()+" not clickable");
                }
            } else {
                throw new ElementNotFoundException(webElement.toString()+ "not visible");
            }
        }
    }

    public void clickByIndex(List<WebElement> element, int index) throws ClickElementException {
       if(!(element==null)) {
           if(element.get(index).isDisplayed()){
               element.get(index).click();
           } else {
               throw new ClickElementException(element.get(index).toString()+" not clicked");
           }
       }
    }

    public void clickIcon(WebElement element,String message) throws ClickIconNotFoundException {
        if (!(element == null)) {
            if (element.isDisplayed()) {
                element.click();
            } else {
                throw new ClickIconNotFoundException(message + " not found");
            }
        }
    }

    public void selectDropdownText(WebElement element, String textValue) throws SelectDropDownNotFoundException {
        try {
            Select selectValue = new Select(element);
            selectValue.selectByVisibleText(textValue);
        } catch(NoSuchElementException ex) {
            throw new SelectDropDownNotFoundException(textValue+" not found", ex);
        }
    }



}


