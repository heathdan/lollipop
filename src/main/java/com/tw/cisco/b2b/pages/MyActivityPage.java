package com.tw.cisco.b2b.pages;


import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(xpath=".//div[@id='activity']//input[@placeholder='Search activities']")
    private WebElement myActivitiesSearch;

        @FindBy(xpath=(".//div[@class='activity-item-description']//a[2]"))
    private WebElement activityDescription;

    @FindBy(xpath=".//div[@class='form-search']/i")
    private WebElement searchIcon;


    private static final Logger LOGGER = LoggerFactory.getLogger(MyActivityPage.class);

    public MyActivityPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
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

    public MyActivityPage searchActivity(String fileName) throws Exception {
        LOGGER.debug("-- Searching activity for "+fileName);
        enterText(myActivitiesSearch,fileName);
        clickIcon(searchIcon,"Search");
        return new MyActivityPage(driver);
    }

    public void isActivityPresent(String fileName) throws NoSuchElementException{
        if(isElementPresent(activityDescription)) {
            LOGGER.debug("-- capturing file upload activity for "+fileName+" ");
            Assert.assertTrue(activityDescription.getText().contains(fileName));
        }else {
            throw new NoSuchElementException("Activity not found");
        }
    }
}
