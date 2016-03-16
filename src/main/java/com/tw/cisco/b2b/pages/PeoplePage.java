package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickElementException;
import com.tw.cisco.b2b.exceptions.ElementNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by aswathyn on 13/03/16.
 */
public class PeoplePage extends BasePage<PeoplePage> {

    @FindBy(xpath = ".//span[text()='All People']")
    private WebElement allPeople;

    @FindBy(xpath = ".//span[text()='Experts']")
    private WebElement experts;

    public PeoplePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(PeoplePage page) {
        try {
            LOGGER.info("** instantiatePage(): ", page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    public AllPeoplePage navToAllPeople() {
        LOGGER.trace(">> navToAllPeople");
        try {
            LOGGER.info("Navigating to navToAllPeople");
            clickButton(allPeople);
        } catch(ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--- navToAllPeople not found",ex);
        }
        return new AllPeoplePage(driver);
    }

    public ExpertsPage navToExperts() {
        LOGGER.trace(">> navToExperts");
        try {
            LOGGER.info("Navigating to navToExperts");
            clickButton(experts);
        } catch(ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--- navToExperts not found",ex);
        }
        return new ExpertsPage(driver);
    }


}
