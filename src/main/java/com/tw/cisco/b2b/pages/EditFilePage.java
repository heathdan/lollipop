package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickElementException;
import com.tw.cisco.b2b.exceptions.ElementNotFoundException;
import com.tw.cisco.b2b.exceptions.IframeNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 17/03/16.
 */
public class EditFilePage extends BasePage<EditFilePage> {

    @FindBy(xpath=".//div[@class='header-panel']//h2[text()='Edit File']")
    private WebElement editPageHeader;

    @FindBy(xpath=".//form[@id='id-message-form']//span[@class='current-file-name']")
    private WebElement currentFileName;

    @FindBy(xpath = ".//form[@id='id-message-form']//input[@placeholder='Title']")
    private WebElement title;

    @FindBy(xpath= ".//iframe[@class='cke_wysiwyg_frame cke_reset']")
    private WebElement iframeClass;

    @FindBy(xpath=".//form[@id='id-message-form']//input[@class='tt-query']")
    private WebElement tags;

    @FindBy(xpath=".//form[@id='id-message-form']//div[@class='tt-suggestion']")
    private WebElement tagSuggestionBox;

    @FindBy(xpath =".//form[@id='id-message-form']//span[@class='tt-suggestions']//p")
    private WebElement tagSuggestions;

    @FindBy(xpath=".//form[@id='id-message-form']//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath=".//form[@id='id-message-form']//button[@type='reset']")
    private WebElement cancelButton;

    By iframeDescription = By.className("cke_editable cke_editable_themed cke_contents_ltr cke_show_borders");

    public EditFilePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(EditFilePage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("-- Error instantiating: ",page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(editPageHeader);
    }


    private boolean verifyFileName(String fileName) {
        if(fileName.equals(currentFileName.getText())) {
            return true;
        } else {
            return false;
        }
    }

    private void enterFileTitle(String fileTitle) {
        try {
            enterText(title,fileTitle);
        } catch(TextElementNotFoundException ex) {
            LOGGER.error("-- Error in locating :"+title.toString());
        }
    }

    private void enterFileDesc(String content) {
        try {
            LOGGER.info("Switching to iframe");
            switchToiFrame(iframeClass);
            LOGGER.info("Passing file description");
            enterTestBy(iframeDescription, content);
            LOGGER.info("switching back from iframe");
            switchBackFromiFrame();
        } catch (IframeNotFoundException ex) {
            LOGGER.error("Switching to iframe failed", ex);
        }
    }

    private void assignTags(String tagName) {
        LOGGER.trace(">> assignTags(): ", tagName);
        try {
            enterText(tags, tagName);
            waitForElement(ExpectedConditions.visibilityOf(tagSuggestionBox),tagSuggestionBox);
            clickButton(tagSuggestions);
        } catch(TextElementNotFoundException| ClickElementException | ElementNotFoundException e) {
            LOGGER.error("-- Failed to click tag name from suggestions", e);
        }
        LOGGER.trace("<< assignTags()");
    }

    public void modifyFileDetails(String fileName, String fileTitle, String tagName) {
        LOGGER.info("-- Editing file metadata");
        Assert.assertTrue(verifyFileName(fileName));
        enterFileTitle(fileTitle);
        enterFileDesc(fileTitle);
        assignTags(tagName);
    }

}
