package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by aswathyn on 13/03/16.
 */
public class SharedFilesPage extends BasePage<SharedFilesPage> {

    public SharedFilesPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(SharedFilesPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }
}

