package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by aswathyn on 01/02/16.
 */
public class AssignExpertisePopupPage extends BasePage<AssignExpertisePopupPage> {

    @FindBys(@FindBy(xpath = ".//div[contains(text(),'Admin Assigned Expertise')]//span[@class='item-tag-text']"))
    private List<WebElement> adminAssignedExpertiseList;

    @FindBys(@FindBy(xpath ="//div[contains(text(),'Admin Assigned Expertise')]//i[@class='icon-remove red']"))
    private List<WebElement> unAssignExpertiseList;

    @FindBys(@FindBy(xpath =  ".//div[@id='selfTaggedExpertise']//span[@class='item-tag-text']"))
    private List<WebElement> selfTaggedExpertiseList ;

    @FindBys(@FindBy(xpath =  ".//div[@id='selfTaggedExpertise']//i[@class='icon-remove red']"))
    private List<WebElement> RemoveSelfTaggedExpertiseList ;

    @FindBys(@FindBy(xpath = "//div[@class='tt-suggestion']\""))
    private List<WebElement> ExpertiseSuggestList ;

    public AssignExpertisePopupPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    public void instantiatePage(AssignExpertisePopupPage page) {
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
