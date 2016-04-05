package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.exceptions.SpinnerNotDisappearException;
import com.tw.cisco.b2b.exceptions.SpinnerNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
import com.tw.cisco.b2b.navigation.HeaderNav;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by aswathyn on 13/03/16.
 */
public class MyFilesPage extends BasePage<MyFilesPage> {

    @FindBy(xpath = "//h1[@title='My Files']")
    private WebElement myFilesWait;

    @FindBy(id = "catalog-grid-view")
    private WebElement gridLayoutIcon;

    @FindBy(id = "catalog-list-view")
    private WebElement listLayoutIcon;

    @FindBy(xpath = "//a[@data-icon-before='sort-descending']")
    private WebElement sortDescending;

    @FindBy(xpath = "//a[@data-icon-before='sort-ascending']")
    private WebElement sortAscending;

    @FindBy(xpath = "//span[@class='icon-caret-down']")
    private WebElement sortMenuIcon;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[text()='Type']")
    private WebElement sortbyType;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[text()='Name']")
    private WebElement sortbyName;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[text()='Size']")
    private WebElement sortbySize;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[text()='Modified']")
    private WebElement sortbyModified;

    @FindBy(xpath = "//ul[@class='dropdown-menu']/li/a[text()='Uploaded']")
    private WebElement sortbyUploaded;

    @FindBy(xpath = "//div[@class='category' and text()='documentType:']")
    private WebElement filterbyDocumentType;

    @FindBy(id="documentCatalogSearchBox")
    private WebElement searchinputField;

    @FindBy(xpath = "//i[@class='icon-search']")
    private WebElement searchIcon;

    @FindBy(xpath = "//li[@class='views-filter']/input[@value='all']")
    private WebElement filterByAllRadioButton;

    @FindBy(xpath = "//li[@class='views-filter']/input[@value='liked']")
    private WebElement filterByLikeRadioButton;

    @FindBy(xpath = "//li[@class='views-filter']/input[@value='favorited']")
    private WebElement filterByFavouriteRadioButton;

    @FindBy(xpath = "//li[@class='views-filter']/input[@value='followed']")
    private WebElement filterByFollowedRadioButton;

    @FindBy(xpath = ".//div[@id='file-library-list']//a[@class='eclipse-text view-details']")
    private WebElement searchfileLink;

    @FindBy(xpath = "//p[@class='subtitle item-size']")
    private WebElement fileSize;

    @FindBy(xpath = "//span[text()='Modified ']/following-sibling::p[@class='subtitle modf-date']")
    private WebElement fileModifiedDate;

    @FindBy(xpath = "//span[text()='Modified ']/following-sibling::p[@class='subtitle modf-time']")
    private WebElement fileModifiedTime;

    @FindBy(xpath = "//span[text()='Uploaded ']/following-sibling::p[@class='subtitle modf-date']")
    private WebElement fileUploadDate;

    @FindBy(xpath = "//span[text()='Uploaded ']/following-sibling::p[@class='subtitle modf-time']")
    private WebElement fileUploaddTime;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Download']")
    private WebElement downloadIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Properties']")
    private WebElement propertiesIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Share']")
    private WebElement shareIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Delete']")
    private WebElement deleteIcon;

    @FindBy(className = "truncate")
    private WebElement fileTags;

    @FindBy(xpath = ".//div[@class='filter-wrapper']//button[@class='btn upload-btn']")
    private WebElement uploadFile;

    @FindBy(xpath = ".//div[@class='no-value' and text()=' No results found']")
    private WebElement noResults;

    @FindBy(xpath=".//div[@id='confirmation']//h4[text()='Delete Files']")
    private WebElement deletePopupHeader;

    @FindBy(xpath=".//button[@id='confirmButton']")
    private WebElement confirmDelete;

    @FindBy(xpath=".//div[@id='messageDiv']/p")
    private WebElement deletionSuccessMessage;

    @FindBy(xpath=".//div[@id='editFileSharing']//h4[text()=' Share Files']")
    private WebElement shareFileHeader;

