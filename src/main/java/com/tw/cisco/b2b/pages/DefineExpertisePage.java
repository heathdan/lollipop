package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

/**
 * Created by chandrad on 2/3/16.
 */
public class DefineExpertisePage extends BasePage<DefineExpertisePage> {


    @FindBy(id="ember2502")
    private WebElement AddExpertiseTextField ;

    @FindBy(xpath = "//div[@class='admin-filters-panel']//span[@class='add-text-long']")
    private WebElement AddExpertiseButton ;

    @FindBy(id="adminSearchBoxvalue")
    private WebElement SearchExpertiseTextField ;

    @FindBy(xpath = "//i[@class='icon-search']")
    private WebElement SearchExpertiseIcon ;

    @FindBy(xpath = "//button[@data-original-title='Bulk Upload']")
    private WebElement BulkUploadExpertiseButton ;

    @FindBy(xpath = "//div[@id='expertise-topics']//p[contains(@class,'item-topic')]")
    private WebElement ExpertiseName ;

    @FindBy(xpath = "//div[@id='expertise-topics']//span[@class='expert-total']")
    private WebElement ExpertsCount ;


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
        return null;
    }


}
