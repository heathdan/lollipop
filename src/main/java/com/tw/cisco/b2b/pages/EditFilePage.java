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


    public EditFilePage verifyFileName(String titleName) {
        LOGGER.info("Verifying title:");
        Assert.assertEquals(titleName,title.getAttribute("value"));
        return this;
    }

    private void enterFileTitle(String fileTitle) throws TextElementNotFoundException {
        enterText(title,fileTitle);
    }

    private void enterFileDesc(String content) throws IframeNotFoundException {
        LOGGER.info("Switching to iframe");
        switchToiFrame(iframeClass);
        LOGGER.info("Passing file description");
        enterTextBy(iframeDescription, content);
        LOGGER.info("switching back from iframe");
        switchBackFromiFrame();
    }

    private void assignTags(String tagName) throws TextElementNotFoundException, ClickElementException , ElementNotFoundException {
        LOGGER.trace(">> assignTags(): ", tagName);
        enterText(tags, tagName);
        waitForElement(ExpectedConditions.visibilityOf(tagSuggestionBox),tagSuggestionBox);
        clickButton(tagSuggestions);
    }

    public KCPropertiesPage modifyFileDetails(String fileTitle, String tagName) throws Exception {
        LOGGER.info("-- Editing file metadata");
        enterFileTitle(fileTitle);
       // enterFileDesc(fileTitle);
        assignTags(tagName);
        clickButton(saveButton);
        return new KCPropertiesPage(driver);
    }

}
