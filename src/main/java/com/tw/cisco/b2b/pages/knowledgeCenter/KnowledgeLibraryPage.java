package com.tw.cisco.b2b.pages.knowledgeCenter;

import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 13/03/16.
 */
public class KnowledgeLibraryPage extends BasePage<KnowledgeLibraryPage> {

    @FindBy(xpath=".//h1[@title='Knowledge Library']")
    private WebElement title;

    @FindBy(xpath=".//div[@class='form-search']//input[@id='documentCatalogSearchBox']")
    private WebElement searchBox;

    private static final Logger LOGGER = LoggerFactory.getLogger(KnowledgeLibraryPage.class);

    public KnowledgeLibraryPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(KnowledgeLibraryPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error in instantiating page: "+page);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

}
