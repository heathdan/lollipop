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

    public UploadFilePopupPage navToFileUpload() {
        uploadFile.click();
        return new UploadFilePopupPage(driver);
    }

    public void searchAndRefresh(String fileName) {
        String searchFileName = null ;
        try {
            enterText(searchinputField, fileName);
            clickIcon(searchIcon, "Searching file");
            getHeaderNav().waitForSpinnerToStop();
            searchFileName= new MyFilesPage(driver).waitForIndexing(searchfileLink).getText();
            Assert.assertEquals(fileName,searchFileName);
        } catch (ClickIconNotFoundException | TextElementNotFoundException |SpinnerNotFoundException | SpinnerNotDisappearException ex) {
            LOGGER.error("-- Error in searching file",ex);
        }
    }

    /***********************GET/SET METHODS*********************/

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
