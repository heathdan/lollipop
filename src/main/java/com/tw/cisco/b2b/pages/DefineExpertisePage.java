package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.exceptions.TextElementNotFoundException;
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

    static final Logger LOGGER = LoggerFactory.getLogger(DefineExpertisePage.class);

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
        try {
            enterText(addExpertiseTextField,Expertise);
            clickIcon(addExpertiseButton,"Expertise");
        } catch ( ClickIconNotFoundException | TextElementNotFoundException ex) {
            LOGGER.error(" Expertise addition failed", ex);
        }
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
