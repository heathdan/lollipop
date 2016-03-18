package com.tw.cisco.b2b.pages;

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
    private WebElement MyFilesWait;

    @FindBy(id = "catalog-grid-view")
    private WebElement gridLayoutIcon;

    @FindBy(id = "catalog-list-view")
    private WebElement ListLayoutIcon;

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

    @FindBy(xpath = "//p[@class='item-title eclipse-text']/a")
    private WebElement fileLink;

    @FindBy(xpath = "//p[@class='subtitle item-size']")
    private WebElement fileSize;

    @FindBy(xpath = "//span[text()='Modified ']/following-sibling::p[@class='subtitle modf-date']")
    private WebElement fileModifiedDate;

    @FindBy(xpath = "//span[text()='Modified ']/following-sibling::p[@class='subtitle modf-time']")
    private WebElement FileModifiedTime;

    @FindBy(xpath = "//span[text()='Uploaded ']/following-sibling::p[@class='subtitle modf-date']")
    private WebElement fileUploadDate;

    @FindBy(xpath = "//span[text()='Uploaded ']/following-sibling::p[@class='subtitle modf-time']")
    private WebElement fileUploaddTime;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Download']")
    private WebElement DownloadIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Properties']")
    private WebElement PropertiesIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Share']")
    private WebElement ShareIcon;

    @FindBy(xpath = "//ul[@class='file-actions']//a[@data-original-title='Delete']")
    private WebElement DeleteIcon;

    @FindBy(className = "truncate")
    private WebElement fileTags;







    public MyFilesPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
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
        return ExpectedConditions.visibilityOf(MyFilesWait);
    }
}
