package com.tw.cisco.b2b.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

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

    public DefineExpertisePage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(DefineExpertisePage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
       return ExpectedConditions.visibilityOf(expertiseTable);
    }

    public DefineExpertisePage addExpertise(String Expertise){
        addExpertiseTextField.sendKeys(Expertise);
        addExpertiseButton.click();
        return new DefineExpertisePage(driver);
    }

    public DefineExpertisePage searchExpertise(String Expertise){
        searchExpertiseTextField.sendKeys(Expertise);
        searchExpertiseIcon.click();
        return new DefineExpertisePage(driver) ;
    }

    public DefineExpertisePage displaySearchResults(){
        System.out.println( expertiseName.getText() +
        expertsCount.getText() +
        expertiseCreatedBy.getText() +
        expertiseUpdatedBy.getText() +
        expertiseLastUpdate.getText() );
        return new DefineExpertisePage(driver);
    }

    public DefineExpertisePage editExpertise(String newExpertise){
        editExpertiseButton.click();


        return new DefineExpertisePage(driver);
    }



}
