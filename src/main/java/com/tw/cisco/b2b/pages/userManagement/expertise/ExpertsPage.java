package com.tw.cisco.b2b.pages.userManagement.expertise;

import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 13/03/16.
 */
public class ExpertsPage extends BasePage<ExpertsPage> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpertsPage.class);

    public ExpertsPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(ExpertsPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }
}
