package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.*;
import com.tw.cisco.b2b.navigation.HeaderNav;
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
 * Created by chandrad on 2/3/16.
 */
public class DefineExpertisePage extends BasePage<DefineExpertisePage> {

    @FindBy(className = "admin-tab-expertise")
    private WebElement expertiseTable;

    @FindBy(xpath = "//input[@placeholder='Type an area of expertise']")
    private WebElement addExpertiseTextField ;

    @FindBy(xpath = ".//div[@class='admin-filters-panel']//span[@class='add-text-long']")
    private WebElement addExpertiseButton ;

    @FindBy(id="adminSearchBoxvalue")
    private WebElement searchExpertiseTextField ;

    @FindBy(xpath = ".//i[@class='icon-search']")
    private WebElement searchExpertiseIcon ;

    @FindBy(xpath = ".//button[@data-original-title='Bulk Upload']")
    private WebElement bulkUploadExpertiseButton ;

    @FindBy(xpath = "//div[@class='admin-filters-panel']//div[contains(@class,'success fade')]")
    private WebElement expertiseSuccessMessage;

    @FindBy(xpath = ".//div[@id='expertise-topics']//p[contains(@class,'item-topic')]")
    private WebElement expertiseName ;

    @FindBy(xpath = ".//div[@id='expertise-topics']//span[@class='expert-total']")
    private WebElement expertsCount ;

    @FindBy(xpath = ".//div[@id='expertise-topics']//p[contains(@class,'item-created-by')]")
    private WebElement expertiseCreatedBy ;

    @FindBy(xpath = ".//div[@id='expertise-topics']//p[contains(@class,'item-topic')]")
    private WebElement expertiseUpdatedBy ;

    @FindBy(xpath = ".//div[@id='expertise-topics']//p[@class='item-updated-short']")
    private WebElement expertiseLastUpdate;

    @FindBy(xpath = ".//div[@class='actions-wrap']/span[contains(@class,'expertise-action-edit')]")
    private WebElement editExpertiseButton ;

    @FindBy(xpath = ".//div[@class='actions-wrap']/span[contains(@class,'expertise-action-delete')]")
    private WebElement expertiseDeleteButton ;

    @FindBy(className = "no-value")
    private WebElement noResultsMsg;

    static final Logger LOGGER = LoggerFactory.getLogger(DefineExpertisePage.class);

    HeaderNav headerNav;
    public DefineExpertisePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
       // headerNav = new HeaderNav(driver);
    }

    @Override
    public void instantiatePage(DefineExpertisePage page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("-- Error instantiating: ",page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
       return ExpectedConditions.visibilityOf(expertiseTable);
    }

    public DefineExpertisePage addExpertise(String Expertise){
        try {
            LOGGER.info("Creating the expertise\""+Expertise+"\"");
            enterText(addExpertiseTextField, Expertise);

            LOGGER.info("Saving the expertise\""+Expertise+"\"");
            clickIcon(addExpertiseButton, "Expertise");

            waitForElement(ExpectedConditions.visibilityOf(expertiseSuccessMessage),expertiseSuccessMessage);

        } catch ( ClickIconNotFoundException|ElementNotVisibleInUI | TextElementNotFoundException ex) {
            LOGGER.error(" Expertise addition failed", ex);
        }
        return new DefineExpertisePage(driver);
    }

    public DefineExpertisePage searchExpertise(String Expertise){
        LOGGER.trace(">>searchExpertise()",Expertise);
        try {
            LOGGER.info("searching the expertise \"" + Expertise + "\"");
            enterText(searchExpertiseTextField, Expertise);
            clickButton(searchExpertiseIcon);
            LOGGER.info("waiting for search to complete");
        } catch(TextElementNotFoundException | ElementNotFoundException| ClickElementException ex) {
            LOGGER.error("--Error in searching expertise",ex);
        }
        return new DefineExpertisePage(driver) ;
    }

    public DefineExpertisePage verfiyExpertiseAsigned(String Expertise) throws ElementNotVisibleInUI{
        searchExpertise(Expertise);
        waitForElement(ExpectedConditions.textToBePresentInElement(expertiseName,Expertise),expertiseName);
        LOGGER.info("expertise text after search is \""+expertiseName.getText()+"\"");
        Assert.assertEquals("Searched expertise is",expertiseName.getText(),Expertise);
        Assert.assertEquals("Expertise count should increase by one",expertsCount.getText(),"1");
        return new DefineExpertisePage(driver);
    }

    public boolean elementIsPresent(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    public DefineExpertisePage verifyDeleteExpertiseIcon (String Expertise){
        LOGGER.info("Verifying expertiseDeleteButton is not visible");
        Assert.assertFalse(isElementPresent(expertiseDeleteButton));
        return new DefineExpertisePage(driver);
    }
    public DefineExpertisePage deleteUnusedExpertise(String Expertise){
        LOGGER.trace(">>Delete Unused Expertise()", Expertise);
        try {
            waitForElement(ExpectedConditions.textToBePresentInElement(expertiseName,Expertise),expertiseName);
            String exp = waitForIndexing(expertiseName).getText();
            LOGGER.info("expertise text after search is \""+expertiseName.getText()+"\"");
            headerNav = new HeaderNav(driver);
            LOGGER.info("Deleting the expertise");
            clickButton((expertiseDeleteButton));
            LOGGER.info("Verify the expertise is deleted");
            headerNav.waitForSpinnerToStop();
            Assert.assertEquals("No results found", noResultsMsg.getText(),"No results found");
        } catch (ElementNotFoundException  | SpinnerNotFoundException | ElementNotVisibleInUI| SpinnerNotDisappearException| ClickElementException ex) {
            LOGGER.error("--Error in deleting expertise",ex);
        }
        return new DefineExpertisePage(driver);
    }

    public DefineExpertisePage displaySearchResults(){
        LOGGER.info(expertiseName.getText() +
                expertsCount.getText() +
                expertiseCreatedBy.getText() +
                expertiseUpdatedBy.getText() +
                expertiseLastUpdate.getText());
        return new DefineExpertisePage(driver);
    }

    public DefineExpertisePage editExpertise(String newExpertise){
        try {
            clickButton(editExpertiseButton);
        } catch(ClickElementException | ElementNotFoundException ex) {
            LOGGER.error("--- Error in edit expertise",ex);
        }
        return new DefineExpertisePage(driver);
    }

    /*********************GET/SET METHODS***************************/


    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
