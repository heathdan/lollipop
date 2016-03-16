package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickElementException;
import com.tw.cisco.b2b.exceptions.ElementNotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 08/02/16.
 */
public class KCPage extends BasePage<KCPage> {

    @FindBy(xpath = ".//span[text()='Knowledge Library']")
    private WebElement knowledgeLibrary;

    @FindBy(xpath = ".//span[text()='My Files']")
    private WebElement myFiles;

    @FindBy(xpath = ".//span[text()='Shared Files']")
    private WebElement sharedFiles;

    static final Logger LOGGER = LoggerFactory.getLogger(KCPage.class);

    public KCPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(KCPage page) {
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

    public KnowledgeLibraryPage navToKnowledgeLibrary() {
        LOGGER.trace(">> navToKnowledgeLibrary");
        try {
            LOGGER.info("Navigating to KnowledgeLibrary");
            clickButton(knowledgeLibrary);
        } catch(ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--- KnowledgeLibrary not found",ex);
        }
        return new KnowledgeLibraryPage(driver);
    }

    public MyFilesPage navToMyFiles() {
        LOGGER.trace(">> navToKnowledgeLibrary");
        try {
            LOGGER.info("Navigating to MyFiles");
            clickButton(myFiles);
        } catch(ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--- MyFiles not found",ex);
        }
        return new MyFilesPage(driver);
    }

}
