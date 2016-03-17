package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 17/03/16.
 */
public class UploadFilePopupPage extends BasePage<UploadFilePopupPage> {

    @FindBy(xpath=".//div[@id='uploadNewFile']//h4[@class='modal-title']")
    private WebElement popupHeader;

    @FindBy(xpath=".//button[@type='file']")
    private WebElement chooseFileButton;

    @FindBy(xpath=".//div[@id='uploadNewFile']//input[@class='ember-view ember-text-field form-control']")
    private WebElement title;

    @FindBy(xpath=".//input[@class='tt-query']")
    private WebElement tags;

    @FindBy(xpath=".//div[@id='uploadNewFile']//button[@type='submit']")
    private WebElement uploadButton;

    @FindBy(xpath=".//div[@class='file-upload-success']/p[text()='This action has been completed. The file will be added to My Files.']")
    private WebElement uploadSuccess;

    @FindBy(xpath=".//div[@class='modal-footer file-upload-success']/button[@type='button']")
    private WebElement doneButton;

    @FindBy(xpath=".//div[@class='file-upload-error']/p")
    private WebElement uploadError;

    @FindBy(xpath=".//div[@class='modal-footer file-upload-error']/button[text()='Cancel']")
    private WebElement uploadErrorCancel;

    @FindBy(xpath=".//div[@class='modal-footer file-upload-error']/button[text()='Try Again']")
    private WebElement uploadErrorTryAgain;


    public UploadFilePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(UploadFilePopupPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("-- Error instantiating: ",page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(popupHeader);
    }

    public void uploadFile(String fileName) {

    }
}
