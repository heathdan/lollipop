package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 29/03/16.
 */
public class MyActivityPage extends BasePage<MyActivityPage> {

    @FindBy(xpath=".//div[@id='user-activity']//h1[text()='My Activities']")
    private WebElement myActivitiesHeader;

    private static final Logger LOGGER = LoggerFactory.getLogger(MyActivityPage.class);

    public MyActivityPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
       // waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(MyActivityPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error in instantiating page: ",page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(myActivitiesHeader);
    }


}