    @FindBy(xpath=".//div[@class='bootstrap-tagsinput']//input[@placeholder='Enter user/community name']")
    private WebElement shareFileText;

    @FindBy(xpath=".//div[@class='bootstrap-tagsinput']//div[@class='tt-suggestion']")
    private WebElement userSuggestionBox;

    @FindBy(xpath=(".//div[@class='bootstrap-tagsinput']//span[@class='tt-suggestions']//p[1]"))
    private WebElement userSuggestionSelect;

    @FindBy(xpath=".//div[@class='modal-footer']//button[contains(text(),'Save')]")
    private WebElement shareFileSave;

    @FindBy(xpath=".//div[@class='alert alert-success']")
    private WebElement shareFileSuccess;

    @FindBy(xpath=".//div[@id='editFileSharing']//i[@class='icon-remove white']")
    private WebElement shareFilePopupClose;

    private static final String DELETE_SUCCESS_MESSAGE = "Selected file has been deleted successfully.";
    private static final String SHARE_FILE_SUCCESS_MESSAGE="The changes made to sharing have been updated successfully";

    HeaderNav headerNav;

    public MyFilesPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected void instantiatePage(MyFilesPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }

    }
    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(myFilesWait);
    }

    public UploadFilePopupPage navToFileUpload() throws Exception {
        LOGGER.info(">> navToFileUpload()");
        clickButton(uploadFile);
        return new UploadFilePopupPage(driver);
    }

    public void searchAndRefresh(String fileName) throws TextElementNotFoundException,ClickIconNotFoundException,SpinnerNotDisappearException, SpinnerNotFoundException {
        LOGGER.info("Searching the file :"+fileName);
        String searchFileName = null ;
        enterText(searchinputField, fileName);
        clickIcon(searchIcon, "Searching file");
        getHeaderNav().waitForSpinnerToStop();
        new MyFilesPage(driver);

        if(isElementPresent(noResults)) {
            searchFileName = waitForIndexing(searchfileLink).getText();
        } else {
            searchFileName = searchfileLink.getText();
        }
        Assert.assertEquals(fileName,searchFileName);
    }

    public MyFilesPage searchAndDeleteFile(String fileName) throws Exception{
        LOGGER.info("searchAndDeleteFile() :"+fileName);
        enterText(searchinputField, fileName);
        clickIcon(searchIcon, "Searching file");
        getHeaderNav().waitForSpinnerToStop();
        Assert.assertEquals(fileName,new MyFilesPage(driver).searchfileLink.getText());
        clickIcon(deleteIcon,"Deleting file");
        waitForElement(ExpectedConditions.visibilityOf(deletePopupHeader),deletePopupHeader);
        clickButton(confirmDelete);
        getHeaderNav().waitForSpinnerToStop();
        return this;
    }

    public void isDeleteSuccess() {
        if(isElementPresent(deletionSuccessMessage)) {
            Assert.assertEquals(DELETE_SUCCESS_MESSAGE,deletionSuccessMessage.getText());
        }
    }

    public MyFilesPage shareFile(String userName) throws Exception{
        clickIcon(shareIcon,"Share File");
        waitForElement(ExpectedConditions.visibilityOf(shareFileHeader),shareFileHeader);
        enterText(shareFileText,userName);
        getHeaderNav().waitForSpinnerToStop();
        waitForElement(ExpectedConditions.visibilityOf(userSuggestionBox),userSuggestionBox);
        LOGGER.info("Sharing with user: "+userSuggestionSelect.getText());
        clickButton(userSuggestionSelect);
        clickButton(shareFileSave);
        waitForElement(ExpectedConditions.visibilityOf(shareFileSuccess),shareFileSuccess);
        Assert.assertEquals(SHARE_FILE_SUCCESS_MESSAGE,shareFileSuccess.getText());
        clickIcon(shareFilePopupClose,"Closing share file popup");
        return new MyFilesPage(driver);
    }

    public KCPropertiesPage navToFileProperties()throws ClickIconNotFoundException {
        clickIcon(propertiesIcon,"Properties Page");
        return new KCPropertiesPage(driver);
    }

    /***********************GET/SET METHODS*********************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
