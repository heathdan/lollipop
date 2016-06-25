package com.tw.cisco.b2b.pages.knowledgeCenter;

import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.pages.BasePage;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by aswathyn on 13/03/16.
 */
public class SharedFilesPage extends BasePage<SharedFilesPage> {

    @FindBy(xpath = "//h1[@title='Shared Files']")
    private WebElement sharedFilesWait;

    @FindBy(xpath = ".//div[@class='no-value' and text()=' No results found']")
    private WebElement noResults;

    @FindBy(id="documentCatalogSearchBox")
    private WebElement searchinputField;

    @FindBy(xpath = "//i[@class='icon-search']")
    private WebElement searchIcon;

    @FindBy(xpath = ".//div[@id='file-library-list']//a[@class='eclipse-text view-details']")
    private WebElement sharedFileLink;

    HeaderNav headerNav;
    private static final Logger LOGGER = LoggerFactory.getLogger(SharedFilesPage.class);

    public SharedFilesPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
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
        return ExpectedConditions.visibilityOf(sharedFilesWait);
    }

    public void isFileSharedPresent(String fileName) throws Exception{
        enterText(searchinputField, fileName);
        clickIcon(searchIcon, "Searching file");
        getHeaderNav().waitForSpinnerToStop();
        new SharedFilesPage(driver);
        if(!isElementPresent(noResults)) {
           Assert.assertEquals(sharedFileLink.getText(),fileName);
        } else {
            throw new NoSuchElementException("File Shared not found :"+fileName);
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

